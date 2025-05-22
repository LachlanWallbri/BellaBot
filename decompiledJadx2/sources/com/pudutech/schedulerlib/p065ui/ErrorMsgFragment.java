package com.pudutech.schedulerlib.p065ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.internal.view.SupportMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import com.pudutech.schedulerlib.C5725R;
import com.pudutech.schedulerlib.p065ui.SchedulerTestActivity;
import com.pudutech.schedulerlib.utils.SystemClock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: ErrorMsgFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J`\u0010\r\u001a\u00020\u000e2\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00102.\u0010\u0011\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0005j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006`\b2\u0006\u0010\u0012\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u000eH\u0002J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J&\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J(\u0010!\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0007\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00100\u0010H\u0016J\u001a\u0010\"\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016R6\u0010\u0004\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0005j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R6\u0010\u000b\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0005j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006#"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ui/ErrorMsgFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/pudutech/schedulerlib/ui/SchedulerTestActivity$OnErrorFrameListener;", "()V", "espMsgList", "Ljava/util/ArrayList;", "Lkotlin/Pair;", "", "Lkotlin/collections/ArrayList;", "espMsgListAdapter", "Lcom/pudutech/schedulerlib/ui/MsgListAdapter;", "udpMsgList", "udpMsgListAdapter", "checkMsgList", "", "info", "", "msgList", "adapter", "view", "Landroidx/recyclerview/widget/RecyclerView;", "initWidget", "onAttach", "activity", "Landroid/app/Activity;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onReceivedMsg", "onViewCreated", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ErrorMsgFragment extends Fragment implements SchedulerTestActivity.OnErrorFrameListener {
    private HashMap _$_findViewCache;
    private MsgListAdapter espMsgListAdapter;
    private MsgListAdapter udpMsgListAdapter;
    private ArrayList<Pair<String, String>> udpMsgList = new ArrayList<>();
    private ArrayList<Pair<String, String>> espMsgList = new ArrayList<>();

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

    @Override // com.pudutech.schedulerlib.ui.SchedulerTestActivity.OnErrorFrameListener
    public void onReceivedMsg(Map<String, Map<String, String>> info) {
        Intrinsics.checkParameterIsNotNull(info, "info");
        Map<String, String> map = info.get("udp");
        ArrayList<Pair<String, String>> arrayList = this.udpMsgList;
        MsgListAdapter msgListAdapter = this.udpMsgListAdapter;
        if (msgListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("udpMsgListAdapter");
        }
        checkMsgList(map, arrayList, msgListAdapter, (RecyclerView) _$_findCachedViewById(C5725R.id.recyclerView_udp));
        Map<String, String> map2 = info.get("esp");
        ArrayList<Pair<String, String>> arrayList2 = this.espMsgList;
        MsgListAdapter msgListAdapter2 = this.espMsgListAdapter;
        if (msgListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("espMsgListAdapter");
        }
        checkMsgList(map2, arrayList2, msgListAdapter2, (RecyclerView) _$_findCachedViewById(C5725R.id.recyclerView_esp));
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        super.onAttach(activity);
        if (activity instanceof SchedulerTestActivity) {
            ((SchedulerTestActivity) activity).setOnErrorMsgListener(this);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C5725R.layout.schedulerlib_fragment_error_msg_list, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        initWidget();
    }

    private final void initWidget() {
        RecyclerView recyclerView_udp = (RecyclerView) _$_findCachedViewById(C5725R.id.recyclerView_udp);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView_udp, "recyclerView_udp");
        RecyclerView.ItemAnimator itemAnimator = (RecyclerView.ItemAnimator) null;
        recyclerView_udp.setItemAnimator(itemAnimator);
        RecyclerView recyclerView_udp2 = (RecyclerView) _$_findCachedViewById(C5725R.id.recyclerView_udp);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView_udp2, "recyclerView_udp");
        recyclerView_udp2.setLayoutManager(new LinearLayoutManager(requireActivity()));
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        this.udpMsgListAdapter = new MsgListAdapter(requireContext, this.udpMsgList, SupportMenu.CATEGORY_MASK);
        RecyclerView recyclerView_udp3 = (RecyclerView) _$_findCachedViewById(C5725R.id.recyclerView_udp);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView_udp3, "recyclerView_udp");
        MsgListAdapter msgListAdapter = this.udpMsgListAdapter;
        if (msgListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("udpMsgListAdapter");
        }
        recyclerView_udp3.setAdapter(msgListAdapter);
        RecyclerView recyclerView_esp = (RecyclerView) _$_findCachedViewById(C5725R.id.recyclerView_esp);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView_esp, "recyclerView_esp");
        recyclerView_esp.setItemAnimator(itemAnimator);
        RecyclerView recyclerView_esp2 = (RecyclerView) _$_findCachedViewById(C5725R.id.recyclerView_esp);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView_esp2, "recyclerView_esp");
        recyclerView_esp2.setLayoutManager(new LinearLayoutManager(requireActivity()));
        Context requireContext2 = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
        this.espMsgListAdapter = new MsgListAdapter(requireContext2, this.espMsgList, SupportMenu.CATEGORY_MASK);
        RecyclerView recyclerView_esp3 = (RecyclerView) _$_findCachedViewById(C5725R.id.recyclerView_esp);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView_esp3, "recyclerView_esp");
        MsgListAdapter msgListAdapter2 = this.espMsgListAdapter;
        if (msgListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("espMsgListAdapter");
        }
        recyclerView_esp3.setAdapter(msgListAdapter2);
    }

    private final void checkMsgList(Map<String, String> info, ArrayList<Pair<String, String>> msgList, MsgListAdapter adapter, RecyclerView view) {
        Pdlog.m3273d("FPS", "msg list " + msgList + ", info: " + info);
        if (info == null) {
            return;
        }
        try {
            if (info.isEmpty()) {
                int size = msgList.size();
                for (int i = 0; i < size && i < msgList.size(); i++) {
                    adapter.notifyItemRemoved(i);
                    msgList.remove(i);
                }
            }
            int size2 = msgList.size();
            for (int i2 = 0; i2 < size2 && i2 < msgList.size(); i2++) {
                if (info.containsKey(msgList.get(i2).getFirst())) {
                    msgList.set(i2, new Pair<>(msgList.get(i2).getFirst(), (((msgList.get(i2).getFirst() + ":") + info.get(msgList.get(i2).getFirst())) + "_") + SystemClock.INSTANCE.get().now()));
                    info.remove(msgList.get(i2).getFirst());
                    adapter.notifyItemChanged(i2);
                } else {
                    adapter.notifyItemRemoved(i2);
                    Intrinsics.checkExpressionValueIsNotNull(msgList.remove(i2), "msgList.removeAt(i)");
                }
            }
            if (info.isEmpty()) {
                return;
            }
            for (Map.Entry<String, String> entry : info.entrySet()) {
                msgList.add(new Pair<>(entry.getKey(), (((entry.getKey() + ":") + entry.getValue()) + "_") + SystemClock.INSTANCE.get().now()));
                adapter.notifyItemChanged(msgList.size() - 1);
                if (view != null) {
                    view.smoothScrollToPosition(adapter.getItemCount());
                }
            }
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("exception msg ");
            StackTraceElement[] stackTrace = e.getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
            sb.append(ArraysKt.contentDeepToString(stackTrace));
            Pdlog.m3277w("FSP", sb.toString());
        }
    }
}
