package BattleBoatView.animation;

import java.io.File;

import BattleBoatView.BBApplication;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class BBAnimationHitM extends BBTokenChangeAnimation{

	public BBAnimationHitM(Button button) {
		super(button);
		this.animation = new Image(new File("src/BattleBoatViewAssets/shipmid.gif").toURI().toString());
		this.finalImage = BBApplication.shipMH;
	}

}
