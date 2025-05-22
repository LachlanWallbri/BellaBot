package com.liulishuo.okdownload.core.breakpoint;

import android.util.SparseArray;
import com.liulishuo.okdownload.DownloadTask;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class KeyToIdMap {
    private final SparseArray<String> idToKeyMap;
    private final HashMap<String, Integer> keyToIdMap;

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyToIdMap() {
        this(new HashMap(), new SparseArray());
    }

    KeyToIdMap(HashMap<String, Integer> hashMap, SparseArray<String> sparseArray) {
        this.keyToIdMap = hashMap;
        this.idToKeyMap = sparseArray;
    }

    public Integer get(DownloadTask downloadTask) {
        Integer num = this.keyToIdMap.get(generateKey(downloadTask));
        if (num != null) {
            return num;
        }
        return null;
    }

    public void remove(int i) {
        String str = this.idToKeyMap.get(i);
        if (str != null) {
            this.keyToIdMap.remove(str);
            this.idToKeyMap.remove(i);
        }
    }

    public void add(DownloadTask downloadTask, int i) {
        String generateKey = generateKey(downloadTask);
        this.keyToIdMap.put(generateKey, Integer.valueOf(i));
        this.idToKeyMap.put(i, generateKey);
    }

    String generateKey(DownloadTask downloadTask) {
        return downloadTask.getUrl() + downloadTask.getUri() + downloadTask.getFilename();
    }
}
