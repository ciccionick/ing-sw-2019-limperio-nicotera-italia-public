package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.ViewEvent;

public class ControllerForPowerUpCard extends Controller {

    public ControllerForPowerUpCard(Game game) {
        super(game);
    }

    public void check(ViewEvent message) /*throws IllegalChooseByUser */ {
        {
            switch(message.getClass().getName()){
                case "DiscardPowerUpCard":
                   if(checkForDiscard(message)){
                       /*chiamata a metodi del model*/
                   }
                   else
                       /*throw new IllegalChooseByUser()*/;
                case "DrawPowerUpCard":
                    if(checkForDraw(message)){
                        /*chiamata a metodi del model*/
                    }
                    else
                        /*throw new IllegalChooseByUser()*/;
                case "UseNewton":
                    if(checkForUseNewton(message)){
                        /*chiamata a metodi del model*/
                    }
                    else
                        /*throw new IllegalChooseByUser()*/;
                case "UseTagBackGranade":
                    if(checkForUseTagBackGranade(message)){
                        /*chiamata a metodi del model*/
                    }
                    else
                        /*throw new IllegalChooseByUser()*/;
                case "UseTeleporter":
                    if(checkForUseTeleporter(message)){
                        /*chiamata a metodi del model*/
                    }
                    else
                        /*throw new IllegalChooseByUser()*/;

            }
        }
    }

    private boolean checkForDiscard(ViewEvent message){ return true;}

    private boolean checkForDraw (ViewEvent message) {return true;}

    private boolean checkForUseNewton (ViewEvent message) { return true;}

    private boolean checkForUseTagBackGranade (ViewEvent message) {return true;}

    private boolean checkForUseTeleporter (ViewEvent message) {return true;}

    @Override
    public void update(ViewEvent message) /*throws IllegalChooseByUser */ {
        this.check(message);
        }
        /*else
            throw new IllegalChooseByUser();*/
    }


