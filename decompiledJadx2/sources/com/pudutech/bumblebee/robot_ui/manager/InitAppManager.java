package com.pudutech.bumblebee.robot_ui.manager;

import android.os.SystemClock;
import android.util.ArrayMap;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.initialization_task.InitStepViewModel;
import com.pudutech.bumblebee.presenter.initialization_task.InitializationContract;
import com.pudutech.bumblebee.presenter.initialization_task.InitializationPresenter;
import com.pudutech.bumblebee.presenter.initialization_task.LEDScreenFontUpdateContract;
import com.pudutech.bumblebee.presenter.initialization_task.LEDScreenFontUpdatePresenter;
import com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.resources.language.LanguageUtils;
import com.pudutech.resources.language.Option;
import com.pudutech.resources.language.SupportedLocale;
import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.Self_checkKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: InitAppManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001f\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\bÆ\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J)\u0010;\u001a\u00020\u00162!\u0010<\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(=\u0012\u0004\u0012\u00020\u00160\u0012J\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u000b0?J\u0010\u0010@\u001a\u00020\u00162\u0006\u0010A\u001a\u00020\u000bH\u0002J\u0006\u0010B\u001a\u00020\u0018J\u0006\u0010C\u001a\u00020\u0016J\u0006\u0010D\u001a\u00020\u0018J\u0010\u0010E\u001a\u00020\u00162\u0006\u00103\u001a\u00020#H\u0016J\b\u0010F\u001a\u00020\u0016H\u0002J)\u0010G\u001a\u00020\u00162!\u0010<\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(=\u0012\u0004\u0012\u00020\u00160\u0012J\u0010\u0010H\u001a\u00020\u00162\u0006\u0010I\u001a\u00020\bH\u0016J\u0010\u0010J\u001a\u00020\u00162\u0006\u0010K\u001a\u00020\u0018H\u0016J\u0010\u0010L\u001a\u00020\u00162\u0006\u0010A\u001a\u00020\u000bH\u0016J\u001a\u0010M\u001a\u00020\u0016*\u00020N2\f\u0010O\u001a\b\u0012\u0004\u0012\u0002080PH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000b0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000RT\u0010\u0011\u001aH\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00160\u00120\u0007j#\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00160\u0012`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001bR\u001a\u0010\u001e\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0019\"\u0004\b\u001f\u0010\u001bR\u001a\u0010 \u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0019\"\u0004\b!\u0010\u001bR\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010&\u001a\u00020'8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b(\u0010)R^\u0010.\u001a\u001f\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00122#\u0010,\u001a\u001f\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0012@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R^\u00104\u001a\u001f\u0012\u0013\u0012\u00110#¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00122#\u0010,\u001a\u001f\u0012\u0013\u0012\u00110#¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0012@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00100\"\u0004\b6\u00102R!\u00107\u001a\u0012\u0012\u0004\u0012\u0002080\u0007j\b\u0012\u0004\u0012\u000208`\t¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:¨\u0006Q"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/InitAppManager;", "Lcom/pudutech/bumblebee/presenter/initialization_task/InitializationContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/initialization_task/LEDScreenFontUpdateContract$ViewInterface;", "()V", "TAG", "", "componentInitDone", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/initialization_task/InitializationContract$Component;", "Lkotlin/collections/ArrayList;", "finishStepViewModel", "Lcom/pudutech/bumblebee/presenter/initialization_task/InitStepViewModel;", "initPresenter", "Lcom/pudutech/bumblebee/presenter/initialization_task/InitializationPresenter;", "initStep", "Landroid/util/ArrayMap;", "Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "initStepListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "step", "", "isInit", "", "()Z", "setInit", "(Z)V", "isNeedSetLanguage", "setNeedSetLanguage", "isSyncFinish", "setSyncFinish", "isSyncMap", "setSyncMap", "ledFontUpdateEvent", "Lcom/pudutech/bumblebee/presenter/initialization_task/LEDScreenFontUpdateContract$UpdateEvent;", "ledScreenFontUpdatePresenter", "Lcom/pudutech/bumblebee/presenter/initialization_task/LEDScreenFontUpdatePresenter;", "mapUpdatePresenter", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapUpdatePresenter;", "getMapUpdatePresenter", "()Lcom/pudutech/bumblebee/presenter/map_switch_task/MapUpdatePresenter;", "mapUpdatePresenter$delegate", "Lkotlin/Lazy;", ES6Iterator.VALUE_PROPERTY, "c", "onComponentInitDone", "getOnComponentInitDone", "()Lkotlin/jvm/functions/Function1;", "setOnComponentInitDone", "(Lkotlin/jvm/functions/Function1;)V", "event", "onFontUpdateListener", "getOnFontUpdateListener", "setOnFontUpdateListener", "usrList", "Lcom/pudutech/resources/language/Option;", "getUsrList", "()Ljava/util/ArrayList;", "addInitStepListener", "l", "b", "getInitStep", "", "handleInitStep", "viewModel", "hasFailed", "init", "isFinish", "onUpdateEvent", "postFinishStepIfNeed", "removeInitStepListener", "showComponentInitDone", "component", "showLocationInitDone", "yes", "showStep", "setLanguage", "Lcom/pudutech/resources/language/SupportedLocale;", "lis", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class InitAppManager implements InitializationContract.ViewInterface, LEDScreenFontUpdateContract.ViewInterface {
    public static final InitAppManager INSTANCE;
    private static final String TAG;
    private static final ArrayList<InitializationContract.Component> componentInitDone;
    private static InitStepViewModel finishStepViewModel;
    private static final InitializationPresenter initPresenter;
    private static final ArrayMap<InitStep, InitStepViewModel> initStep;
    private static final ArrayList<Function1<InitStepViewModel, Unit>> initStepListener;
    private static boolean isInit;
    private static boolean isNeedSetLanguage;
    private static boolean isSyncFinish;
    private static boolean isSyncMap;
    private static LEDScreenFontUpdateContract.UpdateEvent ledFontUpdateEvent;
    private static final LEDScreenFontUpdatePresenter ledScreenFontUpdatePresenter;

    /* renamed from: mapUpdatePresenter$delegate, reason: from kotlin metadata */
    private static final Lazy mapUpdatePresenter;
    private static Function1<? super InitializationContract.Component, Unit> onComponentInitDone;
    private static Function1<? super LEDScreenFontUpdateContract.UpdateEvent, Unit> onFontUpdateListener;
    private static final ArrayList<Option> usrList;

    /* JADX INFO: Access modifiers changed from: private */
    public final MapUpdatePresenter getMapUpdatePresenter() {
        return (MapUpdatePresenter) mapUpdatePresenter.getValue();
    }

    static {
        InitializationPresenter initializationPresenter;
        LEDScreenFontUpdatePresenter lEDScreenFontUpdatePresenter;
        InitAppManager initAppManager = new InitAppManager();
        INSTANCE = initAppManager;
        TAG = TAG;
        PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
        BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(InitializationPresenter.class).toString());
        Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(InitializationPresenter.class) + ' ' + basePresenterInterface);
        if (basePresenterInterface == null) {
            initializationPresenter = new InitializationPresenter();
            presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(InitializationPresenter.class).toString(), initializationPresenter);
        } else {
            if (!(basePresenterInterface instanceof InitializationPresenter)) {
                basePresenterInterface = null;
            }
            initializationPresenter = (InitializationPresenter) basePresenterInterface;
        }
        if (initializationPresenter == null) {
            Intrinsics.throwNpe();
        }
        initPresenter = initializationPresenter;
        PresenterHolder presenterHolder2 = PresenterHolder.INSTANCE;
        BasePresenterInterface basePresenterInterface2 = presenterHolder2.getBox().get(Reflection.getOrCreateKotlinClass(LEDScreenFontUpdatePresenter.class).toString());
        Pdlog.m3273d(presenterHolder2.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(LEDScreenFontUpdatePresenter.class) + ' ' + basePresenterInterface2);
        if (basePresenterInterface2 == null) {
            lEDScreenFontUpdatePresenter = new LEDScreenFontUpdatePresenter();
            presenterHolder2.getBox().put(Reflection.getOrCreateKotlinClass(LEDScreenFontUpdatePresenter.class).toString(), lEDScreenFontUpdatePresenter);
        } else {
            if (!(basePresenterInterface2 instanceof LEDScreenFontUpdatePresenter)) {
                basePresenterInterface2 = null;
            }
            lEDScreenFontUpdatePresenter = (LEDScreenFontUpdatePresenter) basePresenterInterface2;
        }
        if (lEDScreenFontUpdatePresenter == null) {
            Intrinsics.throwNpe();
        }
        ledScreenFontUpdatePresenter = lEDScreenFontUpdatePresenter;
        mapUpdatePresenter = LazyKt.lazy(new Function0<MapUpdatePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.manager.InitAppManager$mapUpdatePresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MapUpdatePresenter invoke() {
                MapUpdatePresenter mapUpdatePresenter2;
                PresenterHolder presenterHolder3 = PresenterHolder.INSTANCE;
                BasePresenterInterface basePresenterInterface3 = presenterHolder3.getBox().get(Reflection.getOrCreateKotlinClass(MapUpdatePresenter.class).toString());
                Pdlog.m3273d(presenterHolder3.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(MapUpdatePresenter.class) + ' ' + basePresenterInterface3);
                if (basePresenterInterface3 == null) {
                    mapUpdatePresenter2 = new MapUpdatePresenter();
                    presenterHolder3.getBox().put(Reflection.getOrCreateKotlinClass(MapUpdatePresenter.class).toString(), mapUpdatePresenter2);
                } else {
                    if (!(basePresenterInterface3 instanceof MapUpdatePresenter)) {
                        basePresenterInterface3 = null;
                    }
                    mapUpdatePresenter2 = (MapUpdatePresenter) basePresenterInterface3;
                }
                if (mapUpdatePresenter2 != null) {
                    return mapUpdatePresenter2;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter");
            }
        });
        initStep = new ArrayMap<>();
        initStepListener = new ArrayList<>();
        componentInitDone = new ArrayList<>();
        usrList = new ArrayList<>();
        InitAppManager initAppManager2 = initAppManager;
        initPresenter.replaceView(initAppManager2);
        ledScreenFontUpdatePresenter.replaceView(initAppManager2);
    }

    private InitAppManager() {
    }

    public final boolean isNeedSetLanguage() {
        return isNeedSetLanguage;
    }

    public final void setNeedSetLanguage(boolean z) {
        isNeedSetLanguage = z;
    }

    public final Function1<InitializationContract.Component, Unit> getOnComponentInitDone() {
        return onComponentInitDone;
    }

    public final void setOnComponentInitDone(Function1<? super InitializationContract.Component, Unit> function1) {
        if (function1 != null && componentInitDone.size() > 0) {
            Iterator<T> it = componentInitDone.iterator();
            while (it.hasNext()) {
                function1.invoke((InitializationContract.Component) it.next());
            }
        }
        onComponentInitDone = function1;
    }

    public final Function1<LEDScreenFontUpdateContract.UpdateEvent, Unit> getOnFontUpdateListener() {
        return onFontUpdateListener;
    }

    public final void setOnFontUpdateListener(Function1<? super LEDScreenFontUpdateContract.UpdateEvent, Unit> function1) {
        onFontUpdateListener = function1;
        LEDScreenFontUpdateContract.UpdateEvent updateEvent = ledFontUpdateEvent;
        if (updateEvent == null || function1 == null) {
            return;
        }
        function1.invoke(updateEvent);
    }

    public final boolean isInit() {
        return isInit;
    }

    public final void setInit(boolean z) {
        isInit = z;
    }

    public final ArrayList<Option> getUsrList() {
        return usrList;
    }

    public final boolean isSyncMap() {
        return isSyncMap;
    }

    public final void setSyncMap(boolean z) {
        isSyncMap = z;
    }

    public final boolean isSyncFinish() {
        return isSyncFinish;
    }

    public final void setSyncFinish(boolean z) {
        isSyncFinish = z;
    }

    private final void setLanguage(SupportedLocale supportedLocale, List<Option> list) {
        usrList.clear();
        usrList.addAll(list);
    }

    public final void init() {
        Pdlog.m3273d(TAG, "init system boot time:" + SystemClock.elapsedRealtime());
        if (isInit) {
            Pdlog.m3274e(TAG, "init : is init");
            return;
        }
        isInit = true;
        setLanguage(SupportedLocale.INSTANCE, CollectionsKt.arrayListOf(SupportedLocale.INSTANCE.getENGLISH(), SupportedLocale.INSTANCE.getCHINESE(), SupportedLocale.INSTANCE.getKOREAN(), SupportedLocale.INSTANCE.getCHINESE_HK(), SupportedLocale.INSTANCE.getCHINESE_TW(), SupportedLocale.INSTANCE.getJAPANESE(), SupportedLocale.INSTANCE.getSPANISH(), SupportedLocale.INSTANCE.getFRENCH(), SupportedLocale.INSTANCE.getITALIAN(), SupportedLocale.INSTANCE.getRUSSIAN(), SupportedLocale.INSTANCE.getGERMAN(), SupportedLocale.INSTANCE.getTHAI_LAND(), SupportedLocale.INSTANCE.getTURKEY(), SupportedLocale.INSTANCE.getPORTUGUESE(), SupportedLocale.INSTANCE.getCZECH(), SupportedLocale.INSTANCE.getDUTCH(), SupportedLocale.INSTANCE.getPORTUGUESE_Brazil(), SupportedLocale.INSTANCE.getARAB(), SupportedLocale.INSTANCE.getPOLAND(), SupportedLocale.INSTANCE.getSPANISH_LATIN_AMERICA(), SupportedLocale.INSTANCE.getINDONESIAN(), SupportedLocale.INSTANCE.getHUNGARIAN(), SupportedLocale.INSTANCE.getBULGARIAN(), SupportedLocale.INSTANCE.getVIETNAMESE(), SupportedLocale.INSTANCE.getSLOVENIAN(), SupportedLocale.INSTANCE.getCROATIAN(), SupportedLocale.INSTANCE.getSLOVAK(), SupportedLocale.INSTANCE.getESTONIA(), SupportedLocale.INSTANCE.getSERBIAN(), SupportedLocale.INSTANCE.getROMANIAN(), SupportedLocale.INSTANCE.getMALAY(), SupportedLocale.INSTANCE.getGEORGIA()));
        isNeedSetLanguage = new LanguageUtils(RobotContext.INSTANCE.getContext()).getNeedSet();
        initPresenter.actionInitNetCloud(Constans.INSTANCE.getServerInfo().length() > 0);
        initPresenter.actionInit(RobotContext.INSTANCE.getContext());
        initPresenter.actionInitLanguageAndVoice(RobotContext.INSTANCE.getContext());
        initPresenter.actionInitSdk(RobotContext.INSTANCE.getContext());
        SafeModeManager.INSTANCE.init();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new InitAppManager$init$1(null), 3, null);
    }

    public final void addInitStepListener(Function1<? super InitStepViewModel, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        if (initStepListener.contains(l)) {
            return;
        }
        initStepListener.add(l);
    }

    public final void removeInitStepListener(Function1<? super InitStepViewModel, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        initStepListener.remove(l);
    }

    public final Collection<InitStepViewModel> getInitStep() {
        Collection<InitStepViewModel> values = initStep.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "initStep.values");
        return values;
    }

    public final boolean isFinish() {
        return initStep.get(InitStep.Finish) != null;
    }

    public final boolean hasFailed() {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = false;
        initStep.forEach(new BiConsumer<InitStep, InitStepViewModel>() { // from class: com.pudutech.bumblebee.robot_ui.manager.InitAppManager$hasFailed$1
            @Override // java.util.function.BiConsumer
            public final void accept(InitStep initStep2, InitStepViewModel initStepViewModel) {
                if (initStepViewModel.getState() == StepState.Fail) {
                    Ref.BooleanRef.this.element = true;
                }
            }
        });
        return booleanRef.element;
    }

    @Override // com.pudutech.bumblebee.presenter.initialization_task.InitializationContract.ViewInterface
    public void showComponentInitDone(InitializationContract.Component component) {
        Intrinsics.checkParameterIsNotNull(component, "component");
        Pdlog.m3273d(TAG, "showComponentInitDone : component = " + component + "; ");
        componentInitDone.add(component);
        Function1<? super InitializationContract.Component, Unit> function1 = onComponentInitDone;
        if (function1 != null) {
            function1.invoke(component);
        }
    }

    @Override // com.pudutech.bumblebee.presenter.initialization_task.InitializationContract.ViewInterface
    public void showLocationInitDone(boolean yes) {
        Pdlog.m3273d(TAG, "showLocationInitDone : yes = " + yes + "; ");
    }

    @Override // com.pudutech.bumblebee.presenter.initialization_task.InitializationContract.ViewInterface
    public void showStep(InitStepViewModel viewModel) {
        String str;
        String name;
        Intrinsics.checkParameterIsNotNull(viewModel, "viewModel");
        TrackingReportManager trackingReportManager = TrackingReportManager.INSTANCE;
        InitStep step = viewModel.getStep();
        String str2 = "";
        if (step == null || (str = step.name()) == null) {
            str = "";
        }
        StepState state = viewModel.getState();
        if (state != null && (name = state.name()) != null) {
            str2 = name;
        }
        Self_checkKt.selfCheck(trackingReportManager, str, str2, viewModel.getDescription());
        Pdlog.m3273d(TAG, "showStep : viewModel = " + viewModel + "; ");
        if (viewModel.getStep() == InitStep.Finish && viewModel.getState() == StepState.Success) {
            finishStepViewModel = viewModel;
            ledScreenFontUpdatePresenter.tryUpdate();
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new InitAppManager$showStep$1(null), 3, null);
            return;
        }
        handleInitStep(viewModel);
    }

    private final void postFinishStepIfNeed() {
        InitStepViewModel initStepViewModel;
        if ((ledFontUpdateEvent == LEDScreenFontUpdateContract.UpdateEvent.UNNECESSARY_UPDATE || ledFontUpdateEvent == LEDScreenFontUpdateContract.UpdateEvent.DONE) && (initStepViewModel = finishStepViewModel) != null) {
            INSTANCE.handleInitStep(initStepViewModel);
        }
    }

    private final void handleInitStep(InitStepViewModel viewModel) {
        InitStep step = viewModel.getStep();
        if (step != null && (viewModel.getState() == StepState.Success || viewModel.getState() == StepState.Fail)) {
            initStep.put(step, viewModel);
        }
        Iterator<T> it = initStepListener.iterator();
        while (it.hasNext()) {
            ((Function1) it.next()).invoke(viewModel);
        }
    }

    @Override // com.pudutech.bumblebee.presenter.initialization_task.LEDScreenFontUpdateContract.ViewInterface
    public void onUpdateEvent(LEDScreenFontUpdateContract.UpdateEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Pdlog.m3273d(TAG, "onUpdateEvent : event = " + event + "; ");
        ledFontUpdateEvent = event;
        Function1<? super LEDScreenFontUpdateContract.UpdateEvent, Unit> function1 = onFontUpdateListener;
        if (function1 != null) {
            function1.invoke(event);
        }
        postFinishStepIfNeed();
    }
}
