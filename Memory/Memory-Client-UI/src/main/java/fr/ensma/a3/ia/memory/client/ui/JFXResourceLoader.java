package fr.ensma.a3.ia.memory.client.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;

public final class JFXResourceLoader {
	
	private static final int MAX_BASIC_TILE = 39;
	
	private static final String TILE_PATH = "/texture/component/game/tile/";
	
	private static List<Image> basicTiles;
	private static Map<String, Image> specialTiles;
	
	private static Image backImage;
	
	public static void loadResources() {
		basicTiles = new ArrayList<Image>();
		specialTiles = new HashMap<String, Image>();
		//TODO Create images and make them be loaded upon client startup
		
		backImage = new Image(TILE_PATH + "back.png" );
		
		for (int i = 0; i < MAX_BASIC_TILE; i++)
			basicTiles.add(new Image(TILE_PATH + "Icons_" + (i < 9 ? "0" : "") + (i+1) + ".png"));
		
			
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
	
	

}
