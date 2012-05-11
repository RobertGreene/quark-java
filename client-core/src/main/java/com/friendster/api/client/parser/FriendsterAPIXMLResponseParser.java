package com.friendster.api.client.parser;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.xml.sax.InputSource;

import com.friendster.api.beans.ApplicationFriendsResponse;
import com.friendster.api.beans.FriendsResponse;
import com.friendster.api.beans.GameScoreResponse;
import com.friendster.api.beans.MessageResponse;
import com.friendster.api.beans.NotificationsResponse;
import com.friendster.api.beans.ShoutoutResponse;
import com.friendster.api.beans.UserResponse;
import com.friendster.api.client.enums.RequestType;
import com.friendster.api.client.throwable.FriendsterAPIException;

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
				com.friendster.api.beans.shoutout.ShoutoutResponse gShoutoutResp = serializer
						.read(com.friendster.api.beans.shoutout.ShoutoutResponse.class,
								httpInput.getContent());
				return gShoutoutResp;
			case FRIENDS:
				FriendsResponse friendsResponse = serializer.read(
						FriendsResponse.class, httpInput.getContent());
				return friendsResponse;
			case TOP_SCORES:
				com.friendster.api.beans.topscores.GameScoreResponse topScoreResp = serializer
						.read(com.friendster.api.beans.topscores.GameScoreResponse.class,
								httpInput.getContent());
				return topScoreResp;
			case SCORE:
				GameScoreResponse gameScoreResponse = serializer.read(
						GameScoreResponse.class, httpInput.getContent());
				return gameScoreResponse;
			case MESSAGE:
				com.friendster.api.beans.message.MessageResponse msgResponse = serializer
						.read(com.friendster.api.beans.message.MessageResponse.class,
								httpInput.getContent());
				return msgResponse;
			case MESSAGE_P:
				MessageResponse messageResponse = serializer.read(
						MessageResponse.class, httpInput.getContent());
				return messageResponse;
			case MESSAGES:
				com.friendster.api.beans.messages.MessageResponse messagesResponse = serializer
						.read(com.friendster.api.beans.messages.MessageResponse.class,
								httpInput.getContent());
				return messagesResponse;
			case NOTIFICATION_P:
				NotificationsResponse notificationsResponse = serializer.read(
						NotificationsResponse.class, httpInput.getContent());
				return notificationsResponse;
			case APP_FRIENDS:
				ApplicationFriendsResponse applicationFriendsResponse = serializer
						.read(ApplicationFriendsResponse.class,
								httpInput.getContent());
				return applicationFriendsResponse;
			case WALLET_BALANCE:
				com.friendster.api.beans.WalletResponse walletResponse = serializer
						.read(com.friendster.api.beans.WalletResponse.class,
								httpInput.getContent());
				return walletResponse;

			case WALLET_GET:
				com.friendster.api.beans.wallet.payment.WalletResponse paymentResp = serializer
						.read(com.friendster.api.beans.wallet.payment.WalletResponse.class,
								httpInput.getContent());
				return paymentResp;

			case WALLET_COMMIT:
				com.friendster.api.beans.wallet.commit.WalletResponse commitResp = serializer
						.read(com.friendster.api.beans.wallet.commit.WalletResponse.class,
								httpInput.getContent());
				return commitResp;
			case NEW_MESSAGES:
				com.friendster.api.beans.NewmessagesResponse newMsgsResponse = serializer
						.read(com.friendster.api.beans.NewmessagesResponse.class,
								httpInput.getContent());
				return newMsgsResponse;
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