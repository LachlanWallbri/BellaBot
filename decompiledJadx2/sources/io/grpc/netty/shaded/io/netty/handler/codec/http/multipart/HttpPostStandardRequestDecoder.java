package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpConstants;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http.QueryStringDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostBodyUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.grpc.netty.shaded.io.netty.util.ByteProcessor;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class HttpPostStandardRequestDecoder implements InterfaceHttpPostRequestDecoder {
    private final List<InterfaceHttpData> bodyListHttpData;
    private int bodyListHttpDataRank;
    private final Map<String, List<InterfaceHttpData>> bodyMapHttpData;
    private final Charset charset;
    private Attribute currentAttribute;
    private HttpPostRequestDecoder.MultiPartStatus currentStatus;
    private boolean destroyed;
    private int discardThreshold;
    private final HttpDataFactory factory;
    private boolean isLastChunk;
    private final HttpRequest request;
    private ByteBuf undecodedChunk;

    public HttpPostStandardRequestDecoder(HttpRequest httpRequest) {
        this(new DefaultHttpDataFactory(16384L), httpRequest, HttpConstants.DEFAULT_CHARSET);
    }

    public HttpPostStandardRequestDecoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest) {
        this(httpDataFactory, httpRequest, HttpConstants.DEFAULT_CHARSET);
    }

    public HttpPostStandardRequestDecoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest, Charset charset) {
        this.bodyListHttpData = new ArrayList();
        this.bodyMapHttpData = new TreeMap(CaseIgnoringComparator.INSTANCE);
        this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.NOTSTARTED;
        this.discardThreshold = OSSConfig.DEFAULT_MULTI_PART_UPLOAD_SIZE;
        this.request = (HttpRequest) ObjectUtil.checkNotNull(httpRequest, "request");
        this.charset = (Charset) ObjectUtil.checkNotNull(charset, "charset");
        this.factory = (HttpDataFactory) ObjectUtil.checkNotNull(httpDataFactory, "factory");
        try {
            if (httpRequest instanceof HttpContent) {
                offer((HttpContent) httpRequest);
            } else {
                parseBody();
            }
        } catch (Throwable th) {
            destroy();
            PlatformDependent.throwException(th);
        }
    }

    private void checkDestroyed() {
        if (this.destroyed) {
            throw new IllegalStateException(HttpPostStandardRequestDecoder.class.getSimpleName() + " was destroyed already");
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public boolean isMultipart() {
        checkDestroyed();
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void setDiscardThreshold(int i) {
        this.discardThreshold = ObjectUtil.checkPositiveOrZero(i, "discardThreshold");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public int getDiscardThreshold() {
        return this.discardThreshold;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public List<InterfaceHttpData> getBodyHttpDatas() {
        checkDestroyed();
        if (!this.isLastChunk) {
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
        }
        return this.bodyListHttpData;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public List<InterfaceHttpData> getBodyHttpDatas(String str) {
        checkDestroyed();
        if (!this.isLastChunk) {
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
        }
        return this.bodyMapHttpData.get(str);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public InterfaceHttpData getBodyHttpData(String str) {
        checkDestroyed();
        if (!this.isLastChunk) {
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
        }
        List<InterfaceHttpData> list = this.bodyMapHttpData.get(str);
        if (list != null) {
            return list.get(0);
        }
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public HttpPostStandardRequestDecoder offer(HttpContent httpContent) {
        ByteBuf writeBytes;
        checkDestroyed();
        if (httpContent instanceof LastHttpContent) {
            this.isLastChunk = true;
        }
        ByteBuf content = httpContent.content();
        ByteBuf byteBuf = this.undecodedChunk;
        if (byteBuf == null) {
            if (this.isLastChunk) {
                writeBytes = content.retainedSlice();
            } else {
                writeBytes = content.alloc().buffer(content.readableBytes()).writeBytes(content);
            }
            this.undecodedChunk = writeBytes;
        } else {
            byteBuf.writeBytes(content);
        }
        parseBody();
        ByteBuf byteBuf2 = this.undecodedChunk;
        if (byteBuf2 != null && byteBuf2.writerIndex() > this.discardThreshold) {
            this.undecodedChunk.discardReadBytes();
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public boolean hasNext() {
        checkDestroyed();
        if (this.currentStatus != HttpPostRequestDecoder.MultiPartStatus.EPILOGUE || this.bodyListHttpDataRank < this.bodyListHttpData.size()) {
            return !this.bodyListHttpData.isEmpty() && this.bodyListHttpDataRank < this.bodyListHttpData.size();
        }
        throw new HttpPostRequestDecoder.EndOfDataDecoderException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public InterfaceHttpData next() {
        checkDestroyed();
        if (!hasNext()) {
            return null;
        }
        List<InterfaceHttpData> list = this.bodyListHttpData;
        int i = this.bodyListHttpDataRank;
        this.bodyListHttpDataRank = i + 1;
        return list.get(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public InterfaceHttpData currentPartialHttpData() {
        return this.currentAttribute;
    }

    private void parseBody() {
        if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE || this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.EPILOGUE) {
            if (this.isLastChunk) {
                this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.EPILOGUE;
                return;
            }
            return;
        }
        parseBodyAttributes();
    }

    protected void addHttpData(InterfaceHttpData interfaceHttpData) {
        if (interfaceHttpData == null) {
            return;
        }
        List<InterfaceHttpData> list = this.bodyMapHttpData.get(interfaceHttpData.getName());
        if (list == null) {
            list = new ArrayList<>(1);
            this.bodyMapHttpData.put(interfaceHttpData.getName(), list);
        }
        list.add(interfaceHttpData);
        this.bodyListHttpData.add(interfaceHttpData);
    }

    private void parseBodyAttributesStandard() {
        int readerIndex = this.undecodedChunk.readerIndex();
        if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.NOTSTARTED) {
            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION;
        }
        int i = readerIndex;
        int i2 = i;
        boolean z = true;
        while (this.undecodedChunk.isReadable() && z) {
            try {
                char readUnsignedByte = (char) this.undecodedChunk.readUnsignedByte();
                i++;
                int i3 = C64901.f8372xeab8d0a3[this.currentStatus.ordinal()];
                if (i3 != 1) {
                    if (i3 == 2) {
                        if (readUnsignedByte == '&') {
                            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION;
                            setFinalBuffer(this.undecodedChunk.retainedSlice(i2, (i - 1) - i2));
                            z = true;
                        } else if (readUnsignedByte == '\r') {
                            if (this.undecodedChunk.isReadable()) {
                                i++;
                                if (((char) this.undecodedChunk.readUnsignedByte()) == '\n') {
                                    this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE;
                                    setFinalBuffer(this.undecodedChunk.retainedSlice(i2, (i - 2) - i2));
                                    i2 = i;
                                } else {
                                    throw new HttpPostRequestDecoder.ErrorDataDecoderException("Bad end of line");
                                }
                            } else {
                                i--;
                            }
                        } else if (readUnsignedByte == '\n') {
                            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE;
                            setFinalBuffer(this.undecodedChunk.retainedSlice(i2, (i - 1) - i2));
                            i2 = i;
                        }
                    }
                    z = false;
                } else if (readUnsignedByte == '=') {
                    this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.FIELD;
                    this.currentAttribute = this.factory.createAttribute(this.request, decodeAttribute(this.undecodedChunk.toString(i2, (i - 1) - i2, this.charset), this.charset));
                } else if (readUnsignedByte == '&') {
                    this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION;
                    this.currentAttribute = this.factory.createAttribute(this.request, decodeAttribute(this.undecodedChunk.toString(i2, (i - 1) - i2, this.charset), this.charset));
                    this.currentAttribute.setValue("");
                    addHttpData(this.currentAttribute);
                    this.currentAttribute = null;
                    z = true;
                }
                i2 = i;
            } catch (HttpPostRequestDecoder.ErrorDataDecoderException e) {
                e = e;
            } catch (IOException e2) {
                e = e2;
            } catch (IllegalArgumentException e3) {
                e = e3;
            }
        }
        if (this.isLastChunk && this.currentAttribute != null) {
            if (i > i2) {
                setFinalBuffer(this.undecodedChunk.retainedSlice(i2, i - i2));
            } else if (!this.currentAttribute.isCompleted()) {
                setFinalBuffer(Unpooled.EMPTY_BUFFER);
            }
            try {
                this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.EPILOGUE;
            } catch (HttpPostRequestDecoder.ErrorDataDecoderException e4) {
                e = e4;
                i2 = i;
                this.undecodedChunk.readerIndex(i2);
                throw e;
            } catch (IOException e5) {
                e = e5;
                i2 = i;
                this.undecodedChunk.readerIndex(i2);
                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
            } catch (IllegalArgumentException e6) {
                e = e6;
                i2 = i;
                this.undecodedChunk.readerIndex(i2);
                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
            }
        } else if (z && this.currentAttribute != null && this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.FIELD) {
            this.currentAttribute.addContent(this.undecodedChunk.retainedSlice(i2, i - i2), false);
        } else {
            i = i2;
        }
        this.undecodedChunk.readerIndex(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostStandardRequestDecoder$1 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C64901 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus */
        static final /* synthetic */ int[] f8372xeab8d0a3 = new int[HttpPostRequestDecoder.MultiPartStatus.values().length];

        static {
            try {
                f8372xeab8d0a3[HttpPostRequestDecoder.MultiPartStatus.DISPOSITION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8372xeab8d0a3[HttpPostRequestDecoder.MultiPartStatus.FIELD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:96:0x011b, code lost:
    
        r2 = r1;
        r1 = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void parseBodyAttributes() {
        int i;
        boolean z;
        int i2;
        ByteBuf byteBuf = this.undecodedChunk;
        if (byteBuf == null) {
            return;
        }
        if (!byteBuf.hasArray()) {
            parseBodyAttributesStandard();
            return;
        }
        HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(this.undecodedChunk);
        int readerIndex = this.undecodedChunk.readerIndex();
        if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.NOTSTARTED) {
            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION;
        }
        try {
            loop0: while (true) {
                i = readerIndex;
                while (true) {
                    try {
                        z = true;
                        if (seekAheadOptimize.pos >= seekAheadOptimize.limit) {
                            break loop0;
                        }
                        byte[] bArr = seekAheadOptimize.bytes;
                        int i3 = seekAheadOptimize.pos;
                        seekAheadOptimize.pos = i3 + 1;
                        char c = (char) (bArr[i3] & 255);
                        readerIndex++;
                        int i4 = C64901.f8372xeab8d0a3[this.currentStatus.ordinal()];
                        if (i4 != 1) {
                            if (i4 != 2) {
                                seekAheadOptimize.setReadPosition(0);
                                z = false;
                                break loop0;
                            }
                            if (c == '&') {
                                this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION;
                                setFinalBuffer(this.undecodedChunk.retainedSlice(i, (readerIndex - 1) - i));
                                break;
                            }
                            if (c == '\r') {
                                if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                                    byte[] bArr2 = seekAheadOptimize.bytes;
                                    int i5 = seekAheadOptimize.pos;
                                    seekAheadOptimize.pos = i5 + 1;
                                    readerIndex++;
                                    if (((char) (bArr2[i5] & 255)) == '\n') {
                                        this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE;
                                        seekAheadOptimize.setReadPosition(0);
                                        setFinalBuffer(this.undecodedChunk.retainedSlice(i, (readerIndex - 2) - i));
                                    } else {
                                        seekAheadOptimize.setReadPosition(0);
                                        throw new HttpPostRequestDecoder.ErrorDataDecoderException("Bad end of line");
                                    }
                                } else if (seekAheadOptimize.limit > 0) {
                                    readerIndex--;
                                }
                            } else if (c == '\n') {
                                this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE;
                                seekAheadOptimize.setReadPosition(0);
                                setFinalBuffer(this.undecodedChunk.retainedSlice(i, (readerIndex - 1) - i));
                                break loop0;
                            }
                            if (!this.isLastChunk && this.currentAttribute != null) {
                                if (i2 > readerIndex) {
                                    setFinalBuffer(this.undecodedChunk.retainedSlice(readerIndex, i2 - readerIndex));
                                } else if (!this.currentAttribute.isCompleted()) {
                                    setFinalBuffer(Unpooled.EMPTY_BUFFER);
                                }
                                this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.EPILOGUE;
                            } else if (z || this.currentAttribute == null || this.currentStatus != HttpPostRequestDecoder.MultiPartStatus.FIELD) {
                                i2 = readerIndex;
                            } else {
                                this.currentAttribute.addContent(this.undecodedChunk.retainedSlice(readerIndex, i2 - readerIndex), false);
                            }
                            this.undecodedChunk.readerIndex(i2);
                            return;
                        }
                        if (c == '=') {
                            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.FIELD;
                            this.currentAttribute = this.factory.createAttribute(this.request, decodeAttribute(this.undecodedChunk.toString(i, (readerIndex - 1) - i, this.charset), this.charset));
                            break;
                        } else if (c == '&') {
                            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION;
                            this.currentAttribute = this.factory.createAttribute(this.request, decodeAttribute(this.undecodedChunk.toString(i, (readerIndex - 1) - i, this.charset), this.charset));
                            this.currentAttribute.setValue("");
                            addHttpData(this.currentAttribute);
                            this.currentAttribute = null;
                            break;
                        }
                    } catch (HttpPostRequestDecoder.ErrorDataDecoderException e) {
                        e = e;
                    } catch (IOException e2) {
                        e = e2;
                    } catch (IllegalArgumentException e3) {
                        e = e3;
                    }
                }
            }
            if (!this.isLastChunk) {
            }
            if (z) {
            }
            i2 = readerIndex;
            this.undecodedChunk.readerIndex(i2);
            return;
        } catch (HttpPostRequestDecoder.ErrorDataDecoderException e4) {
            e = e4;
            i = readerIndex;
            this.undecodedChunk.readerIndex(i);
            throw e;
        } catch (IOException e5) {
            e = e5;
            i = readerIndex;
            this.undecodedChunk.readerIndex(i);
            throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
        } catch (IllegalArgumentException e6) {
            e = e6;
            i = readerIndex;
            this.undecodedChunk.readerIndex(i);
            throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
        }
        i2 = readerIndex;
        z = false;
    }

    private void setFinalBuffer(ByteBuf byteBuf) throws IOException {
        this.currentAttribute.addContent(byteBuf, true);
        ByteBuf decodeAttribute = decodeAttribute(this.currentAttribute.getByteBuf(), this.charset);
        if (decodeAttribute != null) {
            this.currentAttribute.setContent(decodeAttribute);
        }
        addHttpData(this.currentAttribute);
        this.currentAttribute = null;
    }

    private static String decodeAttribute(String str, Charset charset) {
        try {
            return QueryStringDecoder.decodeComponent(str, charset);
        } catch (IllegalArgumentException e) {
            throw new HttpPostRequestDecoder.ErrorDataDecoderException("Bad string: '" + str + '\'', e);
        }
    }

    private static ByteBuf decodeAttribute(ByteBuf byteBuf, Charset charset) {
        if (byteBuf.forEachByte(new UrlEncodedDetector(null)) == -1) {
            return null;
        }
        ByteBuf buffer = byteBuf.alloc().buffer(byteBuf.readableBytes());
        UrlDecoder urlDecoder = new UrlDecoder(buffer);
        int forEachByte = byteBuf.forEachByte(urlDecoder);
        if (urlDecoder.nextEscapedIdx == 0) {
            return buffer;
        }
        if (forEachByte == -1) {
            forEachByte = byteBuf.readableBytes() - 1;
        }
        int i = forEachByte - (urlDecoder.nextEscapedIdx - 1);
        buffer.release();
        throw new HttpPostRequestDecoder.ErrorDataDecoderException(String.format("Invalid hex byte at index '%d' in string: '%s'", Integer.valueOf(i), byteBuf.toString(charset)));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void destroy() {
        cleanFiles();
        this.destroyed = true;
        ByteBuf byteBuf = this.undecodedChunk;
        if (byteBuf == null || byteBuf.refCnt() <= 0) {
            return;
        }
        this.undecodedChunk.release();
        this.undecodedChunk = null;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void cleanFiles() {
        checkDestroyed();
        this.factory.cleanRequestHttpData(this.request);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void removeHttpDataFromClean(InterfaceHttpData interfaceHttpData) {
        checkDestroyed();
        this.factory.removeHttpDataFromClean(this.request, interfaceHttpData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class UrlEncodedDetector implements ByteProcessor {
        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            return (b == 37 || b == 43) ? false : true;
        }

        private UrlEncodedDetector() {
        }

        /* synthetic */ UrlEncodedDetector(C64901 c64901) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class UrlDecoder implements ByteProcessor {
        private byte hiByte;
        private int nextEscapedIdx;
        private final ByteBuf output;

        UrlDecoder(ByteBuf byteBuf) {
            this.output = byteBuf;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) {
            int i = this.nextEscapedIdx;
            if (i != 0) {
                if (i == 1) {
                    this.hiByte = b;
                    this.nextEscapedIdx = i + 1;
                } else {
                    int decodeHexNibble = StringUtil.decodeHexNibble((char) this.hiByte);
                    int decodeHexNibble2 = StringUtil.decodeHexNibble((char) b);
                    if (decodeHexNibble == -1 || decodeHexNibble2 == -1) {
                        this.nextEscapedIdx++;
                        return false;
                    }
                    this.output.writeByte((decodeHexNibble << 4) + decodeHexNibble2);
                    this.nextEscapedIdx = 0;
                }
            } else if (b == 37) {
                this.nextEscapedIdx = 1;
            } else if (b == 43) {
                this.output.writeByte(32);
            } else {
                this.output.writeByte(b);
            }
            return true;
        }
    }
}
