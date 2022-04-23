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
				<h3>${status}</h3>
				
				<c:forEach items="${patronagesBudgets[status]}" var="data">
					<h4><acme:message code="patron.dashboard.form.label.currency" />: ${data.key}</h4>
					<div class="row">
						<div class="col">
							<div class="card">
								<div class="card-body">
									<label>
										<acme:message code="patron.dashboard.form.label.maxPrice" />
										<input type="text" value="${data.value.Max}" readonly />
									</label>
								</div>
							</div>
						</div>
						
						<div class="col">
							<div class="card">
								<div class="card-body">
									<label>
										<acme:message code="patron.dashboard.form.label.minPrice"/>
										<input type="text" value="${data.value.Min}" readonly />
									</label>
								</div>
							</div>
						</div>
						
						<div class="col">
							<div class="card">
								<div class="card-body">					
									<label>
										<acme:message code="patron.dashboard.form.label.avgPrice"/>
										<input type="text" value="${data.value.Avg}" readonly />
									</label>
								</div>
							</div>
						</div>
						
						<div class="col">
							<div class="card">
								<div class="card-body">
									<label>
										<acme:message code="patron.dashboard.form.label.devPrice"/>
										<input type="text" value="${data.value.Dev}" readonly />
									</label>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:forEach>
	
</div>

