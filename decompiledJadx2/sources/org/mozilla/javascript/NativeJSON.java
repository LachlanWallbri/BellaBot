package org.mozilla.javascript;

import com.fasterxml.jackson.core.JsonFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import org.mozilla.javascript.json.JsonParser;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class NativeJSON extends IdScriptableObject {
    private static final int Id_parse = 2;
    private static final int Id_stringify = 3;
    private static final int Id_toSource = 1;
    private static final Object JSON_TAG = JsonFactory.FORMAT_NAME_JSON;
    private static final int LAST_METHOD_ID = 3;
    private static final int MAX_ID = 3;
    private static final int MAX_STRINGIFY_GAP_LENGTH = 10;
    static final long serialVersionUID = -4567599697595654984L;

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return JsonFactory.FORMAT_NAME_JSON;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(Scriptable scriptable, boolean z) {
        NativeJSON nativeJSON = new NativeJSON();
        nativeJSON.activatePrototypeMap(3);
        nativeJSON.setPrototype(getObjectPrototype(scriptable));
        nativeJSON.setParentScope(scriptable);
        if (z) {
            nativeJSON.sealObject();
        }
        ScriptableObject.defineProperty(scriptable, JsonFactory.FORMAT_NAME_JSON, nativeJSON, 2);
    }

    private NativeJSON() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void initPrototypeId(int i) {
        String str;
        int i2 = 3;
        if (i <= 3) {
            if (i == 1) {
                i2 = 0;
                str = "toSource";
            } else if (i == 2) {
                str = "parse";
                i2 = 2;
            } else {
                if (i != 3) {
                    throw new IllegalStateException(String.valueOf(i));
                }
                str = "stringify";
            }
            initPrototypeMethod(JSON_TAG, i, str, i2);
            return;
        }
        throw new IllegalStateException(String.valueOf(i));
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object obj;
        Object obj2;
        Object obj3;
        if (!idFunctionObject.hasTag(JSON_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (methodId == 1) {
            return JsonFactory.FORMAT_NAME_JSON;
        }
        if (methodId == 2) {
            String scriptRuntime = ScriptRuntime.toString(objArr, 0);
            r2 = objArr.length > 1 ? objArr[1] : null;
            if (r2 instanceof Callable) {
                return parse(context, scriptable, scriptRuntime, (Callable) r2);
            }
            return parse(context, scriptable, scriptRuntime);
        }
        if (methodId == 3) {
            int length = objArr.length;
            if (length != 1) {
                if (length != 2) {
                    if (length == 3) {
                        r2 = objArr[2];
                    } else {
                        obj3 = null;
                        obj2 = null;
                        return stringify(context, scriptable, r2, obj3, obj2);
                    }
                }
                Object obj4 = r2;
                r2 = objArr[1];
                obj = obj4;
            } else {
                obj = null;
            }
            obj2 = obj;
            obj3 = r2;
            r2 = objArr[0];
            return stringify(context, scriptable, r2, obj3, obj2);
        }
        throw new IllegalStateException(String.valueOf(methodId));
    }

    private static Object parse(Context context, Scriptable scriptable, String str) {
        try {
            return new JsonParser(context, scriptable).parseValue(str);
        } catch (JsonParser.ParseException e) {
            throw ScriptRuntime.constructError("SyntaxError", e.getMessage());
        }
    }

    public static Object parse(Context context, Scriptable scriptable, String str, Callable callable) {
        Object parse = parse(context, scriptable, str);
        Scriptable newObject = context.newObject(scriptable);
        newObject.put("", newObject, parse);
        return walk(context, scriptable, callable, newObject, "");
    }

    private static Object walk(Context context, Scriptable scriptable, Callable callable, Scriptable scriptable2, Object obj) {
        Object obj2;
        if (obj instanceof Number) {
            obj2 = scriptable2.get(((Number) obj).intValue(), scriptable2);
        } else {
            obj2 = scriptable2.get((String) obj, scriptable2);
        }
        if (obj2 instanceof Scriptable) {
            Scriptable scriptable3 = (Scriptable) obj2;
            if (scriptable3 instanceof NativeArray) {
                long length = ((NativeArray) scriptable3).getLength();
                for (long j = 0; j < length; j++) {
                    if (j > 2147483647L) {
                        String l = Long.toString(j);
                        Object walk = walk(context, scriptable, callable, scriptable3, l);
                        if (walk == Undefined.instance) {
                            scriptable3.delete(l);
                        } else {
                            scriptable3.put(l, scriptable3, walk);
                        }
                    } else {
                        int i = (int) j;
                        Object walk2 = walk(context, scriptable, callable, scriptable3, Integer.valueOf(i));
                        if (walk2 == Undefined.instance) {
                            scriptable3.delete(i);
                        } else {
                            scriptable3.put(i, scriptable3, walk2);
                        }
                    }
                }
            } else {
                for (Object obj3 : scriptable3.getIds()) {
                    Object walk3 = walk(context, scriptable, callable, scriptable3, obj3);
                    if (walk3 == Undefined.instance) {
                        if (obj3 instanceof Number) {
                            scriptable3.delete(((Number) obj3).intValue());
                        } else {
                            scriptable3.delete((String) obj3);
                        }
                    } else if (obj3 instanceof Number) {
                        scriptable3.put(((Number) obj3).intValue(), scriptable3, walk3);
                    } else {
                        scriptable3.put((String) obj3, scriptable3, walk3);
                    }
                }
            }
        }
        return callable.call(context, scriptable, scriptable2, new Object[]{obj, obj2});
    }

    private static String repeat(char c, int i) {
        char[] cArr = new char[i];
        Arrays.fill(cArr, c);
        return new String(cArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static class StringifyState {

        /* renamed from: cx */
        Context f10200cx;
        String gap;
        String indent;
        List<Object> propertyList;
        Callable replacer;
        Scriptable scope;
        Object space;
        Stack<Scriptable> stack = new Stack<>();

        StringifyState(Context context, Scriptable scriptable, String str, String str2, Callable callable, List<Object> list, Object obj) {
            this.f10200cx = context;
            this.scope = scriptable;
            this.indent = str;
            this.gap = str2;
            this.replacer = callable;
            this.propertyList = list;
            this.space = obj;
        }
    }

    public static Object stringify(Context context, Scriptable scriptable, Object obj, Object obj2, Object obj3) {
        Callable callable;
        LinkedList linkedList;
        Object scriptRuntime;
        Object obj4;
        String str;
        String str2;
        if (obj2 instanceof Callable) {
            callable = (Callable) obj2;
            linkedList = null;
        } else if (obj2 instanceof NativeArray) {
            LinkedList linkedList2 = new LinkedList();
            NativeArray nativeArray = (NativeArray) obj2;
            for (Integer num : nativeArray.getIndexIds()) {
                Object obj5 = nativeArray.get(num.intValue(), nativeArray);
                if ((obj5 instanceof String) || (obj5 instanceof Number)) {
                    linkedList2.add(obj5);
                } else if ((obj5 instanceof NativeString) || (obj5 instanceof NativeNumber)) {
                    linkedList2.add(ScriptRuntime.toString(obj5));
                }
            }
            linkedList = linkedList2;
            callable = null;
        } else {
            callable = null;
            linkedList = null;
        }
        if (obj3 instanceof NativeNumber) {
            scriptRuntime = Double.valueOf(ScriptRuntime.toNumber(obj3));
        } else {
            scriptRuntime = obj3 instanceof NativeString ? ScriptRuntime.toString(obj3) : obj3;
        }
        if (scriptRuntime instanceof Number) {
            int min = Math.min(10, (int) ScriptRuntime.toInteger(scriptRuntime));
            str2 = min > 0 ? repeat(' ', min) : "";
            scriptRuntime = Integer.valueOf(min);
        } else if (scriptRuntime instanceof String) {
            str2 = (String) scriptRuntime;
            if (str2.length() > 10) {
                str2 = str2.substring(0, 10);
            }
        } else {
            obj4 = scriptRuntime;
            str = "";
            StringifyState stringifyState = new StringifyState(context, scriptable, "", str, callable, linkedList, obj4);
            NativeObject nativeObject = new NativeObject();
            nativeObject.setParentScope(scriptable);
            nativeObject.setPrototype(ScriptableObject.getObjectPrototype(scriptable));
            nativeObject.defineProperty("", obj, 0);
            return str("", nativeObject, stringifyState);
        }
        obj4 = scriptRuntime;
        str = str2;
        StringifyState stringifyState2 = new StringifyState(context, scriptable, "", str, callable, linkedList, obj4);
        NativeObject nativeObject2 = new NativeObject();
        nativeObject2.setParentScope(scriptable);
        nativeObject2.setPrototype(ScriptableObject.getObjectPrototype(scriptable));
        nativeObject2.defineProperty("", obj, 0);
        return str("", nativeObject2, stringifyState2);
    }

    private static Object str(Object obj, Scriptable scriptable, StringifyState stringifyState) {
        Object property;
        if (obj instanceof String) {
            property = getProperty(scriptable, (String) obj);
        } else {
            property = getProperty(scriptable, ((Number) obj).intValue());
        }
        if (property instanceof Scriptable) {
            Scriptable scriptable2 = (Scriptable) property;
            if (hasProperty(scriptable2, "toJSON") && (getProperty(scriptable2, "toJSON") instanceof Callable)) {
                property = callMethod(stringifyState.f10200cx, scriptable2, "toJSON", new Object[]{obj});
            }
        }
        if (stringifyState.replacer != null) {
            property = stringifyState.replacer.call(stringifyState.f10200cx, stringifyState.scope, scriptable, new Object[]{obj, property});
        }
        if (property instanceof NativeNumber) {
            property = Double.valueOf(ScriptRuntime.toNumber(property));
        } else if (property instanceof NativeString) {
            property = ScriptRuntime.toString(property);
        } else if (property instanceof NativeBoolean) {
            property = ((NativeBoolean) property).getDefaultValue(ScriptRuntime.BooleanClass);
        }
        if (property == null) {
            return "null";
        }
        if (property.equals(Boolean.TRUE)) {
            return "true";
        }
        if (property.equals(Boolean.FALSE)) {
            return "false";
        }
        if (property instanceof CharSequence) {
            return quote(property.toString());
        }
        if (property instanceof Number) {
            double doubleValue = ((Number) property).doubleValue();
            return (doubleValue != doubleValue || doubleValue == Double.POSITIVE_INFINITY || doubleValue == Double.NEGATIVE_INFINITY) ? "null" : ScriptRuntime.toString(property);
        }
        if ((property instanceof Scriptable) && !(property instanceof Callable)) {
            if (property instanceof NativeArray) {
                return m4179ja((NativeArray) property, stringifyState);
            }
            return m4180jo((Scriptable) property, stringifyState);
        }
        return Undefined.instance;
    }

    private static String join(Collection<Object> collection, String str) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        Iterator<Object> it = collection.iterator();
        if (!it.hasNext()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(it.next().toString());
        while (it.hasNext()) {
            sb.append(str);
            sb.append(it.next().toString());
        }
        return sb.toString();
    }

    /* renamed from: jo */
    private static String m4180jo(Scriptable scriptable, StringifyState stringifyState) {
        Object[] ids;
        String str;
        if (stringifyState.stack.search(scriptable) != -1) {
            throw ScriptRuntime.typeError0("msg.cyclic.value");
        }
        stringifyState.stack.push(scriptable);
        String str2 = stringifyState.indent;
        stringifyState.indent += stringifyState.gap;
        if (stringifyState.propertyList != null) {
            ids = stringifyState.propertyList.toArray();
        } else {
            ids = scriptable.getIds();
        }
        LinkedList linkedList = new LinkedList();
        for (Object obj : ids) {
            Object str3 = str(obj, scriptable, stringifyState);
            if (str3 != Undefined.instance) {
                String str4 = quote(obj.toString()) + ":";
                if (stringifyState.gap.length() > 0) {
                    str4 = str4 + " ";
                }
                linkedList.add(str4 + str3);
            }
        }
        if (linkedList.isEmpty()) {
            str = "{}";
        } else if (stringifyState.gap.length() == 0) {
            str = '{' + join(linkedList, ",") + '}';
        } else {
            str = "{\n" + stringifyState.indent + join(linkedList, ",\n" + stringifyState.indent) + '\n' + str2 + '}';
        }
        stringifyState.stack.pop();
        stringifyState.indent = str2;
        return str;
    }

    /* renamed from: ja */
    private static String m4179ja(NativeArray nativeArray, StringifyState stringifyState) {
        String str;
        Object str2;
        if (stringifyState.stack.search(nativeArray) != -1) {
            throw ScriptRuntime.typeError0("msg.cyclic.value");
        }
        stringifyState.stack.push(nativeArray);
        String str3 = stringifyState.indent;
        stringifyState.indent += stringifyState.gap;
        LinkedList linkedList = new LinkedList();
        long length = nativeArray.getLength();
        for (long j = 0; j < length; j++) {
            if (j > 2147483647L) {
                str2 = str(Long.toString(j), nativeArray, stringifyState);
            } else {
                str2 = str(Integer.valueOf((int) j), nativeArray, stringifyState);
            }
            if (str2 == Undefined.instance) {
                linkedList.add("null");
            } else {
                linkedList.add(str2);
            }
        }
        if (linkedList.isEmpty()) {
            str = "[]";
        } else if (stringifyState.gap.length() == 0) {
            str = '[' + join(linkedList, ",") + ']';
        } else {
            str = "[\n" + stringifyState.indent + join(linkedList, ",\n" + stringifyState.indent) + '\n' + str3 + ']';
        }
        stringifyState.stack.pop();
        stringifyState.indent = str3;
        return str;
    }

    private static String quote(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 2);
        sb.append('\"');
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt != '\f') {
                if (charAt != '\r') {
                    if (charAt != '\"') {
                        if (charAt == '\\') {
                            sb.append("\\\\");
                        } else {
                            switch (charAt) {
                                case '\b':
                                    sb.append("\\b");
                                    break;
                                case '\t':
                                    sb.append("\\t");
                                    break;
                                case '\n':
                                    sb.append("\\n");
                                    break;
                                default:
                                    if (charAt < ' ') {
                                        sb.append("\\u");
                                        sb.append(String.format("%04x", Integer.valueOf(charAt)));
                                        break;
                                    } else {
                                        sb.append(charAt);
                                        break;
                                    }
                            }
                        }
                    } else {
                        sb.append("\\\"");
                    }
                } else {
                    sb.append("\\r");
                }
            } else {
                sb.append("\\f");
            }
        }
        sb.append('\"');
        return sb.toString();
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        int i;
        String str2;
        int i2;
        String str3;
        int length = str.length();
        if (length == 5) {
            i = 2;
            str2 = "parse";
        } else if (length == 8) {
            i = 1;
            str2 = "toSource";
        } else {
            if (length != 9) {
                str3 = null;
                i2 = 0;
                if (str3 != null || str3 == str || str3.equals(str)) {
                    return i2;
                }
                return 0;
            }
            i = 3;
            str2 = "stringify";
        }
        String str4 = str2;
        i2 = i;
        str3 = str4;
        if (str3 != null) {
        }
        return i2;
    }
}
