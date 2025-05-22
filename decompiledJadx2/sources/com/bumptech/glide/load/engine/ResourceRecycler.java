package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
class ResourceRecycler {
    private final Handler handler = new Handler(Looper.getMainLooper(), new ResourceRecyclerCallback());
    private boolean isRecycling;

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void recycle(Resource<?> resource, boolean z) {
        if (!this.isRecycling && !z) {
            this.isRecycling = true;
            resource.recycle();
            this.isRecycling = false;
        }
        this.handler.obtainMessage(1, resource).sendToTarget();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    private static final class ResourceRecyclerCallback implements Handler.Callback {
        static final int RECYCLE_RESOURCE = 1;

        ResourceRecyclerCallback() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((Resource) message.obj).recycle();
            return true;
        }
    }
}
