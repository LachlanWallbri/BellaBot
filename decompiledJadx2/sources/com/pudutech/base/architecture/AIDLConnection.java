package com.pudutech.base.architecture;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.base.Pdlog;
import java.util.Map;
import java.util.function.BiConsumer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.TimeoutKt;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* compiled from: AIDLConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0014\u0010\u0005\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00028\u00000\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\tJ1\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0012H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u0010J\r\u0010\u0016\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0017J\u001c\u0010\u0018\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\u001c\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016R\u000e\u0010\n\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u001c\u0010\u0005\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/base/architecture/AIDLConnection;", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/content/ServiceConnection;", "serviceClassPath", "", "asInterface", "Lkotlin/Function1;", "Landroid/os/IBinder;", "packageName", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Ljava/lang/String;)V", "TAG", "_interface", "Ljava/lang/Object;", MqttServiceConstants.CONNECT_ACTION, "", "context", "Landroid/content/Context;", "extras", "", "(Landroid/content/Context;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", MqttServiceConstants.DISCONNECT_ACTION, "", "getInterface", "()Ljava/lang/Object;", "onServiceConnected", "name", "Landroid/content/ComponentName;", "service", "onServiceDisconnected", "pudubase_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public class AIDLConnection<T> implements ServiceConnection {
    private final String TAG;
    private T _interface;
    private final Function1<IBinder, T> asInterface;
    private final String packageName;
    private final String serviceClassPath;

    /* JADX WARN: Multi-variable type inference failed */
    public AIDLConnection(String serviceClassPath, Function1<? super IBinder, ? extends T> asInterface, String str) {
        Intrinsics.checkParameterIsNotNull(serviceClassPath, "serviceClassPath");
        Intrinsics.checkParameterIsNotNull(asInterface, "asInterface");
        this.serviceClassPath = serviceClassPath;
        this.asInterface = asInterface;
        this.packageName = str;
        this.TAG = "AIDLConnection";
    }

    public /* synthetic */ AIDLConnection(String str, Function1 function1, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, function1, (i & 4) != 0 ? (String) null : str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object connect$default(AIDLConnection aIDLConnection, Context context, Map map, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: connect");
        }
        if ((i & 2) != 0) {
            map = (Map) null;
        }
        return aIDLConnection.connect(context, map, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0249 A[Catch: Exception -> 0x0042, TryCatch #2 {Exception -> 0x0042, blocks: (B:11:0x003d, B:12:0x0245, B:14:0x0249, B:15:0x025a, B:19:0x0256), top: B:10:0x003d }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0256 A[Catch: Exception -> 0x0042, TryCatch #2 {Exception -> 0x0042, blocks: (B:11:0x003d, B:12:0x0245, B:14:0x0249, B:15:0x025a, B:19:0x0256), top: B:10:0x003d }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object connect(Context context, Map<String, String> map, Continuation<? super Boolean> continuation) {
        AIDLConnection$connect$1 aIDLConnection$connect$1;
        int i;
        AIDLConnection<T> aIDLConnection;
        String substring;
        Boolean bool;
        boolean booleanValue;
        if (continuation instanceof AIDLConnection$connect$1) {
            aIDLConnection$connect$1 = (AIDLConnection$connect$1) continuation;
            if ((aIDLConnection$connect$1.label & Integer.MIN_VALUE) != 0) {
                aIDLConnection$connect$1.label -= Integer.MIN_VALUE;
                Object obj = aIDLConnection$connect$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = aIDLConnection$connect$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    try {
                        Pdlog.m3273d(this.TAG, "connecting " + this.serviceClassPath);
                        if (this._interface != null) {
                            Pdlog.m3277w(this.TAG, "already binded, unbind first");
                            try {
                                context.unbindService(this);
                            } catch (Exception e) {
                                Pdlog.m3274e(this.TAG, "unbindService exception:" + Log.getStackTraceString(e));
                            }
                            this._interface = null;
                        }
                        if (this.packageName != null) {
                            substring = this.packageName;
                        } else {
                            String str = this.serviceClassPath;
                            int length = str.length() - 1;
                            while (true) {
                                if (length < 0) {
                                    length = -1;
                                    break;
                                }
                                if (Boxing.boxBoolean(Boxing.boxChar(str.charAt(length)).charValue() == '.').booleanValue()) {
                                    break;
                                }
                                length--;
                            }
                            if (length == -1) {
                                Pdlog.m3274e(this.TAG, "serviceClassPath must be a class path with dot inside");
                                return Boxing.boxBoolean(false);
                            }
                            String str2 = this.serviceClassPath;
                            if (str2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            substring = str2.substring(0, length);
                            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        }
                        Pdlog.m3273d(this.TAG, "packageName:" + substring);
                        final Intent intent = new Intent();
                        intent.setClassName(substring + ".mock", this.serviceClassPath);
                        if (Build.VERSION.SDK_INT <= 24) {
                            if (map != null) {
                                for (Map.Entry<String, String> entry : map.entrySet()) {
                                    intent.putExtra(entry.getKey(), entry.getValue());
                                }
                            }
                        } else if (map != null) {
                            map.forEach(new BiConsumer<String, String>() { // from class: com.pudutech.base.architecture.AIDLConnection$connect$2
                                @Override // java.util.function.BiConsumer
                                public final void accept(String t, String u) {
                                    Intrinsics.checkParameterIsNotNull(t, "t");
                                    Intrinsics.checkParameterIsNotNull(u, "u");
                                    intent.putExtra(t, u);
                                }
                            });
                        }
                        boolean bindService = context.bindService(intent, this, 1);
                        Pdlog.m3273d(this.TAG, "bind mock service " + substring + ".mock:" + bindService);
                        if (!bindService) {
                            intent.setClassName(substring, this.serviceClassPath);
                            bindService = context.bindService(intent, this, 1);
                            Pdlog.m3273d(this.TAG, "bind service " + substring + ':' + bindService);
                        }
                        if (!bindService) {
                            Pdlog.m3275i(this.TAG, "bind service with package failed, try local service");
                            final Intent intent2 = new Intent();
                            intent2.setClassName(context, this.serviceClassPath);
                            if (Build.VERSION.SDK_INT <= 24) {
                                if (map != null) {
                                    for (Map.Entry<String, String> entry2 : map.entrySet()) {
                                        intent2.putExtra(entry2.getKey(), entry2.getValue());
                                    }
                                }
                            } else if (map != null) {
                                map.forEach(new BiConsumer<String, String>() { // from class: com.pudutech.base.architecture.AIDLConnection$connect$3
                                    @Override // java.util.function.BiConsumer
                                    public final void accept(String t, String u) {
                                        Intrinsics.checkParameterIsNotNull(t, "t");
                                        Intrinsics.checkParameterIsNotNull(u, "u");
                                        intent2.putExtra(t, u);
                                    }
                                });
                            }
                            if (!context.bindService(intent2, this, 1)) {
                                Pdlog.m3277w(this.TAG, "bind local service failed");
                                return Boxing.boxBoolean(false);
                            }
                        }
                        AIDLConnection$connect$result$1 aIDLConnection$connect$result$1 = new AIDLConnection$connect$result$1(this, null);
                        aIDLConnection$connect$1.L$0 = this;
                        aIDLConnection$connect$1.L$1 = context;
                        aIDLConnection$connect$1.L$2 = map;
                        aIDLConnection$connect$1.L$3 = substring;
                        aIDLConnection$connect$1.L$4 = intent;
                        aIDLConnection$connect$1.Z$0 = bindService;
                        aIDLConnection$connect$1.label = 1;
                        obj = TimeoutKt.withTimeoutOrNull(5000L, aIDLConnection$connect$result$1, aIDLConnection$connect$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        aIDLConnection = this;
                    } catch (Exception e2) {
                        e = e2;
                        aIDLConnection = this;
                        Pdlog.m3274e(aIDLConnection.TAG, "connect exception:" + Log.getStackTraceString(e));
                        return Boxing.boxBoolean(false);
                    }
                } else if (i == 1) {
                    boolean z = aIDLConnection$connect$1.Z$0;
                    aIDLConnection = (AIDLConnection) aIDLConnection$connect$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Exception e3) {
                        e = e3;
                        Pdlog.m3274e(aIDLConnection.TAG, "connect exception:" + Log.getStackTraceString(e));
                        return Boxing.boxBoolean(false);
                    }
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                bool = (Boolean) obj;
                if (bool != null) {
                    Pdlog.m3277w(aIDLConnection.TAG, "connect timeout");
                    booleanValue = false;
                } else {
                    booleanValue = bool.booleanValue();
                }
                return Boxing.boxBoolean(booleanValue);
            }
        }
        aIDLConnection$connect$1 = new AIDLConnection$connect$1(this, continuation);
        Object obj2 = aIDLConnection$connect$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = aIDLConnection$connect$1.label;
        if (i != 0) {
        }
        bool = (Boolean) obj2;
        if (bool != null) {
        }
        return Boxing.boxBoolean(booleanValue);
    }

    public final void disconnect(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Pdlog.m3273d(this.TAG, "disconnect service connection");
        context.unbindService(this);
        this._interface = null;
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        Pdlog.m3273d(this.TAG, "onServiceDisconnected");
        this._interface = null;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) {
        Pdlog.m3273d(this.TAG, "onServiceConnected");
        this._interface = this.asInterface.invoke(service);
    }

    public final T getInterface() {
        return this._interface;
    }
}
