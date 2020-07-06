package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;

@WebFilter(filterName = "TeacherFilter", urlPatterns = "/teacher/*")
public class TeacherFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        if(!"teacher".equals(request.getSession().getAttribute("role"))){
            request.setAttribute("error","not authorized");
            request.getRequestDispatcher("/views/error.jsp").forward(request,(HttpServletResponse)resp);
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
