package view.console;

import model.heroes.AHero;

public class CViewMain {
    public CViewHero CViewHero = null;
    public CViewPrint CViewPrint = null;

    public CViewMain(AHero hero){
        CViewHero = new CViewHero(hero);
        CViewPrint = new CViewPrint();
    }
}
