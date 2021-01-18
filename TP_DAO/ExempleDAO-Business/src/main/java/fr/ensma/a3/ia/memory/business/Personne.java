package fr.ensma.a3.ia.memory.business;

public class Personne {

	private String nomPers;
	private String prenomPers;
	private Adresse adresse;
	

	public String getNomPers() {
		return nomPers;
	}
	public void setNomPers(String nomPers) {
		this.nomPers = nomPers;
	}
	public String getPrenomPers() {
		return prenomPers;
	}
	public void setPrenomPers(String prenomPers) {
		this.prenomPers = prenomPers;
	}

	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	@Override
	public String toString() {
		return "Personne : " + nomPers + " " + prenomPers + " - (" + adresse.toString() + ")";
	}

	
}
