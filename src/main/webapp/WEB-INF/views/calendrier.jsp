<script type="text/javascript">
document.addEventListener('DOMContentLoaded', function() {
	  var initialLocaleCode = 'fr';
var calendarEl = document.getElementById('calendar');

var calendar = new FullCalendar.Calendar(calendarEl, {
  plugins: [ 'interaction', 'dayGrid', 'timeGrid','list' ],
  header: {
    left: 'prev,next today',
    center: 'title',
    right: 'dayGridMonth,timeGridWeek,timeGridDay'
  },
  defaultDate: new Date(),
  locale: initialLocaleCode,
  navLinks: true, // can click day/week names to navigate views
  selectable: true,
  selectMirror: true,
  select: function(arg) {
    var title = prompt('Event Title:');
    if (title) {
      calendar.addEvent({
        title: title,
        start: arg.start,
        end: arg.end,
        allDay: arg.allDay
      })
    }
    calendar.unselect()
  },
  editable: false,
  eventLimit: true,
  //Création du tableau javascript à partir de l'attribut de request "resa"
  //${resa} est une instruction EL traduite côté serveur
  events: ${reservationsJSON}
});

calendar.render();
});


</script>
<%@ include file="partials/navbar.jspf"%>
<section class="container">
	<h1>Calendrier</h1>
	<div></div>
	<div id='calendar'></div>
</section>
<%@ include file="partials/footer.jspf"%>