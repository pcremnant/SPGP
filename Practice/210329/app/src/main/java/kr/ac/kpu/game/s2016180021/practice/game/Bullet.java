package kr.ac.kpu.game.s2016180021.practice.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016180021.practice.R;
import kr.ac.kpu.game.s2016180021.practice.framework.AnimationGameBitmap;
import kr.ac.kpu.game.s2016180021.practice.framework.GameObject;
import kr.ac.kpu.game.s2016180021.practice.ui.view.GameView;

public class Bullet implements GameObject {
    private AnimationGameBitmap bitmap = null;
    private final float angle;
    private final int sx;
    private final int sy;
    private float x, y;
    private float dx, dy;
    private boolean toBeDeleted;

    private float FRAME_RATE = 6;

    public Bullet(float x, float y, float tx, float ty) {
        this.x = x;
        this.y = y;

        float delta_x = tx - this.x;
        float delta_y = ty - this.y;
        angle = (float) Math.atan2(delta_y, delta_x);
        this.dx = (float) Math.cos(angle);
        this.dy = (float) Math.sin(angle);

        toBeDeleted = false;

        if (bitmap == null) {
            bitmap = new AnimationGameBitmap(R.mipmap.bullet_hadoken, FRAME_RATE, 6);
        }
        this.sx = bitmap.getWidth();
        this.sy = bitmap.getHeight();
    }

    public void update() {
        MainGame game = MainGame.get();
        x += dx * game.frameTime * 1000;
        y += dy * game.frameTime * 1000;
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        if (x- sx / 2 < 0  || x + sx / 2 > w)
            toBeDeleted = true;
        if (y - sy / 2 < 0 || y + sy / 2 > h)
            toBeDeleted = true;

        if (toBeDeleted)
            MainGame.get().removeObject(this);

        // bitmap.update();
    }

    public void draw(Canvas canvas) {
        float degree = (float)Math.toDegrees(angle + 90);
        canvas.save();
        canvas.rotate(degree, x, y);
        bitmap.draw(canvas, this.x, this.y);
        canvas.restore();
    }
}
