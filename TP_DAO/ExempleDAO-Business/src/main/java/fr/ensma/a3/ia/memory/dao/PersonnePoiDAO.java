package fr.ensma.a3.ia.memory.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fr.ensma.a3.ia.memory.dao.entity.PersonneEntity;
import fr.ensma.a3.ia.memory.dao.entity.PoiDAOException;
import fr.ensma.a3.ia.memory.dao.entity.PoiDAOException.ElementAbsent;
import fr.ensma.a3.ia.memory.dao.entity.PoiDAOException.ElementDejaPresent;


public class PersonnePoiDAO extends AbstractPoiDAO<PersonneEntity>{
	
	@Override
	public Optional<PersonneEntity> getById(int id) {
		XSSFWorkbook bdd = openBase();
		Sheet tableadr = bdd.getSheet("Personnes");
		Iterator<Row> iterator = tableadr.iterator();
		iterator.next();
		boolean trouve = false;
		PersonneEntity pers = null;
		while (iterator.hasNext() && !trouve) {
			Row ligne = iterator.next();
			pers = new PersonneEntity();
			if (id == (int)ligne.getCell(0).getNumericCellValue()) {
				pers.setIdPers((int)ligne.getCell(0).getNumericCellValue());
				pers.setNomPers(ligne.getCell(1).getStringCellValue());
				pers.setPrenomPers(ligne.getCell(2).getStringCellValue());
				pers.setAddressePers_FK((int)ligne.getCell(3).getNumericCellValue());
				trouve = true;
			}
		}
		if (trouve) {
			return Optional.of(pers);
		}
		return Optional.empty();		
	}
	
	@Override
	public Optional<PersonneEntity> getByValue(PersonneEntity elem) {
		List<PersonneEntity> listtemp = getAll();
		for (PersonneEntity ad : listtemp) {
			if (ad.equals(elem)) {
				return Optional.of(ad);
			}
		}
		return Optional.empty();
	}

	@Override
	public List<PersonneEntity> getAll() {
		XSSFWorkbook bdd = openBase();
		Sheet tableadr = bdd.getSheet("Personnes");
		ArrayList<PersonneEntity> listepers = new ArrayList<PersonneEntity>();
		Iterator<Row> iterator = tableadr.iterator();
		iterator.next();
		while (iterator.hasNext()) {
			Row ligne = iterator.next();
			PersonneEntity pers = new PersonneEntity();
			Iterator<Cell> cellIterator = ligne.iterator();
			Cell cellule = cellIterator.next();
			pers.setIdPers((int)cellule.getNumericCellValue());
			pers.setNomPers(ligne.getCell(1).getStringCellValue());
			pers.setPrenomPers(ligne.getCell(2).getStringCellValue());
			pers.setAddressePers_FK((int)ligne.getCell(3).getNumericCellValue());
			listepers.add(pers);
		}
		return listepers;
	}

	@Override
	public void create(PersonneEntity elem) throws ElementDejaPresent {
		if (getByValue(elem).isEmpty()) {
			XSSFWorkbook bdd = openBase();
			Sheet tableadr = bdd.getSheet("Personnes");
			int lrow = tableadr.getLastRowNum();
			int lid = (int) tableadr.getRow(lrow).getCell(0).getNumericCellValue();
			elem.setIdPers(lid + 1);
			Row ligne = tableadr.createRow(lrow + 1);
			Cell cell = ligne.createCell(0);
			cell.setCellValue(elem.getIdPers());
			cell = ligne.createCell(1);
			cell.setCellValue(elem.getNomPers());
			cell = ligne.createCell(2);
			cell.setCellValue(elem.getPrenomPers());
			cell = ligne.createCell(3);
			cell.setCellValue(elem.getAddressePers_FK());
			writeBase(bdd);
		} else {
			throw new PoiDAOException.ElementDejaPresent(elem);
		}
	}

	@Override
	public void update(PersonneEntity elem) throws ElementAbsent {
		XSSFWorkbook bdd = openBase();
		Sheet tableadr = bdd.getSheet("Personnes");
		Iterator<Row> iterator = tableadr.iterator();
		iterator.next();
		boolean trouve = false;
		while (iterator.hasNext() && !trouve) {
			Row ligne = iterator.next();
			if (elem.getIdPers() == (int) ligne.getCell(0).getNumericCellValue()) {
				trouve = true;
				ligne.getCell(1).setCellValue(elem.getNomPers());
				ligne.getCell(2).setCellValue(elem.getPrenomPers());
				ligne.getCell(3).setCellValue(elem.getAddressePers_FK());
				writeBase(bdd);
			}
		}
		if (!trouve) {
			throw new PoiDAOException.ElementAbsent(elem);
		}
	}

	@Override
	public void delete(PersonneEntity elem) throws ElementAbsent {
		XSSFWorkbook bdd = openBase();
		Sheet tableadr = bdd.getSheet("Personnes");
		Iterator<Row> iterator = tableadr.iterator();
		iterator.next();
		boolean trouve = false;
		while (iterator.hasNext() && !trouve) {
			Row ligne = iterator.next();
			if (elem.getIdPers() == (int) ligne.getCell(0).getNumericCellValue()) {
				trouve = true;
				removeRow(tableadr, ligne.getRowNum());
				writeBase(bdd);
			}
		}
		if (!trouve) {
			throw new PoiDAOException.ElementAbsent(elem);
		}
	}




	
	
}
