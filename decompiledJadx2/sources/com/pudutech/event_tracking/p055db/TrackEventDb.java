package com.pudutech.event_tracking.p055db;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.pudutech.event_tracking.p055db.fix.NoLocalFrameworkSQLiteOpenHelperFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TrackEventDb.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/event_tracking/db/TrackEventDb;", "Landroidx/room/RoomDatabase;", "()V", "trackEventDao", "Lcom/pudutech/event_tracking/db/TrackEventDao;", "Companion", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public abstract class TrackEventDb extends RoomDatabase {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static volatile TrackEventDb INSTANCE;

    public abstract TrackEventDao trackEventDao();

    /* compiled from: TrackEventDb.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/event_tracking/db/TrackEventDb$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/event_tracking/db/TrackEventDb;", "buildDatabase", "context", "Landroid/content/Context;", "getInstance", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TrackEventDb getInstance(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            TrackEventDb trackEventDb = TrackEventDb.INSTANCE;
            if (trackEventDb == null) {
                synchronized (this) {
                    trackEventDb = TrackEventDb.INSTANCE;
                    if (trackEventDb == null) {
                        TrackEventDb buildDatabase = TrackEventDb.INSTANCE.buildDatabase(context);
                        TrackEventDb.INSTANCE = buildDatabase;
                        trackEventDb = buildDatabase;
                    }
                }
            }
            return trackEventDb;
        }

        private final TrackEventDb buildDatabase(Context context) {
            RoomDatabase build = Room.databaseBuilder(context.getApplicationContext(), TrackEventDb.class, "TrackEvent.db").openHelperFactory(new NoLocalFrameworkSQLiteOpenHelperFactory()).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "Room.databaseBuilder(\n  …nHelperFactory()).build()");
            return (TrackEventDb) build;
        }
    }
}
