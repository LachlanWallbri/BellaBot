package org.jboss.netty.handler.codec.http.websocketx;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequestEncoder;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;

/* loaded from: classes7.dex */
public class WebSocketClientHandshaker00 extends WebSocketClientHandshaker {
    private byte[] expectedChallengeResponseBytes;

    public WebSocketClientHandshaker00(URI uri, WebSocketVersion webSocketVersion, String str, Map<String, String> map) {
        this(uri, webSocketVersion, str, map, Long.MAX_VALUE);
    }

    public WebSocketClientHandshaker00(URI uri, WebSocketVersion webSocketVersion, String str, Map<String, String> map, long j) {
        super(uri, webSocketVersion, str, map, j);
    }

    @Override // org.jboss.netty.handler.codec.http.websocketx.WebSocketClientHandshaker
    public ChannelFuture handshake(Channel channel) {
        int randomNumber = WebSocketUtil.randomNumber(1, 12);
        int randomNumber2 = WebSocketUtil.randomNumber(1, 12);
        int randomNumber3 = WebSocketUtil.randomNumber(0, Integer.MAX_VALUE / randomNumber);
        int randomNumber4 = WebSocketUtil.randomNumber(0, Integer.MAX_VALUE / randomNumber2);
        String num = Integer.toString(randomNumber3 * randomNumber);
        String num2 = Integer.toString(randomNumber4 * randomNumber2);
        String insertRandomCharacters = insertRandomCharacters(num);
        String insertRandomCharacters2 = insertRandomCharacters(num2);
        String insertSpaces = insertSpaces(insertRandomCharacters, randomNumber);
        String insertSpaces2 = insertSpaces(insertRandomCharacters2, randomNumber2);
        byte[] randomBytes = WebSocketUtil.randomBytes(8);
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(randomNumber3);
        byte[] array = allocate.array();
        ByteBuffer allocate2 = ByteBuffer.allocate(4);
        allocate2.putInt(randomNumber4);
        byte[] array2 = allocate2.array();
        byte[] bArr = new byte[16];
        System.arraycopy(array, 0, bArr, 0, 4);
        System.arraycopy(array2, 0, bArr, 4, 4);
        System.arraycopy(randomBytes, 0, bArr, 8, 8);
        this.expectedChallengeResponseBytes = WebSocketUtil.md5(bArr);
        URI webSocketUrl = getWebSocketUrl();
        String path = webSocketUrl.getPath();
        if (webSocketUrl.getQuery() != null && webSocketUrl.getQuery().length() > 0) {
            path = webSocketUrl.getPath() + "?" + webSocketUrl.getQuery();
        }
        DefaultHttpRequest defaultHttpRequest = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, path);
        defaultHttpRequest.addHeader("Upgrade", "WebSocket");
        defaultHttpRequest.addHeader("Connection", "Upgrade");
        defaultHttpRequest.addHeader("Host", webSocketUrl.getHost());
        int port = webSocketUrl.getPort();
        String str = "http://" + webSocketUrl.getHost();
        if (port != 80 && port != 443) {
            str = str + ":" + port;
        }
        defaultHttpRequest.addHeader("Origin", str);
        defaultHttpRequest.addHeader("Sec-WebSocket-Key1", insertSpaces);
        defaultHttpRequest.addHeader("Sec-WebSocket-Key2", insertSpaces2);
        String expectedSubprotocol = getExpectedSubprotocol();
        if (expectedSubprotocol != null && !expectedSubprotocol.equals("")) {
            defaultHttpRequest.addHeader("Sec-WebSocket-Protocol", expectedSubprotocol);
        }
        if (this.customHeaders != null) {
            for (String str2 : this.customHeaders.keySet()) {
                defaultHttpRequest.addHeader(str2, this.customHeaders.get(str2));
            }
        }
        defaultHttpRequest.setContent(ChannelBuffers.copiedBuffer(randomBytes));
        ChannelFuture write = channel.write(defaultHttpRequest);
        channel.getPipeline().replace(HttpRequestEncoder.class, "ws-encoder", new WebSocket00FrameEncoder());
        return write;
    }

    @Override // org.jboss.netty.handler.codec.http.websocketx.WebSocketClientHandshaker
    public void finishHandshake(Channel channel, HttpResponse httpResponse) throws WebSocketHandshakeException {
        if (!httpResponse.getStatus().equals(new HttpResponseStatus(101, "WebSocket Protocol Handshake"))) {
            throw new WebSocketHandshakeException("Invalid handshake response status: " + httpResponse.getStatus());
        }
        String header = httpResponse.getHeader("Upgrade");
        if (header == null || !header.equals("WebSocket")) {
            throw new WebSocketHandshakeException("Invalid handshake response upgrade: " + httpResponse.getHeader("Upgrade"));
        }
        String header2 = httpResponse.getHeader("Connection");
        if (header2 == null || !header2.equals("Upgrade")) {
            throw new WebSocketHandshakeException("Invalid handshake response connection: " + httpResponse.getHeader("Connection"));
        }
        if (!Arrays.equals(httpResponse.getContent().array(), this.expectedChallengeResponseBytes)) {
            throw new WebSocketHandshakeException("Invalid challenge");
        }
        setActualSubprotocol(httpResponse.getHeader("Sec-WebSocket-Protocol"));
        setHandshakeComplete();
        ((HttpResponseDecoder) channel.getPipeline().get(HttpResponseDecoder.class)).replace("ws-decoder", new WebSocket00FrameDecoder(getMaxFramePayloadLength()));
    }

    private static String insertRandomCharacters(String str) {
        int randomNumber = WebSocketUtil.randomNumber(1, 12);
        char[] cArr = new char[randomNumber];
        int i = 0;
        while (i < randomNumber) {
            int random = (int) ((Math.random() * 126.0d) + 33.0d);
            if ((33 < random && random < 47) || (58 < random && random < 126)) {
                cArr[i] = (char) random;
                i++;
            }
        }
        String str2 = str;
        for (int i2 = 0; i2 < randomNumber; i2++) {
            int randomNumber2 = WebSocketUtil.randomNumber(0, str2.length());
            str2 = str2.substring(0, randomNumber2) + cArr[i2] + str2.substring(randomNumber2);
        }
        return str2;
    }

    private static String insertSpaces(String str, int i) {
        String str2 = str;
        for (int i2 = 0; i2 < i; i2++) {
            int randomNumber = WebSocketUtil.randomNumber(1, str2.length() - 1);
            str2 = str2.substring(0, randomNumber) + " " + str2.substring(randomNumber);
        }
        return str2;
    }
}
