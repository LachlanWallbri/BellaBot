package com.amazonaws.cognito.clientcontext.datacollection;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ContextDataAggregator {
    private final List<DataCollector> dataCollectors;

    /* loaded from: classes.dex */
    private static class InstanceHolder {
        private static final ContextDataAggregator INSTANCE = new ContextDataAggregator();

        private InstanceHolder() {
        }
    }

    private ContextDataAggregator() {
        this.dataCollectors = getDataCollectors();
    }

    protected ContextDataAggregator(List<DataCollector> list) {
        this.dataCollectors = list;
    }

    public static ContextDataAggregator getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public Map<String, String> getAggregatedData(Context context) {
        HashMap hashMap = new HashMap();
        Iterator<DataCollector> it = this.dataCollectors.iterator();
        while (it.hasNext()) {
            hashMap.putAll(it.next().collect(context));
        }
        removerNullEntries(hashMap);
        return hashMap;
    }

    private void removerNullEntries(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                map.remove(entry.getKey());
            }
        }
    }

    private List<DataCollector> getDataCollectors() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ApplicationDataCollector());
        arrayList.add(new BuildDataCollector());
        arrayList.add(new DeviceDataCollector());
        arrayList.add(new TelephonyDataCollector());
        return arrayList;
    }
}
