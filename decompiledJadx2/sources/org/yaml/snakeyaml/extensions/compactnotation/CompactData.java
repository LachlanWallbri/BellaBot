package org.yaml.snakeyaml.extensions.compactnotation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class CompactData {
    private String prefix;
    private List<String> arguments = new ArrayList();
    private Map<String, String> properties = new HashMap();

    public CompactData(String str) {
        this.prefix = str;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public Map<String, String> getProperties() {
        return this.properties;
    }

    public List<String> getArguments() {
        return this.arguments;
    }

    public String toString() {
        return "CompactData: " + this.prefix + " " + this.properties;
    }
}
