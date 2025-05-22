package io.minio.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.http.HttpHeaders;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

@Root(name = "Rule")
/* loaded from: classes7.dex */
public class ReplicationRule {

    @Element(name = "DeleteMarkerReplication", required = false)
    private DeleteMarkerReplication deleteMarkerReplication;

    @Element(name = "DeleteReplication", required = false)
    private DeleteReplication deleteReplication;

    @Element(name = HttpHeaders.DESTINATION)
    private ReplicationDestination destination;

    @Element(name = "ExistingObjectReplication", required = false)
    private ExistingObjectReplication existingObjectReplication;

    @Element(name = "Filter", required = false)
    private RuleFilter filter;

    /* renamed from: id */
    @Element(name = "ID", required = false)
    private String f8443id;

    @Element(name = "Prefix", required = false)
    @Convert(PrefixConverter.class)
    private String prefix;

    @Element(name = "Priority", required = false)
    private Integer priority;

    @Element(name = "SourceSelectionCriteria", required = false)
    private SourceSelectionCriteria sourceSelectionCriteria;

    @Element(name = "Status")
    private Status status;

    public ReplicationRule(@Element(name = "DeleteMarkerReplication", required = false) @Nullable DeleteMarkerReplication deleteMarkerReplication, @Element(name = "Destination") @Nonnull ReplicationDestination replicationDestination, @Element(name = "ExistingObjectReplication", required = false) @Nullable ExistingObjectReplication existingObjectReplication, @Element(name = "Filter", required = false) @Nullable RuleFilter ruleFilter, @Element(name = "ID", required = false) @Nullable String str, @Element(name = "Prefix", required = false) @Nullable String str2, @Element(name = "Priority", required = false) @Nullable Integer num, @Element(name = "SourceSelectionCriteria", required = false) @Nullable SourceSelectionCriteria sourceSelectionCriteria, @Element(name = "DeleteReplication", required = false) @Nullable DeleteReplication deleteReplication, @Element(name = "Status") @Nonnull Status status) {
        if (ruleFilter != null && deleteMarkerReplication == null) {
            deleteMarkerReplication = new DeleteMarkerReplication(null);
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
        this.deleteMarkerReplication = deleteMarkerReplication;
        this.destination = (ReplicationDestination) Objects.requireNonNull(replicationDestination, "Destination must not be null");
        this.existingObjectReplication = existingObjectReplication;
        this.filter = ruleFilter;
        this.f8443id = str;
        this.prefix = str2;
        this.priority = num;
        this.sourceSelectionCriteria = sourceSelectionCriteria;
        this.deleteReplication = deleteReplication;
        this.status = (Status) Objects.requireNonNull(status, "Status must not be null");
    }

    public ReplicationRule(@Element(name = "DeleteMarkerReplication", required = false) @Nullable DeleteMarkerReplication deleteMarkerReplication, @Element(name = "Destination") @Nonnull ReplicationDestination replicationDestination, @Element(name = "ExistingObjectReplication", required = false) @Nullable ExistingObjectReplication existingObjectReplication, @Element(name = "Filter", required = false) @Nullable RuleFilter ruleFilter, @Element(name = "ID", required = false) @Nullable String str, @Element(name = "Prefix", required = false) @Nullable String str2, @Element(name = "Priority", required = false) @Nullable Integer num, @Element(name = "SourceSelectionCriteria", required = false) @Nullable SourceSelectionCriteria sourceSelectionCriteria, @Element(name = "Status") @Nonnull Status status) {
        this(deleteMarkerReplication, replicationDestination, existingObjectReplication, ruleFilter, str, str2, num, sourceSelectionCriteria, null, status);
    }

    public DeleteMarkerReplication deleteMarkerReplication() {
        return this.deleteMarkerReplication;
    }

    public ReplicationDestination destination() {
        return this.destination;
    }

    public ExistingObjectReplication existingObjectReplication() {
        return this.existingObjectReplication;
    }

    public RuleFilter filter() {
        return this.filter;
    }

    /* renamed from: id */
    public String m3928id() {
        return this.f8443id;
    }

    public String prefix() {
        return this.prefix;
    }

    public Integer priority() {
        return this.priority;
    }

    public SourceSelectionCriteria sourceSelectionCriteria() {
        return this.sourceSelectionCriteria;
    }

    public DeleteReplication deleteReplication() {
        return this.deleteReplication;
    }

    public Status status() {
        return this.status;
    }
}
