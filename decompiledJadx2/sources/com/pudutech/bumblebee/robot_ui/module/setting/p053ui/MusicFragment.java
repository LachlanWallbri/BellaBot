package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListener;
import com.pudutech.importmusic.C4619R;
import com.pudutech.importmusic.ImportMusicActivity;
import com.pudutech.importmusic.MusicHttpServer;
import com.pudutech.importmusic.NotiDialog;
import com.pudutech.importmusic.QRCodeUtils;
import com.pudutech.importmusic.utils.Constant;
import com.pudutech.importmusic.utils.DensityUtil;
import com.pudutech.mpmodule.DeleteMusicActivity;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.mpmodule.PlaylistEditActivity;
import com.pudutech.mpmodule.database.DatabaseManagerFactory;
import com.pudutech.mpmodule.permission.PermissionManager;
import com.pudutech.mpmodule.utils.AppCommonUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import java.io.IOException;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MusicFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\u000eH\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0002J\u0012\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J&\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u001b\u001a\u00020\u000eH\u0016J\b\u0010\u001c\u001a\u00020\u000eH\u0016J\u001a\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u00162\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u001f\u001a\u00020\u000eH\u0002J\b\u0010 \u001a\u00020\u000eH\u0002J\b\u0010!\u001a\u00020\u000eH\u0002J\b\u0010\"\u001a\u00020\u000eH\u0002J\b\u0010#\u001a\u00020\u000eH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/MusicFragment;", "Landroidx/fragment/app/Fragment;", "()V", "mCrashListener", "Lcom/pudutech/importmusic/ImportMusicActivity$ServerCrashListener;", "mDialog", "Lcom/pudutech/importmusic/NotiDialog;", "mHandler", "Landroid/os/Handler;", "mReceivedSongName", "", "mWebserver", "Lcom/pudutech/importmusic/MusicHttpServer;", "fetchImportQrCode", "", "initCrashListeners", "initView", "notifyServerCrash", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onPause", "onResume", "onViewCreated", "view", "releaseRes", "requestMustPermissions", "showNotIProgressDialog", "showOutOfLegalSizeDialog", "showSuccessDialog", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MusicFragment extends Fragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "MusicFragment";
    private HashMap _$_findViewCache;
    private ImportMusicActivity.ServerCrashListener mCrashListener;
    private NotiDialog mDialog;
    private final Handler mHandler = new Handler() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MusicFragment$mHandler$1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            NotiDialog notiDialog;
            NotiDialog notiDialog2;
            NotiDialog notiDialog3;
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            switch (msg.what) {
                case Constant.IMPORT_MUSIC_FLAG /* 783 */:
                    Object obj = msg.obj;
                    if (obj != null) {
                        int floatValue = (int) (((Float) obj).floatValue() * 100);
                        notiDialog = MusicFragment.this.mDialog;
                        if (notiDialog != null && notiDialog.getType() != NotiDialog.DialogType.NOTIPROGRESS) {
                            MusicFragment.this.showNotIProgressDialog();
                        }
                        notiDialog2 = MusicFragment.this.mDialog;
                        if (notiDialog2 == null) {
                            MusicFragment.this.showNotIProgressDialog();
                        }
                        notiDialog3 = MusicFragment.this.mDialog;
                        if (notiDialog3 == null || !notiDialog3.isShowing()) {
                            return;
                        }
                        if (floatValue >= 100) {
                            removeMessages(Constant.IMPORT_MUSIC_FLAG);
                            notiDialog3.dismiss();
                            return;
                        } else {
                            notiDialog3.setContent(floatValue);
                            return;
                        }
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
                case Constant.IMPORT_MUSIC_NAME /* 784 */:
                    MusicFragment musicFragment = MusicFragment.this;
                    Object obj2 = msg.obj;
                    if (obj2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                    }
                    musicFragment.mReceivedSongName = (String) obj2;
                    return;
                case Constant.MSG_IMPORT_MUSIC_OUTOF_LEGAL_SIZE /* 785 */:
                    MusicFragment.this.showOutOfLegalSizeDialog();
                    return;
                default:
                    return;
            }
        }
    };
    private String mReceivedSongName;
    private MusicHttpServer mWebserver;

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

    /* compiled from: MusicFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/MusicFragment$Companion;", "", "()V", "TAG", "", "newInstance", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/MusicFragment;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final MusicFragment newInstance() {
            MusicFragment musicFragment = new MusicFragment();
            musicFragment.setArguments(new Bundle());
            return musicFragment;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C4188R.layout.fragment_music, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        requestMustPermissions();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        fetchImportQrCode();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        releaseRes();
    }

    private final void initView() {
        TextView tv_import_des = (TextView) _$_findCachedViewById(C4188R.id.tv_import_des);
        Intrinsics.checkExpressionValueIsNotNull(tv_import_des, "tv_import_des");
        tv_import_des.setText(getString(C4188R.string.pdStr10_56) + '\n' + getString(C4188R.string.pdStr10_57));
        Switch sw_music = (Switch) _$_findCachedViewById(C4188R.id.sw_music);
        Intrinsics.checkExpressionValueIsNotNull(sw_music, "sw_music");
        sw_music.setChecked(AppCommonUtil.isOpenMusicSwitch());
        ((Switch) _$_findCachedViewById(C4188R.id.sw_music)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MusicFragment$initView$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton compoundButton, boolean z) {
                Intrinsics.checkParameterIsNotNull(compoundButton, "<anonymous parameter 0>");
                Pdlog.m3273d("MusicFragment", "MusicFragment--music:" + z);
                AppCommonUtil.setOpenMusicSwitch(z);
                if (z) {
                    return;
                }
                Pdlog.m3273d("MusicFragment", "open music switch is closed, release player.");
                MusicPlayerHelper.getInstance().release();
            }
        }, 7, null));
        Switch sw_breakpoint = (Switch) _$_findCachedViewById(C4188R.id.sw_breakpoint);
        Intrinsics.checkExpressionValueIsNotNull(sw_breakpoint, "sw_breakpoint");
        sw_breakpoint.setChecked(AppCommonUtil.isOpenConnectionPlay());
        ((Switch) _$_findCachedViewById(C4188R.id.sw_breakpoint)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MusicFragment$initView$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton compoundButton, boolean z) {
                Intrinsics.checkParameterIsNotNull(compoundButton, "<anonymous parameter 0>");
                Pdlog.m3273d("MusicFragment", "MusicFragment--breakpoint:" + z);
                AppCommonUtil.switchConnectionPlay(z);
                MusicPlayerHelper.getInstance().resetConnectionPlayMode(z);
            }
        }, 7, null));
        ((TextView) _$_findCachedViewById(C4188R.id.tv_file_manager)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MusicFragment$initView$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                MusicFragment musicFragment = MusicFragment.this;
                musicFragment.startActivity(new Intent(musicFragment.getContext(), (Class<?>) DeleteMusicActivity.class));
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.tv_list_edit)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MusicFragment$initView$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                MusicPlayerHelper.getInstance().release();
                MusicFragment musicFragment = MusicFragment.this;
                musicFragment.startActivity(new Intent(musicFragment.getContext(), (Class<?>) PlaylistEditActivity.class));
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.tv_import)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MusicFragment$initView$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                Group group_import = (Group) MusicFragment.this._$_findCachedViewById(C4188R.id.group_import);
                Intrinsics.checkExpressionValueIsNotNull(group_import, "group_import");
                if (group_import.getVisibility() == 0) {
                    Group group_import2 = (Group) MusicFragment.this._$_findCachedViewById(C4188R.id.group_import);
                    Intrinsics.checkExpressionValueIsNotNull(group_import2, "group_import");
                    group_import2.setVisibility(8);
                    Resources resources = MusicFragment.this.getResources();
                    int i = C4188R.drawable.ic_arrow_end;
                    Context context = MusicFragment.this.getContext();
                    Drawable drawable = ResourcesCompat.getDrawable(resources, i, context != null ? context.getTheme() : null);
                    if (drawable != null) {
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    }
                    ((TextView) MusicFragment.this._$_findCachedViewById(C4188R.id.tv_import)).setCompoundDrawablesRelative(null, null, drawable, null);
                    return;
                }
                Group group_import3 = (Group) MusicFragment.this._$_findCachedViewById(C4188R.id.group_import);
                Intrinsics.checkExpressionValueIsNotNull(group_import3, "group_import");
                group_import3.setVisibility(0);
                Resources resources2 = MusicFragment.this.getResources();
                int i2 = C4188R.drawable.ic_arrow_down;
                Context context2 = MusicFragment.this.getContext();
                Drawable drawable2 = ResourcesCompat.getDrawable(resources2, i2, context2 != null ? context2.getTheme() : null);
                if (drawable2 != null) {
                    drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                }
                ((TextView) MusicFragment.this._$_findCachedViewById(C4188R.id.tv_import)).setCompoundDrawablesRelative(null, null, drawable2, null);
            }
        });
    }

    private final void fetchImportQrCode() {
        initCrashListeners();
        MusicHttpServer.verifyStoragePermissions(getActivity());
        try {
            FragmentActivity activity = getActivity();
            this.mWebserver = new MusicHttpServer(activity != null ? activity.getApplicationContext() : null, this.mHandler);
        } catch (IOException e) {
            e.printStackTrace();
            Pdlog.m3273d(TAG, "connect music import server fail, exception: " + e);
            notifyServerCrash();
        }
        FragmentActivity activity2 = getActivity();
        String localIpStr = MusicHttpServer.getLocalIpStr(activity2 != null ? activity2.getApplicationContext() : null);
        Bitmap createQRCodeBitmap = QRCodeUtils.createQRCodeBitmap("http://" + localIpStr + "/pdd", DensityUtil.dp2px(200), DensityUtil.dp2px(200));
        if (createQRCodeBitmap == null) {
            Pdlog.m3273d(TAG, "get QR code failed.");
            notifyServerCrash();
        }
        Pdlog.m3273d(TAG, "httpserver address: " + localIpStr);
        ((ImageView) _$_findCachedViewById(C4188R.id.iv_import)).setImageBitmap(createQRCodeBitmap);
    }

    private final void releaseRes() {
        MusicHttpServer musicHttpServer = this.mWebserver;
        if (musicHttpServer != null) {
            musicHttpServer.closeAllConnections();
            this.mWebserver = (MusicHttpServer) null;
            Pdlog.m3273d(TAG, "app stop, so web server close");
        }
        this.mHandler.removeCallbacksAndMessages(null);
        NotiDialog notiDialog = this.mDialog;
        if (notiDialog == null || !notiDialog.isShowing()) {
            return;
        }
        notiDialog.setOnDismissListener(null);
        notiDialog.dismiss();
        this.mDialog = (NotiDialog) null;
    }

    private final void notifyServerCrash() {
        ImportMusicActivity.ServerCrashListener serverCrashListener = this.mCrashListener;
        if (serverCrashListener != null) {
            serverCrashListener.restartServer();
        }
    }

    private final void initCrashListeners() {
        if (this.mCrashListener == null) {
            this.mCrashListener = new MusicFragment$initCrashListeners$1(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSuccessDialog() {
        final Context context = getContext();
        if (context != null) {
            NotiDialog notiDialog = this.mDialog;
            if (notiDialog != null && notiDialog.isShowing()) {
                notiDialog.setOnDismissListener(null);
                notiDialog.dismiss();
            }
            final NotiDialog.DialogType dialogType = NotiDialog.DialogType.NOTISUCCESS;
            this.mDialog = new NotiDialog(context, dialogType) { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MusicFragment$showSuccessDialog$$inlined$let$lambda$1
                @Override // com.pudutech.importmusic.NotiDialog
                public void setContent(int e) {
                    String str;
                    String str2;
                    TextView textView = (TextView) findViewById(C4188R.id.music_name);
                    if (textView != null) {
                        str = this.mReceivedSongName;
                        if (!TextUtils.isEmpty(str)) {
                            str2 = this.mReceivedSongName;
                            textView.setText(str2);
                        } else {
                            textView.setVisibility(8);
                        }
                    }
                }
            };
            NotiDialog notiDialog2 = this.mDialog;
            if (notiDialog2 != null) {
                notiDialog2.setContentView(C4188R.layout.import_music_dialog_success_tip);
                notiDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MusicFragment$showSuccessDialog$$inlined$let$lambda$2
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        MusicFragment.this.mDialog = (NotiDialog) null;
                    }
                });
                notiDialog2.setContent(0);
                notiDialog2.show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOutOfLegalSizeDialog() {
        NotiDialog notiDialog = this.mDialog;
        NotiDialog notiDialog2 = null;
        if (notiDialog != null && notiDialog.isShowing()) {
            notiDialog.setOnDismissListener(null);
            notiDialog.dismiss();
        }
        Context context = getContext();
        if (context != null) {
            notiDialog2 = new NotiDialog(context, NotiDialog.DialogType.NOTIOUTOFLEGALSIZE);
            notiDialog2.setContentView(C4619R.layout.import_music_dialog_outof_legalsize_tip);
            notiDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MusicFragment$showOutOfLegalSizeDialog$$inlined$apply$lambda$1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    MusicFragment.this.mDialog = (NotiDialog) null;
                }
            });
            notiDialog2.show();
        }
        this.mDialog = notiDialog2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showNotIProgressDialog() {
        final Context context = getContext();
        if (context != null) {
            NotiDialog notiDialog = this.mDialog;
            if (notiDialog != null && notiDialog.isShowing()) {
                notiDialog.setOnDismissListener(null);
                notiDialog.dismiss();
            }
            final NotiDialog.DialogType dialogType = NotiDialog.DialogType.NOTIPROGRESS;
            this.mDialog = new NotiDialog(context, dialogType) { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MusicFragment$showNotIProgressDialog$1$2
                @Override // com.pudutech.importmusic.NotiDialog
                public void setContent(int e) {
                    TextView textView = (TextView) findViewById(C4619R.id.dialog_content);
                    if (textView != null) {
                        textView.setText(String.valueOf(e));
                    }
                }
            };
            NotiDialog notiDialog2 = this.mDialog;
            if (notiDialog2 != null) {
                notiDialog2.setContentView(C4619R.layout.import_music_process_dialog_layout);
                notiDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MusicFragment$showNotIProgressDialog$$inlined$let$lambda$1
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        MusicFragment.this.showSuccessDialog();
                    }
                });
                notiDialog2.show();
            }
        }
    }

    private final void requestMustPermissions() {
        PermissionManager.getInstance().checkPermission(new RxPermissions(this), new PermissionManager.OnPermissionResultListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MusicFragment$requestMustPermissions$1
            @Override // com.pudutech.mpmodule.permission.PermissionManager.OnPermissionResultListener
            public void onUnGranted() {
            }

            @Override // com.pudutech.mpmodule.permission.PermissionManager.OnPermissionResultListener
            public void onGranted() {
                Pdlog.m3273d("MusicFragment", "reset and recreate database in music fragment page first");
                DatabaseManagerFactory.getDatabaseManager().initDatabase();
                DatabaseManagerFactory.getDatabaseManager().initDefaultPlaylists(MusicFragment.this.getContext());
            }
        }, "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE");
    }
}
