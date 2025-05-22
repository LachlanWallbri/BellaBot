package com.pudutech.robot.opensdk.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.robot.opensdk.MsgContext;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.interf.IOnOpenSdkEventListener;
import com.pudutech.robot_open_sdk.C5723R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: OpenAPIActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0004\u000f\u0010\u0011\u0012B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\u0012\u0010\f\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/activity/OpenAPIActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "eventList", "Lcom/pudutech/robot/opensdk/activity/OpenAPIActivity$EventListAdapter;", "mqttMsgList", "Lcom/pudutech/robot/opensdk/activity/OpenAPIActivity$MQTTMsgListAdapter;", "initOpenSDK", "", "initWidgets", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "EventListAdapter", "EventViewHolder", "MQTTMsgListAdapter", "MQTTMsgViewHolder", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class OpenAPIActivity extends AppCompatActivity {
    private final String TAG = "OpenAPIActivity";
    private HashMap _$_findViewCache;
    private EventListAdapter eventList;
    private MQTTMsgListAdapter mqttMsgList;

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

    public static final /* synthetic */ EventListAdapter access$getEventList$p(OpenAPIActivity openAPIActivity) {
        EventListAdapter eventListAdapter = openAPIActivity.eventList;
        if (eventListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventList");
        }
        return eventListAdapter;
    }

    public static final /* synthetic */ MQTTMsgListAdapter access$getMqttMsgList$p(OpenAPIActivity openAPIActivity) {
        MQTTMsgListAdapter mQTTMsgListAdapter = openAPIActivity.mqttMsgList;
        if (mQTTMsgListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mqttMsgList");
        }
        return mQTTMsgListAdapter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5723R.layout.activity_open_api);
        initWidgets();
        initOpenSDK();
    }

    private final void initOpenSDK() {
        RobotOpenSdk.INSTANCE.setOnEventListener(new IOnOpenSdkEventListener() { // from class: com.pudutech.robot.opensdk.activity.OpenAPIActivity$initOpenSDK$1
            @Override // com.pudutech.robot.opensdk.interf.IOnOpenSdkEventListener
            public void onEvent(MsgContext<?> msgContext) {
                Intrinsics.checkParameterIsNotNull(msgContext, "msgContext");
                OpenAPIActivity.access$getEventList$p(OpenAPIActivity.this).addEvent(msgContext.getMsgType());
            }
        });
        RobotOpenSdk.INSTANCE.setOnMQTTMessageListener(new RobotOpenSdk.IOnMQTTMessage() { // from class: com.pudutech.robot.opensdk.activity.OpenAPIActivity$initOpenSDK$2
            @Override // com.pudutech.robot.opensdk.RobotOpenSdk.IOnMQTTMessage
            public void onSend(String topic, String payload) {
                Intrinsics.checkParameterIsNotNull(topic, "topic");
                Intrinsics.checkParameterIsNotNull(payload, "payload");
                OpenAPIActivity.access$getMqttMsgList$p(OpenAPIActivity.this).add(true, topic, payload);
            }

            @Override // com.pudutech.robot.opensdk.RobotOpenSdk.IOnMQTTMessage
            public void onReceive(String topic, String payload) {
                Intrinsics.checkParameterIsNotNull(topic, "topic");
                Intrinsics.checkParameterIsNotNull(payload, "payload");
                OpenAPIActivity.access$getMqttMsgList$p(OpenAPIActivity.this).add(false, topic, payload);
            }
        });
    }

    private final void initWidgets() {
        OpenAPIActivity openAPIActivity = this;
        RecyclerView recycleView_event_list = (RecyclerView) _$_findCachedViewById(C5723R.id.recycleView_event_list);
        Intrinsics.checkExpressionValueIsNotNull(recycleView_event_list, "recycleView_event_list");
        this.eventList = new EventListAdapter(openAPIActivity, recycleView_event_list);
        RecyclerView recycleView_message_list = (RecyclerView) _$_findCachedViewById(C5723R.id.recycleView_message_list);
        Intrinsics.checkExpressionValueIsNotNull(recycleView_message_list, "recycleView_message_list");
        this.mqttMsgList = new MQTTMsgListAdapter(openAPIActivity, recycleView_message_list);
    }

    /* compiled from: OpenAPIActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u0005¢\u0006\u0002\u0010\bJ\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000fJ\b\u0010\u001d\u001a\u00020\u0015H\u0016J\u0018\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u0015H\u0016J\u0018\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0015H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006%"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/activity/OpenAPIActivity$EventListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/pudutech/robot/opensdk/activity/OpenAPIActivity$EventViewHolder;", "context", "Landroid/content/Context;", "event_list", "Landroidx/recyclerview/widget/RecyclerView;", "(Landroid/content/Context;Landroidx/recyclerview/widget/RecyclerView;)V", "()V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "data", "", "", "getEvent_list", "()Landroidx/recyclerview/widget/RecyclerView;", "setEvent_list", "(Landroidx/recyclerview/widget/RecyclerView;)V", "selectedItem", "", "getSelectedItem", "()I", "setSelectedItem", "(I)V", "addEvent", "", "event", "getItemCount", "onBindViewHolder", "holder", RequestParameters.POSITION, "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class EventListAdapter extends RecyclerView.Adapter<EventViewHolder> {
        public Context context;
        private List<String> data;
        public RecyclerView event_list;
        private int selectedItem;

        public EventListAdapter() {
            this.data = new ArrayList();
        }

        public final int getSelectedItem() {
            return this.selectedItem;
        }

        public final void setSelectedItem(int i) {
            this.selectedItem = i;
        }

        public final Context getContext() {
            Context context = this.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            return context;
        }

        public final void setContext(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "<set-?>");
            this.context = context;
        }

        public final RecyclerView getEvent_list() {
            RecyclerView recyclerView = this.event_list;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("event_list");
            }
            return recyclerView;
        }

        public final void setEvent_list(RecyclerView recyclerView) {
            Intrinsics.checkParameterIsNotNull(recyclerView, "<set-?>");
            this.event_list = recyclerView;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public EventListAdapter(Context context, RecyclerView event_list) {
            this();
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(event_list, "event_list");
            this.context = context;
            this.event_list = event_list;
            event_list.setLayoutManager(new LinearLayoutManager(context));
            event_list.setAdapter(this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Intrinsics.checkParameterIsNotNull(parent, "parent");
            Context context = this.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            View view = LayoutInflater.from(context).inflate(C5723R.layout.mqtt_event_list, parent, false);
            Intrinsics.checkExpressionValueIsNotNull(view, "view");
            return new EventViewHolder(view);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(EventViewHolder holder, int position) {
            Intrinsics.checkParameterIsNotNull(holder, "holder");
            if (position < 0 || position >= this.data.size()) {
                return;
            }
            holder.getEvent().setText(this.data.get(position));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.data.size();
        }

        public final void addEvent(String event) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            this.data.add(0, event);
            if (this.data.size() > 10) {
                this.data.remove(r3.size() - 1);
            }
            notifyDataSetChanged();
        }
    }

    /* compiled from: OpenAPIActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/activity/OpenAPIActivity$EventViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "event", "Landroid/widget/TextView;", "getEvent$robot_open_sdk_release", "()Landroid/widget/TextView;", "setEvent$robot_open_sdk_release", "(Landroid/widget/TextView;)V", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class EventViewHolder extends RecyclerView.ViewHolder {
        private TextView event;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EventViewHolder(View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "view");
            View findViewById = view.findViewById(C5723R.id.textView_event);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.textView_event)");
            this.event = (TextView) findViewById;
        }

        /* renamed from: getEvent$robot_open_sdk_release, reason: from getter */
        public final TextView getEvent() {
            return this.event;
        }

        public final void setEvent$robot_open_sdk_release(TextView textView) {
            Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
            this.event = textView;
        }
    }

    /* compiled from: OpenAPIActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u0005¢\u0006\u0002\u0010\bJ\u001e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u0011J\b\u0010!\u001a\u00020\u0017H\u0016J\u0018\u0010\"\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0017H\u0016J\u0018\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u0017H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR&\u0010\r\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u000f0\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006)"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/activity/OpenAPIActivity$MQTTMsgListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/pudutech/robot/opensdk/activity/OpenAPIActivity$MQTTMsgViewHolder;", "context", "Landroid/content/Context;", "event_list", "Landroidx/recyclerview/widget/RecyclerView;", "(Landroid/content/Context;Landroidx/recyclerview/widget/RecyclerView;)V", "()V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "data", "", "Lkotlin/Triple;", "", "", "getEvent_list", "()Landroidx/recyclerview/widget/RecyclerView;", "setEvent_list", "(Landroidx/recyclerview/widget/RecyclerView;)V", "selectedItem", "", "getSelectedItem", "()I", "setSelectedItem", "(I)V", TmpConstant.GROUP_OP_ADD, "", "isSend", "topic", MqttServiceConstants.PAYLOAD, "getItemCount", "onBindViewHolder", "holder", RequestParameters.POSITION, "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class MQTTMsgListAdapter extends RecyclerView.Adapter<MQTTMsgViewHolder> {
        public Context context;
        private List<Triple<Boolean, String, String>> data;
        public RecyclerView event_list;
        private int selectedItem;

        public MQTTMsgListAdapter() {
            this.data = new ArrayList();
        }

        public final int getSelectedItem() {
            return this.selectedItem;
        }

        public final void setSelectedItem(int i) {
            this.selectedItem = i;
        }

        public final Context getContext() {
            Context context = this.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            return context;
        }

        public final void setContext(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "<set-?>");
            this.context = context;
        }

        public final RecyclerView getEvent_list() {
            RecyclerView recyclerView = this.event_list;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("event_list");
            }
            return recyclerView;
        }

        public final void setEvent_list(RecyclerView recyclerView) {
            Intrinsics.checkParameterIsNotNull(recyclerView, "<set-?>");
            this.event_list = recyclerView;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public MQTTMsgListAdapter(Context context, RecyclerView event_list) {
            this();
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(event_list, "event_list");
            this.context = context;
            this.event_list = event_list;
            event_list.setLayoutManager(new LinearLayoutManager(context));
            event_list.setAdapter(this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public MQTTMsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Intrinsics.checkParameterIsNotNull(parent, "parent");
            Context context = this.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            View view = LayoutInflater.from(context).inflate(C5723R.layout.mqtt_message_list, parent, false);
            Intrinsics.checkExpressionValueIsNotNull(view, "view");
            return new MQTTMsgViewHolder(view);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(MQTTMsgViewHolder holder, int position) {
            Intrinsics.checkParameterIsNotNull(holder, "holder");
            if (position < 0 || position >= this.data.size()) {
                return;
            }
            holder.getTopic().setText(this.data.get(position).getSecond());
            if (this.data.get(position).getFirst().booleanValue()) {
                holder.getTopic().setTextColor(Color.parseColor("#33cc33"));
            } else {
                holder.getTopic().setTextColor(Color.parseColor("#999933"));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.data.size();
        }

        public final void add(boolean isSend, String topic, String payload) {
            Intrinsics.checkParameterIsNotNull(topic, "topic");
            Intrinsics.checkParameterIsNotNull(payload, "payload");
            this.data.add(0, new Triple<>(Boolean.valueOf(isSend), topic, payload));
            if (this.data.size() > 10) {
                this.data.remove(r3.size() - 1);
            }
            notifyDataSetChanged();
        }
    }

    /* compiled from: OpenAPIActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/activity/OpenAPIActivity$MQTTMsgViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "topic", "Landroid/widget/TextView;", "getTopic$robot_open_sdk_release", "()Landroid/widget/TextView;", "setTopic$robot_open_sdk_release", "(Landroid/widget/TextView;)V", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class MQTTMsgViewHolder extends RecyclerView.ViewHolder {
        private TextView topic;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MQTTMsgViewHolder(View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "view");
            View findViewById = view.findViewById(C5723R.id.textView_topic);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.textView_topic)");
            this.topic = (TextView) findViewById;
        }

        /* renamed from: getTopic$robot_open_sdk_release, reason: from getter */
        public final TextView getTopic() {
            return this.topic;
        }

        public final void setTopic$robot_open_sdk_release(TextView textView) {
            Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
            this.topic = textView;
        }
    }
}
