
package Programa_1;

import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public final class Ventana_Datos extends javax.swing.JFrame 
{

    Conexion conexion = new Conexion("master");
    
    public void CrearBD()
    {
        int count = 0;
        try
        {
            conexion.setSql("SELECT name FROM SYS.databases");
            conexion.setRs(conexion.getSt().executeQuery(conexion.getSql()));
            while(conexion.getRs().next())
            {
                if (conexion.getRs().getString(1).equals("Sakila")) 
                {
                    count = 1;
                }
            }
            if (count == 1) 
            {
                conexion = new Conexion("Sakila");
            }
            else
            {
                conexion.setSql("CREATE DATABASE Sakila");
                conexion.getSt().execute(conexion.getSql());
                conexion = new Conexion("Sakila");
            }
        }
        catch(SQLException e)
        {
            System.out.println("1 "+e);
        }
    }
    
    public void CrearTb()
    {
        int count = 0;
        try
        {
            conexion.setSql("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES");
            conexion.setRs(conexion.getSt().executeQuery(conexion.getSql()));
            while(conexion.getRs().next())
            {
                if (conexion.getRs().getString(1).equals("film")) 
                {
                    count = 1;
                }
            }
            if (count == 0)
            {
                conexion.setSql("CREATE TABLE film (ID int PRIMARY KEY IDENTITY,Nombre varchar(100),Renta int,Trailers varchar(2), Release_Year int);");
                conexion.getSt().executeQuery(conexion.getSql());
            }
        }
        catch(SQLException e)
        {
            System.out.println("2 "+e);
        }
    }
    
    public void ConsultarTabla()
    {
        DefaultTableModel modelo = (DefaultTableModel) jTable_Datos.getModel();
        modelo.setRowCount(0);
        try
        {
            conexion.setSql("SELECT * FROM film");
            conexion.setRs(conexion.getSt().executeQuery(conexion.getSql()));
            while(conexion.getRs().next())
            {
                Object datos[] = {conexion.getRs().getInt(1),conexion.getRs().getString(2),conexion.getRs().getInt(3),conexion.getRs().getString(4),conexion.getRs().getInt(5)};
                modelo.addRow(datos);
            }
        }
        catch(SQLException e)
        {
            System.out.println("3 "+e);
        }
    }
    
    public void ConsultarRenta()
    {
        DefaultTableModel modelo = (DefaultTableModel) jTable_Datos.getModel();
        modelo.setRowCount(0);
        try
        {
            conexion.setSql("SELECT * FROM film WHERE Renta > 6");
            conexion.setRs(conexion.getSt().executeQuery(conexion.getSql()));
            while(conexion.getRs().next())
            {
                Object datos[] = {conexion.getRs().getInt(1),conexion.getRs().getString(2),conexion.getRs().getInt(3),conexion.getRs().getString(4),conexion.getRs().getInt(5)};
                modelo.addRow(datos);
            }
        }
        catch(SQLException e)
        {
            System.out.println(""+e);
        }
    }
    
    public void ConsultarTrailers()
    {
        DefaultTableModel modelo = (DefaultTableModel) jTable_Datos.getModel();
        modelo.setRowCount(0);
        try
        {
            conexion.setSql("SELECT * FROM film WHERE Trailers ='SI'");
            conexion.setRs(conexion.getSt().executeQuery(conexion.getSql()));
            while(conexion.getRs().next())
            {
                Object datos[] = {conexion.getRs().getInt(1),conexion.getRs().getString(2),conexion.getRs().getInt(3),conexion.getRs().getString(4),conexion.getRs().getInt(5)};
                modelo.addRow(datos);
            }
        }
        catch(SQLException e)
        {
            System.out.println(""+e);
        }
    }
    
    public Ventana_Datos() 
    {
        initComponents();
        CrearBD();
        CrearTb();
        ConsultarTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Back = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Datos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel_Back.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CINEMAX");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Elija una busqueda:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-seleccione-", "renta mayor a 6", "Muestra Trailers" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTable_Datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "RENTA", "TRAILERS", "RELEASE YEAR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable_Datos);

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setText("MODIFICAR");

        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setText("ELIMINAR");

        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setText("CONSULTA GENERAL");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_BackLayout = new javax.swing.GroupLayout(jPanel_Back);
        jPanel_Back.setLayout(jPanel_BackLayout);
        jPanel_BackLayout.setHorizontalGroup(
            jPanel_BackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_BackLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_BackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                    .addGroup(jPanel_BackLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel_BackLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_BackLayout.setVerticalGroup(
            jPanel_BackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BackLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_BackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_BackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2))
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_BackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_Back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (jComboBox1.getSelectedIndex() > 0) 
        {
            switch (jComboBox1.getSelectedIndex()) 
            {
                case 1:
                ConsultarRenta();
                break;
                case 2:
                ConsultarTrailers();
                break;
                default:
                    throw new AssertionError();
            }
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ConsultarTabla();
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) 
    {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Datos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel_Back;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Datos;
    // End of variables declaration//GEN-END:variables
}
