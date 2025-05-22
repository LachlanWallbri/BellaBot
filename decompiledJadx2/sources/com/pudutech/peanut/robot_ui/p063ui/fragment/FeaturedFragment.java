package com.pudutech.peanut.robot_ui.p063ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import com.bumptech.glide.Glide;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.ext.view.NavigationExtKt;
import com.pudutech.disinfect.baselib.network.response.FeaturedBean;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.p063ui.SolicitCustomerActivity;
import com.pudutech.peanut.robot_ui.p063ui.adapter.GridSpacingItemDecoration;
import com.pudutech.peanut.robot_ui.p063ui.adapter.PromotionMultipleItem;
import com.pudutech.peanut.robot_ui.p063ui.adapter.PromotionsAdapter;
import com.pudutech.peanut.robot_ui.viewmodel.PromotionsViewModel;
import com.pudutech.peanut.robot_ui.widget.EmptyRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: FeaturedFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\u0010\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0012\u0010\u001d\u001a\u00020\u00182\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J$\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010&\u001a\u00020\u0018H\u0016J\u0016\u0010'\u001a\u00020\u00182\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0(H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000e0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/fragment/FeaturedFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "handler", "Landroid/os/Handler;", "mAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/PromotionsAdapter;", "mData", "", "Lcom/pudutech/disinfect/baselib/network/response/FeaturedBean;", "mList", "Lcom/pudutech/peanut/robot_ui/ui/adapter/PromotionMultipleItem;", "mPromotionVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/PromotionsViewModel;", "getMPromotionVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/PromotionsViewModel;", "mPromotionVm$delegate", "Lkotlin/Lazy;", "pageIndex", "", "initVM", "", "initView", "jumpAndFinish", "intent", "Landroid/content/Intent;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "setData", "", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class FeaturedFragment extends Fragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int DATA_NOTIFY = 4;
    private String TAG = getClass().getSimpleName();
    private HashMap _$_findViewCache;
    private Handler handler;
    private PromotionsAdapter mAdapter;
    private List<FeaturedBean> mData;
    private List<PromotionMultipleItem<FeaturedBean>> mList;

    /* renamed from: mPromotionVm$delegate, reason: from kotlin metadata */
    private final Lazy mPromotionVm;
    private int pageIndex;

    /* JADX INFO: Access modifiers changed from: private */
    public final PromotionsViewModel getMPromotionVm() {
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

    public FeaturedFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.FeaturedFragment$$special$$inlined$viewModels$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        this.mPromotionVm = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(PromotionsViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.FeaturedFragment$$special$$inlined$viewModels$2
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
        this.handler = new Companion.WithoutLeakHandler(this);
        this.mList = new ArrayList();
        this.mData = new ArrayList();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        View inflate = inflater.inflate(C5508R.layout.featured_fragment, container, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…agment, container, false)");
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initVM();
        getMPromotionVm().getFeaturedData(this.pageIndex);
    }

    private final void initView() {
        ((SmartRefreshLayout) _$_findCachedViewById(C5508R.id.refresh)).setEnableLoadMore(true);
        ((SmartRefreshLayout) _$_findCachedViewById(C5508R.id.refresh)).setEnableRefresh(false);
        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(C5508R.id.refresh);
        if (smartRefreshLayout == null) {
            Intrinsics.throwNpe();
        }
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.FeaturedFragment$initView$1
            @Override // com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
            public final void onLoadMore(RefreshLayout it) {
                int i;
                PromotionsViewModel mPromotionVm;
                int i2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                FeaturedFragment featuredFragment = FeaturedFragment.this;
                i = featuredFragment.pageIndex;
                featuredFragment.pageIndex = i + 1;
                mPromotionVm = FeaturedFragment.this.getMPromotionVm();
                i2 = FeaturedFragment.this.pageIndex;
                mPromotionVm.getFeaturedData(i2);
            }
        });
        SmartRefreshLayout smartRefreshLayout2 = (SmartRefreshLayout) _$_findCachedViewById(C5508R.id.refresh);
        if (smartRefreshLayout2 == null) {
            Intrinsics.throwNpe();
        }
        smartRefreshLayout2.setOnRefreshListener(new OnRefreshListener() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.FeaturedFragment$initView$2
            @Override // com.scwang.smartrefresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout it) {
                List list;
                PromotionsViewModel mPromotionVm;
                int i;
                Intrinsics.checkParameterIsNotNull(it, "it");
                FeaturedFragment.this.pageIndex = 0;
                list = FeaturedFragment.this.mList;
                if (list != null) {
                    list.clear();
                }
                mPromotionVm = FeaturedFragment.this.getMPromotionVm();
                i = FeaturedFragment.this.pageIndex;
                mPromotionVm.getFeaturedData(i);
            }
        });
        TextView tvShop = (TextView) _$_findCachedViewById(C5508R.id.tvShop);
        Intrinsics.checkExpressionValueIsNotNull(tvShop, "tvShop");
        ViewExtKt.onSingleClick(tvShop, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.FeaturedFragment$initView$3
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
                Intent intent = new Intent(FeaturedFragment.this.getActivity(), (Class<?>) SolicitCustomerActivity.class);
                intent.putExtra("state", 1);
                FeaturedFragment.this.jumpAndFinish(intent);
            }
        });
        TextView tvCoupon = (TextView) _$_findCachedViewById(C5508R.id.tvCoupon);
        Intrinsics.checkExpressionValueIsNotNull(tvCoupon, "tvCoupon");
        ViewExtKt.onSingleClick(tvCoupon, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.FeaturedFragment$initView$4
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
                NavigationExtKt.nav(FeaturedFragment.this).navigate(C5508R.id.action_FeaturedPromotions_to_initNav);
            }
        });
        TextView tvPromotions = (TextView) _$_findCachedViewById(C5508R.id.tvPromotions);
        Intrinsics.checkExpressionValueIsNotNull(tvPromotions, "tvPromotions");
        ViewExtKt.onSingleClick(tvPromotions, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.FeaturedFragment$initView$5
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
                NavigationExtKt.nav(FeaturedFragment.this).navigate(C5508R.id.action_FeaturedPromotions_to_initNav);
            }
        });
        TextView tvGoShop = (TextView) _$_findCachedViewById(C5508R.id.tvGoShop);
        Intrinsics.checkExpressionValueIsNotNull(tvGoShop, "tvGoShop");
        ViewExtKt.onSingleClick(tvGoShop, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.FeaturedFragment$initView$6
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
                Intent intent = new Intent(FeaturedFragment.this.getActivity(), (Class<?>) SolicitCustomerActivity.class);
                intent.putExtra("state", 1);
                FeaturedFragment.this.jumpAndFinish(intent);
            }
        });
        LinearLayout llBack = (LinearLayout) _$_findCachedViewById(C5508R.id.llBack);
        Intrinsics.checkExpressionValueIsNotNull(llBack, "llBack");
        ViewExtKt.onSingleClick(llBack, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.FeaturedFragment$initView$7
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
                NavigationExtKt.nav(FeaturedFragment.this).popBackStack();
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void setData(List<FeaturedBean> mData) {
        int i;
        Pdlog.m3273d(this.TAG, "mList size: " + this.mList.size() + ' ');
        if (this.pageIndex == 0) {
            int size = mData.size();
            if (size == 1) {
                this.mList.add(new PromotionMultipleItem<>(1, mData.get(0)));
                i = 1;
            } else if (size == 2) {
                this.mList.add(new PromotionMultipleItem<>(2, mData.get(0)));
                this.mList.add(new PromotionMultipleItem<>(2, mData.get(1)));
                i = 2;
            } else {
                i = 3;
                Iterator<T> it = mData.iterator();
                while (it.hasNext()) {
                    this.mList.add(new PromotionMultipleItem<>(2, (FeaturedBean) it.next()));
                }
            }
            this.mAdapter = new PromotionsAdapter(this.mList);
            EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) _$_findCachedViewById(C5508R.id.recyclerView);
            GridLayoutManager gridLayoutManager = new GridLayoutManager((Context) getActivity(), i, 1, false);
            emptyRecyclerView.addItemDecoration(new GridSpacingItemDecoration(i, 16, true));
            emptyRecyclerView.setLayoutManager(gridLayoutManager);
            emptyRecyclerView.setAdapter(this.mAdapter);
            EmptyRecyclerView emptyRecyclerView2 = (EmptyRecyclerView) _$_findCachedViewById(C5508R.id.recyclerView);
            if (emptyRecyclerView2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.widget.EmptyRecyclerView");
            }
            emptyRecyclerView2.setEmptyView(_$_findCachedViewById(C5508R.id.id_empty_view));
        } else {
            Iterator<T> it2 = mData.iterator();
            while (it2.hasNext()) {
                this.mList.add(new PromotionMultipleItem<>(2, (FeaturedBean) it2.next()));
            }
        }
        if (this.mList.size() == 0) {
            LinearLayout llTitle = (LinearLayout) _$_findCachedViewById(C5508R.id.llTitle);
            Intrinsics.checkExpressionValueIsNotNull(llTitle, "llTitle");
            llTitle.setVisibility(8);
        }
        if (mData.size() < 20) {
            ((SmartRefreshLayout) _$_findCachedViewById(C5508R.id.refresh)).setEnableLoadMore(false);
        } else {
            ((SmartRefreshLayout) _$_findCachedViewById(C5508R.id.refresh)).setEnableLoadMore(true);
        }
        PromotionsAdapter promotionsAdapter = this.mAdapter;
        if (promotionsAdapter != null) {
            promotionsAdapter.setNewData(this.mList);
        }
    }

    private final void initVM() {
        Pdlog.m3273d(this.TAG, "initVM ");
        getMPromotionVm().getMFeaturedDada().observe(getViewLifecycleOwner(), new Observer<List<? extends FeaturedBean>>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.FeaturedFragment$initVM$1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends FeaturedBean> list) {
                onChanged2((List<FeaturedBean>) list);
            }

            /* renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<FeaturedBean> list) {
                int i;
                if (list == null) {
                    LinearLayout llTitle = (LinearLayout) FeaturedFragment.this._$_findCachedViewById(C5508R.id.llTitle);
                    Intrinsics.checkExpressionValueIsNotNull(llTitle, "llTitle");
                    llTitle.setVisibility(8);
                    SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) FeaturedFragment.this._$_findCachedViewById(C5508R.id.refresh);
                    if (smartRefreshLayout != null) {
                        smartRefreshLayout.finishRefresh(true);
                    }
                    SmartRefreshLayout smartRefreshLayout2 = (SmartRefreshLayout) FeaturedFragment.this._$_findCachedViewById(C5508R.id.refresh);
                    if (smartRefreshLayout2 != null) {
                        smartRefreshLayout2.finishLoadMore(true);
                        return;
                    }
                    return;
                }
                SmartRefreshLayout smartRefreshLayout3 = (SmartRefreshLayout) FeaturedFragment.this._$_findCachedViewById(C5508R.id.refresh);
                if (smartRefreshLayout3 != null) {
                    smartRefreshLayout3.finishRefresh();
                }
                SmartRefreshLayout smartRefreshLayout4 = (SmartRefreshLayout) FeaturedFragment.this._$_findCachedViewById(C5508R.id.refresh);
                if (smartRefreshLayout4 != null) {
                    smartRefreshLayout4.finishLoadMore();
                }
                i = FeaturedFragment.this.pageIndex;
                if (i == 0) {
                    ((SmartRefreshLayout) FeaturedFragment.this._$_findCachedViewById(C5508R.id.refresh)).finishRefresh();
                } else {
                    ((SmartRefreshLayout) FeaturedFragment.this._$_findCachedViewById(C5508R.id.refresh)).finishLoadMore();
                }
                FeaturedFragment.this.setData(list);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacksAndMessages(null);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Glide.get(activity).clearMemory();
        }
    }

    /* compiled from: FeaturedFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/fragment/FeaturedFragment$Companion;", "", "()V", "DATA_NOTIFY", "", "newInstance", "Lcom/pudutech/peanut/robot_ui/ui/fragment/FeaturedFragment;", "WithoutLeakHandler", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FeaturedFragment newInstance() {
            return new FeaturedFragment();
        }

        /* compiled from: FeaturedFragment.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/fragment/FeaturedFragment$Companion$WithoutLeakHandler;", "Landroid/os/Handler;", "fragment", "Lcom/pudutech/peanut/robot_ui/ui/fragment/FeaturedFragment;", "(Lcom/pudutech/peanut/robot_ui/ui/fragment/FeaturedFragment;)V", "mActivity", "Ljava/lang/ref/WeakReference;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        private static final class WithoutLeakHandler extends Handler {
            private WeakReference<FeaturedFragment> mActivity;

            public WithoutLeakHandler(FeaturedFragment fragment) {
                Intrinsics.checkParameterIsNotNull(fragment, "fragment");
                this.mActivity = new WeakReference<>(fragment);
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                super.handleMessage(msg);
                FeaturedFragment featuredFragment = this.mActivity.get();
                if (featuredFragment == null || msg.what != 4) {
                    return;
                }
                featuredFragment.setData(featuredFragment.mData);
            }
        }
    }
}
