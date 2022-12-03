package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        System.out.println("Updateservlet1");

        String _token = request.getParameter("_token");
        System.out.println("Updateservlet2");
        System.out.println("Updateservlet2"+ _token);
        System.out.println(request.getSession().getId());


        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            System.out.println("Updateservlet3");
            System.out.println(_token);
            System.out.println(request.getSession().getId());

            Task t = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));

            System.out.println("Updateservlet4");
            System.out.println(request.getSession().getAttribute("task_id"));
            System.out.println(t);

            //フォームの内容を更新
            String content = request.getParameter("content");
            t.setContent(content);
            System.out.println("title");
            System.out.println(content);

            System.out.println("Updateservlet5");

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            t.setUpdate_at(currentTime);

            System.out.println("Updateservlet5");


            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();

            System.out.println("Updateservlet6");

            request.getSession().removeAttribute("task_id");

            response.sendRedirect(request.getContextPath() + "/index");


        }
    }

}
