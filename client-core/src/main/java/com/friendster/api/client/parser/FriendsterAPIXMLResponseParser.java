package com.friendster.api.client.parser;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.xml.sax.InputSource;

import com.friendster.api.beans.FriendsResponse;
import com.friendster.api.beans.GameScoreResponse;
import com.friendster.api.beans.MessageResponse;
import com.friendster.api.beans.NotificationsResponse;
import com.friendster.api.beans.ShoutoutResponse;
import com.friendster.api.client.enums.RequestType;
import com.friendster.api.client.throwable.FriendsterAPIException;
import com.friendster.api.v1.NewmessagesResponse;
import com.friendster.api.v1.UserResponse;
import com.friendster.api.v1.WalletResponse;

public class FriendsterAPIXMLResponseParser implements
		FriendsterAPIResponseParserInterface {

	public Object parseResponse(RequestType requestType, HttpEntity httpInput) {
		Object tempObject = null;

		// try {
		// JAXBContext jc = this.getJAXBContext(requestType);
		// Unmarshaller u = jc.createUnmarshaller();
		// XMLReader reader = XMLReaderFactory.createXMLReader();
		// NamespaceFilter inFilter = this.getNamespaceFilter(requestType);
		// inFilter.setParent(reader);
		// InputSource is = new InputSource(httpInput.getContent());
		// SAXSource source = new SAXSource(inFilter, is);
		// tempObject = u.unmarshal(source);
		// } catch (JAXBException e) {
		// throw new FriendsterAPIException(e);
		// } catch (SAXException e) {
		// throw new FriendsterAPIException(e);
		// } catch (IllegalStateException e) {
		// throw new FriendsterAPIException(e);
		// } catch (IOException e) {
		// throw new FriendsterAPIException(e);
		// }

		try {
			InputSource is = new InputSource(httpInput.getContent());
			Serializer serializer = new Persister();

			switch (requestType) {
			case USER:
				// return (UserResponse) tempObject;
				UserResponse userResp = serializer.read(UserResponse.class,
						httpInput.getContent());
				return userResp;
			case SHOUTOUT_P:
				ShoutoutResponse shoutoutResp = serializer.read(
						ShoutoutResponse.class, httpInput.getContent());
				return shoutoutResp;
			case SHOUTOUT:
				return (com.friendster.api.v1.shoutout_list.ShoutoutResponse) tempObject;
			case FRIENDS:
				FriendsResponse friendsResponse = serializer.read(
						FriendsResponse.class, httpInput.getContent());
				return friendsResponse;
				// case TOP_SCORES:
				// return (AvatarScoreResponse) AvatarScoreBuilder
				// .buildAvatarScore((GameScoreResponse) tempObject);
			case SCORE:
				GameScoreResponse gameScoreResponse = serializer.read(
						GameScoreResponse.class, httpInput.getContent());
				return gameScoreResponse;
			case MESSAGE:
				return (MessageResponse) tempObject;
			case MESSAGE_P:
				MessageResponse messageResponse = serializer.read(
						MessageResponse.class, httpInput.getContent());
				return messageResponse;
			case MESSAGES:
				return (com.friendster.api.v1.messages_get.MessageResponse) tempObject;
			case NOTIFICATION_P:
				NotificationsResponse notificationsResponse = serializer.read(
						NotificationsResponse.class, httpInput.getContent());
				return notificationsResponse;
			case APP_FRIENDS:
				return (com.friendster.api.v1.app.ApplicationFriendsResponse) tempObject;
			case WALLET_BALANCE:
			case WALLET_COMMIT:
			case WALLET_GET:
				return (WalletResponse) tempObject;
			case NEW_MESSAGES:
				return (NewmessagesResponse) tempObject;
			default:
				throw new FriendsterAPIException();
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new FriendsterAPIException(e);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new FriendsterAPIException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FriendsterAPIException(e);
		}
	}
}