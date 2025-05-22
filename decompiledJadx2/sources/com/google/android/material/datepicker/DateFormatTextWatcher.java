package com.google.android.material.datepicker;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.material.C1653R;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
abstract class DateFormatTextWatcher extends TextWatcherAdapter {
    private final CalendarConstraints constraints;
    private final DateFormat dateFormat;
    private final String formatHint;
    private final String outOfRange;
    private final TextInputLayout textInputLayout;

    void onInvalidDate() {
    }

    abstract void onValidDate(Long l);

    /* JADX INFO: Access modifiers changed from: package-private */
    public DateFormatTextWatcher(String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints) {
        this.formatHint = str;
        this.dateFormat = dateFormat;
        this.textInputLayout = textInputLayout;
        this.constraints = calendarConstraints;
        this.outOfRange = textInputLayout.getContext().getString(C1653R.string.mtrl_picker_out_of_range);
    }

    /* renamed from: com.google.android.material.datepicker.DateFormatTextWatcher$1 */
    /* loaded from: classes2.dex */
    class RunnableC17121 implements Runnable {
        final /* synthetic */ String val$formatHint;

        RunnableC17121(String str) {
            this.val$formatHint = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout access$000 = DateFormatTextWatcher.access$000(DateFormatTextWatcher.this);
            DateFormat access$100 = DateFormatTextWatcher.access$100(DateFormatTextWatcher.this);
            Context context = access$000.getContext();
            access$000.setError(context.getString(C1653R.string.mtrl_picker_invalid_format) + "\n" + String.format(context.getString(C1653R.string.mtrl_picker_invalid_format_use), this.val$formatHint) + "\n" + String.format(context.getString(C1653R.string.mtrl_picker_invalid_format_example), access$100.format(new Date(UtcDates.getTodayCalendar().getTimeInMillis()))));
            DateFormatTextWatcher.this.onInvalidDate();
        }
    }

    @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (TextUtils.isEmpty(charSequence)) {
            this.textInputLayout.setError(null);
            onValidDate(null);
            return;
        }
        try {
            Date parse = this.dateFormat.parse(charSequence.toString());
            this.textInputLayout.setError(null);
            long time = parse.getTime();
            if (this.constraints.getDateValidator().isValid(time) && this.constraints.isWithinBounds(time)) {
                onValidDate(Long.valueOf(parse.getTime()));
            } else {
                this.textInputLayout.setError(String.format(this.outOfRange, DateStrings.getDateString(time)));
                onInvalidDate();
            }
        } catch (ParseException unused) {
            String string = this.textInputLayout.getContext().getString(C1653R.string.mtrl_picker_invalid_format);
            String format = String.format(this.textInputLayout.getContext().getString(C1653R.string.mtrl_picker_invalid_format_use), this.formatHint);
            String format2 = String.format(this.textInputLayout.getContext().getString(C1653R.string.mtrl_picker_invalid_format_example), this.dateFormat.format(new Date(UtcDates.getTodayCalendar().getTimeInMillis())));
            this.textInputLayout.setError(string + "\n" + format + "\n" + format2);
            onInvalidDate();
        }
    }

    /* renamed from: com.google.android.material.datepicker.DateFormatTextWatcher$2 */
    /* loaded from: classes2.dex */
    class RunnableC17132 implements Runnable {
        final /* synthetic */ long val$milliseconds;

        RunnableC17132(long j) {
            this.val$milliseconds = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            DateFormatTextWatcher.access$000(DateFormatTextWatcher.this).setError(String.format(DateFormatTextWatcher.access$200(DateFormatTextWatcher.this), DateStrings.getDateString(this.val$milliseconds)));
            DateFormatTextWatcher.this.onInvalidDate();
        }
    }
}
