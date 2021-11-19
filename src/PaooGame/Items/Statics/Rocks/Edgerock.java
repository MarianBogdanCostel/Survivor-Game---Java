package PaooGame.Items.Statics.Rocks;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Drop_Item;
import PaooGame.Items.Item;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Edgerock extends Item {
    public Edgerock(RefLinks refLinks, float x, float y)
    {
        super(refLinks,x,y, Tile.TILE_WIDTH,Tile.TILE_HEIGHT*2);

        //Coliziuni
        bounds.x = 4;
        bounds.y = 30;
        bounds.width = 22;
        bounds.height = 25;
    }

    @Override
    public void Update(){
        active = true;
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.edgerock, (int)x, (int)y, width, height,null);
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
