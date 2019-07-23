package model.items;

import lombok.Getter;
import lombok.Setter;
import model.heroes.AHero;

@Getter
@Setter
public abstract class AItem {
    long id = 0;

    long getUniqueId() {
        return id++;
    }

    AHero inventoryTarget;
    protected int level = 0;

    protected String name = "";
    protected int hp = 0;
    protected int attack = 0;
    protected int defence = 0;

    protected AItem(String name, int level) {
        this.name = name;
        this.level = level;

        hp = getBaseHp() + getGrowthHp() * level;
        attack = getBaseAttack() + getGrowthAttack() * level;
        defence = getBaseDefence() + getGrowthDefence() * level;

    }

    //String description getters
    public abstract String getItemDescription();

    public abstract String getItemBonuses();

    //item basic stat getters

    public int getBaseHp() {
        return 0;
    }

    public int getBaseAttack() {
        return 0;
    }

    public int getBaseDefence() {
        return 0;
    }

    public int getGrowthHp() {
        return 0;
    }

    public int getGrowthAttack() {
        return 0;
    }

    public int getGrowthDefence() {
        return 0;
    }

}
