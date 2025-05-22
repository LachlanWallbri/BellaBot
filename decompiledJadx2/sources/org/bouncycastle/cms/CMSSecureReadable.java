package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes9.dex */
interface CMSSecureReadable {
    InputStream getInputStream() throws IOException, CMSException;
}
