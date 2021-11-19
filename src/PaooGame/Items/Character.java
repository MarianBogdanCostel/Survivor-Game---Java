package PaooGame.Items;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

/*! \class public abstract class Character extends Item
    \brief Defineste notiunea abstracta de caracter/individ/fiinta din joc.

    Notiunea este definita doar de viata, viteza de deplasare si distanta cu care trebuie sa se
    miste/deplaseze in urma calculelor.
 */
public abstract class Character extends Item
{
    public static final float DEFAULT_SPEED         = 1.1f; /*!< Viteza implicita a unu caracter.*/
    public static int DEFAULT_CREATURE_WIDTH  = 32;   /*!< Latimea implicita a imaginii caracterului.*/
    public static int DEFAULT_CREATURE_HEIGHT = 32;   /*!< Inaltimea implicita a imaginii caracterului.*/


    protected float speed;  /*!< Retine viteza de deplasare caracterului.*/
    protected float xMove;  /*!< Retine noua pozitie a caracterului pe axa X.*/
    protected float yMove;  /*!< Retine noua pozitie a caracterului pe axa Y.*/

    /*! \fn public Character(RefLinks refLink, float x, float y, int width, int height)
        \brief Constructor de initializare al clasei Character

        \param refLink Referinta catre obiectul shortcut (care retine alte referinte utile/necesare in joc).
        \param x Pozitia de start pa axa X a caracterului.
        \param y Pozitia de start pa axa Y a caracterului.
        \param width Latimea imaginii caracterului.
        \param height Inaltimea imaginii caracterului.
     */
    public Character(RefLinks refLink, float x, float y, int width, int height)
    {
            ///Apel constructor la clasei de baza
        super(refLink, x,y, width, height);
            //Seteaza pe valorile implicite pentru viteza si distantele de deplasare
        speed   = DEFAULT_SPEED;
        xMove   = 0;
        yMove   = 0;
        current_life = life;
    }

    /*! \fn public void Move()
        \brief Modifica pozitia caracterului
     */
    public void Move()
    {
        //Modifica pozitia caracterului pe axa X daca nu exista coliziuni
        if(!checkCollision(xMove,0f))       //Verificam coliziunea cu xMove ca Offset deoarece verificam pozitia in care ne-am dori sa ne mutam
            MoveX();
        //Modifica pozitia caracterului pe axa Y daca nu exista coliziuni
        if(!checkCollision(0f,yMove))
            MoveY();
    }

    /*! \fn public void MoveX()
        \brief Modifica pozitia caracterului pe axa X.
     */

    public void MoveX()
    {
        if(xMove>0){            //Mutare la dreapta
            int tx = (int) ( x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;        //tx ia valoarea pozitiei x adunate cu xMove adica directia in care dorim sa ne mutam

            if(!collisionWithTile(tx,(int)( y + bounds.y) / Tile.TILE_HEIGHT) &&         //Verificam coliziunea in coltul din dreapta sus
                    !collisionWithTile(tx,(int) ( y + bounds.y + bounds.height) / Tile.TILE_HEIGHT))     //Verificam coliziunea in coltul din dreapta jos
            {
                x += xMove;
            }
            else            //Mutare la stanga
            {
                //Daca nu am folosi aceasta linie ar fi un spatiu intre bounds rectangle si tile deoarece player-ul nu s-ar mai misca la dreapta din cauza ca bounds intalneste un tile solid
                //Deci daca exista o coliziune resetam pozitia x a player-ului ca sa fie langa tile
                x = tx*Tile.TILE_WIDTH - bounds.x -bounds.width - 1;        //Folosim acel -1 pentru gap pentru ca player-ul sa se poata misca dupa sus si jos
            }
        }
        else if(xMove<0){       //Mutare la stanga
            int tx = (int) ( x + xMove + bounds.x) / Tile.TILE_WIDTH;

            if(!collisionWithTile(tx,(int)( y + bounds.y) / Tile.TILE_HEIGHT) &&         //Verificam coliziunea in coltul din stanga sus
                    !collisionWithTile(tx,(int) ( y + bounds.y + bounds.height) / Tile.TILE_HEIGHT))     //Verificam coliziunea in coltul din stanga jos
            {
                x += xMove;
            }
            else
            {
                //Scadem bounds.x ca sa fim aliniati fix langa locul coliziunii
                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
            ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa X.

    }

    /*! \fn public void MoveY()
        \brief Modifica pozitia caracterului pe axa Y.
     */
    public void MoveY()
    {
        if(yMove<0){        //Mutare in sus
            int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;       //In ty la valoarea pozitiei y adunam yMove, unde am dori sa ne mutam si bounds rectangle ca sa obtinem varful chenarului de coliziune

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_HEIGHT,ty) &&        //Verificam coltul din stanga sus
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_HEIGHT,ty)){     //Verificam coltul din dreapta sus
                y += yMove;
            }
            else
            {
                //Scadem bounds.y ca sa fim aliniati fix langa locul coliziunii
                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        }

        else if(yMove>0) {       //Mutare in jos
            int ty = (int) (y + yMove + bounds.y + bounds.getHeight()) / Tile.TILE_HEIGHT;      //Adunam bounds.getHeight ca sa obtinem partea de jos a chenarului de coliziune

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_HEIGHT,ty) &&            //Verificam coltul din stanga jos
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_HEIGHT,ty)){         //Verificam coltul din dreapta jos
                y += yMove;
            }
            else
            {
                y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    //Functia de coliziune
    //Ia coordonatele x,y a unui tile si daca este solid returneaza true, daca nu false
    protected boolean collisionWithTile(int x, int y){
        return refLink.GetMap().GetTile(x,y).IsSolid();
    }

    /*! \fn public int GetLife()
        \brief Returneaza viata caracterului.
     */

    //GETTERS & SETTERS
    public int GetLife()
    {
        return life;
    }

    /*! \fn public int GetSpeed()
        \brief Returneaza viteza caracterului.
     */
    public float GetSpeed()
    {
        return speed;
    }

    /*! \fn public void SetLife(int life)
        \brief Seteaza viata caracterului.
     */
    public void SetLife(int life)
    {
        this.life = life;
    }

    /*! \fn public void SetSpeed(float speed)
        \brief
     */
    public void SetSpeed(float speed) {
        this.speed = speed;
    }

    /*! \fn public float GetXMove()
        \brief Returneaza distanta in pixeli pe axa X cu care este actualizata pozitia caracterului.
     */
    public float GetXMove()
    {
        return xMove;
    }

    /*! \fn public float GetYMove()
        \brief Returneaza distanta in pixeli pe axa Y cu care este actualizata pozitia caracterului.
     */
    public float GetYMove()
    {
        return yMove;
    }

    /*! \fn public void SetXMove(float xMove)
        \brief Seteaza distanta in pixeli pe axa X cu care va fi actualizata pozitia caracterului.
     */
    public void SetXMove(float xMove)
    {
        this.xMove = xMove;
    }

    /*! \fn public void SetYMove(float yMove)
        \brief Seteaza distanta in pixeli pe axa Y cu care va fi actualizata pozitia caracterului.
     */
    public void SetYMove(float yMove)
    {
        this.yMove = yMove;
    }
}

