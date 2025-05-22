package com.pudutech.tts_sdk.tts;

import android.os.Handler;
import androidx.core.app.NotificationCompat;
import com.pudutech.disklru.DiskLruCacheManager;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: IflyTtsTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\bH\u0016¨\u0006\f"}, m3961d2 = {"com/pudutech/tts_sdk/tts/IflyTtsTask$genTtsFile$1", "Lcom/pudutech/tts_sdk/tts/OnTtsListener;", "onComplete", "", "filePath", "", "onError", "code", "", NotificationCompat.CATEGORY_MESSAGE, "onProgress", "proses", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class IflyTtsTask$genTtsFile$1 implements OnTtsListener {
    final /* synthetic */ String $extraParam;
    final /* synthetic */ OnTtsListener $onTtsListener;
    final /* synthetic */ String $text;
    final /* synthetic */ IflyTtsTask this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IflyTtsTask$genTtsFile$1(IflyTtsTask iflyTtsTask, OnTtsListener onTtsListener, String str, String str2) {
        this.this$0 = iflyTtsTask;
        this.$onTtsListener = onTtsListener;
        this.$text = str;
        this.$extraParam = str2;
    }

    @Override // com.pudutech.tts_sdk.tts.OnTtsListener
    public void onProgress(int proses) {
        this.$onTtsListener.onProgress(proses);
    }

    @Override // com.pudutech.tts_sdk.tts.OnTtsListener
    public void onComplete(final String filePath) {
        Executor executor;
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        executor = this.this$0.executor;
        executor.execute(new Runnable() { // from class: com.pudutech.tts_sdk.tts.IflyTtsTask$genTtsFile$1$onComplete$1
            @Override // java.lang.Runnable
            public final void run() {
                Handler handler;
                Handler handler2;
                try {
                    handler2 = IflyTtsTask$genTtsFile$1.this.this$0.mainHandler;
                    handler2.post(new Runnable() { // from class: com.pudutech.tts_sdk.tts.IflyTtsTask$genTtsFile$1$onComplete$1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            DiskLruCacheManager.INSTANCE.saveDiskCache(filePath, DiskLruCacheManager.INSTANCE.genDiskLruKey(filePath, IflyTtsTask$genTtsFile$1.this.$text, IflyTtsTask$genTtsFile$1.this.$extraParam));
                            IflyTtsTask$genTtsFile$1.this.$onTtsListener.onComplete(filePath);
                        }
                    });
                } catch (Exception unused) {
                    handler = IflyTtsTask$genTtsFile$1.this.this$0.mainHandler;
                    handler.post(new Runnable() { // from class: com.pudutech.tts_sdk.tts.IflyTtsTask$genTtsFile$1$onComplete$1.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            IflyTtsTask$genTtsFile$1.this.$onTtsListener.onError(-2, "file copy failed");
                        }
                    });
                }
            }
        });
    }

    @Override // com.pudutech.tts_sdk.tts.OnTtsListener
    public void onError(int code, String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        this.$onTtsListener.onError(code, msg);
    }
}
