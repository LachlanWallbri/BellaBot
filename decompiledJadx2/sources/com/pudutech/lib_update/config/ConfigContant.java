package com.pudutech.lib_update.config;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ConfigContant.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/lib_update/config/ConfigContant;", "", "()V", "Companion", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ConfigContant {
    public static final String DEFAULT_SAVE_FILE_PATH = "/sdcard/data/0/";
    public static final String SYSTEM_FILE_SUFFIX = ".pkg";
    public static final String SYSTEM_UPDATE_DIR = "/data/media/0/";
    public static final String SYSTEM_UPDATE_DOWNLOAD_PATH = "/data/media/0/";
    public static final String SYSTEM_UPDATE_FILE_NAME_FORMAT = "system_update_%s.pkg";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String SOFTWARE_VERSION_CODE = "2";
    private static final String SYSTEM_VERSION_CODE = "1";
    private static final String SYSTEM_VERSION_NAME = SYSTEM_VERSION_NAME;
    private static final String SYSTEM_VERSION_NAME = SYSTEM_VERSION_NAME;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ConfigContant.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0014\u0010\u000e\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/lib_update/config/ConfigContant$Companion;", "", "()V", "DEFAULT_SAVE_FILE_PATH", "", "SOFTWARE_VERSION_CODE", "getSOFTWARE_VERSION_CODE", "()Ljava/lang/String;", "SYSTEM_FILE_SUFFIX", "SYSTEM_UPDATE_DIR", "SYSTEM_UPDATE_DOWNLOAD_PATH", "SYSTEM_UPDATE_FILE_NAME_FORMAT", "SYSTEM_VERSION_CODE", "getSYSTEM_VERSION_CODE", "SYSTEM_VERSION_NAME", "getSYSTEM_VERSION_NAME", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getSOFTWARE_VERSION_CODE() {
            return ConfigContant.SOFTWARE_VERSION_CODE;
        }

        public final String getSYSTEM_VERSION_CODE() {
            return ConfigContant.SYSTEM_VERSION_CODE;
        }

        public final String getSYSTEM_VERSION_NAME() {
            return ConfigContant.SYSTEM_VERSION_NAME;
        }
    }
}
