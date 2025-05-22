package com.aliyun.alink.linksdk.tmp.data.script;

import com.aliyun.alink.linksdk.tmp.utils.TextHelper;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ScriptGetRequestPayload {
    public List<ScriptRequestItem> params;

    /* renamed from: id */
    public int f1020id = TextHelper.getRandomInt();
    public String version = "1.0";
    public String method = "thing.script.get";

    public ScriptGetRequestPayload(List<ScriptRequestItem> list) {
        this.params = list;
    }
}
