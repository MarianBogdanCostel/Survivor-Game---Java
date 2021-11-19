package PaooGame.Items.Statics.Trees;

import PaooGame.Graphics.Assets;


import PaooGame.Items.Item;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class EdgeTree extends Item {
    public EdgeTree(RefLinks refLinks, float x, float y)
    {
        super(refLinks,x,y, Tile.TILE_WIDTH*5,Tile.TILE_HEIGHT*5);

        //Coliziuni
        bounds.x = 52;
        bounds.y = 38;
        bounds.width = width/3;
        bounds.height = (int) (height - height / 1.15f);
    }

    @Override
    public void Update(){
        active = true;
    }
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.edgetree, (int)x, (int)y, width, height,null);
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
