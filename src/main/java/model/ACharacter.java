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
    protected int strength = 0;


    protected ACharacter(String name, int level, int coordX, int coordY) {
        super(name, coordX, coordY);
        levelUp(level);
    }


    public abstract int getBaseHp();

    public abstract int getBaseXp();

    public abstract int getBaseAttack();

    public abstract int getBaseDefence();

    public abstract int getBaseStrength();

    public abstract int getGrowthHp();

    public abstract int getGrowthXp();

    public abstract int getGrowthAttack();

    public abstract int getGrowthDefence();

    public abstract int getGrowthStrength();


    //TODO check if HP values calculated correctly
    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttack(){
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getStrength() {
        return strength;
    }

    protected void levelUp(int newLevel) {
        if (newLevel <= 0)
            return;

        // reset to max hp
        hp = maxHp;
        maxHp = getBaseHp() + getGrowthHp() * newLevel;

        xp = getBaseXp() + getGrowthXp() * newLevel;
        attack = getBaseAttack() + getGrowthAttack() * newLevel;
        defence = getBaseDefence() + getGrowthDefence() * newLevel;
        strength = getBaseStrength() + getGrowthStrength() * newLevel;

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
