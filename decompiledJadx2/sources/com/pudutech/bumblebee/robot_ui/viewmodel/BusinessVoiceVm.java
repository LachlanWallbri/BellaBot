package com.pudutech.bumblebee.robot_ui.viewmodel;

import com.pudutech.base.MD5Util;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceManager;
import com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm;
import com.pudutech.disinfect.baselib.network.response.TtsVoiceInfo;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: BusinessVoiceVm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001:\u0001-B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u001e\u001a\u00020\u00192\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00190 H\u0002J\u0016\u0010!\u001a\u00020\u00192\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u0006\u0010#\u001a\u00020\u0010J\b\u0010$\u001a\u00020\u0006H\u0002J\u0010\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\nH\u0002J\u0010\u0010'\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\u0006H\u0002J\u0006\u0010)\u001a\u00020\u0019J\b\u0010\u000f\u001a\u00020\u0019H\u0002J\b\u0010*\u001a\u00020\u0019H\u0016J\u0011\u0010+\u001a\u00020\u0019H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010,R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R7\u0010\u0013\u001a\u001f\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006."}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/viewmodel/BusinessVoiceVm;", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/BaseFVM;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlinx/coroutines/CoroutineScope;)V", "TAG", "", "_Job", "Lkotlinx/coroutines/Job;", "_curProgress", "", "_curTtsVersion", "_ttsVoiceInfoList", "", "Lcom/pudutech/disinfect/baselib/network/response/TtsVoiceInfo;", "localMd5CompareServerMd5", "", "lock", "", "onResult", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/BusinessVoiceVm$TtsResult;", "Lkotlin/ParameterName;", "name", "ttsResult", "", "getOnResult", "()Lkotlin/jvm/functions/Function1;", "setOnResult", "(Lkotlin/jvm/functions/Function1;)V", "callMain", "block", "Lkotlin/Function0;", "generateTts", "ttsVoices", "getIsUpdate", "getLanguageConvert", "getTtsProgress", "size", "isNumeric", "str", "loadTtsVoice", "onCleared", "updateTts", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "TtsResult", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BusinessVoiceVm extends BaseFVM {
    private final String TAG;
    private volatile Job _Job;
    private int _curProgress;
    private String _curTtsVersion;
    private List<TtsVoiceInfo> _ttsVoiceInfoList;
    private boolean localMd5CompareServerMd5;
    private final Object lock;
    private Function1<? super TtsResult, Unit> onResult;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BusinessVoiceVm(CoroutineScope scope) {
        super(scope);
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        this.TAG = "BusinessVoiceVm";
        this._ttsVoiceInfoList = new ArrayList();
        this._curTtsVersion = "";
        this.lock = new Object();
    }

    public final Function1<TtsResult, Unit> getOnResult() {
        return this.onResult;
    }

    public final void setOnResult(Function1<? super TtsResult, Unit> function1) {
        this.onResult = function1;
    }

    /* compiled from: BusinessVoiceVm.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/viewmodel/BusinessVoiceVm$TtsResult;", "", "()V", "ResultFailure", "ResultProgress", "ResultSelected", "ResultSuccess", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/BusinessVoiceVm$TtsResult$ResultSuccess;", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/BusinessVoiceVm$TtsResult$ResultSelected;", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/BusinessVoiceVm$TtsResult$ResultFailure;", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/BusinessVoiceVm$TtsResult$ResultProgress;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static abstract class TtsResult {

        /* compiled from: BusinessVoiceVm.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/viewmodel/BusinessVoiceVm$TtsResult$ResultSuccess;", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/BusinessVoiceVm$TtsResult;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class ResultSuccess extends TtsResult {
            public static final ResultSuccess INSTANCE = new ResultSuccess();

            private ResultSuccess() {
                super(null);
            }
        }

        private TtsResult() {
        }

        public /* synthetic */ TtsResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: BusinessVoiceVm.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/viewmodel/BusinessVoiceVm$TtsResult$ResultSelected;", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/BusinessVoiceVm$TtsResult;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class ResultSelected extends TtsResult {
            public static final ResultSelected INSTANCE = new ResultSelected();

            private ResultSelected() {
                super(null);
            }
        }

        /* compiled from: BusinessVoiceVm.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/viewmodel/BusinessVoiceVm$TtsResult$ResultFailure;", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/BusinessVoiceVm$TtsResult;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class ResultFailure extends TtsResult {
            public static final ResultFailure INSTANCE = new ResultFailure();

            private ResultFailure() {
                super(null);
            }
        }

        /* compiled from: BusinessVoiceVm.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/viewmodel/BusinessVoiceVm$TtsResult$ResultProgress;", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/BusinessVoiceVm$TtsResult;", "progress", "", "(I)V", "getProgress", "()I", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class ResultProgress extends TtsResult {
            private final int progress;

            public ResultProgress(int i) {
                super(null);
                this.progress = i;
            }

            public final int getProgress() {
                return this.progress;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void callMain(Function0<Unit> block) {
        BuildersKt__Builders_commonKt.launch$default(getScope(), Dispatchers.getMain(), null, new BusinessVoiceVm$callMain$1(block, null), 2, null);
    }

    public final void loadTtsVoice() {
        if (this._Job != null) {
            Pdlog.m3273d(this.TAG, "loadTtsVoice is loading");
        } else {
            this._Job = VMExtKt.launchIO(this, new BusinessVoiceVm$loadTtsVoice$1(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void localMd5CompareServerMd5() {
        Object obj;
        List<TtsVoiceInfo> merchantTts = VoicePackageHelper.INSTANCE.getMerchantTts();
        String localeStr$default = LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null);
        String speaker = TtsVoiceManager.INSTANCE.getSpeaker();
        int size = this._ttsVoiceInfoList.size();
        if (merchantTts == null || size != merchantTts.size()) {
            String str = this.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("localMd5CompareServerMd5 server size:");
            sb.append(this._ttsVoiceInfoList.size());
            sb.append(" != local size:");
            sb.append(merchantTts != null ? Integer.valueOf(merchantTts.size()) : null);
            objArr[0] = sb.toString();
            Pdlog.m3273d(str, objArr);
            this.localMd5CompareServerMd5 = true;
            return;
        }
        for (TtsVoiceInfo ttsVoiceInfo : this._ttsVoiceInfoList) {
            Iterator<T> it = merchantTts.iterator();
            while (true) {
                if (it.hasNext()) {
                    obj = it.next();
                    if (Intrinsics.areEqual(ttsVoiceInfo.getId(), ((TtsVoiceInfo) obj).getId())) {
                        break;
                    }
                } else {
                    obj = null;
                    break;
                }
            }
            TtsVoiceInfo ttsVoiceInfo2 = (TtsVoiceInfo) obj;
            if (ttsVoiceInfo2 != null) {
                String str2 = (ttsVoiceInfo.getId() + localeStr$default) + ttsVoiceInfo.getText();
                if (!Intrinsics.areEqual(MD5Util.toMD5(str2 + speaker), ttsVoiceInfo2.getMd5())) {
                    Pdlog.m3273d(this.TAG, "localMd5CompareServerMd5 " + ttsVoiceInfo + " server md5 != " + ttsVoiceInfo2 + " local md5");
                    this.localMd5CompareServerMd5 = true;
                    return;
                }
                if (ttsVoiceInfo2 != null) {
                }
            }
            Pdlog.m3273d(this.TAG, "localMd5CompareServerMd5: " + ttsVoiceInfo + " can't not be found locally");
            this.localMd5CompareServerMd5 = true;
            return;
        }
        this.localMd5CompareServerMd5 = false;
    }

    public final boolean getIsUpdate() {
        String merchantTtsVersion = TtsVoiceManager.INSTANCE.getMerchantTtsVersion();
        Pdlog.m3273d(this.TAG, "getIsUpdate()  _curTtsVersion =" + this._curTtsVersion + " ttsVersion =" + merchantTtsVersion + " localMd5CompareServerMd5 = " + this.localMd5CompareServerMd5 + ' ');
        return ((this._curTtsVersion.length() > 0) && (Intrinsics.areEqual(this._curTtsVersion, merchantTtsVersion) ^ true)) || this.localMd5CompareServerMd5;
    }

    public final Object updateTts(Continuation<? super Unit> continuation) {
        generateTts(this._ttsVoiceInfoList);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v4, types: [T, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r7v1, types: [T, kotlin.jvm.functions.Function0] */
    public final void generateTts(List<TtsVoiceInfo> ttsVoices) {
        final int size = ttsVoices.size();
        if (size == 0) {
            TtsVoiceManager.INSTANCE.setMerchantTtsVersion(this._curTtsVersion);
            this.localMd5CompareServerMd5 = false;
            callMain(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm$generateTts$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    Function1<BusinessVoiceVm.TtsResult, Unit> onResult = BusinessVoiceVm.this.getOnResult();
                    if (onResult != null) {
                        ttsProgress = BusinessVoiceVm.this.getTtsProgress(size);
                        onResult.invoke(new BusinessVoiceVm.TtsResult.ResultProgress(ttsProgress));
                    }
                    Function1<BusinessVoiceVm.TtsResult, Unit> onResult2 = BusinessVoiceVm.this.getOnResult();
                    if (onResult2 != null) {
                        onResult2.invoke(BusinessVoiceVm.TtsResult.ResultSuccess.INSTANCE);
                    }
                }
            });
        }
        Pdlog.m3273d(this.TAG, "generateTts() size =" + size);
        this._curProgress = 0;
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        callMain(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm$generateTts$2
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
                int i;
                Function1<BusinessVoiceVm.TtsResult, Unit> onResult = BusinessVoiceVm.this.getOnResult();
                if (onResult != null) {
                    i = BusinessVoiceVm.this._curProgress;
                    onResult.invoke(new BusinessVoiceVm.TtsResult.ResultProgress(i));
                }
            }
        });
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (Function0) 0;
        objectRef.element = new BusinessVoiceVm$generateTts$3(this, ttsVoices, intRef, size, objectRef);
        ((Function0) objectRef.element).invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getLanguageConvert() {
        String localeStr = LanguageUtils.INSTANCE.getLocaleStr(true);
        switch (localeStr.hashCode()) {
            case 93071103:
                return localeStr.equals("ar_AR") ? "en_XA" : localeStr;
            case 93666943:
                return localeStr.equals("bg_BG") ? "bg_BG" : localeStr;
            case 94947999:
                return localeStr.equals("cs_CS") ? "cs_CZ" : localeStr;
            case 95454463:
                return localeStr.equals("de_DE") ? "de" : localeStr;
            case 96646644:
                return localeStr.equals("en_US") ? "en" : localeStr;
            case 96795103:
                return localeStr.equals("es_ES") ? "es" : localeStr;
            case 96795599:
                return localeStr.equals("es_US") ? "es_LA" : localeStr;
            case 96824880:
                return localeStr.equals("et_EE") ? "et_EE" : localeStr;
            case 97688863:
                return localeStr.equals("fr_FR") ? "fr" : localeStr;
            case 99535967:
                return localeStr.equals("hr_HR") ? "hr_HR" : localeStr;
            case 99625343:
                return localeStr.equals("hu_HU") ? "hu_HU" : localeStr;
            case 100340341:
                return localeStr.equals("in_ID") ? "en_ID" : localeStr;
            case 100519103:
                return localeStr.equals("it_IT") ? "it" : localeStr;
            case 100608468:
                return localeStr.equals("iw_IL") ? "he_IL" : localeStr;
            case 100876622:
                return localeStr.equals("ja_JP") ? "ja" : localeStr;
            case 102217250:
                return localeStr.equals("ko_KR") ? "ko" : localeStr;
            case 104898527:
                return localeStr.equals("nl_NL") ? "nl" : localeStr;
            case 106745631:
                return localeStr.equals("pl_PL") ? "pl_PL" : localeStr;
            case 106983531:
                return localeStr.equals("pt_BR") ? "pt_BR" : localeStr;
            case 106983967:
                return localeStr.equals("pt_PT") ? "pt" : localeStr;
            case 108860863:
                return localeStr.equals("ru_RU") ? "ru" : localeStr;
            case 109486495:
                return localeStr.equals("sk_SK") ? "sk_SK" : localeStr;
            case 109516284:
                return localeStr.equals("sl_SI") ? "sl_SL" : localeStr;
            case 109814190:
                return localeStr.equals("sv_SE") ? "sv_SE" : localeStr;
            case 110320671:
                return localeStr.equals("th_TH") ? "en_TH" : localeStr;
            case 110618591:
                return localeStr.equals("tr_TR") ? "tr_TR" : localeStr;
            case 112197572:
                return localeStr.equals("vi_VN") ? "vi_VN" : localeStr;
            case 115861276:
                return localeStr.equals("zh_CN") ? "zh_cn" : localeStr;
            case 115861428:
                return localeStr.equals("zh_HK") ? "zh_hk" : localeStr;
            case 115861812:
                return localeStr.equals("zh_TW") ? "zh_tw" : localeStr;
            default:
                return localeStr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isNumeric(String str) {
        Pattern compile = Pattern.compile("[0-9]*");
        Intrinsics.checkExpressionValueIsNotNull(compile, "Pattern.compile(\"[0-9]*\")");
        Matcher matcher = compile.matcher(str);
        Intrinsics.checkExpressionValueIsNotNull(matcher, "pattern.matcher(str)");
        return matcher.matches();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getTtsProgress(int size) {
        int i = (int) ((this._curProgress / ((size - 1) * 1.0f)) * 100);
        int i2 = i < 100 ? i : 100;
        Pdlog.m3273d(this.TAG, "_curProgress =" + this._curProgress + "  toAll = " + i2);
        return i2;
    }

    @Override // com.pudutech.bumblebee.robot_ui.viewmodel.BaseFVM
    public void onCleared() {
        Job job = this._Job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this._Job = (Job) null;
        this._curProgress = 0;
        this._curTtsVersion = "";
    }
}
