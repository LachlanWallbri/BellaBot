package com.amazonaws.services.cognitoidentityprovider.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.cognitoidentityprovider.model.CreateResourceServerRequest;
import com.amazonaws.services.cognitoidentityprovider.model.ResourceServerScopeType;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;

/* loaded from: classes.dex */
public class CreateResourceServerRequestMarshaller implements Marshaller<Request<CreateResourceServerRequest>, CreateResourceServerRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateResourceServerRequest> marshall(CreateResourceServerRequest createResourceServerRequest) {
        if (createResourceServerRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateResourceServerRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createResourceServerRequest, "AmazonCognitoIdentityProvider");
        defaultRequest.addHeader("X-Amz-Target", "AWSCognitoIdentityProviderService.CreateResourceServer");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createResourceServerRequest.getUserPoolId() != null) {
                String userPoolId = createResourceServerRequest.getUserPoolId();
                jsonWriter.name("UserPoolId");
                jsonWriter.value(userPoolId);
            }
            if (createResourceServerRequest.getIdentifier() != null) {
                String identifier = createResourceServerRequest.getIdentifier();
                jsonWriter.name("Identifier");
                jsonWriter.value(identifier);
            }
            if (createResourceServerRequest.getName() != null) {
                String name = createResourceServerRequest.getName();
                jsonWriter.name("Name");
                jsonWriter.value(name);
            }
            if (createResourceServerRequest.getScopes() != null) {
                List<ResourceServerScopeType> scopes = createResourceServerRequest.getScopes();
                jsonWriter.name("Scopes");
                jsonWriter.beginArray();
                for (ResourceServerScopeType resourceServerScopeType : scopes) {
                    if (resourceServerScopeType != null) {
                        ResourceServerScopeTypeJsonMarshaller.getInstance().marshall(resourceServerScopeType, jsonWriter);
                    }
                }
                jsonWriter.endArray();
            }
            jsonWriter.endObject();
            jsonWriter.close();
            String stringWriter2 = stringWriter.toString();
            byte[] bytes = stringWriter2.getBytes(StringUtils.UTF8);
            defaultRequest.setContent(new StringInputStream(stringWriter2));
            defaultRequest.addHeader("Content-Length", Integer.toString(bytes.length));
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.1");
            }
            return defaultRequest;
        } catch (Throwable th) {
            throw new AmazonClientException("Unable to marshall request to JSON: " + th.getMessage(), th);
        }
    }
}
