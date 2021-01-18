package fr.ensma.a3.ia.memory;

import java.util.List;
import java.util.Optional;

import fr.ensma.a3.ia.memory.business.Adresse;
import fr.ensma.a3.ia.memory.business.Factory;
import fr.ensma.a3.ia.memory.business.Personne;
import fr.ensma.a3.ia.memory.dao.AdressePoiDAO;
import fr.ensma.a3.ia.memory.dao.IDao;
import fr.ensma.a3.ia.memory.dao.entity.AdresseEntity;

public class App 
{
    public static void main( String[] args )
    {
    	
    	IDao<AdresseEntity> adrdao = new AdressePoiDAO();
    	List<AdresseEntity> alladr = adrdao.getAll();
    	for(AdresseEntity ad : alladr) {
    		System.out.println(ad);
    	}
    	AdresseEntity adcherche = new AdresseEntity();
    	adcherche.setNumRue(12);
    	adcherche.setNomRue("rue toulent");
    	adcherche.setCodePostal(91000);
    	adcherche.setNomVille("JavaLand");
    	Optional<AdresseEntity> res = adrdao.getByValue(adcherche); 
    	if(res.isPresent()) {
    		System.out.println(res.get());
    	}
    	res = adrdao.getById(1);
    	if(res.isPresent()) {
    		System.out.println(res.get());
    	}
    	AdresseEntity adrajout = new AdresseEntity();
    	adrajout.setNumRue(10);
    	adrajout.setNomRue("toumoche");
    	adrajout.setCodePostal(97000);
    	adrajout.setNomVille("PitonVille");
    	adrdao.create(adrajout);
    	adrajout.setNomVille("PythonVille");
    	adrdao.update(adrajout);
    	adrdao.delete(adrdao.getById(3).get());
    	
    	List<Personne> lst = Factory.getPersonnesFromName("RICHARD");
    	lst = Factory.getAllPersonnes();
    	for(Personne pers : lst)
    		System.out.println(pers.toString());
    	//12	rue toulent	91000	JavaLand

    	Adresse addYasEtKeulKeul = new Adresse();
    	addYasEtKeulKeul.setCodePostal(91000);
    	addYasEtKeulKeul.setNomRue("rue toulent");
    	addYasEtKeulKeul.setNomVille("JavaLand");
    	addYasEtKeulKeul.setNumRue(12);
    	
    	Personne yass = new Personne();
    	yass.setAdresse(addYasEtKeulKeul);
    	yass.setNomPers(":)");
    	yass.setPrenomPers("Yass");
    	
    	Factory.addPersonneToDB(yass);
    	
    	
    	Adresse nice = new Adresse();
    	nice.setCodePostal(123456);
    	nice.setNomRue("rue brice");
    	nice.setNomVille("Nice");
    	nice.setNumRue(12345);
    	
    	Personne brice = new Personne();
    	brice.setAdresse(nice);
    	brice.setNomPers("Chardin");
    	brice.setPrenomPers("Brice");
    	
    	Factory.addPersonneToDB(brice);
    	
    }
}
