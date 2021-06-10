package yatospace.web.enviroment.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import yatospace.session.bean.BaseBean;
import yatospace.session.generator.SessionBeansGenerator;

/**
 * Балансирање апликације на пријављеност при корисничким захтјевима. 
 * @author MV
 * @version 1.0
 */
@WebFilter("/*")
public class SessionStateBalancer implements Filter {
    public SessionStateBalancer() {}
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) request;
			BaseBean baseBean = SessionBeansGenerator.baseBean(req.getSession()); 
			baseBean.getLoginBean().balanceLogged(); 
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
