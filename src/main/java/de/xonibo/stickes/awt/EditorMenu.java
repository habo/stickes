package de.xonibo.stickes.awt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EditorMenu implements ActionListener {

    private final JMenuItem menuitemls = new JMenuItem("LindenmayerSystem");

    private final Visual visual;

    public EditorMenu(Visual visual) {
        this.visual = visual;
    }

    public JMenu createMenu() {
        JMenu menuFormat = new JMenu("Editor");

        menuitemls.addActionListener(this);
        menuFormat.add(menuitemls);
        return menuFormat;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuitemls) {
            openLindenmayerSystemsDialog();
        }
    }

    public void openLindenmayerSystemsDialog() {
        JDialog dialog = new JDialog(visual.frame, false);
        final LSEditorJPanel panel = new LSEditorJPanel(visual);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(dialog.getContentPane());
        dialog.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        dialog.add(panel);
        dialog.pack();
        dialog.setVisible(true);
    }
}
