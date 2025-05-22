package org.mozilla.javascript;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class NativeStringIterator extends ES6Iterator {
    private static final String ITERATOR_TAG = "StringIterator";
    private static final long serialVersionUID = 1;
    private int index;
    private String string;

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "String Iterator";
    }

    @Override // org.mozilla.javascript.ES6Iterator
    protected String getTag() {
        return ITERATOR_TAG;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(ScriptableObject scriptableObject, boolean z) {
        ES6Iterator.init(scriptableObject, z, new NativeStringIterator(), ITERATOR_TAG);
    }

    private NativeStringIterator() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NativeStringIterator(Scriptable scriptable, Scriptable scriptable2) {
        super(scriptable);
        this.index = 0;
        this.string = ScriptRuntime.toString(scriptable2);
    }

    @Override // org.mozilla.javascript.ES6Iterator
    protected boolean isDone(Context context, Scriptable scriptable) {
        return this.index >= this.string.length();
    }

    @Override // org.mozilla.javascript.ES6Iterator
    protected Object nextValue(Context context, Scriptable scriptable) {
        int offsetByCodePoints = this.string.offsetByCodePoints(this.index, 1);
        String substring = this.string.substring(this.index, offsetByCodePoints);
        this.index = offsetByCodePoints;
        return substring;
    }
}
