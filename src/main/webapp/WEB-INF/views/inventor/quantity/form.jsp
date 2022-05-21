<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form> 
	
	
	<jstl:choose>
	
		<jstl:when test="${command == 'show'}">
			<acme:input-integer code="inventor.quantity.form.label.number" path="number"/>
			<acme:input-integer code="inventor.quantity.form.label.name" path="item.name"/>
			<acme:input-integer code="inventor.quantity.form.label.code" path="item.code"/>
			<acme:input-integer code="inventor.quantity.form.label.technology" path="item.technology"/>
			<acme:input-integer code="inventor.quantity.form.label.description" path="item.description"/>
			<acme:input-integer code="inventor.quantity.form.label.retailPrice" path="item.retailPrice"/>
			<acme:input-integer code="inventor.quantity.form.label.info" path="item.info"/>
			<acme:input-integer code="inventor.quantity.form.label.status" path="item.status"/>
			<acme:input-integer code="inventor.quantity.form.label.type" path="item.type"/>
		</jstl:when>	
		
		<jstl:when test="${command == 'create'}">
			<acme:input-integer code="inventor.quantity.form.label.number" path="number"/>
			<acme:input-select code="inventor.quantity.form.label.item" path="item">
				<jstl:forEach items="${items}" var = "i">
					<acme:input-option code="${i.getName()}" value="${i.getId()}" selected="${ i.getId() == item }"/>
				</jstl:forEach>
			</acme:input-select>
			<acme:submit code = "inventor.quantity.form.button.create" action = "/inventor/quantity/create?id=${param.id}"/>
			
		</jstl:when>
		
	</jstl:choose>
		
		
</acme:form>