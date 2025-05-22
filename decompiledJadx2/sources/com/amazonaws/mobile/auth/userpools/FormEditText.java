package com.amazonaws.mobile.auth.userpools;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.amazonaws.mobile.auth.core.signin.p046ui.DisplayUtils;
import java.util.Locale;

/* loaded from: classes.dex */
public class FormEditText extends LinearLayout {
    private static final int BIT_FOR_SHOWING_PASSWORD = 16;
    private static final int EDIT_TEXT_ID = 3842;
    private static final int TEXT_VIEW_ID = 3841;
    private LinearLayout editFieldLayout;
    private EditText editText;
    private boolean hasSetMinimumSize;
    private TextView showPasswordToggleTextView;
    private TextView textView;
    private static final int TEXT_VIEW_TOP_MARGIN = DisplayUtils.m502dp(5);
    private static final int EDIT_VIEW_BOTTOM_PADDING = DisplayUtils.m502dp(5);
    private static final int SHOW_PASSWORD_LEFT_RIGHT_MARGIN = DisplayUtils.m502dp(5);
    private static final int SHOW_PASSWORD_TOP_MARGIN = DisplayUtils.m502dp(-5);

    private int toViewId(int i) {
        return i;
    }

    public FormEditText(Context context, int i, String str) {
        super(context);
        this.showPasswordToggleTextView = null;
        this.hasSetMinimumSize = false;
        setOrientation(1);
        setGravity(16);
        this.textView = new TextView(context);
        this.textView.setText(str.toUpperCase(Locale.getDefault()));
        this.textView.setId(toViewId(TEXT_VIEW_ID));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, TEXT_VIEW_TOP_MARGIN, 0, 0);
        addView(this.textView, layoutParams);
        this.textView.setVisibility(4);
        this.editText = new EditText(context);
        this.editText.setSingleLine();
        this.editText.setInputType(i);
        this.editText.setBackgroundColor(0);
        this.editText.setPadding(0, DisplayUtils.m502dp(2), 0, DisplayUtils.m502dp(2) + EDIT_VIEW_BOTTOM_PADDING);
        this.editText.setId(toViewId(EDIT_TEXT_ID));
        this.editText.setHint(str);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.setMargins(0, 0, 0, 0);
        if ((i & 128) > 0) {
            this.editFieldLayout = new LinearLayout(context);
            this.editFieldLayout.setOrientation(0);
            layoutParams2.gravity = GravityCompat.START;
            layoutParams2.weight = 1.0f;
            this.editFieldLayout.addView(this.editText, layoutParams2);
            this.showPasswordToggleTextView = new TextView(context);
            setupShowHidePassword();
            addView(this.editFieldLayout);
        } else {
            addView(this.editText, layoutParams2);
        }
        setupTextChangedListener();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.hasSetMinimumSize) {
            return;
        }
        setMinimumHeight(this.textView.getMeasuredHeight() + TEXT_VIEW_TOP_MARGIN + this.editText.getMeasuredHeight());
        this.textView.setVisibility(8);
        this.hasSetMinimumSize = true;
    }

    private void setupShowHidePassword() {
        final String string = getResources().getString(C1190R.string.sign_in_show_password);
        final String string2 = getResources().getString(C1190R.string.sign_in_hide_password);
        this.showPasswordToggleTextView.setText(string);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        int i = SHOW_PASSWORD_LEFT_RIGHT_MARGIN;
        layoutParams.setMargins(i, SHOW_PASSWORD_TOP_MARGIN, i, 0);
        layoutParams.gravity = 8388629;
        this.editFieldLayout.addView(this.showPasswordToggleTextView, layoutParams);
        this.showPasswordToggleTextView.setVisibility(8);
        this.showPasswordToggleTextView.setOnClickListener(new View.OnClickListener() { // from class: com.amazonaws.mobile.auth.userpools.FormEditText.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CharSequence text = FormEditText.this.showPasswordToggleTextView.getText();
                FormEditText.this.showPasswordToggleTextView.setText(text.equals(string) ? string2 : string);
                int selectionStart = FormEditText.this.editText.getSelectionStart();
                int selectionEnd = FormEditText.this.editText.getSelectionEnd();
                if (text.equals(string)) {
                    FormEditText.this.editText.setInputType(FormEditText.this.editText.getInputType() | 16);
                } else {
                    FormEditText.this.editText.setInputType(FormEditText.this.editText.getInputType() & (-17));
                }
                FormEditText.this.editText.setSelection(selectionStart, selectionEnd);
            }
        });
    }

    private void setupTextChangedListener() {
        this.editText.addTextChangedListener(new TextWatcher() { // from class: com.amazonaws.mobile.auth.userpools.FormEditText.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            private void handleFloatingTextVisibility() {
                if (FormEditText.this.editText.getText().length() == 0) {
                    if (FormEditText.this.hasSetMinimumSize) {
                        FormEditText.this.textView.setVisibility(8);
                    }
                    FormEditText.this.editText.setPadding(0, DisplayUtils.m502dp(2), 0, DisplayUtils.m502dp(2) + FormEditText.EDIT_VIEW_BOTTOM_PADDING);
                    if (FormEditText.this.showPasswordToggleTextView != null) {
                        FormEditText.this.showPasswordToggleTextView.setVisibility(8);
                        return;
                    }
                    return;
                }
                FormEditText.this.textView.setVisibility(0);
                FormEditText.this.editText.setPadding(0, DisplayUtils.m502dp(1), 0, DisplayUtils.m502dp(3) + FormEditText.EDIT_VIEW_BOTTOM_PADDING);
                if (FormEditText.this.showPasswordToggleTextView != null) {
                    FormEditText.this.showPasswordToggleTextView.setVisibility(0);
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                handleFloatingTextVisibility();
            }
        });
    }

    public EditText getEditTextView() {
        return this.editText;
    }
}
