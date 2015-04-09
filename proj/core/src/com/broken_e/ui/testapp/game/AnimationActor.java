package com.broken_e.ui.testapp.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AnimationActor extends Actor {

        public Animation animation;
        public AnimationActor(Animation a){
            this.animation = a;
        }

        float stateTime = 0f;
        @Override
        public void draw(Batch batch, float parentAlpha) {
            TextureRegion region = animation.getKeyFrame(stateTime,true);
            stateTime += Gdx.graphics.getDeltaTime();
            batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

        }
    }