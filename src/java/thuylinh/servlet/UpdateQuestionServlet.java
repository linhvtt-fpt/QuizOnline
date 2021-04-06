/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuylinh.servlet;

import Answer.AnswerDAO;
import Answer.AnswerDTO;
import Question.QuestionDAO;
import Question.QuestionDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thuy Linh
 */
@WebServlet(name = "UpdateQuestionServlet", urlPatterns = {"/UpdateQuestionServlet"})
public class UpdateQuestionServlet extends HttpServlet {

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
        String subjectID = request.getParameter("cboSubject");
        String questionID = request.getParameter("txtQuestionID");
        String questionContent = request.getParameter("txtQuestionContent");
        String questionStatus = request.getParameter("cboStatus");
        String[] ansID = request.getParameterValues("txtAnsID");
        String[] ansContent = request.getParameterValues("txtAnswer");

        String url = "Update_PAGE";
        try {
            ServletContext context = request.getServletContext();
            Map<String, String> index = (Map<String, String>) context.getAttribute("MAP");
            if (index != null) {
                url = index.get(url);
            }
            String ansChoose = request.getParameter("answer_correct");
            if (ansChoose == null) {
                String err = "Please choose answer correct";
                request.setAttribute("Error", err);
                url = "Update_PAGE";
                url = index.get(url);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                int ansCorrect = Integer.parseInt(ansChoose);
                QuestionDTO questionDTO = new QuestionDTO(questionID, subjectID, questionContent, questionStatus);
                QuestionDAO questionDAO = new QuestionDAO();
                boolean result = questionDAO.updateQuestion(questionDTO);
                if (result) {
                    for (int i = 0; i < ansID.length; i++) {
                        boolean status = false;
                        if (ansCorrect == (i + 1)) {
                            status = true;
                        }
                        AnswerDTO ansDTO = new AnswerDTO(ansID[i], questionID, ansContent[i], status);
                        AnswerDAO dao = new AnswerDAO();
                        dao.updateAnswer(ansDTO);
                    }
                    HttpSession session = request.getSession();
                    String searchValue = (String) session.getAttribute("SearchValue");
                    String cboStatus = (String) session.getAttribute("cboStatusss");
                    url = "searchQuestion?"
                            + "&txtSearchValue=" + searchValue
                            + "&cboStatus=" + cboStatus;
                    response.sendRedirect(url);
                }
            }
        } catch (SQLException e) {
            log("UpdateQuestionServlet SQLException " + e.getMessage());
        } catch (NamingException e) {
            log("UpdateQuestionServlet NamingException " + e.getMessage());
        } finally {

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
