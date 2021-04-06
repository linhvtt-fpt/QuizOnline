/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuylinh.servlet;

import Answer.AnswerDAO;
import QuizDetail.QuizDetailDAO;
import QuizDetail.QuizDetailDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.Random;
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
@WebServlet(name = "SubmitQuizServlet", urlPatterns = {"/SubmitQuizServlet"})
public class SubmitQuizServlet extends HttpServlet {

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
        String button = request.getParameter("btnAction");
        try {
            ServletContext context = request.getServletContext();
            Map<String, String> index = (Map<String, String>) context.getAttribute("MAP");
            if (index != null) {
                url = index.get(url);
            }

            HttpSession session = request.getSession();
            Map<String, String> quiz = (Map<String, String>) session.getAttribute("QUIZ");
            int count = 0;
            for (Map.Entry<String, String> entry : quiz.entrySet()) {
                AnswerDAO answerDAO = new AnswerDAO();
                String ansIDtrue = answerDAO.answerCorrectByQuestionID(entry.getKey());
                if (ansIDtrue.trim().equals(entry.getValue().trim())) {
                    count++;
                }

            }
            SubjectDTO subjectDTO = (SubjectDTO) session.getAttribute("SubjectDTO");
            Date endTime = new Date();
            int numberOfquestion = subjectDTO.getNumOfQuestion();
            float point = (float) (10 * count) / numberOfquestion;
            request.setAttribute("NumberOfQuestion", numberOfquestion);
            request.setAttribute("NumberOfCorrect", count);
            request.setAttribute("Point", point);
            String email = (String) session.getAttribute("USER");
            String subjectID = (String) session.getAttribute("SubjectID");
            Random random = new Random();
            int randomNumber = random.nextInt(2000);
            String quizDetailID = subjectID + "-" + email + "-" + randomNumber;
            Date startTime = (Date) session.getAttribute("startTime");
            QuizDetailDTO quizDetailDTO = new QuizDetailDTO(quizDetailID, email, subjectID, count, point, startTime, endTime);
            QuizDetailDAO quizDetailDAO = new QuizDetailDAO();
            boolean result = quizDetailDAO.insertQuizDetail(quizDetailDTO);
            if (result) {
                session.removeAttribute("ContinuteTime");
                session.removeAttribute("attemquiz");
                session.removeAttribute("EndTime");
                session.removeAttribute("startTime");
                session.removeAttribute("QuestionInQuiz");
                session.removeAttribute("Answer");
                session.removeAttribute("QUESTIONID");
                session.removeAttribute("ANS_CHOOSED");
                session.removeAttribute("SubjectIDCurrent");
                session.removeAttribute("QUIZ");
                session.removeAttribute("PAGESIZE");
                session.removeAttribute("RecordOnPage");
                if (button.equals("Log Out")) {
                    url = "logOut";
                    url = index.get(url);
                }
               

            }
        } catch (SQLException e) {
            log("submitQuizServlet SLQException " + e.getMessage());
        } catch (NamingException e) {
            log("submitQuizServlet NamingException " + e.getMessage());
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
