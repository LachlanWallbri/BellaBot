package io.grpc;

import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ConnectivityStateInfo {
    private final ConnectivityState state;
    private final Status status;

    public static ConnectivityStateInfo forNonError(ConnectivityState connectivityState) {
        Preconditions.checkArgument(connectivityState != ConnectivityState.TRANSIENT_FAILURE, "state is TRANSIENT_ERROR. Use forError() instead");
        return new ConnectivityStateInfo(connectivityState, Status.f8305OK);
    }

    public static ConnectivityStateInfo forTransientFailure(Status status) {
        Preconditions.checkArgument(!status.isOk(), "The error status must not be OK");
        return new ConnectivityStateInfo(ConnectivityState.TRANSIENT_FAILURE, status);
    }

    public ConnectivityState getState() {
        return this.state;
    }

    public Status getStatus() {
        return this.status;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConnectivityStateInfo)) {
            return false;
        }
        ConnectivityStateInfo connectivityStateInfo = (ConnectivityStateInfo) obj;
        return this.state.equals(connectivityStateInfo.state) && this.status.equals(connectivityStateInfo.status);
    }

    public int hashCode() {
        return this.state.hashCode() ^ this.status.hashCode();
    }

    public String toString() {
        if (this.status.isOk()) {
            return this.state.toString();
        }
        return this.state + "(" + this.status + ")";
    }

    private ConnectivityStateInfo(ConnectivityState connectivityState, Status status) {
        this.state = (ConnectivityState) Preconditions.checkNotNull(connectivityState, "state is null");
        this.status = (Status) Preconditions.checkNotNull(status, "status is null");
    }
}
