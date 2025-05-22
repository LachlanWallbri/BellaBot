package com.pudutech.disinfect.baselib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.C4429R;
import com.pudutech.disinfect.baselib.util.NavigationBarUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: ShowTipsDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0016\u0018\u0000 E2\u00020\u0001:\u0001EB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010(\u001a\u00020\u0014H\u0002J\b\u0010)\u001a\u00020\u0014H\u0016J\u0010\u0010*\u001a\u00020\u00142\u0006\u0010+\u001a\u00020,H\u0002J\u0010\u0010-\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010.\u001a\u00020\u0014H\u0002J\b\u0010/\u001a\u00020\u0014H\u0002J\u000e\u00100\u001a\u00020\u00142\u0006\u00101\u001a\u000202J\u000e\u00103\u001a\u00020\u00142\u0006\u00104\u001a\u00020\u0006J\u0016\u00105\u001a\u00020\u00142\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013J\u0016\u00106\u001a\u00020\u00142\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013J\u0016\u00107\u001a\u00020\u00142\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013J\u000e\u00108\u001a\u00020\u00142\u0006\u00109\u001a\u000202J\u0016\u0010:\u001a\u00020\u00142\u0006\u0010;\u001a\u0002022\u0006\u0010<\u001a\u000202J\u000e\u0010=\u001a\u00020\u00142\u0006\u00101\u001a\u000202J\u000e\u0010>\u001a\u00020\u00142\u0006\u0010?\u001a\u000202J\b\u0010@\u001a\u00020\u0014H\u0016J\u0017\u0010A\u001a\u00020\u00142\b\u0010B\u001a\u0004\u0018\u00010CH\u0002¢\u0006\u0002\u0010DR\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/dialog/ShowTipsDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "resId", "", "(Landroid/content/Context;I)V", "_context", "btnTips", "Landroid/widget/Button;", "dialogType", "fButton", "Landroid/widget/FrameLayout;", "ivLoading", "Landroid/widget/ImageView;", "lUpdateProgress", "Landroid/widget/LinearLayout;", "onBtnClick", "Lkotlin/Function0;", "", "onCloseClick", "onDismissCallback", "getOnDismissCallback", "()Lkotlin/jvm/functions/Function0;", "setOnDismissCallback", "(Lkotlin/jvm/functions/Function0;)V", "onTitleClick", "rLClose", "Landroid/widget/RelativeLayout;", "rotateAnimation", "Landroid/view/animation/RotateAnimation;", "scrollviewContent", "Landroid/widget/ScrollView;", "tvProgress", "Landroid/widget/TextView;", "tvTips", "tvTitle", "tvUpdateMessage", "tvVersionCode", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "dismiss", "findView", "view", "Landroid/view/View;", "init", "initListener", "initWidget", "setButTips", "tips", "", "setDialogType", "type", "setOnBtnClickListener", "setOnCloseClickListener", "setOnTitleClickListener", "setProgress", "p", "setScrollTips", "v", TmpConstant.SERVICE_DESC, "setTips", "setTitle", "title", "show", "showLoading", "loading", "", "(Ljava/lang/Boolean;)V", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public class ShowTipsDialog extends Dialog {
    private static final String TAG = "ShowTipsDialog";
    private static final int TITLE_AND_TIPS = 0;
    private Context _context;
    private Button btnTips;
    private int dialogType;
    private FrameLayout fButton;
    private ImageView ivLoading;
    private LinearLayout lUpdateProgress;
    private Function0<Unit> onBtnClick;
    private Function0<Unit> onCloseClick;
    private Function0<Unit> onDismissCallback;
    private Function0<Unit> onTitleClick;
    private RelativeLayout rLClose;
    private RotateAnimation rotateAnimation;
    private ScrollView scrollviewContent;
    private TextView tvProgress;
    private TextView tvTips;
    private TextView tvTitle;
    private TextView tvUpdateMessage;
    private TextView tvVersionCode;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int TITLE_TIPS_AND_CLOSE = 1;
    private static final int TITLE_TIPS_BOTTOM_BUTTON_AND_CLOSE = 2;
    private static final int TITLE_TIPS_SCROLL_CONTENT_BOTTOM_AND_BUTTON_CLOSE = 3;
    private static final int TITLE_TIPS_AND_BOTTOM_BUTTON = 4;
    private static final int TITLE_PROGRESS_CLOSE = 5;
    private static final int TITLE_LOADING_CLOSE = 6;
    private static final int TITLE_PROGRESS = 7;
    private static final int TITLE_TIPS_SCROLL_CONTENT_BOTTOM_AND_BUTTON = 8;

    public final Function0<Unit> getOnDismissCallback() {
        return this.onDismissCallback;
    }

    public final void setOnDismissCallback(Function0<Unit> function0) {
        this.onDismissCallback = function0;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: ShowTipsDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0014\u0010\u000b\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0014\u0010\r\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0014\u0010\u000f\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0014\u0010\u0011\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u0014\u0010\u0013\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0014\u0010\u0015\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\bR\u0014\u0010\u0017\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\b¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/dialog/ShowTipsDialog$Companion;", "", "()V", "TAG", "", "TITLE_AND_TIPS", "", "getTITLE_AND_TIPS", "()I", "TITLE_LOADING_CLOSE", "getTITLE_LOADING_CLOSE", "TITLE_PROGRESS", "getTITLE_PROGRESS", "TITLE_PROGRESS_CLOSE", "getTITLE_PROGRESS_CLOSE", "TITLE_TIPS_AND_BOTTOM_BUTTON", "getTITLE_TIPS_AND_BOTTOM_BUTTON", "TITLE_TIPS_AND_CLOSE", "getTITLE_TIPS_AND_CLOSE", "TITLE_TIPS_BOTTOM_BUTTON_AND_CLOSE", "getTITLE_TIPS_BOTTOM_BUTTON_AND_CLOSE", "TITLE_TIPS_SCROLL_CONTENT_BOTTOM_AND_BUTTON", "getTITLE_TIPS_SCROLL_CONTENT_BOTTOM_AND_BUTTON", "TITLE_TIPS_SCROLL_CONTENT_BOTTOM_AND_BUTTON_CLOSE", "getTITLE_TIPS_SCROLL_CONTENT_BOTTOM_AND_BUTTON_CLOSE", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getTITLE_AND_TIPS() {
            return ShowTipsDialog.TITLE_AND_TIPS;
        }

        public final int getTITLE_TIPS_AND_CLOSE() {
            return ShowTipsDialog.TITLE_TIPS_AND_CLOSE;
        }

        public final int getTITLE_TIPS_BOTTOM_BUTTON_AND_CLOSE() {
            return ShowTipsDialog.TITLE_TIPS_BOTTOM_BUTTON_AND_CLOSE;
        }

        public final int getTITLE_TIPS_SCROLL_CONTENT_BOTTOM_AND_BUTTON_CLOSE() {
            return ShowTipsDialog.TITLE_TIPS_SCROLL_CONTENT_BOTTOM_AND_BUTTON_CLOSE;
        }

        public final int getTITLE_TIPS_AND_BOTTOM_BUTTON() {
            return ShowTipsDialog.TITLE_TIPS_AND_BOTTOM_BUTTON;
        }

        public final int getTITLE_PROGRESS_CLOSE() {
            return ShowTipsDialog.TITLE_PROGRESS_CLOSE;
        }

        public final int getTITLE_LOADING_CLOSE() {
            return ShowTipsDialog.TITLE_LOADING_CLOSE;
        }

        public final int getTITLE_PROGRESS() {
            return ShowTipsDialog.TITLE_PROGRESS;
        }

        public final int getTITLE_TIPS_SCROLL_CONTENT_BOTTOM_AND_BUTTON() {
            return ShowTipsDialog.TITLE_TIPS_SCROLL_CONTENT_BOTTOM_AND_BUTTON;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShowTipsDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.dialogType = -1;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShowTipsDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.dialogType = -1;
        init(context);
    }

    private final void init(Context context) {
        this._context = context;
        build();
    }

    private final void build() {
        LayoutInflater layoutInflater = getLayoutInflater();
        Intrinsics.checkExpressionValueIsNotNull(layoutInflater, "layoutInflater");
        View dialog_layout = layoutInflater.inflate(C4429R.layout.dialog_common_show_tips, (ViewGroup) null);
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(17);
            window.setAttributes(attributes);
            window.setContentView(dialog_layout);
        }
        Intrinsics.checkExpressionValueIsNotNull(dialog_layout, "dialog_layout");
        findView(dialog_layout);
        initWidget();
        initListener();
    }

    private final void findView(View view) {
        this.tvTitle = (TextView) view.findViewById(C4429R.id.tvTitle);
        this.rLClose = (RelativeLayout) view.findViewById(C4429R.id.rl_close);
        this.scrollviewContent = (ScrollView) view.findViewById(C4429R.id.scrollview_content);
        this.tvVersionCode = (TextView) view.findViewById(C4429R.id.tvVersionCode);
        this.tvUpdateMessage = (TextView) view.findViewById(C4429R.id.tvUpdateMessage);
        this.ivLoading = (ImageView) view.findViewById(C4429R.id.iv_loading);
        this.lUpdateProgress = (LinearLayout) view.findViewById(C4429R.id.l_update_progress);
        this.tvProgress = (TextView) view.findViewById(C4429R.id.tvProgress);
        this.tvTips = (TextView) view.findViewById(C4429R.id.tvTips);
        this.fButton = (FrameLayout) view.findViewById(C4429R.id.f_button);
        this.btnTips = (Button) view.findViewById(C4429R.id.btnTips);
    }

    private final void initListener() {
        RelativeLayout relativeLayout = this.rLClose;
        if (relativeLayout != null) {
            relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.disinfect.baselib.dialog.ShowTipsDialog$initListener$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Function0 function0;
                    function0 = ShowTipsDialog.this.onCloseClick;
                    if (function0 != null) {
                    }
                    ShowTipsDialog.this.dismiss();
                }
            });
        }
        TextView textView = this.tvTitle;
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.disinfect.baselib.dialog.ShowTipsDialog$initListener$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Function0 function0;
                    function0 = ShowTipsDialog.this.onTitleClick;
                    if (function0 != null) {
                    }
                }
            });
        }
        Button button = this.btnTips;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.disinfect.baselib.dialog.ShowTipsDialog$initListener$3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Function0 function0;
                    function0 = ShowTipsDialog.this.onBtnClick;
                    if (function0 != null) {
                    }
                }
            });
        }
    }

    private final void initWidget() {
        Boolean bool;
        int i = this.dialogType;
        if (i == TITLE_AND_TIPS) {
            TextView textView = this.tvTips;
            if (textView != null) {
                textView.setVisibility(0);
            }
            TextView textView2 = this.tvTitle;
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
            RelativeLayout relativeLayout = this.rLClose;
            if (relativeLayout != null) {
                relativeLayout.setVisibility(8);
            }
            ScrollView scrollView = this.scrollviewContent;
            if (scrollView != null) {
                scrollView.setVisibility(8);
            }
            ImageView imageView = this.ivLoading;
            if (imageView != null) {
                imageView.setVisibility(8);
            }
            LinearLayout linearLayout = this.lUpdateProgress;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
            FrameLayout frameLayout = this.fButton;
            if (frameLayout != null) {
                frameLayout.setVisibility(8);
            }
            setCancelable(true);
        } else if (i == TITLE_TIPS_AND_CLOSE) {
            TextView textView3 = this.tvTips;
            if (textView3 != null) {
                textView3.setVisibility(0);
            }
            TextView textView4 = this.tvTitle;
            if (textView4 != null) {
                textView4.setVisibility(0);
            }
            RelativeLayout relativeLayout2 = this.rLClose;
            if (relativeLayout2 != null) {
                relativeLayout2.setVisibility(0);
            }
            ScrollView scrollView2 = this.scrollviewContent;
            if (scrollView2 != null) {
                scrollView2.setVisibility(8);
            }
            ImageView imageView2 = this.ivLoading;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            LinearLayout linearLayout2 = this.lUpdateProgress;
            if (linearLayout2 != null) {
                linearLayout2.setVisibility(8);
            }
            FrameLayout frameLayout2 = this.fButton;
            if (frameLayout2 != null) {
                frameLayout2.setVisibility(8);
            }
        } else if (i == TITLE_TIPS_BOTTOM_BUTTON_AND_CLOSE) {
            TextView textView5 = this.tvTips;
            if (textView5 != null) {
                textView5.setVisibility(0);
            }
            TextView textView6 = this.tvTitle;
            if (textView6 != null) {
                textView6.setVisibility(0);
            }
            RelativeLayout relativeLayout3 = this.rLClose;
            if (relativeLayout3 != null) {
                relativeLayout3.setVisibility(0);
            }
            FrameLayout frameLayout3 = this.fButton;
            if (frameLayout3 != null) {
                frameLayout3.setVisibility(0);
            }
            ScrollView scrollView3 = this.scrollviewContent;
            if (scrollView3 != null) {
                scrollView3.setVisibility(8);
            }
            ImageView imageView3 = this.ivLoading;
            if (imageView3 != null) {
                imageView3.setVisibility(8);
            }
            LinearLayout linearLayout3 = this.lUpdateProgress;
            if (linearLayout3 != null) {
                linearLayout3.setVisibility(8);
            }
        } else if (i == TITLE_TIPS_SCROLL_CONTENT_BOTTOM_AND_BUTTON_CLOSE) {
            TextView textView7 = this.tvTitle;
            if (textView7 != null) {
                textView7.setVisibility(0);
            }
            RelativeLayout relativeLayout4 = this.rLClose;
            if (relativeLayout4 != null) {
                relativeLayout4.setVisibility(0);
            }
            FrameLayout frameLayout4 = this.fButton;
            if (frameLayout4 != null) {
                frameLayout4.setVisibility(0);
            }
            ScrollView scrollView4 = this.scrollviewContent;
            if (scrollView4 != null) {
                scrollView4.setVisibility(0);
            }
            ImageView imageView4 = this.ivLoading;
            if (imageView4 != null) {
                imageView4.setVisibility(8);
            }
            LinearLayout linearLayout4 = this.lUpdateProgress;
            if (linearLayout4 != null) {
                linearLayout4.setVisibility(8);
            }
            TextView textView8 = this.tvTips;
            if (textView8 != null) {
                textView8.setVisibility(8);
            }
        } else if (i == TITLE_TIPS_AND_BOTTOM_BUTTON) {
            TextView textView9 = this.tvTitle;
            if (textView9 != null) {
                textView9.setVisibility(0);
            }
            TextView textView10 = this.tvTips;
            if (textView10 != null) {
                textView10.setVisibility(0);
            }
            FrameLayout frameLayout5 = this.fButton;
            if (frameLayout5 != null) {
                frameLayout5.setVisibility(0);
            }
            RelativeLayout relativeLayout5 = this.rLClose;
            if (relativeLayout5 != null) {
                relativeLayout5.setVisibility(8);
            }
            ScrollView scrollView5 = this.scrollviewContent;
            if (scrollView5 != null) {
                scrollView5.setVisibility(8);
            }
            ImageView imageView5 = this.ivLoading;
            if (imageView5 != null) {
                imageView5.setVisibility(8);
            }
            LinearLayout linearLayout5 = this.lUpdateProgress;
            if (linearLayout5 != null) {
                linearLayout5.setVisibility(8);
            }
        } else if (i == TITLE_PROGRESS_CLOSE) {
            TextView textView11 = this.tvTitle;
            if (textView11 != null) {
                textView11.setVisibility(0);
            }
            LinearLayout linearLayout6 = this.lUpdateProgress;
            if (linearLayout6 != null) {
                linearLayout6.setVisibility(0);
            }
            RelativeLayout relativeLayout6 = this.rLClose;
            if (relativeLayout6 != null) {
                relativeLayout6.setVisibility(0);
            }
            ScrollView scrollView6 = this.scrollviewContent;
            if (scrollView6 != null) {
                scrollView6.setVisibility(8);
            }
            ImageView imageView6 = this.ivLoading;
            if (imageView6 != null) {
                imageView6.setVisibility(8);
            }
            FrameLayout frameLayout6 = this.fButton;
            if (frameLayout6 != null) {
                frameLayout6.setVisibility(8);
            }
            TextView textView12 = this.tvTips;
            if (textView12 != null) {
                textView12.setVisibility(8);
            }
        } else if (i == TITLE_LOADING_CLOSE) {
            ImageView imageView7 = this.ivLoading;
            if (imageView7 != null) {
                imageView7.setVisibility(0);
            }
            TextView textView13 = this.tvTitle;
            if (textView13 != null) {
                textView13.setVisibility(0);
            }
            RelativeLayout relativeLayout7 = this.rLClose;
            if (relativeLayout7 != null) {
                relativeLayout7.setVisibility(0);
            }
            ScrollView scrollView7 = this.scrollviewContent;
            if (scrollView7 != null) {
                scrollView7.setVisibility(8);
            }
            LinearLayout linearLayout7 = this.lUpdateProgress;
            if (linearLayout7 != null) {
                linearLayout7.setVisibility(8);
            }
            FrameLayout frameLayout7 = this.fButton;
            if (frameLayout7 != null) {
                frameLayout7.setVisibility(8);
            }
            TextView textView14 = this.tvTips;
            if (textView14 != null) {
                textView14.setVisibility(8);
            }
        } else if (i == TITLE_PROGRESS) {
            TextView textView15 = this.tvTitle;
            if (textView15 != null) {
                textView15.setVisibility(0);
            }
            LinearLayout linearLayout8 = this.lUpdateProgress;
            if (linearLayout8 != null) {
                linearLayout8.setVisibility(0);
            }
            RelativeLayout relativeLayout8 = this.rLClose;
            if (relativeLayout8 != null) {
                relativeLayout8.setVisibility(8);
            }
            ScrollView scrollView8 = this.scrollviewContent;
            if (scrollView8 != null) {
                scrollView8.setVisibility(8);
            }
            ImageView imageView8 = this.ivLoading;
            if (imageView8 != null) {
                imageView8.setVisibility(8);
            }
            FrameLayout frameLayout8 = this.fButton;
            if (frameLayout8 != null) {
                frameLayout8.setVisibility(8);
            }
            TextView textView16 = this.tvTips;
            if (textView16 != null) {
                textView16.setVisibility(8);
            }
        } else if (i == TITLE_TIPS_SCROLL_CONTENT_BOTTOM_AND_BUTTON) {
            TextView textView17 = this.tvTitle;
            if (textView17 != null) {
                textView17.setVisibility(0);
            }
            FrameLayout frameLayout9 = this.fButton;
            if (frameLayout9 != null) {
                frameLayout9.setVisibility(0);
            }
            ScrollView scrollView9 = this.scrollviewContent;
            if (scrollView9 != null) {
                scrollView9.setVisibility(0);
            }
            ImageView imageView9 = this.ivLoading;
            if (imageView9 != null) {
                imageView9.setVisibility(8);
            }
            LinearLayout linearLayout9 = this.lUpdateProgress;
            if (linearLayout9 != null) {
                linearLayout9.setVisibility(8);
            }
            TextView textView18 = this.tvTips;
            if (textView18 != null) {
                textView18.setVisibility(8);
            }
            RelativeLayout relativeLayout9 = this.rLClose;
            if (relativeLayout9 != null) {
                relativeLayout9.setVisibility(8);
            }
        }
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("showLoad when the ivLoading is visible ");
        ImageView imageView10 = this.ivLoading;
        Boolean bool2 = null;
        if (imageView10 != null) {
            bool = Boolean.valueOf(imageView10.getVisibility() == 0);
        } else {
            bool = null;
        }
        sb.append(bool);
        sb.append(" dialogType ");
        sb.append(this.dialogType);
        objArr[0] = sb.toString();
        Pdlog.m3273d(TAG, objArr);
        ImageView imageView11 = this.ivLoading;
        if (imageView11 != null) {
            bool2 = Boolean.valueOf(imageView11.getVisibility() == 0);
        }
        showLoading(bool2);
    }

    private final void showLoading(Boolean loading) {
        this.rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        RotateAnimation rotateAnimation = this.rotateAnimation;
        if (rotateAnimation != null) {
            rotateAnimation.setRepeatCount(-1);
        }
        RotateAnimation rotateAnimation2 = this.rotateAnimation;
        if (rotateAnimation2 != null) {
            rotateAnimation2.setDuration(5000L);
        }
        RotateAnimation rotateAnimation3 = this.rotateAnimation;
        if (rotateAnimation3 != null) {
            rotateAnimation3.setFillAfter(true);
        }
        RotateAnimation rotateAnimation4 = this.rotateAnimation;
        if (rotateAnimation4 != null) {
            rotateAnimation4.setInterpolator(new LinearInterpolator());
        }
        RotateAnimation rotateAnimation5 = this.rotateAnimation;
        if (rotateAnimation5 != null) {
            rotateAnimation5.setRepeatMode(1);
        }
        RotateAnimation rotateAnimation6 = this.rotateAnimation;
        if (rotateAnimation6 != null) {
            rotateAnimation6.setRepeatCount(-1);
        }
        if (loading != null && Intrinsics.areEqual((Object) loading, (Object) true)) {
            ImageView imageView = this.ivLoading;
            if (imageView != null) {
                imageView.startAnimation(this.rotateAnimation);
                return;
            }
            return;
        }
        ImageView imageView2 = this.ivLoading;
        if (imageView2 != null) {
            imageView2.clearAnimation();
        }
    }

    public final void setDialogType(int type) {
        this.dialogType = type;
        initWidget();
    }

    public final void setTitle(String title) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        TextView textView = this.tvTitle;
        if (textView != null) {
            textView.setText(title);
        }
    }

    public final void setButTips(String tips) {
        Intrinsics.checkParameterIsNotNull(tips, "tips");
        Button button = this.btnTips;
        if (button != null) {
            button.setText(tips);
        }
    }

    public final void setTips(String tips) {
        Intrinsics.checkParameterIsNotNull(tips, "tips");
        TextView textView = this.tvTips;
        if (textView != null) {
            textView.setText(tips);
        }
    }

    public final void setScrollTips(String v, String desc) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        TextView textView = this.tvVersionCode;
        if (textView != null) {
            textView.setText(v);
        }
        TextView textView2 = this.tvUpdateMessage;
        if (textView2 != null) {
            textView2.setText(desc);
        }
    }

    public final void setOnTitleClickListener(Function0<Unit> onTitleClick) {
        this.onTitleClick = onTitleClick;
    }

    public final void setOnBtnClickListener(Function0<Unit> onBtnClick) {
        this.onBtnClick = onBtnClick;
    }

    public final void setOnCloseClickListener(Function0<Unit> onCloseClick) {
        this.onCloseClick = onCloseClick;
    }

    public final void setProgress(String p) {
        Intrinsics.checkParameterIsNotNull(p, "p");
        TextView textView = this.tvProgress;
        if (textView != null) {
            textView.setText(p);
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        Function0<Unit> function0 = this.onDismissCallback;
        if (function0 != null && function0 != null) {
            function0.invoke();
        }
        RotateAnimation rotateAnimation = this.rotateAnimation;
        if (rotateAnimation != null) {
            if (rotateAnimation != null) {
                rotateAnimation.cancel();
            }
            this.rotateAnimation = (RotateAnimation) null;
        }
        super.dismiss();
        this._context = (Context) null;
    }

    @Override // android.app.Dialog
    public void show() {
        Window window = getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.focusNotAle(window);
        super.show();
        Window window2 = getWindow();
        if (window2 == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.hideNavigationBar(window2);
        Window window3 = getWindow();
        if (window3 == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.clearFocusNotAle(window3);
        Window window4 = getWindow();
        if (window4 != null) {
            window4.setLayout(-2, -2);
        }
    }
}
