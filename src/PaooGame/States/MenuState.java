package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Audio;
import PaooGame.Items.Enemies.Monsters.Devil;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State {


    private boolean soundflag = false;
    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public MenuState(RefLinks refLink) {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        Assets.MenuMusic.setVolume(0.25);
        Assets.Selection.setVolume(0.10);
        Assets.MenuMusic.play();
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update() {

        //Start Game
        if (refLink.GetMouseManager().getMouseY() >= 300 && refLink.GetMouseManager().getMouseY() <= 350) {
            if (refLink.GetMouseManager().getMouseX() >= 515 && refLink.GetMouseManager().getMouseX() <= 765) {
                if (refLink.GetMouseManager().isLeftPressed()) {
                    Assets.Selection.play();
                    Assets.MenuMusic.stop();
                    refLink.GetGame().playState = new PlayState(refLink);
                    State.SetState(refLink.GetGame().GetPlayState());
                    refLink.SetMap(PlayState.map);
                    Hero.getInstance(refLink,0,0).SetLevel(1);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.err.println("Eroare: MenuState!");
                    }
                }
            }
        }


        //Load Game
        if (refLink.GetMouseManager().getMouseY() >= 380 && refLink.GetMouseManager().getMouseY() <= 430) {
            if (refLink.GetMouseManager().getMouseX() >= 515 && refLink.GetMouseManager().getMouseX() <= 765) {
                if (refLink.GetMouseManager().isLeftPressed()) {
                    Assets.MenuMusic.stop();
                    Assets.Selection.play();
                    try {
                        Hero.getInstance(refLink,0,0).SetX(refLink.GetGame().getDataBase().getPlayerX());
                        Hero.getInstance(refLink,0,0).SetY(refLink.GetGame().getDataBase().getPlayerY());
                        Hero.getInstance(refLink,0,0).SetLife(refLink.GetGame().getDataBase().getPlayerLife());
                        Hero.getInstance(refLink,0,0).SetScore(refLink.GetGame().getDataBase().getScore());

                        if(refLink.GetGame().getDataBase().getLevel()==1)
                        {
                            refLink.GetGame().playState = new PlayState(refLink);
                            State.SetState(refLink.GetGame().GetPlayState());
                            refLink.SetMap(PlayState.map);
                        }
                        if(refLink.GetGame().getDataBase().getLevel()==2)
                        {
                            refLink.GetGame().monstersState = new MonstersState(refLink);
                            State.SetState(refLink.GetGame().GetMonstersState());
                            refLink.SetMap(MonstersState.monster_map);
                        }
                        if(refLink.GetGame().getDataBase().getLevel()==3)
                        {
                            refLink.GetGame().playState = new PlayState(refLink);
                            State.SetState(refLink.GetGame().GetPlayState());
                            refLink.SetMap(PlayState.map);
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


        //Settings
        if (refLink.GetMouseManager().getMouseY() >= 460 && refLink.GetMouseManager().getMouseY() <= 510) {
            if (refLink.GetMouseManager().getMouseX() >= 515 && refLink.GetMouseManager().getMouseX() <= 765) {
                if (refLink.GetMouseManager().isLeftPressed()) {
                    Assets.Selection.play();
                    State.SetState(refLink.GetGame().GetSettingsState());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.err.println("Eroare: MenuState!");
                    }
                }
            }

        }


        //Controls
        if (refLink.GetMouseManager().getMouseY() >= 630 && refLink.GetMouseManager().getMouseY() <= 680) {
            if (refLink.GetMouseManager().getMouseX() >= 210 && refLink.GetMouseManager().getMouseX() <= 310) {
                if (refLink.GetMouseManager().isLeftPressed()) {
                    Assets.Selection.play();
                    State.SetState(refLink.GetGame().GetControlsState());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.err.println("Eroare: MenuState!");
                    }
                }
            }
        }

        //About
        if (refLink.GetMouseManager().getMouseY() >= 630 && refLink.GetMouseManager().getMouseY() <= 690) {
            if (refLink.GetMouseManager().getMouseX() >= 980 && refLink.GetMouseManager().getMouseX() <= 1080) {
                if (refLink.GetMouseManager().isLeftPressed()) {
                    Assets.Selection.play();
                    State.SetState(refLink.GetGame().GetAboutState());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.err.println("Eroare: MenuState!");
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
        g.drawImage(Assets.menu, 0, 0, refLink.GetWidth(), refLink.GetHeight(), null);

        //Start Game
        if (refLink.GetMouseManager().getMouseY() >= 300 && refLink.GetMouseManager().getMouseY() <= 350) {
            if (refLink.GetMouseManager().getMouseX() >= 515 && refLink.GetMouseManager().getMouseX() <= 765) {
                g.drawImage(Assets.axe, 465, 300, 50, 50, null);
            }
        }

        //Load Game
        if (refLink.GetMouseManager().getMouseY() >= 380 && refLink.GetMouseManager().getMouseY() <= 430) {
            if (refLink.GetMouseManager().getMouseX() >= 515 && refLink.GetMouseManager().getMouseX() <= 765) {
                g.drawImage(Assets.axe, 477, 380, 50, 50, null);
            }
        }

        //Settings
        if (refLink.GetMouseManager().getMouseY() >= 460 && refLink.GetMouseManager().getMouseY() <= 510) {
            if (refLink.GetMouseManager().getMouseX() >= 515 && refLink.GetMouseManager().getMouseX() <= 765) {
                g.drawImage(Assets.axe, 505, 460, 50, 50, null);
            }
        }

        //Controls
        if (refLink.GetMouseManager().getMouseY() >= 630 && refLink.GetMouseManager().getMouseY() <= 680) {
            if (refLink.GetMouseManager().getMouseX() >= 210 && refLink.GetMouseManager().getMouseX() <= 310) {
                g.drawImage(Assets.axe, 160, 630, 50, 50, null);
            }
        }

        //About
        if (refLink.GetMouseManager().getMouseY() >= 630 && refLink.GetMouseManager().getMouseY() <= 690) {
            if (refLink.GetMouseManager().getMouseX() >= 980 && refLink.GetMouseManager().getMouseX() <= 1080) {
                g.drawImage(Assets.axe, 930, 630, 50, 50, null);
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
