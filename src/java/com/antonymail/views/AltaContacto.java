
package com.antonymail.views;

import com.antonymail.entities.Contactos;
import com.antonymail.model.Conexion;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Susana
 */
public class AltaContacto extends HttpServlet {

    
    public void insertarContacto(String nombre, String apellidos, String direccion, String telefono, String email, String observaciones, String propietario) throws SQLException{
        
        /*Connection cn=conectar();
        String query="Select * from contactos";
        Statement st;
        ResultSet rs=null;
        try{
        st=cn.createStatement();
        rs=st.executeQuery(query);*/
         //Statement st=new Conexion().conectar().createStatement();

        
       Conexion cn=new Conexion();
        //cn.conectar();//NO hace falta....la clase Conexion en su método DevolverTodos, crea la conexión ejecuta el insert en BB.DD.
        
       cn.insertar(nombre,apellidos, direccion,telefono,email,observaciones,propietario);
               
    }
    
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");//En este caso no devolvemos Json. Devolvemos html...
            try (PrintWriter out = response.getWriter()) {
            
            
                //LLamamos al método para grabar en BB.DD.
                
            insertarContacto(request.getParameter("nombre"), request.getParameter("apellidos"), request.getParameter("direccion"), request.getParameter("telefono"), request.getParameter("email"), request.getParameter("observaciones"), request.getParameter("propietario"));
            //insertarContacto(null, null, null, null, null, null, null);
              out.println("Se ha dado de alta un nuevo registro correctamente");
              
              /*RequestDispatcher reenvio;
              reenvio=getServletContext().getRequestDispatcher("/ListarContactos");
              //redireccionamiento...
              reenvio.forward(request, response);*/
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
