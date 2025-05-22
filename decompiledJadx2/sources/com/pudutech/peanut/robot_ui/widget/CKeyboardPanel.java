package com.pudutech.peanut.robot_ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.pudutech.disinfect.baselib.util.DensityUtil;
import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.peanut.robot_ui.C5508R;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* compiled from: CKeyboardPanel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u000234B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010'\u001a\u00020\rH\u0002J\b\u0010(\u001a\u00020\rH\u0002J\u0010\u0010)\u001a\u00020\r2\u0006\u0010*\u001a\u00020+H\u0002J\u0006\u0010,\u001a\u00020\rJ\u0016\u0010-\u001a\u00020\r2\u000e\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\fJ+\u0010/\u001a\u00020\r2#\u0010.\u001a\u001f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0006\u0012\u0004\u0018\u00010\r0\u000fJ\u0016\u00100\u001a\u00020\r2\u000e\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\fJ\u0016\u00101\u001a\u00020\r2\u000e\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\fJ\b\u00102\u001a\u00020\rH\u0002R\u0018\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R-\u0010\u000e\u001a!\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0014\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0015\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00180\u0017j\b\u0012\u0004\u0012\u00020\u0018`\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u001b8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R+\u0010#\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u001b8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b&\u0010\"\u001a\u0004\b$\u0010\u001e\"\u0004\b%\u0010 ¨\u00065"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/widget/CKeyboardPanel;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "onBackListener", "Lkotlin/Function0;", "", "onPasswordVerificationPassedListener", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD, "onSwitchNormalInputListener", "onSwitchSmartInputListener", "passwordItemList", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/robot_ui/widget/CKeyboardPanel$PasswordItem;", "Lkotlin/collections/ArrayList;", "<set-?>", "Landroidx/recyclerview/widget/RecyclerView;", "passwordRecyclerView", "getPasswordRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setPasswordRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "passwordRecyclerView$delegate", "Lkotlin/properties/ReadWriteProperty;", "roomNumberSmartRecyclerView", "getRoomNumberSmartRecyclerView", "setRoomNumberSmartRecyclerView", "roomNumberSmartRecyclerView$delegate", "init", "initData", "initPasswordLayout", "view", "Landroid/view/View;", "roomNumberSmartRecyclerViewScrollToTop", "setOnBackListener", "listener", "setOnPasswordVerificationPassedListener", "setOnSwitchNormalInputListener", "setOnSwitchSmartInputListener", "switchType", "PasswordItem", "PasswordItemAdapter", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CKeyboardPanel extends ConstraintLayout {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CKeyboardPanel.class), "passwordRecyclerView", "getPasswordRecyclerView()Landroidx/recyclerview/widget/RecyclerView;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CKeyboardPanel.class), "roomNumberSmartRecyclerView", "getRoomNumberSmartRecyclerView()Landroidx/recyclerview/widget/RecyclerView;"))};
    private HashMap _$_findViewCache;
    private Function0<Unit> onBackListener;
    private Function1<? super String, Unit> onPasswordVerificationPassedListener;
    private Function0<Unit> onSwitchNormalInputListener;
    private Function0<Unit> onSwitchSmartInputListener;
    private ArrayList<PasswordItem> passwordItemList;

    /* renamed from: passwordRecyclerView$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty passwordRecyclerView;

    /* renamed from: roomNumberSmartRecyclerView$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty roomNumberSmartRecyclerView;

    private final RecyclerView getPasswordRecyclerView() {
        return (RecyclerView) this.passwordRecyclerView.getValue(this, $$delegatedProperties[0]);
    }

    private final RecyclerView getRoomNumberSmartRecyclerView() {
        return (RecyclerView) this.roomNumberSmartRecyclerView.getValue(this, $$delegatedProperties[1]);
    }

    private final void setPasswordRecyclerView(RecyclerView recyclerView) {
        this.passwordRecyclerView.setValue(this, $$delegatedProperties[0], recyclerView);
    }

    private final void setRoomNumberSmartRecyclerView(RecyclerView recyclerView) {
        this.roomNumberSmartRecyclerView.setValue(this, $$delegatedProperties[1], recyclerView);
    }

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

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CKeyboardPanel(Context context) {
        this(context, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CKeyboardPanel(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CKeyboardPanel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.passwordRecyclerView = Delegates.INSTANCE.notNull();
        this.roomNumberSmartRecyclerView = Delegates.INSTANCE.notNull();
        this.passwordItemList = new ArrayList<>();
        init();
    }

    private final void init() {
        switchType();
    }

    private final void initData() {
        ArrayList<PasswordItem> arrayList = this.passwordItemList;
        arrayList.clear();
        for (int i = 0; i < 12; i++) {
            if (i < 9) {
                arrayList.add(new PasswordItem(Integer.valueOf(i + 1), null, null, 0, 6, null));
            } else if (i == 9) {
                arrayList.add(new PasswordItem(null, "返回", null, 1, 5, null));
            } else if (i == 10) {
                arrayList.add(new PasswordItem(0, null, null, 0, 6, null));
            } else if (i == 11) {
                arrayList.add(new PasswordItem(null, null, ContextCompat.getDrawable(getContext(), C5508R.drawable.ic_panel_delete), 2, 3, null));
            }
        }
    }

    private final void switchType() {
        removeAllViews();
        initData();
        View view = LayoutInflater.from(AppContext.INSTANCE.getContext()).inflate(C5508R.layout.view_keyboard_panel_password, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        initPasswordLayout(view);
        addView(view);
    }

    private final void initPasswordLayout(View view) {
        View findViewById = view.findViewById(C5508R.id.recycler_view_password);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.recycler_view_password)");
        setPasswordRecyclerView((RecyclerView) findViewById);
        getPasswordRecyclerView().setHasFixedSize(true);
        getPasswordRecyclerView().setLayoutManager(new GridLayoutManager(getContext(), 3));
        RecyclerView passwordRecyclerView = getPasswordRecyclerView();
        DensityUtil densityUtil = DensityUtil.INSTANCE;
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        passwordRecyclerView.addItemDecoration(new GridDividerItemDecoration(Integer.valueOf(densityUtil.dip2px(context, 2.0f)), null, null, 6, null));
        ArrayList<PasswordItem> arrayList = this.passwordItemList;
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        PasswordItemAdapter passwordItemAdapter = new PasswordItemAdapter(arrayList);
        passwordItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.peanut.robot_ui.widget.CKeyboardPanel$initPasswordLayout$1
            /* JADX WARN: Code restructure failed: missing block: B:3:0x0005, code lost:
            
                r1 = r0.this$0.onBackListener;
             */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void onItemClick(BaseQuickAdapter<Object, BaseViewHolder> baseQuickAdapter, View view2, int i) {
                Function0 function0;
                if (i == 9 && function0 != null) {
                }
            }
        });
        getPasswordRecyclerView().setAdapter(passwordItemAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CKeyboardPanel.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0082\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB1\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÂ\u0003J<\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\b\u0010\u001b\u001a\u00020\u0003H\u0016J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/widget/CKeyboardPanel$PasswordItem;", "Lcom/chad/library/adapter/base/entity/MultiItemEntity;", "number", "", "text", "", "drawable", "Landroid/graphics/drawable/Drawable;", "itemType", "(Ljava/lang/Integer;Ljava/lang/String;Landroid/graphics/drawable/Drawable;I)V", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "getNumber", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getText", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Landroid/graphics/drawable/Drawable;I)Lcom/pudutech/peanut/robot_ui/widget/CKeyboardPanel$PasswordItem;", "equals", "", "other", "", "getItemType", "hashCode", "toString", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class PasswordItem implements MultiItemEntity {
        public static final int ITEM_TYPE_DRAWABLE = 2;
        public static final int ITEM_TYPE_NUMBER = 0;
        public static final int ITEM_TYPE_TEXT = 1;
        private final Drawable drawable;
        private final int itemType;
        private final Integer number;
        private final String text;

        /* renamed from: component4, reason: from getter */
        private final int getItemType() {
            return this.itemType;
        }

        public static /* synthetic */ PasswordItem copy$default(PasswordItem passwordItem, Integer num, String str, Drawable drawable, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                num = passwordItem.number;
            }
            if ((i2 & 2) != 0) {
                str = passwordItem.text;
            }
            if ((i2 & 4) != 0) {
                drawable = passwordItem.drawable;
            }
            if ((i2 & 8) != 0) {
                i = passwordItem.itemType;
            }
            return passwordItem.copy(num, str, drawable, i);
        }

        /* renamed from: component1, reason: from getter */
        public final Integer getNumber() {
            return this.number;
        }

        /* renamed from: component2, reason: from getter */
        public final String getText() {
            return this.text;
        }

        /* renamed from: component3, reason: from getter */
        public final Drawable getDrawable() {
            return this.drawable;
        }

        public final PasswordItem copy(Integer number, String text, Drawable drawable, int itemType) {
            return new PasswordItem(number, text, drawable, itemType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PasswordItem)) {
                return false;
            }
            PasswordItem passwordItem = (PasswordItem) other;
            return Intrinsics.areEqual(this.number, passwordItem.number) && Intrinsics.areEqual(this.text, passwordItem.text) && Intrinsics.areEqual(this.drawable, passwordItem.drawable) && this.itemType == passwordItem.itemType;
        }

        public int hashCode() {
            Integer num = this.number;
            int hashCode = (num != null ? num.hashCode() : 0) * 31;
            String str = this.text;
            int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
            Drawable drawable = this.drawable;
            return ((hashCode2 + (drawable != null ? drawable.hashCode() : 0)) * 31) + Integer.hashCode(this.itemType);
        }

        public String toString() {
            return "PasswordItem(number=" + this.number + ", text=" + this.text + ", drawable=" + this.drawable + ", itemType=" + this.itemType + ")";
        }

        public PasswordItem(Integer num, String str, Drawable drawable, int i) {
            this.number = num;
            this.text = str;
            this.drawable = drawable;
            this.itemType = i;
        }

        public /* synthetic */ PasswordItem(Integer num, String str, Drawable drawable, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? (Integer) null : num, (i2 & 2) != 0 ? (String) null : str, (i2 & 4) != 0 ? (Drawable) null : drawable, i);
        }

        public final Integer getNumber() {
            return this.number;
        }

        public final String getText() {
            return this.text;
        }

        public final Drawable getDrawable() {
            return this.drawable;
        }

        @Override // com.chad.library.adapter.base.entity.MultiItemEntity
        /* renamed from: getItemType */
        public int getMItemType() {
            return this.itemType;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CKeyboardPanel.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001d\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0005j\b\u0012\u0004\u0012\u00020\u0002`\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0002H\u0015¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/widget/CKeyboardPanel$PasswordItemAdapter;", "Lcom/chad/library/adapter/base/BaseMultiItemQuickAdapter;", "Lcom/pudutech/peanut/robot_ui/widget/CKeyboardPanel$PasswordItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "data", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "convert", "", "holder", "item", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class PasswordItemAdapter extends BaseMultiItemQuickAdapter<PasswordItem, BaseViewHolder> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PasswordItemAdapter(ArrayList<PasswordItem> data) {
            super(data);
            Intrinsics.checkParameterIsNotNull(data, "data");
            addItemType(0, C5508R.layout.view_keyboard_panel_item_number);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.chad.library.adapter.base.BaseQuickAdapter
        public void convert(BaseViewHolder holder, PasswordItem item) {
            Intrinsics.checkParameterIsNotNull(holder, "holder");
            Intrinsics.checkParameterIsNotNull(item, "item");
            ViewGroup viewGroup = (ViewGroup) holder.getView(C5508R.id.layout_wrap);
            final View view = holder.getView(C5508R.id.view_mask);
            viewGroup.setOnTouchListener(new View.OnTouchListener() { // from class: com.pudutech.peanut.robot_ui.widget.CKeyboardPanel$PasswordItemAdapter$convert$1
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view2, MotionEvent event) {
                    Intrinsics.checkExpressionValueIsNotNull(event, "event");
                    int action = event.getAction();
                    if (action == 0) {
                        View maskView = view;
                        Intrinsics.checkExpressionValueIsNotNull(maskView, "maskView");
                        maskView.setVisibility(0);
                    } else if (action == 1 || action == 3) {
                        View maskView2 = view;
                        Intrinsics.checkExpressionValueIsNotNull(maskView2, "maskView");
                        maskView2.setVisibility(8);
                    }
                    return false;
                }
            });
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition == 0) {
                viewGroup.setBackgroundResource(C5508R.drawable.shape_solid_ffffff_corners_topleft_12dp_bg);
                view.setBackgroundResource(C5508R.drawable.shape_solid_15000000_corners_topleft_12dp_bg);
            } else if (adapterPosition == 2) {
                viewGroup.setBackgroundResource(C5508R.drawable.shape_solid_ffffff_corners_topright_12dp_bg);
                view.setBackgroundResource(C5508R.drawable.shape_solid_15000000_corners_topright_12dp_bg);
            } else if (adapterPosition == 9) {
                viewGroup.setBackgroundResource(C5508R.drawable.shape_solid_ffffff_corners_bottomleft_12dp_bg);
                view.setBackgroundResource(C5508R.drawable.shape_solid_15000000_corners_bottomleft_12dp_bg);
            } else if (adapterPosition == 11) {
                viewGroup.setBackgroundResource(C5508R.drawable.shape_solid_ffffff_corners_bottomright_12dp_bg);
                view.setBackgroundResource(C5508R.drawable.shape_solid_15000000_corners_bottomright_12dp_bg);
            } else {
                viewGroup.setBackgroundResource(C5508R.drawable.shape_solid_ffffff_bg);
                view.setBackgroundResource(C5508R.drawable.shape_solid_15000000_bg);
            }
            if (holder.getItemViewType() != 0) {
                return;
            }
            holder.setText(C5508R.id.btn_number, String.valueOf(item.getNumber()));
        }
    }

    public final void setOnBackListener(Function0<Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.onBackListener = listener;
    }

    public final void setOnPasswordVerificationPassedListener(Function1<? super String, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.onPasswordVerificationPassedListener = listener;
    }

    public final void setOnSwitchSmartInputListener(Function0<Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.onSwitchSmartInputListener = listener;
    }

    public final void setOnSwitchNormalInputListener(Function0<Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.onSwitchNormalInputListener = listener;
    }

    public final void roomNumberSmartRecyclerViewScrollToTop() {
        getRoomNumberSmartRecyclerView().scrollToPosition(0);
    }
}
