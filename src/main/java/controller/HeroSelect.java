package controller;

import model.heroes.AHero;
import model.heroes.Fighter;
import view.console.CViewWindow;

import java.util.ArrayList;

public class HeroSelect {

    //ArrayList(Collection<? extends E> c)
    //This constructor is used to create a list containing the elements of the specified collection.
    static ArrayList<Class<? extends AHero>> classList;
    static {
        classList = new ArrayList<Class<? extends AHero>>();
        classList.add(Fighter.class);
    }

    Instance instance = Instance.getInstance();
    AHero hero = null;
    //TODO list of saved heroes
    ArrayList<AHero> savedCharacters;
    CViewWindow CViewWindow = null;

    boolean createNewHero = true;

    HeroSelect() {

    }

    private boolean createHero(String name){
        if (createNewHero) {
            try {
                //TODO move to array with hero classes
                hero = new Fighter(name, 1);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        else {
            //TODO get saved heroes
            System.out.println("Saved heroes here:");
        }

        return true;
    }

    public void confirmSelection(String name){
        if (!createHero(name)){
            return;
        }

        if (CViewWindow != null) {
            System.out.println("\nJourney downwards mighty " + name + "!");
        }
        instance.startGame(hero);
    }


}
