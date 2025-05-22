package com.pudu.loracall.library.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.pudu.library.loracall.bean.LoRaConfigParam;
import com.pudu.library.loracall.bean.LoRaVersionParam;
import com.pudu.library.loracall.utils.DetailBindingAdaptersKt;
import com.pudu.library.loracall.viewModel.LoRaConfigViewModel;
import com.pudu.loracall.library.BR;
import com.pudu.loracall.library.C3965R;

/* loaded from: classes4.dex */
public class LoraHomeFragmentBindingImpl extends LoraHomeFragmentBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final EditText mboundView2;
    private InverseBindingListener mboundView2androidTextAttrChanged;
    private final EditText mboundView3;
    private InverseBindingListener mboundView3androidTextAttrChanged;
    private final EditText mboundView5;
    private InverseBindingListener mboundView5androidTextAttrChanged;
    private final EditText mboundView6;
    private InverseBindingListener mboundView6androidTextAttrChanged;
    private final TextView mboundView9;
    private InverseBindingListener networkP1EditandroidTextAttrChanged;
    private InverseBindingListener networkP2EditandroidTextAttrChanged;

    static {
        sViewsWithIds.put(C3965R.id.topItemView, 12);
        sViewsWithIds.put(C3965R.id.checkIdView, 13);
        sViewsWithIds.put(C3965R.id.checkHintView, 14);
        sViewsWithIds.put(C3965R.id.deleteButton, 15);
        sViewsWithIds.put(C3965R.id.topRecyclerView, 16);
        sViewsWithIds.put(C3965R.id.recyclerView, 17);
        sViewsWithIds.put(C3965R.id.emptyLayout, 18);
        sViewsWithIds.put(C3965R.id.configButton, 19);
        sViewsWithIds.put(C3965R.id.configLayout, 20);
        sViewsWithIds.put(C3965R.id.powerView, 21);
        sViewsWithIds.put(C3965R.id.frequencyView, 22);
        sViewsWithIds.put(C3965R.id.networkView, 23);
        sViewsWithIds.put(C3965R.id.broadView, 24);
        sViewsWithIds.put(C3965R.id.net1, 25);
        sViewsWithIds.put(C3965R.id.net2, 26);
        sViewsWithIds.put(C3965R.id.okButton, 27);
        sViewsWithIds.put(C3965R.id.signalTestLayout, 28);
        sViewsWithIds.put(C3965R.id.netStateView, 29);
        sViewsWithIds.put(C3965R.id.stateRefreshView, 30);
        sViewsWithIds.put(C3965R.id.baseInfoLayout, 31);
        sViewsWithIds.put(C3965R.id.aboutButton, 32);
        sViewsWithIds.put(C3965R.id.aboutLayout, 33);
        sViewsWithIds.put(C3965R.id.loRaMacView, 34);
        sViewsWithIds.put(C3965R.id.updateBut, 35);
    }

    public LoraHomeFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 36, sIncludes, sViewsWithIds));
    }

    private LoraHomeFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, (TextView) objArr[32], (LinearLayout) objArr[33], (LinearLayout) objArr[31], (TextView) objArr[24], (TextView) objArr[14], (TextView) objArr[13], (TextView) objArr[19], (LinearLayout) objArr[20], (ImageView) objArr[15], (TextView) objArr[18], (TextView) objArr[4], (TextView) objArr[22], (TextView) objArr[34], (TextView) objArr[25], (TextView) objArr[26], (TextView) objArr[29], (EditText) objArr[7], (EditText) objArr[8], (TextView) objArr[23], (Button) objArr[27], (TextView) objArr[21], (RecyclerView) objArr[17], (RelativeLayout) objArr[28], (ImageView) objArr[30], (View) objArr[1], (View) objArr[12], (RecyclerView) objArr[16], (TextView) objArr[35]);
        this.mboundView2androidTextAttrChanged = new InverseBindingListener() { // from class: com.pudu.loracall.library.databinding.LoraHomeFragmentBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(LoraHomeFragmentBindingImpl.this.mboundView2);
                LoRaConfigViewModel loRaConfigViewModel = LoraHomeFragmentBindingImpl.this.mViewModel;
                if (loRaConfigViewModel != null) {
                    MutableLiveData<LoRaConfigParam> loRaConfig = loRaConfigViewModel.getLoRaConfig();
                    if (loRaConfig != null) {
                        LoRaConfigParam value = loRaConfig.getValue();
                        if (value != null) {
                            value.setSendPower(textString);
                        }
                    }
                }
            }
        };
        this.mboundView3androidTextAttrChanged = new InverseBindingListener() { // from class: com.pudu.loracall.library.databinding.LoraHomeFragmentBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(LoraHomeFragmentBindingImpl.this.mboundView3);
                LoRaConfigViewModel loRaConfigViewModel = LoraHomeFragmentBindingImpl.this.mViewModel;
                if (loRaConfigViewModel != null) {
                    MutableLiveData<LoRaConfigParam> loRaConfig = loRaConfigViewModel.getLoRaConfig();
                    if (loRaConfig != null) {
                        LoRaConfigParam value = loRaConfig.getValue();
                        if (value != null) {
                            value.setReceivePL(textString);
                        }
                    }
                }
            }
        };
        this.mboundView5androidTextAttrChanged = new InverseBindingListener() { // from class: com.pudu.loracall.library.databinding.LoraHomeFragmentBindingImpl.3
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(LoraHomeFragmentBindingImpl.this.mboundView5);
                LoRaConfigViewModel loRaConfigViewModel = LoraHomeFragmentBindingImpl.this.mViewModel;
                if (loRaConfigViewModel != null) {
                    MutableLiveData<LoRaConfigParam> loRaConfig = loRaConfigViewModel.getLoRaConfig();
                    if (loRaConfig != null) {
                        LoRaConfigParam value = loRaConfig.getValue();
                        if (value != null) {
                            value.setNetworkId(textString);
                        }
                    }
                }
            }
        };
        this.mboundView6androidTextAttrChanged = new InverseBindingListener() { // from class: com.pudu.loracall.library.databinding.LoraHomeFragmentBindingImpl.4
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(LoraHomeFragmentBindingImpl.this.mboundView6);
                LoRaConfigViewModel loRaConfigViewModel = LoraHomeFragmentBindingImpl.this.mViewModel;
                if (loRaConfigViewModel != null) {
                    MutableLiveData<LoRaConfigParam> loRaConfig = loRaConfigViewModel.getLoRaConfig();
                    if (loRaConfig != null) {
                        LoRaConfigParam value = loRaConfig.getValue();
                        if (value != null) {
                            value.setBroadCastTime(textString);
                        }
                    }
                }
            }
        };
        this.networkP1EditandroidTextAttrChanged = new InverseBindingListener() { // from class: com.pudu.loracall.library.databinding.LoraHomeFragmentBindingImpl.5
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(LoraHomeFragmentBindingImpl.this.networkP1Edit);
                LoRaConfigViewModel loRaConfigViewModel = LoraHomeFragmentBindingImpl.this.mViewModel;
                if (loRaConfigViewModel != null) {
                    MutableLiveData<LoRaConfigParam> loRaConfig = loRaConfigViewModel.getLoRaConfig();
                    if (loRaConfig != null) {
                        LoRaConfigParam value = loRaConfig.getValue();
                        if (value != null) {
                            value.setNetworkP1(textString);
                        }
                    }
                }
            }
        };
        this.networkP2EditandroidTextAttrChanged = new InverseBindingListener() { // from class: com.pudu.loracall.library.databinding.LoraHomeFragmentBindingImpl.6
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(LoraHomeFragmentBindingImpl.this.networkP2Edit);
                LoRaConfigViewModel loRaConfigViewModel = LoraHomeFragmentBindingImpl.this.mViewModel;
                if (loRaConfigViewModel != null) {
                    MutableLiveData<LoRaConfigParam> loRaConfig = loRaConfigViewModel.getLoRaConfig();
                    if (loRaConfig != null) {
                        LoRaConfigParam value = loRaConfig.getValue();
                        if (value != null) {
                            value.setNetworkP2(textString);
                        }
                    }
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.expandTextView.setTag(null);
        this.mboundView0 = (NestedScrollView) objArr[0];
        this.mboundView0.setTag(null);
        this.mboundView10 = (TextView) objArr[10];
        this.mboundView10.setTag(null);
        this.mboundView11 = (TextView) objArr[11];
        this.mboundView11.setTag(null);
        this.mboundView2 = (EditText) objArr[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (EditText) objArr[3];
        this.mboundView3.setTag(null);
        this.mboundView5 = (EditText) objArr[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (EditText) objArr[6];
        this.mboundView6.setTag(null);
        this.mboundView9 = (TextView) objArr[9];
        this.mboundView9.setTag(null);
        this.networkP1Edit.setTag(null);
        this.networkP2Edit.setTag(null);
        this.stateView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, Object obj) {
        if (BR.viewModel != i) {
            return false;
        }
        setViewModel((LoRaConfigViewModel) obj);
        return true;
    }

    @Override // com.pudu.loracall.library.databinding.LoraHomeFragmentBinding
    public void setViewModel(LoRaConfigViewModel loRaConfigViewModel) {
        this.mViewModel = loRaConfigViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeViewModelLoRaConfigGetValue((LoRaConfigParam) obj, i2);
        }
        if (i == 1) {
            return onChangeViewModelLoRaVersion((MutableLiveData) obj, i2);
        }
        if (i != 2) {
            return false;
        }
        return onChangeViewModelLoRaConfig((MutableLiveData) obj, i2);
    }

    private boolean onChangeViewModelLoRaConfigGetValue(LoRaConfigParam loRaConfigParam, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (i != BR.expand) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelLoRaVersion(MutableLiveData<LoRaVersionParam> mutableLiveData, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelLoRaConfig(MutableLiveData<LoRaConfigParam> mutableLiveData, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b5  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void executeBindings() {
        long j;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        String str16;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        LoRaConfigViewModel loRaConfigViewModel = this.mViewModel;
        if ((63 & j) != 0) {
            if ((j & 42) != 0) {
                LiveData<?> loRaVersion = loRaConfigViewModel != null ? loRaConfigViewModel.getLoRaVersion() : null;
                updateLiveDataRegistration(1, loRaVersion);
                LoRaVersionParam value = loRaVersion != null ? loRaVersion.getValue() : null;
                if (value != null) {
                    str4 = value.getHdVersion();
                    str5 = value.getBootLoaderVersion();
                    str2 = value.getShowVersion();
                    if ((j & 61) == 0) {
                        LiveData<?> loRaConfig = loRaConfigViewModel != null ? loRaConfigViewModel.getLoRaConfig() : null;
                        updateLiveDataRegistration(2, loRaConfig);
                        LoRaConfigParam value2 = loRaConfig != null ? loRaConfig.getValue() : null;
                        updateRegistration(0, value2);
                        if ((j & 45) == 0 || value2 == null) {
                            str11 = null;
                            str12 = null;
                            str13 = null;
                            str14 = null;
                            str15 = null;
                            str16 = null;
                        } else {
                            str11 = value2.getNetworkP1();
                            str12 = value2.getSendPower();
                            str13 = value2.getBroadCastTime();
                            str14 = value2.getNetworkP2();
                            str15 = value2.getNetworkId();
                            str16 = value2.getReceivePL();
                        }
                        if (value2 != null) {
                            str3 = value2.getExpand();
                            str9 = str11;
                            str = str12;
                            str8 = str13;
                            str10 = str14;
                            str7 = str15;
                            str6 = str16;
                        } else {
                            str9 = str11;
                            str = str12;
                            str8 = str13;
                            str10 = str14;
                            str7 = str15;
                            str6 = str16;
                            str3 = null;
                        }
                        if ((61 & j) != 0) {
                            TextViewBindingAdapter.setText(this.expandTextView, str3);
                        }
                        if ((42 & j) != 0) {
                            TextViewBindingAdapter.setText(this.mboundView10, str5);
                            TextViewBindingAdapter.setText(this.mboundView11, str2);
                            TextViewBindingAdapter.setText(this.mboundView9, str4);
                        }
                        if ((j & 45) != 0) {
                            TextViewBindingAdapter.setText(this.mboundView2, str);
                            TextViewBindingAdapter.setText(this.mboundView3, str6);
                            TextViewBindingAdapter.setText(this.mboundView5, str7);
                            TextViewBindingAdapter.setText(this.mboundView6, str8);
                            TextViewBindingAdapter.setText(this.networkP1Edit, str9);
                            TextViewBindingAdapter.setText(this.networkP2Edit, str10);
                        }
                        if ((j & 32) == 0) {
                            TextViewBindingAdapter.BeforeTextChanged beforeTextChanged = (TextViewBindingAdapter.BeforeTextChanged) null;
                            TextViewBindingAdapter.OnTextChanged onTextChanged = (TextViewBindingAdapter.OnTextChanged) null;
                            TextViewBindingAdapter.AfterTextChanged afterTextChanged = (TextViewBindingAdapter.AfterTextChanged) null;
                            TextViewBindingAdapter.setTextWatcher(this.mboundView2, beforeTextChanged, onTextChanged, afterTextChanged, this.mboundView2androidTextAttrChanged);
                            TextViewBindingAdapter.setTextWatcher(this.mboundView3, beforeTextChanged, onTextChanged, afterTextChanged, this.mboundView3androidTextAttrChanged);
                            TextViewBindingAdapter.setTextWatcher(this.mboundView5, beforeTextChanged, onTextChanged, afterTextChanged, this.mboundView5androidTextAttrChanged);
                            TextViewBindingAdapter.setTextWatcher(this.mboundView6, beforeTextChanged, onTextChanged, afterTextChanged, this.mboundView6androidTextAttrChanged);
                            TextViewBindingAdapter.setTextWatcher(this.networkP1Edit, beforeTextChanged, onTextChanged, afterTextChanged, this.networkP1EditandroidTextAttrChanged);
                            TextViewBindingAdapter.setTextWatcher(this.networkP2Edit, beforeTextChanged, onTextChanged, afterTextChanged, this.networkP2EditandroidTextAttrChanged);
                            DetailBindingAdaptersKt.viewRadius(this.stateView, 4);
                            return;
                        }
                        return;
                    }
                    str = null;
                    str3 = null;
                }
            }
            str2 = null;
            str4 = null;
            str5 = null;
            if ((j & 61) == 0) {
            }
        } else {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
        }
        str6 = null;
        str7 = null;
        str8 = null;
        str9 = null;
        str10 = null;
        if ((61 & j) != 0) {
        }
        if ((42 & j) != 0) {
        }
        if ((j & 45) != 0) {
        }
        if ((j & 32) == 0) {
        }
    }
}
