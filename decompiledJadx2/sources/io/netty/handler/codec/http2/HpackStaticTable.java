package io.netty.handler.codec.http2;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.alcs.api.utils.ErrorCode;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.p022h2.stream.utils.StreamUtil;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import io.grpc.internal.GrpcUtil;
import io.netty.handler.codec.UnsupportedValueConverter;
import io.netty.util.AsciiString;
import java.util.Arrays;
import java.util.List;
import okhttp3.internal.http2.Header;
import org.apache.http.HttpHost;
import org.apache.http.cookie.ClientCookie;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
final class HpackStaticTable {
    private static final List<HpackHeaderField> STATIC_TABLE = Arrays.asList(newEmptyHeaderField(Header.TARGET_AUTHORITY_UTF8), newHeaderField(":method", "GET"), newHeaderField(":method", "POST"), newHeaderField(":path", "/"), newHeaderField(":path", "/index.html"), newHeaderField(":scheme", HttpHost.DEFAULT_SCHEME_NAME), newHeaderField(":scheme", "https"), newHeaderField(":status", ErrorCode.UNKNOWN_SUCCESS_CODE), newHeaderField(":status", "204"), newHeaderField(":status", "206"), newHeaderField(":status", "304"), newHeaderField(":status", "400"), newHeaderField(":status", "404"), newHeaderField(":status", "500"), newEmptyHeaderField("accept-charset"), newHeaderField(GrpcUtil.CONTENT_ACCEPT_ENCODING, "gzip, deflate"), newEmptyHeaderField("accept-language"), newEmptyHeaderField("accept-ranges"), newEmptyHeaderField("accept"), newEmptyHeaderField("access-control-allow-origin"), newEmptyHeaderField("age"), newEmptyHeaderField("allow"), newEmptyHeaderField("authorization"), newEmptyHeaderField("cache-control"), newEmptyHeaderField("content-disposition"), newEmptyHeaderField(GrpcUtil.CONTENT_ENCODING), newEmptyHeaderField("content-language"), newEmptyHeaderField(StreamUtil.CONTENT_LENGTH), newEmptyHeaderField("content-location"), newEmptyHeaderField("content-range"), newEmptyHeaderField("content-type"), newEmptyHeaderField("cookie"), newEmptyHeaderField(TmpConstant.TYPE_VALUE_DATE), newEmptyHeaderField(TransferTable.COLUMN_ETAG), newEmptyHeaderField("expect"), newEmptyHeaderField(ClientCookie.EXPIRES_ATTR), newEmptyHeaderField("from"), newEmptyHeaderField("host"), newEmptyHeaderField("if-match"), newEmptyHeaderField("if-modified-since"), newEmptyHeaderField("if-none-match"), newEmptyHeaderField("if-range"), newEmptyHeaderField("if-unmodified-since"), newEmptyHeaderField("last-modified"), newEmptyHeaderField("link"), newEmptyHeaderField(RequestParameters.SUBRESOURCE_LOCATION), newEmptyHeaderField("max-forwards"), newEmptyHeaderField("proxy-authenticate"), newEmptyHeaderField("proxy-authorization"), newEmptyHeaderField("range"), newEmptyHeaderField(RequestParameters.SUBRESOURCE_REFERER), newEmptyHeaderField("refresh"), newEmptyHeaderField("retry-after"), newEmptyHeaderField("server"), newEmptyHeaderField("set-cookie"), newEmptyHeaderField("strict-transport-security"), newEmptyHeaderField("transfer-encoding"), newEmptyHeaderField("user-agent"), newEmptyHeaderField("vary"), newEmptyHeaderField("via"), newEmptyHeaderField("www-authenticate"));
    private static final CharSequenceMap<Integer> STATIC_INDEX_BY_NAME = createMap();
    static final int length = STATIC_TABLE.size();

    private static HpackHeaderField newEmptyHeaderField(String str) {
        return new HpackHeaderField(AsciiString.cached(str), AsciiString.EMPTY_STRING);
    }

    private static HpackHeaderField newHeaderField(String str, String str2) {
        return new HpackHeaderField(AsciiString.cached(str), AsciiString.cached(str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HpackHeaderField getEntry(int i) {
        return STATIC_TABLE.get(i - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getIndex(CharSequence charSequence) {
        Integer num = STATIC_INDEX_BY_NAME.get(charSequence);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getIndex(CharSequence charSequence, CharSequence charSequence2) {
        int index = getIndex(charSequence);
        if (index == -1) {
            return -1;
        }
        while (index <= length) {
            HpackHeaderField entry = getEntry(index);
            if (HpackUtil.equalsConstantTime(charSequence, entry.name) == 0) {
                break;
            }
            if (HpackUtil.equalsConstantTime(charSequence2, entry.value) != 0) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private static CharSequenceMap<Integer> createMap() {
        int size = STATIC_TABLE.size();
        CharSequenceMap<Integer> charSequenceMap = new CharSequenceMap<>(true, UnsupportedValueConverter.instance(), size);
        while (size > 0) {
            charSequenceMap.set((CharSequenceMap<Integer>) getEntry(size).name, (CharSequence) Integer.valueOf(size));
            size--;
        }
        return charSequenceMap;
    }

    private HpackStaticTable() {
    }
}
