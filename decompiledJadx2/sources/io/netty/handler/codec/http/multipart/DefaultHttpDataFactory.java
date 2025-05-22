package io.netty.handler.codec.http.multipart;

import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.HttpRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class DefaultHttpDataFactory implements HttpDataFactory {
    public static final long MAXSIZE = -1;
    public static final long MINSIZE = 16384;
    private Charset charset;
    private final boolean checkSize;
    private long maxSize;
    private long minSize;
    private final Map<HttpRequest, List<HttpData>> requestFileDeleteMap;
    private final boolean useDisk;

    public DefaultHttpDataFactory() {
        this.maxSize = -1L;
        this.charset = HttpConstants.DEFAULT_CHARSET;
        this.requestFileDeleteMap = Collections.synchronizedMap(new IdentityHashMap());
        this.useDisk = false;
        this.checkSize = true;
        this.minSize = 16384L;
    }

    public DefaultHttpDataFactory(Charset charset) {
        this();
        this.charset = charset;
    }

    public DefaultHttpDataFactory(boolean z) {
        this.maxSize = -1L;
        this.charset = HttpConstants.DEFAULT_CHARSET;
        this.requestFileDeleteMap = Collections.synchronizedMap(new IdentityHashMap());
        this.useDisk = z;
        this.checkSize = false;
    }

    public DefaultHttpDataFactory(boolean z, Charset charset) {
        this(z);
        this.charset = charset;
    }

    public DefaultHttpDataFactory(long j) {
        this.maxSize = -1L;
        this.charset = HttpConstants.DEFAULT_CHARSET;
        this.requestFileDeleteMap = Collections.synchronizedMap(new IdentityHashMap());
        this.useDisk = false;
        this.checkSize = true;
        this.minSize = j;
    }

    public DefaultHttpDataFactory(long j, Charset charset) {
        this(j);
        this.charset = charset;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpDataFactory
    public void setMaxLimit(long j) {
        this.maxSize = j;
    }

    private List<HttpData> getList(HttpRequest httpRequest) {
        List<HttpData> list = this.requestFileDeleteMap.get(httpRequest);
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        this.requestFileDeleteMap.put(httpRequest, arrayList);
        return arrayList;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpDataFactory
    public Attribute createAttribute(HttpRequest httpRequest, String str) {
        if (this.useDisk) {
            DiskAttribute diskAttribute = new DiskAttribute(str, this.charset);
            diskAttribute.setMaxSize(this.maxSize);
            getList(httpRequest).add(diskAttribute);
            return diskAttribute;
        }
        if (this.checkSize) {
            MixedAttribute mixedAttribute = new MixedAttribute(str, this.minSize, this.charset);
            mixedAttribute.setMaxSize(this.maxSize);
            getList(httpRequest).add(mixedAttribute);
            return mixedAttribute;
        }
        MemoryAttribute memoryAttribute = new MemoryAttribute(str);
        memoryAttribute.setMaxSize(this.maxSize);
        return memoryAttribute;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpDataFactory
    public Attribute createAttribute(HttpRequest httpRequest, String str, long j) {
        if (this.useDisk) {
            DiskAttribute diskAttribute = new DiskAttribute(str, j, this.charset);
            diskAttribute.setMaxSize(this.maxSize);
            getList(httpRequest).add(diskAttribute);
            return diskAttribute;
        }
        if (this.checkSize) {
            MixedAttribute mixedAttribute = new MixedAttribute(str, j, this.minSize, this.charset);
            mixedAttribute.setMaxSize(this.maxSize);
            getList(httpRequest).add(mixedAttribute);
            return mixedAttribute;
        }
        MemoryAttribute memoryAttribute = new MemoryAttribute(str, j);
        memoryAttribute.setMaxSize(this.maxSize);
        return memoryAttribute;
    }

    private static void checkHttpDataSize(HttpData httpData) {
        try {
            httpData.checkSize(httpData.length());
        } catch (IOException unused) {
            throw new IllegalArgumentException("Attribute bigger than maxSize allowed");
        }
    }

    @Override // io.netty.handler.codec.http.multipart.HttpDataFactory
    public Attribute createAttribute(HttpRequest httpRequest, String str, String str2) {
        Attribute mixedAttribute;
        if (this.useDisk) {
            try {
                mixedAttribute = new DiskAttribute(str, str2, this.charset);
                mixedAttribute.setMaxSize(this.maxSize);
            } catch (IOException unused) {
                mixedAttribute = new MixedAttribute(str, str2, this.minSize, this.charset);
                mixedAttribute.setMaxSize(this.maxSize);
            }
            checkHttpDataSize(mixedAttribute);
            getList(httpRequest).add(mixedAttribute);
            return mixedAttribute;
        }
        if (this.checkSize) {
            MixedAttribute mixedAttribute2 = new MixedAttribute(str, str2, this.minSize, this.charset);
            mixedAttribute2.setMaxSize(this.maxSize);
            checkHttpDataSize(mixedAttribute2);
            getList(httpRequest).add(mixedAttribute2);
            return mixedAttribute2;
        }
        try {
            MemoryAttribute memoryAttribute = new MemoryAttribute(str, str2, this.charset);
            memoryAttribute.setMaxSize(this.maxSize);
            checkHttpDataSize(memoryAttribute);
            return memoryAttribute;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // io.netty.handler.codec.http.multipart.HttpDataFactory
    public FileUpload createFileUpload(HttpRequest httpRequest, String str, String str2, String str3, String str4, Charset charset, long j) {
        if (this.useDisk) {
            DiskFileUpload diskFileUpload = new DiskFileUpload(str, str2, str3, str4, charset, j);
            diskFileUpload.setMaxSize(this.maxSize);
            checkHttpDataSize(diskFileUpload);
            getList(httpRequest).add(diskFileUpload);
            return diskFileUpload;
        }
        if (this.checkSize) {
            MixedFileUpload mixedFileUpload = new MixedFileUpload(str, str2, str3, str4, charset, j, this.minSize);
            mixedFileUpload.setMaxSize(this.maxSize);
            checkHttpDataSize(mixedFileUpload);
            getList(httpRequest).add(mixedFileUpload);
            return mixedFileUpload;
        }
        MemoryFileUpload memoryFileUpload = new MemoryFileUpload(str, str2, str3, str4, charset, j);
        memoryFileUpload.setMaxSize(this.maxSize);
        checkHttpDataSize(memoryFileUpload);
        return memoryFileUpload;
    }

    @Override // io.netty.handler.codec.http.multipart.HttpDataFactory
    public void removeHttpDataFromClean(HttpRequest httpRequest, InterfaceHttpData interfaceHttpData) {
        List<HttpData> list;
        if ((interfaceHttpData instanceof HttpData) && (list = this.requestFileDeleteMap.get(httpRequest)) != null) {
            Iterator<HttpData> it = list.iterator();
            while (it.hasNext()) {
                if (it.next() == interfaceHttpData) {
                    it.remove();
                    if (list.isEmpty()) {
                        this.requestFileDeleteMap.remove(httpRequest);
                        return;
                    }
                    return;
                }
            }
        }
    }

    @Override // io.netty.handler.codec.http.multipart.HttpDataFactory
    public void cleanRequestHttpData(HttpRequest httpRequest) {
        List<HttpData> remove = this.requestFileDeleteMap.remove(httpRequest);
        if (remove != null) {
            Iterator<HttpData> it = remove.iterator();
            while (it.hasNext()) {
                it.next().release();
            }
        }
    }

    @Override // io.netty.handler.codec.http.multipart.HttpDataFactory
    public void cleanAllHttpData() {
        Iterator<Map.Entry<HttpRequest, List<HttpData>>> it = this.requestFileDeleteMap.entrySet().iterator();
        while (it.hasNext()) {
            Iterator<HttpData> it2 = it.next().getValue().iterator();
            while (it2.hasNext()) {
                it2.next().release();
            }
            it.remove();
        }
    }

    @Override // io.netty.handler.codec.http.multipart.HttpDataFactory
    public void cleanRequestHttpDatas(HttpRequest httpRequest) {
        cleanRequestHttpData(httpRequest);
    }

    @Override // io.netty.handler.codec.http.multipart.HttpDataFactory
    public void cleanAllHttpDatas() {
        cleanAllHttpData();
    }
}
