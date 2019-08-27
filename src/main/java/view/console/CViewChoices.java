package view.console;

import controller.Game;
import controller.Instance;
import lombok.Getter;

public class CViewChoices {

    //instance
    Instance instance = Instance.getInstance();

    @Getter boolean await = false;
    boolean inputConsole = true;

    boolean selectHero = true;
    boolean createNewHero = false;
    boolean needConfirm = false;
    boolean canToggle = false;
    String[] selectionLabels = null;

//     to support concurrent programming -> Threads
//     A thread is a thread of execution in a program.
//     The Java Virtual Machine allows an application to have multiple threads of execution running concurrently.
//     in this case we can run the choicepanel concurrently with other functions

    Thread choiceThread = null;


    CViewChoices() {
        //TODO Make thread
        //choiceThread = new Thread(new ChoiceRunnable(this));
        //choiceThread.start();
    }


    public void printLegend() {
        String string = "";

        if (selectHero){
            if (needConfirm) {
                string = "Type ";
                string += (createNewHero) ? "your name, hero" : "anything";
                string +=" to confirm, empty line to change";
            }
            else {
                for (int i = 0; i < selectionLabels.length; i++) {
//                    if (i != 0) {
//                        string += ", ";
//                    }
                    string += (i + 1) + " for '" + selectionLabels[i] + "'";
                }

                if (!createNewHero){
                    string +=", 0 to Create New Hero";
                }
                else if (canToggle){
                    string +=", 0 to Load a Saved Hero";
                }
            }
        }
        else if (instance.getGame() != null) {
            switch (instance.getGame().getGameState()) {
                case WaitChoiceMap:
                    string = "Move: ";
                    for (int i = 0; i < Game.directionsArray.length; i++){
//                        if (i != 0){
//                            string +=", ";
//                        }
                        //prints out direction on array
                        string += (i + 1) + " for " + Game.directionsArray[i];
                    }
                    string += ", 0 to Exit Game";
                    break;
                case WaitChoiceCombat:
                    string = "1 to fight, 2 to flee";
                    break;
                case WaitChoiceItem:
                    string = "1 to equip, 2 to discard";
                    break;
                case Dead:
                    string = "1 to retry, 2 to exit";
                    break;
                default:
                    string = "SEE CVIEWCHOICES CLASS";
                    break;
            }
        }

        if(!string.equals("")){
            System.out.println(string);
        }

    }

}
