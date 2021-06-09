<%@ include file="../partials/navbar.jspf" %>
	<section class="container">
		<h1 class="mt-3 mb-3">Les Adresses</h1>
			<a href="${pageContext.request.contextPath }/private/destination/add"><button class="btn btn-primary mt-3 mb-3">Ajouter une destination</button></a>
		<table class="table table-striped mt-3">
			<thead>
				<tr>
					<th>Id</th>
					<th>Libelle</th>
					<th>Numero</th>
					<th>Rue</th>
					<th>Complement</th>
					<th>Code postal</th>
					<th>Ville</th>
					<th>Agence</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="desti" items="${destinations }">
				<tr>
					<td>${desti.idDestination }</td>
					<td>${desti.libelleDestination }</td>
					<td>${desti.numeroDestination }</td>
					<td>${desti.rueDestination }</td>
					<td>${desti.complementDestination }</td>
					<td>${desti.codePostal }</td>
					<td>${desti.ville }</td>
					<c:choose>
						<c:when test="${desti.agence == true}">
							<td><i class="fas fa-check"></i></td>
						</c:when>
						<c:when test="${desti.agence == false}">
							<td><i class="fas fa-times"></i></td>
						</c:when>
					</c:choose>
					<td>
						<a href="${pageContext.request.contextPath }/private/destination/delete?idDestination=${ desti.idDestination }"><i class="fas fa-trash-alt"></i></a>
					 	<a href="${pageContext.request.contextPath }/private/destination/update?idDestination=${desti.idDestination }"><i class="fas fa-pencil-alt"></i></a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
<%@ include file="../partials/footer.jspf" %>