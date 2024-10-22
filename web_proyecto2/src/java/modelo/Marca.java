/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class Marca extends Producto {
    private String producto;
    private int id_Marca;
    private Conexion cn; 

    public Marca() {}

    public Marca(String producto, int id_Marca, int id, int existencia, String descripcion, String imagen, String fecha_ingreso, double precio_costo, double precio_venta) {
        super(id, existencia, descripcion, imagen, fecha_ingreso, precio_costo, precio_venta);
        this.producto = producto;
        this.id_Marca = id_Marca;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getId_Marca() {
        return id_Marca;
    }

    public void setId_Marca(int id_Marca) {
        this.id_Marca = id_Marca;
    }

    public DefaultTableModel leer(){
       DefaultTableModel tabla = new DefaultTableModel();
       try{
           
           
           cn = new Conexion();
           cn.abrir_conexion();
           String query = "SELECT p.id_Producto AS id, p.producto, p.id_Marca, p.Descripcion, p.Imagen, p.precio_costo, p.precio_venta, p.existencia, p.fecha_ingreso, m.marca FROM productos AS p INNER JOIN marcas AS m ON p.id_Marca = m.id_Marca;";
           ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
           String encabezado[] = {"id,","producto","id_marca","Descripcion","Imagen","precio costo","precio venta","existencia","fecha_ingreso","marca"};
           tabla.setColumnIdentifiers(encabezado);
        
       
        String datos[] = new String[10];
        
        
        while (consulta.next()) {
            datos[0] = consulta.getString("id");
            datos[1] = consulta.getString("producto");
            datos[2] = consulta.getString("id_Marca");
            datos[3] = consulta.getString("Descripcion");
            datos[4] = consulta.getString("Imagen");
            datos[5] = consulta.getString("precio_costo");
            datos[6] = consulta.getString("precio_venta");
            datos[7] = consulta.getString("existencia");
            datos[8] = consulta.getString("fecha_ingreso");
            datos[9] = consulta.getString("marca");
           
            tabla.addRow(datos);
        }
           
           cn.cerrar_conexion();
       }catch(Exceptioin ex){
           
           
       }
       
       
       
       return tabla;
    }
    
    
    
    
    
    public int agregar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            
            // Corregir la consulta SQL
            String query = "INSERT INTO productos (producto, id_Marca, Descripcion,imagen, precio_costo, precio_venta, existencia, fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?, ?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            
           
            parametro.setString(1, getProducto());                 
            parametro.setInt(2, getId_Marca());                   
            parametro.setString(3, getDescripcion());              
           
            parametro.setDouble(4, getPrecio_costo());             
            parametro.setDouble(5, getPrecio_venta());             
            parametro.setInt(6, getExistencia()); 
            parametro.setString(7, getFecha_ingreso());
             parametro.setString(8, getImagen());
           
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (Exception ex) {
            System.out.println("error"+ ex.getMessage());
            retorno = 0; 
        }
        
        return retorno;
    }
}
