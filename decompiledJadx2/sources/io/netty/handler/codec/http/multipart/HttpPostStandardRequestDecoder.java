package io.netty.handler.codec.http.multipart;

import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.HttpPostBodyUtil;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
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
        if (httpRequest instanceof HttpContent) {
            offer((HttpContent) httpRequest);
        } else {
            this.undecodedChunk = Unpooled.buffer();
            parseBody();
        }
    }

    private void checkDestroyed() {
        if (this.destroyed) {
            throw new IllegalStateException(HttpPostStandardRequestDecoder.class.getSimpleName() + " was destroyed already");
        }
    }

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public boolean isMultipart() {
        checkDestroyed();
        return false;
    }

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void setDiscardThreshold(int i) {
        this.discardThreshold = ObjectUtil.checkPositiveOrZero(i, "discardThreshold");
    }

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public int getDiscardThreshold() {
        return this.discardThreshold;
    }

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public List<InterfaceHttpData> getBodyHttpDatas() {
        checkDestroyed();
        if (!this.isLastChunk) {
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
        }
        return this.bodyListHttpData;
    }

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public List<InterfaceHttpData> getBodyHttpDatas(String str) {
        checkDestroyed();
        if (!this.isLastChunk) {
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
        }
        return this.bodyMapHttpData.get(str);
    }

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
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

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public HttpPostStandardRequestDecoder offer(HttpContent httpContent) {
        checkDestroyed();
        ByteBuf content = httpContent.content();
        ByteBuf byteBuf = this.undecodedChunk;
        if (byteBuf == null) {
            this.undecodedChunk = content.copy();
        } else {
            byteBuf.writeBytes(content);
        }
        if (httpContent instanceof LastHttpContent) {
            this.isLastChunk = true;
        }
        parseBody();
        ByteBuf byteBuf2 = this.undecodedChunk;
        if (byteBuf2 != null && byteBuf2.writerIndex() > this.discardThreshold) {
            this.undecodedChunk.discardReadBytes();
        }
        return this;
    }

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public boolean hasNext() {
        checkDestroyed();
        if (this.currentStatus != HttpPostRequestDecoder.MultiPartStatus.EPILOGUE || this.bodyListHttpDataRank < this.bodyListHttpData.size()) {
            return !this.bodyListHttpData.isEmpty() && this.bodyListHttpDataRank < this.bodyListHttpData.size();
        }
        throw new HttpPostRequestDecoder.EndOfDataDecoderException();
    }

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
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

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
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

    /* JADX WARN: Code restructure failed: missing block: B:61:0x00ee, code lost:
    
        if (r9.isLastChunk == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00f2, code lost:
    
        if (r9.currentAttribute == null) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00f4, code lost:
    
        if (r0 <= r2) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00f6, code lost:
    
        setFinalBuffer(r9.undecodedChunk.copy(r2, r0 - r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x010f, code lost:
    
        r9.currentStatus = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.EPILOGUE;
        r9.undecodedChunk.readerIndex(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0118, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0108, code lost:
    
        if (r9.currentAttribute.isCompleted() != false) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x010a, code lost:
    
        setFinalBuffer(io.netty.buffer.Unpooled.EMPTY_BUFFER);
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0119, code lost:
    
        if (r3 == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x011d, code lost:
    
        if (r9.currentAttribute == null) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0123, code lost:
    
        if (r9.currentStatus != io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.FIELD) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0125, code lost:
    
        r9.currentAttribute.addContent(r9.undecodedChunk.copy(r2, r0 - r2), false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0134, code lost:
    
        r9.undecodedChunk.readerIndex(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0133, code lost:
    
        r0 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0142, code lost:
    
        r9.undecodedChunk.readerIndex(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0147, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x013e, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x013f, code lost:
    
        r2 = r0;
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0155, code lost:
    
        r9.undecodedChunk.readerIndex(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x015a, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x013a, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x013b, code lost:
    
        r2 = r0;
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0149, code lost:
    
        r9.undecodedChunk.readerIndex(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0153, code lost:
    
        throw new io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.ErrorDataDecoderException(r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void parseBodyAttributesStandard() {
        int readerIndex = this.undecodedChunk.readerIndex();
        if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.NOTSTARTED) {
            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION;
        }
        loop0: while (true) {
            int i = readerIndex;
            boolean z = true;
            while (this.undecodedChunk.isReadable() && z) {
                try {
                    char readUnsignedByte = (char) this.undecodedChunk.readUnsignedByte();
                    readerIndex++;
                    int i2 = C71551.f8502xeab8d0a3[this.currentStatus.ordinal()];
                    if (i2 != 1) {
                        if (i2 == 2) {
                            if (readUnsignedByte == '&') {
                                this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION;
                                setFinalBuffer(this.undecodedChunk.copy(i, (readerIndex - 1) - i));
                            } else if (readUnsignedByte == '\r') {
                                if (this.undecodedChunk.isReadable()) {
                                    readerIndex++;
                                    if (((char) this.undecodedChunk.readUnsignedByte()) == '\n') {
                                        this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE;
                                        setFinalBuffer(this.undecodedChunk.copy(i, (readerIndex - 2) - i));
                                        i = readerIndex;
                                    } else {
                                        throw new HttpPostRequestDecoder.ErrorDataDecoderException("Bad end of line");
                                    }
                                } else {
                                    readerIndex--;
                                }
                            } else if (readUnsignedByte == '\n') {
                                this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE;
                                setFinalBuffer(this.undecodedChunk.copy(i, (readerIndex - 1) - i));
                                i = readerIndex;
                            }
                        }
                        z = false;
                    } else if (readUnsignedByte == '=') {
                        this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.FIELD;
                        this.currentAttribute = this.factory.createAttribute(this.request, decodeAttribute(this.undecodedChunk.toString(i, (readerIndex - 1) - i, this.charset), this.charset));
                        i = readerIndex;
                    } else if (readUnsignedByte == '&') {
                        this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION;
                        Attribute createAttribute = this.factory.createAttribute(this.request, decodeAttribute(this.undecodedChunk.toString(i, (readerIndex - 1) - i, this.charset), this.charset));
                        this.currentAttribute = createAttribute;
                        createAttribute.setValue("");
                        addHttpData(this.currentAttribute);
                        this.currentAttribute = null;
                    }
                } catch (HttpPostRequestDecoder.ErrorDataDecoderException e) {
                    e = e;
                } catch (IOException e2) {
                    e = e2;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.codec.http.multipart.HttpPostStandardRequestDecoder$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C71551 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus */
        static final /* synthetic */ int[] f8502xeab8d0a3;

        static {
            int[] iArr = new int[HttpPostRequestDecoder.MultiPartStatus.values().length];
            f8502xeab8d0a3 = iArr;
            try {
                iArr[HttpPostRequestDecoder.MultiPartStatus.DISPOSITION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8502xeab8d0a3[HttpPostRequestDecoder.MultiPartStatus.FIELD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void parseBodyAttributes() {
        int i;
        boolean z;
        if (!this.undecodedChunk.hasArray()) {
            parseBodyAttributesStandard();
            return;
        }
        HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(this.undecodedChunk);
        int readerIndex = this.undecodedChunk.readerIndex();
        if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.NOTSTARTED) {
            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION;
        }
        loop0: while (true) {
            i = readerIndex;
            while (true) {
                try {
                    z = true;
                    if (seekAheadOptimize.pos >= seekAheadOptimize.limit) {
                        break loop0;
                    }
                    byte[] bArr = seekAheadOptimize.bytes;
                    int i2 = seekAheadOptimize.pos;
                    seekAheadOptimize.pos = i2 + 1;
                    char c = (char) (bArr[i2] & 255);
                    readerIndex++;
                    int i3 = C71551.f8502xeab8d0a3[this.currentStatus.ordinal()];
                    if (i3 != 1) {
                        if (i3 != 2) {
                            seekAheadOptimize.setReadPosition(0);
                            break loop0;
                        }
                        if (c == '&') {
                            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION;
                            setFinalBuffer(this.undecodedChunk.copy(i, (readerIndex - 1) - i));
                            break;
                        }
                        if (c == '\r') {
                            if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                                byte[] bArr2 = seekAheadOptimize.bytes;
                                int i4 = seekAheadOptimize.pos;
                                seekAheadOptimize.pos = i4 + 1;
                                readerIndex++;
                                if (((char) (bArr2[i4] & 255)) == '\n') {
                                    this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE;
                                    seekAheadOptimize.setReadPosition(0);
                                    setFinalBuffer(this.undecodedChunk.copy(i, (readerIndex - 2) - i));
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
                            setFinalBuffer(this.undecodedChunk.copy(i, (readerIndex - 1) - i));
                            break loop0;
                        }
                    } else if (c == '=') {
                        this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.FIELD;
                        this.currentAttribute = this.factory.createAttribute(this.request, decodeAttribute(this.undecodedChunk.toString(i, (readerIndex - 1) - i, this.charset), this.charset));
                        break;
                    } else if (c == '&') {
                        this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION;
                        Attribute createAttribute = this.factory.createAttribute(this.request, decodeAttribute(this.undecodedChunk.toString(i, (readerIndex - 1) - i, this.charset), this.charset));
                        this.currentAttribute = createAttribute;
                        createAttribute.setValue("");
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
        i = readerIndex;
        z = false;
        if (this.isLastChunk && this.currentAttribute != null) {
            if (readerIndex > i) {
                setFinalBuffer(this.undecodedChunk.copy(i, readerIndex - i));
            } else if (!this.currentAttribute.isCompleted()) {
                setFinalBuffer(Unpooled.EMPTY_BUFFER);
            }
            try {
                this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.EPILOGUE;
                this.undecodedChunk.readerIndex(readerIndex);
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
        }
        if (z && this.currentAttribute != null) {
            if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.FIELD) {
                this.currentAttribute.addContent(this.undecodedChunk.copy(i, readerIndex - i), false);
                i = readerIndex;
            }
            this.undecodedChunk.readerIndex(i);
            return;
        }
        this.undecodedChunk.readerIndex(i);
    }

    private void setFinalBuffer(ByteBuf byteBuf) throws IOException {
        this.currentAttribute.addContent(byteBuf, true);
        this.currentAttribute.setValue(decodeAttribute(this.currentAttribute.getByteBuf().toString(this.charset), this.charset));
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

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
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

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void cleanFiles() {
        checkDestroyed();
        this.factory.cleanRequestHttpData(this.request);
    }

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void removeHttpDataFromClean(InterfaceHttpData interfaceHttpData) {
        checkDestroyed();
        this.factory.removeHttpDataFromClean(this.request, interfaceHttpData);
    }
}
