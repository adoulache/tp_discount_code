package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdbc.DAO;
import jdbc.DiscountEntity;
import simplejdbc.DAOException;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author Anass
 */
@WebServlet(name = "ShowDiscountCodeController", urlPatterns = {"/ShowDiscountCodeController"})
public class ShowDiscountCodeController extends HttpServlet {

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
		try {
                    
                    DAO dao = new DAO(DataSourceFactory.getDataSource());
                    List<DiscountEntity> discountCode = dao.findall();
                    // On renseigne un attribut utilisé par la vue
                    request.setAttribute("discountList", discountCode);
                    
                    try {
                        if(request.getParameter("action").equals("ADD")){
                            
                            String code = request.getParameter("code");
                            String rate = request.getParameter("taux");
                            if (code == null)
                                    throw new Exception("Les paramètres n'ont pas été transmis");

                            // on doit convertir cette valeur en entier (attention aux exceptions !)
                            float taux = Float.valueOf(rate) ;
                            dao.addDiscountCode(code, taux);
                            
                        }else if(request.getParameter("action").equals("DELETE")){
                            try {

                                String code = request.getParameter("code");
                                if (code == null)
                                        throw new Exception("Le paramètre \"code\" n'a pas été transmis");

                                dao.deleteDiscountCode(code);

                            } catch (Exception e){
                                request.setAttribute("error", e.getMessage());
                                request.getRequestDispatcher("Views/discountListView.jsp").forward(request, response);
                            }
                        }
                    } catch (Exception e){
                        request.setAttribute("error", e.getMessage());
                        request.getRequestDispatcher("Views/discountListView.jsp").forward(request, response);
                    }
                    
                    //List<DiscountEntity> discountCode = dao.findall();
                    // On renseigne un attribut utilisé par la vue
                    request.setAttribute("discountList", discountCode);
                    // On redirige vers la vue
                    request.getRequestDispatcher("Views/discountListView.jsp").forward(request, response);

		} catch (ServletException | IOException | DAOException e) {
                    request.setAttribute("error", e.getMessage());			
                    request.getRequestDispatcher("Views/discountListView.jsp").forward(request, response);
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
