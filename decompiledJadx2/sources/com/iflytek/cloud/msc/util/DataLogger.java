package com.iflytek.cloud.msc.util;

import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes3.dex */
public class DataLogger {
    private HashMap<String, ConcurrentLinkedQueue<byte[]>> bufferMap = new HashMap<>();

    public void addData(String str, byte[] bArr) {
        if (this.bufferMap.containsKey(str)) {
            this.bufferMap.get(str).add(bArr);
            return;
        }
        ConcurrentLinkedQueue<byte[]> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        concurrentLinkedQueue.add(bArr);
        this.bufferMap.put(str, concurrentLinkedQueue);
    }

    public void saveToFile() {
        for (String str : this.bufferMap.keySet()) {
            FileUtil.saveFile(this.bufferMap.get(str), str);
        }
    }
}
