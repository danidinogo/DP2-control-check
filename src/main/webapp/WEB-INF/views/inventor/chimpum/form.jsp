<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
<jstl:choose>
	<jstl:when test="${command == 'show'}">	

	<h2> <acme:message code="inventor.chimpum.list.label.item.details"/> </h2>
	
	<acme:input-textbox code="inventor.chimpum.form.label.code" path="code"/>
	<acme:input-moment code="inventor.chimpum.form.label.creation" path="creation"/>	
	<acme:input-textbox  code="inventor.chimpum.form.label.title" path="title"/>
	<acme:input-textbox  code="inventor.chimpum.form.label.desription" path="description"/>
	<acme:input-moment code="inventor.chimpum.form.label.startsAt" path="startsAt"/>
	<acme:input-moment code="inventor.chimpum.form.label.finishesAt" path="finishesAt"/>
	<acme:input-money code="inventor.chimpum.form.label.budget" path="budget"/>
	<acme:input-url code="inventor.chimpum.form.label.link" path="link"/>
	<br>
	<h2> <acme:message code="inventor.chimpum.list.label.item"/> </h2>
	<br>
	<acme:input-textbox code="inventor.item.list.label.item.identify.name" path="item.name"/>
	<acme:input-textbox code="inventor.item.list.label.item.identify.code" path="item.code"/>
	<acme:input-textbox code="inventor.item.list.label.item.identify.technology" path="item.technology"/>
	<acme:input-textbox code="inventor.item.list.label.item.identify.description" path="item.description"/>
	<acme:input-money code="inventor.item.list.label.item.identify.retailPrice" path="item.retailPrice"/>
	<acme:input-textbox code="inventor.item.list.label.item.identify.link" path="item.link"/>
	<acme:input-textbox code="inventor.item.list.label.item.identify.status" path="item.status"/>
	<acme:input-textbox code="inventor.item.list.label.item.identify.type" path="item.type"/>
	</jstl:when>
		<jstl:when test="${command == 'create'}">
	<acme:input-textbox code="inventor.chimpum.form.label.code" path="code"/>
	<acme:input-moment readonly="true" code="inventor.chimpum.form.label.creation" path="creation"/>	
	<acme:input-textarea code="inventor.chimpum.form.label.title" path="title"/>
	<acme:input-textarea code="inventor.chimpum.form.label.description" path="description"/>
	<acme:input-moment readonly="true" code="inventor.chimpum.form.label.startsAt" path="startsAt"/>
	<acme:input-moment readonly="true" code="inventor.chimpum.form.label.finishesAt" path="finishesAt"/>
	<acme:input-money code="inventor.chimpum.form.label.budget" path="budget"/>
	<acme:input-url code="inventor.chimpum.form.label.link" path="link"/>
	<acme:input-checkbox code="inventor.chimpum.form.label.confirm" path="confirm"/>
	<acme:submit code="inventor.chimpum.form.button.create" action="/inventor/chimpum/create?id=${id}"/>
	</jstl:when>	

</jstl:choose>	

</acme:form>