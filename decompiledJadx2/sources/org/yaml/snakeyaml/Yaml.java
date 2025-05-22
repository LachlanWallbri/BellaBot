package org.yaml.snakeyaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.composer.Composer;
import org.yaml.snakeyaml.constructor.BaseConstructor;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.emitter.Emitable;
import org.yaml.snakeyaml.emitter.Emitter;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.parser.ParserImpl;
import org.yaml.snakeyaml.reader.StreamReader;
import org.yaml.snakeyaml.reader.UnicodeReader;
import org.yaml.snakeyaml.representer.Representer;
import org.yaml.snakeyaml.resolver.Resolver;
import org.yaml.snakeyaml.serializer.Serializer;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class Yaml {
    protected BaseConstructor constructor;
    protected DumperOptions dumperOptions;
    private String name;
    protected Representer representer;
    protected final Resolver resolver;

    public Yaml() {
        this(new Constructor(), new Representer(), new DumperOptions(), new Resolver());
    }

    public Yaml(DumperOptions dumperOptions) {
        this(new Constructor(), new Representer(), dumperOptions);
    }

    public Yaml(Representer representer) {
        this(new Constructor(), representer);
    }

    public Yaml(BaseConstructor baseConstructor) {
        this(baseConstructor, new Representer());
    }

    public Yaml(BaseConstructor baseConstructor, Representer representer) {
        this(baseConstructor, representer, new DumperOptions());
    }

    public Yaml(Representer representer, DumperOptions dumperOptions) {
        this(new Constructor(), representer, dumperOptions, new Resolver());
    }

    public Yaml(BaseConstructor baseConstructor, Representer representer, DumperOptions dumperOptions) {
        this(baseConstructor, representer, dumperOptions, new Resolver());
    }

    public Yaml(BaseConstructor baseConstructor, Representer representer, DumperOptions dumperOptions, Resolver resolver) {
        if (!baseConstructor.isExplicitPropertyUtils()) {
            baseConstructor.setPropertyUtils(representer.getPropertyUtils());
        } else if (!representer.isExplicitPropertyUtils()) {
            representer.setPropertyUtils(baseConstructor.getPropertyUtils());
        }
        this.constructor = baseConstructor;
        representer.setDefaultFlowStyle(dumperOptions.getDefaultFlowStyle());
        representer.setDefaultScalarStyle(dumperOptions.getDefaultScalarStyle());
        representer.getPropertyUtils().setAllowReadOnlyProperties(dumperOptions.isAllowReadOnlyProperties());
        representer.setTimeZone(dumperOptions.getTimeZone());
        this.representer = representer;
        this.dumperOptions = dumperOptions;
        this.resolver = resolver;
        this.name = "Yaml:" + System.identityHashCode(this);
    }

    public String dump(Object obj) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(obj);
        return dumpAll(arrayList.iterator());
    }

    public Node represent(Object obj) {
        return this.representer.represent(obj);
    }

    public String dumpAll(Iterator<? extends Object> it) {
        StringWriter stringWriter = new StringWriter();
        dumpAll(it, stringWriter, null);
        return stringWriter.toString();
    }

    public void dump(Object obj, Writer writer) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(obj);
        dumpAll(arrayList.iterator(), writer, null);
    }

    public void dumpAll(Iterator<? extends Object> it, Writer writer) {
        dumpAll(it, writer, null);
    }

    private void dumpAll(Iterator<? extends Object> it, Writer writer, Tag tag) {
        Serializer serializer = new Serializer(new Emitter(writer, this.dumperOptions), this.resolver, this.dumperOptions, tag);
        try {
            serializer.open();
            while (it.hasNext()) {
                serializer.serialize(this.representer.represent(it.next()));
            }
            serializer.close();
        } catch (IOException e) {
            throw new YAMLException(e);
        }
    }

    public String dumpAs(Object obj, Tag tag, DumperOptions.FlowStyle flowStyle) {
        DumperOptions.FlowStyle defaultFlowStyle = this.representer.getDefaultFlowStyle();
        if (flowStyle != null) {
            this.representer.setDefaultFlowStyle(flowStyle);
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(obj);
        StringWriter stringWriter = new StringWriter();
        dumpAll(arrayList.iterator(), stringWriter, tag);
        this.representer.setDefaultFlowStyle(defaultFlowStyle);
        return stringWriter.toString();
    }

    public String dumpAsMap(Object obj) {
        return dumpAs(obj, Tag.MAP, DumperOptions.FlowStyle.BLOCK);
    }

    public List<Event> serialize(Node node) {
        SilentEmitter silentEmitter = new SilentEmitter();
        Serializer serializer = new Serializer(silentEmitter, this.resolver, this.dumperOptions, null);
        try {
            serializer.open();
            serializer.serialize(node);
            serializer.close();
            return silentEmitter.getEvents();
        } catch (IOException e) {
            throw new YAMLException(e);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private static class SilentEmitter implements Emitable {
        private List<Event> events;

        private SilentEmitter() {
            this.events = new ArrayList(100);
        }

        public List<Event> getEvents() {
            return this.events;
        }

        @Override // org.yaml.snakeyaml.emitter.Emitable
        public void emit(Event event) throws IOException {
            this.events.add(event);
        }
    }

    public Object load(String str) {
        return loadFromReader(new StreamReader(str), Object.class);
    }

    public Object load(InputStream inputStream) {
        return loadFromReader(new StreamReader(new UnicodeReader(inputStream)), Object.class);
    }

    public Object load(Reader reader) {
        return loadFromReader(new StreamReader(reader), Object.class);
    }

    public <T> T loadAs(Reader reader, Class<T> cls) {
        return (T) loadFromReader(new StreamReader(reader), cls);
    }

    public <T> T loadAs(String str, Class<T> cls) {
        return (T) loadFromReader(new StreamReader(str), cls);
    }

    public <T> T loadAs(InputStream inputStream, Class<T> cls) {
        return (T) loadFromReader(new StreamReader(new UnicodeReader(inputStream)), cls);
    }

    private Object loadFromReader(StreamReader streamReader, Class<?> cls) {
        this.constructor.setComposer(new Composer(new ParserImpl(streamReader), this.resolver));
        return this.constructor.getSingleData(cls);
    }

    public Iterable<Object> loadAll(Reader reader) {
        this.constructor.setComposer(new Composer(new ParserImpl(new StreamReader(reader)), this.resolver));
        return new YamlIterable(new Iterator<Object>() { // from class: org.yaml.snakeyaml.Yaml.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return Yaml.this.constructor.checkData();
            }

            @Override // java.util.Iterator
            public Object next() {
                return Yaml.this.constructor.getData();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public static class YamlIterable implements Iterable<Object> {
        private Iterator<Object> iterator;

        public YamlIterable(Iterator<Object> it) {
            this.iterator = it;
        }

        @Override // java.lang.Iterable
        public Iterator<Object> iterator() {
            return this.iterator;
        }
    }

    public Iterable<Object> loadAll(String str) {
        return loadAll(new StringReader(str));
    }

    public Iterable<Object> loadAll(InputStream inputStream) {
        return loadAll(new UnicodeReader(inputStream));
    }

    public Node compose(Reader reader) {
        Composer composer = new Composer(new ParserImpl(new StreamReader(reader)), this.resolver);
        this.constructor.setComposer(composer);
        return composer.getSingleNode();
    }

    public Iterable<Node> composeAll(Reader reader) {
        final Composer composer = new Composer(new ParserImpl(new StreamReader(reader)), this.resolver);
        this.constructor.setComposer(composer);
        return new NodeIterable(new Iterator<Node>() { // from class: org.yaml.snakeyaml.Yaml.2
            @Override // java.util.Iterator
            public boolean hasNext() {
                return composer.checkNode();
            }

            @Override // java.util.Iterator
            public Node next() {
                return composer.getNode();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private static class NodeIterable implements Iterable<Node> {
        private Iterator<Node> iterator;

        public NodeIterable(Iterator<Node> it) {
            this.iterator = it;
        }

        @Override // java.lang.Iterable
        public Iterator<Node> iterator() {
            return this.iterator;
        }
    }

    public void addImplicitResolver(Tag tag, Pattern pattern, String str) {
        this.resolver.addImplicitResolver(tag, pattern, str);
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Iterable<Event> parse(Reader reader) {
        final ParserImpl parserImpl = new ParserImpl(new StreamReader(reader));
        return new EventIterable(new Iterator<Event>() { // from class: org.yaml.snakeyaml.Yaml.3
            @Override // java.util.Iterator
            public boolean hasNext() {
                return parserImpl.peekEvent() != null;
            }

            @Override // java.util.Iterator
            public Event next() {
                return parserImpl.getEvent();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private static class EventIterable implements Iterable<Event> {
        private Iterator<Event> iterator;

        public EventIterable(Iterator<Event> it) {
            this.iterator = it;
        }

        @Override // java.lang.Iterable
        public Iterator<Event> iterator() {
            return this.iterator;
        }
    }

    public void setBeanAccess(BeanAccess beanAccess) {
        this.constructor.getPropertyUtils().setBeanAccess(beanAccess);
        this.representer.getPropertyUtils().setBeanAccess(beanAccess);
    }
}
