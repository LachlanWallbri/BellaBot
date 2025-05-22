package com.pudutech.peanut.robot_ui.p063ui;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.mpmodule.bean.Playlist;
import com.pudutech.mpmodule.database.interf.QueryPlayListCallBacker;
import com.pudutech.mpmodule.media.Media;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.listener.OnLazyClickListener;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.p063ui.adapter.SelectMusicItem;
import com.pudutech.peanut.robot_ui.p063ui.adapter.SelectVoiceItem;
import com.pudutech.peanut.robot_ui.p063ui.adapter.SpecialModeMusicAdapter;
import com.pudutech.peanut.robot_ui.p063ui.adapter.SpecialModeVoiceAdapter;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.FaceAnimationDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.HomeSettingDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.OnHomeSettingDialogClickListener;
import com.pudutech.peanut.robot_ui.p063ui.helper.BeeperCallHelper;
import com.pudutech.peanut.robot_ui.p063ui.view.MyStatusBarLayout;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoAnimation;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SpecialModeSelectorActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000o\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0012\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0004H\u0002J\b\u0010\u0019\u001a\u00020\u0017H\u0002J\b\u0010\u001a\u001a\u00020\u0017H\u0002J\b\u0010\u001b\u001a\u00020\u0017H\u0002J\b\u0010\u001c\u001a\u00020\u0017H\u0002J\b\u0010\u001d\u001a\u00020\u0017H\u0002J\u0012\u0010\u001e\u001a\u00020\u00172\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J1\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020\r2\b\u0010'\u001a\u0004\u0018\u00010#H\u0016¢\u0006\u0002\u0010(J\b\u0010)\u001a\u00020\u0017H\u0002J\u0012\u0010*\u001a\u00020\u00172\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\b\u0010-\u001a\u00020\u0017H\u0014J\b\u0010.\u001a\u00020\u0017H\u0014J\u0010\u0010/\u001a\u00020\u00172\u0006\u00100\u001a\u00020\rH\u0016J\b\u00101\u001a\u00020\u0017H\u0002J\u0010\u00102\u001a\u00020\u00172\u0006\u00103\u001a\u00020\u0004H\u0002J\u0010\u00104\u001a\u00020\u00172\u0006\u00105\u001a\u00020\u0004H\u0002J\u0010\u00106\u001a\u00020\u00172\u0006\u00107\u001a\u000208H\u0002J\b\u00109\u001a\u00020\u0017H\u0002J\b\u0010:\u001a\u00020\u0017H\u0002J\b\u0010;\u001a\u00020\u0017H\u0002J\b\u0010<\u001a\u00020\u0017H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000¨\u0006="}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/SpecialModeSelectorActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "beeperCallHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/BeeperCallHelper;", "faceAnimationDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/FaceAnimationDialog;", "homeSettingDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/HomeSettingDialog;", "isFirstStart", "", "isJumpToMusicAc", "musicAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/SpecialModeMusicAdapter;", "onHomeSettingDialogClickListener", "com/pudutech/peanut/robot_ui/ui/SpecialModeSelectorActivity$onHomeSettingDialogClickListener$1", "Lcom/pudutech/peanut/robot_ui/ui/SpecialModeSelectorActivity$onHomeSettingDialogClickListener$1;", "voiceAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/SpecialModeVoiceAdapter;", "bindPresenter", "", "getSaveVoiceSelect", "hideFaceDialog", "initMusicData", "initMusicView", "initView", "initVoiceView", "jumpAndFinish", "intent", "Landroid/content/Intent;", "notifyBatteryInfo", "state", "", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "i", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onConfigChange", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onStart", "onWindowFocusChanged", "hasFocus", "release", "saveSelectMusic", "path", "saveVoiceSelect", "s", "showFaceDialog", "animations", "Lcom/pudutech/peanut/robot_ui/ui/view/videoface/FaceVideoAnimation;", "showSettingDialog", "showStandbyAnimation", "stopStandby", "unBindPresenter", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SpecialModeSelectorActivity extends BatteryBaseActivity {
    private HashMap _$_findViewCache;
    private FaceAnimationDialog faceAnimationDialog;
    private HomeSettingDialog homeSettingDialog;
    private boolean isJumpToMusicAc;
    private SpecialModeMusicAdapter musicAdapter;
    private SpecialModeVoiceAdapter voiceAdapter;
    private final String TAG = getClass().getSimpleName();
    private boolean isFirstStart = true;
    private final BeeperCallHelper beeperCallHelper = new BeeperCallHelper();
    private final SpecialModeSelectorActivity$onHomeSettingDialogClickListener$1 onHomeSettingDialogClickListener = new OnHomeSettingDialogClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.SpecialModeSelectorActivity$onHomeSettingDialogClickListener$1
        @Override // com.pudutech.peanut.robot_ui.p063ui.dialog.OnHomeSettingDialogClickListener
        public void onFunClick(HomeSettingDialog.FunctionType type, Intent intent) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            if (type == HomeSettingDialog.FunctionType.SPECIAL_MODE) {
                return;
            }
            if (type == HomeSettingDialog.FunctionType.MUSIC_MODE) {
                SpecialModeSelectorActivity.this.isJumpToMusicAc = true;
                SpecialModeSelectorActivity.this.release();
            } else if (intent != null) {
                SpecialModeSelectorActivity.this.jumpAndFinish(intent);
            }
        }
    };

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
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

    public static final /* synthetic */ SpecialModeMusicAdapter access$getMusicAdapter$p(SpecialModeSelectorActivity specialModeSelectorActivity) {
        SpecialModeMusicAdapter specialModeMusicAdapter = specialModeSelectorActivity.musicAdapter;
        if (specialModeMusicAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
        }
        return specialModeMusicAdapter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.activity_special_mode_selector);
        initView();
        bindPresenter();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        Pdlog.m3273d(this.TAG, "onStart ");
        if (this.isJumpToMusicAc) {
            this.isJumpToMusicAc = false;
            bindPresenter();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        initMusicData();
    }

    private final void initView() {
        initMusicView();
        initVoiceView();
        ((TextView) _$_findCachedViewById(C5508R.id.btn_sure)).setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.SpecialModeSelectorActivity$initView$1
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyClickListener
            public void onSingleClick() {
                String str;
                str = SpecialModeSelectorActivity.this.TAG;
                Pdlog.m3273d(str, "btn_sure onSingleClick");
                Intent intent = new Intent(SpecialModeSelectorActivity.this, (Class<?>) HomeActivity.class);
                intent.putExtra("MODE_TYPE", 8);
                PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                SpecialModeSelectorActivity.this.jumpAndFinish(intent);
            }
        });
        ((ImageView) _$_findCachedViewById(C5508R.id.menu_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.SpecialModeSelectorActivity$initView$2
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                SpecialModeSelectorActivity.this.showSettingDialog();
            }
        });
    }

    private final void initVoiceView() {
        RecyclerView arrive_voice_recycle_view = (RecyclerView) _$_findCachedViewById(C5508R.id.arrive_voice_recycle_view);
        Intrinsics.checkExpressionValueIsNotNull(arrive_voice_recycle_view, "arrive_voice_recycle_view");
        SpecialModeSelectorActivity specialModeSelectorActivity = this;
        arrive_voice_recycle_view.setLayoutManager(new LinearLayoutManager(specialModeSelectorActivity));
        this.voiceAdapter = new SpecialModeVoiceAdapter(specialModeSelectorActivity);
        RecyclerView arrive_voice_recycle_view2 = (RecyclerView) _$_findCachedViewById(C5508R.id.arrive_voice_recycle_view);
        Intrinsics.checkExpressionValueIsNotNull(arrive_voice_recycle_view2, "arrive_voice_recycle_view");
        SpecialModeVoiceAdapter specialModeVoiceAdapter = this.voiceAdapter;
        if (specialModeVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voiceAdapter");
        }
        arrive_voice_recycle_view2.setAdapter(specialModeVoiceAdapter);
        SpecialModeVoiceAdapter specialModeVoiceAdapter2 = this.voiceAdapter;
        if (specialModeVoiceAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voiceAdapter");
        }
        specialModeVoiceAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.SpecialModeSelectorActivity$initVoiceView$1
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter<Object, BaseViewHolder> adapter, View view, int i) {
                Intrinsics.checkExpressionValueIsNotNull(adapter, "adapter");
                List<Object> data = adapter.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "adapter.data");
                int i2 = 0;
                for (Object obj : data) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    if (obj != null) {
                        SelectVoiceItem selectVoiceItem = (SelectVoiceItem) obj;
                        if (i == i2) {
                            selectVoiceItem.setSelect(!selectVoiceItem.isSelect());
                            if (!selectVoiceItem.isSelect()) {
                                SpecialModeSelectorActivity.this.saveVoiceSelect("");
                            }
                        } else {
                            selectVoiceItem.setSelect(false);
                        }
                        i2 = i3;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.SelectVoiceItem");
                    }
                }
                PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                adapter.notifyDataSetChanged();
                SpecialModeSelectorActivity.this.onConfigChange();
            }
        });
    }

    private final void initMusicData() {
        RecyclerView music_recycle_view = (RecyclerView) _$_findCachedViewById(C5508R.id.music_recycle_view);
        Intrinsics.checkExpressionValueIsNotNull(music_recycle_view, "music_recycle_view");
        music_recycle_view.setVisibility(8);
        RelativeLayout empty_layout = (RelativeLayout) _$_findCachedViewById(C5508R.id.empty_layout);
        Intrinsics.checkExpressionValueIsNotNull(empty_layout, "empty_layout");
        empty_layout.setVisibility(8);
        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) _$_findCachedViewById(C5508R.id.progressBar);
        if (contentLoadingProgressBar != null) {
            contentLoadingProgressBar.setVisibility(0);
        }
        MusicPlayerHelper.getInstance().getMusicListByMode(this, ModeEnum.SPECIAL, new QueryPlayListCallBacker() { // from class: com.pudutech.peanut.robot_ui.ui.SpecialModeSelectorActivity$initMusicData$1
            @Override // com.pudutech.mpmodule.database.interf.QueryPlayListCallBacker, com.pudutech.mpmodule.database.interf.IDatabaseInterface.DatabaseCallBack
            public void onGetPlaylist(Playlist playlist) {
                String str;
                String str2;
                String str3;
                List<Media> mediaList;
                List<Media> mediaList2;
                str = SpecialModeSelectorActivity.this.TAG;
                Pdlog.m3273d(str, "operateOnInitDefaultLists init " + playlist);
                if (SpecialModeSelectorActivity.this.isFinishing() || SpecialModeSelectorActivity.this.isDestroyed()) {
                    str2 = SpecialModeSelectorActivity.this.TAG;
                    Pdlog.m3274e(str2, "onLoadSuccessful ac is finish");
                    return;
                }
                ContentLoadingProgressBar contentLoadingProgressBar2 = (ContentLoadingProgressBar) SpecialModeSelectorActivity.this._$_findCachedViewById(C5508R.id.progressBar);
                if (contentLoadingProgressBar2 != null) {
                    contentLoadingProgressBar2.setVisibility(8);
                }
                if (((playlist == null || (mediaList2 = playlist.getMediaList()) == null) ? 0 : mediaList2.size()) == 0) {
                    RelativeLayout empty_layout2 = (RelativeLayout) SpecialModeSelectorActivity.this._$_findCachedViewById(C5508R.id.empty_layout);
                    Intrinsics.checkExpressionValueIsNotNull(empty_layout2, "empty_layout");
                    empty_layout2.setVisibility(0);
                    return;
                }
                ArrayList arrayList = new ArrayList();
                String str4 = SpUtils.get(RobotContext.INSTANCE.getContext(), "key_spaces_mode_select_music", "");
                str3 = SpecialModeSelectorActivity.this.TAG;
                Pdlog.m3273d(str3, "operateOnInitDefaultLists selectPath = " + str4);
                if (playlist != null && (mediaList = playlist.getMediaList()) != null) {
                    for (Media it : mediaList) {
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        String str5 = str4;
                        arrayList.add(new SelectMusicItem(it, !(str5 == null || StringsKt.isBlank(str5)) && Intrinsics.areEqual(str4, it.getPath()), false));
                    }
                }
                RecyclerView music_recycle_view2 = (RecyclerView) SpecialModeSelectorActivity.this._$_findCachedViewById(C5508R.id.music_recycle_view);
                Intrinsics.checkExpressionValueIsNotNull(music_recycle_view2, "music_recycle_view");
                music_recycle_view2.setVisibility(0);
                SpecialModeSelectorActivity.access$getMusicAdapter$p(SpecialModeSelectorActivity.this).setNewData(arrayList);
                SpecialModeSelectorActivity.this.onConfigChange();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveVoiceSelect(String s) {
        Pdlog.m3273d(this.TAG, "saveVoiceSelect " + s);
        SpUtils.set(this, "key_spaces_mode_select_arrive_voice", s);
    }

    private final String getSaveVoiceSelect() {
        return SpUtils.get(this, "key_spaces_mode_select_arrive_voice", "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onConfigChange() {
        SpecialModeMusicAdapter specialModeMusicAdapter = this.musicAdapter;
        if (specialModeMusicAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
        }
        List<SelectMusicItem> data = specialModeMusicAdapter.getData();
        Intrinsics.checkExpressionValueIsNotNull(data, "musicAdapter.data");
        Iterator<T> it = data.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (((SelectMusicItem) it.next()).isSelect()) {
                z = true;
            }
        }
        SpecialModeVoiceAdapter specialModeVoiceAdapter = this.voiceAdapter;
        if (specialModeVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voiceAdapter");
        }
        List<SelectVoiceItem> data2 = specialModeVoiceAdapter.getData();
        Intrinsics.checkExpressionValueIsNotNull(data2, "voiceAdapter.data");
        Iterator<T> it2 = data2.iterator();
        while (it2.hasNext()) {
            if (((SelectVoiceItem) it2.next()).isSelect()) {
                z = true;
            }
        }
        TextView btn_sure = (TextView) _$_findCachedViewById(C5508R.id.btn_sure);
        Intrinsics.checkExpressionValueIsNotNull(btn_sure, "btn_sure");
        btn_sure.setVisibility(z ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveSelectMusic(String path) {
        Pdlog.m3273d(this.TAG, "saveSelectMusic " + path);
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_spaces_mode_select_music", path);
    }

    private final void initMusicView() {
        Pdlog.m3273d(this.TAG, "initMusicView");
        SpecialModeSelectorActivity specialModeSelectorActivity = this;
        this.musicAdapter = new SpecialModeMusicAdapter(specialModeSelectorActivity);
        ContentLoadingProgressBar progressBar = (ContentLoadingProgressBar) _$_findCachedViewById(C5508R.id.progressBar);
        Intrinsics.checkExpressionValueIsNotNull(progressBar, "progressBar");
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(specialModeSelectorActivity, C5508R.color.theme_main_color), PorterDuff.Mode.MULTIPLY);
        RecyclerView music_recycle_view = (RecyclerView) _$_findCachedViewById(C5508R.id.music_recycle_view);
        Intrinsics.checkExpressionValueIsNotNull(music_recycle_view, "music_recycle_view");
        music_recycle_view.setLayoutManager(new LinearLayoutManager(specialModeSelectorActivity));
        RecyclerView music_recycle_view2 = (RecyclerView) _$_findCachedViewById(C5508R.id.music_recycle_view);
        Intrinsics.checkExpressionValueIsNotNull(music_recycle_view2, "music_recycle_view");
        SpecialModeMusicAdapter specialModeMusicAdapter = this.musicAdapter;
        if (specialModeMusicAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
        }
        music_recycle_view2.setAdapter(specialModeMusicAdapter);
        SpecialModeMusicAdapter specialModeMusicAdapter2 = this.musicAdapter;
        if (specialModeMusicAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
        }
        specialModeMusicAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.SpecialModeSelectorActivity$initMusicView$1
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter<Object, BaseViewHolder> adapter, View view, int i) {
                String str;
                str = SpecialModeSelectorActivity.this.TAG;
                Pdlog.m3273d(str, "setOnItemChildClickListener " + i);
                Intrinsics.checkExpressionValueIsNotNull(adapter, "adapter");
                List<Object> data = adapter.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "adapter.data");
                int i2 = 0;
                for (Object obj : data) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    if (obj != null) {
                        SelectMusicItem selectMusicItem = (SelectMusicItem) obj;
                        if (i == i2) {
                            selectMusicItem.setSelect(!selectMusicItem.isSelect());
                            if (!selectMusicItem.isSelect()) {
                                SpecialModeSelectorActivity.this.saveSelectMusic("");
                            } else {
                                SpecialModeSelectorActivity specialModeSelectorActivity2 = SpecialModeSelectorActivity.this;
                                String path = selectMusicItem.getMedia().getPath();
                                Intrinsics.checkExpressionValueIsNotNull(path, "item.media.path");
                                specialModeSelectorActivity2.saveSelectMusic(path);
                            }
                        } else {
                            selectMusicItem.setSelect(false);
                        }
                        i2 = i3;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.SelectMusicItem");
                    }
                }
                PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                adapter.notifyDataSetChanged();
                SpecialModeSelectorActivity.this.onConfigChange();
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.add_music_tv)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.SpecialModeSelectorActivity$initMusicView$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                MusicPlayerHelper.getInstance().gotoAddMusicForMode(SpecialModeSelectorActivity.this, ModeEnum.SPECIAL);
            }
        });
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent intent) {
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        startActivity(intent);
        finish();
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter");
        BeeperCallHelper.bind$default(this.beeperCallHelper, this, false, false, 6, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void release() {
        MusicPlayerHelper.getInstance().release();
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).stopChangeListener();
        unBindPresenter();
    }

    private final void unBindPresenter() {
        Pdlog.m3273d(this.TAG, "unBindPresenter");
        this.beeperCallHelper.unbind();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSettingDialog() {
        Pdlog.m3273d(this.TAG, "showSettingDialog");
        if (this.homeSettingDialog == null) {
            this.homeSettingDialog = new HomeSettingDialog(this);
            HomeSettingDialog homeSettingDialog = this.homeSettingDialog;
            if (homeSettingDialog == null) {
                Intrinsics.throwNpe();
            }
            homeSettingDialog.setOnHomeSettingDialogClickListener(this.onHomeSettingDialogClickListener);
        }
        HomeSettingDialog homeSettingDialog2 = this.homeSettingDialog;
        if (homeSettingDialog2 == null) {
            Intrinsics.throwNpe();
        }
        homeSettingDialog2.show();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged " + hasFocus);
        if (hasFocus && this.isFirstStart) {
            this.isFirstStart = false;
            ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).startChangeListener(this);
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
        if (state == 2 && i != null) {
            ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setBattery(i.intValue());
        }
    }

    private final void stopStandby() {
        Pdlog.m3273d(this.TAG, "stopStandby");
        hideFaceDialog();
        resetScreenLight();
    }

    private final void showStandbyAnimation() {
        Pdlog.m3273d(this.TAG, "showStandbyAnimation");
        showFaceDialog(SceneAnimationResources.INSTANCE.getStandby());
        setScreenDark();
    }

    private final void showFaceDialog(FaceVideoAnimation animations) {
        Pdlog.m3273d(this.TAG, "showFaceDialog ");
        if (this.faceAnimationDialog == null) {
            this.faceAnimationDialog = new FaceAnimationDialog();
        }
        FaceAnimationDialog faceAnimationDialog = this.faceAnimationDialog;
        if (faceAnimationDialog == null) {
            Intrinsics.throwNpe();
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "supportFragmentManager");
        faceAnimationDialog.show(supportFragmentManager, "face_animation_dialog");
        FaceAnimationDialog faceAnimationDialog2 = this.faceAnimationDialog;
        if (faceAnimationDialog2 == null) {
            Intrinsics.throwNpe();
        }
        faceAnimationDialog2.playAnimation(animations);
        FaceAnimationDialog faceAnimationDialog3 = this.faceAnimationDialog;
        if (faceAnimationDialog3 == null) {
            Intrinsics.throwNpe();
        }
        faceAnimationDialog3.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.SpecialModeSelectorActivity$showFaceDialog$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = SpecialModeSelectorActivity.this.TAG;
                Pdlog.m3273d(str, "faceAnimationDialog OnClick ");
            }
        });
    }

    private final void hideFaceDialog() {
        Pdlog.m3273d(this.TAG, "hideFaceDialog ");
        FaceAnimationDialog faceAnimationDialog = this.faceAnimationDialog;
        if (faceAnimationDialog != null) {
            faceAnimationDialog.dismissAllowingStateLoss();
        }
    }
}
