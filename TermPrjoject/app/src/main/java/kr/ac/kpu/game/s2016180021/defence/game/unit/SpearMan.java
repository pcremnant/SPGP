package kr.ac.kpu.game.s2016180021.defence.game.unit;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016180021.defence.R;
import kr.ac.kpu.game.s2016180021.defence.framework.bitmap.AnimationStateGameBitmap;
import kr.ac.kpu.game.s2016180021.defence.framework.iface.BoxCollidable;
import kr.ac.kpu.game.s2016180021.defence.framework.object.UnitObject;
import kr.ac.kpu.game.s2016180021.defence.framework.view.GameView;
import kr.ac.kpu.game.s2016180021.defence.game.MainGame;

public class SpearMan extends UnitObject{

    float t;
    public SpearMan(float x, float y) {
        this.x = x;
        this.y = y;
        maxHp = 10;
        hp = maxHp;
        state = STATE_WALK;
        moveDirection = 1;
        moveSpeed = 1;
        attackDamage = 1;
        attackRange = 100;
        attackDelay = 0.3f;
        t = 0;
        isPlayer = true;


        AnimationStateGameBitmap walkAnim = new AnimationStateGameBitmap(4, 2);
        walkAnim.addBitmap(R.mipmap.spearman_walk0);
        walkAnim.addBitmap(R.mipmap.spearman_walk1);
        animations.add(walkAnim);

        AnimationStateGameBitmap attackAnim = new AnimationStateGameBitmap(8, 4);
        attackAnim.addBitmap(R.mipmap.spearman_attack0);
        attackAnim.addBitmap(R.mipmap.spearman_attack1);
        attackAnim.addBitmap(R.mipmap.spearman_attack2);
        attackAnim.addBitmap(R.mipmap.spearman_attack3);
        animations.add(attackAnim);

        AnimationStateGameBitmap delayAnim = new AnimationStateGameBitmap(1, 1);
        delayAnim.addBitmap(R.mipmap.spearman_walk1);
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
