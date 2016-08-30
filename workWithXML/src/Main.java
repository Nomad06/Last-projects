import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.*;
import java.util.HashSet;


public class Main {

    public static void main(String[] args) throws IOException, SAXException, XPathExpressionException, ParserConfigurationException, TransformerException {
        System.out.println("Hello World!");
      //SimpleXML simpleXML = new SimpleXMLImpl();
       // simpleXML.createXML("Bi4", "Packet");
        HashSet<Integer> hashSet = new HashSet<>();
        Integer a = new Integer(4);
        Integer b = new Integer(8);
        Integer c = new Integer(4);

        hashSet.add(a);
        hashSet.add(b);
        hashSet.add(c);

        System.out.println(hashSet.size());
        //FileInputStream fileInputStream = new FileInputStream("our.xml");
        // simpleXML.parseRootElement(fileInputStream);
        //Main main = new Main();
        //main.xPathPlaying();


    }

    public void xPathPlaying() throws XPathExpressionException, IOException, ParserConfigurationException, SAXException, TransformerException {
        String expr = "//employee[@deptno=\"10\"]/ename";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("emp.xml");

        XPathCaller xPathCaller = new XPathCallerImpl();
        Element[] elements = xPathCaller.getEmployees(doc, "10", "emp-hire");
        xPathCaller.getHighestPayed(doc, "emp");
        xPathCaller.getHighestPayed(doc, "30", "emp");
        xPathCaller.getTopManagement(doc, "emp");
        xPathCaller.getOrdinaryEmployees(doc, "emp");
        xPathCaller.getCoworkers(doc, "7788", "emp-hire");

        /*XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression xPathExpression = xPath.compile(expr);
        NodeList nodeList = (NodeList)xPathExpression.evaluate(doc, XPathConstants.NODESET);
        System.out.println(nodeList.getLength());
        for(int i = 0; i < nodeList.getLength(); i++){
            System.out.println(nodeToString(nodeList.item(i)));
        }*/
    }

    private String nodeToString(Node node) throws TransformerException {
        StringWriter sw = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.transform(new DOMSource(node), new StreamResult(sw));
        return sw.toString();
    }

}
