package PaooGame.States;

import PaooGame.DataBase;
import PaooGame.Graphics.Assets;
import PaooGame.Items.Enemies.Monsters.Devil;
import PaooGame.Items.Hero;
import PaooGame.Items.Item;
import PaooGame.Items.Statics.Portal;
import PaooGame.RefLinks;
import PaooGame.Maps.Map;

import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.KeyEvent;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{

    public static Map map;    /*!< Referinta catre harta curenta.*/
    protected final Hero hero;
    protected final Devil devil;
    protected final Portal portal;
    public boolean portalflag = true;
    protected boolean flagdevil = false;
    protected static boolean devilspawned=false;
    protected boolean portalspawned = false;


    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza
        super(refLink);
            ///Construieste harta jocului
        hero = Hero.getInstance(refLink,185,90);
        devil= new Devil(refLink,190,222);
        portal = new Portal(refLink,900,550);
        map = new Map(refLink,1);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update()
    {
        if(hero.getCurrentLife()<=0)
        {
            State.SetState(refLink.GetGame().GetDeathState());
        }

        if(MonstersState.levelfinished && !devilspawned) {
            map.getItemManager().addItem(devil);
            devilspawned=true;
        }
        if(devilspawned)
            devil.Update(hero);

        if(refLink.GetKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            State.SetState(refLink.GetGame().GetPauseState());
            Assets.MenuMusic.setVolume(0.25);
            Assets.MenuMusic.play();
        }

        if(devil.getCurrentLife()<=0 && !flagdevil) {
            map.getItemManager().removeItem(devil);
            Hero.AddtoScore(500);
            flagdevil = true;
        }

        if(hero.GetScore()>=150 && portalflag) {
            map.getItemManager().addItem(portal);
            portalflag = false;
        }


        //Portal
        if (hero.GetX() >= 900 && hero.GetX() <= 1000) {
            if (hero.GetY() >=510 && hero.GetY() <= 628) {
                if(refLink.GetKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !portalflag)
                {
                hero.SetX(33);
                hero.SetY(32);
                refLink.GetGame().monstersState = new MonstersState(refLink);
                State.SetState(refLink.GetGame().GetMonstersState());
                refLink.SetMap(MonstersState.monster_map);
                    Hero.getInstance(refLink,0,0).SetLevel(2);
                map.getItemManager().removeItem(portal);
                }
            }
        }
            map.Update();

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        map.Draw(g);
        g.setColor(Color.red);
        if(devilspawned)
            devil.Draw(g);
        g.setFont(new Font("Arial", Font.PLAIN, 50));
        g.drawString("Score: ",990,44);
        g.drawString(Integer.toString(hero.GetScore()),1150,44);
            //g.drawImage(Assets.mesaj, 875, 400, 400, 400, null);


    }

}
