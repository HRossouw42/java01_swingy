package view.console;

import model.heroes.AHero;

public class CHero {

    //holding place for the hero
    protected AHero hero = null;
    
    //constructor needs a hero then runs the main update method
    public CHero(AHero hero) {
        this.hero = hero;
        print();
    }

    public void print() {

        String str = hero.getName() + " Level: " + hero.getLevel() + "\n";
        str += "XP: " + hero.getXpCurrent() + " Needed: " + hero.getXpTolevel() + "\n";
        str += "\n";

        str += "HP: " + hero.getHp() + " | " + hero.getMaxHp() + "\n";
        str += "ATK: " + hero.getAttack() + "\n";
        str += "DEF: " + hero.getDefence() + "\n";
        str += "STR:" + hero.getStrength() + "\n";
        str += "\n";

        str += "Coordinates: " + hero.getCoordX() + " | " + hero.getCoordY() + "\n";
        str += "\n";

        str += "Equipped: " + "\n";
        if (hero.getWeapon() != null) {
            str += hero.getWeapon().getName() + "-> " + hero.getWeapon().getItemBonuses() + "\n";
        }
        else str +="Unarmed" + "\n";

        System.out.println(str);

    }
}
