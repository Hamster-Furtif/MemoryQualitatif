package fr.ensma.a3.ia.memory.dao.entity;

public class PersonneEntity extends Entity{

	private int idPers;
	private String nomPers;
	private String prenomPers;
	private int addressePers_FK;
	
	public int getIdPers() {
		return idPers;
	}
	public void setIdPers(int idPers) {
		this.idPers = idPers;
	}
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
	public int getAddressePers_FK() {
		return addressePers_FK;
	}
	public void setAddressePers_FK(int i) {
		this.addressePers_FK = i;
	}


	
	@Override
	public String toString() {
		return idPers + " : " + nomPers + " " + prenomPers + " - " + addressePers_FK;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + addressePers_FK;
		result = prime * result + idPers;
		result = prime * result + ((nomPers == null) ? 0 : nomPers.hashCode());
		result = prime * result + ((prenomPers == null) ? 0 : prenomPers.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonneEntity other = (PersonneEntity) obj;
		if (addressePers_FK != other.addressePers_FK)
			return false;
		if (idPers != other.idPers)
			return false;
		if (nomPers == null) {
			if (other.nomPers != null)
				return false;
		} else if (!nomPers.equals(other.nomPers))
			return false;
		if (prenomPers == null) {
			if (other.prenomPers != null)
				return false;
		} else if (!prenomPers.equals(other.prenomPers))
			return false;
		return true;
	}
	
	
	
}
