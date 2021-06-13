package kr.ac.kpu.game.s2016180021.defence.game.unit;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016180021.defence.R;
import kr.ac.kpu.game.s2016180021.defence.framework.bitmap.AnimationStateGameBitmap;
import kr.ac.kpu.game.s2016180021.defence.framework.object.ImageObject;
import kr.ac.kpu.game.s2016180021.defence.framework.object.UnitObject;
import kr.ac.kpu.game.s2016180021.defence.framework.view.GameView;

public class EnemyCastle extends UnitObject {
    ImageObject hpBar;
    RectF hpBarRect = new RectF();
    static final int mx = 150;
    static final int my = 200;
    public EnemyCastle(float x, float y) {
        this.x = x;
        this.y = y;
        maxHp = 100;
        hp = maxHp;
        state = STATE_WALK;
        moveDirection = 1;
        moveSpeed = 0;
        attackDamage = 0;
        attackRange = 0;
        attackDelay = 0;
        isPlayer = false;
        hpBarRect.set( x - mx, y - my, x - mx + hp * 3, y + 30 - my);
        hpBar = new ImageObject(R.mipmap.ui_hp,hpBarRect.left,hpBarRect.top, hpBarRect.right, hpBarRect.bottom);
        AnimationStateGameBitmap castle = new AnimationStateGameBitmap(1, 1);
        castle.addBitmap(R.mipmap.e_castle);
        animations.add(castle);
    }

    @Override
    public void update() {
        // super.update();
        hpBarRect.set(x - mx, y - my, x - mx + hp * 3, y + 30 - my);
        hpBar.setDstRect(hpBarRect);
    }

    public int getHp(){
        return this.hp;
    }

    @Override
    public void draw(Canvas canvas) {
        animations.get(0).draw(canvas, x, y);
        hpBar.draw(canvas);
    }
}
