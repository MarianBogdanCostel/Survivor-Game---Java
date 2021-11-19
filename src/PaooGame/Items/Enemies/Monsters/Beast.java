package PaooGame.Items.Enemies.Monsters;

import PaooGame.Graphics.Animation;
import PaooGame.Graphics.Assets;

import PaooGame.Items.Drop_Item;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Beast extends Enemy {
    protected int width, height;

    public Beast(RefLinks refLink, float x, float y) {
        super(refLink, x, y);
        width = 64;
        height = 64;
        speed = 1.1f;
        life = 200;
        current_life = life;

        //Animatii
        animDown = new Animation(150, Assets.beast_down);
        animUp = new Animation(150, Assets.beast_up);
        animLeft = new Animation(150, Assets.beast_left);
        animRight = new Animation(150, Assets.beast_right);
        lastAnim = new Animation(150,Assets.beast_left);

        //Bounds
        normalBounds.x = 15;
        normalBounds.y = 18;
        normalBounds.width = 35;
        normalBounds.height = 40;
    }

    @Override
    public void Update(Hero hero){
        super.Update(hero);
        monsterAttack(hero);
        if(hero.GetX()>610 && hero.GetX()<1247 && hero.GetY()>26 && hero.GetY()<740)
            tracking(hero);
    }


    @Override
    public void Draw(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect((int) x,(int)  y, 64, 10);

        g.setColor(Color.red);
        g.fillRect((int) x,(int)  y, (64*current_life)/life, 10);

        g.setColor(Color.white);
        g.drawRect((int) x,(int)  y, 64, 10);
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
                if(diffy>=64)
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

            if(diffX>=34)
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
    public void die() {
        for(int i=0;i<10;i++)
            refLink.GetMap().getDropManager().addItem(Drop_Item.rockItem.createNew((int)(x+i), (int)(y+i)));
    }

    private BufferedImage getCurrentAnimationFrame(){
        if (xMove < 0) {
            lastAnim = new Animation(100, Assets.beast_left);
            direction = 2;          //Left
            attackbounds.x = -5;
            attackbounds.y = 0;
            attackbounds.width = 38;
            attackbounds.height = 80;
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            lastAnim = new Animation(100,Assets.beast_right);
            direction = 3;          //Right
            attackbounds.x = 40;
            attackbounds.y = 0;
            attackbounds.width = 35;
            attackbounds.height = 80;
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            lastAnim = new Animation(100,Assets.beast_up);
            direction = 1;          //Up
            attackbounds.x = 0;
            attackbounds.y = 0;
            attackbounds.width = 64;
            attackbounds.height = 38;
            return animUp.getCurrentFrame();
        } else if (yMove > 0) {
            lastAnim = new Animation(100,Assets.beast_down);
            direction = 0;          //Down
            attackbounds.x = 0;
            attackbounds.y = 26;
            attackbounds.width = 60;
            attackbounds.height = 60;
            return animDown.getCurrentFrame();
        } else
            return lastAnim.getFirstFrame();
    }
}
