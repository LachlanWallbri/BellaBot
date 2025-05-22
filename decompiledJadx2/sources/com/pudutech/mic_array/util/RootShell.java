package com.pudutech.mic_array.util;

import android.util.Log;
import com.pudutech.lib_update.util.ShellUtils;
import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: classes5.dex */
public final class RootShell {
    private static final String TAG = "RootShell";

    public static int execRootCmdSilent(String str) {
        DataOutputStream dataOutputStream;
        Log.d(TAG, "run " + str);
        int i = -1;
        DataOutputStream dataOutputStream2 = null;
        try {
            try {
                try {
                    Process exec = Runtime.getRuntime().exec(ShellUtils.COMMAND_SU);
                    dataOutputStream = new DataOutputStream(exec.getOutputStream());
                    try {
                        Log.i(TAG, str);
                        dataOutputStream.writeBytes(str + "\n");
                        dataOutputStream.flush();
                        dataOutputStream.writeBytes(ShellUtils.COMMAND_EXIT);
                        dataOutputStream.flush();
                        exec.waitFor();
                        i = exec.exitValue();
                        Log.d(TAG, "run " + str + " result: " + i);
                        dataOutputStream.close();
                    } catch (Exception e) {
                        e = e;
                        dataOutputStream2 = dataOutputStream;
                        e.printStackTrace();
                        if (dataOutputStream2 != null) {
                            dataOutputStream2.close();
                        }
                        return i;
                    } catch (Throwable th) {
                        th = th;
                        if (dataOutputStream != null) {
                            try {
                                dataOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    dataOutputStream = dataOutputStream2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return i;
    }
}
