package PaooGame.Items.Enemies.Monsters;

import PaooGame.Graphics.Animation;
import PaooGame.Graphics.Assets;

import PaooGame.Items.Drop_Item;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Dragon extends Enemy {
    protected int width, height;

    public Dragon(RefLinks refLink, float x, float y) {
        super(refLink, x, y);
        width = 128;
        height = 128;
        speed = 0.9f;
        life = 500;
        current_life = life;

        //Animatii
        animDown = new Animation(150, Assets.dragon_down);
        animUp = new Animation(150, Assets.dragon_up);
        animLeft = new Animation(150, Assets.dragon_left);
        animRight = new Animation(150, Assets.dragon_right);
        lastAnim = new Animation(150,Assets.dragon_right);

        //Bounds
        normalBounds.x = 30;
        normalBounds.y = 70;
        normalBounds.width = 80;
        normalBounds.height = 58;

    }

    @Override
    public void Update(Hero hero){
        super.Update(hero);
        monsterAttack(hero);
        if(hero.GetX()>32 && hero.GetX()<576 && hero.GetY()>450 && hero.GetY()<740)
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
                if(diffy>=35)
                {
                    yMove = -speed;
                    attack = false;
                }
            }

            if(y<hero.GetY())
            {
                if(diffy>=100)
                {
                    yMove = speed;
                    attack = false;
                }
            }

            if(diffX>=100)
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
        for(int i=0;i<20;i++) {
            refLink.GetMap().getDropManager().addItem(Drop_Item.woodItem.createNew((int) x + i, (int) y + i));
            refLink.GetMap().getDropManager().addItem(Drop_Item.rockItem.createNew((int)(50+x+i), (int)(50+y+i)));
        }
    }

    private BufferedImage getCurrentAnimationFrame(){
        if (xMove < 0) {
            lastAnim = new Animation(100, Assets.dragon_left);
            direction = 2;          //Left
            attackbounds.x = -5;
            attackbounds.y = 30;
            attackbounds.width = 40;
            attackbounds.height = 95;
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            lastAnim = new Animation(100,Assets.dragon_right);
            direction = 3;          //Right
            attackbounds.x = 100;
            attackbounds.y = 30;
            attackbounds.width = 36;
            attackbounds.height = 95;
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            lastAnim = new Animation(100,Assets.dragon_up);
            direction = 1;          //Up
            attackbounds.x = 0;
            attackbounds.y = 20;
            attackbounds.width = 128;
            attackbounds.height = 80;
            return animUp.getCurrentFrame();
        } else if (yMove > 0) {
            lastAnim = new Animation(100,Assets.dragon_down);
            direction = 0;          //Down
            attackbounds.x = 16;
            attackbounds.y = 50;
            attackbounds.width = 100;
            attackbounds.height = 100;
            return animDown.getCurrentFrame();
        } else
            return lastAnim.getFirstFrame();
    }
}
