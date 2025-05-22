package com.pudutech.disinfect.baselib.util;

import android.os.SystemClock;
import com.amazonaws.cognito.clientcontext.datacollection.DataRecordKey;
import com.pudutech.base.Pdlog;
import java.io.File;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: FactoryTestHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0006\u0010\u0011\u001a\u00020\u0010J\u0006\u0010\u0012\u001a\u00020\u0010J\u0006\u0010\u0013\u001a\u00020\u0010J\u000e\u0010\u000b\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\rJ\u0006\u0010\u0015\u001a\u00020\u0010J\b\u0010\u0016\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/FactoryTestHelper;", "", "()V", "TAG", "", "circleTimestamp_ms", "", "file", "Ljava/io/File;", "json", "Lorg/json/JSONObject;", "recordDeviceUse", "Ljava/util/ArrayList;", "Lcom/pudutech/disinfect/baselib/util/FactoryTestHelper$DeviceName;", "Lkotlin/collections/ArrayList;", "load", "", "recordCruiseEnd", "recordCruiseStart", "recordDelivery", "name", "recordError", "save", DataRecordKey.MODEL, "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class FactoryTestHelper {
    private static long circleTimestamp_ms;
    private static JSONObject json;
    public static final FactoryTestHelper INSTANCE = new FactoryTestHelper();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final File file = new File("sdcard/BusinessTest");
    private static ArrayList<DeviceName> recordDeviceUse = new ArrayList<>();

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: FactoryTestHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/FactoryTestHelper$DeviceName;", "", "(Ljava/lang/String;I)V", "UvLamp", "Spray", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum DeviceName {
        UvLamp,
        Spray
    }

    private FactoryTestHelper() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void load() {
        JSONObject jSONObject;
        String readText$default = FilesKt.readText$default(file, null, 1, null);
        try {
            jSONObject = new JSONObject(readText$default);
        } catch (Exception e) {
            Pdlog.m3277w(TAG, String.valueOf(e));
            jSONObject = new JSONObject();
        }
        json = jSONObject;
        Pdlog.m3273d(TAG, "load " + readText$default + " to json=" + json);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void save() {
        Pdlog.m3273d(TAG, "save " + json);
        FilesKt.writeText$default(file, String.valueOf(json), null, 2, null);
    }

    public final void recordDelivery() {
        if (file.exists()) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new FactoryTestHelper$recordDelivery$1(null), 2, null);
        }
    }

    public final void recordCruiseStart() {
        circleTimestamp_ms = SystemClock.elapsedRealtime();
    }

    public final void recordCruiseEnd() {
        if (circleTimestamp_ms != 0 && file.exists()) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - circleTimestamp_ms;
            circleTimestamp_ms = 0L;
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new FactoryTestHelper$recordCruiseEnd$1(elapsedRealtime, null), 2, null);
        }
    }

    public final void recordError() {
        if (file.exists()) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new FactoryTestHelper$recordError$1(null), 2, null);
        }
    }

    public final void recordDeviceUse(DeviceName name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (file.exists() && !recordDeviceUse.contains(name)) {
            recordDeviceUse.add(name);
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new FactoryTestHelper$recordDeviceUse$1(null), 2, null);
        }
    }
}
