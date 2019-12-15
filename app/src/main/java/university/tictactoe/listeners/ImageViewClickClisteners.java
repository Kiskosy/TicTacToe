package university.tictactoe.listeners;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import university.tictactoe.R;
import university.tictactoe.activities.MainActivity;
import university.tictactoe.utils.AI;


public class ImageViewClickClisteners  implements View.OnClickListener{

    AI ai = new AI();

    @Override
    public void onClick(View v) {

        String position = String.valueOf(v.getTag());
        char i = position.charAt(0);
        char j = position.charAt(1);
        if(MainActivity.stillPlaying) {
            if(MainActivity.checkIfDraw()){
                Toast.makeText(MainActivity.context, "It is a drawn", Toast.LENGTH_SHORT).show();
                MainActivity.stillPlaying = false;
            } else if (MainActivity.board[(i - '0') - 1][(j - '0') - 1] == 0) {
                    // MainActivity.whosTurn == 0 => player's turn
                    if (MainActivity.whosTurn == 0) {

                        v.setBackgroundResource(R.drawable.cross);

                        MainActivity.board[(i - '0') - 1][(j - '0') - 1] = 1;

                        if (MainActivity.checkIfPlayerWon()) {
                            Toast.makeText(MainActivity.context, "Player has won", Toast.LENGTH_SHORT).show();
                            MainActivity.stillPlaying = false;
                        }

                        ai.move();

                        }
                    }
        }

    }
}
