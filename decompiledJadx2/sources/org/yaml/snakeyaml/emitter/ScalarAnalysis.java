package org.yaml.snakeyaml.emitter;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class ScalarAnalysis {
    public boolean allowBlock;
    public boolean allowBlockPlain;
    public boolean allowFlowPlain;
    public boolean allowSingleQuoted;
    public boolean empty;
    public boolean multiline;
    public String scalar;

    public ScalarAnalysis(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.scalar = str;
        this.empty = z;
        this.multiline = z2;
        this.allowFlowPlain = z3;
        this.allowBlockPlain = z4;
        this.allowSingleQuoted = z5;
        this.allowBlock = z6;
    }
}
