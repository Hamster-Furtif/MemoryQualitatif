package fr.ensma.a3.ia.tile;

import fr.ensma.a3.ia.Mode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TileVue extends Button implements ITilePresentation, EventHandler<ActionEvent> {
	
	private TilePresentation presentation;
	
	public TileVue(int x, int y) {
		presentation = new TilePresentation(this, x, y);
		setMode(Mode.EMPTY);
		setOnAction(this);
	}	
	
	private final static Image iCircle = new Image("/circle.png");
	private final static Image iCross  = new Image("/cross.png");
	private final static Image iEmpty  = new Image("/empty.png");
	
	private ImageView circle = new ImageView(iCircle);
	private ImageView cross  = new ImageView(iCross);
	private ImageView empty  = new ImageView(iEmpty);

	@Override
	public void handle(ActionEvent event) {
		presentation.onClick();
	}
	
	@Override
	public void setMode(Mode mode) {
		switch (mode) {
		case CIRCLE:
			setGraphic(circle);
			this.setDisable(true);
			break;
			
		case CROSS:
			setGraphic(cross);
			this.setDisable(true);
			break;

		default:
			setGraphic(empty);
			break;
		}
	}
	
	public TilePresentation getPresentation() {
		return presentation;
	}

}
