/*package darchi07.sotsuron001;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    public void onClick(View v) {
        Log.v("onClick", "" + v.getId());
        if(v==button){
            Intent intent = new Intent(this, design001.class);
            startActivityForResult(intent, 0);
        }

    }

}
*/

package darchi07.sotsuron7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import android.os.Handler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button;
    Handler countHandler;
    static HashMap<Integer,String> mondai = new HashMap<>();//問題文格納
    static final HashMap<String, String> map = new HashMap<String, String>();//アルファベットを格納
    Random r = new Random();
    int mon=1;
    TextView mondaisu, Toi, output;
    Button[] btn = new Button[4];
    double starttime = 0,endtime = 0;
    boolean oncl = false;
    public String btnGes = "";//ボタンの組み合わせ
    StringBuilder input=new StringBuilder("");
    StringBuilder realinput = new StringBuilder("");
    double inputtime= 0;
    private ArrayList<String> arrayLast = new ArrayList<String>();
    int clcount=0;
    int character=0;
    int n ;
    double resulttime=0;//総合時間
    int jikken=5;


    static {

        mondai.put(1, "add");  mondai.put(2, "length"); mondai.put(3, "play");    mondai.put(4, "design");  mondai.put(5, "end");
        mondai.put(6, "value");   mondai.put(7, "earth");  mondai.put(8, "test");   mondai.put(9, "dentsu");   mondai.put(10, "vote");
        mondai.put(11, "at");  mondai.put(12, "italab"); mondai.put(13, "pc");    mondai.put(14, "art");  mondai.put(15, "it");
        mondai.put(16, "is");   mondai.put(17, "sea");  mondai.put(18, "teach");   mondai.put(19, "long");   mondai.put(20, "put");
        mondai.put(21, "string");  mondai.put(22, "glance"); mondai.put(23, "click");    mondai.put(24, "list");  mondai.put(25, "happy");
        mondai.put(26, "wear");   mondai.put(27, "peach");  mondai.put(28, "rock");   mondai.put(29, "lock");   mondai.put(30, "up");
        mondai.put(31, "apple");  mondai.put(32, "cost"); mondai.put(33, "apply");    mondai.put(34, "prove");  mondai.put(35, "skill");
        mondai.put(36, "sense");   mondai.put(37, "power");  mondai.put(38, "sort");   mondai.put(39, "view");   mondai.put(40, "war");
        mondai.put(41, "as");  mondai.put(42, "loss"); mondai.put(43, "tom");    mondai.put(44, "an");  mondai.put(45, "life");
        mondai.put(46, "class");   mondai.put(47, "jam");  mondai.put(48, "exact");   mondai.put(49, "short");   mondai.put(50, "odd");
        mondai.put(51, "zman");  mondai.put(52, "diet"); mondai.put(53, "joy");    mondai.put(54, "luck");  mondai.put(55, "pain");
        mondai.put(56, "egg");   mondai.put(57, "dog");  mondai.put(58, "door");   mondai.put(59, "milk");   mondai.put(60, "box");
        mondai.put(61, "watch");  mondai.put(62, "map"); mondai.put(63, "lemon");    mondai.put(64, "hard");  mondai.put(65, "camera");
        mondai.put(66, "tree");   mondai.put(67, "fish");  mondai.put(68, "town");   mondai.put(69, "time");   mondai.put(70, "house");
        mondai.put(71, "room");  mondai.put(72, "meal"); mondai.put(73, "sky");    mondai.put(74, "farm");  mondai.put(75, "people");
        mondai.put(76, "cloud");   mondai.put(77, "game");  mondai.put(78, "music");   mondai.put(79, "lie");   mondai.put(80, "cat");
        mondai.put(81, "jr");  mondai.put(82, "video"); mondai.put(83, "ice");    mondai.put(84, "part");  mondai.put(85, "key");
        mondai.put(86, "bell");   mondai.put(87, "fool");  mondai.put(88, "side");   mondai.put(89, "snow");   mondai.put(90, "food");
        mondai.put(91, "answer");  mondai.put(92, "love"); mondai.put(93, "care");    mondai.put(94, "king");  mondai.put(95, "space");
        mondai.put(96, "cut");   mondai.put(97, "become");  mondai.put(98, "lend");   mondai.put(99, "low");   mondai.put(100, "few");
    }
    static {
        map.put("54", "1"); map.put("55", "2");    map.put("56", "3");  map.put("57", "4");
        map.put("64", "5");   map.put("65", "6");map.put("66", "7");    map.put("67", "8");
        map.put("74", "9");  map.put("75", "0");

        map.put("50", "q"); map.put("51", "w");    map.put("52", "e");  map.put("53", "r");
        map.put("60", "t");   map.put("61", "y");map.put("62", "u");    map.put("63", "i");
        map.put("70", "o");  map.put("71", "p");

        map.put("14", "a"); map.put("15", "s"); map.put("16", "d"); map.put("17", "f");
        map.put("24", "g");map.put("25", "h");map.put("26", "j"); map.put("27", "k");
        map.put("34", "l");

        map.put("11", "z"); map.put("12", "x");  map.put("13", "c");
        map.put("20", "v"); map.put("21", "b"); map.put("22", "n"); map.put("23", "m");


    }

    public final Runnable runnable = new Runnable() {
        @Override
        public void run() {

            mondaisu.setText("btnGes:" + btnGes);



            if(clcount == 2){

                if(btnGes.equals("31")){
                    //SHIFT
                    //arrayLast.add("Shift");
                    input.append("↑");
                    realinput.append("×");
                    //output.setText(arrayLast.get(arrayLast.size() - 1));
                    //Intent(0);
                    output.setText(""+input);
                }
                else if(btnGes.equals("37")){
                    //空白
                    input.append(" ");
                    realinput.append("×");
                    //output.setText(arrayLast.get(arrayLast.size() - 1));
                    //Intent(1);
                    output.setText("" + input);
                }
                else if(btnGes.equals("36")){
                    //モード切替
                    input.append("×");
                    realinput.append("×");
                    // arrayLast.add("モード切替");
                    //output.setText(arrayLast.get(arrayLast.size() - 1));
                    //Intent(1);
                    output.setText(""+ input);
                }
                else if(btnGes.equals("32")) {
                    //削除
                    if(input.length()!=0){
                        int i=input.length();
                        input.deleteCharAt(i-1);

                        //output.setText(arrayLast.remove(arrayLast.size()-1));
                        output.setText(""+input);
                    }
                }
                else if (btnGes.equals("33")){
//                    sendDownUpKeyEvents(KeyEvent.KEYCODE_ENTER);
                    //Enter
                    realinput.append("→");
                    String string = input.toString();
                    Log.v("string:","入力したもの:"+ string +"\n問題文:"+mondai.get(n)+"\n乱数:"+n+
                            "\n問題の長さ:"+mondai.get(n).length()+"\n総文字数"+character+"入力時間"+inputtime+"\n"+"問題"+mon);

                    if(string.equals(mondai.get(n))) {
                        n = r.nextInt(100) + 1;

                        if(mon!=jikken){
                            character= character +mondai.get(n).length();
                            endtime = System.nanoTime();

                            inputtime = inputtime + endtime - starttime;
                            starttime=System.nanoTime();
                            Log.v("入力時間", "inputtime" + inputtime+"問題"+mon);
                            input.setLength(0);
                            output.setText("");
                            mon++;

                            Toi.setText("第" + mon + "問:" + mondai.get(n));
                            output.setText("" + input);
                        }
                        else if(mon==jikken){

                            endtime = System.nanoTime();

                            inputtime = inputtime + endtime - starttime;
                            Toi.setText("終了です！");
                            input.setLength(0);
                            resulttime = inputtime / (1000000000);
                            input.append("inputtime:" + resulttime + ":総入力文字数:" + character);
                            output.setText("" + input);
                            Log.v("実験 ", "総文字数:入力時間, " + (character + jikken) + " " + resulttime +
                                    " 誤入力数： " + (realinput.length() - character - jikken) + " realinput: " + realinput);


                        }
                    }
                }

                else if (map.containsKey(btnGes)) {
                    input.append(map.get(btnGes));
                    realinput.append(map.get(btnGes));
                    //arrayLast.add(map.get(btnGes));
//                    getCurrentInputConnection().commitText(arrayLast.get(arrayLast.size() - 1), 1);
                    output.setText(""+input);
                }
                btnGes = "";
                clcount = 0;
                mondaisu.setText("btnGes:" + btnGes);
            }else if(clcount==3){

                btnGes = "";
                clcount = 0;
                mondaisu.setText("btnGes:" + btnGes);
            }


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(darchi07.sotsuron7.R.layout.activity_main);
        button=(Button)findViewById(darchi07.sotsuron7.R.id.button);
        button.setOnClickListener(this);
        btnGes = "";
        n = r.nextInt(100) + 1;
        starttime = System.nanoTime();
        character= character +mondai.get(n).length();
        countHandler = new Handler();
        countHandler.post(runnable);
    }


    @Override
    protected void onStart(){
        // TODO 自動生成されたメソッド・スタブ
        super.onStart();
        mondaisu = (TextView)findViewById(R.id.mondaisu);
        output = (TextView)findViewById(R.id.output);
        //Toi = (TextView)findViewById(R.id.mondai);
        //Toi.setText("第1問:" + mondai.get(n));
        output.setText("出力");
        countHandler = new Handler();
        countHandler.post(runnable);

        btn[0] = (Button) findViewById(R.id.btn01);
        btn[1] = (Button) findViewById(R.id.btn02);
        btn[2] = (Button) findViewById(R.id.btn03);
        btn[3] = (Button) findViewById(R.id.btn04);



        btn[0].setClickable(true);
        btn[1].setClickable(true);
        btn[2].setClickable(true);
        btn[3].setClickable(true);



        btn[0].setOnClickListener(this);
        btn[1].setOnClickListener(this);
        btn[2].setOnClickListener(this);
        btn[3].setOnClickListener(this);



        btn[0].setOnLongClickListener(new View.OnLongClickListener() {
            //ボタンが長押しクリックされた時のハンドラ
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                // 長押しクリックされた時の処理を記述
                if(btnGes.length()==0) {
                    oncl = true;
                    btnGes = btnGes + "4";
                    btn[0].setBackgroundResource(R.drawable.shihyou009aa2);
                    btn[1].setBackgroundResource(R.drawable.shihyou009ab2);
                    btn[2].setBackgroundResource(R.drawable.shihyou009ac2);
                    btn[3].setBackgroundResource(R.drawable.shihyou009ad2);
                }
                else if(btnGes.length()>=1) {
                    oncl = false;
                    btnGes = btnGes + "4";
                    btn[0].setBackgroundResource(R.drawable.shihyou009a);
                    btn[1].setBackgroundResource(R.drawable.shihyou009b);
                    btn[2].setBackgroundResource(R.drawable.shihyou009c);
                    btn[3].setBackgroundResource(R.drawable.shihyou009d);
                }

                clcount++;
                countHandler.post(runnable);
                return true;
            }
        });
        btn[1].setOnLongClickListener(new View.OnLongClickListener() {
            //ボタンが長押しクリックされた時のハンドラ
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                // 長押しクリックされた時の処理を記述
                if(btnGes.length()==0) {
                    oncl = true;
                    btnGes = btnGes + "5";
                    btn[0].setBackgroundResource(R.drawable.shihyou009ba2);
                    btn[1].setBackgroundResource(R.drawable.shihyou009bb2);
                    btn[2].setBackgroundResource(R.drawable.shihyou009bc2);
                    btn[3].setBackgroundResource(R.drawable.shihyou009bd2);
                }
                else if(btnGes.length()>=1) {
                    oncl = false;
                    btnGes = btnGes + "5";
                    btn[0].setBackgroundResource(R.drawable.shihyou009a);
                    btn[1].setBackgroundResource(R.drawable.shihyou009b);
                    btn[2].setBackgroundResource(R.drawable.shihyou009c);
                    btn[3].setBackgroundResource(R.drawable.shihyou009d);
                }

                clcount++;
                countHandler.post(runnable);
                return true;
            }
        });
        btn[2].setOnLongClickListener(new View.OnLongClickListener() {
            //ボタンが長押しクリックされた時のハンドラ
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                // 長押しクリックされた時の処理を記述
                if(btnGes.length()==0) {
                    oncl = true;
                    btnGes = btnGes + "6";
                    btn[0].setBackgroundResource(R.drawable.shihyou009ca2);
                    btn[1].setBackgroundResource(R.drawable.shihyou009cb2);
                    btn[2].setBackgroundResource(R.drawable.shihyou009cc2);
                    btn[3].setBackgroundResource(R.drawable.shihyou009cd2);
                }
                else if(btnGes.length()>=1) {
                    oncl = false;
                    btnGes = btnGes + "6";
                    btn[0].setBackgroundResource(R.drawable.shihyou009a);
                    btn[1].setBackgroundResource(R.drawable.shihyou009b);
                    btn[2].setBackgroundResource(R.drawable.shihyou009c);
                    btn[3].setBackgroundResource(R.drawable.shihyou009d);
                }

                clcount++;
                countHandler.post(runnable);
                return true;
            }
        });
        btn[3].setOnLongClickListener(new View.OnLongClickListener() {
            //ボタンが長押しクリックされた時のハンドラ
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                // 長押しクリックされた時の処理を記述
                if(btnGes.length()==0) {
                    oncl = true;
                    btnGes = btnGes + "7";
                    btn[0].setBackgroundResource(R.drawable.shihyou009da2);
                    btn[1].setBackgroundResource(R.drawable.shihyou009db2);
                    btn[2].setBackgroundResource(R.drawable.shihyou009dc2);
                    btn[3].setBackgroundResource(R.drawable.shihyou009dd2);
                }
                else if(btnGes.length()>=1) {
                    oncl = false;
                    btnGes = btnGes + "7";
                    btn[0].setBackgroundResource(R.drawable.shihyou009a);
                    btn[1].setBackgroundResource(R.drawable.shihyou009b);
                    btn[2].setBackgroundResource(R.drawable.shihyou009c);
                    btn[3].setBackgroundResource(R.drawable.shihyou009d);
                }

                clcount++;
                countHandler.post(runnable);
                return true;
            }
        });


    }



    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    public void onDestroy() {
        // life cycle 6
        countHandler.removeCallbacks(runnable);
        super.onDestroy();
    }
    @Override
    public void onClick(View v) {

        if(((v == btn[0])||(v == btn[1])||(v == btn[2])||(v == btn[3]))){
            if(btnGes.length() == 0){
                oncl = true;
                if(v==btn[0]){
                    btnGes =btnGes + "0";
                    btn[0].setBackgroundResource(R.drawable.shihyou009aa);
                    btn[1].setBackgroundResource(R.drawable.shihyou009ab);
                    btn[2].setBackgroundResource(R.drawable.shihyou009ac);
                    btn[3].setBackgroundResource(R.drawable.shihyou009ad);
                }else if(v==btn[1]){
                    btnGes =btnGes + "1";
                    btn[0].setBackgroundResource(R.drawable.shihyou009ba);
                    btn[1].setBackgroundResource(R.drawable.shihyou009bb);
                    btn[2].setBackgroundResource(R.drawable.shihyou009bc);
                    btn[3].setBackgroundResource(R.drawable.shihyou009bd);
                }else if(v==btn[2]){
                    btnGes =btnGes + "2";
                    btn[0].setBackgroundResource(R.drawable.shihyou009ca);
                    btn[1].setBackgroundResource(R.drawable.shihyou009cb);
                    btn[2].setBackgroundResource(R.drawable.shihyou009cc);
                    btn[3].setBackgroundResource(R.drawable.shihyou009cd);
                }else if(v==btn[3]){
                    btnGes =btnGes + "3";
                    btn[0].setBackgroundResource(R.drawable.shihyou009da);
                    btn[1].setBackgroundResource(R.drawable.shihyou009db);
                    btn[2].setBackgroundResource(R.drawable.shihyou009dc);
                    btn[3].setBackgroundResource(R.drawable.shihyou009dd);
                }
                clcount++;
                countHandler.post(runnable);
            }else if(btnGes.length() == 1){
                oncl = false;
                if(v==btn[0]){
                    btnGes =btnGes + "0";

                }else if(v==btn[1]){
                    btnGes =btnGes + "1";

                }else if(v==btn[2]){
                    btnGes =btnGes + "2";

                }else if(v==btn[3]){
                    btnGes =btnGes + "3";

                }
                clcount++;
                countHandler.post(runnable);
                btn[0].setBackgroundResource(R.drawable.shihyou009a);
                btn[1].setBackgroundResource(R.drawable.shihyou009b);
                btn[2].setBackgroundResource(R.drawable.shihyou009c);
                btn[3].setBackgroundResource(R.drawable.shihyou009d);
            }


        }
        if(v==button){
            Intent intent = new Intent(this, design7.class);
            startActivityForResult(intent, 0);
        }
    }



}


