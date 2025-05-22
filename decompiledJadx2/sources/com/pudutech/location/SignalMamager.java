package com.pudutech.location;

import android.app.Activity;
import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import com.pudutech.base.Pdlog;
import com.pudutech.location.utils.FlowCardInfo;
import com.pudutech.location.view.SignalView;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SignalMamager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u001a\u0018\u0000 22\u00020\u0001:\u000223B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0014\u001a\u00020\r2\u0006\u0010'\u001a\u00020\tH\u0003J\u000e\u0010(\u001a\u00020\r2\u0006\u0010)\u001a\u00020\rJ\b\u0010*\u001a\u0004\u0018\u00010\u0011J\b\u0010+\u001a\u00020\u001aH\u0002J\b\u0010,\u001a\u00020\u001aH\u0016J\b\u0010-\u001a\u00020\u001aH\u0016J\b\u0010.\u001a\u00020\u001aH\u0016J\b\u0010/\u001a\u00020\u001aH\u0016J\b\u00100\u001a\u00020\u001aH\u0002J\b\u00101\u001a\u00020\u001aH\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0018\u00010\u000bR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R&\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001a0\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR&\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u001a0\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001eR\u001a\u0010\"\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u00064"}, m3961d2 = {"Lcom/pudutech/location/SignalMamager;", "Lcom/pudutech/location/LifecycleManager;", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "TAG", "", "kotlin.jvm.PlatformType", "mContext", "Landroid/content/Context;", "mDefPhoneStateListerer", "Lcom/pudutech/location/SignalMamager$DefPhoneStateListerer;", "mLastLevel", "", "mPhone", "Landroid/telephony/TelephonyManager;", "mSignalView", "Lcom/pudutech/location/view/SignalView;", "mState", "mobileNetType", "getMobileNetType", "()Ljava/lang/String;", "setMobileNetType", "(Ljava/lang/String;)V", "onNetworkClass", "Lkotlin/Function1;", "", "getOnNetworkClass", "()Lkotlin/jvm/functions/Function1;", "setOnNetworkClass", "(Lkotlin/jvm/functions/Function1;)V", "onSignalType", "getOnSignalType", "setOnSignalType", "signalValue", "getSignalValue", "()I", "setSignalValue", "(I)V", "context", "getNetWorkClass", "networkType", "getSignalView", "notifyUi", "onCreate", "onDestroy", "onResume", "onStart", "registerPhoneStateListerer", "unRegisterPhoneStateListener", "Companion", "DefPhoneStateListerer", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SignalMamager extends LifecycleManager {
    public static final int NETWORK_2G = 2;
    public static final int NETWORK_3G = 3;
    public static final int NETWORK_4G = 4;
    public static final int NETWORK_5G = 5;
    public static final int NETWORK_UNKNOW = -2;
    private final String TAG;
    private final Context mContext;
    private DefPhoneStateListerer mDefPhoneStateListerer;
    private int mLastLevel;
    private TelephonyManager mPhone;
    private SignalView mSignalView;
    private int mState;
    private String mobileNetType;
    private Function1<? super String, Unit> onNetworkClass;
    private Function1<? super Integer, Unit> onSignalType;
    private int signalValue;

    @Override // com.pudutech.location.LifecycleManager
    public void onStart() {
    }

    public final int getSignalValue() {
        return this.signalValue;
    }

    public final void setSignalValue(int i) {
        this.signalValue = i;
    }

    public final String getMobileNetType() {
        return this.mobileNetType;
    }

    public final void setMobileNetType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mobileNetType = str;
    }

    public final Function1<String, Unit> getOnNetworkClass() {
        return this.onNetworkClass;
    }

    public final void setOnNetworkClass(Function1<? super String, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "<set-?>");
        this.onNetworkClass = function1;
    }

    public final Function1<Integer, Unit> getOnSignalType() {
        return this.onSignalType;
    }

    public final void setOnSignalType(Function1<? super Integer, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "<set-?>");
        this.onSignalType = function1;
    }

    /* renamed from: getSignalView, reason: from getter */
    public final SignalView getMSignalView() {
        return this.mSignalView;
    }

    public SignalMamager(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        this.TAG = getClass().getSimpleName();
        this.mobileNetType = "-2G";
        this.mState = -1;
        this.onNetworkClass = new Function1<String, Unit>() { // from class: com.pudutech.location.SignalMamager$onNetworkClass$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                SignalView signalView;
                SignalView signalView2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (!StringsKt.contains$default((CharSequence) it, (CharSequence) "-2", false, 2, (Object) null)) {
                    signalView2 = SignalMamager.this.mSignalView;
                    if (signalView2 != null) {
                        signalView2.setSignalTypeText(it);
                    }
                } else {
                    signalView = SignalMamager.this.mSignalView;
                    if (signalView != null) {
                        signalView.setSignalTypeText("");
                    }
                }
                Pdlog.m3273d(SignalMamager.this.TAG, "网络的类型：" + it);
            }
        };
        this.onSignalType = new Function1<Integer, Unit>() { // from class: com.pudutech.location.SignalMamager$onSignalType$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                SignalView signalView;
                signalView = SignalMamager.this.mSignalView;
                if (signalView != null) {
                    signalView.setSignalValue(i);
                }
                Pdlog.m3273d(SignalMamager.this.TAG, "信号的强度：" + i);
            }
        };
        addLifecycle(activity);
        Context applicationContext = activity.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "activity.applicationContext");
        this.mContext = applicationContext;
        this.mSignalView = (SignalView) activity.findViewById(C4766R.id.signal_view);
    }

    private final void registerPhoneStateListerer() {
        Object systemService = this.mContext.getSystemService("phone");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.telephony.TelephonyManager");
        }
        this.mPhone = (TelephonyManager) systemService;
        this.mDefPhoneStateListerer = new DefPhoneStateListerer();
        TelephonyManager telephonyManager = this.mPhone;
        if (telephonyManager == null) {
            Intrinsics.throwNpe();
        }
        telephonyManager.listen(this.mDefPhoneStateListerer, 321);
        Pdlog.m3273d(this.TAG, "registerPhoneStateListerer");
    }

    private final void unRegisterPhoneStateListener() {
        Object systemService = this.mContext.getSystemService("phone");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.telephony.TelephonyManager");
        }
        ((TelephonyManager) systemService).listen(this.mDefPhoneStateListerer, 0);
        Pdlog.m3273d(this.TAG, "unRegisterPhoneStateListener");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SignalMamager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/location/SignalMamager$DefPhoneStateListerer;", "Landroid/telephony/PhoneStateListener;", "(Lcom/pudutech/location/SignalMamager;)V", "onDataConnectionStateChanged", "", "state", "", "networkType", "onServiceStateChanged", "serviceState", "Landroid/telephony/ServiceState;", "onSignalStrengthsChanged", "signalStrength", "Landroid/telephony/SignalStrength;", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final class DefPhoneStateListerer extends PhoneStateListener {
        public DefPhoneStateListerer() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onServiceStateChanged(ServiceState serviceState) {
            Intrinsics.checkParameterIsNotNull(serviceState, "serviceState");
            super.onServiceStateChanged(serviceState);
            SignalMamager signalMamager = SignalMamager.this;
            StringBuilder sb = new StringBuilder();
            SignalMamager signalMamager2 = SignalMamager.this;
            sb.append(signalMamager2.getMobileNetType(signalMamager2.mContext));
            sb.append('G');
            signalMamager.setMobileNetType(sb.toString());
            if (StringsKt.contains$default((CharSequence) SignalMamager.this.getMobileNetType(), (CharSequence) "-2", false, 2, (Object) null)) {
                SignalMamager.this.setSignalValue(0);
            } else {
                SignalMamager signalMamager3 = SignalMamager.this;
                signalMamager3.setSignalValue(signalMamager3.mLastLevel);
            }
            SignalMamager.this.notifyUi();
            Pdlog.m3273d(SignalMamager.this.TAG, "onServiceStateChanged = " + SignalMamager.this.getMobileNetType() + "  serviceState = " + serviceState + " signalValue = " + SignalMamager.this.getSignalValue());
        }

        @Override // android.telephony.PhoneStateListener
        public void onDataConnectionStateChanged(int state, int networkType) {
            super.onDataConnectionStateChanged(state, networkType);
            SignalMamager.this.mState = state;
            SignalMamager signalMamager = SignalMamager.this;
            StringBuilder sb = new StringBuilder();
            SignalMamager signalMamager2 = SignalMamager.this;
            sb.append(signalMamager2.getMobileNetType(signalMamager2.mContext));
            sb.append('G');
            signalMamager.setMobileNetType(sb.toString());
            if (state == 0 || state == -1) {
                SignalMamager.this.setSignalValue(0);
            } else {
                SignalMamager signalMamager3 = SignalMamager.this;
                signalMamager3.setSignalValue(signalMamager3.mLastLevel);
            }
            SignalMamager.this.notifyUi();
            Pdlog.m3273d(SignalMamager.this.TAG, "onDataConnectionStateChanged = " + SignalMamager.this.getMobileNetType() + " state = " + state + "  networkType = " + networkType + " signalValue = " + SignalMamager.this.getSignalValue());
        }

        @Override // android.telephony.PhoneStateListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            Intrinsics.checkParameterIsNotNull(signalStrength, "signalStrength");
            super.onSignalStrengthsChanged(signalStrength);
            SignalMamager.this.setSignalValue(signalStrength.getLevel() == 0 ? 0 : signalStrength.getLevel() + 1);
            SignalMamager signalMamager = SignalMamager.this;
            signalMamager.mLastLevel = signalMamager.getSignalValue();
            if (StringsKt.contains$default((CharSequence) SignalMamager.this.getMobileNetType(), (CharSequence) "-2", false, 2, (Object) null)) {
                SignalMamager.this.setSignalValue(0);
            }
            SignalMamager.this.notifyUi();
            Pdlog.m3273d(SignalMamager.this.TAG, "onSignalStrengthsChanged  level = " + signalStrength.getLevel() + " signalValue = " + SignalMamager.this.getSignalValue() + "  mobileNetType = " + SignalMamager.this.getMobileNetType());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyUi() {
        Function1<? super String, Unit> function1 = this.onNetworkClass;
        if (function1 != null) {
            function1.invoke(this.mobileNetType);
        }
        Function1<? super Integer, Unit> function12 = this.onSignalType;
        if (function12 != null) {
            function12.invoke(Integer.valueOf(this.signalValue));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getMobileNetType(Context context) {
        Object systemService = context.getSystemService("phone");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.telephony.TelephonyManager");
        }
        return getNetWorkClass(((TelephonyManager) systemService).getNetworkType());
    }

    public final int getNetWorkClass(int networkType) {
        Pdlog.m3273d(this.TAG, "getNetWorkClass networkType=" + networkType);
        switch (networkType) {
            case 0:
                return -2;
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return 2;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return 3;
            case 13:
            default:
                return 4;
        }
    }

    @Override // com.pudutech.location.LifecycleManager
    public void onCreate() {
        registerPhoneStateListerer();
    }

    @Override // com.pudutech.location.LifecycleManager
    public void onResume() {
        super.onResume();
        boolean z = FlowCardInfo.INSTANCE.get4GDataEnable(this.mContext);
        Pdlog.m3273d(this.TAG, "get4GStatus = " + z);
        if (z) {
            SignalView signalView = this.mSignalView;
            if (signalView != null) {
                signalView.setVisibility(0);
                return;
            }
            return;
        }
        SignalView signalView2 = this.mSignalView;
        if (signalView2 != null) {
            signalView2.setVisibility(8);
        }
    }

    @Override // com.pudutech.location.LifecycleManager
    public void onDestroy() {
        unRegisterPhoneStateListener();
    }
}
