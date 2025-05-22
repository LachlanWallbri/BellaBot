package org.simpleframework.xml.filter;

import java.util.Map;

/* loaded from: classes9.dex */
public class MapFilter implements Filter {
    private Filter filter;
    private Map map;

    public MapFilter(Map map) {
        this(map, null);
    }

    public MapFilter(Map map, Filter filter) {
        this.filter = filter;
        this.map = map;
    }

    @Override // org.simpleframework.xml.filter.Filter
    public String replace(String str) {
        Map map = this.map;
        Object obj = map != null ? map.get(str) : null;
        if (obj != null) {
            return obj.toString();
        }
        Filter filter = this.filter;
        if (filter != null) {
            return filter.replace(str);
        }
        return null;
    }
}
