package com.pudutech.freeinstall_ui.viewmodel;

import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.utils.Utils;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.TopoTrack;
import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.bean.Line;
import com.pudutech.opengl_draw.geometry.Vector3;
import java.util.ArrayList;
import java.util.List;
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
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AddVirtualViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.AddVirtualViewModel$getTopoMap$1", m3970f = "AddVirtualViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
final class AddVirtualViewModel$getTopoMap$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5261p$;
    final /* synthetic */ AddVirtualViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddVirtualViewModel$getTopoMap$1(AddVirtualViewModel addVirtualViewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = addVirtualViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AddVirtualViewModel$getTopoMap$1 addVirtualViewModel$getTopoMap$1 = new AddVirtualViewModel$getTopoMap$1(this.this$0, completion);
        addVirtualViewModel$getTopoMap$1.f5261p$ = (CoroutineScope) obj;
        return addVirtualViewModel$getTopoMap$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AddVirtualViewModel$getTopoMap$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5261p$;
            List<TopoTrack> topPath = LocateMappingManager.INSTANCE.getTopPath();
            String tag = this.this$0.getTAG();
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("getTopoMap ");
            sb.append(topPath != null ? Boxing.boxInt(topPath.size()) : null);
            objArr[0] = sb.toString();
            Pdlog.m3273d(tag, objArr);
            if (topPath == null || topPath.isEmpty()) {
                return Unit.INSTANCE;
            }
            ArrayList arrayList = new ArrayList();
            for (TopoTrack topoTrack : topPath) {
                if (topoTrack.getStart_pose() != null && topoTrack.getEnd_pose() != null) {
                    ArrayList arrayList2 = new ArrayList();
                    Utils.Companion companion = Utils.INSTANCE;
                    Vector3d start_pose = topoTrack.getStart_pose();
                    if (start_pose == null) {
                        Intrinsics.throwNpe();
                    }
                    Vector3 vector3dToVector3 = companion.vector3dToVector3(start_pose);
                    if (vector3dToVector3 == null) {
                        Intrinsics.throwNpe();
                    }
                    arrayList2.add(vector3dToVector3);
                    Utils.Companion companion2 = Utils.INSTANCE;
                    Vector3d end_pose = topoTrack.getEnd_pose();
                    if (end_pose == null) {
                        Intrinsics.throwNpe();
                    }
                    Vector3 vector3dToVector32 = companion2.vector3dToVector3(end_pose);
                    if (vector3dToVector32 == null) {
                        Intrinsics.throwNpe();
                    }
                    arrayList2.add(vector3dToVector32);
                    Line line = new Line(arrayList2, "");
                    line.setColor(Color.fromHexAndAlpha("FFFFFF", 1.0f));
                    arrayList.add(line);
                }
            }
            Pdlog.m3273d(this.this$0.getTAG(), "topPath " + arrayList.size());
            this.this$0.getTopoMapLiveData().postValue(arrayList);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
