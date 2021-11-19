package PaooGame.Items;

import PaooGame.RefLinks;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class DropManager {
    private RefLinks refLinks;
    private ArrayList<Drop_Item> items;

    public DropManager(RefLinks refLinks){
        this.refLinks = refLinks;
        items = new ArrayList<Drop_Item>();
    }

    public void Update(){
        Iterator<Drop_Item> it = items.iterator();
        while(it.hasNext()){
            Drop_Item i = it.next();
            i.Update();
            if(i.isPickedUp())
                it.remove();
        }
    }

    public void Draw(Graphics g){
        for(Drop_Item i : items)
            i.Draw(g);
    }

    public void addItem(Drop_Item i)
    {
        i.setRefLinks(refLinks);
        items.add(i);
    }

    public RefLinks getRefLinks(){
        return refLinks;
    }
}
