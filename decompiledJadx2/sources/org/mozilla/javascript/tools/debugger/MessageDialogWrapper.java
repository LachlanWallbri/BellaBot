package org.mozilla.javascript.tools.debugger;

import java.awt.Component;
import javax.swing.JOptionPane;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* compiled from: SwingGui.java */
/* loaded from: classes2.dex */
class MessageDialogWrapper {
    MessageDialogWrapper() {
    }

    public static void showMessageDialog(Component component, String str, String str2, int i) {
        if (str.length() > 60) {
            StringBuilder sb = new StringBuilder();
            int length = str.length();
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                char charAt = str.charAt(i2);
                sb.append(charAt);
                if (Character.isWhitespace(charAt)) {
                    int i4 = i2 + 1;
                    while (i4 < length && !Character.isWhitespace(str.charAt(i4))) {
                        i4++;
                    }
                    if (i4 < length && (i4 - i2) + i3 > 60) {
                        sb.append('\n');
                        i3 = 0;
                    }
                }
                i2++;
                i3++;
            }
            str = sb.toString();
        }
        JOptionPane.showMessageDialog(component, str, str2, i);
    }
}
