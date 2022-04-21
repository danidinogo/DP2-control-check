<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<div>
	<label><acme:message code="administrator.dashboard.form.label.totalComponents"/>
	<input type="text" value="${totalsData.Component}" readonly />
	</label>
</div>

<div>
	<label><acme:message code="administrator.dashboard.form.label.totalTools"/>
	<input type="text" value="${totalsData.Tool}" readonly />
	</label>
</div>

<div>
	<label><acme:message code="administrator.dashboard.form.label.totalNumberPatronagesProposed"/>
	<input type="text" value="${totalsData.Proposed}" readonly />
	</label>
</div>

<div>
	<label><acme:message code="administrator.dashboard.form.label.totalNumberPatronagesAccepted"/>
	<input type="text" value="${totalsData.Accepted}" readonly />
	</label>
</div>

<div>
	<label><acme:message code="administrator.dashboard.form.label.totalNumberPatronagesDenied"/>
	<input type="text" value="${totalsData.Denied}" readonly />
	</label>
</div>