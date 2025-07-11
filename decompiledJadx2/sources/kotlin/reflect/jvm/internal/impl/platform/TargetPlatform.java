package kotlin.reflect.jvm.internal.impl.platform;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: TargetPlatform.kt */
/* loaded from: classes8.dex */
public class TargetPlatform implements Collection<SimplePlatform>, KMappedMarker {
    private final Set<SimplePlatform> componentPlatforms;

    @Override // java.util.Collection
    public /* synthetic */ boolean add(SimplePlatform simplePlatform) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends SimplePlatform> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean contains(SimplePlatform element) {
        Intrinsics.checkParameterIsNotNull(element, "element");
        return this.componentPlatforms.contains(element);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<? extends Object> elements) {
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return this.componentPlatforms.containsAll(elements);
    }

    public int getSize() {
        return this.componentPlatforms.size();
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.componentPlatforms.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<SimplePlatform> iterator() {
        return this.componentPlatforms.iterator();
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) CollectionToArray.toArray(this, tArr);
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof SimplePlatform) {
            return contains((SimplePlatform) obj);
        }
        return false;
    }

    public final Set<SimplePlatform> getComponentPlatforms() {
        return this.componentPlatforms;
    }

    @Override // java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    public String toString() {
        return PlatformUtilKt.getPresentableDescription(this);
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof TargetPlatform) && !(Intrinsics.areEqual(this.componentPlatforms, ((TargetPlatform) obj).componentPlatforms) ^ true);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return this.componentPlatforms.hashCode();
    }
}
