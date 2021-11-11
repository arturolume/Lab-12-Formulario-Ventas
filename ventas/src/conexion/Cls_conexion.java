package conexion;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.transform.Result;
import java.util.Scanner;
import javax.swing.JOptionPane;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.transform.Result;

public class Cls_conexion {
        private Connection conn;
     
        private static final String driver   = "com.mysql.jdbc.Driver";
        private static final String url      = "jdbc:mysql://localhost:3306/ventas?autoReconnect=true&useSSL=false";
        private static final String user     = "root";
        private static final String pass     = "Reyes562";
        
        public Cls_conexion(){
            conn = null;
        }
        
        public Connection getConnection(){
            try{
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, pass);
            } catch (ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al conectar con la base de datos", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            return conn;
        }
        
        public void close(){
            try{
                conn.close();
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al cerrar la conexion con la base de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
}
