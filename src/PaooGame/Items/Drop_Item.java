package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Drop_Item {

    public static Drop_Item[] items = new Drop_Item[256];
    public static Drop_Item woodItem = new Drop_Item(Assets.drop_tree,"WOOD",0);
    public static Drop_Item rockItem = new Drop_Item(Assets.drop_rock,"ROCK",1);


    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

    protected RefLinks refLinks;
    protected BufferedImage texture;
    protected String name;
    protected final int id;

    protected Rectangle bounds;

    protected int x, y, count;
    protected boolean pickedUp = false;

    public Drop_Item(BufferedImage texture, String name, int id){
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;

        bounds = new Rectangle(x,y,ITEMWIDTH,ITEMHEIGHT);
        items[id] = this;
    }

    public void Update(){
        if(refLinks.GetMap().getItemManager().getHero().getCollisionBounds(0f,0f).intersects(bounds))
        {
            pickedUp = true;
            refLinks.GetMap().getItemManager().getHero().getInventory().addDrop_Item(this);
        }
    }

    public void Draw(Graphics g){       //Atunci cand itemele sunt dropate pe harta
        if(refLinks == null)
            return;
        Draw(g,(int) (x),(int) (y));
        //g.setColor(Color.blue);
        //g.fillRect((int)(x), (int)(y), bounds.width, bounds.height);
    }

    public void Draw(Graphics g, int x, int y){
        g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT,null);     //Pentru inventory
    }

    public Drop_Item createNew(int count){
        Drop_Item i= new Drop_Item(texture,name,id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }
    public Drop_Item createNew(int x, int y){
        Drop_Item i= new Drop_Item(texture,name,id);
        i.setPosition(x,y);
        return i;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    // Getters and Setters

    public void setPickedUp(boolean pickedUp)
    {
        this.pickedUp = pickedUp;
    }

    public RefLinks getReflinks() {
        return refLinks;
    }

    public void setRefLinks(RefLinks refLinks) {
        this.refLinks = refLinks;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }


}
