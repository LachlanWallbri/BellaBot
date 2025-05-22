package com.pudu.library.loracall.dao;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.library.loracall.LoRaClient;
import com.pudu.library.loracall.LoRaHelper;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppDatabase.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudu/library/loracall/dao/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "getTableDao", "Lcom/pudu/library/loracall/dao/TableBindDao;", "Companion", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class AppDatabase extends RoomDatabase {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static Pair<String, ? extends AppDatabase> mCache;

    public abstract TableBindDao getTableDao();

    /* compiled from: AppDatabase.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\tH\u0002J\b\u0010\f\u001a\u00020\u0004H\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudu/library/loracall/dao/AppDatabase$Companion;", "", "()V", "instance", "Lcom/pudu/library/loracall/dao/AppDatabase;", "getInstance", "()Lcom/pudu/library/loracall/dao/AppDatabase;", "mCache", "Lkotlin/Pair;", "", "buildDatabase", "mapName", "getAppDatabase", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AppDatabase getInstance() {
            return AppDatabase.INSTANCE.getAppDatabase();
        }

        private final AppDatabase getAppDatabase() {
            final String mapName = LoRaHelper.INSTANCE.getMapTable().getMapName();
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.dao.AppDatabase$Companion$getAppDatabase$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "地图：" + mapName;
                }
            }, 1, null);
            if (AppDatabase.mCache == null) {
                AppDatabase.mCache = TuplesKt.m3968to(mapName, buildDatabase(mapName));
                Pair pair = AppDatabase.mCache;
                if (pair == null) {
                    Intrinsics.throwNpe();
                }
                return (AppDatabase) pair.getSecond();
            }
            Pair pair2 = AppDatabase.mCache;
            if (Intrinsics.areEqual(pair2 != null ? (String) pair2.getFirst() : null, mapName)) {
                Pair pair3 = AppDatabase.mCache;
                if (pair3 == null) {
                    Intrinsics.throwNpe();
                }
                return (AppDatabase) pair3.getSecond();
            }
            AppDatabase.mCache = TuplesKt.m3968to(mapName, buildDatabase(mapName));
            Pair pair4 = AppDatabase.mCache;
            if (pair4 == null) {
                Intrinsics.throwNpe();
            }
            return (AppDatabase) pair4.getSecond();
        }

        private final AppDatabase buildDatabase(String mapName) {
            RoomDatabase.Builder databaseBuilder = Room.databaseBuilder(LoRaClient.INSTANCE.getInstance().getMContext$library_loracall_release(), AppDatabase.class, "table_" + mapName + "_match.db");
            Intrinsics.checkExpressionValueIsNotNull(databaseBuilder, "Room.databaseBuilder(\n  …}_match.db\"\n            )");
            databaseBuilder.allowMainThreadQueries();
            databaseBuilder.fallbackToDestructiveMigration();
            RoomDatabase build = databaseBuilder.build();
            Intrinsics.checkExpressionValueIsNotNull(build, "builder.build()");
            return (AppDatabase) build;
        }
    }
}
