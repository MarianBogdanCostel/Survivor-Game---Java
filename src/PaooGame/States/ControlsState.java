package PaooGame.States;

import PaooGame.Graphics.Assets;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.beans.IntrospectionException;

public class ControlsState extends State{
    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public ControlsState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        //refLink.GetMouseManager().setUIManager(null);
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
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
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.controls,0,0, refLink.GetWidth(), refLink.GetHeight(), null);

        if (refLink.GetMouseManager().getMouseY() >= 630 && refLink.GetMouseManager().getMouseY() <= 690) {
            if (refLink.GetMouseManager().getMouseX() >= 200 && refLink.GetMouseManager().getMouseX() <= 400) {
                g.drawImage(Assets.axe, 150, 630, 50, 50, null);
            }
        }
    }
}
