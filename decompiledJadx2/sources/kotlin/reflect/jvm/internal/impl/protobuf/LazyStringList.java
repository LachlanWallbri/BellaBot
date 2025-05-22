package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface LazyStringList extends ProtocolStringList {
    void add(ByteString byteString);

    ByteString getByteString(int i);

    List<?> getUnderlyingElements();

    LazyStringList getUnmodifiableView();
}
