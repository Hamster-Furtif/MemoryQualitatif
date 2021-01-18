package fr.ensma.a3.ia.memory.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.ensma.a3.ia.memory.dao.AdressePoiDAO;
import fr.ensma.a3.ia.memory.dao.IDao;
import fr.ensma.a3.ia.memory.dao.PersonnePoiDAO;
import fr.ensma.a3.ia.memory.dao.entity.AdresseEntity;
import fr.ensma.a3.ia.memory.dao.entity.PersonneEntity;
import fr.ensma.a3.ia.memory.dao.entity.PoiDAOException.ElementDejaPresent;


public final class Factory {

	private static Adresse getAddresseFromID(int id) {
		Adresse adr = new Adresse();
		AdressePoiDAO poi = new AdressePoiDAO();
		Optional<AdresseEntity> ent = poi.getById(id);
		if(!ent.isEmpty()) {
			adr.setCodePostal(ent.get().getCodePostal());
			adr.setNomRue(ent.get().getNomRue());
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
			adr.setNomRue(ent.getNomRue());
			adr.setNomVille(ent.getNomVille());
			adr.setNumRue(ent.getNumRue());
			lst.add(adr);
		}
		
		return lst;
	}
	
	public static void addPersonneToDB(Personne per) throws ElementDejaPresent {
		IDao<PersonneEntity> pDAO = new PersonnePoiDAO();
		PersonneEntity perEnt = new PersonneEntity();
		
		int addID = Factory.addAdresseToDBAndReturnInt(per.getAdresse());
		
		perEnt.setNomPers(per.getNomPers());
		perEnt.setPrenomPers(per.getPrenomPers());
		perEnt.setAddressePers_FK(addID);
		
		pDAO.create(perEnt);
		
	}
	
	public static void addAdresseToDB(Adresse adr) throws ElementDejaPresent {
		Factory.addAdresseToDBAndReturnInt(adr);
	}
	
	private static int addAdresseToDBAndReturnInt(Adresse adr) throws ElementDejaPresent {
		IDao<AdresseEntity> aDAO = new AdressePoiDAO();
		AdresseEntity ent = new AdresseEntity();
		System.out.println(">>" + adr.toString());
		ent.setCodePostal(adr.getCodePostal());
		ent.setNomRue(adr.getNomRue());
		ent.setNomVille(adr.getNomVille());
		ent.setNumRue(adr.getNumRue());
		
		Optional<AdresseEntity> opt;
		
		if( (opt = aDAO.getByValue(ent)).isEmpty()){
			aDAO.create(ent);
			opt = aDAO.getByValue(ent);
		}
		
		return opt.get().getIdAdr();
	}

	
}
