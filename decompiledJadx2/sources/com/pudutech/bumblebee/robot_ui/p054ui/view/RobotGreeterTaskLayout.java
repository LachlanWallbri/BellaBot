package com.pudutech.bumblebee.robot_ui.p054ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.pudutech.bumblebee.business.movementInterface.SortType;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.util.PlaySound;
import com.pudutech.bumblebee.robot_ui.util.UiUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: RobotGreeterTaskLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u001f\u001a\u00020\u001eJ\u0016\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\f0\u001aj\b\u0012\u0004\u0012\u00020\f`\u001bJ\u0012\u0010!\u001a\u00020\u00142\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0002J\b\u0010\"\u001a\u00020\u0014H\u0002J\u0018\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\fH\u0002J\b\u0010'\u001a\u00020\u0014H\u0002J\b\u0010(\u001a\u00020\u0014H\u0002J\u000e\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020\fJ\b\u0010+\u001a\u00020\u0014H\u0002R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\f0\u001aj\b\u0012\u0004\u0012\u00020\f`\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u001aj\b\u0012\u0004\u0012\u00020\u0001`\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/RobotGreeterTaskLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "kotlin.jvm.PlatformType", "currentSelectIndex", "onItemChooseClickListener", "Landroid/view/View$OnClickListener;", "onItemCloseClickListener", "onTaskChangeListener", "Lkotlin/Function0;", "", "getOnTaskChangeListener", "()Lkotlin/jvm/functions/Function0;", "setOnTaskChangeListener", "(Lkotlin/jvm/functions/Function0;)V", "palletDates", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "palletViewArray", "sortType", "Lcom/pudutech/bumblebee/business/movementInterface/SortType;", "getSortType", "getTaskList", "initView", "resetPalletDate", "resetTextSize", "tv", "Landroid/widget/TextView;", "text", "selectNextEmptyPallet", "setListener", "setPalletData", "palletName", "updateViewState", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotGreeterTaskLayout extends RelativeLayout {
    private final String TAG;
    private HashMap _$_findViewCache;
    private int currentSelectIndex;
    private final View.OnClickListener onItemChooseClickListener;
    private View.OnClickListener onItemCloseClickListener;
    private Function0<Unit> onTaskChangeListener;
    private final ArrayList<String> palletDates;
    private final ArrayList<RelativeLayout> palletViewArray;
    private SortType sortType;

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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final Function0<Unit> getOnTaskChangeListener() {
        return this.onTaskChangeListener;
    }

    public final void setOnTaskChangeListener(Function0<Unit> function0) {
        this.onTaskChangeListener = function0;
    }

    public RobotGreeterTaskLayout(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.sortType = SortType.AUTO;
        this.palletDates = new ArrayList<>();
        this.palletViewArray = new ArrayList<>();
        this.onItemCloseClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotGreeterTaskLayout$onItemCloseClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                ArrayList arrayList;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    int intValue = ((Integer) tag).intValue();
                    arrayList = RobotGreeterTaskLayout.this.palletDates;
                    arrayList.set(intValue, "");
                    RobotGreeterTaskLayout.this.updateViewState();
                    Function0<Unit> onTaskChangeListener = RobotGreeterTaskLayout.this.getOnTaskChangeListener();
                    if (onTaskChangeListener != null) {
                        onTaskChangeListener.invoke();
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onItemChooseClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotGreeterTaskLayout$onItemChooseClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                int i;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    int intValue = ((Integer) tag).intValue();
                    i = RobotGreeterTaskLayout.this.currentSelectIndex;
                    if (intValue != i) {
                        RobotGreeterTaskLayout.this.currentSelectIndex = intValue;
                        RobotGreeterTaskLayout.this.updateViewState();
                        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
                        return;
                    }
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        initView(getContext());
    }

    public RobotGreeterTaskLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.sortType = SortType.AUTO;
        this.palletDates = new ArrayList<>();
        this.palletViewArray = new ArrayList<>();
        this.onItemCloseClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotGreeterTaskLayout$onItemCloseClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                ArrayList arrayList;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    int intValue = ((Integer) tag).intValue();
                    arrayList = RobotGreeterTaskLayout.this.palletDates;
                    arrayList.set(intValue, "");
                    RobotGreeterTaskLayout.this.updateViewState();
                    Function0<Unit> onTaskChangeListener = RobotGreeterTaskLayout.this.getOnTaskChangeListener();
                    if (onTaskChangeListener != null) {
                        onTaskChangeListener.invoke();
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onItemChooseClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotGreeterTaskLayout$onItemChooseClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                int i;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    int intValue = ((Integer) tag).intValue();
                    i = RobotGreeterTaskLayout.this.currentSelectIndex;
                    if (intValue != i) {
                        RobotGreeterTaskLayout.this.currentSelectIndex = intValue;
                        RobotGreeterTaskLayout.this.updateViewState();
                        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
                        return;
                    }
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        initView(getContext());
    }

    public RobotGreeterTaskLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.sortType = SortType.AUTO;
        this.palletDates = new ArrayList<>();
        this.palletViewArray = new ArrayList<>();
        this.onItemCloseClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotGreeterTaskLayout$onItemCloseClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                ArrayList arrayList;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    int intValue = ((Integer) tag).intValue();
                    arrayList = RobotGreeterTaskLayout.this.palletDates;
                    arrayList.set(intValue, "");
                    RobotGreeterTaskLayout.this.updateViewState();
                    Function0<Unit> onTaskChangeListener = RobotGreeterTaskLayout.this.getOnTaskChangeListener();
                    if (onTaskChangeListener != null) {
                        onTaskChangeListener.invoke();
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onItemChooseClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotGreeterTaskLayout$onItemChooseClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                int i2;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    int intValue = ((Integer) tag).intValue();
                    i2 = RobotGreeterTaskLayout.this.currentSelectIndex;
                    if (intValue != i2) {
                        RobotGreeterTaskLayout.this.currentSelectIndex = intValue;
                        RobotGreeterTaskLayout.this.updateViewState();
                        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
                        return;
                    }
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        initView(getContext());
    }

    private final void resetPalletDate() {
        this.palletDates.clear();
        int size = this.palletViewArray.size();
        for (int i = 0; i < size; i++) {
            this.palletDates.add("");
        }
    }

    private final void setListener() {
        int i = 0;
        for (Object obj : this.palletViewArray) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            RelativeLayout relativeLayout = (RelativeLayout) obj;
            relativeLayout.setTag(Integer.valueOf(i));
            ImageView it = (ImageView) relativeLayout.findViewById(C4188R.id.greeter_item_clear);
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            it.setTag(Integer.valueOf(i));
            it.setOnClickListener(this.onItemCloseClickListener);
            RelativeLayout it2 = (RelativeLayout) relativeLayout.findViewById(C4188R.id.greeter_item);
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            it2.setTag(Integer.valueOf(i));
            it2.setOnClickListener(this.onItemChooseClickListener);
            i = i2;
        }
    }

    public final ArrayList<String> getTaskList() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : this.palletDates) {
            String str2 = str;
            if (!(str2 == null || str2.length() == 0)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateViewState() {
        Iterator<T> it = this.palletDates.iterator();
        int i = 0;
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) next;
            String str2 = str;
            if (str2 != null && str2.length() != 0) {
                z = false;
            }
            if (z) {
                View findViewById = this.palletViewArray.get(i).findViewById(C4188R.id.greeter_item_name);
                Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletViewArray[index].f…>(R.id.greeter_item_name)");
                ((TextView) findViewById).setText(str2);
                View findViewById2 = this.palletViewArray.get(i).findViewById(C4188R.id.greeter_item);
                Intrinsics.checkExpressionValueIsNotNull(findViewById2, "palletViewArray[index].f…ayout>(R.id.greeter_item)");
                Sdk27PropertiesKt.setBackgroundResource(findViewById2, C4188R.drawable.rectangle_greeter_item__corners_default);
                View findViewById3 = this.palletViewArray.get(i).findViewById(C4188R.id.greeter_item_clear);
                Intrinsics.checkExpressionValueIsNotNull(findViewById3, "palletViewArray[index].f…(R.id.greeter_item_clear)");
                ((ImageView) findViewById3).setVisibility(4);
            } else {
                View findViewById4 = this.palletViewArray.get(i).findViewById(C4188R.id.greeter_item_name);
                Intrinsics.checkExpressionValueIsNotNull(findViewById4, "palletViewArray[index].f…>(R.id.greeter_item_name)");
                ((TextView) findViewById4).setText(str2);
                View findViewById5 = this.palletViewArray.get(i).findViewById(C4188R.id.greeter_item);
                Intrinsics.checkExpressionValueIsNotNull(findViewById5, "palletViewArray[index].f…ayout>(R.id.greeter_item)");
                Sdk27PropertiesKt.setBackgroundResource(findViewById5, C4188R.drawable.rectangle_greeter_item__corners_press);
                View findViewById6 = this.palletViewArray.get(i).findViewById(C4188R.id.greeter_item_clear);
                Intrinsics.checkExpressionValueIsNotNull(findViewById6, "palletViewArray[index].f…(R.id.greeter_item_clear)");
                ((ImageView) findViewById6).setVisibility(0);
            }
            View findViewById7 = this.palletViewArray.get(i).findViewById(C4188R.id.greeter_item_name);
            Intrinsics.checkExpressionValueIsNotNull(findViewById7, "palletViewArray[index].f…>(R.id.greeter_item_name)");
            resetTextSize((TextView) findViewById7, str);
            i = i2;
        }
        String str3 = this.palletDates.get(this.currentSelectIndex);
        if (str3 == null || str3.length() == 0) {
            View findViewById8 = this.palletViewArray.get(this.currentSelectIndex).findViewById(C4188R.id.greeter_item);
            Intrinsics.checkExpressionValueIsNotNull(findViewById8, "palletViewArray[currentS…ayout>(R.id.greeter_item)");
            Sdk27PropertiesKt.setBackgroundResource(findViewById8, C4188R.drawable.rectangle_greeter_item__corners_press);
            View findViewById9 = this.palletViewArray.get(this.currentSelectIndex).findViewById(C4188R.id.greeter_item_clear);
            Intrinsics.checkExpressionValueIsNotNull(findViewById9, "palletViewArray[currentS…(R.id.greeter_item_clear)");
            ((ImageView) findViewById9).setVisibility(4);
        }
    }

    private final void resetTextSize(TextView tv, String text) {
        float dimension = getResources().getDimension(C4188R.dimen.delivery_robot_tray_tv_s);
        float dimension2 = getResources().getDimension(C4188R.dimen.greeter_menu_item_text_w);
        tv.setTextSize(0, dimension);
        UiUtils.adjustTvTextSize(tv, ((int) dimension2) - 10, text, 22);
    }

    public final SortType getSortType() {
        return this.sortType;
    }

    public final void setPalletData(String palletName) {
        Intrinsics.checkParameterIsNotNull(palletName, "palletName");
        String str = palletName;
        if ((str.length() == 0) || StringsKt.isBlank(str)) {
            return;
        }
        this.palletDates.set(this.currentSelectIndex, palletName);
        selectNextEmptyPallet();
        updateViewState();
    }

    private final void selectNextEmptyPallet() {
        int i = this.currentSelectIndex;
        int size = this.palletDates.size();
        while (true) {
            if (i < size) {
                if (i > this.currentSelectIndex) {
                    String str = this.palletDates.get(i);
                    if (str == null || str.length() == 0) {
                        this.currentSelectIndex = i;
                        return;
                    }
                }
                i++;
            } else {
                int i2 = this.currentSelectIndex;
                for (int i3 = 0; i3 < i2; i3++) {
                    if (i3 < this.currentSelectIndex) {
                        String str2 = this.palletDates.get(i3);
                        if (str2 == null || str2.length() == 0) {
                            this.currentSelectIndex = i3;
                            return;
                        }
                    }
                }
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void initView(Context context) {
        if (context != null) {
            Object systemService = context.getSystemService("layout_inflater");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
            }
            LayoutInflater layoutInflater = (LayoutInflater) systemService;
            if (layoutInflater != null) {
                layoutInflater.inflate(C4188R.layout.layout_robot_greeter_task, this);
            }
        }
        this.palletViewArray.add(findViewById(C4188R.id.layout_greeter_item_1));
        this.palletViewArray.add(findViewById(C4188R.id.layout_greeter_item_2));
        this.palletViewArray.add(findViewById(C4188R.id.layout_greeter_item_3));
        this.palletViewArray.add(findViewById(C4188R.id.layout_greeter_item_4));
        resetPalletDate();
        updateViewState();
        setListener();
    }
}
