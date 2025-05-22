package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.widget.ImageView;
import androidx.core.content.FileProvider;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* compiled from: SleepSettingFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.module.setting.ui.SleepSettingFragment$previewVideo$job$1", m3970f = "SleepSettingFragment.kt", m3971i = {0, 0, 0, 0}, m3972l = {148}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "retriever", "bm", "it"}, m3975s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes3.dex */
final class SleepSettingFragment$previewVideo$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $path;
    final /* synthetic */ ImageView $target;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4885p$;
    final /* synthetic */ SleepSettingFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SleepSettingFragment$previewVideo$job$1(SleepSettingFragment sleepSettingFragment, String str, ImageView imageView, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sleepSettingFragment;
        this.$path = str;
        this.$target = imageView;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SleepSettingFragment$previewVideo$job$1 sleepSettingFragment$previewVideo$job$1 = new SleepSettingFragment$previewVideo$job$1(this.this$0, this.$path, this.$target, completion);
        sleepSettingFragment$previewVideo$job$1.f4885p$ = (CoroutineScope) obj;
        return sleepSettingFragment$previewVideo$job$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SleepSettingFragment$previewVideo$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v5, types: [T, android.graphics.Bitmap] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4885p$;
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            if (SceneAnimationResources.INSTANCE.isCustom()) {
                mediaMetadataRetriever.setDataSource(this.this$0.requireContext(), FileProvider.getUriForFile(this.this$0.requireContext(), "com.pudutech.bumblebee.FileProvider", new File(this.$path)));
            } else {
                Context requireContext = this.this$0.requireContext();
                Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
                AssetFileDescriptor afd = requireContext.getAssets().openFd(this.$path);
                Intrinsics.checkExpressionValueIsNotNull(afd, "afd");
                mediaMetadataRetriever.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            }
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = mediaMetadataRetriever.getFrameAtTime(1000000L, 2);
            Bitmap bitmap = (Bitmap) objectRef.element;
            if (bitmap != null) {
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C4255xba5a59ca c4255xba5a59ca = new C4255xba5a59ca(null, this, objectRef);
                this.L$0 = coroutineScope;
                this.L$1 = mediaMetadataRetriever;
                this.L$2 = objectRef;
                this.L$3 = bitmap;
                this.label = 1;
                obj = BuildersKt.withContext(main, c4255xba5a59ca, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return Unit.INSTANCE;
    }
}
