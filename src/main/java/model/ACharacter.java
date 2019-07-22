package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter

public abstract class ACharacter extends ADoodad {
    //instance
    protected int level = 0;

    //in game
    protected int hp = 0;
    protected int maxHp = 0;
    protected int xp = 0;
    protected int attack = 0;
    protected int defence = 0;
    protected int speed = 0;


    protected ACharacter(String name, int level, int coordX, int coordY) {
        super(name, coordX, coordY);
        levelUp(level);
    }


    public abstract int getBaseHp();

    public abstract int getBaseXp();

    public abstract int getBaseAttack();

    public abstract int getBaseDefence();

    public abstract int getBaseSpeed();

    public abstract int getBaseGrowthHp();

    public abstract int getBaseGrowthXp();

    public abstract int getBaseGrowthAttack();

    public abstract int getBaseGrowthDefence();

    public abstract int getBaseGrowthSpeed();


    protected void levelUp(int newLevel) {
        if (newLevel <= 0)
            return;

        // reset to max hp
        hp = maxHp;
        maxHp = getBaseHp() + getBaseGrowthHp() * newLevel;

        xp = getBaseXp() + getBaseGrowthXp() * newLevel;
        attack = getBaseAttack() + getBaseGrowthAttack() * newLevel;
        defence = getBaseDefence() + getBaseGrowthDefence() * newLevel;
        speed = getBaseSpeed() + getBaseGrowthSpeed() * newLevel;

        level = newLevel;
    }

    // public version of function
    public void levelUp() {
        levelUp(level + 1);
    }

    // dice
    public int rollD20() {
        Random r = new Random();
        //TODO see if this returns 20 or 21
        return (r.nextInt(20) + 1);
    }

    public void setPosition(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public void fullHeal() {
        hp = maxHp;
    }

    public void getDamaged(int damage) {
        if (damage < 0)
            return;

        this.hp = this.hp - damage;
        // making sure you don't go into minuses when killed
        if (this.hp < 0)
            this.hp = 0;
    }

    //TODO moving

}
