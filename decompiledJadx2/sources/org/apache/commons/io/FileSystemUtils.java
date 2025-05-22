package org.apache.commons.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
@Deprecated
/* loaded from: classes2.dex */
public class FileSystemUtils {

    /* renamed from: DF */
    private static final String f8978DF;
    private static final int INIT_PROBLEM = -1;
    private static final FileSystemUtils INSTANCE = new FileSystemUtils();

    /* renamed from: OS */
    private static final int f8979OS;
    private static final int OTHER = 0;
    private static final int POSIX_UNIX = 3;
    private static final int UNIX = 2;
    private static final int WINDOWS = 1;

    static {
        int i;
        String property;
        String str = "df";
        try {
            property = System.getProperty("os.name");
        } catch (Exception unused) {
            i = -1;
        }
        if (property == null) {
            throw new IOException("os.name not found");
        }
        String lowerCase = property.toLowerCase(Locale.ENGLISH);
        i = 3;
        if (lowerCase.contains("windows")) {
            i = 1;
        } else {
            if (!lowerCase.contains("linux") && !lowerCase.contains("mpe/ix") && !lowerCase.contains("freebsd") && !lowerCase.contains("openbsd") && !lowerCase.contains("irix") && !lowerCase.contains("digital unix") && !lowerCase.contains("unix") && !lowerCase.contains("mac os x")) {
                if (!lowerCase.contains("sun os") && !lowerCase.contains("sunos") && !lowerCase.contains("solaris")) {
                    if (!lowerCase.contains("hp-ux") && !lowerCase.contains("aix")) {
                        i = 0;
                    }
                }
                str = "/usr/xpg4/bin/df";
            }
            i = 2;
        }
        f8979OS = i;
        f8978DF = str;
    }

    @Deprecated
    public static long freeSpace(String str) throws IOException {
        return INSTANCE.freeSpaceOS(str, f8979OS, false, -1L);
    }

    @Deprecated
    public static long freeSpaceKb(String str) throws IOException {
        return freeSpaceKb(str, -1L);
    }

    @Deprecated
    public static long freeSpaceKb(String str, long j) throws IOException {
        return INSTANCE.freeSpaceOS(str, f8979OS, true, j);
    }

    @Deprecated
    public static long freeSpaceKb() throws IOException {
        return freeSpaceKb(-1L);
    }

    @Deprecated
    public static long freeSpaceKb(long j) throws IOException {
        return freeSpaceKb(new File(".").getAbsolutePath(), j);
    }

