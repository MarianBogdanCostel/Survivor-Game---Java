package PaooGame.Items.Enemies.Animals;

import PaooGame.Graphics.Animation;
import PaooGame.Graphics.Assets;
import PaooGame.Items.Character;
import PaooGame.Items.Hero;
import PaooGame.Items.Item;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

public class Rabbit extends Animals{
    private int width, height;
    private int direction = 0;      //Voi folosi acest int pentru a retine directie in care se uita animalul
    //0 - JOS
    //1 - SUS
    //2 - STANGA
    //3 - DREAPTA


    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    public boolean attack;
    private int difficulty;

    public Rabbit(RefLinks refLink, float x, float y) {
        super(refLink, x, y);
        width = 32;
        height = 32;
        speed = 2.5f;
        life = 10;
        current_life = life;

        normalBounds.x = 8;
        normalBounds.y = 10;
        normalBounds.width = 16;
        normalBounds.height = 16;

        try{
            difficulty = refLink.GetGame().getDataBase().getDifficulty();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }



        //Animatii
        animDown = new Animation(150, Assets.rabbit_down);
        animUp = new Animation(150, Assets.rabbit_up);
        animLeft = new Animation(150, Assets.rabbit_left);
        animRight = new Animation(150, Assets.rabbit_right);
        lastAnim = new Animation(150,Assets.rabbit_left);

    }


    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update()
    {
        super.Update();
    }

    @Override
    public void die() {
        if(Hero.getInstance(refLink,0,0).getCurrentLife()<100)
        {
            if (difficulty == 1)
            {
                Hero.getInstance(refLink, 0, 0).AddtoLife(30);
            }
            if(difficulty == 2)
            {
                Hero.getInstance(refLink,0,0).AddtoLife(20);
            }
            if(difficulty == 3)
            {
                Hero.getInstance(refLink,0,0).AddtoLife(10);
            }
        }
        else
            Hero.getInstance(refLink,0,0).AddtoLife(0);

        Hero.AddtoScore(10);
    }
    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect((int) x,(int)  y, 32, 10);

        g.setColor(Color.red);
        g.fillRect((int) x,(int)  y, (32*current_life)/life, 10);

        g.setColor(Color.white);
        g.drawRect((int) x,(int)  y, 32, 10);
        g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
        ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }


    private BufferedImage getCurrentAnimationFrame(){
        if (xMove < 0) {
            lastAnim = new Animation(100,Assets.rabbit_left);
            direction = 2;          //Left
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            lastAnim = new Animation(100,Assets.rabbit_right);
            direction = 3;          //Right
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            lastAnim = new Animation(100,Assets.rabbit_up);
            direction = 1;          //Up
            return animUp.getCurrentFrame();
        } else if (yMove > 0) {
            lastAnim = new Animation(100,Assets.rabbit_down);
            direction = 0;          //Down
            return animDown.getCurrentFrame();
        } else
            return lastAnim.getFirstFrame();
    }


}