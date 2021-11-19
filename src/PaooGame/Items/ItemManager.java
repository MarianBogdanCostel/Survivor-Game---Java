package PaooGame.Items;

import PaooGame.Items.Enemies.Monsters.Dragon;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

//Clasa care se ocupa de toate itemele din joc
public class ItemManager {

    private RefLinks refLinks;
    private Hero hero;

    private ArrayList<Item> items;  //Un vector ce retine itemele din joc

    //Folosim renderSorter pentru randarea itemelor in ordinea dorita
    private Comparator<Item> renderSorter = new Comparator<Item>() {        //Comparam itemele
        public int compare(Item a, Item b){
            if(a.GetY()+a.GetHeight() < b.GetY()+b.GetHeight())   //Comparam coordonatele itemelor pe axa Y in partea jos jos, de asta adunam Height
                return -1;      //Inseamna ca a trebuie randat inaintea lui b, a are coordonata Y mai mica decat b
            return 1;           //Inseamna ca a trebuie randat dupa b
        }
    };

    public ItemManager(RefLinks refLinks, Hero hero){
        this.refLinks = refLinks;
        this.hero = hero;
        items = new ArrayList<>();
        addItem(hero);
    }

    public void Update(){

        Iterator<Item> it = items.iterator();
        while(it.hasNext()){
            Item e = it.next();
            e.Update();
            if(!e.isActive())       //Verificam daca item-ul are valoarea active false
            {
                Hero.AddtoScore(10);
                it.remove();    //Daca da, stergem item-ul
            }
        }
        items.sort(renderSorter);       //Sortam itemele din joc
    }

    public void Draw(Graphics g){
        for(Item e : items){
            e.Draw(g);
        }
        hero.postDraw(g);
    }

    public void removeItem(Item item) { items.remove(item); }

    public void addItem(Item e){
        items.add(e);
    }

    //GETTERS & SETTERS
    public RefLinks getRefLinks(){
        return refLinks;
    }

    public Hero getHero(){
        return hero;
    }

    public void setRefLinks(RefLinks refLinks){
        this.refLinks=refLinks;
    }

    public void setHero(Hero hero){
        this.hero=hero;
    }

    public ArrayList<Item> getItems(){
        return items;
    }
}
