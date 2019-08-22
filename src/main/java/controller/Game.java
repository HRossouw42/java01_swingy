package controller;

import model.ACharacter;
import model.heroes.AHero;
import model.monsters.AMonster;

public class Game {

    AHero hero;
    boolean didFight = false;

    Game(AHero hero){
        this.hero = hero;
    }

    private boolean simulateCombat(AMonster monster, boolean heroStarts){
        didFight = true;
        System.out.println("A " + monster.getName() + " blocks your path!");
        boolean combatEnd = false;

        ACharacter attacker = (heroStarts) ? hero : monster;
        ACharacter defender = (heroStarts) ? monster : hero;
        ACharacter charTemp;

        int damage;

        String str;
        while (!combatEnd) {
            damage = (attacker.getAttack() - defender.getDefence());
            //TODO add critical hits
            //TODO show rolls and attack damage mitigation

            str = attacker.getName() + "attacks";

            defender.getDamaged(damage);
            if (defender.getHp() == 0){
                System.out.println(str + defender.getName() + " is slain!");
                combatEnd = true;
            }
            else {
                System.out.println(str + defender.getName() + "'s HP is " + defender.getHp());
            }

            charTemp = attacker;
            attacker = defender;
            defender = charTemp;
        }

        if(hero.getHp() == 0){
            return false;
        }

        System.out.println("You gained XP: " + monster.getXp());
        if(hero.getXp(monster.getXp())){
            System.out.println("Level up!" + hero.getStatString());
        }
        System.out.println("\n Fight Ended \n");

        return true;
    }
}
