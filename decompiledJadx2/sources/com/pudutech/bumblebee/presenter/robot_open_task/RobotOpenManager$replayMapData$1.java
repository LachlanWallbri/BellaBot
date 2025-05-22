package com.pudutech.bumblebee.presenter.robot_open_task;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.utils.FileUtil;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.robot.opensdk.MsgContext;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.bean.resp.RespRobotMapBody;
import java.io.FileInputStream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RobotOpenManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$replayMapData$1", m3970f = "RobotOpenManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
public final class RobotOpenManager$replayMapData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MsgContext $msgContext;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4709p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotOpenManager$replayMapData$1(MsgContext msgContext, Continuation continuation) {
        super(2, continuation);
        this.$msgContext = msgContext;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotOpenManager$replayMapData$1 robotOpenManager$replayMapData$1 = new RobotOpenManager$replayMapData$1(this.$msgContext, completion);
        robotOpenManager$replayMapData$1.f4709p$ = (CoroutineScope) obj;
        return robotOpenManager$replayMapData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotOpenManager$replayMapData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4709p$;
        try {
            String currentMapAtlasPath = RobotMapManager.INSTANCE.getCurrentMapAtlasPath();
            RobotOpenManager robotOpenManager = RobotOpenManager.INSTANCE;
            str2 = RobotOpenManager.TAG;
            Pdlog.m3273d(str2, "replayMapData : path = " + currentMapAtlasPath + "; ");
            FileInputStream fileInputStream = new FileInputStream(currentMapAtlasPath);
            byte[] bArr = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while (true) {
                Integer boxInt = Boxing.boxInt(fileInputStream.read(bArr));
                int intValue = boxInt.intValue();
                if (boxInt.intValue() == -1) {
                    break;
                }
                sb.append(new String(bArr, 0, intValue, Charsets.UTF_8));
            }
            String compress = FileUtil.INSTANCE.compress(sb.toString());
            RobotOpenSdk robotOpenSdk = RobotOpenSdk.INSTANCE;
            MsgContext msgContext = this.$msgContext;
            if (compress == null) {
                compress = "";
            }
            RobotOpenSdk.responseMsg$default(robotOpenSdk, msgContext, new RespRobotMapBody(compress), null, 4, null);
        } catch (Exception e) {
            RobotOpenManager robotOpenManager2 = RobotOpenManager.INSTANCE;
            str = RobotOpenManager.TAG;
            Pdlog.m3274e(str, "replayMapData : " + Log.getStackTraceString(e));
            RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, this.$msgContext, new RespRobotMapBody(""), null, 4, null);
        }
        return Unit.INSTANCE;
    }
}
