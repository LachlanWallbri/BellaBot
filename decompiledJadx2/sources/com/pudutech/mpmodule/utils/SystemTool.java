package com.pudutech.mpmodule.utils;

import android.util.Pair;
import com.pudutech.base.Tools;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class SystemTool {
    public static Integer getPidsByPackageName(String str) {
        try {
            Pair<Integer, String> execCommand = Tools.execCommand("pidof -s " + str, true);
            if (execCommand != null) {
                return Integer.valueOf(Integer.parseInt(((String) execCommand.second).substring(0, ((String) execCommand.second).indexOf("\n"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void killProcessByPid(int i) {
        try {
            Tools.execCommand("kill " + i, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
