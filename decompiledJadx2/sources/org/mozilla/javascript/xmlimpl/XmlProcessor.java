package org.mozilla.javascript.xmlimpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class XmlProcessor implements Serializable {
    private static final long serialVersionUID = 6903514433204808713L;
    private transient LinkedBlockingDeque<DocumentBuilder> documentBuilderPool;
    private transient DocumentBuilderFactory dom;
    private RhinoSAXErrorHandler errorHandler = new RhinoSAXErrorHandler();
    private boolean ignoreComments;
    private boolean ignoreProcessingInstructions;
    private boolean ignoreWhitespace;
    private int prettyIndent;
    private boolean prettyPrint;
    private transient TransformerFactory xform;

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        this.dom = newInstance;
        newInstance.setNamespaceAware(true);
        this.dom.setIgnoringComments(false);
        this.xform = TransformerFactory.newInstance();
        this.documentBuilderPool = new LinkedBlockingDeque<>(Runtime.getRuntime().availableProcessors() * 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static class RhinoSAXErrorHandler implements ErrorHandler, Serializable {
        private static final long serialVersionUID = 6918417235413084055L;

        private RhinoSAXErrorHandler() {
        }

        private void throwError(SAXParseException sAXParseException) {
            throw ScriptRuntime.constructError("TypeError", sAXParseException.getMessage(), sAXParseException.getLineNumber() - 1);
        }

        @Override // org.xml.sax.ErrorHandler
        public void error(SAXParseException sAXParseException) {
            throwError(sAXParseException);
        }

        @Override // org.xml.sax.ErrorHandler
        public void fatalError(SAXParseException sAXParseException) {
            throwError(sAXParseException);
        }

        @Override // org.xml.sax.ErrorHandler
        public void warning(SAXParseException sAXParseException) {
            Context.reportWarning(sAXParseException.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XmlProcessor() {
        setDefault();
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        this.dom = newInstance;
        newInstance.setNamespaceAware(true);
        this.dom.setIgnoringComments(false);
        this.xform = TransformerFactory.newInstance();
        this.documentBuilderPool = new LinkedBlockingDeque<>(Runtime.getRuntime().availableProcessors() * 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setDefault() {
        setIgnoreComments(true);
        setIgnoreProcessingInstructions(true);
        setIgnoreWhitespace(true);
        setPrettyPrinting(true);
        setPrettyIndent(2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setIgnoreComments(boolean z) {
        this.ignoreComments = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setIgnoreWhitespace(boolean z) {
        this.ignoreWhitespace = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setIgnoreProcessingInstructions(boolean z) {
        this.ignoreProcessingInstructions = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setPrettyPrinting(boolean z) {
        this.prettyPrint = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setPrettyIndent(int i) {
        this.prettyIndent = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isIgnoreComments() {
        return this.ignoreComments;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isIgnoreProcessingInstructions() {
        return this.ignoreProcessingInstructions;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isIgnoreWhitespace() {
        return this.ignoreWhitespace;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isPrettyPrinting() {
        return this.prettyPrint;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getPrettyIndent() {
        return this.prettyIndent;
    }

    private String toXmlNewlines(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\r') {
                if (str.charAt(i + 1) != '\n') {
                    sb.append('\n');
                }
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    private DocumentBuilderFactory getDomFactory() {
        return this.dom;
    }

    private DocumentBuilder getDocumentBuilderFromPool() throws ParserConfigurationException {
        DocumentBuilder pollFirst = this.documentBuilderPool.pollFirst();
        if (pollFirst == null) {
            pollFirst = getDomFactory().newDocumentBuilder();
        }
        pollFirst.setErrorHandler(this.errorHandler);
        return pollFirst;
    }

    private void returnDocumentBuilderToPool(DocumentBuilder documentBuilder) {
        try {
            documentBuilder.reset();
            this.documentBuilderPool.offerFirst(documentBuilder);
        } catch (UnsupportedOperationException unused) {
        }
    }

    private void addProcessingInstructionsTo(List<Node> list, Node node) {
        if (node instanceof ProcessingInstruction) {
            list.add(node);
        }
        if (node.getChildNodes() != null) {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                addProcessingInstructionsTo(list, node.getChildNodes().item(i));
            }
        }
    }

    private void addCommentsTo(List<Node> list, Node node) {
        if (node instanceof Comment) {
            list.add(node);
        }
        if (node.getChildNodes() != null) {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                addProcessingInstructionsTo(list, node.getChildNodes().item(i));
            }
        }
    }

    private void addTextNodesToRemoveAndTrim(List<Node> list, Node node) {
        if (node instanceof Text) {
            Text text = (Text) node;
            text.setData(text.getData().trim());
            if (text.getData().length() == 0) {
                list.add(node);
            }
        }
        if (node.getChildNodes() != null) {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                addTextNodesToRemoveAndTrim(list, node.getChildNodes().item(i));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Node toXml(String str, String str2) throws SAXException {
        try {
            try {
                DocumentBuilder documentBuilderFromPool = getDocumentBuilderFromPool();
                Document parse = documentBuilderFromPool.parse(new InputSource(new StringReader("<parent xmlns=\"" + str + "\">" + str2 + "</parent>")));
                if (this.ignoreProcessingInstructions) {
                    ArrayList arrayList = new ArrayList();
                    addProcessingInstructionsTo(arrayList, parse);
                    for (Node node : arrayList) {
                        node.getParentNode().removeChild(node);
                    }
                }
                if (this.ignoreComments) {
                    ArrayList arrayList2 = new ArrayList();
                    addCommentsTo(arrayList2, parse);
                    for (Node node2 : arrayList2) {
                        node2.getParentNode().removeChild(node2);
                    }
                }
                if (this.ignoreWhitespace) {
                    ArrayList arrayList3 = new ArrayList();
                    addTextNodesToRemoveAndTrim(arrayList3, parse);
                    for (Node node3 : arrayList3) {
                        node3.getParentNode().removeChild(node3);
                    }
                }
                NodeList childNodes = parse.getDocumentElement().getChildNodes();
                if (childNodes.getLength() > 1) {
                    throw ScriptRuntime.constructError("SyntaxError", "XML objects may contain at most one node.");
                }
                if (childNodes.getLength() == 0) {
                    Text createTextNode = parse.createTextNode("");
                    if (documentBuilderFromPool != null) {
                        returnDocumentBuilderToPool(documentBuilderFromPool);
                    }
                    return createTextNode;
                }
                Node item = childNodes.item(0);
                parse.getDocumentElement().removeChild(item);
                if (documentBuilderFromPool != null) {
                    returnDocumentBuilderToPool(documentBuilderFromPool);
                }
                return item;
            } catch (IOException unused) {
                throw new RuntimeException("Unreachable.");
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            }
        } catch (Throwable th) {
            if (0 != 0) {
                returnDocumentBuilderToPool(null);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Document newDocument() {
        DocumentBuilder documentBuilder = null;
        try {
            try {
                documentBuilder = getDocumentBuilderFromPool();
                return documentBuilder.newDocument();
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            }
        } finally {
            if (documentBuilder != null) {
                returnDocumentBuilderToPool(documentBuilder);
            }
        }
    }

    private String toString(Node node) {
        DOMSource dOMSource = new DOMSource(node);
        StringWriter stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);
        try {
            Transformer newTransformer = this.xform.newTransformer();
            newTransformer.setOutputProperty("omit-xml-declaration", "yes");
            newTransformer.setOutputProperty("indent", "no");
            newTransformer.setOutputProperty("method", "xml");
            newTransformer.transform(dOMSource, streamResult);
            return toXmlNewlines(stringWriter.toString());
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String escapeAttributeValue(Object obj) {
        String scriptRuntime = ScriptRuntime.toString(obj);
        if (scriptRuntime.length() == 0) {
            return "";
        }
        Element createElement = newDocument().createElement("a");
        createElement.setAttribute("b", scriptRuntime);
        String xmlProcessor = toString(createElement);
        return xmlProcessor.substring(xmlProcessor.indexOf(34) + 1, xmlProcessor.lastIndexOf(34));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String escapeTextValue(Object obj) {
        if (obj instanceof XMLObjectImpl) {
            return ((XMLObjectImpl) obj).toXMLString();
        }
        String scriptRuntime = ScriptRuntime.toString(obj);
        if (scriptRuntime.length() == 0) {
            return scriptRuntime;
        }
        Element createElement = newDocument().createElement("a");
        createElement.setTextContent(scriptRuntime);
        String xmlProcessor = toString(createElement);
        int indexOf = xmlProcessor.indexOf(62) + 1;
        int lastIndexOf = xmlProcessor.lastIndexOf(60);
        return indexOf < lastIndexOf ? xmlProcessor.substring(indexOf, lastIndexOf) : "";
    }

    private String escapeElementValue(String str) {
        return escapeTextValue(str);
    }

    private String elementToXmlString(Element element) {
        Element element2 = (Element) element.cloneNode(true);
        if (this.prettyPrint) {
            beautifyElement(element2, 0);
        }
        return toString(element2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String ecmaToXmlString(Node node) {
        StringBuilder sb = new StringBuilder();
        boolean z = this.prettyPrint;
        if (node instanceof Text) {
            String data = ((Text) node).getData();
            if (this.prettyPrint) {
                data = data.trim();
            }
            sb.append(escapeElementValue(data));
            return sb.toString();
        }
        if (node instanceof Attr) {
            sb.append(escapeAttributeValue(((Attr) node).getValue()));
            return sb.toString();
        }
        if (node instanceof Comment) {
            sb.append("<!--" + ((Comment) node).getNodeValue() + "-->");
            return sb.toString();
        }
        if (node instanceof ProcessingInstruction) {
            ProcessingInstruction processingInstruction = (ProcessingInstruction) node;
            sb.append("<?" + processingInstruction.getTarget() + " " + processingInstruction.getData() + "?>");
            return sb.toString();
        }
        sb.append(elementToXmlString((Element) node));
        return sb.toString();
    }

    private void beautifyElement(Element element, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(' ');
        }
        String sb2 = sb.toString();
        for (int i3 = 0; i3 < this.prettyIndent; i3++) {
            sb.append(' ');
        }
        String sb3 = sb.toString();
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (int i4 = 0; i4 < element.getChildNodes().getLength(); i4++) {
            if (i4 == 1) {
                z = true;
            }
            if (element.getChildNodes().item(i4) instanceof Text) {
                arrayList.add(element.getChildNodes().item(i4));
            } else {
                arrayList.add(element.getChildNodes().item(i4));
                z = true;
            }
        }
        if (z) {
            for (int i5 = 0; i5 < arrayList.size(); i5++) {
                element.insertBefore(element.getOwnerDocument().createTextNode(sb3), (Node) arrayList.get(i5));
            }
        }
        NodeList childNodes = element.getChildNodes();
        ArrayList arrayList2 = new ArrayList();
        for (int i6 = 0; i6 < childNodes.getLength(); i6++) {
            if (childNodes.item(i6) instanceof Element) {
                arrayList2.add((Element) childNodes.item(i6));
            }
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            beautifyElement((Element) it.next(), this.prettyIndent + i);
        }
        if (z) {
            element.appendChild(element.getOwnerDocument().createTextNode(sb2));
        }
    }
}
