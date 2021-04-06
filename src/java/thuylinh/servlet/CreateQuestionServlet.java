/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuylinh.servlet;

import Answer.AnswerDAO;
import Answer.AnswerDTO;
import Question.CreateQuestionErr;
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
@WebServlet(name = "CreateQuestionServlet", urlPatterns = {"/CreateQuestionServlet"})
public class CreateQuestionServlet extends HttpServlet {

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
        String questionID = request.getParameter("txtQuestionID");
        String content = request.getParameter("txtContent");
        String answer1 = request.getParameter("txtAnswer1");
        String answer2 = request.getParameter("txtAnswer2");
        String answer3 = request.getParameter("txtAnswer3");
        String answer4 = request.getParameter("txtAnswer4");
        String answerCorrect = request.getParameter("answer_correct");
        String status = request.getParameter("cboStatus");
        CreateQuestionErr err = new CreateQuestionErr();
        String url = "CreateQuestion_PAGE";
        ServletContext context = request.getServletContext();
        Map<String, String> index = (Map<String, String>) context.getAttribute("MAP");
        try {

            if (index != null) {
                url = index.get(url);
            }
            boolean foundErr = false;
            if (questionID.length() < 2 || questionID.length() > 10) {
                err.setIDLengthErr("QuestionID require from 2 to 10 chars");
                foundErr = true;
            }
            if (content.length() < 2) {
                err.setContentQuestionLengthErr("Content question requires from 2 chars");
                foundErr = true;
            }
            if (answer1.equals("") && answer2.equals("") && answer3.equals("") && answer4.equals("")) {
                err.setAnswerNull("Please fill in at least 1 answer.");
                foundErr = true;
            }
            if (answerCorrect == null) {
                err.setRadioNull("Please choose answer correct");
                foundErr = true;
            }
            if (foundErr) {
                request.setAttribute("ERROR", err);
                url = "CreateQuestion_PAGE";
                url = index.get(url);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                boolean result = false;
                HttpSession session = request.getSession();
                String subjectID = (String) session.getAttribute("SubjectID");
                QuestionDTO questionDTO = new QuestionDTO(questionID, subjectID, content, status);
                QuestionDAO questionDAO = new QuestionDAO();
                result = questionDAO.insertQuestion(questionDTO);
                if (result) {
                    boolean ans = false;
                    if (!answer1.equals("")) {
                        String answerID1 = "Ans1_" + questionID;
                        boolean statusAns = false;
                        if (answerCorrect.equals("1")) {
                            statusAns = true;
                        }
                        AnswerDTO ansDTO = new AnswerDTO(answerID1, questionID, answer1, statusAns);
                        AnswerDAO ansDAO = new AnswerDAO();
                        ans = ansDAO.insertAns(ansDTO);
                    }
                    if (!answer2.equals("")) {
                        String answerID2 = "Ans2_" + questionID;
                        boolean statusAns = false;
                        if (answerCorrect.equals("2")) {
                            statusAns = true;
                        }
                        AnswerDTO ansDTO = new AnswerDTO(answerID2, questionID, answer2, statusAns);
                        AnswerDAO ansDAO = new AnswerDAO();
                        ans = ansDAO.insertAns(ansDTO);
                    }
                    if (!answer3.equals("")) {
                        String answerID3 = "Ans3_" + questionID;
                        boolean statusAns = false;
                        if (answerCorrect.equals("3")) {
                            statusAns = true;
                        }
                        AnswerDTO ansDTO = new AnswerDTO(answerID3, questionID, answer3, statusAns);
                        AnswerDAO ansDAO = new AnswerDAO();
                        ans = ansDAO.insertAns(ansDTO);
                    }
                    if (!answer4.equals("")) {
                        String answerID4 = "Ans4_" + questionID;
                        boolean statusAns = false;
                        if (answerCorrect.equals("4")) {
                            statusAns = true;
                        }
                        AnswerDTO ansDTO = new AnswerDTO(answerID4, questionID, answer4, statusAns);
                        AnswerDAO ansDAO = new AnswerDAO();
                        ans = ansDAO.insertAns(ansDTO);
                    }
                    String searchValue = (String) session.getAttribute("SearchValue");
                    String cboStatus = (String) session.getAttribute("cboStatusss");
                    url = "searchQuestion?"
                            + "&txtSearchValue=" + searchValue
                            + "&cboStatus=" + cboStatus;
                    response.sendRedirect(url);
                }
            }

        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate")) {
                err.setIDisExisted(questionID + " is existed");
                request.setAttribute("ERROR", err);
                url = "CreateQuestion_PAGE";
                url = index.get(url);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
        } catch (NamingException e) {
            log("CreateQuestionServlet NamingException " + e.getMessage());
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
