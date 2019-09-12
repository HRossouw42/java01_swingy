package controller;

import model.heroes.AHero;
import model.heroes.Fighter;
import view.console.CViewWindow;

import java.util.ArrayList;
import java.util.Arrays;

public class HeroSelect {

    //ArrayList(Collection<? extends E> c)
    //This constructor is used to create a list containing the elements of the specified collection.
    static ArrayList<Class<? extends AHero>> classList;

    static {
        classList = new ArrayList<Class<? extends AHero>>();
        classList.add(Fighter.class);
    }

    String[] classListLegend = null;
    Instance instance = Instance.getInstance();
    AHero hero = null;
    //TODO list of saved heroes
    ArrayList<AHero> savedCharacters;
    String[] savedCharactersLegend = null;


    boolean createNewHero = true;
    int index;
    int tempIndex;

    CViewWindow cViewWindow = null;

    HeroSelect(CViewWindow cViewWindow) {
        this.cViewWindow = cViewWindow;
    }

    private boolean createHero(String name) {
        if (createNewHero) {
            try {
                //TODO move to array with hero classes
//                hero = new Fighter(name, 1);
                hero = classList.get(index).getConstructor(String.class, int.class).newInstance(name, 1);
                System.out.println("Create new hero ->" + hero);
                System.out.println(hero.getName());
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            //TODO get saved heroes
            System.out.println("Saved heroes here:");
        }


        return true;
    }

    public void confirmSelection(String name) {
        if (!createHero(name)) {
            return;
        }
//        hero = new Fighter("Testman", 1);
        System.out.println("CONFIRM SELECTION");
        if (cViewWindow != null) {
            System.out.println("\nJourney downwards mighty " + name + "!");
        }
        instance.startGameInstance(hero);
    }

    public void startHeroSelect() {
        index = 0;
        createNewHero = true;


        try {
            hero = classList.get(index).getConstructor(String.class, int.class).newInstance(classList.get(index).getSimpleName(), 1);
        } catch (Exception e) {
            System.out.println(e);
        }
//        lists all available classes
        classListLegend = new String[classList.size()];
        for (int i = 0; i < classListLegend.length; i++) {
            classListLegend[i] = classList.get(i).getSimpleName();
        }
        //TODO show saved heroes here in a legend

        if (cViewWindow != null) {
            cViewWindow.startChoiceWindow();
        }

        createNewHero = true;
        continueHeroSelect();
    }

    public void continueHeroSelect() {
//        if (createNewHero)
//            return;
        createNewHero = !createNewHero; //reset
        tempIndex = index;
        index = 0;
        String name = (createNewHero) ? classListLegend[index] : "";
        if (!createHero(name)) {
            createNewHero = !createNewHero;
            index = tempIndex;
            return;
        }

        final String[] combinedLabels = classListLegend;
        final boolean isCreateNewHero = createNewHero;
        final AHero selectedHero = hero;
        final boolean continueHeroSelect = true;

        if (cViewWindow != null) {
            System.out.println(Arrays.toString(combinedLabels));
            System.out.println(createNewHero);
            System.out.println(hero);
            System.out.println(continueHeroSelect);
            cViewWindow.cViewChoices.refreshSelection(combinedLabels, createNewHero, hero, continueHeroSelect);

        }
    }

}
