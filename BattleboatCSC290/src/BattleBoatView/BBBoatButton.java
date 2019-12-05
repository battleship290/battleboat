package BattleBoatView;

import javafx.scene.control.Button;

public class BBBoatButton extends Button{
	int usageCount;
	int fixedLength;
	
	public BBBoatButton(int length, int count) {
		super();
		this.fixedLength = length;
		this.usageCount = count;
	}
	
	public int getLength() {
		return this.fixedLength;
	}
	
	public void use() {
		if (this.usageCount == 0) this.setDisable(true);
		else {
			usageCount--;
		}
	}
	
}
