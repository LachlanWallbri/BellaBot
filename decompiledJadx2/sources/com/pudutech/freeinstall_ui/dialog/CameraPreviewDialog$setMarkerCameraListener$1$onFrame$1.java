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
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.dialog.CameraPreviewDialog$setMarkerCameraListener$1$onFrame$1", m3970f = "CameraPreviewDialog.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes3.dex */
public final class CameraPreviewDialog$setMarkerCameraListener$1$onFrame$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ParcelFileDescriptor $p0;
    final /* synthetic */ int $p1;
    final /* synthetic */ int $p2;
    final /* synthetic */ int $p3;
    final /* synthetic */ int $p4;
    final /* synthetic */ long $p5;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5237p$;
    final /* synthetic */ CameraPreviewDialog$setMarkerCameraListener$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraPreviewDialog$setMarkerCameraListener$1$onFrame$1(CameraPreviewDialog$setMarkerCameraListener$1 cameraPreviewDialog$setMarkerCameraListener$1, ParcelFileDescriptor parcelFileDescriptor, int i, int i2, int i3, int i4, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cameraPreviewDialog$setMarkerCameraListener$1;
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
        CameraPreviewDialog$setMarkerCameraListener$1$onFrame$1 cameraPreviewDialog$setMarkerCameraListener$1$onFrame$1 = new CameraPreviewDialog$setMarkerCameraListener$1$onFrame$1(this.this$0, this.$p0, this.$p1, this.$p2, this.$p3, this.$p4, this.$p5, completion);
        cameraPreviewDialog$setMarkerCameraListener$1$onFrame$1.f5237p$ = (CoroutineScope) obj;
        return cameraPreviewDialog$setMarkerCameraListener$1$onFrame$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraPreviewDialog$setMarkerCameraListener$1$onFrame$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5237p$;
            Bitmap bitMapFromMarker = Utils.INSTANCE.getBitMapFromMarker(this.$p0, this.$p1, this.$p2, this.$p3, this.$p4, this.$p5);
            if (bitMapFromMarker != null) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C45741(bitMapFromMarker, null), 2, null);
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CameraPreviewDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.dialog.CameraPreviewDialog$setMarkerCameraListener$1$onFrame$1$1", m3970f = "CameraPreviewDialog.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.freeinstall_ui.dialog.CameraPreviewDialog$setMarkerCameraListener$1$onFrame$1$1 */
    /* loaded from: classes3.dex */
    public static final class C45741 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Bitmap $bitMapFromMarker;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5238p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C45741(Bitmap bitmap, Continuation continuation) {
            super(2, continuation);
            this.$bitMapFromMarker = bitmap;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C45741 c45741 = new C45741(this.$bitMapFromMarker, completion);
            c45741.f5238p$ = (CoroutineScope) obj;
            return c45741;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C45741) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5238p$;
            if (((ImageView) CameraPreviewDialog$setMarkerCameraListener$1$onFrame$1.this.this$0.this$0._$_findCachedViewById(C5362R.id.iv_camera_pic)) != null) {
                ((ImageView) CameraPreviewDialog$setMarkerCameraListener$1$onFrame$1.this.this$0.this$0._$_findCachedViewById(C5362R.id.iv_camera_pic)).setImageBitmap(this.$bitMapFromMarker);
            }
            return Unit.INSTANCE;
        }
    }
}
