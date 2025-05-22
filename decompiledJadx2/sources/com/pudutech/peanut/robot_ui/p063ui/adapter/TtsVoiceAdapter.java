package com.pudutech.peanut.robot_ui.p063ui.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import com.airbnb.lottie.LottieAnimationView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.p063ui.dialog.CustomTtsVoiceInputDialog;
import com.pudutech.peanut.robot_ui.p063ui.helper.TtsVoiceHelper;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.pudutech.robot.module.voice.AudioTrackUtils;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: TtsVoiceAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u0002H\u0014J\u0006\u0010(\u001a\u00020\u0017J\u0010\u0010)\u001a\u00020\u00172\u0006\u0010'\u001a\u00020\u0002H\u0002J\u0018\u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020,2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0006R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u0006-"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/TtsVoiceAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "getContext", "()Landroid/content/Context;", "setContext", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "getFragmentManager", "()Landroidx/fragment/app/FragmentManager;", "setFragmentManager", "(Landroidx/fragment/app/FragmentManager;)V", "onChooseClickListener", "Landroid/view/View$OnClickListener;", "onClickListener", "onCloseSwtichListener", "Lkotlin/Function0;", "", "getOnCloseSwtichListener", "()Lkotlin/jvm/functions/Function0;", "setOnCloseSwtichListener", "(Lkotlin/jvm/functions/Function0;)V", "onEditClickListener", "onLongClickListener", "Landroid/view/View$OnLongClickListener;", "type", "Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "getType", "()Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "setType", "(Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;)V", "convert", "helper", "item", "resetPlayStatus", "showCustomInputDialog", "showDeletePopupWindow", "view", "Landroid/view/View;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TtsVoiceAdapter extends BaseQuickAdapter<TtsVoiceHelper.TtsConfigData, BaseViewHolder> {
    private final String TAG;
    private Context context;
    private FragmentManager fragmentManager;
    private View.OnClickListener onChooseClickListener;
    private final View.OnClickListener onClickListener;
    private Function0<Unit> onCloseSwtichListener;
    private View.OnClickListener onEditClickListener;
    private View.OnLongClickListener onLongClickListener;
    private TtsVoiceHelper.TtsVoiceType type;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TtsVoiceHelper.TtsVoiceType.values().length];

        static {
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.SOLICIT_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.USHER_TYPE.ordinal()] = 4;
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TtsVoiceAdapter(Context context) {
        super(C5508R.layout.item_select_tts_voice);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = "TtsVoiceAdapter";
        this.type = TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE;
        this.onClickListener = new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.TtsVoiceAdapter$onClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                String str;
                Boolean bool;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object tag = it.getTag();
                if (tag == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper.TtsConfigData");
                }
                TtsVoiceHelper.TtsConfigData ttsConfigData = (TtsVoiceHelper.TtsConfigData) tag;
                final int indexOf = TtsVoiceAdapter.this.getData().indexOf(ttsConfigData);
                str = TtsVoiceAdapter.this.TAG;
                boolean z = false;
                Pdlog.m3273d(str, "try play " + ttsConfigData);
                if (ttsConfigData.isPlay()) {
                    TtsVoiceHelper.INSTANCE.stopCruiseTts();
                    ttsConfigData.setPlay(false);
                    TtsVoiceAdapter.this.notifyItemChanged(indexOf);
                    return;
                }
                List<TtsVoiceHelper.TtsConfigData> data = TtsVoiceAdapter.this.getData();
                if (data != null) {
                    List<TtsVoiceHelper.TtsConfigData> list = data;
                    if (!(list instanceof Collection) || !list.isEmpty()) {
                        Iterator<T> it2 = list.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            } else if (((TtsVoiceHelper.TtsConfigData) it2.next()).isPlay()) {
                                z = true;
                                break;
                            }
                        }
                    }
                    bool = Boolean.valueOf(z);
                } else {
                    bool = null;
                }
                if (bool.booleanValue()) {
                    TtsVoiceAdapter.this.resetPlayStatus();
                    ttsConfigData.setPlay(true);
                    TtsVoiceAdapter.this.notifyDataSetChanged();
                } else {
                    ttsConfigData.setPlay(true);
                    TtsVoiceAdapter.this.notifyItemChanged(indexOf);
                }
                TtsVoiceHelper.INSTANCE.playPcm(ttsConfigData, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.TtsVoiceAdapter$onClickListener$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AudioTrackUtils.AudioPlayEvent audioPlayEvent) {
                        invoke2(audioPlayEvent);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AudioTrackUtils.AudioPlayEvent event) {
                        String str2;
                        String str3;
                        Intrinsics.checkParameterIsNotNull(event, "event");
                        str2 = TtsVoiceAdapter.this.TAG;
                        Pdlog.m3273d(str2, "onStateChange : event = " + event + "; ");
                        if (event == AudioTrackUtils.AudioPlayEvent.STOP || Intrinsics.areEqual(event.name(), "COMPLETE")) {
                            str3 = TtsVoiceAdapter.this.TAG;
                            Pdlog.m3273d(str3, "onStateChange : start jump ");
                            List<TtsVoiceHelper.TtsConfigData> data2 = TtsVoiceAdapter.this.getData();
                            if (data2 != null) {
                                Iterator<T> it3 = data2.iterator();
                                while (it3.hasNext()) {
                                    ((TtsVoiceHelper.TtsConfigData) it3.next()).setPlay(false);
                                }
                            }
                            TtsVoiceAdapter.this.notifyItemChanged(indexOf);
                        }
                    }
                });
            }
        };
        this.onChooseClickListener = new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.TtsVoiceAdapter$onChooseClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                Context context2 = TtsVoiceAdapter.this.getContext();
                if (context2 == null) {
                    Intrinsics.throwNpe();
                }
                if (ttsVoiceHelper.checkTtsOpenType(context2, TtsVoiceAdapter.this.getType()) == TtsVoiceHelper.TtsVoiceOpenType.OPEN) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    Object tag = it.getTag();
                    if (tag == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper.TtsConfigData");
                    }
                    TtsVoiceHelper.INSTANCE.changeChoose((TtsVoiceHelper.TtsConfigData) tag, TtsVoiceAdapter.this.getType());
                    TtsVoiceAdapter.this.notifyDataSetChanged();
                    return;
                }
                ToastUtils.show(TtsVoiceAdapter.this.getContext(), C5508R.string.please_open_swtich);
            }
        };
        this.onLongClickListener = new View.OnLongClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.TtsVoiceAdapter$onLongClickListener$1
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(final View itemView) {
                TtsVoiceAdapter ttsVoiceAdapter = TtsVoiceAdapter.this;
                Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
                ttsVoiceAdapter.showDeletePopupWindow(itemView, new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.TtsVoiceAdapter$onLongClickListener$1.1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        View itemView2 = itemView;
                        Intrinsics.checkExpressionValueIsNotNull(itemView2, "itemView");
                        Object tag = itemView2.getTag();
                        if (tag == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper.TtsConfigData");
                        }
                        TtsVoiceHelper.INSTANCE.deleteConfig((TtsVoiceHelper.TtsConfigData) tag, TtsVoiceAdapter.this.getType());
                        if (!TtsVoiceHelper.INSTANCE.hasSelect(TtsVoiceAdapter.this.getType())) {
                            ToastUtils.show(TtsVoiceAdapter.this.getContext(), C5508R.string.please_select_one);
                            Function0<Unit> onCloseSwtichListener = TtsVoiceAdapter.this.getOnCloseSwtichListener();
                            if (onCloseSwtichListener != null) {
                                onCloseSwtichListener.invoke();
                            }
                        }
                        TtsVoiceAdapter.this.notifyDataSetChanged();
                    }
                });
                return true;
            }
        };
        this.onEditClickListener = new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.TtsVoiceAdapter$onEditClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                TtsVoiceHelper.INSTANCE.stopCruiseTts();
                TtsVoiceAdapter.this.resetPlayStatus();
                TtsVoiceAdapter.this.notifyDataSetChanged();
                TtsVoiceAdapter ttsVoiceAdapter = TtsVoiceAdapter.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object tag = it.getTag();
                if (tag == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper.TtsConfigData");
                }
                ttsVoiceAdapter.showCustomInputDialog((TtsVoiceHelper.TtsConfigData) tag);
            }
        };
    }

    public final TtsVoiceHelper.TtsVoiceType getType() {
        return this.type;
    }

    public final void setType(TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "<set-?>");
        this.type = ttsVoiceType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDeletePopupWindow(View view, final View.OnClickListener onClickListener) {
        Object systemService = this.context.getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(C5508R.layout.layout_popupwindow_delete, (ViewGroup) null, false);
            final PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, false);
            inflate.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.TtsVoiceAdapter$showDeletePopupWindow$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    onClickListener.onClick(view2);
                    popupWindow.dismiss();
                }
            });
            popupWindow.setBackgroundDrawable(new ColorDrawable(0));
            popupWindow.setOutsideTouchable(true);
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            if (600 - iArr[1] < 150) {
                popupWindow.showAsDropDown(view, (view.getWidth() / 2) - (popupWindow.getWidth() / 2), -view.getHeight());
                return;
            } else {
                popupWindow.showAsDropDown(view, (view.getWidth() / 2) - (popupWindow.getWidth() / 2), -10);
                return;
            }
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    public final Function0<Unit> getOnCloseSwtichListener() {
        return this.onCloseSwtichListener;
    }

    public final void setOnCloseSwtichListener(Function0<Unit> function0) {
        this.onCloseSwtichListener = function0;
    }

    public final FragmentManager getFragmentManager() {
        return this.fragmentManager;
    }

    public final void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCustomInputDialog(final TtsVoiceHelper.TtsConfigData item) {
        String string;
        FragmentManager fragmentManager = this.fragmentManager;
        if (fragmentManager != null) {
            CustomTtsVoiceInputDialog customTtsVoiceInputDialog = new CustomTtsVoiceInputDialog();
            int i = WhenMappings.$EnumSwitchMapping$0[this.type.ordinal()];
            if (i == 1) {
                string = this.context.getString(C5508R.string.pdStr7_120);
                Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr7_120)");
            } else if (i == 2) {
                string = this.context.getString(C5508R.string.pdStr7_121);
                Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr7_121)");
            } else if (i == 3) {
                string = this.context.getString(C5508R.string.solicit_my_self_setting);
                Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.stri….solicit_my_self_setting)");
            } else {
                if (i != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                string = this.context.getString(C5508R.string.greeter_setting_self);
                Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.greeter_setting_self)");
            }
            customTtsVoiceInputDialog.setTitle(string);
            customTtsVoiceInputDialog.setEditIndex(Integer.valueOf(getData().indexOf(item)));
            customTtsVoiceInputDialog.setSelect(item.isSelect());
            customTtsVoiceInputDialog.setType(this.type);
            customTtsVoiceInputDialog.setContent(item.getName());
            customTtsVoiceInputDialog.show(fragmentManager, "voice_custom");
            customTtsVoiceInputDialog.setOnContentChange(new Function1<String, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.TtsVoiceAdapter$showCustomInputDialog$$inlined$let$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    TtsVoiceHelper.INSTANCE.deleteConfig(item, TtsVoiceAdapter.this.getType());
                    if (TtsVoiceAdapter.this.getContext() != null) {
                        TtsVoiceAdapter.this.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, TtsVoiceHelper.TtsConfigData item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView tryPlay = (TextView) helper.getView(C5508R.id.try_play_tv);
        TextView name = (TextView) helper.getView(C5508R.id.name_texts);
        ImageView chooseIv = (ImageView) helper.getView(C5508R.id.choose_iv);
        final LottieAnimationView voiceAnim = (LottieAnimationView) helper.getView(C5508R.id.voice_float_anim);
        ConstraintLayout containerBg = (ConstraintLayout) helper.getView(C5508R.id.containerBg);
        TextView tryEditTv = (TextView) helper.getView(C5508R.id.try_edit_tv);
        Intrinsics.checkExpressionValueIsNotNull(name, "name");
        CharSequence text = name.getText();
        Intrinsics.checkExpressionValueIsNotNull(text, "name.text");
        if ((text.length() == 0) || (!Intrinsics.areEqual(name.getText(), item.getName()))) {
            name.setText(item.getName());
            name.setSelected(true);
        }
        TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwNpe();
        }
        if (ttsVoiceHelper.checkTtsOpenType(context, this.type) == TtsVoiceHelper.TtsVoiceOpenType.OPEN) {
            name.setTextColor(this.context.getResources().getColor(C5508R.color.font_color_1));
            Intrinsics.checkExpressionValueIsNotNull(chooseIv, "chooseIv");
            chooseIv.setSelected(item.isSelect());
            chooseIv.setImageDrawable(this.context.getDrawable(C5508R.drawable.selector_tts_choose));
        } else {
            Intrinsics.checkExpressionValueIsNotNull(chooseIv, "chooseIv");
            chooseIv.setSelected(item.isSelect());
            chooseIv.setImageDrawable(this.context.getDrawable(C5508R.drawable.selector_tts_choose_disable));
            name.setTextColor(this.context.getResources().getColor(C5508R.color.sub_text_color));
        }
        if (item.isPlay()) {
            Intrinsics.checkExpressionValueIsNotNull(tryPlay, "tryPlay");
            tryPlay.setText(this.context.getString(C5508R.string.stop));
            Sdk27PropertiesKt.setTextColor(tryPlay, this.context.getResources().getColor(C5508R.color.btn_red_normal_color));
            Intrinsics.checkExpressionValueIsNotNull(voiceAnim, "voiceAnim");
            voiceAnim.setVisibility(0);
            voiceAnim.setAnimation("animation/animation_playings.json");
            voiceAnim.postOnAnimation(new Runnable() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.TtsVoiceAdapter$convert$1$1
                @Override // java.lang.Runnable
                public final void run() {
                    LottieAnimationView.this.playAnimation();
                }
            });
        } else {
            Intrinsics.checkExpressionValueIsNotNull(voiceAnim, "voiceAnim");
            voiceAnim.setVisibility(8);
            Intrinsics.checkExpressionValueIsNotNull(tryPlay, "tryPlay");
            tryPlay.setText(this.context.getString(C5508R.string.pdStr7_71));
            Sdk27PropertiesKt.setTextColor(tryPlay, this.context.getResources().getColor(C5508R.color.btn_blue_normal_color));
            voiceAnim.pauseAnimation();
        }
        Intrinsics.checkExpressionValueIsNotNull(containerBg, "containerBg");
        containerBg.setTag(item);
        containerBg.setOnClickListener(this.onChooseClickListener);
        containerBg.setOnLongClickListener(this.onLongClickListener);
        tryPlay.setTag(item);
        tryPlay.setOnClickListener(this.onClickListener);
        Intrinsics.checkExpressionValueIsNotNull(tryEditTv, "tryEditTv");
        tryEditTv.setTag(item);
        tryEditTv.setOnClickListener(this.onEditClickListener);
    }

    public final void resetPlayStatus() {
        List<TtsVoiceHelper.TtsConfigData> data = getData();
        if (data != null) {
            Iterator<T> it = data.iterator();
            while (it.hasNext()) {
                ((TtsVoiceHelper.TtsConfigData) it.next()).setPlay(false);
            }
        }
    }
}
