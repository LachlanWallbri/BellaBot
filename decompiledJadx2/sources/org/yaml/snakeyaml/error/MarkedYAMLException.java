package org.yaml.snakeyaml.error;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class MarkedYAMLException extends YAMLException {
    private static final long serialVersionUID = -9119388488683035101L;
    private String context;
    private Mark contextMark;
    private String note;
    private String problem;
    private Mark problemMark;

    /* JADX INFO: Access modifiers changed from: protected */
    public MarkedYAMLException(String str, Mark mark, String str2, Mark mark2, String str3) {
        this(str, mark, str2, mark2, str3, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MarkedYAMLException(String str, Mark mark, String str2, Mark mark2, String str3, Throwable th) {
        super(str + "; " + str2 + "; " + mark2, th);
        this.context = str;
        this.contextMark = mark;
        this.problem = str2;
        this.problemMark = mark2;
        this.note = str3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MarkedYAMLException(String str, Mark mark, String str2, Mark mark2) {
        this(str, mark, str2, mark2, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MarkedYAMLException(String str, Mark mark, String str2, Mark mark2, Throwable th) {
        this(str, mark, str2, mark2, null, th);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return toString();
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.context;
        if (str != null) {
            sb.append(str);
            sb.append("\n");
        }
        Mark mark = this.contextMark;
        if (mark != null && (this.problem == null || this.problemMark == null || mark.getName().equals(this.problemMark.getName()) || this.contextMark.getLine() != this.problemMark.getLine() || this.contextMark.getColumn() != this.problemMark.getColumn())) {
            sb.append(this.contextMark.toString());
            sb.append("\n");
        }
        String str2 = this.problem;
        if (str2 != null) {
            sb.append(str2);
            sb.append("\n");
        }
        Mark mark2 = this.problemMark;
        if (mark2 != null) {
            sb.append(mark2.toString());
            sb.append("\n");
        }
        String str3 = this.note;
        if (str3 != null) {
            sb.append(str3);
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getContext() {
        return this.context;
    }

    public Mark getContextMark() {
        return this.contextMark;
    }

    public String getProblem() {
        return this.problem;
    }

    public Mark getProblemMark() {
        return this.problemMark;
    }
}
