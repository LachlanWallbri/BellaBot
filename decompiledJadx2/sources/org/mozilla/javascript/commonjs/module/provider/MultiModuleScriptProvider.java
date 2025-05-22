package org.mozilla.javascript.commonjs.module.provider;

import java.net.URI;
import java.util.Iterator;
import java.util.LinkedList;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.commonjs.module.ModuleScript;
import org.mozilla.javascript.commonjs.module.ModuleScriptProvider;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class MultiModuleScriptProvider implements ModuleScriptProvider {
    private final ModuleScriptProvider[] providers;

    public MultiModuleScriptProvider(Iterable<? extends ModuleScriptProvider> iterable) {
        LinkedList linkedList = new LinkedList();
        Iterator<? extends ModuleScriptProvider> it = iterable.iterator();
        while (it.hasNext()) {
            linkedList.add(it.next());
        }
        this.providers = (ModuleScriptProvider[]) linkedList.toArray(new ModuleScriptProvider[linkedList.size()]);
    }

    @Override // org.mozilla.javascript.commonjs.module.ModuleScriptProvider
    public ModuleScript getModuleScript(Context context, String str, URI uri, URI uri2, Scriptable scriptable) throws Exception {
        for (ModuleScriptProvider moduleScriptProvider : this.providers) {
            ModuleScript moduleScript = moduleScriptProvider.getModuleScript(context, str, uri, uri2, scriptable);
            if (moduleScript != null) {
                return moduleScript;
            }
        }
        return null;
    }
}
