package org.hash.mock.debug.view.radio;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.pudutech.disinfect.baselib.C4429R;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class GroupRadioView extends LinearLayout {
    private int check;
    private String groupBtnName;
    private boolean isAdded;
    private OnGroupBtnClickListener listener;
    private Context mContext;
    private RadioGroup mRg;

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public interface OnGroupBtnClickListener {
        void groupBtnClick(int i);
    }

    public GroupRadioView(Context context) {
        this(context, null);
    }

    public GroupRadioView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GroupRadioView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.groupBtnName = "A#B";
        this.mContext = context;
        initContentView();
        initData();
    }

    public GroupRadioView setGroupBtnName(String str) {
        this.groupBtnName = str;
        initView();
        return this;
    }

    private void initContentView() {
        View.inflate(this.mContext, C4429R.layout.debug_view_item_group_buttons, this);
        this.mRg = (RadioGroup) findViewById(C4429R.id.rg_item_group_btn);
    }

    private void initView() {
        if (this.isAdded) {
            return;
        }
        String[] split = this.groupBtnName.split(MqttTopic.MULTI_LEVEL_WILDCARD);
        if (split.length < 2) {
            return;
        }
        for (int i = 0; i < split.length; i++) {
            RadioButton radioButton = (RadioButton) View.inflate(this.mContext, C4429R.layout.debug_view_item_group_radio_button, null);
            if (split.length == 2) {
                if (i == 0) {
                    radioButton.setBackgroundResource(C4429R.drawable.debug_left_button_selector);
                } else if (i == 1) {
                    radioButton.setBackgroundResource(C4429R.drawable.debug_right_button_selector);
                }
            } else if (i == 0) {
                radioButton.setBackgroundResource(C4429R.drawable.debug_left_button_selector);
            } else if (i == split.length - 1) {
                radioButton.setBackgroundResource(C4429R.drawable.debug_right_button_selector);
            } else {
                radioButton.setBackgroundResource(C4429R.drawable.debug_mid_button_selector);
            }
            if (i == this.check) {
                radioButton.setChecked(true);
            }
            radioButton.setId(i);
            radioButton.setTag(Integer.valueOf(i));
            radioButton.setText(split[i]);
            this.mRg.addView(radioButton);
        }
        this.isAdded = true;
        postInvalidate();
        requestLayout();
    }

    private void initData() {
        this.mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: org.hash.mock.debug.view.radio.GroupRadioView.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) GroupRadioView.this.findViewById(i);
                if (radioButton == null || !radioButton.isChecked() || GroupRadioView.this.listener == null) {
                    return;
                }
                GroupRadioView.this.listener.groupBtnClick(((Integer) radioButton.getTag()).intValue());
            }
        });
    }

    public void setCheck(int i) {
        this.check = i;
    }

    public void setOnGroupBtnClickListener(OnGroupBtnClickListener onGroupBtnClickListener) {
        this.listener = onGroupBtnClickListener;
    }
}
