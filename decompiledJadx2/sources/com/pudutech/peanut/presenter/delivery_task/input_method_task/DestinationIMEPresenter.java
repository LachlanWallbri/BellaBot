package com.pudutech.peanut.presenter.delivery_task.input_method_task;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdkwrap.lib.map.Destination;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.peanut.presenter.delivery_task.input_method_task.DestinationIMEContract;
import com.pudutech.peanut.presenter.delivery_task.input_method_task.DestinationIMEPresenter;
import com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: DestinationIMEPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001#B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0006H\u0016J\b\u0010\u0019\u001a\u00020\u0014H\u0016J\b\u0010\u001a\u001a\u00020\u0014H\u0002J\b\u0010\u001b\u001a\u00020\u0014H\u0016J\b\u0010\u001c\u001a\u00020\u0014H\u0002J\f\u0010\u001d\u001a\u00020\u0017*\u00020\u0006H\u0002J\u001c\u0010\u001e\u001a\u00020\u0017*\u0012\u0012\u0004\u0012\u00020\u000f0\nj\b\u0012\u0004\u0012\u00020\u000f`\fH\u0002J@\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\nj\b\u0012\u0004\u0012\u00020\u000f`\f*\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0 j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f`!2\u0006\u0010\u0018\u001a\u00020\u0006H\u0002J\u001c\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020\u00060\nj\b\u0012\u0004\u0012\u00020\u0006`\f*\u00020\u0006H\u0002R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u000ej\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\nj\b\u0012\u0004\u0012\u00020\u000f`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/delivery_task/input_method_task/DestinationIMEPresenter;", "Lcom/pudutech/peanut/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/peanut/presenter/delivery_task/input_method_task/DestinationIMEContract$ViewInterface;", "Lcom/pudutech/peanut/presenter/delivery_task/input_method_task/DestinationIMEContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "allDestinations", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/presenter/delivery_task/input_method_task/DestinationIMEPresenter$StringModel;", "Lkotlin/collections/ArrayList;", "allWords", "Ljava/util/LinkedHashMap;", "Lcom/pudutech/peanut/presenter/delivery_task/input_method_task/WordModel;", "Lkotlin/collections/LinkedHashMap;", "inputted", "selected", "backspace", "", "clear", "input", "", "str", "loadMap", "processSelected", "refreshAfterBackspace", "showInputted", "containDigit", "isNumberOnly", "splitFrom", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "splitWord", "StringModel", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class DestinationIMEPresenter extends BaseOneViewPresenter<DestinationIMEContract.ViewInterface> implements DestinationIMEContract.PresenterInterface {
    private final String TAG = "DestinationIMEPresenter";
    private final ArrayList<StringModel> allDestinations = new ArrayList<>();
    private final ArrayList<StringModel> selected = new ArrayList<>();
    private final LinkedHashMap<String, WordModel> allWords = new LinkedHashMap<>();
    private final ArrayList<WordModel> inputted = new ArrayList<>();

    @Override // com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter
    protected String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DestinationIMEPresenter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\b\b\u0082\b\u0018\u00002\u00020\u0001Ba\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00120\b\u0002\u0010\u0004\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0005j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006`\b\u0012\u0016\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0005j\b\u0012\u0004\u0012\u00020\n`\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007¢\u0006\u0002\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J1\u0010\u0019\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0005j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006`\bHÆ\u0003J\u0019\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0005j\b\u0012\u0004\u0012\u00020\n`\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003Ji\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u000320\b\u0002\u0010\u0004\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0005j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006`\b2\u0018\b\u0002\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0005j\b\u0012\u0004\u0012\u00020\n`\b2\b\b\u0002\u0010\u000b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0007HÖ\u0001J\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\nJ\u001e\u0010#\u001a\u00020\u001e2\u0016\u0010$\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0005j\b\u0012\u0004\u0012\u00020\n`\bJ\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR!\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0005j\b\u0012\u0004\u0012\u00020\n`\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010RB\u0010\u0004\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0005j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006`\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0010\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u000b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006&"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/delivery_task/input_method_task/DestinationIMEPresenter$StringModel;", "", "origin", "", "selectedChars", "Ljava/util/ArrayList;", "Lkotlin/Pair;", "", "Lkotlin/collections/ArrayList;", "pieces", "Lcom/pudutech/peanut/presenter/delivery_task/input_method_task/WordModel;", "selectedIndex", "(Ljava/lang/String;Ljava/util/ArrayList;Ljava/util/ArrayList;I)V", "getOrigin", "()Ljava/lang/String;", "getPieces", "()Ljava/util/ArrayList;", "getSelectedChars", "setSelectedChars", "(Ljava/util/ArrayList;)V", "getSelectedIndex", "()I", "setSelectedIndex", "(I)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "matchNext", ES6Iterator.NEXT_METHOD, "rematch", "sequence", "toString", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class StringModel {
        private final String origin;
        private final ArrayList<WordModel> pieces;
        private ArrayList<Pair<Integer, Integer>> selectedChars;
        private int selectedIndex;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ StringModel copy$default(StringModel stringModel, String str, ArrayList arrayList, ArrayList arrayList2, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = stringModel.origin;
            }
            if ((i2 & 2) != 0) {
                arrayList = stringModel.selectedChars;
            }
            if ((i2 & 4) != 0) {
                arrayList2 = stringModel.pieces;
            }
            if ((i2 & 8) != 0) {
                i = stringModel.selectedIndex;
            }
            return stringModel.copy(str, arrayList, arrayList2, i);
        }

        /* renamed from: component1, reason: from getter */
        public final String getOrigin() {
            return this.origin;
        }

        public final ArrayList<Pair<Integer, Integer>> component2() {
            return this.selectedChars;
        }

        public final ArrayList<WordModel> component3() {
            return this.pieces;
        }

        /* renamed from: component4, reason: from getter */
        public final int getSelectedIndex() {
            return this.selectedIndex;
        }

        public final StringModel copy(String origin, ArrayList<Pair<Integer, Integer>> selectedChars, ArrayList<WordModel> pieces, int selectedIndex) {
            Intrinsics.checkParameterIsNotNull(origin, "origin");
            Intrinsics.checkParameterIsNotNull(selectedChars, "selectedChars");
            Intrinsics.checkParameterIsNotNull(pieces, "pieces");
            return new StringModel(origin, selectedChars, pieces, selectedIndex);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StringModel)) {
                return false;
            }
            StringModel stringModel = (StringModel) other;
            return Intrinsics.areEqual(this.origin, stringModel.origin) && Intrinsics.areEqual(this.selectedChars, stringModel.selectedChars) && Intrinsics.areEqual(this.pieces, stringModel.pieces) && this.selectedIndex == stringModel.selectedIndex;
        }

        public int hashCode() {
            String str = this.origin;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            ArrayList<Pair<Integer, Integer>> arrayList = this.selectedChars;
            int hashCode2 = (hashCode + (arrayList != null ? arrayList.hashCode() : 0)) * 31;
            ArrayList<WordModel> arrayList2 = this.pieces;
            return ((hashCode2 + (arrayList2 != null ? arrayList2.hashCode() : 0)) * 31) + this.selectedIndex;
        }

        public String toString() {
            return "StringModel(origin=" + this.origin + ", selectedChars=" + this.selectedChars + ", pieces=" + this.pieces + ", selectedIndex=" + this.selectedIndex + ")";
        }

        public StringModel(String origin, ArrayList<Pair<Integer, Integer>> selectedChars, ArrayList<WordModel> pieces, int i) {
            Intrinsics.checkParameterIsNotNull(origin, "origin");
            Intrinsics.checkParameterIsNotNull(selectedChars, "selectedChars");
            Intrinsics.checkParameterIsNotNull(pieces, "pieces");
            this.origin = origin;
            this.selectedChars = selectedChars;
            this.pieces = pieces;
            this.selectedIndex = i;
        }

        public final String getOrigin() {
            return this.origin;
        }

        public /* synthetic */ StringModel(String str, ArrayList arrayList, ArrayList arrayList2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i2 & 2) != 0 ? new ArrayList() : arrayList, arrayList2, (i2 & 8) != 0 ? -1 : i);
        }

        public final ArrayList<Pair<Integer, Integer>> getSelectedChars() {
            return this.selectedChars;
        }

        public final void setSelectedChars(ArrayList<Pair<Integer, Integer>> arrayList) {
            Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
            this.selectedChars = arrayList;
        }

        public final ArrayList<WordModel> getPieces() {
            return this.pieces;
        }

        public final int getSelectedIndex() {
            return this.selectedIndex;
        }

        public final void setSelectedIndex(int i) {
            this.selectedIndex = i;
        }

        public final boolean rematch(ArrayList<WordModel> sequence) {
            Intrinsics.checkParameterIsNotNull(sequence, "sequence");
            if (sequence.isEmpty()) {
                return false;
            }
            this.selectedChars.clear();
            int i = -1;
            int i2 = 0;
            int i3 = 0;
            for (Object obj : this.pieces) {
                int i4 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                if (Intrinsics.areEqual((WordModel) obj, sequence.get(i3))) {
                    int indexOf$default = StringsKt.indexOf$default((CharSequence) this.origin, sequence.get(i3).getContent(), i, false, 4, (Object) null);
                    if (indexOf$default < 0) {
                        return false;
                    }
                    this.selectedChars.add(new Pair<>(Integer.valueOf(indexOf$default), Integer.valueOf(sequence.get(i3).getContent().length() + indexOf$default)));
                    i3++;
                    if (i3 == sequence.size()) {
                        this.selectedIndex = i2;
                        return true;
                    }
                    i = indexOf$default + sequence.get(i3).getContent().length();
                }
                i2 = i4;
            }
            return false;
        }

        public final boolean matchNext(WordModel next) {
            Intrinsics.checkParameterIsNotNull(next, "next");
            if (this.selectedIndex == CollectionsKt.getLastIndex(this.pieces) || (!Intrinsics.areEqual(next, this.pieces.get(this.selectedIndex + 1)))) {
                return false;
            }
            int indexOf$default = StringsKt.indexOf$default((CharSequence) this.origin, next.getContent(), this.selectedChars.size() > 0 ? ((Number) ((Pair) CollectionsKt.last((List) this.selectedChars)).getSecond()).intValue() : -1, false, 4, (Object) null);
            if (indexOf$default < 0) {
                return false;
            }
            this.selectedChars.add(new Pair<>(Integer.valueOf(indexOf$default), Integer.valueOf(indexOf$default + next.getContent().length())));
            this.selectedIndex++;
            return true;
        }
    }

    @Override // com.pudutech.peanut.presenter.delivery_task.input_method_task.DestinationIMEContract.PresenterInterface
    public void loadMap() {
        boolean z;
        Pdlog.m3273d(getTAG(), "loadMap ");
        this.allDestinations.clear();
        this.allWords.clear();
        this.inputted.clear();
        this.selected.clear();
        ArrayList arrayList = new ArrayList();
        ArrayList<Destination> allDestination = RobotMapManager.INSTANCE.getAllDestination();
        if (allDestination != null) {
            z = false;
            for (Destination destination : allDestination) {
                ArrayList<String> splitWord = splitWord(destination.getName());
                Pdlog.m3273d(getTAG(), "split name=" + destination.getName() + ' ' + splitWord);
                arrayList.addAll(splitWord);
                if (containDigit(destination.getName())) {
                    z = true;
                }
            }
        } else {
            z = false;
        }
        arrayList.removeIf(new Predicate<String>() { // from class: com.pudutech.peanut.presenter.delivery_task.input_method_task.DestinationIMEPresenter$loadMap$2
            @Override // java.util.function.Predicate
            public final boolean test(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return StringsKt.isBlank(it);
            }
        });
        if (z) {
            for (int i = 0; i <= 9; i++) {
                WordModel wordModel = new WordModel(null, false, false, false, 15, null);
                wordModel.setContent(String.valueOf(i));
                wordModel.setSelectable(false);
                wordModel.setExistInMap(false);
                wordModel.setNumber(true);
                this.allWords.put(String.valueOf(i), wordModel);
            }
        }
        for (String str : CollectionsKt.distinct(arrayList)) {
            WordModel wordModel2 = this.allWords.get(str);
            if (wordModel2 == null) {
                wordModel2 = new WordModel(null, false, false, false, 15, null);
            }
            Intrinsics.checkExpressionValueIsNotNull(wordModel2, "allWords[it] ?: WordModel()");
            wordModel2.setContent(str);
            wordModel2.setSelectable(true);
            wordModel2.setExistInMap(true);
            this.allWords.put(str, wordModel2);
        }
        if (allDestination != null) {
            for (Destination destination2 : allDestination) {
                this.allDestinations.add(new StringModel(destination2.getName(), null, splitFrom(this.allWords, destination2.getName()), 0, 10, null));
            }
        }
        DestinationIMEContract.ViewInterface theView = getTheView();
        if (theView != null) {
            theView.showResults(new ArrayList<>());
        }
        DestinationIMEContract.ViewInterface theView2 = getTheView();
        if (theView2 != null) {
            theView2.showWordModels(new ArrayList<>(this.allWords.values()));
        }
    }

    @Override // com.pudutech.peanut.presenter.delivery_task.input_method_task.DestinationIMEContract.PresenterInterface
    public boolean input(String str) {
        Intrinsics.checkParameterIsNotNull(str, "str");
        Pdlog.m3273d(getTAG(), "input str=" + str);
        final WordModel wordModel = this.allWords.get(str);
        if (wordModel == null) {
            return false;
        }
        if (!this.selected.isEmpty()) {
            this.selected.removeIf(new Predicate<StringModel>() { // from class: com.pudutech.peanut.presenter.delivery_task.input_method_task.DestinationIMEPresenter$input$3
                @Override // java.util.function.Predicate
                public final boolean test(DestinationIMEPresenter.StringModel it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return !it.matchNext(WordModel.this);
                }
            });
        } else {
            for (StringModel stringModel : this.allDestinations) {
                if (stringModel.rematch(CollectionsKt.arrayListOf(wordModel))) {
                    this.selected.add(stringModel);
                }
            }
        }
        if (this.selected.isEmpty()) {
            return false;
        }
        this.inputted.add(wordModel);
        processSelected();
        showInputted();
        return true;
    }

    @Override // com.pudutech.peanut.presenter.delivery_task.input_method_task.DestinationIMEContract.PresenterInterface
    public void backspace() {
        Pdlog.m3273d(getTAG(), "backspace inputted.size=" + this.inputted.size() + " lastIndex=" + CollectionsKt.getLastIndex(this.inputted));
        if (this.inputted.size() > 0) {
            ArrayList<WordModel> arrayList = this.inputted;
            arrayList.remove(CollectionsKt.getLastIndex(arrayList));
        }
        showInputted();
    }

    private final void showInputted() {
        StringBuilder sb = new StringBuilder();
        Iterator<T> it = this.inputted.iterator();
        while (it.hasNext()) {
            sb.append(((WordModel) it.next()).getContent());
        }
        DestinationIMEContract.ViewInterface theView = getTheView();
        if (theView != null) {
            String sb2 = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb2, "builder.toString()");
            theView.showInputted(sb2);
        }
    }

    @Override // com.pudutech.peanut.presenter.delivery_task.input_method_task.DestinationIMEContract.PresenterInterface
    public void refreshAfterBackspace() {
        this.selected.clear();
        for (StringModel stringModel : this.allDestinations) {
            if (stringModel.rematch(this.inputted)) {
                Pdlog.m3273d(getTAG(), "move index success. inputted.size=" + this.inputted.size());
                this.selected.add(stringModel);
            }
        }
        if (!this.selected.isEmpty()) {
            processSelected();
            return;
        }
        Iterator<Map.Entry<String, WordModel>> it = this.allWords.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().setSelectable(true);
        }
        DestinationIMEContract.ViewInterface theView = getTheView();
        if (theView != null) {
            theView.showResults(new ArrayList<>());
        }
        DestinationIMEContract.ViewInterface theView2 = getTheView();
        if (theView2 != null) {
            theView2.showWordModels(new ArrayList<>(this.allWords.values()));
        }
    }

    private final void processSelected() {
        Pdlog.m3273d(getTAG(), "showSelected selected.size=" + this.selected.size());
        Iterator<Map.Entry<String, WordModel>> it = this.allWords.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().setSelectable(false);
        }
        CollectionsKt.sortWith(this.selected, ComparisonsKt.compareBy(new Function1<StringModel, Boolean>() { // from class: com.pudutech.peanut.presenter.delivery_task.input_method_task.DestinationIMEPresenter$processSelected$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(DestinationIMEPresenter.StringModel stringModel) {
                return Boolean.valueOf(invoke2(stringModel));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(DestinationIMEPresenter.StringModel it2) {
                Intrinsics.checkParameterIsNotNull(it2, "it");
                return CollectionsKt.getLastIndex(it2.getPieces()) != it2.getSelectedIndex();
            }
        }, new Function1<StringModel, Boolean>() { // from class: com.pudutech.peanut.presenter.delivery_task.input_method_task.DestinationIMEPresenter$processSelected$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(DestinationIMEPresenter.StringModel stringModel) {
                return Boolean.valueOf(invoke2(stringModel));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(DestinationIMEPresenter.StringModel it2) {
                boolean isNumberOnly;
                Intrinsics.checkParameterIsNotNull(it2, "it");
                isNumberOnly = DestinationIMEPresenter.this.isNumberOnly(it2.getPieces());
                return isNumberOnly;
            }
        }, new Function1<StringModel, Integer>() { // from class: com.pudutech.peanut.presenter.delivery_task.input_method_task.DestinationIMEPresenter$processSelected$4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(DestinationIMEPresenter.StringModel stringModel) {
                return Integer.valueOf(invoke2(stringModel));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final int invoke2(DestinationIMEPresenter.StringModel it2) {
                Intrinsics.checkParameterIsNotNull(it2, "it");
                return CollectionsKt.getLastIndex(it2.getPieces()) - it2.getSelectedIndex();
            }
        }));
        ArrayList<DestinationModel> arrayList = new ArrayList<>();
        for (StringModel stringModel : this.selected) {
            DestinationModel destinationModel = new DestinationModel();
            destinationModel.setContent(stringModel.getOrigin());
            destinationModel.setInputtedChars(stringModel.getSelectedChars());
            if (stringModel.getSelectedIndex() < CollectionsKt.getLastIndex(stringModel.getPieces())) {
                stringModel.getPieces().get(stringModel.getSelectedIndex() + 1).setSelectable(true);
                String content = stringModel.getPieces().get(stringModel.getSelectedIndex() + 1).getContent();
                int indexOf$default = StringsKt.indexOf$default((CharSequence) stringModel.getOrigin(), content, stringModel.getSelectedChars().size() > 0 ? ((Number) ((Pair) CollectionsKt.last((List) stringModel.getSelectedChars())).getSecond()).intValue() : -1, false, 4, (Object) null);
                if (indexOf$default >= 0) {
                    destinationModel.setNextChar(new Pair<>(Integer.valueOf(indexOf$default), Integer.valueOf(indexOf$default + content.length())));
                }
            }
            arrayList.add(destinationModel);
        }
        showInputted();
        DestinationIMEContract.ViewInterface theView = getTheView();
        if (theView != null) {
            theView.showResults(arrayList);
        }
        DestinationIMEContract.ViewInterface theView2 = getTheView();
        if (theView2 != null) {
            theView2.showWordModels(new ArrayList<>(this.allWords.values()));
        }
    }

    @Override // com.pudutech.peanut.presenter.delivery_task.input_method_task.DestinationIMEContract.PresenterInterface
    public void clear() {
        Pdlog.m3273d(getTAG(), "clear ");
        this.inputted.clear();
        this.selected.clear();
        for (Map.Entry<String, WordModel> entry : this.allWords.entrySet()) {
            if (entry.getValue().isExistInMap()) {
                entry.getValue().setSelectable(true);
            }
        }
        showInputted();
        DestinationIMEContract.ViewInterface theView = getTheView();
        if (theView != null) {
            theView.showResults(new ArrayList<>());
        }
        DestinationIMEContract.ViewInterface theView2 = getTheView();
        if (theView2 != null) {
            theView2.showWordModels(new ArrayList<>(this.allWords.values()));
        }
    }

    private final ArrayList<String> splitWord(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String str2 = str;
        Boolean bool = (Boolean) null;
        for (int i = 0; i < str2.length(); i++) {
            char charAt = str2.charAt(i);
            if (Character.isDigit(charAt)) {
                if (Intrinsics.areEqual((Object) bool, (Object) false)) {
                    arrayList.add(sb.toString());
                    StringsKt.clear(sb);
                }
                arrayList.add(String.valueOf(charAt));
            } else {
                sb.append(charAt);
            }
            bool = Boolean.valueOf(Character.isDigit(charAt));
        }
        if (sb.length() > 0) {
            arrayList.add(sb.toString());
        }
        return arrayList;
    }

    private final boolean containDigit(String str) {
        String str2 = str;
        for (int i = 0; i < str2.length(); i++) {
            if (Character.isDigit(str2.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isNumberOnly(ArrayList<WordModel> arrayList) {
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            if (!((WordModel) it.next()).isNumber()) {
                return false;
            }
        }
        return true;
    }

    private final ArrayList<WordModel> splitFrom(HashMap<String, WordModel> hashMap, String str) {
        WordModel wordModel;
        ArrayList<WordModel> arrayList = new ArrayList<>();
        for (String str2 : splitWord(str)) {
            if ((!StringsKt.isBlank(str2)) && (wordModel = hashMap.get(str2)) != null) {
                arrayList.add(wordModel);
            }
        }
        return arrayList;
    }
}
