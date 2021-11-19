package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Enemies.Monsters.Beast;
import PaooGame.Items.Enemies.Monsters.CarnivorTree;
import PaooGame.Items.Enemies.Monsters.Devil;
import PaooGame.Items.Enemies.Monsters.Dragon;
import PaooGame.Items.Hero;
import PaooGame.Items.Statics.Portal;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MonstersState extends State{

    protected Dragon dragon;
    protected CarnivorTree carnivorTree;
    protected Beast beast;
    protected static int finalscore;
    protected boolean flagdragon = false;
    protected boolean flagtree = false;
    protected boolean flagbeast = false;
    private boolean portalflag = false;
    protected static boolean levelfinished=false;
    private Hero hero;

    protected static Map monster_map;
    public MonstersState(RefLinks refLink) {
        super(refLink);
        ///Construieste harta jocului
        monster_map = new Map(refLink,2);
        dragon = new Dragon(refLink,240,550);
        carnivorTree = new CarnivorTree(refLink,648,610);
        beast = new Beast(refLink,1000,350);
        monster_map.getItemManager().addItem(dragon);
        monster_map.getItemManager().addItem(beast);
        monster_map.getItemManager().addItem(carnivorTree);
        hero = Hero.getInstance(refLink,0,0);
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
        if(dragon.getCurrentLife()<=0 && !flagdragon) {
            monster_map.getItemManager().removeItem(dragon);
            Hero.AddtoScore(500);
            flagdragon = true;
        }
        if(carnivorTree.getCurrentLife()<=0 && !flagtree) {
            monster_map.getItemManager().removeItem(carnivorTree);
            Hero.AddtoScore(100);
            flagtree = true;
        }
        if(beast.getCurrentLife()<=0 && !flagbeast) {
        monster_map.getItemManager().removeItem(beast);
        Hero.AddtoScore(200);
        flagbeast = true;
    }

        dragon.Update(hero);
        beast.Update(hero);
        carnivorTree.Update(hero);
        if(refLink.GetKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            State.SetState(refLink.GetGame().GetPauseState());
            Assets.MenuMusic.setVolume(0.25);
            Assets.MenuMusic.play();
        }

        if(flagdragon && flagbeast && flagtree)
            levelfinished = true;

        //Portal
        if (hero.GetX() >= 113 && hero.GetX() <= 211) {
            if (hero.GetY() >= 520 && hero.GetY() <= 635) {
                if (refLink.GetKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
                    State.SetState(refLink.GetGame().GetPlayState());
                    hero.SetX(185);
                    hero.SetY(90);
                    refLink.SetMap(PlayState.map);
                    Hero.getInstance(refLink,0,0).SetLevel(3);
                }
            }
        }
        monster_map.Update();

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        monster_map.Draw(g);
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.PLAIN, 50));
        g.drawString("Score: ",990,44);
        g.drawString(Integer.toString(hero.GetScore()),1150,44);
        finalscore = hero.GetScore();
        if(dragon.getCurrentLife()>0)
            dragon.Draw(g);
        if(beast.getCurrentLife()>0)
            beast.Draw(g);
        if(carnivorTree.getCurrentLife()>0)
            carnivorTree.Draw(g);
        if(levelfinished && !portalflag)
        {
            monster_map.getItemManager().addItem(new Portal(refLink, 90, 510));
            portalflag = true;
        }
    }

}
