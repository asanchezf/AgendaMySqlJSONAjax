/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antonymail.views;

import com.antonymail.entities.Contactos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.antonymail.model.Conexion;
import com.google.gson.Gson;
import static java.lang.System.out;
import java.sql.*;
import java.util.ArrayList;

public class ListarContactos extends HttpServlet {

    
    public String getContactos() throws SQLException{
        
        /*Connection cn=conectar();
        String query="Select * from contactos";
        Statement st;
        ResultSet rs=null;
        try{
        st=cn.createStatement();
        rs=st.executeQuery(query);*/
         //Statement st=new Conexion().conectar().createStatement();

        
       Conexion cn=new Conexion();
        //cn.conectar();//NO hace falta....la clase Conexion en su método DevolverTodos, crea la conexión ejecuta la query y nos devuelve un resulset...
        
        ResultSet rs=cn.DevolverTodos();
        ArrayList<Contactos> contactos=new ArrayList<Contactos>();
        while(rs.next()){
            
            //contactos.add(new Contactos(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("telefono")));
            
            contactos.add(new Contactos(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("direccion"),rs.getString("telefono"),rs.getString("email"),rs.getString("observaciones") ,rs.getString("owner")) );
            
            
        }

        //El método va a devolver un objeto Gson...
        //Creamos un objeto JSon y le pasamos el arraylista que hemos obtenido en el while...
        return new Gson().toJson(contactos);
    }
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //response.setContentType("text/html;charset=UTF-8");
        //Se cambia el tipo que devuelve. será json en vez de html....
        response.setContentType("application/json;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            
            
            
            out.println(getContactos());
        }
        
        catch(Exception e){
            out.println("Se ha producido un error: " +e.getLocalizedMessage());
            
        }
        
        finally{
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
