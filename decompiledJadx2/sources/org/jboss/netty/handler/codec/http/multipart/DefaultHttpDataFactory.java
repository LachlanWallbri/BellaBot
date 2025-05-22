package org.jboss.netty.handler.codec.http.multipart;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.jboss.netty.handler.codec.http.HttpRequest;

/* loaded from: classes7.dex */
public class DefaultHttpDataFactory implements HttpDataFactory {
    public static long MINSIZE = 16384;
    private final boolean checkSize;
    private long minSize;
    private final ConcurrentHashMap<HttpRequest, List<HttpData>> requestFileDeleteMap;
    private final boolean useDisk;

    public DefaultHttpDataFactory() {
        this.requestFileDeleteMap = new ConcurrentHashMap<>();
        this.useDisk = false;
        this.checkSize = true;
        this.minSize = MINSIZE;
    }

    public DefaultHttpDataFactory(boolean z) {
        this.requestFileDeleteMap = new ConcurrentHashMap<>();
        this.useDisk = z;
        this.checkSize = false;
    }

    public DefaultHttpDataFactory(long j) {
        this.requestFileDeleteMap = new ConcurrentHashMap<>();
        this.useDisk = false;
        this.checkSize = true;
        this.minSize = j;
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

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpDataFactory
    public Attribute createAttribute(HttpRequest httpRequest, String str) {
        if (this.useDisk) {
            DiskAttribute diskAttribute = new DiskAttribute(str);
            getList(httpRequest).add(diskAttribute);
            return diskAttribute;
        }
        if (this.checkSize) {
            MixedAttribute mixedAttribute = new MixedAttribute(str, this.minSize);
            getList(httpRequest).add(mixedAttribute);
            return mixedAttribute;
        }
        return new MemoryAttribute(str);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpDataFactory
    public Attribute createAttribute(HttpRequest httpRequest, String str, String str2) {
        Attribute mixedAttribute;
        if (this.useDisk) {
            try {
                mixedAttribute = new DiskAttribute(str, str2);
            } catch (IOException unused) {
                mixedAttribute = new MixedAttribute(str, str2, this.minSize);
            }
            getList(httpRequest).add(mixedAttribute);
            return mixedAttribute;
        }
        if (this.checkSize) {
            MixedAttribute mixedAttribute2 = new MixedAttribute(str, str2, this.minSize);
            getList(httpRequest).add(mixedAttribute2);
            return mixedAttribute2;
        }
        try {
            return new MemoryAttribute(str, str2);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpDataFactory
    public FileUpload createFileUpload(HttpRequest httpRequest, String str, String str2, String str3, String str4, Charset charset, long j) {
        if (this.useDisk) {
            DiskFileUpload diskFileUpload = new DiskFileUpload(str, str2, str3, str4, charset, j);
            getList(httpRequest).add(diskFileUpload);
            return diskFileUpload;
        }
        if (this.checkSize) {
            MixedFileUpload mixedFileUpload = new MixedFileUpload(str, str2, str3, str4, charset, j, this.minSize);
            getList(httpRequest).add(mixedFileUpload);
            return mixedFileUpload;
        }
        return new MemoryFileUpload(str, str2, str3, str4, charset, j);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpDataFactory
    public void removeHttpDataFromClean(HttpRequest httpRequest, InterfaceHttpData interfaceHttpData) {
        if (interfaceHttpData instanceof HttpData) {
            getList(httpRequest).remove(interfaceHttpData);
        }
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpDataFactory
    public void cleanRequestHttpDatas(HttpRequest httpRequest) {
        List<HttpData> remove = this.requestFileDeleteMap.remove(httpRequest);
        if (remove != null) {
            Iterator<HttpData> it = remove.iterator();
            while (it.hasNext()) {
                it.next().delete();
            }
            remove.clear();
        }
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpDataFactory
    public void cleanAllHttpDatas() {
        for (HttpRequest httpRequest : this.requestFileDeleteMap.keySet()) {
            List<HttpData> list = this.requestFileDeleteMap.get(httpRequest);
            if (list != null) {
                Iterator<HttpData> it = list.iterator();
                while (it.hasNext()) {
                    it.next().delete();
                }
                list.clear();
            }
            this.requestFileDeleteMap.remove(httpRequest);
        }
    }
}
