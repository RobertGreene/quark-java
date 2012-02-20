package com.friendster.api.client.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.friendster.api.client.throwable.FriendsterAPIException;

public class XMLResponseParser implements FriendsterAPIResponseParserInterface {
	private static Logger logger = Logger.getLogger(XMLResponseParser.class);

//	public Document parseResponse(String stringInput) {
//		
//		try {
//			return DocumentBuilderFactory.newInstance().newDocumentBuilder()
//					.parse(new ByteArrayInputStream(stringInput.getBytes()));
//			
//		} catch (SAXException e) {
//			logger.debug(e.getMessage());
//			throw new FriendsterAPIException(e);
//		} catch (IOException e) {
//			logger.debug(e);
//			throw new FriendsterAPIException(e);
//		} catch (ParserConfigurationException e) {
//			logger.debug(e);
//			throw new FriendsterAPIException(e);
//		}
//
//	}

	public Document parseResponse(HttpEntity httpInput) {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			String responseHtml = EntityUtils.toString(httpInput);
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			InputSource iSrc = new InputSource();
			iSrc.setCharacterStream(new StringReader(responseHtml));
			
			logger.info("responseHtml----" + responseHtml);
			
			Document doc = db.parse(iSrc);
			NodeList nodes = doc.getElementsByTagName("error_response");

			if (nodes.getLength() == 0) {
				System.out.println("I have no nodes");
			}
			// iterate the employees
//			for (int i = 0; i < nodes.getLength(); i++) {
//				Element element = (Element) nodes.item(i);
//
//				NodeList name = element.getElementsByTagName("name");
//				Element line = (Element) name.item(0);
//				System.out.println("Name: " + getCharacterDataFromElement(line));
//
//				NodeList title = element.getElementsByTagName("title");
//				line = (Element) title.item(0);
//				System.out.println("Title: "
//						+ getCharacterDataFromElement(line));

			return doc;
		} catch (ParserConfigurationException e) {
			throw new FriendsterAPIException(e);
		} catch (IllegalStateException e) {
			throw new FriendsterAPIException(e);
		} catch (SAXParseException e) {
			logger.info(e.getMessage());
			throw new FriendsterAPIException(e);
		} catch (SAXException e) {
			throw new FriendsterAPIException(e);
		} catch (IOException e) {
			throw new FriendsterAPIException(e);
		}

	}

}