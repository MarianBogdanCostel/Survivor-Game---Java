package PaooGame.Items.Statics;

import PaooGame.Graphics.Animation;
import PaooGame.Graphics.Assets;
import PaooGame.Items.Item;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Portal extends Item {
    private Animation portal;

    public Portal(RefLinks refLinks,float x, float y)
    {
        super(refLinks,x,y, Tile.TILE_WIDTH*4,Tile.TILE_HEIGHT*4);

        //Coliziuni
        bounds.x = width/2;
        bounds.y = height/2;
        bounds.width = 0;
        bounds.height = 0;



        portal = new Animation(260,Assets.portal);
    }


    @Override
    public void Update() {
        portal.Update();
        active = true;
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(portal.getCurrentFrame(), (int)x, (int)y, width, height,null);
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }

    @Override
    public void die() {

    }

    public boolean IsSolid()
    {
        return false;
    }

}
