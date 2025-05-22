package org.simpleframework.xml.core;

import java.util.Iterator;
import java.util.LinkedHashMap;

/* loaded from: classes9.dex */
class ModelMap extends LinkedHashMap<String, ModelList> implements Iterable<ModelList> {
    private final Detail detail;

    public ModelMap(Detail detail) {
        this.detail = detail;
    }

    public ModelMap getModels() throws Exception {
        ModelMap modelMap = new ModelMap(this.detail);
        for (String str : keySet()) {
            ModelList modelList = get(str);
            if (modelList != null) {
                modelList = modelList.build();
            }
            if (modelMap.containsKey(str)) {
                throw new PathException("Path with name '%s' is a duplicate in %s ", str, this.detail);
            }
            modelMap.put(str, modelList);
        }
        return modelMap;
    }

    public Model lookup(String str, int i) {
        ModelList modelList = get(str);
        if (modelList != null) {
            return modelList.lookup(i);
        }
        return null;
    }

    public void register(String str, Model model) {
        ModelList modelList = (ModelList) get(str);
        if (modelList == null) {
            modelList = new ModelList();
            put(str, modelList);
        }
        modelList.register(model);
    }

    @Override // java.lang.Iterable
    public Iterator<ModelList> iterator() {
        return values().iterator();
    }
}
