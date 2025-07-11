package io.opencensus.trace;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import io.opencensus.internal.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class Tracestate {
    private static final int KEY_MAX_SIZE = 256;
    private static final int MAX_KEY_VALUE_PAIRS = 32;
    private static final int VALUE_MAX_SIZE = 256;

    public abstract List<Entry> getEntries();

    @Nullable
    public String get(String str) {
        for (Entry entry : getEntries()) {
            if (entry.getKey().equals(str)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static final class Builder {
        private static final Tracestate EMPTY = Tracestate.create(Collections.emptyList());

        @Nullable
        private ArrayList<Entry> entries;
        private final Tracestate parent;

        private Builder(Tracestate tracestate) {
            Utils.checkNotNull(tracestate, "parent");
            this.parent = tracestate;
            this.entries = null;
        }

        public Builder set(String str, String str2) {
            Entry create = Entry.create(str, str2);
            if (this.entries == null) {
                this.entries = new ArrayList<>(this.parent.getEntries());
            }
            int i = 0;
            while (true) {
                if (i >= this.entries.size()) {
                    break;
                }
                if (this.entries.get(i).getKey().equals(create.getKey())) {
                    this.entries.remove(i);
                    break;
                }
                i++;
            }
            this.entries.add(0, create);
            return this;
        }

        public Builder remove(String str) {
            Utils.checkNotNull(str, TransferTable.COLUMN_KEY);
            if (this.entries == null) {
                this.entries = new ArrayList<>(this.parent.getEntries());
            }
            int i = 0;
            while (true) {
                if (i >= this.entries.size()) {
                    break;
                }
                if (this.entries.get(i).getKey().equals(str)) {
                    this.entries.remove(i);
                    break;
                }
                i++;
            }
            return this;
        }

        public Tracestate build() {
            ArrayList<Entry> arrayList = this.entries;
            if (arrayList != null) {
                return Tracestate.create(arrayList);
            }
            return this.parent;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class Entry {
        public abstract String getKey();

        public abstract String getValue();

        public static Entry create(String str, String str2) {
            Utils.checkNotNull(str, TransferTable.COLUMN_KEY);
            Utils.checkNotNull(str2, ES6Iterator.VALUE_PROPERTY);
            Utils.checkArgument(Tracestate.validateKey(str), "Invalid key %s", str);
            Utils.checkArgument(Tracestate.validateValue(str2), "Invalid value %s", str2);
            return new AutoValue_Tracestate_Entry(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean validateKey(String str) {
        if (str.length() > 256 || str.isEmpty() || str.charAt(0) < 'a' || str.charAt(0) > 'z') {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if ((charAt < 'a' || charAt > 'z') && !((charAt >= '0' && charAt <= '9') || charAt == '_' || charAt == '-' || charAt == '*' || charAt == '/')) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean validateValue(String str) {
        if (str.length() > 256 || str.charAt(str.length() - 1) == ' ') {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == ',' || charAt == '=' || charAt < ' ' || charAt > '~') {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Tracestate create(List<Entry> list) {
        Utils.checkState(list.size() <= 32, "Invalid size");
        return new AutoValue_Tracestate(Collections.unmodifiableList(list));
    }
}
