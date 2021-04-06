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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import javax.servlet.http.HttpSession;
import subject.SubjectDTO;

/**
 *
 * @author Thuy Linh
 */
@WebServlet(name = "TakeAQuizServlet", urlPatterns = {"/TakeAQuizServlet"})
public class TakeAQuizServlet extends HttpServlet {

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
        String url = "TakeAQuiz_PAGE";

        String p = request.getParameter("page");
        int numberOfRecord = 0;
        int recordOnPage = 10;
        int pageSize;
        try {
            int pageID = 1;
            if (p != null) {
                pageID = Integer.parseInt(p);
            }

            ServletContext context = request.getServletContext();
            Map<String, String> index = (Map<String, String>) context.getAttribute("MAP");
            if (index != null) {
                url = index.get(url);
            }
            HttpSession session = request.getSession();
            SubjectDTO subjectDTO = (SubjectDTO) session.getAttribute("SubjectDTO");
            QuestionDAO questionDAO = new QuestionDAO();
            List<QuestionDTO> listQuestion = questionDAO.getQuestionInQuiz(subjectDTO.getNumOfQuestion(), subjectDTO.getSubjectID());
                        
            if (listQuestion == null || listQuestion.size()<subjectDTO.getNumOfQuestion()) {
                request.setAttribute("ERROR", "Not enough question to do the quiz");
                url = "VIEW_BY_STUDENT_PAGE";
                url = index.get(url);
            }
            
                        
            Map<String, String> quiz = new HashMap<>();
            for (QuestionDTO questionDTO : listQuestion) {
                quiz.put(questionDTO.getQuestionID(), "");
            }
            session.setAttribute("QUIZ", quiz);
            
            numberOfRecord = listQuestion.size();
            pageSize = (int) Math.ceil((double) numberOfRecord / recordOnPage);
            int begin = (pageID - 1) * recordOnPage;
            int end = begin + recordOnPage - 1;
            request.setAttribute("BEGIN", begin);
            request.setAttribute("END", end);
            session.setAttribute("PAGESIZE", pageSize);
            request.setAttribute("currentPage", pageID);
            session.setAttribute("RecordOnPage", recordOnPage);

            AnswerDAO answerDAO = new AnswerDAO();
            List<AnswerDTO> answerList = answerDAO.getAnswer();
            session.setAttribute("QuestionInQuiz", listQuestion);
            session.setAttribute("Answer", answerList);
            Calendar cal = Calendar.getInstance();
            session.setAttribute("attemquiz", cal.getTime().getTime());
            cal.add(Calendar.MINUTE, +subjectDTO.getTimeQuiz());
            session.setAttribute("EndTime", cal.getTime().getTime());
            Date startingTime = new Date();
            session.setAttribute("startTime", startingTime);
        } catch (SQLException e) {
            log("TakeAQuizServlet SQLException " + e.getMessage());
        } catch (NamingException e) {
            log("TakeAQuizServlet NamingException " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
