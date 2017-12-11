package krishnas.numbergame;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView textView;
    TextView textView3;
    TextView textscore;
    TextView textView2;
    TextView textresult;
    MediaPlayer mediaplayer;
    ///RelativeLayout layout2;
    int locationofcorrectanswer;
    int incorrectanswer;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int score = 0;
    int numberofquestions = 0;



    public void playagain( View view){

        score=0;
        numberofquestions=0;
        textView.setText("0");
        textscore.setText("0/0");
        textresult.setText("");
        button4.setVisibility(View.INVISIBLE);
        //layout2.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        generatequestion();
       // mediaplayer.reset();


        new CountDownTimer(30100, 1000) {
            public void onTick(long millisuntilfinish) {
                textView.setText(String.valueOf(millisuntilfinish / 1000 + "s"));
            }

            public void onFinish() {
                button4.setVisibility(View.VISIBLE);
                textView.setText("0");
                textresult.setText("yourScore:"+Integer.toString(score) + "/" + Integer.toString(numberofquestions));
                button.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);

            }
        }.start();

    }


    public void generatequestion() {
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        textView3.setText(Integer.toString(a) + "+" + Integer.toString(b));
        locationofcorrectanswer = random.nextInt(4);
        answer.clear();
        for (int i = 0; i < 4; i++) {
            if (i == locationofcorrectanswer) {
                answer.add(a + b);

            } else {
                incorrectanswer = random.nextInt(41);
                while (incorrectanswer == a + b) {
                    incorrectanswer = random.nextInt(41);
                }
                answer.add(incorrectanswer);
            }
        }
        button.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }


    public void chooseanswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationofcorrectanswer))) {
            score++;
            textresult.setText("Correct!");
        } else {
            textresult.setText("Wrong!");

        }
        numberofquestions++;
        textscore.setText(Integer.toString(score) + "/" + Integer.toString(numberofquestions));
        generatequestion();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        textView = (TextView) findViewById(R.id.textView);
        textView3 = (TextView) findViewById(R.id.textView3);
        textscore = (TextView) findViewById(R.id.textscore);
        textresult = (TextView) findViewById(R.id.textresult);
        //textView2 = (TextView) findViewById(R.id.textView2);
        generatequestion();
        playagain(findViewById(R.id.button4));
        //layout2 = (RelativeLayout) findViewById(R.id.layout2);
        mediaplayer = MediaPlayer.create(this, R.raw.supermario);
        mediaplayer.start();


    }
}
