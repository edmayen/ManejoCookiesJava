/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author edmayen
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
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
        //suponer que el usuario está visitando por primera vez el sitio
        boolean nuevoUsuario = true;
        
        //obtenemos todas la cookies
        Cookie[] cookies = request.getCookies();
        
        //buscamo si ya existe una cookie creada con anterioridad
        //llamada visitanteRecurrente
        if(cookies != null)
        {
            for(Cookie c : cookies)
            {
                if(c.getName().equals("visitanteRecurrente") && c.getValue().equals("si"))
                {
                    //en casod de que ya exista una cookie indicando que ya existe un usuario registrado
                    //con anterioridad poenmos la bandera en falso y salimos del ciclo
                    nuevoUsuario = false;
                    break;
                }
            }
        }
        //revisamos si el usuario es un nuevo visitante
        String mensaje = null;
        if(nuevoUsuario)
        {
            //en caso de ser un nuevo usuario creamos el objeto cookie 
            Cookie visitanteCookie = new Cookie("visitanteRecurrente", "si");
            //agregamos la cookie en la resouesta
            response.addCookie(visitanteCookie);
            mensaje = "Gracias por visitar nuestro sitio";
        }
        else
        {
            mensaje = "Gracias por visitar nuevamente nuestro sitio";
        }
        //Escribimos la salida
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //omitimos codigo html para simplificar el codigo
        out.println("Mensaje: " + mensaje);
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
