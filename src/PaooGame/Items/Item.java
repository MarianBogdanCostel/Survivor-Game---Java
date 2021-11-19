package PaooGame.Items;

//O clasa StaticItems este o clasa Item care nu se misca
//Voi folosi aceasta clasa pentru copaci, pietre, plante

import PaooGame.Graphics.Assets;
import PaooGame.Items.Item;
import PaooGame.RefLinks;

import java.awt.*;

public abstract class Item {
    ///asa cum s-a mai precizat, coordonatele x si y sunt de tip float pentru a se elimina erorile de rotunjire
    ///ce pot sa apara in urma calculelor, urmand a se converti la intreg doar in momentul desenarii.
    protected float x;                  /*!< Pozitia pe axa X a "tablei" de joc a imaginii entitatii.*/
    protected float y;                  /*!< Pozitia pe axa Y a "tablei" de joc a imaginii entitatii.*/
    protected int width;                /*!< Latimea imaginii entitatii.*/
    protected int height;               /*!< Inaltimea imaginii entitatii.*/
    protected int life;                 /*!< Retine viata entitatii.*/
    protected  int current_life;        /*!< Retine viata curenta a entitatii.*/
    public static final int DEFAULT_LIFE            = 100;   /*!< Valoarea implicita a vietii unui caracter.*/
    public static int CURRENT_LIFE                  = 100;
    protected Rectangle bounds;         /*!< Dreptunghiul curent de coliziune.*/
    protected Rectangle attackbounds;
    protected Rectangle normalBounds;   /*!< Dreptunghiul de coliziune aferent starii obisnuite(spatiul ocupat de entitate in mod normal), poate fi mai mic sau mai mare decat dimensiunea imaginii sale.*/
    protected Rectangle attackBounds;   /*!< Dreptunghiul de coliziune aferent starii de atac.*/
    protected boolean active = true;    /*!< Toate itemele care sunt active vor fi pe harta, iar atunci cand active devine false iteme-ul respectiv va disparea de pe harta*/
    protected RefLinks refLink;         /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/

    /*! \fn public Item(RefLinks refLink, float x, float y, int width, int height)
        \brief Constructor de initializare al clasei

        \param  reflink Referinte "shortcut" catre alte referinte
        \param  x   Pozitia pe axa X a "tablei" de joc a imaginii entitatii.
        \param  y   Pozitia pe axa Y a "tablei" de joc a imaginii entitatii.
        \param  width   Latimea imaginii entitatii.
        \param  height  Inaltimea imaginii entitatii.
     */
    public Item(RefLinks refLink, float x, float y, int width, int height)
    {
        this.refLink = refLink; /*!< Retine the "shortcut".*/
        this.x = x;             /*!< Retine coordonata pe axa X.*/
        this.y = y;             /*!< Retine coordonata pe axa X.*/
        this.width = width;     /*!< Retine latimea imaginii.*/
        this.height = height;   /*!< Retine inaltimea imaginii.*/
        life    = DEFAULT_LIFE;
        current_life = life;

        ///Creaza dreptunghi de coliziune pentru modul normal, aici a fost stabilit la dimensiunea imaginii dar poate fi orice alta dimensiune
        normalBounds = new Rectangle(0, 0, width, height);
        ///Creaza dreptunghi de coliziune pentru modul de atack, aici a fost stabilit la dimensiunea imaginii dar poate fi orice alta dimensiune
        attackBounds = new Rectangle(0, 0, width, height);
        ///Dreptunghiul de coliziune implicit este setat ca fiind cel normal
        bounds = normalBounds;
        attackbounds = attackBounds;
    }

    ///Metoda abstracta destinata actualizarii starii curente
    public abstract void Update();
    ///Metoda abstracta destinata desenarii starii curente
    public abstract void Draw(Graphics g);
    //Metoda abstracta prin care o entitate moare
    public abstract void die();

    public void hurt(int amt){
        current_life -=amt;             //Scadem din viata valoarea dorita
        if(current_life <= 0){
            active = false;     //Setam active cu false pentru ca dorim sa eliminam de pe harta entitatea respectiva
            die();
        }
    }

    //Testeaza fiecare entitate din joc(Item) daca
    public boolean checkCollision(float xOffset, float yOffset){
        for(Item e : refLink.GetMap().getItemManager().getItems()){     //Cautam in fiecare item
            if(e.equals(this))
                continue;       //nu verificam coliziunea cu elementul in sine
            if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset, yOffset)))        //Daca entitatea la care suntem se interesecteaza cu rectangle de coliziuni atunci avem coliziune intre cele 2 entitati
                return true;
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset){      //Va returna un nou rectangle de coliziuni,care acopera aria entitatii solide
        return new Rectangle((int)(x+bounds.x+xOffset), (int)(y+ bounds.y+yOffset), bounds.width, bounds.height);       //returneaza un nou Rectangle de coliziuni
    }

    public Rectangle getAttackBounds(float xOffset, float yOffset){
        return new Rectangle((int)(x+ attackbounds.x+xOffset), (int) (y+attackbounds.y+yOffset), attackbounds.width, attackbounds.height);
    }


    /*! \fn public float GetX()
        \brief Returneaza coordonata pe axa X.
     */
    public float GetX()
    {
        return x;
    }

    /*! \fn public float GetY()
        \brief Returneaza coordonata pe axa Y.
     */
    public float GetY()
    {
        return y;
    }

    /*! \fn public float GetWidth()
        \brief Returneaza latimea entitatii.
     */
    public int GetWidth()
    {
        return width;
    }

    /*! \fn public float GetHeight()
        \brief Returneaza inaltimea entitatii.
     */
    public int GetHeight()
    {
        return height;
    }

    /*! \fn public float SetX()
        \brief Seteaza coordonata pe axa X.
     */
    public void SetX(float x)
    {
        this.x = x;
    }

    /*! \fn public float SetY()
        \brief Seteaza coordonata pe axa Y.
     */
    public void SetY(float y)
    {
        this.y = y;
    }

    /*! \fn public float SetWidth()
        \brief Seteaza latimea imaginii entitatii.
     */
    public void SetWidth(int width)
    {
        this.width = width;
    }

    /*! \fn public float SetHeight()
        \brief Seteaza inaltimea imaginii entitatii.
     */
    public void SetHeight(int height)
    {
        this.height = height;
    }

    /*! \fn public void SetNormalMode()
        \brief Seteaza modul normal de interactiune
     */
    public void SetNormalMode()
    {
        bounds = normalBounds;
    }

    /*! \fn public void SetAttackMode()
        \brief Seteaza modul de atac de interactiune
     */
    public void SetAttackMode()
    {
        bounds = attackBounds;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life= life;
    }

    public int getCurrentLife() {
        return current_life;
    }

    public void setCurrentLife(int current_life) {
        this.current_life=current_life;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
