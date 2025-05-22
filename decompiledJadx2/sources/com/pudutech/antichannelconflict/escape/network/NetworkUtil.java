package com.pudutech.antichannelconflict.escape.network;

import androidx.core.app.NotificationCompat;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.antichannelconflict.escape.util.MapStatus;
import com.pudutech.base.Pdlog;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.PdHost;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/* compiled from: NetworkUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001!B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J°\u0001\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042]\u0010\u0016\u001aY\b\u0001\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001b\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJx\u0010\u001f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042]\u0010\u0016\u001aY\b\u0001\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001b\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010 R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, m3961d2 = {"Lcom/pudutech/antichannelconflict/escape/network/NetworkUtil;", "", "()V", "LOCK_STATE", "", "LOCK_URL_V2", "TAG", "getTAG", "()Ljava/lang/String;", "checkEscape", "", "mac", "longitude", "latitude", "autoLock", "", "changeType", "Lcom/pudutech/antichannelconflict/escape/util/MapStatus;", "mbts", "product", "", "softver", "callback", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "code", "lockStatus", NotificationCompat.CATEGORY_MESSAGE, "Lkotlin/coroutines/Continuation;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLcom/pudutech/antichannelconflict/escape/util/MapStatus;Ljava/lang/String;ILjava/lang/String;Lkotlin/jvm/functions/Function4;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkLockStatus", "(Ljava/lang/String;Lkotlin/jvm/functions/Function4;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "EscapeService", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class NetworkUtil {
    private static final String LOCK_STATE = "/api/common/robot_life/lock_state/v1";
    private static final String LOCK_URL_V2 = "/api/common/robot/avoid_collusion/v1";
    public static final NetworkUtil INSTANCE = new NetworkUtil();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    /* compiled from: NetworkUtil.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0001\u0010\u000b\u001a\u00020\fH§@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/escape/network/NetworkUtil$EscapeService;", "", "checkHasEscape", "Lcom/pudutech/antichannelconflict/escape/network/EscapeResBase;", "Lcom/pudutech/antichannelconflict/escape/network/EscapeResData;", "reqData", "Lcom/pudutech/antichannelconflict/escape/network/EscapeReq;", "(Lcom/pudutech/antichannelconflict/escape/network/EscapeReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAntiBugSellStatus", "Lcom/pudutech/antichannelconflict/escape/network/ApiResponse;", "Lcom/pudutech/antichannelconflict/escape/network/RespAntiBugSell;", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/antichannelconflict/escape/network/ReqAntiBugSell;", "(Lcom/pudutech/antichannelconflict/escape/network/ReqAntiBugSell;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface EscapeService {
        @Headers({PdHost.ROBOT_BIZ})
        @POST(NetworkUtil.LOCK_URL_V2)
        Object checkHasEscape(@Body EscapeReq escapeReq, Continuation<? super EscapeResBase<EscapeResData>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST(NetworkUtil.LOCK_STATE)
        Object getAntiBugSellStatus(@Body ReqAntiBugSell reqAntiBugSell, Continuation<? super ApiResponse<RespAntiBugSell>> continuation);
    }

    private NetworkUtil() {
    }

    public final String getTAG() {
        return TAG;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(16:1|(2:3|(13:5|6|7|(1:(1:(1:(1:(1:(3:14|15|16)(2:18|19))(6:20|21|22|(2:24|(1:26))|15|16))(9:27|28|29|30|31|22|(0)|15|16))(8:40|41|42|31|22|(0)|15|16))(4:43|44|45|46))(7:79|80|81|82|83|84|(1:86)(1:87))|47|48|49|50|(4:52|53|54|(2:56|(1:58)(3:59|42|31))(4:60|61|62|(1:64)(4:65|29|30|31)))|22|(0)|15|16))|96|6|7|(0)(0)|47|48|49|50|(0)|22|(0)|15|16|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x012d, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x00b2, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x00b3, code lost:
    
        r6 = r2;
        r7 = 3;
        r5 = r5;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0202 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0030  */
    /* JADX WARN: Type inference failed for: r0v14, types: [T, com.pudutech.antichannelconflict.escape.network.NetworkUtil$EscapeService] */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v18, types: [com.pudutech.antichannelconflict.escape.network.ApiResponse] */
    /* JADX WARN: Type inference failed for: r2v26, types: [com.pudutech.antichannelconflict.escape.network.ApiResponse] */
    /* JADX WARN: Type inference failed for: r5v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v34 */
    /* JADX WARN: Type inference failed for: r5v35 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r6v10, types: [com.pudutech.antichannelconflict.escape.network.ApiResponse, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object checkLockStatus(String str, Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function4, Continuation<? super Unit> continuation) {
        NetworkUtil$checkLockStatus$1 networkUtil$checkLockStatus$1;
        int i;
        Ref.ObjectRef objectRef;
        Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function42;
        String str2;
        NetworkUtil networkUtil;
        String str3;
        ?? r5;
        Exception exc;
        Integer boxInt;
        Boolean boxBoolean;
        String message;
        NetworkUtil networkUtil2;
        String str4;
        Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function43;
        Object obj;
        Ref.ObjectRef objectRef2;
        Throwable m4513exceptionOrNullimpl;
        Object obj2;
        Ref.ObjectRef objectRef3;
        Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function44;
        NetworkUtil networkUtil3;
        String str5;
        Object m4510constructorimpl;
        boolean m4517isSuccessimpl;
        Ref.ObjectRef objectRef4;
        ?? r2;
        ApiResponse apiResponse;
        Object obj3;
        String str6;
        Object obj4;
        Object obj5;
        Object obj6;
        String str7 = str;
        if (continuation instanceof NetworkUtil$checkLockStatus$1) {
            networkUtil$checkLockStatus$1 = (NetworkUtil$checkLockStatus$1) continuation;
            if ((networkUtil$checkLockStatus$1.label & Integer.MIN_VALUE) != 0) {
                networkUtil$checkLockStatus$1.label -= Integer.MIN_VALUE;
                Object obj7 = networkUtil$checkLockStatus$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = networkUtil$checkLockStatus$1.label;
                int i2 = -1;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj7);
                    Pdlog.m3273d(TAG, "checkLockStatus");
                    objectRef3 = new Ref.ObjectRef();
                    objectRef3.element = (EscapeService) PdNetworkManager.f10310INSTANCE.createService(EscapeService.class);
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        NetworkUtil networkUtil4 = this;
                        EscapeService escapeService = (EscapeService) objectRef3.element;
                        ReqAntiBugSell reqAntiBugSell = new ReqAntiBugSell(str7, null, 2, null);
                        networkUtil$checkLockStatus$1.L$0 = this;
                        networkUtil$checkLockStatus$1.L$1 = str7;
                        function44 = function4;
                        try {
                            networkUtil$checkLockStatus$1.L$2 = function44;
                            networkUtil$checkLockStatus$1.L$3 = objectRef3;
                            networkUtil$checkLockStatus$1.L$4 = networkUtil4;
                            networkUtil$checkLockStatus$1.label = 1;
                            obj7 = escapeService.getAntiBugSellStatus(reqAntiBugSell, networkUtil$checkLockStatus$1);
                            if (obj7 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            networkUtil3 = this;
                            str5 = str7;
                            objectRef2 = objectRef3;
                        } catch (Throwable th) {
                            th = th;
                            networkUtil3 = this;
                            Result.Companion companion2 = Result.INSTANCE;
                            function43 = function44;
                            networkUtil2 = networkUtil3;
                            str4 = str7;
                            objectRef2 = objectRef3;
                            m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                            m4517isSuccessimpl = Result.m4517isSuccessimpl(m4510constructorimpl);
                            obj = m4510constructorimpl;
                            if (m4517isSuccessimpl) {
                            }
                            m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj);
                            if (m4513exceptionOrNullimpl != null) {
                            }
                            return Unit.INSTANCE;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        function44 = function4;
                    }
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            apiResponse = (ApiResponse) networkUtil$checkLockStatus$1.L$5;
                            Object obj8 = networkUtil$checkLockStatus$1.L$4;
                            objectRef4 = (Ref.ObjectRef) networkUtil$checkLockStatus$1.L$3;
                            function43 = (Function4) networkUtil$checkLockStatus$1.L$2;
                            str4 = (String) networkUtil$checkLockStatus$1.L$1;
                            networkUtil2 = (NetworkUtil) networkUtil$checkLockStatus$1.L$0;
                            ResultKt.throwOnFailure(obj7);
                            obj3 = obj8;
                            Pdlog.m3273d(TAG, "checkLockStatus code is 0", apiResponse.getResponseData());
                            obj5 = obj3;
                            objectRef2 = objectRef4;
                            obj = obj5;
                            m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj);
                            if (m4513exceptionOrNullimpl != null) {
                            }
                            return Unit.INSTANCE;
                        }
                        if (i != 3) {
                            if (i != 4) {
                                if (i == 5) {
                                    Object obj9 = networkUtil$checkLockStatus$1.L$4;
                                    ResultKt.throwOnFailure(obj7);
                                    return Unit.INSTANCE;
                                }
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            exc = (Exception) networkUtil$checkLockStatus$1.L$6;
                            Object obj10 = networkUtil$checkLockStatus$1.L$4;
                            objectRef = (Ref.ObjectRef) networkUtil$checkLockStatus$1.L$3;
                            function42 = (Function4) networkUtil$checkLockStatus$1.L$2;
                            str2 = (String) networkUtil$checkLockStatus$1.L$1;
                            networkUtil = (NetworkUtil) networkUtil$checkLockStatus$1.L$0;
                            ResultKt.throwOnFailure(obj7);
                            obj2 = obj10;
                            Pdlog.m3273d(TAG, "checkLockStatus Exception ", exc.getMessage());
                            objectRef2 = objectRef;
                            networkUtil2 = networkUtil;
                            str4 = str2;
                            function43 = function42;
                            obj = obj2;
                            m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj);
                            if (m4513exceptionOrNullimpl != null) {
                                Pdlog.m3274e(TAG, "checkLockStatus fail " + m4513exceptionOrNullimpl.getMessage());
                                Integer boxInt2 = Boxing.boxInt(-1);
                                Boolean boxBoolean2 = Boxing.boxBoolean(false);
                                String message2 = m4513exceptionOrNullimpl.getMessage();
                                networkUtil$checkLockStatus$1.L$0 = networkUtil2;
                                networkUtil$checkLockStatus$1.L$1 = str4;
                                networkUtil$checkLockStatus$1.L$2 = function43;
                                networkUtil$checkLockStatus$1.L$3 = objectRef2;
                                networkUtil$checkLockStatus$1.L$4 = obj;
                                networkUtil$checkLockStatus$1.L$5 = m4513exceptionOrNullimpl;
                                networkUtil$checkLockStatus$1.label = 5;
                                if (function43.invoke(boxInt2, boxBoolean2, message2, networkUtil$checkLockStatus$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                            return Unit.INSTANCE;
                        }
                        r2 = (ApiResponse) networkUtil$checkLockStatus$1.L$5;
                        Object obj11 = networkUtil$checkLockStatus$1.L$4;
                        objectRef4 = (Ref.ObjectRef) networkUtil$checkLockStatus$1.L$3;
                        function43 = (Function4) networkUtil$checkLockStatus$1.L$2;
                        str4 = (String) networkUtil$checkLockStatus$1.L$1;
                        networkUtil2 = (NetworkUtil) networkUtil$checkLockStatus$1.L$0;
                        ResultKt.throwOnFailure(obj7);
                        obj6 = obj11;
                        try {
                            Pdlog.m3273d(TAG, "checkLockStatus code is -1", r2.getMsg());
                            obj5 = obj6;
                            objectRef2 = objectRef4;
                            obj = obj5;
                        } catch (Exception e) {
                            e = e;
                            str6 = r2;
                            objectRef = objectRef4;
                            obj4 = obj6;
                            function42 = function43;
                            str2 = str4;
                            networkUtil = networkUtil2;
                            i2 = -1;
                            r5 = obj4;
                            str3 = str6;
                            exc = e;
                            boxInt = Boxing.boxInt(i2);
                            boxBoolean = Boxing.boxBoolean(false);
                            message = exc.getMessage();
                            networkUtil$checkLockStatus$1.L$0 = networkUtil;
                            networkUtil$checkLockStatus$1.L$1 = str2;
                            networkUtil$checkLockStatus$1.L$2 = function42;
                            networkUtil$checkLockStatus$1.L$3 = objectRef;
                            networkUtil$checkLockStatus$1.L$4 = r5;
                            networkUtil$checkLockStatus$1.L$5 = str3;
                            networkUtil$checkLockStatus$1.L$6 = exc;
                            networkUtil$checkLockStatus$1.label = 4;
                            obj2 = r5;
                            if (function42.invoke(boxInt, boxBoolean, message, networkUtil$checkLockStatus$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            Pdlog.m3273d(TAG, "checkLockStatus Exception ", exc.getMessage());
                            objectRef2 = objectRef;
                            networkUtil2 = networkUtil;
                            str4 = str2;
                            function43 = function42;
                            obj = obj2;
                            m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj);
                            if (m4513exceptionOrNullimpl != null) {
                            }
                            return Unit.INSTANCE;
                        }
                        m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj);
                        if (m4513exceptionOrNullimpl != null) {
                        }
                        return Unit.INSTANCE;
                    }
                    objectRef2 = (Ref.ObjectRef) networkUtil$checkLockStatus$1.L$3;
                    Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function45 = (Function4) networkUtil$checkLockStatus$1.L$2;
                    str5 = (String) networkUtil$checkLockStatus$1.L$1;
                    networkUtil3 = (NetworkUtil) networkUtil$checkLockStatus$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj7);
                        function44 = function45;
                    } catch (Throwable th3) {
                        th = th3;
                        function44 = function45;
                        objectRef3 = objectRef2;
                        str7 = str5;
                        Result.Companion companion22 = Result.INSTANCE;
                        function43 = function44;
                        networkUtil2 = networkUtil3;
                        str4 = str7;
                        objectRef2 = objectRef3;
                        m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                        m4517isSuccessimpl = Result.m4517isSuccessimpl(m4510constructorimpl);
                        obj = m4510constructorimpl;
                        if (m4517isSuccessimpl) {
                        }
                        m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj);
                        if (m4513exceptionOrNullimpl != null) {
                        }
                        return Unit.INSTANCE;
                    }
                }
                m4510constructorimpl = Result.m4510constructorimpl((ApiResponse) obj7);
                NetworkUtil networkUtil5 = networkUtil3;
                str4 = str5;
                function43 = function44;
                networkUtil2 = networkUtil5;
                m4517isSuccessimpl = Result.m4517isSuccessimpl(m4510constructorimpl);
                obj = m4510constructorimpl;
                if (m4517isSuccessimpl) {
                    ?? r6 = (ApiResponse) m4510constructorimpl;
                    try {
                        if (r6.getCode() == 0) {
                            Integer boxInt3 = Boxing.boxInt(0);
                            Boolean boxBoolean3 = Boxing.boxBoolean(Intrinsics.areEqual(((RespAntiBugSell) r6.getResponseData()).getLock_state(), "locked"));
                            String msg = r6.getMsg();
                            networkUtil$checkLockStatus$1.L$0 = networkUtil2;
                            networkUtil$checkLockStatus$1.L$1 = str4;
                            networkUtil$checkLockStatus$1.L$2 = function43;
                            networkUtil$checkLockStatus$1.L$3 = objectRef2;
                            networkUtil$checkLockStatus$1.L$4 = m4510constructorimpl;
                            networkUtil$checkLockStatus$1.L$5 = r6;
                            networkUtil$checkLockStatus$1.label = 2;
                            if (function43.invoke(boxInt3, boxBoolean3, msg, networkUtil$checkLockStatus$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            objectRef4 = objectRef2;
                            apiResponse = r6;
                            obj3 = m4510constructorimpl;
                            Pdlog.m3273d(TAG, "checkLockStatus code is 0", apiResponse.getResponseData());
                            obj5 = obj3;
                            objectRef2 = objectRef4;
                            obj = obj5;
                        } else {
                            Integer boxInt4 = Boxing.boxInt(-1);
                            Boolean boxBoolean4 = Boxing.boxBoolean(false);
                            try {
                                String msg2 = r6.getMsg();
                                networkUtil$checkLockStatus$1.L$0 = networkUtil2;
                                networkUtil$checkLockStatus$1.L$1 = str4;
                                networkUtil$checkLockStatus$1.L$2 = function43;
                                networkUtil$checkLockStatus$1.L$3 = objectRef2;
                                networkUtil$checkLockStatus$1.L$4 = m4510constructorimpl;
                                networkUtil$checkLockStatus$1.L$5 = r6;
                                networkUtil$checkLockStatus$1.label = 3;
                                if (function43.invoke(boxInt4, boxBoolean4, msg2, networkUtil$checkLockStatus$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                objectRef4 = objectRef2;
                                r2 = r6;
                                obj6 = m4510constructorimpl;
                                Pdlog.m3273d(TAG, "checkLockStatus code is -1", r2.getMsg());
                                obj5 = obj6;
                                objectRef2 = objectRef4;
                                obj = obj5;
                            } catch (Exception e2) {
                                e = e2;
                                objectRef = objectRef2;
                                obj4 = m4510constructorimpl;
                                str6 = r6;
                                function42 = function43;
                                str2 = str4;
                                networkUtil = networkUtil2;
                                i2 = -1;
                                r5 = obj4;
                                str3 = str6;
                                exc = e;
                                boxInt = Boxing.boxInt(i2);
                                boxBoolean = Boxing.boxBoolean(false);
                                message = exc.getMessage();
                                networkUtil$checkLockStatus$1.L$0 = networkUtil;
                                networkUtil$checkLockStatus$1.L$1 = str2;
                                networkUtil$checkLockStatus$1.L$2 = function42;
                                networkUtil$checkLockStatus$1.L$3 = objectRef;
                                networkUtil$checkLockStatus$1.L$4 = r5;
                                networkUtil$checkLockStatus$1.L$5 = str3;
                                networkUtil$checkLockStatus$1.L$6 = exc;
                                networkUtil$checkLockStatus$1.label = 4;
                                obj2 = r5;
                                if (function42.invoke(boxInt, boxBoolean, message, networkUtil$checkLockStatus$1) == coroutine_suspended) {
                                }
                                Pdlog.m3273d(TAG, "checkLockStatus Exception ", exc.getMessage());
                                objectRef2 = objectRef;
                                networkUtil2 = networkUtil;
                                str4 = str2;
                                function43 = function42;
                                obj = obj2;
                                m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj);
                                if (m4513exceptionOrNullimpl != null) {
                                }
                                return Unit.INSTANCE;
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        objectRef = objectRef2;
                        int i3 = m4510constructorimpl;
                        String str8 = r6;
                        function42 = function43;
                        str2 = str4;
                        networkUtil = networkUtil2;
                        r5 = i3;
                        str3 = str8;
                        exc = e;
                        boxInt = Boxing.boxInt(i2);
                        boxBoolean = Boxing.boxBoolean(false);
                        message = exc.getMessage();
                        networkUtil$checkLockStatus$1.L$0 = networkUtil;
                        networkUtil$checkLockStatus$1.L$1 = str2;
                        networkUtil$checkLockStatus$1.L$2 = function42;
                        networkUtil$checkLockStatus$1.L$3 = objectRef;
                        networkUtil$checkLockStatus$1.L$4 = r5;
                        networkUtil$checkLockStatus$1.L$5 = str3;
                        networkUtil$checkLockStatus$1.L$6 = exc;
                        networkUtil$checkLockStatus$1.label = 4;
                        obj2 = r5;
                        if (function42.invoke(boxInt, boxBoolean, message, networkUtil$checkLockStatus$1) == coroutine_suspended) {
                        }
                        Pdlog.m3273d(TAG, "checkLockStatus Exception ", exc.getMessage());
                        objectRef2 = objectRef;
                        networkUtil2 = networkUtil;
                        str4 = str2;
                        function43 = function42;
                        obj = obj2;
                        m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj);
                        if (m4513exceptionOrNullimpl != null) {
                        }
                        return Unit.INSTANCE;
                    }
                }
                m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj);
                if (m4513exceptionOrNullimpl != null) {
                }
                return Unit.INSTANCE;
            }
        }
        networkUtil$checkLockStatus$1 = new NetworkUtil$checkLockStatus$1(this, continuation);
        Object obj72 = networkUtil$checkLockStatus$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = networkUtil$checkLockStatus$1.label;
        int i22 = -1;
        if (i != 0) {
        }
        m4510constructorimpl = Result.m4510constructorimpl((ApiResponse) obj72);
        NetworkUtil networkUtil52 = networkUtil3;
        str4 = str5;
        function43 = function44;
        networkUtil2 = networkUtil52;
        m4517isSuccessimpl = Result.m4517isSuccessimpl(m4510constructorimpl);
        obj = m4510constructorimpl;
        if (m4517isSuccessimpl) {
        }
        m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj);
        if (m4513exceptionOrNullimpl != null) {
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x046d  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x047b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0444 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /* JADX WARN: Type inference failed for: r0v5, types: [T, com.pudutech.antichannelconflict.escape.network.NetworkUtil$EscapeService] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object checkEscape(String str, String str2, String str3, boolean z, MapStatus mapStatus, String str4, int i, String str5, Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function4, Continuation<? super Unit> continuation) {
        NetworkUtil$checkEscape$1 networkUtil$checkEscape$1;
        int i2;
        Ref.ObjectRef objectRef;
        String str6;
        String str7;
        String str8;
        boolean z2;
        MapStatus mapStatus2;
        String str9;
        int i3;
        String str10;
        Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function42;
        String str11;
        NetworkUtil networkUtil;
        NetworkUtil networkUtil2;
        EscapeReq escapeReq;
        EscapeService escapeService;
        Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function43;
        Object m4510constructorimpl;
        Ref.ObjectRef objectRef2;
        NetworkUtil networkUtil3;
        String str12;
        boolean z3;
        String str13;
        String str14;
        String str15;
        String str16;
        MapStatus mapStatus3;
        int i4;
        Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function44;
        Object obj;
        int i5;
        Object obj2;
        NetworkUtil networkUtil4;
        EscapeResBase escapeResBase;
        NetworkUtil networkUtil5;
        Object obj3;
        Object obj4;
        MapStatus mapStatus4;
        int i6;
        EscapeResBase escapeResBase2;
        Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function45;
        boolean z4;
        String str17;
        String str18;
        int i7;
        String str19;
        Object obj5;
        Exception exc;
        NetworkUtil networkUtil6;
        Integer boxInt;
        EscapeResBase escapeResBase3;
        Boolean boxBoolean;
        String message;
        Object obj6;
        Object obj7;
        String str20;
        String str21;
        Object invoke;
        Ref.ObjectRef objectRef3;
        Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function46;
        String str22;
        int i8;
        String str23;
        MapStatus mapStatus5;
        Object obj8;
        EscapeResBase escapeResBase4;
        NetworkUtil networkUtil7;
        boolean z5;
        String str24;
        String str25;
        NetworkUtil networkUtil8;
        Ref.ObjectRef objectRef4;
        NetworkUtil networkUtil9;
        Object invoke2;
        EscapeResBase escapeResBase5;
        Object obj9;
        Throwable m4513exceptionOrNullimpl;
        if (continuation instanceof NetworkUtil$checkEscape$1) {
            networkUtil$checkEscape$1 = (NetworkUtil$checkEscape$1) continuation;
            if ((networkUtil$checkEscape$1.label & Integer.MIN_VALUE) != 0) {
                networkUtil$checkEscape$1.label -= Integer.MIN_VALUE;
                Object obj10 = networkUtil$checkEscape$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = networkUtil$checkEscape$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj10);
                    Pdlog.m3273d(TAG, "checkLockStatus");
                    objectRef = new Ref.ObjectRef();
                    objectRef.element = (EscapeService) PdNetworkManager.f10310INSTANCE.createService(EscapeService.class);
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        networkUtil2 = this;
                        escapeReq = new EscapeReq(str, str2, str3, z, null, str5, 0L, i, mapStatus.name(), str4, 80, null);
                        escapeService = (EscapeService) objectRef.element;
                        networkUtil$checkEscape$1.L$0 = this;
                        str6 = str;
                    } catch (Throwable th) {
                        th = th;
                        str6 = str;
                    }
                    try {
                        networkUtil$checkEscape$1.L$1 = str6;
                        str7 = str2;
                    } catch (Throwable th2) {
                        th = th2;
                        str7 = str2;
                        str8 = str3;
                        z2 = z;
                        mapStatus2 = mapStatus;
                        str9 = str4;
                        i3 = i;
                        str10 = str5;
                        function42 = function4;
                        str11 = str10;
                        networkUtil = this;
                        Result.Companion companion2 = Result.INSTANCE;
                        m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                        Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function47 = function42;
                        objectRef2 = objectRef;
                        networkUtil3 = networkUtil;
                        str12 = str7;
                        z3 = z2;
                        str13 = str9;
                        str14 = str11;
                        str15 = str6;
                        str16 = str8;
                        mapStatus3 = mapStatus2;
                        i4 = i3;
                        function44 = function47;
                        if (!Result.m4517isSuccessimpl(m4510constructorimpl)) {
                        }
                    }
                    try {
                        networkUtil$checkEscape$1.L$2 = str7;
                        str8 = str3;
                    } catch (Throwable th3) {
                        th = th3;
                        str8 = str3;
                        z2 = z;
                        mapStatus2 = mapStatus;
                        str9 = str4;
                        i3 = i;
                        str10 = str5;
                        function42 = function4;
                        str11 = str10;
                        networkUtil = this;
                        Result.Companion companion22 = Result.INSTANCE;
                        m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                        Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function472 = function42;
                        objectRef2 = objectRef;
                        networkUtil3 = networkUtil;
                        str12 = str7;
                        z3 = z2;
                        str13 = str9;
                        str14 = str11;
                        str15 = str6;
                        str16 = str8;
                        mapStatus3 = mapStatus2;
                        i4 = i3;
                        function44 = function472;
                        if (!Result.m4517isSuccessimpl(m4510constructorimpl)) {
                        }
                    }
                    try {
                        networkUtil$checkEscape$1.L$3 = str8;
                        z2 = z;
                    } catch (Throwable th4) {
                        th = th4;
                        z2 = z;
                        mapStatus2 = mapStatus;
                        str9 = str4;
                        i3 = i;
                        str10 = str5;
                        function42 = function4;
                        str11 = str10;
                        networkUtil = this;
                        Result.Companion companion222 = Result.INSTANCE;
                        m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                        Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function4722 = function42;
                        objectRef2 = objectRef;
                        networkUtil3 = networkUtil;
                        str12 = str7;
                        z3 = z2;
                        str13 = str9;
                        str14 = str11;
                        str15 = str6;
                        str16 = str8;
                        mapStatus3 = mapStatus2;
                        i4 = i3;
                        function44 = function4722;
                        if (!Result.m4517isSuccessimpl(m4510constructorimpl)) {
                        }
                    }
                    try {
                        networkUtil$checkEscape$1.Z$0 = z2;
                        mapStatus2 = mapStatus;
                        try {
                            networkUtil$checkEscape$1.L$4 = mapStatus2;
                            str9 = str4;
                        } catch (Throwable th5) {
                            th = th5;
                            str9 = str4;
                            i3 = i;
                            str10 = str5;
                            function42 = function4;
                            str11 = str10;
                            networkUtil = this;
                            Result.Companion companion2222 = Result.INSTANCE;
                            m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                            Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function47222 = function42;
                            objectRef2 = objectRef;
                            networkUtil3 = networkUtil;
                            str12 = str7;
                            z3 = z2;
                            str13 = str9;
                            str14 = str11;
                            str15 = str6;
                            str16 = str8;
                            mapStatus3 = mapStatus2;
                            i4 = i3;
                            function44 = function47222;
                            if (!Result.m4517isSuccessimpl(m4510constructorimpl)) {
                            }
                        }
                        try {
                            networkUtil$checkEscape$1.L$5 = str9;
                            i3 = i;
                        } catch (Throwable th6) {
                            th = th6;
                            i3 = i;
                            str10 = str5;
                            function42 = function4;
                            str11 = str10;
                            networkUtil = this;
                            Result.Companion companion22222 = Result.INSTANCE;
                            m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                            Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function472222 = function42;
                            objectRef2 = objectRef;
                            networkUtil3 = networkUtil;
                            str12 = str7;
                            z3 = z2;
                            str13 = str9;
                            str14 = str11;
                            str15 = str6;
                            str16 = str8;
                            mapStatus3 = mapStatus2;
                            i4 = i3;
                            function44 = function472222;
                            if (!Result.m4517isSuccessimpl(m4510constructorimpl)) {
                            }
                        }
                        try {
                            networkUtil$checkEscape$1.I$0 = i3;
                            str10 = str5;
                        } catch (Throwable th7) {
                            th = th7;
                            str10 = str5;
                            function42 = function4;
                            str11 = str10;
                            networkUtil = this;
                            Result.Companion companion222222 = Result.INSTANCE;
                            m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                            Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function4722222 = function42;
                            objectRef2 = objectRef;
                            networkUtil3 = networkUtil;
                            str12 = str7;
                            z3 = z2;
                            str13 = str9;
                            str14 = str11;
                            str15 = str6;
                            str16 = str8;
                            mapStatus3 = mapStatus2;
                            i4 = i3;
                            function44 = function4722222;
                            if (!Result.m4517isSuccessimpl(m4510constructorimpl)) {
                            }
                        }
                        try {
                            networkUtil$checkEscape$1.L$6 = str10;
                            function42 = function4;
                        } catch (Throwable th8) {
                            th = th8;
                            function42 = function4;
                            str11 = str10;
                            networkUtil = this;
                            Result.Companion companion2222222 = Result.INSTANCE;
                            m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                            Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function47222222 = function42;
                            objectRef2 = objectRef;
                            networkUtil3 = networkUtil;
                            str12 = str7;
                            z3 = z2;
                            str13 = str9;
                            str14 = str11;
                            str15 = str6;
                            str16 = str8;
                            mapStatus3 = mapStatus2;
                            i4 = i3;
                            function44 = function47222222;
                            if (!Result.m4517isSuccessimpl(m4510constructorimpl)) {
                            }
                        }
                        try {
                            networkUtil$checkEscape$1.L$7 = function42;
                            networkUtil$checkEscape$1.L$8 = objectRef;
                            networkUtil$checkEscape$1.L$9 = networkUtil2;
                            networkUtil$checkEscape$1.L$10 = escapeReq;
                            networkUtil$checkEscape$1.label = 1;
                            obj10 = escapeService.checkHasEscape(escapeReq, networkUtil$checkEscape$1);
                            if (obj10 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            function43 = function42;
                            str11 = str10;
                            networkUtil = this;
                        } catch (Throwable th9) {
                            th = th9;
                            str11 = str10;
                            networkUtil = this;
                            Result.Companion companion22222222 = Result.INSTANCE;
                            m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                            Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function472222222 = function42;
                            objectRef2 = objectRef;
                            networkUtil3 = networkUtil;
                            str12 = str7;
                            z3 = z2;
                            str13 = str9;
                            str14 = str11;
                            str15 = str6;
                            str16 = str8;
                            mapStatus3 = mapStatus2;
                            i4 = i3;
                            function44 = function472222222;
                            if (!Result.m4517isSuccessimpl(m4510constructorimpl)) {
                            }
                        }
                    } catch (Throwable th10) {
                        th = th10;
                        mapStatus2 = mapStatus;
                        str9 = str4;
                        i3 = i;
                        str10 = str5;
                        function42 = function4;
                        str11 = str10;
                        networkUtil = this;
                        Result.Companion companion222222222 = Result.INSTANCE;
                        m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                        Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function4722222222 = function42;
                        objectRef2 = objectRef;
                        networkUtil3 = networkUtil;
                        str12 = str7;
                        z3 = z2;
                        str13 = str9;
                        str14 = str11;
                        str15 = str6;
                        str16 = str8;
                        mapStatus3 = mapStatus2;
                        i4 = i3;
                        function44 = function4722222222;
                        if (!Result.m4517isSuccessimpl(m4510constructorimpl)) {
                        }
                    }
                } else if (i2 == 1) {
                    objectRef = (Ref.ObjectRef) networkUtil$checkEscape$1.L$8;
                    function43 = (Function4) networkUtil$checkEscape$1.L$7;
                    str11 = (String) networkUtil$checkEscape$1.L$6;
                    i3 = networkUtil$checkEscape$1.I$0;
                    str9 = (String) networkUtil$checkEscape$1.L$5;
                    mapStatus2 = (MapStatus) networkUtil$checkEscape$1.L$4;
                    z2 = networkUtil$checkEscape$1.Z$0;
                    str8 = (String) networkUtil$checkEscape$1.L$3;
                    str7 = (String) networkUtil$checkEscape$1.L$2;
                    str6 = (String) networkUtil$checkEscape$1.L$1;
                    networkUtil = (NetworkUtil) networkUtil$checkEscape$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj10);
                    } catch (Throwable th11) {
                        th = th11;
                        function42 = function43;
                        Result.Companion companion2222222222 = Result.INSTANCE;
                        m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                        Function4<? super Integer, ? super Boolean, ? super String, ? super Continuation<? super Unit>, ? extends Object> function47222222222 = function42;
                        objectRef2 = objectRef;
                        networkUtil3 = networkUtil;
                        str12 = str7;
                        z3 = z2;
                        str13 = str9;
                        str14 = str11;
                        str15 = str6;
                        str16 = str8;
                        mapStatus3 = mapStatus2;
                        i4 = i3;
                        function44 = function47222222222;
                        if (!Result.m4517isSuccessimpl(m4510constructorimpl)) {
                        }
                    }
                } else {
                    if (i2 == 2) {
                        EscapeResBase escapeResBase6 = (EscapeResBase) networkUtil$checkEscape$1.L$10;
                        Object obj11 = networkUtil$checkEscape$1.L$9;
                        Ref.ObjectRef objectRef5 = (Ref.ObjectRef) networkUtil$checkEscape$1.L$8;
                        function46 = (Function4) networkUtil$checkEscape$1.L$7;
                        str22 = (String) networkUtil$checkEscape$1.L$6;
                        i8 = networkUtil$checkEscape$1.I$0;
                        str23 = (String) networkUtil$checkEscape$1.L$5;
                        mapStatus5 = (MapStatus) networkUtil$checkEscape$1.L$4;
                        boolean z6 = networkUtil$checkEscape$1.Z$0;
                        str24 = (String) networkUtil$checkEscape$1.L$3;
                        str25 = (String) networkUtil$checkEscape$1.L$2;
                        String str26 = (String) networkUtil$checkEscape$1.L$1;
                        NetworkUtil networkUtil10 = (NetworkUtil) networkUtil$checkEscape$1.L$0;
                        try {
                            ResultKt.throwOnFailure(obj10);
                            networkUtil7 = networkUtil10;
                            escapeResBase4 = escapeResBase6;
                            obj8 = obj11;
                            objectRef3 = objectRef5;
                            str15 = str26;
                            z5 = z6;
                            try {
                                networkUtil8 = networkUtil7;
                                objectRef4 = objectRef3;
                                try {
                                    Pdlog.m3273d(TAG, "checkLockStatus code is 0", escapeResBase4.getResponseData());
                                    networkUtil4 = networkUtil8;
                                    obj = coroutine_suspended;
                                    obj2 = obj8;
                                    i5 = 1;
                                    mapStatus3 = mapStatus5;
                                    str13 = str23;
                                    i4 = i8;
                                    str14 = str22;
                                    function44 = function46;
                                    objectRef2 = objectRef4;
                                    String str27 = str25;
                                    str16 = str24;
                                    z3 = z5;
                                    str12 = str27;
                                } catch (Exception e) {
                                    e = e;
                                    obj4 = coroutine_suspended;
                                    escapeResBase2 = escapeResBase4;
                                    mapStatus4 = mapStatus5;
                                    str21 = str25;
                                    i6 = -1;
                                    exc = e;
                                    i7 = i8;
                                    str18 = str24;
                                    networkUtil6 = networkUtil8;
                                    str17 = str22;
                                    z4 = z5;
                                    str20 = str23;
                                    str19 = str15;
                                    obj5 = obj8;
                                    function45 = function46;
                                    objectRef2 = objectRef4;
                                    Integer boxInt2 = Boxing.boxInt(i6);
                                    Boolean boxBoolean2 = Boxing.boxBoolean(false);
                                    String message2 = exc.getMessage();
                                    networkUtil$checkEscape$1.L$0 = networkUtil6;
                                    networkUtil$checkEscape$1.L$1 = str19;
                                    networkUtil$checkEscape$1.L$2 = str21;
                                    networkUtil$checkEscape$1.L$3 = str18;
                                    networkUtil$checkEscape$1.Z$0 = z4;
                                    networkUtil$checkEscape$1.L$4 = mapStatus4;
                                    networkUtil$checkEscape$1.L$5 = str20;
                                    networkUtil$checkEscape$1.I$0 = i7;
                                    networkUtil$checkEscape$1.L$6 = str17;
                                    networkUtil$checkEscape$1.L$7 = function45;
                                    networkUtil$checkEscape$1.L$8 = objectRef2;
                                    networkUtil$checkEscape$1.L$9 = obj5;
                                    networkUtil$checkEscape$1.L$10 = escapeResBase2;
                                    networkUtil$checkEscape$1.L$11 = exc;
                                    networkUtil$checkEscape$1.label = 4;
                                    networkUtil9 = networkUtil6;
                                    invoke2 = function45.invoke(boxInt2, boxBoolean2, message2, networkUtil$checkEscape$1);
                                    obj = obj4;
                                    if (invoke2 == obj) {
                                    }
                                    MapStatus mapStatus6 = mapStatus4;
                                    i5 = 1;
                                    Pdlog.m3273d(TAG, "checkLockStatus Exception ", exc.getMessage());
                                    obj2 = obj5;
                                    str15 = str19;
                                    i4 = i7;
                                    networkUtil4 = networkUtil9;
                                    str16 = str18;
                                    str14 = str17;
                                    z3 = z4;
                                    function44 = function45;
                                    mapStatus3 = mapStatus6;
                                    String str28 = str20;
                                    str12 = str21;
                                    str13 = str28;
                                    m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
                                    if (m4513exceptionOrNullimpl != null) {
                                    }
                                    return Unit.INSTANCE;
                                }
                            } catch (Exception e2) {
                                e = e2;
                                networkUtil8 = networkUtil7;
                                objectRef4 = objectRef3;
                            }
                        } catch (Exception e3) {
                            obj4 = coroutine_suspended;
                            i6 = -1;
                            escapeResBase2 = escapeResBase6;
                            exc = e3;
                            networkUtil6 = networkUtil10;
                            obj5 = obj11;
                            mapStatus4 = mapStatus5;
                            str21 = str25;
                            i7 = i8;
                            str18 = str24;
                            str17 = str22;
                            z4 = z6;
                            function45 = function46;
                            objectRef2 = objectRef5;
                            str20 = str23;
                            str19 = str26;
                        }
                        m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
                        if (m4513exceptionOrNullimpl != null) {
                        }
                        return Unit.INSTANCE;
                    }
                    if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 == 5) {
                                Object obj12 = networkUtil$checkEscape$1.L$9;
                                int i9 = networkUtil$checkEscape$1.I$0;
                                boolean z7 = networkUtil$checkEscape$1.Z$0;
                                ResultKt.throwOnFailure(obj10);
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        Exception exc2 = (Exception) networkUtil$checkEscape$1.L$11;
                        obj5 = networkUtil$checkEscape$1.L$9;
                        objectRef2 = (Ref.ObjectRef) networkUtil$checkEscape$1.L$8;
                        function45 = (Function4) networkUtil$checkEscape$1.L$7;
                        str17 = (String) networkUtil$checkEscape$1.L$6;
                        i7 = networkUtil$checkEscape$1.I$0;
                        str20 = (String) networkUtil$checkEscape$1.L$5;
                        mapStatus4 = (MapStatus) networkUtil$checkEscape$1.L$4;
                        z4 = networkUtil$checkEscape$1.Z$0;
                        str18 = (String) networkUtil$checkEscape$1.L$3;
                        str21 = (String) networkUtil$checkEscape$1.L$2;
                        str19 = (String) networkUtil$checkEscape$1.L$1;
                        NetworkUtil networkUtil11 = (NetworkUtil) networkUtil$checkEscape$1.L$0;
                        ResultKt.throwOnFailure(obj10);
                        obj = coroutine_suspended;
                        networkUtil9 = networkUtil11;
                        exc = exc2;
                        MapStatus mapStatus62 = mapStatus4;
                        i5 = 1;
                        Pdlog.m3273d(TAG, "checkLockStatus Exception ", exc.getMessage());
                        obj2 = obj5;
                        str15 = str19;
                        i4 = i7;
                        networkUtil4 = networkUtil9;
                        str16 = str18;
                        str14 = str17;
                        z3 = z4;
                        function44 = function45;
                        mapStatus3 = mapStatus62;
                        String str282 = str20;
                        str12 = str21;
                        str13 = str282;
                        m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
                        if (m4513exceptionOrNullimpl != null) {
                            Object obj13 = obj;
                            String str29 = TAG;
                            Object[] objArr = new Object[i5];
                            objArr[0] = String.valueOf(m4513exceptionOrNullimpl.getMessage());
                            Pdlog.m3274e(str29, objArr);
                            Integer boxInt3 = Boxing.boxInt(-1);
                            Boolean boxBoolean3 = Boxing.boxBoolean(false);
                            String message3 = m4513exceptionOrNullimpl.getMessage();
                            networkUtil$checkEscape$1.L$0 = networkUtil4;
                            networkUtil$checkEscape$1.L$1 = str15;
                            networkUtil$checkEscape$1.L$2 = str12;
                            networkUtil$checkEscape$1.L$3 = str16;
                            networkUtil$checkEscape$1.Z$0 = z3;
                            networkUtil$checkEscape$1.L$4 = mapStatus3;
                            networkUtil$checkEscape$1.L$5 = str13;
                            networkUtil$checkEscape$1.I$0 = i4;
                            networkUtil$checkEscape$1.L$6 = str14;
                            networkUtil$checkEscape$1.L$7 = function44;
                            networkUtil$checkEscape$1.L$8 = objectRef2;
                            networkUtil$checkEscape$1.L$9 = obj2;
                            networkUtil$checkEscape$1.L$10 = m4513exceptionOrNullimpl;
                            networkUtil$checkEscape$1.label = 5;
                            if (function44.invoke(boxInt3, boxBoolean3, message3, networkUtil$checkEscape$1) == obj13) {
                                return obj13;
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    EscapeResBase escapeResBase7 = (EscapeResBase) networkUtil$checkEscape$1.L$10;
                    obj6 = networkUtil$checkEscape$1.L$9;
                    objectRef2 = (Ref.ObjectRef) networkUtil$checkEscape$1.L$8;
                    function44 = (Function4) networkUtil$checkEscape$1.L$7;
                    str14 = (String) networkUtil$checkEscape$1.L$6;
                    i4 = networkUtil$checkEscape$1.I$0;
                    str13 = (String) networkUtil$checkEscape$1.L$5;
                    mapStatus3 = (MapStatus) networkUtil$checkEscape$1.L$4;
                    z3 = networkUtil$checkEscape$1.Z$0;
                    str16 = (String) networkUtil$checkEscape$1.L$3;
                    str12 = (String) networkUtil$checkEscape$1.L$2;
                    str15 = (String) networkUtil$checkEscape$1.L$1;
                    escapeResBase3 = escapeResBase7;
                    NetworkUtil networkUtil12 = (NetworkUtil) networkUtil$checkEscape$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj10);
                        obj = coroutine_suspended;
                        networkUtil5 = networkUtil12;
                        escapeResBase5 = escapeResBase3;
                        try {
                            obj9 = obj6;
                            try {
                                Pdlog.m3273d(TAG, "checkLockStatus code is -1", escapeResBase5.getMessage());
                                obj2 = obj9;
                                networkUtil4 = networkUtil5;
                                i5 = 1;
                            } catch (Exception e4) {
                                e = e4;
                                obj4 = obj;
                                escapeResBase2 = escapeResBase5;
                                mapStatus4 = mapStatus3;
                                i6 = -1;
                                exc = e;
                                function45 = function44;
                                z4 = z3;
                                networkUtil6 = networkUtil5;
                                str17 = str14;
                                str18 = str16;
                                i7 = i4;
                                str19 = str15;
                                obj5 = obj9;
                                String str30 = str12;
                                str20 = str13;
                                str21 = str30;
                                Integer boxInt22 = Boxing.boxInt(i6);
                                Boolean boxBoolean22 = Boxing.boxBoolean(false);
                                String message22 = exc.getMessage();
                                networkUtil$checkEscape$1.L$0 = networkUtil6;
                                networkUtil$checkEscape$1.L$1 = str19;
                                networkUtil$checkEscape$1.L$2 = str21;
                                networkUtil$checkEscape$1.L$3 = str18;
                                networkUtil$checkEscape$1.Z$0 = z4;
                                networkUtil$checkEscape$1.L$4 = mapStatus4;
                                networkUtil$checkEscape$1.L$5 = str20;
                                networkUtil$checkEscape$1.I$0 = i7;
                                networkUtil$checkEscape$1.L$6 = str17;
                                networkUtil$checkEscape$1.L$7 = function45;
                                networkUtil$checkEscape$1.L$8 = objectRef2;
                                networkUtil$checkEscape$1.L$9 = obj5;
                                networkUtil$checkEscape$1.L$10 = escapeResBase2;
                                networkUtil$checkEscape$1.L$11 = exc;
                                networkUtil$checkEscape$1.label = 4;
                                networkUtil9 = networkUtil6;
                                invoke2 = function45.invoke(boxInt22, boxBoolean22, message22, networkUtil$checkEscape$1);
                                obj = obj4;
                                if (invoke2 == obj) {
                                }
                                MapStatus mapStatus622 = mapStatus4;
                                i5 = 1;
                                Pdlog.m3273d(TAG, "checkLockStatus Exception ", exc.getMessage());
                                obj2 = obj5;
                                str15 = str19;
                                i4 = i7;
                                networkUtil4 = networkUtil9;
                                str16 = str18;
                                str14 = str17;
                                z3 = z4;
                                function44 = function45;
                                mapStatus3 = mapStatus622;
                                String str2822 = str20;
                                str12 = str21;
                                str13 = str2822;
                                m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
                                if (m4513exceptionOrNullimpl != null) {
                                }
                                return Unit.INSTANCE;
                            }
                        } catch (Exception e5) {
                            e = e5;
                            obj9 = obj6;
                        }
                    } catch (Exception e6) {
                        obj4 = coroutine_suspended;
                        i6 = -1;
                        escapeResBase2 = escapeResBase3;
                        exc = e6;
                        networkUtil6 = networkUtil12;
                        obj5 = obj6;
                        mapStatus4 = mapStatus3;
                        function45 = function44;
                        z4 = z3;
                        str17 = str14;
                        str18 = str16;
                        i7 = i4;
                        str19 = str15;
                        str20 = str13;
                        str21 = str12;
                    }
                    m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
                    if (m4513exceptionOrNullimpl != null) {
                    }
                    return Unit.INSTANCE;
                    Integer boxInt222 = Boxing.boxInt(i6);
                    Boolean boxBoolean222 = Boxing.boxBoolean(false);
                    String message222 = exc.getMessage();
                    networkUtil$checkEscape$1.L$0 = networkUtil6;
                    networkUtil$checkEscape$1.L$1 = str19;
                    networkUtil$checkEscape$1.L$2 = str21;
                    networkUtil$checkEscape$1.L$3 = str18;
                    networkUtil$checkEscape$1.Z$0 = z4;
                    networkUtil$checkEscape$1.L$4 = mapStatus4;
                    networkUtil$checkEscape$1.L$5 = str20;
                    networkUtil$checkEscape$1.I$0 = i7;
                    networkUtil$checkEscape$1.L$6 = str17;
                    networkUtil$checkEscape$1.L$7 = function45;
                    networkUtil$checkEscape$1.L$8 = objectRef2;
                    networkUtil$checkEscape$1.L$9 = obj5;
                    networkUtil$checkEscape$1.L$10 = escapeResBase2;
                    networkUtil$checkEscape$1.L$11 = exc;
                    networkUtil$checkEscape$1.label = 4;
                    networkUtil9 = networkUtil6;
                    invoke2 = function45.invoke(boxInt222, boxBoolean222, message222, networkUtil$checkEscape$1);
                    obj = obj4;
                    if (invoke2 == obj) {
                        return obj;
                    }
                    MapStatus mapStatus6222 = mapStatus4;
                    i5 = 1;
                    Pdlog.m3273d(TAG, "checkLockStatus Exception ", exc.getMessage());
                    obj2 = obj5;
                    str15 = str19;
                    i4 = i7;
                    networkUtil4 = networkUtil9;
                    str16 = str18;
                    str14 = str17;
                    z3 = z4;
                    function44 = function45;
                    mapStatus3 = mapStatus6222;
                    String str28222 = str20;
                    str12 = str21;
                    str13 = str28222;
                    m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
                    if (m4513exceptionOrNullimpl != null) {
                    }
                    return Unit.INSTANCE;
                }
                objectRef2 = objectRef;
                networkUtil3 = networkUtil;
                str12 = str7;
                z3 = z2;
                str13 = str9;
                str14 = str11;
                str15 = str6;
                str16 = str8;
                mapStatus3 = mapStatus2;
                i4 = i3;
                function44 = function43;
                m4510constructorimpl = Result.m4510constructorimpl((EscapeResBase) obj10);
                if (!Result.m4517isSuccessimpl(m4510constructorimpl)) {
                    EscapeResBase escapeResBase8 = (EscapeResBase) m4510constructorimpl;
                    try {
                    } catch (Exception e7) {
                        e = e7;
                        escapeResBase = escapeResBase8;
                        networkUtil5 = networkUtil3;
                        obj3 = m4510constructorimpl;
                        obj4 = coroutine_suspended;
                        mapStatus4 = mapStatus3;
                        i6 = -1;
                    }
                    if (escapeResBase8.getCode() == 0) {
                        try {
                            Integer boxInt4 = Boxing.boxInt(0);
                            obj4 = coroutine_suspended;
                            try {
                                Boolean boxBoolean4 = Boxing.boxBoolean(((EscapeResData) escapeResBase8.getResponseData()).getLock());
                                String message4 = escapeResBase8.getMessage();
                                networkUtil$checkEscape$1.L$0 = networkUtil3;
                                networkUtil$checkEscape$1.L$1 = str15;
                                networkUtil$checkEscape$1.L$2 = str12;
                                networkUtil$checkEscape$1.L$3 = str16;
                                networkUtil$checkEscape$1.Z$0 = z3;
                                networkUtil$checkEscape$1.L$4 = mapStatus3;
                                networkUtil$checkEscape$1.L$5 = str13;
                                networkUtil$checkEscape$1.I$0 = i4;
                                networkUtil$checkEscape$1.L$6 = str14;
                                networkUtil$checkEscape$1.L$7 = function44;
                                networkUtil$checkEscape$1.L$8 = objectRef2;
                                networkUtil$checkEscape$1.L$9 = m4510constructorimpl;
                                networkUtil$checkEscape$1.L$10 = escapeResBase8;
                                obj7 = m4510constructorimpl;
                                try {
                                    networkUtil$checkEscape$1.label = 2;
                                    invoke = function44.invoke(boxInt4, boxBoolean4, message4, networkUtil$checkEscape$1);
                                    coroutine_suspended = obj4;
                                } catch (Exception e8) {
                                    e = e8;
                                    escapeResBase2 = escapeResBase8;
                                    mapStatus4 = mapStatus3;
                                    i6 = -1;
                                    function45 = function44;
                                    z4 = z3;
                                    str17 = str14;
                                    str18 = str16;
                                    i7 = i4;
                                    str19 = str15;
                                    obj5 = obj7;
                                    NetworkUtil networkUtil13 = networkUtil3;
                                    exc = e;
                                    networkUtil6 = networkUtil13;
                                    String str31 = str12;
                                    str20 = str13;
                                    str21 = str31;
                                    Integer boxInt2222 = Boxing.boxInt(i6);
                                    Boolean boxBoolean2222 = Boxing.boxBoolean(false);
                                    String message2222 = exc.getMessage();
                                    networkUtil$checkEscape$1.L$0 = networkUtil6;
                                    networkUtil$checkEscape$1.L$1 = str19;
                                    networkUtil$checkEscape$1.L$2 = str21;
                                    networkUtil$checkEscape$1.L$3 = str18;
                                    networkUtil$checkEscape$1.Z$0 = z4;
                                    networkUtil$checkEscape$1.L$4 = mapStatus4;
                                    networkUtil$checkEscape$1.L$5 = str20;
                                    networkUtil$checkEscape$1.I$0 = i7;
                                    networkUtil$checkEscape$1.L$6 = str17;
                                    networkUtil$checkEscape$1.L$7 = function45;
                                    networkUtil$checkEscape$1.L$8 = objectRef2;
                                    networkUtil$checkEscape$1.L$9 = obj5;
                                    networkUtil$checkEscape$1.L$10 = escapeResBase2;
                                    networkUtil$checkEscape$1.L$11 = exc;
                                    networkUtil$checkEscape$1.label = 4;
                                    networkUtil9 = networkUtil6;
                                    invoke2 = function45.invoke(boxInt2222, boxBoolean2222, message2222, networkUtil$checkEscape$1);
                                    obj = obj4;
                                    if (invoke2 == obj) {
                                    }
                                    MapStatus mapStatus62222 = mapStatus4;
                                    i5 = 1;
                                    Pdlog.m3273d(TAG, "checkLockStatus Exception ", exc.getMessage());
                                    obj2 = obj5;
                                    str15 = str19;
                                    i4 = i7;
                                    networkUtil4 = networkUtil9;
                                    str16 = str18;
                                    str14 = str17;
                                    z3 = z4;
                                    function44 = function45;
                                    mapStatus3 = mapStatus62222;
                                    String str282222 = str20;
                                    str12 = str21;
                                    str13 = str282222;
                                    m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
                                    if (m4513exceptionOrNullimpl != null) {
                                    }
                                    return Unit.INSTANCE;
                                }
                            } catch (Exception e9) {
                                e = e9;
                                obj7 = m4510constructorimpl;
                            }
                        } catch (Exception e10) {
                            e = e10;
                            obj7 = m4510constructorimpl;
                            obj4 = coroutine_suspended;
                            mapStatus4 = mapStatus3;
                            i6 = -1;
                            escapeResBase2 = escapeResBase8;
                        }
                        if (invoke == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        objectRef3 = objectRef2;
                        function46 = function44;
                        str22 = str14;
                        i8 = i4;
                        str23 = str13;
                        mapStatus5 = mapStatus3;
                        obj8 = obj7;
                        NetworkUtil networkUtil14 = networkUtil3;
                        escapeResBase4 = escapeResBase8;
                        networkUtil7 = networkUtil14;
                        String str32 = str12;
                        z5 = z3;
                        str24 = str16;
                        str25 = str32;
                        networkUtil8 = networkUtil7;
                        objectRef4 = objectRef3;
                        Pdlog.m3273d(TAG, "checkLockStatus code is 0", escapeResBase4.getResponseData());
                        networkUtil4 = networkUtil8;
                        obj = coroutine_suspended;
                        obj2 = obj8;
                        i5 = 1;
                        mapStatus3 = mapStatus5;
                        str13 = str23;
                        i4 = i8;
                        str14 = str22;
                        function44 = function46;
                        objectRef2 = objectRef4;
                        String str272 = str25;
                        str16 = str24;
                        z3 = z5;
                        str12 = str272;
                        m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
                        if (m4513exceptionOrNullimpl != null) {
                        }
                        return Unit.INSTANCE;
                    }
                    Object obj14 = m4510constructorimpl;
                    try {
                        boxInt = Boxing.boxInt(-1);
                        obj4 = coroutine_suspended;
                        try {
                            boxBoolean = Boxing.boxBoolean(false);
                            message = escapeResBase8.getMessage();
                            networkUtil$checkEscape$1.L$0 = networkUtil3;
                            networkUtil$checkEscape$1.L$1 = str15;
                            networkUtil$checkEscape$1.L$2 = str12;
                            networkUtil$checkEscape$1.L$3 = str16;
                            networkUtil$checkEscape$1.Z$0 = z3;
                            networkUtil$checkEscape$1.L$4 = mapStatus3;
                            networkUtil$checkEscape$1.L$5 = str13;
                            networkUtil$checkEscape$1.I$0 = i4;
                            networkUtil$checkEscape$1.L$6 = str14;
                            networkUtil$checkEscape$1.L$7 = function44;
                            networkUtil$checkEscape$1.L$8 = objectRef2;
                            networkUtil5 = networkUtil3;
                            obj3 = obj14;
                            try {
                                networkUtil$checkEscape$1.L$9 = obj3;
                                networkUtil$checkEscape$1.L$10 = escapeResBase8;
                                escapeResBase3 = escapeResBase8;
                                try {
                                    networkUtil$checkEscape$1.label = 3;
                                    obj = obj4;
                                } catch (Exception e11) {
                                    e = e11;
                                    escapeResBase2 = escapeResBase3;
                                    mapStatus4 = mapStatus3;
                                    i6 = -1;
                                    function45 = function44;
                                    z4 = z3;
                                    str17 = str14;
                                    str18 = str16;
                                    i7 = i4;
                                    str19 = str15;
                                    obj5 = obj3;
                                    exc = e;
                                    networkUtil6 = networkUtil5;
                                    String str302 = str12;
                                    str20 = str13;
                                    str21 = str302;
                                    Integer boxInt22222 = Boxing.boxInt(i6);
                                    Boolean boxBoolean22222 = Boxing.boxBoolean(false);
                                    String message22222 = exc.getMessage();
                                    networkUtil$checkEscape$1.L$0 = networkUtil6;
                                    networkUtil$checkEscape$1.L$1 = str19;
                                    networkUtil$checkEscape$1.L$2 = str21;
                                    networkUtil$checkEscape$1.L$3 = str18;
                                    networkUtil$checkEscape$1.Z$0 = z4;
                                    networkUtil$checkEscape$1.L$4 = mapStatus4;
                                    networkUtil$checkEscape$1.L$5 = str20;
                                    networkUtil$checkEscape$1.I$0 = i7;
                                    networkUtil$checkEscape$1.L$6 = str17;
                                    networkUtil$checkEscape$1.L$7 = function45;
                                    networkUtil$checkEscape$1.L$8 = objectRef2;
                                    networkUtil$checkEscape$1.L$9 = obj5;
                                    networkUtil$checkEscape$1.L$10 = escapeResBase2;
                                    networkUtil$checkEscape$1.L$11 = exc;
                                    networkUtil$checkEscape$1.label = 4;
                                    networkUtil9 = networkUtil6;
                                    invoke2 = function45.invoke(boxInt22222, boxBoolean22222, message22222, networkUtil$checkEscape$1);
                                    obj = obj4;
                                    if (invoke2 == obj) {
                                    }
                                    MapStatus mapStatus622222 = mapStatus4;
                                    i5 = 1;
                                    Pdlog.m3273d(TAG, "checkLockStatus Exception ", exc.getMessage());
                                    obj2 = obj5;
                                    str15 = str19;
                                    i4 = i7;
                                    networkUtil4 = networkUtil9;
                                    str16 = str18;
                                    str14 = str17;
                                    z3 = z4;
                                    function44 = function45;
                                    mapStatus3 = mapStatus622222;
                                    String str2822222 = str20;
                                    str12 = str21;
                                    str13 = str2822222;
                                    m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
                                    if (m4513exceptionOrNullimpl != null) {
                                    }
                                    return Unit.INSTANCE;
                                }
                            } catch (Exception e12) {
                                e = e12;
                                escapeResBase3 = escapeResBase8;
                            }
                        } catch (Exception e13) {
                            e = e13;
                            escapeResBase3 = escapeResBase8;
                            networkUtil5 = networkUtil3;
                            obj3 = obj14;
                        }
                    } catch (Exception e14) {
                        e = e14;
                        escapeResBase = escapeResBase8;
                        networkUtil5 = networkUtil3;
                        obj3 = obj14;
                        obj4 = coroutine_suspended;
                        i6 = -1;
                        mapStatus4 = mapStatus3;
                        escapeResBase2 = escapeResBase;
                        function45 = function44;
                        z4 = z3;
                        str17 = str14;
                        str18 = str16;
                        i7 = i4;
                        str19 = str15;
                        obj5 = obj3;
                        exc = e;
                        networkUtil6 = networkUtil5;
                        String str3022 = str12;
                        str20 = str13;
                        str21 = str3022;
                        Integer boxInt222222 = Boxing.boxInt(i6);
                        Boolean boxBoolean222222 = Boxing.boxBoolean(false);
                        String message222222 = exc.getMessage();
                        networkUtil$checkEscape$1.L$0 = networkUtil6;
                        networkUtil$checkEscape$1.L$1 = str19;
                        networkUtil$checkEscape$1.L$2 = str21;
                        networkUtil$checkEscape$1.L$3 = str18;
                        networkUtil$checkEscape$1.Z$0 = z4;
                        networkUtil$checkEscape$1.L$4 = mapStatus4;
                        networkUtil$checkEscape$1.L$5 = str20;
                        networkUtil$checkEscape$1.I$0 = i7;
                        networkUtil$checkEscape$1.L$6 = str17;
                        networkUtil$checkEscape$1.L$7 = function45;
                        networkUtil$checkEscape$1.L$8 = objectRef2;
                        networkUtil$checkEscape$1.L$9 = obj5;
                        networkUtil$checkEscape$1.L$10 = escapeResBase2;
                        networkUtil$checkEscape$1.L$11 = exc;
                        networkUtil$checkEscape$1.label = 4;
                        networkUtil9 = networkUtil6;
                        invoke2 = function45.invoke(boxInt222222, boxBoolean222222, message222222, networkUtil$checkEscape$1);
                        obj = obj4;
                        if (invoke2 == obj) {
                        }
                        MapStatus mapStatus6222222 = mapStatus4;
                        i5 = 1;
                        Pdlog.m3273d(TAG, "checkLockStatus Exception ", exc.getMessage());
                        obj2 = obj5;
                        str15 = str19;
                        i4 = i7;
                        networkUtil4 = networkUtil9;
                        str16 = str18;
                        str14 = str17;
                        z3 = z4;
                        function44 = function45;
                        mapStatus3 = mapStatus6222222;
                        String str28222222 = str20;
                        str12 = str21;
                        str13 = str28222222;
                        m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
                        if (m4513exceptionOrNullimpl != null) {
                        }
                        return Unit.INSTANCE;
                    }
                    if (function44.invoke(boxInt, boxBoolean, message, networkUtil$checkEscape$1) == obj) {
                        return obj;
                    }
                    obj6 = obj3;
                    escapeResBase5 = escapeResBase3;
                    obj9 = obj6;
                    Pdlog.m3273d(TAG, "checkLockStatus code is -1", escapeResBase5.getMessage());
                    obj2 = obj9;
                    networkUtil4 = networkUtil5;
                    i5 = 1;
                    m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
                    if (m4513exceptionOrNullimpl != null) {
                    }
                    return Unit.INSTANCE;
                }
                obj = coroutine_suspended;
                NetworkUtil networkUtil15 = networkUtil3;
                Object obj15 = m4510constructorimpl;
                i5 = 1;
                obj2 = obj15;
                networkUtil4 = networkUtil15;
                m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
                if (m4513exceptionOrNullimpl != null) {
                }
                return Unit.INSTANCE;
            }
        }
        networkUtil$checkEscape$1 = new NetworkUtil$checkEscape$1(this, continuation);
        Object obj102 = networkUtil$checkEscape$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = networkUtil$checkEscape$1.label;
        if (i2 != 0) {
        }
        objectRef2 = objectRef;
        networkUtil3 = networkUtil;
        str12 = str7;
        z3 = z2;
        str13 = str9;
        str14 = str11;
        str15 = str6;
        str16 = str8;
        mapStatus3 = mapStatus2;
        i4 = i3;
        function44 = function43;
        m4510constructorimpl = Result.m4510constructorimpl((EscapeResBase) obj102);
        if (!Result.m4517isSuccessimpl(m4510constructorimpl)) {
        }
    }
}
