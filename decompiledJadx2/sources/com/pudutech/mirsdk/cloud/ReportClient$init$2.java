package com.pudutech.mirsdk.cloud;

import com.pudutech.mirsdk.cloud.ReportClient;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ReportClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final /* synthetic */ class ReportClient$init$2 extends MutablePropertyReference0 {
    ReportClient$init$2(ReportClient reportClient) {
        super(reportClient);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "reportThread";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(ReportClient.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getReportThread()Lcom/pudutech/mirsdk/cloud/ReportClient$ReportIntentThread;";
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return ReportClient.access$getReportThread$p((ReportClient) this.receiver);
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        ReportClient.reportThread = (ReportClient.ReportIntentThread) obj;
    }
}
