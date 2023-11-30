
package model;

import view.InterfazView;
import view.Loginview;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;;
import java.sql.Statement;
public class RegistroUsuarioSql extends javax.swing.JFrame {
    public Connection conexion;
    public Statement sentencia;
    public ResultSet resultado;
    public Connection ConectarBasedeDatos() {
        try {
            final String Controlador = "com.mysql.jdbc.Driver";
            Class.forName(Controlador);
            final String url_bd = "jdbc:mysql://localhost:3306/usuarios";
            conexion = DriverManager.getConnection(url_bd, "root", "");
            sentencia = conexion.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "No se pudo conectar ", JOptionPane.ERROR_MESSAGE);
             return conexion;
        }
        return null;
    }
    public void ValidarUsuario(){
        try {
            Loginview con= new Loginview();
            String Usuario= "";
            String Contraseña= "";
            con.ConectarBasedeDatos();
            String Sql= "select * from usuario where Usuario='"+Usuario+"' and Contraseña='"+Contraseña+"' ";
            con.resultado=con.sentencia.executeQuery(Sql);
            if(con.resultado.next()){
                setVisible(false);
                InterfazView menu= new InterfazView();
                menu.setVisible(true);}
            else{
                JOptionPane.showMessageDialog(null,"Usuario o contraseña invalido","conexion",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}