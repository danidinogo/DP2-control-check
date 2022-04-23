<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.toolkit.form.label.code" path="code" readonly="true"/>
	<acme:input-textbox code="inventor.toolkit.form.label.title" path="title" readonly="true"/>
	<acme:input-textbox code="inventor.toolkit.form.label.descripcion" path="descripcion" readonly="true"/>
	<acme:input-textbox code="inventor.toolkit.form.label.assemblyNotes" path="assemblyNotes" readonly="true"/>
	<acme:input-textbox code="inventor.toolkit.form.label.retailPrice" path="retailPrice" readonly="true"/>
	<acme:input-url code="inventor.toolkit.form.label.link" path="link"/>
	<acme:button code="inventor.toolkit.form.buttom.items" action="/inventor/item/list-by-toolkit?id=${id}"/>
</acme:form>