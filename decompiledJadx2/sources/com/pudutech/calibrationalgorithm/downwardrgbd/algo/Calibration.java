package com.pudutech.calibrationalgorithm.downwardrgbd.algo;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CalibrationTool.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J4\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00042$\u0010\u000e\u001a \u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tJ!\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0010H\u0086 J9\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0086 J\u000e\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0004J&\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\nR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R2\u0010\u0007\u001a&\u0012\"\u0012 \u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/calibrationalgorithm/downwardrgbd/algo/Calibration;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "resultFrameListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lkotlin/Function4;", "", "", "addResultFrameListener", "name", "callback", "initParam", "", "machineType", "rgbdType", "flag", "process", "fd", "rows", "cols", "memSize", "forcePass", "save", "removeResultFrameListener", "updateResultFrame", "memorySize", "CalibrationAlgorithm_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Calibration {
    private final String TAG = "DownwardRGBDCalibration";
    private ThreadSafeListener<Function4<Integer, Integer, Integer, Integer, Unit>> resultFrameListener;

    public final native boolean initParam(int machineType, int rgbdType, boolean flag);

    public final native boolean process(int fd, int rows, int cols, int memSize, boolean forcePass, boolean save);

    public Calibration() {
        Pdlog.m3273d(this.TAG, "load downward_rgbd_calibration so");
        System.loadLibrary("downward_rgbd_calibration");
        this.resultFrameListener = new ThreadSafeListener<>();
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final void addResultFrameListener(String name, Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.resultFrameListener.add(name, callback);
    }

    public final void removeResultFrameListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.resultFrameListener.remove(name);
    }

    public final void updateResultFrame(final int fd, final int rows, final int cols, final int memorySize) {
        this.resultFrameListener.notify(new Function2<Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends Unit>, String, Unit>() { // from class: com.pudutech.calibrationalgorithm.downwardrgbd.algo.Calibration$updateResultFrame$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends Unit> function4, String str) {
                invoke2((Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit>) function4, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(Integer.valueOf(fd), Integer.valueOf(rows), Integer.valueOf(cols), Integer.valueOf(memorySize));
            }
        });
    }
}
