package PaooGame.Graphics;

import java.awt.image.BufferedImage;

public class Animation {

    private int speed, index;
    private BufferedImage[] frames;     //vectorul ce contine imaginile pentru animatie
    private long lastTime, timer;

    public Animation(int speed, BufferedImage[] frames)
    {
        this.speed=speed;
        this.frames=frames;
        index = 0;
        timer = 0;
        lastTime=System.currentTimeMillis();
    }

    public void Update(){
        timer += System.currentTimeMillis() - lastTime;     //calculeaza timpul trecut de la ultimul update
        lastTime=System.currentTimeMillis();

        //Ma asigur ca atunci cand index-ul depaseste numarul de imaginii reincepe de la imaginea initiala
        if(timer > speed){
            index++;
            timer=0;
            if(index >= frames.length)
                index = 0;
        }
    }

    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

    public void IndexZero(){
        index = 0;
    }

    public BufferedImage getFirstFrame(){
        return frames[0];
    }
}
