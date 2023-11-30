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

public class generarReport {

    public static void generarReport (Connection connection) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        String fechaActual = dateFormat.format(new Date());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String consulta = "SELECT producto, cantidad, fecha_Ing FROM productos";
            ps = connection.prepareStatement(consulta);
            ps.setString(0, fechaActual);
            rs = ps.executeQuery();

            // Procesar y mostrar la información del informe
            // Puedes adaptar esta sección según tus necesidades
            while (rs.next()) {
                String producto = rs.getString("producto");
                String cantidad = rs.getString("cantidad");
                int fecha = rs.getInt("fecha_Ing");
                

                // Aquí puedes realizar cualquier operación que necesites con los datos obtenidos
                // Por ejemplo, imprimirlos, guardarlos en un archivo, etc.
                System.out.println("Producto: " + producto + ", Cantidad: " + cantidad + ", Fecha ingreso: " + fecha);
            }

            System.out.println("Informe mensual generado con éxito");

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al generar el informe mensual");

        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}