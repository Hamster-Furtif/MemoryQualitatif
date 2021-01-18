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
import fr.ensma.a3.ia.memory.dao.entity.GameEntity;

public class GamePoiDAO extends AbstractPoiDAO<GameEntity>{

	private static final int PLAYER_RECORD_BEGIN = 3;
	
	@Override
	public Optional<GameEntity> getById(int id) {
		XSSFWorkbook bdd = openBase();
		Sheet tableadr = bdd.getSheet("Games");
		Iterator<Row> iterator = tableadr.iterator();
		iterator.next();
		boolean trouve = false;
		GameEntity game = null;
		while (iterator.hasNext() && !trouve) {
			Row ligne = iterator.next();
			game = new GameEntity();
			int nPlayers;
			if (id == (int)ligne.getCell(0).getNumericCellValue()) {
				game.setId(id);
				game.setnCards((int)ligne.getCell(1).getNumericCellValue());
				game.setnPlayers(nPlayers = (int)ligne.getCell(2).getNumericCellValue());
				int[] playerIDs    = new int[nPlayers];
				int[] playerScores = new int[nPlayers];
				for(int i = 0; i < nPlayers; i++) {
					playerIDs[i]    = (int)ligne.getCell(PLAYER_RECORD_BEGIN + 2*i    ).getNumericCellValue();
					playerScores[i] = (int)ligne.getCell(PLAYER_RECORD_BEGIN + 2*i + 1).getNumericCellValue();
				}
				game.setPlayerIDs(playerIDs);
				game.setPlayerScores(playerScores);
				
				trouve = true;
			}
		}
		if (trouve) {
			return Optional.of(game);
		}
		return Optional.empty();
	}

	@Override
	public Optional<GameEntity> getByValue(GameEntity elem) {
		List<GameEntity> listtemp = getAll();
		for (GameEntity ad : listtemp) {
			if (ad.equals(elem)) {
				return Optional.of(ad);
			}
		}
		return Optional.empty();
	}

	@Override
	public List<GameEntity> getAll() {
		XSSFWorkbook bdd = openBase();
		Sheet tableadr = bdd.getSheet("Games");
		ArrayList<GameEntity> listegame = new ArrayList<GameEntity>();
		Iterator<Row> iterator = tableadr.iterator();
		iterator.next();
		while (iterator.hasNext()) {
			Row ligne = iterator.next();
			GameEntity game = new GameEntity();
			int nPlayers;
			game.setId((int)ligne.getCell(0).getNumericCellValue());
			game.setnCards((int)ligne.getCell(1).getNumericCellValue());
			game.setnPlayers(nPlayers = (int)ligne.getCell(2).getNumericCellValue());
			int[] playerIDs    = new int[nPlayers];
			int[] playerScores = new int[nPlayers];
			for(int i = 0; i < nPlayers; i++) {
				playerIDs[i]    = (int)ligne.getCell(3 + 2*i    ).getNumericCellValue();
				playerScores[i] = (int)ligne.getCell(3 + 2*i + 1).getNumericCellValue();
			}
			game.setPlayerIDs(playerIDs);
			game.setPlayerScores(playerScores);
			listegame.add(game);
		}
		return listegame;
	}

	@Override
	public void create(GameEntity elem) throws DuplicateElement {
		if (getByValue(elem).isEmpty()) {
			XSSFWorkbook bdd = openBase();
			Sheet tableadr = bdd.getSheet("Games");
			int lrow = tableadr.getLastRowNum();
			int lid = (int) tableadr.getRow(lrow).getCell(0).getNumericCellValue();
			elem.setId(lid + 1);
			int nPlayers;
			Row ligne = tableadr.createRow(lrow + 1);
			Cell cell = ligne.createCell(0);
			cell.setCellValue(elem.getId());
			cell = ligne.createCell(1);
			ligne.getCell(1).setCellValue(elem.getnCards());
			cell = ligne.createCell(2);
			ligne.getCell(2).setCellValue(nPlayers = elem.getnPlayers());
			for(int i = 0; i < nPlayers; i++) {
				cell = ligne.createCell(PLAYER_RECORD_BEGIN + 2*i);
				ligne.getCell(PLAYER_RECORD_BEGIN + 2*i    ).setCellValue(elem.getPlayerIDs()[i]);
				cell = ligne.createCell(PLAYER_RECORD_BEGIN + 2*i + 1);
				ligne.getCell(PLAYER_RECORD_BEGIN + 2*i + 1).setCellValue(elem.getPlayerScores()[i]);
			}
			writeBase(bdd);
		} else {
			throw new DAOException.DuplicateElement(elem);
		}
	}

	@Override
	public void update(GameEntity elem) throws ElementNotFound {
		XSSFWorkbook bdd = openBase();
		Sheet tableadr = bdd.getSheet("Games");
		Iterator<Row> iterator = tableadr.iterator();
		iterator.next();
		boolean trouve = false;
		while (iterator.hasNext() && !trouve) {
			Row ligne = iterator.next();
			if (elem.getId() == (int) ligne.getCell(0).getNumericCellValue()) {
				trouve = true;
				int nPlayers;
				ligne.getCell(1).setCellValue(elem.getnCards());
				ligne.getCell(2).setCellValue(nPlayers = elem.getnPlayers());
				for(int i = 0; i < nPlayers; i++) {
					ligne.getCell(PLAYER_RECORD_BEGIN + 2*i    ).setCellValue(elem.getPlayerIDs()[i]);
					ligne.getCell(PLAYER_RECORD_BEGIN + 2*i + 1).setCellValue(elem.getPlayerScores()[i]);
				}

				writeBase(bdd);
			}
		}
		if (!trouve) {
			throw new DAOException.ElementNotFound(elem);
		}
	}

	@Override
	public void delete(GameEntity elem) throws ElementNotFound {
		XSSFWorkbook bdd = openBase();
		Sheet tableadr = bdd.getSheet("Games");
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
