<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page import="acme.enums.Status" %>
<style>
	.totals input {
		display: block;
		margin: auto;
	}
	.totals {
		text-align: center;
	}
	.totals > .row {
		margin-bottom: 25px;
	}
</style>

<div class="totals">
	<div class="row">
<c:forEach items="${Status.values()}" var="status">
		
			<div class="col">
				<div class="card">
					<div class="card-body">
						<label>
							<acme:message code="patron.dashboard.form.label.totalpatronage${status}" />
							<input type="text" value="${totalNumberPatronage[status]}" readonly />
						</label>
					</div>
				</div>
			</div>
			
			
			
			
			
			
	</c:forEach>
	</div>
	
	<c:forEach items="${Status.values()}" var="status">
		<div class="row">
					<div class="col">
						<div class="card">
							<div class="card-body">
								<label>
									<acme:message code="administrator.dashboard.form.label.patronagesBudgetsMax${status}" />
									<input type="text" value="${patronagesBudgets[status].Max}" readonly />
								</label>
							</div>
						</div>
					</div>
					
					<div class="col">
						<div class="card">
							<div class="card-body">
								<label>
									<acme:message code="administrator.dashboard.form.label.patronagesBudgetsMin${status}"/>
									<input type="text" value="${patronagesBudgets[status].Min}" readonly />
								</label>
							</div>
						</div>
					</div>
					
					<div class="col">
						<div class="card">
							<div class="card-body">					
								<label>
									<acme:message code="administrator.dashboard.form.label.patronagesBudgetsAvg${status}"/>
									<input type="text" value="${patronagesBudgets[status].Avg}" readonly />
								</label>
							</div>
						</div>
					</div>
					
					<div class="col">
						<div class="card">
							<div class="card-body">
								<label>
									<acme:message code="administrator.dashboard.form.label.patronagesBudgetsDev${status}"/>
									<input type="text" value="${patronagesBudgets[status].Dev}" readonly />
								</label>
							</div>
						</div>
					</div>
				</div>
	</c:forEach>
	
</div>

