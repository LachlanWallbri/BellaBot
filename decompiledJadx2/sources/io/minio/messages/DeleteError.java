package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = MoveError.LEVEL_ERROR, strict = false)
/* loaded from: classes7.dex */
public class DeleteError extends ErrorResponse {
    private static final long serialVersionUID = 1905162041950251407L;
}
