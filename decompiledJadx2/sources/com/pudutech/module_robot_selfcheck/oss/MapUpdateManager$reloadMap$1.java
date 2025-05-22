package com.pudutech.module_robot_selfcheck.oss;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.mapify.LoadCoreListener;
import com.pudutech.mirsdk.aidl.serialize.CoreStepType;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: MapUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/module_robot_selfcheck/oss/MapUpdateManager$reloadMap$1", "Lcom/pudutech/mirsdk/aidl/mapify/LoadCoreListener$Stub;", "coreInitStepResult", "", "p0", "Lcom/pudutech/mirsdk/aidl/serialize/CoreStepType;", "p1", "Lcom/pudutech/mirsdk/mircore/coreparcel/CoreInitState;", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MapUpdateManager$reloadMap$1 extends LoadCoreListener.Stub {
    final /* synthetic */ Ref.BooleanRef $isLoadLocateMap;
    final /* synthetic */ String $name;
    final /* synthetic */ Function1 $result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MapUpdateManager$reloadMap$1(Function1 function1, String str, Ref.BooleanRef booleanRef) {
        this.$result = function1;
        this.$name = str;
        this.$isLoadLocateMap = booleanRef;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LoadCoreListener
    public void coreInitStepResult(CoreStepType p0, CoreInitState p1) {
        String str;
        MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
        str = MapUpdateManager.TAG;
        Pdlog.m3274e(str, p0, p1);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new MapUpdateManager$reloadMap$1$coreInitStepResult$1(this, p0, p1, null), 2, null);
    }
}
