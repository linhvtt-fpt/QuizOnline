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
import QuizDetail.QuizDetailDAO;
import QuizDetail.QuizDetailDTO;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;
import login.LoginDAO;
import subject.SubjectDAO;
import subject.SubjectDTO;

/**
 *
 * @author Thuy Linh
 */
@WebServlet(name = "ViewASubjectServlet", urlPatterns = {"/ViewASubjectServlet"})
public class ViewASubjectServlet extends HttpServlet {

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
        String subjectID = request.getParameter("txtSubjectID").trim();
        String url = "SEARCH_PAGE";
        String p = request.getParameter("page");
        int numberOfRecord = 0;
        int recordOnPage = 20;
        int pageSize;
        int offset;
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

            HttpSession session = request.getSession(true);
            session.setAttribute("SearchValue", "");
            session.setAttribute("cboStatusss", "ALL");
            session.setAttribute("SubjectID", subjectID);
            String email = (String) session.getAttribute("USER");
            LoginDAO dao = new LoginDAO();
            boolean result = dao.checkAdmin(email);
            if (result) {
                QuestionDAO questionDao = new QuestionDAO();
                offset = (pageID - 1) * recordOnPage;
                List<QuestionDTO> list = questionDao.searchQuestion("", "", subjectID, offset, recordOnPage);
                numberOfRecord = questionDao.getRecord("", "", subjectID);
                pageSize = (int) Math.ceil((double) numberOfRecord / recordOnPage);
                request.setAttribute("PAGESIZE", pageSize);
                request.setAttribute("currentPage", pageID);
                if (list != null) {
                    request.setAttribute("LISTQUESTION", list);
                    AnswerDAO answerDao = new AnswerDAO();
                    List<AnswerDTO> option = answerDao.getAnswer();
                    request.setAttribute("LISTANSWER", option);
                }
                url = "VIEW_BY_ADMIN_PAGE";
                url = index.get(url);
            } else {
                String subjectIDCurrent = (String) session.getAttribute("SubjectIDCurrent");
                if (subjectIDCurrent == null) {
                    SubjectDAO subjectDAO = new SubjectDAO();
                    SubjectDTO subjectDTO = subjectDAO.getSubjectByID(subjectID);
                    session.setAttribute("SubjectDTO", subjectDTO);
                    url = "VIEW_BY_STUDENT_PAGE";
                    url = index.get(url);
                } else {
                    if (subjectID.equals(subjectIDCurrent)) {
                        if (session.getAttribute("startTime") != null && session.getAttribute("QuestionInQuiz") != null && session.getAttribute("Answer") != null) {
                            request.setAttribute("page", 1);
                        url = "pagingInQuiz";
                            url = index.get(url);
                        }
                    } else {
                        request.setAttribute("Notification", "You are doing the " + subjectIDCurrent + " quiz");
                        url = "SEARCH_PAGE";
                        url = index.get(url);
                    }
                }
            }

        } catch (SQLException e) {
            log("ViewASubject SQLException " + e.getMessage());
        } catch (NamingException e) {
            log("ViewASubject NamingException " + e.getMessage());
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
