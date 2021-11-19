package PaooGame.Items.Enemies.Monsters;

import PaooGame.Graphics.Animation;
import PaooGame.Graphics.Assets;
import PaooGame.Items.Drop_Item;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CarnivorTree extends Enemy {
    protected int width, height;

    public CarnivorTree(RefLinks refLink, float x, float y) {
        super(refLink, x, y);
        width = 128;
        height = 128;
        speed = 1.2f;
        life = 200;
        current_life = life;

        //Animatii
        animDown = new Animation(150, Assets.tree_down);
        animUp = new Animation(150, Assets.tree_up);
        animLeft = new Animation(150, Assets.tree_left);
        animRight = new Animation(150, Assets.tree_right);
        lastAnim = new Animation(150,Assets.tree_right);

        //Bounds
        normalBounds.x = 32;
        normalBounds.y = 85;
        normalBounds.width = 70;
        normalBounds.height = 44;

    }

    @Override
    public void Update(Hero hero){
        super.Update(hero);
        monsterAttack(hero);
        if(hero.GetX()>610 && hero.GetX()<1247 && hero.GetY()>450 && hero.GetY()<740)
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

    @Override
    public void die() {
        for(int i=0;i<10;i++)
            refLink.GetMap().getDropManager().addItem(Drop_Item.woodItem.createNew((int)x+i, (int)y+i));
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
                if(diffy>=20)
                {
                    yMove = -speed;
                    attack = false;
                }
            }

            if(y<hero.GetY())
            {
                if(diffy>=20)
                {
                    yMove = speed;
                    attack = false;
                }
            }

            if(diffX>=20)
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


    private BufferedImage getCurrentAnimationFrame(){
        if (xMove < 0) {
            lastAnim = new Animation(100, Assets.tree_left);
            direction = 2;          //Left
            attackbounds.x = 10;
            attackbounds.y = 60;
            attackbounds.width = 50;
            attackbounds.height = 70;
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            lastAnim = new Animation(100,Assets.tree_right);
            direction = 3;          //Right
            attackbounds.x = 64;
            attackbounds.y = 60;
            attackbounds.width = 60;
            attackbounds.height = 70;
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            lastAnim = new Animation(100,Assets.tree_up);
            direction = 1;          //Up
            attackbounds.x = 5;
            attackbounds.y = 50;
            attackbounds.width = 115;
            attackbounds.height = 64;
            return animUp.getCurrentFrame();
        } else if (yMove > 0) {
            lastAnim = new Animation(100,Assets.tree_down);
            direction = 0;          //Down
            attackbounds.x = 5;
            attackbounds.y = 90;
            attackbounds.width = 113;
            attackbounds.height = 44;
            return animDown.getCurrentFrame();
        } else
            return lastAnim.getFirstFrame();
    }
}
