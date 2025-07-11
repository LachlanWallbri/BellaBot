package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import java.util.HashSet;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class DupDetector {
    protected String _firstName;
    protected String _secondName;
    protected HashSet<String> _seen;
    protected final Object _source;

    private DupDetector(Object obj) {
        this._source = obj;
    }

    public static DupDetector rootDetector(JsonParser jsonParser) {
        return new DupDetector(jsonParser);
    }

    public static DupDetector rootDetector(JsonGenerator jsonGenerator) {
        return new DupDetector(jsonGenerator);
    }

    public DupDetector child() {
        return new DupDetector(this._source);
    }

    public void reset() {
        this._firstName = null;
        this._secondName = null;
        this._seen = null;
    }

    public JsonLocation findLocation() {
        Object obj = this._source;
        if (obj instanceof JsonParser) {
            return ((JsonParser) obj).getCurrentLocation();
        }
        return null;
    }

    public Object getSource() {
        return this._source;
    }

    public boolean isDup(String str) throws JsonParseException {
        String str2 = this._firstName;
        if (str2 == null) {
            this._firstName = str;
            return false;
        }
        if (str.equals(str2)) {
            return true;
        }
        String str3 = this._secondName;
        if (str3 == null) {
            this._secondName = str;
            return false;
        }
        if (str.equals(str3)) {
            return true;
        }
        if (this._seen == null) {
            this._seen = new HashSet<>(16);
            this._seen.add(this._firstName);
            this._seen.add(this._secondName);
        }
        return !this._seen.add(str);
    }
}
