package fr.ensma.a3.ia.memory.client.ui.components.game.tile;

import fr.ensma.a3.ia.memory.client.ui.JFXResourceLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TileVue extends ImageView implements ITilePresentation{
		
	private static Image backImage, emptyImage;
	
	private Image frontImage;
	
	public TileVue(TilePresentation presentation, Image img) {
		frontImage = img;
		setPickOnBounds(true);
		setImage(backImage);
		setOnMouseClicked((MouseEvent e) -> {
			presentation.onClick();
		});
	}

	
	
	@Override
	public void setFlipped(boolean isFlipped) {		
		if(isFlipped) {
			setImage(frontImage);
		}
		else {
			setImage(backImage);
		}
	}
	
	public static void setBackImage(Image image) {
		backImage = image;
	}
	
	public static void setEmptyImage(Image image) {
		emptyImage = image;
	}

	@Override
	public void setImageFromCardNumber(int n) {
		frontImage = JFXResourceLoader.getBasicTileImage(n);
	}



	@Override
	public void setEmpty() {
		setImage(emptyImage);
	}

	
}
