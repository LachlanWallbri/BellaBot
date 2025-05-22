package com.pudutech.peanut.robot_ui.p063ui.cruise;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.ext.view.NavigationExtKt;
import com.pudutech.disinfect.baselib.network.response.PromotionsBean;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.p063ui.SolicitCustomerActivity;
import com.pudutech.peanut.robot_ui.viewmodel.PromotionsViewModel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: CruisePromotionsFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 &2\u00020\u0001:\u0001&B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\u0010\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0012\u0010\u001a\u001a\u00020\u00152\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J$\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010#\u001a\u00020\u0015H\u0016J\u0010\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\nH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011¨\u0006'"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/cruise/CruisePromotionsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "handler", "Landroid/os/Handler;", "mAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/disinfect/baselib/network/response/PromotionsBean;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "mList", "", "mPromotionVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/PromotionsViewModel;", "getMPromotionVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/PromotionsViewModel;", "mPromotionVm$delegate", "Lkotlin/Lazy;", "initVM", "", "initView", "jumpAndFinish", "intent", "Landroid/content/Intent;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "setContent", "data", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CruisePromotionsFragment extends Fragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int DATA_NOTIFY = 4;
    private HashMap _$_findViewCache;
    private BaseQuickAdapter<PromotionsBean, BaseViewHolder> mAdapter;
    private List<PromotionsBean> mList;

    /* renamed from: mPromotionVm$delegate, reason: from kotlin metadata */
    private final Lazy mPromotionVm;
    private String TAG = getClass().getSimpleName();
    private Handler handler = new Companion.WithoutLeakHandler(this);

    private final PromotionsViewModel getMPromotionVm() {
        return (PromotionsViewModel) this.mPromotionVm.getValue();
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

    public CruisePromotionsFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruisePromotionsFragment$$special$$inlined$viewModels$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        this.mPromotionVm = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(PromotionsViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruisePromotionsFragment$$special$$inlined$viewModels$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) Function0.this.invoke()).getViewModelStore();
                Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "ownerProducer().viewModelStore");
                return viewModelStore;
            }
        }, (Function0) null);
        this.mList = new ArrayList();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        View inflate = inflater.inflate(C5508R.layout.promotions_fragment, container, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…agment, container, false)");
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initVM();
        getMPromotionVm().getPromotionsData(0);
    }

    private final void initView() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView);
        if (recyclerView == null) {
            Intrinsics.throwNpe();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        BaseQuickAdapter<PromotionsBean, BaseViewHolder> baseQuickAdapter = this.mAdapter;
        if (baseQuickAdapter == null) {
            this.mAdapter = new CruisePromotionsFragment$initView$1(this, C5508R.layout.promotion_item, this.mList);
        } else if (baseQuickAdapter != null) {
            baseQuickAdapter.notifyDataSetChanged();
        }
        BaseQuickAdapter<PromotionsBean, BaseViewHolder> baseQuickAdapter2 = this.mAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwNpe();
        }
        baseQuickAdapter2.notifyDataSetChanged();
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "recyclerView");
        recyclerView2.setAdapter(this.mAdapter);
        TextView tvFeature = (TextView) _$_findCachedViewById(C5508R.id.tvFeature);
        Intrinsics.checkExpressionValueIsNotNull(tvFeature, "tvFeature");
        ViewExtKt.onSingleClick(tvFeature, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruisePromotionsFragment$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                NavigationExtKt.nav(CruisePromotionsFragment.this).navigate(C5508R.id.action_PromotionsFeatured_to_initNav);
            }
        });
        TextView tvFeatured = (TextView) _$_findCachedViewById(C5508R.id.tvFeatured);
        Intrinsics.checkExpressionValueIsNotNull(tvFeatured, "tvFeatured");
        ViewExtKt.onSingleClick(tvFeatured, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruisePromotionsFragment$initView$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                NavigationExtKt.nav(CruisePromotionsFragment.this).navigate(C5508R.id.action_PromotionsFeatured_to_initNav);
            }
        });
        TextView tvGoShop = (TextView) _$_findCachedViewById(C5508R.id.tvGoShop);
        Intrinsics.checkExpressionValueIsNotNull(tvGoShop, "tvGoShop");
        ViewExtKt.onSingleClick(tvGoShop, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruisePromotionsFragment$initView$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intent intent = new Intent(CruisePromotionsFragment.this.getActivity(), (Class<?>) SolicitCustomerActivity.class);
                intent.putExtra("state", 1);
                CruisePromotionsFragment.this.jumpAndFinish(intent);
            }
        });
        TextView tvShop = (TextView) _$_findCachedViewById(C5508R.id.tvShop);
        Intrinsics.checkExpressionValueIsNotNull(tvShop, "tvShop");
        ViewExtKt.onSingleClick(tvShop, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruisePromotionsFragment$initView$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intent intent = new Intent(CruisePromotionsFragment.this.getActivity(), (Class<?>) SolicitCustomerActivity.class);
                intent.putExtra("state", 1);
                CruisePromotionsFragment.this.jumpAndFinish(intent);
            }
        });
        LinearLayout llBack = (LinearLayout) _$_findCachedViewById(C5508R.id.llBack);
        Intrinsics.checkExpressionValueIsNotNull(llBack, "llBack");
        ViewExtKt.onSingleClick(llBack, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruisePromotionsFragment$initView$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                NavigationExtKt.nav(CruisePromotionsFragment.this).popBackStack();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jumpAndFinish(Intent intent) {
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        startActivity(intent);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    private final void initVM() {
        Pdlog.m3273d(this.TAG, "initVM ");
        getMPromotionVm().getMPromotionsDada().observe(getViewLifecycleOwner(), new Observer<List<? extends PromotionsBean>>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruisePromotionsFragment$initVM$1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends PromotionsBean> list) {
                onChanged2((List<PromotionsBean>) list);
            }

            /* renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<PromotionsBean> list) {
                if (list == null || list.size() == 0) {
                    LinearLayout llTitle = (LinearLayout) CruisePromotionsFragment.this._$_findCachedViewById(C5508R.id.llTitle);
                    Intrinsics.checkExpressionValueIsNotNull(llTitle, "llTitle");
                    llTitle.setVisibility(8);
                    LinearLayout llContent = (LinearLayout) CruisePromotionsFragment.this._$_findCachedViewById(C5508R.id.llContent);
                    Intrinsics.checkExpressionValueIsNotNull(llContent, "llContent");
                    llContent.setVisibility(8);
                    ConstraintLayout container = (ConstraintLayout) CruisePromotionsFragment.this._$_findCachedViewById(C5508R.id.container);
                    Intrinsics.checkExpressionValueIsNotNull(container, "container");
                    container.setVisibility(0);
                    return;
                }
                LinearLayout llTitle2 = (LinearLayout) CruisePromotionsFragment.this._$_findCachedViewById(C5508R.id.llTitle);
                Intrinsics.checkExpressionValueIsNotNull(llTitle2, "llTitle");
                llTitle2.setVisibility(0);
                LinearLayout llContent2 = (LinearLayout) CruisePromotionsFragment.this._$_findCachedViewById(C5508R.id.llContent);
                Intrinsics.checkExpressionValueIsNotNull(llContent2, "llContent");
                llContent2.setVisibility(0);
                ConstraintLayout container2 = (ConstraintLayout) CruisePromotionsFragment.this._$_findCachedViewById(C5508R.id.container);
                Intrinsics.checkExpressionValueIsNotNull(container2, "container");
                container2.setVisibility(8);
                CruisePromotionsFragment.this.setContent(list.get(0));
                list.get(0).setSelect(true);
                CruisePromotionsFragment.this.mList.addAll(list);
                RecyclerView recyclerView = (RecyclerView) CruisePromotionsFragment.this._$_findCachedViewById(C5508R.id.recyclerView);
                Intrinsics.checkExpressionValueIsNotNull(recyclerView, "recyclerView");
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setContent(PromotionsBean data) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Glide.with(activity).load(data.getImage_url()).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new RoundedCorners((int) getResources().getDimension(C5508R.dimen.d16)))).into((ImageView) _$_findCachedViewById(C5508R.id.ivFeaturedImg));
        }
        TextView tvDesc = (TextView) _$_findCachedViewById(C5508R.id.tvDesc);
        Intrinsics.checkExpressionValueIsNotNull(tvDesc, "tvDesc");
        tvDesc.setText(data.getContent());
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacksAndMessages(null);
    }

    /* compiled from: CruisePromotionsFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/cruise/CruisePromotionsFragment$Companion;", "", "()V", "DATA_NOTIFY", "", "newInstance", "Lcom/pudutech/peanut/robot_ui/ui/cruise/CruisePromotionsFragment;", "WithoutLeakHandler", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CruisePromotionsFragment newInstance() {
            return new CruisePromotionsFragment();
        }

        /* compiled from: CruisePromotionsFragment.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/cruise/CruisePromotionsFragment$Companion$WithoutLeakHandler;", "Landroid/os/Handler;", "fragment", "Lcom/pudutech/peanut/robot_ui/ui/cruise/CruisePromotionsFragment;", "(Lcom/pudutech/peanut/robot_ui/ui/cruise/CruisePromotionsFragment;)V", "mActivity", "Ljava/lang/ref/WeakReference;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        private static final class WithoutLeakHandler extends Handler {
            private WeakReference<CruisePromotionsFragment> mActivity;

            public WithoutLeakHandler(CruisePromotionsFragment fragment) {
                Intrinsics.checkParameterIsNotNull(fragment, "fragment");
                this.mActivity = new WeakReference<>(fragment);
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                super.handleMessage(msg);
                CruisePromotionsFragment cruisePromotionsFragment = this.mActivity.get();
                if (cruisePromotionsFragment == null || msg.what != 4) {
                    return;
                }
                LinearLayout llTitle = (LinearLayout) cruisePromotionsFragment._$_findCachedViewById(C5508R.id.llTitle);
                Intrinsics.checkExpressionValueIsNotNull(llTitle, "llTitle");
                llTitle.setVisibility(0);
                LinearLayout llContent = (LinearLayout) cruisePromotionsFragment._$_findCachedViewById(C5508R.id.llContent);
                Intrinsics.checkExpressionValueIsNotNull(llContent, "llContent");
                llContent.setVisibility(0);
                ConstraintLayout container = (ConstraintLayout) cruisePromotionsFragment._$_findCachedViewById(C5508R.id.container);
                Intrinsics.checkExpressionValueIsNotNull(container, "container");
                container.setVisibility(8);
                cruisePromotionsFragment.mList.add(new PromotionsBean("小花生", "商家文案描述商家文案描述，商家文案描述商家文案描述商家文案，商家文案描述，商家文案描述商家文案描述商家文案描述商家文案描述.", "", true));
                cruisePromotionsFragment.mList.add(new PromotionsBean("小猫", "第十六届丰富的射流风机", "", false));
                cruisePromotionsFragment.mList.add(new PromotionsBean("小狗", "大幅度水电费克雷登斯", "", false));
                cruisePromotionsFragment.mList.add(new PromotionsBean("小宋", "第三方的范德萨理发店里健康垃圾嘚瑟福利都", "", false));
                RecyclerView recyclerView = (RecyclerView) cruisePromotionsFragment._$_findCachedViewById(C5508R.id.recyclerView);
                Intrinsics.checkExpressionValueIsNotNull(recyclerView, "recyclerView");
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
