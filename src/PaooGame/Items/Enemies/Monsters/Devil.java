package PaooGame.Items.Enemies.Monsters;

import PaooGame.Graphics.Animation;
import PaooGame.Graphics.Assets;
import PaooGame.Items.Drop_Item;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;
import PaooGame.States.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Devil extends Enemy {
    private int tick=0;
    protected int width, height;

    public Devil(RefLinks refLink, float x, float y) {
        super(refLink, x, y);
        width = 128;
        height = 128;
        speed = 0.2f;
        life = 500;
        current_life = life;

        //Animatii
        animDown = new Animation(150, Assets.devil_down);
        animUp = new Animation(150, Assets.devil_up);
        animLeft = new Animation(150, Assets.devil_left);
        animRight = new Animation(150, Assets.devil_right);
        lastAnim = new Animation(150,Assets.devil_left);

        //Bounds
        normalBounds.x = 45;
        normalBounds.y = 70;
        normalBounds.width = 38;
        normalBounds.height = 52;

    }

    @Override
    public void Update(Hero hero){
        super.Update(hero);
        monsterAttack(hero);
        tracking(hero);
    }


    @Override
    public void Draw(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect((int) x,(int)  y, 128, 10);

        g.setColor(Color.red);
        g.fillRect((int) x,(int)  y, (128*current_life)/life, 10);

        g.setColor(Color.white);
        g.drawRect((int) x,(int)  y, 128, 10);
        g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
        ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
        //g.setColor(Color.red);
        //g.drawRect((int)(x+attackbounds.x), (int)(y+attackbounds.y), attackbounds.width, attackbounds.height);
    }


    protected void tracking(Hero hero)
    {
        float diffX = Math.abs(hero.GetX()-x);
        float diffy = Math.abs(hero.GetY()-y);

        if(!hero.Dead())
        {
            xMove = 0;
            yMove = 0;
            if(y>hero.GetY())
            {
                if(diffy>=25)
                {
                    yMove = -speed;
                    attack = false;
                }
            }

            if(y<hero.GetY())
            {
                if(diffy>=34)
                {
                    yMove = speed;
                    attack = false;
                }
            }

            if(diffX>=24)
            {
                if(x>hero.GetX())
                {
                    xMove = -speed;
                    attack = false;
                }
                if(x<hero.GetX())
                {
                    xMove = speed;
                    attack = false;
                }
            }
            Move();
        }
    }

    @Override

    protected void monsterAttack(Hero hero) {
        if (!hero.Dead() && current_life > 0) {
            if (xMove == 0 && yMove == 0) {
                attack = true;
                if (hero.getCollisionBounds(0, 0).intersects(this.getAttackBounds(0, 0)) || hero.getCollisionBounds(0,0).intersects(this.getCollisionBounds(0,0))) {
                    if (hero.getCurrentLife() >= 0 && Assets.monsterAttackTimeElapsed()) {
                        if(difficulty == 2)
                            hero.setCurrentLife(hero.getCurrentLife() - 30);
                        if(difficulty == 3)
                            hero.setCurrentLife(hero.getCurrentLife() - 40);
                        if(difficulty == 1)
                            hero.setCurrentLife(hero.getCurrentLife() - 20);
                        attack = false;
                    }
                }
            }
        }
    }
    @Override
    public void die() {
        State.SetState(refLink.GetGame().GetWonState());
    }

    private BufferedImage getCurrentAnimationFrame(){
        if (xMove < 0) {
            lastAnim = new Animation(100, Assets.devil_left);
            direction = 2;          //Left
            attackbounds.x = 30;
            attackbounds.y = 30;
            attackbounds.width = 40;
            attackbounds.height = 95;
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            lastAnim = new Animation(100,Assets.devil_right);
            direction = 3;          //Right
            attackbounds.x = 56;
            attackbounds.y = 30;
            attackbounds.width = 40;
            attackbounds.height = 95;
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            lastAnim = new Animation(100,Assets.devil_up);
            direction = 1;          //Up
            attackbounds.x = 0;
            attackbounds.y = 15;
            attackbounds.width = 124;
            attackbounds.height = 60;
            return animUp.getCurrentFrame();
        } else if (yMove > 0) {
            lastAnim = new Animation(100,Assets.devil_down);
            direction = 0;          //Down
            attackbounds.x = 0;
            attackbounds.y = 80;
            attackbounds.width = 124;
            attackbounds.height = 60;
            return animDown.getCurrentFrame();
        } else
            return lastAnim.getFirstFrame();
    }
}


