package view.console;

import model.heroes.AHero;

//main frame for the console view
public class CViewWindow {
    public CViewMain cViewMain = null;
    public CViewChoices cViewChoices = null;

    public CViewWindow () {
        System.out.println("Starting Swingy \n");
    }

    public void startChoiceWindow() {
        cViewChoices = new CViewChoices();
    }

    public void startMainWindow(AHero hero){
        cViewMain = new CViewMain(hero);
        cViewChoices.mainGameStarted();
    }

    public void dump() {
        cViewChoices.dump();
    }



}
