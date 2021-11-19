package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.sql.SQLException;

/*! \class public class SettingsState extends State
    \brief Implementeaza notiunea de settings pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class SettingsState extends State
{
    /*! \fn public SettingsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public SettingsState(RefLinks refLink)
    {
            ///Apel al construcotrului clasei de baza.
        super(refLink);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update() {
        //Change difficulty
        if (refLink.GetMouseManager().getMouseX() >= 780 && refLink.GetMouseManager().getMouseX() <= 915) {
            if (refLink.GetMouseManager().getMouseY() >= 350 && refLink.GetMouseManager().getMouseY() <= 410) {
                if (refLink.GetMouseManager().isLeftPressed()) {
                    Assets.Selection.play();
                    try {
                        if (refLink.GetGame().getDataBase().getDifficulty()==3)
                            refLink.GetGame().getDataBase().updateDifficulty(1);
                        else
                            refLink.GetGame().getDataBase().updateDifficulty(refLink.GetGame().getDataBase().getDifficulty()+1);

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

        //Change sound
        if (refLink.GetMouseManager().getMouseX() >= 780 && refLink.GetMouseManager().getMouseX() <= 915){
        if (refLink.GetMouseManager().getMouseY() >= 430 && refLink.GetMouseManager().getMouseY() <= 490) {
            if (refLink.GetMouseManager().isLeftPressed()) {
                Assets.Selection.play();
                try {
                    if (refLink.GetGame().getDataBase().getMenuVolume() == 1) {
                        refLink.GetGame().getDataBase().updateMenuMusicVolume(0);
                    } else {
                        refLink.GetGame().getDataBase().updateMenuMusicVolume(1);
                    }
                    if((refLink.GetGame().getDataBase().getMenuVolume() == 0)) {
                        Assets.MenuMusic.setVolume(0);
                        Assets.Selection.setVolume(0);
                    }
                    else {
                        Assets.MenuMusic.setVolume(0.25);
                        Assets.Selection.setVolume(0.10);
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        }


        //Back button
        if (refLink.GetMouseManager().getMouseY() >= 630 && refLink.GetMouseManager().getMouseY() <= 690) {
            if (refLink.GetMouseManager().getMouseX() >= 200 && refLink.GetMouseManager().getMouseX() <= 400) {
                if (refLink.GetMouseManager().isLeftPressed()) {
                    Assets.Selection.play();
                    State.SetState(State.GetPreviousState());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.err.println("Eroare: SettingsState!");
                    }
                }
            }
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.settings,0,0, refLink.GetWidth(), refLink.GetHeight(), null);

        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 40));

        try{
            if (refLink.GetGame().getDataBase().getDifficulty()==1)
            {
                g.drawString("Easy", 600, 394);
            }

            if (refLink.GetGame().getDataBase().getDifficulty()==2)
            {
                g.drawString("Normal", 600, 394);
            }

            if (refLink.GetGame().getDataBase().getDifficulty()==3)
            {
                g.drawString("Hard", 600, 394);
            }


            if(refLink.GetGame().getDataBase().getMenuVolume() == 0)
                g.drawString("NO", 532,468);
            else
                g.drawString("YES", 532, 468);
            }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        //Change difficulty
        if (refLink.GetMouseManager().getMouseY() >= 350 && refLink.GetMouseManager().getMouseY() <= 410) {
            if (refLink.GetMouseManager().getMouseX() >= 780 && refLink.GetMouseManager().getMouseX() <= 915) {
                g.drawImage(Assets.axe, 735, 350, 50, 50, null);
            }
        }


        //Change sound
        if (refLink.GetMouseManager().getMouseY() >= 430 && refLink.GetMouseManager().getMouseY() <= 490) {
            if (refLink.GetMouseManager().getMouseX() >= 780 && refLink.GetMouseManager().getMouseX() <= 915) {
                g.drawImage(Assets.axe, 735, 423, 50, 50, null);
            }
        }

        //Back
        if (refLink.GetMouseManager().getMouseY() >= 630 && refLink.GetMouseManager().getMouseY() <= 690) {
            if (refLink.GetMouseManager().getMouseX() >= 200 && refLink.GetMouseManager().getMouseX() <= 400) {
                g.drawImage(Assets.axe, 150, 630, 50, 50, null);
            }
        }
    }
}
