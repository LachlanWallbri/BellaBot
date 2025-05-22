package com.amazonaws.regions;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Deprecated
/* loaded from: classes.dex */
public class RegionMetadataParser {
    private static final String DOMAIN_TAG = "Domain";
    private static final String ENDPOINT_TAG = "Endpoint";
    private static final String HOSTNAME_TAG = "Hostname";
    private static final String HTTPS_TAG = "Https";
    private static final String HTTP_TAG = "Http";
    private static final String REGION_ID_TAG = "Name";
    private static final String REGION_TAG = "Region";
    private static final String SERVICE_TAG = "ServiceName";

    public static RegionMetadata parse(InputStream inputStream) throws IOException {
        return new RegionMetadata(internalParse(inputStream, false));
    }

    @Deprecated
    public RegionMetadataParser() {
    }

    @Deprecated
    public List<Region> parseRegionMetadata(InputStream inputStream) throws IOException {
        return internalParse(inputStream, false);
    }

    @Deprecated
    public List<Region> parseRegionMetadata(InputStream inputStream, boolean z) throws IOException {
        return internalParse(inputStream, z);
    }

    private static List<Region> internalParse(InputStream inputStream, boolean z) throws IOException {
        try {
            try {
                Document parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
                NodeList elementsByTagName = parse.getElementsByTagName(REGION_TAG);
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < elementsByTagName.getLength(); i++) {
                    Node item = elementsByTagName.item(i);
                    if (item.getNodeType() == 1) {
                        arrayList.add(parseRegionElement((Element) item, z));
                    }
                }
                return arrayList;
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                throw new IOException("Unable to parse region metadata file: " + e2.getMessage(), e2);
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException unused2) {
            }
            throw th;
        }
    }

    private static Region parseRegionElement(Element element, boolean z) {
        Region region = new Region(getChildElementValue(REGION_ID_TAG, element), getChildElementValue("Domain", element));
        NodeList elementsByTagName = element.getElementsByTagName(ENDPOINT_TAG);
        for (int i = 0; i < elementsByTagName.getLength(); i++) {
            addRegionEndpoint(region, (Element) elementsByTagName.item(i), z);
        }
        return region;
    }

    private static void addRegionEndpoint(Region region, Element element, boolean z) {
        String childElementValue = getChildElementValue(SERVICE_TAG, element);
        String childElementValue2 = getChildElementValue(HOSTNAME_TAG, element);
        String childElementValue3 = getChildElementValue(HTTP_TAG, element);
        String childElementValue4 = getChildElementValue(HTTPS_TAG, element);
        if (z && !verifyLegacyEndpoint(childElementValue2)) {
            throw new IllegalStateException("Invalid service endpoint (" + childElementValue2 + ") is detected.");
        }
        region.getServiceEndpoints().put(childElementValue, childElementValue2);
        region.getHttpSupport().put(childElementValue, Boolean.valueOf("true".equals(childElementValue3)));
        region.getHttpsSupport().put(childElementValue, Boolean.valueOf("true".equals(childElementValue4)));
    }

    private static String getChildElementValue(String str, Element element) {
        Node item = element.getElementsByTagName(str).item(0);
        if (item == null) {
            return null;
        }
        return item.getChildNodes().item(0).getNodeValue();
    }

    private static boolean verifyLegacyEndpoint(String str) {
        return str.endsWith(".amazonaws.com");
    }
}
