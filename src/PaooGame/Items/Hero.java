package PaooGame.Items;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import PaooGame.Graphics.Animation;
import PaooGame.Inventory.Inventory;
import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.States.State;
import PaooGame.Tiles.Tile;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */
public class Hero extends Character
{
    //Animatie
    private Animation animDown, animUp, animLeft, animRight, lastAnim;
    private Animation atackD, atackU, atackL, atackR, anim_die;
    private int direction = 0;      //Voi folosi acest int pentru a retine directia in care se uita player-ul
    protected static int level;     //Retin numarul hartii
    //0 - JOS
    //1 - SUS
    //2 - STANGA
    //3 - DREAPTA

    protected int damage;
    protected static int score;

    //Inventory
    private Inventory inventory;

    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    public boolean attack;
    private static Hero instance;

    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    private Hero(RefLinks refLink, float x, float y)
    {
            ///Apel al constructorului clasei de baza
        super(refLink, x, y,Character.DEFAULT_CREATURE_WIDTH*2,Character.DEFAULT_CREATURE_HEIGHT*2);
            ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 18;
        normalBounds.y = 46;
        normalBounds.width = 30;
        normalBounds.height = 22;

        score = 0;

        //Animatii
        animDown = new Animation(100, Assets.player_down);
        animUp = new Animation(100, Assets.player_up);
        animLeft = new Animation(100, Assets.player_left);
        animRight = new Animation(100, Assets.player_right);
        lastAnim = new Animation(100,Assets.player_down);

        atackD = new Animation(100, Assets.atack_down);
        atackU = new Animation(100, Assets.atack_up);
        atackL = new Animation(100, Assets.atack_left);
        atackR = new Animation(100, Assets.atack_right);
        anim_die = new Animation(100, Assets.player_die);

        inventory = new Inventory(refLink);

    }

    //Singleton
    public static Hero getInstance(RefLinks refLinks, float x, float y){
        if(instance==null)
        {
            instance = new Hero(refLinks,x,y);
        }
        return instance;
    }
    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update()
    {

        //Animatii
        if(!Dead()) {
            animDown.Update();
            animUp.Update();
            animLeft.Update();
            animRight.Update();
            lastAnim.Update();
        }
        if(Dead())
            anim_die.Update();

        if(Assets.secondElapsed())
            attack = false;
        atackD.Update();
        atackU.Update();
        atackL.Update();
        atackR.Update();

        //Verifica daca a fost apasata o tasta
        GetInput();

        //Actualizeaza pozitia
        Move();

        //Atack
        checkAtacks();

        //centram camera pe caracter
     //   refLink.getGameCamera().centerOnCharacter(this);
        //Inventory
        inventory.Update();

    }

    private void checkAtacks()
    {
        if(inventory.isActive())
            return;

        Rectangle col = getCollisionBounds(0,0);


        if(refLink.GetKeyManager().attack_button && direction == 1)
        {
            attackbounds.x = col.x + col.width / 2 - 16;
            attackbounds.y = col.y - 32;
            attackbounds.width = 32;
            attackbounds.height = 48;

        }
        else if(refLink.GetKeyManager().attack_button && direction == 0)
        {
            attackbounds.x = col.x + col.width / 2 - 16;
            attackbounds.y = col.y - col.height/2;
            attackbounds.width = 32;
            attackbounds.height = 48;
        }
        else if(refLink.GetKeyManager().attack_button && direction == 2)
        {
            attackbounds.x = col.x - 32;
            attackbounds.y = col.y - col.height-16;
            attackbounds.height = 60;
            attackbounds.width = 32;
        }
        else if(refLink.GetKeyManager().attack_button && direction == 3)
        {
            attackbounds.x = col.x + col.width;
            attackbounds.y = col.y - col.height-16;
            attackbounds.height = 60;
            attackbounds.width = 32;
        }
        else{
            return;
        }


        if((inventory.getWood()>=5 && inventory.getRocks()>=5) && (inventory.getWood()<10 && inventory.getRocks()<10))
            damage = 20;
        else if(inventory.getWood()>=10 && inventory.getRocks()>=10 && (inventory.getWood()<20 && inventory.getRocks()<20))
            damage = 30;
        else if(inventory.getWood()>=20 && inventory.getRocks()>=20 && (inventory.getWood()<40 && inventory.getRocks()<40))
            damage = 40;
        else if(inventory.getWood()>=40 && inventory.getRocks()>=40)
            damage = 50;
        else
            damage = 10;

        for(Item e: refLink.GetMap().getItemManager().getItems()){
            if(e.equals(this))          //Verificam ca entitatea sa nu fie caracterul
                continue;
            if(e.getCollisionBounds(0,0).intersects(attackbounds) && Assets.attackTimeElapsed()){
                e.hurt(damage);
                return;
            }
        }
    }

