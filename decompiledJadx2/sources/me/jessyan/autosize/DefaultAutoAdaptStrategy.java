package me.jessyan.autosize;

import android.app.Activity;
import java.util.Locale;
import me.jessyan.autosize.external.ExternalAdaptInfo;
import me.jessyan.autosize.internal.CancelAdapt;
import me.jessyan.autosize.internal.CustomAdapt;
import me.jessyan.autosize.utils.LogUtils;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes8.dex */
public class DefaultAutoAdaptStrategy implements AutoAdaptStrategy {
    @Override // me.jessyan.autosize.AutoAdaptStrategy
    public void applyAdapt(Object obj, Activity activity) {
        if (AutoSizeConfig.getInstance().getExternalAdaptManager().isRun()) {
            if (AutoSizeConfig.getInstance().getExternalAdaptManager().isCancelAdapt(obj.getClass())) {
                LogUtils.m3986w(String.format(Locale.ENGLISH, "%s canceled the adaptation!", obj.getClass().getName()));
                AutoSize.cancelAdapt(activity);
                return;
            } else {
                ExternalAdaptInfo externalAdaptInfoOfActivity = AutoSizeConfig.getInstance().getExternalAdaptManager().getExternalAdaptInfoOfActivity(obj.getClass());
                if (externalAdaptInfoOfActivity != null) {
                    LogUtils.m3984d(String.format(Locale.ENGLISH, "%s used %s for adaptation!", obj.getClass().getName(), ExternalAdaptInfo.class.getName()));
                    AutoSize.autoConvertDensityOfExternalAdaptInfo(activity, externalAdaptInfoOfActivity);
                    return;
                }
            }
        }
        if (obj instanceof CancelAdapt) {
            LogUtils.m3986w(String.format(Locale.ENGLISH, "%s canceled the adaptation!", obj.getClass().getName()));
            AutoSize.cancelAdapt(activity);
        } else if (obj instanceof CustomAdapt) {
            LogUtils.m3984d(String.format(Locale.ENGLISH, "%s implemented by %s!", obj.getClass().getName(), CustomAdapt.class.getName()));
            AutoSize.autoConvertDensityOfCustomAdapt(activity, (CustomAdapt) obj);
        } else {
            LogUtils.m3984d(String.format(Locale.ENGLISH, "%s used the global configuration.", obj.getClass().getName()));
            AutoSize.autoConvertDensityOfGlobal(activity);
        }
    }
}
