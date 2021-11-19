package PaooGame.Graphics;


import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
    private static final int    tileWidth   = 32;   /*!< Latimea unei dale din sprite sheet.*/
    private static final int    tileHeight  = 32;   /*!< Inaltime unei dale din sprite sheet.*/
        /// Referinte catre elementele grafice (dale) utilizate in joc.

    //Hero
    public static BufferedImage[] player_down, player_up, player_left, player_right;        //vector ce contine imaginile pentru animatia caracterului
    public static BufferedImage[] atack_down, atack_up, atack_left, atack_right, player_die;        //vector ce contine imaginile pentru animatia caracterului

    //Enemy
    public static BufferedImage[] enemy_walk_down, enemy_walk_up, enemy_walk_left, enemy_walk_right;
    public static BufferedImage[] cow_down, cow_up, cow_left, cow_right;
    public static BufferedImage[] rabbit_down, rabbit_up, rabbit_left, rabbit_right;

    //Monsters
    public static BufferedImage[] dragon_down, dragon_up, dragon_left, dragon_right;
    public static BufferedImage[] beast_down, beast_up, beast_left, beast_right;
    public static BufferedImage[] tree_down, tree_up, tree_left, tree_right;
    public static BufferedImage[] devil_down, devil_up, devil_left, devil_right;

    //Menu
    public static BufferedImage menu, about, controls, settings, pause, death, win, mesaj;

    //Map 1
    public static BufferedImage cave;
    public static BufferedImage grass;
    public static BufferedImage water;
    public static BufferedImage tree0,tree1,tree2,tree3, edgetree;
    public static BufferedImage cuttree;
    public static BufferedImage rock,rock1,rock2;
    public static BufferedImage log1,log2;
    public static BufferedImage drop_tree, drop_rock;
    public static BufferedImage yellowflower, redflower;
    public static BufferedImage bush;
    public static BufferedImage axe;
    public static BufferedImage[] portal;

    //Map 2
    public static BufferedImage soil1,soil2,soil_rocks;
    public static BufferedImage edgerock;


    //Time
    static boolean passed=false;
    static long now;
    static long then;
    static boolean monster_passed = false;
    static long monster_then, monster_now;

    //Fonts
    public static Font font30;

    //Inventory
    public static BufferedImage inventoryScreen;

    //Audio
    public static Audio MenuMusic;
    public static Audio Selection;


    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */

    public static void Init()
    {
        //Fonts
        font30 = FontLoader.loadFont("res/Fonts/Sofia.otf",30);

        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Sprite.png"));
        SpriteSheet watersheet = new SpriteSheet(ImageLoader.LoadImage("/textures/water.png"));
        SpriteSheet home = new SpriteSheet(ImageLoader.LoadImage("/textures/Cave/Cave.png"));
        SpriteSheet t0 = new SpriteSheet(ImageLoader.LoadImage("/textures/Trees/Tree0.png"));
        SpriteSheet t1 = new SpriteSheet(ImageLoader.LoadImage("/textures/Trees/Tree1.png"));
        SpriteSheet t2 = new SpriteSheet(ImageLoader.LoadImage("/textures/Trees/Tree2.png"));
        SpriteSheet t3 = new SpriteSheet(ImageLoader.LoadImage("/textures/Trees/Tree3.png"));
        SpriteSheet margtree = new SpriteSheet(ImageLoader.LoadImage("/textures/Trees/Tree.png"));


        //Menu
        menu = ImageLoader.LoadImage("/textures/Menu/Menu.jpg");
        pause = ImageLoader.LoadImage("/textures/Menu/Pause.jpg");
        controls = ImageLoader.LoadImage("/textures/Menu/Controls.jpg");
        settings = ImageLoader.LoadImage("/textures/Menu/Settings.jpg");
        death = ImageLoader.LoadImage("/textures/Menu/Dead.jpg");
        win = ImageLoader.LoadImage("/textures/Menu/Won.jpg");
        about = ImageLoader.LoadImage("/textures/Menu/About.jpg");
        axe = ImageLoader.LoadImage("/textures/Menu/axe1.png");
        mesaj = ImageLoader.LoadImage("/textures/Menu/Mesaj.png");


        //Audio
        MenuMusic = new Audio("res/Sound/menu.mp3");
        Selection = new Audio("res/Sound/Selection.wav");

        //Inventory
        inventoryScreen = ImageLoader.LoadImage("/textures/Inventory/Inventory.jpg");

        //Hero Walk animations
        player_down = new BufferedImage[9];     //9 frame-uri pentru animatia de mutare in jos
        player_up = new BufferedImage[9];     //9 frame-uri pentru animatia de mutare in sus
        player_left = new BufferedImage[9];     //9 frame-uri pentru animatia de mutare in stanga
        player_right = new BufferedImage[9];     //9 frame-uri pentru animatia de mutare in dreapta


        player_down[0] = sheet.crop(0,tileHeight*35,tileWidth*2,tileHeight*2);
        player_down[1] = sheet.crop(tileWidth*2,tileHeight*35,tileWidth*2,tileHeight*2);
        player_down[2] = sheet.crop(tileWidth*4,tileHeight*35,tileWidth*2,tileHeight*2);
        player_down[3] = sheet.crop(tileWidth*6,tileHeight*35,tileWidth*2,tileHeight*2);
        player_down[4] = sheet.crop(tileWidth*8,tileHeight*35,tileWidth*2,tileHeight*2);
        player_down[5] = sheet.crop(tileWidth*10,tileHeight*35,tileWidth*2,tileHeight*2);
        player_down[6] = sheet.crop(tileWidth*12,tileHeight*35,tileWidth*2,tileHeight*2);
        player_down[7] = sheet.crop(tileWidth*14,tileHeight*35,tileWidth*2,tileHeight*2);
        player_down[8] = sheet.crop(tileWidth*16,tileHeight*35,tileWidth*2,tileHeight*2);

        player_up[0] = sheet.crop(0,tileHeight*31,tileWidth*2,tileHeight*2);
        player_up[1] = sheet.crop(tileWidth*2,tileHeight*31,tileWidth*2,tileHeight*2);
        player_up[2] = sheet.crop(tileWidth*4,tileHeight*31,tileWidth*2,tileHeight*2);
        player_up[3] = sheet.crop(tileWidth*6,tileHeight*31,tileWidth*2,tileHeight*2);
        player_up[4] = sheet.crop(tileWidth*8,tileHeight*31,tileWidth*2,tileHeight*2);
        player_up[5] = sheet.crop(tileWidth*10,tileHeight*31,tileWidth*2,tileHeight*2);
        player_up[6] = sheet.crop(tileWidth*12,tileHeight*31,tileWidth*2,tileHeight*2);
        player_up[7] = sheet.crop(tileWidth*14,tileHeight*31,tileWidth*2,tileHeight*2);
        player_up[8] = sheet.crop(tileWidth*16,tileHeight*31,tileWidth*2,tileHeight*2);

        player_left[0] = sheet.crop(0,tileHeight*33,tileWidth*2,tileHeight*2);
        player_left[1] = sheet.crop(tileWidth*2,tileHeight*33,tileWidth*2,tileHeight*2);
        player_left[2] = sheet.crop(tileWidth*4,tileHeight*33,tileWidth*2,tileHeight*2);
        player_left[3] = sheet.crop(tileWidth*6,tileHeight*33,tileWidth*2,tileHeight*2);
        player_left[4] = sheet.crop(tileWidth*8,tileHeight*33,tileWidth*2,tileHeight*2);
        player_left[5] = sheet.crop(tileWidth*10,tileHeight*33,tileWidth*2,tileHeight*2);
        player_left[6] = sheet.crop(tileWidth*12,tileHeight*33,tileWidth*2,tileHeight*2);
        player_left[7] = sheet.crop(tileWidth*14,tileHeight*33,tileWidth*2,tileHeight*2);
        player_left[8] = sheet.crop(tileWidth*16,tileHeight*33,tileWidth*2,tileHeight*2);

        player_right[0] = sheet.crop(0,tileHeight*37,tileWidth*2,tileHeight*2);
        player_right[1] = sheet.crop(tileWidth*2,tileHeight*37,tileWidth*2,tileHeight*2);
        player_right[2] = sheet.crop(tileWidth*4,tileHeight*37,tileWidth*2,tileHeight*2);
        player_right[3] = sheet.crop(tileWidth*6,tileHeight*37,tileWidth*2,tileHeight*2);
        player_right[4] = sheet.crop(tileWidth*8,tileHeight*37,tileWidth*2,tileHeight*2);
        player_right[5] = sheet.crop(tileWidth*10,tileHeight*37,tileWidth*2,tileHeight*2);
        player_right[6] = sheet.crop(tileWidth*12,tileHeight*37,tileWidth*2,tileHeight*2);
        player_right[7] = sheet.crop(tileWidth*14,tileHeight*37,tileWidth*2,tileHeight*2);
        player_right[8] = sheet.crop(tileWidth*16,tileHeight*37,tileWidth*2,tileHeight*2);


        //Hero Atack animations
        atack_down = new BufferedImage[6];
        atack_up = new BufferedImage[6];
        atack_left = new BufferedImage[6];
        atack_right = new BufferedImage[6];
        player_die = new BufferedImage[6];


        atack_down[0] = sheet.crop(0,tileHeight*43,tileWidth*2,tileHeight*2);
        atack_down[1] = sheet.crop(tileWidth*2,tileHeight*43,tileWidth*2,tileHeight*2);
        atack_down[2] = sheet.crop(tileWidth*4,tileHeight*43,tileWidth*2,tileHeight*2);
        atack_down[3] = sheet.crop(tileWidth*6,tileHeight*43,tileWidth*2,tileHeight*2);
        atack_down[4] = sheet.crop(tileWidth*8,tileHeight*43,tileWidth*2,tileHeight*2);
        atack_down[5] = sheet.crop(tileWidth*10,tileHeight*43,tileWidth*2,tileHeight*2);

        atack_up[0] = sheet.crop(0,tileHeight*39,tileWidth*2,tileHeight*2);
        atack_up[1] = sheet.crop(tileWidth*2,tileHeight*39,tileWidth*2,tileHeight*2);
        atack_up[2] = sheet.crop(tileWidth*4,tileHeight*39,tileWidth*2,tileHeight*2);
        atack_up[3] = sheet.crop(tileWidth*6,tileHeight*39,tileWidth*2,tileHeight*2);
        atack_up[4] = sheet.crop(tileWidth*8,tileHeight*39,tileWidth*2,tileHeight*2);
        atack_up[5] = sheet.crop(tileWidth*10,tileHeight*39,tileWidth*2,tileHeight*2);

        atack_left[0] = sheet.crop(0,tileHeight*41,tileWidth*2,tileHeight*2);
        atack_left[1] = sheet.crop(tileWidth*2,tileHeight*41,tileWidth*2,tileHeight*2);
        atack_left[2] = sheet.crop(tileWidth*4,tileHeight*41,tileWidth*2,tileHeight*2);
        atack_left[3] = sheet.crop(tileWidth*6,tileHeight*41,tileWidth*2,tileHeight*2);
        atack_left[4] = sheet.crop(tileWidth*8,tileHeight*41,tileWidth*2,tileHeight*2);
        atack_left[5] = sheet.crop(tileWidth*10,tileHeight*41,tileWidth*2,tileHeight*2);

        atack_right[0] = sheet.crop(0,tileHeight*45,tileWidth*2,tileHeight*2);
        atack_right[1] = sheet.crop(tileWidth*2,tileHeight*45,tileWidth*2,tileHeight*2);
        atack_right[2] = sheet.crop(tileWidth*4,tileHeight*45,tileWidth*2,tileHeight*2);
        atack_right[3] = sheet.crop(tileWidth*6,tileHeight*45,tileWidth*2,tileHeight*2);
        atack_right[4] = sheet.crop(tileWidth*8,tileHeight*45,tileWidth*2,tileHeight*2);
        atack_right[5] = sheet.crop(tileWidth*10,tileHeight*45,tileWidth*2,tileHeight*2);

        player_die[0] = sheet.crop(0,tileHeight*47,tileWidth*2,tileHeight*2);
        player_die[1] = sheet.crop(tileWidth*2,tileHeight*47,tileWidth*2,tileHeight*2);
        player_die[2] = sheet.crop(tileWidth*4,tileHeight*47,tileWidth*2,tileHeight*2);
        player_die[3] = sheet.crop(tileWidth*6,tileHeight*47,tileWidth*2,tileHeight*2);
        player_die[4] = sheet.crop(tileWidth*8,tileHeight*47,tileWidth*2,tileHeight*2);
        player_die[5] = sheet.crop(tileWidth*10,tileHeight*47,tileWidth*2,tileHeight*2);

        //Monsters
        SpriteSheet dragon = new SpriteSheet(ImageLoader.LoadImage("/textures/Monsters/Dragon.png"));
        SpriteSheet tree = new SpriteSheet(ImageLoader.LoadImage("/textures/Monsters/Tree.png"));
        SpriteSheet beast = new SpriteSheet(ImageLoader.LoadImage("/textures/Monsters/Beast.png"));
        SpriteSheet devil = new SpriteSheet(ImageLoader.LoadImage("/textures/Monsters/Devil.png"));

        devil_down = new BufferedImage[4];
        devil_up = new BufferedImage[4];
        devil_left = new BufferedImage[4];
        devil_right = new BufferedImage[4];

        devil_down[0] = devil.crop(0,0,tileWidth*3,tileHeight*3);
        devil_down[1] = devil.crop(tileWidth*3,0,tileWidth*3,tileHeight*3);
        devil_down[2] = devil.crop(tileWidth*6,0,tileWidth*3,tileHeight*3);
        devil_down[3] = devil.crop(tileWidth*9,0,tileWidth*3,tileHeight*3);

        devil_left[0] = devil.crop(0,tileHeight*3,tileWidth*3,tileHeight*3);
        devil_left[1] = devil.crop(tileWidth*3,tileHeight*3,tileWidth*3,tileHeight*3);
        devil_left[2] = devil.crop(tileWidth*6,tileHeight*3,tileWidth*3,tileHeight*3);
        devil_left[3] = devil.crop(tileWidth*9,tileHeight*3,tileWidth*3,tileHeight*3);

        devil_right[0] = devil.crop(0,tileHeight*6,tileWidth*3,tileHeight*3);
        devil_right[1] = devil.crop(tileWidth*3,tileHeight*6,tileWidth*3,tileHeight*3);
        devil_right[2] = devil.crop(tileWidth*6,tileHeight*6,tileWidth*3,tileHeight*3);
        devil_right[3] = devil.crop(tileWidth*9,tileHeight*6,tileWidth*3,tileHeight*3);

        devil_up[0] = devil.crop(0,tileHeight*9,tileWidth*3,tileHeight*3);
        devil_up[1] = devil.crop(tileWidth*3,tileHeight*9,tileWidth*3,tileHeight*3);
        devil_up[2] = devil.crop(tileWidth*6,tileHeight*9,tileWidth*3,tileHeight*3);
        devil_up[3] = devil.crop(tileWidth*9,tileHeight*9,tileWidth*3,tileHeight*3);

        //Enemy
        SpriteSheet cow = new SpriteSheet(ImageLoader.LoadImage("/textures/Animals/Cow.png"));
        SpriteSheet rabbit = new SpriteSheet(ImageLoader.LoadImage("/textures/Animals/Rabbit.png"));

        //Cow
        cow_down = new BufferedImage[4];
        cow_up = new BufferedImage[4];
        cow_left = new BufferedImage[4];
        cow_right = new BufferedImage[4];

        cow_down[0] = cow.crop(0,0, tileWidth*2, tileHeight*2);
        cow_down[1] = cow.crop(tileWidth*2,0, tileWidth*2, tileHeight*2);
        cow_down[2] = cow.crop(tileWidth*4,0, tileWidth*2, tileHeight*2);
        cow_down[3] = cow.crop(tileWidth*6,0, tileWidth*2, tileHeight*2);

        cow_left[0] = cow.crop(0,tileHeight*2, tileWidth*2, tileHeight*2);
        cow_left[1] = cow.crop(tileWidth*2,tileHeight*2, tileWidth*2, tileHeight*2);
        cow_left[2] = cow.crop(tileWidth*4,tileHeight*2, tileWidth*2, tileHeight*2);
        cow_left[3] = cow.crop(tileWidth*6,tileHeight*2, tileWidth*2, tileHeight*2);

        cow_right[0] = cow.crop(0,tileHeight*4, tileWidth*2, tileHeight*2);
        cow_right[1] = cow.crop(tileWidth*2,tileHeight*4, tileWidth*2, tileHeight*2);
        cow_right[2] = cow.crop(tileWidth*4,tileHeight*4, tileWidth*2, tileHeight*2);
        cow_right[3] = cow.crop(tileWidth*6,tileHeight*4, tileWidth*2, tileHeight*2);

        cow_up[0] = cow.crop(0,tileHeight*6, tileWidth*2, tileHeight*2);
        cow_up[1] = cow.crop(tileWidth*2,tileHeight*6, tileWidth*2, tileHeight*2);
        cow_up[2] = cow.crop(tileWidth*4,tileHeight*6, tileWidth*2, tileHeight*2);
        cow_up[3] = cow.crop(tileWidth*6,tileHeight*6, tileWidth*2, tileHeight*2);


        //Rabbit
        rabbit_down = new BufferedImage[4];
        rabbit_up = new BufferedImage[4];
        rabbit_left = new BufferedImage[4];
        rabbit_right = new BufferedImage[4];

        rabbit_down[0] = rabbit.crop(0,0,tileWidth,tileHeight);
        rabbit_down[1] = rabbit.crop(tileWidth,0,tileWidth,tileHeight);
        rabbit_down[2] = rabbit.crop(tileWidth*2,0,tileWidth,tileHeight);
        rabbit_down[3] = rabbit.crop(tileWidth*3,0,tileWidth,tileHeight);

        rabbit_left[0] = rabbit.crop(0,tileHeight,tileWidth,tileHeight);
        rabbit_left[1] = rabbit.crop(tileWidth,tileHeight,tileWidth,tileHeight);
        rabbit_left[2] = rabbit.crop(tileWidth*2,tileHeight,tileWidth,tileHeight);
        rabbit_left[3] = rabbit.crop(tileWidth*3,tileHeight,tileWidth,tileHeight);

        rabbit_right[0] = rabbit.crop(0,tileHeight*2,tileWidth,tileHeight);
        rabbit_right[1] = rabbit.crop(tileWidth,tileHeight*2,tileWidth,tileHeight);
        rabbit_right[2] = rabbit.crop(tileWidth*2,tileHeight*2,tileWidth,tileHeight);
        rabbit_right[3] = rabbit.crop(tileWidth*3,tileHeight*2,tileWidth,tileHeight);

        rabbit_up[0] = rabbit.crop(0,tileHeight*3,tileWidth,tileHeight);
        rabbit_up[1] = rabbit.crop(tileWidth,tileHeight*3,tileWidth,tileHeight);
        rabbit_up[2] = rabbit.crop(tileWidth*2,tileHeight*3,tileWidth,tileHeight);
        rabbit_up[3] = rabbit.crop(tileWidth*3,tileHeight*3,tileWidth,tileHeight);


        //Monsters
        //Dragon
        dragon_down = new BufferedImage[4];
        dragon_left = new BufferedImage[4];
        dragon_right = new BufferedImage[4];
        dragon_up    = new BufferedImage[4];

        dragon_down[0] = dragon.crop(0,0,tileWidth*3,tileHeight*3);
        dragon_down[1] = dragon.crop(tileWidth*3,0,tileWidth*3,tileHeight*3);
        dragon_down[2] = dragon.crop(tileWidth*6,0,tileWidth*3,tileHeight*3);
        dragon_down[3] = dragon.crop(tileWidth*9,0,tileWidth*3,tileHeight*3);

        dragon_left[0] = dragon.crop(0,tileHeight*3,tileWidth*3,tileHeight*3);
        dragon_left[1] = dragon.crop(tileWidth*3,tileHeight*3,tileWidth*3,tileHeight*3);
        dragon_left[2] = dragon.crop(tileWidth*6,tileHeight*3,tileWidth*3,tileHeight*3);
        dragon_left[3] = dragon.crop(tileWidth*9,tileHeight*3,tileWidth*3,tileHeight*3);

        dragon_right[0] = dragon.crop(0,tileHeight*6,tileWidth*3,tileHeight*3);
        dragon_right[1] = dragon.crop(tileWidth*3,tileHeight*6,tileWidth*3,tileHeight*3);
        dragon_right[2] = dragon.crop(tileWidth*6,tileHeight*6,tileWidth*3,tileHeight*3);
        dragon_right[3] = dragon.crop(tileWidth*9,tileHeight*6,tileWidth*3,tileHeight*3);

        dragon_up[0] = dragon.crop(0,tileHeight*9,tileWidth*3,tileHeight*3);
        dragon_up[1] = dragon.crop(tileWidth*3,tileHeight*9,tileWidth*3,tileHeight*3);
        dragon_up[2] = dragon.crop(tileWidth*6,tileHeight*9,tileWidth*3,tileHeight*3);
        dragon_up[3] = dragon.crop(tileWidth*9,tileHeight*9,tileWidth*3,tileHeight*3);

        //Monster Tree
        tree_down = new BufferedImage[4];
        tree_left = new BufferedImage[4];
        tree_right = new BufferedImage[4];
        tree_up   = new BufferedImage[4];

        tree_down[0] = tree.crop(0,0,tileWidth*3,tileHeight*3);
        tree_down[1] = tree.crop(tileWidth*3,0,tileWidth*3,tileHeight*3);
        tree_down[2] = tree.crop(tileWidth*6,0,tileWidth*3,tileHeight*3);
        tree_down[3] = tree.crop(tileWidth*9,0,tileWidth*3,tileHeight*3);

        tree_left[0] = tree.crop(0,tileHeight*3,tileWidth*3,tileHeight*3);
        tree_left[1] = tree.crop(tileWidth*3,tileHeight*3,tileWidth*3,tileHeight*3);
        tree_left[2] = tree.crop(tileWidth*6,tileHeight*3,tileWidth*3,tileHeight*3);
        tree_left[3] = tree.crop(tileWidth*9,tileHeight*3,tileWidth*3,tileHeight*3);

        tree_right[0] = tree.crop(0,tileHeight*6,tileWidth*3,tileHeight*3);
        tree_right[1] = tree.crop(tileWidth*3,tileHeight*6,tileWidth*3,tileHeight*3);
        tree_right[2] = tree.crop(tileWidth*6,tileHeight*6,tileWidth*3,tileHeight*3);
        tree_right[3] = tree.crop(tileWidth*9,tileHeight*6,tileWidth*3,tileHeight*3);

        tree_up[0] = tree.crop(0,tileHeight*9,tileWidth*3,tileHeight*3);
        tree_up[1] = tree.crop(tileWidth*3,tileHeight*9,tileWidth*3,tileHeight*3);
        tree_up[2] = tree.crop(tileWidth*6,tileHeight*9,tileWidth*3,tileHeight*3);
        tree_up[3] = tree.crop(tileWidth*9,tileHeight*9,tileWidth*3,tileHeight*3);

        //Beast 3 heads
        beast_down = new BufferedImage[4];
        beast_left = new BufferedImage[4];
        beast_right = new BufferedImage[4];
        beast_up   = new BufferedImage[4];

        beast_down[0] = beast.crop(0,0,tileWidth*2,tileHeight*2);
        beast_down[1] = beast.crop(tileWidth*2,0,tileWidth*2,tileHeight*2);
        beast_down[2] = beast.crop(tileWidth*4,0,tileWidth*2,tileHeight*2);
        beast_down[3] = beast.crop(tileWidth*6,0,tileWidth*2,tileHeight*2);

        beast_left[0] = beast.crop(0,tileHeight*2,tileWidth*2,tileHeight*2);
        beast_left[1] = beast.crop(tileWidth*2,tileHeight*2,tileWidth*2,tileHeight*2);
        beast_left[2] = beast.crop(tileWidth*4,tileHeight*2,tileWidth*2,tileHeight*2);
        beast_left[3] = beast.crop(tileWidth*6,tileHeight*2,tileWidth*2,tileHeight*2);

        beast_right[0] = beast.crop(0,tileHeight*4,tileWidth*2,tileHeight*2);
        beast_right[1] = beast.crop(tileWidth*2,tileHeight*4,tileWidth*2,tileHeight*2);
        beast_right[2] = beast.crop(tileWidth*4,tileHeight*4,tileWidth*2,tileHeight*2);
        beast_right[3] = beast.crop(tileWidth*6,tileHeight*4,tileWidth*2,tileHeight*2);

        beast_up[0] = beast.crop(0,tileHeight*6,tileWidth*2,tileHeight*2);
        beast_up[1] = beast.crop(tileWidth*2,tileHeight*6,tileWidth*2,tileHeight*2);
        beast_up[2] = beast.crop(tileWidth*4,tileHeight*6,tileWidth*2,tileHeight*2);
        beast_up[3] = beast.crop(tileWidth*6,tileHeight*6,tileWidth*2,tileHeight*2);

        //Tiles
        //Map 1
        grass = sheet.crop(tileWidth*14, tileHeight*7,tileWidth,tileHeight);
        water = watersheet.crop(0, 0, tileWidth, tileHeight);

        //Rocks
        rock = sheet.crop(tileWidth*11,tileHeight*15,tileWidth,tileHeight*2);
        rock1 = sheet.crop(tileWidth*12,tileHeight*15,tileWidth,tileHeight);
        rock2 = sheet.crop(tileWidth*12,tileHeight*18,tileWidth,tileHeight);

        //Trees
        cuttree = sheet.crop(tileWidth*4,0,tileWidth,tileHeight);
        tree0 = t0.crop(0,0,tileWidth*8,tileHeight*8);
        tree1 = t1.crop(0,0,tileWidth*16,tileHeight*22);
        tree2 = t2.crop(0,0,tileWidth*14,tileHeight*22);
        tree3 = t3.crop(0,0,tileWidth*19,tileHeight*22);
        edgetree = margtree.crop(0,0,tileWidth*12,tileHeight*23);

        //Cave
        cave = home.crop(0,0,tileWidth*6,tileHeight*7);

        //Logs
        log1 = sheet.crop(0,tileHeight*2,tileWidth*2, tileHeight);
        log2 = sheet.crop(tileWidth*9,0,tileWidth,tileHeight*2);


        //Plants
        bush = sheet.crop(tileWidth*4,tileHeight*12,tileWidth*3,tileHeight*3);
        yellowflower = sheet.crop(tileWidth*6,0,tileWidth,tileHeight);
        redflower = sheet.crop(tileWidth*7,tileHeight*13,tileWidth,tileHeight);

        //Drops
        drop_tree = ImageLoader.LoadImage("/textures/drop_wood.png");
        drop_rock = sheet.crop(tileWidth*12,tileHeight*15,tileWidth,tileHeight);

        portal = new BufferedImage[3];

        portal[0] = sheet.crop(tileWidth*12,tileHeight*26,tileWidth*5,tileHeight*5);
        portal[1] = sheet.crop(tileWidth*6,tileHeight*26,tileWidth*5,tileHeight*5);
        portal[2] = sheet.crop(0,tileHeight*26,tileWidth*5,tileHeight*5);

        //Map 2
        soil1 = sheet.crop(tileWidth*9, tileHeight*11, tileWidth, tileHeight);
        soil2 = sheet.crop(tileWidth*10, tileHeight*3, tileWidth, tileHeight);
        soil_rocks = sheet.crop(tileWidth*7, tileHeight*14, tileWidth, tileHeight);
        edgerock = sheet.crop(tileWidth*12,tileHeight*16,tileWidth,tileHeight*2);
    }


    //Folosesc aceasta functie pentru a pune un timer intre atacurile player-ului
    public static boolean attackTimeElapsed()
    {
        if(!passed)
        {
            then = System.nanoTime();
            passed = true;
        }
        now = System.nanoTime();
        if(now-then>1000000000/1.66)
        {
            passed = false;
            return true;
        }
        return false;
    }
    public static boolean monsterAttackTimeElapsed()
    {
        if(!monster_passed)
        {
            monster_then = System.nanoTime();
            monster_passed = true;
        }
        monster_now = System.nanoTime();
        if(monster_now-monster_then>1000000000/1.66)
        {
            monster_passed = false;
            return true;
        }
        return false;
    }


    //Folosesc aceasta functie pentru a pune un delay la atacul player-ului pentru ca damage-ul sa fie dat dupa acest delay, nu inainte
    public static boolean secondElapsed()
    {
        if(!passed)
        {
            then= System.nanoTime();
            passed=true;
        }
        now=System.nanoTime();
        if(now-then>=1000000000)
        {
            passed=false;
            return true;
        }
        return false;
    }
}
