package university.tictactoe.utils;

import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import university.tictactoe.R;
import university.tictactoe.activities.MainActivity;

public class AI {


    public AI() {
    }

    public void move(){
        boolean temp = true;

        while(temp){

            int i = new Random().nextInt(3);
            int j = new Random().nextInt(3);

            if(MainActivity.board[i][j] == 0){
                String position = String.valueOf(i+1) + String.valueOf(j+1);
                for(ImageView imageView : MainActivity.imageViews){
                    if(imageView.getTag().equals(position)){

                        MainActivity.board[i][j] = 2;

                        imageView.setBackgroundResource(R.drawable.circle);

                        temp = false;
                    }
                }
            }
        }
        if (MainActivity.checkIfAIWon()){
            Toast.makeText(MainActivity.context, "AI has won", Toast.LENGTH_SHORT).show();
            MainActivity.stillPlaying = false;
        }
    }
}
