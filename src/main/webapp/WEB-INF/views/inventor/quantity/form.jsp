<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form> 
	<acme:input-integer code="inventor.quantity.form.label.number" path="number"/>
	<acme:input-select code="inventor.quantity.form.label.item" path="item">
		<jstl:forEach items="${items}" var = "i">
			<acme:input-option code="${i.getName()}" value="${i.getId()}" selected="${ i.getId() == item }"/>
		</jstl:forEach>
	</acme:input-select>

	<acme:submit test="${command == 'create'}" code="inventor.quantity.form.button.create" action="/inventor/quantity/create?toolkitId=${toolkitId}"/>
		
</acme:form>