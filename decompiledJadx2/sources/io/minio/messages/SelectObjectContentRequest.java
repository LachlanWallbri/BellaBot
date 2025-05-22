package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "SelectObjectContentRequest")
/* loaded from: classes7.dex */
public class SelectObjectContentRequest {

    @Element(name = "Expression")
    private String expression;

    @Element(name = "ExpressionType")
    private String expressionType = "SQL";

    @Element(name = "InputSerialization")
    private InputSerialization inputSerialization;

    @Element(name = "OutputSerialization")
    private OutputSerialization outputSerialization;

    @Element(name = "RequestProgress", required = false)
    private RequestProgress requestProgress;

    @Element(name = "ScanRange", required = false)
    private ScanRange scanRange;

    public SelectObjectContentRequest(String str, boolean z, InputSerialization inputSerialization, OutputSerialization outputSerialization, Long l, Long l2) {
        this.expression = str;
        if (z) {
            this.requestProgress = new RequestProgress();
        }
        this.inputSerialization = inputSerialization;
        this.outputSerialization = outputSerialization;
        if (l == null && l2 == null) {
            return;
        }
        this.scanRange = new ScanRange(l, l2);
    }
}
