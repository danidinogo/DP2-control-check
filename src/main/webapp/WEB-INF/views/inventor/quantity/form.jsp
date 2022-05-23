<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
		
	<jstl:choose>
	
		<jstl:when test="${acme:anyOf(command, 'show, update')}">
			<acme:input-integer code="inventor.quantity.form.label.number" path="number" readonly="${item.type == 'TOOL' || toolkit.status == 'PUBLISHED'}" />
			
			<acme:input-textbox code="inventor.quantity.form.label.name" path="item.name" readonly="${item.status == 'PUBLISHED' || toolkit.status == 'PUBLISHED'}"/>
			<acme:input-textbox code="inventor.quantity.form.label.code" path="item.code" readonly="${item.status == 'PUBLISHED' || toolkit.status == 'PUBLISHED'}"/>
			<acme:input-textbox code="inventor.quantity.form.label.technology" path="item.technology" readonly="${item.status == 'PUBLISHED' || toolkit.status == 'PUBLISHED'}"/>
			<acme:input-textarea code="inventor.quantity.form.label.description" path="item.description" readonly="${item.status == 'PUBLISHED' || toolkit.status == 'PUBLISHED'}"/>
			<acme:input-money code="inventor.quantity.form.label.retailPrice" path="item.retailPrice" readonly="${item.status == 'PUBLISHED' || toolkit.status == 'PUBLISHED'}"/>
			
			<jstl:if test="${command == 'show'}">
				<acme:input-money code="inventor.label.moneyExchange" path="moneyExchange" readonly="true"/>
			</jstl:if>
			


			<acme:input-url code="inventor.quantity.form.label.link" path="item.link" readonly="${item.status == 'PUBLISHED' || toolkit.status == 'PUBLISHED'}"/>

			
			<acme:input-textbox code="inventor.quantity.form.label.status" path="item.status" readonly="true" />
			
			<jstl:choose>
				<jstl:when test="${item.status == 'PUBLISHED' || toolkit.status == 'PUBLISHED'}">
					<acme:input-textbox code="inventor.quantity.form.label.type" path="item.type" readonly="true"/>
				</jstl:when>
				<jstl:when test="${item.status != 'PUBLISHED' && toolkit.status != 'PUBLISHED' }">
					<acme:input-select code="inventor.item.form.label.type" path="item.type">
						<acme:input-option code="inventor.item.form.label.component" value="COMPONENT" selected="${ item.type == 'COMPONENT' }"/>
						<acme:input-option code="inventor.item.form.label.tool" value="TOOL" selected="${ item.type == 'TOOL' }"/>
					</acme:input-select>
				</jstl:when>
			</jstl:choose>
			
			<jstl:if test="${toolkit.status == 'NON_PUBLISHED'}" >
				<acme:submit code="inventor.quantity.form.button.update" action="/inventor/quantity/update"/>
			</jstl:if>
			
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