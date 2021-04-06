/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuylinh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

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
        String button = request.getParameter("btnAction");
        String[] questionID = request.getParameterValues("txtquestionID");
        String p = request.getParameter("page");

        String url = "submitQuiz";
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
            List<String> ansChoosed = new ArrayList<>();

            HttpSession session = request.getSession();
            Map<String, String> quiz = (Map<String, String>) session.getAttribute("QUIZ");

            for (String ans : questionID) {
                String ansChoose = request.getParameter(ans);
                if (ansChoose == null) {
                    ansChoose = "";
                }
                if (quiz.containsKey(ans)) {
                    quiz.replace(ans, ansChoose);
                                   } 
            }
                    session.setAttribute("QUIZ", quiz);

                    for (Map.Entry<String, String> entry : quiz.entrySet()) {
                        ansChoosed.add(entry.getValue());
                    }

                    SubjectDTO subject = (SubjectDTO) session.getAttribute("SubjectDTO");
                    Calendar continuteTime = Calendar.getInstance();
                    session.setAttribute("ContinuteTime", continuteTime.getTime().getTime());
                    session.setAttribute("QUESTIONID", questionID);
                    session.setAttribute("ANS_CHOOSED", ansChoosed);
                    session.setAttribute("SubjectIDCurrent", subject.getSubjectID());
                    if (button == null) {
                        url = "submitQuiz";
                        url = index.get(url);
                    } else if (button.equals("Subject")) {
                        url = "SEARCH_PAGE";
                        url = index.get(url);
                    } else if (button.equals("Log Out")) {
                        url = "submitQuiz";
                        url = index.get(url);
                    } else if (button.equals("Finish")) {
                        url = "submitQuiz";
                        url = index.get(url);
                    } else if (button.equals("Previous")) {
                        request.setAttribute("page", pageID - 1);
                        url = "pagingInQuiz";
                        url = index.get(url);
                    } else if (button.equals("Next")) {
                        request.setAttribute("page", pageID + 1);
                        url = "pagingInQuiz";
                        url = index.get(url);
                    }
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                }catch (NullPointerException e) {
            log("DispatchServlet NullpointerException " + e.getMessage());
        }catch (Exception e) {
            log("DispatchServlet Exception " + e.getMessage());
        }finally {
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
            protected void doGet
            (HttpServletRequest request, HttpServletResponse response)
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
            protected void doPost
            (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                processRequest(request, response);
            }

            /**
             * Returns a short description of the servlet.
             *
             * @return a String containing servlet description
             */
            @Override
            public String getServletInfo
            
                () {
        return "Short description";
            }// </editor-fold>

        }
