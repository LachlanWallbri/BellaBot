package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Channel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, m3961d2 = {"Channel", "Lkotlinx/coroutines/channels/Channel;", ExifInterface.LONGITUDE_EAST, "capacity", "", "kotlinx-coroutines-core"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ChannelKt {
    public static /* synthetic */ Channel Channel$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return Channel(i);
    }

    public static final <E> Channel<E> Channel(int i) {
        if (i == -2) {
            return new ArrayChannel(Channel.INSTANCE.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core());
        }
        if (i == -1) {
            return new ConflatedChannel();
        }
        if (i == 0) {
            return new RendezvousChannel();
        }
        if (i == Integer.MAX_VALUE) {
            return new LinkedListChannel();
        }
        return new ArrayChannel(i);
    }
}
