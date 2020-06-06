
package com.emergentes.controlador;


import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Inicio", urlPatterns = {"/Inicio"})
public class Inicio extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ProductoDAO dao = new ProductoDAOimpl();
            // para recibir el id
            int  id;
            //para gestionar registros
            Producto pro = new Producto();
            
            String action = (request.getParameter("action")!= null)? request.getParameter("action"): "view";
            switch(action){
                case "add":
                    //Nuevo registro
                    request.setAttribute("producto", pro);
                    request.getRequestDispatcher("frmproducto.jsp").forward(request, response);
                    break;
                case "edit":
                    // Para editar un registro
                    id = Integer.parseInt(request.getParameter("id"));
                    pro = dao.getById(id);
                    request.setAttribute("producto", pro);
                    request.getRequestDispatcher("frmproducto.jsp").forward(request, response);
                    break;
                case "delete":
                    //Para eleiminar un registro
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                   response.sendRedirect("Inicio");
                    break;
                default:
                    // Listar los registros
                    List<Producto> lista = dao.getAll();
                    request.setAttribute("productos", lista);
                    request.getRequestDispatcher("listaproducto.jsp").forward(request, response);
                    break;
                    
            }
            
        } catch (Exception e) {
            System.out.println("Error "+e.getMessage());
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ProductoDAO dao = new ProductoDAOimpl();
        int id = Integer.parseInt(request.getParameter("id")); 
        String descripcion = request.getParameter("descripcion");
        int stock =Integer.parseInt("stock");
        
        Producto pro = new Producto();
        pro.setId(id);
        pro.setDescripcion(descripcion);
        pro.setStock(stock);
        
        if (id ==0){
            // Nuevo registro
            try {
                dao.insert(pro);
                response.sendRedirect("Inicio");
            } catch (Exception e) {
                System.out.println("Error "+ e.getMessage());
            }
            
        }
        else{ 
            //Actualizacion
             try {
  
                dao.update(pro);
                response.sendRedirect("Inicio");
            } catch (Exception e) {
                System.out.println("Error "+ e.getMessage());
            }
        
        }
       
    }

    
}
