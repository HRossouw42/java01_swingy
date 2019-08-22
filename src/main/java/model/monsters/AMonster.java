package model.monsters;

import lombok.Getter;
import model.ACharacter;
import model.ADoodad;

@Getter
//baseline monster class
public abstract class AMonster extends ACharacter {

    private ADoodad doodad;
    private int itemDropChance = 0;

    protected AMonster(String name, int level, int coordX, int coordY, int itemDropChance) {
        super(name, level, coordX, coordY);
        this.itemDropChance = itemDropChance;
    }

    //monster stats
    //TODO balance monsters

    @Override
    public int getBaseHp() {
        return 5;
    }

    @Override
    public int getBaseXp() {
        return 100;
    }

    @Override
    public int getBaseAttack() {
        return 1;
    }

    @Override
    public int getBaseDefence() {
        return 1;
    }

    @Override
    public int getBaseStrength() {
        return 1;
    }

    @Override
    public int getGrowthHp() {
        return 1;
    }

    @Override
    public int getGrowthXp() {
        return 50;
    }

    @Override
    public int getGrowthAttack() {
        return 1;
    }

    @Override
    public int getGrowthDefence() {
        return 1;
    }

    @Override
    public int getGrowthStrength() {
        return 1;
    }

    public int getItemDropChance() {
        return 50;
    }

    public String getStatString() {
        String str;
        str = "MaxHP: " + maxHp;
        str = str + "Attack: " + attack;
        str = str + "Defence: " + defence;
        str = str + "Strength: " + strength;
        return str;
    }

    //TODO balance XP
    public int getXp(){
        int xp = getBaseXp() + getGrowthXp() * level;
        return xp;
    }

    @Override
    public void setPosition(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

}
