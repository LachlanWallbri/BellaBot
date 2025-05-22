package org.yaml.snakeyaml.reader;

import org.yaml.snakeyaml.error.YAMLException;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class ReaderException extends YAMLException {
    private static final long serialVersionUID = 8710781187529689083L;
    private final char character;
    private final String name;
    private final int position;

    public ReaderException(String str, int i, char c, String str2) {
        super(str2);
        this.name = str;
        this.character = c;
        this.position = i;
    }

    public String getName() {
        return this.name;
    }

    public char getCharacter() {
        return this.character;
    }

    public int getPosition() {
        return this.position;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "unacceptable character '" + this.character + "' (0x" + Integer.toHexString(this.character).toUpperCase() + ") " + getMessage() + "\nin \"" + this.name + "\", position " + this.position;
    }
}
