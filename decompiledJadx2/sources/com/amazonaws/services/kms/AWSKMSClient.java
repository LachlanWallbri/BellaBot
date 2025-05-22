package com.amazonaws.services.kms;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceClient;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Request;
import com.amazonaws.Response;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.handlers.HandlerChainFactory;
import com.amazonaws.http.ExecutionContext;
import com.amazonaws.http.HttpClient;
import com.amazonaws.http.HttpResponseHandler;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.http.JsonResponseHandler;
import com.amazonaws.http.UrlHttpClient;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.services.kms.model.CancelKeyDeletionRequest;
import com.amazonaws.services.kms.model.CancelKeyDeletionResult;
import com.amazonaws.services.kms.model.ConnectCustomKeyStoreRequest;
import com.amazonaws.services.kms.model.ConnectCustomKeyStoreResult;
import com.amazonaws.services.kms.model.CreateAliasRequest;
import com.amazonaws.services.kms.model.CreateCustomKeyStoreRequest;
import com.amazonaws.services.kms.model.CreateCustomKeyStoreResult;
import com.amazonaws.services.kms.model.CreateGrantRequest;
import com.amazonaws.services.kms.model.CreateGrantResult;
import com.amazonaws.services.kms.model.CreateKeyRequest;
import com.amazonaws.services.kms.model.CreateKeyResult;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.services.kms.model.DecryptResult;
import com.amazonaws.services.kms.model.DeleteAliasRequest;
import com.amazonaws.services.kms.model.DeleteCustomKeyStoreRequest;
import com.amazonaws.services.kms.model.DeleteCustomKeyStoreResult;
import com.amazonaws.services.kms.model.DeleteImportedKeyMaterialRequest;
import com.amazonaws.services.kms.model.DescribeCustomKeyStoresRequest;
import com.amazonaws.services.kms.model.DescribeCustomKeyStoresResult;
import com.amazonaws.services.kms.model.DescribeKeyRequest;
import com.amazonaws.services.kms.model.DescribeKeyResult;
import com.amazonaws.services.kms.model.DisableKeyRequest;
import com.amazonaws.services.kms.model.DisableKeyRotationRequest;
import com.amazonaws.services.kms.model.DisconnectCustomKeyStoreRequest;
import com.amazonaws.services.kms.model.DisconnectCustomKeyStoreResult;
import com.amazonaws.services.kms.model.EnableKeyRequest;
import com.amazonaws.services.kms.model.EnableKeyRotationRequest;
import com.amazonaws.services.kms.model.EncryptRequest;
import com.amazonaws.services.kms.model.EncryptResult;
import com.amazonaws.services.kms.model.GenerateDataKeyPairRequest;
import com.amazonaws.services.kms.model.GenerateDataKeyPairResult;
import com.amazonaws.services.kms.model.GenerateDataKeyPairWithoutPlaintextRequest;
import com.amazonaws.services.kms.model.GenerateDataKeyPairWithoutPlaintextResult;
import com.amazonaws.services.kms.model.GenerateDataKeyRequest;
import com.amazonaws.services.kms.model.GenerateDataKeyResult;
import com.amazonaws.services.kms.model.GenerateDataKeyWithoutPlaintextRequest;
import com.amazonaws.services.kms.model.GenerateDataKeyWithoutPlaintextResult;
import com.amazonaws.services.kms.model.GenerateRandomRequest;
import com.amazonaws.services.kms.model.GenerateRandomResult;
import com.amazonaws.services.kms.model.GetKeyPolicyRequest;
import com.amazonaws.services.kms.model.GetKeyPolicyResult;
import com.amazonaws.services.kms.model.GetKeyRotationStatusRequest;
import com.amazonaws.services.kms.model.GetKeyRotationStatusResult;
import com.amazonaws.services.kms.model.GetParametersForImportRequest;
import com.amazonaws.services.kms.model.GetParametersForImportResult;
import com.amazonaws.services.kms.model.GetPublicKeyRequest;
import com.amazonaws.services.kms.model.GetPublicKeyResult;
import com.amazonaws.services.kms.model.ImportKeyMaterialRequest;
import com.amazonaws.services.kms.model.ImportKeyMaterialResult;
import com.amazonaws.services.kms.model.ListAliasesRequest;
import com.amazonaws.services.kms.model.ListAliasesResult;
import com.amazonaws.services.kms.model.ListGrantsRequest;
import com.amazonaws.services.kms.model.ListGrantsResult;
import com.amazonaws.services.kms.model.ListKeyPoliciesRequest;
import com.amazonaws.services.kms.model.ListKeyPoliciesResult;
import com.amazonaws.services.kms.model.ListKeysRequest;
import com.amazonaws.services.kms.model.ListKeysResult;
import com.amazonaws.services.kms.model.ListResourceTagsRequest;
import com.amazonaws.services.kms.model.ListResourceTagsResult;
import com.amazonaws.services.kms.model.ListRetirableGrantsRequest;
import com.amazonaws.services.kms.model.ListRetirableGrantsResult;
import com.amazonaws.services.kms.model.PutKeyPolicyRequest;
import com.amazonaws.services.kms.model.ReEncryptRequest;
import com.amazonaws.services.kms.model.ReEncryptResult;
import com.amazonaws.services.kms.model.ReplicateKeyRequest;
import com.amazonaws.services.kms.model.ReplicateKeyResult;
import com.amazonaws.services.kms.model.RetireGrantRequest;
import com.amazonaws.services.kms.model.RevokeGrantRequest;
import com.amazonaws.services.kms.model.ScheduleKeyDeletionRequest;
import com.amazonaws.services.kms.model.ScheduleKeyDeletionResult;
import com.amazonaws.services.kms.model.SignRequest;
import com.amazonaws.services.kms.model.SignResult;
import com.amazonaws.services.kms.model.TagResourceRequest;
import com.amazonaws.services.kms.model.UntagResourceRequest;
import com.amazonaws.services.kms.model.UpdateAliasRequest;
import com.amazonaws.services.kms.model.UpdateCustomKeyStoreRequest;
import com.amazonaws.services.kms.model.UpdateCustomKeyStoreResult;
import com.amazonaws.services.kms.model.UpdateKeyDescriptionRequest;
import com.amazonaws.services.kms.model.UpdatePrimaryRegionRequest;
import com.amazonaws.services.kms.model.VerifyRequest;
import com.amazonaws.services.kms.model.VerifyResult;
import com.amazonaws.services.kms.model.transform.AlreadyExistsExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.CancelKeyDeletionRequestMarshaller;
import com.amazonaws.services.kms.model.transform.CancelKeyDeletionResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.CloudHsmClusterInUseExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.CloudHsmClusterInvalidConfigurationExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.CloudHsmClusterNotActiveExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.CloudHsmClusterNotFoundExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.CloudHsmClusterNotRelatedExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.ConnectCustomKeyStoreRequestMarshaller;
import com.amazonaws.services.kms.model.transform.ConnectCustomKeyStoreResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.CreateAliasRequestMarshaller;
import com.amazonaws.services.kms.model.transform.CreateCustomKeyStoreRequestMarshaller;
import com.amazonaws.services.kms.model.transform.CreateCustomKeyStoreResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.CreateGrantRequestMarshaller;
import com.amazonaws.services.kms.model.transform.CreateGrantResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.CreateKeyRequestMarshaller;
import com.amazonaws.services.kms.model.transform.CreateKeyResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.CustomKeyStoreHasCMKsExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.CustomKeyStoreInvalidStateExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.CustomKeyStoreNameInUseExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.CustomKeyStoreNotFoundExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.DecryptRequestMarshaller;
import com.amazonaws.services.kms.model.transform.DecryptResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.DeleteAliasRequestMarshaller;
import com.amazonaws.services.kms.model.transform.DeleteCustomKeyStoreRequestMarshaller;
import com.amazonaws.services.kms.model.transform.DeleteCustomKeyStoreResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.DeleteImportedKeyMaterialRequestMarshaller;
import com.amazonaws.services.kms.model.transform.DependencyTimeoutExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.DescribeCustomKeyStoresRequestMarshaller;
import com.amazonaws.services.kms.model.transform.DescribeCustomKeyStoresResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.DescribeKeyRequestMarshaller;
import com.amazonaws.services.kms.model.transform.DescribeKeyResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.DisableKeyRequestMarshaller;
import com.amazonaws.services.kms.model.transform.DisableKeyRotationRequestMarshaller;
import com.amazonaws.services.kms.model.transform.DisabledExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.DisconnectCustomKeyStoreRequestMarshaller;
import com.amazonaws.services.kms.model.transform.DisconnectCustomKeyStoreResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.EnableKeyRequestMarshaller;
import com.amazonaws.services.kms.model.transform.EnableKeyRotationRequestMarshaller;
import com.amazonaws.services.kms.model.transform.EncryptRequestMarshaller;
import com.amazonaws.services.kms.model.transform.EncryptResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.ExpiredImportTokenExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.GenerateDataKeyPairRequestMarshaller;
import com.amazonaws.services.kms.model.transform.GenerateDataKeyPairResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.GenerateDataKeyPairWithoutPlaintextRequestMarshaller;
import com.amazonaws.services.kms.model.transform.GenerateDataKeyPairWithoutPlaintextResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.GenerateDataKeyRequestMarshaller;
import com.amazonaws.services.kms.model.transform.GenerateDataKeyResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.GenerateDataKeyWithoutPlaintextRequestMarshaller;
import com.amazonaws.services.kms.model.transform.GenerateDataKeyWithoutPlaintextResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.GenerateRandomRequestMarshaller;
import com.amazonaws.services.kms.model.transform.GenerateRandomResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.GetKeyPolicyRequestMarshaller;
import com.amazonaws.services.kms.model.transform.GetKeyPolicyResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.GetKeyRotationStatusRequestMarshaller;
import com.amazonaws.services.kms.model.transform.GetKeyRotationStatusResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.GetParametersForImportRequestMarshaller;
import com.amazonaws.services.kms.model.transform.GetParametersForImportResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.GetPublicKeyRequestMarshaller;
import com.amazonaws.services.kms.model.transform.GetPublicKeyResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.ImportKeyMaterialRequestMarshaller;
import com.amazonaws.services.kms.model.transform.ImportKeyMaterialResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.IncorrectKeyExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.IncorrectKeyMaterialExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.IncorrectTrustAnchorExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.InvalidAliasNameExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.InvalidArnExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.InvalidCiphertextExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.InvalidGrantIdExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.InvalidGrantTokenExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.InvalidImportTokenExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.InvalidKeyUsageExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.InvalidMarkerExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.KMSInternalExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.KMSInvalidSignatureExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.KMSInvalidStateExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.KeyUnavailableExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.LimitExceededExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.ListAliasesRequestMarshaller;
import com.amazonaws.services.kms.model.transform.ListAliasesResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.ListGrantsRequestMarshaller;
import com.amazonaws.services.kms.model.transform.ListGrantsResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.ListKeyPoliciesRequestMarshaller;
import com.amazonaws.services.kms.model.transform.ListKeyPoliciesResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.ListKeysRequestMarshaller;
import com.amazonaws.services.kms.model.transform.ListKeysResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.ListResourceTagsRequestMarshaller;
import com.amazonaws.services.kms.model.transform.ListResourceTagsResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.ListRetirableGrantsRequestMarshaller;
import com.amazonaws.services.kms.model.transform.ListRetirableGrantsResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.MalformedPolicyDocumentExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.NotFoundExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.PutKeyPolicyRequestMarshaller;
import com.amazonaws.services.kms.model.transform.ReEncryptRequestMarshaller;
import com.amazonaws.services.kms.model.transform.ReEncryptResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.ReplicateKeyRequestMarshaller;
import com.amazonaws.services.kms.model.transform.ReplicateKeyResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.RetireGrantRequestMarshaller;
import com.amazonaws.services.kms.model.transform.RevokeGrantRequestMarshaller;
import com.amazonaws.services.kms.model.transform.ScheduleKeyDeletionRequestMarshaller;
import com.amazonaws.services.kms.model.transform.ScheduleKeyDeletionResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.SignRequestMarshaller;
import com.amazonaws.services.kms.model.transform.SignResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.TagExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.TagResourceRequestMarshaller;
import com.amazonaws.services.kms.model.transform.UnsupportedOperationExceptionUnmarshaller;
import com.amazonaws.services.kms.model.transform.UntagResourceRequestMarshaller;
import com.amazonaws.services.kms.model.transform.UpdateAliasRequestMarshaller;
import com.amazonaws.services.kms.model.transform.UpdateCustomKeyStoreRequestMarshaller;
import com.amazonaws.services.kms.model.transform.UpdateCustomKeyStoreResultJsonUnmarshaller;
import com.amazonaws.services.kms.model.transform.UpdateKeyDescriptionRequestMarshaller;
import com.amazonaws.services.kms.model.transform.UpdatePrimaryRegionRequestMarshaller;
import com.amazonaws.services.kms.model.transform.VerifyRequestMarshaller;
import com.amazonaws.services.kms.model.transform.VerifyResultJsonUnmarshaller;
import com.amazonaws.transform.JsonErrorUnmarshaller;
import com.amazonaws.util.AWSRequestMetrics;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AWSKMSClient extends AmazonWebServiceClient implements AWSKMS {
    private AWSCredentialsProvider awsCredentialsProvider;
    protected List<JsonErrorUnmarshaller> jsonErrorUnmarshallers;

    private static ClientConfiguration adjustClientConfiguration(ClientConfiguration clientConfiguration) {
        return clientConfiguration;
    }

    @Deprecated
    public AWSKMSClient() {
        this(new DefaultAWSCredentialsProviderChain(), new ClientConfiguration());
    }

    @Deprecated
    public AWSKMSClient(ClientConfiguration clientConfiguration) {
        this(new DefaultAWSCredentialsProviderChain(), clientConfiguration);
    }

    public AWSKMSClient(AWSCredentials aWSCredentials) {
        this(aWSCredentials, new ClientConfiguration());
    }

    public AWSKMSClient(AWSCredentials aWSCredentials, ClientConfiguration clientConfiguration) {
        this(new StaticCredentialsProvider(aWSCredentials), clientConfiguration);
    }

    public AWSKMSClient(AWSCredentialsProvider aWSCredentialsProvider) {
        this(aWSCredentialsProvider, new ClientConfiguration());
    }

    public AWSKMSClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration) {
        this(aWSCredentialsProvider, clientConfiguration, new UrlHttpClient(clientConfiguration));
    }

    @Deprecated
    public AWSKMSClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, RequestMetricCollector requestMetricCollector) {
        super(adjustClientConfiguration(clientConfiguration), requestMetricCollector);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }

    public AWSKMSClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, HttpClient httpClient) {
        super(adjustClientConfiguration(clientConfiguration), httpClient);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }

    private void init() {
        this.jsonErrorUnmarshallers = new ArrayList();
        this.jsonErrorUnmarshallers.add(new AlreadyExistsExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CloudHsmClusterInUseExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CloudHsmClusterInvalidConfigurationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CloudHsmClusterNotActiveExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CloudHsmClusterNotFoundExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CloudHsmClusterNotRelatedExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CustomKeyStoreHasCMKsExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CustomKeyStoreInvalidStateExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CustomKeyStoreNameInUseExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CustomKeyStoreNotFoundExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new DependencyTimeoutExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new DisabledExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ExpiredImportTokenExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new IncorrectKeyExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new IncorrectKeyMaterialExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new IncorrectTrustAnchorExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidAliasNameExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidArnExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidCiphertextExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidGrantIdExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidGrantTokenExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidImportTokenExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidKeyUsageExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidMarkerExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new KMSInternalExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new KMSInvalidSignatureExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new KMSInvalidStateExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new KeyUnavailableExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new LimitExceededExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new MalformedPolicyDocumentExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new NotFoundExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new TagExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new UnsupportedOperationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new JsonErrorUnmarshaller());
        setEndpoint("kms.us-east-1.amazonaws.com");
        this.endpointPrefix = "kms";
        HandlerChainFactory handlerChainFactory = new HandlerChainFactory();
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandlerChain("/com/amazonaws/services/kms/request.handlers"));
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandler2Chain("/com/amazonaws/services/kms/request.handler2s"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.CancelKeyDeletionRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public CancelKeyDeletionResult cancelKeyDeletion(CancelKeyDeletionRequest cancelKeyDeletionRequest) throws AmazonServiceException, AmazonClientException {
        Request<CancelKeyDeletionRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(cancelKeyDeletionRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new CancelKeyDeletionRequestMarshaller().marshall((CancelKeyDeletionRequest) cancelKeyDeletionRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new CancelKeyDeletionResultJsonUnmarshaller()), createExecutionContext);
                    CancelKeyDeletionResult cancelKeyDeletionResult = (CancelKeyDeletionResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return cancelKeyDeletionResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cancelKeyDeletionRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, cancelKeyDeletionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, cancelKeyDeletionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.kms.model.ConnectCustomKeyStoreRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public ConnectCustomKeyStoreResult connectCustomKeyStore(ConnectCustomKeyStoreRequest connectCustomKeyStoreRequest) throws AmazonServiceException, AmazonClientException {
        Request<ConnectCustomKeyStoreRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(connectCustomKeyStoreRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ConnectCustomKeyStoreRequestMarshaller().marshall((ConnectCustomKeyStoreRequest) connectCustomKeyStoreRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new ConnectCustomKeyStoreResultJsonUnmarshaller()), createExecutionContext);
                    ConnectCustomKeyStoreResult connectCustomKeyStoreResult = (ConnectCustomKeyStoreResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return connectCustomKeyStoreResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                connectCustomKeyStoreRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, connectCustomKeyStoreRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, connectCustomKeyStoreRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.CreateAliasRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public void createAlias(CreateAliasRequest createAliasRequest) throws AmazonServiceException, AmazonClientException {
        Request<CreateAliasRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(createAliasRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new CreateAliasRequestMarshaller().marshall((CreateAliasRequest) createAliasRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                createAliasRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, createAliasRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createAliasRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.kms.model.CreateCustomKeyStoreRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public CreateCustomKeyStoreResult createCustomKeyStore(CreateCustomKeyStoreRequest createCustomKeyStoreRequest) throws AmazonServiceException, AmazonClientException {
        Request<CreateCustomKeyStoreRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(createCustomKeyStoreRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new CreateCustomKeyStoreRequestMarshaller().marshall((CreateCustomKeyStoreRequest) createCustomKeyStoreRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new CreateCustomKeyStoreResultJsonUnmarshaller()), createExecutionContext);
                    CreateCustomKeyStoreResult createCustomKeyStoreResult = (CreateCustomKeyStoreResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return createCustomKeyStoreResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                createCustomKeyStoreRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, createCustomKeyStoreRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createCustomKeyStoreRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.CreateGrantRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public CreateGrantResult createGrant(CreateGrantRequest createGrantRequest) throws AmazonServiceException, AmazonClientException {
        Request<CreateGrantRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(createGrantRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new CreateGrantRequestMarshaller().marshall((CreateGrantRequest) createGrantRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new CreateGrantResultJsonUnmarshaller()), createExecutionContext);
                    CreateGrantResult createGrantResult = (CreateGrantResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return createGrantResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                createGrantRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, createGrantRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createGrantRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.CreateKeyRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public CreateKeyResult createKey(CreateKeyRequest createKeyRequest) throws AmazonServiceException, AmazonClientException {
        Request<CreateKeyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(createKeyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new CreateKeyRequestMarshaller().marshall((CreateKeyRequest) createKeyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new CreateKeyResultJsonUnmarshaller()), createExecutionContext);
                    CreateKeyResult createKeyResult = (CreateKeyResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return createKeyResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                createKeyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, createKeyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createKeyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.DecryptRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public DecryptResult decrypt(DecryptRequest decryptRequest) throws AmazonServiceException, AmazonClientException {
        Request<DecryptRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(decryptRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DecryptRequestMarshaller().marshall((DecryptRequest) decryptRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new DecryptResultJsonUnmarshaller()), createExecutionContext);
                    DecryptResult decryptResult = (DecryptResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return decryptResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                decryptRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, decryptRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, decryptRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.DeleteAliasRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public void deleteAlias(DeleteAliasRequest deleteAliasRequest) throws AmazonServiceException, AmazonClientException {
        Request<DeleteAliasRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(deleteAliasRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DeleteAliasRequestMarshaller().marshall((DeleteAliasRequest) deleteAliasRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                deleteAliasRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, deleteAliasRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteAliasRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.kms.model.DeleteCustomKeyStoreRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public DeleteCustomKeyStoreResult deleteCustomKeyStore(DeleteCustomKeyStoreRequest deleteCustomKeyStoreRequest) throws AmazonServiceException, AmazonClientException {
        Request<DeleteCustomKeyStoreRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(deleteCustomKeyStoreRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DeleteCustomKeyStoreRequestMarshaller().marshall((DeleteCustomKeyStoreRequest) deleteCustomKeyStoreRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new DeleteCustomKeyStoreResultJsonUnmarshaller()), createExecutionContext);
                    DeleteCustomKeyStoreResult deleteCustomKeyStoreResult = (DeleteCustomKeyStoreResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return deleteCustomKeyStoreResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                deleteCustomKeyStoreRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, deleteCustomKeyStoreRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteCustomKeyStoreRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.DeleteImportedKeyMaterialRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public void deleteImportedKeyMaterial(DeleteImportedKeyMaterialRequest deleteImportedKeyMaterialRequest) throws AmazonServiceException, AmazonClientException {
        Request<DeleteImportedKeyMaterialRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(deleteImportedKeyMaterialRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DeleteImportedKeyMaterialRequestMarshaller().marshall((DeleteImportedKeyMaterialRequest) deleteImportedKeyMaterialRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                deleteImportedKeyMaterialRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, deleteImportedKeyMaterialRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteImportedKeyMaterialRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.kms.model.DescribeCustomKeyStoresRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public DescribeCustomKeyStoresResult describeCustomKeyStores(DescribeCustomKeyStoresRequest describeCustomKeyStoresRequest) throws AmazonServiceException, AmazonClientException {
        Request<DescribeCustomKeyStoresRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(describeCustomKeyStoresRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DescribeCustomKeyStoresRequestMarshaller().marshall((DescribeCustomKeyStoresRequest) describeCustomKeyStoresRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeCustomKeyStoresResultJsonUnmarshaller()), createExecutionContext);
                    DescribeCustomKeyStoresResult describeCustomKeyStoresResult = (DescribeCustomKeyStoresResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return describeCustomKeyStoresResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                describeCustomKeyStoresRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, describeCustomKeyStoresRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeCustomKeyStoresRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.DescribeKeyRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public DescribeKeyResult describeKey(DescribeKeyRequest describeKeyRequest) throws AmazonServiceException, AmazonClientException {
        Request<DescribeKeyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(describeKeyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DescribeKeyRequestMarshaller().marshall((DescribeKeyRequest) describeKeyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeKeyResultJsonUnmarshaller()), createExecutionContext);
                    DescribeKeyResult describeKeyResult = (DescribeKeyResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return describeKeyResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                describeKeyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, describeKeyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeKeyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.DisableKeyRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public void disableKey(DisableKeyRequest disableKeyRequest) throws AmazonServiceException, AmazonClientException {
        Request<DisableKeyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(disableKeyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DisableKeyRequestMarshaller().marshall((DisableKeyRequest) disableKeyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                disableKeyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, disableKeyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, disableKeyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.DisableKeyRotationRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public void disableKeyRotation(DisableKeyRotationRequest disableKeyRotationRequest) throws AmazonServiceException, AmazonClientException {
        Request<DisableKeyRotationRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(disableKeyRotationRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DisableKeyRotationRequestMarshaller().marshall((DisableKeyRotationRequest) disableKeyRotationRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                disableKeyRotationRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, disableKeyRotationRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, disableKeyRotationRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.kms.model.DisconnectCustomKeyStoreRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public DisconnectCustomKeyStoreResult disconnectCustomKeyStore(DisconnectCustomKeyStoreRequest disconnectCustomKeyStoreRequest) throws AmazonServiceException, AmazonClientException {
        Request<DisconnectCustomKeyStoreRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(disconnectCustomKeyStoreRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DisconnectCustomKeyStoreRequestMarshaller().marshall((DisconnectCustomKeyStoreRequest) disconnectCustomKeyStoreRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new DisconnectCustomKeyStoreResultJsonUnmarshaller()), createExecutionContext);
                    DisconnectCustomKeyStoreResult disconnectCustomKeyStoreResult = (DisconnectCustomKeyStoreResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return disconnectCustomKeyStoreResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                disconnectCustomKeyStoreRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, disconnectCustomKeyStoreRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, disconnectCustomKeyStoreRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.EnableKeyRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public void enableKey(EnableKeyRequest enableKeyRequest) throws AmazonServiceException, AmazonClientException {
        Request<EnableKeyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(enableKeyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new EnableKeyRequestMarshaller().marshall((EnableKeyRequest) enableKeyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                enableKeyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, enableKeyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, enableKeyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.EnableKeyRotationRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public void enableKeyRotation(EnableKeyRotationRequest enableKeyRotationRequest) throws AmazonServiceException, AmazonClientException {
        Request<EnableKeyRotationRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(enableKeyRotationRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new EnableKeyRotationRequestMarshaller().marshall((EnableKeyRotationRequest) enableKeyRotationRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                enableKeyRotationRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, enableKeyRotationRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, enableKeyRotationRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.EncryptRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public EncryptResult encrypt(EncryptRequest encryptRequest) throws AmazonServiceException, AmazonClientException {
        Request<EncryptRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(encryptRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new EncryptRequestMarshaller().marshall((EncryptRequest) encryptRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new EncryptResultJsonUnmarshaller()), createExecutionContext);
                    EncryptResult encryptResult = (EncryptResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return encryptResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                encryptRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, encryptRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, encryptRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.GenerateDataKeyRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public GenerateDataKeyResult generateDataKey(GenerateDataKeyRequest generateDataKeyRequest) throws AmazonServiceException, AmazonClientException {
        Request<GenerateDataKeyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(generateDataKeyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GenerateDataKeyRequestMarshaller().marshall((GenerateDataKeyRequest) generateDataKeyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new GenerateDataKeyResultJsonUnmarshaller()), createExecutionContext);
                    GenerateDataKeyResult generateDataKeyResult = (GenerateDataKeyResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return generateDataKeyResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                generateDataKeyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, generateDataKeyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, generateDataKeyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.GenerateDataKeyPairRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public GenerateDataKeyPairResult generateDataKeyPair(GenerateDataKeyPairRequest generateDataKeyPairRequest) throws AmazonServiceException, AmazonClientException {
        Request<GenerateDataKeyPairRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(generateDataKeyPairRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GenerateDataKeyPairRequestMarshaller().marshall((GenerateDataKeyPairRequest) generateDataKeyPairRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new GenerateDataKeyPairResultJsonUnmarshaller()), createExecutionContext);
                    GenerateDataKeyPairResult generateDataKeyPairResult = (GenerateDataKeyPairResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return generateDataKeyPairResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                generateDataKeyPairRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, generateDataKeyPairRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, generateDataKeyPairRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.GenerateDataKeyPairWithoutPlaintextRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public GenerateDataKeyPairWithoutPlaintextResult generateDataKeyPairWithoutPlaintext(GenerateDataKeyPairWithoutPlaintextRequest generateDataKeyPairWithoutPlaintextRequest) throws AmazonServiceException, AmazonClientException {
        Request<GenerateDataKeyPairWithoutPlaintextRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(generateDataKeyPairWithoutPlaintextRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GenerateDataKeyPairWithoutPlaintextRequestMarshaller().marshall((GenerateDataKeyPairWithoutPlaintextRequest) generateDataKeyPairWithoutPlaintextRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new GenerateDataKeyPairWithoutPlaintextResultJsonUnmarshaller()), createExecutionContext);
                    GenerateDataKeyPairWithoutPlaintextResult generateDataKeyPairWithoutPlaintextResult = (GenerateDataKeyPairWithoutPlaintextResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return generateDataKeyPairWithoutPlaintextResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                generateDataKeyPairWithoutPlaintextRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, generateDataKeyPairWithoutPlaintextRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, generateDataKeyPairWithoutPlaintextRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.GenerateDataKeyWithoutPlaintextRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public GenerateDataKeyWithoutPlaintextResult generateDataKeyWithoutPlaintext(GenerateDataKeyWithoutPlaintextRequest generateDataKeyWithoutPlaintextRequest) throws AmazonServiceException, AmazonClientException {
        Request<GenerateDataKeyWithoutPlaintextRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(generateDataKeyWithoutPlaintextRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GenerateDataKeyWithoutPlaintextRequestMarshaller().marshall((GenerateDataKeyWithoutPlaintextRequest) generateDataKeyWithoutPlaintextRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new GenerateDataKeyWithoutPlaintextResultJsonUnmarshaller()), createExecutionContext);
                    GenerateDataKeyWithoutPlaintextResult generateDataKeyWithoutPlaintextResult = (GenerateDataKeyWithoutPlaintextResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return generateDataKeyWithoutPlaintextResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                generateDataKeyWithoutPlaintextRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, generateDataKeyWithoutPlaintextRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, generateDataKeyWithoutPlaintextRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.GenerateRandomRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public GenerateRandomResult generateRandom(GenerateRandomRequest generateRandomRequest) throws AmazonServiceException, AmazonClientException {
        Request<GenerateRandomRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(generateRandomRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GenerateRandomRequestMarshaller().marshall((GenerateRandomRequest) generateRandomRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new GenerateRandomResultJsonUnmarshaller()), createExecutionContext);
                    GenerateRandomResult generateRandomResult = (GenerateRandomResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return generateRandomResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                generateRandomRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, generateRandomRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, generateRandomRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.kms.model.GetKeyPolicyRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public GetKeyPolicyResult getKeyPolicy(GetKeyPolicyRequest getKeyPolicyRequest) throws AmazonServiceException, AmazonClientException {
        Request<GetKeyPolicyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getKeyPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GetKeyPolicyRequestMarshaller().marshall((GetKeyPolicyRequest) getKeyPolicyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new GetKeyPolicyResultJsonUnmarshaller()), createExecutionContext);
                    GetKeyPolicyResult getKeyPolicyResult = (GetKeyPolicyResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return getKeyPolicyResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                getKeyPolicyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, getKeyPolicyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getKeyPolicyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.GetKeyRotationStatusRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public GetKeyRotationStatusResult getKeyRotationStatus(GetKeyRotationStatusRequest getKeyRotationStatusRequest) throws AmazonServiceException, AmazonClientException {
        Request<GetKeyRotationStatusRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getKeyRotationStatusRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GetKeyRotationStatusRequestMarshaller().marshall((GetKeyRotationStatusRequest) getKeyRotationStatusRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new GetKeyRotationStatusResultJsonUnmarshaller()), createExecutionContext);
                    GetKeyRotationStatusResult getKeyRotationStatusResult = (GetKeyRotationStatusResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return getKeyRotationStatusResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                getKeyRotationStatusRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, getKeyRotationStatusRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getKeyRotationStatusRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.GetParametersForImportRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public GetParametersForImportResult getParametersForImport(GetParametersForImportRequest getParametersForImportRequest) throws AmazonServiceException, AmazonClientException {
        Request<GetParametersForImportRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getParametersForImportRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GetParametersForImportRequestMarshaller().marshall((GetParametersForImportRequest) getParametersForImportRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new GetParametersForImportResultJsonUnmarshaller()), createExecutionContext);
                    GetParametersForImportResult getParametersForImportResult = (GetParametersForImportResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return getParametersForImportResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                getParametersForImportRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, getParametersForImportRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getParametersForImportRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.GetPublicKeyRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public GetPublicKeyResult getPublicKey(GetPublicKeyRequest getPublicKeyRequest) throws AmazonServiceException, AmazonClientException {
        Request<GetPublicKeyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getPublicKeyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GetPublicKeyRequestMarshaller().marshall((GetPublicKeyRequest) getPublicKeyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new GetPublicKeyResultJsonUnmarshaller()), createExecutionContext);
                    GetPublicKeyResult getPublicKeyResult = (GetPublicKeyResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return getPublicKeyResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                getPublicKeyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, getPublicKeyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getPublicKeyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.ImportKeyMaterialRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public ImportKeyMaterialResult importKeyMaterial(ImportKeyMaterialRequest importKeyMaterialRequest) throws AmazonServiceException, AmazonClientException {
        Request<ImportKeyMaterialRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(importKeyMaterialRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ImportKeyMaterialRequestMarshaller().marshall((ImportKeyMaterialRequest) importKeyMaterialRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new ImportKeyMaterialResultJsonUnmarshaller()), createExecutionContext);
                    ImportKeyMaterialResult importKeyMaterialResult = (ImportKeyMaterialResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return importKeyMaterialResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                importKeyMaterialRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, importKeyMaterialRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, importKeyMaterialRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.kms.model.ListAliasesRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public ListAliasesResult listAliases(ListAliasesRequest listAliasesRequest) throws AmazonServiceException, AmazonClientException {
        Request<ListAliasesRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(listAliasesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ListAliasesRequestMarshaller().marshall((ListAliasesRequest) listAliasesRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new ListAliasesResultJsonUnmarshaller()), createExecutionContext);
                    ListAliasesResult listAliasesResult = (ListAliasesResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return listAliasesResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                listAliasesRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, listAliasesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listAliasesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.ListGrantsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public ListGrantsResult listGrants(ListGrantsRequest listGrantsRequest) throws AmazonServiceException, AmazonClientException {
        Request<ListGrantsRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(listGrantsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ListGrantsRequestMarshaller().marshall((ListGrantsRequest) listGrantsRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new ListGrantsResultJsonUnmarshaller()), createExecutionContext);
                    ListGrantsResult listGrantsResult = (ListGrantsResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return listGrantsResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                listGrantsRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, listGrantsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listGrantsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.ListKeyPoliciesRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public ListKeyPoliciesResult listKeyPolicies(ListKeyPoliciesRequest listKeyPoliciesRequest) throws AmazonServiceException, AmazonClientException {
        Request<ListKeyPoliciesRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(listKeyPoliciesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ListKeyPoliciesRequestMarshaller().marshall((ListKeyPoliciesRequest) listKeyPoliciesRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new ListKeyPoliciesResultJsonUnmarshaller()), createExecutionContext);
                    ListKeyPoliciesResult listKeyPoliciesResult = (ListKeyPoliciesResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return listKeyPoliciesResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                listKeyPoliciesRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, listKeyPoliciesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listKeyPoliciesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.ListKeysRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public ListKeysResult listKeys(ListKeysRequest listKeysRequest) throws AmazonServiceException, AmazonClientException {
        Request<ListKeysRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(listKeysRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ListKeysRequestMarshaller().marshall((ListKeysRequest) listKeysRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new ListKeysResultJsonUnmarshaller()), createExecutionContext);
                    ListKeysResult listKeysResult = (ListKeysResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return listKeysResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                listKeysRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, listKeysRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listKeysRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.ListResourceTagsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public ListResourceTagsResult listResourceTags(ListResourceTagsRequest listResourceTagsRequest) throws AmazonServiceException, AmazonClientException {
        Request<ListResourceTagsRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(listResourceTagsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ListResourceTagsRequestMarshaller().marshall((ListResourceTagsRequest) listResourceTagsRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new ListResourceTagsResultJsonUnmarshaller()), createExecutionContext);
                    ListResourceTagsResult listResourceTagsResult = (ListResourceTagsResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return listResourceTagsResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                listResourceTagsRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, listResourceTagsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listResourceTagsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.ListRetirableGrantsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public ListRetirableGrantsResult listRetirableGrants(ListRetirableGrantsRequest listRetirableGrantsRequest) throws AmazonServiceException, AmazonClientException {
        Request<ListRetirableGrantsRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(listRetirableGrantsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ListRetirableGrantsRequestMarshaller().marshall((ListRetirableGrantsRequest) listRetirableGrantsRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new ListRetirableGrantsResultJsonUnmarshaller()), createExecutionContext);
                    ListRetirableGrantsResult listRetirableGrantsResult = (ListRetirableGrantsResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return listRetirableGrantsResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                listRetirableGrantsRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, listRetirableGrantsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listRetirableGrantsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.PutKeyPolicyRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public void putKeyPolicy(PutKeyPolicyRequest putKeyPolicyRequest) throws AmazonServiceException, AmazonClientException {
        Request<PutKeyPolicyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(putKeyPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new PutKeyPolicyRequestMarshaller().marshall((PutKeyPolicyRequest) putKeyPolicyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                putKeyPolicyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, putKeyPolicyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, putKeyPolicyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.ReEncryptRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public ReEncryptResult reEncrypt(ReEncryptRequest reEncryptRequest) throws AmazonServiceException, AmazonClientException {
        Request<ReEncryptRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(reEncryptRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ReEncryptRequestMarshaller().marshall((ReEncryptRequest) reEncryptRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new ReEncryptResultJsonUnmarshaller()), createExecutionContext);
                    ReEncryptResult reEncryptResult = (ReEncryptResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return reEncryptResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                reEncryptRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, reEncryptRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, reEncryptRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.ReplicateKeyRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public ReplicateKeyResult replicateKey(ReplicateKeyRequest replicateKeyRequest) throws AmazonServiceException, AmazonClientException {
        Request<ReplicateKeyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(replicateKeyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ReplicateKeyRequestMarshaller().marshall((ReplicateKeyRequest) replicateKeyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new ReplicateKeyResultJsonUnmarshaller()), createExecutionContext);
                    ReplicateKeyResult replicateKeyResult = (ReplicateKeyResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return replicateKeyResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                replicateKeyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, replicateKeyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, replicateKeyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.RetireGrantRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public void retireGrant(RetireGrantRequest retireGrantRequest) throws AmazonServiceException, AmazonClientException {
        Request<RetireGrantRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(retireGrantRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new RetireGrantRequestMarshaller().marshall((RetireGrantRequest) retireGrantRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                retireGrantRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, retireGrantRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, retireGrantRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.RevokeGrantRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public void revokeGrant(RevokeGrantRequest revokeGrantRequest) throws AmazonServiceException, AmazonClientException {
        Request<RevokeGrantRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(revokeGrantRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new RevokeGrantRequestMarshaller().marshall((RevokeGrantRequest) revokeGrantRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                revokeGrantRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, revokeGrantRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, revokeGrantRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.ScheduleKeyDeletionRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public ScheduleKeyDeletionResult scheduleKeyDeletion(ScheduleKeyDeletionRequest scheduleKeyDeletionRequest) throws AmazonServiceException, AmazonClientException {
        Request<ScheduleKeyDeletionRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(scheduleKeyDeletionRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ScheduleKeyDeletionRequestMarshaller().marshall((ScheduleKeyDeletionRequest) scheduleKeyDeletionRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new ScheduleKeyDeletionResultJsonUnmarshaller()), createExecutionContext);
                    ScheduleKeyDeletionResult scheduleKeyDeletionResult = (ScheduleKeyDeletionResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return scheduleKeyDeletionResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                scheduleKeyDeletionRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, scheduleKeyDeletionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, scheduleKeyDeletionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.kms.model.SignRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public SignResult sign(SignRequest signRequest) throws AmazonServiceException, AmazonClientException {
        Request<SignRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(signRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new SignRequestMarshaller().marshall((SignRequest) signRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new SignResultJsonUnmarshaller()), createExecutionContext);
                    SignResult signResult = (SignResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return signResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                signRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, signRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, signRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.TagResourceRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public void tagResource(TagResourceRequest tagResourceRequest) throws AmazonServiceException, AmazonClientException {
        Request<TagResourceRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(tagResourceRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new TagResourceRequestMarshaller().marshall((TagResourceRequest) tagResourceRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                tagResourceRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, tagResourceRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, tagResourceRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.UntagResourceRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public void untagResource(UntagResourceRequest untagResourceRequest) throws AmazonServiceException, AmazonClientException {
        Request<UntagResourceRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(untagResourceRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new UntagResourceRequestMarshaller().marshall((UntagResourceRequest) untagResourceRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                untagResourceRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, untagResourceRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, untagResourceRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.UpdateAliasRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public void updateAlias(UpdateAliasRequest updateAliasRequest) throws AmazonServiceException, AmazonClientException {
        Request<UpdateAliasRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(updateAliasRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new UpdateAliasRequestMarshaller().marshall((UpdateAliasRequest) updateAliasRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                updateAliasRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, updateAliasRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateAliasRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.kms.model.UpdateCustomKeyStoreRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public UpdateCustomKeyStoreResult updateCustomKeyStore(UpdateCustomKeyStoreRequest updateCustomKeyStoreRequest) throws AmazonServiceException, AmazonClientException {
        Request<UpdateCustomKeyStoreRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(updateCustomKeyStoreRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new UpdateCustomKeyStoreRequestMarshaller().marshall((UpdateCustomKeyStoreRequest) updateCustomKeyStoreRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new UpdateCustomKeyStoreResultJsonUnmarshaller()), createExecutionContext);
                    UpdateCustomKeyStoreResult updateCustomKeyStoreResult = (UpdateCustomKeyStoreResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return updateCustomKeyStoreResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                updateCustomKeyStoreRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, updateCustomKeyStoreRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateCustomKeyStoreRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.UpdateKeyDescriptionRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public void updateKeyDescription(UpdateKeyDescriptionRequest updateKeyDescriptionRequest) throws AmazonServiceException, AmazonClientException {
        Request<UpdateKeyDescriptionRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(updateKeyDescriptionRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new UpdateKeyDescriptionRequestMarshaller().marshall((UpdateKeyDescriptionRequest) updateKeyDescriptionRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                updateKeyDescriptionRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, updateKeyDescriptionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateKeyDescriptionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.UpdatePrimaryRegionRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public void updatePrimaryRegion(UpdatePrimaryRegionRequest updatePrimaryRegionRequest) throws AmazonServiceException, AmazonClientException {
        Request<UpdatePrimaryRegionRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(updatePrimaryRegionRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new UpdatePrimaryRegionRequestMarshaller().marshall((UpdatePrimaryRegionRequest) updatePrimaryRegionRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                updatePrimaryRegionRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, updatePrimaryRegionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updatePrimaryRegionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kms.model.VerifyRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kms.AWSKMS
    public VerifyResult verify(VerifyRequest verifyRequest) throws AmazonServiceException, AmazonClientException {
        Request<VerifyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(verifyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new VerifyRequestMarshaller().marshall((VerifyRequest) verifyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response invoke = invoke(marshall, new JsonResponseHandler(new VerifyResultJsonUnmarshaller()), createExecutionContext);
                    VerifyResult verifyResult = (VerifyResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return verifyResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                verifyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, verifyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, verifyRequest, null, true);
            throw th;
        }
    }

    @Override // com.amazonaws.services.kms.AWSKMS
    public CreateKeyResult createKey() throws AmazonServiceException, AmazonClientException {
        return createKey(new CreateKeyRequest());
    }

    @Override // com.amazonaws.services.kms.AWSKMS
    public ListKeysResult listKeys() throws AmazonServiceException, AmazonClientException {
        return listKeys(new ListKeysRequest());
    }

    @Override // com.amazonaws.services.kms.AWSKMS
    public ListAliasesResult listAliases() throws AmazonServiceException, AmazonClientException {
        return listAliases(new ListAliasesRequest());
    }

    @Override // com.amazonaws.services.kms.AWSKMS
    public void retireGrant() throws AmazonServiceException, AmazonClientException {
        retireGrant(new RetireGrantRequest());
    }

    @Override // com.amazonaws.services.kms.AWSKMS
    public GenerateRandomResult generateRandom() throws AmazonServiceException, AmazonClientException {
        return generateRandom(new GenerateRandomRequest());
    }

    @Override // com.amazonaws.services.kms.AWSKMS
    @Deprecated
    public ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest) {
        return this.client.getResponseMetadataForRequest(amazonWebServiceRequest);
    }

    private <X, Y extends AmazonWebServiceRequest> Response<X> invoke(Request<Y> request, HttpResponseHandler<AmazonWebServiceResponse<X>> httpResponseHandler, ExecutionContext executionContext) {
        request.setEndpoint(this.endpoint);
        request.setTimeOffset(this.timeOffset);
        AWSRequestMetrics awsRequestMetrics = executionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.CredentialsRequestTime);
        try {
            AWSCredentials credentials = this.awsCredentialsProvider.getCredentials();
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.CredentialsRequestTime);
            AmazonWebServiceRequest originalRequest = request.getOriginalRequest();
            if (originalRequest != null && originalRequest.getRequestCredentials() != null) {
                credentials = originalRequest.getRequestCredentials();
            }
            executionContext.setCredentials(credentials);
            return this.client.execute(request, httpResponseHandler, new JsonErrorResponseHandler(this.jsonErrorUnmarshallers), executionContext);
        } catch (Throwable th) {
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.CredentialsRequestTime);
            throw th;
        }
    }
}
