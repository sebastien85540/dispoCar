/**
 * 
 */
package fr.eni.dispocar.bo;

/**
 * @author sebastien
 * Cette classe contient la destination finale de la reservation ainsi que l'adresse des agences
 *
 */
public class Destination {
	
	// ATTRIBUTS
	
	private Integer idDestination;
	private String libelleDestination;
	private int numeroDestination;
	private String rueDestination;
	private String complementDestination;
	private int codePostal;
	private String ville;
	
	// CONSTRUCTEURS
	
	/**
	 * Constructeur par defaut
	 */
	public Destination() {
	}
	
	/**
	 * Constructeur surchargé avec les attributs obligatoires
	 * @param numeroDestination
	 * @param rueDestination
	 * @param codePostal
	 * @param ville
	 */
	public Destination(int numeroDestination, String rueDestination, int codePostal, String ville) {
		setNumeroDestination(numeroDestination);
		setRueDestination(rueDestination);
		setCodePostal(codePostal);
		setVille(ville);
	}
	
	/**
	 * Constructeur surchargé avec tous les attributs sauf id
	 * @param libelleDestination
	 * @param numeroDestination
	 * @param rueDestination
	 * @param complementDestination
	 * @param codePostal
	 * @param ville
	 */
	public Destination(String libelleDestination, int numeroDestination, String rueDestination,
			String complementDestination, int codePostal, String ville) {
		setLibelleDestination(libelleDestination);
		setNumeroDestination(numeroDestination);
		setRueDestination(rueDestination);
		setComplementDestination(complementDestination);
		setCodePostal(codePostal);
		setVille(ville);
	}
	
	/**
	 * Constructeur surchargé avec tous les attributs
	 * @param idDestination
	 * @param libelleDestination
	 * @param numeroDestination
	 * @param rueDestination
	 * @param complementDestination
	 * @param codePostal
	 * @param ville
	 */
	public Destination(Integer idDestination, String libelleDestination, int numeroDestination, String rueDestination,
			String complementDestination, int codePostal, String ville) {
		setIdDestination(idDestination);
		setLibelleDestination(libelleDestination);
		setNumeroDestination(numeroDestination);
		setRueDestination(rueDestination);
		setComplementDestination(complementDestination);
		setCodePostal(codePostal);
		setVille(ville);
	}
	
	// GETTERS ET SETTERS
	// permet l'encapsulation des attributs

	/**
	 * @return the idDestination
	 */
	public Integer getIdDestination() {
		return idDestination;
	}

	/**
	 * @param idDestination the idDestination to set
	 */
	public void setIdDestination(Integer idDestination) {
		this.idDestination = idDestination;
	}

	/**
	 * @return the libelleDestination
	 */
	public String getLibelleDestination() {
		return libelleDestination;
	}

	/**
	 * @param libelleDestination the libelleDestination to set
	 */
	public void setLibelleDestination(String libelleDestination) {
		this.libelleDestination = libelleDestination;
	}

	/**
	 * @return the numeroDestination
	 */
	public int getNumeroDestination() {
		return numeroDestination;
	}

	/**
	 * @param numeroDestination the numeroDestination to set
	 */
	public void setNumeroDestination(int numeroDestination) {
		this.numeroDestination = numeroDestination;
	}

	/**
	 * @return the rueDestination
	 */
	public String getRueDestination() {
		return rueDestination;
	}

	/**
	 * @param rueDestination the rueDestination to set
	 */
	public void setRueDestination(String rueDestination) {
		this.rueDestination = rueDestination;
	}

	/**
	 * @return the complementDestination
	 */
	public String getComplementDestination() {
		return complementDestination;
	}

	/**
	 * @param complementDestination the complementDestination to set
	 */
	public void setComplementDestination(String complementDestination) {
		this.complementDestination = complementDestination;
	}

	/**
	 * @return the codePostal
	 */
	public int getCodePostal() {
		return codePostal;
	}

	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	// METHODE TO STRING
	

}
