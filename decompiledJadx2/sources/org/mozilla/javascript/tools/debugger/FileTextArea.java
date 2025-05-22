package org.mozilla.javascript.tools.debugger;

import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextArea;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.BadLocationException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* compiled from: SwingGui.java */
/* loaded from: classes2.dex */
public class FileTextArea extends JTextArea implements ActionListener, PopupMenuListener, KeyListener, MouseListener {
    private static final long serialVersionUID = -25032065448563720L;
    private FilePopupMenu popup;

    /* renamed from: w */
    private FileWindow f10266w;

    public void mouseEntered(MouseEvent mouseEvent) {
    }

    public void mouseExited(MouseEvent mouseEvent) {
    }

    public void popupMenuCanceled(PopupMenuEvent popupMenuEvent) {
    }

    public void popupMenuWillBecomeInvisible(PopupMenuEvent popupMenuEvent) {
    }

    public void popupMenuWillBecomeVisible(PopupMenuEvent popupMenuEvent) {
    }

    public FileTextArea(FileWindow fileWindow) {
        this.f10266w = fileWindow;
        FilePopupMenu filePopupMenu = new FilePopupMenu(this);
        this.popup = filePopupMenu;
        filePopupMenu.addPopupMenuListener(this);
        addMouseListener(this);
        addKeyListener(this);
        setFont(new Font("Monospaced", 0, 12));
    }

    public void select(int i) {
        if (i >= 0) {
            try {
                int lineOfOffset = getLineOfOffset(i);
                Rectangle modelToView = modelToView(i);
                if (modelToView == null) {
                    select(i, i);
                    return;
                }
                try {
                    Rectangle modelToView2 = modelToView(getLineStartOffset(lineOfOffset + 1));
                    if (modelToView2 != null) {
                        modelToView = modelToView2;
                    }
                } catch (Exception unused) {
                }
                Rectangle viewRect = getParent().getViewRect();
                if (viewRect.y + viewRect.height > modelToView.y) {
                    select(i, i);
                    return;
                }
                modelToView.y += (viewRect.height - modelToView.height) / 2;
                scrollRectToVisible(modelToView);
                select(i, i);
            } catch (BadLocationException unused2) {
                select(i, i);
            }
        }
    }

    private void checkPopup(MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            this.popup.show(this, mouseEvent.getX(), mouseEvent.getY());
        }
    }

    public void mousePressed(MouseEvent mouseEvent) {
        checkPopup(mouseEvent);
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        checkPopup(mouseEvent);
        requestFocus();
        getCaret().setVisible(true);
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        checkPopup(mouseEvent);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        int i;
        int viewToModel = viewToModel(new Point(this.popup.f10264x, this.popup.f10265y));
        this.popup.setVisible(false);
        String actionCommand = actionEvent.getActionCommand();
        try {
            i = getLineOfOffset(viewToModel);
        } catch (Exception unused) {
            i = -1;
        }
        if (actionCommand.equals("Set Breakpoint")) {
            this.f10266w.setBreakPoint(i + 1);
        } else if (actionCommand.equals("Clear Breakpoint")) {
            this.f10266w.clearBreakPoint(i + 1);
        } else if (actionCommand.equals("Run")) {
            this.f10266w.load();
        }
    }

    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode != 127) {
            switch (keyCode) {
                case 8:
                case 9:
                case 10:
                    break;
                default:
                    return;
            }
        }
        keyEvent.consume();
    }

    public void keyTyped(KeyEvent keyEvent) {
        keyEvent.consume();
    }

    public void keyReleased(KeyEvent keyEvent) {
        keyEvent.consume();
    }
}
