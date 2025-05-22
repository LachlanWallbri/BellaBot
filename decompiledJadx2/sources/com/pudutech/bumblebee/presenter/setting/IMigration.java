package com.pudutech.bumblebee.presenter.setting;

import kotlin.Metadata;

/* compiled from: FunctionUpdateHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H&R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/setting/IMigration;", "", "startVersion", "", "endVersion", "(II)V", "getEndVersion", "()I", "getStartVersion", "update", "", "newVersion", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class IMigration {
    private final int endVersion;
    private final int startVersion;

    public abstract void update(int newVersion);

    public IMigration(int i, int i2) {
        this.startVersion = i;
        this.endVersion = i2;
    }

    public final int getEndVersion() {
        return this.endVersion;
    }

    public final int getStartVersion() {
        return this.startVersion;
    }
}
