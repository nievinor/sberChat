package sber;

import sber.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Chat extends HttpServlet {
    final List<Message> messages = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = getLoginFromCookie(req);

        if (login == null) {
            String path = "/authorization.html";
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
            requestDispatcher.forward(req, resp);
        } else {
            req.setAttribute("messages", messages);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

        }

    }

    private String getLoginFromCookie(HttpServletRequest req) {
        String login = null;

        Cookie[] cookies = req.getCookies();
        String cookieName = "login";
        Cookie cookie = null;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }

        if (cookie != null) login = cookie.getName();

        return login;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        String login = req.getParameter("login");
        String msg = req.getParameter("message");
        String exit = req.getParameter("exit");

        if (login != null) {
            resp.addCookie(new Cookie("login", login));
            req.setAttribute("messages", messages);
            resp.sendRedirect(req.getContextPath() + req.getServletPath());

        }

        if (msg != null) {
            Message message = new Message(getLoginFromCookie(req),msg,new Date());
            messages.add(message);

            req.setAttribute("messages", messages);
            resp.sendRedirect(req.getContextPath() + req.getServletPath());


        }

        if (exit != null) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    c.setMaxAge(0);
                    resp.addCookie(c);
                }
            }

            getServletContext().getRequestDispatcher("/authorization.html").forward(req, resp);
        }

    }
}
