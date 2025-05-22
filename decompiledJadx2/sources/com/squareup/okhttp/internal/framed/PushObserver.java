package com.squareup.okhttp.internal.framed;

import java.io.IOException;
import java.util.List;
import okio.BufferedSource;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface PushObserver {
    public static final PushObserver CANCEL = new PushObserver() { // from class: com.squareup.okhttp.internal.framed.PushObserver.1
        @Override // com.squareup.okhttp.internal.framed.PushObserver
        public boolean onHeaders(int i, List<Header> list, boolean z) {
            return true;
        }

        @Override // com.squareup.okhttp.internal.framed.PushObserver
        public boolean onRequest(int i, List<Header> list) {
            return true;
        }

        @Override // com.squareup.okhttp.internal.framed.PushObserver
        public void onReset(int i, ErrorCode errorCode) {
        }

        @Override // com.squareup.okhttp.internal.framed.PushObserver
        public boolean onData(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException {
            bufferedSource.skip(i2);
            return true;
        }
    };

    boolean onData(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException;

    boolean onHeaders(int i, List<Header> list, boolean z);

    boolean onRequest(int i, List<Header> list);

    void onReset(int i, ErrorCode errorCode);
}
