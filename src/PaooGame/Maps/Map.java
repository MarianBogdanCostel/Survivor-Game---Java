package PaooGame.Maps;


import PaooGame.Graphics.Animation;
import PaooGame.Graphics.Assets;
import PaooGame.Items.DropManager;
import PaooGame.Items.Enemies.Animals.Cow;
import PaooGame.Items.Enemies.Animals.Rabbit;
import PaooGame.Items.Enemies.Monsters.Beast;
import PaooGame.Items.Enemies.Monsters.CarnivorTree;
import PaooGame.Items.Enemies.Monsters.Dragon;
import PaooGame.Items.Hero;
import PaooGame.Items.Item;
import PaooGame.Items.ItemManager;
import PaooGame.Items.Statics.Cave;
import PaooGame.Items.Statics.Logs.Log1;
import PaooGame.Items.Statics.Logs.Log2;
import PaooGame.Items.Statics.Plants.Bush;
import PaooGame.Items.Statics.Portal;
import PaooGame.Items.Statics.Rocks.Edgerock;
import PaooGame.Items.Statics.Rocks.Rock;
import PaooGame.Items.Statics.Rocks.Rock1;
import PaooGame.Items.Statics.Rocks.Rock2;
import PaooGame.Items.Statics.Trees.*;
import PaooGame.Items.Statics.Plants.*;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map
{
    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    private int spawnX;         //pozitia caracterului pe axa X
    private int spawnY;         //pozitia caracterului pe axa Y
    private int [][] tiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/
    private ItemManager itemManager;
    private int number;

    private DropManager dropManager;

    /*! \fn public Map(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public Map(RefLinks refLink, int number)
    {
            /// Retine referinta "shortcut".
        this.refLink = refLink;
        itemManager = new ItemManager(refLink, Hero.getInstance(refLink,0,0));
        dropManager = new DropManager(refLink);

    if(number==1) {

        //Trees
        itemManager.addItem(new Tree(refLink, -64, Tile.TILE_HEIGHT * 3));
        itemManager.addItem(new EdgeTree(refLink, 0, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new EdgeTree(refLink, Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new EdgeTree(refLink, Tile.TILE_WIDTH * 4, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new EdgeTree(refLink, Tile.TILE_WIDTH * 6, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new EdgeTree(refLink, Tile.TILE_WIDTH * 8, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new EdgeTree(refLink, Tile.TILE_WIDTH * 10, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new EdgeTree(refLink, Tile.TILE_WIDTH * 12, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new EdgeTree(refLink, Tile.TILE_WIDTH * 14, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new EdgeTree(refLink, Tile.TILE_WIDTH * 16, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new EdgeTree(refLink, Tile.TILE_WIDTH * 18, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new EdgeTree(refLink, Tile.TILE_WIDTH * 20, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new EdgeTree(refLink, Tile.TILE_WIDTH * 22, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new EdgeTree(refLink, Tile.TILE_WIDTH * 24, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new EdgeTree(refLink, Tile.TILE_WIDTH * 26, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new EdgeTree(refLink, Tile.TILE_WIDTH * 28, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new EdgeTree(refLink, Tile.TILE_WIDTH * 30, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new Tree(refLink, Tile.TILE_WIDTH * 33, Tile.TILE_HEIGHT * 16));
        itemManager.addItem(new Tree(refLink, Tile.TILE_WIDTH * 15, -40));
        //itemManager.addItem(new Tree3(refLink, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 21));


        itemManager.addItem(new Tree1(refLink, Tile.TILE_WIDTH * 30, Tile.TILE_HEIGHT * 12));
        itemManager.addItem(new Tree1(refLink, Tile.TILE_WIDTH * 15, Tile.TILE_HEIGHT * 13));
        itemManager.addItem(new Tree1(refLink, Tile.TILE_WIDTH * 24, Tile.TILE_HEIGHT * 4));
        itemManager.addItem(new Tree1(refLink, Tile.TILE_WIDTH * 7, Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Tree1(refLink, Tile.TILE_WIDTH * 20, Tile.TILE_HEIGHT * 16));

        itemManager.addItem(new Tree2(refLink, Tile.TILE_WIDTH * 26, Tile.TILE_HEIGHT * 13));
        itemManager.addItem(new Tree2(refLink, Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT * 9));
        itemManager.addItem(new Tree2(refLink, Tile.TILE_WIDTH * 34, Tile.TILE_HEIGHT * 3));
        itemManager.addItem(new Tree2(refLink, Tile.TILE_WIDTH * 10, Tile.TILE_HEIGHT * 16));
        itemManager.addItem(new Tree2(refLink, Tile.TILE_WIDTH * 15, Tile.TILE_HEIGHT * 7));

        itemManager.addItem(new Tree3(refLink, Tile.TILE_WIDTH * 35, Tile.TILE_HEIGHT * 7));
        itemManager.addItem(new Tree3(refLink, Tile.TILE_WIDTH * 14, Tile.TILE_HEIGHT * 3));
        itemManager.addItem(new Tree3(refLink, Tile.TILE_WIDTH * 9, Tile.TILE_HEIGHT * 11));
        itemManager.addItem(new Tree3(refLink, Tile.TILE_WIDTH * 4, Tile.TILE_HEIGHT * 15));
        itemManager.addItem(new Tree3(refLink, Tile.TILE_WIDTH * 22, Tile.TILE_HEIGHT * 8));
        itemManager.addItem(new Tree3(refLink, Tile.TILE_WIDTH * 29, Tile.TILE_HEIGHT));

        itemManager.addItem(new CutTree(refLink, 0, Tile.TILE_HEIGHT * 16 - 6));
        itemManager.addItem(new CutTree(refLink, Tile.TILE_WIDTH/2+5, Tile.TILE_HEIGHT * 7));
        itemManager.addItem(new CutTree(refLink, Tile.TILE_WIDTH * 7, Tile.TILE_HEIGHT * 20));
        itemManager.addItem(new CutTree(refLink, Tile.TILE_WIDTH * 23, Tile.TILE_HEIGHT * 7));
        itemManager.addItem(new CutTree(refLink, Tile.TILE_WIDTH * 37, Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new CutTree(refLink, Tile.TILE_WIDTH * 34, Tile.TILE_HEIGHT * 13));

        itemManager.addItem(new Cave(refLink, 0, 0));

        //Rocks
        itemManager.addItem(new Rock(refLink, 5, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new Rock(refLink, Tile.TILE_WIDTH * 38 + 20, Tile.TILE_HEIGHT + 20));
        itemManager.addItem(new Rock1(refLink, Tile.TILE_WIDTH * 9, Tile.TILE_HEIGHT * 7));
        itemManager.addItem(new Rock1(refLink, Tile.TILE_WIDTH * 19, Tile.TILE_HEIGHT * 4));
        itemManager.addItem(new Rock1(refLink, Tile.TILE_WIDTH * 14, Tile.TILE_HEIGHT * 17));
        itemManager.addItem(new Rock1(refLink, Tile.TILE_WIDTH * 20, Tile.TILE_HEIGHT * 9));
        itemManager.addItem(new Rock1(refLink, Tile.TILE_WIDTH * 33, Tile.TILE_HEIGHT * 20));
        itemManager.addItem(new Rock2(refLink, Tile.TILE_WIDTH * 12, Tile.TILE_HEIGHT * 8));
        itemManager.addItem(new Rock2(refLink, Tile.TILE_WIDTH * 26, Tile.TILE_HEIGHT * 3));
        itemManager.addItem(new Rock2(refLink, Tile.TILE_WIDTH * 31, Tile.TILE_HEIGHT * 21));
        itemManager.addItem(new Rock2(refLink, Tile.TILE_WIDTH * 33, Tile.TILE_HEIGHT * 15));
        itemManager.addItem(new Rock2(refLink, Tile.TILE_WIDTH * 7, Tile.TILE_HEIGHT * 3));
        itemManager.addItem(new Rock2(refLink, Tile.TILE_WIDTH * 15, Tile.TILE_HEIGHT * 20));
        itemManager.addItem(new Rock2(refLink, Tile.TILE_WIDTH * 3, Tile.TILE_HEIGHT * 14));


        //Logs
        itemManager.addItem(new Log2(refLink, Tile.TILE_WIDTH * 15, -30));
        itemManager.addItem(new Log1(refLink, Tile.TILE_WIDTH * 23, 0));
        itemManager.addItem(new Log2(refLink, Tile.TILE_WIDTH * 5 - 4, (-16)));
        itemManager.addItem(new Log1(refLink, Tile.TILE_WIDTH * 12, Tile.TILE_HEIGHT * 3));
        itemManager.addItem(new Log1(refLink, Tile.TILE_WIDTH * 20, Tile.TILE_HEIGHT * 13));
        itemManager.addItem(new Log2(refLink, Tile.TILE_WIDTH * 37, Tile.TILE_HEIGHT * 17));
        itemManager.addItem(new Log2(refLink, Tile.TILE_WIDTH * 29, Tile.TILE_HEIGHT * 5));


        //Bush
        itemManager.addItem(new Bush(refLink, -(Tile.TILE_WIDTH), Tile.TILE_HEIGHT * 7));
        itemManager.addItem(new Bush(refLink, -(Tile.TILE_WIDTH), Tile.TILE_HEIGHT * 8));
        itemManager.addItem(new Bush(refLink, -(Tile.TILE_WIDTH), Tile.TILE_HEIGHT * 9));
        itemManager.addItem(new Bush(refLink, -(Tile.TILE_WIDTH), Tile.TILE_HEIGHT * 10));
        itemManager.addItem(new Bush(refLink, -(Tile.TILE_WIDTH), Tile.TILE_HEIGHT * 11));
        itemManager.addItem(new Bush(refLink, -(Tile.TILE_WIDTH), Tile.TILE_HEIGHT * 12));
        itemManager.addItem(new Bush(refLink, -(Tile.TILE_WIDTH), Tile.TILE_HEIGHT * 13));
        itemManager.addItem(new Bush(refLink, -(Tile.TILE_WIDTH), Tile.TILE_HEIGHT * 16));
        itemManager.addItem(new Bush(refLink, -(Tile.TILE_WIDTH), Tile.TILE_HEIGHT * 17));
        itemManager.addItem(new Bush(refLink, -(Tile.TILE_WIDTH), Tile.TILE_HEIGHT * 18));
        itemManager.addItem(new Bush(refLink, -(Tile.TILE_WIDTH), Tile.TILE_HEIGHT * 19));
        itemManager.addItem(new Bush(refLink, -(Tile.TILE_WIDTH), Tile.TILE_HEIGHT * 20));


        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 38, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 38, -Tile.TILE_HEIGHT / 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 38, Tile.TILE_HEIGHT * 3));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 38, Tile.TILE_HEIGHT * 4));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 38, Tile.TILE_HEIGHT * 5));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 38, Tile.TILE_HEIGHT * 6));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 38, Tile.TILE_HEIGHT * 7));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 38, Tile.TILE_HEIGHT * 8));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 38, Tile.TILE_HEIGHT * 9));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 38, Tile.TILE_HEIGHT * 10 - 16));

        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 37, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 36, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 35, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 34, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 33, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 32, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 31, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 30, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 29, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 28, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 27, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 26, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 25, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 24, -Tile.TILE_HEIGHT * 2));

        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 13, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 12, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 11, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 10, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 9, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 8, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 7, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 6, -Tile.TILE_HEIGHT * 2));
        itemManager.addItem(new Bush(refLink, Tile.TILE_WIDTH * 5, -Tile.TILE_HEIGHT * 2));


        //Flowers
        itemManager.addItem(new YellowFlower(refLink, Tile.TILE_WIDTH*2, Tile.TILE_HEIGHT * 8));
        itemManager.addItem(new YellowFlower(refLink, Tile.TILE_WIDTH / 2, 0));
        itemManager.addItem(new YellowFlower(refLink, Tile.TILE_WIDTH * 22, Tile.TILE_HEIGHT * 3));
        itemManager.addItem(new YellowFlower(refLink, Tile.TILE_WIDTH * 9, Tile.TILE_HEIGHT * 11));
        itemManager.addItem(new YellowFlower(refLink, Tile.TILE_WIDTH * 17, Tile.TILE_HEIGHT * 17));
        itemManager.addItem(new YellowFlower(refLink, Tile.TILE_WIDTH * 30, Tile.TILE_HEIGHT * 9));
        itemManager.addItem(new RedFlower(refLink, Tile.TILE_WIDTH * 8, Tile.TILE_HEIGHT));
        itemManager.addItem(new RedFlower(refLink, Tile.TILE_WIDTH * 38, Tile.TILE_HEIGHT * 16));
        itemManager.addItem(new RedFlower(refLink, Tile.TILE_WIDTH * 3, Tile.TILE_HEIGHT * 19));
        itemManager.addItem(new RedFlower(refLink, Tile.TILE_WIDTH * 33, Tile.TILE_HEIGHT * 3));
        itemManager.addItem(new RedFlower(refLink, Tile.TILE_WIDTH * 5, Tile.TILE_HEIGHT * 10));


        //Animals
        itemManager.addItem(new Cow(refLink, 180, 390));
        itemManager.addItem(new Cow(refLink, 1000, 180));
        itemManager.addItem(new Rabbit(refLink, 300, 230));
        itemManager.addItem(new Rabbit(refLink, 800, 540));
    }

    if(number==2)
    {

        for(int i=0;i<23;i++)
        {
            itemManager.addItem(new Edgerock(refLink,Tile.TILE_WIDTH*39,i*Tile.TILE_HEIGHT));
        }

        for(int i=0;i<40;i++)
        {
            itemManager.addItem(new Edgerock(refLink,i*Tile.TILE_WIDTH,Tile.TILE_HEIGHT*22+18));
        }

        for(int i=0;i<20;i++){
            itemManager.addItem(new Edgerock(refLink,0,96+i*Tile.TILE_HEIGHT));
        }

        for(int i=0;i<22;i++){
            itemManager.addItem(new Edgerock(refLink,Tile.TILE_WIDTH*19+i*Tile.TILE_WIDTH,-32));
        }

        for(int i=0;i<7;i++){
            itemManager.addItem(new Edgerock(refLink,i*Tile.TILE_WIDTH,-32));
        }

        for(int i=0;i<10;i++){
            itemManager.addItem(new Edgerock(refLink,Tile.TILE_WIDTH*6,Tile.TILE_HEIGHT*i));
        }

        for(int i=0;i<12;i++){
            itemManager.addItem(new Edgerock(refLink,Tile.TILE_WIDTH*7+i*Tile.TILE_WIDTH,Tile.TILE_HEIGHT*9));
        }

        itemManager.addItem(new Edgerock(refLink,Tile.TILE_WIDTH,Tile.TILE_HEIGHT*3));
        itemManager.addItem(new Edgerock(refLink,0,0));
        itemManager.addItem(new Edgerock(refLink,0,Tile.TILE_HEIGHT));
        itemManager.addItem(new Edgerock(refLink,0,Tile.TILE_HEIGHT*2));

        for(int i=0;i<11;i++){
            itemManager.addItem(new Edgerock(refLink,Tile.TILE_WIDTH*2,Tile.TILE_HEIGHT*3+Tile.TILE_HEIGHT*i));
        }

        for(int i=0;i<16;i++){
            itemManager.addItem(new Edgerock(refLink,Tile.TILE_WIDTH*3+Tile.TILE_WIDTH*i,Tile.TILE_HEIGHT*13));
        }

        for(int i=0;i<10;i++){
            itemManager.addItem(new Edgerock(refLink,Tile.TILE_WIDTH*18,-32+Tile.TILE_HEIGHT*i));
        }

        for(int i=0;i<6;i++){
            itemManager.addItem(new Edgerock(refLink,Tile.TILE_WIDTH*18,Tile.TILE_HEIGHT*13+Tile.TILE_HEIGHT*i));
        }

        //Trees
        itemManager.addItem(new Tree(refLink, -32, Tile.TILE_HEIGHT * 10+10));
        itemManager.addItem(new Tree(refLink, -32, Tile.TILE_HEIGHT * 3));

        //Flowers
        itemManager.addItem(new YellowFlower(refLink, Tile.TILE_WIDTH * 22, Tile.TILE_HEIGHT * 3));
        itemManager.addItem(new YellowFlower(refLink, Tile.TILE_WIDTH * 36, Tile.TILE_HEIGHT * 22));
        itemManager.addItem(new YellowFlower(refLink, Tile.TILE_WIDTH * 17, Tile.TILE_HEIGHT * 17));
        itemManager.addItem(new YellowFlower(refLink, Tile.TILE_WIDTH * 32, Tile.TILE_HEIGHT * 7));
        itemManager.addItem(new RedFlower(refLink, Tile.TILE_WIDTH * 28, Tile.TILE_HEIGHT*21));
        itemManager.addItem(new RedFlower(refLink, Tile.TILE_WIDTH * 38, Tile.TILE_HEIGHT * 16));
        itemManager.addItem(new RedFlower(refLink, Tile.TILE_WIDTH * 3, Tile.TILE_HEIGHT * 19));
        itemManager.addItem(new RedFlower(refLink, Tile.TILE_WIDTH * 33, Tile.TILE_HEIGHT * 3));
    }

            ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
        LoadWorld(number);

        //Pozitia unde o sa apara caracterul citita din fisier
        //itemManager.getHero().SetX(spawnX);
        //itemManager.getHero().SetY(spawnY);
    }

    /*! \fn private void LoadWorld(path)
     Functie de incarcare a hartii jocului din fisier
 */
    private void LoadWorld(int number)
    {
        String[] tokens = null;
        if(number==1) {
            String file = RefLinks.loadFileAsString("res/worlds/world1.txt");
            tokens = file.split("\\s+"); //separam fiecare caracter din fisier si il pune intr-un vector
            width = RefLinks.parseInt(tokens[0]);           ///Se stabileste latimea hartii in numar de dale din fisier
            height = RefLinks.parseInt(tokens[1]);            ///Se stabileste inaltimea hartii in numar de dale din fisier
            spawnX = RefLinks.parseInt(tokens[2]);            ///Se stabileste pozitia caracterului din fisier
            spawnY = RefLinks.parseInt(tokens[3]);            ///Se stabileste pozitia caracterului din fisier
        }

        if(number==2) {
            String file = RefLinks.loadFileAsString("res/worlds/worldMonsters.txt");
            tokens = file.split("\\s+"); //separam fiecare caracter din fisier si il pune intr-un vector
            width = RefLinks.parseInt(tokens[0]);           ///Se stabileste latimea hartii in numar de dale din fisier
            height = RefLinks.parseInt(tokens[1]);            ///Se stabileste inaltimea hartii in numar de dale din fisier
            spawnX = RefLinks.parseInt(tokens[2]);            ///Se stabileste pozitia caracterului din fisier
            spawnY = RefLinks.parseInt(tokens[3]);            ///Se stabileste pozitia caracterului din fisier
        }


        //atentie latimea si inaltimea trebuiesc corelate cu dimensiunile ferestrei sau
        //se poate implementa notiunea de camera/cadru de vizualizare al hartii

        tiles = new int[width][height];
        //Se incarca matricea cu coduri
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                tiles[x][y] = RefLinks.parseInt(tokens[(x+y*width)+4]);
            }
        }
    }



    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public  void Update()
    {
        dropManager.Update();
        itemManager.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextl grafi in care se realizeaza desenarea.
     */
    public void Draw(Graphics g)
    {
        int xStart=0;
        int xEnd=0;
        int yStart=0;
        int yEnd=0;
            ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for(int y = 0; y < refLink.GetGame().GetHeight()/Tile.TILE_HEIGHT; y++)
        {
            for(int x = 0; x < refLink.GetGame().GetWidth()/Tile.TILE_WIDTH; x++)
            {
                GetTile(x, y).Draw(g, (int)(x * Tile.TILE_WIDTH), (int)(y * Tile.TILE_HEIGHT));
            }
        }
        dropManager.Draw(g);
        itemManager.Draw(g);
    }

    /*! \fn public Tile GetTile(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

        In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
        intoarce o dala predefinita (ex. grassTile, mountainTile)
     */
    public Tile GetTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tile.grassTile;
        }

        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
        {
            return Tile.grassTile;
        }
        return t;
    }

    public ItemManager getItemManager(){
        return itemManager;
}

    public RefLinks getRefLink(){
        return refLink;
    }

    public int GetMapNumber(){
        return number;
    }

    public void setRefLink(RefLinks refLinks){
        this.refLink = refLinks;
    }


    public DropManager getDropManager(){
        return dropManager;
    }

    public void setDropManager(DropManager dropManager) {
        this.dropManager = dropManager;
    }


}