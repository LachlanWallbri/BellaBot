package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class MultipartContent extends AbstractHttpContent {
    static final String NEWLINE = "\r\n";
    private static final String TWO_DASHES = "--";
    private ArrayList<Part> parts;

    public MultipartContent() {
        this("__END_OF_PART__" + UUID.randomUUID().toString() + "__");
    }

    public MultipartContent(String str) {
        super(new HttpMediaType("multipart/related").setParameter("boundary", str));
        this.parts = new ArrayList<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v3, types: [com.google.api.client.http.HttpEncodingStreamingContent] */
    @Override // com.google.api.client.http.HttpContent, com.google.api.client.util.StreamingContent
    public void writeTo(OutputStream outputStream) throws IOException {
        long j;
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, getCharset());
        String boundary = getBoundary();
        Iterator<Part> it = this.parts.iterator();
        while (it.hasNext()) {
            Part next = it.next();
            HttpHeaders acceptEncoding = new HttpHeaders().setAcceptEncoding(null);
            if (next.headers != null) {
                acceptEncoding.fromHttpHeaders(next.headers);
            }
            acceptEncoding.setContentEncoding(null).setUserAgent(null).setContentType(null).setContentLength(null).set("Content-Transfer-Encoding", (Object) null);
            HttpContent httpContent = next.content;
            if (httpContent != null) {
                acceptEncoding.set("Content-Transfer-Encoding", (Object) Arrays.asList("binary"));
                acceptEncoding.setContentType(httpContent.getType());
                HttpEncoding httpEncoding = next.encoding;
                if (httpEncoding == null) {
                    j = httpContent.getLength();
                } else {
                    acceptEncoding.setContentEncoding(httpEncoding.getName());
                    ?? httpEncodingStreamingContent = new HttpEncodingStreamingContent(httpContent, httpEncoding);
                    long computeLength = AbstractHttpContent.computeLength(httpContent);
                    httpContent = httpEncodingStreamingContent;
                    j = computeLength;
                }
                if (j != -1) {
                    acceptEncoding.setContentLength(Long.valueOf(j));
                }
            } else {
                httpContent = null;
            }
            outputStreamWriter.write(TWO_DASHES);
            outputStreamWriter.write(boundary);
            outputStreamWriter.write("\r\n");
            HttpHeaders.serializeHeadersForMultipartRequests(acceptEncoding, null, null, outputStreamWriter);
            if (httpContent != null) {
                outputStreamWriter.write("\r\n");
                outputStreamWriter.flush();
                httpContent.writeTo(outputStream);
            }
            outputStreamWriter.write("\r\n");
        }
        outputStreamWriter.write(TWO_DASHES);
        outputStreamWriter.write(boundary);
        outputStreamWriter.write(TWO_DASHES);
        outputStreamWriter.write("\r\n");
        outputStreamWriter.flush();
    }

    @Override // com.google.api.client.http.AbstractHttpContent, com.google.api.client.http.HttpContent
    public boolean retrySupported() {
        Iterator<Part> it = this.parts.iterator();
        while (it.hasNext()) {
            if (!it.next().content.retrySupported()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.api.client.http.AbstractHttpContent
    public MultipartContent setMediaType(HttpMediaType httpMediaType) {
        super.setMediaType(httpMediaType);
        return this;
    }

    public final Collection<Part> getParts() {
        return Collections.unmodifiableCollection(this.parts);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MultipartContent addPart(Part part) {
        this.parts.add(Preconditions.checkNotNull(part));
        return this;
    }

    public MultipartContent setParts(Collection<Part> collection) {
        this.parts = new ArrayList<>(collection);
        return this;
    }

    public MultipartContent setContentParts(Collection<? extends HttpContent> collection) {
        this.parts = new ArrayList<>(collection.size());
        Iterator<? extends HttpContent> it = collection.iterator();
        while (it.hasNext()) {
            addPart(new Part(it.next()));
        }
        return this;
    }

    public final String getBoundary() {
        return getMediaType().getParameter("boundary");
    }

    public MultipartContent setBoundary(String str) {
        getMediaType().setParameter("boundary", (String) Preconditions.checkNotNull(str));
        return this;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static final class Part {
        HttpContent content;
        HttpEncoding encoding;
        HttpHeaders headers;

        public Part() {
            this(null);
        }

        public Part(HttpContent httpContent) {
            this(null, httpContent);
        }

        public Part(HttpHeaders httpHeaders, HttpContent httpContent) {
            setHeaders(httpHeaders);
            setContent(httpContent);
        }

        public Part setContent(HttpContent httpContent) {
            this.content = httpContent;
            return this;
        }

        public HttpContent getContent() {
            return this.content;
        }

        public Part setHeaders(HttpHeaders httpHeaders) {
            this.headers = httpHeaders;
            return this;
        }

        public HttpHeaders getHeaders() {
            return this.headers;
        }

        public Part setEncoding(HttpEncoding httpEncoding) {
            this.encoding = httpEncoding;
            return this;
        }

        public HttpEncoding getEncoding() {
            return this.encoding;
        }
    }
}
