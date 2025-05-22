package org.bouncycastle.est.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpHost;
import org.bouncycastle.est.ESTClient;
import org.bouncycastle.est.ESTClientSourceProvider;
import org.bouncycastle.est.ESTException;
import org.bouncycastle.est.ESTRequest;
import org.bouncycastle.est.ESTRequestBuilder;
import org.bouncycastle.est.ESTResponse;
import org.bouncycastle.est.Source;
import org.bouncycastle.util.Properties;

/* loaded from: classes9.dex */
class DefaultESTClient implements ESTClient {
    private final ESTClientSourceProvider sslSocketProvider;
    private static final Charset utf8 = Charset.forName("UTF-8");
    private static byte[] CRLF = {13, 10};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class PrintingOutputStream extends OutputStream {
        private final OutputStream tgt;

        public PrintingOutputStream(OutputStream outputStream) {
            this.tgt = outputStream;
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            System.out.print(String.valueOf((char) i));
            this.tgt.write(i);
        }
    }

    public DefaultESTClient(ESTClientSourceProvider eSTClientSourceProvider) {
        this.sslSocketProvider = eSTClientSourceProvider;
    }

    private static void writeLine(OutputStream outputStream, String str) throws IOException {
        outputStream.write(str.getBytes());
        outputStream.write(CRLF);
    }

