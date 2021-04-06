/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuylinh.servlet;

import StudentEnrollSubject.StudentEnrollSubjectDAO;
import StudentEnrollSubject.StudentEnrollSubjectDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import login.LoginCreateError;
import login.LoginDAO;
import login.LoginDTO;
import subject.SubjectDAO;
import subject.SubjectDTO;

/**
 *
 * @author Thuy Linh
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String email = request.getParameter("txtEmail").trim();
        String password = request.getParameter("txtPassword").trim();
        String name = request.getParameter("txtName").trim();
        String confirm = request.getParameter("txtConfirm").trim();
        LoginCreateError err = new LoginCreateError();
        boolean foundErr = false;
        String url = "SignUp_PAGE";
        String urlIndex = null;
        String button = request.getParameter("btnAction");
        try {
            ServletContext context = request.getServletContext();
            Map<String, String> index = (Map<String, String>) context.getAttribute("MAP");
            if (index != null) {
                urlIndex = index.get(url);
            }
            if(button.equals("Log In")){
                url = "";
               urlIndex = index.get(url);
            }
            else{
            String format = "\\w+@\\w+[.]\\w+([.]\\w+)?";
            if (!email.trim().matches(format)) {
                err.setEmailFormatError("Email format: linh244@fpt.edu.vn");
                foundErr = true;
            }
            if (email.trim().length() < 5 || email.trim().length() > 50) {
                err.setEmailLengError("Email requires typing from 5 to 50 chars");
                foundErr = true;
            }
            if (name.trim().length() < 3 || name.trim().length() > 50) {
                err.setNameLengError("Name requires typing form 3 to 50 chars");
                foundErr = true;
            }
            if (password.trim().length() < 8 || password.trim().length() > 15) {
                err.setPasswordLengError("Password requires typing from 8 to 15 chars");
                foundErr = true;
            } else if (!password.trim().equals(confirm)) {
                err.setConfirmNotMatched("Confirm must match password");
                foundErr = true;
            }
            if (foundErr) {
                request.setAttribute("CREATE_ERROR", err);
            } else {
                LoginDAO dao = new LoginDAO();
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(password.getBytes(StandardCharsets.UTF_8));
                byte[] digest = md.digest();
                String hex = String.format("%064x", new BigInteger(1, digest));
                LoginDTO dto = new LoginDTO(email, hex, "Student", "NEW", name);
                boolean result = dao.SignUp(dto);
                if (result) {
                    SubjectDAO subjectDAO = new SubjectDAO();
                    List<SubjectDTO> subject = subjectDAO.getSubject();
                    String[] str = email.split("@");
                    int count = 1;
                    for (SubjectDTO subjectDTO : subject) {
                        String enrollID = str[0] + "-" + count;
                        StudentEnrollSubjectDTO enrollDTO = new StudentEnrollSubjectDTO(enrollID, email, subjectDTO.getSubjectID());
                        StudentEnrollSubjectDAO enrollDAO = new StudentEnrollSubjectDAO();
                        boolean rs = enrollDAO.insertStudentEnroll(enrollDTO);
                        count++;
                    }

                }
                url = "";
                urlIndex = index.get(url);
            }
            }

        } catch (SQLException e) {
            log("SignUpServlet SQLException " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                err.setEmailIsExisted("This Email is existed");
                request.setAttribute("CREATE_ERROR", err);
            }
        } catch (NamingException e) {
            log("SignUpServlet NamingException " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            log("SignUpServlet NoSuchAlgorithmException " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(urlIndex);
            rd.forward(request, response);
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
