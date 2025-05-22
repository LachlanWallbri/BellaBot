package com.google.common.util.concurrent;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.util.concurrent.Service;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
interface ServiceManagerBridge {
    ImmutableMultimap<Service.State, Service> servicesByState();
}
