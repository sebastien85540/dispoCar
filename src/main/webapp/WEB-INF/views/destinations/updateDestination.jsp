<%@ include file="../partials/navbar.jspf" %>
	<section class="container">
		<h1>Modification d'une destination</h1>
				<form action="${pageContext.request.contextPath }/private/destination/update?idDestination=${desti.idDestination}" method="post">
			<label for="libelle">Nom de la destination</label>
			<input type="text" name="libelle" placeholder="Entrez le nom de l'adresse" class="form-control" value="${desti.libelleDestination }">
			<label for="numero">Numero</label>
			<input type="number" name="numero" placeholder="Entrez le numero de l'adresse" maxlength="7" class="form-control" value="${desti.numeroDestination }">
			<label for="rue">Adresse</label>
			<input type="text" name="rue" placeholder="Entrez le nom de la rue/impasse/boulevard ..." class="form-control" value="${desti.rueDestination }">
			<label for="complement">Complement d'adresse</label>
			<input type="text" name="complement" placeholder="Entrez le complement d'adresse ZAC/ZA ..." class="form-control" value="${desti.complementDestination }">
			<label for="codePostal">Code postal</label>
			<input type="number" name="codePostal" placeholder="Entrez le code postal a 5 chiffres" maxlength="5" class="form-control" value="${desti.codePostal }">
			<label for="ville">Ville</label>
			<input type="text" name="ville" placeholder="Entrez le nom de la ville" class="form-control" value="${desti.ville }">
			<label for="agence">Agence</label>
			<select name="agence" class="form-control">
				<option value="false">non</option>
				<option value="true">oui</option>
			</select>
			<button class="btn btn-primary mt-3" type="submit">Envoyer</button>
		</form>
	</section>
<%@ include file="../partials/footer.jspf" %>