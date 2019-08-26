import controller.Game;
import lombok.Getter;
import model.heroes.Fighter;
import model.items.weapons.Sword;
import model.monsters.goblins.Goblin;
import model.monsters.goblins.Hobgoblin;
import view.console.CViewHero;
import view.console.CViewMain;

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
        Hobgoblin hobgoblin = new Hobgoblin(1, 0, 0,100);
        System.out.println(hobgoblin.getName());

//        System.out.println(goblin.getStatString());

        Sword sword = new Sword(1);

        System.out.println(sword.getItemDescription());
        System.out.println("Look a shiny");
        fighter.equipWeapon(sword);

        System.out.println("\nConsole terminal thing\n");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX\n");
        CViewHero console = new CViewHero(fighter);
//      CViewMain viewMain = new CViewMain(fighter);

        System.out.println("Simulate Combat");
        Game game = new Game(fighter);
        game.combatSimulate(goblin, true);
        game.combatSimulate(goblin, false);
        game.combatSimulate(hobgoblin, false);



        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX");


        return;
    }
}
