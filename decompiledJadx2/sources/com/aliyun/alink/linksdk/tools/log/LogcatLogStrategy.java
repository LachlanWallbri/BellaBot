package com.aliyun.alink.linksdk.tools.log;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LogcatLogStrategy implements ILogStrategy {
    @Override // com.aliyun.alink.linksdk.tools.log.ILogStrategy
    public boolean isSupport() {
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tools.log.ILogStrategy
    public void log(int i, String str, String str2) {
        LogHelper.print(i, str, str2);
    }
}
