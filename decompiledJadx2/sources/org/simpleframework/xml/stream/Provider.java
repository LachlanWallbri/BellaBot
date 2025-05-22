package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;

/* loaded from: classes9.dex */
public interface Provider {
    EventReader provide(InputStream inputStream) throws Exception;

    EventReader provide(Reader reader) throws Exception;
}
