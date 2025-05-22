package androidx.test.internal.platform.p004os;

import android.view.View;

/* loaded from: classes.dex */
public interface ControlledLooper {
    public static final ControlledLooper NO_OP_CONTROLLED_LOOPER = new ControlledLooper() { // from class: androidx.test.internal.platform.os.ControlledLooper.1
        @Override // androidx.test.internal.platform.p004os.ControlledLooper
        public void drainMainThreadUntilIdle() {
        }

        @Override // androidx.test.internal.platform.p004os.ControlledLooper
        public void simulateWindowFocus(View decorView) {
        }
    };

    void drainMainThreadUntilIdle();

    void simulateWindowFocus(View decorView);
}
