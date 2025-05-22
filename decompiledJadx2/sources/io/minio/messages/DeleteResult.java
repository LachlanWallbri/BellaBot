package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "DeleteResult", strict = false)
/* loaded from: classes7.dex */
public class DeleteResult {

    @ElementList(inline = true, name = MoveError.LEVEL_ERROR, required = false)
    private List<DeleteError> errorList;

    @ElementList(inline = true, name = "Deleted", required = false)
    private List<DeletedObject> objectList;

    public DeleteResult() {
    }

    public DeleteResult(DeleteError deleteError) {
        this.errorList = new LinkedList();
        this.errorList.add(deleteError);
    }

    public List<DeletedObject> objectList() {
        List<DeletedObject> list = this.objectList;
        if (list == null) {
            return Collections.unmodifiableList(new LinkedList());
        }
        return Collections.unmodifiableList(list);
    }

    public List<DeleteError> errorList() {
        List<DeleteError> list = this.errorList;
        if (list == null) {
            return Collections.unmodifiableList(new LinkedList());
        }
        return Collections.unmodifiableList(list);
    }
}
