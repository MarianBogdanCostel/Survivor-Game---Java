package PaooGame.Items.Enemies.Animals;

import PaooGame.Graphics.Animation;
import PaooGame.Graphics.Assets;
import PaooGame.Items.Character;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animals extends Character {


    private int width, height;
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a caracterului.*/
    public boolean attack;
    protected Animation animDown, animUp, animLeft, animRight, lastAnim;
    private int tick=0;
    protected Hero hero;

    /*! \fn public Enemy(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Enemy.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a inamicului.
        \param y Pozitia initiala pe axa Y a inamicului.
     */
    public Animals(RefLinks refLink, float x, float y) {
        ///Apel al constructorului clasei de baza
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        Character.DEFAULT_CREATURE_WIDTH = width;
        Character.DEFAULT_CREATURE_HEIGHT = height;
    }


    @Override
    public void Update() {
        //Animatii
        animDown.Update();
        animUp.Update();
        animLeft.Update();
        animRight.Update();
        lastAnim.Update();

        tick++;

        xMove = 0;
        yMove = 0;

          if(tick<60) {
                xMove = speed;
                yMove=0;
          }
          else if(tick>60 && tick<120)
          {
              xMove=0;
              yMove=-speed;
          }
          else if(tick>120 && tick<180){
              yMove=0;
              xMove=-speed;
          }
          else if(tick>180 && tick<240){
              yMove=speed;
              xMove=0;
          }
          else if (tick>240)
              tick=0;

    //Actualizeaza pozitia
    Move();

}

    @Override
    public void Draw(Graphics g) {
    }


    @Override
    public void die() {
    }


}

