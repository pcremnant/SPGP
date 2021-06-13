package kr.ac.kpu.game.s2016180021.defence.game.unit;

import android.graphics.Canvas;

import kr.ac.kpu.game.s2016180021.defence.R;
import kr.ac.kpu.game.s2016180021.defence.framework.bitmap.AnimationStateGameBitmap;
import kr.ac.kpu.game.s2016180021.defence.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180021.defence.game.MainGame;

public class DeadEffect implements GameObject {
    float x;
    float y;
    AnimationStateGameBitmap animation;
    public DeadEffect(float x, float y){
        this.x = x;
        this.y = y;

        animation = new AnimationStateGameBitmap(10, 4);
        animation.addBitmap(R.mipmap.unit_dead0);
        animation.addBitmap(R.mipmap.unit_dead1);
        animation.addBitmap(R.mipmap.unit_dead2);
        animation.addBitmap(R.mipmap.unit_dead3);
    }

    @Override
    public void update() {
        animation.advanceFrame();
        if (animation.isEndDelay()){
            MainGame game = MainGame.get();
            game.remove(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        animation.draw(canvas, this.x, this.y);
    }
}
