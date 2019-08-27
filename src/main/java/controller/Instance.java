package controller;

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
    @Getter private HeroSelect heroSelect = null;
    @Getter private Game game = null;

    public Game getGame() {
        return game;
    }

    //Ui
    CViewWindow CViewWindow = null;
    boolean onlyConsole = false;

    private Instance() {
    }

    public void initialiseGame(final String [] args) {
//        for (int i = 0; i < args.length; i++) {
//
//            if (args[i].equals("console")) {
//                //error check if console mode has been activated already
//                if (onlyConsole = true) {
//                    System.out.println("You're already in console mode!");
//                    return;
//                } else {
//                    onlyConsole = true;
//                }
//            } else {
//                System.out.println("Unknown argument: (" + args[i] + " ), only option available is 'console'");
//                return;
//            }
//        }

        //TODO only Console available at the moment
        uiToLoad = 1;
        CViewWindow = new CViewWindow();
        //TODO load select screen
    }

    public void startGame(AHero hero){
        uiToLoad = 1;
        this.hero = hero;

        if (CViewWindow != null){
            //CViewWindow.startMainPanel(hero);
            //uiReadytoPlay
        }
    }

}
