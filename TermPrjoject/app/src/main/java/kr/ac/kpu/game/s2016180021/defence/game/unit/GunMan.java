package kr.ac.kpu.game.s2016180021.defence.game.unit;

import android.graphics.Canvas;

import kr.ac.kpu.game.s2016180021.defence.R;
import kr.ac.kpu.game.s2016180021.defence.framework.bitmap.AnimationStateGameBitmap;
import kr.ac.kpu.game.s2016180021.defence.framework.object.UnitObject;

public class GunMan extends UnitObject {
    public GunMan(float x, float y) {
        this.x = x;
        this.y = y;
        maxHp = 10;
        hp = maxHp;
        state = STATE_WALK;
        moveDirection = 1;
        moveSpeed = 1;
        attackDamage = 2;
        attackRange = 400;
        attackDelay = 0.8f;
        isPlayer = true;


        AnimationStateGameBitmap walkAnim = new AnimationStateGameBitmap(8, 4);
        walkAnim.addBitmap(R.mipmap.gunman_walk0);
        walkAnim.addBitmap(R.mipmap.gunman_walk1);
        walkAnim.addBitmap(R.mipmap.gunman_walk2);
        walkAnim.addBitmap(R.mipmap.gunman_walk3);
        animations.add(walkAnim);

        AnimationStateGameBitmap attackAnim = new AnimationStateGameBitmap(6, 2);
        attackAnim.addBitmap(R.mipmap.gunman_attack0);
        attackAnim.addBitmap(R.mipmap.gunman_attack1);
        animations.add(attackAnim);

        AnimationStateGameBitmap delayAnim = new AnimationStateGameBitmap(1, 1);
        delayAnim.addBitmap(R.mipmap.gunman_walk3);
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
