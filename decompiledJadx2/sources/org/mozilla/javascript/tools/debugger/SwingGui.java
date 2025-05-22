package org.mozilla.javascript.tools.debugger;

import java.awt.ActiveEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.MenuComponent;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.SecurityUtilities;
import org.mozilla.javascript.tools.debugger.Dim;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class SwingGui extends JFrame implements GuiCallback {
    private static final long serialVersionUID = -8217029773456711621L;
    private EventQueue awtEventQueue;
    private JSInternalConsole console;
    private ContextWindow context;
    private FileWindow currentWindow;
    private JDesktopPane desk;
    Dim dim;
    JFileChooser dlg;
    private Runnable exitAction;
    private final Map<String, FileWindow> fileWindows;
    private Menubar menubar;
    private JSplitPane split1;
    private JLabel statusBar;
    private JToolBar toolBar;
    private final Map<String, JFrame> toplevels;

    public SwingGui(Dim dim, String str) {
        super(str);
        this.toplevels = Collections.synchronizedMap(new HashMap());
        this.fileWindows = Collections.synchronizedMap(new HashMap());
        this.dim = dim;
        init();
        dim.setGuiCallback(this);
    }

    public Menubar getMenubar() {
        return this.menubar;
    }

    public void setExitAction(Runnable runnable) {
        this.exitAction = runnable;
    }

    public JSInternalConsole getConsole() {
        return this.console;
    }

    public void setVisible(boolean z) {
        super.setVisible(z);
        if (z) {
            this.console.consoleTextArea.requestFocus();
            this.context.split.setDividerLocation(0.5d);
            try {
                this.console.setMaximum(true);
                this.console.setSelected(true);
                this.console.show();
                this.console.consoleTextArea.requestFocus();
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addTopLevel(String str, JFrame jFrame) {
        if (jFrame != this) {
            this.toplevels.put(str, jFrame);
        }
    }

    private void init() {
        Menubar menubar = new Menubar(this);
        this.menubar = menubar;
        setJMenuBar(menubar);
        this.toolBar = new JToolBar();
        String[] strArr = {"Break (Pause)", "Go (F5)", "Step Into (F11)", "Step Over (F7)", "Step Out (F8)"};
        JButton jButton = new JButton("Break");
        jButton.setToolTipText("Break");
        jButton.setActionCommand("Break");
        jButton.addActionListener(this.menubar);
        jButton.setEnabled(true);
        jButton.setToolTipText(strArr[0]);
        JButton jButton2 = new JButton("Go");
        jButton2.setToolTipText("Go");
        jButton2.setActionCommand("Go");
        jButton2.addActionListener(this.menubar);
        jButton2.setEnabled(false);
        jButton2.setToolTipText(strArr[1]);
        JButton jButton3 = new JButton("Step Into");
        jButton3.setToolTipText("Step Into");
        jButton3.setActionCommand("Step Into");
        jButton3.addActionListener(this.menubar);
        jButton3.setEnabled(false);
        jButton3.setToolTipText(strArr[2]);
        JButton jButton4 = new JButton("Step Over");
        jButton4.setToolTipText("Step Over");
        jButton4.setActionCommand("Step Over");
        jButton4.setEnabled(false);
        jButton4.addActionListener(this.menubar);
        jButton4.setToolTipText(strArr[3]);
        JButton jButton5 = new JButton("Step Out");
        jButton5.setToolTipText("Step Out");
        jButton5.setActionCommand("Step Out");
        jButton5.setEnabled(false);
        jButton5.addActionListener(this.menubar);
        jButton5.setToolTipText(strArr[4]);
        Dimension preferredSize = jButton4.getPreferredSize();
        jButton.setPreferredSize(preferredSize);
        jButton.setMinimumSize(preferredSize);
        jButton.setMaximumSize(preferredSize);
        jButton.setSize(preferredSize);
        jButton2.setPreferredSize(preferredSize);
        jButton2.setMinimumSize(preferredSize);
        jButton2.setMaximumSize(preferredSize);
        jButton3.setPreferredSize(preferredSize);
        jButton3.setMinimumSize(preferredSize);
        jButton3.setMaximumSize(preferredSize);
        jButton4.setPreferredSize(preferredSize);
        jButton4.setMinimumSize(preferredSize);
        jButton4.setMaximumSize(preferredSize);
        jButton5.setPreferredSize(preferredSize);
        jButton5.setMinimumSize(preferredSize);
        jButton5.setMaximumSize(preferredSize);
        this.toolBar.add(jButton);
        this.toolBar.add(jButton2);
        this.toolBar.add(jButton3);
        this.toolBar.add(jButton4);
        this.toolBar.add(jButton5);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        getContentPane().add(this.toolBar, "North");
        getContentPane().add(jPanel, "Center");
        JDesktopPane jDesktopPane = new JDesktopPane();
        this.desk = jDesktopPane;
        jDesktopPane.setPreferredSize(new Dimension(600, 300));
        this.desk.setMinimumSize(new Dimension(150, 50));
        JDesktopPane jDesktopPane2 = this.desk;
        JSInternalConsole jSInternalConsole = new JSInternalConsole("JavaScript Console");
        this.console = jSInternalConsole;
        jDesktopPane2.add(jSInternalConsole);
        ContextWindow contextWindow = new ContextWindow(this);
        this.context = contextWindow;
        contextWindow.setPreferredSize(new Dimension(600, 120));
        this.context.setMinimumSize(new Dimension(50, 50));
        JSplitPane jSplitPane = new JSplitPane(0, this.desk, this.context);
        this.split1 = jSplitPane;
        jSplitPane.setOneTouchExpandable(true);
        setResizeWeight(this.split1, 0.66d);
        jPanel.add(this.split1, "Center");
        JLabel jLabel = new JLabel();
        this.statusBar = jLabel;
        jLabel.setText("Thread: ");
        jPanel.add(this.statusBar, "South");
        this.dlg = new JFileChooser();
        this.dlg.addChoosableFileFilter(new FileFilter() { // from class: org.mozilla.javascript.tools.debugger.SwingGui.1
            public String getDescription() {
                return "JavaScript Files (*.js)";
            }

            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                String name = file.getName();
                int lastIndexOf = name.lastIndexOf(46);
                return lastIndexOf > 0 && lastIndexOf < name.length() - 1 && name.substring(lastIndexOf + 1).toLowerCase().equals("js");
            }
        });
        addWindowListener(new WindowAdapter() { // from class: org.mozilla.javascript.tools.debugger.SwingGui.2
            public void windowClosing(WindowEvent windowEvent) {
                SwingGui.this.exit();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exit() {
        Runnable runnable = this.exitAction;
        if (runnable != null) {
            SwingUtilities.invokeLater(runnable);
        }
        this.dim.setReturnValue(5);
    }

    FileWindow getFileWindow(String str) {
        if (str == null || str.equals("<stdin>")) {
            return null;
        }
        return this.fileWindows.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getShortName(String str) {
        int i;
        int lastIndexOf = str.lastIndexOf(47);
        if (lastIndexOf < 0) {
            lastIndexOf = str.lastIndexOf(92);
        }
        return (lastIndexOf < 0 || (i = lastIndexOf + 1) >= str.length()) ? str : str.substring(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeWindow(FileWindow fileWindow) {
        this.fileWindows.remove(fileWindow.getUrl());
        JMenu windowMenu = getWindowMenu();
        int itemCount = windowMenu.getItemCount();
        int i = itemCount - 1;
        JMenuItem item = windowMenu.getItem(i);
        String shortName = getShortName(fileWindow.getUrl());
        int i2 = 5;
        while (true) {
            if (i2 >= itemCount) {
                break;
            }
            JMenuItem item2 = windowMenu.getItem(i2);
            if (item2 != null) {
                String text = item2.getText();
                if (text.substring(text.indexOf(32) + 1).equals(shortName)) {
                    windowMenu.remove(item2);
                    if (itemCount == 6) {
                        windowMenu.remove(4);
                    } else {
                        int i3 = i2 - 4;
                        while (i2 < i) {
                            JMenuItem item3 = windowMenu.getItem(i2);
                            if (item3 != null) {
                                String text2 = item3.getText();
                                if (text2.equals("More Windows...")) {
                                    break;
                                }
                                int indexOf = text2.indexOf(32);
                                StringBuilder sb = new StringBuilder();
                                int i4 = i3 + 48;
                                sb.append((char) i4);
                                sb.append(" ");
                                sb.append(text2.substring(indexOf + 1));
                                item3.setText(sb.toString());
                                item3.setMnemonic(i4);
                                i3++;
                            }
                            i2++;
                        }
                        if (itemCount - 6 == 0 && item != item2 && item.getText().equals("More Windows...")) {
                            windowMenu.remove(item);
                        }
                    }
                }
            }
            i2++;
        }
        windowMenu.revalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void showStopLine(Dim.StackFrame stackFrame) {
        String url = stackFrame.getUrl();
        if (url == null || url.equals("<stdin>")) {
            if (this.console.isVisible()) {
                this.console.show();
            }
        } else {
            showFileWindow(url, -1);
            int lineNumber = stackFrame.getLineNumber();
            FileWindow fileWindow = getFileWindow(url);
            if (fileWindow != null) {
                setFilePosition(fileWindow, lineNumber);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showFileWindow(String str, int i) {
        FileWindow fileWindow = getFileWindow(str);
        if (fileWindow == null) {
            createFileWindow(this.dim.sourceInfo(str), -1);
            fileWindow = getFileWindow(str);
        }
        if (i > -1) {
            int position = fileWindow.getPosition(i - 1);
            int position2 = fileWindow.getPosition(i) - 1;
            fileWindow.textArea.select(position);
            fileWindow.textArea.setCaretPosition(position);
            fileWindow.textArea.moveCaretPosition(position2);
        }
        try {
            if (fileWindow.isIcon()) {
                fileWindow.setIcon(false);
            }
            fileWindow.setVisible(true);
            fileWindow.moveToFront();
            fileWindow.setSelected(true);
            requestFocus();
            fileWindow.requestFocus();
            fileWindow.textArea.requestFocus();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void createFileWindow(Dim.SourceInfo sourceInfo, int i) {
        String url = sourceInfo.url();
        FileWindow fileWindow = new FileWindow(this, sourceInfo);
        this.fileWindows.put(url, fileWindow);
        if (i != -1) {
            FileWindow fileWindow2 = this.currentWindow;
            if (fileWindow2 != null) {
                fileWindow2.setPosition(-1);
            }
            try {
                try {
                    fileWindow.setPosition(fileWindow.textArea.getLineStartOffset(i - 1));
                } catch (BadLocationException unused) {
                    fileWindow.setPosition(fileWindow.textArea.getLineStartOffset(0));
                }
            } catch (BadLocationException unused2) {
                fileWindow.setPosition(-1);
            }
        }
        this.desk.add(fileWindow);
        if (i != -1) {
            this.currentWindow = fileWindow;
        }
        this.menubar.addFile(url);
        fileWindow.setVisible(true);
        try {
            fileWindow.setMaximum(true);
            fileWindow.setSelected(true);
            fileWindow.moveToFront();
        } catch (Exception unused3) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean updateFileWindow(Dim.SourceInfo sourceInfo) {
        FileWindow fileWindow = getFileWindow(sourceInfo.url());
        if (fileWindow == null) {
            return false;
        }
        fileWindow.updateText(sourceInfo);
        fileWindow.show();
        return true;
    }

    private void setFilePosition(FileWindow fileWindow, int i) {
        FileTextArea fileTextArea = fileWindow.textArea;
        try {
            if (i == -1) {
                fileWindow.setPosition(-1);
                if (this.currentWindow == fileWindow) {
                    this.currentWindow = null;
                }
            } else {
                int lineStartOffset = fileTextArea.getLineStartOffset(i - 1);
                if (this.currentWindow != null && this.currentWindow != fileWindow) {
                    this.currentWindow.setPosition(-1);
                }
                fileWindow.setPosition(lineStartOffset);
                this.currentWindow = fileWindow;
            }
        } catch (BadLocationException unused) {
        }
        if (fileWindow.isIcon()) {
            this.desk.getDesktopManager().deiconifyFrame(fileWindow);
        }
        this.desk.getDesktopManager().activateFrame(fileWindow);
        try {
            fileWindow.show();
            fileWindow.toFront();
            fileWindow.setSelected(true);
        } catch (Exception unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enterInterruptImpl(Dim.StackFrame stackFrame, String str, String str2) {
        this.statusBar.setText("Thread: " + str);
        showStopLine(stackFrame);
        if (str2 != null) {
            MessageDialogWrapper.showMessageDialog(this, str2, "Exception in Script", 0);
        }
        updateEnabled(true);
        Dim.ContextData contextData = stackFrame.contextData();
        JComboBox jComboBox = this.context.context;
        List<String> list = this.context.toolTips;
        this.context.disableUpdate();
        int frameCount = contextData.frameCount();
        jComboBox.removeAllItems();
        jComboBox.setSelectedItem((Object) null);
        list.clear();
        for (int i = 0; i < frameCount; i++) {
            Dim.StackFrame frame = contextData.getFrame(i);
            String url = frame.getUrl();
            int lineNumber = frame.getLineNumber();
            jComboBox.insertItemAt("\"" + (url.length() > 20 ? "..." + url.substring(url.length() - 17) : url) + "\", line " + lineNumber, i);
            list.add("\"" + url + "\", line " + lineNumber);
        }
        this.context.enableUpdate();
        jComboBox.setSelectedIndex(0);
        jComboBox.setMinimumSize(new Dimension(50, jComboBox.getMinimumSize().height));
    }

    private JMenu getWindowMenu() {
        return this.menubar.getMenu(3);
    }

    private String chooseFile(String str) {
        this.dlg.setDialogTitle(str);
        String systemProperty = SecurityUtilities.getSystemProperty("user.dir");
        File file = systemProperty != null ? new File(systemProperty) : null;
        if (file != null) {
            this.dlg.setCurrentDirectory(file);
        }
        if (this.dlg.showOpenDialog(this) == 0) {
            try {
                String canonicalPath = this.dlg.getSelectedFile().getCanonicalPath();
                File parentFile = this.dlg.getSelectedFile().getParentFile();
                Properties properties = System.getProperties();
                properties.put("user.dir", parentFile.getPath());
                System.setProperties(properties);
                return canonicalPath;
            } catch (IOException | SecurityException unused) {
            }
        }
        return null;
    }

    private JInternalFrame getSelectedFrame() {
        JInternalFrame[] allFrames = this.desk.getAllFrames();
        for (int i = 0; i < allFrames.length; i++) {
            if (allFrames[i].isShowing()) {
                return allFrames[i];
            }
        }
        return allFrames[allFrames.length - 1];
    }

    private void updateEnabled(boolean z) {
        ((Menubar) getJMenuBar()).updateEnabled(z);
        int componentCount = this.toolBar.getComponentCount();
        int i = 0;
        while (i < componentCount) {
            this.toolBar.getComponent(i).setEnabled(i == 0 ? !z : z);
            i++;
        }
        if (z) {
            this.toolBar.setEnabled(true);
            if (getExtendedState() == 1) {
                setExtendedState(0);
            }
            toFront();
            this.context.setEnabled(true);
            return;
        }
        FileWindow fileWindow = this.currentWindow;
        if (fileWindow != null) {
            fileWindow.setPosition(-1);
        }
        this.context.setEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setResizeWeight(JSplitPane jSplitPane, double d) {
        try {
            JSplitPane.class.getMethod("setResizeWeight", Double.TYPE).invoke(jSplitPane, new Double(d));
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
    }

    private String readFile(String str) {
        try {
            FileReader fileReader = new FileReader(str);
            try {
                String readReader = Kit.readReader(fileReader);
                fileReader.close();
                return readReader;
            } catch (Throwable th) {
                fileReader.close();
                throw th;
            }
        } catch (IOException e) {
            MessageDialogWrapper.showMessageDialog(this, e.getMessage(), "Error reading " + str, 0);
            return null;
        }
    }

    @Override // org.mozilla.javascript.tools.debugger.GuiCallback
    public void updateSourceText(Dim.SourceInfo sourceInfo) {
        RunProxy runProxy = new RunProxy(this, 3);
        runProxy.sourceInfo = sourceInfo;
        SwingUtilities.invokeLater(runProxy);
    }

    @Override // org.mozilla.javascript.tools.debugger.GuiCallback
    public void enterInterrupt(Dim.StackFrame stackFrame, String str, String str2) {
        if (SwingUtilities.isEventDispatchThread()) {
            enterInterruptImpl(stackFrame, str, str2);
            return;
        }
        RunProxy runProxy = new RunProxy(this, 4);
        runProxy.lastFrame = stackFrame;
        runProxy.threadTitle = str;
        runProxy.alertMessage = str2;
        SwingUtilities.invokeLater(runProxy);
    }

    @Override // org.mozilla.javascript.tools.debugger.GuiCallback
    public boolean isGuiEventThread() {
        return SwingUtilities.isEventDispatchThread();
    }

    @Override // org.mozilla.javascript.tools.debugger.GuiCallback
    public void dispatchNextGuiEvent() throws InterruptedException {
        EventQueue eventQueue = this.awtEventQueue;
        if (eventQueue == null) {
            eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
            this.awtEventQueue = eventQueue;
        }
        ActiveEvent nextEvent = eventQueue.getNextEvent();
        if (nextEvent instanceof ActiveEvent) {
            nextEvent.dispatch();
            return;
        }
        Object source = nextEvent.getSource();
        if (source instanceof Component) {
            ((Component) source).dispatchEvent(nextEvent);
        } else if (source instanceof MenuComponent) {
            ((MenuComponent) source).dispatchEvent(nextEvent);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void actionPerformed(ActionEvent actionEvent) {
        int i;
        String readFile;
        String readFile2;
        String actionCommand = actionEvent.getActionCommand();
        int i2 = 2;
        if (actionCommand.equals("Cut") || actionCommand.equals("Copy") || actionCommand.equals("Paste")) {
            ActionListener selectedFrame = getSelectedFrame();
            if (selectedFrame != null && (selectedFrame instanceof ActionListener)) {
                selectedFrame.actionPerformed(actionEvent);
            }
        } else {
            if (actionCommand.equals("Step Over")) {
                i2 = 0;
            } else if (actionCommand.equals("Step Into")) {
                i2 = 1;
            } else if (!actionCommand.equals("Step Out")) {
                if (actionCommand.equals("Go")) {
                    i2 = 3;
                } else if (actionCommand.equals("Break")) {
                    this.dim.setBreak();
                } else if (actionCommand.equals("Exit")) {
                    exit();
                } else if (actionCommand.equals("Open")) {
                    String chooseFile = chooseFile("Select a file to compile");
                    if (chooseFile != null && (readFile2 = readFile(chooseFile)) != null) {
                        RunProxy runProxy = new RunProxy(this, 1);
                        runProxy.fileName = chooseFile;
                        runProxy.text = readFile2;
                        new Thread(runProxy).start();
                    }
                } else if (actionCommand.equals("Load")) {
                    String chooseFile2 = chooseFile("Select a file to execute");
                    if (chooseFile2 != null && (readFile = readFile(chooseFile2)) != null) {
                        RunProxy runProxy2 = new RunProxy(this, 2);
                        runProxy2.fileName = chooseFile2;
                        runProxy2.text = readFile;
                        new Thread(runProxy2).start();
                    }
                } else if (actionCommand.equals("More Windows...")) {
                    new MoreWindows(this, this.fileWindows, "Window", "Files").showDialog(this);
                } else if (actionCommand.equals("Console")) {
                    if (this.console.isIcon()) {
                        this.desk.getDesktopManager().deiconifyFrame(this.console);
                    }
                    this.console.show();
                    this.desk.getDesktopManager().activateFrame(this.console);
                    this.console.consoleTextArea.requestFocus();
                } else if (!actionCommand.equals("Cut") && !actionCommand.equals("Copy") && !actionCommand.equals("Paste")) {
                    if (actionCommand.equals("Go to function...")) {
                        new FindFunction(this, "Go to function", "Function").showDialog(this);
                    } else if (actionCommand.equals("Tile")) {
                        JComponent[] allFrames = this.desk.getAllFrames();
                        int length = allFrames.length;
                        int sqrt = (int) Math.sqrt(length);
                        if (sqrt * sqrt < length) {
                            i = sqrt + 1;
                            if (sqrt * i < length) {
                                sqrt = i;
                            } else {
                                i = sqrt;
                                sqrt = i;
                            }
                        } else {
                            i = sqrt;
                        }
                        Dimension size = this.desk.getSize();
                        int i3 = size.width / sqrt;
                        int i4 = size.height / i;
                        int i5 = 0;
                        for (int i6 = 0; i6 < i; i6++) {
                            int i7 = 0;
                            for (int i8 = 0; i8 < sqrt; i8++) {
                                int i9 = (i6 * sqrt) + i8;
                                if (i9 >= allFrames.length) {
                                    break;
                                }
                                JComponent jComponent = allFrames[i9];
                                try {
                                    jComponent.setIcon(false);
                                    jComponent.setMaximum(false);
                                } catch (Exception unused) {
                                }
                                this.desk.getDesktopManager().setBoundsForFrame(jComponent, i7, i5, i3, i4);
                                i7 += i3;
                            }
                            i5 += i4;
                        }
                    } else if (actionCommand.equals("Cascade")) {
                        JInternalFrame[] allFrames2 = this.desk.getAllFrames();
                        int length2 = allFrames2.length;
                        int height = this.desk.getHeight() / length2;
                        if (height > 30) {
                            height = 30;
                        }
                        int i10 = length2 - 1;
                        int i11 = 0;
                        int i12 = 0;
                        while (i10 >= 0) {
                            JInternalFrame jInternalFrame = allFrames2[i10];
                            try {
                                jInternalFrame.setIcon(false);
                                jInternalFrame.setMaximum(false);
                            } catch (Exception unused2) {
                            }
                            Dimension preferredSize = jInternalFrame.getPreferredSize();
                            this.desk.getDesktopManager().setBoundsForFrame(jInternalFrame, i11, i12, preferredSize.width, preferredSize.height);
                            i10--;
                            i11 += height;
                            i12 += height;
                        }
                    } else {
                        FileWindow fileWindow = getFileWindow(actionCommand);
                        if (fileWindow != null) {
                            FileWindow fileWindow2 = fileWindow;
                            try {
                                if (fileWindow2.isIcon()) {
                                    fileWindow2.setIcon(false);
                                }
                                fileWindow2.setVisible(true);
                                fileWindow2.moveToFront();
                                fileWindow2.setSelected(true);
                            } catch (Exception unused3) {
                            }
                        }
                    }
                }
            }
            if (i2 == -1) {
                updateEnabled(false);
                this.dim.setReturnValue(i2);
                return;
            }
            return;
        }
        i2 = -1;
        if (i2 == -1) {
        }
    }
}
