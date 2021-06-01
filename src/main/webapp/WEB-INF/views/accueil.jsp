<%@ include file="layout/main.jspf" %>
	<%
String nom = null;
if (request.getAttribute("nom") != null) {
	nom = String.valueOf(request.getAttribute("nom"));
}
%>
<section class="container">
	<div class="row">
		<div class="col-md">
			<h1>Page de connexion</h1>
			<c:if test="${not empty erreur }">
				<p>${erreur }</p>
			</c:if>

			<form action="${pageContext.request.contextPath }/public/connection"
				method="post">
				<%-- ==============================  EMAIL  ================================================= --%>
				<label for="pseudo" class="form-label">Email</label> <input
					type="text" name="email" id="email"
					placeholder="Veuillez entrer votre adresse mail"
					class="form-control" value="<%=(nom != null) ? nom : ""%>">
				<%--==============================  PASSWORD  ================================================= --%>
				<label for="password" class="form-label">Mot de passe</label> <input
					type="password" name="password" id="password"
					placeholder="Veuillez entrer votre mot de passe"
					class="form-control"> <input type="checkbox" id="memoriser"
					name="memoriser"> <label for="memoriser">Se
					souvenir</label>
				<button class="btn btn-primary mt-2" type="submit">Connexion</button>
			</form>
		</div>
	</div>
</section>
<%@ include file="partials/footer.jspf"%>