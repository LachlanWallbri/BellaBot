package io.minio;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/* loaded from: classes7.dex */
class HttpRequestBody extends RequestBody {
    private byte[] bytes;
    private String contentType;
    private int length;
    private PartSource partSource;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpRequestBody(PartSource partSource, String str) {
        this.partSource = partSource;
        this.contentType = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpRequestBody(byte[] bArr, int i, String str) {
        this.bytes = bArr;
        this.length = i;
        this.contentType = str;
    }

    @Override // okhttp3.RequestBody
    /* renamed from: contentType */
    public MediaType get$contentType() {
        String str = this.contentType;
        MediaType parse = str != null ? MediaType.parse(str) : null;
        return parse == null ? MediaType.parse("application/octet-stream") : parse;
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        PartSource partSource = this.partSource;
        return partSource != null ? partSource.size() : this.length;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        PartSource partSource = this.partSource;
        if (partSource != null) {
            bufferedSink.write(partSource.source(), this.partSource.size());
        } else {
            bufferedSink.write(this.bytes, 0, this.length);
        }
    }
}
