package kr.ac.kpu.game.s2016180021.practice.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import kr.ac.kpu.game.s2016180021.practice.R;
import kr.ac.kpu.game.s2016180021.practice.framework.GameObject;
import kr.ac.kpu.game.s2016180021.practice.ui.view.GameView;

public class Ball implements GameObject {
    private static Bitmap bitmap = null;
    private static int imageWidth;
    private static int imageHeight;
    private float x, y;
    private float dx, dy;

    public Ball(float x, float y, float dx, float dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        if (bitmap == null) {
            Resources res = GameView.view.getResources();
            bitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
            imageWidth = bitmap.getWidth();
            imageHeight = bitmap.getHeight();
        }
    }

    public void update() {
        MainGame game = MainGame.get();
        x += dx * game.frameTime;
        y += dy * game.frameTime;
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        if (x < 0 || x + imageWidth > w)
            this.dx *= -1;
        if (y < 0 || y + imageHeight > h)
            this.dy *= -1;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, this.x, this.y, null);
    }
}
