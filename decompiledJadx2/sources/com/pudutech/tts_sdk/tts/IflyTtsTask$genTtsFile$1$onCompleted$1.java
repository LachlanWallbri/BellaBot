package com.pudutech.tts_sdk.tts;

import android.os.Handler;
import com.pudutech.tts_sdk.utils.FileUtils;
import kotlin.Metadata;

/* compiled from: IflyTtsTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "run"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class IflyTtsTask$genTtsFile$1$onCompleted$1 implements Runnable {
    final /* synthetic */ IflyTtsTask$genTtsFile$1 this$0;

    IflyTtsTask$genTtsFile$1$onCompleted$1(IflyTtsTask$genTtsFile$1 iflyTtsTask$genTtsFile$1) {
        this.this$0 = iflyTtsTask$genTtsFile$1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Handler handler;
        Handler handler2;
        try {
            FileUtils.copyFile(this.this$0.$cachePath, this.this$0.$filePath);
            handler2 = this.this$0.this$0.mainHandler;
            handler2.post(new Runnable() { // from class: com.pudutech.tts_sdk.tts.IflyTtsTask$genTtsFile$1$onCompleted$1.1
                @Override // java.lang.Runnable
                public final void run() {
                    IflyTtsTask$genTtsFile$1$onCompleted$1.this.this$0.$onTtsListener.onComplete(IflyTtsTask$genTtsFile$1$onCompleted$1.this.this$0.$filePath);
                }
            });
        } catch (Exception unused) {
            handler = this.this$0.this$0.mainHandler;
            handler.post(new Runnable() { // from class: com.pudutech.tts_sdk.tts.IflyTtsTask$genTtsFile$1$onCompleted$1.2
                @Override // java.lang.Runnable
                public final void run() {
                    IflyTtsTask$genTtsFile$1$onCompleted$1.this.this$0.$onTtsListener.onError(-2, "file copy failed");
                }
            });
        }
    }
}
