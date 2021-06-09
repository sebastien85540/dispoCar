<%@ include file="../partials/navbar.jspf" %>
	<section class="container">
		<h1>Liste des salariés</h1>
		<a href="${pageContext.request.contextPath }/private/salarie/add"><button class="btn btn-primary">Ajouter un salarié</button></a>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nom</th>
					<th>Prenom</th>
					<th>Email</th>
					<th>Telephone</th>
					<th>permis</th>
					<th>agence</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="salarie" items="${salaries }">
					<tr>
						<td>${salarie.idSalarie }</td>
						<td>${salarie.nom }</td>
						<td>${salarie.prenom }</td>
						<td>${salarie.email }</td>
						<td>${salarie.telephone }</td>
						<td>${salarie.permisName }</td>
						<td>${salarie.agenceSalarie.libelleDestination }</td>
						<td>
							<a href="${pageContext.request.contextPath }/private/salarie/delete?idSalarie=${salarie.idSalarie }"><i class="fas fa-trash-alt"></i></a>
						 	<a href="${pageContext.request.contextPath }/private/salarie/update?idSalarie=${salarie.idSalarie }"><i class="fas fa-pencil-alt"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
<%@ include file="../partials/footer.jspf" %>