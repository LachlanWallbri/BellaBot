package org.mozilla.javascript.tools.shell;

import com.amazonaws.cognito.clientcontext.datacollection.DataRecordKey;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.mozilla.javascript.SecurityUtilities;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class JSConsole extends JFrame implements ActionListener {
    static final long serialVersionUID = 2551225560631876300L;
    private File CWD;
    private ConsoleTextArea consoleTextArea;
    private JFileChooser dlg;

    public String chooseFile() {
        String systemProperty;
        if (this.CWD == null && (systemProperty = SecurityUtilities.getSystemProperty("user.dir")) != null) {
            this.CWD = new File(systemProperty);
        }
        File file = this.CWD;
        if (file != null) {
            this.dlg.setCurrentDirectory(file);
        }
        this.dlg.setDialogTitle("Select a file to load");
        if (this.dlg.showOpenDialog(this) != 0) {
            return null;
        }
        String path = this.dlg.getSelectedFile().getPath();
        this.CWD = new File(this.dlg.getSelectedFile().getParent());
        return path;
    }

    public static void main(String[] strArr) {
        new JSConsole(strArr);
    }

    public void createFileChooser() {
        this.dlg = new JFileChooser();
        this.dlg.addChoosableFileFilter(new FileFilter() { // from class: org.mozilla.javascript.tools.shell.JSConsole.1
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
    }

    public JSConsole(String[] strArr) {
        super("Rhino JavaScript Console");
        JMenuBar jMenuBar = new JMenuBar();
        createFileChooser();
        String[] strArr2 = {"Load...", "Exit"};
        String[] strArr3 = {"Load", "Exit"};
        int i = 2;
        char[] cArr = {Matrix.MATRIX_TYPE_RANDOM_LT, 'X'};
        String[] strArr4 = {"Cut", "Copy", "Paste"};
        int i2 = 3;
        char[] cArr2 = {'T', 'C', 'P'};
        String[] strArr5 = {"Metal", "Windows", "Motif"};
        boolean[] zArr = {true, false, false};
        JMenu jMenu = new JMenu("File");
        jMenu.setMnemonic('F');
        JMenu jMenu2 = new JMenu("Edit");
        jMenu2.setMnemonic('E');
        JMenu jMenu3 = new JMenu(DataRecordKey.PLATFORM);
        jMenu3.setMnemonic('P');
        int i3 = 0;
        while (i3 < i) {
            JMenuItem jMenuItem = new JMenuItem(strArr2[i3], cArr[i3]);
            jMenuItem.setActionCommand(strArr3[i3]);
            jMenuItem.addActionListener(this);
            jMenu.add(jMenuItem);
            i3++;
            i = 2;
            i2 = 3;
        }
        int i4 = 0;
        for (int i5 = i2; i4 < i5; i5 = 3) {
            JMenuItem jMenuItem2 = new JMenuItem(strArr4[i4], cArr2[i4]);
            jMenuItem2.addActionListener(this);
            jMenu2.add(jMenuItem2);
            i4++;
        }
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i6 = 0; i6 < 3; i6++) {
            JRadioButtonMenuItem jRadioButtonMenuItem = new JRadioButtonMenuItem(strArr5[i6], zArr[i6]);
            buttonGroup.add(jRadioButtonMenuItem);
            jRadioButtonMenuItem.addActionListener(this);
            jMenu3.add(jRadioButtonMenuItem);
        }
        jMenuBar.add(jMenu);
        jMenuBar.add(jMenu2);
        jMenuBar.add(jMenu3);
        setJMenuBar(jMenuBar);
        this.consoleTextArea = new ConsoleTextArea(strArr);
        setContentPane(new JScrollPane(this.consoleTextArea));
        this.consoleTextArea.setRows(24);
        this.consoleTextArea.setColumns(80);
        addWindowListener(new WindowAdapter() { // from class: org.mozilla.javascript.tools.shell.JSConsole.2
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        pack();
        setVisible(true);
        Main.setIn(this.consoleTextArea.getIn());
        Main.setOut(this.consoleTextArea.getOut());
        Main.setErr(this.consoleTextArea.getErr());
        Main.main(strArr);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        String str;
        String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Load")) {
            String chooseFile = chooseFile();
            if (chooseFile != null) {
                String replace = chooseFile.replace('\\', '/');
                this.consoleTextArea.eval("load(\"" + replace + "\");");
                return;
            }
            return;
        }
        if (actionCommand.equals("Exit")) {
            System.exit(0);
            return;
        }
        if (actionCommand.equals("Cut")) {
            this.consoleTextArea.cut();
            return;
        }
        if (actionCommand.equals("Copy")) {
            this.consoleTextArea.copy();
            return;
        }
        if (actionCommand.equals("Paste")) {
            this.consoleTextArea.paste();
            return;
        }
        if (actionCommand.equals("Metal")) {
            str = "javax.swing.plaf.metal.MetalLookAndFeel";
        } else if (actionCommand.equals("Windows")) {
            str = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        } else {
            str = actionCommand.equals("Motif") ? "com.sun.java.swing.plaf.motif.MotifLookAndFeel" : null;
        }
        if (str != null) {
            try {
                UIManager.setLookAndFeel(str);
                SwingUtilities.updateComponentTreeUI(this);
                this.consoleTextArea.postUpdateUI();
                createFileChooser();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), DataRecordKey.PLATFORM, 0);
            }
        }
    }
}
