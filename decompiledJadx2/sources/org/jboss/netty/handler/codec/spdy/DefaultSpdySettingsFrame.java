package org.jboss.netty.handler.codec.spdy;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.jboss.netty.util.internal.StringUtil;

/* loaded from: classes7.dex */
public class DefaultSpdySettingsFrame implements SpdySettingsFrame {
    private boolean clear;
    private final Map<Integer, Setting> settingsMap = new TreeMap();

    @Override // org.jboss.netty.handler.codec.spdy.SpdySettingsFrame
    public Set<Integer> getIDs() {
        return getIds();
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySettingsFrame
    public Set<Integer> getIds() {
        return this.settingsMap.keySet();
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySettingsFrame
    public boolean isSet(int i) {
        return this.settingsMap.containsKey(new Integer(i));
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySettingsFrame
    public int getValue(int i) {
        Integer num = new Integer(i);
        if (this.settingsMap.containsKey(num)) {
            return this.settingsMap.get(num).getValue();
        }
        return -1;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySettingsFrame
    public void setValue(int i, int i2) {
        setValue(i, i2, false, false);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySettingsFrame
    public void setValue(int i, int i2, boolean z, boolean z2) {
        if (i <= 0 || i > 16777215) {
            throw new IllegalArgumentException("Setting ID is not valid: " + i);
        }
        Integer num = new Integer(i);
        if (this.settingsMap.containsKey(num)) {
            Setting setting = this.settingsMap.get(num);
            setting.setValue(i2);
            setting.setPersist(z);
            setting.setPersisted(z2);
            return;
        }
        this.settingsMap.put(num, new Setting(i2, z, z2));
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySettingsFrame
    public void removeValue(int i) {
        Integer num = new Integer(i);
        if (this.settingsMap.containsKey(num)) {
            this.settingsMap.remove(num);
        }
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySettingsFrame
    public boolean persistValue(int i) {
        return isPersistValue(i);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySettingsFrame
    public boolean isPersistValue(int i) {
        Integer num = new Integer(i);
        if (this.settingsMap.containsKey(num)) {
            return this.settingsMap.get(num).isPersist();
        }
        return false;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySettingsFrame
    public void setPersistValue(int i, boolean z) {
        Integer num = new Integer(i);
        if (this.settingsMap.containsKey(num)) {
            this.settingsMap.get(num).setPersist(z);
        }
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySettingsFrame
    public boolean isPersisted(int i) {
        Integer num = new Integer(i);
        if (this.settingsMap.containsKey(num)) {
            return this.settingsMap.get(num).isPersisted();
        }
        return false;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySettingsFrame
    public void setPersisted(int i, boolean z) {
        Integer num = new Integer(i);
        if (this.settingsMap.containsKey(num)) {
            this.settingsMap.get(num).setPersisted(z);
        }
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySettingsFrame
    public boolean clearPreviouslyPersistedSettings() {
        return this.clear;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdySettingsFrame
    public void setClearPreviouslyPersistedSettings(boolean z) {
        this.clear = z;
    }

    private Set<Map.Entry<Integer, Setting>> getSettings() {
        return this.settingsMap.entrySet();
    }

    private void appendSettings(StringBuilder sb) {
        for (Map.Entry<Integer, Setting> entry : getSettings()) {
            Setting value = entry.getValue();
            sb.append("--> ");
            sb.append(entry.getKey().toString());
            sb.append(":");
            sb.append(value.getValue());
            sb.append(" (persist value: ");
            sb.append(value.isPersist());
            sb.append("; persisted: ");
            sb.append(value.isPersisted());
            sb.append(')');
            sb.append(StringUtil.NEWLINE);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(StringUtil.NEWLINE);
        appendSettings(sb);
        sb.setLength(sb.length() - StringUtil.NEWLINE.length());
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static final class Setting {
        private boolean persist;
        private boolean persisted;
        private int value;

        Setting(int i, boolean z, boolean z2) {
            this.value = i;
            this.persist = z;
            this.persisted = z2;
        }

        int getValue() {
            return this.value;
        }

        void setValue(int i) {
            this.value = i;
        }

        boolean isPersist() {
            return this.persist;
        }

        void setPersist(boolean z) {
            this.persist = z;
        }

        boolean isPersisted() {
            return this.persisted;
        }

        void setPersisted(boolean z) {
            this.persisted = z;
        }
    }
}
