package com.amazonaws.cognito.clientcontext.datacollection;

import android.content.Context;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class DataCollector {
    public abstract Map<String, String> collect(Context context);
}
