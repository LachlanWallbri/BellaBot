package com.pudutech.mirsdk.mircore.mirperception;

import android.graphics.Bitmap;
import android.system.Os;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: DEngineServer.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005J:\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00162*\u0010\u0017\u001a&\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00070\rJ:\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00162*\u0010\u0017\u001a&\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00070\rJ\u0011\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001cH\u0086 J\u0011\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001cH\u0086 J\u0011\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001cH\u0086 J\u0011\u0010 \u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001cH\u0086 J\u0011\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020#H\u0086 J\u0011\u0010$\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020#H\u0086 J\u0011\u0010%\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020'H\u0086 J\t\u0010(\u001a\u00020\u0007H\u0082 J\t\u0010)\u001a\u00020\u0007H\u0082 J\t\u0010*\u001a\u00020\u0007H\u0082 J\t\u0010+\u001a\u00020\u001cH\u0086 J\u000e\u0010,\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010-\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010.\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010/\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u0006H\u0002J\t\u00101\u001a\u00020\u0007H\u0082 J\t\u00102\u001a\u00020\u0007H\u0082 J\t\u00103\u001a\u00020\u0007H\u0082 J0\u00104\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u00105\u001a\u00020\u000e2\u0006\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\u000e2\u0006\u00108\u001a\u00020\u000eH\u0002J0\u00109\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010:\u001a\u00020\u00062\u0006\u00108\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\u000eH\u0002R,\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bRD\u0010\f\u001a,\u0012(\u0012&\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00070\r0\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000bRD\u0010\u0011\u001a,\u0012(\u0012&\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00070\r0\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\t\"\u0004\b\u0013\u0010\u000b¨\u0006<"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirperception/DEngineServer;", "", "()V", "dengineErrorListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lkotlin/Function1;", "", "", "getDengineErrorListener", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "setDengineErrorListener", "(Lcom/pudutech/base/architecture/ThreadSafeListener;)V", "facePropertyServerListener", "Lkotlin/Function5;", "", "getFacePropertyServerListener", "setFacePropertyServerListener", "objPropertyServerListener", "getObjPropertyServerListener", "setObjPropertyServerListener", "addDEngineErrorListener", "name", "", "callback", "addFacePropertyServerListener", "addObjPropertyServerListener", "dengineTest", "enable", "", "enableFaceFilter", "flag", "enableFaceServer", "enableObjDetServer", "getFaceDetView", "bitmap", "Landroid/graphics/Bitmap;", "getObjectDetView", "initialize", "machineModel", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "installDEngineErrorListener", "installFacePropertyServerListener", "installObjPropertyServerListener", "isModuleInited", "removeDEngineErrorListener", "removeFacePropertyServerListener", "removeObjPropertyServerListener", "reportErrorInfo", "code", "uninstallDEngineErrorListener", "uninstallFacePropertyServerListener", "uninstallObjPropertyServerListener", "updateFaceInfo", "yaw", "pitch", "dist", "angle", "updateObjDetInfo", "classIdx", "width", "MirPerception_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DEngineServer {
    public static final DEngineServer INSTANCE = new DEngineServer();
    private static ThreadSafeListener<Function1<Integer, Unit>> dengineErrorListener;
    private static ThreadSafeListener<Function5<Integer, Double, Double, Double, Double, Unit>> facePropertyServerListener;
    private static ThreadSafeListener<Function5<Integer, Integer, Double, Double, Double, Unit>> objPropertyServerListener;

    private final native void installDEngineErrorListener();

    private final native void installFacePropertyServerListener();

    private final native void installObjPropertyServerListener();

    private final native void uninstallDEngineErrorListener();

    private final native void uninstallFacePropertyServerListener();

    private final native void uninstallObjPropertyServerListener();

    public final native void dengineTest(boolean enable);

    public final native void enableFaceFilter(boolean flag);

    public final native void enableFaceServer(boolean flag);

    public final native void enableObjDetServer(boolean flag);

    public final native void getFaceDetView(Bitmap bitmap);

    public final native void getObjectDetView(Bitmap bitmap);

    public final native boolean initialize(MachineModel machineModel);

    public final native boolean isModuleInited();

    static {
        Pdlog.m3273d(PerceptionKt.getTAG(), "load library: dengine_server");
        System.loadLibrary("dengine_server");
        Os.setenv("USB_CONFIG_INI", "/sdcard/pudu/config/usbprop.ini", true);
        Os.setenv("LD_LIBRARY_PATH", "/data/DEngine/desdk/platform/host_android-armv8/lib", false);
        try {
            Pdlog.m3273d(PerceptionKt.getTAG(), "command = chmod 777 /dev/hidraw0");
            Runtime.getRuntime().exec("chmod 777 /dev/hidraw0");
        } catch (IOException e) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "chmod 777 /dev/hidraw0 failed");
            e.printStackTrace();
        }
        facePropertyServerListener = new ThreadSafeListener<>();
        objPropertyServerListener = new ThreadSafeListener<>();
        dengineErrorListener = new ThreadSafeListener<>();
    }

    private DEngineServer() {
    }

    public final void addFacePropertyServerListener(String name, Function5<? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(PerceptionKt.getTAG(), "add FacePropertyServerListener");
        facePropertyServerListener.add(name, callback);
        installFacePropertyServerListener();
    }

    public final void removeFacePropertyServerListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(PerceptionKt.getTAG(), "remove FacePropertyServerListener");
        uninstallFacePropertyServerListener();
        facePropertyServerListener.remove(name);
    }

    public final void addObjPropertyServerListener(String name, Function5<? super Integer, ? super Integer, ? super Double, ? super Double, ? super Double, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(PerceptionKt.getTAG(), "add addObjPropertyServerListener");
        objPropertyServerListener.add(name, callback);
        installObjPropertyServerListener();
    }

    public final void removeObjPropertyServerListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(PerceptionKt.getTAG(), "remove ObjPropertyServerListener");
        uninstallObjPropertyServerListener();
        objPropertyServerListener.remove(name);
    }

    public final void addDEngineErrorListener(String name, Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(PerceptionKt.getTAG(), "add Dengine Error Listener");
        dengineErrorListener.add(name, callback);
        installDEngineErrorListener();
    }

    public final void removeDEngineErrorListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(PerceptionKt.getTAG(), "remove DEngineErrorListener");
        uninstallDEngineErrorListener();
        dengineErrorListener.remove(name);
    }

    private final void updateFaceInfo(final int flag, final double yaw, final double pitch, final double dist, final double angle) {
        facePropertyServerListener.notify(new Function2<Function5<? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mirperception.DEngineServer$updateFaceInfo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function5<? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, ? extends Unit> function5, String str) {
                invoke2((Function5<? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, Unit>) function5, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function5<? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(Integer.valueOf(flag), Double.valueOf(yaw), Double.valueOf(pitch), Double.valueOf(dist), Double.valueOf(angle));
            }
        });
    }

    private final void updateObjDetInfo(final int flag, final int classIdx, final double angle, final double width, final double dist) {
        objPropertyServerListener.notify(new Function2<Function5<? super Integer, ? super Integer, ? super Double, ? super Double, ? super Double, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mirperception.DEngineServer$updateObjDetInfo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function5<? super Integer, ? super Integer, ? super Double, ? super Double, ? super Double, ? extends Unit> function5, String str) {
                invoke2((Function5<? super Integer, ? super Integer, ? super Double, ? super Double, ? super Double, Unit>) function5, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function5<? super Integer, ? super Integer, ? super Double, ? super Double, ? super Double, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(Integer.valueOf(flag), Integer.valueOf(classIdx), Double.valueOf(angle), Double.valueOf(width), Double.valueOf(dist));
            }
        });
    }

    private final void reportErrorInfo(final int code) {
        dengineErrorListener.notify(new Function2<Function1<? super Integer, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mirperception.DEngineServer$reportErrorInfo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function1<? super Integer, ? extends Unit> function1, String str) {
                invoke2((Function1<? super Integer, Unit>) function1, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function1<? super Integer, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(Integer.valueOf(code));
            }
        });
    }

    public final ThreadSafeListener<Function5<Integer, Double, Double, Double, Double, Unit>> getFacePropertyServerListener() {
        return facePropertyServerListener;
    }

    public final void setFacePropertyServerListener(ThreadSafeListener<Function5<Integer, Double, Double, Double, Double, Unit>> threadSafeListener) {
        Intrinsics.checkParameterIsNotNull(threadSafeListener, "<set-?>");
        facePropertyServerListener = threadSafeListener;
    }

    public final ThreadSafeListener<Function5<Integer, Integer, Double, Double, Double, Unit>> getObjPropertyServerListener() {
        return objPropertyServerListener;
    }

    public final void setObjPropertyServerListener(ThreadSafeListener<Function5<Integer, Integer, Double, Double, Double, Unit>> threadSafeListener) {
        Intrinsics.checkParameterIsNotNull(threadSafeListener, "<set-?>");
        objPropertyServerListener = threadSafeListener;
    }

    public final ThreadSafeListener<Function1<Integer, Unit>> getDengineErrorListener() {
        return dengineErrorListener;
    }

    public final void setDengineErrorListener(ThreadSafeListener<Function1<Integer, Unit>> threadSafeListener) {
        Intrinsics.checkParameterIsNotNull(threadSafeListener, "<set-?>");
        dengineErrorListener = threadSafeListener;
    }
}
