package org.eclipse.paho.client.mqttv3.persist;

import java.io.File;
import java.io.FileFilter;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class PersistanceFileFilter implements FileFilter {
    private final String fileExtension;

    public PersistanceFileFilter(String str) {
        this.fileExtension = str;
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        return file.getName().endsWith(this.fileExtension);
    }
}
