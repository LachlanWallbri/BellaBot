package com.pudutech.bumblebee.robot_ui.database.interf;

import com.pudutech.bumblebee.business.ims.config.CallPoint;
import com.pudutech.bumblebee.robot_ui.bean.Beeper;
import com.pudutech.bumblebee.robot_ui.bean.BeeperCard;
import java.util.ArrayList;
import kotlin.Metadata;

/* compiled from: IDatabaseInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0012\u0010\u0005\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\n\u001a\u00020\u0003H&J\u0014\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0014\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\u0007H&J\u001c\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010j\n\u0012\u0004\u0012\u00020\u000e\u0018\u0001`\u0011H&J&\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010j\n\u0012\u0004\u0012\u00020\u000e\u0018\u0001`\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007H&J\u001c\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00020\f\u0018\u00010\u0010j\n\u0012\u0004\u0012\u00020\f\u0018\u0001`\u0011H&J\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\t\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\u0016\u001a\u00020\u0003H&J\u0012\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\fH&J\u0012\u0010\u0019\u001a\u00020\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u000eH&J$\u0010\u001b\u001a\u00020\u00032\u001a\u0010\u001c\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010j\n\u0012\u0004\u0012\u00020\u000e\u0018\u0001`\u0011H&J$\u0010\u001d\u001a\u00020\u00032\u001a\u0010\u001e\u001a\u0016\u0012\u0004\u0012\u00020\f\u0018\u00010\u0010j\n\u0012\u0004\u0012\u00020\f\u0018\u0001`\u0011H&¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/database/interf/IDatabaseInterface;", "", "deleteAllBeeperCardList", "", "deleteAllBeeperList", "deleteBeeper", "beeperMac", "", "deleteBeeperCard", "rfid", "init", "queryBeeper", "Lcom/pudutech/bumblebee/robot_ui/bean/Beeper;", "queryBeeperCard", "Lcom/pudutech/bumblebee/robot_ui/bean/BeeperCard;", "queryBeeperCardList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "callPointName", "queryBeeperList", "queryCallPoint", "Lcom/pudutech/bumblebee/business/ims/config/CallPoint;", "release", "saveBeeper", "beeper", "saveBeeperCard", "beeperCard", "saveBeeperCardList", "beeperCardList", "saveBeeperList", "beeperList", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public interface IDatabaseInterface {
    void deleteAllBeeperCardList();

    void deleteAllBeeperList();

    void deleteBeeper(String beeperMac);

    void deleteBeeperCard(String rfid);

    void init();

    Beeper queryBeeper(String beeperMac);

    BeeperCard queryBeeperCard(String rfid);

    ArrayList<BeeperCard> queryBeeperCardList();

    ArrayList<BeeperCard> queryBeeperCardList(String callPointName);

    ArrayList<Beeper> queryBeeperList();

    CallPoint queryCallPoint(String rfid);

    void release();

    void saveBeeper(Beeper beeper);

    void saveBeeperCard(BeeperCard beeperCard);

    void saveBeeperCardList(ArrayList<BeeperCard> beeperCardList);

    void saveBeeperList(ArrayList<Beeper> beeperList);
}
