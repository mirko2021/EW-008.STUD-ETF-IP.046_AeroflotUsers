package yatospace.web.enviroment.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * UTF-8 постављање за подразумјевано кодовање при 
 * захтијевима и одговорима за веб ресурсе. 
 * @author MV
 * @version 1.0
 */
@WebFilter("/*")
public class CommunicationEncodingSetter implements Filter {

    public CommunicationEncodingSetter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {}

}
