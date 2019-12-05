package BattleBoatView.animation;

import java.io.File;

import BattleBoatView.BBApplication;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class BBAnimationHitE extends BBTokenChangeAnimation{

	public BBAnimationHitE(Button button) {
		super(button);
		this.animation = new Image(new File("src/BattleBoatViewAssets/shipend.gif").toURI().toString());
		this.finalImage = BBApplication.shipEH;
	}

}
