package PaooGame.States;

import PaooGame.Graphics.Assets;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

/*! \class public class AboutState extends State
    \brief Implementeaza notiunea de credentiale (about)
 */
public class AboutState extends State
{


    /*! \fn public AboutState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public AboutState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza.
        super(refLink);

    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniu about.
     */
    @Override
    public void Update() {
        //Back button
        if (refLink.GetMouseManager().getMouseY() >= 630 && refLink.GetMouseManager().getMouseY() <= 690) {
            if (refLink.GetMouseManager().getMouseX() >= 200 && refLink.GetMouseManager().getMouseX() <= 400) {
                if (refLink.GetMouseManager().isLeftPressed()) {
                    Assets.Selection.play();
                    State.SetState(State.GetPreviousState());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.err.println("Eroare: ControlState!");
                    }
                }
            }
        }
    }


    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniu about.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.about,0,0, refLink.GetWidth(), refLink.GetHeight(), null);

        //Back
        if (refLink.GetMouseManager().getMouseY() >= 630 && refLink.GetMouseManager().getMouseY() <= 690) {
            if (refLink.GetMouseManager().getMouseX() >= 200 && refLink.GetMouseManager().getMouseX() <= 400) {
                g.drawImage(Assets.axe, 150, 630, 50, 50, null);
            }
        }
    }
}
