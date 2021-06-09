<%@ include file="../partials/navbar.jspf" %>
	<section class="container">
		<h1>Entrez une indisponibilité</h1>
		<form action="${pageContext.request.contextPath }/private/indisponibilite/add" method="post">
			<label for="idIndispo">Accronyme</label>
			<input type="text" name="idIndispo" placeholder="Entrez maximum 3 lettres" maxlength="3" class="form-control">
			<label for="libelle">Libelle</label>
			<input type="text" name="libelle" placeholder="Entrez le nom de l'indisponibilité" class="form-control">
			<button type="submit" class="btn btn-primary mt-3">Enregistrer</button>
		</form>
	</section>
<%@ include file="../partials/footer.jspf" %>