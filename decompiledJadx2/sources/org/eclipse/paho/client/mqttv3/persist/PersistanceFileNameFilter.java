package org.eclipse.paho.client.mqttv3.persist;

import java.io.File;
import java.io.FilenameFilter;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class PersistanceFileNameFilter implements FilenameFilter {
    private final String fileExtension;

    public PersistanceFileNameFilter(String str) {
        this.fileExtension = str;
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return str.endsWith(this.fileExtension);
    }
}
