package com.amazonaws.services.securitytoken.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.securitytoken.model.RegionDisabledException;
import com.amazonaws.transform.StandardErrorUnmarshaller;
import org.w3c.dom.Node;

/* loaded from: classes.dex */
public class RegionDisabledExceptionUnmarshaller extends StandardErrorUnmarshaller {
    public RegionDisabledExceptionUnmarshaller() {
        super(RegionDisabledException.class);
    }

    @Override // com.amazonaws.transform.StandardErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(Node node) throws Exception {
        String parseErrorCode = parseErrorCode(node);
        if (parseErrorCode == null || !parseErrorCode.equals("RegionDisabledException")) {
            return null;
        }
        return (RegionDisabledException) super.unmarshall(node);
    }
}
