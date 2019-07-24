package model.items.weapons;

import model.items.AItem;

public class AWeapon extends AItem {

    AWeapon (String name, int level){
        super(name, level);
    }

    AWeapon() {
        this("Weapon", 1);
    }

    public String getItemDescription() {
        return (name + " lv: " + level + "(Bonus Attack: " + attack + ")");
    }

    public String getItemBonuses() {
        return ("Attack +" + attack);
    }

    @Override
    public int getGrowthAttack() {
        return 1;
    }

    //TODO have weapons show +damage not attack
}
