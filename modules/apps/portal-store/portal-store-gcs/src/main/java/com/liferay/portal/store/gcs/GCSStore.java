/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.store.gcs;

import com.google.api.gax.paging.Page;
import com.google.api.gax.retrying.RetrySettings;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.CopyWriter;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import com.liferay.document.library.kernel.store.BaseStore;
import com.liferay.document.library.kernel.store.Store;
import com.liferay.document.library.kernel.util.comparator.VersionNumberComparator;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.store.gcs.configuration.GCSStoreConfiguration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.ByteBuffer;
import java.nio.channels.Channels;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import org.threeten.bp.Duration;

/**
 * @author Shanon Mathai
 */
@Component(
	configurationPid = "com.liferay.portal.store.gcs.configuration.GCSStoreConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	property = "store.type=com.liferay.portal.store.gcs.GCSStore",
	service = Store.class
)
public class GCSStore extends BaseStore {

	@Override
	public void addDirectory(
		long companyId, long repositoryId, String dirName) {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Liferay GCS adapter does not support creating empty " +
					"directory structures");
		}
	}

	@Override
	public void addFile(
			long companyId, long repositoryId, String fileName, InputStream inputStream)
		throws PortalException {

		addFile(companyId, repositoryId, fileName, VERSION_DEFAULT, inputStream);
	}

	@Override
	public void addFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel, InputStream inputStream)
		throws PortalException {

		if (hasFile(companyId, repositoryId, fileName, versionLabel)) {
			deleteFile(companyId, repositoryId, fileName, versionLabel);
		}

		String path = _keyTransformer.getFileVersionKey(
			companyId, repositoryId, fileName, versionLabel);

		BlobInfo blobInfo = BlobInfo.newBuilder(
			_getBucketInfo(), path
		).build();

		try (WriteChannel writer = _getWriter(blobInfo)) {
			_writeInputStream(inputStream, writer);
		}
		catch (IOException ioException) {
			throw new PortalException(
				"Unable to write out to buffer", ioException);
		}
	}

	@Override
	public void checkRoot(long companyId) {
		if (_log.isDebugEnabled()) {
			_log.debug(
				"Liferay GCS adapter does not support \"check root\" " +
					"operations");
		}
	}

	@Override
	public void deleteDirectory(
		long companyId, long repositoryId, String dirName) {

		String path = _keyTransformer.getDirectoryKey(
			companyId, repositoryId, dirName);

		Page<Blob> blobPage = _gcsStore.list(
			_gcsStoreConfiguration.bucketName(), Storage.BlobListOption.prefix(path));

		Iterable<Blob> blobs = blobPage.iterateAll();

		blobs.forEach(blob -> _deleteBlob(blob));
	}

	@Override
	public void deleteFile(long companyId, long repositoryId, String fileName) {
		deleteFile(companyId, repositoryId, fileName, null);
	}

	@Override
	public void deleteFile(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		String path = _getHeadVersionLabel(
			companyId, repositoryId, fileName, versionLabel);

		_gcsStore.delete(
			BlobId.of(_gcsStoreConfiguration.bucketName(), path));
	}

	@Override
	public InputStream getFileAsStream(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		String pathName = _getHeadVersionLabel(
			companyId, repositoryId, fileName,
			versionLabel);

		Blob blob = _gcsStore.get(
			BlobId.of(
				_gcsStoreConfiguration.bucketName(),
				pathName));

		return Channels.newInputStream(
			_getReader(
				blob));
	}

	@Override
	public String[] getFileNames(long companyId, long repositoryId) {
		return getFileNames(companyId, repositoryId, StringPool.BLANK);
	}

	@Override
	public String[] getFileNames(
		long companyId, long repositoryId, String dirName) {

		String path = null;

		if (Validator.isNull(dirName) ||
			dirName.equals(StringPool.FORWARD_SLASH)) {

			path = _keyTransformer.getRepositoryKey(companyId, repositoryId);
		}
		else {
			path = _keyTransformer.getDirectoryKey(
				companyId, repositoryId, dirName);
		}

		Bucket bucket = _gcsStore.get(_gcsStoreConfiguration.bucketName());

		Page<Blob> blobPage = bucket.list(Storage.BlobListOption.prefix(path));

		Iterable<Blob> blobs = blobPage.iterateAll();

		Stream<Blob> blobStream = StreamSupport.stream(
			blobs.spliterator(), false);

		return blobStream.map(
			BlobInfo::getName
		).toArray(
			String[]::new
		);
	}

	@Override
	public long getFileSize(long companyId, long repositoryId, String fileName)
		throws PortalException {

		String pathName = _getHeadVersionLabel(
			companyId, repositoryId, fileName);

		Blob blob = _gcsStore.get(
			BlobId.of(_gcsStoreConfiguration.bucketName(), pathName));

		if (blob == null) {
			throw new PortalException("No such file store entry: " + pathName);
		}

		return blob.getSize();
	}

	@Override
	public String[] getFileVersions(
		long companyId, long repositoryId, String fileName) {

		return getFileNames(
			companyId, repositoryId,
			_keyTransformer.getFileKey(companyId, repositoryId, fileName));
	}


	@Override
	public boolean hasDirectory(
		long companyId, long repositoryId, String dirName) {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Liferay GCS adapter does not support check for directory, " +
				"returning true");
		}

		return true;
	}

	@Override
	public boolean hasFile(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		String path = _keyTransformer.getFileVersionKey(
			companyId, repositoryId, fileName, versionLabel);

		Page<Blob> blobPage = _gcsStore.list(
			_gcsStoreConfiguration.bucketName(),
			Storage.BlobListOption.pageSize(1),
			Storage.BlobListOption.prefix(
				path));

		Iterable<Blob> filesFoundIterable = blobPage.getValues();

		Iterator<Blob> filesFoundIterator = filesFoundIterable.iterator();

		return filesFoundIterator.hasNext();
	}

	@Override
	public void updateFile(
		long companyId, long repositoryId, long newRepositoryId,
		String fileName) {

		String[] fileNames = getFileNames(companyId, repositoryId, fileName);

		for (String oldPath : fileNames) {
			String version = _getVersionFromFullPath(oldPath);

			Blob oldBlob = _gcsStore.get(
				BlobId.of(_gcsStoreConfiguration.bucketName(), oldPath));

			String newPath = _keyTransformer.getFileVersionKey(
				companyId, newRepositoryId, fileName, version);

			_move(oldBlob, newPath);
		}
	}

	@Override
	public void updateFile(
		long companyId, long repositoryId, String fileName,
		String newFileName) {

		String[] fileNames = getFileNames(companyId, repositoryId, fileName);

		for (String oldPath : fileNames) {
			String version = _getVersionFromFullPath(oldPath);

			String path = _keyTransformer.getFileVersionKey(
				companyId, repositoryId, fileName, version);

			Blob oldBlob = _gcsStore.get(
				BlobId.of(_gcsStoreConfiguration.bucketName(), path));

			String newPath = _keyTransformer.getFileVersionKey(
				companyId, repositoryId, newFileName, version);

			_move(oldBlob, newPath);
		}
	}

	@Override
	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel, InputStream is)
		throws PortalException {

		String path = _keyTransformer.getFileVersionKey(
			companyId, repositoryId, fileName, versionLabel);

		BlobInfo blobInfo = BlobInfo.newBuilder(
			_getBucketInfo(), path
		).build();

		try (WriteChannel writer = _getWriter(blobInfo)) {
			_writeInputStream(is, writer);
		}
		catch (IOException ioException) {
			throw new PortalException(
				"Unable to write out to buffer", ioException);
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {

		try {
			_gcsStoreConfiguration = ConfigurableUtil.createConfigurable(
				GCSStoreConfiguration.class, properties);

			_gcsStore = null;

			_initCryptOptions();

			_initGCSStore();
		}
		catch (PortalException portalException) {
			throw new IllegalStateException(
				"Unable to initialize GCS store", portalException);
		}
	}

	private void _deleteBlob(Blob blob) {
		if (_blobDecryptSourceOption == null) {
			blob.delete();
		}

		blob.delete(_blobDecryptSourceOption);
	}

	private BucketInfo _getBucketInfo() {
		if (_bucketInfo == null) {
			BucketInfo.Builder builder = BucketInfo.newBuilder(
				_gcsStoreConfiguration.bucketName());

			_bucketInfo = builder.build();
		}

		return _bucketInfo;
	}

	private Storage.CopyRequest _getCopyRequest(
		BlobId newBlobId, BlobId oldBlobId,
		Storage.BlobSourceOption sourceOption,
		Storage.BlobTargetOption targetOption) {

		Storage.CopyRequest.Builder copyRequestBuilder =
			Storage.CopyRequest.newBuilder();

		copyRequestBuilder.setSource(oldBlobId);
		copyRequestBuilder.setSourceOptions(sourceOption);
		copyRequestBuilder.setTarget(newBlobId, targetOption);

		return copyRequestBuilder.build();
	}

	private void _setCredentials() throws PortalException {
		try (InputStream inputStream = new FileInputStream(
			_gcsStoreConfiguration.authFileLocation())) {

			_googleCredentials = ServiceAccountCredentials.fromStream(
				inputStream);
		}
		catch (IOException ioException) {
			throw new PortalException(
				"Unable to authenticate with authentication file", ioException);
		}
	}

	private String _getHeadVersionLabel(
		long companyId, long repositoryId, String fileName) {

		return _getHeadVersionLabel(companyId, repositoryId, fileName, null);
	}

	private String _getHeadVersionLabel(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		if (Validator.isNotNull(versionLabel)) {
			return _keyTransformer.getFileVersionKey(
				companyId, repositoryId, fileName, versionLabel);
		}

		String path = _keyTransformer.getFileKey(
			companyId, repositoryId, fileName);

		String[] names = getFileNames(companyId, repositoryId, path);

		if ((names == null) || (names.length == 0)) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						"Unable to determine available versions for: ", path,
						" using default version: ", VERSION_DEFAULT));
			}

			return _keyTransformer.getFileVersionKey(
				companyId, repositoryId, fileName, VERSION_DEFAULT);
		}

		List<String> fileNames = Arrays.asList(names);

		fileNames.sort(new VersionNumberComparator());

		return fileNames.get(fileNames.size() - 1);
	}

	private ReadChannel _getReader(Blob blob) {
		if (_blobDecryptSourceOption == null) {
			return blob.reader();
		}

		return blob.reader(_blobDecryptSourceOption);
	}

	private String _getVersionFromFullPath(String fullPath) {
		int indexOfLastSlash = fullPath.lastIndexOf(StringPool.FORWARD_SLASH);

		return fullPath.substring(indexOfLastSlash + 1);
	}

	private WriteChannel _getWriter(BlobInfo blobInfo) {
		if (_blobEncryptWriteOption == null) {
			return _gcsStore.writer(blobInfo);
		}

		return _gcsStore.writer(blobInfo, _blobEncryptWriteOption);
	}

	private void _move(Blob oldBlob, String newPath) {
		BlobId newBlobId =
			BlobId.of(_gcsStoreConfiguration.bucketName(), newPath);

		BlobId oldBlobId = oldBlob.getBlobId();

		Storage.CopyRequest copyRequest = _getCopyRequest(
			newBlobId, oldBlobId, _storageDecryptionSourceOption,
			_blobEncryptTargetOption);

		CopyWriter copyWriter = _gcsStore.copy(copyRequest);

		// block until complete

		while (!copyWriter.isDone()) {
			copyWriter.copyChunk();
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				StringBundler.concat("Copied ", oldBlob, " to ", newBlobId));
		}

		_deleteBlob(oldBlob);

	}

	private void _initGCSStore() throws PortalException {
		if (_gcsStore == null) {
			try (InputStream inputStream = new FileInputStream(
				_gcsStoreConfiguration.authFileLocation())) {

				_googleCredentials = ServiceAccountCredentials.fromStream(
					inputStream);
			}
			catch (IOException ioException) {
				throw new PortalException(
					"Unable to authenticate with authentication file",
					ioException);
			}

			RetrySettings retrySettings = RetrySettings.newBuilder(
			).setInitialRetryDelay(
				Duration.ofMillis(_gcsStoreConfiguration.initialRetryDelay())
			).setInitialRpcTimeout(
				Duration.ofMillis(_gcsStoreConfiguration.initialRpcTimeout())
			).setJittered(
				_gcsStoreConfiguration.retryJitter()
			).setMaxAttempts(
				_gcsStoreConfiguration.maxRetryAttempts()
			).setMaxRetryDelay(
				Duration.ofMillis(_gcsStoreConfiguration.maxRetryDelay())
			).setMaxRpcTimeout(
				Duration.ofMillis(_gcsStoreConfiguration.maxRpcTimeout())
			).setRetryDelayMultiplier(
				_gcsStoreConfiguration.retryDelayMultiplier()
			).setRpcTimeoutMultiplier(
				_gcsStoreConfiguration.rpcTimeoutMultiplier()
			).build();

			StorageOptions storageOptions = StorageOptions.newBuilder(
			).setCredentials(
				_googleCredentials
			).setRetrySettings(
				retrySettings
			).build();

			_gcsStore = storageOptions.getService();
		}
	}

	private void _initCryptOptions() {
		String key = PropsUtil.get(_DL_STORE_GCS_AES_256_KEY);

		if (Validator.isNull(key)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Property \"dl.store.gcs.aes256.key\" should be set to " +
					"encrypt stored files. Using default storage. The " +
					"key must be AES 256bit key, encoded in Base64.");
			}

			_blobDecryptSourceOption = null;

			_blobEncryptWriteOption = null;
		}
		else {
			_storageDecryptionSourceOption =
				Storage.BlobSourceOption.decryptionKey(key);

			_blobDecryptSourceOption = Blob.BlobSourceOption.decryptionKey(
				key);

			_blobEncryptWriteOption = Storage.BlobWriteOption.encryptionKey(
				key);

			_blobEncryptTargetOption = Storage.BlobTargetOption.encryptionKey(
				key);
		}
	}

	private void _writeInputStream(InputStream inputStream, WriteChannel writer)
		throws IOException, PortalException {

		byte[] buffer = new byte[_WRITE_BUFFER_SIZE];
		int limit = -1;

		while ((limit = inputStream.read(buffer)) >= 0) {
			try {
				writer.write(ByteBuffer.wrap(buffer, 0, limit));
			}
			catch (IOException ioException) {
				throw new PortalException(ioException);
			}
		}
	}


	private static final String _DL_STORE_GCS_AES_256_KEY =
		"dl.store.gcs.aes256.key";

	private static final int _WRITE_BUFFER_SIZE = 1024;

	private static final Log _log = LogFactoryUtil.getLog(GCSStore.class);

	private Blob.BlobSourceOption _blobDecryptSourceOption;
	private Storage.BlobTargetOption _blobEncryptTargetOption;
	private Storage.BlobWriteOption _blobEncryptWriteOption;
	private BucketInfo _bucketInfo;
	private Storage _gcsStore;
	private GCSStoreConfiguration _gcsStoreConfiguration;
	private GoogleCredentials _googleCredentials;
	private final GCSKeyTransformer _keyTransformer = new GCSKeyTransformer();

	private Storage.BlobSourceOption _storageDecryptionSourceOption;

}