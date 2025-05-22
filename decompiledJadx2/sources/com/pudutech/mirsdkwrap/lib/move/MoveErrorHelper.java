package com.pudutech.mirsdkwrap.lib.move;

import android.os.Build;
import android.util.Log;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.google.gson.Gson;
import com.iflytek.cloud.SpeechUtility;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.util.ShellUtils;
import com.pudutech.mirsdkwrap.lib.move.bean.Classify;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveErrorClassifyDefineKt;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveErrorProcess;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveErrors;
import com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveErrorHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010 \u001a\u00020!J\b\u0010\"\u001a\u00020!H\u0002J;\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u001b2!\u0010&\u001a\u001d\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020!0'H\u0002J\u0006\u0010+\u001a\u00020!J\r\u0010,\u001a\u00020!H\u0000¢\u0006\u0002\b-J\u0012\u0010.\u001a\u0004\u0018\u00010/2\u0006\u00100\u001a\u00020\u0006H\u0002J\u0012\u00101\u001a\u0004\u0018\u00010/2\u0006\u00102\u001a\u00020\u0006H\u0002J\u0016\u00103\u001a\u00020!2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\t05H\u0002J\u0015\u00106\u001a\u00020\u001b2\u0006\u00107\u001a\u00020\u0006H\u0000¢\u0006\u0002\b8J)\u00109\u001a\u00020!2!\u0010&\u001a\u001d\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020!0'J3\u0010:\u001a\u00020!2\u0006\u0010;\u001a\u00020\t2!\u0010&\u001a\u001d\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020!0'H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f0\u000bj\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f8F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012RH\u0010\u0015\u001a\u0016\u0012\u0004\u0012\u00020\t\u0018\u00010\u0013j\n\u0012\u0004\u0012\u00020\t\u0018\u0001`\u00142\u001a\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\t\u0018\u00010\u0013j\n\u0012\u0004\u0012\u00020\t\u0018\u0001`\u00148F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0013j\b\u0012\u0004\u0012\u00020\t`\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0013j\b\u0012\u0004\u0012\u00020\t`\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0013j\b\u0012\u0004\u0012\u00020\t`\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0013j\b\u0012\u0004\u0012\u00020\t`\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006<"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/MoveErrorHelper;", "", "devicesControlHelper", "Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;", "(Lcom/pudutech/mirsdkwrap/lib/robot/DevicesControlHelper;)V", "TAG", "", "allError", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveError;", "autoFixMap", "Ljava/util/HashMap;", "Lkotlinx/coroutines/Job;", "Lkotlin/collections/HashMap;", "<set-?>", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveErrorProcess;", "currentErrorSuggestion", "getCurrentErrorSuggestion", "()Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveErrorProcess;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "currentErrors", "getCurrentErrors", "()Ljava/util/ArrayList;", "gson", "Lcom/google/gson/Gson;", "isDestroy", "", "rebootPower", "restartSoftware", "userTry", "wait", "cancelAutoFix", "", "checkDestroy", "checkFixJobAndCallBack", TransferTable.COLUMN_KEY, SpeechUtility.TAG_RESOURCE_RESULT, "cb", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", ShellUtils.COMMAND_SU, "clearErrors", "destroy", "destroy$module_robot_mirsdk_wrapper_release", "parseErrorByGson", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveErrors;", "string", "parseErrors", "error", "setCurrentErrors", "l", "", "setErrors", "s", "setErrors$module_robot_mirsdk_wrapper_release", "startAutoFix", "startAutoFixJob", C3898x.f4338g, "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MoveErrorHelper {
    private final String TAG;
    private final CopyOnWriteArrayList<MoveError> allError;
    private final HashMap<String, Job> autoFixMap;
    private MoveErrorProcess currentErrorSuggestion;
    private ArrayList<MoveError> currentErrors;
    private final DevicesControlHelper devicesControlHelper;
    private final Gson gson;
    private boolean isDestroy;
    private final ArrayList<MoveError> rebootPower;
    private final ArrayList<MoveError> restartSoftware;
    private final ArrayList<MoveError> userTry;
    private final ArrayList<MoveError> wait;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MoveErrorProcess.values().length];

        static {
            $EnumSwitchMapping$0[MoveErrorProcess.REBOOT_POWER.ordinal()] = 1;
            $EnumSwitchMapping$0[MoveErrorProcess.RESTART_SOFTWARE.ordinal()] = 2;
            $EnumSwitchMapping$0[MoveErrorProcess.TRY.ordinal()] = 3;
            $EnumSwitchMapping$0[MoveErrorProcess.AUTO_RESUME.ordinal()] = 4;
            $EnumSwitchMapping$0[MoveErrorProcess.NOTHING.ordinal()] = 5;
        }
    }

    public MoveErrorHelper(DevicesControlHelper devicesControlHelper) {
        Intrinsics.checkParameterIsNotNull(devicesControlHelper, "devicesControlHelper");
        this.devicesControlHelper = devicesControlHelper;
        this.TAG = "MoveErrorHelper";
        this.gson = new Gson();
        this.allError = new CopyOnWriteArrayList<>();
        this.rebootPower = new ArrayList<>();
        this.restartSoftware = new ArrayList<>();
        this.userTry = new ArrayList<>();
        this.wait = new ArrayList<>();
        this.currentErrorSuggestion = MoveErrorProcess.NOTHING;
        this.autoFixMap = new HashMap<>();
    }

    public final MoveErrorProcess getCurrentErrorSuggestion() {
        checkDestroy();
        return this.currentErrorSuggestion;
    }

    public final ArrayList<MoveError> getCurrentErrors() {
        checkDestroy();
        ArrayList<MoveError> arrayList = this.currentErrors;
        if (arrayList == null) {
            return null;
        }
        ArrayList<MoveError> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator<T> it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(MoveError.copy$default((MoveError) it.next(), null, null, null, null, 0L, 31, null));
        }
        ArrayList<MoveError> arrayList4 = new ArrayList<>();
        arrayList4.addAll(arrayList3);
        return arrayList4;
    }

    public final boolean setErrors$module_robot_mirsdk_wrapper_release(String s) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        Pdlog.m3273d(this.TAG, "setError : s = " + s + ';');
        MoveErrors parseErrors = parseErrors(s);
        if (parseErrors == null) {
            return false;
        }
        ArrayList<MoveError> list = parseErrors.getList();
        this.allError.addAll(list);
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            MoveError moveError = (MoveError) obj;
            if (moveError == null) {
                Intrinsics.throwNpe();
            }
            if (Intrinsics.areEqual(moveError.getLevel(), MoveError.LEVEL_ERROR) || Intrinsics.areEqual(moveError.getLevel(), MoveError.LEVEL_FATAL) || Intrinsics.areEqual(moveError.getLevel(), MoveError.LEVEL_EVENT)) {
                arrayList.add(obj);
            }
        }
        ArrayList<MoveError> arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            Pdlog.m3273d(this.TAG, "setErrors need process is empty , is warning error");
            setCurrentErrors(this.allError);
            return true;
        }
        for (MoveError moveError2 : arrayList2) {
            if (moveError2 == null) {
                Intrinsics.throwNpe();
            }
            Classify classify = MoveErrorClassifyDefineKt.getErrorProcessMap().get(MoveErrorClassifyDefineKt.toProcessKey(moveError2));
            if (classify != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[classify.getProcess().ordinal()];
                if (i == 1) {
                    this.rebootPower.add(moveError2);
                } else if (i == 2) {
                    this.restartSoftware.add(moveError2);
                } else if (i == 3) {
                    this.userTry.add(moveError2);
                } else if (i == 4) {
                    this.wait.add(moveError2);
                } else if (i != 5) {
                    throw new NoWhenBranchMatchedException();
                }
            }
            if (classify == null) {
                String json = this.gson.toJson(moveError2);
                Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(e)");
                MoveError moveError3 = new MoveError("NoDefine", MoveError.LEVEL_ERROR, json, null, 0L, 24, null);
                moveError3.genId();
                Boolean.valueOf(this.restartSoftware.add(moveError3));
            }
        }
        if (!this.rebootPower.isEmpty()) {
            this.currentErrorSuggestion = MoveErrorProcess.REBOOT_POWER;
            setCurrentErrors(this.rebootPower);
        } else if (!this.restartSoftware.isEmpty()) {
            this.currentErrorSuggestion = MoveErrorProcess.RESTART_SOFTWARE;
            setCurrentErrors(this.restartSoftware);
        } else if (!this.userTry.isEmpty()) {
            this.currentErrorSuggestion = MoveErrorProcess.TRY;
            setCurrentErrors(this.userTry);
        } else if (!this.wait.isEmpty()) {
            this.currentErrorSuggestion = MoveErrorProcess.AUTO_RESUME;
            setCurrentErrors(this.wait);
        } else {
            this.currentErrorSuggestion = MoveErrorProcess.NOTHING;
            clearErrors();
        }
        Pdlog.m3273d(this.TAG, "setErrors currentErrorSuggestion = " + getCurrentErrorSuggestion());
        return true;
    }

    private final void checkDestroy() {
        if (this.isDestroy) {
            throw new Exception("move action is destroy, error can not use");
        }
    }

    private final void setCurrentErrors(List<MoveError> l) {
        ArrayList<MoveError> arrayList = new ArrayList<>();
        List<MoveError> list = l;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList2.add(MoveError.copy$default((MoveError) it.next(), null, null, null, null, 0L, 31, null));
        }
        arrayList.addAll(arrayList2);
        this.currentErrors = arrayList;
    }

    public final void clearErrors() {
        Pdlog.m3273d(this.TAG, "clearErrors ");
        this.currentErrorSuggestion = MoveErrorProcess.NOTHING;
        cancelAutoFix();
        this.allError.clear();
        this.rebootPower.clear();
        this.restartSoftware.clear();
        this.userTry.clear();
        this.wait.clear();
    }

    public final void destroy$module_robot_mirsdk_wrapper_release() {
        this.isDestroy = true;
        clearErrors();
    }

    public final void startAutoFix(Function1<? super Boolean, Unit> cb) {
        Intrinsics.checkParameterIsNotNull(cb, "cb");
        Pdlog.m3273d(this.TAG, "startAutoFix : cb = " + cb + "; ");
        checkDestroy();
        if (getCurrentErrorSuggestion() != MoveErrorProcess.AUTO_RESUME || !(!this.wait.isEmpty())) {
            Pdlog.m3273d(this.TAG, "startAutoFix : has not AUTO_RESUME error");
            cb.invoke(false);
        } else {
            Iterator<T> it = this.wait.iterator();
            while (it.hasNext()) {
                startAutoFixJob((MoveError) it.next(), cb);
            }
        }
    }

    public final void cancelAutoFix() {
        Pdlog.m3273d(this.TAG, "cancelAutoFix ");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new MoveErrorHelper$cancelAutoFix$1(this, null), 3, null);
    }

    private final void startAutoFixJob(MoveError e, Function1<? super Boolean, Unit> cb) {
        Job launch$default;
        Job launch$default2;
        if (this.autoFixMap.containsKey(e.getError_type())) {
            Pdlog.m3273d(this.TAG, "startAutoFixJob , has same job : " + e);
            return;
        }
        String error_type = e.getError_type();
        int hashCode = error_type.hashCode();
        if (hashCode != -1854713221) {
            if (hashCode == -1666029548 && error_type.equals("LostLidar")) {
                launch$default2 = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new MoveErrorHelper$startAutoFixJob$job$1(this, e, cb, null), 3, null);
                this.autoFixMap.put(e.getError_type(), launch$default2);
                return;
            }
        } else if (error_type.equals("LostRGBD")) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new MoveErrorHelper$startAutoFixJob$job$2(this, e, cb, null), 3, null);
            this.autoFixMap.put(e.getError_type(), launch$default);
            return;
        }
        Pdlog.m3274e(this.TAG, "no define " + e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkFixJobAndCallBack(String key, boolean result, Function1<? super Boolean, Unit> cb) {
        Pdlog.m3273d(this.TAG, "checkFixJobAndCallBack : key = " + key + "; result = " + result + "; cb = " + cb + "; ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new MoveErrorHelper$checkFixJobAndCallBack$1(this, result, key, cb, null), 2, null);
    }

    private final MoveErrors parseErrors(String error) {
        ArrayList<MoveError> list;
        ArrayList<MoveError> list2;
        Iterator<MoveError> it = null;
        MoveErrors moveErrors = (MoveErrors) null;
        try {
            if (error.charAt(0) == '{') {
                moveErrors = parseErrorByGson("{\"list\":[" + error + "]}");
            } else if (error.charAt(0) == '[') {
                moveErrors = parseErrorByGson("{\"list\":" + error + '}');
            } else {
                Pdlog.m3274e(this.TAG, "parse fail.wrong description string=" + error);
            }
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
        if (Build.VERSION.SDK_INT < 24) {
            if (moveErrors != null && (list = moveErrors.getList()) != null) {
                it = list.iterator();
            }
            while (it != null && it.hasNext()) {
                MoveError next = it.next();
                if (next == null) {
                    it.remove();
                } else {
                    next.genId();
                }
            }
        } else if (moveErrors != null && (list2 = moveErrors.getList()) != null) {
            list2.removeIf(new Predicate<MoveError>() { // from class: com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper$parseErrors$1
                @Override // java.util.function.Predicate
                public final boolean test(MoveError moveError) {
                    if (moveError != null) {
                        moveError.genId();
                    }
                    return moveError == null;
                }
            });
        }
        return moveErrors;
    }

    private final MoveErrors parseErrorByGson(String string) {
        Pdlog.m3273d(this.TAG, "parse " + string);
        return (MoveErrors) this.gson.fromJson(string, MoveErrors.class);
    }
}
