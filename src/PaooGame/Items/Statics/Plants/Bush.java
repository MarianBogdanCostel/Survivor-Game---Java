package PaooGame.Items.Statics.Plants;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Item;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Bush extends Item {
    public Bush(RefLinks refLinks, float x, float y)
    {
        super(refLinks,x,y, Tile.TILE_WIDTH*3,Tile.TILE_HEIGHT*3);

        //Coliziuni
        bounds.x = 32;
        bounds.y = 36;
        bounds.width = width/2-13;
        bounds.height = (int) (height/2-3);
    }

    @Override
    public void Update(){
        active = true;
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.bush, (int)x, (int)y, width, height,null);
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
