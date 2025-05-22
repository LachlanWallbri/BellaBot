package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.view.View;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SelectLanguageItem;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.TransparentLoadDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener;
import com.pudutech.resources.language.LanguageUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* compiled from: BasicSetupFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0002¨\u0006\r"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/module/setting/ui/BasicSetupFragment$initView$1", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyItemClickListener;", "onSingleItemClick", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "view", "Landroid/view/View;", RequestParameters.POSITION, "", "resetLocalRes", "curItem", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectLanguageItem;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class BasicSetupFragment$initView$1 extends OnLazyItemClickListener {
    final /* synthetic */ BasicSetupFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BasicSetupFragment$initView$1(BasicSetupFragment basicSetupFragment) {
        super(null, 0, 3, null);
        this.this$0 = basicSetupFragment;
    }

    @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
    public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
        int i;
        TransparentLoadDialog transparentLoadDialog;
        String str;
        String str2;
        CoroutineScope coroutineScope;
        String str3;
        Job launch$default;
        String str4;
        Intrinsics.checkParameterIsNotNull(adapter, "adapter");
        Intrinsics.checkParameterIsNotNull(view, "view");
        i = this.this$0.mCurIndex;
        if (i == position) {
            return;
        }
        transparentLoadDialog = this.this$0.mLoadDialog;
        if (transparentLoadDialog != null && !transparentLoadDialog.isShowing()) {
            transparentLoadDialog.show();
            str4 = this.this$0.TAG;
            Pdlog.m3273d(str4, "mLoadDialog.show");
        }
        this.this$0.creatThread();
        Object item = adapter.getItem(position);
        if (item instanceof SelectLanguageItem) {
            this.this$0.creatThread();
            SettingActivity.INSTANCE.setSwichlanguage(true);
            str2 = this.this$0.TAG;
            Pdlog.m3273d(str2, "curItem = " + item);
            coroutineScope = this.this$0.mScope;
            if (coroutineScope != null) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new BasicSetupFragment$initView$1$onSingleItemClick$2(this, item, adapter, position, null), 3, null);
                if (launch$default != null) {
                    return;
                }
            }
            str3 = this.this$0.TAG;
            Pdlog.m3273d(str3, "mScope is null");
            Unit unit = Unit.INSTANCE;
            return;
        }
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onSingleItemClick curItem not SelectLanguageItem");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetLocalRes(SelectLanguageItem curItem) {
        LanguageUtils languageUtils;
        String str;
        String str2;
        languageUtils = this.this$0.languageUtils;
        LanguageUtils.setSettingLocale$default(languageUtils, RobotContext.INSTANCE.getContext(), curItem.getOp(), false, 4, null);
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "start set language");
        AiVoiceManager.INSTANCE.changLanguage(curItem.getOp().getLocale());
        TtsVoiceHelper.INSTANCE.loadAllLoacalConfig(true, curItem.getOp().getLocale());
        str2 = this.this$0.TAG;
        Pdlog.m3273d(str2, "end set language");
        VoicePlayer.INSTANCE.loadResources();
    }
}
