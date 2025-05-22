package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.List;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Version;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class ObjectScanner implements Scanner {
    private StructureBuilder builder;
    private Detail detail;
    private ClassScanner scanner;
    private Structure structure;
    private Support support;

    public ObjectScanner(Detail detail, Support support) throws Exception {
        this.scanner = new ClassScanner(detail, support);
        this.builder = new StructureBuilder(this, detail, support);
        this.support = support;
        this.detail = detail;
        scan(detail);
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Signature getSignature() {
        return this.scanner.getSignature();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public List<Signature> getSignatures() {
        return this.scanner.getSignatures();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public ParameterMap getParameters() {
        return this.scanner.getParameters();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Instantiator getInstantiator() {
        return this.structure.getInstantiator();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Class getType() {
        return this.detail.getType();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Decorator getDecorator() {
        return this.scanner.getDecorator();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Caller getCaller(Context context) {
        return new Caller(this, context);
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Section getSection() {
        return this.structure.getSection();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Version getRevision() {
        return this.structure.getRevision();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Order getOrder() {
        return this.scanner.getOrder();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Label getVersion() {
        return this.structure.getVersion();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Label getText() {
        return this.structure.getText();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public String getName() {
        return this.detail.getName();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Function getCommit() {
        return this.scanner.getCommit();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Function getValidate() {
        return this.scanner.getValidate();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Function getPersist() {
        return this.scanner.getPersist();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Function getComplete() {
        return this.scanner.getComplete();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Function getReplace() {
        return this.scanner.getReplace();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Function getResolve() {
        return this.scanner.getResolve();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public boolean isPrimitive() {
        return this.structure.isPrimitive();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public boolean isEmpty() {
        return this.scanner.getRoot() == null;
    }

    @Override // org.simpleframework.xml.core.Scanner, org.simpleframework.xml.core.Policy
    public boolean isStrict() {
        return this.detail.isStrict();
    }

    private void scan(Detail detail) throws Exception {
        order(detail);
        field(detail);
        method(detail);
        validate(detail);
        commit(detail);
    }

    private void order(Detail detail) throws Exception {
        this.builder.assemble(detail.getType());
    }

    private void commit(Detail detail) throws Exception {
        Class type = detail.getType();
        if (this.structure == null) {
            this.structure = this.builder.build(type);
        }
        this.builder = null;
    }

    private void validate(Detail detail) throws Exception {
        Class type = detail.getType();
        this.builder.commit(type);
        this.builder.validate(type);
    }

    private void field(Detail detail) throws Exception {
        Iterator<Contact> it = this.support.getFields(detail.getType(), detail.getOverride()).iterator();
        while (it.hasNext()) {
            Contact next = it.next();
            Annotation annotation = next.getAnnotation();
            if (annotation != null) {
                this.builder.process(next, annotation);
            }
        }
    }

    private void method(Detail detail) throws Exception {
        Iterator<Contact> it = this.support.getMethods(detail.getType(), detail.getOverride()).iterator();
        while (it.hasNext()) {
            Contact next = it.next();
            Annotation annotation = next.getAnnotation();
            if (annotation != null) {
                this.builder.process(next, annotation);
            }
        }
    }
}
