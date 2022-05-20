<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.toolkit.form.label.code" path="code"/>
	<acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>
	<acme:input-textbox code="inventor.toolkit.form.label.descripcion" path="descripcion"/>
	<acme:input-textbox code="inventor.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
	<acme:input-url code="inventor.toolkit.form.label.link" path="link"/>
	<acme:input-url code="inventor.toolkit.form.label.status" path="link"/>


	<jstl:choose>
		<jstl:when test="${command == 'show' }">
			<acme:input-textbox code="inventor.toolkit.form.label.retailPrice" path="retailPrice" readonly="true"/>
			<acme:button code="inventor.toolkit.form.buttom.items" action="/inventor/item/list-by-toolkit?id=${id}"/>			
		</jstl:when>
		
		<jstl:when test="${command == 'create' }">
			<acme:input-select code="inventor.toolkit.form.label.qquantity" path="quantity">
				<jstl:forEach items="${items}" var="items">
					<acme:input-option code="inventor.toolkit.quantity" value="${items.getName()}"/>
				</jstl:forEach>
			</acme:input-select>
			<acme:button code="inventor.toolkit.form.buttom.create" action="/inventor/toolkit/create"/>
		</jstl:when>
		
	</jstl:choose>

</acme:form>