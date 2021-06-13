package kr.ac.kpu.game.s2016180021.defence.game.unit;

import android.graphics.Canvas;

import kr.ac.kpu.game.s2016180021.defence.R;
import kr.ac.kpu.game.s2016180021.defence.framework.bitmap.AnimationStateGameBitmap;
import kr.ac.kpu.game.s2016180021.defence.framework.object.UnitObject;

public class EnemyCastle extends UnitObject {
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


        AnimationStateGameBitmap castle = new AnimationStateGameBitmap(1, 1);
        castle.addBitmap(R.mipmap.e_castle);
        animations.add(castle);
    }

    @Override
    public void update() {
        // super.update();
    }

    @Override
    public void draw(Canvas canvas) {
        animations.get(0).draw(canvas, x, y);
    }
}
