package de.xonibo.stickes.gui;

import de.xonibo.stickes.gui.ls.LSEntry;
import de.xonibo.stickes.gui.ls.LSList;
import de.xonibo.stickes.StichData;
import de.xonibo.stickes.assemble.LindenmayerTurtle;
import de.xonibo.stickes.gui.ls.LSEntryComparator;
import de.xonibo.stickes.stiches.Plain;
import java.awt.event.ItemEvent;
import java.awt.geom.GeneralPath;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LSEditorJPanel extends javax.swing.JPanel {

    private final Visual visual;
    private final LSList lslist = new LSList(new LSEntryComparator());

    public LSEditorJPanel() {
        this.visual = null;
    }

    public LSEditorJPanel(Visual visual) {
        this.visual = visual;
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelAngle = new javax.swing.JLabel();
        jLabelIterations = new javax.swing.JLabel();
        jLabelAxiom = new javax.swing.JLabel();
        jLabelRule1 = new javax.swing.JLabel();
        jLabelRule2 = new javax.swing.JLabel();
        jButtonDeleteLS = new javax.swing.JButton();
        jButtonRunLS = new javax.swing.JButton();
        jButtonSaveLS = new javax.swing.JButton();
        axiom = new javax.swing.JTextField();
        rule1 = new javax.swing.JTextField();
        rule2 = new javax.swing.JTextField();
        jCheckBoxAutoRun = new javax.swing.JCheckBox();
        angle = new javax.swing.JSpinner();
        iterations = new javax.swing.JSpinner();
        jLabelStepWidth = new javax.swing.JLabel();
        stepwidth = new javax.swing.JSpinner();
        jLabelRule3 = new javax.swing.JLabel();
        rule4 = new javax.swing.JTextField();
        rule3 = new javax.swing.JTextField();
        jLabelRule4 = new javax.swing.JLabel();
        jComboBox = new javax.swing.JComboBox();
        jLabel = new javax.swing.JLabel();
        jLabelStartAngle = new javax.swing.JLabel();
        startangle = new javax.swing.JSpinner();

        jLabelAngle.setText("Angle");

        jLabelIterations.setText("Iterations");

        jLabelAxiom.setText("Axiom");

        jLabelRule1.setText("Rule1");

        jLabelRule2.setText("Rule2");

        jButtonDeleteLS.setText("Delete");
        jButtonDeleteLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteLSActionPerformed(evt);
            }
        });

        jButtonRunLS.setText("Run");
        jButtonRunLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRunLSActionPerformed(evt);
            }
        });

        jButtonSaveLS.setText("Save");
        jButtonSaveLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveLSActionPerformed(evt);
            }
        });

        axiom.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                axiomCaretUpdate(evt);
            }
        });

        rule1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                rule1CaretUpdate(evt);
            }
        });

        rule2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                rule2CaretUpdate(evt);
            }
        });

        jCheckBoxAutoRun.setText("autoRun");
        jCheckBoxAutoRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAutoRunActionPerformed(evt);
            }
        });

        angle.setModel(new javax.swing.SpinnerNumberModel(90, -180, 180, 1));
        angle.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                angleStateChanged(evt);
            }
        });

        iterations.setModel(new javax.swing.SpinnerNumberModel(10, 1, 42, 1));
        iterations.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                iterationsStateChanged(evt);
            }
        });

        jLabelStepWidth.setText("Stepwidth");

        stepwidth.setModel(new javax.swing.SpinnerNumberModel(10, 1, 50, 1));
        stepwidth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                stepwidthStateChanged(evt);
            }
        });

        jLabelRule3.setText("Rule3");

        rule4.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                rule4CaretUpdate(evt);
            }
        });

        rule3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                rule3CaretUpdate(evt);
            }
        });

        jLabelRule4.setText("Rule4");

        jComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Example Lindemayer System" }));
        jComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxItemStateChanged(evt);
            }
        });

        jLabel.setText("Name");

        jLabelStartAngle.setText("Startangle");

        startangle.setModel(new javax.swing.SpinnerNumberModel(90, -180, 180, 1));
        startangle.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                startangleStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabelIterations)
                                .addComponent(jLabelAngle)
                                .addComponent(jLabelRule3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelRule2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelRule4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabelRule1)
                            .addComponent(jLabelAxiom, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonRunLS)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox, 0, 338, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSaveLS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDeleteLS))
                    .addComponent(rule2)
                    .addComponent(rule3)
                    .addComponent(rule1)
                    .addComponent(rule4)
                    .addComponent(axiom)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxAutoRun)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(iterations, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(angle, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelStepWidth)
                                        .addGap(18, 18, 18)
                                        .addComponent(stepwidth, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelStartAngle)
                                        .addGap(18, 18, 18)
                                        .addComponent(startangle, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSaveLS)
                    .addComponent(jButtonDeleteLS)
                    .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAngle)
                    .addComponent(angle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelStartAngle)
                    .addComponent(startangle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIterations)
                    .addComponent(iterations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelStepWidth)
                    .addComponent(stepwidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(axiom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAxiom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rule1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRule1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rule2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRule2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rule3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRule3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rule4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRule4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRunLS)
                    .addComponent(jCheckBoxAutoRun))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRunLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRunLSActionPerformed
        calculate();
    }//GEN-LAST:event_jButtonRunLSActionPerformed

    public void calculate() {
        int it = (int) iterations.getValue();
        int a = (int) angle.getValue();
        int sa = (int) startangle.getValue();
        int sw = (int) stepwidth.getValue();
        List<String> rules = new ArrayList<>();
        rules.add(rule1.getText());
        rules.add(rule2.getText());
        rules.add(rule3.getText());
        rules.add(rule4.getText());
        LindenmayerTurtle ls = new LindenmayerTurtle(sa, it, a, sw, axiom.getText(), rules);

        GeneralPath path = ls.getPath();
        StichData sd = new Plain(path).toStichData();
        visual.setStichData(sd);
        visual.init();
        visual.repaint();
    }

    private void jCheckBoxAutoRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAutoRunActionPerformed
        jButtonRunLS.setEnabled(!jCheckBoxAutoRun.isSelected());
        if (jCheckBoxAutoRun.isSelected()) {
            calculate();
        }
    }//GEN-LAST:event_jCheckBoxAutoRunActionPerformed

    private void angleStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_angleStateChanged
        if (jCheckBoxAutoRun.isSelected()) {
            calculate();
        }
    }//GEN-LAST:event_angleStateChanged

    private void iterationsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_iterationsStateChanged
        if (jCheckBoxAutoRun.isSelected()) {
            calculate();
        }
    }//GEN-LAST:event_iterationsStateChanged

    private void axiomCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_axiomCaretUpdate
        if (jCheckBoxAutoRun.isSelected()) {
            calculate();
        }
    }//GEN-LAST:event_axiomCaretUpdate

    private void rule1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_rule1CaretUpdate
        if (jCheckBoxAutoRun.isSelected()) {
            calculate();
        }
    }//GEN-LAST:event_rule1CaretUpdate

    private void rule2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_rule2CaretUpdate
        if (jCheckBoxAutoRun.isSelected()) {
            calculate();
        }
    }//GEN-LAST:event_rule2CaretUpdate

    private void stepwidthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_stepwidthStateChanged
        if (jCheckBoxAutoRun.isSelected()) {
            calculate();
        }
    }//GEN-LAST:event_stepwidthStateChanged

    private void rule4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_rule4CaretUpdate
        if (jCheckBoxAutoRun.isSelected()) {
            calculate();
        }
    }//GEN-LAST:event_rule4CaretUpdate

    private void rule3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_rule3CaretUpdate
        if (jCheckBoxAutoRun.isSelected()) {
            calculate();
        }

    }//GEN-LAST:event_rule3CaretUpdate

    private void jComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.DESELECTED) {
            return;
        }
        LSEntry e = lslist.getSelectedItem();
        String[] rules = e.getRules();
        if (rules.length == 0) {
            return;
        }
        boolean remember = jCheckBoxAutoRun.isSelected();
        jCheckBoxAutoRun.setSelected(false);
        axiom.setText(e.getAxiom());
        stepwidth.setValue(e.getStepwidth());
        angle.setValue(e.getAngle());
        startangle.setValue(e.getStartangle());
        iterations.setValue(e.getIterations());
        rule4.setText("");
        rule3.setText("");
        rule2.setText("");
        rule1.setText("");
        switch (rules.length) {
            default:
                Logger.getLogger(LSEditorJPanel.class.getName()).log(Level.WARNING, "Too many rules found, rule " + rules.length + " at LS: " + e.getName());
            case 4:
                rule4.setText(rules[3]);
            case 3:
                rule3.setText(rules[2]);
            case 2:
                rule2.setText(rules[1]);
            case 1:
                rule1.setText(rules[0]);
        }
        if (remember) {
            jCheckBoxAutoRun.setSelected(true);
            calculate();
        }
    }//GEN-LAST:event_jComboBoxItemStateChanged

    private void startangleStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_startangleStateChanged
        if (jCheckBoxAutoRun.isSelected()) {
            calculate();
        }
    }//GEN-LAST:event_startangleStateChanged

    private void jButtonSaveLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveLSActionPerformed
        String newname = null;
        String msg = "Please enter name of Lindenmayer-System.";
        boolean lookingForName = true;
        while (lookingForName) {
            newname = JOptionPane.showInputDialog(msg);
            if (newname == null) {
                // exit, as we just want no action
                return;
            }
            if (newname.trim().length() < 3) {
                msg = "Name is to short";
                continue;
            }
            if (lslist.containsName(newname)) {
                msg = "Name already exists, please enter a new one.";
                continue;
            }
            lookingForName = false;
        }
        LSEntry e = new LSEntry(newname, (int) startangle.getValue(), (int) iterations.getValue(), (int) angle.getValue(), (int) stepwidth.getValue(), axiom.getText(), rule1.getText(), rule2.getText(), rule3.getText(), rule4.getText());
        lslist.addElement(e);
        try {
            lslist.save();
        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(this, "Error 23");
        }
    }//GEN-LAST:event_jButtonSaveLSActionPerformed

    private void jButtonDeleteLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteLSActionPerformed
        int answer = JOptionPane.showConfirmDialog(this, "Do you really want to delete", "Confirm", JOptionPane.YES_NO_OPTION);
        if (answer != 0) {
            return;
        }
        int i = jComboBox.getSelectedIndex();
        lslist.getElementAt(i);
        jComboBox.removeItemAt(i);
        try {
            lslist.save();
        } catch (IOException ex) {
            Logger.getLogger(LSEditorJPanel.class.getName()).log(Level.SEVERE, "Error during Delete", ex);
        }

    }//GEN-LAST:event_jButtonDeleteLSActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner angle;
    private javax.swing.JTextField axiom;
    private javax.swing.JSpinner iterations;
    private javax.swing.JButton jButtonDeleteLS;
    private javax.swing.JButton jButtonRunLS;
    private javax.swing.JButton jButtonSaveLS;
    private javax.swing.JCheckBox jCheckBoxAutoRun;
    private javax.swing.JComboBox jComboBox;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabelAngle;
    private javax.swing.JLabel jLabelAxiom;
    private javax.swing.JLabel jLabelIterations;
    private javax.swing.JLabel jLabelRule1;
    private javax.swing.JLabel jLabelRule2;
    private javax.swing.JLabel jLabelRule3;
    private javax.swing.JLabel jLabelRule4;
    private javax.swing.JLabel jLabelStartAngle;
    private javax.swing.JLabel jLabelStepWidth;
    private javax.swing.JTextField rule1;
    private javax.swing.JTextField rule2;
    private javax.swing.JTextField rule3;
    private javax.swing.JTextField rule4;
    private javax.swing.JSpinner startangle;
    private javax.swing.JSpinner stepwidth;
    // End of variables declaration//GEN-END:variables

    private void init() {

        try {
            lslist.load();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(LSEditorJPanel.class.getName()).log(Level.CONFIG, "Load default Lindenmayer Systems from " + lslist.getFilename());
        }
        lslist.addElementIfNotExists(new LSEntry("dragon", -90, 11, 90, 10, "FX", "X=X+YF+", "Y=-FX-Y"));
        lslist.addElementIfNotExists(new LSEntry("fractal-plant", -90, 6, 25, 10, "X", "X=C0F-[C2[X]+C3X]+C1F[C3+FX]-X", "F=FF"));
        lslist.addElementIfNotExists(new LSEntry("hilbert", -90, 6, 90, 10, "X", "X=-YF+XFX+FY-", "Y=+XF-YFY-FX+"));
        lslist.addElementIfNotExists(new LSEntry("joined-cross", -90, 3, 90, 10, "XYXYXYX+XYXYXYX+XYXYXYX+XYXYXYX", "F=", "X=FX+FX+FXFY-FY-", "Y=+FX+FXFY-FY-FY"));
        lslist.addElementIfNotExists(new LSEntry("kevs-pond-weed", -90, 5, 27, 10, "F", "F=C0FF[C1-F++F][C2+F--F]C3++F--F"));
        lslist.addElementIfNotExists(new LSEntry("kevs-tree", -90, 4, 22, 10, "F", "F=C0FF-[C1-F+F+F]+[C2+F-F-F]"));
        lslist.addElementIfNotExists(new LSEntry("kevs-wisply-tree", -90, 5, 25, 10, "FX", "F=C0FF-[C1-F+F]+[C2+F-F]", "X=C0FF+[C1+F]+[C3-F]"));
        lslist.addElementIfNotExists(new LSEntry("koch-curve", -90, 6, 90, 4, "-F", "F=F+F-F-F+F"));
        lslist.addElementIfNotExists(new LSEntry("koch-snowflake", -90, 4, 60, 10, "F++F++F", "F=F-F++F-F", "X=FF"));
        lslist.addElementIfNotExists(new LSEntry("lace", -90, 7, 30, 10, "W", "W=+++X--F--ZFX+", "X=---W++F++YFW-", "Y=+ZFX--F--Z+++", "Z=-YFW++F++Y---"));
        lslist.addElementIfNotExists(new LSEntry("penrose-tiling", -90, 5, 36, 10, "[7]++[7]++[7]++[7]++[7]", "6=81++91----71[-81----61]++", "7=+81--91[---61--71]+", "8=-61++71[+++81++91]-", "9=--81++++61[+91++++71]--71", "1="));
        lslist.addElementIfNotExists(new LSEntry("plesant-error", -90, 4, 72, 8, "F-F-F-F-F", "F=F-F++F+F-F-F"));
        lslist.addElementIfNotExists(new LSEntry("sierpinski-arrowtip", -90, 7, 60, 10, "A", "A=B-A-B", "B=A+B+A"));
        lslist.addElementIfNotExists(new LSEntry("sierpinski-carpet", -90, 4, 90, 10, "F", "F=F+F-F-F-G+F+F+F-F", "G=GGG"));
        lslist.addElementIfNotExists(new LSEntry("sierpinski-median", -90, 8, 45, 10, "L--F--L--F", "L=+R-F-R+", "R=-L+F+L-"));
        lslist.addElementIfNotExists(new LSEntry("sierpinski-triangle", -90, 6, 120, 10, "F-G-G", "F=F-G+F+G-F", "G=GG"));

        if (lslist.getSize() == 0) {
            return;
        }
        final LSEntry defaultEntry = lslist.getElementAt(0);
        jComboBox.setModel(lslist);
        startangle.setValue(defaultEntry.getStartangle());
        angle.setValue(defaultEntry.getAngle());
        stepwidth.setValue(defaultEntry.getStepwidth());
        axiom.setText(defaultEntry.getAxiom());
        final String[] rules = defaultEntry.getRules();
        switch (rules.length) {
            case 4:
                rule4.setText(defaultEntry.getRules()[3]);
            case 3:
                rule3.setText(defaultEntry.getRules()[2]);
            case 2:
                rule2.setText(defaultEntry.getRules()[1]);
            case 1:
                rule1.setText(defaultEntry.getRules()[0]);
        }
    }
}
