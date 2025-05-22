package com.pudutech.mirsdk.hardware.activity;

import android.os.IBinder;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareConntection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/activity/HardwareConnection;", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "()V", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class HardwareConnection extends AIDLConnection<HardwareInterface> {
    public static final HardwareConnection INSTANCE = new HardwareConnection();

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: HardwareConntection.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012,\u0010\u0003\u001a( \u0002*\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\b"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "kotlin.jvm.PlatformType", "p1", "Landroid/os/IBinder;", "Lkotlin/ParameterName;", "name", "p0", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.activity.HardwareConnection$1 */
    /* loaded from: classes2.dex */
    static final /* synthetic */ class C51291 extends FunctionReference implements Function1<IBinder, HardwareInterface> {
        public static final C51291 INSTANCE = new C51291();

        C51291() {
            super(1);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "asInterface";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(HardwareInterface.Stub.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "asInterface(Landroid/os/IBinder;)Lcom/pudutech/mirsdk/hardware/HardwareInterface;";
        }

        @Override // kotlin.jvm.functions.Function1
        public final HardwareInterface invoke(IBinder iBinder) {
            return HardwareInterface.Stub.asInterface(iBinder);
        }
    }

    private HardwareConnection() {
        super("com.pudutech.mirsdk.hardware.HardwareService", C51291.INSTANCE, null, 4, null);
    }
}
