package PaooGame.Items.Statics.Logs;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Item;


import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Log1 extends Item {
    public Log1(RefLinks refLinks, float x, float y)
    {
        super(refLinks,x,y, Tile.TILE_WIDTH*2,Tile.TILE_HEIGHT);

        //Coliziuni
        bounds.x = 0;
        bounds.y = 8;
        bounds.width = (int) (width-3);
        bounds.height = 15;
    }

    @Override
    public void Update(){
        active = true;
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.log1, (int)x, (int)y, width, height,null);
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }

    @Override
    public void die() {

    }


    public boolean IsSolid()
    {
        return true;
    }
}
