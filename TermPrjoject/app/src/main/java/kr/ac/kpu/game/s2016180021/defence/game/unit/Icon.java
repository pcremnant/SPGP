package kr.ac.kpu.game.s2016180021.defence.game.unit;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016180021.defence.framework.bitmap.GameBitmap;
import kr.ac.kpu.game.s2016180021.defence.framework.iface.GameObject;

public class Icon implements GameObject {

    GameBitmap bitmap;
    float x, y;
    int resId;
    public Icon(int resId, float x, float y){
        this.resId = resId;
        bitmap = new GameBitmap(resId);
        this.x = x;
        this.y = y;
    }

    public boolean isClicked(float x, float y){
        RectF r = new RectF();
        bitmap.getBoundingRect(this.x, this.y, r);
        return r.contains(x, y);
    }

    public int getResId() {return resId;}

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        bitmap.draw(canvas, x, y);
    }
}
