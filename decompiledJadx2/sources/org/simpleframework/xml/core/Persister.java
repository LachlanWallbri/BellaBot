package org.simpleframework.xml.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.filter.Filter;
import org.simpleframework.xml.filter.PlatformFilter;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.TreeStrategy;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeBuilder;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.transform.Matcher;

/* loaded from: classes9.dex */
public class Persister implements Serializer {
    private final Format format;
    private final SessionManager manager;
    private final Strategy strategy;
    private final Support support;

    public Persister() {
        this(new HashMap());
    }

    public Persister(Format format) {
        this(new TreeStrategy(), format);
    }

    public Persister(Map map) {
        this(new PlatformFilter(map));
    }

    public Persister(Map map, Format format) {
        this(new PlatformFilter(map));
    }

    public Persister(Filter filter) {
        this(new TreeStrategy(), filter);
    }

    public Persister(Filter filter, Format format) {
        this(new TreeStrategy(), filter, format);
    }

    public Persister(Matcher matcher) {
        this(new TreeStrategy(), matcher);
    }

    public Persister(Matcher matcher, Format format) {
        this(new TreeStrategy(), matcher, format);
    }

    public Persister(Strategy strategy) {
        this(strategy, new HashMap());
    }

    public Persister(Strategy strategy, Format format) {
        this(strategy, new HashMap(), format);
    }

    public Persister(Filter filter, Matcher matcher) {
        this(new TreeStrategy(), filter, matcher);
    }

    public Persister(Filter filter, Matcher matcher, Format format) {
        this(new TreeStrategy(), filter, matcher, format);
    }

    public Persister(Strategy strategy, Map map) {
        this(strategy, new PlatformFilter(map));
    }

    public Persister(Strategy strategy, Map map, Format format) {
        this(strategy, new PlatformFilter(map), format);
    }

    public Persister(Strategy strategy, Filter filter) {
        this(strategy, filter, new Format());
    }

    public Persister(Strategy strategy, Filter filter, Format format) {
        this(strategy, filter, new EmptyMatcher(), format);
    }

    public Persister(Strategy strategy, Matcher matcher) {
        this(strategy, new PlatformFilter(), matcher);
    }

    public Persister(Strategy strategy, Matcher matcher, Format format) {
        this(strategy, new PlatformFilter(), matcher, format);
    }

    public Persister(Strategy strategy, Filter filter, Matcher matcher) {
        this(strategy, filter, matcher, new Format());
    }

