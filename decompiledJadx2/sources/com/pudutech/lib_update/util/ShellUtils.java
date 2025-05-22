package com.pudutech.lib_update.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public class ShellUtils {
    public static final String COMMAND_EXIT = "exit\n";
    public static final String COMMAND_LINE_END = "\n";
    public static final String COMMAND_SH = "sh";
    public static final String COMMAND_SU = "su";

    private ShellUtils() {
        throw new AssertionError();
    }

    public static boolean checkRootPermission() {
        return execCommand("echo root", true, false).result == 0;
    }

    public static CommandResult execCommand(String str, boolean z) {
        return execCommand(new String[]{str}, z, true);
    }

    public static CommandResult execCommand(List<String> list, boolean z) {
        return execCommand(list == null ? null : (String[]) list.toArray(new String[0]), z, true);
    }

    public static CommandResult execCommand(String[] strArr, boolean z) {
        return execCommand(strArr, z, true);
    }

    public static CommandResult execCommand(String str, boolean z, boolean z2) {
        return execCommand(new String[]{str}, z, z2);
    }

    public static CommandResult execCommand(List<String> list, boolean z, boolean z2) {
        return execCommand(list == null ? null : (String[]) list.toArray(new String[0]), z, z2);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(23:6|7|(1:9)(1:148)|10|11|12|13|14|(3:16|(2:18|19)(2:21|22)|20)|23|24|(19:26|28|29|31|32|34|35|(3:36|37|(1:39)(1:40))|(2:41|(1:43)(0))|45|(1:47)|(1:49)|(1:52)|53|54|(1:56)(1:62)|(1:58)|59|60)(1:128)|44|45|(0)|(0)|(0)|53|54|(0)(0)|(0)|59|60) */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00d8, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00d9, code lost:
    
        r2.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0146, code lost:
    
        if (r9 != null) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0122, code lost:
    
        r9.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0120, code lost:
    
        if (r9 != null) goto L94;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x016a A[Catch: IOException -> 0x0166, TryCatch #15 {IOException -> 0x0166, blocks: (B:119:0x0162, B:107:0x016a, B:109:0x016f), top: B:118:0x0162 }] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x016f A[Catch: IOException -> 0x0166, TRY_LEAVE, TryCatch #15 {IOException -> 0x0166, blocks: (B:119:0x0162, B:107:0x016a, B:109:0x016f), top: B:118:0x0162 }] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:117:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0162 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00cf A[Catch: IOException -> 0x00d8, TryCatch #1 {IOException -> 0x00d8, blocks: (B:45:0x00ca, B:47:0x00cf, B:49:0x00d4), top: B:44:0x00ca }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d4 A[Catch: IOException -> 0x00d8, TRY_LEAVE, TryCatch #1 {IOException -> 0x00d8, blocks: (B:45:0x00ca, B:47:0x00cf, B:49:0x00d4), top: B:44:0x00ca }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x013a A[Catch: IOException -> 0x0136, TryCatch #2 {IOException -> 0x0136, blocks: (B:81:0x0132, B:71:0x013a, B:73:0x013f), top: B:80:0x0132 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x013f A[Catch: IOException -> 0x0136, TRY_LEAVE, TryCatch #2 {IOException -> 0x0136, blocks: (B:81:0x0132, B:71:0x013a, B:73:0x013f), top: B:80:0x0132 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0132 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0114 A[Catch: IOException -> 0x0110, TryCatch #6 {IOException -> 0x0110, blocks: (B:96:0x010c, B:87:0x0114, B:89:0x0119), top: B:95:0x010c }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0119 A[Catch: IOException -> 0x0110, TRY_LEAVE, TryCatch #6 {IOException -> 0x0110, blocks: (B:96:0x010c, B:87:0x0114, B:89:0x0119), top: B:95:0x010c }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x010c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v20 */
    /* JADX WARN: Type inference failed for: r10v22 */
    /* JADX WARN: Type inference failed for: r10v24 */
    /* JADX WARN: Type inference failed for: r10v25 */
    /* JADX WARN: Type inference failed for: r10v26 */
    /* JADX WARN: Type inference failed for: r10v27 */
    /* JADX WARN: Type inference failed for: r10v28 */
    /* JADX WARN: Type inference failed for: r10v29 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r9v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v19, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static CommandResult execCommand(String[] strArr, boolean z, boolean z2) {
        Process process;
        BufferedReader bufferedReader;
        ?? r2;
        Process process2;
        BufferedReader bufferedReader2;
        Process process3;
        ?? r10;
        Process process4;
        BufferedReader bufferedReader3;
        Process process5;
        BufferedReader bufferedReader4;
        BufferedReader bufferedReader5;
        ?? r5;
        Process process6;
        BufferedReader bufferedReader6;
        Process process7;
        ?? r22;
        ?? r23;
        StringBuilder sb;
        StringBuilder sb2;
        BufferedReader bufferedReader7 = null;
        bufferedReader7 = null;
        int i = -1;
        if (strArr != null) {
            ?? length = strArr.length;
            try {
                if (length != 0) {
                    try {
                        z = Runtime.getRuntime().exec(z != 0 ? COMMAND_SU : COMMAND_SH);
                    } catch (IOException e) {
                        e = e;
                        process4 = null;
                        r10 = null;
                    } catch (Exception e2) {
                        e = e2;
                        process3 = null;
                        r10 = null;
                    } catch (Throwable th) {
                        th = th;
                        process2 = null;
                        bufferedReader2 = null;
                    }
                    try {
                        length = new DataOutputStream(z.getOutputStream());
                    } catch (IOException e3) {
                        e = e3;
                        r10 = null;
                        process4 = z;
                        BufferedReader bufferedReader8 = r10;
                        bufferedReader3 = bufferedReader8;
                        bufferedReader6 = bufferedReader8;
                        process6 = process4;
                        bufferedReader5 = bufferedReader3;
                        r5 = bufferedReader5;
                        r23 = bufferedReader6;
                        process7 = process6;
                        e.printStackTrace();
                        if (r23 != 0) {
                        }
                        if (bufferedReader3 != null) {
                        }
                        if (bufferedReader5 != null) {
                        }
                    } catch (Exception e4) {
                        e = e4;
                        r10 = null;
                        process3 = z;
                        BufferedReader bufferedReader9 = r10;
                        bufferedReader3 = bufferedReader9;
                        bufferedReader4 = bufferedReader9;
                        process5 = process3;
                        bufferedReader5 = bufferedReader3;
                        r5 = bufferedReader5;
                        r22 = bufferedReader4;
                        process7 = process5;
                        e.printStackTrace();
                        if (r22 != 0) {
                        }
                        if (bufferedReader3 != null) {
                        }
                        if (bufferedReader5 != null) {
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader2 = null;
                        process2 = z;
                        bufferedReader = bufferedReader2;
                        r2 = bufferedReader2;
                        process = process2;
                        if (r2 != 0) {
                        }
                        if (bufferedReader7 != null) {
                        }
                        if (bufferedReader != null) {
                        }
                        if (process != null) {
                        }
                    }
                    try {
                        try {
                            for (String str : strArr) {
                                if (str != null) {
                                    length.write(str.getBytes());
                                    length.writeBytes("\n");
                                    length.flush();
                                }
                            }
                            length.writeBytes(COMMAND_EXIT);
                            length.flush();
                            i = z.waitFor();
                        } catch (IOException e5) {
                            e = e5;
                            r10 = null;
                            bufferedReader3 = null;
                            bufferedReader6 = length;
                            process6 = z;
                            bufferedReader5 = bufferedReader3;
                            r5 = bufferedReader5;
                            r23 = bufferedReader6;
                            process7 = process6;
                            e.printStackTrace();
                            if (r23 != 0) {
                                try {
                                    r23.close();
                                } catch (IOException e6) {
                                    e6.printStackTrace();
                                }
                            }
                            if (bufferedReader3 != null) {
                                bufferedReader3.close();
                            }
                            if (bufferedReader5 != null) {
                                bufferedReader5.close();
                            }
                        } catch (Exception e7) {
                            e = e7;
                            r10 = null;
                            bufferedReader3 = null;
                            bufferedReader4 = length;
                            process5 = z;
                            bufferedReader5 = bufferedReader3;
                            r5 = bufferedReader5;
                            r22 = bufferedReader4;
                            process7 = process5;
                            e.printStackTrace();
                            if (r22 != 0) {
                                try {
                                    r22.close();
                                } catch (IOException e8) {
                                    e8.printStackTrace();
                                }
                            }
                            if (bufferedReader3 != null) {
                                bufferedReader3.close();
                            }
                            if (bufferedReader5 != null) {
                                bufferedReader5.close();
                            }
                        }
                        if (z2) {
                            sb = new StringBuilder();
                            try {
                                sb2 = new StringBuilder();
                                try {
                                    bufferedReader3 = new BufferedReader(new InputStreamReader(z.getInputStream()));
                                    try {
                                        bufferedReader5 = new BufferedReader(new InputStreamReader(z.getErrorStream()));
                                        while (true) {
                                            try {
                                                String readLine = bufferedReader3.readLine();
                                                if (readLine == null) {
                                                    break;
                                                }
                                                sb.append(readLine);
                                            } catch (IOException e9) {
                                                r10 = sb;
                                                e = e9;
                                                r5 = sb2;
                                                r23 = length;
                                                process7 = z;
                                                e.printStackTrace();
                                                if (r23 != 0) {
                                                }
                                                if (bufferedReader3 != null) {
                                                }
                                                if (bufferedReader5 != null) {
                                                }
                                            } catch (Exception e10) {
                                                r10 = sb;
                                                e = e10;
                                                r5 = sb2;
                                                r22 = length;
                                                process7 = z;
                                                e.printStackTrace();
                                                if (r22 != 0) {
                                                }
                                                if (bufferedReader3 != null) {
                                                }
                                                if (bufferedReader5 != null) {
                                                }
                                            }
                                        }
                                        while (true) {
                                            String readLine2 = bufferedReader5.readLine();
                                            if (readLine2 != null) {
                                                sb2.append(readLine2);
                                            }
                                        }
                                        length.close();
                                        if (bufferedReader3 != null) {
                                            bufferedReader3.close();
                                        }
                                        if (bufferedReader5 != null) {
                                            bufferedReader5.close();
                                        }
                                        if (z != 0) {
                                            z.destroy();
                                        }
                                        r5 = sb2;
                                        r10 = sb;
                                    } catch (IOException e11) {
                                        r5 = sb2;
                                        r10 = sb;
                                        e = e11;
                                        bufferedReader5 = null;
                                        r23 = length;
                                        process7 = z;
                                    } catch (Exception e12) {
                                        r5 = sb2;
                                        r10 = sb;
                                        e = e12;
                                        bufferedReader5 = null;
                                        r22 = length;
                                        process7 = z;
                                    } catch (Throwable th3) {
                                        th = th3;
                                        bufferedReader = null;
                                        bufferedReader7 = bufferedReader3;
                                        r2 = length;
                                        process = z;
                                        if (r2 != 0) {
                                        }
                                        if (bufferedReader7 != null) {
                                        }
                                        if (bufferedReader != null) {
                                        }
                                        if (process != null) {
                                        }
                                    }
                                } catch (IOException e13) {
                                    r5 = sb2;
                                    bufferedReader5 = null;
                                    r10 = sb;
                                    e = e13;
                                    bufferedReader3 = null;
                                    r23 = length;
                                    process7 = z;
                                } catch (Exception e14) {
                                    r5 = sb2;
                                    bufferedReader5 = null;
                                    r10 = sb;
                                    e = e14;
                                    bufferedReader3 = null;
                                    r22 = length;
                                    process7 = z;
                                }
                            } catch (IOException e15) {
                                bufferedReader3 = null;
                                bufferedReader5 = null;
                                r5 = 0;
                                r10 = sb;
                                e = e15;
                                r23 = length;
                                process7 = z;
                            } catch (Exception e16) {
                                bufferedReader3 = null;
                                bufferedReader5 = null;
                                r5 = 0;
                                r10 = sb;
                                e = e16;
                                r22 = length;
                                process7 = z;
                            }
                            return new CommandResult(i, r10 != null ? null : r10.toString(), r5 != 0 ? r5.toString() : null);
                        }
                        sb = null;
                        sb2 = null;
                        bufferedReader3 = null;
                        bufferedReader5 = null;
                        length.close();
                        if (bufferedReader3 != null) {
                        }
                        if (bufferedReader5 != null) {
                        }
                        if (z != 0) {
                        }
                        r5 = sb2;
                        r10 = sb;
                        return new CommandResult(i, r10 != null ? null : r10.toString(), r5 != 0 ? r5.toString() : null);
                    } catch (Throwable th4) {
                        th = th4;
                        bufferedReader = null;
                        r2 = length;
                        process = z;
                        if (r2 != 0) {
                            try {
                                r2.close();
                            } catch (IOException e17) {
                                e17.printStackTrace();
                                if (process != null) {
                                }
                            }
                        }
                        if (bufferedReader7 != null) {
                            bufferedReader7.close();
                        }
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (process != null) {
                            throw th;
                        }
                        process.destroy();
                        throw th;
                    }
                }
            } catch (Throwable th5) {
                th = th5;
            }
        }
        return new CommandResult(-1, null, null);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static class CommandResult {
        public String errorMsg;
        public int result;
        public String successMsg;

        public CommandResult(int i) {
            this.result = i;
        }

        public CommandResult(int i, String str, String str2) {
            this.result = i;
            this.successMsg = str;
            this.errorMsg = str2;
        }

        public String toString() {
            return "CommandResult{result=" + this.result + ", successMsg='" + this.successMsg + "', errorMsg='" + this.errorMsg + "'}";
        }
    }
}
