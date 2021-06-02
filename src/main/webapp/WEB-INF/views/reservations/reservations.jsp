<%@ include file="../partials/navbar.jspf" %>

	<section class="container">
		<h1>Les réservations</h1>
		
			<%--                  TABLEAU D'UTILISATEURS             --%>
	<a href="${pageContext.request.contextPath }/private/reservation/add"><button class="btn btn-primary">Ajouter une reservation</button></a>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>Motif</th>
				<th>Debut</th>
				<th>Fin</th>
				<th>Description</th>
				<th>Depart</th>
				<th>Arrivée</th>
				<th>Reservataires</th>
				<th>Vehicule</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="reservation" items="${reservations }">
				<tr>
					<td>${reservation.idReservation }</td>
					<td>${reservation.motifReservation }</td>
					<td>${reservation.dateDebutReservation }</td>
					<td>${reservation.dateFinReservation }</td>
					<td>${reservation.descriptionReservation }</td>
					<td>${reservation.departReservation }</td>
					<td>${reservation.arriveeDestination }</td>
					<td>${reservation.reservataires }</td>
					<td>${reservation.vehiculeReservation }</td>
					<td>
						<a href="${pageContext.request.contextPath }/private/utilisateur/delete?idUtilisateur=${ reservation.idReservation }"><i class="fas fa-trash-alt"></i></a>
					 	<a href="${pageContext.request.contextPath }/private/utilisateur/update?idUtilisateurUpdate=${reservation.idReservation }"><i class="fas fa-pencil-alt"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	</section>

<%@ include file="../partials/footer.jspf" %>