package university.tictactoe.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import university.tictactoe.R;
import university.tictactoe.databinding.ActivityMainBinding;
import university.tictactoe.listeners.ImageViewClickClisteners;
import university.tictactoe.models.UserModel;

public class MainActivity extends Activity {

    private UserModel userModel;
    private String username;
    private TextView textView;

    public static List<ImageView> imageViews = new ArrayList<>();
    public static int[][] board = new int[3][3];
    public static int whosTurn = 0;
    public static Context context;
    public static boolean stillPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        username = null;
        if(bundle != null)
            username = (String) bundle.get("username");

        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        stillPlaying = true;

        textView = (TextView) findViewById(R.id.activity_main_text_view_username);
        textView.setTypeface(null, Typeface.BOLD);

        //Sets value to the data variable
        userModel = new UserModel(username);
        binding.setUser(userModel);

        initBoard();

        storeImageViewsAndSetLargeness();

    }

    private void initBoard(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = 0;
            }
        }
    }

    public static boolean checkIfPlayerWon(){

        // horizontal
        if(board[0][0] == 1 && board[0][1] == 1 && board[0][2] == 1){
            return true;
        }

        if(board[1][0] == 1 && board[1][1] == 1 && board[1][2] == 1){
            return true;
        }

        if(board[2][0] == 1 && board[2][1] == 1 && board[2][2] == 1){
            return true;
        }


        // vertical
        if(board[0][0] == 1 && board[1][0] == 1 && board[2][0] == 1){
            return true;
        }

        if(board[0][1] == 1 && board[1][1] == 1 && board[2][1] == 1){
            return true;
        }

        if(board[0][2] == 1 && board[1][2] == 1 && board[2][2] == 1){
            return true;
        }


        // diagonal, both way
        if(board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1){
            return true;
        }

        if(board[0][2] == 1 && board[1][1] == 1 && board[2][0] == 1){
            return true;
        }

        return false;
    }

    public static boolean checkIfAIWon(){

        // horizontal
        if(board[0][0] == 2 && board[0][1] == 2 && board[0][2] == 2){
            return true;
        }

        if(board[1][0] == 2 && board[1][1] == 2 && board[1][2] == 2){
            return true;
        }

        if(board[2][0] == 2 && board[2][1] == 2 && board[2][2] == 2){
            return true;
        }


        // vertical
        if(board[0][0] == 2 && board[1][0] == 1 && board[2][0] == 2){
            return true;
        }

        if(board[0][1] == 2 && board[1][1] == 2 && board[2][1] == 2){
            return true;
        }

        if(board[0][2] == 2 && board[1][2] == 2 && board[2][2] == 2){
            return true;
        }


        // diagonal, both way
        if(board[0][0] == 2 && board[1][1] == 2 && board[2][2] == 2){
            return true;
        }

        if(board[0][2] == 2 && board[1][1] == 2 && board[2][0] == 2){
            return true;
        }

        return false;

    }

    public static boolean checkIfDraw(){
        int counter = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == 0){
                    counter++;
                }
            }
        }

        if(counter == 1)
            return true;

        return false;
    }

    private void storeImageViewsAndSetLargeness() {

        int widthOfOneCell = Math.round(ScreenWidth()/3);
        ImageView imageView11 = (ImageView) findViewById(R.id.activity_main_image_view_1_1);
        ImageView imageView12 = (ImageView) findViewById(R.id.activity_main_image_view_1_2);
        ImageView imageView13 = (ImageView) findViewById(R.id.activity_main_image_view_1_3);

        ImageView imageView21 = (ImageView) findViewById(R.id.activity_main_image_view_2_1);
        ImageView imageView22 = (ImageView) findViewById(R.id.activity_main_image_view_2_2);
        ImageView imageView23 = (ImageView) findViewById(R.id.activity_main_image_view_2_3);

        ImageView imageView31 = (ImageView) findViewById(R.id.activity_main_image_view_3_1);
        ImageView imageView32 = (ImageView) findViewById(R.id.activity_main_image_view_3_2);
        ImageView imageView33 = (ImageView) findViewById(R.id.activity_main_image_view_3_3);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(widthOfOneCell-1, widthOfOneCell-1);
        imageView11.setLayoutParams(layoutParams);
        imageView12.setLayoutParams(layoutParams);
        imageView13.setLayoutParams(layoutParams);

        imageView21.setLayoutParams(layoutParams);
        imageView22.setLayoutParams(layoutParams);
        imageView23.setLayoutParams(layoutParams);

        imageView31.setLayoutParams(layoutParams);
        imageView32.setLayoutParams(layoutParams);
        imageView33.setLayoutParams(layoutParams);

        imageView11.setTag("11");
        imageView12.setTag("12");
        imageView13.setTag("13");

        imageView21.setTag("21");
        imageView22.setTag("22");
        imageView23.setTag("23");

        imageView31.setTag("31");
        imageView32.setTag("32");
        imageView33.setTag("33");

        imageViews.add(imageView11);
        imageViews.add(imageView12);
        imageViews.add(imageView13);

        imageViews.add(imageView21);
        imageViews.add(imageView22);
        imageViews.add(imageView23);

        imageViews.add(imageView31);
        imageViews.add(imageView32);
        imageViews.add(imageView33);

        for(ImageView imageView : imageViews){
            imageView.setOnClickListener(new ImageViewClickClisteners());
        }
    }

    private float ScreenWidth(){

        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;

    }

}
