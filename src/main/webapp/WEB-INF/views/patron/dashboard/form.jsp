<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

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
	
	<h2>Patronages totales por Status</h2>
	<div class="row">
		<div class="col">
			<div class="card">
				<div class="card-body">
					<label><acme:message code="patron.dashboard.form.label.totalNumberPatronagesProposed"/>
						<input type="text" value="${totalNumberPatronage.proposed}" readonly />
					</label>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="card">
				<div class="card-body">
					<label>
						<acme:message code="patron.dashboard.form.label.totalNumberPatronagesAccepted"/>
						<input type="text" value="${totalNumberPatronage.accepted}" readonly />
					</label>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="card">
				<div class="card-body">
					<label>
						<acme:message code="patron.dashboard.form.label.totalNumberPatronagesDenied"/>
						<input type="text" value="${totalNumberPatronage.denied}" readonly />
					</label>
				</div>
			</div>
		</div>
	</div>
</div>

