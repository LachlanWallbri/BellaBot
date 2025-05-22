package com.pudutech.antichannelconflict.upgrade;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CmdConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004J\u0018\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/upgrade/CmdConfig;", "", "()V", "excuteUpdate", "", "getRevision", "readBTS", "requireBTS", "getCopyFirmwareCmd", "context", "Landroid/content/Context;", "path", "getDeleteUpdateFile", "deleteFiles", "deleteFolder", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CmdConfig {
    public static final CmdConfig INSTANCE = new CmdConfig();
    public static final String excuteUpdate = " cd /data/fastboot && chmod 777 fastboot sim79xx_upgrade_C &&  ./sim79xx_upgrade_C && cd ../..";
    public static final String getRevision = "echo -e \"AT+SIMCOMATI\\r\\n\" > /dev/device";
    public static final String readBTS = "cat /dev/device | head -n 10 ";
    public static final String requireBTS = "echo -e 'AT+CNETCI=38\\r\\n'> /dev/device";

    private CmdConfig() {
    }

    public final String getCopyFirmwareCmd(Context context, String path) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return "cp -R " + path + "* /data ";
    }

    public final String getDeleteUpdateFile(String deleteFiles, String deleteFolder) {
        Intrinsics.checkParameterIsNotNull(deleteFiles, "deleteFiles");
        Intrinsics.checkParameterIsNotNull(deleteFolder, "deleteFolder");
        return "rm -rf " + deleteFolder + " && cd data && rm -rf  " + deleteFiles + " && cd ..";
    }
}
