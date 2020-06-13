package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/admin/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        request.getSession().getAttribute("role");
        String ipAddress = request.getRemoteAddr();
        System.out.println(ipAddress+request.getSession().getAttribute("role"));
        if(!("admin").equals(request.getSession().getAttribute("role"))){
            request.setAttribute("error","not authorized");
            request.getRequestDispatcher("/views/error.jsp").forward(request,(HttpServletResponse)resp);
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
