<%@ include file="../partials/navbar.jspf"%>
<section class="container">
	<h1 class="mb-3">${requestScope.title }</h1>
	<div class="row mt-5">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<p>Voici les différentes informations de la réservation</p>
			<p>depart le : ${resaCalendar.start }</p>
			<p>retour le : ${resaCalendar.end }
			<p>Ce sera avec le vehicule ainsi que le personnel associé</p>
			<p>${resaCalendar.title }
		</div>
		<div class="col-md-4"></div>
	</div>
</section>
<%@ include file="../partials/footer.jspf"%>