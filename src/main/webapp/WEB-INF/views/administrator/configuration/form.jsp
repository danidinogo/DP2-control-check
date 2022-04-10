<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="administrator.configuration.form.label.weakSpamWords" path="weakSpamWords" readonly="true"/>
	<acme:input-textbox code="administrator.configuration.form.label.weakSpamThreshold" path="weakSpamThreshold" readonly="true"/>
	<acme:input-textbox code="administrator.configuration.form.label.strongSpamWords" path="strongSpamWords" readonly="true"/>
	<acme:input-email code="administrator.configuration.form.label.strongSpamThreshold" path="strongSpamThreshold" readonly="true"/>
	<acme:input-textbox code="administrator.configuration.form.label.acceptedCurr" path="acceptedCurr" readonly="true"/>
	<acme:input-textbox code="administrator.configuration.form.label.defaultCurr" path="defaultCurr" readonly="true"/>
	
	<jstl:if test="${canUpdate}">
		<acme:submit code="administrator.configuration.form.button.update" action="/administrator/configuration/update"/>
	</jstl:if>
</acme:form>
