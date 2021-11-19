package PaooGame.Graphics;
import javax.sound.sampled.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Audio {
    private Clip clip;
    private FloatControl gainControl;  //setarea volumului

    public Audio(String path) {
        File file = new File(path);

        try {
            AudioInputStream aud = AudioSystem.getAudioInputStream(file);

            AudioFormat baseFormat = aud.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false);

            AudioInputStream daud = AudioSystem.getAudioInputStream(decodeFormat, aud);

            clip = AudioSystem.getClip();
            clip.open(daud);

            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if(clip == null) return;
        stop();
        clip.setFramePosition(0);
        clip.start();
    }


    public void stop() {
        if(clip.isRunning()) clip.stop();
    }

    public void reset() {
        clip.setMicrosecondPosition(0);
    }

    public void close() {
        stop();
        clip.close();
    }

    public void setVolume(double gain) {
        float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }


    public Clip getClip() {
        return clip;
    }


    public void setClip(Clip clip) {
        this.clip = clip;
    }
}
