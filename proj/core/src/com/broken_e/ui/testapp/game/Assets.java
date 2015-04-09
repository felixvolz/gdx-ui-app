package com.broken_e.ui.testapp.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {
    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();
    private AssetManager assetManager;
    private TextureRegion textureRegion;
    // singleton: prevent instantiation from other classes
    private TextureAtlas textureAtlas;


    private Assets() {
    }

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
// set asset manager error handler
        assetManager.setErrorListener(this);
// load texture atlas
        assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS,
                TextureAtlas.class);
// start loading assets and wait until finished
        assetManager.finishLoading();

        textureAtlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS,
                TextureAtlas.class);

        Gdx.app.debug(TAG, "# of assets loaded: "
                + assetManager.getAssetNames().size);
        for (String a : assetManager.getAssetNames())
            Gdx.app.debug(TAG, "asset: " + a);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }



    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset '"
                + asset + "'", (Exception) throwable);
    }

    public AnimationActor getAnimationActor(String name){
        return new AnimationActor(getAnimation(name));
    }

    public Animation getAnimation(String image){

        //look up any animations against this asset
        Array<TextureAtlas.AtlasRegion> array  =textureAtlas.findRegions(image);
        Animation a = new Animation(1f/array.size,array);
        a.setPlayMode(Animation.PlayMode.LOOP);
        return a;
    }
}