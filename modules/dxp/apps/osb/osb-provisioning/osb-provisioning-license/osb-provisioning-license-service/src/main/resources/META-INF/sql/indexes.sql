create index IX_A19B1CDC on Provisioning_CommonLicenseKey (fileName[$COLUMN_LENGTH:75$]);
create index IX_20F268B3 on Provisioning_CommonLicenseKey (productGroup[$COLUMN_LENGTH:75$], productEnvironment[$COLUMN_LENGTH:75$], productVersion[$COLUMN_LENGTH:75$], startDate, endDate);
create index IX_31959A75 on Provisioning_CommonLicenseKey (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_EAA5BCC8 on Provisioning_LicenseEntry (name[$COLUMN_LENGTH:75$]);
create unique index IX_CDCDE588 on Provisioning_LicenseEntry (productKey[$COLUMN_LENGTH:75$], type_[$COLUMN_LENGTH:75$]);
create index IX_CB38227B on Provisioning_LicenseEntry (productKey[$COLUMN_LENGTH:75$], versionMin[$COLUMN_LENGTH:75$]);
create index IX_D0969DEC on Provisioning_LicenseEntry (type_[$COLUMN_LENGTH:75$]);

create index IX_658CA000 on Provisioning_LicenseKey (accountKey[$COLUMN_LENGTH:75$], productKey[$COLUMN_LENGTH:75$]);
create index IX_8C69FD3B on Provisioning_LicenseKey (assetReceiptLicenseUuid[$COLUMN_LENGTH:75$], active_);
create index IX_4ADAEB29 on Provisioning_LicenseKey (assetReceiptLicenseUuid[$COLUMN_LENGTH:75$], complimentary, active_);
create index IX_EE607F4B on Provisioning_LicenseKey (assetReceiptLicenseUuid[$COLUMN_LENGTH:75$], productId[$COLUMN_LENGTH:75$], serverId[$COLUMN_LENGTH:4000$], active_);
create index IX_B9071156 on Provisioning_LicenseKey (productId[$COLUMN_LENGTH:75$], serverId[$COLUMN_LENGTH:4000$]);
create index IX_204B38C3 on Provisioning_LicenseKey (productName[$COLUMN_LENGTH:75$], serverId[$COLUMN_LENGTH:4000$], active_);
create index IX_4E05D49D on Provisioning_LicenseKey (productPurchaseKey[$COLUMN_LENGTH:75$], clusterId, active_);
create index IX_25272E34 on Provisioning_LicenseKey (productPurchaseKey[$COLUMN_LENGTH:75$], complimentary, active_);
create index IX_EA2A66B3 on Provisioning_LicenseKey (productPurchaseKey[$COLUMN_LENGTH:75$], licenseEntryType[$COLUMN_LENGTH:75$], complimentary, active_);
create index IX_C2A51B6 on Provisioning_LicenseKey (userUuid[$COLUMN_LENGTH:75$], accountKey[$COLUMN_LENGTH:75$]);
create index IX_424E2ECA on Provisioning_LicenseKey (userUuid[$COLUMN_LENGTH:75$], productId[$COLUMN_LENGTH:75$]);
create index IX_E6B6472A on Provisioning_LicenseKey (uuid_[$COLUMN_LENGTH:75$], companyId);