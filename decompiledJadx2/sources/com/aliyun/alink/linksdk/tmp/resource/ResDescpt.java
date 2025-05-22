package com.aliyun.alink.linksdk.tmp.resource;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ResDescpt {
    protected List<ResElement> mEleList = new ArrayList();
    protected String mModelId;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum ResElementType {
        PROPERTY,
        SERVICE,
        EVENT,
        DISCOVERY,
        ALCS
    }

    public ResDescpt(String str) {
        this.mModelId = str;
    }

    public void addEle(ResElement resElement) {
        this.mEleList.add(resElement);
    }

    public List<ResElement> getEleList() {
        return this.mEleList;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class ResElement {
        protected ResElementType mElementType;
        protected String mIdentifier;

        public ResElement(String str, ResElementType resElementType) {
            this.mIdentifier = str;
            this.mElementType = resElementType;
        }
    }
}
