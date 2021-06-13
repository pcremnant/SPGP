package kr.ac.kpu.game.s2016180021.defence.framework.object;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016180021.defence.framework.bitmap.AnimationStateGameBitmap;
import kr.ac.kpu.game.s2016180021.defence.framework.iface.BoxCollidable;
import kr.ac.kpu.game.s2016180021.defence.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180021.defence.framework.view.GameView;

public class UnitObject implements GameObject, BoxCollidable {
    protected static final int STATE_WALK = 0;
    protected static final int STATE_ATTACK = 1;
    protected static final int STATE_DELAY = 2;

    protected int hp;
    protected int maxHp;
    protected float x;
    protected float y;
    protected int state;
    protected float moveSpeed;
    protected int moveDirection;
    protected float attackRange;
    protected int attackDamage;
    protected float attackDelay;
    protected boolean isPlayer;
    protected boolean isDead;

    protected ArrayList<AnimationStateGameBitmap> animations = new ArrayList<>();

    @Override
    public void update() {
        // boolean endDelay = animations.get(state).advanceFrame();
        animations.get(state).advanceFrame();
        switch (state) {
            case STATE_WALK:
                x += moveSpeed * moveDirection;
                // if (t >= 2) {
                //     t = 0;
                //    changeState(STATE_ATTACK, true);
                // }
                break;
            case STATE_ATTACK:
                if (animations.get(state).isEndDelay()) {
                    changeState(STATE_DELAY, true);
                }
                break;
            case STATE_DELAY:
                if (animations.get(state).isEndDelay()) {
                    changeState(STATE_WALK, true);
                }
                break;
        }
    }

    @Override
    public void getBoundingRect(RectF rect) {
        float mult = GameView.MULTIPLIER;
        float l = x - animations.get(state).getWidth() / 2 * mult;
        float t = y - animations.get(state).getHeight() / 2 * mult;
        float r = 0;
        switch (state){
            case STATE_WALK:
            case STATE_DELAY:
                r = x + animations.get(state).getWidth() / 2 * mult;
                break;
            case STATE_ATTACK:
                float att = attackRange;
                if (!isPlayer)
                    att *= -1;
                r = x + animations.get(state).getWidth() / 2 * mult + att;
                break;
        }
        float b = y + animations.get(state).getHeight() / 2 * mult;
        rect.set(
                l, t, r, b
        );
    }

    @Override
    public void draw(Canvas canvas) {
        animations.get(state).draw(canvas, x,  y);
    }

    public boolean isPlayerUnit(){
        return isPlayer;
    }

    public void getDamage(int damage){
        hp -= damage;
        if (hp <= 0){
            hp = 0;
            isDead = true;
        }
    }

    protected void changeState(int state, boolean resetTimer){
        // this.animations.get(state).resetTimer();
        if (resetTimer)
            animations.get(state).resetTimer();
        this.state = state;
        // if (resetTimer)
            // animations.get(state).resetTimer();
    }

    public boolean checkInAttackRange(UnitObject other){
        if (state != STATE_WALK)
            return false;

        if (other.isPlayerUnit() != this.isPlayer){
            if (Math.abs(this.x - other.x) < this.attackRange) {
                other.getDamage(this.attackDamage);
                changeState(STATE_ATTACK, true);
                return true;
            }
        }

        return false;
    }
}
