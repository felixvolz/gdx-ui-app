package com.broken_e.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.broken_e.ui.testapp.game.AnimationActor;
import com.broken_e.ui.testapp.game.Assets;

/**
 * the abstract base screen class similar to gdx.Screen but used for UiApp and includes screen transitions
 * 
 * @author trey miller
 * 
 */
public abstract class BaseScreen extends Group {

	protected final UiApp app;

	/** a table that covers the whole screen by default */
	protected final Table mainTable = new Table();

	/** the default padding of the mainTable */
	public static float defaultPad;
	
	/** the duration of the screen transition for the screenOut method */
	public float dur;

    AnimationActor background;

	public BaseScreen(UiApp app) {
		this.app = app;
		this.dur = app.defaultDur;
		defaultPad = Math.round(Math.max(app.h, app.w) * .02f);
		mainTable.defaults().pad(defaultPad);
		mainTable.setSize(app.w, app.h);
		this.addActor(mainTable);
        mainTable.toFront();

        //BACKGROUND
        background = Assets.instance.getAnimationActor(getBackground());
        background.setPosition(0,0);
        background.setBounds(0, 0, app.w, app.h);

        this.addActor(background);
        background.toBack();
        //BACKGROUND
	}

	/** override if you want, good for if you keep your screens around instead of creating new ones each time */
	public BaseScreen show() {
		return this;
	}

	/** override for custom screen transitions, otherwise current screen just slides to the left */
	protected void screenOut() {
		float xPos = -app.w;
		MoveToAction action = Actions.moveTo(xPos, 0f, dur);
		addAction(action);
	}

	/** what happens when the back button is pressed on Android */
	public abstract void onBackPress();

    public abstract String getBackground();

	/** default ad width from admob */
	public static int getAdPxW() {
		return (int) (320f * Gdx.graphics.getDensity());
	}

	/** default ad height from admob */
	public static int getAdPxH() {
		return (int) (50f * Gdx.graphics.getDensity());
	}

	public void hide() {
	}
}
