package com.pudutech.robot.module.report.protocol.bean;

import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class ErrorString {
    public String error_type = "";
    public String detail = "";

    /* renamed from: id */
    public String f7202id = "";
    public int grade = 3;

    public void setGrade(String str) {
        if (MoveError.LEVEL_FATAL.equals(str)) {
            this.grade = 1;
        } else if (MoveError.LEVEL_ERROR.equals(str)) {
            this.grade = 2;
        } else {
            this.grade = 3;
        }
    }
}
