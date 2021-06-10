<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="locationTrafficRelations"  class="studiranje.ip.admin.jsp.bean.LocationBean" scope='session'></jsp:useBean>
<jsp:useBean id="trafficRelationsBean"  class="yatospace.traffic.relation.bean.TrafficRelationsBean" scope='session'></jsp:useBean>


<h2 style='font-family:Tahoma, Geneva, sans-serif;'>Извод из реда вожње - шифарник релација - управљање</h2>

<form method="post" id='rtt' action='${pageContext.request.contextPath}/WEB-OPS/relations_listing_m.jsp' accept-charset="UTF-8">
	<input type="hidden" name="submitted" value="true" />
	<label><input type="hidden" name="category" value="idstanica"/></label>
	
	<label>			
		Мјесто : 
		<select name="location_for_trafic_rule">
		  <option value="0"></option>
		  <c:set var='counter' value='1'></c:set>
		  <c:forEach var='location' items='${locationTrafficRelations.list(pageContext.session)}'>
			  <c:if test="${locationTrafficRelations.getLocationValue(pageContext.session) eq location}">
			  	<option value="${counter}" selected><c:out value='${location}'></c:out></option>
			  </c:if>
			  <c:if test="${locationTrafficRelations.getLocationValue(pageContext.session) ne location}">
			  	<option value="${counter}"><c:out value='${location}'></c:out></option>
			  </c:if>
		  	  <c:set var='counter' value='${counter+1}'></c:set>
		  </c:forEach>
		</select>
	</label>
	<input type="submit" value="Претрага"/>
	<br><br>
	<table class='rt'>
		<tbody>
			<tr> 
				<th class='rt'>МЈЕСТО</th> 
				<th class='rt'>ШИФРА</th>
				<th class='rt'>БРОЈ</th> 
				<th class='rt'>ВРСТА</th> 
				<th class='rt'>ПОДВРСТА</th>
				<th class='rt'>РЕЛАЦИЈА</th> 
				<th class='rt'>ДОЛАЗАК</th> 
				<th class='rt'>ПОЛАЗАК</th>
				<th class='rt'>СМИЈЕР</th>
				<th class='rt'>ДЕСТИНАЦИЈА</th>
			</tr>
			<tr style="text-align:center; font-family:Tahoma, Geneva, sans-serif; font-size:14px">
			 	<td contenteditable="true" id='rt_place_ce'>*</td>
				<td contenteditable="true" id='rt_word_ce'>*</td>
				<td contenteditable="true" id='rt_number_ce'>*</td>
				<td contenteditable="true" id='rt_type_ce'>*</td>
				<td contenteditable="true" id='rt_subtype_ce'>*</td>
				<td contenteditable="true" id='rt_relation_ce'>*</td>
				<td contenteditable="true" id='rt_outcome_ce'>*</td>
				<td contenteditable="true" id='rt_income_ce'>*</td>
				<td contenteditable="true" id='rt_direction_ce'>*</td>
				<td contenteditable="true" id='rt_destination_ce'>*</td>
			</tr>
			<tr> 
				<td contenteditable="true" colspan='10'  style='white-space: pre-wrap; font-family:Courier New; border-bottom: 1px dotted black; border-top: 1px solid black' id='rt_notes_ce'>*</td>
			</tr>
			<tr align='right'>
				<td colspan='10' style='border-bottom: 2px solid black'>
					<input type='submit' id='add' value='Додавање' onclick='transport_data_neo()' formaction='${pageContext.request.contextPath}/WEB-OPS/relations_insert.jsp'/>
				</td>
			</tr>
			<c:forEach var='trafficRelation' items='${trafficRelationsBean.list(pageContext.session)}'>
				<tr style="text-align:center; font-family:Tahoma, Geneva, sans-serif; font-size:14px">
					<td contenteditable="true" id='rt_place_ce_${trafficRelation.urlRtWord()}'><c:out value='${trafficRelation.place}'></c:out></td>
					<td contenteditable="true" id='rt_word_ce_${trafficRelation.urlRtWord()}'><c:out value='${trafficRelation.rtWord}'></c:out></td>
					<td contenteditable="true" id='rt_number_ce_${trafficRelation.urlRtWord()}'><c:out value='${trafficRelation.rtNumber}'></c:out></td>
					<td contenteditable="true" id='rt_type_ce_${trafficRelation.urlRtWord()}'><c:out value='${trafficRelation.rtType}'></c:out></td>
					<td contenteditable="true" id='rt_subtype_ce_${trafficRelation.urlRtWord()}'><c:out value='${trafficRelation.rtSubType}'></c:out></td>
					<td contenteditable="true" id='rt_relation_ce_${trafficRelation.urlRtWord()}'><c:out value='${trafficRelation.relation}'></c:out></td>
					<td contenteditable="true" id='rt_outcome_ce_${trafficRelation.urlRtWord()}'><c:out value='${trafficRelation.outcome}'></c:out></td>
					<td contenteditable="true" id='rt_income_ce_${trafficRelation.urlRtWord()}'><c:out value='${trafficRelation.income}'></c:out></td>
					<td contenteditable="true" id='rt_direction_ce_${trafficRelation.urlRtWord()}'><c:out value='${trafficRelation.direction}'></c:out></td>
					<td contenteditable="true" id='rt_destination_ce_${trafficRelation.urlRtWord()}'><c:out value='${trafficRelation.destination}'></c:out></td>
				</tr>
				<tr> 
					<td contenteditable="true" colspan='10'  style='white-space: pre-wrap; font-family:Courier New; border-bottom: 1px dotted black; border-top: 1px solid black' id='rt_notes_ce_${trafficRelation.urlRtWord()}'><c:out value='${trafficRelation.rtNotes}'></c:out></td>
				</tr>
				<tr style='display: none'>
					<td style='display: none' id='rt_word_ce_old_${trafficRelation.urlRtWord()}'><c:out value='${trafficRelation.rtWord}'></c:out></td>
				</tr>
				<tr align='right'>
					<td colspan='10' style='border-bottom: 2px solid black'>
						<input type='submit' id='update_${trafficRelation.urlRtWord()}' value='Потврда' onclick='transport_data_old("${trafficRelation.urlRtWord()}")' formaction='${pageContext.request.contextPath}/WEB-OPS/relations_update.jsp'/>
						<input type='submit' id='delete_${trafficRelation.urlRtWord()}' value='брисање' onclick='transport_data_old("${trafficRelation.urlRtWord()}")' formaction='${pageContext.request.contextPath}/WEB-OPS/relations_erase.jsp'/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input type='hidden' id='rt_word_fm_old'    name='rt_word_fm_old'   value=''/>
	<input type='hidden' id='rt_place_fm'       name='rt_place_fm'       value=''/>
	<input type='hidden' id='rt_word_fm'        name='rt_word_fm'        value=''/>
	<input type='hidden' id='rt_number_fm'      name='rt_number_fm'      value=''/>
	<input type='hidden' id='rt_type_fm'        name='rt_type_fm'        value=''/>
	<input type='hidden' id='rt_subtype_fm'     name='rt_subtype_fm'     value=''/>
	<input type='hidden' id='rt_relation_fm'    name='rt_relation_fm'    value=''/>
	<input type='hidden' id='rt_outcome_fm'     name='rt_outcome_fm'     value=''/>
	<input type='hidden' id='rt_income_fm'      name='rt_income_fm'      value=''/>
	<input type='hidden' id='rt_direction_fm'   name='rt_direction_fm'   value=''/>
	<input type='hidden' id='rt_destination_fm' name='rt_destination_fm' value=''/>
	<input type='hidden' id='rt_notes_fm'       name='rt_notes_fm'       value=''/>
</form>
<br>
<div>Обајвештење: <c:out value='${trafficRelationsBean.message}'></c:out></div>
<c:out value='${trafficRelationsBean.resetMessage()}'></c:out><br>