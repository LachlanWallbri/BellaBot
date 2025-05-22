package org.simpleframework.xml.core;

import java.util.List;

/* loaded from: classes9.dex */
class SignatureCreator implements Creator {
    private final List<Parameter> list;
    private final Signature signature;
    private final Class type;

    public SignatureCreator(Signature signature) {
        this.type = signature.getType();
        this.list = signature.getAll();
        this.signature = signature;
    }

    @Override // org.simpleframework.xml.core.Creator
    public Class getType() {
        return this.type;
    }

    @Override // org.simpleframework.xml.core.Creator
    public Signature getSignature() {
        return this.signature;
    }

    @Override // org.simpleframework.xml.core.Creator
    public Object getInstance() throws Exception {
        return this.signature.create();
    }

    @Override // org.simpleframework.xml.core.Creator
    public Object getInstance(Criteria criteria) throws Exception {
        Object[] array = this.list.toArray();
        for (int i = 0; i < this.list.size(); i++) {
            array[i] = getVariable(criteria, i);
        }
        return this.signature.create(array);
    }

    private Object getVariable(Criteria criteria, int i) throws Exception {
        Variable remove = criteria.remove(this.list.get(i).getKey());
        if (remove != null) {
            return remove.getValue();
        }
        return null;
    }

    @Override // org.simpleframework.xml.core.Creator
    public double getScore(Criteria criteria) throws Exception {
        Signature copy = this.signature.copy();
        for (Object obj : criteria) {
            Parameter parameter = copy.get(obj);
            Variable variable = criteria.get(obj);
            Contact contact = variable.getContact();
            if (parameter != null && !Support.isAssignable(variable.getValue().getClass(), parameter.getType())) {
                return -1.0d;
            }
            if (contact.isReadOnly() && parameter == null) {
                return -1.0d;
            }
        }
        return getPercentage(criteria);
    }

    private double getPercentage(Criteria criteria) throws Exception {
        double d = 0.0d;
        for (Parameter parameter : this.list) {
            if (criteria.get(parameter.getKey()) != null) {
                d += 1.0d;
            } else if (parameter.isRequired() || parameter.isPrimitive()) {
                return -1.0d;
            }
        }
        return getAdjustment(d);
    }

    private double getAdjustment(double d) {
        double size = this.list.size() / 1000.0d;
        if (d > 0.0d) {
            return size + (d / this.list.size());
        }
        return d / this.list.size();
    }

    public String toString() {
        return this.signature.toString();
    }
}
