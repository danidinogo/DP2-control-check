<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.user-account.list.label.title" path="title" width="20%"/>
	<acme:list-column code="administrator.user-account.list.label.descripcion" path="descripcion" width="20%"/>		
	<acme:list-column code="administrator.user-account.list.label.assemblyNotes" path="assemblyNotes" width="20%"/>		
			
</acme:list>
