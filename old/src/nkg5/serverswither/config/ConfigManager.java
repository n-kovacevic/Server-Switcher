package nkg5.serverswither.config;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ConfigManager {
	
	// Save config to XML file
	public static void saveConfig(Config conf){
		Document doc;
		Element element = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.newDocument();
			Element root = doc.createElement("root"); 
			for(String key: conf.getProps().keySet()){
				element = doc.createElement("property");
				element.setAttribute("name", key);
				element.setTextContent(conf.getProp(key));
				root.appendChild(element);
			}
			Element servers = doc.createElement("servers");
			for(String key: conf.getServers().keySet()){
				element = doc.createElement("server");
				element.setAttribute("name", key);
				element.setTextContent(conf.getServer(key));
				servers.appendChild(element);
			}
			root.appendChild(servers);
			
			doc.appendChild(root);
			Transformer trans = TransformerFactory.newInstance().newTransformer();
			trans.setOutputProperty(OutputKeys.INDENT, "yes");
			trans.setOutputProperty(OutputKeys.METHOD, "xml");
			trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            trans.transform(new DOMSource(doc), 
                    new StreamResult(new FileOutputStream("config.xml")));
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace();
		} catch (TransformerConfigurationException ex) {
			ex.printStackTrace();
		} catch (TransformerFactoryConfigurationError ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (TransformerException ex) {
			ex.printStackTrace();
		}
	}
	
	// Load config from XML file
	public static Config loadConfig(){
		HashMap<String, String> servers = new HashMap<>();
		HashMap<String, String> props = new HashMap<>();
		Document xmlDoc = loadXML("config.xml");
		
		NodeList properties = xmlDoc.getElementsByTagName("property");
		for(int i=0; i<properties.getLength(); i++){
			String name = properties.item(i).getAttributes().item(0).getNodeValue();
			props.put(name, properties.item(i).getTextContent());
		}
		
		NodeList serverList = xmlDoc.getElementsByTagName("server");
		for(int i=0; i<serverList.getLength(); i++){
			String name = serverList.item(i).getAttributes().item(0).getNodeValue();
			String location = serverList.item(i).getTextContent();
			servers.put(name, location);
		}
		Config conf = new Config();
		conf.setProps(props);
		conf.setServers(servers);
		return conf;
	}
	
	// Parse XML
	private static Document loadXML(String filename) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			return builder.parse(new InputSource(filename));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
