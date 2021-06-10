<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="locationTrafficRelations"  class="studiranje.ip.admin.jsp.bean.LocationBean" scope='session'></jsp:useBean>
<jsp:useBean id="trafficRelationsBean"  class="yatospace.traffic.relation.bean.TrafficRelationsBean" scope='session'></jsp:useBean>

<c:out value='${trafficRelationsBean.insert(pageContext.request)}'></c:out>
<c:redirect url='${pageConetxt.request.contextPath}/faces/home-admin-rt.jsp#rtt'></c:redirect>