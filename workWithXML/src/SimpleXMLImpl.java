import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.InputStream;
import java.io.StringWriter;

public class SimpleXMLImpl extends DefaultHandler implements SimpleXML {

    private DocumentBuilder builder;
    private String rootElement = "";

    @Override
    public String createXML(String tagName, String textNode) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Document document = builder.newDocument();
        Element RootElement = document.createElement(tagName);
        RootElement.appendChild(document.createTextNode(textNode));
        document.appendChild(RootElement);
        String xml = document.toString();
        System.out.println(xml);
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String parseRootElement(InputStream xmlStream) throws SAXException {
        this.rootElement = "";
        InputSource inputSource = new InputSource(xmlStream);
        OwnHadler ownHadler = new OwnHadler();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(false);
            factory.setValidating(true);
            SAXParser parser = factory.newSAXParser();
            parser.parse(inputSource, ownHadler);
            System.out.println(this.rootElement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootElement;
    }

    public class OwnHadler extends DefaultHandler
    {

        private boolean wrongXML = false;

        OwnHadler(){
            super();
        }

        @Override
        public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException
        {
            if(!wrongXML && SimpleXMLImpl.this.rootElement.equals(""))
            {
                SimpleXMLImpl.this.rootElement = qName;
            }
        }

        @Override
        public void fatalError (SAXParseException e) throws SAXException
        {
            System.out.println("Fatal error! Something went wrong!");
            wrongXML = true;
            SimpleXMLImpl.this.rootElement = "";
            throw e;
        }

    }
}
