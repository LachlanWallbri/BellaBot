package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.manager.SystemSoundManager;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSeekBarChangeListener;
import com.pudutech.bumblebee.robot_ui.util.PlaySound;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SoundFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u0010\b\u001a\u00020\u0006H\u0002J\b\u0010\t\u001a\u00020\u0006H\u0002J&\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u001a\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0018\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/SoundFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "initBtnVoice", "", "initMusicVoice", "initSoundVoice", "initTtsSoundVoice", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "setProgressTv", "tv", "Landroid/widget/TextView;", "seekBar", "Landroid/widget/SeekBar;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
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
        return inflater.inflate(C4188R.layout.fragment_sound_setup, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().keepVolumeMax();
        initMusicVoice();
        initSoundVoice();
        initTtsSoundVoice();
        initBtnVoice();
    }

    private final void initMusicVoice() {
        SeekBar music_degree = (SeekBar) _$_findCachedViewById(C4188R.id.music_degree);
        Intrinsics.checkExpressionValueIsNotNull(music_degree, "music_degree");
        music_degree.setProgress(SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getMusicVoice());
        TextView song_progress_tv = (TextView) _$_findCachedViewById(C4188R.id.song_progress_tv);
        Intrinsics.checkExpressionValueIsNotNull(song_progress_tv, "song_progress_tv");
        SeekBar music_degree2 = (SeekBar) _$_findCachedViewById(C4188R.id.music_degree);
        Intrinsics.checkExpressionValueIsNotNull(music_degree2, "music_degree");
        setProgressTv(song_progress_tv, music_degree2);
        ((SeekBar) _$_findCachedViewById(C4188R.id.music_degree)).setOnSeekBarChangeListener(new VoiceSeekBarChangeListener(false, null, 0, new Function3<SeekBar, Integer, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SoundFragment$initMusicVoice$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(SeekBar seekBar, Integer num, Boolean bool) {
                invoke(seekBar, num.intValue(), bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(SeekBar seekBar, int i, boolean z) {
                if (seekBar != null) {
                    SoundFragment soundFragment = SoundFragment.this;
                    TextView song_progress_tv2 = (TextView) soundFragment._$_findCachedViewById(C4188R.id.song_progress_tv);
                    Intrinsics.checkExpressionValueIsNotNull(song_progress_tv2, "song_progress_tv");
                    soundFragment.setProgressTv(song_progress_tv2, seekBar);
                }
            }
        }, null, new Function1<SeekBar, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SoundFragment$initMusicVoice$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SeekBar seekBar) {
                invoke2(seekBar);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SeekBar seekBar) {
                String str;
                str = SoundFragment.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("music progress :");
                sb.append(seekBar != null ? Integer.valueOf(seekBar.getProgress()) : null);
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
                if (seekBar != null) {
                    SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().setMusicVoice(seekBar.getProgress());
                }
                PlaySound.play(RobotContext.INSTANCE.getContext(), C4188R.raw.voice_tmp, SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getNOW_AUDIO_TYPE(), SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getMusicVoice() / 100.0f);
            }
        }, 22, null));
    }

    private final void initSoundVoice() {
        SeekBar sound_degree = (SeekBar) _$_findCachedViewById(C4188R.id.sound_degree);
        Intrinsics.checkExpressionValueIsNotNull(sound_degree, "sound_degree");
        sound_degree.setProgress(SystemSoundManager.INSTANCE.getSoundHelper().getSoundVocie());
        TextView sound_progress_tv = (TextView) _$_findCachedViewById(C4188R.id.sound_progress_tv);
        Intrinsics.checkExpressionValueIsNotNull(sound_progress_tv, "sound_progress_tv");
        SeekBar sound_degree2 = (SeekBar) _$_findCachedViewById(C4188R.id.sound_degree);
        Intrinsics.checkExpressionValueIsNotNull(sound_degree2, "sound_degree");
        setProgressTv(sound_progress_tv, sound_degree2);
        ((SeekBar) _$_findCachedViewById(C4188R.id.sound_degree)).setOnSeekBarChangeListener(new VoiceSeekBarChangeListener(false, null, 0, new Function3<SeekBar, Integer, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SoundFragment$initSoundVoice$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(SeekBar seekBar, Integer num, Boolean bool) {
                invoke(seekBar, num.intValue(), bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(SeekBar seekBar, int i, boolean z) {
                if (seekBar != null) {
                    SoundFragment soundFragment = SoundFragment.this;
                    TextView sound_progress_tv2 = (TextView) soundFragment._$_findCachedViewById(C4188R.id.sound_progress_tv);
                    Intrinsics.checkExpressionValueIsNotNull(sound_progress_tv2, "sound_progress_tv");
                    soundFragment.setProgressTv(sound_progress_tv2, seekBar);
                }
            }
        }, null, new Function1<SeekBar, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SoundFragment$initSoundVoice$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SeekBar seekBar) {
                invoke2(seekBar);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SeekBar seekBar) {
                String str;
                str = SoundFragment.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("sound progress :");
                sb.append(seekBar != null ? Integer.valueOf(seekBar.getProgress()) : null);
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
                if (seekBar != null) {
                    SystemSoundManager.INSTANCE.getSoundHelper().setSoundVoice(seekBar.getProgress());
                }
                PlaySound.play(RobotContext.INSTANCE.getContext(), C4188R.raw.voice_tmp, SystemSoundManager.INSTANCE.getSoundHelper().getNOW_AUDIO_TYPE(), 1.0f);
            }
        }, 22, null));
    }

    private final void initTtsSoundVoice() {
        SeekBar sbTtsSound = (SeekBar) _$_findCachedViewById(C4188R.id.sbTtsSound);
        Intrinsics.checkExpressionValueIsNotNull(sbTtsSound, "sbTtsSound");
        sbTtsSound.setProgress(SystemSoundManager.INSTANCE.getTtsSoundHelper().getTtsSoundVoice());
        TextView tvTtsSound = (TextView) _$_findCachedViewById(C4188R.id.tvTtsSound);
        Intrinsics.checkExpressionValueIsNotNull(tvTtsSound, "tvTtsSound");
        SeekBar sbTtsSound2 = (SeekBar) _$_findCachedViewById(C4188R.id.sbTtsSound);
        Intrinsics.checkExpressionValueIsNotNull(sbTtsSound2, "sbTtsSound");
        setProgressTv(tvTtsSound, sbTtsSound2);
        ((SeekBar) _$_findCachedViewById(C4188R.id.sbTtsSound)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SoundFragment$initTtsSoundVoice$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                SoundFragment soundFragment = SoundFragment.this;
                TextView tvTtsSound2 = (TextView) soundFragment._$_findCachedViewById(C4188R.id.tvTtsSound);
                Intrinsics.checkExpressionValueIsNotNull(tvTtsSound2, "tvTtsSound");
                soundFragment.setProgressTv(tvTtsSound2, seekBar);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                String str;
                Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                str = SoundFragment.this.TAG;
                Pdlog.m3273d(str, "sound progress :" + seekBar.getProgress());
                SystemSoundManager.INSTANCE.getTtsSoundHelper().setTtsSoundVoice(seekBar.getProgress());
                PlaySound.play(RobotContext.INSTANCE.getContext(), C4188R.raw.voice_tmp, SystemSoundManager.INSTANCE.getTtsSoundHelper().getNOW_AUDIO_TYPE(), 1.0f);
            }
        });
    }

    private final void initBtnVoice() {
        SeekBar btn_voice_bar = (SeekBar) _$_findCachedViewById(C4188R.id.btn_voice_bar);
        Intrinsics.checkExpressionValueIsNotNull(btn_voice_bar, "btn_voice_bar");
        btn_voice_bar.setProgress(SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getBtnVoice());
        TextView click_voice_progress_tv = (TextView) _$_findCachedViewById(C4188R.id.click_voice_progress_tv);
        Intrinsics.checkExpressionValueIsNotNull(click_voice_progress_tv, "click_voice_progress_tv");
        SeekBar btn_voice_bar2 = (SeekBar) _$_findCachedViewById(C4188R.id.btn_voice_bar);
        Intrinsics.checkExpressionValueIsNotNull(btn_voice_bar2, "btn_voice_bar");
        setProgressTv(click_voice_progress_tv, btn_voice_bar2);
        ((SeekBar) _$_findCachedViewById(C4188R.id.btn_voice_bar)).setOnSeekBarChangeListener(new VoiceSeekBarChangeListener(false, null, 0, new Function3<SeekBar, Integer, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SoundFragment$initBtnVoice$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(SeekBar seekBar, Integer num, Boolean bool) {
                invoke(seekBar, num.intValue(), bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(SeekBar seekBar, int i, boolean z) {
                if (seekBar != null) {
                    SoundFragment soundFragment = SoundFragment.this;
                    TextView click_voice_progress_tv2 = (TextView) soundFragment._$_findCachedViewById(C4188R.id.click_voice_progress_tv);
                    Intrinsics.checkExpressionValueIsNotNull(click_voice_progress_tv2, "click_voice_progress_tv");
                    soundFragment.setProgressTv(click_voice_progress_tv2, seekBar);
                }
            }
        }, null, new Function1<SeekBar, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SoundFragment$initBtnVoice$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SeekBar seekBar) {
                invoke2(seekBar);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SeekBar seekBar) {
                String str;
                str = SoundFragment.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("sound progress :");
                sb.append(seekBar != null ? Integer.valueOf(seekBar.getProgress()) : null);
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
                if (seekBar != null) {
                    SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().setBtnVoice(seekBar.getProgress());
                }
                PlaySound.play(RobotContext.INSTANCE.getContext(), C4188R.raw.voice_tmp, SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getNOW_AUDIO_TYPE(), SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getBtnVoice() / 100.0f);
            }
        }, 22, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setProgressTv(TextView tv, SeekBar seekBar) {
        int progress = (seekBar.getProgress() * 100) / seekBar.getMax();
        StringBuilder sb = new StringBuilder();
        sb.append(progress);
        sb.append('%');
        tv.setText(sb.toString());
    }
}
