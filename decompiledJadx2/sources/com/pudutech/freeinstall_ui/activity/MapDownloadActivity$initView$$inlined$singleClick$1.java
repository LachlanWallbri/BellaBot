package com.pudutech.freeinstall_ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.TextView;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.disinfect.baselib.network.response.RobotMapResp;
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.utils.CommonDialogUtils;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.freeinstall_ui.viewmodel.MapDownloadViewModel;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.module_freeinstall_ui.C5362R;
import com.pudutech.module_robot_selfcheck.oss.MapUpdateManager;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: Extands.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "it", "kotlin.jvm.PlatformType", "onClick", "com/pudutech/freeinstall_ui/utils/ExtandsKt$singleClick$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MapDownloadActivity$initView$$inlined$singleClick$1 implements View.OnClickListener {
    final /* synthetic */ View $this_singleClick;
    final /* synthetic */ long $time;
    final /* synthetic */ MapDownloadActivity this$0;

    public MapDownloadActivity$initView$$inlined$singleClick$1(View view, long j, MapDownloadActivity mapDownloadActivity) {
        this.$this_singleClick = view;
        this.$time = j;
        this.this$0 = mapDownloadActivity;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - ExtandsKt.getLastClickTime(this.$this_singleClick) > this.$time || (this.$this_singleClick instanceof Checkable)) {
            ExtandsKt.setLastClickTime(this.$this_singleClick, currentTimeMillis);
            TextView tv_map_empty = (TextView) this.this$0._$_findCachedViewById(C5362R.id.tv_map_empty);
            Intrinsics.checkExpressionValueIsNotNull(tv_map_empty, "tv_map_empty");
            boolean z = true;
            ArrayList arrayList = null;
            if (tv_map_empty.getVisibility() == 0) {
                Integer power = BatteryInfoManager.INSTANCE.getPower();
                if ((power != null ? power.intValue() : 0) < 35) {
                    CommonDialogUtils.Companion companion = CommonDialogUtils.INSTANCE;
                    MapDownloadActivity mapDownloadActivity = this.this$0;
                    String string = mapDownloadActivity.getString(C5362R.string.power_lower_notice);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.power_lower_notice)");
                    CommonDialogUtils.Companion.showSingleCommonDialog$default(companion, mapDownloadActivity, null, string, null, null, 26, null);
                    return;
                }
                BaseActivity.showLoadingDialog$default(this.this$0, null, false, 3, null);
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new MapDownloadActivity$initView$$inlined$singleClick$1$lambda$1(null, this), 2, null);
                return;
            }
            if (this.this$0.getAdapter().getIsShowCheck()) {
                List<RobotMapResp> data = this.this$0.getAdapter().getData();
                if (data != null) {
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj : data) {
                        if (((RobotMapResp) obj).isSetting()) {
                            arrayList2.add(obj);
                        }
                    }
                    arrayList = arrayList2;
                }
                ArrayList arrayList3 = arrayList;
                if (arrayList3 != null && !arrayList3.isEmpty()) {
                    z = false;
                }
                if (!z) {
                    RobotMapResp robotMapResp = (RobotMapResp) arrayList.get(0);
                    if (!MapUpdateManager.INSTANCE.checkMapRule(robotMapResp.getName())) {
                        this.this$0.showErrorDialog();
                        return;
                    }
                    MapDownloadActivity mapDownloadActivity2 = this.this$0;
                    String string2 = mapDownloadActivity2.getString(C5362R.string.map_setting_hint);
                    Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.map_setting_hint)");
                    mapDownloadActivity2.showLoading(string2);
                    this.this$0.currentSelectMap = robotMapResp;
                    ((MapDownloadViewModel) this.this$0.getMViewModel()).reloadMap(robotMapResp);
                    return;
                }
                MapDownloadActivity mapDownloadActivity3 = this.this$0;
                String string3 = mapDownloadActivity3.getString(C5362R.string.please_select_map);
                Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.please_select_map)");
                Toast makeText = Toast.makeText(mapDownloadActivity3, string3, 0);
                makeText.show();
                Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
                return;
            }
            Button btn_start_download = (Button) this.this$0._$_findCachedViewById(C5362R.id.btn_start_download);
            Intrinsics.checkExpressionValueIsNotNull(btn_start_download, "btn_start_download");
            btn_start_download.setEnabled(false);
            List<RobotMapResp> data2 = this.this$0.getAdapter().getData();
            if (data2 != null) {
                ((MapDownloadViewModel) this.this$0.getMViewModel()).download(data2);
            }
        }
    }
}
