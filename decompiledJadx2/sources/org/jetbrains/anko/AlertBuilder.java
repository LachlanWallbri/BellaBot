package org.jetbrains.anko;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.loc.C3898x;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import org.jetbrains.anko.internals.AnkoInternals;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: AlertBuilder.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003J\r\u00101\u001a\u00028\u0000H&¢\u0006\u0002\u00102Ji\u00103\u001a\u000204\"\u0004\b\u0001\u001052\f\u00103\u001a\b\u0012\u0004\u0012\u0002H5062K\u00107\u001aG\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H5¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(<\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(=\u0012\u0004\u0012\u00020408H&JN\u00103\u001a\u0002042\f\u00103\u001a\b\u0012\u0004\u0012\u00020#0626\u00107\u001a2\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(=\u0012\u0004\u0012\u0002040>H&J5\u0010?\u001a\u0002042\b\b\u0001\u0010@\u001a\u00020\u00182!\u0010A\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002040BH&J3\u0010?\u001a\u0002042\u0006\u0010C\u001a\u00020D2!\u0010A\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002040BH&J5\u0010E\u001a\u0002042\b\b\u0001\u0010@\u001a\u00020\u00182!\u0010A\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002040BH&J3\u0010E\u001a\u0002042\u0006\u0010C\u001a\u00020D2!\u0010A\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002040BH&J+\u0010F\u001a\u0002042!\u0010G\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002040BH&JU\u0010H\u001a\u0002042K\u0010G\u001aG\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(I\u0012\u0013\u0012\u00110J¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\u001e08H&J5\u0010L\u001a\u0002042\b\b\u0001\u0010@\u001a\u00020\u00182!\u0010A\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002040BH&J3\u0010L\u001a\u0002042\u0006\u0010C\u001a\u00020D2!\u0010A\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b9\u0012\b\b:\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002040BH&J\r\u0010M\u001a\u00028\u0000H&¢\u0006\u0002\u00102R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\t8gX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\t8gX¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u001a\u0010\u0011\u001a\u00020\u00128gX¦\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u00020\u00188g@&X§\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001e8gX¦\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#8gX¦\u000e¢\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020\u00188gX¦\u000e¢\u0006\f\u001a\u0004\b)\u0010\u001a\"\u0004\b*\u0010\u001cR\u001a\u0010+\u001a\u00020#8gX¦\u000e¢\u0006\f\u001a\u0004\b,\u0010%\"\u0004\b-\u0010'R\u001a\u0010.\u001a\u00020\u00188gX¦\u000e¢\u0006\f\u001a\u0004\b/\u0010\u001a\"\u0004\b0\u0010\u001c¨\u0006N"}, m3961d2 = {"Lorg/jetbrains/anko/AlertBuilder;", "D", "Landroid/content/DialogInterface;", "", "ctx", "Landroid/content/Context;", "getCtx", "()Landroid/content/Context;", "customTitle", "Landroid/view/View;", "getCustomTitle", "()Landroid/view/View;", "setCustomTitle", "(Landroid/view/View;)V", "customView", "getCustomView", "setCustomView", "icon", "Landroid/graphics/drawable/Drawable;", "getIcon", "()Landroid/graphics/drawable/Drawable;", "setIcon", "(Landroid/graphics/drawable/Drawable;)V", "iconResource", "", "getIconResource", "()I", "setIconResource", "(I)V", "isCancelable", "", "()Z", "setCancelable", "(Z)V", "message", "", "getMessage", "()Ljava/lang/CharSequence;", "setMessage", "(Ljava/lang/CharSequence;)V", "messageResource", "getMessageResource", "setMessageResource", "title", "getTitle", "setTitle", "titleResource", "getTitleResource", "setTitleResource", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "()Landroid/content/DialogInterface;", "items", "", ExifInterface.GPS_DIRECTION_TRUE, "", "onItemSelected", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "dialog", "item", "index", "Lkotlin/Function2;", "negativeButton", "buttonTextResource", "onClicked", "Lkotlin/Function1;", "buttonText", "", "neutralPressed", "onCancelled", "handler", "onKeyPressed", "keyCode", "Landroid/view/KeyEvent;", C3898x.f4338g, "positiveButton", "show", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public interface AlertBuilder<D extends DialogInterface> {
    D build();

    Context getCtx();

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    View getCustomTitle();

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    View getCustomView();

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    Drawable getIcon();

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    int getIconResource();

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    CharSequence getMessage();

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    int getMessageResource();

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    CharSequence getTitle();

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    int getTitleResource();

    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    boolean isCancelable();

    void items(List<? extends CharSequence> items, Function2<? super DialogInterface, ? super Integer, Unit> onItemSelected);

    <T> void items(List<? extends T> items, Function3<? super DialogInterface, ? super T, ? super Integer, Unit> onItemSelected);

    void negativeButton(int buttonTextResource, Function1<? super DialogInterface, Unit> onClicked);

    void negativeButton(String buttonText, Function1<? super DialogInterface, Unit> onClicked);

    void neutralPressed(int buttonTextResource, Function1<? super DialogInterface, Unit> onClicked);

    void neutralPressed(String buttonText, Function1<? super DialogInterface, Unit> onClicked);

    void onCancelled(Function1<? super DialogInterface, Unit> handler);

    void onKeyPressed(Function3<? super DialogInterface, ? super Integer, ? super KeyEvent, Boolean> handler);

    void positiveButton(int buttonTextResource, Function1<? super DialogInterface, Unit> onClicked);

    void positiveButton(String buttonText, Function1<? super DialogInterface, Unit> onClicked);

    void setCancelable(boolean z);

    void setCustomTitle(View view);

    void setCustomView(View view);

    void setIcon(Drawable drawable);

    void setIconResource(int i);

    void setMessage(CharSequence charSequence);

    void setMessageResource(int i);

    void setTitle(CharSequence charSequence);

    void setTitleResource(int i);

    D show();
}
