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

import services.ManagerService;
import util.ParsedRequest;
import util.SessionUtil;

/**
 * Servlet Filter implementation class SecurityFilter
 */
public class CoordinatorFilter implements Filter {

	private static Logger logger = Logger.getLogger(CoordinatorFilter.class);

	/**
	 * Default constructor.
	 */
	public CoordinatorFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		HttpSession session = request.getSession(false);
		ParsedRequest r = SessionUtil.getParsedRequest(request);
		// TODO check that the user is in the right path

		logger.trace("coordinator filter");

		if (session == null || r.getUserId() == null) {
			logger.warn("attempting access with no user session");
			response.sendError(401);
		} else if (!r.getUserRole().equals("coordinator")) {
			logger.warn("attempting access to coordinator; " + r.getUserName() + " is not a coordinator");
			response.sendError(403);
		} else {
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
