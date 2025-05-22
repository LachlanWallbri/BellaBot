package androidx.test.internal.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes.dex */
public final class ProcSummary {
    public final String cmdline;
    public final String name;
    public final String parent;
    public final String pid;
    public final String realUid;
    public final long startTime;

    private ProcSummary(Builder b) {
        this.name = (String) Checks.checkNotNull(b.name);
        this.pid = (String) Checks.checkNotNull(b.pid);
        this.realUid = (String) Checks.checkNotNull(b.realUid);
        this.parent = (String) Checks.checkNotNull(b.parent);
        this.cmdline = (String) Checks.checkNotNull(b.cmdline);
        this.startTime = b.startTime;
    }

    public static ProcSummary summarize(String pid) {
        return parse(readToString(new File(new File("/proc", pid), "stat")), readToString(new File(new File("/proc", pid), "status")), readToString(new File(new File("/proc", pid), "cmdline")));
    }

    private static final String readToString(File path) {
        StringBuilder sb = new StringBuilder();
        char[] cArr = new char[1024];
        InputStreamReader inputStreamReader = null;
        try {
            try {
                InputStreamReader inputStreamReader2 = new InputStreamReader(new FileInputStream(path));
                while (true) {
                    try {
                        int read = inputStreamReader2.read(cArr, 0, cArr.length);
                        if (read == -1) {
                            break;
                        }
                        sb.append(cArr, 0, read);
                    } catch (IOException e) {
                        e = e;
                        String valueOf = String.valueOf(path);
                        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 16);
                        sb2.append("Could not read: ");
                        sb2.append(valueOf);
                        throw new SummaryException(sb2.toString(), e);
                    } catch (RuntimeException e2) {
                        e = e2;
                        String valueOf2 = String.valueOf(path);
                        StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf2).length() + 15);
                        sb3.append("Error reading: ");
                        sb3.append(valueOf2);
                        throw new SummaryException(sb3.toString(), e);
                    } catch (Throwable th) {
                        th = th;
                        inputStreamReader = inputStreamReader2;
                        if (inputStreamReader != null) {
                            try {
                                inputStreamReader.close();
                            } catch (IOException unused) {
                            }
                        }
                        throw th;
                    }
                }
                String sb4 = sb.toString();
                try {
                    inputStreamReader2.close();
                } catch (IOException unused2) {
                }
                return sb4;
            } catch (IOException e3) {
                e = e3;
            } catch (RuntimeException e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* loaded from: classes.dex */
    public static class SummaryException extends RuntimeException {
        public SummaryException(String msg, Throwable cause) {
            super(msg, cause);
        }

        public SummaryException(String msg) {
            super(msg);
        }
    }

    static ProcSummary parse(String statLine, String statusContent, String cmdline) {
        String[] split = statLine.substring(statLine.lastIndexOf(41) + 2).split(" ", -1);
        String substring = statusContent.substring(statusContent.indexOf("\nUid:") + 1);
        return new Builder().withPid(statLine.substring(0, statLine.indexOf(32))).withName(statLine.substring(statLine.indexOf(40) + 1, statLine.lastIndexOf(41))).withParent(split[1]).withRealUid(substring.substring(0, substring.indexOf(10)).split("\\s", -1)[1]).withCmdline(cmdline.trim().replace((char) 0, ' ')).withStartTime(Long.parseLong(split[19])).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Builder {
        private String cmdline;
        private String name;
        private String parent;
        private String pid;
        private String realUid;
        private long startTime;

        Builder() {
        }

        Builder withParent(String ppid) {
            try {
                Integer.parseInt(ppid);
                this.parent = ppid;
                return this;
            } catch (NumberFormatException unused) {
                String valueOf = String.valueOf(ppid);
                throw new IllegalArgumentException(valueOf.length() != 0 ? "not a pid: ".concat(valueOf) : new String("not a pid: "));
            }
        }

        Builder withCmdline(String cmdline) {
            this.cmdline = cmdline;
            return this;
        }

        Builder withName(String name) {
            this.name = name;
            return this;
        }

        Builder withPid(String pid) {
            try {
                Integer.parseInt(pid);
                this.pid = pid;
                return this;
            } catch (NumberFormatException unused) {
                String valueOf = String.valueOf(pid);
                throw new IllegalArgumentException(valueOf.length() != 0 ? "not a pid: ".concat(valueOf) : new String("not a pid: "));
            }
        }

        Builder withRealUid(String uid) {
            try {
                Integer.parseInt(uid);
                this.realUid = uid;
                return this;
            } catch (NumberFormatException unused) {
                String valueOf = String.valueOf(uid);
                throw new IllegalArgumentException(valueOf.length() != 0 ? "not a uid: ".concat(valueOf) : new String("not a uid: "));
            }
        }

        Builder withStartTime(long startTime) {
            this.startTime = startTime;
            return this;
        }

        ProcSummary build() {
            return new ProcSummary(this);
        }
    }

    public String toString() {
        return String.format("ProcSummary(name: '%s', cmdline: '%s', pid: '%s', parent: '%s', realUid: '%s', startTime: %d)", this.name, this.cmdline, this.pid, this.parent, this.realUid, Long.valueOf(this.startTime));
    }

    public int hashCode() {
        return this.pid.hashCode();
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof ProcSummary)) {
            return false;
        }
        ProcSummary procSummary = (ProcSummary) o;
        return procSummary.name.equals(this.name) && procSummary.pid.equals(this.pid) && procSummary.parent.equals(this.parent) && procSummary.realUid.equals(this.realUid) && procSummary.cmdline.equals(this.cmdline) && procSummary.startTime == this.startTime;
    }
}
