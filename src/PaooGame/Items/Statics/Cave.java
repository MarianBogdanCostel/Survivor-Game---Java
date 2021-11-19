package PaooGame.Items.Statics;
import PaooGame.Items.Item;
import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Cave extends Item {
    public Cave(RefLinks refLinks, float x, float y)
    {
        super(refLinks,x,y, Tile.TILE_WIDTH*6,Tile.TILE_HEIGHT*7);

        //Coliziuni
        bounds.x = 10;
        bounds.y = 0;
        bounds.width = (int) (width-width/10);
        bounds.height = (int) (height-height/24);
    }


    @Override
    public void Update() {
        active = true;
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.cave, (int)x, (int)y, width, height,null);
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
