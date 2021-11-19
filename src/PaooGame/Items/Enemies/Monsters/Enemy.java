package PaooGame.Items.Enemies.Monsters;

import PaooGame.Graphics.Animation;
import PaooGame.Graphics.Assets;
import PaooGame.Items.Character;
import PaooGame.Items.Enemies.Monsters.Dragon;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

public class Enemy extends Character {

    private int width, height;
    public boolean attack = false;
    private int k;
    protected Animation animDown, animUp, animLeft, animRight, lastAnim;
    protected Hero hero;
    protected Dragon dragon;
    protected int direction = 0;
    protected int difficulty;
    //0 - JOS
    //1 - SUS
    //2 - STANGA
    //3 - DREAPTA

    /*! \fn public Enemy(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Enemy.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a inamicului.
        \param y Pozitia initiala pe axa Y a inamicului.
     */
    public Enemy(RefLinks refLink, float x, float y)
    {
        ///Apel al constructorului clasei de baza
        super(refLink, x, y,Character.DEFAULT_CREATURE_WIDTH,Character.DEFAULT_CREATURE_HEIGHT);
        Character.DEFAULT_CREATURE_WIDTH = width;
        Character.DEFAULT_CREATURE_HEIGHT = height;

        try{
        difficulty = refLink.GetGame().getDataBase().getDifficulty();
        } catch(SQLException e){
            System.out.println(e.getMessage());
    }

    }


    @Override
    public void Update() {
        animDown.Update();
        animUp.Update();
        animLeft.Update();
        animRight.Update();
        lastAnim.Update();
        Move();
    }

    public void Update(Hero hero){
        Update();
        attacked(hero);
        Move();
    }


    @Override
    public void Draw(Graphics g) {

    }


    @Override
    public void die() {

    }

    protected void attacked(Hero hero){
        if(!hero.Dead()){
            if(getCollisionBounds(0,0).intersects(hero.getAttackBounds(0,0))){
                if(refLink.GetKeyManager().attack_button && Assets.attackTimeElapsed()){
                    current_life = current_life - Hero.getInstance(refLink,0,0).getDamage();
                }
            }
        }
    }

    protected void monsterAttack(Hero hero) {
        if (!hero.Dead() && current_life > 0) {
            if (xMove == 0 && yMove == 0) {
                attack = true;
                if (hero.getCollisionBounds(0, 0).intersects(this.getAttackBounds(0, 0)) || hero.getCollisionBounds(0,0).intersects(this.getCollisionBounds(0,0))) {
                    if (hero.getCurrentLife() >= 0 && Assets.monsterAttackTimeElapsed()) {
                        if(difficulty == 2)
                            hero.setCurrentLife(hero.getCurrentLife() - 10);
                        if(difficulty == 3)
                            hero.setCurrentLife(hero.getCurrentLife() - 30);
                        if(difficulty == 1)
                            hero.setCurrentLife(hero.getCurrentLife() - 5);
                        attack = false;
                    }
                }
            }
        }
    }



}
