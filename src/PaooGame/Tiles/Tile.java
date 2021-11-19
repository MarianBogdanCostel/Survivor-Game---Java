package PaooGame.Tiles;

import PaooGame.Tiles.Map1.GrassTile;
import PaooGame.Tiles.Map1.WaterTile;
import PaooGame.Tiles.Map2.Soil1Tile;
import PaooGame.Tiles.Map2.Soil2Tile;
import PaooGame.Tiles.Map2.SoilRocksTile;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    public static final int TILE_WIDTH  = 32;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 32;                       /*!< Inaltimea unei dale.*/

    private static final int NO_TILES   = 50;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/

    protected BufferedImage texture;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
    /// o singura data in memorie
    public static Tile grassTile        = new GrassTile(0);     /*!< Dala de tip iarba*/
    public static Tile soil1            = new Soil1Tile(1);     /*!< Dala de tip sol 1*/
    public static Tile soil2            = new Soil2Tile(2);     /*!< Dala de tip sol 2*/
    public static Tile waterTile        = new WaterTile(3);     /*!< Dala de tip apa*/
    public static Tile soilrocks        = new SoilRocksTile(4); /*!< Dala de tip rocks*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.
        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage texture, int id)
    {
        this.texture = texture;
        this.id = id;
        tiles[id] = this;
    }

    /*! \fn public int GetId()
    \brief Returneaza id-ul dalei.
 */
    public int GetId()
    {
        return id;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
        Adica pozitia pe ecran
     */
    public void Draw(Graphics g, int x, int y)
    {
            /// Desenare dala
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return false;
    }


}
