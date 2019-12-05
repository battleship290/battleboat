package BattleBoatView.animation;

import java.io.File;

import BattleBoatView.BBApplication;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class BBAnimationHitS extends BBTokenChangeAnimation{

	public BBAnimationHitS(Button button) {
		super(button);
		this.animation = new Image(new File("src/BattleBoatViewAssets/shipstart.gif").toURI().toString());
		this.finalImage = BBApplication.shipSH;
	}

}
