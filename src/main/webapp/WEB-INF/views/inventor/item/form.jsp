<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.item.form.label.name" path="name" readonly="true"/>
	<acme:input-textbox code="inventor.item.form.label.code" path="code" readonly="true"/>
	<acme:input-textbox code="inventor.item.form.label.technology" path="technology" readonly="true"/>
	<acme:input-textbox code="inventor.item.form.label.description" path="description" readonly="true"/>
	<acme:input-textbox code="inventor.item.form.label.price" path="retailPrice" readonly="true"/>
	<acme:input-textbox code="inventor.item.form.label.info" path="info" readonly="true"/>
</acme:form>