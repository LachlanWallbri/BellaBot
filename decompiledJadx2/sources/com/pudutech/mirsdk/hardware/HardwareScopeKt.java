package com.pudutech.mirsdk.hardware;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareScope.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, m3961d2 = {"HardwareScope", "Lkotlinx/coroutines/CoroutineScope;", "getHardwareScope", "()Lkotlinx/coroutines/CoroutineScope;", "mirhardware_packRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class HardwareScopeKt {
    private static final CoroutineScope HardwareScope = new CoroutineScope() { // from class: com.pudutech.mirsdk.hardware.HardwareScopeKt$HardwareScope$1
        private final CoroutineContext coroutineContext = Dispatchers.getDefault();

        @Override // kotlinx.coroutines.CoroutineScope
        public CoroutineContext getCoroutineContext() {
            return this.coroutineContext;
        }
    };

    public static final CoroutineScope getHardwareScope() {
        return HardwareScope;
    }
}
