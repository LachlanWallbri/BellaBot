package com.bumptech.glide.manager;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
class ApplicationLifecycle implements Lifecycle {
    @Override // com.bumptech.glide.manager.Lifecycle
    public void removeListener(LifecycleListener lifecycleListener) {
    }

    @Override // com.bumptech.glide.manager.Lifecycle
    public void addListener(LifecycleListener lifecycleListener) {
        lifecycleListener.onStart();
    }
}
