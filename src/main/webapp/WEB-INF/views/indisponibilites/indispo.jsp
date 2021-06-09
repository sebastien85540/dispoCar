<%@ include file="../partials/navbar.jspf" %>
	<section class="container">
		<h1>Les indisponibilités</h1>
		<a href="${pageContext.request.contextPath }/private/indisponibilite/add"><button class="btn btn-primary">Ajouter une indisponibilité</button></a>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id indispo</th>
					<th>Libelle</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="indispo" items="${indisponibilites }">
				<tr>
					<td>${indispo.idIndisponibilite }</td>
					<td>${indispo.libelleIndisponibilite }</td>
					<td>
						<a href="${pageContext.request.contextPath }/private/indisponibilite/delete?idIndispoDelete=${ indispo.idIndisponibilite }"><i class="fas fa-trash-alt"></i></a>
					 	<a href="${pageContext.request.contextPath }/private/indisponibilite/update?idIndispoUpdate=${ indispo.idIndisponibilite }"><i class="fas fa-pencil-alt"></i></a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
<%@ include file="../partials/footer.jspf" %>