package com.pudutech.mpcomponent.interf;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public abstract class IMusicPlayerStateCallback {
    public abstract void onCompletion();

    public abstract void onError();

    public abstract void onInitialized();

    public abstract void onPause();

    public abstract void onPrepared();

    public abstract void onRelease();

    public abstract void onReset();

    public abstract void onStop();
}
