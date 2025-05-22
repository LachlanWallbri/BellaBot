package com.pudutech.event_tracking.bean;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: tracking_event.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u0007\b\t\n\u000b\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0006\r\u000e\u000f\u0010\u0011\u0012¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/event_tracking/bean/Event;", "", AIUIConstant.KEY_TAG, "", "(Ljava/lang/String;)V", "getTag", "()Ljava/lang/String;", "Click", TypedValues.Custom.NAME, "OnCreate", "OnStop", "PagePause", "PageResume", "Lcom/pudutech/event_tracking/bean/Event$Click;", "Lcom/pudutech/event_tracking/bean/Event$PageResume;", "Lcom/pudutech/event_tracking/bean/Event$PagePause;", "Lcom/pudutech/event_tracking/bean/Event$OnCreate;", "Lcom/pudutech/event_tracking/bean/Event$OnStop;", "Lcom/pudutech/event_tracking/bean/Event$Custom;", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public abstract class Event {
    private final String tag;

    private Event(String str) {
        this.tag = str;
    }

    public /* synthetic */ Event(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    public final String getTag() {
        return this.tag;
    }

    /* compiled from: tracking_event.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/event_tracking/bean/Event$Click;", "Lcom/pudutech/event_tracking/bean/Event;", "()V", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Click extends Event {
        public static final Click INSTANCE = new Click();

        private Click() {
            super("Click", null);
        }
    }

    /* compiled from: tracking_event.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/event_tracking/bean/Event$PageResume;", "Lcom/pudutech/event_tracking/bean/Event;", "()V", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class PageResume extends Event {
        public static final PageResume INSTANCE = new PageResume();

        private PageResume() {
            super("Resume", null);
        }
    }

    /* compiled from: tracking_event.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/event_tracking/bean/Event$PagePause;", "Lcom/pudutech/event_tracking/bean/Event;", "()V", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class PagePause extends Event {
        public static final PagePause INSTANCE = new PagePause();

        private PagePause() {
            super("Pause", null);
        }
    }

    /* compiled from: tracking_event.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/event_tracking/bean/Event$OnCreate;", "Lcom/pudutech/event_tracking/bean/Event;", "()V", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class OnCreate extends Event {
        public static final OnCreate INSTANCE = new OnCreate();

        private OnCreate() {
            super("Create", null);
        }
    }

    /* compiled from: tracking_event.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/event_tracking/bean/Event$OnStop;", "Lcom/pudutech/event_tracking/bean/Event;", "()V", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class OnStop extends Event {
        public static final OnStop INSTANCE = new OnStop();

        private OnStop() {
            super("OnStop", null);
        }
    }

    /* compiled from: tracking_event.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/event_tracking/bean/Event$Custom;", "Lcom/pudutech/event_tracking/bean/Event;", "()V", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Custom extends Event {
        public static final Custom INSTANCE = new Custom();

        private Custom() {
            super(TypedValues.Custom.NAME, null);
        }
    }
}
