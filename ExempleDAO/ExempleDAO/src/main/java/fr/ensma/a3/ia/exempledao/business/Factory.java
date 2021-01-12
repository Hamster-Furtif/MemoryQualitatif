package fr.ensma.a3.ia.exempledao.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.ensma.a3.ia.exempledao.dao.AdressePoiDAO;
import fr.ensma.a3.ia.exempledao.dao.IDao;
import fr.ensma.a3.ia.exempledao.dao.PersonnePoiDAO;
import fr.ensma.a3.ia.exempledao.dao.entity.AdresseEntity;
import fr.ensma.a3.ia.exempledao.dao.entity.PersonneEntity;

public final class Factory {

	private static Adresse getAddresseFromID(int id) {
		Adresse adr = new Adresse();
		AdressePoiDAO poi = new AdressePoiDAO();
		Optional<AdresseEntity> ent = poi.getById(id);
		if(!ent.isEmpty()) {
			adr.setCodePostal(ent.get().getCodePostal());
			adr.setNumRue(ent.get().getNumRue());
			adr.setNomVille(ent.get().getNomVille());
			adr.setNumRue(ent.get().getNumRue());
		}
		
		return adr;
	}
	
	public static List<Personne> getPersonnesFromName(String name){
		ArrayList<Personne> lst = new ArrayList<Personne>();
		
		IDao<PersonneEntity> poi = new PersonnePoiDAO();
		for(PersonneEntity ent : poi.getAll())
			if(ent.getNomPers().equals(name)){
				Personne pers = new Personne();
				pers.setNomPers(ent.getNomPers());
				pers.setPrenomPers(ent.getPrenomPers());
				pers.setAdresse(Factory.getAddresseFromID(ent.getAddressePers_FK()));
				lst.add(pers);
			}
		
		return lst;
	}
	
	public static List<Personne> getPersonnesFromFirstName(String fname){
		ArrayList<Personne> lst = new ArrayList<Personne>();
		
		IDao<PersonneEntity> poi = new PersonnePoiDAO();
		for(PersonneEntity ent : poi.getAll())
			if(ent.getPrenomPers().equals(fname)){
				Personne pers = new Personne();
				pers.setNomPers(ent.getNomPers());
				pers.setPrenomPers(ent.getPrenomPers());
				pers.setAdresse(Factory.getAddresseFromID(ent.getAddressePers_FK()));
				lst.add(pers);
			}
		
		return lst;
	}
	
	public static List<Personne> getAllPersonnes(){
		ArrayList<Personne> lst = new ArrayList<Personne>();
		IDao<PersonneEntity> poi = new PersonnePoiDAO();
		
		for(PersonneEntity ent : poi.getAll()){
				Personne pers = new Personne();
				pers.setNomPers(ent.getNomPers());
				pers.setPrenomPers(ent.getPrenomPers());
				pers.setAdresse(Factory.getAddresseFromID(ent.getAddressePers_FK()));
				lst.add(pers);
			}
		
		return lst;
	} 
	
	public static List<Adresse> getAllAdresses(){
		ArrayList<Adresse> lst = new ArrayList<Adresse>();
		IDao<AdresseEntity> poi = new AdressePoiDAO();
		
		for(AdresseEntity ent : poi.getAll()) {
			Adresse adr = new Adresse();
			adr.setCodePostal(ent.getCodePostal());
			adr.setNumRue(ent.getNumRue());
			adr.setNomVille(ent.getNomVille());
			adr.setNumRue(ent.getNumRue());
			lst.add(adr);
		}
		
		return lst;
	}
	

	
}
