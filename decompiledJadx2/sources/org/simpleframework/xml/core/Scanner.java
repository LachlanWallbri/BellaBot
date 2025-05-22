package org.simpleframework.xml.core;

import java.util.List;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Version;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public interface Scanner extends Policy {
    Caller getCaller(Context context);

    Function getCommit();

    Function getComplete();

    Decorator getDecorator();

    Instantiator getInstantiator();

    String getName();

    Order getOrder();

    ParameterMap getParameters();

    Function getPersist();

    Function getReplace();

    Function getResolve();

    Version getRevision();

    Section getSection();

    Signature getSignature();

    List<Signature> getSignatures();

    Label getText();

    Class getType();

    Function getValidate();

    Label getVersion();

    boolean isEmpty();

    boolean isPrimitive();

    @Override // org.simpleframework.xml.core.Policy
    boolean isStrict();
}
