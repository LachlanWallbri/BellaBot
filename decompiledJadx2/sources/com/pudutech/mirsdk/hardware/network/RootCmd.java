package com.pudutech.mirsdk.hardware.network;

import android.util.Log;
import android.util.Pair;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Tools;
import com.pudutech.lib_update.util.ShellUtils;
import com.pudutech.mirsdk.hardware.HardwareConfig;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* loaded from: classes2.dex */
public class RootCmd {
    private static final String TAG = "RootCmd";
    private static boolean mHaveRoot = false;

    public static boolean haveRoot() {
        if (!mHaveRoot) {
            if (execRootCmdSilent("echo test") != -1) {
                Log.i(TAG, "have root!");
                mHaveRoot = true;
            } else {
                Log.i(TAG, "not root!");
            }
        } else {
            Log.i(TAG, "mHaveRoot = true, have root!");
        }
        return mHaveRoot;
    }

    public static Pair<Integer, String> execCommand(String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("execCommand:");
        sb.append(str);
        sb.append(z ? "with su" : "with sh");
        Log.d(TAG, sb.toString());
        String[] strArr = new String[3];
        strArr[0] = z ? ShellUtils.COMMAND_SU : ShellUtils.COMMAND_SH;
        strArr[1] = "-c";
        strArr[2] = str;
        try {
            Process exec = Runtime.getRuntime().exec(strArr);
            if (!Tools.waitFor(HardwareConfig.RGBDFwUpdateTimeOut, TimeUnit.MILLISECONDS, exec)) {
                return Pair.create(-1, "exec command[$command] timeout.");
            }
            InputStream inputStream = exec.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            StringBuilder sb2 = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb2.append(scanner.nextLine());
                sb2.append("\n");
            }
            inputStream.close();
            int exitValue = exec.exitValue();
            Log.d(TAG, "su exit value, exitValue = " + exitValue + "\tsb = " + ((Object) sb2));
            return Pair.create(Integer.valueOf(exitValue), sb2.toString());
        } catch (Throwable th) {
            Log.e(TAG, th.getMessage());
            return Pair.create(-1, "exec commond failure.");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x009e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:59:0x0073 -> B:16:0x009a). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String execRootCmd(String str) {
        DataInputStream dataInputStream;
        String str2 = "";
        DataOutputStream dataOutputStream = null;
        try {
            try {
                Process exec = Runtime.getRuntime().exec(ShellUtils.COMMAND_SU);
                DataOutputStream dataOutputStream2 = new DataOutputStream(exec.getOutputStream());
                try {
                    dataInputStream = new DataInputStream(exec.getInputStream());
                    try {
                        Log.i(TAG, str);
                        dataOutputStream2.writeBytes("su-c" + str + "\n");
                        dataOutputStream2.flush();
                        dataOutputStream2.writeBytes(ShellUtils.COMMAND_EXIT);
                        dataOutputStream2.flush();
                        while (true) {
                            String readLine = dataInputStream.readLine();
                            if (readLine == null) {
                                break;
                            }
                            Log.d(SpeechUtility.TAG_RESOURCE_RESULT, readLine);
                            str2 = str2 + readLine;
                        }
                        exec.waitFor();
                        try {
                            dataOutputStream2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        dataInputStream.close();
                    } catch (Exception e2) {
                        e = e2;
                        dataOutputStream = dataOutputStream2;
                        try {
                            e.printStackTrace();
                            if (dataOutputStream != null) {
                                try {
                                    dataOutputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            if (dataInputStream != null) {
                                dataInputStream.close();
                            }
                            return str2;
                        } catch (Throwable th) {
                            th = th;
                            if (dataOutputStream != null) {
                                try {
                                    dataOutputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            if (dataInputStream == null) {
                                try {
                                    dataInputStream.close();
                                    throw th;
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                    throw th;
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        dataOutputStream = dataOutputStream2;
                        if (dataOutputStream != null) {
                        }
                        if (dataInputStream == null) {
                        }
                    }
                } catch (Exception e6) {
                    e = e6;
                    dataInputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    dataInputStream = null;
                }
            } catch (Exception e7) {
                e = e7;
                dataInputStream = null;
            } catch (Throwable th4) {
                th = th4;
                dataInputStream = null;
            }
        } catch (IOException e8) {
            e8.printStackTrace();
        }
        return str2;
    }

    public static int execRootCmdSilent(String str) {
        DataOutputStream dataOutputStream = null;
        try {
            try {
                Process exec = Runtime.getRuntime().exec(ShellUtils.COMMAND_SU);
                DataOutputStream dataOutputStream2 = new DataOutputStream(exec.getOutputStream());
                try {
                    Log.i(TAG, str);
                    dataOutputStream2.writeBytes(str + "\n");
                    dataOutputStream2.flush();
                    dataOutputStream2.writeBytes(ShellUtils.COMMAND_EXIT);
                    dataOutputStream2.flush();
                    exec.waitFor();
                    int exitValue = exec.exitValue();
                    try {
                        dataOutputStream2.close();
                        return exitValue;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return exitValue;
                    }
                } catch (Exception e2) {
                    e = e2;
                    dataOutputStream = dataOutputStream2;
                    e.printStackTrace();
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return -1;
                } catch (Throwable th) {
                    th = th;
                    dataOutputStream = dataOutputStream2;
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e5) {
            e = e5;
        }
    }
}
