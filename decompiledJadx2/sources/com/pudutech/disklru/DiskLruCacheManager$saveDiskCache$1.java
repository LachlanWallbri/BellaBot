package com.pudutech.disklru;

import com.jakewharton.disklrucache.DiskLruCache;
import com.pudutech.base.Pdlog;
import java.io.InputStream;
import java.io.OutputStream;
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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: DiskLruCacheManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.disklru.DiskLruCacheManager$saveDiskCache$1", m3970f = "DiskLruCacheManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
final class DiskLruCacheManager$saveDiskCache$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ InputStream $inputStream;
    final /* synthetic */ String $key;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5071p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DiskLruCacheManager$saveDiskCache$1(String str, InputStream inputStream, Continuation continuation) {
        super(2, continuation);
        this.$key = str;
        this.$inputStream = inputStream;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DiskLruCacheManager$saveDiskCache$1 diskLruCacheManager$saveDiskCache$1 = new DiskLruCacheManager$saveDiskCache$1(this.$key, this.$inputStream, completion);
        diskLruCacheManager$saveDiskCache$1.f5071p$ = (CoroutineScope) obj;
        return diskLruCacheManager$saveDiskCache$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DiskLruCacheManager$saveDiskCache$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0098, code lost:
    
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0093, code lost:
    
        if (r0 == null) goto L30;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5071p$;
            OutputStream outputStream = (OutputStream) null;
            try {
                try {
                    DiskLruCache mDiskCache = DiskLruCacheManager.INSTANCE.getMDiskCache();
                    DiskLruCache.Editor edit = mDiskCache != null ? mDiskCache.edit(this.$key) : null;
                    Pdlog.m3273d("TtsDiskLruCacheManager", "saveDiskCache--edit" + edit);
                    if (edit != null) {
                        outputStream = edit.newOutputStream(0);
                        byte[] bArr = new byte[8192];
                        Ref.IntRef intRef = new Ref.IntRef();
                        while (true) {
                            Integer boxInt = Boxing.boxInt(this.$inputStream.read(bArr, 0, 8192));
                            intRef.element = boxInt.intValue();
                            if (boxInt.intValue() == -1) {
                                break;
                            }
                            if (outputStream != null) {
                                outputStream.write(bArr, 0, intRef.element);
                            }
                        }
                    }
                    if (edit != null) {
                        edit.commit();
                    }
                    DiskLruCache mDiskCache2 = DiskLruCacheManager.INSTANCE.getMDiskCache();
                    if (mDiskCache2 != null) {
                        mDiskCache2.flush();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.$inputStream.close();
                }
            } finally {
                this.$inputStream.close();
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
