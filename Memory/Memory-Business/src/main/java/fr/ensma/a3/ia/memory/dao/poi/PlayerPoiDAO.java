package fr.ensma.a3.ia.memory.dao.poi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fr.ensma.a3.ia.memory.dao.DAOException;
import fr.ensma.a3.ia.memory.dao.DAOException.DuplicateElement;
import fr.ensma.a3.ia.memory.dao.DAOException.ElementNotFound;
import fr.ensma.a3.ia.memory.dao.entity.PlayerEntity;

public class PlayerPoiDAO extends AbstractPoiDAO<PlayerEntity>{
	
	@Override
	public Optional<PlayerEntity> getById(int id) {
		XSSFWorkbook bdd = openBase();
		Sheet tableadr = bdd.getSheet("Players");
		Iterator<Row> iterator = tableadr.iterator();
		iterator.next();
		boolean trouve = false;
		PlayerEntity player = null;
		while (iterator.hasNext() && !trouve) {
			Row ligne = iterator.next();
			player = new PlayerEntity();
			if (id == (int)ligne.getCell(0).getNumericCellValue()) {
				player.setId(id);
				player.setEmail(ligne.getCell(1).getStringCellValue());
				trouve = true;
			}
		}
		if (trouve) {
			return Optional.of(player);
		}
		return Optional.empty();
	}

	@Override
	public Optional<PlayerEntity> getByValue(PlayerEntity elem) {
		List<PlayerEntity> listtemp = getAll();
		for (PlayerEntity ad : listtemp) {
			if (ad.equals(elem)) {
				return Optional.of(ad);
			}
		}
		return Optional.empty();
	}

	@Override
	public List<PlayerEntity> getAll() {
		XSSFWorkbook bdd = openBase();
		Sheet tableadr = bdd.getSheet("Players");
		ArrayList<PlayerEntity> listeplayer = new ArrayList<PlayerEntity>();
		Iterator<Row> iterator = tableadr.iterator();
		iterator.next();
		while (iterator.hasNext()) {
			Row ligne = iterator.next();
			PlayerEntity player = new PlayerEntity();
			player.setId((int)ligne.getCell(0).getNumericCellValue());
			player.setEmail(ligne.getCell(1).getStringCellValue());
			listeplayer.add(player);
		}
		return listeplayer;
	}

	@Override
	public void create(PlayerEntity elem) throws DuplicateElement {
		if (getByValue(elem).isEmpty()) {
			XSSFWorkbook bdd = openBase();
			Sheet tableadr = bdd.getSheet("Players");
			int lrow = tableadr.getLastRowNum();
			int lid = (int) tableadr.getRow(lrow).getCell(0).getNumericCellValue();
			elem.setId(lid + 1);
			Row ligne = tableadr.createRow(lrow + 1);
			Cell cell = ligne.createCell(0);
			cell.setCellValue(elem.getId());
			cell = ligne.createCell(1);
			cell.setCellValue(elem.getEmail());
			writeBase(bdd);
		} else {
			throw new DAOException.DuplicateElement(elem);
		}
	}

	@Override
	public void update(PlayerEntity elem) throws ElementNotFound {
		XSSFWorkbook bdd = openBase();
		Sheet tableadr = bdd.getSheet("Players");
		Iterator<Row> iterator = tableadr.iterator();
		iterator.next();
		boolean trouve = false;
		while (iterator.hasNext() && !trouve) {
			Row ligne = iterator.next();
			if (elem.getId() == (int) ligne.getCell(0).getNumericCellValue()) {
				trouve = true;
				ligne.getCell(1).setCellValue(elem.getEmail());
				writeBase(bdd);
			}
		}
		if (!trouve) {
			throw new DAOException.ElementNotFound(elem);
		}
	}

	@Override
	public void delete(PlayerEntity elem) throws ElementNotFound {
		XSSFWorkbook bdd = openBase();
		Sheet tableadr = bdd.getSheet("Players");
		Iterator<Row> iterator = tableadr.iterator();
		iterator.next();
		boolean trouve = false;
		while (iterator.hasNext() && !trouve) {
			Row ligne = iterator.next();
			if (elem.getId() == (int) ligne.getCell(0).getNumericCellValue()) {
				trouve = true;
				removeRow(tableadr, ligne.getRowNum());
				writeBase(bdd);
			}
		}
		if (!trouve) {
			throw new DAOException.ElementNotFound(elem);
		}
	}
}
