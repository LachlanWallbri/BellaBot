package com.pudutech.mpmodule;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.pudutech.mpmodule.p060ui.widget.CStateRecyclerView;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class AddMusicActivity_ViewBinding implements Unbinder {
    private AddMusicActivity target;
    private View view7f0b0059;

    public AddMusicActivity_ViewBinding(AddMusicActivity addMusicActivity) {
        this(addMusicActivity, addMusicActivity.getWindow().getDecorView());
    }

    public AddMusicActivity_ViewBinding(final AddMusicActivity addMusicActivity, View view) {
        this.target = addMusicActivity;
        addMusicActivity.mFileListRecyclerView = (CStateRecyclerView) Utils.findRequiredViewAsType(view, C5441R.id.repository_music_list, "field 'mFileListRecyclerView'", CStateRecyclerView.class);
        addMusicActivity.mAddMusicConfirm = (TextView) Utils.findRequiredViewAsType(view, C5441R.id.add_music_confirm_text, "field 'mAddMusicConfirm'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, C5441R.id.btn_confirm_inactivity, "method 'onClickListener'");
        this.view7f0b0059 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.pudutech.mpmodule.AddMusicActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                addMusicActivity.onClickListener(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AddMusicActivity addMusicActivity = this.target;
        if (addMusicActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        addMusicActivity.mFileListRecyclerView = null;
        addMusicActivity.mAddMusicConfirm = null;
        this.view7f0b0059.setOnClickListener(null);
        this.view7f0b0059 = null;
    }
}