    long freeSpaceOS(String str, int i, boolean z, long j) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("Path must not be null");
        }
        if (i == 0) {
            throw new IllegalStateException("Unsupported operating system");
        }
        if (i == 1) {
            long freeSpaceWindows = freeSpaceWindows(str, j);
            return z ? freeSpaceWindows / 1024 : freeSpaceWindows;
        }
        if (i == 2) {
            return freeSpaceUnix(str, z, false, j);
        }
        if (i == 3) {
            return freeSpaceUnix(str, z, true, j);
        }
        throw new IllegalStateException("Exception caught when determining operating system");
    }

    long freeSpaceWindows(String str, long j) throws IOException {
        String normalize = FilenameUtils.normalize(str, false);
        if (normalize == null) {
            throw new IllegalArgumentException(str);
        }
        if (normalize.length() > 0 && normalize.charAt(0) != '\"') {
            normalize = "\"" + normalize + "\"";
        }
        List<String> performCommand = performCommand(new String[]{"cmd.exe", "/C", "dir /a /-c " + normalize}, Integer.MAX_VALUE, j);
        for (int size = performCommand.size() - 1; size >= 0; size--) {
            String str2 = performCommand.get(size);
            if (str2.length() > 0) {
                return parseDir(str2, normalize);
            }
        }
        throw new IOException("Command line 'dir /-c' did not return any info for path '" + normalize + "'");
    }

    long parseDir(String str, String str2) throws IOException {
        int i;
        int i2;
        int i3;
        int length = str.length();
        while (true) {
            length--;
            i = 0;
            if (length < 0) {
                i2 = 0;
                break;
            }
            if (Character.isDigit(str.charAt(length))) {
                i2 = length + 1;
                break;
            }
        }
        while (true) {
            if (length < 0) {
                i3 = 0;
                break;
            }
            char charAt = str.charAt(length);
            if (!Character.isDigit(charAt) && charAt != ',' && charAt != '.') {
                i3 = length + 1;
                break;
            }
            length--;
        }
        if (length < 0) {
            throw new IOException("Command line 'dir /-c' did not return valid info for path '" + str2 + "'");
        }
        StringBuilder sb = new StringBuilder(str.substring(i3, i2));
        while (i < sb.length()) {
            if (sb.charAt(i) == ',' || sb.charAt(i) == '.') {
                sb.deleteCharAt(i);
                i--;
            }
            i++;
        }
        return parseBytes(sb.toString(), str2);
    }

    long freeSpaceUnix(String str, boolean z, boolean z2, long j) throws IOException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Path must not be empty");
        }
        String str2 = "-";
        if (z) {
            str2 = "-k";
        }
        if (z2) {
            str2 = str2 + "P";
        }
        List<String> performCommand = performCommand(str2.length() > 1 ? new String[]{f8978DF, str2, str} : new String[]{f8978DF, str}, 3, j);
        if (performCommand.size() < 2) {
            throw new IOException("Command line '" + f8978DF + "' did not return info as expected for path '" + str + "'- response was " + performCommand);
        }
        StringTokenizer stringTokenizer = new StringTokenizer(performCommand.get(1), " ");
        if (stringTokenizer.countTokens() < 4) {
            if (stringTokenizer.countTokens() == 1 && performCommand.size() >= 3) {
                stringTokenizer = new StringTokenizer(performCommand.get(2), " ");
            } else {
                throw new IOException("Command line '" + f8978DF + "' did not return data as expected for path '" + str + "'- check path is valid");
            }
        } else {
            stringTokenizer.nextToken();
        }
        stringTokenizer.nextToken();
        stringTokenizer.nextToken();
        return parseBytes(stringTokenizer.nextToken(), str);
    }

    long parseBytes(String str, String str2) throws IOException {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong >= 0) {
                return parseLong;
            }
            throw new IOException("Command line '" + f8978DF + "' did not find free space in response for path '" + str2 + "'- check path is valid");
        } catch (NumberFormatException e) {
            throw new IOException("Command line '" + f8978DF + "' did not return numeric data as expected for path '" + str2 + "'- check path is valid", e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:45:0x012d  */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.io.Reader] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    List<String> performCommand(String[] strArr, int i, long j) throws IOException {
        Process process;
        OutputStream outputStream;
        InputStream inputStream;
        InputStream inputStream2;
        ?? r7;
        ?? r72;
        Thread start;
        ArrayList arrayList = new ArrayList(20);
        InputStream inputStream3 = null;
        try {
            start = ThreadMonitor.start(j);
            process = openProcess(strArr);
        } catch (InterruptedException e) {
            e = e;
            inputStream = null;
            outputStream = null;
            inputStream2 = null;
            r7 = 0;
        } catch (Throwable th) {
            th = th;
            process = null;
            outputStream = null;
        }
        try {
            inputStream = process.getInputStream();
            try {
                outputStream = process.getOutputStream();
                try {
                    inputStream2 = process.getErrorStream();
                    try {
                        r7 = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()));
                    } catch (InterruptedException e2) {
                        e = e2;
                        r7 = 0;
                    } catch (Throwable th2) {
                        th = th2;
                        r7 = 0;
                    }
                } catch (InterruptedException e3) {
                    e = e3;
                    inputStream2 = null;
                    r7 = inputStream2;
                    inputStream3 = process;
                    r7 = r7;
                    try {
                        throw new IOException("Command line threw an InterruptedException for command " + Arrays.asList(strArr) + " timeout=" + j, e);
                    } catch (Throwable th3) {
                        th = th3;
                        process = inputStream3;
                        inputStream3 = inputStream;
                        r72 = r7;
                        IOUtils.closeQuietly(inputStream3);
                        IOUtils.closeQuietly(outputStream);
                        IOUtils.closeQuietly(inputStream2);
                        IOUtils.closeQuietly((Reader) r72);
                        if (process != null) {
                            process.destroy();
                        }
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    inputStream2 = null;
                    r7 = inputStream2;
                    inputStream3 = inputStream;
                    r72 = r7;
                    IOUtils.closeQuietly(inputStream3);
                    IOUtils.closeQuietly(outputStream);
                    IOUtils.closeQuietly(inputStream2);
                    IOUtils.closeQuietly((Reader) r72);
                    if (process != null) {
                    }
                    throw th;
                }
                try {
                    for (String readLine = r7.readLine(); readLine != null && arrayList.size() < i; readLine = r7.readLine()) {
                        arrayList.add(readLine.toLowerCase(Locale.ENGLISH).trim());
                    }
                    process.waitFor();
                    ThreadMonitor.stop(start);
                    if (process.exitValue() != 0) {
                        throw new IOException("Command line returned OS error code '" + process.exitValue() + "' for command " + Arrays.asList(strArr));
                    }
                    if (arrayList.isEmpty()) {
                        throw new IOException("Command line did not return any info for command " + Arrays.asList(strArr));
                    }
                    r7.close();
                    inputStream.close();
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                            outputStream = null;
                        } catch (InterruptedException e4) {
                            e = e4;
                            inputStream = null;
                            r7 = 0;
                            inputStream3 = process;
                            r7 = r7;
                            throw new IOException("Command line threw an InterruptedException for command " + Arrays.asList(strArr) + " timeout=" + j, e);
                        } catch (Throwable th5) {
                            th = th5;
                            r72 = 0;
                            IOUtils.closeQuietly(inputStream3);
                            IOUtils.closeQuietly(outputStream);
                            IOUtils.closeQuietly(inputStream2);
                            IOUtils.closeQuietly((Reader) r72);
                            if (process != null) {
                            }
                            throw th;
                        }
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                        inputStream2 = null;
                    }
                    IOUtils.closeQuietly((InputStream) null);
                    IOUtils.closeQuietly(outputStream);
                    IOUtils.closeQuietly(inputStream2);
                    IOUtils.closeQuietly((Reader) null);
                    if (process != null) {
                        process.destroy();
                    }
                    return arrayList;
                } catch (InterruptedException e5) {
                    e = e5;
                } catch (Throwable th6) {
                    th = th6;
                    inputStream3 = inputStream;
                    r72 = r7;
                    IOUtils.closeQuietly(inputStream3);
                    IOUtils.closeQuietly(outputStream);
                    IOUtils.closeQuietly(inputStream2);
                    IOUtils.closeQuietly((Reader) r72);
                    if (process != null) {
                    }
                    throw th;
                }
            } catch (InterruptedException e6) {
                e = e6;
                outputStream = null;
                inputStream2 = outputStream;
                r7 = inputStream2;
                inputStream3 = process;
                r7 = r7;
                throw new IOException("Command line threw an InterruptedException for command " + Arrays.asList(strArr) + " timeout=" + j, e);
            } catch (Throwable th7) {
                th = th7;
                outputStream = null;
                inputStream2 = null;
            }
        } catch (InterruptedException e7) {
            e = e7;
            inputStream = null;
            outputStream = null;
        } catch (Throwable th8) {
            th = th8;
            outputStream = null;
            inputStream2 = outputStream;
            r72 = inputStream2;
            IOUtils.closeQuietly(inputStream3);
            IOUtils.closeQuietly(outputStream);
            IOUtils.closeQuietly(inputStream2);
            IOUtils.closeQuietly((Reader) r72);
            if (process != null) {
            }
            throw th;
        }
    }

    Process openProcess(String[] strArr) throws IOException {
        return Runtime.getRuntime().exec(strArr);
    }
}
