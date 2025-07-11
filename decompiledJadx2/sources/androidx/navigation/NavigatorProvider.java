package androidx.navigation;

import androidx.navigation.Navigator;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class NavigatorProvider {
    private static final HashMap<Class<?>, String> sAnnotationNames = new HashMap<>();
    private final HashMap<String, Navigator<? extends NavDestination>> mNavigators = new HashMap<>();

    private static boolean validateName(String str) {
        return (str == null || str.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getNameForNavigator(Class<? extends Navigator> cls) {
        String str = sAnnotationNames.get(cls);
        if (str == null) {
            Navigator.Name name = (Navigator.Name) cls.getAnnotation(Navigator.Name.class);
            str = name != null ? name.value() : null;
            if (!validateName(str)) {
                throw new IllegalArgumentException("No @Navigator.Name annotation found for " + cls.getSimpleName());
            }
            sAnnotationNames.put(cls, str);
        }
        return str;
    }

    public final <T extends Navigator<?>> T getNavigator(Class<T> cls) {
        return (T) getNavigator(getNameForNavigator(cls));
    }

    public <T extends Navigator<?>> T getNavigator(String str) {
        if (!validateName(str)) {
            throw new IllegalArgumentException("navigator name cannot be an empty string");
        }
        Navigator<? extends NavDestination> navigator = this.mNavigators.get(str);
        if (navigator != null) {
            return navigator;
        }
        throw new IllegalStateException("Could not find Navigator with name \"" + str + "\". You must call NavController.addNavigator() for each navigation type.");
    }

    public final Navigator<? extends NavDestination> addNavigator(Navigator<? extends NavDestination> navigator) {
        return addNavigator(getNameForNavigator(navigator.getClass()), navigator);
    }

    public Navigator<? extends NavDestination> addNavigator(String str, Navigator<? extends NavDestination> navigator) {
        if (!validateName(str)) {
            throw new IllegalArgumentException("navigator name cannot be an empty string");
        }
        return this.mNavigators.put(str, navigator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, Navigator<? extends NavDestination>> getNavigators() {
        return this.mNavigators;
    }
}
