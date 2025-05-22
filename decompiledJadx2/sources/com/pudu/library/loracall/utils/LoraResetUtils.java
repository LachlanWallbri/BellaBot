package com.pudu.library.loracall.utils;

import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.library.loracall.bean.LoRaLevel;
import com.pudutech.base.Tools;
import java.io.File;
import java.io.FileWriter;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.DelayKt;

/* compiled from: LoraResetUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\"\u00020\u0007H\u0002¢\u0006\u0002\u0010\bJ\u0019\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0007H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, m3961d2 = {"Lcom/pudu/library/loracall/utils/LoraResetUtils;", "", "()V", "initChmod", "", "pathNames", "", "", "([Ljava/lang/String;)V", "loraReset", "pathName", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setupLevel", "", "level", "Lcom/pudu/library/loracall/bean/LoRaLevel;", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoraResetUtils {
    public static final LoraResetUtils INSTANCE = new LoraResetUtils();

    private LoraResetUtils() {
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object loraReset(String str, Continuation<? super Unit> continuation) {
        LoraResetUtils$loraReset$1 loraResetUtils$loraReset$1;
        int i;
        final boolean z;
        LoraResetUtils loraResetUtils;
        if (continuation instanceof LoraResetUtils$loraReset$1) {
            loraResetUtils$loraReset$1 = (LoraResetUtils$loraReset$1) continuation;
            if ((loraResetUtils$loraReset$1.label & Integer.MIN_VALUE) != 0) {
                loraResetUtils$loraReset$1.label -= Integer.MIN_VALUE;
                Object obj = loraResetUtils$loraReset$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = loraResetUtils$loraReset$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    initChmod(str);
                    z = setupLevel(str, LoRaLevel.High);
                    loraResetUtils$loraReset$1.L$0 = this;
                    loraResetUtils$loraReset$1.L$1 = str;
                    loraResetUtils$loraReset$1.Z$0 = z;
                    loraResetUtils$loraReset$1.label = 1;
                    if (DelayKt.delay(500L, loraResetUtils$loraReset$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    loraResetUtils = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    boolean z2 = loraResetUtils$loraReset$1.Z$0;
                    String str2 = (String) loraResetUtils$loraReset$1.L$1;
                    loraResetUtils = (LoraResetUtils) loraResetUtils$loraReset$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    z = z2;
                    str = str2;
                }
                final boolean z3 = loraResetUtils.setupLevel(str, LoRaLevel.Low);
                KotlinUtilsKt.log$default(loraResetUtils, null, new Function0<String>() { // from class: com.pudu.library.loracall.utils.LoraResetUtils$loraReset$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "reset复位：" + z + "---" + z3;
                    }
                }, 1, null);
                return Unit.INSTANCE;
            }
        }
        loraResetUtils$loraReset$1 = new LoraResetUtils$loraReset$1(this, continuation);
        Object obj2 = loraResetUtils$loraReset$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = loraResetUtils$loraReset$1.label;
        if (i != 0) {
        }
        final boolean z32 = loraResetUtils.setupLevel(str, LoRaLevel.Low);
        KotlinUtilsKt.log$default(loraResetUtils, null, new Function0<String>() { // from class: com.pudu.library.loracall.utils.LoraResetUtils$loraReset$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "reset复位：" + z + "---" + z32;
            }
        }, 1, null);
        return Unit.INSTANCE;
    }

    private final boolean setupLevel(String pathName, LoRaLevel level) {
        boolean z;
        FileWriter fileWriter = (FileWriter) null;
        try {
            try {
                FileWriter fileWriter2 = new FileWriter(new File(pathName));
                try {
                    fileWriter2.write(level.getValue());
                    fileWriter2.flush();
                    z = true;
                    fileWriter2.close();
                } catch (Exception e) {
                    e = e;
                    fileWriter = fileWriter2;
                    e.printStackTrace();
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                    z = false;
                    return z;
                } catch (Throwable th) {
                    th = th;
                    fileWriter = fileWriter2;
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
            return z;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private final void initChmod(String... pathNames) {
        for (String str : pathNames) {
            Tools.execCommand("chmod 777 " + str, true);
        }
    }
}
