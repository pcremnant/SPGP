package kr.ac.kpu.game.s2016180021.defence.game.unit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016180021.defence.R;
import kr.ac.kpu.game.s2016180021.defence.framework.bitmap.GameBitmap;
import kr.ac.kpu.game.s2016180021.defence.framework.view.GameView;
import kr.ac.kpu.game.s2016180021.defence.framework.iface.GameObject;

public class Resource implements GameObject {
    private final Bitmap bitmap;
    private final int right;
    private final int top;
    int max = 800;

    public void setResource(int resource) {
        this.resource = resource;
        this.displayScore = resource;
    }
    public void addResource(int amount) {
        this.resource += amount;
        if (this.resource >= max)
            this.resource = max;
    }

    public int getResource(){
        return resource;
    }

    private int resource, displayScore;
    private Rect src = new Rect();
    private RectF dst = new RectF();

    public Resource(int right, int top) {
        bitmap = GameBitmap.load(R.mipmap.number_24x32);
        this.right = right;
        this.top = top;
    }
    @Override
    public void update() {
        addResource(1);
        if (displayScore < resource) {
            displayScore++;
        }
        else if (displayScore > resource){
            displayScore--;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        int value = this.displayScore;
        int nw = bitmap.getWidth() / 10;
        int nh = bitmap.getHeight();
        int x = right;
        int dw = (int) (nw * GameView.MULTIPLIER) / 2;
        int dh = (int) (nh * GameView.MULTIPLIER) / 2;
        while (value > 0) {
            int digit = value % 10;
            src.set(digit * nw, 0, (digit + 1) * nw, nh);
            x -= dw;
            dst.set(x, top, x + dw, top + dh);
            canvas.drawBitmap(bitmap, src, dst, null);
            value /= 10;
        }
    }
}
