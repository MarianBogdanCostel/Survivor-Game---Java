package PaooGame.Items.Statics.Trees;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Drop_Item;
import PaooGame.Items.Item;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Tree2 extends Item {
    public Tree2(RefLinks refLinks, float x, float y)
    {
        super(refLinks,x,y, Tile.TILE_WIDTH*3,Tile.TILE_HEIGHT*5);

        //Coliziuni
        bounds.x = 46;
        bounds.y = 140;
        bounds.width = width/32;
        bounds.height = (int) (height - height / 1.05f);
    }

    @Override
    public void Update(){}

    @Override
    public void Draw(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect((int) x+16,(int)  y, 64, 5);

        g.setColor(Color.green);
        g.fillRect((int) x+16,(int)  y, (64*current_life)/life, 5);

        g.setColor(Color.white);
        g.drawRect((int) x+16,(int)  y, 64, 5);
        g.drawImage(Assets.tree2, (int)x, (int)y, width, height,null);
        //g.setColor(Color.green);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }

    @Override
    public void die() {
        refLink.GetMap().getDropManager().addItem(Drop_Item.woodItem.createNew((int)x+32, (int)y+96));
    }

    public boolean IsSolid()
    {
        return true;
    }
}