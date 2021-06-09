<%@ include file="../partials/navbar.jspf"%>
	<section class="container">
		<h1>Les vehicules indisponibles</h1>
		<a href="${pageContext.request.contextPath }/private/vehicule/indisponibilite/add"><button class="btn btn-primary">Ajouter un salarié</button></a>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Date debut</th>
					<th>Date fin</th>
					<th>Vehicule</th>
					<th>Motif</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="indispo" items="${indispos }">
					<tr>
						<td>${indispo.idIndispoVehicule }</td>
						<td>${indispo.dateDebutIndispo }</td>
						<td>${indispo.dateFinIndispo }</td>
						<td>${indispo.vehiculeIndispo.immatriculation }</td>
						<td>${indispo.raisonIndispo.libelleIndisponibilite }</td>
						<td>
							<a href="${pageContext.request.contextPath }/private/vehicule/indisponibilite/delete?idIndispoVehicule=${indispo.idIndispoVehicule }"><i class="fas fa-trash-alt"></i></a>
						 	<a href="${pageContext.request.contextPath }/private/vehicule/indisponibilite/update?idSalarie=${indispo.idIndispoVehicule }"><i class="fas fa-pencil-alt"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
<%@ include file="../partials/footer.jspf"%>