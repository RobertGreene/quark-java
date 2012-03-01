package com.friendster.api.client.parser;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.sax.SAXSource;

import org.apache.http.HttpEntity;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.friendster.api.client.enums.RequestType;
import com.friendster.api.client.parser.xml.NamespaceFilter;
import com.friendster.api.client.special.AvatarScoreBuilder;
import com.friendster.api.client.special.AvatarScoreResponse;
import com.friendster.api.client.throwable.FriendsterAPIException;
import com.friendster.api.v1.GameScoreResponse;
import com.friendster.api.v1.ShoutoutResponse;
import com.friendster.api.v1.UserResponse;
import com.friendster.api.v1.WalletResponse;
import com.friendster.api.v1.app.ApplicationFriendsResponse;
import com.friendster.api.v1.error.ErrorResponse;
import com.friendster.api.v1.friends.FriendsResponse;
import com.friendster.api.v1.message.MessageResponse;
import com.friendster.api.v1.notification.NotificationsResponse;

@SuppressWarnings("restriction")
public class FriendsterAPIXMLResponseParser implements
		FriendsterAPIResponseParserInterface {
	public Object parseResponse(RequestType requestType,
			HttpEntity httpInput) {
		Object tempObject = null;

		try {
			JAXBContext jc = this.getJAXBContext(requestType);
			Unmarshaller u = jc.createUnmarshaller();
			XMLReader reader = XMLReaderFactory.createXMLReader();
			NamespaceFilter inFilter = this.getNamespaceFilter(requestType);
			inFilter.setParent(reader);
			InputSource is = new InputSource(httpInput.getContent());
			SAXSource source = new SAXSource(inFilter, is);
			tempObject = u.unmarshal(source);
		} catch (JAXBException e) {
			throw new FriendsterAPIException(e);
		} catch (SAXException e) {
			throw new FriendsterAPIException(e);
		} catch (IllegalStateException e) {
			throw new FriendsterAPIException(e);
		} catch (IOException e) {
			throw new FriendsterAPIException(e);
		}

		switch (requestType) {
		case USER:
			return (UserResponse) tempObject;
		case SHOUTOUT_P:
			return (ShoutoutResponse) tempObject;
		case SHOUTOUT:
			return (com.friendster.api.v1.shoutout_list.ShoutoutResponse) tempObject;
		case FRIENDS:
			return (FriendsResponse) tempObject;
		case TOP_SCORES:
			return (AvatarScoreResponse) AvatarScoreBuilder
					.buildAvatarScore((GameScoreResponse) tempObject);
		case SCORE:
			return (com.friendster.api.v1.score.GameScoreResponse) tempObject;
		case MESSAGE:
			return (MessageResponse) tempObject;
		case MESSAGE_P:
			return (MessageResponse) tempObject;
		case MESSAGES:
			return (com.friendster.api.v1.messages_get.MessageResponse) tempObject;
		case NOTIFICATION_P:
			return (NotificationsResponse) tempObject;
		case APP_FRIENDS:
			return (ApplicationFriendsResponse) tempObject;
		case WALLET_BALANCE:
		case WALLET_COMMIT:
		case WALLET_GET:
			return (WalletResponse) tempObject;
		default:
			throw new FriendsterAPIException();
		}
	}

	public ErrorResponse parsePossibleError(HttpEntity httpInput) throws FriendsterAPIException {
		try {
			JAXBContext jc = JAXBContext
					.newInstance("com.friendster.api.v1.error");
			Unmarshaller u = jc.createUnmarshaller();
			XMLReader reader = XMLReaderFactory.createXMLReader();
			NamespaceFilter inFilter = new NamespaceFilter(
					"http://api.friendster.com/v1/error", true);
			inFilter.setParent(reader);
			InputSource is = new InputSource(httpInput.getContent());
			SAXSource source = new SAXSource(inFilter, is);
			return (ErrorResponse) u.unmarshal(source);
		} catch (JAXBException e) {
			throw new FriendsterAPIException();
		} catch (SAXException e) {
			throw new FriendsterAPIException();
		} catch (IllegalStateException e) {
			throw new FriendsterAPIException();
		} catch (IOException e) {
			throw new FriendsterAPIException();
		}

	}

	private JAXBContext getJAXBContext(RequestType requestType)
			throws JAXBException {
		switch (requestType) {
		case APP_FRIENDS:
			return JAXBContext.newInstance("com.friendster.api.v1.app");
		case FRIENDS:
			return JAXBContext.newInstance("com.friendster.api.v1.friends");
		case SCORE:
			return JAXBContext.newInstance("com.friendster.api.v1.score");
		case MESSAGE:
		case MESSAGE_P:
			return JAXBContext.newInstance("com.friendster.api.v1.message");
		case MESSAGES:
			return JAXBContext
					.newInstance("com.friendster.api.v1.messages_get");
		case USER:
		case SHOUTOUT_P:
		case TOP_SCORES:
		case WALLET_BALANCE:
		case WALLET_COMMIT:
		case WALLET_GET:
			return JAXBContext.newInstance("com.friendster.api.v1");
		case SHOUTOUT:
			return JAXBContext.newInstance("com.friendster.api.v1.shoutout_list");
		case NOTIFICATION_P:
			return JAXBContext
					.newInstance("com.friendster.api.v1.notification");
		default:
			throw new FriendsterAPIException();
		}
	}

	private NamespaceFilter getNamespaceFilter(RequestType requestType) {
		switch (requestType) {
		case APP_FRIENDS:
			return new NamespaceFilter("http://api.friendster.com/v1/app", true);
		case FRIENDS:
			return new NamespaceFilter("http://api.friendster.com/v1/friends",
					true);
		case SCORE:
			return new NamespaceFilter("http://api.friendster.com/v1/score",
					true);
		case MESSAGE:
		case MESSAGE_P:
			return new NamespaceFilter("http://api.friendster.com/v1/message",
					true);
		case MESSAGES:
			return new NamespaceFilter(
					"http://api.friendster.com/v1/messages_get", true);
		case USER:
		case TOP_SCORES:
		case SHOUTOUT_P:
		case WALLET_BALANCE:
		case WALLET_COMMIT:
		case WALLET_GET:
			return new NamespaceFilter("http://api.friendster.com/v1/", true);
		case SHOUTOUT:
			return new NamespaceFilter("http://api.friendster.com/v1/shoutout_list", true);
		case NOTIFICATION_P:
			return new NamespaceFilter(
					"http://api.friendster.com/v1/notification", true);
		default:
			throw new FriendsterAPIException();
		}
	}
}