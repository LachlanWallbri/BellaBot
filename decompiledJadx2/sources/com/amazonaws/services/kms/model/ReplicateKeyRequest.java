package com.amazonaws.services.kms.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes.dex */
public class ReplicateKeyRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean bypassPolicyLockoutSafetyCheck;
    private String description;
    private String keyId;
    private String policy;
    private String replicaRegion;
    private List<Tag> tags = new ArrayList();

    public String getKeyId() {
        return this.keyId;
    }

    public void setKeyId(String str) {
        this.keyId = str;
    }

    public ReplicateKeyRequest withKeyId(String str) {
        this.keyId = str;
        return this;
    }

    public String getReplicaRegion() {
        return this.replicaRegion;
    }

    public void setReplicaRegion(String str) {
        this.replicaRegion = str;
    }

    public ReplicateKeyRequest withReplicaRegion(String str) {
        this.replicaRegion = str;
        return this;
    }

    public String getPolicy() {
        return this.policy;
    }

    public void setPolicy(String str) {
        this.policy = str;
    }

    public ReplicateKeyRequest withPolicy(String str) {
        this.policy = str;
        return this;
    }

    public Boolean isBypassPolicyLockoutSafetyCheck() {
        return this.bypassPolicyLockoutSafetyCheck;
    }

    public Boolean getBypassPolicyLockoutSafetyCheck() {
        return this.bypassPolicyLockoutSafetyCheck;
    }

    public void setBypassPolicyLockoutSafetyCheck(Boolean bool) {
        this.bypassPolicyLockoutSafetyCheck = bool;
    }

    public ReplicateKeyRequest withBypassPolicyLockoutSafetyCheck(Boolean bool) {
        this.bypassPolicyLockoutSafetyCheck = bool;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public ReplicateKeyRequest withDescription(String str) {
        this.description = str;
        return this;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(Collection<Tag> collection) {
        if (collection == null) {
            this.tags = null;
        } else {
            this.tags = new ArrayList(collection);
        }
    }

    public ReplicateKeyRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public ReplicateKeyRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getKeyId() != null) {
            sb.append("KeyId: " + getKeyId() + ",");
        }
        if (getReplicaRegion() != null) {
            sb.append("ReplicaRegion: " + getReplicaRegion() + ",");
        }
        if (getPolicy() != null) {
            sb.append("Policy: " + getPolicy() + ",");
        }
        if (getBypassPolicyLockoutSafetyCheck() != null) {
            sb.append("BypassPolicyLockoutSafetyCheck: " + getBypassPolicyLockoutSafetyCheck() + ",");
        }
        if (getDescription() != null) {
            sb.append("Description: " + getDescription() + ",");
        }
        if (getTags() != null) {
            sb.append("Tags: " + getTags());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getKeyId() == null ? 0 : getKeyId().hashCode()) + 31) * 31) + (getReplicaRegion() == null ? 0 : getReplicaRegion().hashCode())) * 31) + (getPolicy() == null ? 0 : getPolicy().hashCode())) * 31) + (getBypassPolicyLockoutSafetyCheck() == null ? 0 : getBypassPolicyLockoutSafetyCheck().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getTags() != null ? getTags().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ReplicateKeyRequest)) {
            return false;
        }
        ReplicateKeyRequest replicateKeyRequest = (ReplicateKeyRequest) obj;
        if ((replicateKeyRequest.getKeyId() == null) ^ (getKeyId() == null)) {
            return false;
        }
        if (replicateKeyRequest.getKeyId() != null && !replicateKeyRequest.getKeyId().equals(getKeyId())) {
            return false;
        }
        if ((replicateKeyRequest.getReplicaRegion() == null) ^ (getReplicaRegion() == null)) {
            return false;
        }
        if (replicateKeyRequest.getReplicaRegion() != null && !replicateKeyRequest.getReplicaRegion().equals(getReplicaRegion())) {
            return false;
        }
        if ((replicateKeyRequest.getPolicy() == null) ^ (getPolicy() == null)) {
            return false;
        }
        if (replicateKeyRequest.getPolicy() != null && !replicateKeyRequest.getPolicy().equals(getPolicy())) {
            return false;
        }
        if ((replicateKeyRequest.getBypassPolicyLockoutSafetyCheck() == null) ^ (getBypassPolicyLockoutSafetyCheck() == null)) {
            return false;
        }
        if (replicateKeyRequest.getBypassPolicyLockoutSafetyCheck() != null && !replicateKeyRequest.getBypassPolicyLockoutSafetyCheck().equals(getBypassPolicyLockoutSafetyCheck())) {
            return false;
        }
        if ((replicateKeyRequest.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (replicateKeyRequest.getDescription() != null && !replicateKeyRequest.getDescription().equals(getDescription())) {
            return false;
        }
        if ((replicateKeyRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return replicateKeyRequest.getTags() == null || replicateKeyRequest.getTags().equals(getTags());
    }
}
