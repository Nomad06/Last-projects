import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.StringWriter;

/**
 * Created by bubal on 05.08.2016.
 */
public class XPathCallerImpl implements XPathCaller {

    private String xPathExpression;

    @Override
    public Element[] getEmployees(Document src, String deptno, String docType) {
        this.xPathExpression = "//employee[@deptno=\"" + deptno + "\"]";
        NodeList nodeList = getNodeList(src);
        return getElements(nodeList);
    }

    private String nodeToString(Node node){
        StringWriter sw = new StringWriter();
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(new DOMSource(node), new StreamResult(sw));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sw.toString();
    }

    private Element[] getElements(NodeList nodeList) {
        int length = nodeList.getLength();
        Element[] elements = new Element[length];
        for (int i = 0; i < length; i++){
            elements[i] = (Element)nodeList.item(i);
        }
        return elements;
    }

    private NodeList getNodeList(Document doc) {
       NodeList nodeList = null;
       try {
           XPath xPath = XPathFactory.newInstance().newXPath();
           XPathExpression xPathExpression = xPath.compile(this.xPathExpression);
           nodeList = (NodeList)xPathExpression.evaluate(doc, XPathConstants.NODESET);
       }
       catch (Exception ex){ex.printStackTrace();}
       return nodeList;
    }

    @Override
    public String getHighestPayed(Document src, String docType) {
        this.xPathExpression = "//employee[not(sal<//employee/sal)]/ename";
        NodeList nodeList = getNodeList(src);
        String name = nodeList.item(0).getTextContent();
        System.out.println(name);
        return name;
    }

    @Override
    public String getHighestPayed(Document src, String deptno, String docType) {
        this.xPathExpression = "//employee[@deptno=\"" + deptno + "\"][not(sal<//employee[@deptno=\"" + deptno + "\"]/sal)]/ename";
        NodeList nodeList = getNodeList(src);
        String name = nodeList.item(0).getTextContent();
        System.out.println(name);
        return name;
    }

    @Override
    public Element[] getTopManagement(Document src, String docType) {
        this.xPathExpression = "//employee[not(@mgr)]";
        NodeList nodeList = getNodeList(src);
        Element[] elements = getElements(nodeList);

        return elements;
    }

    @Override
    public Element[] getOrdinaryEmployees(Document src, String docType) {
        System.out.println("Start getOrdinaryEmployees");
        if(docType.equals("emp")){
            this.xPathExpression = "//employee[not(@empno=//employee/@mgr)]";
        }
        else {
            this.xPathExpression = "//employee[not(job=\"MANAGER\")][not(job=\"PRESIDENT\")][not(child::employee)]";
        }
        NodeList nodeList = getNodeList(src);
        for(int i = 0; i < nodeList.getLength(); i++){
            System.out.println(nodeToString(nodeList.item(i)));
        }
        Element[] elements = getElements(nodeList);
        return elements;
    }

    @Override
    public Element[] getCoworkers(Document src, String empno, String docType) {
        System.out.println("Get coworkers");
        if(docType.equals("emp")){
            this.xPathExpression = "//employee[@mgr = //employee[@empno = \"" + empno + "\"]/@mgr][@empno!=\"" + empno + "\"]";
        }
        else {
            this.xPathExpression = "//employee[@empno = \"" + empno + "\"]/parent::employee//child::employee[not(@empno = \"" + empno + "\")]";
        }

        NodeList nodeList = getNodeList(src);
        NodeList childNodes = null;

        Element[] elements = getElements(nodeList);
        return elements;
    }
}
