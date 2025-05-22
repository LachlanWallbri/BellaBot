package com.pudutech.bumblebee.robot_ui.module.escape;

import android.content.SharedPreferences;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.leaselib.utils.EncryptUtils;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.codec.language.Soundex;

/* compiled from: EscapeStorage.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeStorage;", "Lcom/pudutech/bumblebee/robot_ui/module/escape/IEscapeStorage;", "()V", "TAG", "", TransferTable.COLUMN_KEY, "preferences", "Landroid/content/SharedPreferences;", "getPreferences", "()Landroid/content/SharedPreferences;", "preferences$delegate", "Lkotlin/Lazy;", "doubleEncrypt", "mapMD5", "mapCheckEnd", "", "mapIsNew", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class EscapeStorage implements IEscapeStorage {
    private final String TAG = "EscapeStorage";
    private final String key = "escape_map_list";

    /* renamed from: preferences$delegate, reason: from kotlin metadata */
    private final Lazy preferences = LazyKt.lazy(new Function0<SharedPreferences>() { // from class: com.pudutech.bumblebee.robot_ui.module.escape.EscapeStorage$preferences$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SharedPreferences invoke() {
            return RobotContext.INSTANCE.getContext().getSharedPreferences("escape_config", 0);
        }
    });

    private final SharedPreferences getPreferences() {
        return (SharedPreferences) this.preferences.getValue();
    }

    @Override // com.pudutech.bumblebee.robot_ui.module.escape.IEscapeStorage
    public boolean mapIsNew() {
        ArrayList arrayList;
        String string = getPreferences().getString(this.key, "");
        if (string == null) {
            string = "";
        }
        List list = CollectionsKt.toList(StringsKt.split$default((CharSequence) string, new char[]{','}, false, 0, 6, (Object) null));
        List<MapInfo> mapList = RobotMapManager.INSTANCE.getMapList();
        if (mapList == null) {
            arrayList = new ArrayList();
        } else {
            List<MapInfo> list2 = mapList;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                String encryptMD5ToString = EncryptUtils.encryptMD5ToString(((MapInfo) it.next()).getMd5());
                Intrinsics.checkExpressionValueIsNotNull(encryptMD5ToString, "EncryptUtils.encryptMD5ToString(it.md5)");
                arrayList2.add(doubleEncrypt(encryptMD5ToString));
            }
            arrayList = arrayList2;
        }
        boolean z = (list.containsAll(arrayList) && arrayList.containsAll(list)) ? false : true;
        Pdlog.m3273d(this.TAG, "mapIsNew() isMapNew = " + z);
        Pdlog.m3273d(this.TAG, "mapIsNew() mapStr = " + string + "  oldMapList = " + list + " sdkMapList = " + arrayList);
        return z;
    }

    @Override // com.pudutech.bumblebee.robot_ui.module.escape.IEscapeStorage
    public void mapCheckEnd() {
        ArrayList arrayList;
        List<MapInfo> mapList = RobotMapManager.INSTANCE.getMapList();
        if (mapList == null) {
            arrayList = new ArrayList();
        } else {
            List<MapInfo> list = mapList;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                String mapMD5 = EncryptUtils.encryptMD5ToString(((MapInfo) it.next()).getMd5());
                Intrinsics.checkExpressionValueIsNotNull(mapMD5, "mapMD5");
                arrayList2.add(doubleEncrypt(mapMD5));
            }
            arrayList = arrayList2;
        }
        String joinToString$default = CollectionsKt.joinToString$default(arrayList, ",", null, null, 0, null, null, 62, null);
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.putString(this.key, joinToString$default);
        edit.apply();
        Pdlog.m3273d(this.TAG, "mapCheckEnd() = " + joinToString$default);
    }

    private final String doubleEncrypt(String mapMD5) {
        String encryptMD5ToString = EncryptUtils.encryptMD5ToString(WifiUtil.INSTANCE.getMac() + Soundex.SILENT_MARKER + mapMD5);
        Intrinsics.checkExpressionValueIsNotNull(encryptMD5ToString, "EncryptUtils.encryptMD5T…${WifiUtil.mac}-$mapMD5\")");
        return encryptMD5ToString;
    }
}
