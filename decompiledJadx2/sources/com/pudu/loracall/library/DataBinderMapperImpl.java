package com.pudu.loracall.library;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudu.loracall.library.databinding.LoraApproveFragmentBindingImpl;
import com.pudu.loracall.library.databinding.LoraCommonBlankBindingImpl;
import com.pudu.loracall.library.databinding.LoraCommonMvvmBindingImpl;
import com.pudu.loracall.library.databinding.LoraFilterPopBindingImpl;
import com.pudu.loracall.library.databinding.LoraHintPopBindingImpl;
import com.pudu.loracall.library.databinding.LoraHomeFragmentBindingImpl;
import com.pudu.loracall.library.databinding.LoraLogItemBindingImpl;
import com.pudu.loracall.library.databinding.LoraPerformanceFragmentBindingImpl;
import com.pudu.loracall.library.databinding.LoraPopBindingImpl;
import com.pudu.loracall.library.databinding.LoraPopFilterItemBindingImpl;
import com.pudu.loracall.library.databinding.LoraPopItemBindingImpl;
import com.pudu.loracall.library.databinding.LoraTableContentItemBindingImpl;
import com.pudu.loracall.library.databinding.LoraTableTopItemBindingImpl;
import com.pudu.loracall.library.databinding.LoraUpdatePopBindingImpl;
import com.pudu.loracall.library.databinding.LoraUpdatePopItemBindingImpl;
import com.pudu.loracall.library.databinding.LoraUpdateProgressPopBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(16);
    private static final int LAYOUT_LORAAPPROVEFRAGMENT = 1;
    private static final int LAYOUT_LORACOMMONBLANK = 2;
    private static final int LAYOUT_LORACOMMONMVVM = 3;
    private static final int LAYOUT_LORAFILTERPOP = 4;
    private static final int LAYOUT_LORAHINTPOP = 5;
    private static final int LAYOUT_LORAHOMEFRAGMENT = 6;
    private static final int LAYOUT_LORALOGITEM = 7;
    private static final int LAYOUT_LORAPERFORMANCEFRAGMENT = 8;
    private static final int LAYOUT_LORAPOP = 9;
    private static final int LAYOUT_LORAPOPFILTERITEM = 10;
    private static final int LAYOUT_LORAPOPITEM = 11;
    private static final int LAYOUT_LORATABLECONTENTITEM = 12;
    private static final int LAYOUT_LORATABLETOPITEM = 13;
    private static final int LAYOUT_LORAUPDATEPOP = 14;
    private static final int LAYOUT_LORAUPDATEPOPITEM = 15;
    private static final int LAYOUT_LORAUPDATEPROGRESSPOP = 16;

    static {
        INTERNAL_LAYOUT_ID_LOOKUP.put(C3965R.layout.lora_approve_fragment, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(C3965R.layout.lora_common_blank, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(C3965R.layout.lora_common_mvvm, 3);
        INTERNAL_LAYOUT_ID_LOOKUP.put(C3965R.layout.lora_filter_pop, 4);
        INTERNAL_LAYOUT_ID_LOOKUP.put(C3965R.layout.lora_hint_pop, 5);
        INTERNAL_LAYOUT_ID_LOOKUP.put(C3965R.layout.lora_home_fragment, 6);
        INTERNAL_LAYOUT_ID_LOOKUP.put(C3965R.layout.lora_log_item, 7);
        INTERNAL_LAYOUT_ID_LOOKUP.put(C3965R.layout.lora_performance_fragment, 8);
        INTERNAL_LAYOUT_ID_LOOKUP.put(C3965R.layout.lora_pop, 9);
        INTERNAL_LAYOUT_ID_LOOKUP.put(C3965R.layout.lora_pop_filter_item, 10);
        INTERNAL_LAYOUT_ID_LOOKUP.put(C3965R.layout.lora_pop_item, 11);
        INTERNAL_LAYOUT_ID_LOOKUP.put(C3965R.layout.lora_table_content_item, 12);
        INTERNAL_LAYOUT_ID_LOOKUP.put(C3965R.layout.lora_table_top_item, 13);
        INTERNAL_LAYOUT_ID_LOOKUP.put(C3965R.layout.lora_update_pop, 14);
        INTERNAL_LAYOUT_ID_LOOKUP.put(C3965R.layout.lora_update_pop_item, 15);
        INTERNAL_LAYOUT_ID_LOOKUP.put(C3965R.layout.lora_update_progress_pop, 16);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag == null) {
            throw new RuntimeException("view must have a tag");
        }
        switch (i2) {
            case 1:
                if ("layout/lora_approve_fragment_0".equals(tag)) {
                    return new LoraApproveFragmentBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for lora_approve_fragment is invalid. Received: " + tag);
            case 2:
                if ("layout/lora_common_blank_0".equals(tag)) {
                    return new LoraCommonBlankBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for lora_common_blank is invalid. Received: " + tag);
            case 3:
                if ("layout/lora_common_mvvm_0".equals(tag)) {
                    return new LoraCommonMvvmBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for lora_common_mvvm is invalid. Received: " + tag);
            case 4:
                if ("layout/lora_filter_pop_0".equals(tag)) {
                    return new LoraFilterPopBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for lora_filter_pop is invalid. Received: " + tag);
            case 5:
                if ("layout/lora_hint_pop_0".equals(tag)) {
                    return new LoraHintPopBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for lora_hint_pop is invalid. Received: " + tag);
            case 6:
                if ("layout/lora_home_fragment_0".equals(tag)) {
                    return new LoraHomeFragmentBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for lora_home_fragment is invalid. Received: " + tag);
            case 7:
                if ("layout/lora_log_item_0".equals(tag)) {
                    return new LoraLogItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for lora_log_item is invalid. Received: " + tag);
            case 8:
                if ("layout/lora_performance_fragment_0".equals(tag)) {
                    return new LoraPerformanceFragmentBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for lora_performance_fragment is invalid. Received: " + tag);
            case 9:
                if ("layout/lora_pop_0".equals(tag)) {
                    return new LoraPopBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for lora_pop is invalid. Received: " + tag);
            case 10:
                if ("layout/lora_pop_filter_item_0".equals(tag)) {
                    return new LoraPopFilterItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for lora_pop_filter_item is invalid. Received: " + tag);
            case 11:
                if ("layout/lora_pop_item_0".equals(tag)) {
                    return new LoraPopItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for lora_pop_item is invalid. Received: " + tag);
            case 12:
                if ("layout/lora_table_content_item_0".equals(tag)) {
                    return new LoraTableContentItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for lora_table_content_item is invalid. Received: " + tag);
            case 13:
                if ("layout/lora_table_top_item_0".equals(tag)) {
                    return new LoraTableTopItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for lora_table_top_item is invalid. Received: " + tag);
            case 14:
                if ("layout/lora_update_pop_0".equals(tag)) {
                    return new LoraUpdatePopBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for lora_update_pop is invalid. Received: " + tag);
            case 15:
                if ("layout/lora_update_pop_item_0".equals(tag)) {
                    return new LoraUpdatePopItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for lora_update_pop_item is invalid. Received: " + tag);
            case 16:
                if ("layout/lora_update_progress_pop_0".equals(tag)) {
                    return new LoraUpdateProgressPopBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for lora_update_progress_pop is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    /* loaded from: classes4.dex */
    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>(12);

        private InnerBrLookup() {
        }

        static {
            sKeys.put(0, "_all");
            sKeys.put(1, "devAdder");
            sKeys.put(2, "item");
            sKeys.put(3, "expand");
            sKeys.put(4, "viewModel");
            sKeys.put(5, "binding");
            sKeys.put(6, "isfood");
            sKeys.put(7, "oldItem");
            sKeys.put(8, "isSelect");
            sKeys.put(9, RequestParameters.POSITION);
            sKeys.put(10, "mContext");
        }
    }

    /* loaded from: classes4.dex */
    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(16);

        private InnerLayoutIdLookup() {
        }

        static {
            sKeys.put("layout/lora_approve_fragment_0", Integer.valueOf(C3965R.layout.lora_approve_fragment));
            sKeys.put("layout/lora_common_blank_0", Integer.valueOf(C3965R.layout.lora_common_blank));
            sKeys.put("layout/lora_common_mvvm_0", Integer.valueOf(C3965R.layout.lora_common_mvvm));
            sKeys.put("layout/lora_filter_pop_0", Integer.valueOf(C3965R.layout.lora_filter_pop));
            sKeys.put("layout/lora_hint_pop_0", Integer.valueOf(C3965R.layout.lora_hint_pop));
            sKeys.put("layout/lora_home_fragment_0", Integer.valueOf(C3965R.layout.lora_home_fragment));
            sKeys.put("layout/lora_log_item_0", Integer.valueOf(C3965R.layout.lora_log_item));
            sKeys.put("layout/lora_performance_fragment_0", Integer.valueOf(C3965R.layout.lora_performance_fragment));
            sKeys.put("layout/lora_pop_0", Integer.valueOf(C3965R.layout.lora_pop));
            sKeys.put("layout/lora_pop_filter_item_0", Integer.valueOf(C3965R.layout.lora_pop_filter_item));
            sKeys.put("layout/lora_pop_item_0", Integer.valueOf(C3965R.layout.lora_pop_item));
            sKeys.put("layout/lora_table_content_item_0", Integer.valueOf(C3965R.layout.lora_table_content_item));
            sKeys.put("layout/lora_table_top_item_0", Integer.valueOf(C3965R.layout.lora_table_top_item));
            sKeys.put("layout/lora_update_pop_0", Integer.valueOf(C3965R.layout.lora_update_pop));
            sKeys.put("layout/lora_update_pop_item_0", Integer.valueOf(C3965R.layout.lora_update_pop_item));
            sKeys.put("layout/lora_update_progress_pop_0", Integer.valueOf(C3965R.layout.lora_update_progress_pop));
        }
    }
}
