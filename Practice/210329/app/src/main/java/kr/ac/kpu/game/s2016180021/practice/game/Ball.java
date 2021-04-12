package kr.ac.kpu.game.s2016180021.practice.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016180021.practice.R;
import kr.ac.kpu.game.s2016180021.practice.framework.AnimationGameBitmap;
import kr.ac.kpu.game.s2016180021.practice.framework.GameObject;
import kr.ac.kpu.game.s2016180021.practice.ui.view.GameView;

public class Ball implements GameObject {
    private final int sx;
    private final int sy;
    private float x, y;
    private float dx, dy;
    private static AnimationGameBitmap bitmap = null;
    private float FRAME_RATE = 8.5f;

    public Ball(float x, float y, float dx, float dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;

        if (bitmap == null) {
            bitmap = new AnimationGameBitmap(R.mipmap.fireball_128_24f, FRAME_RATE, 0);
        }


        this.sx = bitmap.getWidth();
        this.sy = bitmap.getHeight();
    }

    public void update() {
        MainGame game = MainGame.get();
        x += dx * game.frameTime;
        y += dy * game.frameTime;
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        if (x - sx / 2 < 0)
            this.dx = Math.abs(this.dx);
        else if (x + sx / 2 > w )
            this.dx = Math.abs(this.dx) * -1;
        if (y- sy / 2 < 0 )
            this.dy = Math.abs(this.dy);
        else if(y + sy / 2 > h)
            this.dy = Math.abs(this.dy) * -1;

        // bitmap.update();
    }

    public void draw(Canvas canvas) {
        bitmap.draw(canvas, this.x, this.y);
    }
}
