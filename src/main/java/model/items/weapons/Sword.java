package model.items.weapons;

public class Sword extends AWeapon {
    Sword(int level){
        super("Sword", 1);
    }

    Sword() {
        this(1);
    }

    @Override
    public int getBaseAttack() {
        return 5;
    }
}
