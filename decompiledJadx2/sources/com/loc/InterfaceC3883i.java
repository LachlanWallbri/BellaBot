package com.loc;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: ILocationProviderService.java */
/* renamed from: com.loc.i */
/* loaded from: classes4.dex */
public interface InterfaceC3883i extends IInterface {

    /* compiled from: ILocationProviderService.java */
    /* renamed from: com.loc.i$a */
    /* loaded from: classes4.dex */
    public static abstract class a extends Binder implements InterfaceC3883i {

        /* compiled from: ILocationProviderService.java */
        /* renamed from: com.loc.i$a$a, reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        private static class C9032a implements InterfaceC3883i {

            /* renamed from: a */
            private IBinder f4221a;

            C9032a(IBinder iBinder) {
                this.f4221a = iBinder;
            }

            @Override // com.loc.InterfaceC3883i
            /* renamed from: a */
            public final int mo3116a(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.amap.api.service.locationprovider.ILocationProviderService");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f4221a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        bundle.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f4221a;
            }
        }

        /* renamed from: a */
        public static InterfaceC3883i m3117a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.amap.api.service.locationprovider.ILocationProviderService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof InterfaceC3883i)) ? new C9032a(iBinder) : (InterfaceC3883i) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.amap.api.service.locationprovider.ILocationProviderService");
                return true;
            }
            parcel.enforceInterface("com.amap.api.service.locationprovider.ILocationProviderService");
            Bundle bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
            int a = mo3116a(bundle);
            parcel2.writeNoException();
            parcel2.writeInt(a);
            if (bundle != null) {
                parcel2.writeInt(1);
                bundle.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }
    }

    /* renamed from: a */
    int mo3116a(Bundle bundle) throws RemoteException;
}
