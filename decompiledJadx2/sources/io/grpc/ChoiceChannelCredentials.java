package io.grpc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ChoiceChannelCredentials extends ChannelCredentials {
    private final List<ChannelCredentials> creds;

    public static ChannelCredentials create(ChannelCredentials... channelCredentialsArr) {
        if (channelCredentialsArr.length == 0) {
            throw new IllegalArgumentException("At least one credential is required");
        }
        for (ChannelCredentials channelCredentials : channelCredentialsArr) {
            if (channelCredentials == null) {
                throw new NullPointerException();
            }
        }
        return new ChoiceChannelCredentials(Collections.unmodifiableList(new ArrayList(Arrays.asList(channelCredentialsArr))));
    }

    private ChoiceChannelCredentials(List<ChannelCredentials> list) {
        this.creds = list;
    }

    public List<ChannelCredentials> getCredentialsList() {
        return this.creds;
    }

    @Override // io.grpc.ChannelCredentials
    public ChannelCredentials withoutBearerTokens() {
        ArrayList arrayList = new ArrayList();
        Iterator<ChannelCredentials> it = this.creds.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().withoutBearerTokens());
        }
        return new ChoiceChannelCredentials(Collections.unmodifiableList(arrayList));
    }
}
