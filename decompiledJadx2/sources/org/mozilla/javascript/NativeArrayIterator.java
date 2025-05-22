package org.mozilla.javascript;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class NativeArrayIterator extends ES6Iterator {
    private static final String ITERATOR_TAG = "ArrayIterator";
    private static final long serialVersionUID = 1;
    private Scriptable arrayLike;
    private int index;

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Array Iterator";
    }

    @Override // org.mozilla.javascript.ES6Iterator
    protected String getTag() {
        return ITERATOR_TAG;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(ScriptableObject scriptableObject, boolean z) {
        ES6Iterator.init(scriptableObject, z, new NativeArrayIterator(), ITERATOR_TAG);
    }

    private NativeArrayIterator() {
    }

    public NativeArrayIterator(Scriptable scriptable, Scriptable scriptable2) {
        super(scriptable);
        this.index = 0;
        this.arrayLike = scriptable2;
    }

    @Override // org.mozilla.javascript.ES6Iterator
    protected boolean isDone(Context context, Scriptable scriptable) {
        return ((long) this.index) >= NativeArray.getLengthProperty(context, this.arrayLike);
    }

    @Override // org.mozilla.javascript.ES6Iterator
    protected Object nextValue(Context context, Scriptable scriptable) {
        Scriptable scriptable2 = this.arrayLike;
        int i = this.index;
        this.index = i + 1;
        Object obj = scriptable2.get(i, scriptable2);
        return obj == ScriptableObject.NOT_FOUND ? Undefined.instance : obj;
    }
}
