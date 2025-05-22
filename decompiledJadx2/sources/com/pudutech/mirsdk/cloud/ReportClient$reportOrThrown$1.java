package com.pudutech.mirsdk.cloud;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import okhttp3.OkHttpClient;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ReportClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final /* synthetic */ class ReportClient$reportOrThrown$1 extends MutablePropertyReference0 {
    ReportClient$reportOrThrown$1(ReportClient reportClient) {
        super(reportClient);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "client";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(ReportClient.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getClient()Lokhttp3/OkHttpClient;";
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return ReportClient.access$getClient$p((ReportClient) this.receiver);
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        ReportClient.client = (OkHttpClient) obj;
    }
}
