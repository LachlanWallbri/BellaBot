package com.airbnb.lottie.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class KeyPath {
    private final List<String> keys;
    private KeyPathElement resolvedElement;

    public KeyPath(String... strArr) {
        this.keys = Arrays.asList(strArr);
    }

    private KeyPath(KeyPath keyPath) {
        this.keys = new ArrayList(keyPath.keys);
        this.resolvedElement = keyPath.resolvedElement;
    }

    public KeyPath addKey(String str) {
        KeyPath keyPath = new KeyPath(this);
        keyPath.keys.add(str);
        return keyPath;
    }

    public KeyPath resolve(KeyPathElement keyPathElement) {
        KeyPath keyPath = new KeyPath(this);
        keyPath.resolvedElement = keyPathElement;
        return keyPath;
    }

    public KeyPathElement getResolvedElement() {
        return this.resolvedElement;
    }

    public boolean matches(String str, int i) {
        if (isContainer(str)) {
            return true;
        }
        if (i >= this.keys.size()) {
            return false;
        }
        return this.keys.get(i).equals(str) || this.keys.get(i).equals("**") || this.keys.get(i).equals("*");
    }

    public int incrementDepthBy(String str, int i) {
        if (isContainer(str)) {
            return 0;
        }
        if (this.keys.get(i).equals("**")) {
            return (i != this.keys.size() - 1 && this.keys.get(i + 1).equals(str)) ? 2 : 0;
        }
        return 1;
    }

    public boolean fullyResolvesTo(String str, int i) {
        if (i >= this.keys.size()) {
            return false;
        }
        boolean z = i == this.keys.size() - 1;
        String str2 = this.keys.get(i);
        if (!str2.equals("**")) {
            return (z || (i == this.keys.size() + (-2) && endsWithGlobstar())) && (str2.equals(str) || str2.equals("*"));
        }
        if (!z && this.keys.get(i + 1).equals(str)) {
            return i == this.keys.size() + (-2) || (i == this.keys.size() + (-3) && endsWithGlobstar());
        }
        if (z) {
            return true;
        }
        int i2 = i + 1;
        if (i2 < this.keys.size() - 1) {
            return false;
        }
        return this.keys.get(i2).equals(str);
    }

    public boolean propagateToChildren(String str, int i) {
        return "__container".equals(str) || i < this.keys.size() - 1 || this.keys.get(i).equals("**");
    }

    private boolean isContainer(String str) {
        return "__container".equals(str);
    }

    private boolean endsWithGlobstar() {
        return this.keys.get(r0.size() - 1).equals("**");
    }

    public String keysToString() {
        return this.keys.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("KeyPath{keys=");
        sb.append(this.keys);
        sb.append(",resolved=");
        sb.append(this.resolvedElement != null);
        sb.append('}');
        return sb.toString();
    }
}
