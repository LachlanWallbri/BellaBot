package com.amazonaws.services.kms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes.dex */
public class ReplicateKeyResult implements Serializable {
    private KeyMetadata replicaKeyMetadata;
    private String replicaPolicy;
    private List<Tag> replicaTags = new ArrayList();

    public KeyMetadata getReplicaKeyMetadata() {
        return this.replicaKeyMetadata;
    }

    public void setReplicaKeyMetadata(KeyMetadata keyMetadata) {
        this.replicaKeyMetadata = keyMetadata;
    }

    public ReplicateKeyResult withReplicaKeyMetadata(KeyMetadata keyMetadata) {
        this.replicaKeyMetadata = keyMetadata;
        return this;
    }

    public String getReplicaPolicy() {
        return this.replicaPolicy;
    }

    public void setReplicaPolicy(String str) {
        this.replicaPolicy = str;
    }

    public ReplicateKeyResult withReplicaPolicy(String str) {
        this.replicaPolicy = str;
        return this;
    }

    public List<Tag> getReplicaTags() {
        return this.replicaTags;
    }

    public void setReplicaTags(Collection<Tag> collection) {
        if (collection == null) {
            this.replicaTags = null;
        } else {
            this.replicaTags = new ArrayList(collection);
        }
    }

    public ReplicateKeyResult withReplicaTags(Tag... tagArr) {
        if (getReplicaTags() == null) {
            this.replicaTags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.replicaTags.add(tag);
        }
        return this;
    }

    public ReplicateKeyResult withReplicaTags(Collection<Tag> collection) {
        setReplicaTags(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getReplicaKeyMetadata() != null) {
            sb.append("ReplicaKeyMetadata: " + getReplicaKeyMetadata() + ",");
        }
        if (getReplicaPolicy() != null) {
            sb.append("ReplicaPolicy: " + getReplicaPolicy() + ",");
        }
        if (getReplicaTags() != null) {
            sb.append("ReplicaTags: " + getReplicaTags());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getReplicaKeyMetadata() == null ? 0 : getReplicaKeyMetadata().hashCode()) + 31) * 31) + (getReplicaPolicy() == null ? 0 : getReplicaPolicy().hashCode())) * 31) + (getReplicaTags() != null ? getReplicaTags().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ReplicateKeyResult)) {
            return false;
        }
        ReplicateKeyResult replicateKeyResult = (ReplicateKeyResult) obj;
        if ((replicateKeyResult.getReplicaKeyMetadata() == null) ^ (getReplicaKeyMetadata() == null)) {
            return false;
        }
        if (replicateKeyResult.getReplicaKeyMetadata() != null && !replicateKeyResult.getReplicaKeyMetadata().equals(getReplicaKeyMetadata())) {
            return false;
        }
        if ((replicateKeyResult.getReplicaPolicy() == null) ^ (getReplicaPolicy() == null)) {
            return false;
        }
        if (replicateKeyResult.getReplicaPolicy() != null && !replicateKeyResult.getReplicaPolicy().equals(getReplicaPolicy())) {
            return false;
        }
        if ((replicateKeyResult.getReplicaTags() == null) ^ (getReplicaTags() == null)) {
            return false;
        }
        return replicateKeyResult.getReplicaTags() == null || replicateKeyResult.getReplicaTags().equals(getReplicaTags());
    }
}
