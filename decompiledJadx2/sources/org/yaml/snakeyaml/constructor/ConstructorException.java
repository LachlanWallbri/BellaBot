package org.yaml.snakeyaml.constructor;

import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.error.MarkedYAMLException;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class ConstructorException extends MarkedYAMLException {
    private static final long serialVersionUID = -8816339931365239910L;

    /* JADX INFO: Access modifiers changed from: protected */
    public ConstructorException(String str, Mark mark, String str2, Mark mark2, Throwable th) {
        super(str, mark, str2, mark2, th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ConstructorException(String str, Mark mark, String str2, Mark mark2) {
        this(str, mark, str2, mark2, null);
    }
}
