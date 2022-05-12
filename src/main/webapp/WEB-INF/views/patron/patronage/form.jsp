
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
 

<acme:form readonly="true">
    <acme:input-textbox code="authenticated.patron.patronage.form.label.status" path="status"/>	
	<acme:input-textbox code="authenticated.patron.patronage.form.label.code" path="code"/>	
	<acme:input-textbox code="authenticated.patron.patronage.form.label.legalStuff" path="legalStuff"/>	
	<acme:input-money code="authenticated.patron.patronage.form.label.budget" path="budget"/>	
	<acme:input-textbox code="authenticated.patron.patronage.form.label.startsAt" path="startsAt"/>
	<acme:input-textarea code="authenticated.patron.patronage.form.label.finishesAt" path="finishesAt"/>
	<acme:input-url code="authenticated.patron.patronage.form.label.link" path="link"/>
	<acme:input-textarea code="authenticated.patron.patronage.form.label.inventor.company" path="inventor.company"/>
	<acme:input-textarea code="authenticated.patron.patronage.form.label.inventor.statement" path="inventor.statement"/>
	<acme:input-url code="authenticated.patron.patronage.form.label.inventor.link" path="inventor.link"/>
	<acme:button code="patron.patronage.form.buttom.patronage-reports" action="/patron/patronage-report/list-by-patronage?id=${id}"/>
	
<jstl:choose>

		<jstl:when test="${acme:anyOf(command, 'show, update, delete') && draftMode == true}">
			<acme:submit code="employer.duty.form.button.update" action="/patron/patronge/update"/>
			<acme:submit code="employer.duty.form.button.delete" action="/patron/patronge/delete"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="employer.duty.form.button.create" action="/patron/patronge/create?masterId=${masterId}"/>
		</jstl:when>		
	</jstl:choose>	
</acme:form>