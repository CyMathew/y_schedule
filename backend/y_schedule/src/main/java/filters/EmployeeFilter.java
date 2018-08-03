package filters;

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

import org.apache.log4j.Logger;

import util.ParsedRequest;
import util.SessionUtil;

/**
 * Servlet Filter implementation class EmployeeFilter
 */
public class EmployeeFilter implements Filter {
	
	private static Logger logger = Logger.getLogger(EmployeeFilter.class);

    /**
     * Default constructor. 
     */
    public EmployeeFilter() {

    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		HttpSession session = request.getSession(false);
		ParsedRequest r = SessionUtil.getParsedRequest(request);
		//TODO check that the user is in the right path
		
		if(session == null || r.getUserId() == null) {
			logger.warn("attempting access with no user session");
			response.sendError(401);
		}else if(!r.getUserRole().equals("employee")){
			logger.warn("attempting access to employee; "  + r.getUserName() + " is not an employee");
			response.sendError(403);
		}else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
