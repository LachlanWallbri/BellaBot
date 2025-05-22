package com.pudutech.freeinstall_wrapper;

import android.os.IBinder;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.aidl.mapify.LocalizationListener;
import com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface;
import com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: MapServiceConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000)\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0007\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/freeinstall_wrapper/MapServiceConnection;", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/aidl/mapify/MappingFunctionInterface;", "()V", "TAG", "", "locatlistener", "com/pudutech/freeinstall_wrapper/MapServiceConnection$locatlistener$1", "Lcom/pudutech/freeinstall_wrapper/MapServiceConnection$locatlistener$1;", "getLocatMappingInterface", "Lcom/pudutech/mirsdk/aidl/mapify/LocateMappingInterface;", "init", "", "module_freeinstall_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MapServiceConnection extends AIDLConnection<MappingFunctionInterface> {
    public static final MapServiceConnection INSTANCE = new MapServiceConnection();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final MapServiceConnection$locatlistener$1 locatlistener = new LocalizationListener.Stub() { // from class: com.pudutech.freeinstall_wrapper.MapServiceConnection$locatlistener$1
        @Override // com.pudutech.mirsdk.aidl.mapify.LocalizationListener
        public void updateRobotPosition(Vector3d p0) {
            throw new NotImplementedError("An operation is not implemented: Not yet implemented");
        }
    };

    public final void init() {
    }

    /* compiled from: MapServiceConnection.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012,\u0010\u0003\u001a( \u0002*\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\b"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/mirsdk/aidl/mapify/MappingFunctionInterface;", "kotlin.jvm.PlatformType", "p1", "Landroid/os/IBinder;", "Lkotlin/ParameterName;", "name", "p0", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.freeinstall_wrapper.MapServiceConnection$1 */
    /* loaded from: classes4.dex */
    static final /* synthetic */ class C45911 extends FunctionReference implements Function1<IBinder, MappingFunctionInterface> {
        public static final C45911 INSTANCE = new C45911();

        C45911() {
            super(1);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "asInterface";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(MappingFunctionInterface.Stub.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "asInterface(Landroid/os/IBinder;)Lcom/pudutech/mirsdk/aidl/mapify/MappingFunctionInterface;";
        }

        @Override // kotlin.jvm.functions.Function1
        public final MappingFunctionInterface invoke(IBinder iBinder) {
            return MappingFunctionInterface.Stub.asInterface(iBinder);
        }
    }

    private MapServiceConnection() {
        super(Constant.mapingFuntionServer, C45911.INSTANCE, null, 4, null);
    }

    public final LocateMappingInterface getLocatMappingInterface() {
        MappingFunctionInterface mappingFunctionInterface = INSTANCE.getInterface();
        LocateMappingInterface locatMappingInterface = mappingFunctionInterface != null ? mappingFunctionInterface.getLocatMappingInterface() : null;
        if (locatMappingInterface == null) {
            Intrinsics.throwNpe();
        }
        return locatMappingInterface;
    }
}
