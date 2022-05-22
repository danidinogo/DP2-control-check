<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.item.list.label.name" path="name" width="10%"/>
	<acme:list-column code="inventor.item.list.label.description" path="description"/>
	<acme:list-column code="inventor.item.list.label.price" path="retailPrice"/>
</acme:list>

<acme:button code="inventor.item.list.button.create" action="/inventor/item/create"/>
