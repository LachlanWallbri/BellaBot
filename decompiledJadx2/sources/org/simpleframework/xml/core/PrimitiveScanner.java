package org.simpleframework.xml.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Version;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class PrimitiveScanner implements Scanner {
    private final Detail detail;
    private final Section section = new EmptySection(this);

    @Override // org.simpleframework.xml.core.Scanner
    public Function getCommit() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Function getComplete() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Decorator getDecorator() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Instantiator getInstantiator() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Scanner
    public String getName() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Order getOrder() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Function getPersist() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Function getReplace() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Function getResolve() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Version getRevision() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Signature getSignature() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Label getText() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Function getValidate() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Label getVersion() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Scanner
    public boolean isEmpty() {
        return true;
    }

    @Override // org.simpleframework.xml.core.Scanner
    public boolean isPrimitive() {
        return true;
    }

    @Override // org.simpleframework.xml.core.Scanner, org.simpleframework.xml.core.Policy
    public boolean isStrict() {
        return true;
    }

    public PrimitiveScanner(Detail detail) {
        this.detail = detail;
    }

    @Override // org.simpleframework.xml.core.Scanner
    public List<Signature> getSignatures() {
        return new LinkedList();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public ParameterMap getParameters() {
        return new ParameterMap();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Class getType() {
        return this.detail.getType();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Caller getCaller(Context context) {
        return new Caller(this, context);
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Section getSection() {
        return this.section;
    }

    /* loaded from: classes9.dex */
    private static class EmptySection implements Section {
        private final List<String> list = new LinkedList();
        private final Scanner scanner;

        @Override // org.simpleframework.xml.core.Section
        public String getAttribute(String str) {
            return null;
        }

        @Override // org.simpleframework.xml.core.Section
        public Label getElement(String str) {
            return null;
        }

        @Override // org.simpleframework.xml.core.Section
        public String getName() {
            return null;
        }

        @Override // org.simpleframework.xml.core.Section
        public String getPath(String str) {
            return null;
        }

        @Override // org.simpleframework.xml.core.Section
        public String getPrefix() {
            return null;
        }

        @Override // org.simpleframework.xml.core.Section
        public Section getSection(String str) {
            return null;
        }

        @Override // org.simpleframework.xml.core.Section
        public Label getText() {
            return null;
        }

        @Override // org.simpleframework.xml.core.Section
        public boolean isSection(String str) {
            return false;
        }

        public EmptySection(Scanner scanner) {
            this.scanner = scanner;
        }

        @Override // java.lang.Iterable
        public Iterator<String> iterator() {
            return this.list.iterator();
        }

        @Override // org.simpleframework.xml.core.Section
        public LabelMap getElements() {
            return new LabelMap(this.scanner);
        }

        @Override // org.simpleframework.xml.core.Section
        public LabelMap getAttributes() {
            return new LabelMap(this.scanner);
        }
    }
}
