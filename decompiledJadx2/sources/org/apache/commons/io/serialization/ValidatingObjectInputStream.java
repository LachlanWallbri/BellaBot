package org.apache.commons.io.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class ValidatingObjectInputStream extends ObjectInputStream {
    private final List<ClassNameMatcher> acceptMatchers;
    private final List<ClassNameMatcher> rejectMatchers;

    public ValidatingObjectInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
        this.acceptMatchers = new ArrayList();
        this.rejectMatchers = new ArrayList();
    }

    private void validateClassName(String str) throws InvalidClassException {
        Iterator<ClassNameMatcher> it = this.rejectMatchers.iterator();
        while (it.hasNext()) {
            if (it.next().matches(str)) {
                invalidClassNameFound(str);
            }
        }
        boolean z = false;
        Iterator<ClassNameMatcher> it2 = this.acceptMatchers.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            } else if (it2.next().matches(str)) {
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        invalidClassNameFound(str);
    }

    protected void invalidClassNameFound(String str) throws InvalidClassException {
        throw new InvalidClassException("Class name not accepted: " + str);
    }

    @Override // java.io.ObjectInputStream
    protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
        validateClassName(objectStreamClass.getName());
        return super.resolveClass(objectStreamClass);
    }

    public ValidatingObjectInputStream accept(Class<?>... clsArr) {
        for (Class<?> cls : clsArr) {
            this.acceptMatchers.add(new FullClassNameMatcher(cls.getName()));
        }
        return this;
    }

    public ValidatingObjectInputStream reject(Class<?>... clsArr) {
        for (Class<?> cls : clsArr) {
            this.rejectMatchers.add(new FullClassNameMatcher(cls.getName()));
        }
        return this;
    }

    public ValidatingObjectInputStream accept(String... strArr) {
        for (String str : strArr) {
            this.acceptMatchers.add(new WildcardClassNameMatcher(str));
        }
        return this;
    }

    public ValidatingObjectInputStream reject(String... strArr) {
        for (String str : strArr) {
            this.rejectMatchers.add(new WildcardClassNameMatcher(str));
        }
        return this;
    }

    public ValidatingObjectInputStream accept(Pattern pattern) {
        this.acceptMatchers.add(new RegexpClassNameMatcher(pattern));
        return this;
    }

    public ValidatingObjectInputStream reject(Pattern pattern) {
        this.rejectMatchers.add(new RegexpClassNameMatcher(pattern));
        return this;
    }

    public ValidatingObjectInputStream accept(ClassNameMatcher classNameMatcher) {
        this.acceptMatchers.add(classNameMatcher);
        return this;
    }

    public ValidatingObjectInputStream reject(ClassNameMatcher classNameMatcher) {
        this.rejectMatchers.add(classNameMatcher);
        return this;
    }
}
