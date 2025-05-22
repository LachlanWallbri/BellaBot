package com.pudutech.lora.library;

import kotlin.Metadata;
import org.simpleframework.xml.strategy.Name;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: IDataReceiveListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/lora/library/IDataReceiveListener;", "", "onReceive", "", "channel", "Lcom/pudutech/lora/library/LoRaChannel;", "data", "", Name.LENGTH, "", "library_lora_release"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public interface IDataReceiveListener {
    void onReceive(LoRaChannel channel, byte[] data, int length);
}
