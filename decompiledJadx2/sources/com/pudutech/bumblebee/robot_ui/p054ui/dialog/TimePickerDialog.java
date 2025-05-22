package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.cruise_task.CruiseConfig;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.base.BaseDialog;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.widget.loopview.LoopView;
import com.pudutech.bumblebee.robot_ui.widget.loopview.OnItemScrollListener;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TimePickerDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0001H\u0016J\b\u0010\u0015\u001a\u00020\tH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\nR\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u00040\fj\b\u0012\u0004\u0012\u00020\u0004`\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00040\fj\b\u0012\u0004\u0012\u00020\u0004`\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/TimePickerDialog;", "Lcom/pudutech/bumblebee/robot_ui/base/BaseDialog;", "()V", "FILE_NAME", "", "KEY_ARRIVAL_DELAY_AUTO_FINISH", "TAG", "kotlin.jvm.PlatformType", "minute", "", "Ljava/lang/Integer;", "minuteTimeData", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "secondTimeData", "sencond", "bindView", "", "rootView", "Landroid/view/View;", "dialog", "getDialogLayoutId", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public class TimePickerDialog extends BaseDialog {
    private HashMap _$_findViewCache;
    private Integer minute;
    private Integer sencond;
    private ArrayList<String> minuteTimeData = new ArrayList<>();
    private ArrayList<String> secondTimeData = new ArrayList<>();
    private final String KEY_ARRIVAL_DELAY_AUTO_FINISH = "KEY_ARRIVAL_DELAY_AUTO_FINISH";
    private final String FILE_NAME = "CruiseConfig";
    private final String TAG = getClass().getSimpleName();

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog
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

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public TimePickerDialog() {
        for (int i = 0; i <= 59; i++) {
            String valueOf = String.valueOf(i);
            this.secondTimeData.add(valueOf);
            if (i <= 10) {
                this.minuteTimeData.add(valueOf);
            }
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog
    public int getDialogLayoutId() {
        return C4188R.layout.dialog_time_picker;
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog
    public void bindView(final View rootView, BaseDialog dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        if (rootView != null) {
            ((ImageView) _$_findCachedViewById(C4188R.id.ivClose)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.TimePickerDialog$bindView$$inlined$apply$lambda$1
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
                    TimePickerDialog.this.dismissDialog();
                }
            }, 3, null));
            ((Button) _$_findCachedViewById(C4188R.id.btnConfirm)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.TimePickerDialog$bindView$$inlined$apply$lambda$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    String str;
                    Integer num;
                    Integer num2;
                    Integer num3;
                    Integer num4;
                    String str2;
                    SharedPreferences.Editor edit;
                    String str3;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    str = this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("m: ");
                    num = this.minute;
                    sb.append(num);
                    sb.append("  s: ");
                    num2 = this.sencond;
                    sb.append(num2);
                    Pdlog.m3275i(str, sb.toString());
                    CruiseConfig cruiseConfig = CruiseConfig.INSTANCE;
                    num3 = this.minute;
                    int intValue = (num3 != null ? num3.intValue() : 0) * 60;
                    num4 = this.sencond;
                    cruiseConfig.setDelayAutoFinish_ms((intValue + (num4 != null ? num4.intValue() : 0)) * 1000);
                    Context context = rootView.getContext();
                    if (context != null) {
                        str2 = this.FILE_NAME;
                        SharedPreferences sharedPreferences = context.getSharedPreferences(str2, 0);
                        if (sharedPreferences != null && (edit = sharedPreferences.edit()) != null) {
                            str3 = this.KEY_ARRIVAL_DELAY_AUTO_FINISH;
                            SharedPreferences.Editor putLong = edit.putLong(str3, CruiseConfig.INSTANCE.getDelayAutoFinish_ms());
                            if (putLong != null) {
                                putLong.apply();
                            }
                        }
                    }
                    this.dismissDialog();
                }
            }, 3, null));
            LoopView loopView = (LoopView) _$_findCachedViewById(C4188R.id.minuteLooperView);
            loopView.setItems(this.minuteTimeData);
            loopView.setOnItemScrollListener(new OnItemScrollListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.TimePickerDialog$bindView$$inlined$apply$lambda$3
                @Override // com.pudutech.bumblebee.robot_ui.widget.loopview.OnItemScrollListener
                public void onItemScrollStateChanged(LoopView loopView2, int currentPassItem, int oldScrollState, int scrollState, int totalScrollY) {
                }

                @Override // com.pudutech.bumblebee.robot_ui.widget.loopview.OnItemScrollListener
                public void onItemScrolling(LoopView loopView2, int currentPassItem, int scrollState, int totalScrollY) {
                    TimePickerDialog.this.minute = Integer.valueOf(currentPassItem);
                }
            });
            long j = 1000;
            long j2 = 60;
            this.minute = Integer.valueOf((int) ((CruiseConfig.INSTANCE.getDelayAutoFinish_ms() / j) / j2));
            loopView.setInitPosition(this.minuteTimeData.indexOf(String.valueOf(this.minute)));
            LoopView loopView2 = (LoopView) _$_findCachedViewById(C4188R.id.secondsLooperView);
            loopView2.setItems(this.secondTimeData);
            loopView2.setOnItemScrollListener(new OnItemScrollListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.TimePickerDialog$bindView$$inlined$apply$lambda$4
                @Override // com.pudutech.bumblebee.robot_ui.widget.loopview.OnItemScrollListener
                public void onItemScrollStateChanged(LoopView loopView3, int currentPassItem, int oldScrollState, int scrollState, int totalScrollY) {
                }

                @Override // com.pudutech.bumblebee.robot_ui.widget.loopview.OnItemScrollListener
                public void onItemScrolling(LoopView loopView3, int currentPassItem, int scrollState, int totalScrollY) {
                    TimePickerDialog.this.sencond = Integer.valueOf(currentPassItem);
                }
            });
            this.sencond = Integer.valueOf((int) ((CruiseConfig.INSTANCE.getDelayAutoFinish_ms() / j) % j2));
            loopView2.setInitPosition(this.secondTimeData.indexOf(String.valueOf(this.sencond)));
        }
    }
}
