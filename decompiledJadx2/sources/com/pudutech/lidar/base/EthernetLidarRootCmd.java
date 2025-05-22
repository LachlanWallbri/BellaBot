package com.pudutech.lidar.base;

import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.lib_update.util.ShellUtils;
import com.pudutech.mirsdk.hardware.HardwareConfig;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: EthernetLidarRootCmd.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0006J\"\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00040\t2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0006J\u0010\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/lidar/base/EthernetLidarRootCmd;", "", "()V", "TAG", "", "mRootPermission", "", "checkRoot", "execCommand", "Lkotlin/Pair;", "", "cmd", "useSU", "execRootCmd", "execRootCmdSilent", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class EthernetLidarRootCmd {
    private static boolean mRootPermission;
    public static final EthernetLidarRootCmd INSTANCE = new EthernetLidarRootCmd();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private EthernetLidarRootCmd() {
    }

    public final boolean checkRoot() {
        if (!mRootPermission) {
            if (execRootCmdSilent("echo test") != -1) {
                Pdlog.m3273d(TAG, "hasRoot ");
                mRootPermission = true;
            } else {
                Pdlog.m3277w(TAG, "no root ");
            }
        }
        return mRootPermission;
    }

    public final Pair<Integer, String> execCommand(String cmd, boolean useSU) {
        Intrinsics.checkParameterIsNotNull(cmd, "cmd");
        Pdlog.m3275i(TAG, "execCommand cmd=" + cmd + "  useSU=" + useSU);
        String[] strArr = new String[3];
        strArr[0] = useSU ? ShellUtils.COMMAND_SU : ShellUtils.COMMAND_SH;
        strArr[1] = "-c";
        strArr[2] = cmd;
        try {
            Process proc = Runtime.getRuntime().exec(strArr);
            if (!Tools.waitFor(HardwareConfig.RGBDFwUpdateTimeOut, TimeUnit.MILLISECONDS, proc)) {
                return new Pair<>(-1, "exec command[$command] timeout.");
            }
            Intrinsics.checkExpressionValueIsNotNull(proc, "proc");
            InputStream inputStream = proc.getInputStream();
            Intrinsics.checkExpressionValueIsNotNull(inputStream, "proc.inputStream");
            Scanner scanner = new Scanner(inputStream);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
                sb.append("\n");
            }
            inputStream.close();
            int exitValue = proc.exitValue();
            Pdlog.m3273d(TAG, "su exit value, exitValue = " + exitValue + "\tsb = " + ((Object) sb));
            return new Pair<>(Integer.valueOf(exitValue), sb.toString());
        } catch (Throwable th) {
            Pdlog.m3274e(TAG, th.getMessage());
            return new Pair<>(-1, "exec commond failure.");
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x0097 -> B:17:0x00bc). Please report as a decompilation issue!!! */
    public final String execRootCmd(String cmd) {
        Intrinsics.checkParameterIsNotNull(cmd, "cmd");
        String str = "";
        DataOutputStream dataOutputStream = (DataOutputStream) null;
        DataInputStream dataInputStream = (DataInputStream) null;
        try {
            try {
                try {
                    Process p = Runtime.getRuntime().exec(ShellUtils.COMMAND_SU);
                    Intrinsics.checkExpressionValueIsNotNull(p, "p");
                    DataOutputStream dataOutputStream2 = new DataOutputStream(p.getOutputStream());
                    try {
                        DataInputStream dataInputStream2 = new DataInputStream(p.getInputStream());
                        try {
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                            Pdlog.m3275i(TAG, cmd);
                            dataOutputStream2.writeBytes("su-c" + cmd + '\n');
                            dataOutputStream2.flush();
                            dataOutputStream2.writeBytes(ShellUtils.COMMAND_EXIT);
                            dataOutputStream2.flush();
                            while (true) {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                Pdlog.m3273d(SpeechUtility.TAG_RESOURCE_RESULT, readLine);
                                str = Intrinsics.stringPlus(str, readLine);
                            }
                            p.waitFor();
                            bufferedReader.close();
                            try {
                                dataOutputStream2.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            dataInputStream2.close();
                        } catch (Exception e2) {
                            e = e2;
                            dataInputStream = dataInputStream2;
                            dataOutputStream = dataOutputStream2;
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
                            return str;
                        } catch (Throwable th) {
                            th = th;
                            dataInputStream = dataInputStream2;
                            dataOutputStream = dataOutputStream2;
                            if (dataOutputStream != null) {
                                try {
                                    dataOutputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            if (dataInputStream != null) {
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
                    } catch (Exception e6) {
                        e = e6;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Exception e7) {
                e = e7;
            }
        } catch (IOException e8) {
            e8.printStackTrace();
        }
        return str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int execRootCmdSilent(String cmd) {
        int i;
        Intrinsics.checkParameterIsNotNull(cmd, "cmd");
        DataOutputStream dataOutputStream = (DataOutputStream) null;
        try {
            try {
                Process p = Runtime.getRuntime().exec(ShellUtils.COMMAND_SU);
                Intrinsics.checkExpressionValueIsNotNull(p, "p");
                DataOutputStream dataOutputStream2 = new DataOutputStream(p.getOutputStream());
                try {
                    Pdlog.m3275i(TAG, cmd);
                    StringBuilder sb = new StringBuilder();
                    sb.append(cmd);
                    sb.append('\n');
                    dataOutputStream2.writeBytes(sb.toString());
                    dataOutputStream2.flush();
                    dataOutputStream2.writeBytes(ShellUtils.COMMAND_EXIT);
                    dataOutputStream2.flush();
                    p.waitFor();
                    i = p.exitValue();
                    try {
                        dataOutputStream2.close();
                        dataOutputStream = sb;
                    } catch (IOException e) {
                        e.printStackTrace();
                        dataOutputStream = e;
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
                    i = -1;
                    dataOutputStream = dataOutputStream;
                    return i;
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
            } catch (Exception e5) {
                e = e5;
            }
            return i;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
