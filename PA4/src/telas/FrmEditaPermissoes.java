/*
Produzido por: Gabriel Nunes de Moraes Ghirardelli & Luiz Henrique Aguiar Campos
 */
package telas;

import dao.TiposUsuariosDAO;
import java.awt.List;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Permissoes;
import model.TiposUsuarios;

/**
 *
 * @author gabri
 */
public class FrmEditaPermissoes extends javax.swing.JFrame {

    /**
     * Creates new form FrmEditaPermissoes
     */
    public FrmEditaPermissoes() {
        initComponents();
    }
    
    void preencher(TiposUsuarios tipo) throws SQLException {
        txtcodigo.setText(tipo.getCod_tipo()+"");
        txtnome.setText(tipo.getNome());
        
        TiposUsuariosDAO dao = new TiposUsuariosDAO();
        Permissoes perm = new Permissoes();
        
        perm.setPermissoes(dao.BuscarPermissoes(tipo.getCod_permissoes()));
        
        /*
        0 -> Cadastrar      1 -> Buscar         2 -> Alterar
        3 -> Compra         4 -> Venda          5 -> Pagamentos
        6 -> Clientes       7 -> Fornecedores   8 -> Produtos
        9 -> Usuarios       10 -> Configuracoes 11 -> Relatorios
        */
        
        //Cadastrar
        if (perm.getPermissaoAt(0)) cbxCad.setSelected(true);
        
        //Buscar
        if (perm.getPermissaoAt(1)) {
            cbxBus.setSelected(true);
            
            //Alterar
            if (perm.getPermissaoAt(2)) cbxAlt.setSelected(true);
        } else {
            cbxAlt.setEnabled(false);
        }
        
        //Compra, venda e pagamentos
        if (perm.getPermissaoAt(3)) cbxCom.setSelected(true);
        if (perm.getPermissaoAt(4)) cbxVen.setSelected(true);
        if (perm.getPermissaoAt(5)) cbxPag.setSelected(true);
        
        //Clientes, fornecedoresn produtos e usuarios
        if (perm.getPermissaoAt(6)) cbxCad.setSelected(true);
        if (perm.getPermissaoAt(7)) cbxCad.setSelected(true);
        if (perm.getPermissaoAt(8)) cbxCad.setSelected(true);
        if (perm.getPermissaoAt(9)) cbxCad.setSelected(true);
        
        //Permissoes e relatorios
        if (perm.getPermissaoAt(10)) cbxCon.setSelected(true);
        if (perm.getPermissaoAt(11)) cbxRel.setSelected(true);
        
        menucadastrar.setVisible(false);
        menuEspacos.setText("                                                                                           ");
    }
    
