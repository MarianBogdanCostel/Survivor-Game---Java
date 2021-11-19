package PaooGame.Inventory;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.Items.Drop_Item;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory {
    private RefLinks refLinks;
    protected int wood, rocks;
    private boolean active = false;
    private ArrayList<Drop_Item> inventoryItems;

    private int invX = 335, invY = 121, invWidth = 612, invHeight = 484, invListCenterX = invX + 200, invListCenterY = invY + invHeight/2+27;

    private int invListSpacing = 37;

    private int invImageX = 806, invImageY = 210, invImageWidth = 64, invImageHeight = 64;

    private int invCountX = 838, invCountY = 308;

    private int selectedItem = 0;   //reprezinta indicele din vectorul inventoryItems



    public Inventory(RefLinks refLinks){
        this.refLinks = refLinks;
        inventoryItems = new ArrayList<Drop_Item>();

    }

    public void Update(){
        if(refLinks.GetKeyManager().keyJustPressed(KeyEvent.VK_I))
            active = !active;
        if(!active)
            return;

        if(refLinks.GetKeyManager().keyJustPressed(KeyEvent.VK_UP))
            selectedItem--;
        if(refLinks.GetKeyManager().keyJustPressed(KeyEvent.VK_DOWN))
            selectedItem++;

        if(selectedItem < 0)
            selectedItem = inventoryItems.size() - 1;
        else if(selectedItem >= inventoryItems.size())
            selectedItem = 0;

    }

    public void Draw(Graphics g){
        if(!active)
            return;

        g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);


        int len = inventoryItems.size();
        if(len == 0)
            return;

        for(int i = -5; i < 6; i++){
            if(selectedItem + i < 0 || selectedItem + i >= len)
                continue;
            if(i==0)
            {
                Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName()+" <",invListCenterX, invListCenterY + i * invListSpacing, true, Color.RED,Assets.font30);
            }else{
                Text.drawString(g, inventoryItems.get(selectedItem + i).getName(),invListCenterX, invListCenterY + i * invListSpacing, true, Color.WHITE,Assets.font30);

            }

            Drop_Item item = inventoryItems.get(selectedItem);
            g.drawImage(item.getTexture(), invImageX, invImageY,invImageWidth,invImageHeight,null);
            Text.drawString(g,Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.font30);
            if(item.getId() == 0)
                wood = item.getCount();
            if(item.getId() == 1)
                rocks = item.getCount();
        }
    }

    public void addDrop_Item(Drop_Item drop_item){
        for(Drop_Item i : inventoryItems){
            if(i.getId() == drop_item.getId()){
                i.setCount(i.getCount() + drop_item.getCount());
                return;
            }
        }
        inventoryItems.add(drop_item);
    }

    public RefLinks getRefLinks(){
        return refLinks;
    }

    public void setRefLinks(RefLinks refLinks)
    {
        this.refLinks = refLinks;
    }

    public boolean isActive() {
        return active;
    }

    public int getWood()
    {
        return wood;
    }

    public int getRocks()
    {
        return rocks;
    }

}
