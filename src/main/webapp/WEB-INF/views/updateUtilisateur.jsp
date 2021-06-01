<%@ include file="partials/navbar.jspf" %>
	<section class="container">
		<h1>Modification du profil</h1>
		<form action="${pageContext.request.contextPath }/private/utilisateur/update?idUtilisateurUpdate=${utilUpdate.idUtilisateur}" method="post">
			<label for="email">Email</label>
			<input type="email" name="email" placeholder="Entrez votre email" value="${ utilUpdate.email }" class="form-control">
			<label for="password">Mot de passe</label>
			<input type="password" name="password" placeholder="Entrez votre mot de passe " class="form-control" required="required">
			<label for="admin">administrateur</label>
			<select name="administrateur">
				<option value="false">Utilisateur</option>
				<option value="true">Administrateur</option>
			</select>
			<button class="btn btn-primary" type="submit">Envoyer</button>
		</form>
	</section>
<%@ include file="partials/footer.jspf" %>