package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.strategy.Type;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class TextListLabel extends TemplateLabel {
    private final String empty;
    private final Label label;
    private final Text text;

    @Override // org.simpleframework.xml.core.Label
    public Decorator getDecorator() throws Exception {
        return null;
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public boolean isCollection() {
        return true;
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public boolean isTextList() {
        return true;
    }

    public TextListLabel(Label label, Text text) {
        this.empty = text.empty();
        this.label = label;
        this.text = text;
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public String[] getNames() throws Exception {
        return this.label.getNames();
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public String[] getPaths() throws Exception {
        return this.label.getPaths();
    }

    @Override // org.simpleframework.xml.core.Label
    public String getEmpty(Context context) throws Exception {
        return this.empty;
    }

    @Override // org.simpleframework.xml.core.Label
    public Converter getConverter(Context context) throws Exception {
        Contact contact = getContact();
        if (!this.label.isCollection()) {
            throw new TextException("Cannot use %s to represent %s", contact, this.label);
        }
        return new TextList(context, contact, this.label);
    }

    @Override // org.simpleframework.xml.core.Label
    public String getName() throws Exception {
        return this.label.getName();
    }

    @Override // org.simpleframework.xml.core.Label
    public String getPath() throws Exception {
        return this.label.getPath();
    }

    @Override // org.simpleframework.xml.core.Label
    public Expression getExpression() throws Exception {
        return this.label.getExpression();
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public Type getDependent() throws Exception {
        return this.label.getDependent();
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public String getEntry() throws Exception {
        return this.label.getEntry();
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public Object getKey() throws Exception {
        return this.label.getKey();
    }

    @Override // org.simpleframework.xml.core.Label
    public Annotation getAnnotation() {
        return this.label.getAnnotation();
    }

    @Override // org.simpleframework.xml.core.Label
    public Contact getContact() {
        return this.label.getContact();
    }

    @Override // org.simpleframework.xml.core.Label
    public Class getType() {
        return this.label.getType();
    }

    @Override // org.simpleframework.xml.core.Label
    public String getOverride() {
        return this.label.getOverride();
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isData() {
        return this.label.isData();
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isRequired() {
        return this.label.isRequired();
    }

    @Override // org.simpleframework.xml.core.TemplateLabel, org.simpleframework.xml.core.Label
    public boolean isInline() {
        return this.label.isInline();
    }

    @Override // org.simpleframework.xml.core.Label
    public String toString() {
        return String.format("%s %s", this.text, this.label);
    }
}
