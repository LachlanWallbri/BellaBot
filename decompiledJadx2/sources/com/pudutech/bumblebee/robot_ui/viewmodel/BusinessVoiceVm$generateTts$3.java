package com.pudutech.bumblebee.robot_ui.viewmodel;

import com.pudutech.base.MD5Util;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceManager;
import com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm;
import com.pudutech.disinfect.baselib.network.response.TtsVoiceInfo;
import com.pudutech.disinfect.baselib.util.GsonSingleton;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BusinessVoiceVm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BusinessVoiceVm$generateTts$3 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Ref.ObjectRef $createTTs;
    final /* synthetic */ Ref.IntRef $curNum;
    final /* synthetic */ int $size;
    final /* synthetic */ List $ttsVoices;
    final /* synthetic */ BusinessVoiceVm this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BusinessVoiceVm$generateTts$3(BusinessVoiceVm businessVoiceVm, List list, Ref.IntRef intRef, int i, Ref.ObjectRef objectRef) {
        super(0);
        this.this$0 = businessVoiceVm;
        this.$ttsVoices = list;
        this.$curNum = intRef;
        this.$size = i;
        this.$createTTs = objectRef;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        String localeStr$default = LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null);
        final TtsVoiceInfo ttsVoiceInfo = (TtsVoiceInfo) this.$ttsVoices.get(this.$curNum.element);
        String speaker = TtsVoiceManager.INSTANCE.getSpeaker();
        final String md5 = MD5Util.toMD5(((ttsVoiceInfo.getId() + localeStr$default) + ttsVoiceInfo.getText()) + speaker);
        TtsVoiceManager ttsVoiceManager = TtsVoiceManager.INSTANCE;
        String text = ttsVoiceInfo.getText();
        Intrinsics.checkExpressionValueIsNotNull(md5, "md5");
        ttsVoiceManager.createMerchantTts(text, md5, new Function2<Boolean, String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm$generateTts$3.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                invoke(bool.booleanValue(), str);
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z, String pathFile) {
                int i;
                String str;
                String str2;
                String str3;
                Intrinsics.checkParameterIsNotNull(pathFile, "pathFile");
                BusinessVoiceVm$generateTts$3.this.$curNum.element++;
                ttsVoiceInfo.setMd5(md5);
                if (z) {
                    BusinessVoiceVm businessVoiceVm = BusinessVoiceVm$generateTts$3.this.this$0;
                    i = businessVoiceVm._curProgress;
                    businessVoiceVm._curProgress = i + 1;
                    ttsVoiceInfo.setProduced(true);
                    TtsVoiceManager ttsVoiceManager2 = TtsVoiceManager.INSTANCE;
                    String md52 = md5;
                    Intrinsics.checkExpressionValueIsNotNull(md52, "md5");
                    String copyTempTtsToMerchantTts = ttsVoiceManager2.copyTempTtsToMerchantTts(pathFile, md52);
                    if (copyTempTtsToMerchantTts != null) {
                        ttsVoiceInfo.setPath(copyTempTtsToMerchantTts);
                        BusinessVoiceVm$generateTts$3.this.this$0.callMain(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm.generateTts.3.1.3
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
                                int ttsProgress;
                                Function1<BusinessVoiceVm.TtsResult, Unit> onResult = BusinessVoiceVm$generateTts$3.this.this$0.getOnResult();
                                if (onResult != null) {
                                    ttsProgress = BusinessVoiceVm$generateTts$3.this.this$0.getTtsProgress(BusinessVoiceVm$generateTts$3.this.$size);
                                    onResult.invoke(new BusinessVoiceVm.TtsResult.ResultProgress(ttsProgress));
                                }
                            }
                        });
                        if (BusinessVoiceVm$generateTts$3.this.$curNum.element >= BusinessVoiceVm$generateTts$3.this.$size) {
                            String json = GsonSingleton.INSTANCE.getINSTANCE().toJson(BusinessVoiceVm$generateTts$3.this.$ttsVoices);
                            VoicePackageHelper.INSTANCE.setMerchantTtsData(json);
                            TtsVoiceManager.INSTANCE.deleteOldMerchantTts(BusinessVoiceVm$generateTts$3.this.$ttsVoices);
                            str2 = BusinessVoiceVm$generateTts$3.this.this$0.TAG;
                            Pdlog.m3273d(str2, "generateTts() toJson =" + json);
                            TtsVoiceManager ttsVoiceManager3 = TtsVoiceManager.INSTANCE;
                            str3 = BusinessVoiceVm$generateTts$3.this.this$0._curTtsVersion;
                            ttsVoiceManager3.setMerchantTtsVersion(str3);
                            BusinessVoiceVm$generateTts$3.this.this$0.localMd5CompareServerMd5 = false;
                            BusinessVoiceVm$generateTts$3.this.this$0.callMain(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm.generateTts.3.1.4
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
                                    Function1<BusinessVoiceVm.TtsResult, Unit> onResult = BusinessVoiceVm$generateTts$3.this.this$0.getOnResult();
                                    if (onResult != null) {
                                        onResult.invoke(BusinessVoiceVm.TtsResult.ResultSuccess.INSTANCE);
                                    }
                                }
                            });
                            return;
                        }
                        Function0 function0 = (Function0) BusinessVoiceVm$generateTts$3.this.$createTTs.element;
                        if (function0 != null) {
                        }
                        str = BusinessVoiceVm$generateTts$3.this.this$0.TAG;
                        Pdlog.m3273d(str, "generateTts() invoke curNum =" + BusinessVoiceVm$generateTts$3.this.$curNum.element);
                        return;
                    }
                    TtsVoiceManager ttsVoiceManager4 = TtsVoiceManager.INSTANCE;
                    List<TtsVoiceInfo> merchantTtsList = TtsVoiceManager.INSTANCE.getMerchantTtsList();
                    if (merchantTtsList == null) {
                        merchantTtsList = CollectionsKt.emptyList();
                    }
                    ttsVoiceManager4.deleteOldMerchantTts(merchantTtsList);
                    BusinessVoiceVm$generateTts$3.this.this$0.callMain(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm.generateTts.3.1.1
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
                            Function1<BusinessVoiceVm.TtsResult, Unit> onResult = BusinessVoiceVm$generateTts$3.this.this$0.getOnResult();
                            if (onResult != null) {
                                onResult.invoke(BusinessVoiceVm.TtsResult.ResultFailure.INSTANCE);
                            }
                        }
                    });
                    return;
                }
                TtsVoiceManager ttsVoiceManager5 = TtsVoiceManager.INSTANCE;
                List<TtsVoiceInfo> merchantTtsList2 = TtsVoiceManager.INSTANCE.getMerchantTtsList();
                if (merchantTtsList2 == null) {
                    merchantTtsList2 = CollectionsKt.emptyList();
                }
                ttsVoiceManager5.deleteOldMerchantTts(merchantTtsList2);
                BusinessVoiceVm$generateTts$3.this.this$0.callMain(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm.generateTts.3.1.2
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
                        Function1<BusinessVoiceVm.TtsResult, Unit> onResult = BusinessVoiceVm$generateTts$3.this.this$0.getOnResult();
                        if (onResult != null) {
                            onResult.invoke(BusinessVoiceVm.TtsResult.ResultFailure.INSTANCE);
                        }
                    }
                });
            }
        });
    }
}
