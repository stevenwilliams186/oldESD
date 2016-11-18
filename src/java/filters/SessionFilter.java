/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import beans.DBBean;
import beans.LogonBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Steve
 */
public class SessionFilter implements Filter {

    private FilterConfig fc;
    private LogonBean logon;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logon = new LogonBean();
        fc = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (session == null && !httpRequest.getRequestURI().equals("/GPBusiness/pages/logon")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username == null || password == null) {
                httpResponse.sendRedirect("/GPBusiness/pages/logon");
                return;
            }

            logon.setUsername(username);
            logon.setPassword(password);
            logon.setDb((DBBean)request.getServletContext().getAttribute("dbConnection"));

            if (logon.getValid()) {
                httpRequest.getSession();
            } else {
                httpResponse.sendRedirect("/GPBusiness/pages/logon");
            }
        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
    }

}
