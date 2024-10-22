/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Marca;
/**
 *
 * @author pc
 */
public class sr_producto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     Marca marca;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_producto</title>");
            out.println("</head>");
            out.println("<body>");
            
            String producto = request.getParameter("txt_producto");
            int idMarca = Integer.parseInt(request.getParameter("drop_marca"));
            String descripcion = request.getParameter("txt_descripcion");
            String imagen = request.getParameter("txt_imagen"); 
            double precioCosto = Double.parseDouble(request.getParameter("txt_precio_costo"));
            double precioVenta = Double.parseDouble(request.getParameter("txt_precio_venta"));
            int existencia = Integer.parseInt(request.getParameter("txt_existencia"));
            String fechaIngreso = request.getParameter("txt_fecha_ingreso");

            

            // Instancia el objeto Marca y asigna los valores obtenidos
             marca = new Marca(producto, idMarca, 0, existencia, descripcion,imagen, fechaIngreso, precioCosto, precioVenta);


            // Botón Agregar
            if("agregar".equals( request.getParameter("btn_agregar"))){
                
           
            if(marca.agregar()> 0){
                response.sendRedirect("index.jsp");
            }else{
                 out.println("<h1>Error............</h1>");
                 out.println("<a href='index.jsp'>Regresar</a>");
            }
                
            }
            
            out.println("</body>");
            out.println("</html>");
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

