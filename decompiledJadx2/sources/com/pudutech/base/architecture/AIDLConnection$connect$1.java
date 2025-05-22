package com.pudutech.base.architecture;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* compiled from: AIDLConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0086@"}, m3961d2 = {MqttServiceConstants.CONNECT_ACTION, "", ExifInterface.GPS_DIRECTION_TRUE, "context", "Landroid/content/Context;", "extras", "", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.base.architecture.AIDLConnection", m3970f = "AIDLConnection.kt", m3971i = {0, 0, 0, 0, 0, 0}, m3972l = {101}, m3973m = MqttServiceConstants.CONNECT_ACTION, m3974n = {"this", "context", "extras", "mixPackageName", "intent", "bindResult"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4", "Z$0"})
/* loaded from: classes.dex */
public final class AIDLConnection$connect$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AIDLConnection this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AIDLConnection$connect$1(AIDLConnection aIDLConnection, Continuation continuation) {
        super(continuation);
        this.this$0 = aIDLConnection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.connect(null, null, this);
    }
}
