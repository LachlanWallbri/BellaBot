package org.apache.commons.compress.harmony.pack200;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.compress.java.util.jar.Pack200;

/* loaded from: classes9.dex */
public abstract class Pack200Adapter {
    protected static final int DEFAULT_BUFFER_SIZE = 8192;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final SortedMap<String, String> properties = new TreeMap();

    public SortedMap<String, String> properties() {
        return this.properties;
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        this.support.addPropertyChangeListener(propertyChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void firePropertyChange(String str, Object obj, Object obj2) {
        this.support.firePropertyChange(str, obj, obj2);
    }

    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        this.support.removePropertyChangeListener(propertyChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void completed(double d) {
        firePropertyChange(Pack200.Packer.PROGRESS, null, String.valueOf((int) (d * 100.0d)));
    }
}
