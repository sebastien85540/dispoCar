<%@ include file="../partials/navbar.jspf" %>
	<section class="container">
		<h1>Liste des vehicules</h1>
		<a href="${pageContext.request.contextPath }/private/vehicule/add"><button class="btn btn-primary">Ajouter un vehicule</button></a>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Immatriculation</th>
					<th>Description</th>
					<th>places</th>
					<th>date achat</th>
					<th>type vehicule</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${vehicules }" var="vehi">
				<tr>
					<td>${vehi.immatriculation }</td>
					<td>${vehi.designationVehicule }</td>
					<td>${vehi.nbrePlaces }</td>
					<td>${vehi.dateAchat }</td>
					<td>${vehi.typeVehicule }</td>
					<td>
						<a href="${pageContext.request.contextPath }/private/vehicule/delete?immatriculation=${ vehi.immatriculation }"><i class="fas fa-trash-alt"></i></a>
					 	<a href="${pageContext.request.contextPath }/private/vehicule/update?immatriculation=${ vehi.immatriculation }"><i class="fas fa-pencil-alt"></i></a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</section>
<%@ include file="../partials/footer.jspf" %>