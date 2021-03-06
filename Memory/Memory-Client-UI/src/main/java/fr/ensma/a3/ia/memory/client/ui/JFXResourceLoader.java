package fr.ensma.a3.ia.memory.client.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.ensma.a3.ia.memory.client.ui.components.game.tile.TileVue;
import fr.ensma.a3.ia.memory.table.card.SpecialCard;
import javafx.scene.image.Image;

public final class JFXResourceLoader {
	
	private static final int MAX_BASIC_TILE = 35;
	
	private static final String TILE_PATH = "/texture/component/game/tile/";
	
	private static List<Image> basicTiles;
	private static Map<String, Image> specialTiles;
	
	private static Image backImage, emptyImage;
	
	public static void loadResources() {
		basicTiles = new ArrayList<Image>();
		specialTiles = new HashMap<String, Image>();
		
		backImage = new Image(TILE_PATH + "back.png" );
		emptyImage = new Image(TILE_PATH + "empty.png");
		
		for(int i = 0; i < MAX_BASIC_TILE; i++)
			basicTiles.add(new Image(TILE_PATH + "Icons_" + (i < 9 ? "0" : "") + (i+1) + ".png"));
		
		for(SpecialCard card : SpecialCard.getSpecialCards())
			specialTiles.put(card.getName(), new Image(TILE_PATH + card.getName() + ".png"));
		
			
	}
	
	public static void init() {
		TileVue.setBackImage(backImage);
		TileVue.setEmptyImage(emptyImage);
	}
	
	public static Image getBasicTileImage(int n) {
		return basicTiles.get(n);
	}
	
	public static Image getSpecialTileImage(String name) {
		return specialTiles.get(name);
	}
	
	public static Image getBackImage() {
		return backImage;
	}
	
	public static Image getEmptyImage() {
		return emptyImage;
	}
	
	

}
