package com.pudutech.disklru;

import com.jakewharton.disklrucache.DiskLruCache;
import com.pudutech.base.Pdlog;
import java.io.File;
import java.io.FileInputStream;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DiskLruCacheManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.disklru.DiskLruCacheManager$saveDiskCache$2", m3970f = "DiskLruCacheManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
public final class DiskLruCacheManager$saveDiskCache$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ File $file;
    final /* synthetic */ String $key;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5072p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DiskLruCacheManager$saveDiskCache$2(String str, File file, Continuation continuation) {
        super(2, continuation);
        this.$key = str;
        this.$file = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DiskLruCacheManager$saveDiskCache$2 diskLruCacheManager$saveDiskCache$2 = new DiskLruCacheManager$saveDiskCache$2(this.$key, this.$file, completion);
        diskLruCacheManager$saveDiskCache$2.f5072p$ = (CoroutineScope) obj;
        return diskLruCacheManager$saveDiskCache$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DiskLruCacheManager$saveDiskCache$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0071, code lost:
    
        r0.write(r3, 0, r4.element);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x009f, code lost:
    
        if (r0 != null) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a4, code lost:
    
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0090, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x008e, code lost:
    
        if (r0 != null) goto L36;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5072p$;
            OutputStream outputStream = (OutputStream) null;
            FileInputStream fileInputStream = (FileInputStream) null;
            try {
                try {
                    DiskLruCache mDiskCache = DiskLruCacheManager.INSTANCE.getMDiskCache();
                    DiskLruCache.Editor edit = mDiskCache != null ? mDiskCache.edit(this.$key) : null;
                    Pdlog.m3273d("TtsDiskLruCacheManager", "saveDiskCache--edit" + edit);
                    if (edit != null) {
                        outputStream = edit.newOutputStream(0);
                        FileInputStream fileInputStream2 = new FileInputStream(this.$file);
                        try {
                            byte[] bArr = new byte[8192];
                            Ref.IntRef intRef = new Ref.IntRef();
                            while (true) {
                                Integer boxInt = Boxing.boxInt(fileInputStream2.read(bArr, 0, 8192));
                                intRef.element = boxInt != null ? boxInt.intValue() : 0;
                                if (boxInt != null && boxInt.intValue() == -1) {
                                    break;
                                }
                            }
                            fileInputStream = fileInputStream2;
                        } catch (Exception e) {
                            e = e;
                            fileInputStream = fileInputStream2;
                            e.printStackTrace();
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            throw th;
                        }
                    }
                    if (edit != null) {
                        edit.commit();
                    }
                    DiskLruCacheManager.INSTANCE.flush();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
