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
	<h2>Componentes y herramientas totales</h2>
	<div class="row">
		<div class="col">
			<div class="card">
				<div class="card-body">
					<label><acme:message code="administrator.dashboard.form.label.totalComponents"/>
					<input type="text" value="${totalsData.Component}" readonly />
					</label>
				</div>
			</div>
		</div>
		
		<div class="col">
			<div class="card">
				<div class="card-body">
					<label><acme:message code="administrator.dashboard.form.label.totalTools"/>
					<input type="text" value="${totalsData.Tool}" readonly />
				</label>
				</div>
			</div>
		</div>
	</div>
	
	<h2>Patronages totales por Status</h2>
	<div class="row">
		<div class="col">
			<div class="card">
				<div class="card-body">
					<label><acme:message code="administrator.dashboard.form.label.totalNumberPatronagesProposed"/>
						<input type="text" value="${totalsData.Proposed}" readonly />
					</label>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="card">
				<div class="card-body">
					<label>
						<acme:message code="administrator.dashboard.form.label.totalNumberPatronagesAccepted"/>
						<input type="text" value="${totalsData.Accepted}" readonly />
					</label>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="card">
				<div class="card-body">
					<label>
						<acme:message code="administrator.dashboard.form.label.totalNumberPatronagesDenied"/>
						<input type="text" value="${totalsData.Denied}" readonly />
					</label>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="budgets">
	<h2>Mínimo, máximo, media y desviación de presupuestos de patronages</h2>
	<div class="row">
		<div class="col">
			<div class="card">
				<div class="card-body">
					
				</div>
			</div>
		</div>
	</div>
</div>