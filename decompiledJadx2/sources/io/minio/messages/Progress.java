package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "Progress", strict = false)
/* loaded from: classes7.dex */
public class Progress extends Stats {
}
