/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antonymail.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Susana
 */
public class Conexion {

    private Connection cn;
    
    
    public Conexion() {
    }
    
    public Connection conectar  (){
        
        cn=null;
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");//Nombre de la clase del driver de conexión 
            cn=DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "root");//ruta de la bb.dd, usuario, contraseña
            
            
        } catch (Exception e) {
        }
        
        return cn;
    }
    
    public void CerrarConexion(){
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void insertar(String nombre, String apellidos, String direccion, String telefono, String email, String observaciones, String propietario){
        
        try {
            //Creamos una conexión llamando al método conectar...
            Connection cn;
            cn=conectar();
            PreparedStatement pst = cn.prepareStatement("INSERT INTO contactos (AndroidID, Nombre, Apellidos, Direccion, Telefono, Email, Id_Categoria, Observaciones, Owner) VALUES (?,?,?,?,?,?,?,?,?)");
            
            
            //Posición y nombre del campo
            pst.setString(1, null);//Viene desde una página web. no debe tener AndroidID
            pst.setString(2, nombre);
            pst.setString(3, apellidos);
            pst.setString(4, direccion);
            pst.setString(5, telefono);
            pst.setString(6, email);
            pst.setInt(7, 5);//No tiene categoría. Viene de la web...
            pst.setString(8, observaciones);
            pst.setString(9, propietario);
            
            
                    
                    
            pst.execute();
            //CerrarConexion();
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
    public ResultSet DevolverTodos (){
        
            Connection cn=conectar();
            String query="Select * from contactos";
            Statement st;
            ResultSet rs=null;
            try{                    
            st=cn.createStatement();
            rs=st.executeQuery(query);
            //CerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
        
        
    }
    
    public ResultSet DevolverUsuario(String nombre){
         Connection cn=conectar();
          ResultSet rs=null;
        try {
           //PrepareStatement pq hay una condición where y se complica la sintaxis...
            PreparedStatement pst = cn.prepareStatement("Select * from contacto where nombre=?");
            pst.setString(1, nombre);//Posición y nombre del campo..
            rs=pst.executeQuery();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        //CerrarConexion();
        return rs;
        
    }
    
    public void BorrarUsuario(int id){
        Connection cn=conectar();
          
          try {
           //PrepareStatement pq hay una condición where y se complica la sintaxis...
            PreparedStatement pst = cn.prepareStatement("DELETE from contactos where Id=?");
            pst.setInt(1, id);//Posición y nombre del campo..
            pst.execute();
            //CerrarConexion();
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void ActualizarUsuario(String nombre, String email, int Id){
        Connection cn=conectar();
        
          try {
           //PrepareStatement pq hay una condición where y se complica la sintaxis...
            PreparedStatement pst = cn.prepareStatement("UPDATE contactos set Nombre=?,Email=? WHERE Id=?");
            pst.setString(1, nombre);//Posición y nombre del campo..
            pst.setString(2, email);
            pst.setInt(3, Id);
            pst.executeUpdate();
            //CerrarConexion();
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
