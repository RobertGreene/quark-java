package com.friendster.api.client.parser;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.friendster.api.client.throwable.FriendsterAPIException;

public class XMLResponseParser implements FriendsterAPIResponseParserInterface {
	private static Logger logger = Logger.getLogger(XMLResponseParser.class);

	public Document parseResponse(HttpEntity httpInput) {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			String responseHtml = EntityUtils.toString(httpInput);
			DocumentBuilder db = dbf.newDocumentBuilder();

			InputSource iSrc = new InputSource();
			iSrc.setCharacterStream(new StringReader(responseHtml));

			Document doc = db.parse(iSrc);

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

	public String getStringFromDocument(Document doc) {
		try {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			return writer.toString();
		} catch (TransformerException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}