    void criar() {
        cbxAlt.setEnabled(false);
        menusalvar.setVisible(false);
        menuexcluir.setVisible(false);
        txtcodigo.setVisible(false);
        lblcodigo.setVisible(false);
        menuEspacos.setText("                                                                                                           ");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtcodigo = new javax.swing.JTextField();
        lblcodigo = new javax.swing.JLabel();
        txtnome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbxCom = new javax.swing.JCheckBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cbxVen = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        cbxPag = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        cbxCon = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cbxRel = new javax.swing.JCheckBox();
        jLabel23 = new javax.swing.JLabel();
        cbxCad = new javax.swing.JCheckBox();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cbxBus = new javax.swing.JCheckBox();
        jLabel26 = new javax.swing.JLabel();
        cbxAlt = new javax.swing.JCheckBox();
        jLabel27 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbxFor = new javax.swing.JCheckBox();
        cbxPro = new javax.swing.JCheckBox();
        cbxUsu = new javax.swing.JCheckBox();
        cbxCli = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menutitulo = new javax.swing.JMenu();
        menuEspacos = new javax.swing.JMenu();
        menucadastrar = new javax.swing.JMenu();
        menuexcluir = new javax.swing.JMenu();
        menusalvar = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 450));
        setResizable(false);
        setSize(new java.awt.Dimension(700, 450));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtcodigo.setEditable(false);
        txtcodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtcodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodigoActionPerformed(evt);
            }
        });
        getContentPane().add(txtcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 40, 30));

        lblcodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblcodigo.setText("Código:");
        getContentPane().add(lblcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 50, 30));

        txtnome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnomeActionPerformed(evt);
            }
        });
        getContentPane().add(txtnome, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 230, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tipo do usuário:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 100, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Permissões:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 80, 30));

        cbxCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxComActionPerformed(evt);
            }
        });
        getContentPane().add(cbxCom, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Registrar:");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, 70, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Compras");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 80, 30));

        cbxVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxVenActionPerformed(evt);
            }
        });
        getContentPane().add(cbxVen, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Vendas");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, 80, 30));

        cbxPag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPagActionPerformed(evt);
            }
        });
        getContentPane().add(cbxPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Pagamentos");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, 90, 30));

        cbxCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxConActionPerformed(evt);
            }
        });
        getContentPane().add(cbxCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Visualizar:");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 320, 70, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Configurações");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 290, 90, 30));

        cbxRel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxRelActionPerformed(evt);
            }
        });
        getContentPane().add(cbxRel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 330, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Relatórios");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, 90, 30));

        cbxCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCadActionPerformed(evt);
            }
        });
        getContentPane().add(cbxCad, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("das entidades:");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 100, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Cadastros");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 80, 30));

        cbxBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxBusActionPerformed(evt);
            }
        });
        getContentPane().add(cbxBus, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Buscas");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 80, 30));

        cbxAlt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAltActionPerformed(evt);
            }
        });
        getContentPane().add(cbxAlt, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, -1, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Alterações");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 90, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText(" Produtos");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 80, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Clientes ");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 80, 30));
        getContentPane().add(cbxFor, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, -1, -1));
        getContentPane().add(cbxPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, -1, -1));
        getContentPane().add(cbxUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, -1, -1));
        getContentPane().add(cbxCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Usuários");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 70, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Fornecedores");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 90, 30));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Realizar:");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 70, 30));

        menutitulo.setText("Gerenciamento de permissões");
        jMenuBar1.add(menutitulo);
        jMenuBar1.add(menuEspacos);

        menucadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconfinder_add_cross_new_plus_create_392530 (1).png"))); // NOI18N
        menucadastrar.setText("Cadastrar");
        menucadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menucadastrarMouseClicked(evt);
            }
        });
        jMenuBar1.add(menucadastrar);

        menuexcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3643729 - bin delete garbage rubbish trash waste.png"))); // NOI18N
        menuexcluir.setText("Excluir");
        menuexcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuexcluirMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuexcluir);

        menusalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3643774 - disk floppy save saveas saved saving.png"))); // NOI18N
        menusalvar.setText("Salvar");
        menusalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menusalvarMouseClicked(evt);
            }
        });
        jMenuBar1.add(menusalvar);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3643764 - back backward left reply turn.png"))); // NOI18N
        jMenu4.setText("Voltar");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        this.dispose();
    }//GEN-LAST:event_jMenu4MouseClicked

    private void txtcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigoActionPerformed

    private void txtnomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnomeActionPerformed

    private void cbxComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxComActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxComActionPerformed

    private void cbxVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxVenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxVenActionPerformed

    private void cbxPagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxPagActionPerformed

    private void cbxConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxConActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxConActionPerformed

    private void cbxRelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxRelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxRelActionPerformed

    private void menucadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menucadastrarMouseClicked
        try {
            boolean autoriza;
            
            autoriza = txtnome.getText().length() >= 3;
            
            if (!autoriza) {
                JOptionPane.showMessageDialog(null, "Todos os campos devem conter no mínimo 3 caracteres");
                txtnome.grabFocus();
                return;
            } 
            
            TiposUsuarios tipo = new TiposUsuarios();
            tipo.setNome(txtnome.getText());
            
            List permissoes = new List();
            
            //Cadastrar, buscar e alterar
            permissoes.add(cbxCad.isSelected() ? "1" : "0");
            permissoes.add(cbxBus.isSelected() ? "1" : "0");
            permissoes.add(cbxAlt.isSelected() ? "1" : "0");
            
            //Compra, venda e pagamentos
            permissoes.add(cbxCom.isSelected() ? "1" : "0");
            permissoes.add(cbxVen.isSelected() ? "1" : "0");
            permissoes.add(cbxPag.isSelected() ? "1" : "0");
            
            //Clientes, fornecedoresn produtos e usuarios
            permissoes.add(cbxCli.isSelected() ? "1" : "0");
            permissoes.add(cbxFor.isSelected() ? "1" : "0");
            permissoes.add(cbxPro.isSelected() ? "1" : "0");
            permissoes.add(cbxUsu.isSelected() ? "1" : "0");
            
            //Permissoes e relatorios
            permissoes.add(cbxCon.isSelected() ? "1" : "0");
            permissoes.add(cbxRel.isSelected() ? "1" : "0");
            
            TiposUsuariosDAO dao = new TiposUsuariosDAO();
            dao.Cadastrar(tipo, permissoes);
            
            this.dispose();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Aconteceu o erro:" +e);
        }
    }//GEN-LAST:event_menucadastrarMouseClicked

    private void menusalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menusalvarMouseClicked
        try {
            TiposUsuarios tipo = new TiposUsuarios();
            tipo.setCod_tipo(Integer.parseInt(txtcodigo.getText()));
            tipo.setNome(txtnome.getText());
            tipo.setCod_permissoes(Integer.parseInt(txtcodigo.getText()));
            
            List permissoes = new List();
            
            //Cadastrar, buscar e alterar
            permissoes.add(cbxCad.isSelected() ? "1" : "0");
            permissoes.add(cbxBus.isSelected() ? "1" : "0");
            permissoes.add(cbxAlt.isSelected() ? "1" : "0");
            
            //Compra, venda e pagamentos
            permissoes.add(cbxCom.isSelected() ? "1" : "0");
            permissoes.add(cbxVen.isSelected() ? "1" : "0");
            permissoes.add(cbxPag.isSelected() ? "1" : "0");
            
            //Clientes, fornecedoresn produtos e usuarios
            permissoes.add(cbxCli.isSelected() ? "1" : "0");
            permissoes.add(cbxFor.isSelected() ? "1" : "0");
            permissoes.add(cbxPro.isSelected() ? "1" : "0");
            permissoes.add(cbxUsu.isSelected() ? "1" : "0");
            
            //Permissoes e relatorios
            permissoes.add(cbxCon.isSelected() ? "1" : "0");
            permissoes.add(cbxRel.isSelected() ? "1" : "0");
            
            TiposUsuariosDAO dao = new TiposUsuariosDAO();
            dao.Alterar(tipo, permissoes);
            
            this.dispose();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Aconteceu o erro:" +e);
        }
    }//GEN-LAST:event_menusalvarMouseClicked

    private void menuexcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuexcluirMouseClicked
        try {
            TiposUsuarios tipo = new TiposUsuarios();
            tipo.setCod_tipo(Integer.parseInt(txtcodigo.getText()));
            tipo.setNome(txtnome.getText());
            tipo.setCod_permissoes(Integer.parseInt(txtcodigo.getText()));
            
            TiposUsuariosDAO dao = new TiposUsuariosDAO();
            dao.Excluir(tipo);
            
            this.dispose();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Aconteceu o erro:" +e);
        }
    }//GEN-LAST:event_menuexcluirMouseClicked

    private void cbxCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCadActionPerformed

    private void cbxBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBusActionPerformed
        if (cbxBus.isSelected()) {
            cbxAlt.setEnabled(true);
            cbxAlt.setSelected(false);
        } else {
            cbxAlt.setEnabled(false);
        }
    }//GEN-LAST:event_cbxBusActionPerformed

    private void cbxAltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAltActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAltActionPerformed

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
            java.util.logging.Logger.getLogger(FrmEditaPermissoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEditaPermissoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEditaPermissoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEditaPermissoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEditaPermissoes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbxAlt;
    private javax.swing.JCheckBox cbxBus;
    private javax.swing.JCheckBox cbxCad;
    private javax.swing.JCheckBox cbxCli;
    private javax.swing.JCheckBox cbxCom;
    private javax.swing.JCheckBox cbxCon;
    private javax.swing.JCheckBox cbxFor;
    private javax.swing.JCheckBox cbxPag;
    private javax.swing.JCheckBox cbxPro;
    private javax.swing.JCheckBox cbxRel;
    private javax.swing.JCheckBox cbxUsu;
    private javax.swing.JCheckBox cbxVen;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblcodigo;
    private javax.swing.JMenu menuEspacos;
    private javax.swing.JMenu menucadastrar;
    private javax.swing.JMenu menuexcluir;
    private javax.swing.JMenu menusalvar;
    private javax.swing.JMenu menutitulo;
    public javax.swing.JTextField txtcodigo;
    public javax.swing.JTextField txtnome;
    // End of variables declaration//GEN-END:variables
}
