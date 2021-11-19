package PaooGame.Items.Statics.Rocks;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Drop_Item;

import PaooGame.Items.Item;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Rock2 extends Item {
    public Rock2(RefLinks refLinks, float x, float y)
    {
        super(refLinks,x,y, Tile.TILE_WIDTH,Tile.TILE_HEIGHT);

        life=30;
        current_life = life;
        //Coliziuni
        bounds.x = 0;
        bounds.y = (int)(height/2);
        bounds.width = (int) width;
        bounds.height = (int) (height/2-5);
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

        g.drawImage(Assets.rock2, (int)x, (int)y, width, height,null);
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }

    @Override
    public void die() {
        refLink.GetMap().getDropManager().addItem(Drop_Item.rockItem.createNew((int)x, (int)y));
    }

    public boolean IsSolid()
    {
        return true;
    }

}
