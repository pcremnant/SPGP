package kr.ac.kpu.game.s2016180021.practice.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import kr.ac.kpu.game.s2016180021.practice.R;
import kr.ac.kpu.game.s2016180021.practice.framework.GameObject;
import kr.ac.kpu.game.s2016180021.practice.framework.Sound;
import kr.ac.kpu.game.s2016180021.practice.ui.view.GameView;

public class Player implements GameObject {
    private static Bitmap bitmap = null;
    private static int imageWidth;
    private static int imageHeight;
    private Sound mediaPlayer;
    private float x, y;
    private float dx, dy;
    private float tx, ty;
    private float speed;
    private float angle;

    public Player(float x, float y, float dx, float dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.tx = 0;
        this.ty = 0;
        this.speed = 800;
        if (bitmap == null) {
            Resources res = GameView.view.getResources();
            bitmap = BitmapFactory.decodeResource(res, R.mipmap.plane_240);
            imageWidth = bitmap.getWidth();
            imageHeight = bitmap.getHeight();
        }
        // mediaPlayer = MediaPlayer.create(GameView.view.getContext(), R.raw.hadoken);
    }

    public void update() {
        // this.x += speed * dx * MainGame.get().frameTime;
        // this.y += speed * dy * MainGame.get().frameTime;
        // if (dx > 0 && x > tx)
        //     x = tx;
        // else if (dx <= 0 && x < tx)
        //     x = tx;
        // if (dy > 0 && y > ty)
        //     y = ty;
        // else if (dy<= 0 && y<ty)
        //     y = ty;
    }

    public void moveTo(float x, float y){
        mediaPlayer.Play(R.raw.knife_swing);
        Bullet bullet = new Bullet(this.x, this.y, x, y);
        MainGame.get().add(bullet);
       this.tx = x;
       this.ty = y;
       float delta_x = tx - this.x;
       float delta_y = ty - this.y;
       angle = (float) Math.atan2(delta_y, delta_x);
       // this.dx = (float) Math.cos(angle);
       // this.dy = (float) Math.sin(angle);
    }

    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate((float)Math.toDegrees(angle) + 90, this.x, this.y);
        canvas.drawBitmap(bitmap, this.x - imageWidth/2, this.y - imageHeight/2, null);
        canvas.restore();
        // canvas.rotate( -((float)Math.toDegrees(angle) + 90), this.x, this.y);
    }
}
