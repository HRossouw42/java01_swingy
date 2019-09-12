package controller;

import model.ADoodad;
import model.heroes.AHero;
import view.console.CViewWindow;

import java.util.Random;
import lombok.Getter;

/*
Instance is a class that which state persists switching of levels, game mode switches etc.
Where classes like Game or HeroSelect are being reset and any data stored in them is deleted and put to defaults again.
Any data that you want to keep beyond levels and matches, for example "what player did in specific moment so you can have consequence on other level" should be placed here.
 */

public class Instance {
    //main running instance
    //@Getter
    private static Instance instance = new Instance();

    public static Instance getInstance() {
        return instance;
    }

    //main variables
    AHero hero = null;
        //counter for how many ui need to load
    int uiToLoad = 0;
    public Random random = new Random();

    //other controllers
    @Getter
    private HeroSelect heroSelect = null;

    public HeroSelect getHeroSelect() {
        return heroSelect;
    }

    @Getter
    private Game game = null;

    public Game getGame() {
        return game;
    }

    //Ui
    CViewWindow cViewWindow = null;
    boolean onlyConsole = false;

    private Instance() {
    }



    public void setupInstance(final String [] args) {
        //only Console available at the moment
        onlyConsole = true;
        uiToLoad = 1;
        cViewWindow = new CViewWindow();
        uiChoiceReady();
    }

//    UI controllers
//    TODO feat make them work with GUI as well
    public void startGameInstance(AHero hero){
        uiToLoad = 1;
        this.hero = hero;
        AHero currentHero = hero;

        if (cViewWindow != null){
            cViewWindow.startMainWindow(hero);
            uiGameReady();
        }
    }

    private void uiGameReady() {
        uiToLoad--;
        System.out.println("UI left to load = " + uiToLoad);
        if (uiToLoad == 0) {
            game = new Game(hero);
            game.startGame();
        }
    }

    private void uiChoiceReady(){
        uiToLoad--;
        if (uiToLoad == 0) {
            heroSelect = new HeroSelect(cViewWindow);
            heroSelect.startHeroSelect();
        }
    }

    public void startCombatChoice() {
        System.out.println("Fight or Attempt to Run?(50%)");
        if (cViewWindow != null)
            cViewWindow.cViewChoices.startCombatInput();
    }

    public void stopCombatChoice(String input) {
        if (cViewWindow != null)
            cViewWindow.cViewChoices.stopFightInput(input);
    }

    public void startDeathChoice() {
        if (cViewWindow != null)
            cViewWindow.cViewChoices.startDeathInput();
    }

    public void restartGame() {
        if (cViewWindow != null)
            cViewWindow.cViewChoices.stopDeathInput();
        System.out.println("RESTARTING");
        game.startGame();
    }

    public void gameOver(){
        if (cViewWindow != null){
            cViewWindow.dump();
        }
    }

    public void startMapChoice() {
        if (cViewWindow != null)
            cViewWindow.cViewChoices.startDirectionInput();
    }

    public void stopMapChoice(int directionInt) {
        if (cViewWindow != null)
            cViewWindow.cViewChoices.stopDirectionInput(directionInt);
    }

//    UI controllers to reprint the map & hero when something changes
    public void refreshMap(ADoodad[][] aDoodads) {
        //TODO refreshes gui
    }

    public void refreshHero() {
        if (cViewWindow != null)
            cViewWindow.cViewMain.CViewHero.print();
    }

    //MAIN
    public static void  main(String[] args) {
        Instance.getInstance().setupInstance(args);
    }

}
