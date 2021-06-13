package kr.ac.kpu.game.s2016180021.defence.game.unit;

import android.graphics.Canvas;

import kr.ac.kpu.game.s2016180021.defence.R;
import kr.ac.kpu.game.s2016180021.defence.framework.bitmap.AnimationStateGameBitmap;
import kr.ac.kpu.game.s2016180021.defence.framework.object.UnitObject;

public class EnemySpearMan extends UnitObject {

    public EnemySpearMan(float x, float y) {
        this.x = x;
        this.y = y;
        maxHp = 10;
        hp = maxHp;
        state = STATE_WALK;
        moveDirection = -1;
        moveSpeed = 1;
        attackDamage = 1;
        attackRange = 100;
        attackDelay = 0.5f;
        isPlayer = false;


        AnimationStateGameBitmap walkAnim = new AnimationStateGameBitmap(10, 5);
        walkAnim.addBitmap(R.mipmap.e_spearman_walk0);
        walkAnim.addBitmap(R.mipmap.e_spearman_walk1);
        walkAnim.addBitmap(R.mipmap.e_spearman_walk2);
        walkAnim.addBitmap(R.mipmap.e_spearman_walk3);
        walkAnim.addBitmap(R.mipmap.e_spearman_walk4);
        animations.add(walkAnim);

        AnimationStateGameBitmap attackAnim = new AnimationStateGameBitmap(6, 3);
        attackAnim.addBitmap(R.mipmap.e_spearman_attack0);
        attackAnim.addBitmap(R.mipmap.e_spearman_attack1);
        attackAnim.addBitmap(R.mipmap.e_spearman_attack2);
        animations.add(attackAnim);

        AnimationStateGameBitmap delayAnim = new AnimationStateGameBitmap(1, 1);
        delayAnim.addBitmap(R.mipmap.e_spearman_walk4);
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
