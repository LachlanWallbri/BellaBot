package org.mozilla.javascript.tools.debugger.treetable;

import javax.swing.tree.TreeModel;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface TreeTableModel extends TreeModel {
    Class<?> getColumnClass(int i);

    int getColumnCount();

    String getColumnName(int i);

    Object getValueAt(Object obj, int i);

    boolean isCellEditable(Object obj, int i);

    void setValueAt(Object obj, Object obj2, int i);
}
