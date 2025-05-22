package com.pudutech.disinfect.baselib.state;

import androidx.exifinterface.media.ExifInterface;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: ResultState.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u0004*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0004\u0004\u0005\u0006\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0003\u0082\u0001\u0003\b\t\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/state/ResultState;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "Companion", MoveError.LEVEL_ERROR, "Loading", "Success", "Lcom/pudutech/disinfect/baselib/state/ResultState$Loading;", "Lcom/pudutech/disinfect/baselib/state/ResultState$Success;", "Lcom/pudutech/disinfect/baselib/state/ResultState$Error;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public abstract class ResultState<T> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: ResultState.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\t\u001a\u00020\nJ\u001f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\f\u001a\u0002H\u0005¢\u0006\u0002\u0010\r¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/state/ResultState$Companion;", "", "()V", "onError", "Lcom/pudutech/disinfect/baselib/state/ResultState;", ExifInterface.GPS_DIRECTION_TRUE, "error", "Lcom/pudutech/disinfect/baselib/state/AppException;", "onLoading", "loadingMessage", "", "onSuccess", "data", "(Ljava/lang/Object;)Lcom/pudutech/disinfect/baselib/state/ResultState;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final <T> ResultState<T> onSuccess(T data) {
            return new Success(data);
        }

        public final <T> ResultState<T> onLoading(String loadingMessage) {
            Intrinsics.checkParameterIsNotNull(loadingMessage, "loadingMessage");
            return new Loading(loadingMessage);
        }

        public final <T> ResultState<T> onError(AppException error) {
            Intrinsics.checkParameterIsNotNull(error, "error");
            return new Error(error);
        }
    }

    private ResultState() {
    }

    public /* synthetic */ ResultState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: ResultState.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0004HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/state/ResultState$Loading;", "Lcom/pudutech/disinfect/baselib/state/ResultState;", "", "loadingMessage", "", "(Ljava/lang/String;)V", "getLoadingMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class Loading extends ResultState {
        private final String loadingMessage;

        public static /* synthetic */ Loading copy$default(Loading loading, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = loading.loadingMessage;
            }
            return loading.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getLoadingMessage() {
            return this.loadingMessage;
        }

        public final Loading copy(String loadingMessage) {
            Intrinsics.checkParameterIsNotNull(loadingMessage, "loadingMessage");
            return new Loading(loadingMessage);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof Loading) && Intrinsics.areEqual(this.loadingMessage, ((Loading) other).loadingMessage);
            }
            return true;
        }

        public int hashCode() {
            String str = this.loadingMessage;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "Loading(loadingMessage=" + this.loadingMessage + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Loading(String loadingMessage) {
            super(null);
            Intrinsics.checkParameterIsNotNull(loadingMessage, "loadingMessage");
            this.loadingMessage = loadingMessage;
        }

        public final String getLoadingMessage() {
            return this.loadingMessage;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: ResultState.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\u0006J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/state/ResultState$Success;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/disinfect/baselib/state/ResultState;", "data", "(Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/pudutech/disinfect/baselib/state/ResultState$Success;", "equals", "", "other", "", "hashCode", "", "toString", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class Success<T> extends ResultState<T> {
        private final T data;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Success copy$default(Success success, Object obj, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = success.data;
            }
            return success.copy(obj);
        }

        public final T component1() {
            return this.data;
        }

        public final Success<T> copy(T data) {
            return new Success<>(data);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof Success) && Intrinsics.areEqual(this.data, ((Success) other).data);
            }
            return true;
        }

        public int hashCode() {
            T t = this.data;
            if (t != null) {
                return t.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "Success(data=" + this.data + ")";
        }

        public Success(T t) {
            super(null);
            this.data = t;
        }

        public final T getData() {
            return this.data;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: ResultState.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0004HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/state/ResultState$Error;", "Lcom/pudutech/disinfect/baselib/state/ResultState;", "", "error", "Lcom/pudutech/disinfect/baselib/state/AppException;", "(Lcom/pudutech/disinfect/baselib/state/AppException;)V", "getError", "()Lcom/pudutech/disinfect/baselib/state/AppException;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class Error extends ResultState {
        private final AppException error;

        public static /* synthetic */ Error copy$default(Error error, AppException appException, int i, Object obj) {
            if ((i & 1) != 0) {
                appException = error.error;
            }
            return error.copy(appException);
        }

        /* renamed from: component1, reason: from getter */
        public final AppException getError() {
            return this.error;
        }

        public final Error copy(AppException error) {
            Intrinsics.checkParameterIsNotNull(error, "error");
            return new Error(error);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof Error) && Intrinsics.areEqual(this.error, ((Error) other).error);
            }
            return true;
        }

        public int hashCode() {
            AppException appException = this.error;
            if (appException != null) {
                return appException.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "Error(error=" + this.error + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Error(AppException error) {
            super(null);
            Intrinsics.checkParameterIsNotNull(error, "error");
            this.error = error;
        }

        public final AppException getError() {
            return this.error;
        }
    }
}
