/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GeneradorReporteMensual {

    public DefaultTableModel generarReporteMensual(Connection connection) {
        DefaultTableModel modeloTabla = new DefaultTableModel();

        Date fechaActual = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String mesActual = dateFormat.format(fechaActual);

        try {
            String consulta = "SELECT fecha, producto, cantidad, precio FROM entradas WHERE DATE_FORMAT(fecha, '%Y-%m') = ?";
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setString(1, mesActual);
            ResultSet rs = ps.executeQuery();

            // Configurar el modelo de la tabla
            modeloTabla.addColumn("Fecha_Caducidad");
            modeloTabla.addColumn("Producto");
            modeloTabla.addColumn("Cantidad");
            modeloTabla.addColumn("Precio");

            while (rs.next()) {
                Object[] fila = {rs.getString("fecha"), rs.getString("producto"), rs.getInt("cantidad"), rs.getDouble("precio")};
                modeloTabla.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al generar el reporte mensual: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return modeloTabla;
    }
}
