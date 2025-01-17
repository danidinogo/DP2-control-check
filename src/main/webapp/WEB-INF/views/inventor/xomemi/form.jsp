<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	
	<jstl:choose>
	<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish')}">
		<acme:input-textbox code="inventor.chimpum.form.label.code" path="code" readonly="true"/>
		<acme:input-moment code="inventor.chimpum.form.label.creationMoment" path="creationMoment" readonly="true"/>
		<acme:input-textbox code="inventor.chimpum.form.label.title" path="name"/>
		<acme:input-textbox code="inventor.chimpum.form.label.description" path="statement"/>
		<acme:input-moment code="inventor.chimpum.form.label.startDate" path="startDate"/>
		<acme:input-moment code="inventor.chimpum.form.label.finishDate" path="finishDate"/>
		<acme:input-money code="inventor.chimpum.form.label.budget" path="ration"/>
		<acme:input-url code="inventor.chimpum.form.label.link" path="furtherInfo"/>
		<acme:submit code="inventor.chimpum.form.label.delete" action="/inventor/xomemi/delete?id=${id}"/>
		<acme:submit code="inventor.chimpum.form.label.update" action="/inventor/xomemi/update?id=${id}"/>
			
	</jstl:when>
		
		
	<jstl:when test = "${command == 'create' }">
		<acme:input-textbox code="inventor.chimpum.form.label.code" path="code" readonly="true"/>
		<acme:input-textbox code="inventor.chimpum.form.label.title" path="name"/>
		<acme:input-textbox code="inventor.chimpum.form.label.description" path="statement"/>
		<acme:input-moment code="inventor.chimpum.form.label.startDate" path="startDate"/>
		<acme:input-moment code="inventor.chimpum.form.label.finishDate" path="finishDate"/>
		<acme:input-money code="inventor.chimpum.form.label.budget" path="ration"/>
		<acme:input-url code="inventor.chimpum.form.label.link" path="furtherInfo"/>
		<acme:submit code = "inventor.chimpum.form.button.create" action = "/inventor/xomemi/create?id=${id}"/>
	</jstl:when>
	
	</jstl:choose>
	
	
	
</acme:form>