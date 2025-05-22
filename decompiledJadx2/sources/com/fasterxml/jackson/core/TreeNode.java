package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public interface TreeNode {
    JsonToken asToken();

    /* renamed from: at */
    TreeNode mo563at(JsonPointer jsonPointer);

    /* renamed from: at */
    TreeNode mo564at(String str) throws IllegalArgumentException;

    Iterator<String> fieldNames();

    TreeNode get(int i);

    TreeNode get(String str);

    boolean isArray();

    boolean isContainerNode();

    boolean isMissingNode();

    boolean isObject();

    boolean isValueNode();

    JsonParser.NumberType numberType();

    TreeNode path(int i);

    TreeNode path(String str);

    int size();

    JsonParser traverse();

    JsonParser traverse(ObjectCodec objectCodec);
}
