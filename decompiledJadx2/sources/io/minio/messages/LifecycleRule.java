package io.minio.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Rule")
/* loaded from: classes7.dex */
public class LifecycleRule {

    @Element(name = "AbortIncompleteMultipartUpload", required = false)
    private AbortIncompleteMultipartUpload abortIncompleteMultipartUpload;

    @Element(name = "Expiration", required = false)
    private Expiration expiration;

    @Element(name = "Filter", required = false)
    private RuleFilter filter;

    /* renamed from: id */
    @Element(name = "ID", required = false)
    private String f8440id;

    @Element(name = "NoncurrentVersionExpiration", required = false)
    private NoncurrentVersionExpiration noncurrentVersionExpiration;

    @Element(name = "NoncurrentVersionTransition", required = false)
    private NoncurrentVersionTransition noncurrentVersionTransition;

    @Element(name = "Status")
    private Status status;

    @Element(name = "Transition", required = false)
    private Transition transition;

    public LifecycleRule(@Element(name = "Status") @Nonnull Status status, @Element(name = "AbortIncompleteMultipartUpload", required = false) @Nullable AbortIncompleteMultipartUpload abortIncompleteMultipartUpload, @Element(name = "Expiration", required = false) @Nullable Expiration expiration, @Element(name = "Filter", required = false) @Nonnull RuleFilter ruleFilter, @Element(name = "ID", required = false) @Nullable String str, @Element(name = "NoncurrentVersionExpiration", required = false) @Nullable NoncurrentVersionExpiration noncurrentVersionExpiration, @Element(name = "NoncurrentVersionTransition", required = false) @Nullable NoncurrentVersionTransition noncurrentVersionTransition, @Element(name = "Transition", required = false) @Nullable Transition transition) {
        if (abortIncompleteMultipartUpload == null && expiration == null && noncurrentVersionExpiration == null && noncurrentVersionTransition == null && transition == null) {
            throw new IllegalArgumentException("At least one of action (AbortIncompleteMultipartUpload, Expiration, NoncurrentVersionExpiration, NoncurrentVersionTransition or Transition) must be specified in a rule");
        }
        if (str != null) {
            str = str.trim();
            if (str.isEmpty()) {
                throw new IllegalArgumentException("ID must be non-empty string");
            }
            if (str.length() > 255) {
                throw new IllegalArgumentException("ID must be exceed 255 characters");
            }
        }
        this.abortIncompleteMultipartUpload = abortIncompleteMultipartUpload;
        this.expiration = expiration;
        this.filter = (RuleFilter) Objects.requireNonNull(ruleFilter, "Filter must not be null");
        this.f8440id = str;
        this.noncurrentVersionExpiration = noncurrentVersionExpiration;
        this.noncurrentVersionTransition = noncurrentVersionTransition;
        this.status = (Status) Objects.requireNonNull(status, "Status must not be null");
        this.transition = transition;
    }

    public AbortIncompleteMultipartUpload abortIncompleteMultipartUpload() {
        return this.abortIncompleteMultipartUpload;
    }

    public Expiration expiration() {
        return this.expiration;
    }

    public RuleFilter filter() {
        return this.filter;
    }

    /* renamed from: id */
    public String m3925id() {
        return this.f8440id;
    }

    public NoncurrentVersionExpiration noncurrentVersionExpiration() {
        return this.noncurrentVersionExpiration;
    }

    public NoncurrentVersionTransition noncurrentVersionTransition() {
        return this.noncurrentVersionTransition;
    }

    public Status status() {
        return this.status;
    }

    public Transition transition() {
        return this.transition;
    }
}
