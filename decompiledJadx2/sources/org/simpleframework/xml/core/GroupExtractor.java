package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.stream.Format;

/* loaded from: classes9.dex */
class GroupExtractor implements Group {
    private final ExtractorFactory factory;
    private final Annotation label;
    private final LabelMap elements = new LabelMap();
    private final Registry registry = new Registry(this.elements);

    public GroupExtractor(Contact contact, Annotation annotation, Format format) throws Exception {
        this.factory = new ExtractorFactory(contact, annotation, format);
        this.label = annotation;
        extract();
    }

    public String[] getNames() throws Exception {
        return this.elements.getKeys();
    }

    public String[] getPaths() throws Exception {
        return this.elements.getPaths();
    }

    @Override // org.simpleframework.xml.core.Group
    public LabelMap getElements() throws Exception {
        return this.elements.getLabels();
    }

    @Override // org.simpleframework.xml.core.Group
    public Label getLabel(Class cls) {
        return this.registry.resolve(cls);
    }

    @Override // org.simpleframework.xml.core.Group
    public Label getText() {
        return this.registry.resolveText();
    }

    public boolean isValid(Class cls) {
        return this.registry.resolve(cls) != null;
    }

    public boolean isDeclared(Class cls) {
        return this.registry.containsKey(cls);
    }

    @Override // org.simpleframework.xml.core.Group
    public boolean isInline() {
        Iterator<Label> it = this.registry.iterator();
        while (it.hasNext()) {
            if (!it.next().isInline()) {
                return false;
            }
        }
        return !this.registry.isEmpty();
    }

    @Override // org.simpleframework.xml.core.Group
    public boolean isTextList() {
        return this.registry.isText();
    }

    private void extract() throws Exception {
        Extractor extractorFactory = this.factory.getInstance();
        if (extractorFactory != null) {
            extract(extractorFactory);
        }
    }

    private void extract(Extractor extractor) throws Exception {
        for (Annotation annotation : extractor.getAnnotations()) {
            extract(extractor, annotation);
        }
    }

    private void extract(Extractor extractor, Annotation annotation) throws Exception {
        Label label = extractor.getLabel(annotation);
        Class type = extractor.getType(annotation);
        Registry registry = this.registry;
        if (registry != null) {
            registry.register(type, label);
        }
    }

    @Override // org.simpleframework.xml.core.Group
    public String toString() {
        return this.label.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class Registry extends LinkedHashMap<Class, Label> implements Iterable<Label> {
        private LabelMap elements;
        private Label text;

        public Registry(LabelMap labelMap) {
            this.elements = labelMap;
        }

        public boolean isText() {
            return this.text != null;
        }

        @Override // java.lang.Iterable
        public Iterator<Label> iterator() {
            return values().iterator();
        }

        public Label resolveText() {
            return resolveText(String.class);
        }

        public Label resolve(Class cls) {
            Label resolveText = resolveText(cls);
            return resolveText == null ? resolveElement(cls) : resolveText;
        }

        private Label resolveText(Class cls) {
            Label label = this.text;
            if (label == null || cls != String.class) {
                return null;
            }
            return label;
        }

        private Label resolveElement(Class cls) {
            while (cls != null) {
                Label label = get(cls);
                if (label != null) {
                    return label;
                }
                cls = cls.getSuperclass();
            }
            return null;
        }

        public void register(Class cls, Label label) throws Exception {
            CacheLabel cacheLabel = new CacheLabel(label);
            registerElement(cls, cacheLabel);
            registerText(cacheLabel);
        }

        private void registerElement(Class cls, Label label) throws Exception {
            String name = label.getName();
            if (!this.elements.containsKey(name)) {
                this.elements.put(name, label);
            }
            if (containsKey(cls)) {
                return;
            }
            put(cls, label);
        }

        private void registerText(Label label) throws Exception {
            Text text = (Text) label.getContact().getAnnotation(Text.class);
            if (text != null) {
                this.text = new TextListLabel(label, text);
            }
        }
    }
}
