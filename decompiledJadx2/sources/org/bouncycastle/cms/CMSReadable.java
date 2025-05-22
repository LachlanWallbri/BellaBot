package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes9.dex */
interface CMSReadable {
    InputStream getInputStream() throws IOException, CMSException;
}
