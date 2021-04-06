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
import java.util.ArrayList;
import java.util.Map;
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
@WebServlet(name = "UpdateAndDeleteServlet", urlPatterns = {"/UpdateAndDeleteServlet"})
public class UpdateAndDeleteServlet extends HttpServlet {

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
        try {
            HttpSession session = request.getSession();
            String questionID = request.getParameter("txtQuestionID");
            String[] ansID = request.getParameterValues("txtAnswerID");

            if (questionID != null) {
                QuestionDAO quesDAO = new QuestionDAO();
                QuestionDTO question = quesDAO.findQuestionByID(questionID);
                session.setAttribute("QUESTION", question);
            }
            if(ansID!=null){
            AnswerDAO ansDAO = new AnswerDAO();
            ArrayList<AnswerDTO> answer = new ArrayList<>();
            for (String ID : ansID) {
                AnswerDTO ansDTO = ansDAO.findAnswerByID(ID);
                answer.add(ansDTO);
            }

            if (answer != null) {
                session.setAttribute("ANSWER", answer);
            }
            }
            String searchValue = (String) session.getAttribute("SearchValue");
            String cboStatus = (String) session.getAttribute("cboStatusss");
            String url = null;
            String button = request.getParameter("btnAction");

            if (button.trim().equals("Update")) {
                AnswerDAO ansDAO = new AnswerDAO();
                String answerCorrectID = ansDAO.answerCorrectByQuestionID(questionID);
                request.setAttribute("AnswerCorrectID", answerCorrectID);
                url = "Update_PAGE";
                ServletContext context = request.getServletContext();
                Map<String, String> index = (Map<String, String>) context.getAttribute("MAP");
                if (index != null) {
                    url = index.get(url);
                }
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else if (button.trim().equals("Delete")) {
                String questionStatus = request.getParameter("txtQuestionStatus");
                if (questionStatus.equals("Active")) {
                    QuestionDAO questionDAO = new QuestionDAO();
                    boolean result = questionDAO.deleteQuestion(questionID);
                    if (result) {
                        String urlRewriting = "searchQuestion?"
                                + "&txtSearchValue=" + searchValue
                                + "&cboStatus=" + cboStatus;
                        response.sendRedirect(urlRewriting);
                    }
                }
            }

        } catch (Exception e) {
            log("UpdateAndDeleteServlet Exception " + e.getMessage());
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
