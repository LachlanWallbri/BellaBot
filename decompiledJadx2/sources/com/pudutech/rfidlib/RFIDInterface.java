package com.pudutech.rfidlib;

import android.content.Context;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.rfidlib.RFIDListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RFIDInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000I\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\r\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u001a\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\t2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0011J\u0006\u0010\u001c\u001a\u00020\u0019J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010\u001f\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\tJ\u000e\u0010 \u001a\u00020\u00192\u0006\u0010\u0004\u001a\u00020\u0005R\u0014\u0010\b\u001a\u00020\tX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006!"}, m3961d2 = {"Lcom/pudutech/rfidlib/RFIDInterface;", "", "context", "Landroid/content/Context;", "protocol", "Lcom/pudutech/rfidlib/RFIDProtocol;", "(Landroid/content/Context;Lcom/pudutech/rfidlib/RFIDProtocol;)V", "(Landroid/content/Context;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "mRFIDListener", "com/pudutech/rfidlib/RFIDInterface$mRFIDListener$1", "Lcom/pudutech/rfidlib/RFIDInterface$mRFIDListener$1;", "onRFIDListeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/rfidlib/RFIDListener;", "rfidSerialInputOutputManager", "Lcom/pudutech/rfidlib/RFIDSerialInputOutputManager;", "getRfidSerialInputOutputManager", "()Lcom/pudutech/rfidlib/RFIDSerialInputOutputManager;", "setRfidSerialInputOutputManager", "(Lcom/pudutech/rfidlib/RFIDSerialInputOutputManager;)V", "addRFIDListener", "", "name", "listener", "closeRFID", "openRFID", "", "removeRFIDListener", "setProtocol", "RFIDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RFIDInterface {
    private final String TAG;
    private Context context;
    private final RFIDInterface$mRFIDListener$1 mRFIDListener;
    private final ThreadSafeListener<RFIDListener> onRFIDListeners;
    private RFIDProtocol protocol;
    private RFIDSerialInputOutputManager rfidSerialInputOutputManager;

    /* JADX WARN: Type inference failed for: r3v3, types: [com.pudutech.rfidlib.RFIDInterface$mRFIDListener$1] */
    public RFIDInterface(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.onRFIDListeners = new ThreadSafeListener<>();
        this.TAG = "RFIDInterface";
        this.mRFIDListener = new RFIDListener() { // from class: com.pudutech.rfidlib.RFIDInterface$mRFIDListener$1
            @Override // com.pudutech.rfidlib.RFIDListener
            public void onError(int i) {
                RFIDListener.DefaultImpls.onError(this, i);
            }

            @Override // com.pudutech.rfidlib.RFIDListener
            public void onData(final byte[] rawData) {
                ThreadSafeListener threadSafeListener;
                RFIDProtocol rFIDProtocol;
                Intrinsics.checkParameterIsNotNull(rawData, "rawData");
                threadSafeListener = RFIDInterface.this.onRFIDListeners;
                threadSafeListener.notify(new Function2<RFIDListener, String, Unit>() { // from class: com.pudutech.rfidlib.RFIDInterface$mRFIDListener$1$onData$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(RFIDListener rFIDListener, String str) {
                        invoke2(rFIDListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(RFIDListener l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onData(rawData);
                    }
                });
                rFIDProtocol = RFIDInterface.this.protocol;
                if (rFIDProtocol != null) {
                    rFIDProtocol.parserData(rawData, this);
                }
            }

            @Override // com.pudutech.rfidlib.RFIDListener
            public void onUID(final boolean success, final byte[] cardID) {
                ThreadSafeListener threadSafeListener;
                threadSafeListener = RFIDInterface.this.onRFIDListeners;
                threadSafeListener.notify(new Function2<RFIDListener, String, Unit>() { // from class: com.pudutech.rfidlib.RFIDInterface$mRFIDListener$1$onUID$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(RFIDListener rFIDListener, String str) {
                        invoke2(rFIDListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(RFIDListener l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onUID(success, cardID);
                    }
                });
            }

            @Override // com.pudutech.rfidlib.RFIDListener
            public void onReadBlockData(final boolean success, final byte[] blockData) {
                ThreadSafeListener threadSafeListener;
                threadSafeListener = RFIDInterface.this.onRFIDListeners;
                threadSafeListener.notify(new Function2<RFIDListener, String, Unit>() { // from class: com.pudutech.rfidlib.RFIDInterface$mRFIDListener$1$onReadBlockData$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(RFIDListener rFIDListener, String str) {
                        invoke2(rFIDListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(RFIDListener l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onReadBlockData(success, blockData);
                    }
                });
            }

            @Override // com.pudutech.rfidlib.RFIDListener
            public void onWriteBlockData(final boolean success) {
                ThreadSafeListener threadSafeListener;
                threadSafeListener = RFIDInterface.this.onRFIDListeners;
                threadSafeListener.notify(new Function2<RFIDListener, String, Unit>() { // from class: com.pudutech.rfidlib.RFIDInterface$mRFIDListener$1$onWriteBlockData$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(RFIDListener rFIDListener, String str) {
                        invoke2(rFIDListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(RFIDListener l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onWriteBlockData(success);
                    }
                });
            }
        };
        this.rfidSerialInputOutputManager = new RFIDSerialInputOutputManager(this.context, this.mRFIDListener);
    }

    public final RFIDSerialInputOutputManager getRfidSerialInputOutputManager() {
        return this.rfidSerialInputOutputManager;
    }

    public final void setRfidSerialInputOutputManager(RFIDSerialInputOutputManager rFIDSerialInputOutputManager) {
        Intrinsics.checkParameterIsNotNull(rFIDSerialInputOutputManager, "<set-?>");
        this.rfidSerialInputOutputManager = rFIDSerialInputOutputManager;
    }

    public final String getTAG() {
        return this.TAG;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RFIDInterface(Context context, RFIDProtocol protocol) {
        this(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(protocol, "protocol");
        this.context = context;
        this.protocol = protocol;
    }

    public final void setProtocol(RFIDProtocol protocol) {
        Intrinsics.checkParameterIsNotNull(protocol, "protocol");
        this.protocol = protocol;
    }

    public final int openRFID() {
        return this.rfidSerialInputOutputManager.connect();
    }

    public final void closeRFID() {
        this.rfidSerialInputOutputManager.disconnect();
    }

    public final void addRFIDListener(String name, RFIDListener listener) {
        if (listener == null || name == null) {
            return;
        }
        this.onRFIDListeners.add(name, listener);
    }

    public final void removeRFIDListener(String name) {
        if (name != null) {
            this.onRFIDListeners.remove(name);
        }
    }
}
