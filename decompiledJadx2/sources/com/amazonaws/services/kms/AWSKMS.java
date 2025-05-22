package com.amazonaws.services.kms;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.regions.Region;
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

/* loaded from: classes.dex */
public interface AWSKMS {
    CancelKeyDeletionResult cancelKeyDeletion(CancelKeyDeletionRequest cancelKeyDeletionRequest) throws AmazonClientException, AmazonServiceException;

    ConnectCustomKeyStoreResult connectCustomKeyStore(ConnectCustomKeyStoreRequest connectCustomKeyStoreRequest) throws AmazonClientException, AmazonServiceException;

    void createAlias(CreateAliasRequest createAliasRequest) throws AmazonClientException, AmazonServiceException;

    CreateCustomKeyStoreResult createCustomKeyStore(CreateCustomKeyStoreRequest createCustomKeyStoreRequest) throws AmazonClientException, AmazonServiceException;

    CreateGrantResult createGrant(CreateGrantRequest createGrantRequest) throws AmazonClientException, AmazonServiceException;

    CreateKeyResult createKey() throws AmazonClientException, AmazonServiceException;

    CreateKeyResult createKey(CreateKeyRequest createKeyRequest) throws AmazonClientException, AmazonServiceException;

    DecryptResult decrypt(DecryptRequest decryptRequest) throws AmazonClientException, AmazonServiceException;

    void deleteAlias(DeleteAliasRequest deleteAliasRequest) throws AmazonClientException, AmazonServiceException;

    DeleteCustomKeyStoreResult deleteCustomKeyStore(DeleteCustomKeyStoreRequest deleteCustomKeyStoreRequest) throws AmazonClientException, AmazonServiceException;

    void deleteImportedKeyMaterial(DeleteImportedKeyMaterialRequest deleteImportedKeyMaterialRequest) throws AmazonClientException, AmazonServiceException;

    DescribeCustomKeyStoresResult describeCustomKeyStores(DescribeCustomKeyStoresRequest describeCustomKeyStoresRequest) throws AmazonClientException, AmazonServiceException;

    DescribeKeyResult describeKey(DescribeKeyRequest describeKeyRequest) throws AmazonClientException, AmazonServiceException;

    void disableKey(DisableKeyRequest disableKeyRequest) throws AmazonClientException, AmazonServiceException;

    void disableKeyRotation(DisableKeyRotationRequest disableKeyRotationRequest) throws AmazonClientException, AmazonServiceException;

    DisconnectCustomKeyStoreResult disconnectCustomKeyStore(DisconnectCustomKeyStoreRequest disconnectCustomKeyStoreRequest) throws AmazonClientException, AmazonServiceException;

    void enableKey(EnableKeyRequest enableKeyRequest) throws AmazonClientException, AmazonServiceException;

    void enableKeyRotation(EnableKeyRotationRequest enableKeyRotationRequest) throws AmazonClientException, AmazonServiceException;

    EncryptResult encrypt(EncryptRequest encryptRequest) throws AmazonClientException, AmazonServiceException;

    GenerateDataKeyResult generateDataKey(GenerateDataKeyRequest generateDataKeyRequest) throws AmazonClientException, AmazonServiceException;

    GenerateDataKeyPairResult generateDataKeyPair(GenerateDataKeyPairRequest generateDataKeyPairRequest) throws AmazonClientException, AmazonServiceException;

    GenerateDataKeyPairWithoutPlaintextResult generateDataKeyPairWithoutPlaintext(GenerateDataKeyPairWithoutPlaintextRequest generateDataKeyPairWithoutPlaintextRequest) throws AmazonClientException, AmazonServiceException;

    GenerateDataKeyWithoutPlaintextResult generateDataKeyWithoutPlaintext(GenerateDataKeyWithoutPlaintextRequest generateDataKeyWithoutPlaintextRequest) throws AmazonClientException, AmazonServiceException;

    GenerateRandomResult generateRandom() throws AmazonClientException, AmazonServiceException;

