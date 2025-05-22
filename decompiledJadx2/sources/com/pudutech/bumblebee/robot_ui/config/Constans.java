package com.pudutech.bumblebee.robot_ui.config;

import android.content.Context;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.business.movementInterface.SortType;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.TableBindItem;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.TableWatchBindItem;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.PalletCountHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.disinfect.baselib.util.MMKVManager;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: Constans.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000y\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0003\b\u008b\u0001\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\bM\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u001d\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0089\u0002\u001a\u00030\u008a\u0002J\b\u0010\u008b\u0002\u001a\u00030\u008a\u0002J\b\u0010\u008c\u0002\u001a\u00030¡\u0001J\b\u0010\u008d\u0002\u001a\u00030¡\u0001J\u0019\u0010\u008e\u0002\u001a\u00020\u00062\u0007\u0010\u008f\u0002\u001a\u00020\u00062\u0007\u0010\u0090\u0002\u001a\u00020\u0006J\b\u0010\u0091\u0002\u001a\u00030¡\u0001J\b\u0010\u0092\u0002\u001a\u00030¡\u0001J\b\u0010\u0093\u0002\u001a\u00030¡\u0001J\u0010\u0010\u0094\u0002\u001a\u000b\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0095\u0002J\u0010\u0010\u0096\u0002\u001a\u000b\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0095\u0002J\b\u0010\u0097\u0002\u001a\u00030¡\u0001J\b\u0010\u0098\u0002\u001a\u00030¡\u0001J\b\u0010\u0099\u0002\u001a\u00030¡\u0001J\u0010\u0010\u009a\u0002\u001a\u000b\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u009b\u0002J6\u0010\u009c\u0002\u001a1\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\u00060\u009d\u0002\u0018\u00010\u009d\u0002j\u001d\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u00060\u009d\u0002j\t\u0012\u0004\u0012\u00020\u0006`\u009e\u0002\u0018\u0001`\u009e\u0002J\u001d\u0010\u009f\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u009d\u0002j\u000b\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u009e\u0002J\u001f\u0010 \u0002\u001a\u001a\u0012\u0005\u0012\u00030¡\u0002\u0018\u00010\u009d\u0002j\f\u0012\u0005\u0012\u00030¡\u0002\u0018\u0001`\u009e\u0002J\u0007\u0010¢\u0002\u001a\u00020\u0006J\b\u0010£\u0002\u001a\u00030¡\u0001J\b\u0010¤\u0002\u001a\u00030¡\u0001J\b\u0010¥\u0002\u001a\u00030\u0092\u0001J\b\u0010¦\u0002\u001a\u00030¡\u0001J\b\u0010§\u0002\u001a\u00030¡\u0001J\b\u0010¨\u0002\u001a\u00030¡\u0001J\b\u0010©\u0002\u001a\u00030¡\u0001J\b\u0010ª\u0002\u001a\u00030¡\u0001J\b\u0010«\u0002\u001a\u00030¡\u0001J\b\u0010¬\u0002\u001a\u00030¡\u0001J\b\u0010\u00ad\u0002\u001a\u00030®\u0002J\u0007\u0010¯\u0002\u001a\u00020\u0004J\u0007\u0010°\u0002\u001a\u00020\u0004J\b\u0010±\u0002\u001a\u00030¡\u0001J\u0011\u0010²\u0002\u001a\f\u0012\u0005\u0012\u00030´\u0002\u0018\u00010³\u0002J\u0011\u0010µ\u0002\u001a\u00030\u008a\u00022\u0007\u0010¶\u0002\u001a\u00020\u0006J\u0018\u0010·\u0002\u001a\u00030\u008a\u00022\u000e\u0010¸\u0002\u001a\t\u0012\u0004\u0012\u00020\u00060\u0095\u0002J\u0018\u0010¹\u0002\u001a\u00030\u008a\u00022\u000e\u0010¸\u0002\u001a\t\u0012\u0004\u0012\u00020\u00060\u0095\u0002J\u0019\u0010º\u0002\u001a\u00030\u008a\u00022\u000f\u0010¸\u0002\u001a\n\u0012\u0005\u0012\u00030¡\u00020\u009b\u0002J%\u0010»\u0002\u001a\u00030\u008a\u00022\u001b\u0010¸\u0002\u001a\u0016\u0012\u0005\u0012\u00030¡\u00020\u009d\u0002j\n\u0012\u0005\u0012\u00030¡\u0002`\u009e\u0002J\u0019\u0010¼\u0002\u001a\u00030\u008a\u00022\u000f\u0010¸\u0002\u001a\n\u0012\u0005\u0012\u00030¡\u00020\u009b\u0002J\u001a\u0010½\u0002\u001a\u00030\u008a\u00022\u0007\u0010¾\u0002\u001a\u00020\u00062\u0007\u0010¿\u0002\u001a\u00020\u0006J%\u0010À\u0002\u001a\u00030\u008a\u00022\u001b\u0010¸\u0002\u001a\u0016\u0012\u0005\u0012\u00030¡\u00020\u009d\u0002j\n\u0012\u0005\u0012\u00030¡\u0002`\u009e\u0002J\"\u0010Á\u0002\u001a\u00030\u008a\u00022\u0007\u0010¶\u0002\u001a\u00020\u00062\u000f\u0010¸\u0002\u001a\n\u0012\u0005\u0012\u00030Â\u00020³\u0002J\u0012\u0010Ã\u0002\u001a\u00030\u008a\u00022\b\u0010Ä\u0002\u001a\u00030¡\u0001J\u0012\u0010Å\u0002\u001a\u00030\u008a\u00022\b\u0010Ä\u0002\u001a\u00030¡\u0001J\u001a\u0010Æ\u0002\u001a\u00030\u008a\u00022\u0007\u0010\u008f\u0002\u001a\u00020\u00062\u0007\u0010Ç\u0002\u001a\u00020\u0006J\u0012\u0010È\u0002\u001a\u00030\u008a\u00022\b\u0010É\u0002\u001a\u00030¡\u0001J\u0012\u0010Ê\u0002\u001a\u00030\u008a\u00022\b\u0010Ä\u0002\u001a\u00030¡\u0001J\u0012\u0010Ë\u0002\u001a\u00030\u008a\u00022\b\u0010Ì\u0002\u001a\u00030¡\u0001J\u0012\u0010Í\u0002\u001a\u00030\u008a\u00022\b\u0010Ä\u0002\u001a\u00030¡\u0001J\u0012\u0010Î\u0002\u001a\u00030\u008a\u00022\b\u0010É\u0002\u001a\u00030¡\u0001J\u0012\u0010Ï\u0002\u001a\u00030\u008a\u00022\b\u0010Ì\u0002\u001a\u00030¡\u0001J\b\u0010Ð\u0002\u001a\u00030\u008a\u0002J\u0012\u0010Ñ\u0002\u001a\u00030\u008a\u00022\b\u0010Ò\u0002\u001a\u00030¡\u0001J\u0012\u0010Ó\u0002\u001a\u00030\u008a\u00022\b\u0010Ä\u0002\u001a\u00030¡\u0001J\u0012\u0010Ô\u0002\u001a\u00030\u008a\u00022\b\u0010Ä\u0002\u001a\u00030¡\u0001J\u0012\u0010Õ\u0002\u001a\u00030\u008a\u00022\b\u0010Ö\u0002\u001a\u00030¡\u0001J\u0012\u0010×\u0002\u001a\u00030\u008a\u00022\b\u0010Ä\u0002\u001a\u00030¡\u0001J\u0012\u0010Ø\u0002\u001a\u00030\u008a\u00022\b\u0010Ä\u0002\u001a\u00030¡\u0001J\u0012\u0010Ù\u0002\u001a\u00030\u008a\u00022\b\u0010É\u0002\u001a\u00030¡\u0001J\u0012\u0010Ú\u0002\u001a\u00030\u008a\u00022\b\u0010Ä\u0002\u001a\u00030¡\u0001J\u0012\u0010Û\u0002\u001a\u00030\u008a\u00022\b\u0010Ä\u0002\u001a\u00030¡\u0001J\u0011\u0010Ü\u0002\u001a\u00030\u008a\u00022\u0007\u0010Ö\u0002\u001a\u00020\u0004J\u0011\u0010Ý\u0002\u001a\u00030\u008a\u00022\u0007\u0010Ö\u0002\u001a\u00020\u0004J\u0012\u0010Þ\u0002\u001a\u00030\u008a\u00022\b\u0010Ä\u0002\u001a\u00030¡\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010^\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010_\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010`\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010a\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010c\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010d\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010e\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010g\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010h\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010i\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010j\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010k\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010l\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010m\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010n\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010o\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010p\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010q\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010r\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010s\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010t\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010u\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010v\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010w\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010x\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010y\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010z\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010{\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010|\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010}\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010~\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u007f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0080\u0001\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0081\u0001\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0082\u0001\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0083\u0001\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0084\u0001\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0085\u0001\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0086\u0001\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0087\u0001\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0088\u0001\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0089\u0001\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u008a\u0001\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u008b\u0001\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u008c\u0001\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u008d\u0001\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u008e\u0001\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u008f\u0001\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0090\u0001\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0091\u0001\u001a\u00030\u0092\u0001X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0093\u0001\u001a\u00030\u0092\u0001X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0094\u0001\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R*\u0010\u0096\u0001\u001a\u00020\u00062\u0007\u0010\u0095\u0001\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001\"\u0006\b\u0099\u0001\u0010\u009a\u0001R*\u0010\u009b\u0001\u001a\u00020\u00062\u0007\u0010\u0095\u0001\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u009c\u0001\u0010\u0098\u0001\"\u0006\b\u009d\u0001\u0010\u009a\u0001R*\u0010\u009e\u0001\u001a\u00020\u00062\u0007\u0010\u0095\u0001\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u009f\u0001\u0010\u0098\u0001\"\u0006\b \u0001\u0010\u009a\u0001R,\u0010¢\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b£\u0001\u0010¤\u0001\"\u0006\b¥\u0001\u0010¦\u0001R,\u0010§\u0001\u001a\u00030\u0092\u00012\b\u0010\u0095\u0001\u001a\u00030\u0092\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b¨\u0001\u0010©\u0001\"\u0006\bª\u0001\u0010«\u0001R*\u0010¬\u0001\u001a\u00020\u00062\u0007\u0010\u0095\u0001\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u00ad\u0001\u0010\u0098\u0001\"\u0006\b®\u0001\u0010\u009a\u0001R,\u0010¯\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b¯\u0001\u0010¤\u0001\"\u0006\b°\u0001\u0010¦\u0001R,\u0010±\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b±\u0001\u0010¤\u0001\"\u0006\b²\u0001\u0010¦\u0001R,\u0010³\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b³\u0001\u0010¤\u0001\"\u0006\b´\u0001\u0010¦\u0001R,\u0010µ\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bµ\u0001\u0010¤\u0001\"\u0006\b¶\u0001\u0010¦\u0001R,\u0010·\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b·\u0001\u0010¤\u0001\"\u0006\b¸\u0001\u0010¦\u0001R,\u0010¹\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b¹\u0001\u0010¤\u0001\"\u0006\bº\u0001\u0010¦\u0001R,\u0010»\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b»\u0001\u0010¤\u0001\"\u0006\b¼\u0001\u0010¦\u0001R,\u0010½\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b½\u0001\u0010¤\u0001\"\u0006\b¾\u0001\u0010¦\u0001R,\u0010¿\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b¿\u0001\u0010¤\u0001\"\u0006\bÀ\u0001\u0010¦\u0001R,\u0010Á\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÁ\u0001\u0010¤\u0001\"\u0006\bÂ\u0001\u0010¦\u0001R,\u0010Ã\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÃ\u0001\u0010¤\u0001\"\u0006\bÄ\u0001\u0010¦\u0001R,\u0010Å\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÅ\u0001\u0010¤\u0001\"\u0006\bÆ\u0001\u0010¦\u0001R,\u0010Ç\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÇ\u0001\u0010¤\u0001\"\u0006\bÈ\u0001\u0010¦\u0001R,\u0010É\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÉ\u0001\u0010¤\u0001\"\u0006\bÊ\u0001\u0010¦\u0001R,\u0010Ë\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bË\u0001\u0010¤\u0001\"\u0006\bÌ\u0001\u0010¦\u0001R,\u0010Í\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÍ\u0001\u0010¤\u0001\"\u0006\bÎ\u0001\u0010¦\u0001R,\u0010Ï\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÏ\u0001\u0010¤\u0001\"\u0006\bÐ\u0001\u0010¦\u0001R,\u0010Ñ\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÑ\u0001\u0010¤\u0001\"\u0006\bÒ\u0001\u0010¦\u0001R,\u0010Ó\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÓ\u0001\u0010¤\u0001\"\u0006\bÔ\u0001\u0010¦\u0001R,\u0010Õ\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÕ\u0001\u0010¤\u0001\"\u0006\bÖ\u0001\u0010¦\u0001R,\u0010×\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b×\u0001\u0010¤\u0001\"\u0006\bØ\u0001\u0010¦\u0001R,\u0010Ù\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÙ\u0001\u0010¤\u0001\"\u0006\bÚ\u0001\u0010¦\u0001R,\u0010Û\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÛ\u0001\u0010¤\u0001\"\u0006\bÜ\u0001\u0010¦\u0001R,\u0010Ý\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÝ\u0001\u0010¤\u0001\"\u0006\bÞ\u0001\u0010¦\u0001R,\u0010ß\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bß\u0001\u0010¤\u0001\"\u0006\bà\u0001\u0010¦\u0001R,\u0010á\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bá\u0001\u0010¤\u0001\"\u0006\bâ\u0001\u0010¦\u0001R,\u0010ã\u0001\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bä\u0001\u0010¤\u0001\"\u0006\bå\u0001\u0010¦\u0001R*\u0010æ\u0001\u001a\u00020\u00062\u0007\u0010\u0095\u0001\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bç\u0001\u0010\u0098\u0001\"\u0006\bè\u0001\u0010\u009a\u0001R*\u0010é\u0001\u001a\u00020\u00062\u0007\u0010\u0095\u0001\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bê\u0001\u0010\u0098\u0001\"\u0006\bë\u0001\u0010\u009a\u0001R*\u0010ì\u0001\u001a\u00020\u00062\u0007\u0010\u0095\u0001\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bí\u0001\u0010\u0098\u0001\"\u0006\bî\u0001\u0010\u009a\u0001R,\u0010ð\u0001\u001a\u00030ï\u00012\b\u0010\u0095\u0001\u001a\u00030ï\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bñ\u0001\u0010ò\u0001\"\u0006\bó\u0001\u0010ô\u0001R*\u0010õ\u0001\u001a\u00020\u00062\u0007\u0010\u0095\u0001\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bö\u0001\u0010\u0098\u0001\"\u0006\b÷\u0001\u0010\u009a\u0001R*\u0010ø\u0001\u001a\u00020\u00042\u0007\u0010\u0095\u0001\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bù\u0001\u0010ú\u0001\"\u0006\bû\u0001\u0010ü\u0001R*\u0010ý\u0001\u001a\u00020\u00042\u0007\u0010\u0095\u0001\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bþ\u0001\u0010ú\u0001\"\u0006\bÿ\u0001\u0010ü\u0001R,\u0010\u0080\u0002\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u0081\u0002\u0010¤\u0001\"\u0006\b\u0082\u0002\u0010¦\u0001R,\u0010\u0083\u0002\u001a\u00030¡\u00012\b\u0010\u0095\u0001\u001a\u00030¡\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u0084\u0002\u0010¤\u0001\"\u0006\b\u0085\u0002\u0010¦\u0001R*\u0010\u0086\u0002\u001a\u00020\u00042\u0007\u0010\u0095\u0001\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u0087\u0002\u0010ú\u0001\"\u0006\b\u0088\u0002\u0010ü\u0001¨\u0006ß\u0002"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/config/Constans;", "", "()V", "APP_UPDATE_POWER", "", "CRUISE_VOICE_DEFAULT_INTERVAL", "", "DEBUG_PWD", "EXTRA_CRUISE_STAY_COPY_WRITE_PREVIEW", "EXTRA_VOLUME_STREAM_TYPE", "GREETER_GUIDE_VOICE_DEFAULT_INTERVAL", "GREETER_VOICE_DEFAULT_INTERVAL", "KEY_AIUI_SPEAKER_SET", "KEY_AIUI_TW_SPEAKER_SET", "KEY_ASSIGN_PALLET_LIGHT_SWITCH", "KEY_BIRTHDAY_MODE_TYPE", "KEY_BIRTHDAY_TEXT", "KEY_BIRTHDAY_TTS_CONFIG", Constans.KEY_CALL_RETURN_DESTINATION, "KEY_CRUISE_EXIT_SWITCH", "KEY_CRUISE_FACE_SWITCH", Constans.KEY_CRUISE_RETURN_DESTINATION, Constans.KEY_CRUISE_RETURN_SWITCH, "KEY_CRUISE_STAY_COPY_WRITE_TYPE", "KEY_CRUISE_STAY_POINT_TIME", "KEY_CRUISE_STAY_TYPE", "KEY_CRUISE_STAY_VOICE_INTERVAL_TIME", "KEY_CRUISE_TTS_CONFIG", "KEY_CRUISE_TTS_TYPE", "KEY_CRUISE_VOICE_INTERVAL", Constans.KEY_CUSTOM_CRUISE_TIME, Constans.KEY_CUSTOM_FINISH_BTN_CONTENT, "KEY_DEFAULT_SPEED_CONFIG", "KEY_DELIVERY_HISTORY_TASK", "KEY_DELIVERY_LAST_TASK", "KEY_DELIVER_EXIT_SWITCH", "KEY_DELIVER_FACE_SWITCH", "KEY_DELIVER_FINISH_VOICE_ADVANCE_SWITCH", "KEY_DELIVER_RECYCLE_PLATE_SWITCH", "KEY_DELIVER_SPEED_CONFIG", "KEY_DELIVER_TTS_CONFIG", "KEY_DELIVER_TTS_TYPE", "KEY_FROM_BIRTHDAY_MUSIC_SET", "KEY_GREETER_EXIT_SWITCH", "KEY_GREETER_FACE_DEFAULT_VOICE_SWITCH", "KEY_GREETER_GUIDE_TEXT_SWITCH", "KEY_GREETER_GUIDE_TTS_CONFIG", "KEY_GREETER_GUIDE_TTS_TYPE", "KEY_GREETER_GUIDE_VOICE_INTERVAL", "KEY_GREETER_SPEED_CONFIG", "KEY_GREETER_TTS_CONFIG", "KEY_GREETER_TTS_TYPE", "KEY_GREETER_VOICE_INTERVAL", "KEY_GUIDE_ARRIVAL_TTS_CONFIG", "KEY_GUIDE_ARRIVAL_TTS_TYPE", "KEY_ITERACTION_PRIZE_PROBABILITY_LEVEL_0", "KEY_ITERACTION_PRIZE_PROBABILITY_LEVEL_1", "KEY_ITERACTION_PRIZE_PROBABILITY_LEVEL_2", "KEY_ITERACTION_PRIZE_PROBABILITY_LEVEL_3", "KEY_ITERACTION_SWITCH", "KEY_LATTICE_CRUISE", "KEY_LATTICE_GUIDE_TABLE", "KEY_LATTICE_RECYCLE", "KEY_LATTICE_TURN_BACK", "KEY_LATTICE_WELCOME_AREA", "KEY_LEADING_FACE_SWITCH", "KEY_LOCKED_STATE", "KEY_MAP_CHOSEN", "KEY_MAP_SYNC_SWITCH", "KEY_MODE_BIRTHDAY_ARRIVAL_MUSIC_SWITCH", "KEY_MODE_BIRTHDAY_MUSIC_SET_TIP", "KEY_MODE_BIRTHDAY_ON_THE_WAY_MUSIC_SWITCH", "KEY_ORDER_DELIVERY_MODE", "KEY_PALLET_ALARM_SWITCH", "KEY_PALLET_DELIVER_TTS_CONFIG", "KEY_PALLET_DELIVER_TTS_FUNCTION", "KEY_PALLET_DELIVER_TTS_TYPE", "KEY_RECYCLE_EXIT_SWITCH", "KEY_RECYCLE_HISTORY_TASK", Constans.KEY_RECYCLE_LAST_TASK, "KEY_RECYCLE_PLATE_FACE_SWITCH", Constans.KEY_RECYCLE_POINT_ARRIVE_CONFIG, Constans.KEY_RECYCLE_POINT_ARRIVE_TYPE, Constans.KEY_RECYCLE_SORT, Constans.KEY_RECYCLE_TABLE_ARRIVE_CONFIG, Constans.KEY_RECYCLE_TABLE_ARRIVE_TYPE, Constans.KEY_RECYCLE_TABLE_LEAVE_CONFIG, Constans.KEY_RECYCLE_TABLE_LEAVE_TYPE, "KEY_RECYCLING_PLATE_SWITCH", Constans.KEY_REPEAT_LAST_RECYCLE, "KEY_REPEAT_LAST_TASK_SWITCH", "KEY_RETURN_FACE_SWITCH", Constans.KEY_SERVER_INFO, "KEY_SETTING_ABOUT_INFO_SWITCH", "KEY_SETTING_BOX_SWITCH", "KEY_SETTING_ENTER_SWITCH", "KEY_SETTING_LED_BIRTHDAY_SWITCH", "KEY_SETTING_LED_CRUISE_SWITCH", "KEY_SETTING_LED_DELIVER_SWITCH", "KEY_SETTING_LED_DIRECT_DELIVER_SWITCH", "KEY_SETTING_LED_GREETER_SWITCH", "KEY_SETTING_LED_NEW_TIPS", "KEY_SETTING_LED_RECYCLE_PLATE_SWITCH", "KEY_SETTING_LED_SPECIAL_SWITCH", "KEY_SETTING_PASSWORD", "KEY_SETTING_SAFE_MODE_SWITCH", "KEY_SETTING_STEADY_MODE_SWITCH", "KEY_SINGLE_TRAY_MULTI_TABLE_SWITCH", Constans.KEY_SLEEP_FACE, "KEY_SOFTWARE_LAST_VC", "KEY_SOFTWARE_LAST_VN", "KEY_SPACES_MODE_SELECT_ARRIVE_VOICE", "KEY_SPACES_MODE_SELECT_ARRIVE_VOICE_TYPE", "KEY_SPACES_MODE_SELECT_MUSIC", "KEY_SPECIAL_MODE_ARRIVE_CONFIG", "KEY_SPECIAL_MODE_ARRIVE_TTS_TYPE", "KEY_SPECIAL_MODE_CRUISE_CONFIG", "KEY_SPECIAL_MODE_CRUISE_TTS_TYPE", "KEY_SPECIAL_MODE_TEXT", "KEY_SPEED_LIMIT_AREA_SWITCH", "KEY_SPEED_LIMIT_AREA_TIME", "KEY_SYSTEM_UPDATE_COUNT", "KEY_TABLE_CLASSIFY_DRAG_SORT", "KEY_TABLE_CLASSIFY_DRAG_SORT_DIRECT", "KEY_TABLE_CLASSIFY_DRAG_SORT_TIP", "KEY_TABLE_INPUT_SMART_DEFAULT_TYPE", "KEY_TABLE_INPUT_TYPE", Constans.KEY_TOUCH_SWITCH, "KEY_TRAY_DESTINATION_WARN", Constans.KEY_VOICE_SWITCH, "KEY_WATCH_TABLE_BIND", "KEY_WELCOME_FACE_SWITCH", "KEY_WIFI_AUTO_OPEN", "PWD_PUBLIC", "PWD_TEST_MODE", "SETTING_PWD", "SKIP_KEY", "SKIP_TYPE_DROPCHECK", "SPEED_KEY", "STEADY_MODE_TYPE_KEY", "STEADY_RUNNING_SWITCH", "STEADY_TYPE_LONG", "STEADY_TYPE_REPEAT_LAST", "STEADY_TYPE_SINGLE", "TAG", "TIME_INTERVAL_MS", "", "TIME_ITEM_INTERVAL_MS", "VOLUME_CHANGED_ACTION", ES6Iterator.VALUE_PROPERTY, "birthdayText", "getBirthdayText", "()Ljava/lang/String;", "setBirthdayText", "(Ljava/lang/String;)V", "callReturnDestination", "getCallReturnDestination", "setCallReturnDestination", "cruiseReturnDestination", "getCruiseReturnDestination", "setCruiseReturnDestination", "", "cruiseReturnSwitch", "getCruiseReturnSwitch", "()Z", "setCruiseReturnSwitch", "(Z)V", "customCruiseTime", "getCustomCruiseTime", "()J", "setCustomCruiseTime", "(J)V", "customFinishBtnContent", "getCustomFinishBtnContent", "setCustomFinishBtnContent", "isBirthdayLedSwitch", "setBirthdayLedSwitch", "isBirthdayMusicArrival", "setBirthdayMusicArrival", "isBirthdayMusicOnTheWay", "setBirthdayMusicOnTheWay", "isBirthdayMusicSetTip", "setBirthdayMusicSetTip", "isCruiseLedSwitch", "setCruiseLedSwitch", "isDeliverLedSwitch", "setDeliverLedSwitch", "isDirectDeliverLedSwitch", "setDirectDeliverLedSwitch", "isFromBirthdayMusicSet", "setFromBirthdayMusicSet", "isGreeterGuideTextOpen", "setGreeterGuideTextOpen", "isGreeterLedSwitch", "setGreeterLedSwitch", "isLeadingFace", "setLeadingFace", "isLedSettingNew", "setLedSettingNew", "isLockedMachine", "setLockedMachine", "isMapSyncSwitch", "setMapSyncSwitch", "isOpenCruiseFace", "setOpenCruiseFace", "isOpenPalletDeliverTtsFunction", "setOpenPalletDeliverTtsFunction", "isOrderRecycle", "setOrderRecycle", "isRecyclePlateFace", "setRecyclePlateFace", "isRecyclePlateLedSwitch", "setRecyclePlateLedSwitch", "isRepeatLastRecycle", "setRepeatLastRecycle", "isReturnFace", "setReturnFace", "isSafeMode", "setSafeMode", "isSpecialLedSwitch", "setSpecialLedSwitch", "isSpeedLimitArea", "setSpeedLimitArea", "isSteadyRunning", "setSteadyRunning", "isWelcomeFace", "setWelcomeFace", "recycleExitSwitch", "getRecycleExitSwitch", "setRecycleExitSwitch", "recycleModeText", "getRecycleModeText", "setRecycleModeText", "serverInfo", "getServerInfo", "setServerInfo", "settingPassword", "getSettingPassword", "setSettingPassword", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/SceneAnimationResources$SleepFace;", "sleepFace", "getSleepFace", "()Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/SceneAnimationResources$SleepFace;", "setSleepFace", "(Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/SceneAnimationResources$SleepFace;)V", "specialModeText", "getSpecialModeText", "setSpecialModeText", "speedLimitTime", "getSpeedLimitTime", "()I", "setSpeedLimitTime", "(I)V", Constans.STEADY_MODE_TYPE_KEY, "getSteadyModeType", "setSteadyModeType", "touchSwitch", "getTouchSwitch", "setTouchSwitch", "voiceSwitch", "getVoiceSwitch", "setVoiceSwitch", "wifiAutoOpenType", "getWifiAutoOpenType", "setWifiAutoOpenType", "clearLastRecycleTask", "", "clearLastTask", "getAssignPalletLightSwitch", "getCruiseExitSwitch", "getCruiseRouteName", "id", "defName", "getDeliverExitSwitch", "getDeliverFaceSwitch", "getDeliverFinishVoiceAdvanceSwitch", "getDragSort", "", "getDragSortDirect", "getDragTip", "getGreeterExitSwitch", "getGreeterFaceDefaultVoiceSwitch", "getHistoryRecycleTask", "", "getLastDeliveryTask", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getLastRecycleTask", "getLastTask", "Lcom/pudutech/bumblebee/presenter/delivery_task/TrayModel;", "getMapChosen", "getOrderDeliveryModeSwitch", "getPalletAlarmSwitch", "getPlayLooperArriveVoiceTime", "getRecyclingPlateSwitch", "getRepeatLastTaskSwitch", "getSettingAboutInfoSwitch", "getSettingBoxSwitch", "getSettingEnterSwitch", "getSettingSteadyModeSwitch", "getSingleTrayMultiTableSwitch", "getSortType", "Lcom/pudutech/bumblebee/business/movementInterface/SortType;", "getTableInputSmartDefaultType", "getTableInputType", "getTrayDestinationWarn", "getWatchTableBindInfo", "", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TableWatchBindItem;", "removeWatchTableBindInfo", TransferTable.COLUMN_KEY, "saveDragSort", "list", "saveDragSortDirect", "saveHistoryRecycleTask", "saveLastDeliveryTask", "saveLastRecycleTask", "saveLastSoftwareVersion", "vn", "vc", "saveLastTask", "saveWatchTableBindInfo", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TableBindItem;", "setAssignPalletLightSwitch", "boolean", "setCruiseExitSwitch", "setCruiseRouteName", "name", "setDeliverExitSwitch", "state", "setDeliverFaceSwitch", "setDeliverFinishVoiceAdvanceSwitch", "b", "setDragTip", "setGreeterExitSwitch", "setGreeterFaceDefaultVoiceSwitch", "setMapChosen", "setOrderDeliveryModeSwitch", "isSmoothMode", "setPalletAlarmSwitch", "setRecyclingPlateSwitch", "setRepeatLastTaskSwitch", "i", "setSettingAboutInfoSwitch", "setSettingBoxSwitch", "setSettingEnterSwitch", "setSettingSteadyModeSwitch", "setSingleTrayMultiTableSwitch", "setTableInputSmartDefaultType", "setTableInputType", "setTrayDestinationWarn", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class Constans {
    public static final int APP_UPDATE_POWER = 20;
    public static final String CRUISE_VOICE_DEFAULT_INTERVAL = "20";
    public static final String DEBUG_PWD = "pudu666";
    public static final String EXTRA_CRUISE_STAY_COPY_WRITE_PREVIEW = "extra_cruise_stay_copy_write_preview";
    public static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
    public static final String GREETER_GUIDE_VOICE_DEFAULT_INTERVAL = "20";
    public static final String GREETER_VOICE_DEFAULT_INTERVAL = "20";
    public static final String KEY_AIUI_SPEAKER_SET = "key_aiui_speaker_set";
    public static final String KEY_AIUI_TW_SPEAKER_SET = "key_tw_aiui_speaker_set";
    public static final String KEY_ASSIGN_PALLET_LIGHT_SWITCH = "key_assign_pallet_light_switch";
    public static final String KEY_BIRTHDAY_MODE_TYPE = "key_birthday_mode_type";
    private static final String KEY_BIRTHDAY_TEXT = "key_birthday_text";
    public static final String KEY_BIRTHDAY_TTS_CONFIG = "key_birthday_tts_config";
    private static final String KEY_CALL_RETURN_DESTINATION = "KEY_CALL_RETURN_DESTINATION";
    public static final String KEY_CRUISE_EXIT_SWITCH = "key_cruise_exit_switch";
    public static final String KEY_CRUISE_FACE_SWITCH = "key_cruise_face_switch";
    private static final String KEY_CRUISE_RETURN_DESTINATION = "KEY_CRUISE_RETURN_DESTINATION";
    private static final String KEY_CRUISE_RETURN_SWITCH = "KEY_CRUISE_RETURN_SWITCH";
    public static final String KEY_CRUISE_STAY_COPY_WRITE_TYPE = "key_cruise_stay_copy_write_type";
    public static final String KEY_CRUISE_STAY_POINT_TIME = "key_cruise_stay_point_time";
    public static final String KEY_CRUISE_STAY_TYPE = "key_cruise_stay_type";
    public static final String KEY_CRUISE_STAY_VOICE_INTERVAL_TIME = "key_cruise_stay_voice_interval_time";
    public static final String KEY_CRUISE_TTS_CONFIG = "key_cruise_tts_config";
    public static final String KEY_CRUISE_TTS_TYPE = "key_cruise_tts_type";
    public static final String KEY_CRUISE_VOICE_INTERVAL = "key_cruise_voice_interval";
    private static final String KEY_CUSTOM_CRUISE_TIME = "KEY_CUSTOM_CRUISE_TIME";
    private static final String KEY_CUSTOM_FINISH_BTN_CONTENT = "KEY_CUSTOM_FINISH_BTN_CONTENT";
    public static final String KEY_DEFAULT_SPEED_CONFIG = "0.5";
    private static final String KEY_DELIVERY_HISTORY_TASK = "key_delivery_history_task";
    private static final String KEY_DELIVERY_LAST_TASK = "key_delivery_last_task";
    public static final String KEY_DELIVER_EXIT_SWITCH = "key_deliver_exit_switch";
    public static final String KEY_DELIVER_FACE_SWITCH = "key_deliver_face_switch";
    private static final String KEY_DELIVER_FINISH_VOICE_ADVANCE_SWITCH = "key_deliver_finish_voice_advance_switch";
    public static final String KEY_DELIVER_RECYCLE_PLATE_SWITCH = "key_deliver_recycle_plate_switch";
    public static final String KEY_DELIVER_SPEED_CONFIG = "key_deliver_speed_config";
    public static final String KEY_DELIVER_TTS_CONFIG = "key_deliver_tts_config";
    public static final String KEY_DELIVER_TTS_TYPE = "key_deliver_tts_type";
    private static final String KEY_FROM_BIRTHDAY_MUSIC_SET = "key_from_birthday_music_set";
    public static final String KEY_GREETER_EXIT_SWITCH = "key_greeter_exit_switch";
    private static final String KEY_GREETER_FACE_DEFAULT_VOICE_SWITCH = "key_greeter_face_default_voice_switch";
    private static final String KEY_GREETER_GUIDE_TEXT_SWITCH = "key_greeter_guide_text_switch";
    public static final String KEY_GREETER_GUIDE_TTS_CONFIG = "key_greeter_guide_tts_config";
    public static final String KEY_GREETER_GUIDE_TTS_TYPE = "key_greeter_guide_tts_type";
    public static final String KEY_GREETER_GUIDE_VOICE_INTERVAL = "key_greeter_guide_voice_interval";
    public static final String KEY_GREETER_SPEED_CONFIG = "key_greeter_speed_config";
    public static final String KEY_GREETER_TTS_CONFIG = "key_greeter_tts_config";
    public static final String KEY_GREETER_TTS_TYPE = "key_greeter_tts_type";
    public static final String KEY_GREETER_VOICE_INTERVAL = "key_greeter_voice_interval";
    public static final String KEY_GUIDE_ARRIVAL_TTS_CONFIG = "key_greeter_guide_tts_config";
    public static final String KEY_GUIDE_ARRIVAL_TTS_TYPE = "key_guide_arrival_tts_type";
    public static final String KEY_ITERACTION_PRIZE_PROBABILITY_LEVEL_0 = "key_interaction_prize_probability_level_0";
    public static final String KEY_ITERACTION_PRIZE_PROBABILITY_LEVEL_1 = "key_interaction_prize_probability_level_1";
    public static final String KEY_ITERACTION_PRIZE_PROBABILITY_LEVEL_2 = "key_interaction_prize_probability_level_2";
    public static final String KEY_ITERACTION_PRIZE_PROBABILITY_LEVEL_3 = "key_interaction_prize_probability_level_3";
    public static final String KEY_ITERACTION_SWITCH = "key_interaction_switch";
    public static final String KEY_LATTICE_CRUISE = "key_lattice_cruise";
    public static final String KEY_LATTICE_GUIDE_TABLE = "key_lattice_guide_table";
    public static final String KEY_LATTICE_RECYCLE = "key_lattice_recycle";
    public static final String KEY_LATTICE_TURN_BACK = "key_lattice_turn_back";
    public static final String KEY_LATTICE_WELCOME_AREA = "key_lattice_welcome_area";
    public static final String KEY_LEADING_FACE_SWITCH = "key_leading_face_switch";
    public static final String KEY_LOCKED_STATE = "lockedState";
    private static final String KEY_MAP_CHOSEN = "key_map_chosen";
    private static final String KEY_MAP_SYNC_SWITCH = "key_map_sync_switch";
    private static final String KEY_MODE_BIRTHDAY_ARRIVAL_MUSIC_SWITCH = "key_mode_birthday_arrival_music_switch";
    private static final String KEY_MODE_BIRTHDAY_MUSIC_SET_TIP = "key_mode_birthday_music_set_tip";
    private static final String KEY_MODE_BIRTHDAY_ON_THE_WAY_MUSIC_SWITCH = "key_mode_birthday_on_the_way_music_switch";
    public static final String KEY_ORDER_DELIVERY_MODE = "key_order_delivery_mode";
    public static final String KEY_PALLET_ALARM_SWITCH = "key_pallet_alarm_switch";
    public static final String KEY_PALLET_DELIVER_TTS_CONFIG = "key_pallet_deliver_tts_config";
    public static final String KEY_PALLET_DELIVER_TTS_FUNCTION = "key_pallet_deliver_tts_function";
    public static final String KEY_PALLET_DELIVER_TTS_TYPE = "key_pallet_deliver_tts_type";
    private static final String KEY_RECYCLE_EXIT_SWITCH = "key_recycle_exit_switch";
    private static final String KEY_RECYCLE_HISTORY_TASK = "key_recycle_history_task";
    private static final String KEY_RECYCLE_LAST_TASK = "KEY_RECYCLE_LAST_TASK";
    private static final String KEY_RECYCLE_PLATE_FACE_SWITCH = "key_recycle_plate_face_switch";
    public static final String KEY_RECYCLE_POINT_ARRIVE_CONFIG = "KEY_RECYCLE_POINT_ARRIVE_CONFIG";
    public static final String KEY_RECYCLE_POINT_ARRIVE_TYPE = "KEY_RECYCLE_POINT_ARRIVE_TYPE";
    private static final String KEY_RECYCLE_SORT = "KEY_RECYCLE_SORT";
    public static final String KEY_RECYCLE_TABLE_ARRIVE_CONFIG = "KEY_RECYCLE_TABLE_ARRIVE_CONFIG";
    public static final String KEY_RECYCLE_TABLE_ARRIVE_TYPE = "KEY_RECYCLE_TABLE_ARRIVE_TYPE";
    public static final String KEY_RECYCLE_TABLE_LEAVE_CONFIG = "KEY_RECYCLE_TABLE_LEAVE_CONFIG";
    public static final String KEY_RECYCLE_TABLE_LEAVE_TYPE = "KEY_RECYCLE_TABLE_LEAVE_TYPE";
    public static final String KEY_RECYCLING_PLATE_SWITCH = "key_recycling_plate_switch";
    private static final String KEY_REPEAT_LAST_RECYCLE = "KEY_REPEAT_LAST_RECYCLE";
    private static final String KEY_REPEAT_LAST_TASK_SWITCH = "key_repeat_last_task_switch";
    public static final String KEY_RETURN_FACE_SWITCH = "key_return_face_switch";
    private static final String KEY_SERVER_INFO = "KEY_SERVER_INFO";
    public static final String KEY_SETTING_ABOUT_INFO_SWITCH = "key_setting_about_info_switch";
    public static final String KEY_SETTING_BOX_SWITCH = "key_setting_box_switch";
    public static final String KEY_SETTING_ENTER_SWITCH = "key_setting_enter_switch";
    private static final String KEY_SETTING_LED_BIRTHDAY_SWITCH = "key_setting_led_birthday_switch";
    private static final String KEY_SETTING_LED_CRUISE_SWITCH = "key_setting_led_cruise_switch";
    private static final String KEY_SETTING_LED_DELIVER_SWITCH = "key_setting_led_deliver_switch";
    private static final String KEY_SETTING_LED_DIRECT_DELIVER_SWITCH = "key_setting_led_direct_deliver_switch";
    private static final String KEY_SETTING_LED_GREETER_SWITCH = "key_setting_led_greeter_switch";
    private static final String KEY_SETTING_LED_NEW_TIPS = "key_setting_led_new_tips";
    private static final String KEY_SETTING_LED_RECYCLE_PLATE_SWITCH = "key_setting_led_recycle_plate_switch";
    private static final String KEY_SETTING_LED_SPECIAL_SWITCH = "key_setting_led_special_switch";
    private static final String KEY_SETTING_PASSWORD = "key_setting_password";
    private static final String KEY_SETTING_SAFE_MODE_SWITCH = "key_setting_safe_mode_switch";
    public static final String KEY_SETTING_STEADY_MODE_SWITCH = "key_setting_steady_mode_switch";
    public static final String KEY_SINGLE_TRAY_MULTI_TABLE_SWITCH = "key_single_tray_multi_table_switch";
    private static final String KEY_SLEEP_FACE = "KEY_SLEEP_FACE";
    public static final String KEY_SOFTWARE_LAST_VC = "key_software_last_vc";
    public static final String KEY_SOFTWARE_LAST_VN = "key_software_last_vn";
    public static final String KEY_SPACES_MODE_SELECT_ARRIVE_VOICE = "key_spaces_mode_select_arrive_voice";
    public static final String KEY_SPACES_MODE_SELECT_ARRIVE_VOICE_TYPE = "key_spaces_mode_select_arrive_voice_type";
    public static final String KEY_SPACES_MODE_SELECT_MUSIC = "key_spaces_mode_select_music";
    public static final String KEY_SPECIAL_MODE_ARRIVE_CONFIG = "key_special_mode_arrive_config";
    public static final String KEY_SPECIAL_MODE_ARRIVE_TTS_TYPE = "key_special_mode_arrive_type";
    public static final String KEY_SPECIAL_MODE_CRUISE_CONFIG = "key_special_mode_cruise_config";
    public static final String KEY_SPECIAL_MODE_CRUISE_TTS_TYPE = "key_special_mode_cruise_type";
    private static final String KEY_SPECIAL_MODE_TEXT = "key_special_mode_text";
    private static final String KEY_SPEED_LIMIT_AREA_SWITCH = "key_speed_area_switch";
    private static final String KEY_SPEED_LIMIT_AREA_TIME = "key_speed_area_time";
    public static final String KEY_SYSTEM_UPDATE_COUNT = "key_system_update_count";
    private static final String KEY_TABLE_CLASSIFY_DRAG_SORT = "key_table_classify_drag_sort";
    private static final String KEY_TABLE_CLASSIFY_DRAG_SORT_DIRECT = "key_table_classify_drag_sort_direct";
    private static final String KEY_TABLE_CLASSIFY_DRAG_SORT_TIP = "key_table_classify_drag_sort_tip";
    private static final String KEY_TABLE_INPUT_SMART_DEFAULT_TYPE = "key_table_input_smart_default_type";
    private static final String KEY_TABLE_INPUT_TYPE = "key_table_input_type";
    private static final String KEY_TOUCH_SWITCH = "KEY_TOUCH_SWITCH";
    private static final String KEY_TRAY_DESTINATION_WARN = "key_tray_destination_warn";
    private static final String KEY_VOICE_SWITCH = "KEY_VOICE_SWITCH";
    private static final String KEY_WATCH_TABLE_BIND = "key_watch_table_bind";
    public static final String KEY_WELCOME_FACE_SWITCH = "key_welcome_face_switch";
    private static final String KEY_WIFI_AUTO_OPEN = "key_wifi_auto_open";
    public static final String PWD_PUBLIC = "pudupw";
    public static final String PWD_TEST_MODE = "000000";
    private static final String SETTING_PWD = "pudu888";
    public static final String SKIP_KEY = "skipkey";
    public static final int SKIP_TYPE_DROPCHECK = 1;
    public static final String SPEED_KEY = "key_robot_speed_level";
    private static final String STEADY_MODE_TYPE_KEY = "steadyModeType";
    private static final String STEADY_RUNNING_SWITCH = "steadyRunningSwitch";
    public static final int STEADY_TYPE_LONG = 1;
    public static final int STEADY_TYPE_REPEAT_LAST = 2;
    public static final int STEADY_TYPE_SINGLE = 0;
    public static final long TIME_INTERVAL_MS = 600;
    public static final long TIME_ITEM_INTERVAL_MS = 300;
    public static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    public static final Constans INSTANCE = new Constans();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private Constans() {
    }

    public final boolean getRecyclingPlateSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), "key_recycling_plate_switch", false);
    }

    public final boolean getSettingBoxSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_SETTING_BOX_SWITCH, false);
    }

    public final void setSettingBoxSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_SETTING_BOX_SWITCH, r3);
    }

    public final void setRecyclingPlateSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_recycling_plate_switch", r3);
    }

    public final boolean getPalletAlarmSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_PALLET_ALARM_SWITCH, false);
    }

    public final void setPalletAlarmSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_PALLET_ALARM_SWITCH, r3);
    }

    public final boolean getAssignPalletLightSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_ASSIGN_PALLET_LIGHT_SWITCH, false);
    }

    public final void setAssignPalletLightSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_ASSIGN_PALLET_LIGHT_SWITCH, r3);
    }

    public final boolean getCruiseExitSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_CRUISE_EXIT_SWITCH, false);
    }

    public final void setCruiseExitSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_CRUISE_EXIT_SWITCH, r3);
    }

    public final boolean getGreeterExitSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_GREETER_EXIT_SWITCH, false);
    }

    public final void setGreeterExitSwitch(boolean state) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_GREETER_EXIT_SWITCH, state);
    }

    public final boolean getDeliverExitSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_DELIVER_EXIT_SWITCH, false);
    }

    public final void setDeliverExitSwitch(boolean state) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_DELIVER_EXIT_SWITCH, state);
    }

    public final boolean getSettingEnterSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_SETTING_ENTER_SWITCH, false);
    }

    public final void setSettingEnterSwitch(boolean state) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_SETTING_ENTER_SWITCH, state);
    }

    public final void setRecycleExitSwitch(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_RECYCLE_EXIT_SWITCH, Boolean.valueOf(z));
    }

    public final boolean getRecycleExitSwitch() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_RECYCLE_EXIT_SWITCH, false);
    }

    public final boolean getOrderDeliveryModeSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_ORDER_DELIVERY_MODE, false);
    }

    public final void setOrderDeliveryModeSwitch(boolean isSmoothMode) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_ORDER_DELIVERY_MODE, isSmoothMode);
    }

    public final SortType getSortType() {
        if (getOrderDeliveryModeSwitch()) {
            return SortType.FIXED;
        }
        return SortType.AUTO;
    }

    public final boolean getSettingAboutInfoSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), "key_setting_about_info_switch", true);
    }

    public final void setSettingAboutInfoSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_setting_about_info_switch", r3);
    }

    public final boolean getSettingSteadyModeSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), "key_setting_steady_mode_switch", false);
    }

    public final void setSettingSteadyModeSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_setting_steady_mode_switch", r3);
    }

    public final void setSteadyRunning(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(STEADY_RUNNING_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isSteadyRunning() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(STEADY_RUNNING_SWITCH);
    }

    public final void setSteadyModeType(int i) {
        MMKVManager.INSTANCE.getINSTANCE().set(STEADY_MODE_TYPE_KEY, Integer.valueOf(i));
    }

    public final int getSteadyModeType() {
        return MMKVManager.INSTANCE.getINSTANCE().getInt(STEADY_MODE_TYPE_KEY);
    }

    public final boolean getSingleTrayMultiTableSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), "key_single_tray_multi_table_switch", false);
    }

    public final void setSingleTrayMultiTableSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_single_tray_multi_table_switch", r3);
    }

    public final boolean getDeliverFinishVoiceAdvanceSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_DELIVER_FINISH_VOICE_ADVANCE_SWITCH, false);
    }

    public final void setDeliverFinishVoiceAdvanceSwitch(boolean b) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_DELIVER_FINISH_VOICE_ADVANCE_SWITCH, b);
    }

    public final long getPlayLooperArriveVoiceTime() {
        if (BusinessSetting.INSTANCE.getArrivalLoopVoiceDelayTime_ms() > 0) {
            return BusinessSetting.INSTANCE.getArrivalLoopVoiceDelayTime_ms();
        }
        return 15000L;
    }

    public final boolean getGreeterFaceDefaultVoiceSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_GREETER_FACE_DEFAULT_VOICE_SWITCH, true);
    }

    public final void setGreeterFaceDefaultVoiceSwitch(boolean b) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_GREETER_FACE_DEFAULT_VOICE_SWITCH, b);
    }

    public final void saveLastSoftwareVersion(String vn, String vc) {
        Intrinsics.checkParameterIsNotNull(vn, "vn");
        Intrinsics.checkParameterIsNotNull(vc, "vc");
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_software_last_vn", vn);
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_software_last_vc", vc);
    }

    public final int getTableInputType() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_TABLE_INPUT_TYPE, 0);
    }

    public final void setTableInputType(int i) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_TABLE_INPUT_TYPE, i);
    }

    public final int getTableInputSmartDefaultType() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_TABLE_INPUT_SMART_DEFAULT_TYPE, 0);
    }

    public final void setTableInputSmartDefaultType(int i) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_TABLE_INPUT_SMART_DEFAULT_TYPE, i);
    }

    public final void saveLastDeliveryTask(ArrayList<TrayModel> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        ArrayList arrayList = new ArrayList();
        int count = PalletCountHelper.INSTANCE.getCount();
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TrayModel trayModel = (TrayModel) obj;
            if (i <= count - 1) {
                ArrayList arrayList2 = new ArrayList();
                arrayList.add(arrayList2);
                Iterator<T> it = trayModel.getAllDestinations().iterator();
                while (it.hasNext()) {
                    arrayList2.add(((DeliveryModel) it.next()).getDestination());
                }
            }
            i = i2;
        }
        String s = new Gson().toJson(arrayList);
        Pdlog.m3273d(TAG, "saveLastDeliveryTask : list = " + list + "; " + s);
        Context context = RobotContext.INSTANCE.getContext();
        Intrinsics.checkExpressionValueIsNotNull(s, "s");
        SpUtils.set(context, KEY_DELIVERY_HISTORY_TASK, s);
    }

    public final ArrayList<ArrayList<String>> getLastDeliveryTask() {
        String str = SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_DELIVERY_HISTORY_TASK, "");
        if (str.length() == 0) {
            return null;
        }
        try {
            return (ArrayList) new Gson().fromJson(str, new TypeToken<ArrayList<ArrayList<String>>>() { // from class: com.pudutech.bumblebee.robot_ui.config.Constans$getLastDeliveryTask$list$1
            }.getType());
        } catch (Exception unused) {
            return null;
        }
    }

    public final void saveWatchTableBindInfo(String key, List<TableBindItem> list) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(list, "list");
        ArrayList watchTableBindInfo = getWatchTableBindInfo();
        Object obj = null;
        if (watchTableBindInfo != null) {
            Iterator<T> it = watchTableBindInfo.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (Intrinsics.areEqual(((TableWatchBindItem) next).getMac(), key)) {
                    obj = next;
                    break;
                }
            }
            obj = (TableWatchBindItem) obj;
        }
        if (obj != null && watchTableBindInfo != null) {
            watchTableBindInfo.remove(obj);
        }
        if (watchTableBindInfo == null) {
            watchTableBindInfo = new ArrayList();
        }
        watchTableBindInfo.add(new TableWatchBindItem(key, list));
        String s = new Gson().toJson(watchTableBindInfo);
        Pdlog.m3273d(TAG, "saveWatchTableBindInfo : list = " + list + "; " + s);
        Context context = RobotContext.INSTANCE.getContext();
        Intrinsics.checkExpressionValueIsNotNull(s, "s");
        SpUtils.set(context, KEY_WATCH_TABLE_BIND, s);
    }

    public final List<TableWatchBindItem> getWatchTableBindInfo() {
        String str = SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_WATCH_TABLE_BIND, "");
        if (!Intrinsics.areEqual(str, "null")) {
            if (!(str.length() == 0)) {
                try {
                    return (ArrayList) new Gson().fromJson(str, new TypeToken<ArrayList<TableWatchBindItem>>() { // from class: com.pudutech.bumblebee.robot_ui.config.Constans$getWatchTableBindInfo$list$1
                    }.getType());
                } catch (Exception unused) {
                }
            }
        }
        return null;
    }

    public final void removeWatchTableBindInfo(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        ArrayList watchTableBindInfo = getWatchTableBindInfo();
        Object obj = null;
        if (watchTableBindInfo != null) {
            Iterator<T> it = watchTableBindInfo.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (Intrinsics.areEqual(((TableWatchBindItem) next).getMac(), key)) {
                    obj = next;
                    break;
                }
            }
            obj = (TableWatchBindItem) obj;
        }
        if (obj != null && watchTableBindInfo != null) {
            watchTableBindInfo.remove(obj);
        }
        if (watchTableBindInfo == null) {
            watchTableBindInfo = new ArrayList();
        }
        String s = new Gson().toJson(watchTableBindInfo);
        Pdlog.m3273d(TAG, "saveWatchTableBindInfo : list = " + watchTableBindInfo + "; " + s);
        Context context = RobotContext.INSTANCE.getContext();
        Intrinsics.checkExpressionValueIsNotNull(s, "s");
        SpUtils.set(context, KEY_WATCH_TABLE_BIND, s);
    }

    public final boolean getRepeatLastTaskSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_REPEAT_LAST_TASK_SWITCH, false);
    }

    public final void setRepeatLastTaskSwitch(boolean i) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_REPEAT_LAST_TASK_SWITCH, i);
    }

    public final String getMapChosen() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_MAP_CHOSEN, "");
    }

    public final void setMapChosen() {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_MAP_CHOSEN, RobotMapManager.INSTANCE.getDefaultPdmap());
    }

    public final void saveLastTask(ArrayList<TrayModel> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        setMapChosen();
        ArrayList arrayList = new ArrayList();
        Pdlog.m3275i(TAG, "saveLastTask:" + list);
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList.add(arrayList2);
            Iterator<T> it = ((TrayModel) obj).getAllDestinations().iterator();
            while (it.hasNext()) {
                arrayList2.add(((DeliveryModel) it.next()).getDestination());
            }
            i = i2;
        }
        String s = new Gson().toJson(arrayList);
        Pdlog.m3275i(TAG, "saveLastTask  :" + arrayList);
        Context context = RobotContext.INSTANCE.getContext();
        Intrinsics.checkExpressionValueIsNotNull(s, "s");
        SpUtils.set(context, KEY_DELIVERY_LAST_TASK, s);
    }

    public final ArrayList<TrayModel> getLastTask() {
        if (!getRepeatLastTaskSwitch() || (!Intrinsics.areEqual(getMapChosen(), RobotMapManager.INSTANCE.getDefaultPdmap()))) {
            clearLastTask();
        }
        ArrayList<TrayModel> arrayList = new ArrayList<>();
        String str = SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_DELIVERY_LAST_TASK, "");
        Pdlog.m3275i(TAG, "getLastTask  gson:" + str);
        if (str.length() == 0) {
            return null;
        }
        try {
            ArrayList<ArrayList> list = (ArrayList) new Gson().fromJson(str, new TypeToken<ArrayList<ArrayList<String>>>() { // from class: com.pudutech.bumblebee.robot_ui.config.Constans$getLastTask$list$1
            }.getType());
            Intrinsics.checkExpressionValueIsNotNull(list, "list");
            for (ArrayList<String> arrayList2 : list) {
                TrayModel trayModel = new TrayModel();
                arrayList.add(trayModel);
                for (String str2 : arrayList2) {
                    if (!RobotMapManager.INSTANCE.checkDestinationExist(str2)) {
                        INSTANCE.clearLastTask();
                        Pdlog.m3273d(TAG, "getLastTask:地图点改变了");
                        return null;
                    }
                    trayModel.getAllDestinations().add(new DeliveryModel(str2, null, null, null, 14, null));
                }
            }
            Pdlog.m3275i(TAG, "getLastTask  lastTask:" + arrayList);
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    public final void clearLastTask() {
        Pdlog.m3275i(TAG, "clearLastTask");
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_DELIVERY_LAST_TASK, "");
    }

    public final boolean getDragTip() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_TABLE_CLASSIFY_DRAG_SORT_TIP, true);
    }

    public final void setDragTip(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_TABLE_CLASSIFY_DRAG_SORT_TIP, r3);
    }

    public final void saveDragSort(Set<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        String s = new Gson().toJson(list);
        Context context = RobotContext.INSTANCE.getContext();
        Intrinsics.checkExpressionValueIsNotNull(s, "s");
        SpUtils.set(context, KEY_TABLE_CLASSIFY_DRAG_SORT, s);
    }

    public final Set<String> getDragSort() {
        String str = SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_TABLE_CLASSIFY_DRAG_SORT, "");
        if (!Intrinsics.areEqual(str, "null")) {
            if (!(str.length() == 0)) {
                try {
                    return (Set) new Gson().fromJson(str, new TypeToken<Set<String>>() { // from class: com.pudutech.bumblebee.robot_ui.config.Constans$getDragSort$list$1
                    }.getType());
                } catch (Exception unused) {
                }
            }
        }
        return null;
    }

    public final void saveDragSortDirect(Set<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        String s = new Gson().toJson(list);
        Context context = RobotContext.INSTANCE.getContext();
        Intrinsics.checkExpressionValueIsNotNull(s, "s");
        SpUtils.set(context, KEY_TABLE_CLASSIFY_DRAG_SORT_DIRECT, s);
    }

    public final Set<String> getDragSortDirect() {
        String str = SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_TABLE_CLASSIFY_DRAG_SORT_DIRECT, "");
        if (!Intrinsics.areEqual(str, "null")) {
            if (!(str.length() == 0)) {
                try {
                    return (Set) new Gson().fromJson(str, new TypeToken<Set<String>>() { // from class: com.pudutech.bumblebee.robot_ui.config.Constans$getDragSortDirect$list$1
                    }.getType());
                } catch (Exception unused) {
                }
            }
        }
        return null;
    }

    public final boolean getDeliverFaceSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), "key_deliver_face_switch", true);
    }

    public final void setDeliverFaceSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_deliver_face_switch", r3);
    }

    public final void setOpenCruiseFace(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_CRUISE_FACE_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isOpenCruiseFace() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_CRUISE_FACE_SWITCH, true);
    }

    public final void setWelcomeFace(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_WELCOME_FACE_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isWelcomeFace() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_WELCOME_FACE_SWITCH, true);
    }

    public final void setLeadingFace(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_LEADING_FACE_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isLeadingFace() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_LEADING_FACE_SWITCH, true);
    }

    public final void setReturnFace(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_RETURN_FACE_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isReturnFace() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_RETURN_FACE_SWITCH, true);
    }

    public final void setRecyclePlateFace(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_RECYCLE_PLATE_FACE_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isRecyclePlateFace() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_RECYCLE_PLATE_FACE_SWITCH, true);
    }

    public final void setLockedMachine(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_LOCKED_STATE, Boolean.valueOf(z));
    }

    public final boolean isLockedMachine() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_LOCKED_STATE);
    }

    public final void setMapSyncSwitch(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_MAP_SYNC_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isMapSyncSwitch() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_MAP_SYNC_SWITCH);
    }

    public final void setWifiAutoOpenType(int i) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_WIFI_AUTO_OPEN, Integer.valueOf(i));
    }

    public final int getWifiAutoOpenType() {
        return MMKVManager.INSTANCE.getINSTANCE().getInt(KEY_WIFI_AUTO_OPEN, -1);
    }

    public final void setBirthdayMusicOnTheWay(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_MODE_BIRTHDAY_ON_THE_WAY_MUSIC_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isBirthdayMusicOnTheWay() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_MODE_BIRTHDAY_ON_THE_WAY_MUSIC_SWITCH, true);
    }

    public final void setBirthdayMusicArrival(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_MODE_BIRTHDAY_ARRIVAL_MUSIC_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isBirthdayMusicArrival() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_MODE_BIRTHDAY_ARRIVAL_MUSIC_SWITCH, true);
    }

    public final void setBirthdayMusicSetTip(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_MODE_BIRTHDAY_MUSIC_SET_TIP, Boolean.valueOf(z));
    }

    public final boolean isBirthdayMusicSetTip() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_MODE_BIRTHDAY_MUSIC_SET_TIP, true);
    }

    public final void setFromBirthdayMusicSet(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_FROM_BIRTHDAY_MUSIC_SET, Boolean.valueOf(z));
    }

    public final boolean isFromBirthdayMusicSet() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_FROM_BIRTHDAY_MUSIC_SET, false);
    }

    public final void setBirthdayText(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_BIRTHDAY_TEXT, value);
    }

    public final String getBirthdayText() {
        String string = MMKVManager.INSTANCE.getINSTANCE().getString(KEY_BIRTHDAY_TEXT, "");
        return string != null ? string : "";
    }

    public final void setSpeedLimitArea(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_SPEED_LIMIT_AREA_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isSpeedLimitArea() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_SPEED_LIMIT_AREA_SWITCH, false);
    }

    public final void setSpeedLimitTime(int i) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_SPEED_LIMIT_AREA_TIME, Integer.valueOf(i));
    }

    public final int getSpeedLimitTime() {
        return MMKVManager.INSTANCE.getINSTANCE().getInt(KEY_SPEED_LIMIT_AREA_TIME, 3);
    }

    public final void setSpecialModeText(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_SPECIAL_MODE_TEXT, value);
    }

    public final String getSpecialModeText() {
        String string = MMKVManager.INSTANCE.getINSTANCE().getString(KEY_SPECIAL_MODE_TEXT, "");
        return string != null ? string : "";
    }

    public final void setRecycleModeText(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_LATTICE_RECYCLE, value);
    }

    public final String getRecycleModeText() {
        String string = MMKVManager.INSTANCE.getINSTANCE().getString(KEY_LATTICE_RECYCLE, "");
        return string != null ? string : "";
    }

    public final void setSafeMode(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_SETTING_SAFE_MODE_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isSafeMode() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_SETTING_SAFE_MODE_SWITCH, false);
    }

    public final void setGreeterGuideTextOpen(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_GREETER_GUIDE_TEXT_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isGreeterGuideTextOpen() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_GREETER_GUIDE_TEXT_SWITCH, true);
    }

    public final void setLedSettingNew(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_SETTING_LED_NEW_TIPS, Boolean.valueOf(z));
    }

    public final boolean isLedSettingNew() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_SETTING_LED_NEW_TIPS, true);
    }

    public final void setDeliverLedSwitch(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_SETTING_LED_DELIVER_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isDeliverLedSwitch() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_SETTING_LED_DELIVER_SWITCH, false);
    }

    public final void setCruiseLedSwitch(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_SETTING_LED_CRUISE_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isCruiseLedSwitch() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_SETTING_LED_CRUISE_SWITCH, false);
    }

    public final void setDirectDeliverLedSwitch(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_SETTING_LED_DIRECT_DELIVER_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isDirectDeliverLedSwitch() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_SETTING_LED_DIRECT_DELIVER_SWITCH, false);
    }

    public final void setGreeterLedSwitch(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_SETTING_LED_GREETER_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isGreeterLedSwitch() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_SETTING_LED_GREETER_SWITCH, false);
    }

    public final void setSpecialLedSwitch(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_SETTING_LED_SPECIAL_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isSpecialLedSwitch() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_SETTING_LED_SPECIAL_SWITCH, false);
    }

    public final void setBirthdayLedSwitch(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_SETTING_LED_BIRTHDAY_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isBirthdayLedSwitch() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_SETTING_LED_BIRTHDAY_SWITCH, true);
    }

    public final void setRecyclePlateLedSwitch(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_SETTING_LED_RECYCLE_PLATE_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isRecyclePlateLedSwitch() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_SETTING_LED_RECYCLE_PLATE_SWITCH, false);
    }

    public final void setSettingPassword(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_SETTING_PASSWORD, value);
    }

    public final String getSettingPassword() {
        String string = MMKVManager.INSTANCE.getINSTANCE().getString(KEY_SETTING_PASSWORD, "pudu888");
        return string != null ? string : "pudu888";
    }

    public final String getCruiseRouteName(String id, String defName) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(defName, "defName");
        return SpUtils.get(RobotContext.INSTANCE.getContext(), "cruise_edit_route", RobotMapManager.INSTANCE.getDefaultPdmap() + '_' + id, defName);
    }

    public final void setCruiseRouteName(String id, String name) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(name, "name");
        SpUtils.set(RobotContext.INSTANCE.getContext(), "cruise_edit_route", RobotMapManager.INSTANCE.getDefaultPdmap() + '_' + id, name);
    }

    public final void setSleepFace(SceneAnimationResources.SleepFace value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_SLEEP_FACE, value.name());
    }

    public final SceneAnimationResources.SleepFace getSleepFace() {
        String string = MMKVManager.INSTANCE.getINSTANCE().getString(KEY_SLEEP_FACE, SceneAnimationResources.SleepFace.Sleep.name());
        if (string == null) {
            string = SceneAnimationResources.SleepFace.Sleep.name();
        }
        return SceneAnimationResources.SleepFace.valueOf(string);
    }

    public final void setOpenPalletDeliverTtsFunction(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_PALLET_DELIVER_TTS_FUNCTION, Boolean.valueOf(z));
    }

    public final boolean isOpenPalletDeliverTtsFunction() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_PALLET_DELIVER_TTS_FUNCTION, false);
    }

    public final void setCustomFinishBtnContent(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_CUSTOM_FINISH_BTN_CONTENT, value);
    }

    public final String getCustomFinishBtnContent() {
        String string = MMKVManager.INSTANCE.getINSTANCE().getString(KEY_CUSTOM_FINISH_BTN_CONTENT, "");
        return string != null ? string : "";
    }

    public final void setVoiceSwitch(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_VOICE_SWITCH, Boolean.valueOf(z));
    }

    public final boolean getVoiceSwitch() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_VOICE_SWITCH, true);
    }

    public final void setTouchSwitch(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_TOUCH_SWITCH, Boolean.valueOf(z));
    }

    public final boolean getTouchSwitch() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_TOUCH_SWITCH, true);
    }

    public final void setServerInfo(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_SERVER_INFO, value);
    }

    public final String getServerInfo() {
        String string = MMKVManager.INSTANCE.getINSTANCE().getString(KEY_SERVER_INFO, "");
        return string != null ? string : "";
    }

    public final void setCustomCruiseTime(long j) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_CUSTOM_CRUISE_TIME, Long.valueOf(j));
    }

    public final long getCustomCruiseTime() {
        return MMKVManager.INSTANCE.getINSTANCE().getLong(KEY_CUSTOM_CRUISE_TIME, 30L);
    }

    public final void setCruiseReturnSwitch(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_CRUISE_RETURN_SWITCH, Boolean.valueOf(z));
    }

    public final boolean getCruiseReturnSwitch() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_CRUISE_RETURN_SWITCH, false);
    }

    public final void setCruiseReturnDestination(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_CRUISE_RETURN_DESTINATION, value);
    }

    public final String getCruiseReturnDestination() {
        String string = MMKVManager.INSTANCE.getINSTANCE().getString(KEY_CRUISE_RETURN_DESTINATION, "");
        return string != null ? string : "";
    }

    public final void setCallReturnDestination(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_CALL_RETURN_DESTINATION, value);
    }

    public final String getCallReturnDestination() {
        String string = MMKVManager.INSTANCE.getINSTANCE().getString(KEY_CALL_RETURN_DESTINATION, "");
        return string != null ? string : "";
    }

    public final boolean getTrayDestinationWarn() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_TRAY_DESTINATION_WARN, false);
    }

    public final void setTrayDestinationWarn(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_TRAY_DESTINATION_WARN, r3);
    }

    public final void saveHistoryRecycleTask(List<TrayModel> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        List<TrayModel> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((TrayModel) it.next()).getAllDestinations().get(0).getDestination());
        }
        String s = new Gson().toJson(arrayList);
        Pdlog.m3273d(TAG, "saveLastDeliveryTask : list = " + list + "; " + s);
        Context context = RobotContext.INSTANCE.getContext();
        Intrinsics.checkExpressionValueIsNotNull(s, "s");
        SpUtils.set(context, KEY_RECYCLE_HISTORY_TASK, s);
    }

    public final List<String> getHistoryRecycleTask() {
        String str = SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_RECYCLE_HISTORY_TASK, "");
        if (str.length() == 0) {
            return null;
        }
        try {
            return (List) new Gson().fromJson(str, new TypeToken<List<? extends String>>() { // from class: com.pudutech.bumblebee.robot_ui.config.Constans$getHistoryRecycleTask$list$1
            }.getType());
        } catch (Exception unused) {
            return null;
        }
    }

    public final void setOrderRecycle(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_RECYCLE_SORT, Boolean.valueOf(z));
    }

    public final boolean isOrderRecycle() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_RECYCLE_SORT, false);
    }

    public final void setRepeatLastRecycle(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_REPEAT_LAST_RECYCLE, Boolean.valueOf(z));
    }

    public final boolean isRepeatLastRecycle() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_REPEAT_LAST_RECYCLE, false);
    }

    public final void saveLastRecycleTask(List<TrayModel> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        setMapChosen();
        List<TrayModel> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((TrayModel) it.next()).getAllDestinations().get(0).getDestination());
        }
        ArrayList arrayList2 = arrayList;
        Pdlog.m3275i(TAG, "saveLastRecycleTask:" + list);
        String s = new Gson().toJson(arrayList2);
        Pdlog.m3275i(TAG, "saveLastRecycleTask  :" + arrayList2);
        Context context = RobotContext.INSTANCE.getContext();
        Intrinsics.checkExpressionValueIsNotNull(s, "s");
        SpUtils.set(context, KEY_RECYCLE_LAST_TASK, s);
    }

    public final ArrayList<String> getLastRecycleTask() {
        if (!isRepeatLastRecycle() || (!Intrinsics.areEqual(getMapChosen(), RobotMapManager.INSTANCE.getDefaultPdmap()))) {
            clearLastRecycleTask();
        }
        ArrayList<String> arrayList = new ArrayList<>();
        String str = SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_RECYCLE_LAST_TASK, "");
        Pdlog.m3275i(TAG, "getLastRecycleTask  gson:" + str);
        if (str.length() == 0) {
            return null;
        }
        try {
            ArrayList<String> list = (ArrayList) new Gson().fromJson(str, new TypeToken<ArrayList<String>>() { // from class: com.pudutech.bumblebee.robot_ui.config.Constans$getLastRecycleTask$list$1
            }.getType());
            Intrinsics.checkExpressionValueIsNotNull(list, "list");
            for (String str2 : list) {
                if (!RobotMapManager.INSTANCE.checkDestinationExist(str2)) {
                    INSTANCE.clearLastRecycleTask();
                    Pdlog.m3273d(TAG, "getLastRecycleTask:地图点改变了");
                    return null;
                }
                arrayList.add(str2);
            }
            Pdlog.m3275i(TAG, "getLastRecycleTask  lastTask:" + arrayList);
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    public final void clearLastRecycleTask() {
        Pdlog.m3275i(TAG, "clearLastRecycleTask");
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_RECYCLE_LAST_TASK, "");
    }
}
