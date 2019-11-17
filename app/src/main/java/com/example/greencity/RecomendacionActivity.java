package com.example.greencity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class RecomendacionActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, YouTubePlayer.PlaybackEventListener{

    YouTubePlayerView YoutubePlayerView;
    String claveYoutube="AIzaSyAPpKA8lPdTyUEZn4Xfy-hoEldt4jKiyGA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendacion);

        YoutubePlayerView=(YouTubePlayerView)findViewById(R.id.youtube_view);
        YoutubePlayerView.initialize(claveYoutube,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean fuerestaurado) {

        if(!fuerestaurado){

            //youTubePlayer.cueVideo("_6Wgwx4aTeY"); //https://www.youtube.com/watch?v=_6Wgwx4aTeY
            //https://www.youtube.com/watch?v=-UFFFUTMlCw
            youTubePlayer.cueVideo("-UFFFUTMlCw");
        }


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        if(youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, 1).show();
        }else{

            String error="Error al iniciar Youtube"+youTubeInitializationResult.toString();
            Toast.makeText(getApplication(),error,Toast.LENGTH_LONG).show();

        }
    }


    protected void onActivityResult(int requestCode, int resultcode, Intent data){

        if(resultcode==1){

            getYoutubePlayerProvider().initialize(claveYoutube,this);
        }

    }

    protected YouTubePlayer.Provider getYoutubePlayerProvider(){

        return YoutubePlayerView;

    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }

}
