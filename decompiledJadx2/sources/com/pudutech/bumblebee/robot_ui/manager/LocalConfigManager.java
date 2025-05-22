package com.pudutech.bumblebee.robot_ui.manager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pudutech.bumblebee.business.ims.config.CallPoint;
import com.pudutech.bumblebee.business.ims.lora.Channel;
import com.pudutech.bumblebee.business.ims.lora.LoRaChannelManager2;
import com.pudutech.bumblebee.robot_ui.bean.Beeper;
import com.pudutech.bumblebee.robot_ui.bean.BeeperCard;
import com.pudutech.bumblebee.robot_ui.bean.CentralControl;
import com.pudutech.bumblebee.robot_ui.database.DatabaseManagerFactory;
import com.pudutech.bumblebee.robot_ui.util.FileUtil;
import java.io.File;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: LocalConfigManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\u0018\u0000 12\u00020\u0001:\u0003123B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u001a\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\f\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\f\u0018\u0001`\u000fJ\u001a\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\u0011\u0018\u0001`\u000fJ\u001a\u0010\u0012\u001a\u0016\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\u0013\u0018\u0001`\u000fJ\b\u0010\u0014\u001a\u0004\u0018\u00010\fJ\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u001a\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\u0018\u0018\u0001`\u000fJ\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aJ\u0006\u0010\u001b\u001a\u00020\u001cJ\b\u0010\u001d\u001a\u00020\nH\u0002J\b\u0010\u001e\u001a\u00020\nH\u0002J\u0010\u0010\u001f\u001a\u00020\u001c2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\b\u0010 \u001a\u00020\nH\u0002J\b\u0010!\u001a\u00020\nH\u0002J\u0006\u0010\"\u001a\u00020\nJ\u0006\u0010#\u001a\u00020\nJ\b\u0010$\u001a\u00020\nH\u0002J\b\u0010%\u001a\u00020\nH\u0002J\"\u0010&\u001a\u00020\n2\u001a\u0010'\u001a\u0016\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\u0018\u0018\u0001`\u000fJ\"\u0010(\u001a\u00020\n2\u001a\u0010)\u001a\u0016\u0012\u0004\u0012\u00020\f\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\f\u0018\u0001`\u000fJ\"\u0010*\u001a\u00020\n2\u001a\u0010+\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\u0011\u0018\u0001`\u000fJ\"\u0010,\u001a\u00020\n2\u001a\u0010-\u001a\u0016\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\u0013\u0018\u0001`\u000fJ\u0010\u0010.\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0010\u0010/\u001a\u00020\n2\b\u00100\u001a\u0004\u0018\u00010\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00064"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LocalConfigManager;", "", "()V", "gson", "Lcom/google/gson/Gson;", "recycleRobotCentralControlConfig", "Lcom/pudutech/bumblebee/robot_ui/manager/LocalConfigManager$RecycleRobotCentralControlConfig;", "recycleRobotConfig", "Lcom/pudutech/bumblebee/robot_ui/manager/LocalConfigManager$RecycleRobotConfig;", "addCentralControl", "", "centralControl", "Lcom/pudutech/bumblebee/robot_ui/bean/CentralControl;", "getAddedDeviceCentralControlList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getBeeperCardList", "Lcom/pudutech/bumblebee/robot_ui/bean/BeeperCard;", "getBeeperList", "Lcom/pudutech/bumblebee/robot_ui/bean/Beeper;", "getCentralControl", "getChannel", "Lcom/pudutech/bumblebee/business/ims/lora/Channel;", "getTaskPartitionTable", "Lcom/pudutech/bumblebee/business/ims/config/CallPoint;", "getTaskPartitionTableFileMD5", "", "hasCentralControl", "", "initRecycleRobotCentralControlConfig", "initRecycleRobotConfig", "isAddedDeviceCentralControl", "readRecycleRobotCentralControlConfig", "readRecycleRobotConfig", "resetAll", "resetChannel", "saveRecycleRobotCentralControlConfig", "saveRecycleRobotConfig", "saveTaskPartitionTable", "callPointList", "setAddedDeviceCentralControlList", "centralControlList", "setBeeperCardList", "beeperCardList", "setBeeperList", "beeperList", "setCentralControl", "setChannel", "channel", "Companion", "RecycleRobotCentralControlConfig", "RecycleRobotConfig", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class LocalConfigManager {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<LocalConfigManager>() { // from class: com.pudutech.bumblebee.robot_ui.manager.LocalConfigManager$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LocalConfigManager invoke() {
            return new LocalConfigManager(null);
        }
    });
    private static final String RECYCLE_ROBOT_CENTRAL_CONTROL_CONFIG_PATH = "/sdcard/pudu/config/recycle_robot_central_control.config";
    private static final String RECYCLE_ROBOT_CONFIG_PATH = "/sdcard/pudu/config/recycle_robot.config";
    private static final String TASK_PARTITION_TABLE_CONFIG_PATH = "/sdcard/pudu/config/task_partition_table.config";
    private static volatile boolean isMapSyncedCompleteLy;
    private final Gson gson;
    private RecycleRobotCentralControlConfig recycleRobotCentralControlConfig;
    private RecycleRobotConfig recycleRobotConfig;

    private LocalConfigManager() {
        this.gson = new Gson();
        readRecycleRobotConfig();
    }

    public /* synthetic */ LocalConfigManager(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: LocalConfigManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\t\u001a\u00020\n8\u0002X\u0083T¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\n8\u0002X\u0083T¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\n8\u0002X\u0083T¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LocalConfigManager$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/bumblebee/robot_ui/manager/LocalConfigManager;", "getINSTANCE", "()Lcom/pudutech/bumblebee/robot_ui/manager/LocalConfigManager;", "INSTANCE$delegate", "Lkotlin/Lazy;", "RECYCLE_ROBOT_CENTRAL_CONTROL_CONFIG_PATH", "", "RECYCLE_ROBOT_CONFIG_PATH", "TASK_PARTITION_TABLE_CONFIG_PATH", "isMapSyncedCompleteLy", "", "()Z", "setMapSyncedCompleteLy", "(Z)V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        public final LocalConfigManager getINSTANCE() {
            Lazy lazy = LocalConfigManager.INSTANCE$delegate;
            Companion companion = LocalConfigManager.INSTANCE;
            return (LocalConfigManager) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isMapSyncedCompleteLy() {
            return LocalConfigManager.isMapSyncedCompleteLy;
        }

        public final void setMapSyncedCompleteLy(boolean z) {
            LocalConfigManager.isMapSyncedCompleteLy = z;
        }
    }

    public final void setCentralControl(CentralControl centralControl) {
        initRecycleRobotCentralControlConfig();
        RecycleRobotCentralControlConfig recycleRobotCentralControlConfig = this.recycleRobotCentralControlConfig;
        if (recycleRobotCentralControlConfig != null) {
            recycleRobotCentralControlConfig.setCentralControl(centralControl);
        }
        saveRecycleRobotCentralControlConfig();
    }

    public final CentralControl getCentralControl() {
        readRecycleRobotCentralControlConfig();
        RecycleRobotCentralControlConfig recycleRobotCentralControlConfig = this.recycleRobotCentralControlConfig;
        if (recycleRobotCentralControlConfig == null || recycleRobotCentralControlConfig == null) {
            return null;
        }
        return recycleRobotCentralControlConfig.getCentralControl();
    }

    public final boolean hasCentralControl() {
        return getCentralControl() != null;
    }

    public final void addCentralControl(CentralControl centralControl) {
        if (centralControl == null) {
            return;
        }
        ArrayList<CentralControl> addedDeviceCentralControlList = getAddedDeviceCentralControlList();
        if (addedDeviceCentralControlList == null) {
            addedDeviceCentralControlList = new ArrayList<>();
        }
        if (addedDeviceCentralControlList.contains(centralControl)) {
            return;
        }
        addedDeviceCentralControlList.add(centralControl);
        setAddedDeviceCentralControlList(addedDeviceCentralControlList);
    }

    public final boolean isAddedDeviceCentralControl(CentralControl centralControl) {
        if (centralControl == null) {
            return false;
        }
        ArrayList<CentralControl> addedDeviceCentralControlList = getAddedDeviceCentralControlList();
        ArrayList<CentralControl> arrayList = addedDeviceCentralControlList;
        if (arrayList == null || arrayList.isEmpty()) {
            return false;
        }
        return addedDeviceCentralControlList.contains(centralControl);
    }

    public final void setAddedDeviceCentralControlList(ArrayList<CentralControl> centralControlList) {
        initRecycleRobotCentralControlConfig();
        RecycleRobotCentralControlConfig recycleRobotCentralControlConfig = this.recycleRobotCentralControlConfig;
        if (recycleRobotCentralControlConfig != null) {
            recycleRobotCentralControlConfig.setAddedDeviceCentralControlList(centralControlList);
        }
        saveRecycleRobotCentralControlConfig();
    }

    public final ArrayList<CentralControl> getAddedDeviceCentralControlList() {
        readRecycleRobotCentralControlConfig();
        RecycleRobotCentralControlConfig recycleRobotCentralControlConfig = this.recycleRobotCentralControlConfig;
        if (recycleRobotCentralControlConfig == null || recycleRobotCentralControlConfig == null) {
            return null;
        }
        return recycleRobotCentralControlConfig.getAddedDeviceCentralControlList();
    }

    public final void setChannel(Channel channel) {
        initRecycleRobotConfig();
        RecycleRobotConfig recycleRobotConfig = this.recycleRobotConfig;
        if (recycleRobotConfig != null) {
            recycleRobotConfig.setChannel(channel);
        }
        saveRecycleRobotConfig();
    }

    public final Channel getChannel() {
        Channel channel;
        readRecycleRobotConfig();
        RecycleRobotConfig recycleRobotConfig = this.recycleRobotConfig;
        if (recycleRobotConfig == null) {
            return null;
        }
        return (recycleRobotConfig == null || (channel = recycleRobotConfig.getChannel()) == null) ? LoRaChannelManager2.INSTANCE.getINSTANCE().getCurrentChannel() : channel;
    }

    public final void setBeeperList(ArrayList<Beeper> beeperList) {
        initRecycleRobotConfig();
        RecycleRobotConfig recycleRobotConfig = this.recycleRobotConfig;
        if (recycleRobotConfig != null) {
            recycleRobotConfig.setBeeperList(beeperList);
        }
        saveRecycleRobotConfig();
    }

    public final ArrayList<Beeper> getBeeperList() {
        readRecycleRobotConfig();
        RecycleRobotConfig recycleRobotConfig = this.recycleRobotConfig;
        if (recycleRobotConfig == null || recycleRobotConfig == null) {
            return null;
        }
        return recycleRobotConfig.getBeeperList();
    }

    public final void setBeeperCardList(ArrayList<BeeperCard> beeperCardList) {
        initRecycleRobotConfig();
        RecycleRobotConfig recycleRobotConfig = this.recycleRobotConfig;
        if (recycleRobotConfig != null) {
            recycleRobotConfig.setBeeperCardList(beeperCardList);
        }
        saveRecycleRobotConfig();
    }

    public final ArrayList<BeeperCard> getBeeperCardList() {
        readRecycleRobotConfig();
        RecycleRobotConfig recycleRobotConfig = this.recycleRobotConfig;
        if (recycleRobotConfig == null || recycleRobotConfig == null) {
            return null;
        }
        return recycleRobotConfig.getBeeperCardList();
    }

    private final void initRecycleRobotConfig() {
        readRecycleRobotConfig();
        if (this.recycleRobotConfig == null) {
            this.recycleRobotConfig = new RecycleRobotConfig();
        }
    }

    private final void initRecycleRobotCentralControlConfig() {
        readRecycleRobotCentralControlConfig();
        if (this.recycleRobotCentralControlConfig == null) {
            this.recycleRobotCentralControlConfig = new RecycleRobotCentralControlConfig();
        }
    }

    private final void saveRecycleRobotConfig() {
        try {
            FileUtil.INSTANCE.saveContentToFile(RECYCLE_ROBOT_CONFIG_PATH, this.recycleRobotConfig == null ? null : this.gson.toJson(this.recycleRobotConfig), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void saveRecycleRobotCentralControlConfig() {
        try {
            String json = this.recycleRobotCentralControlConfig == null ? null : this.gson.toJson(this.recycleRobotCentralControlConfig);
            FileUtil fileUtil = FileUtil.INSTANCE;
            if (json == null) {
                json = "";
            }
            fileUtil.saveContentToFile(RECYCLE_ROBOT_CENTRAL_CONTROL_CONFIG_PATH, json, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void readRecycleRobotConfig() {
        Channel channel;
        File file;
        try {
            try {
                file = new File(RECYCLE_ROBOT_CONFIG_PATH);
            } catch (Exception e) {
                e.printStackTrace();
                RecycleRobotConfig recycleRobotConfig = this.recycleRobotConfig;
                channel = recycleRobotConfig != null ? recycleRobotConfig.getChannel() : null;
                if (channel == null) {
                    channel = LoRaChannelManager2.INSTANCE.getINSTANCE().getDefaultChannel();
                    RecycleRobotConfig recycleRobotConfig2 = this.recycleRobotConfig;
                    if (recycleRobotConfig2 != null) {
                        recycleRobotConfig2.setChannel(channel);
                    }
                    saveRecycleRobotConfig();
                }
                if (LoRaChannelManager2.INSTANCE.getINSTANCE().getCurrentChannel() != null) {
                    return;
                }
            }
            if (file.isFile() && file.exists()) {
                String readContentFromFile = FileUtil.INSTANCE.readContentFromFile(file.getPath());
                String str = readContentFromFile;
                if (str == null || str.length() == 0) {
                    RecycleRobotConfig recycleRobotConfig3 = this.recycleRobotConfig;
                    channel = recycleRobotConfig3 != null ? recycleRobotConfig3.getChannel() : null;
                    if (channel == null) {
                        channel = LoRaChannelManager2.INSTANCE.getINSTANCE().getDefaultChannel();
                        RecycleRobotConfig recycleRobotConfig4 = this.recycleRobotConfig;
                        if (recycleRobotConfig4 != null) {
                            recycleRobotConfig4.setChannel(channel);
                        }
                        saveRecycleRobotConfig();
                    }
                    if (LoRaChannelManager2.INSTANCE.getINSTANCE().getCurrentChannel() == null) {
                        LoRaChannelManager2.INSTANCE.getINSTANCE().setCurrentChannel(channel);
                        return;
                    }
                    return;
                }
                this.recycleRobotConfig = (RecycleRobotConfig) this.gson.fromJson(readContentFromFile, RecycleRobotConfig.class);
                RecycleRobotConfig recycleRobotConfig5 = this.recycleRobotConfig;
                channel = recycleRobotConfig5 != null ? recycleRobotConfig5.getChannel() : null;
                if (channel == null) {
                    channel = LoRaChannelManager2.INSTANCE.getINSTANCE().getDefaultChannel();
                    RecycleRobotConfig recycleRobotConfig6 = this.recycleRobotConfig;
                    if (recycleRobotConfig6 != null) {
                        recycleRobotConfig6.setChannel(channel);
                    }
                    saveRecycleRobotConfig();
                }
                if (LoRaChannelManager2.INSTANCE.getINSTANCE().getCurrentChannel() != null) {
                    return;
                }
                LoRaChannelManager2.INSTANCE.getINSTANCE().setCurrentChannel(channel);
                return;
            }
            RecycleRobotConfig recycleRobotConfig7 = this.recycleRobotConfig;
            channel = recycleRobotConfig7 != null ? recycleRobotConfig7.getChannel() : null;
            if (channel == null) {
                channel = LoRaChannelManager2.INSTANCE.getINSTANCE().getDefaultChannel();
                RecycleRobotConfig recycleRobotConfig8 = this.recycleRobotConfig;
                if (recycleRobotConfig8 != null) {
                    recycleRobotConfig8.setChannel(channel);
                }
                saveRecycleRobotConfig();
            }
            if (LoRaChannelManager2.INSTANCE.getINSTANCE().getCurrentChannel() == null) {
                LoRaChannelManager2.INSTANCE.getINSTANCE().setCurrentChannel(channel);
            }
        } catch (Throwable th) {
            RecycleRobotConfig recycleRobotConfig9 = this.recycleRobotConfig;
            channel = recycleRobotConfig9 != null ? recycleRobotConfig9.getChannel() : null;
            if (channel == null) {
                channel = LoRaChannelManager2.INSTANCE.getINSTANCE().getDefaultChannel();
                RecycleRobotConfig recycleRobotConfig10 = this.recycleRobotConfig;
                if (recycleRobotConfig10 != null) {
                    recycleRobotConfig10.setChannel(channel);
                }
                saveRecycleRobotConfig();
            }
            if (LoRaChannelManager2.INSTANCE.getINSTANCE().getCurrentChannel() == null) {
                LoRaChannelManager2.INSTANCE.getINSTANCE().setCurrentChannel(channel);
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0030 A[Catch: Exception -> 0x003e, TRY_LEAVE, TryCatch #0 {Exception -> 0x003e, blocks: (B:2:0x0000, B:4:0x000d, B:9:0x0014, B:11:0x0023, B:17:0x0030), top: B:1:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void readRecycleRobotCentralControlConfig() {
        boolean z;
        try {
            File file = new File(RECYCLE_ROBOT_CENTRAL_CONTROL_CONFIG_PATH);
            if (file.isFile() && file.exists()) {
                String readContentFromFile = FileUtil.INSTANCE.readContentFromFile(file.getPath());
                String str = readContentFromFile;
                if (str != null && str.length() != 0) {
                    z = false;
                    if (z) {
                        this.recycleRobotCentralControlConfig = (RecycleRobotCentralControlConfig) this.gson.fromJson(readContentFromFile, RecycleRobotCentralControlConfig.class);
                        return;
                    }
                    return;
                }
                z = true;
                if (z) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void resetChannel() {
        initRecycleRobotConfig();
        RecycleRobotConfig recycleRobotConfig = this.recycleRobotConfig;
        if (recycleRobotConfig != null) {
            recycleRobotConfig.setChannel(LoRaChannelManager2.INSTANCE.getINSTANCE().getDefaultChannel());
        }
        saveRecycleRobotConfig();
    }

    public final void resetAll() {
        this.recycleRobotConfig = (RecycleRobotConfig) null;
        saveRecycleRobotConfig();
        this.recycleRobotCentralControlConfig = (RecycleRobotCentralControlConfig) null;
        saveRecycleRobotCentralControlConfig();
        DatabaseManagerFactory.INSTANCE.getDatabaseManager().deleteAllBeeperList();
        DatabaseManagerFactory.INSTANCE.getDatabaseManager().deleteAllBeeperCardList();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0014 A[Catch: Exception -> 0x0027, TryCatch #0 {Exception -> 0x0027, blocks: (B:2:0x0000, B:4:0x0006, B:10:0x001a, B:13:0x0023, B:18:0x0014), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void saveTaskPartitionTable(ArrayList<CallPoint> callPointList) {
        boolean z;
        String json;
        try {
            ArrayList<CallPoint> arrayList = callPointList;
            if (arrayList != null && !arrayList.isEmpty()) {
                z = false;
                json = !z ? null : this.gson.toJson(callPointList);
                FileUtil fileUtil = FileUtil.INSTANCE;
                if (json != null) {
                    json = "";
                }
                fileUtil.saveContentToFile(TASK_PARTITION_TABLE_CONFIG_PATH, json, false);
            }
            z = true;
            if (!z) {
            }
            FileUtil fileUtil2 = FileUtil.INSTANCE;
            if (json != null) {
            }
            fileUtil2.saveContentToFile(TASK_PARTITION_TABLE_CONFIG_PATH, json, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0030 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0031 A[Catch: Exception -> 0x0044, TRY_LEAVE, TryCatch #0 {Exception -> 0x0044, blocks: (B:3:0x0001, B:5:0x000e, B:8:0x0015, B:10:0x0024, B:17:0x0031), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final ArrayList<CallPoint> getTaskPartitionTable() {
        boolean z;
        try {
            File file = new File(TASK_PARTITION_TABLE_CONFIG_PATH);
            if (file.isFile() && file.exists()) {
                String readContentFromFile = FileUtil.INSTANCE.readContentFromFile(file.getPath());
                String str = readContentFromFile;
                if (str != null && str.length() != 0) {
                    z = false;
                    if (!z) {
                        return null;
                    }
                    return (ArrayList) this.gson.fromJson(readContentFromFile, new TypeToken<ArrayList<CallPoint>>() { // from class: com.pudutech.bumblebee.robot_ui.manager.LocalConfigManager$getTaskPartitionTable$typeToken$1
                    }.getType());
                }
                z = true;
                if (!z) {
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final String getTaskPartitionTableFileMD5() {
        try {
            File file = new File(TASK_PARTITION_TABLE_CONFIG_PATH);
            if (file.isFile() && file.exists()) {
                return FileUtil.INSTANCE.getFileMD5(file);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LocalConfigManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R.\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR.\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\f\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00020\f\u0018\u0001`\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\nR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LocalConfigManager$RecycleRobotConfig;", "", "()V", "beeperCardList", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/robot_ui/bean/BeeperCard;", "Lkotlin/collections/ArrayList;", "getBeeperCardList", "()Ljava/util/ArrayList;", "setBeeperCardList", "(Ljava/util/ArrayList;)V", "beeperList", "Lcom/pudutech/bumblebee/robot_ui/bean/Beeper;", "getBeeperList", "setBeeperList", "channel", "Lcom/pudutech/bumblebee/business/ims/lora/Channel;", "getChannel", "()Lcom/pudutech/bumblebee/business/ims/lora/Channel;", "setChannel", "(Lcom/pudutech/bumblebee/business/ims/lora/Channel;)V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class RecycleRobotConfig {
        private ArrayList<BeeperCard> beeperCardList;
        private ArrayList<Beeper> beeperList;
        private Channel channel;

        public final Channel getChannel() {
            return this.channel;
        }

        public final void setChannel(Channel channel) {
            this.channel = channel;
        }

        public final ArrayList<Beeper> getBeeperList() {
            return this.beeperList;
        }

        public final void setBeeperList(ArrayList<Beeper> arrayList) {
            this.beeperList = arrayList;
        }

        public final ArrayList<BeeperCard> getBeeperCardList() {
            return this.beeperCardList;
        }

        public final void setBeeperCardList(ArrayList<BeeperCard> arrayList) {
            this.beeperCardList = arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LocalConfigManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R.\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LocalConfigManager$RecycleRobotCentralControlConfig;", "", "()V", "addedDeviceCentralControlList", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/robot_ui/bean/CentralControl;", "Lkotlin/collections/ArrayList;", "getAddedDeviceCentralControlList", "()Ljava/util/ArrayList;", "setAddedDeviceCentralControlList", "(Ljava/util/ArrayList;)V", "centralControl", "getCentralControl", "()Lcom/pudutech/bumblebee/robot_ui/bean/CentralControl;", "setCentralControl", "(Lcom/pudutech/bumblebee/robot_ui/bean/CentralControl;)V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class RecycleRobotCentralControlConfig {
        private ArrayList<CentralControl> addedDeviceCentralControlList;
        private CentralControl centralControl;

        public final CentralControl getCentralControl() {
            return this.centralControl;
        }

        public final void setCentralControl(CentralControl centralControl) {
            this.centralControl = centralControl;
        }

        public final ArrayList<CentralControl> getAddedDeviceCentralControlList() {
            return this.addedDeviceCentralControlList;
        }

        public final void setAddedDeviceCentralControlList(ArrayList<CentralControl> arrayList) {
            this.addedDeviceCentralControlList = arrayList;
        }
    }
}
