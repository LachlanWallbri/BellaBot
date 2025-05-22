package com.google.common.io;

import java.nio.file.FileSystemException;

/* loaded from: classes3.dex */
public final class InsecureRecursiveDeleteException extends FileSystemException {
    public InsecureRecursiveDeleteException(String str) {
        super(str, null, "unable to guarantee security of recursive delete");
    }
}
