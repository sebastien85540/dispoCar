<%@ include file="partials/navbar.jspf" %>
	<section class="container">
		<h1>Creation de compte utilisateur</h1>
		<form action="${ pageContext.request.contextPath}/private/utilisateur/add" method="post">
			<label for="email">Email</label>
			<input type="email" name="email" placeholder="Entrez l'email" class="form-control">
			<label for="password">Mot de passe</label>
			<input type="password" name="password" placeholder="Entrez un mot de passe" class="form-control">
			<label for="administrateur">Administrateur</label>
			<select name="administrateur">
				<option value="false">Utilisateur</option>
				<option value="true">Administrateur</option>
			</select>
			<button class="btn btn-primary" type="submit">Envoyer</button>
			
		</form>
	</section>
<%@ include file="partials/footer.jspf" %>