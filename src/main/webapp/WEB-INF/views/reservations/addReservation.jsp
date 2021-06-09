<%@ include file="../partials/navbar.jspf" %>
	<section class="container">
		<h1>Inserez une réservation</h1>
		<form action="${pageContext.request.contextPath }/private/reservation/add" method="post">
			<div>
				<label for="debut">Debut</label>
				<input type="datetime-local" name="debut" placeholder="Veuillez entrer l'heure de debut" class="form-control">
			</div>		
			<div>
				<label for="fin">Fin</label>
				<input type="datetime-local" name="fin" placeholder="Veuillez entrer l'heure de fin" class="form-control">
			</div>		
			<div>
				<label for="motif">Motif</label>
				<input type="text" name="motif" placeholder="Veuillez entrer le motif " class="form-control">
			</div>		
			<div>
			<div>
				<label for="description">Description</label>
				<input type="text" name="description" placeholder="Veuillez entrer la description de la réservation" class="form-control">
			</div>		
				<label for="depart">Lieu de depart</label>
				<select name="depart" class="form-control">
					<c:forEach var="agence" items="${agences }">
						<option value="${agence.idDestination }">${agence.libelleDestination }</option>
					</c:forEach>
				</select>	
			</div>		
			<div>
				<label for="destination">Destination</label>		
				<select name="destination" class="form-control">
					<c:forEach var="destination" items="${destinations }">
						<option value="${destination.idDestination }">${destination.libelleDestination }</option>
					</c:forEach>
				</select>			
			</div>		
			<div>
				<label for="salarie">Reservataires</label>		
				<select name="salarie" class="form-control" id="reservataire">
					<c:forEach var="salarie" items="${salaries }">
						<option value="${ salarie.idSalarie}">${salarie.prenom }</option>
					</c:forEach>
				</select>	
				<i class="fas fa-plus-circle fa-3x" onclick="oncickEvent()"></i>
				<div id="reservataires">
					<input type="text" value="${salarie.prenom }">
					<input type="hidden" name="reservataire1" value="${salarie.idSalarie }">
				</div>
			</div>		
			<div>
				<label for="vehicule">Vehicule</label>
				<select name="vehicule" class="form-control">
					<c:forEach var="vehicule" items="${vehicules }">
						<option value="${vehicule.immatriculation }">${vehicule.designationVehicule }</option>
					</c:forEach>
				</select>
			</div>		
		</form>
	</section>
<%@ include file="../partials/footer.jspf" %>