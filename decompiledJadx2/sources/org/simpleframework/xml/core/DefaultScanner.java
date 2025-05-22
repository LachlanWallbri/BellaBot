package org.simpleframework.xml.core;

import java.util.List;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Version;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class DefaultScanner implements Scanner {
    private Detail detail;
    private Scanner scanner;

    public DefaultScanner(Detail detail, Support support) throws Exception {
        this.detail = new DefaultDetail(detail, DefaultType.FIELD);
        this.scanner = new ObjectScanner(this.detail, support);
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
        return this.scanner.getInstantiator();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Class getType() {
        return this.scanner.getType();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Decorator getDecorator() {
        return this.scanner.getDecorator();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Caller getCaller(Context context) {
        return this.scanner.getCaller(context);
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Section getSection() {
        return this.scanner.getSection();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Version getRevision() {
        return this.scanner.getRevision();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Order getOrder() {
        return this.scanner.getOrder();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Label getVersion() {
        return this.scanner.getVersion();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Label getText() {
        return this.scanner.getText();
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
        return this.scanner.isPrimitive();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public boolean isEmpty() {
        return this.scanner.isEmpty();
    }

    @Override // org.simpleframework.xml.core.Scanner, org.simpleframework.xml.core.Policy
    public boolean isStrict() {
        return this.scanner.isStrict();
    }
}
