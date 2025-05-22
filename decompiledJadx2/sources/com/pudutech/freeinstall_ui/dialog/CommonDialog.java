package com.pudutech.freeinstall_ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.disinfect.baselib.util.NavigationBarUtil;
import com.pudutech.module_freeinstall_ui.C5362R;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: CommonDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\f\u0018\u00002\u00020\u0001:\u0002/0B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0007H\u0002J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0015J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u001e\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u0018J\u001e\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u0018J\u000e\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020%J\u000e\u0010&\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u001eJ\u000e\u0010(\u001a\u00020\u00072\u0006\u0010)\u001a\u00020\u001eJ\u000e\u0010*\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u001eJ\u000e\u0010,\u001a\u00020\u00072\u0006\u0010-\u001a\u00020\u001eJ\b\u0010.\u001a\u00020\u0007H\u0016R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/dialog/CommonDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "btLeftClick", "Lkotlin/Function0;", "", "getBtLeftClick", "()Lkotlin/jvm/functions/Function0;", "setBtLeftClick", "(Lkotlin/jvm/functions/Function0;)V", "btRightClick", "getBtRightClick", "setBtRightClick", "closeClick", "getCloseClick", "setCloseClick", "mContext", "buildDialog", "getBtLeft", "Landroid/widget/Button;", "getBtRight", "getResource", "", "btBg", "Lcom/pudutech/freeinstall_ui/dialog/CommonDialog$BtBg;", "init", "setBtLeft", "btLeft", "", "btLeftTvColor", "setBtRigth", "btRight", "btRightTvColor", "setClose", "isClose", "", "setContent", "cont", "setMinContent", "minContent", "setMintitle", "minTitle", "setTit", "tit", "show", "BtBg", "Builder", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CommonDialog extends Dialog {
    private Function0<Unit> btLeftClick;
    private Function0<Unit> btRightClick;
    private Function0<Unit> closeClick;
    private Context mContext;

    /* compiled from: CommonDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/dialog/CommonDialog$BtBg;", "", "(Ljava/lang/String;I)V", "RED", "BLUE", "BLUE_WIDTH", "GRAY", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public enum BtBg {
        RED,
        BLUE,
        BLUE_WIDTH,
        GRAY
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[BtBg.values().length];

        static {
            $EnumSwitchMapping$0[BtBg.RED.ordinal()] = 1;
            $EnumSwitchMapping$0[BtBg.BLUE.ordinal()] = 2;
            $EnumSwitchMapping$0[BtBg.BLUE_WIDTH.ordinal()] = 3;
            $EnumSwitchMapping$0[BtBg.GRAY.ordinal()] = 4;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommonDialog(Context context) {
        super(context, C5362R.style.dialogstyle);
        Intrinsics.checkParameterIsNotNull(context, "context");
        init(context);
    }

    public final Function0<Unit> getBtLeftClick() {
        return this.btLeftClick;
    }

    public final void setBtLeftClick(Function0<Unit> function0) {
        this.btLeftClick = function0;
    }

    public final Function0<Unit> getBtRightClick() {
        return this.btRightClick;
    }

    public final void setBtRightClick(Function0<Unit> function0) {
        this.btRightClick = function0;
    }

    public final Function0<Unit> getCloseClick() {
        return this.closeClick;
    }

    public final void setCloseClick(Function0<Unit> function0) {
        this.closeClick = function0;
    }

    private final void init(Context context) {
        this.mContext = context;
        buildDialog();
    }

    private final void buildDialog() {
        View inflate = getLayoutInflater().inflate(C5362R.layout.dialog_common, (ViewGroup) null);
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setGravity(17);
            window.setAttributes(attributes);
            window.setContentView(inflate);
        }
    }

    public final void setTit(String tit) {
        Intrinsics.checkParameterIsNotNull(tit, "tit");
        TextView title = (TextView) findViewById(C5362R.id.title);
        Intrinsics.checkExpressionValueIsNotNull(title, "title");
        title.setVisibility(0);
        TextView title2 = (TextView) findViewById(C5362R.id.title);
        Intrinsics.checkExpressionValueIsNotNull(title2, "title");
        title2.setText(tit);
    }

    public final void setBtLeft(String btLeft, BtBg btBg, int btLeftTvColor) {
        Intrinsics.checkParameterIsNotNull(btLeft, "btLeft");
        Intrinsics.checkParameterIsNotNull(btBg, "btBg");
        Button button_left = (Button) findViewById(C5362R.id.button_left);
        Intrinsics.checkExpressionValueIsNotNull(button_left, "button_left");
        button_left.setVisibility(0);
        Button button_left2 = (Button) findViewById(C5362R.id.button_left);
        Intrinsics.checkExpressionValueIsNotNull(button_left2, "button_left");
        button_left2.setText(btLeft);
        ((Button) findViewById(C5362R.id.button_left)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.dialog.CommonDialog$setBtLeft$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> btLeftClick = CommonDialog.this.getBtLeftClick();
                if (btLeftClick != null) {
                    btLeftClick.invoke();
                }
            }
        });
        Button button_left3 = (Button) findViewById(C5362R.id.button_left);
        Intrinsics.checkExpressionValueIsNotNull(button_left3, "button_left");
        Sdk27PropertiesKt.setBackgroundResource(button_left3, getResource(btBg));
        Button button_left4 = (Button) findViewById(C5362R.id.button_left);
        Intrinsics.checkExpressionValueIsNotNull(button_left4, "button_left");
        Sdk27PropertiesKt.setTextColor(button_left4, btLeftTvColor);
    }

    public final Button getBtLeft() {
        Button button_left = (Button) findViewById(C5362R.id.button_left);
        Intrinsics.checkExpressionValueIsNotNull(button_left, "button_left");
        return button_left;
    }

    public final Button getBtRight() {
        Button button_right = (Button) findViewById(C5362R.id.button_right);
        Intrinsics.checkExpressionValueIsNotNull(button_right, "button_right");
        return button_right;
    }

    public final void setBtRigth(String btRight, BtBg btBg, int btRightTvColor) {
        Intrinsics.checkParameterIsNotNull(btRight, "btRight");
        Intrinsics.checkParameterIsNotNull(btBg, "btBg");
        Button button_right = (Button) findViewById(C5362R.id.button_right);
        Intrinsics.checkExpressionValueIsNotNull(button_right, "button_right");
        button_right.setVisibility(0);
        Button button_right2 = (Button) findViewById(C5362R.id.button_right);
        Intrinsics.checkExpressionValueIsNotNull(button_right2, "button_right");
        button_right2.setText(btRight);
        ((Button) findViewById(C5362R.id.button_right)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.dialog.CommonDialog$setBtRigth$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> btRightClick = CommonDialog.this.getBtRightClick();
                if (btRightClick != null) {
                    btRightClick.invoke();
                }
            }
        });
        Button button_right3 = (Button) findViewById(C5362R.id.button_right);
        Intrinsics.checkExpressionValueIsNotNull(button_right3, "button_right");
        Sdk27PropertiesKt.setBackgroundResource(button_right3, getResource(btBg));
        Button button_right4 = (Button) findViewById(C5362R.id.button_right);
        Intrinsics.checkExpressionValueIsNotNull(button_right4, "button_right");
        Sdk27PropertiesKt.setTextColor(button_right4, btRightTvColor);
    }

    public final void setContent(String cont) {
        Intrinsics.checkParameterIsNotNull(cont, "cont");
        ConstraintLayout cl_min_content = (ConstraintLayout) findViewById(C5362R.id.cl_min_content);
        Intrinsics.checkExpressionValueIsNotNull(cl_min_content, "cl_min_content");
        cl_min_content.setVisibility(0);
        AppCompatTextView content = (AppCompatTextView) findViewById(C5362R.id.content);
        Intrinsics.checkExpressionValueIsNotNull(content, "content");
        content.setVisibility(0);
        AppCompatTextView content2 = (AppCompatTextView) findViewById(C5362R.id.content);
        Intrinsics.checkExpressionValueIsNotNull(content2, "content");
        content2.setText(cont);
    }

    public final void setClose(boolean isClose) {
        if (isClose) {
            ImageView close = (ImageView) findViewById(C5362R.id.close);
            Intrinsics.checkExpressionValueIsNotNull(close, "close");
            close.setVisibility(0);
            ((ImageView) findViewById(C5362R.id.close)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.dialog.CommonDialog$setClose$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Function0<Unit> closeClick = CommonDialog.this.getCloseClick();
                    if (closeClick != null) {
                        closeClick.invoke();
                    }
                }
            });
        }
    }

    public final void setMintitle(String minTitle) {
        Intrinsics.checkParameterIsNotNull(minTitle, "minTitle");
        ConstraintLayout cl_min_content = (ConstraintLayout) findViewById(C5362R.id.cl_min_content);
        Intrinsics.checkExpressionValueIsNotNull(cl_min_content, "cl_min_content");
        cl_min_content.setVisibility(0);
        TextView tv_min_title = (TextView) findViewById(C5362R.id.tv_min_title);
        Intrinsics.checkExpressionValueIsNotNull(tv_min_title, "tv_min_title");
        tv_min_title.setVisibility(0);
        TextView tv_min_title2 = (TextView) findViewById(C5362R.id.tv_min_title);
        Intrinsics.checkExpressionValueIsNotNull(tv_min_title2, "tv_min_title");
        tv_min_title2.setText(minTitle);
    }

    public final void setMinContent(String minContent) {
        Intrinsics.checkParameterIsNotNull(minContent, "minContent");
        ConstraintLayout cl_min_content = (ConstraintLayout) findViewById(C5362R.id.cl_min_content);
        Intrinsics.checkExpressionValueIsNotNull(cl_min_content, "cl_min_content");
        cl_min_content.setVisibility(0);
        TextView tv_min_content = (TextView) findViewById(C5362R.id.tv_min_content);
        Intrinsics.checkExpressionValueIsNotNull(tv_min_content, "tv_min_content");
        tv_min_content.setVisibility(0);
        TextView tv_min_content2 = (TextView) findViewById(C5362R.id.tv_min_content);
        Intrinsics.checkExpressionValueIsNotNull(tv_min_content2, "tv_min_content");
        tv_min_content2.setText(minContent);
    }

    /* compiled from: CommonDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u001e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0006J\u001e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\nJ\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0006J\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0006J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0006J\u000e\u0010 \u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0006R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0004R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/dialog/CommonDialog$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "btLeft", "", "btLeftBg", "Lcom/pudutech/freeinstall_ui/dialog/CommonDialog$BtBg;", "btLeftTvColor", "", "btRight", "btRightBg", "btRightTvColor", "close", "", AIUIConstant.KEY_CONTENT, "getContext", "()Landroid/content/Context;", "setContext", "minContent", "minTitle", "title", "create", "Lcom/pudutech/freeinstall_ui/dialog/CommonDialog;", "setBtLeft", "setBtRight", "setClose", "setContent", "setMinContent", "setMinTitle", "contentTitle", "setTitle", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Builder {
        private String btLeft;
        private BtBg btLeftBg;
        private int btLeftTvColor;
        private String btRight;
        private BtBg btRightBg;
        private int btRightTvColor;
        private boolean close;
        private String content;
        private Context context;
        private String minContent;
        private String minTitle;
        private String title;

        public Builder(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.context = context;
            this.btLeftBg = BtBg.BLUE_WIDTH;
            this.btRightBg = BtBg.BLUE;
            this.btLeftTvColor = this.context.getResources().getColor(C5362R.color.btn_blue_normal_color);
            this.btRightTvColor = this.context.getResources().getColor(C5362R.color.white);
        }

        public final Context getContext() {
            return this.context;
        }

        public final void setContext(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "<set-?>");
            this.context = context;
        }

        public final Builder setTitle(String title) {
            Intrinsics.checkParameterIsNotNull(title, "title");
            this.title = title;
            return this;
        }

        public final Builder setBtLeft(String btLeft) {
            Intrinsics.checkParameterIsNotNull(btLeft, "btLeft");
            this.btLeft = btLeft;
            return this;
        }

        public final Builder setBtLeft(String btLeft, BtBg btLeftBg, int btLeftTvColor) {
            Intrinsics.checkParameterIsNotNull(btLeft, "btLeft");
            Intrinsics.checkParameterIsNotNull(btLeftBg, "btLeftBg");
            setBtLeft(btLeft);
            this.btLeftBg = btLeftBg;
            this.btLeftTvColor = btLeftTvColor;
            return this;
        }

        public final Builder setBtRight(String btRight) {
            Intrinsics.checkParameterIsNotNull(btRight, "btRight");
            this.btRight = btRight;
            return this;
        }

        public final Builder setBtRight(String btRight, BtBg btRightBg, int btRightTvColor) {
            Intrinsics.checkParameterIsNotNull(btRight, "btRight");
            Intrinsics.checkParameterIsNotNull(btRightBg, "btRightBg");
            this.btRight = btRight;
            this.btRightBg = btRightBg;
            this.btRightTvColor = btRightTvColor;
            return this;
        }

        public final Builder setContent(String content) {
            Intrinsics.checkParameterIsNotNull(content, "content");
            this.content = content;
            return this;
        }

        public final Builder setMinTitle(String contentTitle) {
            Intrinsics.checkParameterIsNotNull(contentTitle, "contentTitle");
            this.minTitle = contentTitle;
            return this;
        }

        public final Builder setMinContent(String minContent) {
            Intrinsics.checkParameterIsNotNull(minContent, "minContent");
            this.minContent = minContent;
            return this;
        }

        public final Builder setClose(boolean close) {
            this.close = close;
            return this;
        }

        public final CommonDialog create() {
            CommonDialog commonDialog = new CommonDialog(this.context);
            String str = this.title;
            if (str != null) {
                if (str == null) {
                    Intrinsics.throwNpe();
                }
                commonDialog.setTit(str);
            }
            String str2 = this.btLeft;
            if (str2 != null) {
                if (str2 == null) {
                    Intrinsics.throwNpe();
                }
                commonDialog.setBtLeft(str2, this.btLeftBg, this.btLeftTvColor);
            }
            String str3 = this.btRight;
            if (str3 != null) {
                if (str3 == null) {
                    Intrinsics.throwNpe();
                }
                commonDialog.setBtRigth(str3, this.btRightBg, this.btRightTvColor);
            }
            String str4 = this.content;
            if (str4 != null) {
                if (str4 == null) {
                    Intrinsics.throwNpe();
                }
                commonDialog.setContent(str4);
            }
            String str5 = this.minTitle;
            if (str5 != null) {
                if (str5 == null) {
                    Intrinsics.throwNpe();
                }
                commonDialog.setMintitle(str5);
            }
            String str6 = this.minContent;
            if (str6 != null) {
                if (str6 == null) {
                    Intrinsics.throwNpe();
                }
                commonDialog.setMinContent(str6);
            }
            commonDialog.setClose(this.close);
            return commonDialog;
        }
    }

    private final int getResource(BtBg btBg) {
        int i = WhenMappings.$EnumSwitchMapping$0[btBg.ordinal()];
        if (i == 1) {
            return C5362R.drawable.shape_radius_fb313b;
        }
        if (i == 2) {
            return C5362R.drawable.shape_radius_0072ff;
        }
        if (i == 3) {
            return C5362R.drawable.shape_radius_stroke_0072ff;
        }
        if (i == 4) {
            return C5362R.drawable.rectangle_background_61666b_8;
        }
        throw new NoWhenBranchMatchedException();
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
    }
}
