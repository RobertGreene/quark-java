import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.friendster.api.client.parser.XMLResponseParser;

public class XMLTester {

	// public static void main(String[] args) throws IOException {
	// XMLResponseParser xs = new XMLResponseParser();
	// FileInputStream fis = new FileInputStream(new File("test.txt"));
	// StringBuffer sb = new StringBuffer();
	// byte buf[] = new byte[1];
	// while (fis.read(buf) != -1) {
	// sb.append(buf.toString());
	// }
	// xs.parseResponse(sb.toString());
	//
	// }

	public static void main(String arg[]) throws IOException {

		XMLResponseParser xs = new XMLResponseParser();
		BufferedReader br = new BufferedReader(new FileReader(new File(
				"test.xml")));
		String string = new String();
		StringBuffer sb = new StringBuffer();
		while ((string = br.readLine()) != null) {
			sb.append(string);
		}

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(sb.toString()));

			Document doc = db.parse(is);
			System.out.println(doc);
			NodeList nodes = doc.getElementsByTagName("employee");

			if (nodes.getLength() == 0) {
				System.out.println("I have no nodes");
			}
			// iterate the employees
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);

				NodeList name = element.getElementsByTagName("name");
				Element line = (Element) name.item(0);
				System.out.println("Name: " + getCharacterDataFromElement(line));

				NodeList title = element.getElementsByTagName("title");
				line = (Element) title.item(0);
				System.out.println("Title: "
						+ getCharacterDataFromElement(line));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * output : Name: John Title: Manager Name: Sara Title: Clerk
		 */

	}

	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "?";
	}

}