    @Override
    public void die() {
        System.out.println("Ai murit");
        Dead();
    }
    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput() {
        ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;

        if (inventory.isActive())
            return;

        if (!attack) {
            ///Verificare apasare tasta "sus"
            if (refLink.GetKeyManager().up) {
                yMove = -speed;
            }
            ///Verificare apasare tasta "jos"
            if (refLink.GetKeyManager().down) {
                yMove = speed;
            }
            ///Verificare apasare tasta "left"
            if (refLink.GetKeyManager().left) {
                xMove = -speed;
            }
            ///Verificare apasare tasta "dreapta"
            if (refLink.GetKeyManager().right) {
                xMove = speed;
            }
            if (refLink.GetKeyManager().attack_button) {
                xMove = 0;
                yMove = 0;
                attack = true;
                atackU.IndexZero();
                atackD.IndexZero();
                atackL.IndexZero();
                atackR.IndexZero();
            }
        }
    }


    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {

        if(!this.Dead())
        {
            //Health bar
            g.setColor(Color.gray);
            g.fillRect((int) x,(int)  y, 64, 10);

            g.setColor(Color.green);
            g.fillRect((int) x,(int)  y, (64*current_life)/life, 10);

            g.setColor(Color.white);
            g.drawRect((int) x,(int)  y, 64, 10);
        if(attack)
        {
            if(direction == 0)
            {
                g.drawImage(atackD.getCurrentFrame(),(int) x, (int) y, width, height, null);
            }
            if(direction == 1)
            {
                g.drawImage(atackU.getCurrentFrame(),(int) x, (int) y, width, height, null);
            }
            if(direction == 2)
            {
                g.drawImage(atackL.getCurrentFrame(),(int) x, (int) y, width, height, null);
            }
            if(direction == 3)
            {
                g.drawImage(atackR.getCurrentFrame(),(int) x, (int) y, width, height, null);
            }
            if(Assets.attackTimeElapsed()){
                attack = false;
            }
        }
        else {
            g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
        }  ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);

        //g.setColor(Color.green);
        //g.drawRect((int)(attackbounds.x), (int)(attackbounds.y), attackbounds.width, attackbounds.height);
        }
    }


    public void postDraw(Graphics g)
    {
        inventory.Draw(g);
    }


    private BufferedImage getCurrentAnimationFrame(){
            if (xMove < 0) {
                lastAnim = new Animation(100,Assets.player_left);
                direction = 2;          //Left
                return animLeft.getCurrentFrame();
            } else if (xMove > 0) {
                lastAnim = new Animation(100,Assets.player_right);
                direction = 3;          //Right
                return animRight.getCurrentFrame();
            } else if (yMove < 0) {
                lastAnim = new Animation(100,Assets.player_up);
                direction = 1;          //Up
                return animUp.getCurrentFrame();
            } else if (yMove > 0) {
                lastAnim = new Animation(100,Assets.player_down);
                direction = 0;          //Down
                return animDown.getCurrentFrame();
            } else
                return lastAnim.getFirstFrame();
        }


    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public boolean Dead(){
        if(current_life<=0)
        {
            return true;
        }
        else
            return false;
    }

    public int GetScore()
    {
        return score;
    }

    public static void AddtoScore(int val)
    {
        score += val;
    }

    public void SetScore(int val)
    {
        score = val;
    }

    public void AddtoLife(int val){
        current_life += val;
    }

    public int getDamage()
    {
        return damage;
    }
    public void SetLevel(int val)
    {
        level = val;
    }

    public int GetLevel(){
        return level;
    }


}
