package com.pudutech.bumblebee.robot_ui.roomdata;

import androidx.lifecycle.LiveData;
import com.pudutech.bumblebee.robot_ui.bean.TtsVoiceData;
import java.util.List;
import kotlin.Metadata;

/* compiled from: TtsVoiceDao.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H'J\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H'J\u0010\u0010\r\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u0004H'J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H'J\u0016\u0010\u0011\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u0012H'J \u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0010H'J&\u0010\u0016\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u00122\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0010H'J\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0015\u001a\u00020\u0010H'J\u001a\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H'J\u0010\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u0004H'J\u0016\u0010\u0019\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H'R\u001c\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038gX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/roomdata/TtsVoiceDao;", "", "ttsAllList", "", "Lcom/pudutech/bumblebee/robot_ui/bean/TtsVoiceData;", "getTtsAllList", "()Ljava/util/List;", "addTts", "", "ttsVoiceData", "addTtsAll", "", "ttsVoiceDatas", "deleteTts", "deleteTtsByMd5", "mMd5", "", "getTtsAllLiveData", "Landroidx/lifecycle/LiveData;", "getTtsList", "mLocale", "mTtsType", "getTtsLiveData", "getTtsMd5Data", "getTtsMd5List", "updataTts", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public interface TtsVoiceDao {
    long addTts(TtsVoiceData ttsVoiceData);

    void addTtsAll(List<TtsVoiceData> ttsVoiceDatas);

    void deleteTts(TtsVoiceData ttsVoiceData);

    void deleteTtsByMd5(String mMd5);

    List<TtsVoiceData> getTtsAllList();

    LiveData<List<TtsVoiceData>> getTtsAllLiveData();

    List<TtsVoiceData> getTtsList(String mLocale, String mTtsType);

    LiveData<List<TtsVoiceData>> getTtsLiveData(String mLocale, String mTtsType);

    TtsVoiceData getTtsMd5Data(String mMd5, String mTtsType);

    List<TtsVoiceData> getTtsMd5List(String mMd5);

    void updataTts(TtsVoiceData ttsVoiceData);

    void updataTts(List<TtsVoiceData> ttsVoiceDatas);
}
