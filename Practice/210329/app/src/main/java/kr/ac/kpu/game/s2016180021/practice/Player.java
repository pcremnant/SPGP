package kr.ac.kpu.game.s2016180021.practice;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Player {
    private static Bitmap bitmap = null;
    private static int imageWidth;
    private static int imageHeight;
    private float x, y;
    private float dx, dy;

    public Player(float x, float y, float dx, float dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        if (bitmap == null) {
            Resources res = GameView.view.getResources();
            bitmap = BitmapFactory.decodeResource(res, R.mipmap.plane_240);
            imageWidth = bitmap.getWidth();
            imageHeight = bitmap.getHeight();
        }
    }

    public void update() {
        // x += dx * GameView.frameTime;
        // y += dy * GameView.frameTime;
        // int w = GameView.view.getWidth();
        // int h = GameView.view.getHeight();
        // if (x < 0 || x + imageWidth > w)
        //     this.dx *= -1;
        // if (y < 0 || y + imageHeight > h)
        //     this.dy *= -1;
    }

    public void moveTo(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, this.x - imageWidth/2, this.y - imageHeight/2, null);
    }
}
