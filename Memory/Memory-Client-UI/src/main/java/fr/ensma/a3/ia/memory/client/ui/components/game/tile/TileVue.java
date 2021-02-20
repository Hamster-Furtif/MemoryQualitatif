package fr.ensma.a3.ia.memory.client.ui.components.game.tile;

import fr.ensma.a3.ia.memory.client.ui.JFXResourceLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class TileVue extends ImageView implements ITilePresentation, EventHandler<ActionEvent> {
		
	private static Image backImage;
	
	private Image frontImage;
	private TilePresentation presentation;
	
	public TileVue(TilePresentation presentation, Image img) {
		this.presentation = presentation;
		setPickOnBounds(true);
		setImage(img);
		setOnMouseClicked((MouseEvent e) -> {
			presentation.onClick();
		});
		System.out.println("yo");
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

	@Override
	public void setImageFromCardNumber(int n) {
		frontImage = JFXResourceLoader.getBasicTileImage(n);
	}



	@Override
	public void handle(ActionEvent event) {
		presentation.onClick();
		System.out.println("click");
	}
	
}
