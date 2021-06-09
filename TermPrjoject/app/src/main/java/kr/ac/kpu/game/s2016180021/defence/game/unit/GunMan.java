package kr.ac.kpu.game.s2016180021.defence.game.unit;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016180021.defence.R;
import kr.ac.kpu.game.s2016180021.defence.framework.bitmap.AnimationStateGameBitmap;
import kr.ac.kpu.game.s2016180021.defence.framework.iface.BoxCollidable;
import kr.ac.kpu.game.s2016180021.defence.framework.object.UnitObject;
import kr.ac.kpu.game.s2016180021.defence.framework.view.GameView;
import kr.ac.kpu.game.s2016180021.defence.game.MainGame;

public class GunMan extends UnitObject implements BoxCollidable {
    private static final int STATE_WALK = 0;
    private static final int STATE_ATTACK = 1;
    private static final int STATE_IDLE = 2;
    float t;
    public GunMan(float x, float y) {
        this.x = x;
        this.y = y;
        maxHp = 10;
        hp = maxHp;
        state = STATE_WALK;
        moveDirection = 1;
        moveSpeed = 1;
        attackDamage = 1;
        attackRange = 4;
        attackDelay = 1;
        t = 0;


        AnimationStateGameBitmap walkAnim = new AnimationStateGameBitmap(10, 4);
        walkAnim.addBitmap(R.mipmap.gunman_walk0);
        walkAnim.addBitmap(R.mipmap.gunman_walk1);
        walkAnim.addBitmap(R.mipmap.gunman_walk2);
        walkAnim.addBitmap(R.mipmap.gunman_walk3);
        animations.add(walkAnim);

        AnimationStateGameBitmap attackAnim = new AnimationStateGameBitmap(10, 2);
        attackAnim.addBitmap(R.mipmap.gunman_attack0);
        attackAnim.addBitmap(R.mipmap.gunman_attack1);
        animations.add(attackAnim);

        AnimationStateGameBitmap delayAnim = new AnimationStateGameBitmap(10, 1);
        delayAnim.addBitmap(R.mipmap.gunman_walk3);
        delayAnim.setDelayTime(3);
        animations.add(delayAnim);
    }

    @Override
    public void update() {
        super.update();
        t += MainGame.get().frameTime;
        boolean endDelay = animations.get(state).advanceFrame();
        switch (state) {
            case STATE_WALK:
                x += moveSpeed * moveDirection;
                if (t >= 2) {
                    t = 0;
                    changeState(STATE_ATTACK, true);
                }
                break;
            case STATE_ATTACK:
                if (endDelay) {
                    changeState(STATE_IDLE, true);
                    t = 0;
                }
                break;
            case STATE_IDLE:
                if (endDelay) {
                    changeState(STATE_ATTACK, true);
                    t = 0;
                }
                break;
        }
    }

    private void changeState(int state, boolean resetTimer){
        this.state = state;
        if (resetTimer)
            animations.get(state).resetTimer();
    }

    @Override
    public void getBoundingRect(RectF rect) {
        float mult = GameView.MULTIPLIER;
        float l = x - animations.get(state).getWidth() / 2 * mult;
        float t = y - animations.get(state).getHeight() / 2 * mult;
        float r = 0;
        switch (state){
            case STATE_WALK:
            case STATE_IDLE:
                r = x + animations.get(state).getWidth() / 2 * mult;
                break;
            case STATE_ATTACK:
                r = x + animations.get(state).getWidth() / 2 * mult + attackRange * mult;
                break;
        }
        float b = y + animations.get(state).getHeight() / 2 * mult;
        rect.set(
                l, t, r, b
        );
    }

    @Override
    public void draw(Canvas canvas) {
        animations.get(state).draw(canvas, x, y);
    }
}
