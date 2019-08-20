import lombok.Getter;
import model.heroes.Fighter;
import model.items.weapons.Sword;
import model.monsters.goblins.Goblin;
import view.Startscreen;
import view.console.CHero;

import javax.swing.*;
@Getter
public class Main {

    public static void main(String[] args) {

        Fighter fighter = new Fighter("Fighty boi", 1);
        fighter.rest(fighter.getMaxHp());
        System.out.println("A champion steps into the caves");

        System.out.println(fighter.getClassDescription());
        //System.out.println(fighter.getStatString());

        Goblin goblin = new Goblin(1, 0, 0, 1);
        System.out.println("A new foe appears");
        System.out.println(goblin.getName());

        System.out.println(goblin.getStatString());

        Sword sword = new Sword(1);

        System.out.println(sword.getItemDescription());
        System.out.println("Look a shiny");
        fighter.equipWeapon(sword);

        System.out.println("\nConsole terminal thing\n");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX\n");
        CHero console = new CHero(fighter);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX");


        return;
    }
}
