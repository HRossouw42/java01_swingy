package controller;

import model.heroes.AHero;
import view.console.CWindow;

import java.util.Random;

public class UserInterface {
    //main running instance
    private static UserInterface instance = new UserInterface();

    //main variables
    AHero hero = null;
        //counter for how many ui need to load
    int uiToLoad = 0;
    public Random random = new Random();

    //other controllers
    private HeroSelect heroSelect = null;
    private Game game = null;

    //Ui
    CWindow cWindow = null;
    boolean onlyConsole = false;

    private UserInterface() {
    }

    public void startGame(final String [] args) {
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
        cWindow = new CWindow();
        //TODO load select screen


    }

}
