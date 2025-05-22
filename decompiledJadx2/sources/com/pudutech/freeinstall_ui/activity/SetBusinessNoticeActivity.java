package com.pudutech.freeinstall_ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.freeinstall_ui.viewmodel.SetBusinessViewModel;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: SetBusinessNoticeActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\tH\u0002J\u0012\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\tH\u0002J\b\u0010\u0014\u001a\u00020\u0007H\u0016J\b\u0010\u0015\u001a\u00020\tH\u0014J\b\u0010\u0016\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/activity/SetBusinessNoticeActivity;", "Lcom/pudutech/freeinstall_ui/base/BaseActivity;", "Lcom/pudutech/freeinstall_ui/viewmodel/SetBusinessViewModel;", "()V", "singleClickListener", "Landroid/view/View$OnClickListener;", "step", "", "createObserver", "", "currentActivityIsDark", "", "initIntent", "intent", "Landroid/content/Intent;", "initListener", "initView", "saveInstanceState", "Landroid/os/Bundle;", "jump", "layoutId", "onDestroy", "setView", "Companion", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SetBusinessNoticeActivity extends BaseActivity<SetBusinessViewModel> {
    public static final String STEP_ARGUMENTS = "set_business_type";
    public static final int STEP_SET_CHARGING_PILE = 7;
    public static final int STEP_SET_CRUISE_PATH = 4;
    public static final int STEP_SET_DOOR = 3;
    public static final int STEP_SET_MEAL = 5;
    public static final int STEP_SET_STATIONS = 6;
    public static final int STEP_SET_TABLE = 2;
    public static final String TAG = "SetBusinessNoticeActivity";
    private HashMap _$_findViewCache;
    private int step = 2;
    private final View.OnClickListener singleClickListener = new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.SetBusinessNoticeActivity$singleClickListener$1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (view != null) {
                int id = view.getId();
                if (id == C5362R.id.tv_complete) {
                    SetBusinessNoticeActivity.this.jump();
                } else if (id == C5362R.id.tv_back) {
                    SetBusinessNoticeActivity.this.finish();
                }
            }
        }
    };

    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void createObserver() {
    }

    @Override // com.pudutech.module_project_common.statusBar.StatusBaseActivity
    public boolean currentActivityIsDark() {
        return true;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C5362R.layout.activity_set_business_notice;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        initIntent(intent);
        setView();
        initListener();
    }

    private final void initListener() {
        ExtandsKt.singleClick$default((TextView) _$_findCachedViewById(C5362R.id.tv_complete), this.singleClickListener, 0L, 2, (Object) null);
        ExtandsKt.singleClick$default((TextView) _$_findCachedViewById(C5362R.id.tv_back), this.singleClickListener, 0L, 2, (Object) null);
    }

    private final void initIntent(Intent intent) {
        this.step = intent.getIntExtra(STEP_ARGUMENTS, 2);
        Pdlog.m3273d(TAG, "step" + this.step);
    }

    private final void setView() {
        switch (this.step) {
            case 2:
                TextView tv_notice_title = (TextView) _$_findCachedViewById(C5362R.id.tv_notice_title);
                Intrinsics.checkExpressionValueIsNotNull(tv_notice_title, "tv_notice_title");
                tv_notice_title.setText(getString(C5362R.string.set_table_num));
                TextView tv_notice_content = (TextView) _$_findCachedViewById(C5362R.id.tv_notice_content);
                Intrinsics.checkExpressionValueIsNotNull(tv_notice_content, "tv_notice_content");
                tv_notice_content.setText(getString(C5362R.string.set_table_notice));
                TextView tv_complete = (TextView) _$_findCachedViewById(C5362R.id.tv_complete);
                Intrinsics.checkExpressionValueIsNotNull(tv_complete, "tv_complete");
                tv_complete.setText(getString(C5362R.string.start_setting));
                ImageView iv_guid = (ImageView) _$_findCachedViewById(C5362R.id.iv_guid);
                Intrinsics.checkExpressionValueIsNotNull(iv_guid, "iv_guid");
                Sdk27PropertiesKt.setImageResource(iv_guid, C5362R.drawable.icon_add_table);
                return;
            case 3:
                TextView tv_notice_title2 = (TextView) _$_findCachedViewById(C5362R.id.tv_notice_title);
                Intrinsics.checkExpressionValueIsNotNull(tv_notice_title2, "tv_notice_title");
                tv_notice_title2.setText(getString(C5362R.string.set_door_position));
                TextView tv_notice_content2 = (TextView) _$_findCachedViewById(C5362R.id.tv_notice_content);
                Intrinsics.checkExpressionValueIsNotNull(tv_notice_content2, "tv_notice_content");
                tv_notice_content2.setText(getString(C5362R.string.set_door_position_notice));
                TextView tv_complete2 = (TextView) _$_findCachedViewById(C5362R.id.tv_complete);
                Intrinsics.checkExpressionValueIsNotNull(tv_complete2, "tv_complete");
                tv_complete2.setText(getString(C5362R.string.start_setting));
                ImageView iv_guid2 = (ImageView) _$_findCachedViewById(C5362R.id.iv_guid);
                Intrinsics.checkExpressionValueIsNotNull(iv_guid2, "iv_guid");
                Sdk27PropertiesKt.setImageResource(iv_guid2, C5362R.drawable.icon_add_door);
                return;
            case 4:
                TextView tv_notice_title3 = (TextView) _$_findCachedViewById(C5362R.id.tv_notice_title);
                Intrinsics.checkExpressionValueIsNotNull(tv_notice_title3, "tv_notice_title");
                tv_notice_title3.setText(getString(C5362R.string.set_cruise_path));
                TextView tv_notice_content3 = (TextView) _$_findCachedViewById(C5362R.id.tv_notice_content);
                Intrinsics.checkExpressionValueIsNotNull(tv_notice_content3, "tv_notice_content");
                tv_notice_content3.setText(getString(C5362R.string.set_cruise_notice));
                TextView tv_complete3 = (TextView) _$_findCachedViewById(C5362R.id.tv_complete);
                Intrinsics.checkExpressionValueIsNotNull(tv_complete3, "tv_complete");
                tv_complete3.setText(getString(C5362R.string.start_setting));
                ImageView iv_guid3 = (ImageView) _$_findCachedViewById(C5362R.id.iv_guid);
                Intrinsics.checkExpressionValueIsNotNull(iv_guid3, "iv_guid");
                Sdk27PropertiesKt.setImageResource(iv_guid3, C5362R.drawable.icon_add_curise);
                return;
            case 5:
                TextView tv_notice_title4 = (TextView) _$_findCachedViewById(C5362R.id.tv_notice_title);
                Intrinsics.checkExpressionValueIsNotNull(tv_notice_title4, "tv_notice_title");
                tv_notice_title4.setText(getString(C5362R.string.set_meal_point));
                TextView tv_notice_content4 = (TextView) _$_findCachedViewById(C5362R.id.tv_notice_content);
                Intrinsics.checkExpressionValueIsNotNull(tv_notice_content4, "tv_notice_content");
                tv_notice_content4.setText(getString(C5362R.string.set_meal_notice));
                TextView tv_complete4 = (TextView) _$_findCachedViewById(C5362R.id.tv_complete);
                Intrinsics.checkExpressionValueIsNotNull(tv_complete4, "tv_complete");
                tv_complete4.setText(getString(C5362R.string.start_setting));
                ImageView iv_guid4 = (ImageView) _$_findCachedViewById(C5362R.id.iv_guid);
                Intrinsics.checkExpressionValueIsNotNull(iv_guid4, "iv_guid");
                Sdk27PropertiesKt.setImageResource(iv_guid4, C5362R.drawable.icon_add_meal);
                return;
            case 6:
                TextView tv_notice_title5 = (TextView) _$_findCachedViewById(C5362R.id.tv_notice_title);
                Intrinsics.checkExpressionValueIsNotNull(tv_notice_title5, "tv_notice_title");
                tv_notice_title5.setText(getString(C5362R.string.set_station_point));
                TextView tv_notice_content5 = (TextView) _$_findCachedViewById(C5362R.id.tv_notice_content);
                Intrinsics.checkExpressionValueIsNotNull(tv_notice_content5, "tv_notice_content");
                tv_notice_content5.setText(getString(C5362R.string.set_station_notice));
                TextView tv_complete5 = (TextView) _$_findCachedViewById(C5362R.id.tv_complete);
                Intrinsics.checkExpressionValueIsNotNull(tv_complete5, "tv_complete");
                tv_complete5.setText(getString(C5362R.string.start_setting));
                ImageView iv_guid5 = (ImageView) _$_findCachedViewById(C5362R.id.iv_guid);
                Intrinsics.checkExpressionValueIsNotNull(iv_guid5, "iv_guid");
                Sdk27PropertiesKt.setImageResource(iv_guid5, C5362R.drawable.icon_add_station);
                return;
            case 7:
                TextView tv_notice_title6 = (TextView) _$_findCachedViewById(C5362R.id.tv_notice_title);
                Intrinsics.checkExpressionValueIsNotNull(tv_notice_title6, "tv_notice_title");
                tv_notice_title6.setText(getString(C5362R.string.set_charging_pile));
                TextView tv_notice_content6 = (TextView) _$_findCachedViewById(C5362R.id.tv_notice_content);
                Intrinsics.checkExpressionValueIsNotNull(tv_notice_content6, "tv_notice_content");
                tv_notice_content6.setText(getString(C5362R.string.set_charging_pile_notice));
                TextView tv_complete6 = (TextView) _$_findCachedViewById(C5362R.id.tv_complete);
                Intrinsics.checkExpressionValueIsNotNull(tv_complete6, "tv_complete");
                tv_complete6.setText(getString(C5362R.string.start_setting));
                ImageView iv_guid6 = (ImageView) _$_findCachedViewById(C5362R.id.iv_guid);
                Intrinsics.checkExpressionValueIsNotNull(iv_guid6, "iv_guid");
                Sdk27PropertiesKt.setImageResource(iv_guid6, C5362R.drawable.icon_add_charge_pile);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jump() {
        Pdlog.m3273d(TAG, "jump " + this.step);
        switch (this.step) {
            case 2:
                startActivity(new Intent(this, (Class<?>) AddTableActivity.class));
                return;
            case 3:
                startActivity(new Intent(this, (Class<?>) AddDoorMeetActivity.class));
                return;
            case 4:
                startActivity(new Intent(this, (Class<?>) AddCruisePathActivity.class));
                return;
            case 5:
                startActivity(new Intent(this, (Class<?>) AddMealActivity.class));
                return;
            case 6:
                startActivity(new Intent(this, (Class<?>) AddStationActivity.class));
                return;
            case 7:
                startActivity(new Intent(this, (Class<?>) AddChargePileActivity.class));
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(TAG, "onDestroy");
    }
}
