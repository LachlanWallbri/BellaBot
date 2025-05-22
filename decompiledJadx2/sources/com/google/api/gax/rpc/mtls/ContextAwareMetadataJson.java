package com.google.api.gax.rpc.mtls;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.google.api.core.BetaApi;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes2.dex */
public class ContextAwareMetadataJson extends GenericJson {

    @Key("cert_provider_command")
    private List<String> commands;

    public final ImmutableList<String> getCommands() {
        return ImmutableList.copyOf((Collection) this.commands);
    }
}
