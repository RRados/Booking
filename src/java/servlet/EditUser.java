package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import model.RoleDAL;

import model.Users;
import model.UsersDAL;

public class EditUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        int id = Integer.parseInt(request.getParameter("id"));
        int idRole = Integer.parseInt(request.getParameter("idRole"));
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        Users user = new Users(id, firstname, lastname, username, email);

        String obavestenje = "";
        String err = "";

        try 
        {
            UsersDAL userUpdate = new UsersDAL();
            RoleDAL userRole = new RoleDAL();

            if (userUpdate.editUser(user) == true && userRole.updateUserRole(id, idRole)) 
            {
                obavestenje = "Successfully updated user!";

                request.setAttribute("obavestenje", obavestenje);

                RequestDispatcher rd = request.getRequestDispatcher("/adminPages/adminPage.jsp");
                rd.forward(request, response);
            }
            else 
            {
                err = "Error updating user!";

                request.setAttribute("err", err);

                RequestDispatcher rd = request.getRequestDispatcher("/adminPages/adminPage.jsp");
                rd.forward(request, response);
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
