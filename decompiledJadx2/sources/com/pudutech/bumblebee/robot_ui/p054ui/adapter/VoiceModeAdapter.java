package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageInfo;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoiceModeAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0015B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0014J\b\u0010\u0012\u001a\u0004\u0018\u00010\nJ\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/VoiceModeAdapter;", "Lcom/chad/library/adapter/base/MultipleItemRvAdapter;", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageInfo;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "data", "", "mContext", "Landroid/content/Context;", "(Ljava/util/List;Landroid/content/Context;)V", "_voiceModeItemProvider", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/VoiceModeItemProvider;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "getViewType", "", "t", "getVoiceModeItemProvider", "registerItemProvider", "", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class VoiceModeAdapter extends MultipleItemRvAdapter<VoicePackageInfo, BaseViewHolder> {
    public static final int VIEW_TYPE_DEFAULT = 0;
    public static final int VIEW_TYPE_TITLE = 1;
    private VoiceModeItemProvider _voiceModeItemProvider;
    private Context mContext;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoiceModeAdapter(List<VoicePackageInfo> data, Context mContext) {
        super(data);
        Intrinsics.checkParameterIsNotNull(data, "data");
        Intrinsics.checkParameterIsNotNull(mContext, "mContext");
        this.mContext = mContext;
        finishInitialize();
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final void setMContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.MultipleItemRvAdapter
    public int getViewType(VoicePackageInfo t) {
        Intrinsics.checkParameterIsNotNull(t, "t");
        return t.getViewType();
    }

    @Override // com.chad.library.adapter.base.MultipleItemRvAdapter
    public void registerItemProvider() {
        this.mProviderDelegate.registerProvider(new VoiceModeTitleItemProvider());
        this._voiceModeItemProvider = new VoiceModeItemProvider();
        VoiceModeItemProvider voiceModeItemProvider = this._voiceModeItemProvider;
        if (voiceModeItemProvider != null) {
            this.mProviderDelegate.registerProvider(voiceModeItemProvider);
        }
    }

    /* renamed from: getVoiceModeItemProvider, reason: from getter */
    public final VoiceModeItemProvider get_voiceModeItemProvider() {
        return this._voiceModeItemProvider;
    }
}
