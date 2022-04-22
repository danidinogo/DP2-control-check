<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.toolkit.form.label.title" path="title" readonly="true"/>
	<acme:input-textbox code="any.toolkit.form.label.descripcion" path="descripcion" readonly="true"/>
	<acme:input-url code="any.toolkit.form.label.link" path="link"/>
	<acme:button code="any.toolkit.form.buttom.items" action="/inventor/item/list-by-toolkit?id=${id}"/>
</acme:form>