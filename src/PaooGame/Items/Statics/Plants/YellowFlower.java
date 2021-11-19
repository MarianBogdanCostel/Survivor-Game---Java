package PaooGame.Items.Statics.Plants;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Hero;
import PaooGame.Items.Item;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.sql.SQLException;

public class YellowFlower extends Item {
    private int difficulty;
    public YellowFlower(RefLinks refLinks, float x, float y)
    {
        super(refLinks,x,y, Tile.TILE_WIDTH,Tile.TILE_HEIGHT);
        life = 20;
        current_life = 20;

        try{
            difficulty = refLink.GetGame().getDataBase().getDifficulty();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

        //Coliziuni
        bounds.x = 7;
        bounds.y = 7;
        bounds.width = width-16;
        bounds.height = (int) (height-16);
    }

    @Override
    public void Update(){}

    @Override
    public void Draw(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect((int) x,(int)  y-8, 32, 5);

        g.setColor(Color.green);
        g.fillRect((int) x,(int)  y-8, (32*current_life)/life, 5);

        g.setColor(Color.white);
        g.drawRect((int) x,(int)  y-8, 32, 5);

        g.drawImage(Assets.yellowflower, (int)x, (int)y, width, height,null);
        //g.setColor(Color.blue);
       // g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }

    @Override
    public void die() {
        if(Hero.getInstance(refLink,0,0).getCurrentLife() <100)
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
    }

    public boolean IsSolid()
    {
        return true;
    }
}
