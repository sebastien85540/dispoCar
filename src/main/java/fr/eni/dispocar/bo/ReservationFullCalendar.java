/**
 * 
 */
package fr.eni.dispocar.bo;

/**
 * @author sebastien
 *
 */
public class ReservationFullCalendar {
	
	// attributs
	private String start;
	private String end;
	private String title;
	private String url;
	
	// getters et setters
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	// constructeurs
	
	public ReservationFullCalendar(String start, String end, String title, String url) {
		super();
		setStart(start);
		setEnd(end);
		setTitle(title);
		setUrl(url);
	}
	public ReservationFullCalendar() {
	}
	
	// methode to string
	
	@Override
	public String toString() {
		return "ReservationFullCalendar [start=" + start + ", end=" + end + ", title=" + title + ", url=" + url + "]";
	}

}
