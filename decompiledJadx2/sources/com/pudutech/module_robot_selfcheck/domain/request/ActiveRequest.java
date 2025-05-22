package com.pudutech.module_robot_selfcheck.domain.request;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.BaseRequest;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.network.response.RobotActiveStatusResp;
import com.pudutech.disinfect.baselib.state.AppException;
import com.pudutech.module_robot_selfcheck.ExtandsKt;
import com.pudutech.module_robot_selfcheck.data.ActiveStatusWrapInfo;
import com.pudutech.module_robot_selfcheck.data.Language;
import com.pudutech.module_robot_selfcheck.repository.ActiveRepository;
import com.pudutech.resources.language.LanguageUtils;
import com.pudutech.resources.language.Option;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: ActiveRequest.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u0016\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u001aj\b\u0012\u0004\u0012\u00020\u001b`\u001cJ\u000e\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020\u0014J\u000e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0018J\u0006\u0010$\u001a\u00020\"J\u0016\u0010%\u001a\u00020\"2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020'R!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u000b\u001a\u0004\b\u0010\u0010\u0011R!\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u000b\u001a\u0004\b\u0015\u0010\t¨\u0006("}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/domain/request/ActiveRequest;", "Lcom/pudutech/disinfect/baselib/base/BaseRequest;", "VM", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "(Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;)V", "activeStatusLD", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudutech/module_robot_selfcheck/data/ActiveStatusWrapInfo;", "getActiveStatusLD", "()Landroidx/lifecycle/MutableLiveData;", "activeStatusLD$delegate", "Lkotlin/Lazy;", "languageUtils", "Lcom/pudutech/resources/language/LanguageUtils;", "selfCheckRepository", "Lcom/pudutech/module_robot_selfcheck/repository/ActiveRepository;", "getSelfCheckRepository", "()Lcom/pudutech/module_robot_selfcheck/repository/ActiveRepository;", "selfCheckRepository$delegate", "setLanguageLD", "", "getSetLanguageLD", "setLanguageLD$delegate", "getPID", "", "getSupportLanguageList", "Ljava/util/ArrayList;", "Lcom/pudutech/module_robot_selfcheck/data/Language;", "Lkotlin/collections/ArrayList;", "isAlreadySetupLanguage", "context", "Landroid/content/Context;", "isLocalActive", "requestActiveByCode", "", "code", "requestActiveRobot", "setLocale", "op", "Lcom/pudutech/resources/language/Option;", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ActiveRequest extends BaseRequest {

    /* renamed from: activeStatusLD$delegate, reason: from kotlin metadata */
    private final Lazy activeStatusLD;
    private LanguageUtils languageUtils;

    /* renamed from: selfCheckRepository$delegate, reason: from kotlin metadata */
    private final Lazy selfCheckRepository;

    /* renamed from: setLanguageLD$delegate, reason: from kotlin metadata */
    private final Lazy setLanguageLD;

    /* JADX INFO: Access modifiers changed from: private */
    public final ActiveRepository getSelfCheckRepository() {
        return (ActiveRepository) this.selfCheckRepository.getValue();
    }

    public final MutableLiveData<ActiveStatusWrapInfo> getActiveStatusLD() {
        return (MutableLiveData) this.activeStatusLD.getValue();
    }

    public final MutableLiveData<Boolean> getSetLanguageLD() {
        return (MutableLiveData) this.setLanguageLD.getValue();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActiveRequest(BaseViewModel VM) {
        super(VM);
        Intrinsics.checkParameterIsNotNull(VM, "VM");
        this.selfCheckRepository = LazyKt.lazy(new Function0<ActiveRepository>() { // from class: com.pudutech.module_robot_selfcheck.domain.request.ActiveRequest$selfCheckRepository$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ActiveRepository invoke() {
                return new ActiveRepository();
            }
        });
        this.activeStatusLD = LazyKt.lazy(new Function0<MutableLiveData<ActiveStatusWrapInfo>>() { // from class: com.pudutech.module_robot_selfcheck.domain.request.ActiveRequest$activeStatusLD$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MutableLiveData<ActiveStatusWrapInfo> invoke() {
                return new MutableLiveData<>();
            }
        });
        this.setLanguageLD = LazyKt.lazy(new Function0<MutableLiveData<Boolean>>() { // from class: com.pudutech.module_robot_selfcheck.domain.request.ActiveRequest$setLanguageLD$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MutableLiveData<Boolean> invoke() {
                return new MutableLiveData<>();
            }
        });
    }

    public final ArrayList<Language> getSupportLanguageList() {
        ArrayList<Language> arrayList = new ArrayList<>();
        ArrayList<Option> list = ExtandsKt.getList();
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new Language((Option) it.next()));
            }
        }
        return arrayList;
    }

    public final void setLocale(Context context, Option op) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(op, "op");
        CoroutineScope viewModelScope = getViewModelScope();
        if (viewModelScope != null) {
            BuildersKt__Builders_commonKt.launch$default(viewModelScope, Dispatchers.getDefault(), null, new ActiveRequest$setLocale$1(this, context, op, null), 2, null);
        }
    }

    public final boolean isAlreadySetupLanguage(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (this.languageUtils == null) {
            this.languageUtils = new LanguageUtils(context);
            Unit unit = Unit.INSTANCE;
        }
        LanguageUtils languageUtils = this.languageUtils;
        if (languageUtils == null) {
            return false;
        }
        languageUtils.getNeedSet();
        LanguageUtils languageUtils2 = this.languageUtils;
        if ((languageUtils2 != null ? Boolean.valueOf(languageUtils2.getNeedSet()) : null) == null) {
            Intrinsics.throwNpe();
        }
        return !r2.booleanValue();
    }

    public final boolean isLocalActive() {
        return getSelfCheckRepository().isLocalActive();
    }

    public final void requestActiveByCode(String code) {
        Intrinsics.checkParameterIsNotNull(code, "code");
        getActiveStatusLD().setValue(null);
        BaseViewModel baseViewModel = getMVM().get();
        if (baseViewModel != null) {
            baseViewModel.requestSelfCheck(new ActiveRequest$requestActiveByCode$1(this, code, null), new Function1<String, Unit>() { // from class: com.pudutech.module_robot_selfcheck.domain.request.ActiveRequest$requestActiveByCode$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: ActiveRequest.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012,\u0010\u0002\u001a( \u0007*\u0013\u0018\u00010\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u00060\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\b"}, m3961d2 = {"<anonymous>", "", "p1", "Lcom/pudutech/module_robot_selfcheck/data/ActiveStatusWrapInfo;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
                /* renamed from: com.pudutech.module_robot_selfcheck.domain.request.ActiveRequest$requestActiveByCode$2$1 */
                /* loaded from: classes5.dex */
                public static final /* synthetic */ class C53711 extends FunctionReference implements Function1<ActiveStatusWrapInfo, Unit> {
                    C53711(MutableLiveData mutableLiveData) {
                        super(1, mutableLiveData);
                    }

                    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
                    public final String getName() {
                        return "postValue";
                    }

                    @Override // kotlin.jvm.internal.CallableReference
                    public final KDeclarationContainer getOwner() {
                        return Reflection.getOrCreateKotlinClass(MutableLiveData.class);
                    }

                    @Override // kotlin.jvm.internal.CallableReference
                    public final String getSignature() {
                        return "postValue(Ljava/lang/Object;)V";
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(ActiveStatusWrapInfo activeStatusWrapInfo) {
                        invoke2(activeStatusWrapInfo);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ActiveStatusWrapInfo activeStatusWrapInfo) {
                        ((MutableLiveData) this.receiver).postValue(activeStatusWrapInfo);
                    }
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    ActiveRepository selfCheckRepository;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    selfCheckRepository = ActiveRequest.this.getSelfCheckRepository();
                    selfCheckRepository.handleActiveStatusByCode(new C53711(ActiveRequest.this.getActiveStatusLD()), Integer.parseInt(it));
                }
            }, new Function1<AppException, Unit>() { // from class: com.pudutech.module_robot_selfcheck.domain.request.ActiveRequest$requestActiveByCode$3
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: ActiveRequest.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012,\u0010\u0002\u001a( \u0007*\u0013\u0018\u00010\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u00060\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\b"}, m3961d2 = {"<anonymous>", "", "p1", "Lcom/pudutech/module_robot_selfcheck/data/ActiveStatusWrapInfo;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
                /* renamed from: com.pudutech.module_robot_selfcheck.domain.request.ActiveRequest$requestActiveByCode$3$1 */
                /* loaded from: classes5.dex */
                public static final /* synthetic */ class C53721 extends FunctionReference implements Function1<ActiveStatusWrapInfo, Unit> {
                    C53721(MutableLiveData mutableLiveData) {
                        super(1, mutableLiveData);
                    }

                    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
                    public final String getName() {
                        return "postValue";
                    }

                    @Override // kotlin.jvm.internal.CallableReference
                    public final KDeclarationContainer getOwner() {
                        return Reflection.getOrCreateKotlinClass(MutableLiveData.class);
                    }

                    @Override // kotlin.jvm.internal.CallableReference
                    public final String getSignature() {
                        return "postValue(Ljava/lang/Object;)V";
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(ActiveStatusWrapInfo activeStatusWrapInfo) {
                        invoke2(activeStatusWrapInfo);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ActiveStatusWrapInfo activeStatusWrapInfo) {
                        ((MutableLiveData) this.receiver).postValue(activeStatusWrapInfo);
                    }
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AppException appException) {
                    invoke2(appException);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AppException it) {
                    String tag;
                    ActiveRepository selfCheckRepository;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    tag = ActiveRequest.this.getTAG();
                    Pdlog.m3274e(tag, it);
                    selfCheckRepository = ActiveRequest.this.getSelfCheckRepository();
                    selfCheckRepository.handleActiveStatusByCode(new C53721(ActiveRequest.this.getActiveStatusLD()), it.getErrorCode());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v11, types: [T, java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v14, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, java.io.BufferedReader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String getPID() {
        FileReader fileReader;
        String str;
        BufferedReader bufferedReader;
        File file = new File("/sdcard/pudu/config/pid");
        if (!file.exists()) {
            return "";
        }
        FileReader fileReader2 = (FileReader) null;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (BufferedReader) 0;
        try {
            try {
                fileReader = new FileReader(file);
            } catch (Throwable th) {
                th = th;
                fileReader = fileReader2;
            }
        } catch (Exception e) {
            e = e;
            str = "";
        }
        try {
            try {
                objectRef.element = new BufferedReader(fileReader);
                try {
                    final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = (String) 0;
                    str = "";
                    while (new Function0<String>() { // from class: com.pudutech.module_robot_selfcheck.domain.request.ActiveRequest$getPID$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Multi-variable type inference failed */
                        /* JADX WARN: Type inference failed for: r1v3, types: [T, java.lang.String] */
                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            Ref.ObjectRef.this.element = ((BufferedReader) objectRef.element).readLine();
                            return (String) Ref.ObjectRef.this.element;
                        }
                    }.invoke() != null) {
                        try {
                            str = Intrinsics.stringPlus(str, (String) objectRef2.element);
                        } catch (Exception e2) {
                            e = e2;
                            try {
                                e.printStackTrace();
                                bufferedReader = (BufferedReader) objectRef.element;
                                if (bufferedReader != null) {
                                }
                                fileReader.close();
                            } catch (Exception e3) {
                                e = e3;
                                fileReader2 = fileReader;
                                e.printStackTrace();
                                BufferedReader bufferedReader2 = (BufferedReader) objectRef.element;
                                if (bufferedReader2 != null) {
                                    bufferedReader2.close();
                                }
                                if (fileReader2 != null) {
                                    fileReader2.close();
                                }
                                Pdlog.m3273d(getTAG(), "读取出来的文件内容是：" + str);
                                if (str != null) {
                                }
                            }
                            Pdlog.m3273d(getTAG(), "读取出来的文件内容是：" + str);
                            if (str != null) {
                            }
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    str = "";
                }
                bufferedReader = (BufferedReader) objectRef.element;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                fileReader.close();
            } catch (Exception e5) {
                e = e5;
                str = "";
            }
            Pdlog.m3273d(getTAG(), "读取出来的文件内容是：" + str);
            return str != null ? str : "";
        } catch (Throwable th2) {
            th = th2;
            BufferedReader bufferedReader3 = (BufferedReader) objectRef.element;
            if (bufferedReader3 != null) {
                bufferedReader3.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            throw th;
        }
    }

    public final void requestActiveRobot() {
        getActiveStatusLD().setValue(null);
        BaseViewModel baseViewModel = getMVM().get();
        if (baseViewModel != null) {
            baseViewModel.request(new ActiveRequest$requestActiveRobot$1(this, null), new Function1<RobotActiveStatusResp, Unit>() { // from class: com.pudutech.module_robot_selfcheck.domain.request.ActiveRequest$requestActiveRobot$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: ActiveRequest.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012,\u0010\u0002\u001a( \u0007*\u0013\u0018\u00010\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u00060\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\b"}, m3961d2 = {"<anonymous>", "", "p1", "Lcom/pudutech/module_robot_selfcheck/data/ActiveStatusWrapInfo;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
                /* renamed from: com.pudutech.module_robot_selfcheck.domain.request.ActiveRequest$requestActiveRobot$2$1 */
                /* loaded from: classes5.dex */
                public static final /* synthetic */ class C53731 extends FunctionReference implements Function1<ActiveStatusWrapInfo, Unit> {
                    C53731(MutableLiveData mutableLiveData) {
                        super(1, mutableLiveData);
                    }

                    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
                    public final String getName() {
                        return "postValue";
                    }

                    @Override // kotlin.jvm.internal.CallableReference
                    public final KDeclarationContainer getOwner() {
                        return Reflection.getOrCreateKotlinClass(MutableLiveData.class);
                    }

                    @Override // kotlin.jvm.internal.CallableReference
                    public final String getSignature() {
                        return "postValue(Ljava/lang/Object;)V";
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(ActiveStatusWrapInfo activeStatusWrapInfo) {
                        invoke2(activeStatusWrapInfo);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ActiveStatusWrapInfo activeStatusWrapInfo) {
                        ((MutableLiveData) this.receiver).postValue(activeStatusWrapInfo);
                    }
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(RobotActiveStatusResp robotActiveStatusResp) {
                    invoke2(robotActiveStatusResp);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(RobotActiveStatusResp it) {
                    ActiveRepository selfCheckRepository;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    selfCheckRepository = ActiveRequest.this.getSelfCheckRepository();
                    selfCheckRepository.handleActiveStatusResponse(it, new C53731(ActiveRequest.this.getActiveStatusLD()));
                }
            }, new Function1<AppException, Unit>() { // from class: com.pudutech.module_robot_selfcheck.domain.request.ActiveRequest$requestActiveRobot$3
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: ActiveRequest.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012,\u0010\u0002\u001a( \u0007*\u0013\u0018\u00010\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u00060\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\b"}, m3961d2 = {"<anonymous>", "", "p1", "Lcom/pudutech/module_robot_selfcheck/data/ActiveStatusWrapInfo;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
                /* renamed from: com.pudutech.module_robot_selfcheck.domain.request.ActiveRequest$requestActiveRobot$3$1 */
                /* loaded from: classes5.dex */
                public static final /* synthetic */ class C53741 extends FunctionReference implements Function1<ActiveStatusWrapInfo, Unit> {
                    C53741(MutableLiveData mutableLiveData) {
                        super(1, mutableLiveData);
                    }

                    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
                    public final String getName() {
                        return "postValue";
                    }

                    @Override // kotlin.jvm.internal.CallableReference
                    public final KDeclarationContainer getOwner() {
                        return Reflection.getOrCreateKotlinClass(MutableLiveData.class);
                    }

                    @Override // kotlin.jvm.internal.CallableReference
                    public final String getSignature() {
                        return "postValue(Ljava/lang/Object;)V";
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(ActiveStatusWrapInfo activeStatusWrapInfo) {
                        invoke2(activeStatusWrapInfo);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ActiveStatusWrapInfo activeStatusWrapInfo) {
                        ((MutableLiveData) this.receiver).postValue(activeStatusWrapInfo);
                    }
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AppException appException) {
                    invoke2(appException);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AppException it) {
                    ActiveRepository selfCheckRepository;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    selfCheckRepository = ActiveRequest.this.getSelfCheckRepository();
                    selfCheckRepository.checkLocalActiveStatus(new C53741(ActiveRequest.this.getActiveStatusLD()));
                }
            });
        }
    }
}
