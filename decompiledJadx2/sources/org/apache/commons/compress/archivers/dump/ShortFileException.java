package org.apache.commons.compress.archivers.dump;

/* loaded from: classes7.dex */
public class ShortFileException extends DumpArchiveException {
    private static final long serialVersionUID = 1;

    public ShortFileException() {
        super("unexpected EOF");
    }
}
