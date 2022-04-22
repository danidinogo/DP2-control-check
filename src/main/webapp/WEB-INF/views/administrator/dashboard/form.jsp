<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page import="acme.enums.Status" %>
<%@ page import="acme.entities.item.ItemType" %>

<style>
	.budgets input, .totals input, .items input {
		display: block;
		margin: auto;
	}
	.totals, .budgets, .items {
		text-align: center;
	}
	.budgets > .row, .totals > .row, .items > .row {
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

<div class="items">
	<h2>Mínimo, máximo, media y desviación de precio de Componentes y Herramientas</h2>
	
	<c:forEach items="${ItemType.values()}" var="type">
		<h3>${type}</h3>
		<div class="row">
			<div class="col">
				<div class="card">
					<div class="card-body">
						<label>
							<acme:message code="administrator.dashboard.form.label.max${type}" />
							<input type="text" value="${componentsData[type].Max}" readonly />
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