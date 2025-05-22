package com.aliyun.alink.linksdk.tmp.device.payload.permission;

import com.aliyun.alink.linksdk.tmp.device.payload.CommonRequestPayload;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class GroupAuthRequestPayload extends CommonRequestPayload<GroupAuthParam> {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class GroupAuthParam {
        public String datatype;

        /* renamed from: id */
        public String f1033id;

        /* renamed from: op */
        public String f1034op;
        public String param1;
        public String param2;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.aliyun.alink.linksdk.tmp.device.payload.permission.GroupAuthRequestPayload$GroupAuthParam, T] */
    public GroupAuthRequestPayload(String str, String str2) {
        super(str, str2);
        this.params = new GroupAuthParam();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setOp(String str) {
        ((GroupAuthParam) this.params).f1034op = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setDataType(String str) {
        ((GroupAuthParam) this.params).datatype = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setGroupId(String str) {
        ((GroupAuthParam) this.params).f1033id = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setParam1(String str) {
        ((GroupAuthParam) this.params).param1 = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setParam2(String str) {
        ((GroupAuthParam) this.params).param2 = str;
    }
}
