package com.aliyun.alink.linksdk.alcs.coap.resources;

import com.aliyun.alink.linksdk.tools.ALog;
import com.iflytek.speech.VoiceWakeuperAidl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LinkFormat {
    public static final int COAP_PERM_DELETE = 8;
    public static final int COAP_PERM_GET = 1;
    public static final int COAP_PERM_GET_POST = 3;
    public static final int COAP_PERM_NONE = 0;
    public static final int COAP_PERM_OBSERVE = 256;
    public static final int COAP_PERM_POST = 2;
    public static final int COAP_PERM_PUT = 4;
    public static final String CONTENT_TYPE = "ct";
    public static final String CONTEXT = "con";
    public static final String DOMAIN = "d";
    public static final String END_POINT = "ep";
    public static final String END_POINT_TYPE = "et";
    public static final String HOST = "h";
    public static final String INSTANCE = "ins";
    public static final String INTERFACE_DESCRIPTION = "if";
    public static final String LIFE_TIME = "lt";
    public static final String LINK = "href";
    public static final String MAX_SIZE_ESTIMATE = "sz";
    public static final String OBSERVABLE = "obs";
    public static final String RESOURCE_TYPE = "rt";
    private static final String TAG = "LinkFormat";
    public static final String TITLE = "title";
    public static final Pattern DELIMITER = Pattern.compile("\\s*,+\\s*");
    public static final Pattern SEPARATOR = Pattern.compile("\\s*;+\\s*");
    public static final Pattern WORD = Pattern.compile("\\w+");
    public static final Pattern QUOTED_STRING = Pattern.compile("\\G\".*?\"");
    public static final Pattern CARDINAL = Pattern.compile("\\G\\d+");

    public static String serializeTree(Resource resource) {
        StringBuilder sb = new StringBuilder();
        List emptyList = Collections.emptyList();
        Iterator<Resource> it = resource.getChildren().iterator();
        while (it.hasNext()) {
            serializeTree(it.next(), emptyList, sb);
        }
        if (sb.length() > 1) {
            sb.delete(sb.length() - 1, sb.length());
        }
        return sb.toString();
    }

    public static void serializeTree(Resource resource, List<String> list, StringBuilder sb) {
        if (resource.isVisible() && matches(resource, list)) {
            sb.append((CharSequence) serializeResource(resource));
        }
        ArrayList arrayList = new ArrayList(resource.getChildren());
        Collections.sort(arrayList, new Comparator<Resource>() { // from class: com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat.1
            @Override // java.util.Comparator
            public int compare(Resource resource2, Resource resource3) {
                return resource2.getName().compareTo(resource3.getName());
            }
        });
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            serializeTree((Resource) it.next(), list, sb);
        }
    }

    public static StringBuilder serializeResource(Resource resource) {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(resource.getPath());
        sb.append(resource.getName());
        sb.append(">");
        sb.append((CharSequence) serializeAttributes(resource.getAttributes()));
        sb.append(",");
        return sb;
    }

    public static StringBuilder serializeAttributes(ResourceAttributes resourceAttributes) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> arrayList = new ArrayList(resourceAttributes.getAttributeKeySet());
        Collections.sort(arrayList);
        for (String str : arrayList) {
            List<String> attributeValues = resourceAttributes.getAttributeValues(str);
            if (attributeValues != null) {
                sb.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                sb.append((CharSequence) serializeAttribute(str, new LinkedList(attributeValues)));
            }
        }
        return sb;
    }

    public static StringBuilder serializeAttribute(String str, List<String> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (list == null) {
            ALog.m480e(TAG, "Values null");
            return sb;
        }
        if (!list.isEmpty()) {
            boolean z = false;
            if (!list.get(0).equals("")) {
                sb.append("=");
                if (list.size() > 1 || !list.get(0).matches("^[0-9]+$")) {
                    sb.append('\"');
                    z = true;
                }
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    sb.append(it.next());
                    if (it.hasNext()) {
                        sb.append(' ');
                    }
                }
                if (z) {
                    sb.append('\"');
                }
            }
        }
        return sb;
    }

    public static boolean matches(Resource resource, List<String> list) {
        if (resource == null) {
            return false;
        }
        if (list == null || list.size() == 0) {
            return true;
        }
        ResourceAttributes attributes = resource.getAttributes();
        String str = resource.getPath() + resource.getName();
        for (String str2 : list) {
            int indexOf = str2.indexOf("=");
            if (indexOf != -1) {
                String substring = str2.substring(0, indexOf);
                String substring2 = str2.substring(indexOf + 1);
                if (substring.equals(LINK)) {
                    if (substring2.endsWith("*")) {
                        return str.startsWith(substring2.substring(0, substring2.length() - 1));
                    }
                    return str.equals(substring2);
                }
                if (attributes.containsAttribute(substring)) {
                    Iterator<String> it = attributes.getAttributeValues(substring).iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        int indexOf2 = substring2.indexOf(42);
                        if (indexOf2 >= 0 && indexOf2 < next.length()) {
                            substring2 = substring2.substring(0, indexOf2);
                            next = next.substring(0, indexOf2);
                        }
                        if (next.indexOf(" ") > -1) {
                            for (String str3 : next.split(" ")) {
                                if (str3.equals(substring2)) {
                                    return true;
                                }
                            }
                        }
                        if (substring2.equals(next)) {
                            return true;
                        }
                    }
                } else {
                    continue;
                }
            } else if (attributes.getAttributeValues(str2).size() > 0) {
                return true;
            }
        }
        return false;
    }
}
