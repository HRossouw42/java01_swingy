package model.monsters.goblins;

import model.monsters.AMonster;

public class Goblin extends AMonster {

    public Goblin(int level, int coordX, int coordY, int itemDropChance){
        super ("Goblin", level, coordX, coordY, itemDropChance);
    }

    //Stats that are different from the typical monster

    @Override
    public int getGrowthAttack() {
        return super.getGrowthAttack() + 1;
    }
}
