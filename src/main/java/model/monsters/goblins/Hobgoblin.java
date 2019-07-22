package model.monsters.goblins;

import model.monsters.AMonster;

public class Hobgoblin extends AMonster {

    public Hobgoblin(int level, int coordX, int coordY, int itemDropChance){
        super("Hobgoblin", level, coordX, coordY, itemDropChance);
    }

    @Override
    public int getGrowthAttack() {
        return super.getGrowthAttack() + 2;
    }

    @Override
    public int getBaseDefence() {
        return super.getBaseDefence() + 1;
    }

    @Override
    public int getItemDropChance() {
        return super.getItemDropChance() + 10;
    }
}