    @Override // org.bouncycastle.est.ESTClient
    public ESTResponse doRequest(ESTRequest eSTRequest) throws IOException {
        ESTResponse performRequest;
        int i = 15;
        while (true) {
            performRequest = performRequest(eSTRequest);
            ESTRequest redirectURL = redirectURL(performRequest);
            if (redirectURL == null || i - 1 <= 0) {
                break;
            }
            eSTRequest = redirectURL;
        }
        if (i != 0) {
            return performRequest;
        }
        throw new ESTException("Too many redirects..");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0066 A[Catch: all -> 0x0151, TryCatch #0 {all -> 0x0151, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:12:0x004c, B:14:0x0066, B:15:0x0071, B:17:0x0087, B:18:0x008c, B:21:0x009a, B:22:0x00b4, B:23:0x00bd, B:24:0x00ed, B:26:0x00f3, B:27:0x0102, B:29:0x0105, B:32:0x0127, B:34:0x013b, B:40:0x014b, B:42:0x00b8, B:44:0x0043), top: B:2:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0087 A[Catch: all -> 0x0151, TryCatch #0 {all -> 0x0151, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:12:0x004c, B:14:0x0066, B:15:0x0071, B:17:0x0087, B:18:0x008c, B:21:0x009a, B:22:0x00b4, B:23:0x00bd, B:24:0x00ed, B:26:0x00f3, B:27:0x0102, B:29:0x0105, B:32:0x0127, B:34:0x013b, B:40:0x014b, B:42:0x00b8, B:44:0x0043), top: B:2:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x009a A[Catch: all -> 0x0151, TRY_ENTER, TryCatch #0 {all -> 0x0151, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:12:0x004c, B:14:0x0066, B:15:0x0071, B:17:0x0087, B:18:0x008c, B:21:0x009a, B:22:0x00b4, B:23:0x00bd, B:24:0x00ed, B:26:0x00f3, B:27:0x0102, B:29:0x0105, B:32:0x0127, B:34:0x013b, B:40:0x014b, B:42:0x00b8, B:44:0x0043), top: B:2:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00f3 A[Catch: all -> 0x0151, TryCatch #0 {all -> 0x0151, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:12:0x004c, B:14:0x0066, B:15:0x0071, B:17:0x0087, B:18:0x008c, B:21:0x009a, B:22:0x00b4, B:23:0x00bd, B:24:0x00ed, B:26:0x00f3, B:27:0x0102, B:29:0x0105, B:32:0x0127, B:34:0x013b, B:40:0x014b, B:42:0x00b8, B:44:0x0043), top: B:2:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x013b A[Catch: all -> 0x0151, TRY_LEAVE, TryCatch #0 {all -> 0x0151, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:12:0x004c, B:14:0x0066, B:15:0x0071, B:17:0x0087, B:18:0x008c, B:21:0x009a, B:22:0x00b4, B:23:0x00bd, B:24:0x00ed, B:26:0x00f3, B:27:0x0102, B:29:0x0105, B:32:0x0127, B:34:0x013b, B:40:0x014b, B:42:0x00b8, B:44:0x0043), top: B:2:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x014b A[Catch: all -> 0x0151, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0151, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:12:0x004c, B:14:0x0066, B:15:0x0071, B:17:0x0087, B:18:0x008c, B:21:0x009a, B:22:0x00b4, B:23:0x00bd, B:24:0x00ed, B:26:0x00f3, B:27:0x0102, B:29:0x0105, B:32:0x0127, B:34:0x013b, B:40:0x014b, B:42:0x00b8, B:44:0x0043), top: B:2:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b8 A[Catch: all -> 0x0151, TryCatch #0 {all -> 0x0151, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:12:0x004c, B:14:0x0066, B:15:0x0071, B:17:0x0087, B:18:0x008c, B:21:0x009a, B:22:0x00b4, B:23:0x00bd, B:24:0x00ed, B:26:0x00f3, B:27:0x0102, B:29:0x0105, B:32:0x0127, B:34:0x013b, B:40:0x014b, B:42:0x00b8, B:44:0x0043), top: B:2:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ESTResponse performRequest(ESTRequest eSTRequest) throws IOException {
        OutputStream printingOutputStream;
        ESTRequest build;
        Source source = null;
        try {
            Source makeSource = this.sslSocketProvider.makeSource(eSTRequest.getURL().getHost(), eSTRequest.getURL().getPort());
            if (eSTRequest.getListener() != null) {
                eSTRequest = eSTRequest.getListener().onConnection(makeSource, eSTRequest);
            }
            Set<String> asKeySet = Properties.asKeySet("org.bouncycastle.debug.est");
            if (!asKeySet.contains("output") && !asKeySet.contains("all")) {
                printingOutputStream = makeSource.getOutputStream();
                StringBuilder sb = new StringBuilder();
                sb.append(eSTRequest.getURL().getPath());
                sb.append(eSTRequest.getURL().getQuery() == null ? eSTRequest.getURL().getQuery() : "");
                String sb2 = sb.toString();
                ESTRequestBuilder eSTRequestBuilder = new ESTRequestBuilder(eSTRequest);
                if (!eSTRequest.getHeaders().containsKey("Connection")) {
                    eSTRequestBuilder.addHeader("Connection", "close");
                }
                URL url = eSTRequest.getURL();
                eSTRequestBuilder.setHeader("Host", url.getPort() <= -1 ? String.format("%s:%d", url.getHost(), Integer.valueOf(url.getPort())) : url.getHost());
                build = eSTRequestBuilder.build();
                writeLine(printingOutputStream, build.getMethod() + " " + sb2 + " HTTP/1.1");
                for (Map.Entry<String, String[]> entry : build.getHeaders().entrySet()) {
                    String[] value = entry.getValue();
                    for (int i = 0; i != value.length; i++) {
                        writeLine(printingOutputStream, entry.getKey() + ": " + value[i]);
                    }
                }
                printingOutputStream.write(CRLF);
                printingOutputStream.flush();
                build.writeData(printingOutputStream);
                printingOutputStream.flush();
                if (build.getHijacker() != null) {
                    return new ESTResponse(build, makeSource);
                }
                ESTResponse hijack = build.getHijacker().hijack(build, makeSource);
                if (makeSource != null && hijack == null) {
                    makeSource.close();
                }
                return hijack;
            }
            printingOutputStream = new PrintingOutputStream(makeSource.getOutputStream());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(eSTRequest.getURL().getPath());
            sb3.append(eSTRequest.getURL().getQuery() == null ? eSTRequest.getURL().getQuery() : "");
            String sb22 = sb3.toString();
            ESTRequestBuilder eSTRequestBuilder2 = new ESTRequestBuilder(eSTRequest);
            if (!eSTRequest.getHeaders().containsKey("Connection")) {
            }
            URL url2 = eSTRequest.getURL();
            eSTRequestBuilder2.setHeader("Host", url2.getPort() <= -1 ? String.format("%s:%d", url2.getHost(), Integer.valueOf(url2.getPort())) : url2.getHost());
            build = eSTRequestBuilder2.build();
            writeLine(printingOutputStream, build.getMethod() + " " + sb22 + " HTTP/1.1");
            while (r0.hasNext()) {
            }
            printingOutputStream.write(CRLF);
            printingOutputStream.flush();
            build.writeData(printingOutputStream);
            printingOutputStream.flush();
            if (build.getHijacker() != null) {
            }
        } catch (Throwable th) {
            if (0 != 0) {
                source.close();
            }
            throw th;
        }
    }

    protected ESTRequest redirectURL(ESTResponse eSTResponse) throws IOException {
        ESTRequest eSTRequest;
        ESTRequestBuilder withURL;
        if (eSTResponse.getStatusCode() < 300 || eSTResponse.getStatusCode() > 399) {
            eSTRequest = null;
        } else {
            switch (eSTResponse.getStatusCode()) {
                case 301:
                case 302:
                case 303:
                case 306:
                case 307:
                    String header = eSTResponse.getHeader("Location");
                    if (!"".equals(header)) {
                        ESTRequestBuilder eSTRequestBuilder = new ESTRequestBuilder(eSTResponse.getOriginalRequest());
                        if (header.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                            withURL = eSTRequestBuilder.withURL(new URL(header));
                        } else {
                            URL url = eSTResponse.getOriginalRequest().getURL();
                            withURL = eSTRequestBuilder.withURL(new URL(url.getProtocol(), url.getHost(), url.getPort(), header));
                        }
                        eSTRequest = withURL.build();
                        break;
                    } else {
                        throw new ESTException("Redirect status type: " + eSTResponse.getStatusCode() + " but no location header");
                    }
                case 304:
                case 305:
                default:
                    throw new ESTException("Client does not handle http status code: " + eSTResponse.getStatusCode());
            }
        }
        if (eSTRequest != null) {
            eSTResponse.close();
        }
        return eSTRequest;
    }
}
