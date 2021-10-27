/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.distributed.messaging.google.pubsub.connector;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.security.MessageEncryptor;
import com.liferay.osb.distributed.messaging.subscribing.router.MessageRouter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Amos Fong
 */
public class DefaultMessageReceiver implements MessageReceiver {

	public DefaultMessageReceiver(
		String topic, MessageRouter messageRouter,
		MessageEncryptor messageEncryptor) {

		_topic = topic;
		_messageRouter = messageRouter;
		_messageEncryptor = messageEncryptor;
	}

	@Override
	public void receiveMessage(
		PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {

		ByteString byteString = pubsubMessage.getData();

		String decryptedMessage = _messageEncryptor.decrypt(
			byteString.toStringUtf8());

		if (_log.isDebugEnabled()) {
			_log.debug("Received message " + decryptedMessage);
		}

		try {
			Message message = new Message(decryptedMessage);

			message.setStringAttributes(pubsubMessage.getAttributes());
			message.setTopic(_topic);

			_messageRouter.route(_topic, message);

			ackReplyConsumer.ack();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			ackReplyConsumer.nack();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultMessageReceiver.class);

	private final MessageEncryptor _messageEncryptor;
	private final MessageRouter _messageRouter;
	private final String _topic;

}