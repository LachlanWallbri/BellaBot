package org.mozilla.javascript.tools.debugger;

import javax.swing.JTable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* compiled from: SwingGui.java */
/* loaded from: classes2.dex */
public class Evaluator extends JTable {
    private static final long serialVersionUID = 8133672432982594256L;
    MyTableModel tableModel;

    public Evaluator(SwingGui swingGui) {
        super(new MyTableModel(swingGui));
        this.tableModel = getModel();
    }
}
