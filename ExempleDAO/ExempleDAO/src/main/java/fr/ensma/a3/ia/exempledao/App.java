package fr.ensma.a3.ia.exempledao;

import java.util.List;
import java.util.Optional;

import fr.ensma.a3.ia.exempledao.dao.AdressePoiDAO;
import fr.ensma.a3.ia.exempledao.dao.IDao;
import fr.ensma.a3.ia.exempledao.dao.entity.AdresseEntity;

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
    }
}