    GenerateRandomResult generateRandom(GenerateRandomRequest generateRandomRequest) throws AmazonClientException, AmazonServiceException;

    ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest);

    GetKeyPolicyResult getKeyPolicy(GetKeyPolicyRequest getKeyPolicyRequest) throws AmazonClientException, AmazonServiceException;

    GetKeyRotationStatusResult getKeyRotationStatus(GetKeyRotationStatusRequest getKeyRotationStatusRequest) throws AmazonClientException, AmazonServiceException;

    GetParametersForImportResult getParametersForImport(GetParametersForImportRequest getParametersForImportRequest) throws AmazonClientException, AmazonServiceException;

    GetPublicKeyResult getPublicKey(GetPublicKeyRequest getPublicKeyRequest) throws AmazonClientException, AmazonServiceException;

    ImportKeyMaterialResult importKeyMaterial(ImportKeyMaterialRequest importKeyMaterialRequest) throws AmazonClientException, AmazonServiceException;

    ListAliasesResult listAliases() throws AmazonClientException, AmazonServiceException;

    ListAliasesResult listAliases(ListAliasesRequest listAliasesRequest) throws AmazonClientException, AmazonServiceException;

    ListGrantsResult listGrants(ListGrantsRequest listGrantsRequest) throws AmazonClientException, AmazonServiceException;

    ListKeyPoliciesResult listKeyPolicies(ListKeyPoliciesRequest listKeyPoliciesRequest) throws AmazonClientException, AmazonServiceException;

    ListKeysResult listKeys() throws AmazonClientException, AmazonServiceException;

    ListKeysResult listKeys(ListKeysRequest listKeysRequest) throws AmazonClientException, AmazonServiceException;

    ListResourceTagsResult listResourceTags(ListResourceTagsRequest listResourceTagsRequest) throws AmazonClientException, AmazonServiceException;

    ListRetirableGrantsResult listRetirableGrants(ListRetirableGrantsRequest listRetirableGrantsRequest) throws AmazonClientException, AmazonServiceException;

    void putKeyPolicy(PutKeyPolicyRequest putKeyPolicyRequest) throws AmazonClientException, AmazonServiceException;

    ReEncryptResult reEncrypt(ReEncryptRequest reEncryptRequest) throws AmazonClientException, AmazonServiceException;

    ReplicateKeyResult replicateKey(ReplicateKeyRequest replicateKeyRequest) throws AmazonClientException, AmazonServiceException;

    void retireGrant() throws AmazonClientException, AmazonServiceException;

    void retireGrant(RetireGrantRequest retireGrantRequest) throws AmazonClientException, AmazonServiceException;

    void revokeGrant(RevokeGrantRequest revokeGrantRequest) throws AmazonClientException, AmazonServiceException;

    ScheduleKeyDeletionResult scheduleKeyDeletion(ScheduleKeyDeletionRequest scheduleKeyDeletionRequest) throws AmazonClientException, AmazonServiceException;

    void setEndpoint(String str) throws IllegalArgumentException;

    void setRegion(Region region) throws IllegalArgumentException;

    void shutdown();

    SignResult sign(SignRequest signRequest) throws AmazonClientException, AmazonServiceException;

    void tagResource(TagResourceRequest tagResourceRequest) throws AmazonClientException, AmazonServiceException;

    void untagResource(UntagResourceRequest untagResourceRequest) throws AmazonClientException, AmazonServiceException;

    void updateAlias(UpdateAliasRequest updateAliasRequest) throws AmazonClientException, AmazonServiceException;

    UpdateCustomKeyStoreResult updateCustomKeyStore(UpdateCustomKeyStoreRequest updateCustomKeyStoreRequest) throws AmazonClientException, AmazonServiceException;

    void updateKeyDescription(UpdateKeyDescriptionRequest updateKeyDescriptionRequest) throws AmazonClientException, AmazonServiceException;

    void updatePrimaryRegion(UpdatePrimaryRegionRequest updatePrimaryRegionRequest) throws AmazonClientException, AmazonServiceException;

    VerifyResult verify(VerifyRequest verifyRequest) throws AmazonClientException, AmazonServiceException;
}
