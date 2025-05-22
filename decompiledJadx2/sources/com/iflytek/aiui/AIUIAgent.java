package com.iflytek.aiui;

import android.content.Context;
import com.iflytek.aiui.impl.AIUIAgentImpl;
import com.iflytek.aiui.pro.C3582al;
import com.iflytek.aiui.pro.C3589as;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public final class AIUIAgent {

    /* renamed from: a */
    private static AIUIAgent f2131a;

    /* renamed from: b */
    private AIUIAgentImpl f2132b;

    private AIUIAgent(Context context, String str, AIUIListener aIUIListener) {
        AIUIAgentImpl aIUIAgentImpl = new AIUIAgentImpl(context);
        this.f2132b = aIUIAgentImpl;
        aIUIAgentImpl.m851a(str, aIUIListener);
    }

    public static AIUIAgent createAgent(Context context, String str, AIUIListener aIUIListener) {
        AIUIAgent aIUIAgent;
        synchronized (AIUIAgent.class) {
            if (context == null) {
                C3589as.m1063b("AIUIAgent", "parameter context is null.");
                aIUIAgent = null;
            } else {
                if (f2131a == null) {
                    f2131a = new AIUIAgent(context, str, aIUIListener);
                }
                aIUIAgent = f2131a;
            }
        }
        return aIUIAgent;
    }

    public void destroy() {
        synchronized (this) {
            if (this.f2132b != null) {
                this.f2132b.m852a();
                this.f2132b = null;
                f2131a = null;
                C3582al.m1026a();
            }
        }
    }

    public void sendMessage(AIUIMessage aIUIMessage) {
        if (aIUIMessage == null) {
            C3589as.m1063b("AIUIAgent", "message is null.");
            return;
        }
        synchronized (this) {
            if (this.f2132b != null) {
                this.f2132b.m854a(aIUIMessage);
            } else {
                C3589as.m1063b("AIUIAgent", "AIUIAgent has been destroyed.");
            }
        }
    }
}
