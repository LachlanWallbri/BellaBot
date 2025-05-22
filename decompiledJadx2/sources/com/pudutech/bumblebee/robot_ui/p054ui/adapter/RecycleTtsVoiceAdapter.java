package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceItemLongClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListenerKt;
import com.pudutech.disinfect.baselib.ext.util.CommonExtKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: RecycleTtsVoiceAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u0002H\u0014J$\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020%2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\b0'H\u0002R4\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR.\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R4\u0010\u0013\u001a\u001c\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\n\"\u0004\b\u0015\u0010\fR\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0002X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006("}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/RecycleTtsVoiceAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "()V", "controllerClickListener", "Lkotlin/Function3;", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "", "getControllerClickListener", "()Lkotlin/jvm/functions/Function3;", "setControllerClickListener", "(Lkotlin/jvm/functions/Function3;)V", "deleteListener", "Lkotlin/Function2;", "getDeleteListener", "()Lkotlin/jvm/functions/Function2;", "setDeleteListener", "(Lkotlin/jvm/functions/Function2;)V", "editClickListener", "getEditClickListener", "setEditClickListener", "playing", "getPlaying", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "setPlaying", "(Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;)V", "type", "getType", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "setType", "(Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;)V", "convert", "helper", "item", "showDeletePopupWindow", "view", "Landroid/view/View;", "onClickListener", "Lkotlin/Function1;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class RecycleTtsVoiceAdapter extends BaseQuickAdapter<TtsVoiceHelper.TtsConfigData, BaseViewHolder> {
    private Function3<? super BaseViewHolder, ? super TtsVoiceHelper.TtsConfigData, ? super TtsVoiceHelper.TtsVoiceType, Unit> controllerClickListener;
    private Function2<? super TtsVoiceHelper.TtsConfigData, ? super TtsVoiceHelper.TtsVoiceType, Unit> deleteListener;
    private Function3<? super BaseViewHolder, ? super TtsVoiceHelper.TtsConfigData, ? super TtsVoiceHelper.TtsVoiceType, Unit> editClickListener;
    private TtsVoiceHelper.TtsConfigData playing;
    private TtsVoiceHelper.TtsVoiceType type;

    /* compiled from: RecycleTtsVoiceAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0010\u0010\u0002\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, m3961d2 = {"<anonymous>", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "view", "Landroid/view/View;", RequestParameters.POSITION, "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.bumblebee.robot_ui.ui.adapter.RecycleTtsVoiceAdapter$1 */
    /* loaded from: classes3.dex */
    static final class C43051 extends Lambda implements Function3<BaseQuickAdapter<?, ?>, View, Integer, Boolean> {
        C43051() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Boolean invoke(BaseQuickAdapter<?, ?> baseQuickAdapter, View view, Integer num) {
            return Boolean.valueOf(invoke(baseQuickAdapter, view, num.intValue()));
        }

        public final boolean invoke(BaseQuickAdapter<?, ?> baseQuickAdapter, View view, final int i) {
            if (view == null) {
                return true;
            }
            RecycleTtsVoiceAdapter.this.showDeletePopupWindow(view, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.RecycleTtsVoiceAdapter$1$$special$$inlined$let$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                    invoke2(view2);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    List list;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Function2<TtsVoiceHelper.TtsConfigData, TtsVoiceHelper.TtsVoiceType, Unit> deleteListener = RecycleTtsVoiceAdapter.this.getDeleteListener();
                    if (deleteListener != 0) {
                        list = RecycleTtsVoiceAdapter.this.mData;
                        Object obj = list.get(i);
                        Intrinsics.checkExpressionValueIsNotNull(obj, "mData[position]");
                    }
                }
            });
            return true;
        }
    }

    public RecycleTtsVoiceAdapter() {
        super(C4188R.layout.item_recycle_tts_voice);
        this.type = TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE;
        setOnItemLongClickListener(new VoiceItemLongClickListener(null, 0, new C43051(), 3, null));
    }

    public final TtsVoiceHelper.TtsVoiceType getType() {
        return this.type;
    }

    public final void setType(TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "<set-?>");
        this.type = ttsVoiceType;
    }

    public final Function3<BaseViewHolder, TtsVoiceHelper.TtsConfigData, TtsVoiceHelper.TtsVoiceType, Unit> getEditClickListener() {
        return this.editClickListener;
    }

    public final void setEditClickListener(Function3<? super BaseViewHolder, ? super TtsVoiceHelper.TtsConfigData, ? super TtsVoiceHelper.TtsVoiceType, Unit> function3) {
        this.editClickListener = function3;
    }

    public final Function3<BaseViewHolder, TtsVoiceHelper.TtsConfigData, TtsVoiceHelper.TtsVoiceType, Unit> getControllerClickListener() {
        return this.controllerClickListener;
    }

    public final void setControllerClickListener(Function3<? super BaseViewHolder, ? super TtsVoiceHelper.TtsConfigData, ? super TtsVoiceHelper.TtsVoiceType, Unit> function3) {
        this.controllerClickListener = function3;
    }

    public final Function2<TtsVoiceHelper.TtsConfigData, TtsVoiceHelper.TtsVoiceType, Unit> getDeleteListener() {
        return this.deleteListener;
    }

    public final void setDeleteListener(Function2<? super TtsVoiceHelper.TtsConfigData, ? super TtsVoiceHelper.TtsVoiceType, Unit> function2) {
        this.deleteListener = function2;
    }

    public final TtsVoiceHelper.TtsConfigData getPlaying() {
        return this.playing;
    }

    public final void setPlaying(TtsVoiceHelper.TtsConfigData ttsConfigData) {
        this.playing = ttsConfigData;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder helper, final TtsVoiceHelper.TtsConfigData item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView textView = (TextView) helper.getView(C4188R.id.content);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setMarqueeRepeatLimit(-1);
        textView.setSelected(true);
        textView.setFocusable(true);
        textView.setFocusableInTouchMode(true);
        LottieAnimationView anim = (LottieAnimationView) helper.getView(C4188R.id.anim_playing);
        ImageView edit = (ImageView) helper.getView(C4188R.id.iv_edit);
        ImageView controller = (ImageView) helper.getView(C4188R.id.iv_controller);
        CheckBox cb = (CheckBox) helper.getView(C4188R.id.f4801cb);
        helper.setText(C4188R.id.content, item.getName());
        Intrinsics.checkExpressionValueIsNotNull(cb, "cb");
        cb.setChecked(item.isSelect());
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default(cb, null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.RecycleTtsVoiceAdapter$convert$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                TtsVoiceHelper.INSTANCE.changeChoose(item, RecycleTtsVoiceAdapter.this.getType());
                RecycleTtsVoiceAdapter.this.notifyItemChanged(helper.getAdapterPosition());
            }
        }, 7, null);
        if (Intrinsics.areEqual(item, this.playing)) {
            Intrinsics.checkExpressionValueIsNotNull(anim, "anim");
            anim.setVisibility(0);
            controller.setImageResource(C4188R.drawable.ic_play);
            anim.setRepeatMode(1);
            anim.setRepeatCount(-1);
            anim.setVisibility(0);
            anim.playAnimation();
        } else {
            anim.cancelAnimation();
            Intrinsics.checkExpressionValueIsNotNull(anim, "anim");
            anim.setVisibility(4);
            controller.setImageResource(C4188R.drawable.ic_stop);
        }
        Intrinsics.checkExpressionValueIsNotNull(edit, "edit");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(edit, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.RecycleTtsVoiceAdapter$convert$3
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
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function3<BaseViewHolder, TtsVoiceHelper.TtsConfigData, TtsVoiceHelper.TtsVoiceType, Unit> editClickListener = RecycleTtsVoiceAdapter.this.getEditClickListener();
                if (editClickListener != null) {
                    editClickListener.invoke(helper, item, RecycleTtsVoiceAdapter.this.getType());
                }
            }
        }, 3, null);
        Intrinsics.checkExpressionValueIsNotNull(controller, "controller");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(controller, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.RecycleTtsVoiceAdapter$convert$4
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
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function3<BaseViewHolder, TtsVoiceHelper.TtsConfigData, TtsVoiceHelper.TtsVoiceType, Unit> controllerClickListener = RecycleTtsVoiceAdapter.this.getControllerClickListener();
                if (controllerClickListener != null) {
                    controllerClickListener.invoke(helper, item, RecycleTtsVoiceAdapter.this.getType());
                }
            }
        }, 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDeletePopupWindow(View view, final Function1<? super View, Unit> onClickListener) {
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "view.context");
        Object systemService = context.getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(C4188R.layout.layout_popupwindow_delete, (ViewGroup) null, false);
            final PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, false);
            inflate.setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.RecycleTtsVoiceAdapter$showDeletePopupWindow$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(null, 0, 3, null);
                }

                @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
                public void onSingleClick(View v) {
                    Intrinsics.checkParameterIsNotNull(v, "v");
                    super.onSingleClick(v);
                    Function1.this.invoke(v);
                    popupWindow.dismiss();
                }
            });
            popupWindow.setBackgroundDrawable(new ColorDrawable(0));
            popupWindow.setOutsideTouchable(true);
            popupWindow.getContentView().measure(0, 0);
            int width = view.getWidth() / 2;
            View contentView = popupWindow.getContentView();
            Intrinsics.checkExpressionValueIsNotNull(contentView, "popupWindow.contentView");
            int measuredWidth = width - (contentView.getMeasuredWidth() / 2);
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            int screenHeight = (CommonExtKt.getScreenHeight(RobotContext.INSTANCE.getContext()) - iArr[1]) - view.getHeight();
            View contentView2 = popupWindow.getContentView();
            Intrinsics.checkExpressionValueIsNotNull(contentView2, "popupWindow.contentView");
            if (screenHeight < contentView2.getMeasuredHeight()) {
                View contentView3 = popupWindow.getContentView();
                Intrinsics.checkExpressionValueIsNotNull(contentView3, "popupWindow.contentView");
                popupWindow.showAsDropDown(view, measuredWidth, (-contentView3.getMeasuredHeight()) * 2);
                return;
            } else {
                View contentView4 = popupWindow.getContentView();
                Intrinsics.checkExpressionValueIsNotNull(contentView4, "popupWindow.contentView");
                popupWindow.showAsDropDown(view, measuredWidth, (-contentView4.getMeasuredHeight()) / 2);
                return;
            }
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }
}
