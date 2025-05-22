package com.pudutech.robot.opensdk.interf;

import com.iflytek.cloud.SpeechUtility;
import com.loc.C3898x;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ICallback.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0014\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/interf/ICallback;", "", "onFailed", "", C3898x.f4338g, "Ljava/lang/Exception;", "onSuccess", SpeechUtility.TAG_RESOURCE_RESULT, "Lcom/pudutech/robot/opensdk/interf/IBody;", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public interface ICallback {
    void onFailed(Exception e);

    void onSuccess(IBody result);

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: ICallback.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void onSuccess$default(ICallback iCallback, IBody iBody, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onSuccess");
            }
            if ((i & 1) != 0) {
                iBody = (IBody) null;
            }
            iCallback.onSuccess(iBody);
        }
    }
}
