package com.google.android.material.timepicker;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.C1653R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public final class MaterialTimePicker extends DialogFragment {
    public static final int INPUT_MODE_CLOCK = 0;
    static final String INPUT_MODE_EXTRA = "TIME_PICKER_INPUT_MODE";
    public static final int INPUT_MODE_KEYBOARD = 1;
    static final String TIME_MODEL_EXTRA = "TIME_PICKER_TIME_MODEL";
    private TimePickerPresenter activePresenter;
    private OnTimeSetListener listener;
    private MaterialButton modeButton;
    private LinearLayout textInputView;
    private TimePickerClockPresenter timePickerClockPresenter;
    private TimePickerTextInputPresenter timePickerTextInputPresenter;
    private TimePickerView timePickerView;
    private static final int CLOCK_ICON = C1653R.drawable.ic_clock_black_24dp;
    private static final int KEYBOARD_ICON = C1653R.drawable.ic_keyboard_black_24dp;
    private int inputMode = 0;
    private TimeModel time = new TimeModel();

    /* loaded from: classes.dex */
    public interface OnTimeSetListener {
        void onTimeSet(MaterialTimePicker materialTimePicker);
    }

    public static MaterialTimePicker newInstance() {
        return new MaterialTimePicker();
    }

    public void setHour(int i) {
        this.time.setHourOfDay(i);
    }

    public void setMinute(int i) {
        this.time.setMinute(i);
    }

    public int getMinute() {
        return this.time.minute;
    }

    public int getHour() {
        return this.time.hour % 24;
    }

    public void setTimeFormat(int i) {
        this.time = new TimeModel(i);
    }

    public void setInputMode(int i) {
        this.inputMode = i;
    }

    public int getInputMode() {
        return this.inputMode;
    }

    @Override // androidx.fragment.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle) {
        TypedValue resolve = MaterialAttributes.resolve(requireContext(), C1653R.attr.materialTimePickerTheme);
        Dialog dialog = new Dialog(requireContext(), resolve == null ? 0 : resolve.data);
        Context context = dialog.getContext();
        int resolveOrThrow = MaterialAttributes.resolveOrThrow(context, C1653R.attr.colorSurface, MaterialTimePicker.class.getCanonicalName());
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(context, null, 0, C1653R.style.Widget_MaterialComponents_TimePicker);
        materialShapeDrawable.initializeElevationOverlay(context);
        materialShapeDrawable.setFillColor(ColorStateList.valueOf(resolveOrThrow));
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(materialShapeDrawable);
        window.requestFeature(1);
        window.setLayout(-2, -2);
        return dialog;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        restoreState(bundle);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(TIME_MODEL_EXTRA, this.time);
        bundle.putInt(INPUT_MODE_EXTRA, this.inputMode);
    }

    private void restoreState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        TimeModel timeModel = (TimeModel) bundle.getParcelable(TIME_MODEL_EXTRA);
        this.time = timeModel;
        if (timeModel == null) {
            this.time = new TimeModel();
        }
        this.inputMode = bundle.getInt(INPUT_MODE_EXTRA, 0);
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(C1653R.layout.material_timepicker_dialog, viewGroup);
        this.timePickerView = (TimePickerView) viewGroup2.findViewById(C1653R.id.material_timepicker_view);
        this.textInputView = (LinearLayout) viewGroup2.findViewById(C1653R.id.material_textinput_timepicker);
        MaterialButton materialButton = (MaterialButton) viewGroup2.findViewById(C1653R.id.material_timepicker_mode_button);
        this.modeButton = materialButton;
        updateInputMode(materialButton);
        ((MaterialButton) viewGroup2.findViewById(C1653R.id.material_timepicker_ok_button)).setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.timepicker.MaterialTimePicker.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MaterialTimePicker.this.listener != null) {
                    MaterialTimePicker.this.listener.onTimeSet(MaterialTimePicker.this);
                }
                MaterialTimePicker.this.dismiss();
            }
        });
        ((MaterialButton) viewGroup2.findViewById(C1653R.id.material_timepicker_cancel_button)).setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.timepicker.MaterialTimePicker.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MaterialTimePicker.this.dismiss();
            }
        });
        this.modeButton.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.timepicker.MaterialTimePicker.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MaterialTimePicker materialTimePicker = MaterialTimePicker.this;
                materialTimePicker.inputMode = materialTimePicker.inputMode == 0 ? 1 : 0;
                MaterialTimePicker materialTimePicker2 = MaterialTimePicker.this;
                materialTimePicker2.updateInputMode(materialTimePicker2.modeButton);
            }
        });
        return viewGroup2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateInputMode(MaterialButton materialButton) {
        materialButton.setChecked(false);
        TimePickerPresenter timePickerPresenter = this.activePresenter;
        if (timePickerPresenter != null) {
            timePickerPresenter.hide();
        }
        TimePickerPresenter initializeOrRetrieveActivePresenterForMode = initializeOrRetrieveActivePresenterForMode(this.inputMode);
        this.activePresenter = initializeOrRetrieveActivePresenterForMode;
        initializeOrRetrieveActivePresenterForMode.show();
        this.activePresenter.invalidate();
        materialButton.setIconResource(iconForMode(this.inputMode));
    }

    private TimePickerPresenter initializeOrRetrieveActivePresenterForMode(int i) {
        if (i == 0) {
            TimePickerClockPresenter timePickerClockPresenter = this.timePickerClockPresenter;
            if (timePickerClockPresenter == null) {
                timePickerClockPresenter = new TimePickerClockPresenter(this.timePickerView, this.time);
            }
            this.timePickerClockPresenter = timePickerClockPresenter;
            return timePickerClockPresenter;
        }
        if (this.timePickerTextInputPresenter == null) {
            this.timePickerTextInputPresenter = new TimePickerTextInputPresenter(this.textInputView, this.time);
        }
        return this.timePickerTextInputPresenter;
    }

    /* renamed from: com.google.android.material.timepicker.MaterialTimePicker$4 */
    /* loaded from: classes2.dex */
    class ViewOnClickListenerC18934 implements View.OnClickListener {
        ViewOnClickListenerC18934() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MaterialTimePicker materialTimePicker = MaterialTimePicker.this;
            MaterialTimePicker.access$502(materialTimePicker, MaterialTimePicker.access$500(materialTimePicker) == 0 ? 1 : 0);
            MaterialTimePicker materialTimePicker2 = MaterialTimePicker.this;
            MaterialTimePicker.access$700(materialTimePicker2, MaterialTimePicker.access$600(materialTimePicker2));
        }
    }

    private static int iconForMode(int i) {
        if (i == 0) {
            return KEYBOARD_ICON;
        }
        if (i == 1) {
            return CLOCK_ICON;
        }
        throw new IllegalArgumentException("no icon for mode: " + i);
    }

    public void setListener(OnTimeSetListener onTimeSetListener) {
        this.listener = onTimeSetListener;
    }

    TimePickerClockPresenter getTimePickerClockPresenter() {
        return this.timePickerClockPresenter;
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private int inputMode;
        private CharSequence titleText;
        private TimeModel time = new TimeModel();
        private int titleTextResId = 0;
        private int overrideThemeResId = 0;

        public Builder setInputMode(int i) {
            this.inputMode = i;
            return this;
        }

        public Builder setHour(int i) {
            this.time.setHourOfDay(i);
            return this;
        }

        public Builder setMinute(int i) {
            this.time.setMinute(i);
            return this;
        }

        public Builder setTimeFormat(int i) {
            int i2 = this.time.hour;
            int i3 = this.time.minute;
            this.time = new TimeModel(i);
            this.time.setMinute(i3);
            this.time.setHourOfDay(i2);
            return this;
        }

        public Builder setTitleText(int i) {
            this.titleTextResId = i;
            return this;
        }

        public Builder setTitleText(CharSequence charSequence) {
            this.titleText = charSequence;
            return this;
        }

        public Builder setTheme(int i) {
            this.overrideThemeResId = i;
            return this;
        }

        public MaterialTimePicker build() {
            return MaterialTimePicker.access$1100(this);
        }
    }
}
