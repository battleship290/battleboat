package BattleBoatView.animation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;


abstract class BBTokenChangeAnimation {
	public static final long animationTime = 350; // Time it takes for animation to play from start to finish
	protected Image animation;
	protected Image finalImage; // the image to display after the animation has played
	private Button button;
	
	/**
	 * Is meant to be a parent constructor
	 * @param button
	 */
	protected BBTokenChangeAnimation(Button button) {
		this.button = button;
	}
	
	/**
	 * plays the animation, disables buttons while animation is playing.
	 */
	public void play() {
		new Timeline(new KeyFrame(Duration.millis(animationTime), ae -> reset())).play();
		button.setGraphic(new ImageView(this.animation));
	}
	
	/**
	 * set an image of the end of animation on the button
	 */
	private void reset() {
		button.setGraphic(new ImageView(this.finalImage));
	}
}