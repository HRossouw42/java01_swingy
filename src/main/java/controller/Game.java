package controller;

import lombok.Getter;
import model.ACharacter;
import model.ADoodad;
import model.heroes.AHero;
import model.monsters.AMonster;
import model.monsters.goblins.Goblin;

import java.util.ArrayList;

public class Game {

    //get main running instance
    private Instance instance = Instance.getInstance();

    AHero hero;

    // ~~ map variables ~~
    // thought process: by making everything in the game a 'doodad' object, you can hold all info in an array. Blame the warcraft 3 editor for the terminology
    ADoodad aDoodad; //holds a temp doodad on the map
    ADoodad[][] mapDoodads; //holds all other doodads

    int mapSize;
    int mapXp; //xp granted on map completion


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

    @Getter
    GameState gameState;

    public GameState getGameState() {
        return gameState;
    }

    //directions
    public static String NORTH = "NORTH";
    public static String EAST = "EAST";
    public static String SOUTH = "SOUTH";
    public static String WEST = "WEST";
    public static String[] directionsArray = new String[]{NORTH, EAST, SOUTH, WEST};

//    ~~ combat variables ~~
    ArrayList<AMonster> monstersList = new ArrayList<AMonster>(); //holds all monsters

    boolean didFight = false; //did you fight this turn
    int preCombatX;
    int preCombatY;

    public Game(AHero hero){
        this.hero = hero;
    }

    public void startGame() {
        createLevel();
    }

    public void createLevel() {
        //map size formula from PDF
        mapSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
        mapDoodads = new ADoodad[mapSize][mapSize];

        //TODO balance mapXP
        mapXp = hero.getLevel() * 2000;

        //populate map with monsters
        //TODO monsterfactory
        AMonster aMonster = new Goblin(1, 3, 3, 0);
        //TODO populate map with monsters
        mapDoodads[3][3] = aMonster;

        //setup hero on the map
        hero.setPosition(mapSize/2, mapSize / 2);
        mapDoodads[hero.getCoordX()][hero.getCoordY()] = hero;

        System.out.println("You venture further into the darkness...\n");
        endTurn();
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
            if(!combatSimulate((AMonster) aDoodad, true)) {
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

    private void moveHero(String direction) {
//        in case hero flees
        preCombatX = hero.getCoordX();
        preCombatY = hero.getCoordY();

        hero.beginMove(direction);
//        check for event on current coordinates
        aDoodad = mapDoodads[hero.getCoordX()][hero.getCoordY()];
        if (aDoodad !=null){
            if (aDoodad instanceof AMonster) {
                System.out.println("A " + aDoodad.getName() + "blocks your path!");
                //System.out.println("It's seems to be " + ((AMonster)aDoodad).getLevel());
                gameState = GameState.WaitChoiceCombat;
                // TODO instance.showFightCombat
            }
        }
    }


    private void endTurn() {
        if (didFight) {
            //TODO refresh hero
            //instance.refreshHero();
        }
        didFight = false;

        gameState = GameState.WaitChoiceMap;

        instance.refreshMap(mapDoodads);
        instance.startMapChoice();
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
