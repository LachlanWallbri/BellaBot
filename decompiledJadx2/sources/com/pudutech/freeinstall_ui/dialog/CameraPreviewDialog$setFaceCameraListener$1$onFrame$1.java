package com.pudutech.freeinstall_ui.dialog;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;
import com.pudutech.freeinstall_ui.utils.Utils;
import com.pudutech.module_freeinstall_ui.C5362R;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CameraPreviewDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.dialog.CameraPreviewDialog$setFaceCameraListener$1$onFrame$1", m3970f = "CameraPreviewDialog.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes3.dex */
public final class CameraPreviewDialog$setFaceCameraListener$1$onFrame$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ParcelFileDescriptor $p0;
    final /* synthetic */ int $p1;
    final /* synthetic */ int $p2;
    final /* synthetic */ int $p3;
    final /* synthetic */ int $p4;
    final /* synthetic */ long $p5;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5235p$;
    final /* synthetic */ CameraPreviewDialog$setFaceCameraListener$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraPreviewDialog$setFaceCameraListener$1$onFrame$1(CameraPreviewDialog$setFaceCameraListener$1 cameraPreviewDialog$setFaceCameraListener$1, ParcelFileDescriptor parcelFileDescriptor, int i, int i2, int i3, int i4, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cameraPreviewDialog$setFaceCameraListener$1;
        this.$p0 = parcelFileDescriptor;
        this.$p1 = i;
        this.$p2 = i2;
        this.$p3 = i3;
        this.$p4 = i4;
        this.$p5 = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CameraPreviewDialog$setFaceCameraListener$1$onFrame$1 cameraPreviewDialog$setFaceCameraListener$1$onFrame$1 = new CameraPreviewDialog$setFaceCameraListener$1$onFrame$1(this.this$0, this.$p0, this.$p1, this.$p2, this.$p3, this.$p4, this.$p5, completion);
        cameraPreviewDialog$setFaceCameraListener$1$onFrame$1.f5235p$ = (CoroutineScope) obj;
        return cameraPreviewDialog$setFaceCameraListener$1$onFrame$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraPreviewDialog$setFaceCameraListener$1$onFrame$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5235p$;
            Bitmap bitMapFromFace = Utils.INSTANCE.getBitMapFromFace(this.$p0, this.$p1, this.$p2, this.$p3, this.$p4, this.$p5);
            if (bitMapFromFace != null) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C45731(bitMapFromFace, null), 2, null);
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CameraPreviewDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.dialog.CameraPreviewDialog$setFaceCameraListener$1$onFrame$1$1", m3970f = "CameraPreviewDialog.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.freeinstall_ui.dialog.CameraPreviewDialog$setFaceCameraListener$1$onFrame$1$1 */
    /* loaded from: classes3.dex */
    public static final class C45731 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Bitmap $bitMapFromFace;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5236p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C45731(Bitmap bitmap, Continuation continuation) {
            super(2, continuation);
            this.$bitMapFromFace = bitmap;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C45731 c45731 = new C45731(this.$bitMapFromFace, completion);
            c45731.f5236p$ = (CoroutineScope) obj;
            return c45731;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C45731) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5236p$;
            if (((ImageView) CameraPreviewDialog$setFaceCameraListener$1$onFrame$1.this.this$0.this$0._$_findCachedViewById(C5362R.id.iv_camera_pic)) != null) {
                ((ImageView) CameraPreviewDialog$setFaceCameraListener$1$onFrame$1.this.this$0.this$0._$_findCachedViewById(C5362R.id.iv_camera_pic)).setImageBitmap(this.$bitMapFromFace);
            }
            return Unit.INSTANCE;
        }
    }
}
