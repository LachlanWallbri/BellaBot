package org.simpleframework.xml.core;

/* loaded from: classes9.dex */
interface Creator {
    Object getInstance() throws Exception;

    Object getInstance(Criteria criteria) throws Exception;

    double getScore(Criteria criteria) throws Exception;

    Signature getSignature() throws Exception;

    Class getType() throws Exception;
}
