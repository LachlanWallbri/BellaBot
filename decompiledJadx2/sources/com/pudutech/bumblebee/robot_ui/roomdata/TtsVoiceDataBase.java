package com.pudutech.bumblebee.robot_ui.roomdata;

import androidx.room.RoomDatabase;
import kotlin.Metadata;

/* compiled from: TtsVoiceDataBase.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/roomdata/TtsVoiceDataBase;", "Landroidx/room/RoomDatabase;", "()V", "getCallHistoryDao", "Lcom/pudutech/bumblebee/robot_ui/roomdata/CallHistoryDao;", "getPalletTtsScheme", "Lcom/pudutech/bumblebee/robot_ui/roomdata/PalletTtsSchemeDao;", "getTtsVoiceDao", "Lcom/pudutech/bumblebee/robot_ui/roomdata/TtsVoiceDao;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public abstract class TtsVoiceDataBase extends RoomDatabase {
    public abstract CallHistoryDao getCallHistoryDao();

    public abstract PalletTtsSchemeDao getPalletTtsScheme();

    public abstract TtsVoiceDao getTtsVoiceDao();
}
