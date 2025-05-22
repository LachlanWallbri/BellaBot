package androidx.lifecycle;

import androidx.arch.core.util.Function;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Transformations {
    private Transformations() {
    }

    public static <X, Y> LiveData<Y> map(LiveData<X> liveData, final Function<X, Y> function) {
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Observer<X>() { // from class: androidx.lifecycle.Transformations.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(X x) {
                MediatorLiveData.this.setValue(function.apply(x));
            }
        });
        return mediatorLiveData;
    }

    public static <X, Y> LiveData<Y> switchMap(LiveData<X> liveData, final Function<X, LiveData<Y>> function) {
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Observer<X>() { // from class: androidx.lifecycle.Transformations.2
            LiveData<Y> mSource;

            @Override // androidx.lifecycle.Observer
            public void onChanged(X x) {
                LiveData<Y> liveData2 = (LiveData) Function.this.apply(x);
                Object obj = this.mSource;
                if (obj == liveData2) {
                    return;
                }
                if (obj != null) {
                    mediatorLiveData.removeSource(obj);
                }
                this.mSource = liveData2;
                if (liveData2 != 0) {
                    mediatorLiveData.addSource(liveData2, new Observer<Y>() { // from class: androidx.lifecycle.Transformations.2.1
                        @Override // androidx.lifecycle.Observer
                        public void onChanged(Y y) {
                            mediatorLiveData.setValue(y);
                        }
                    });
                }
            }
        });
        return mediatorLiveData;
    }

    /* JADX INFO: Add missing generic type declarations: [X] */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: androidx.lifecycle.Transformations$3 */
    /* loaded from: classes.dex */
    static class C04573<X> implements Observer<X> {
        boolean mFirstTime = true;
        final /* synthetic */ MediatorLiveData val$outputLiveData;

        C04573(MediatorLiveData mediatorLiveData) {
            this.val$outputLiveData = mediatorLiveData;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(X x) {
            T value = this.val$outputLiveData.getValue();
            if (this.mFirstTime || ((value == 0 && x != null) || !(value == 0 || value.equals(x)))) {
                this.mFirstTime = false;
                this.val$outputLiveData.setValue(x);
            }
        }
    }
}
