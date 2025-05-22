package androidx.media;

import android.os.Bundle;
import androidx.versionedparcelable.VersionedParcelable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
interface AudioAttributesImpl extends VersionedParcelable {
    Object getAudioAttributes();

    int getContentType();

    int getFlags();

    int getLegacyStreamType();

    int getRawLegacyStreamType();

    int getUsage();

    int getVolumeControlStream();

    Bundle toBundle();
}
