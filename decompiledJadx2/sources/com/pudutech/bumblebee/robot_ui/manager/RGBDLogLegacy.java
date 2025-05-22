package com.pudutech.bumblebee.robot_ui.manager;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.util.MMKVManager;
import java.io.File;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: LegacyManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R$\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/RGBDLogLegacy;", "Lcom/pudutech/bumblebee/robot_ui/manager/ILegacyInterface;", "()V", "LOG_PATH", "", "TAG", ES6Iterator.VALUE_PROPERTY, "", "rgbdLogLegacy", "getRgbdLogLegacy", "()Z", "setRgbdLogLegacy", "(Z)V", "action", "", "context", "Landroid/content/Context;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
final class RGBDLogLegacy implements ILegacyInterface {
    private final String TAG = "RGBDLogLegacy";
    private final String LOG_PATH = "/sdcard/pudu/log";

    private final boolean getRgbdLogLegacy() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(this.TAG, false);
    }

    private final void setRgbdLogLegacy(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(this.TAG, Boolean.valueOf(z));
    }

    @Override // com.pudutech.bumblebee.robot_ui.manager.ILegacyInterface
    public void action(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            Pdlog.m3273d(this.TAG, "action rgbdLogLegacy:" + getRgbdLogLegacy() + ' ');
            if (getRgbdLogLegacy()) {
                return;
            }
            Pattern compile = Pattern.compile("[0-9]{4}(_)[0-9]{2}(_)[0-9]{2}(__)[0-9]{2}(_)[0-9]{2}(_)[0-9]{2}(_)[0-9]*(.log)");
            File file = new File(this.LOG_PATH);
            if (file.exists() && file.isDirectory()) {
                File[] listFiles = file.listFiles();
                Intrinsics.checkExpressionValueIsNotNull(listFiles, "path.listFiles()");
                for (File it : listFiles) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    if (compile.matcher(it.getName()).matches()) {
                        Pdlog.m3273d(this.TAG, "action delete: " + it.getName());
                        it.delete();
                    }
                }
            }
            setRgbdLogLegacy(true);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "action error:" + e + ' ');
        }
    }
}
