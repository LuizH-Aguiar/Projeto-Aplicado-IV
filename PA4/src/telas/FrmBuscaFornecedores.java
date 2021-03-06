/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package telas;

import dao.FornecedoresDAO;
import javax.swing.table.DefaultTableModel;
import model.Fornecedores;

/**
 *
 * @author Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
public class FrmBuscaFornecedores extends javax.swing.JFrame {

    /**
     * Creates new form FrmBuscaFornecedores
     */
    public FrmBuscaFornecedores() {
        initComponents();
    }
    
    private boolean permissaoEditar;

    public boolean getPermissaoEditar() {
        return permissaoEditar;
    }

    public void setPermissaoEditar(boolean permissaoEditar) {
        this.permissaoEditar = permissaoEditar;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnbusca = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelafornecedores = new javax.swing.JTable();
        txtbusca = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(725, 425));
        setResizable(false);
        setSize(new java.awt.Dimension(725, 425));
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnbusca.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnbusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3643762 - find glass magnifying search zoom.png"))); // NOI18N
        btnbusca.setText("Buscar");
        btnbusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscaActionPerformed(evt);
            }
        });
        getContentPane().add(btnbusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, -1, 30));

        tabelafornecedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Empresa", "Representante", "CNPJ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelafornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelafornecedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelafornecedores);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 600, 220));

        txtbusca.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtbusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 243, 30));

        jMenu1.setText("Consulta de fornecedores");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("                                                                                                                                                             ");
        jMenuBar1.add(jMenu2);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3643769 - building home house main menu start.png"))); // NOI18N
        jMenu5.setText("Menu");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabelafornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelafornecedoresMouseClicked
        if(tabelafornecedores.getSelectedRow() != -1 && getPermissaoEditar()){
            FrmEditaFornecedores EditaFornecedores = new FrmEditaFornecedores();
            Fornecedores fornecedor = new Fornecedores();
            
            fornecedor.setCod_fornecedor(Integer.parseInt(tabelafornecedores.getValueAt(tabelafornecedores.getSelectedRow(), 0).toString()));
            fornecedor.setEmpresa(tabelafornecedores.getValueAt(tabelafornecedores.getSelectedRow(), 1).toString());
            fornecedor.setRepresentante((tabelafornecedores.getValueAt(tabelafornecedores.getSelectedRow(), 2).toString()));
            fornecedor.setCnpj(tabelafornecedores.getValueAt(tabelafornecedores.getSelectedRow(), 3).toString());
            
            EditaFornecedores.preencher(fornecedor);
            
            EditaFornecedores.setVisible(true);
        }
    }//GEN-LAST:event_tabelafornecedoresMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {

            FornecedoresDAO dao = new FornecedoresDAO();

            DefaultTableModel model = dao.Buscar("");

            tabelafornecedores.setModel(model);
            tabelafornecedores.setEnabled(getPermissaoEditar());
            txtbusca.setText(null);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnbuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscaActionPerformed
        try {

            FornecedoresDAO dao = new FornecedoresDAO();

            DefaultTableModel model = dao.Buscar(txtbusca.getText());

            tabelafornecedores.setModel(model);
            tabelafornecedores.setEnabled(getPermissaoEditar());
            txtbusca.setText(null);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnbuscaActionPerformed

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        this.dispose();
    }//GEN-LAST:event_jMenu5MouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        try {

            FornecedoresDAO dao = new FornecedoresDAO();

            DefaultTableModel model = dao.Buscar("");

            tabelafornecedores.setModel(model);
            tabelafornecedores.setEnabled(getPermissaoEditar());
            txtbusca.setText(null);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_formWindowGainedFocus

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmBuscaFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBuscaFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBuscaFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBuscaFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBuscaFornecedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbusca;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelafornecedores;
    private javax.swing.JTextField txtbusca;
    // End of variables declaration//GEN-END:variables
}
