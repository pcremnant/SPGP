package kr.ac.kpu.game.s2016180021.defence.game.unit;

import android.graphics.Canvas;

import kr.ac.kpu.game.s2016180021.defence.R;
import kr.ac.kpu.game.s2016180021.defence.framework.bitmap.AnimationStateGameBitmap;
import kr.ac.kpu.game.s2016180021.defence.framework.object.UnitObject;

public class EnemySkell extends UnitObject {

    public EnemySkell(float x, float y) {
        this.x = x;
        this.y = y;
        maxHp = 10;
        hp = maxHp;
        state = STATE_WALK;
        moveDirection = -1;
        moveSpeed = 1;
        attackDamage = 2;
        attackRange = 200;
        attackDelay = 1.5f;
        isPlayer = false;


        AnimationStateGameBitmap walkAnim = new AnimationStateGameBitmap(10, 5);
        walkAnim.addBitmap(R.mipmap.e_skell_walk0);
        walkAnim.addBitmap(R.mipmap.e_skell_walk1);
        walkAnim.addBitmap(R.mipmap.e_skell_walk2);
        walkAnim.addBitmap(R.mipmap.e_skell_walk3);
        walkAnim.addBitmap(R.mipmap.e_skell_walk4);
        animations.add(walkAnim);

        AnimationStateGameBitmap attackAnim = new AnimationStateGameBitmap(6, 3);
        attackAnim.addBitmap(R.mipmap.e_skell_attack0);
        attackAnim.addBitmap(R.mipmap.e_skell_attack1);
        attackAnim.addBitmap(R.mipmap.e_skell_attack2);
        animations.add(attackAnim);

        AnimationStateGameBitmap delayAnim = new AnimationStateGameBitmap(1, 1);
        delayAnim.addBitmap(R.mipmap.e_skell_walk0);
        delayAnim.setDelayTime(attackDelay);
        animations.add(delayAnim);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Canvas canvas) {
        animations.get(state).draw(canvas, x, y);
    }
}
