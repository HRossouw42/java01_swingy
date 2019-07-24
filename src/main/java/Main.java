import lombok.Getter;
import model.heroes.Fighter;
import model.items.weapons.Sword;
import model.monsters.goblins.Goblin;
import view.Startscreen;

import javax.swing.*;
@Getter
public class Main {

    public static void main(String[] args) {

        Fighter fighter = new Fighter("Fighty boi", 1);
        System.out.println(fighter.getClassDescription());
        System.out.println(fighter.getStatString());

        Goblin goblin = new Goblin(1, 0, 0, 1);
        System.out.println(goblin.getStatString());

        Sword sword = new Sword(1);
        System.out.println(sword.getItemDescription());

        return;
    }
}
