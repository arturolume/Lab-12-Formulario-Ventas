package logica;

import conexion.Cls_conexion;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.TableModel;
/**
 *
 * @author Usuario
 */
public class Cls_ventas {
    private final String SQL_INSERT = "INSERT INTO ventas (codigo, nombre, cantidad, precio) values (?,?,?,?)";
    private final String SQL_SELECT = "SELECT * FROM ventas"; 
    private PreparedStatement PS;
    private DefaultTableModel DT;
    private ResultSet RS;
    private Cls_conexion CN;
    
    public Cls_ventas(){
        PS = null;
        CN = new Cls_conexion();
    }
    
    private DefaultTableModel setTitulos(){
        DT = new DefaultTableModel();
        DT.addColumn("Codigo");
        DT.addColumn("Nombre");
        DT.addColumn("Cantidad");
        DT.addColumn("Precio");
        return DT;
    }
    
    public int insertarDatos(int codigo, String nombre, int cantidad, double precio){
        try{
            PS = CN.getConnection().prepareStatement(SQL_INSERT);
            PS.setInt(1, codigo);
            PS.setString(2, nombre);
            PS.setInt(3, cantidad);
            PS.setDouble(4, precio);
            int res = PS.executeUpdate();
            if (res > 0){
                JOptionPane.showMessageDialog(null, "Registro guardado..");
            }
        }catch(Exception e){
            System.err.println("Error al guardar los datos en la db: "+e.getMessage());
        }finally{
            PS = null;
            CN.close();
        }
        return 0;
    }
    
    public TableModel getDatos(int ctr, String prm){
        String SQL;
        if (ctr == 0){
            SQL = "SELECT * FROM ventas WHERE nombre ="+prm;
        } else{
            SQL = "SELECT * FROM ventas WHERE precio ="+prm;
        }
        try{
            setTitulos();
            PS = CN.getConnection().prepareStatement(SQL);
            RS = PS.executeQuery();
            Object[] fila = new Object [4];
            while(RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getInt(3);
                fila[3] = RS.getDouble(4);
                DT.addRow(fila);
            }
        } catch(SQLException e){
            System.out.println("Error al listar los datos: "+e.getMessage());
        }finally{
            PS = null;
            RS = null;
            CN.close();
        }
        return DT;
    }
}
