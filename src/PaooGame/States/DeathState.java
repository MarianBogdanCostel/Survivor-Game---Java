package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;

import java.awt.*;

public class DeathState extends State{
    public DeathState(RefLinks refLink) {
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

        //Retry
        if (refLink.GetMouseManager().getMouseY() >= 450 && refLink.GetMouseManager().getMouseY() <= 510) {
            if (refLink.GetMouseManager().getMouseX() >= 575 && refLink.GetMouseManager().getMouseX() <= 705) {
                if (refLink.GetMouseManager().isLeftPressed()) {
                    Assets.Selection.play();
                    Assets.MenuMusic.stop();
                    refLink.GetGame().playState = new PlayState(refLink);
                    Hero.getInstance(refLink, 0,0).setCurrentLife(100);
                    Hero.getInstance(refLink,0,0).SetX(185);
                    Hero.getInstance(refLink,0,0).SetY(90);
                    Hero.getInstance(refLink, 0,0).SetScore(0);
                    State.SetState(refLink.GetGame().GetPlayState());
                    refLink.SetMap(PlayState.map);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.err.println("Eroare: DeathState!");
                    }
                }
            }
        }


        //Main Menu
        if (refLink.GetMouseManager().getMouseY() >= 520 && refLink.GetMouseManager().getMouseY() <= 580) {
            if (refLink.GetMouseManager().getMouseX() >= 530 && refLink.GetMouseManager().getMouseX() <= 750) {
                if (refLink.GetMouseManager().isLeftPressed()) {
                    Assets.Selection.play();
                    State.SetState(refLink.GetGame().GetMenuState());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.err.println("Eroare: DeathState!");
                    }
                }
            }
        }

        //Quit
        if (refLink.GetMouseManager().getMouseY() >= 590 && refLink.GetMouseManager().getMouseY() <= 650) {
            if (refLink.GetMouseManager().getMouseX() >= 590 && refLink.GetMouseManager().getMouseX() <= 690) {
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
        g.drawImage(Assets.death, 0, 0, refLink.GetWidth(), refLink.GetHeight(), null);
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.PLAIN, 60));
        g.drawString(String.valueOf(MonstersState.finalscore),590,410);

        //Retry
        if (refLink.GetMouseManager().getMouseY() >= 450 && refLink.GetMouseManager().getMouseY() <= 510) {
            if (refLink.GetMouseManager().getMouseX() >= 575 && refLink.GetMouseManager().getMouseX() <= 705) {
                g.drawImage(Assets.axe, 530, 450, 50, 50, null);
            }
        }


        //Main Menu
        if (refLink.GetMouseManager().getMouseY() >= 520 && refLink.GetMouseManager().getMouseY() <= 580) {
            if (refLink.GetMouseManager().getMouseX() >= 530 && refLink.GetMouseManager().getMouseX() <= 750) {
                g.drawImage(Assets.axe, 485, 520, 50, 50, null);
            }
        }


        //Quit
        if (refLink.GetMouseManager().getMouseY() >= 590 && refLink.GetMouseManager().getMouseY() <= 650) {
            if (refLink.GetMouseManager().getMouseX() >= 590 && refLink.GetMouseManager().getMouseX() <= 690) {
                g.drawImage(Assets.axe, 555, 590, 50, 50, null);

            }
        }
    }
}
