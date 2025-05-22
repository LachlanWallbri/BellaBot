package androidx.test.internal.platform.app;

import android.content.ComponentName;
import android.content.Intent;
import androidx.test.platform.app.InstrumentationRegistry;

/* loaded from: classes.dex */
public abstract /* synthetic */ class ActivityInvoker$$CC {
    public static Intent getIntentForActivity$$dflt$$(ActivityInvoker activityInvoker, Class cls) {
        Intent makeMainActivity = Intent.makeMainActivity(new ComponentName(InstrumentationRegistry.getInstrumentation().getTargetContext(), (Class<?>) cls));
        return InstrumentationRegistry.getInstrumentation().getTargetContext().getPackageManager().resolveActivity(makeMainActivity, 0) != null ? makeMainActivity : Intent.makeMainActivity(new ComponentName(InstrumentationRegistry.getInstrumentation().getContext(), (Class<?>) cls));
    }
}
