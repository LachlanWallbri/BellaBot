package com.amazonaws.services.p048s3.internal;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.Request;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferService;
import com.amazonaws.services.p048s3.Headers;
import com.amazonaws.util.StringUtils;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import kotlin.text.Typography;

/* loaded from: classes.dex */
public class RestUtils {
    private static final List<String> SIGNED_PARAMETERS = Arrays.asList(RequestParameters.SUBRESOURCE_ACL, "torrent", RequestParameters.SUBRESOURCE_LOGGING, RequestParameters.SUBRESOURCE_LOCATION, "policy", "requestPayment", "versioning", "versions", "versionId", TransferService.INTENT_KEY_NOTIFICATION, RequestParameters.UPLOAD_ID, RequestParameters.SUBRESOURCE_UPLOADS, RequestParameters.PART_NUMBER, RequestParameters.SUBRESOURCE_WEBSITE, RequestParameters.SUBRESOURCE_DELETE, RequestParameters.SUBRESOURCE_LIFECYCLE, "tagging", RequestParameters.SUBRESOURCE_CORS, "restore", "replication", "accelerate", "inventory", "analytics", "metrics", "response-cache-control", "response-content-disposition", "response-content-encoding", "response-content-language", "response-content-type", "response-expires");

    public static <T> String makeS3CanonicalString(String str, String str2, Request<T> request, String str3) {
        return makeS3CanonicalString(str, str2, request, str3, null);
    }

    public static <T> String makeS3CanonicalString(String str, String str2, Request<T> request, String str3, Collection<String> collection) {
        StringBuilder sb = new StringBuilder();
        sb.append(str + "\n");
        Map<String, String> headers = request.getHeaders();
        TreeMap treeMap = new TreeMap();
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key != null) {
                    String lowerCase = StringUtils.lowerCase(key);
                    if ("content-type".equals(lowerCase) || "content-md5".equals(lowerCase) || TmpConstant.TYPE_VALUE_DATE.equals(lowerCase) || lowerCase.startsWith(Headers.AMAZON_PREFIX)) {
                        treeMap.put(lowerCase, value);
                    }
                }
            }
        }
        if (treeMap.containsKey(Headers.S3_ALTERNATE_DATE)) {
            treeMap.put(TmpConstant.TYPE_VALUE_DATE, "");
        }
        if (str3 != null) {
            treeMap.put(TmpConstant.TYPE_VALUE_DATE, str3);
        }
        if (!treeMap.containsKey("content-type")) {
            treeMap.put("content-type", "");
        }
        if (!treeMap.containsKey("content-md5")) {
            treeMap.put("content-md5", "");
        }
        for (Map.Entry<String, String> entry2 : request.getParameters().entrySet()) {
            if (entry2.getKey().startsWith(Headers.AMAZON_PREFIX)) {
                treeMap.put(entry2.getKey(), entry2.getValue());
            }
        }
        for (Map.Entry entry3 : treeMap.entrySet()) {
            String str4 = (String) entry3.getKey();
            String str5 = (String) entry3.getValue();
            if (str4.startsWith(Headers.AMAZON_PREFIX)) {
                sb.append(str4);
                sb.append(':');
                if (str5 != null) {
                    sb.append(str5);
                }
            } else if (str5 != null) {
                sb.append(str5);
            }
            sb.append("\n");
        }
        sb.append(str2);
        String[] strArr = (String[]) request.getParameters().keySet().toArray(new String[request.getParameters().size()]);
        Arrays.sort(strArr);
        char c = '?';
        for (String str6 : strArr) {
            if (SIGNED_PARAMETERS.contains(str6) || (collection != null && collection.contains(str6))) {
                if (sb.length() == 0) {
                    sb.append(c);
                }
                sb.append(str6);
                String str7 = request.getParameters().get(str6);
                if (str7 != null) {
                    sb.append("=");
                    sb.append(str7);
                }
                c = Typography.amp;
            }
        }
        return sb.toString();
    }
}
