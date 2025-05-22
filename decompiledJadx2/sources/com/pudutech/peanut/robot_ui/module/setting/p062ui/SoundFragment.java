package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.fragment.app.Fragment;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.manager.SystemSoundManager;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SoundFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u0010\b\u001a\u00020\u0006H\u0002J&\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/SoundFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "initBtnVoice", "", "initMusicVoice", "initSoundVoice", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SoundFragment extends Fragment {
    private final String TAG = "SoundFragment";
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C5508R.layout.fragment_sound_setup, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().keepVolumeMax();
        initMusicVoice();
        initSoundVoice();
        initBtnVoice();
    }

    private final void initMusicVoice() {
        SeekBar music_degree = (SeekBar) _$_findCachedViewById(C5508R.id.music_degree);
        Intrinsics.checkExpressionValueIsNotNull(music_degree, "music_degree");
        music_degree.setProgress(SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getMusicVoice());
        ((SeekBar) _$_findCachedViewById(C5508R.id.music_degree)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.SoundFragment$initMusicVoice$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                String str;
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                str = SoundFragment.this.TAG;
                Pdlog.m3273d(str, "music progress :" + seekBar.getProgress());
                SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().setMusicVoice(seekBar.getProgress());
                PlaySound.play(RobotContext.INSTANCE.getContext(), C5508R.raw.voice_tmp, SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getNOW_AUDIO_TYPE(), ((float) SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getMusicVoice()) / 100.0f);
            }
        });
    }

    private final void initSoundVoice() {
        SeekBar sound_degree = (SeekBar) _$_findCachedViewById(C5508R.id.sound_degree);
        Intrinsics.checkExpressionValueIsNotNull(sound_degree, "sound_degree");
        sound_degree.setProgress(SystemSoundManager.INSTANCE.getSoundHelper().getSoundVocie());
        ((SeekBar) _$_findCachedViewById(C5508R.id.sound_degree)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.SoundFragment$initSoundVoice$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                String str;
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                str = SoundFragment.this.TAG;
                Pdlog.m3273d(str, "sound progress :" + seekBar.getProgress());
                SystemSoundManager.INSTANCE.getSoundHelper().setSoundVoice(seekBar.getProgress());
                PlaySound.play(RobotContext.INSTANCE.getContext(), C5508R.raw.voice_tmp, SystemSoundManager.INSTANCE.getSoundHelper().getNOW_AUDIO_TYPE(), ((float) SystemSoundManager.INSTANCE.getSoundHelper().getSoundVocie()) / 100.0f);
            }
        });
    }

    private final void initBtnVoice() {
        SeekBar btn_voice_bar = (SeekBar) _$_findCachedViewById(C5508R.id.btn_voice_bar);
        Intrinsics.checkExpressionValueIsNotNull(btn_voice_bar, "btn_voice_bar");
        btn_voice_bar.setProgress(SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getBtnVoice());
        ((SeekBar) _$_findCachedViewById(C5508R.id.btn_voice_bar)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.SoundFragment$initBtnVoice$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                String str;
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                str = SoundFragment.this.TAG;
                Pdlog.m3273d(str, "sound progress :" + seekBar.getProgress());
                SystemSoundManager.INSTANCE.getSoundHelper().setBtnVoice(seekBar.getProgress());
                PlaySound.play(RobotContext.INSTANCE.getContext(), C5508R.raw.voice_tmp, SystemSoundManager.INSTANCE.getSoundHelper().getNOW_AUDIO_TYPE(), ((float) SystemSoundManager.INSTANCE.getSoundHelper().getBtnVoice()) / 100.0f);
            }
        });
    }
}
