package com.google.cloud.dialogflow.p049v2.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.OperationCallable;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.p049v2.Agent;
import com.google.cloud.dialogflow.p049v2.AgentsClient;
import com.google.cloud.dialogflow.p049v2.DeleteAgentRequest;
import com.google.cloud.dialogflow.p049v2.ExportAgentRequest;
import com.google.cloud.dialogflow.p049v2.ExportAgentResponse;
import com.google.cloud.dialogflow.p049v2.GetAgentRequest;
import com.google.cloud.dialogflow.p049v2.GetValidationResultRequest;
import com.google.cloud.dialogflow.p049v2.ImportAgentRequest;
import com.google.cloud.dialogflow.p049v2.RestoreAgentRequest;
import com.google.cloud.dialogflow.p049v2.SearchAgentsRequest;
import com.google.cloud.dialogflow.p049v2.SearchAgentsResponse;
import com.google.cloud.dialogflow.p049v2.SetAgentRequest;
import com.google.cloud.dialogflow.p049v2.TrainAgentRequest;
import com.google.cloud.dialogflow.p049v2.ValidationResult;
import com.google.longrunning.Operation;
import com.google.longrunning.stub.OperationsStub;
import com.google.protobuf.Empty;
import com.google.protobuf.Struct;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("A restructuring of stub classes is planned, so this may break in the future")
/* loaded from: classes2.dex */
public abstract class AgentsStub implements BackgroundResource {
    @Override // java.lang.AutoCloseable
    public abstract void close();

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationsStub getOperationsStub() {
        throw new UnsupportedOperationException("Not implemented: getOperationsStub()");
    }

    public UnaryCallable<SetAgentRequest, Agent> setAgentCallable() {
        throw new UnsupportedOperationException("Not implemented: setAgentCallable()");
    }

    public UnaryCallable<DeleteAgentRequest, Empty> deleteAgentCallable() {
        throw new UnsupportedOperationException("Not implemented: deleteAgentCallable()");
    }

    public UnaryCallable<GetAgentRequest, Agent> getAgentCallable() {
        throw new UnsupportedOperationException("Not implemented: getAgentCallable()");
    }

    public UnaryCallable<SearchAgentsRequest, AgentsClient.SearchAgentsPagedResponse> searchAgentsPagedCallable() {
        throw new UnsupportedOperationException("Not implemented: searchAgentsPagedCallable()");
    }

    public UnaryCallable<SearchAgentsRequest, SearchAgentsResponse> searchAgentsCallable() {
        throw new UnsupportedOperationException("Not implemented: searchAgentsCallable()");
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<TrainAgentRequest, Empty, Struct> trainAgentOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: trainAgentOperationCallable()");
    }

    public UnaryCallable<TrainAgentRequest, Operation> trainAgentCallable() {
        throw new UnsupportedOperationException("Not implemented: trainAgentCallable()");
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<ExportAgentRequest, ExportAgentResponse, Struct> exportAgentOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: exportAgentOperationCallable()");
    }

    public UnaryCallable<ExportAgentRequest, Operation> exportAgentCallable() {
        throw new UnsupportedOperationException("Not implemented: exportAgentCallable()");
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<ImportAgentRequest, Empty, Struct> importAgentOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: importAgentOperationCallable()");
    }

    public UnaryCallable<ImportAgentRequest, Operation> importAgentCallable() {
        throw new UnsupportedOperationException("Not implemented: importAgentCallable()");
    }

    @BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
    public OperationCallable<RestoreAgentRequest, Empty, Struct> restoreAgentOperationCallable() {
        throw new UnsupportedOperationException("Not implemented: restoreAgentOperationCallable()");
    }

    public UnaryCallable<RestoreAgentRequest, Operation> restoreAgentCallable() {
        throw new UnsupportedOperationException("Not implemented: restoreAgentCallable()");
    }

    public UnaryCallable<GetValidationResultRequest, ValidationResult> getValidationResultCallable() {
        throw new UnsupportedOperationException("Not implemented: getValidationResultCallable()");
    }
}
