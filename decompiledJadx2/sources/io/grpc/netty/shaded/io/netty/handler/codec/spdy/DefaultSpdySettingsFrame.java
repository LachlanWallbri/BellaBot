package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultSpdySettingsFrame implements SpdySettingsFrame {
    private boolean clear;
    private final Map<Integer, Setting> settingsMap = new TreeMap();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySettingsFrame
    public Set<Integer> ids() {
        return this.settingsMap.keySet();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySettingsFrame
    public boolean isSet(int i) {
        return this.settingsMap.containsKey(Integer.valueOf(i));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySettingsFrame
    public int getValue(int i) {
        Setting setting = this.settingsMap.get(Integer.valueOf(i));
        if (setting != null) {
            return setting.getValue();
        }
        return -1;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySettingsFrame
    public SpdySettingsFrame setValue(int i, int i2) {
        return setValue(i, i2, false, false);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySettingsFrame
    public SpdySettingsFrame setValue(int i, int i2, boolean z, boolean z2) {
        if (i < 0 || i > 16777215) {
            throw new IllegalArgumentException("Setting ID is not valid: " + i);
        }
        Integer valueOf = Integer.valueOf(i);
        Setting setting = this.settingsMap.get(valueOf);
        if (setting != null) {
            setting.setValue(i2);
            setting.setPersist(z);
            setting.setPersisted(z2);
        } else {
            this.settingsMap.put(valueOf, new Setting(i2, z, z2));
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySettingsFrame
    public SpdySettingsFrame removeValue(int i) {
        this.settingsMap.remove(Integer.valueOf(i));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySettingsFrame
    public boolean isPersistValue(int i) {
        Setting setting = this.settingsMap.get(Integer.valueOf(i));
        return setting != null && setting.isPersist();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySettingsFrame
    public SpdySettingsFrame setPersistValue(int i, boolean z) {
        Setting setting = this.settingsMap.get(Integer.valueOf(i));
        if (setting != null) {
            setting.setPersist(z);
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySettingsFrame
    public boolean isPersisted(int i) {
        Setting setting = this.settingsMap.get(Integer.valueOf(i));
        return setting != null && setting.isPersisted();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySettingsFrame
    public SpdySettingsFrame setPersisted(int i, boolean z) {
        Setting setting = this.settingsMap.get(Integer.valueOf(i));
        if (setting != null) {
            setting.setPersisted(z);
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySettingsFrame
    public boolean clearPreviouslyPersistedSettings() {
        return this.clear;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdySettingsFrame
    public SpdySettingsFrame setClearPreviouslyPersistedSettings(boolean z) {
        this.clear = z;
        return this;
    }

    private Set<Map.Entry<Integer, Setting>> getSettings() {
        return this.settingsMap.entrySet();
    }

    private void appendSettings(StringBuilder sb) {
        for (Map.Entry<Integer, Setting> entry : getSettings()) {
            Setting value = entry.getValue();
            sb.append("--> ");
            sb.append(entry.getKey());
            sb.append(':');
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
        sb.append(StringUtil.simpleClassName(this));
        sb.append(StringUtil.NEWLINE);
        appendSettings(sb);
        sb.setLength(sb.length() - StringUtil.NEWLINE.length());
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
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
