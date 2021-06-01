<%@ include file="partials/navbar.jspf" %>
<section class="container">
		<h1>ATTENTION vous allez effacer votre compte !</h1>


<h2>SUR D'EFFACER ?</h2>

	<h2>Etes vous sur de vouloir fermer ce compte ?</h2>

		<form action="${pageContext.request.contextPath }/private/utilisateur/delete" method="post">
		${utilisateur.idUtilisateur }
		
			<button class="btn btn-danger" type="submit">Supprimer le profil</button>
			
		</form>

	<a href="${pageContext.request.contextPath }/utilisateurs">
		<button class="btn btn-success">Retour au tableau</button>
	</a>
	</section>
<%@ include file="partials/footer.jspf" %>