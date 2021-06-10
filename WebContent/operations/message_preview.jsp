<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import='java.lang.Exception' %>
<%@ page import='yatospace.web.gui.bean.GeneralBean' %>
<%@ page import='yatospace.web.gui.bean.MessageBean' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:useBean id='designGeneralBean' class='yatospace.web.gui.bean.GeneralBean' scope='session'></jsp:useBean>
<c:out value='${designGeneralBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<%
	 GeneralBean bean = (GeneralBean) request.getSession().getAttribute("designGeneralBean"); 
	 if(bean!=null){
		 try{
			 Exception ex = bean.getMessageBean().getException(); 
			 if(ex==null) throw new NullPointerException("Exception not found.");
			 throw ex; 
		 }catch(RuntimeException ex){
			 throw ex; 
		 }
		 catch(Exception ex){
			 throw new RuntimeException(ex);
		 }
	 }else{
		 throw new RuntimeException("PREVIEW ERROR NOT SUCCESS");
	 }
%>