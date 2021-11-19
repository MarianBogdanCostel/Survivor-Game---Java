package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Hero;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;

import java.awt.*;
import java.sql.SQLException;

public class PauseState extends State {

    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PauseState(RefLinks refLink) {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        Assets.MenuMusic.setVolume(0.25);
        Assets.MenuMusic.play();
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update() {

        //Resume
        if (refLink.GetMouseManager().getMouseY() >= 300 && refLink.GetMouseManager().getMouseY() <= 350) {
            if (refLink.GetMouseManager().getMouseX() >= 515 && refLink.GetMouseManager().getMouseX() <= 765) {
                if (refLink.GetMouseManager().isLeftPressed()) {
                    Assets.Selection.play();
                    Assets.MenuMusic.stop();
                    refLink.SetMap(refLink.GetMap());
                        State.SetState(State.GetPreviousState());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.err.println("Eroare: PauseState!");
                    }
                }
            }
        }

        //Save Game
        if (refLink.GetMouseManager().getMouseY() >= 380 && refLink.GetMouseManager().getMouseY() <= 430) {
            if (refLink.GetMouseManager().getMouseX() >= 515 && refLink.GetMouseManager().getMouseX() <= 765) {
                if (refLink.GetMouseManager().isLeftPressed()) {
                    Assets.Selection.play();
                    try {
                            refLink.GetGame().getDataBase().updateAll((int)Hero.getInstance(refLink,0,0).GetX(),(int)Hero.getInstance(refLink,0,0).GetY(),Hero.getInstance(refLink,0,0).GetLife(),Hero.getInstance(refLink,0,0).GetScore(),Hero.getInstance(refLink,0,0).GetLevel());
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println("Eroare");
                    }
                }
            }
        }


        //Controls
        if (refLink.GetMouseManager().getMouseY() >= 460 && refLink.GetMouseManager().getMouseY() <= 510) {
            if (refLink.GetMouseManager().getMouseX() >= 515 && refLink.GetMouseManager().getMouseX() <= 765) {
                if (refLink.GetMouseManager().isLeftPressed()) {
                    Assets.Selection.play();
                    State.SetState(refLink.GetGame().GetControlsState());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.err.println("Eroare: PauseState!");
                    }
                }
            }
        }

        //Quit
        if (refLink.GetMouseManager().getMouseY() >= 540 && refLink.GetMouseManager().getMouseY() <= 590) {
            if (refLink.GetMouseManager().getMouseX() >= 600 && refLink.GetMouseManager().getMouseX() <= 670) {
                if (refLink.GetMouseManager().isLeftPressed()) {
                    System.exit(0);
                }
            }
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.pause, 0, 0, refLink.GetWidth(), refLink.GetHeight(), null);

        //Resume
        if (refLink.GetMouseManager().getMouseY() >= 300 && refLink.GetMouseManager().getMouseY() <= 350) {
            if (refLink.GetMouseManager().getMouseX() >= 515 && refLink.GetMouseManager().getMouseX() <= 765) {
                g.drawImage(Assets.axe, 500, 300, 50, 50, null);
            }
        }

        //Save Game
        if (refLink.GetMouseManager().getMouseY() >= 380 && refLink.GetMouseManager().getMouseY() <= 430) {
            if (refLink.GetMouseManager().getMouseX() >= 515 && refLink.GetMouseManager().getMouseX() <= 765) {
                g.drawImage(Assets.axe, 480, 380, 50, 50, null);
            }
        }

        //Controls
        if (refLink.GetMouseManager().getMouseY() >= 460 && refLink.GetMouseManager().getMouseY() <= 510) {
            if (refLink.GetMouseManager().getMouseX() >= 515 && refLink.GetMouseManager().getMouseX() <= 765) {
                g.drawImage(Assets.axe, 500, 460, 50, 50, null);
            }
        }


        //Quit
        if (refLink.GetMouseManager().getMouseY() >= 540 && refLink.GetMouseManager().getMouseY() <= 590) {
            if (refLink.GetMouseManager().getMouseX() >= 600 && refLink.GetMouseManager().getMouseX() <= 670) {
                g.drawImage(Assets.axe, 550, 540, 50, 50, null);
            }
        }
    }

}
