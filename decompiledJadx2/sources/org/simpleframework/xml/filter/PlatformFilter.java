package org.simpleframework.xml.filter;

import java.util.Map;

/* loaded from: classes9.dex */
public class PlatformFilter extends StackFilter {
    public PlatformFilter() {
        this(null);
    }

    public PlatformFilter(Map map) {
        push(new EnvironmentFilter());
        push(new SystemFilter());
        push(new MapFilter(map));
    }
}
