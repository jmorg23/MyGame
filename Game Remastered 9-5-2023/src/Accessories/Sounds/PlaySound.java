package src.Accessories.Sounds;

import java.io.FileInputStream;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
public class PlaySound {

    private static AdvancedPlayer player;


    public static void playSound(String filePath) {
        try {

            FileInputStream fileInputStream = new FileInputStream(filePath);
            player = new AdvancedPlayer(fileInputStream);

            player.setPlayBackListener(new PlaybackListener() {

                @Override
                public void playbackFinished(PlaybackEvent evt) {

                }
            });

            Thread playerThread = new Thread(() -> {
                try {
                    player.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            playerThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void stopMusic() {
        player.stop();
    }

}
