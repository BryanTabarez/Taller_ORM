/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import Controlador.PizzabaseControlador;
import Persistencia.Conexion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author bryanstm
 */
public class Vista_Administrador1 extends javax.swing.JFrame {

    /**
     * Creates new form Vista_Administrador1
     */
    PizzabaseControlador pc = new PizzabaseControlador(this);
    byte[] imagen;
    
    
    public Vista_Administrador1() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panel_pizza = new javax.swing.JPanel();
        jl_nombre = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jl_presentacion = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jt_nombre = new javax.swing.JTextField();
        jc_presentacion = new javax.swing.JComboBox();
        jt_precio = new javax.swing.JTextField();
        jb_cargar = new javax.swing.JButton();
        jl_imagen = new javax.swing.JLabel();
        jb_actualizar = new javax.swing.JButton();
        jb_ingresar = new javax.swing.JButton();
        jb_eliminar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jc_tamano = new javax.swing.JComboBox();
        jt_buscar = new javax.swing.JTextField();
        jb_buscar = new javax.swing.JButton();
        panel_ingrediente = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jl_nombre.setText("Nombre:");

        jLabel1.setText("Tamaño:");

        jl_presentacion.setText("Presentación:");

        jLabel2.setText("Precio:");

        jLabel3.setText("Foto:");

        jc_presentacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "4", "8", "12" }));
        jc_presentacion.setToolTipText("");

        jb_cargar.setText("Cargar");
        jb_cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cargarActionPerformed(evt);
            }
        });

        jl_imagen.setText("jLabel4");
        jl_imagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jb_actualizar.setText("Actualizar");

        jb_ingresar.setText("Ingresar");
        jb_ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_ingresarActionPerformed(evt);
            }
        });

        jb_eliminar.setText("Eliminar");

        jLabel5.setText("Buscar ID:");

        jc_tamano.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pequeña", "Mediana", "Grande" }));

        jb_buscar.setText("Buscar");

        javax.swing.GroupLayout panel_pizzaLayout = new javax.swing.GroupLayout(panel_pizza);
        panel_pizza.setLayout(panel_pizzaLayout);
        panel_pizzaLayout.setHorizontalGroup(
            panel_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pizzaLayout.createSequentialGroup()
                .addGroup(panel_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_pizzaLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(panel_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl_nombre)
                            .addGroup(panel_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jt_nombre)
                            .addComponent(jc_tamano, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jt_buscar)))
                    .addGroup(panel_pizzaLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jb_actualizar)))
                .addGap(18, 18, 18)
                .addGroup(panel_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_pizzaLayout.createSequentialGroup()
                        .addGroup(panel_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl_presentacion)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(panel_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_pizzaLayout.createSequentialGroup()
                                .addComponent(jb_cargar)
                                .addGap(44, 44, 44)
                                .addComponent(jl_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jc_presentacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jt_precio))))
                    .addGroup(panel_pizzaLayout.createSequentialGroup()
                        .addComponent(jb_ingresar)
                        .addGap(56, 56, 56)
                        .addComponent(jb_eliminar))
                    .addComponent(jb_buscar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_pizzaLayout.setVerticalGroup(
            panel_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pizzaLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(panel_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_presentacion)
                    .addComponent(jc_presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(panel_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_nombre)
                    .addComponent(jLabel2)
                    .addComponent(jt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(panel_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jb_cargar)
                    .addComponent(jl_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jc_tamano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_buscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(panel_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_actualizar)
                    .addComponent(jb_ingresar)
                    .addComponent(jb_eliminar))
                .addGap(21, 21, 21))
        );

        jTabbedPane1.addTab("Pizza", panel_pizza);

        javax.swing.GroupLayout panel_ingredienteLayout = new javax.swing.GroupLayout(panel_ingrediente);
        panel_ingrediente.setLayout(panel_ingredienteLayout);
        panel_ingredienteLayout.setHorizontalGroup(
            panel_ingredienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );
        panel_ingredienteLayout.setVerticalGroup(
            panel_ingredienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 316, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Ingrediente", panel_ingrediente);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_cargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_cargarActionPerformed
        // TODO add your handling code here:
        //Create a file chooser
        final JFileChooser fc = new JFileChooser();
        //In response to a button click:
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File foto = fc.getSelectedFile();
            System.out.println("Un archivo fue seleccionado...");
            
            InputStream is = null;
            try {
                is = new FileInputStream(foto); //lo abrimos. Lo importante es que sea un InputStream
            } catch (FileNotFoundException ex) {
                System.out.println("ERROR CON EL ARCHIVO!");
            }
            byte[] buffer = new byte[(int) foto.length()]; //creamos el buffer
            try {
                int readers = is.read(buffer); //leemos el archivo al buffer
            } catch (IOException ex) {
                System.out.println("ERROR BUFFER ARCHIVO!");
            }
            imagen = buffer; //lo guardamos en la variable foto
            
        } else {
            System.out.println("Cancelar seleccion...");
        }
    }//GEN-LAST:event_jb_cargarActionPerformed

    private void jb_ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_ingresarActionPerformed
        // TODO add your handling code here:
        boolean result = pc.IngresarPizzabase(imagen, 1, jt_nombre.getText(), (String)jc_tamano.getSelectedItem(),
                (String)jc_presentacion.getSelectedItem(), Double.parseDouble(jt_precio.getText()));
         //Double.parseDouble(jt_precio.getText())
        
        if (result){
            System.out.println("true ingresar pizza action");
        }else{
            System.out.println("false ingresar pizza action");
        }
            
        
        // pc.setFoto(bytea[] fotos);
    }//GEN-LAST:event_jb_ingresarActionPerformed

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
            java.util.logging.Logger.getLogger(Vista_Administrador1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista_Administrador1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista_Administrador1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista_Administrador1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista_Administrador1().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jb_actualizar;
    private javax.swing.JButton jb_buscar;
    private javax.swing.JButton jb_cargar;
    private javax.swing.JButton jb_eliminar;
    private javax.swing.JButton jb_ingresar;
    private javax.swing.JComboBox jc_presentacion;
    private javax.swing.JComboBox jc_tamano;
    private javax.swing.JLabel jl_imagen;
    private javax.swing.JLabel jl_nombre;
    private javax.swing.JLabel jl_presentacion;
    private javax.swing.JTextField jt_buscar;
    private javax.swing.JTextField jt_nombre;
    private javax.swing.JTextField jt_precio;
    private javax.swing.JPanel panel_ingrediente;
    private javax.swing.JPanel panel_pizza;
    // End of variables declaration//GEN-END:variables
}