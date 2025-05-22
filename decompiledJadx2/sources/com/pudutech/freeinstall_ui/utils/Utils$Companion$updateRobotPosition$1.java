package com.pudutech.freeinstall_ui.utils;

import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableKt;
import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.module_freeinstall_ui.C5362R;
import com.pudutech.opengl_draw.geometry.Quaternion;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.layer.RobotLayer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Utils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.utils.Utils$Companion$updateRobotPosition$1", m3970f = "Utils.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
public final class Utils$Companion$updateRobotPosition$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Vector3d $p0;
    final /* synthetic */ RobotLayer $robotLayer;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5248p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Utils$Companion$updateRobotPosition$1(Vector3d vector3d, RobotLayer robotLayer, Continuation continuation) {
        super(2, continuation);
        this.$p0 = vector3d;
        this.$robotLayer = robotLayer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        Utils$Companion$updateRobotPosition$1 utils$Companion$updateRobotPosition$1 = new Utils$Companion$updateRobotPosition$1(this.$p0, this.$robotLayer, completion);
        utils$Companion$updateRobotPosition$1.f5248p$ = (CoroutineScope) obj;
        return utils$Companion$updateRobotPosition$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((Utils$Companion$updateRobotPosition$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5248p$;
        Transform transform = new Transform(Utils.INSTANCE.vector3dToVector3(new Vector3d(this.$p0.getX(), this.$p0.getY(), 0.0d, 4, null)), new Quaternion(new double[]{0.0d, 0.0d, (-this.$p0.getZ()) + 1.5707963267948966d}));
        RobotLayer robotLayer = this.$robotLayer;
        if (robotLayer != null) {
            Drawable drawable = AppContext.INSTANCE.getContext().getDrawable(C5362R.drawable.robot);
            robotLayer.update(transform, drawable != null ? DrawableKt.toBitmap$default(drawable, 0, 0, null, 7, null) : null);
        }
        return Unit.INSTANCE;
    }
}
