package controller;

import model.ACharacter;
import model.ADoodad;
import model.heroes.AHero;
import model.monsters.AMonster;

import java.util.ArrayList;

public class Game {

    AHero hero;
    ADoodad elem; //holds an element on the map

    int mapSize;
    int mapXp;

    // ~~ variables for gamestate ~~

    //An enum is a special "class" that represents a group of constants (unchangeable variables)
    //here used to make sure I know what game state it currently is
    public enum GameState {
        None,
        Loading,
        WaitChoiceMap,
        WaitChoiceCombat,
        WaitChoiceItem,
        Dead
    }

    GameState gameState;

    boolean didFight = false; //did you fight this turn
    // final keyword: https://www.geeksforgeeks.org/final-keyword-java/

    ArrayList<AMonster> monstersList = new ArrayList<AMonster>();
    int preCombatX;
    int preCombatY;

    public Game(AHero hero){
        this.hero = hero;
    }

    //main combat logic
    public boolean combatSimulate(AMonster monster, boolean heroStarts){
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

            str = attacker.getName() + " attacks!\n";

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

    public void combatChoice(boolean doFight) {
        if (gameState != GameState.WaitChoiceCombat)
            return;
        //TODO fight choices
        gameState = GameState.Loading;

        if (doFight){
            if(!combatSimulate((AMonster)elem, true)) {
                heroDeath();
                return;
            }
            else{
                //TODO item
                System.out.println("INSERT ITEM DROPS & EQUIPS");
            }
        }
        else {

        }
    }

    //what happens on hero death
    private void heroDeath(){
        gameState = GameState.Dead;

        System.out.println("~~~~~");
        System.out.println("YOU DIED");
        System.out.println("~~~~~");

        hero.fullHeal();
        //TODO go to death screen

    }
}
