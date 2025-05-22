package com.amitshekhar.model;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Response {
    public int dbVersion;
    public String error;
    public boolean isSuccessful;
    public List<Object> rows = new ArrayList();
    public List<String> columns = new ArrayList();
}
