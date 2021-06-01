<%@ include file="partials/navbar.jspf"%>
<section class="container">
	<h1>Les utilisateurs</h1>

	<%--                  TABLEAU D'UTILISATEURS             --%>
	<a href="${pageContext.request.contextPath }/private/utilisateur/add"><button class="btn btn-primary">Ajouter un utilisateur</button></a>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>Email</th>
				<th>Administrateur</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="utilisateur" items="${utilisateurs }">
				<tr>
					<td>${utilisateur.idUtilisateur }</td>
					<td>${utilisateur.email }</td>
					<td>${utilisateur.administrateur }</td>
					<td>
						<a href="${pageContext.request.contextPath }/private/utilisateur/delete?idUtilisateur=${ utilisateur.idUtilisateur }"><i class="fas fa-trash-alt"></i></a>
					 	<a href="${pageContext.request.contextPath }/private/utilisateur/update?idUtilisateurUpdate=${utilisateur.idUtilisateur }"><i class="fas fa-pencil-alt"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</section>
<%@ include file="partials/footer.jspf"%>