    public Persister(Strategy strategy, Filter filter, Matcher matcher, Format format) {
        this.support = new Support(filter, matcher, format);
        this.manager = new SessionManager();
        this.strategy = strategy;
        this.format = format;
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(Class<? extends T> cls, String str) throws Exception {
        return (T) read((Class) cls, str, true);
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(Class<? extends T> cls, File file) throws Exception {
        return (T) read((Class) cls, file, true);
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(Class<? extends T> cls, InputStream inputStream) throws Exception {
        return (T) read((Class) cls, inputStream, true);
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(Class<? extends T> cls, Reader reader) throws Exception {
        return (T) read((Class) cls, reader, true);
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(Class<? extends T> cls, InputNode inputNode) throws Exception {
        return (T) read((Class) cls, inputNode, true);
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(Class<? extends T> cls, String str, boolean z) throws Exception {
        return (T) read((Class) cls, (Reader) new StringReader(str), z);
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(Class<? extends T> cls, File file, boolean z) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return (T) read((Class) cls, (InputStream) fileInputStream, z);
        } finally {
            fileInputStream.close();
        }
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(Class<? extends T> cls, InputStream inputStream, boolean z) throws Exception {
        return (T) read((Class) cls, NodeBuilder.read(inputStream), z);
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(Class<? extends T> cls, Reader reader, boolean z) throws Exception {
        return (T) read((Class) cls, NodeBuilder.read(reader), z);
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(Class<? extends T> cls, InputNode inputNode, boolean z) throws Exception {
        try {
            return (T) read((Class) cls, inputNode, this.manager.open(z));
        } finally {
            this.manager.close();
        }
    }

    private <T> T read(Class<? extends T> cls, InputNode inputNode, Session session) throws Exception {
        return (T) read((Class) cls, inputNode, (Context) new Source(this.strategy, this.support, session));
    }

    private <T> T read(Class<? extends T> cls, InputNode inputNode, Context context) throws Exception {
        return (T) new Traverser(context).read(inputNode, (Class) cls);
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(T t, String str) throws Exception {
        return (T) read((Persister) t, str, true);
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(T t, File file) throws Exception {
        return (T) read((Persister) t, file, true);
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(T t, InputStream inputStream) throws Exception {
        return (T) read((Persister) t, inputStream, true);
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(T t, Reader reader) throws Exception {
        return (T) read((Persister) t, reader, true);
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(T t, InputNode inputNode) throws Exception {
        return (T) read((Persister) t, inputNode, true);
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(T t, String str, boolean z) throws Exception {
        return (T) read((Persister) t, (Reader) new StringReader(str), z);
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(T t, File file, boolean z) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return (T) read((Persister) t, (InputStream) fileInputStream, z);
        } finally {
            fileInputStream.close();
        }
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(T t, InputStream inputStream, boolean z) throws Exception {
        return (T) read((Persister) t, NodeBuilder.read(inputStream), z);
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(T t, Reader reader, boolean z) throws Exception {
        return (T) read((Persister) t, NodeBuilder.read(reader), z);
    }

    @Override // org.simpleframework.xml.Serializer
    public <T> T read(T t, InputNode inputNode, boolean z) throws Exception {
        try {
            return (T) read((Persister) t, inputNode, this.manager.open(z));
        } finally {
            this.manager.close();
        }
    }

    private <T> T read(T t, InputNode inputNode, Session session) throws Exception {
        return (T) read((Persister) t, inputNode, (Context) new Source(this.strategy, this.support, session));
    }

    private <T> T read(T t, InputNode inputNode, Context context) throws Exception {
        return (T) new Traverser(context).read(inputNode, t);
    }

    @Override // org.simpleframework.xml.Serializer
    public boolean validate(Class cls, String str) throws Exception {
        return validate(cls, str, true);
    }

    @Override // org.simpleframework.xml.Serializer
    public boolean validate(Class cls, File file) throws Exception {
        return validate(cls, file, true);
    }

    @Override // org.simpleframework.xml.Serializer
    public boolean validate(Class cls, InputStream inputStream) throws Exception {
        return validate(cls, inputStream, true);
    }

    @Override // org.simpleframework.xml.Serializer
    public boolean validate(Class cls, Reader reader) throws Exception {
        return validate(cls, reader, true);
    }

    @Override // org.simpleframework.xml.Serializer
    public boolean validate(Class cls, InputNode inputNode) throws Exception {
        return validate(cls, inputNode, true);
    }

    @Override // org.simpleframework.xml.Serializer
    public boolean validate(Class cls, String str, boolean z) throws Exception {
        return validate(cls, new StringReader(str), z);
    }

    @Override // org.simpleframework.xml.Serializer
    public boolean validate(Class cls, File file, boolean z) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return validate(cls, fileInputStream, z);
        } finally {
            fileInputStream.close();
        }
    }

    @Override // org.simpleframework.xml.Serializer
    public boolean validate(Class cls, InputStream inputStream, boolean z) throws Exception {
        return validate(cls, NodeBuilder.read(inputStream), z);
    }

    @Override // org.simpleframework.xml.Serializer
    public boolean validate(Class cls, Reader reader, boolean z) throws Exception {
        return validate(cls, NodeBuilder.read(reader), z);
    }

    @Override // org.simpleframework.xml.Serializer
    public boolean validate(Class cls, InputNode inputNode, boolean z) throws Exception {
        try {
            return validate(cls, inputNode, this.manager.open(z));
        } finally {
            this.manager.close();
        }
    }

    private boolean validate(Class cls, InputNode inputNode, Session session) throws Exception {
        return validate(cls, inputNode, new Source(this.strategy, this.support, session));
    }

    private boolean validate(Class cls, InputNode inputNode, Context context) throws Exception {
        return new Traverser(context).validate(inputNode, cls);
    }

    @Override // org.simpleframework.xml.Serializer
    public void write(Object obj, OutputNode outputNode) throws Exception {
        try {
            write(obj, outputNode, this.manager.open());
        } finally {
            this.manager.close();
        }
    }

    private void write(Object obj, OutputNode outputNode, Session session) throws Exception {
        write(obj, outputNode, new Source(this.strategy, this.support, session));
    }

    private void write(Object obj, OutputNode outputNode, Context context) throws Exception {
        new Traverser(context).write(outputNode, obj);
    }

    @Override // org.simpleframework.xml.Serializer
    public void write(Object obj, File file) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            write(obj, fileOutputStream);
        } finally {
            fileOutputStream.close();
        }
    }

    @Override // org.simpleframework.xml.Serializer
    public void write(Object obj, OutputStream outputStream) throws Exception {
        write(obj, outputStream, "utf-8");
    }

    public void write(Object obj, OutputStream outputStream, String str) throws Exception {
        write(obj, new OutputStreamWriter(outputStream, str));
    }

    @Override // org.simpleframework.xml.Serializer
    public void write(Object obj, Writer writer) throws Exception {
        write(obj, NodeBuilder.write(writer, this.format));
    }
}
