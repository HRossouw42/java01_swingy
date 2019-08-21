package controller;

import model.heroes.AHero;
import view.console.CWindow;

import java.util.Random;

public class Main {
    //main running instance
    private static Main instance = new Main();

    //main variables
    AHero hero = null;
    int uiToLoad = 0;
    public Random random = new Random();

    //other controllers
    private HeroSelect heroSelect = null;
    private Game game = null;

    //Ui
    CWindow cWindow = null;
    boolean onlyConsole = false;

    private Main() {
    }

}
