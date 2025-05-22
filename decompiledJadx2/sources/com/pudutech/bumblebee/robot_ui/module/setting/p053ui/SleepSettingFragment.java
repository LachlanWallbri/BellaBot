package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SelectTimeAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SelectTimeItem;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceRadioGroupChangeListenerKt;
import com.pudutech.mirsdk.hardware.HardwareConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: SleepSettingFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\rH\u0002J&\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\rH\u0016J\u001a\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u001a\u001a\u00020\rH\u0002J\b\u0010\u001b\u001a\u00020\rH\u0002J\u0018\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/SleepSettingFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "jobs", "", "Lkotlinx/coroutines/Job;", "timeList", "", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectTimeItem;", "initData", "", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "playSleep", "playSmile", "previewVideo", "path", TypedValues.Attributes.S_TARGET, "Landroid/widget/ImageView;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SleepSettingFragment extends Fragment {
    private HashMap _$_findViewCache;
    private final List<SelectTimeItem> timeList;
    private final String TAG = getClass().getSimpleName();
    private final List<Job> jobs = new ArrayList();

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SceneAnimationResources.SleepFace.values().length];

        static {
            $EnumSwitchMapping$0[SceneAnimationResources.SleepFace.Sleep.ordinal()] = 1;
            $EnumSwitchMapping$0[SceneAnimationResources.SleepFace.Smile.ordinal()] = 2;
        }
    }

    private final void initData() {
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public SleepSettingFragment() {
        List listOf = CollectionsKt.listOf((Object[]) new Long[]{-1L, 30000L, 60000L, 120000L, Long.valueOf(HardwareConfig.RGBDFwUpdateTimeOut), 300000L, 600000L, 900000L});
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listOf, 10));
        Iterator it = listOf.iterator();
        while (it.hasNext()) {
            arrayList.add(new SelectTimeItem(((Number) it.next()).longValue(), false));
        }
        this.timeList = arrayList;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C4188R.layout.fragment_sleep_setting, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
    }

    private final void initView() {
        RadioButton rb_sleep = (RadioButton) _$_findCachedViewById(C4188R.id.rb_sleep);
        Intrinsics.checkExpressionValueIsNotNull(rb_sleep, "rb_sleep");
        rb_sleep.setText(getString(C4188R.string.face, 1));
        RadioButton rb_blink = (RadioButton) _$_findCachedViewById(C4188R.id.rb_blink);
        Intrinsics.checkExpressionValueIsNotNull(rb_blink, "rb_blink");
        rb_blink.setText(getString(C4188R.string.face, 2));
        int i = WhenMappings.$EnumSwitchMapping$0[Constans.INSTANCE.getSleepFace().ordinal()];
        if (i == 1) {
            ((RadioGroup) _$_findCachedViewById(C4188R.id.rg_face)).check(C4188R.id.rb_sleep);
            playSleep();
        } else if (i == 2) {
            ((RadioGroup) _$_findCachedViewById(C4188R.id.rg_face)).check(C4188R.id.rb_blink);
            playSmile();
        }
        VoiceRadioGroupChangeListenerKt.onVoiceSwitchChanged$default((RadioGroup) _$_findCachedViewById(C4188R.id.rg_face), null, 0, new Function2<RadioGroup, Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SleepSettingFragment$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(RadioGroup radioGroup, Integer num) {
                invoke(radioGroup, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(RadioGroup radioGroup, int i2) {
                if (i2 == C4188R.id.rb_sleep) {
                    Constans.INSTANCE.setSleepFace(SceneAnimationResources.SleepFace.Sleep);
                    SleepSettingFragment.this.playSleep();
                } else if (i2 == C4188R.id.rb_blink) {
                    Constans.INSTANCE.setSleepFace(SceneAnimationResources.SleepFace.Smile);
                    SleepSettingFragment.this.playSmile();
                }
            }
        }, 3, null);
        SleepSettingFragment sleepSettingFragment = this;
        Glide.with(sleepSettingFragment).load(Integer.valueOf(C4188R.drawable.sleep)).into((ImageView) _$_findCachedViewById(C4188R.id.sleep_preview));
        Glide.with(sleepSettingFragment).load(Integer.valueOf(C4188R.drawable.happy)).into((ImageView) _$_findCachedViewById(C4188R.id.blink_preview));
        ImageView sleep_start = (ImageView) _$_findCachedViewById(C4188R.id.sleep_start);
        Intrinsics.checkExpressionValueIsNotNull(sleep_start, "sleep_start");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(sleep_start, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SleepSettingFragment$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                SleepSettingFragment.this.playSleep();
            }
        }, 3, null);
        ImageView blink_start = (ImageView) _$_findCachedViewById(C4188R.id.blink_start);
        Intrinsics.checkExpressionValueIsNotNull(blink_start, "blink_start");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(blink_start, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SleepSettingFragment$initView$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                SleepSettingFragment.this.playSmile();
            }
        }, 3, null);
        RecyclerView rv_time = (RecyclerView) _$_findCachedViewById(C4188R.id.rv_time);
        Intrinsics.checkExpressionValueIsNotNull(rv_time, "rv_time");
        rv_time.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView rv_time2 = (RecyclerView) _$_findCachedViewById(C4188R.id.rv_time);
        Intrinsics.checkExpressionValueIsNotNull(rv_time2, "rv_time");
        rv_time2.setAdapter(new SelectTimeAdapter(this.timeList));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playSleep() {
        ViewExtKt.gone(CollectionsKt.listOf((Object[]) new ImageView[]{(ImageView) _$_findCachedViewById(C4188R.id.sleep_start), (ImageView) _$_findCachedViewById(C4188R.id.sleep_preview)}));
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.sleep_faceView)).playAnimation(SceneAnimationResources.INSTANCE.sleepMoreTimes(2));
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.sleep_faceView)).setOnPlayFinishListener(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SleepSettingFragment$playSleep$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ViewExtKt.show(CollectionsKt.listOf((Object[]) new ImageView[]{(ImageView) SleepSettingFragment.this._$_findCachedViewById(C4188R.id.sleep_start), (ImageView) SleepSettingFragment.this._$_findCachedViewById(C4188R.id.sleep_preview)}));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playSmile() {
        ViewExtKt.gone(CollectionsKt.listOf((Object[]) new ImageView[]{(ImageView) _$_findCachedViewById(C4188R.id.blink_start), (ImageView) _$_findCachedViewById(C4188R.id.blink_preview)}));
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.blink_faceView)).playAnimation(SceneAnimationResources.INSTANCE.smileMoreTimes(2));
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.blink_faceView)).setOnPlayFinishListener(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SleepSettingFragment$playSmile$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ViewExtKt.show(CollectionsKt.listOf((Object[]) new ImageView[]{(ImageView) SleepSettingFragment.this._$_findCachedViewById(C4188R.id.blink_start), (ImageView) SleepSettingFragment.this._$_findCachedViewById(C4188R.id.blink_preview)}));
            }
        });
    }

    private final void previewVideo(String path, ImageView target) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SleepSettingFragment$previewVideo$job$1(this, path, target, null), 2, null);
        this.jobs.add(launch$default);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.blink_faceView)).stopPlay();
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.sleep_faceView)).stopPlay();
        Iterator<T> it = this.jobs.iterator();
        while (it.hasNext()) {
            Job.DefaultImpls.cancel$default((Job) it.next(), (CancellationException) null, 1, (Object) null);
        }
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }
}
