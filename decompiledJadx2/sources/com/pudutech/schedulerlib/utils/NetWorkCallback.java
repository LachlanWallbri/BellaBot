package com.pudutech.schedulerlib.utils;

import android.net.ConnectivityManager;
import android.net.Network;
import kotlin.Metadata;

/* compiled from: PdNetworkUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/schedulerlib/utils/NetWorkCallback;", "Landroid/net/ConnectivityManager$NetworkCallback;", "()V", "onNetWokChange", "", "network", "Landroid/net/Network;", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public abstract class NetWorkCallback extends ConnectivityManager.NetworkCallback {
    public abstract void onNetWokChange(Network network);
}
