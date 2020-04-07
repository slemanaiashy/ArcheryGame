package com.example.archerygame;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;


import java.util.Random;

import androidx.annotation.RequiresApi;

import static android.content.Context.MODE_PRIVATE;

public class GameView extends View {




    // add setting button using X and Y from touch listener
    @RequiresApi(api = Build.VERSION_CODES.O)

    public void onClick(View view) {
        view.requestPointerCapture();
    }

    public boolean onCapturedPointerEvent(MotionEvent motionEvent) {
        float verticalOffset = motionEvent.getY();
        System.out.println(verticalOffset);
        return true;
    }

    public boolean onCapturedPointer(View view, MotionEvent motionEvent) {
        // Get the coordinates required by your app

        float horizontalOffset = motionEvent.getX();
        System.out.println(horizontalOffset);
        return true;
    }

    @Override
    public boolean onTouchEvent (MotionEvent event){
        float Ytouch = event.getY();// y
        float Xtouch = event.getX();//x
        System.out.println("Xtouch"+Xtouch);
        System.out.println("Ytouch"+Ytouch);
        int action = event.getAction();
        long now =  System.currentTimeMillis();
        System.out.println("WTishapen"+(T % 4 == 0 && T != 1&&action==MotionEvent.ACTION_UP));
        if (T % 4 == 0 && T != 1&&action==MotionEvent.ACTION_UP) {
            z=now-z;
            up = true;
        }
        System.out.println("nooow"+up);
        if(T%3==0&&action==MotionEvent.ACTION_DOWN){
            z=now;
        }
        if(action==MotionEvent.ACTION_MOVE){
        }
        else
        if (!(Xtouch > 0 && Xtouch < 105*fx && Ytouch > 0 && Ytouch < 106*fy)&&!settingpress&&!isShop&&gamemodedone&&!midair&&!isHighscores&&finishgame) {
            T++;
            System.out.println("zxcsa");
        }
        else {
            System.out.println("asdfa "+finishgame+" "+!midair+" "+gamemodedone+" "+!settingpress+" "+!isShop+" "+!isHighscores);
            if(!isShop&&gamemodedone&&!midair&&finishgame){
                settingpress = true;
                if(Xtouch>900*fx&&Xtouch<980*fx&&Ytouch>32*fy&&Ytouch<112*fy){
                    if(action==MotionEvent.ACTION_UP)
                        settingpress=false;
                }
                if(Xtouch>705*fx&&Xtouch<790*fx&&Ytouch>195*fy&&Ytouch<280*fy&&action==MotionEvent.ACTION_UP){
                    bm=!bm;
                    updateBK(player.getUsername(),bm);
                }
                if(Xtouch>705*fx&&Xtouch<790*fx&&Ytouch>295*fy&&Ytouch<380*fy&&action==MotionEvent.ACTION_UP){
                    se=!se;
                    updateSE(player.getUsername(),se);
                }
                if(Xtouch>(width)* fx/ 2-200*fx&&Xtouch<(width)* fx/ 2+180*fx&&Ytouch>(height) * fy / 8+450*fy&&Ytouch<(height) * fy / 8+550*fy){
                    isHighscores=true;
                }
            }
        }
        if(isHighscores&&Xtouch>900*fx&&Xtouch<980*fx&&Ytouch>32*fy&&Ytouch<112*fy&&finishgame){
            if(action==MotionEvent.ACTION_UP)
                isHighscores=false;
        }
        if(T%3==0){
            flag=true;
        }
        if (action == MotionEvent.ACTION_UP && !settingpress&&T%5==0) {
            touch = false;
        }
        if(Xtouch>900*fx&&Xtouch<980*fx&&Ytouch>32*fy&&Ytouch<112*fy&&isShop&&finishgame){
            shopex=true;
            if(action==MotionEvent.ACTION_UP)
                isShop=false;
        }
        //700*fx, (height/2-70)*fy,290*fx, (height/2-70)*fy//(width)* fx/ 2-100*fx, (height ) * fy / 8+450*fy//87,50
        //(width)* fx/ 2-100*fx, (height ) * fy / 8+450*fy
        System.out.println("areyouin? "+ ( Xtouch>(width)* fx/ 2-100*fx&&Xtouch<(width)* fx/ 2+74*fx*fx&&Ytouch>(height ) * fy&&Ytouch<(height+100 ) * fy&&isShop&&isbuy));
        if(Xtouch>(width)* fx/ 2-100*fx&&Xtouch<(width)* fx/ 2+74*fx&&Ytouch>(height ) * fy / 8+450*fy&&Ytouch<(height ) * fy / 8+550*fy&&!finishgame){
            if(action==MotionEvent.ACTION_UP) {
                switch (Gamemode) {
                    case 1:
                        heartnum = 3;
                        break;
                    case 2:
                        heartnum = 2;
                        break;
                    case 3:
                        heartnum = 1;

                }
                T=1;
                score = 0;
                done = true ;
                restart();
                finishgame = true;
            }

        }
  //      15*fx, (height-15) * fy
        if(Xtouch>15*fx&&Xtouch<189*fx*fx&&Ytouch>(height -155) * fy&&Ytouch<(height -55) * fy&&!finishgame){
            if(action==MotionEvent.ACTION_UP){
           Gamemode=4;
           shopex=true;
           heartnum=5;
            score = 0;
            done = true ;
            restart();
            gamemodedone=false;
            firstdiffi=true;
            finishgame = true;
            }
        }
        if(Xtouch>(width)* fx/ 2-100*fx&&Xtouch<(width)* fx/ 2+74*fx*fx&&Ytouch>(height ) * fy / 8+450*fy&&Ytouch<(height ) * fy / 8+550*fy&&isShop&&isbuy&&!isequip){
            if(Prices[arrownum]<player.getCurrentGold())
                if(action==MotionEvent.ACTION_UP) {
                    updatedata(player.getUsername(),new Player(player.getUsername(),player.getNumberOfGames()+1,0,maxGoldEarned,maxCombo,player.getCurrentGold()-Prices[arrownum],highscore));
                    Collection[arrownum]=true;
                    updateCollection(player.getUsername(),Collection[arrownum]);
                    isbuy=false;
                }
        }

        System.out.println("equip "+isequip+" "+isShop+" "+isbuy);
        if(Xtouch>(width)* fx/ 2-100*fx&&Xtouch<(width)* fx/ 2+74*fx*fx&&Ytouch>(height ) * fy / 8+450*fy&&Ytouch<(height ) * fy / 8+550*fy&&isShop&&isequip&&!isbuy){
            if(action==MotionEvent.ACTION_UP){
                player.setEquiped(arrownum);
                updateEquiped(player.getUsername(),player.getEquiped());
                isequip=false;
            }
        }
        if(Xtouch>700*fx&&Xtouch<800*fx&&Ytouch>(height/2-70)*fy&&Ytouch<(height/2-70)*fy+60*fy&&isShop){
            if(action==MotionEvent.ACTION_UP)
                if(arrownum!=4)
                    arrownum++;
        }
        if(Xtouch>290*fx&&Xtouch<390*fx&&Ytouch>(height/2-70)*fy&&Ytouch<(height/2-70)*fy+60*fy&&isShop){
            if(action==MotionEvent.ACTION_UP)
                if(arrownum!=0)
                    arrownum--;
        }
        if(Xtouch>(width)* fx/ 2-100*fx&&Xtouch<(width)* fx/ 2+74*fx&&Ytouch>(height) * fy / 8&&Ytouch<(height) * fy / 8+100*fy&&shopex){
            if(action==MotionEvent.ACTION_UP){
                shopex=false;
                Gamemode=1;}


            System.out.println("gooood");
        }
        if(Xtouch>(width)* fx/ 2-100*fx&&Xtouch<(width)* fx/ 2+74*fx&& Ytouch>(height) * fy / 8+150*fy&&Ytouch<(height) * fy / 8+250*fy&&shopex){
            if(action==MotionEvent.ACTION_UP){
                Gamemode=2;
                shopex=false;
            }

            System.out.println("gooood");
        }
        if(Xtouch>(width)* fx/ 2-100*fx&&Xtouch<(width)* fx/ 2+74*fx&&Ytouch>(height) * fy / 8+300*fy&&Ytouch<(height) * fy / 8+400*fy&&shopex){
            if(action==MotionEvent.ACTION_UP){
                Gamemode=3;
                shopex=false;
            }

        }
        if(Xtouch>(width)* fx/ 2-100*fx&&Xtouch<(width)* fx/ 2+74*fx&&Ytouch>(height) * fy / 8+450*fy&&Ytouch<(height) * fy / 8+550*fy&&shopex){
            if(action==MotionEvent.ACTION_UP)
                Gamemode=4;
            shopex=false;
            isShop=true;

        }
      if(Xtouch>15*fx&&Xtouch<189*fx*fx&&Ytouch>(height -155) * fy&&Ytouch<(height -55) * fy&&shopex){
            if(action==MotionEvent.ACTION_DOWN){
                if(action==MotionEvent.ACTION_UP){
                final String PREFS_NAME="PrefsFile";
                SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putString("username","");
                editor.putString("password","");
                editor.putBoolean("checkbox",false);
                editor.apply();
                System.exit(0);
                }
            }
        }

        System.out.println("number  "+T);
        return true;


    }
    Random random = new Random();
    SoundPlayer soundPlayer;
    GameData gameData;
    boolean  isHighscores=false;
    protected Canvas canvas;
    Boolean finish=false,recreate=false,done=true,se,bm,isShop=false,firstdiffi=true,shopex=false,gamemodedone=false,midair=false,isbuy=false,isequip=false;
    Animator animator;
    Player player ;
    Handler handler;
    Arrow arrow1;
    int [] Prices= new int[5];
    float scalex,scaley;
    int arrowheadx,arrowheady,score=0,highscore=0;
    Runnable runnable;
    int rotation = 10,timee,maxCombo,maxGoldEarned=0,Gamemode;
    float xcoordinate=0f,ycoordinate=0f;
    double angle=0;
    Rect rect;
    Rect rect1;
    long startTime, timeInMilliseconds = 0;
    Handler customHandler = new Handler();
    Bitmap logout,background, apple, Bow, settings,Score,gameoverback,settingback,coin,xbutton,shop,easy,hard,med,shopbut,arrowleft,arrowright,buy,equip,equiped,highscores,highscoresbut,play,home;
    Point point;
    Display display;
    int   applecorx = (random. nextInt(644)+483), applecory=random.nextInt(428)+100;
    int heartnum=3;
    final int Delay = 30, Speed = 55;
    int delayfinish=0;
    float fx,density;
    Matrix matrixglobal;
    float fy;
    final double speedHeight = 10, Gravity = -10;
    int x = 5, ground, Helpx, Helpy, Time = 1,combo=0,arrownum=0;
    double arrowx, arrowy, pressTime;
    boolean appleboolean=true,firsttime=true;
    FirebaseDatabase database;
    DatabaseReference PlayerInfo;
    double yspeed=0, xspeed=0, yEquation, xEquation;
    int rotateafter;
    long z = 0;
    boolean [] Collection = new boolean[5];
    MediaPlayer mp ;
    float scaleapplex,scaleappley;
    boolean check = true, touch = true, flag = false, up = false, first = true, settingpress = false, bound = true, maxDegree = true,angleboolean=true,finishgame=true;
    int width=1184, height=720, heartFrame = 0, bowFrame = 0, T = 1,wid,hei; // work
    Bitmap[]  heart1, heart3, heart2,number,numberscore,boxBM,boxSE,bow, bowafter, arrow;
    View view;
    Context context;
    private MotionEvent event;
    Intent intent12;
    boolean mpbol=true;
    public GameView(Context context,Player player,int Gamemode,GameData gameData) {
        super(context);
        this.Gamemode=Gamemode;
        this.context=context;
        this.gameData=gameData;
        this.canvas=canvas;
        soundPlayer=new SoundPlayer(context);
        handler = new Handler();
        this.player=player;
        database = FirebaseDatabase.getInstance();
        PlayerInfo= database.getReference("PlayersInfo");
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        Arrow arrow1;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        wid = point.x;
        hei = point.y;
        System.out.println(wid+" shar"+hei);
        fy=hei/720f;
        fx =(wid/1184f);
        density = context.getResources().getDisplayMetrics().density/2;
        scalex=fx/density;
        scaley=fy/density;
        System.out.println(fx+" wt "+2094/1184);
        xbutton= BitmapFactory.decodeResource(context.getResources(), R.drawable.xbutton);
        applecorx =(int)(applecorx*fx);
        applecory =(int)(applecory*fy);
        System.out.println(fy+"  wt1 "+1080/720);
        ground = height - 200;
        rect = new Rect(0, 0, wid, hei);
        rect1 = new Rect(10, 10, width, height);
        shopbut =BitmapFactory.decodeResource(context.getResources(), R.drawable.shopbut);
        bow=new Bitmap[5];
        bowafter=new Bitmap[5];
        arrow=new Bitmap[5];
        Prices[1]=100;
        Prices[2]=100;
        Prices[3]=100;
        Prices[4]=400;
        logout = BitmapFactory.decodeResource(context.getResources(), R.drawable.logoutbut);
        play = BitmapFactory.decodeResource(context.getResources(), R.drawable.playbut);
        home= BitmapFactory.decodeResource(context.getResources(),R.drawable.homebut);
        buy = BitmapFactory.decodeResource(context.getResources(), R.drawable.buy);
        equip =BitmapFactory.decodeResource(context.getResources(), R.drawable.equip);
        equiped=BitmapFactory.decodeResource(context.getResources(), R.drawable.equiped);
        arrowright=BitmapFactory.decodeResource(getResources(),R.drawable.arrowright);
        arrowleft=BitmapFactory.decodeResource(getResources(),R.drawable.arrowleft);
        bow[0] =BitmapFactory.decodeResource(getResources(), R.drawable.bow);
        bowafter[0] = BitmapFactory.decodeResource(getResources(), R.drawable.bowbow);
        arrow[0] =  BitmapFactory.decodeResource(getResources(), R.drawable.cropped);
        bow[1] =BitmapFactory.decodeResource(getResources(), R.drawable.bowred);
        bowafter[1] = BitmapFactory.decodeResource(getResources(), R.drawable.bowbowred);
        arrow[1] =  BitmapFactory.decodeResource(getResources(), R.drawable.arrowred);
        bow[2] =BitmapFactory.decodeResource(getResources(), R.drawable.bowblue);
        bowafter[2] = BitmapFactory.decodeResource(getResources(), R.drawable.bowbowblue);
        arrow[2] =  BitmapFactory.decodeResource(getResources(), R.drawable.arrowblue);
        bow[3] =BitmapFactory.decodeResource(getResources(), R.drawable.bowyellow);
        bowafter[3] = BitmapFactory.decodeResource(getResources(), R.drawable.bowbowyellow);
        arrow[3] =  BitmapFactory.decodeResource(getResources(), R.drawable.arrowyellow);
        bow[4] =BitmapFactory.decodeResource(getResources(), R.drawable.bowgreen);
        bowafter[4] = BitmapFactory.decodeResource(getResources(), R.drawable.bowbowgreen);
        arrow[4] =  BitmapFactory.decodeResource(getResources(), R.drawable.arrowgreen);
        Score = BitmapFactory.decodeResource(getResources(),R.drawable.score);
        heart1 = new Bitmap[2];
        heart2 = new Bitmap[2];
        Collection[0]=player.isBow1();
        Collection[1]=player.isBow2();
        Collection[2]=player.isBow3();
        Collection[3]=player.isBow4();
        Collection[4]=player.isBow5();
        System.out.println("solution"+player.isBow2()+"  "+player.isBow3());
        heart3 = new Bitmap[2];
        boxBM= new Bitmap[2];
        boxSE= new Bitmap[2];
        number=new Bitmap[10];
        numberscore=new Bitmap[10];
        bm=player.isBK();
        se=player.isSE();
        highscores = BitmapFactory.decodeResource(getResources(),R.drawable.gamehighescores);
        highscoresbut = BitmapFactory.decodeResource(getResources(),R.drawable.highscores);
        easy = BitmapFactory.decodeResource(getResources(), R.drawable.easy);
        hard = BitmapFactory.decodeResource(getResources(), R.drawable.hard);
        med = BitmapFactory.decodeResource(getResources(), R.drawable.med);
        shop = BitmapFactory.decodeResource(getResources(), R.drawable.shop);
        settings = BitmapFactory.decodeResource(getResources(), R.drawable.finalsettingsbutton);
        settingback=BitmapFactory.decodeResource(getResources(),R.drawable.settings);
        gameoverback= BitmapFactory.decodeResource(getResources(),R.drawable.gameover);
        boxBM[0] =BitmapFactory.decodeResource(getResources(),R.drawable.emptybox);
        boxSE[0] =BitmapFactory.decodeResource(getResources(),R.drawable.emptybox);
        boxBM[1] =BitmapFactory.decodeResource(getResources(),R.drawable.tickedbox);
        boxSE[1] =BitmapFactory.decodeResource(getResources(),R.drawable.tickedbox);
        number[0]=BitmapFactory.decodeResource(getResources(),R.drawable.n0);
        number[1]=BitmapFactory.decodeResource(getResources(),R.drawable.n1);
        number[2]=BitmapFactory.decodeResource(getResources(),R.drawable.n2);
        number[3]=BitmapFactory.decodeResource(getResources(),R.drawable.n3);
        number[4]=BitmapFactory.decodeResource(getResources(),R.drawable.n4);
        number[5]=BitmapFactory.decodeResource(getResources(),R.drawable.n5);
        number[6]=BitmapFactory.decodeResource(getResources(),R.drawable.n6);
        number[7]=BitmapFactory.decodeResource(getResources(),R.drawable.n7);
        number[8]=BitmapFactory.decodeResource(getResources(),R.drawable.n8);
        number[9]=BitmapFactory.decodeResource(getResources(),R.drawable.n9);
        coin = BitmapFactory.decodeResource(getResources(),R.drawable.coin);
        heart1[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fullheart);
        heart2[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fullheart);
        heart3[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fullheart);
        heart1[1] = BitmapFactory.decodeResource(getResources(), R.drawable.emptyheart);
        heart2[1] = BitmapFactory.decodeResource(getResources(), R.drawable.emptyheart);
        heart3[1] = BitmapFactory.decodeResource(getResources(), R.drawable.emptyheart);
        apple = BitmapFactory.decodeResource(getResources(), R.drawable.apple);
    }

  /*  public GameView  recreate(){
        GameView gameView = new GameView(context);
        return gameView;
    }*/

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        BitmapFactory.Options options = new BitmapFactory.Options();
        matrixglobal=new Matrix();
        System.out.println(Collection[2]);
        System.out.println("ytu"+T);
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.cropped, options);
        int arrowheight = options.outHeight;//12px
        int arrowwidth = options.outWidth;
        canvas.drawBitmap(background, null, rect, null);
        System.out.println(Gamemode+"asdadgawgdf");
        switch (Gamemode){
            case 1:
                gamemodedone=true;
                if(firstdiffi)
                    heartnum=3;
                firstdiffi=false;
                scaleapplex=1.25f*scalex;
                scaleappley=1.25f*scaley;
                break;
            case 2:
                gamemodedone=true;
                if(firstdiffi)
                    heartnum=2;
                firstdiffi=false;
                scaleappley=scalex;
                scaleapplex=scaley;
                break;
            case 3:
                gamemodedone=true;
                if(firstdiffi)
                    heartnum=1;
                firstdiffi=false;
                scaleapplex=0.75f*scalex;
                scaleappley=0.75f*scaley;
                break;
            case 4:
                System.out.println("whgato"+shopex);
                if(!shopex){
                    matrixglobal.setTranslate(130*fx,25*fy);
                    matrixglobal.postScale(scalex,scaley,130*fx,25*fy);
                    canvas.drawBitmap(shop,matrixglobal,null);
                    isShop=true;
                    //  System.out.println( "pooota"+gameData.getMostGoldEarnedInSingleGame());
                    matrixglobal.setTranslate(900*fx,32*fy);
                    matrixglobal.postScale(scalex,scaley,900*fx,32*fy);
                    canvas.drawBitmap(xbutton,matrixglobal,null);
                    matrixglobal.setTranslate(495*fx, (height/2-112)*fy);
                    matrixglobal.postScale(scalex*1.5f, scaley*1.5f, 495*fx, (height/2-112)*fy);
                    canvas.drawBitmap(bow[arrownum], matrixglobal, null);
                    switch(arrownum){
                        case 0:
                            matrixglobal.setTranslate((width)* fx/ 2-100*fx, (height ) * fy / 8+450*fy);
                            matrixglobal.postScale(scalex, scaley, (width) * fx / 2-100*fx, (height ) * fy / 8+450*fy);
                            if(player.equiped==0){
                                canvas.drawBitmap(equiped,matrixglobal,null);}

                            else{
                                isequip=true;
                                isbuy=false;
                                canvas.drawBitmap(equip,matrixglobal,null);
                            }
                            break;
                        case 1:
                            matrixglobal.setTranslate((width)* fx/ 2-100*fx, (height ) * fy / 8+450*fy);
                            matrixglobal.postScale(scalex, scaley, (width) * fx / 2-100*fx, (height ) * fy / 8+450*fy);
                            if(player.equiped==1)
                                canvas.drawBitmap(equiped,matrixglobal,null);
                            else{
                                if(Collection[arrownum]){
                                    isequip=true;
                                    isbuy=false;
                                    canvas.drawBitmap(equip,matrixglobal,null);
                                }
                                else{
                                    isequip=false;
                                    isbuy=true;
                                    Paint paint = new Paint();
                                    paint.setColor(Color.RED);
                                    paint.setTextSize(40);
                                    paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                                    canvas.drawText("Buy PROJECT:RED for 100 gold", (width) * fx / 2-270*fx, (height ) * fy / 8+420*fy, paint);
                                    canvas.drawBitmap(buy,matrixglobal,null);
                                }
                            }
                            break;
                        case 2:
                            matrixglobal.setTranslate((width)* fx/ 2-100*fx, (height ) * fy / 8+450*fy);
                            matrixglobal.postScale(scalex, scaley, (width) * fx / 2-100*fx, (height ) * fy / 8+450*fy);
                            if(player.equiped==2){
                                canvas.drawBitmap(equiped,matrixglobal,null);}

                            else{
                                if(Collection[arrownum]){
                                    isequip=true;
                                    isbuy=false;
                                    canvas.drawBitmap(equip,matrixglobal,null);}
                                else{
                                    isbuy=true;
                                    isequip=false;
                                    Paint paint = new Paint();
                                    paint.setColor(Color.BLUE);
                                    paint.setTextSize(40);
                                    paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                                    canvas.drawText("Buy PROJECT:BLUE for 100 gold", (width) * fx / 2-270*fx, (height ) * fy / 8+420*fy, paint);
                                    canvas.drawBitmap(buy,matrixglobal,null);
                                }
                            }
                            break;
                        case 3:
                            matrixglobal.setTranslate((width)* fx/ 2-100*fx, (height ) * fy / 8+450*fy);
                            matrixglobal.postScale(scalex, scaley, (width) * fx / 2-100*fx, (height ) * fy / 8+450*fy);
                            if(player.equiped==3){
                                canvas.drawBitmap(equiped,matrixglobal,null);}
                            else{
                                if(Collection[arrownum]){
                                    isequip=true;
                                    isbuy=false;
                                    canvas.drawBitmap(equip,matrixglobal,null);
                                }


                                else{
                                    isbuy=true;
                                    isequip=false;
                                    Paint paint = new Paint();
                                    paint.setColor(Color.YELLOW);
                                    paint.setTextSize(40);
                                    paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                                    canvas.drawText("Buy PROJECT:Yellow for 100 gold", (width) * fx / 2-250*fx, (height ) * fy / 8+420*fy, paint);
                                    canvas.drawBitmap(buy,matrixglobal,null);
                                }
                            }
                            break;
                        case 4:
                            matrixglobal.setTranslate((width)* fx/ 2-100*fx, (height ) * fy / 8+450*fy);
                            matrixglobal.postScale(scalex, scaley, (width) * fx / 2-100*fx, (height ) * fy / 8+450*fy);
                            if(player.equiped==4){
                                canvas.drawBitmap(equiped,matrixglobal,null);

                            }

                            else{
                                if(Collection[arrownum]){
                                    isequip=true;
                                    isbuy=false;
                                    canvas.drawBitmap(equip,matrixglobal,null);
                                }

                                else{
                                    isbuy=true;
                                    isequip=false;
                                    Paint paint = new Paint();
                                    paint.setColor(Color.GREEN);
                                    paint.setTextSize(40);
                                    paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC));
                                    canvas.drawText("Buy Legendary:q9lq for 400 gold", (width) * fx / 2-270*fx, (height ) * fy / 8+420*fy, paint);
                                    canvas.drawBitmap(buy,matrixglobal,null);
                                }
                            }

                            break;

                    }
                    matrixglobal.setTranslate(700*fx, (height/2-70)*fy);
                    matrixglobal.postScale(scalex*1.5f, scaley*1.5f, 700*fx, (height/2-70)*fy);
                    canvas.drawBitmap(arrowright,matrixglobal,null);
                    matrixglobal.setTranslate(290*fx, (height/2-70)*fy);
                    matrixglobal.postScale(scalex*1.5f, scaley*1.5f, 290*fx, (height/2-70)*fy);
                    canvas.drawBitmap(arrowleft,matrixglobal,null);
                    final int distance = 68;
                    matrixglobal.setTranslate((710)*fx, 80*fy);
                    matrixglobal.postScale(scalex,scaley,(710)*fx, 80*fy);
                    canvas.drawBitmap(coin,matrixglobal,null);
                    switch (String.valueOf(player.getCurrentGold()).length()){
                        case 1:
                            matrixglobal.setTranslate((780)*fx, 80*fy);
                            matrixglobal.postScale(scalex,scaley,(780)*fx, 80*fy);
                            canvas.drawBitmap(number[player.getCurrentGold()%10], matrixglobal, null);
                            break;
                        case 2:
                            matrixglobal.setTranslate((780)*fx, 80*fy);
                            matrixglobal.postScale(scalex,scaley,(780)*fx, 80*fy);
                            canvas.drawBitmap(number[player.getCurrentGold() / 10],matrixglobal, null);
                            matrixglobal.setTranslate((780+distance)*fx, 80*fy);
                            matrixglobal.postScale(scalex,scaley,(780+distance)*fx, 80*fy);
                            canvas.drawBitmap(number[player.getCurrentGold()%10],matrixglobal, null);
                            break;
                        case 3:
                            matrixglobal.setTranslate((780)*fx, 80*fy);
                            matrixglobal.postScale(scalex,scaley,(780)*fx, 80*fy);
                            canvas.drawBitmap(number[player.getCurrentGold() / 100],matrixglobal, null);
                            matrixglobal.setTranslate((780+distance)*fx, 80*fy);
                            matrixglobal.postScale(scalex,scaley,(780+distance)*fx, 80*fy);
                            canvas.drawBitmap(number[(player.getCurrentGold() / 10) % 10],matrixglobal, null);
                            matrixglobal.setTranslate((780+distance*2)*fx, 80*fy);
                            matrixglobal.postScale(scalex,scaley,(780+distance*2)*fx, 80*fy);
                            canvas.drawBitmap(number[player.getCurrentGold()%10], matrixglobal, null);
                            break;
                        case 4:
                            matrixglobal.setTranslate((780)*fx, 80*fy);
                            matrixglobal.postScale(scalex,scaley,(780)*fx, 80*fy);
                            canvas.drawBitmap(number[player.getCurrentGold() / 1000],matrixglobal, null);
                            matrixglobal.setTranslate((780+distance)*fx, 80*fy);
                            matrixglobal.postScale(scalex,scaley,(780+distance)*fx, 80*fy);
                            canvas.drawBitmap(number[(player.getCurrentGold() / 100) % 10], matrixglobal, null);
                            matrixglobal.setTranslate((780+distance*2)*fx, 80*fy);
                            matrixglobal.postScale(scalex,scaley,(780+distance*2)*fx, 80*fy);
                            canvas.drawBitmap(number[(player.getCurrentGold() / 10) % 10], matrixglobal, null);
                            matrixglobal.setTranslate((780+distance*3)*fx, 80*fy);
                            matrixglobal.postScale(scalex,scaley,(780+distance*3)*fx, 80*fy);
                            canvas.drawBitmap(number[player.getCurrentGold()%10], matrixglobal, null);
                            break;
                        default:
                            matrixglobal.setTranslate((780)*fx, 80*fy);
                            matrixglobal.postScale(scalex,scaley,(780)*fx, 80*fy);
                            canvas.drawBitmap(number[9],matrixglobal, null);
                            matrixglobal.setTranslate((780+distance)*fx, 80*fy);
                            matrixglobal.postScale(scalex,scaley,(780+distance)*fx, 80*fy);
                            canvas.drawBitmap(number[9], matrixglobal, null);
                            matrixglobal.setTranslate((780+distance*2)*fx, 80*fy);
                            matrixglobal.postScale(scalex,scaley,(780+distance*2)*fx, 80*fy);
                            canvas.drawBitmap(number[9], matrixglobal, null);
                            matrixglobal.setTranslate((780+distance*3)*fx, 80*fy);
                            matrixglobal.postScale(scalex,scaley,(780+distance*3)*fx, 80*fy);
                            canvas.drawBitmap(number[9], matrixglobal, null);
                            break;
                    }


                }
                //
                if(shopex) {
                    System.out.println("success?");
                    matrixglobal.setTranslate(width * fx / 2-100*fx, height * fy / 8);
                    matrixglobal.postScale(scalex, scaley, width * fx / 2-100*fx, height * fy / 8);
                    canvas.drawBitmap(easy, matrixglobal, null);
                    matrixglobal.setTranslate((width) * fx / 2-100*fx, (height) * fy / 8+150*fy);
                    matrixglobal.postScale(scalex, scaley, (width) * fx / 2-100*fx, (height) * fy / 8+150*fy);
                    canvas.drawBitmap(med, matrixglobal, null);
                    matrixglobal.setTranslate((width) * fx / 2-100*fx, (height ) * fy / 8+300*fy);
                    matrixglobal.postScale(scalex, scaley, (width) * fx / 2-100*fx, (height ) * fy / 8+300*fy);
                    canvas.drawBitmap(hard, matrixglobal, null);
                    matrixglobal.setTranslate((width)* fx/ 2-100*fx, (height ) * fy / 8+450*fy);
                    matrixglobal.postScale(scalex, scaley, (width) * fx / 2-100*fx, (height ) * fy / 8+450*fy);
                    canvas.drawBitmap(shopbut, matrixglobal, null);
                    matrixglobal.setTranslate(15*fx, (height-160) * fy);
                    matrixglobal.postScale(scalex, scaley, 15*fx, (height-160) * fy);
                    canvas.drawBitmap(logout,matrixglobal,null);


                }

        }

        if(gamemodedone){

            matrixglobal.setTranslate(0,0);
            matrixglobal.postScale(scalex,scaley,0,0);
            canvas.drawBitmap(settings, matrixglobal, null);

            matrixglobal.setTranslate(190*fx,25*fy);
            matrixglobal.postScale(scalex,scaley,190*fx,25*fy);
            canvas.drawBitmap(Score, matrixglobal, null);
            Matrix matriixMatrix = new Matrix();
            matriixMatrix.setTranslate(0,0);
            matriixMatrix.preScale(fx,fy);
            // canvas.drawBitmap(gameoverback, matriixMatrix, null);
            final int distance = 60,distancescore=40; //distance between each number
            //  canvas.drawBitmap(Score2,380,25,null);


            switch (String.valueOf(score).length()) {
                case 1:
                    matrixglobal.setTranslate(390*fx, 15*fy);
                    matrixglobal.postScale(scalex,scaley,390*fx, 15*fy);
                    canvas.drawBitmap(number[0], matrixglobal, null);

                    break;
                case 2:
                    matrixglobal.setTranslate(390*fx, 15*fy);
                    matrixglobal.postScale(scalex,scaley,390*fx, 15*fy);
                    canvas.drawBitmap(number[score / 10], matrixglobal, null);
                    matrixglobal.setTranslate((390+distance)*fx, 15*fy);
                    matrixglobal.postScale(scalex,scaley,(390+distance)*fx, 15*fy);
                    canvas.drawBitmap(number[0], matrixglobal, null);

                    break;
                case 3:
                    matrixglobal.setTranslate(390*fx, 15*fy);
                    matrixglobal.postScale(scalex,scaley,390*fx, 15*fy);
                    canvas.drawBitmap(number[score / 100], matrixglobal, null);
                    matrixglobal.setTranslate((390+distance)*fx, 15*fy);
                    matrixglobal.postScale(scalex,scaley,(390+distance)*fx, 15*fy);
                    canvas.drawBitmap(number[(score / 10) % 10], matrixglobal, null);
                    matrixglobal.setTranslate((390+distance*2)*fx, 15*fy);
                    matrixglobal.postScale(scalex,scaley,(390+distance*2)*fx, 15*fy);
                    canvas.drawBitmap(number[0], matrixglobal, null);
                    break;
                case 4:
                    matrixglobal.setTranslate(390*fx, 15*fy);
                    matrixglobal.postScale(scalex,scaley,390*fx, 15*fy);
                    canvas.drawBitmap(number[score / 1000], matrixglobal, null);
                    matrixglobal.setTranslate((390+distance)*fx, 15*fy);
                    matrixglobal.postScale(scalex,scaley,(390+distance)*fx, 15*fy);
                    canvas.drawBitmap(number[(score / 100) % 10], matrixglobal, null);
                    matrixglobal.setTranslate((390+distance*2)*fx, 15*fy);
                    matrixglobal.postScale(scalex,scaley,(390+distance*2)*fx, 15*fy);
                    canvas.drawBitmap(number[(score / 10) % 10],matrixglobal, null);
                    matrixglobal.setTranslate((390+distance*3)*fx, 15*fy);
                    matrixglobal.postScale(scalex,scaley,(390+distance*3)*fx, 15*fy);
                    canvas.drawBitmap(number[0],matrixglobal, null);


            }

            switch (heartnum) {
                case 3:
                    matrixglobal.setTranslate((width - 275)*fx , 20*fy);
                    matrixglobal.postScale(scalex,scaley,(width - 275)*fx, 20*fy);
                    canvas.drawBitmap(heart1[0],matrixglobal, null);
                    matrixglobal.setTranslate((width - 185)*fx , 20*fy);
                    matrixglobal.postScale(scalex,scaley,(width - 185)*fx, 20*fy);
                    canvas.drawBitmap(heart2[0], matrixglobal, null);
                    matrixglobal.setTranslate((width - 100)*fx , 20*fy);
                    matrixglobal.postScale(scalex,scaley,(width - 100)*fx,20*fy);
                    canvas.drawBitmap(heart3[0], matrixglobal, null);
                    break;
                case 2:
                    matrixglobal.setTranslate((width - 275)*fx , 20*fy);
                    matrixglobal.postScale(scalex,scaley,(width - 275)*fx, 20*fy);
                    canvas.drawBitmap(heart1[1],matrixglobal, null);
                    matrixglobal.setTranslate((width - 185)*fx , 20*fy);
                    matrixglobal.postScale(scalex,scaley,(width - 185)*fx, 20*fy);
                    canvas.drawBitmap(heart2[0], matrixglobal, null);
                    matrixglobal.setTranslate((width - 100)*fx , 20*fy);
                    matrixglobal.postScale(scalex,scaley,(width - 100)*fx,20*fy);
                    canvas.drawBitmap(heart3[0], matrixglobal, null);
                    break;
                case 1:
                    matrixglobal.setTranslate((width - 275)*fx , 20*fy);
                    matrixglobal.postScale(scalex,scaley,(width - 275)*fx, 20*fy);
                    canvas.drawBitmap(heart1[1],matrixglobal, null);
                    matrixglobal.setTranslate((width - 185)*fx , 20*fy);
                    matrixglobal.postScale(scalex,scaley,(width - 185)*fx, 20*fy);
                    canvas.drawBitmap(heart2[1], matrixglobal, null);
                    matrixglobal.setTranslate((width - 100)*fx , 20*fy);
                    matrixglobal.postScale(scalex,scaley,(width - 100)*fx,20*fy);
                    canvas.drawBitmap(heart3[0], matrixglobal, null);
                    break;
                case 0:
                    finishgame = false;
                    matrixglobal.setTranslate(145*fx,40*fy);
                    matrixglobal.postScale(scalex,scaley,145*fx,40*fy);
                    canvas.drawBitmap(gameoverback, matrixglobal, null);
                    matrixglobal.setTranslate((width)* fx/ 2-100*fx, (height ) * fy / 8+450*fy);
                    matrixglobal.postScale(scalex, scaley, (width) * fx / 2-100*fx, (height ) * fy / 8+450*fy);
                    canvas.drawBitmap(play,matrixglobal,null);
                    matrixglobal.setTranslate(15*fx, (height-160) * fy);
                    matrixglobal.postScale(scalex, scaley, 15*fx, (height-160) * fy);
                    canvas.drawBitmap(home,matrixglobal,null);
                    matrixglobal.setTranslate(330*fx,259*fy);
                    matrixglobal.postScale(scalex,scaley,330*fx,259*fy);
                    canvas.drawBitmap(coin,matrixglobal,null);
                    if(done){
                        if(bm)
                            soundPlayer.playGameOver();
                        if(maxCombo<player.getLongestCombo())
                            maxCombo=player.getLongestCombo();
                        System.out.println("popopo v3 "+maxCombo);
                        maxGoldEarned=score/10;
                        if(score/10*Gamemode<player.getMostGoldEarnedInSingleGame())
                            maxGoldEarned=player.getMostGoldEarnedInSingleGame();
                        highscore=score;
                        if(score<player.getHighScore())
                            highscore=player.getHighScore();
                        updatedata(player.getUsername(),new Player(player.getUsername(),player.getNumberOfGames()+1,0,maxGoldEarned,maxCombo,player.getCurrentGold()+(score/10)*Gamemode,highscore));
                        done =false;}
                    switch (String.valueOf(score).length()) {
                        case 1:
                            matrixglobal.setTranslate(625*fx, 465*fy);
                            matrixglobal.postScale(scalex,scaley,625*fx, 465*fy);
                            canvas.drawBitmap(number[0], matrixglobal, null);
                            break;
                        case 2:
                            matrixglobal.setTranslate(625*fx, 465*fy);
                            matrixglobal.postScale(scalex,scaley,625*fx, 465*fy);
                            canvas.drawBitmap(number[score / 10], matrixglobal, null);
                            matrixglobal.setTranslate((625+distance)*fx, 465*fy);
                            matrixglobal.postScale(scalex,scaley,(625+distance)*fx, 465*fy);
                            canvas.drawBitmap(number[0], matrixglobal, null);
                            break;
                        case 3:
                            matrixglobal.setTranslate(625*fx, 465*fy);
                            matrixglobal.postScale(scalex,scaley,625*fx, 465*fy);
                            canvas.drawBitmap(number[score / 100], matrixglobal, null);
                            matrixglobal.setTranslate((625+distance)*fx, 465*fy);
                            matrixglobal.postScale(scalex,scaley,(625+distance)*fx, 465*fy);
                            canvas.drawBitmap(number[(score / 10) % 10], matrixglobal, null);
                            matrixglobal.setTranslate((625+distance*2)*fx, 465*fy);
                            matrixglobal.postScale(scalex,scaley,(625+distance*2)*fx, 465*fy);
                            canvas.drawBitmap(number[0], matrixglobal, null);
                            break;
                        case 4:
                            matrixglobal.setTranslate(625*fx, 465*fy);
                            matrixglobal.postScale(scalex,scaley,625*fx, 465*fy);
                            canvas.drawBitmap(number[score / 1000], matrixglobal, null);
                            matrixglobal.setTranslate((625+distance)*fx, 465*fy);
                            matrixglobal.postScale(scalex,scaley,(625+distance)*fx, 465*fy);
                            canvas.drawBitmap(number[(score / 100) % 10], matrixglobal, null);
                            matrixglobal.setTranslate((625+distance*2)*fx, 465*fy);
                            matrixglobal.postScale(scalex,scaley,(625+distance*2)*fx, 465*fy);
                            canvas.drawBitmap(number[(score / 10) % 10], matrixglobal, null);
                            matrixglobal.setTranslate((625+distance*3)*fx, 465*fy);
                            matrixglobal.postScale(scalex,scaley,(625+distance*3)*fx, 465*fy);
                            canvas.drawBitmap(number[0], matrixglobal, null);
                            break;

                    }


                    switch (String.valueOf(highscore).length()){

                        case 1:
                            matrixglobal.setTranslate((645)*fx, 362*fy);
                            matrixglobal.postScale(scalex,scaley,(645)*fx, 362*fy);
                            canvas.drawBitmap(number[0], matrixglobal, null);
                            break;
                        case 2:
                            matrixglobal.setTranslate((645)*fx, 362*fy);
                            matrixglobal.postScale(scalex,scaley,(645)*fx, 362*fy);
                            canvas.drawBitmap(number[highscore / 10],matrixglobal, null);
                            matrixglobal.setTranslate((645+distance)*fx, 362*fy);
                            matrixglobal.postScale(scalex,scaley,(645+distance)*fx, 362*fy);
                            canvas.drawBitmap(number[0], matrixglobal, null);
                            break;
                        case 3:
                            matrixglobal.setTranslate((645)*fx, 362*fy);
                            matrixglobal.postScale(scalex,scaley,(645)*fx, 362*fy);
                            canvas.drawBitmap(number[highscore / 100],matrixglobal, null);
                            matrixglobal.setTranslate((645+distance)*fx, 362*fy);
                            matrixglobal.postScale(scalex,scaley,(645+distance)*fx, 362*fy);
                            canvas.drawBitmap(number[(highscore / 10) % 10], matrixglobal, null);
                            matrixglobal.setTranslate((645+distance*2)*fx, 362*fy);
                            matrixglobal.postScale(scalex,scaley,(645+distance*2)*fx, 362*fy);
                            canvas.drawBitmap(number[0], matrixglobal, null);
                            break;
                        case 4:
                            matrixglobal.setTranslate((645)*fx, 362*fy);
                            matrixglobal.postScale(scalex,scaley,(645)*fx, 362*fy);
                            canvas.drawBitmap(number[highscore / 1000],matrixglobal, null);
                            matrixglobal.setTranslate((645+distance)*fx, 362*fy);
                            matrixglobal.postScale(scalex,scaley,(645+distance)*fx, 362*fy);
                            canvas.drawBitmap(number[(highscore / 100) % 10], matrixglobal, null);
                            matrixglobal.setTranslate((645+distance*2)*fx, 362*fy);
                            matrixglobal.postScale(scalex,scaley,(645+distance*2)*fx, 362*fy);
                            canvas.drawBitmap(number[(highscore / 10) % 10], matrixglobal, null);
                            matrixglobal.setTranslate((645+distance*3)*fx, 362*fy);
                            matrixglobal.postScale(scalex,scaley,(645+distance*3)*fx, 362*fy);
                            canvas.drawBitmap(number[0], matrixglobal, null);
                            break;
                    }
                    switch (String.valueOf(player.getCurrentGold()).length()){
                        case 1:
                            matrixglobal.setTranslate((420)*fx, 259*fy);
                            matrixglobal.postScale(scalex,scaley,(420)*fx, 259*fy);
                            canvas.drawBitmap(number[player.getCurrentGold()%10], matrixglobal, null);
                            break;
                        case 2:
                            matrixglobal.setTranslate((420)*fx, 259*fy);
                            matrixglobal.postScale(scalex,scaley,(420)*fx, 259*fy);
                            canvas.drawBitmap(number[player.getCurrentGold() / 10],matrixglobal, null);
                            matrixglobal.setTranslate((420+distance)*fx, 259*fy);
                            matrixglobal.postScale(scalex,scaley,(420+distance)*fx, 259*fy);
                            canvas.drawBitmap(number[player.getCurrentGold()%10],matrixglobal, null);
                            break;
                        case 3:
                            matrixglobal.setTranslate((420)*fx, 259*fy);
                            matrixglobal.postScale(scalex,scaley,(420)*fx, 259*fy);
                            canvas.drawBitmap(number[player.getCurrentGold() / 100],matrixglobal, null);
                            matrixglobal.setTranslate((420+distance)*fx, 259*fy);
                            matrixglobal.postScale(scalex,scaley,(420+distance)*fx, 259*fy);
                            canvas.drawBitmap(number[(player.getCurrentGold() / 10) % 10],matrixglobal, null);
                            matrixglobal.setTranslate((420+distance*2)*fx, 259*fy);
                            matrixglobal.postScale(scalex,scaley,(420+distance*2)*fx, 259*fy);
                            canvas.drawBitmap(number[player.getCurrentGold()%10], matrixglobal, null);
                            break;
                        case 4:
                            matrixglobal.setTranslate((420)*fx, 259*fy);
                            matrixglobal.postScale(scalex,scaley,(420)*fx, 259*fy);
                            canvas.drawBitmap(number[player.getCurrentGold() / 1000],matrixglobal, null);
                            matrixglobal.setTranslate((420+distance)*fx, 259*fy);
                            matrixglobal.postScale(scalex,scaley,(420+distance)*fx, 259*fy);
                            canvas.drawBitmap(number[(player.getCurrentGold() / 100) % 10], matrixglobal, null);
                            matrixglobal.setTranslate((420+distance*2)*fx, 259*fy);
                            matrixglobal.postScale(scalex,scaley,(420+distance*2)*fx, 259*fy);
                            canvas.drawBitmap(number[(player.getCurrentGold() / 10) % 10], (420 + distance * 2)*fx*density, 259*fy*density, null);
                            matrixglobal.setTranslate((420+distance*3)*fx, 259*fy);
                            matrixglobal.postScale(scalex,scaley,(420+distance*3)*fx, 259*fy);
                            canvas.drawBitmap(number[player.getCurrentGold()%10], matrixglobal, null);
                            break;
                        default:
                            matrixglobal.setTranslate((420)*fx, 259*fy);
                            matrixglobal.postScale(scalex,scaley,(420)*fx, 259*fy);
                            canvas.drawBitmap(number[9],matrixglobal, null);
                            matrixglobal.setTranslate((420+distance)*fx, 259*fy);
                            matrixglobal.postScale(scalex,scaley,(420+distance)*fx, 259*fy);
                            canvas.drawBitmap(number[9], matrixglobal, null);
                            matrixglobal.setTranslate((420+distance*2)*fx, 259*fy);
                            matrixglobal.postScale(scalex,scaley,(420+distance*2)*fx, 259*fy);
                            canvas.drawBitmap(number[9], (420 + distance * 2)*fx*density, 259*fy*density, null);
                            matrixglobal.setTranslate((420+distance*3)*fx, 259*fy);
                            matrixglobal.postScale(scalex,scaley,(420+distance*3)*fx, 259*fy);
                            canvas.drawBitmap(number[9], matrixglobal, null);
                            break;

                    }
                    defualt :
                    break;


            }

            if(settingpress){
                Matrix matrix=new Matrix();//455,322
                matrix.setTranslate(130*fx,25*fy);
                matrix.postScale(scalex,scaley,130*fx,25*fy);
                canvas.drawBitmap(settingback,matrix, null);
                matrixglobal.setTranslate(900*fx,32*fy);
                matrixglobal.postScale(scalex,scaley,900*fx,32*fy);
                canvas.drawBitmap(xbutton,matrixglobal,null);
                matrixglobal.setTranslate((width)* fx/ 2-200*fx, (height ) * fy / 8+450*fy);
                matrixglobal.postScale(scalex, scaley, (width) * fx / 2-200*fx, (height ) * fy / 8+450*fy);
                canvas.drawBitmap(highscoresbut,matrixglobal,null);

                if(player.isBK()){
                    canvas.drawBitmap(boxBM[1],710*fx,205*fy,null);
                }
                else{
                    canvas.drawBitmap(boxBM[0],710*fx,205*fy,null);
                }
                if(player.isSE())
                    canvas.drawBitmap(boxBM[1],710*fx,318*fy,null);
                else{
                    canvas.drawBitmap(boxBM[0],710*fx,318*fy,null);
                }

            }
            if(isHighscores){
                settingpress=false;
                matrixglobal.setTranslate(130*fx,25*fy);
                matrixglobal.postScale(scalex,scaley,130*fx,25*fy);
                canvas.drawBitmap(highscores,matrixglobal,null);
                matrixglobal.setTranslate(900*fx,32*fy);
                matrixglobal.postScale(scalex,scaley,900*fx,32*fy);
                canvas.drawBitmap(xbutton,matrixglobal,null);
                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                paint.setTextSize(45);
                paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC));
                canvas.drawText(""+gameData.MostGoldEarnedInSingleGame,800*fx,250*fy,paint);
                canvas.drawText(""+gameData.longestCombo,550*fx,335*fy,paint);
                canvas.drawText(""+gameData.HighestGold,500*fx,415*fy,paint);
                canvas.drawText(""+gameData.HighestScore,550*fx,520*fy,paint);

            }
            Random random = new Random();
            if (finishgame) {
                if (!settingpress) {

                    if(!isHighscores) {

                        if (!((arrowheady > (applecory - 30 *fy* scaleappley) && arrowheady < (applecory + 60 * scaleappley)) && (arrowheadx > (applecorx - 25 * scaleapplex) && arrowheadx < (applecorx + 55 * scaleapplex))) && appleboolean) {
                            matrixglobal.setTranslate(applecorx, applecory);
                            matrixglobal.postScale(scaleapplex, scaleappley, applecorx, applecory);
                            canvas.drawBitmap(apple, matrixglobal, null);

                        } else {
                            if (appleboolean == true && se) {
                                soundPlayer.playHitSound();
                            }
                            appleboolean = false;
                        }



      /* canvas.drawBitmap(black,width/3+41,height/2+44,null);
       canvas.drawBitmap(black,width /3,height/2+44,null);
      canvas.drawBitmap(black,width/3,height / 2,null);
        canvas.drawBitmap(black,width/3+41,height / 2,null);*/
                        if (touch == true) {
                            Matrix matrix = new Matrix();
                            if (rotation == -47) {
                                maxDegree = true;
                            }
                            if (rotation == 46)
                                maxDegree = false;
                            if (!flag) {
                                if (maxDegree && rotation < 46) {
                                    matrix.postRotate(rotation, bow[0].getWidth() / 2, bow[0].getHeight() / 2);
                                    rotation += 3;
                                }

                                if (!maxDegree && rotation > -48) {
                                    matrix.postRotate(rotation, bow[0].getWidth() / 2, bow[0].getHeight() / 2);
                                    rotation -= 3;
                                }
                            } else {
                                matrix.postRotate(rotation, (bow[0].getWidth() / 2), bow[0].getHeight() / 2);
                            }
                            matrix.postTranslate((width / 9 - bow[0].getWidth() / 2) * fx, (height * 16 / 32 - bow[0].getHeight() / 2) * fy);
                            matrix.postScale(scalex, scaley, (width / 9 - bow[0].getWidth() / 2) * fx, (height * 16 / 32 - bow[0].getHeight() / 2) * fy);
                            canvas.drawBitmap(bow[player.equiped], matrix, null);
                        }
                        System.out.println(up +" test "+T);
                        if (up && T % 5 == 0) {
                            midair = true;
                            if (first) {
                                pressTime = z;
                                if (pressTime > 1000)
                                    pressTime = 1;
                                else {
                                    pressTime = pressTime / 1000;
                                }
                            }
                            if (z != 0)
                                first = false;
                            if (bound) {
                                bound = false;

                            }

                            rotateafter = rotation;

                            if (angleboolean) {
                                yspeed = ((pressTime * Math.sin(Math.toRadians(rotateafter)) * -Speed - (Time * 0.9)) * fy);
                                xspeed = ((pressTime * Math.cos(Math.toRadians(rotateafter)) * Speed * 1.65) * fx);
                            }

                            Matrix matrixafter = new Matrix();
                            matrixafter.postRotate(rotateafter, bow[0].getWidth() / 2, bow[0].getHeight() / 2);
                            matrixafter.postTranslate((width / 9 - bow[0].getWidth() / 2) * fx, (height * 16 / 32 - bow[0].getHeight() / 2) * fy);
                            matrixafter.postScale(scalex, scaley, (width / 9 - bow[0].getWidth() / 2) * fx, (height * 16 / 32 - bow[0].getHeight() / 2) * fy);
                            canvas.drawBitmap(bowafter[player.equiped], matrixafter, null);

                            arrow1 = new Arrow(Time, yspeed, xspeed, arrow[player.equiped], width, height, arrow[0].getWidth(), arrow[0].getHeight(), (width / 9 - arrow[0].getWidth() / 2) * fx, ((height - (arrow[0].getHeight())) / 2) * fy, true, false, false, fx, fy);
                            arrow1.setCanvasWidth((float) (canvas.getWidth() - Math.cos(Math.toRadians(arrow1.getAngle())) * arrowwidth * density));
                            arrow1.setCanvasHeight((float) (canvas.getHeight() - Math.sin(Math.toRadians(arrow1.getAngle())) * arrowwidth * density));
                            if (arrow1.getX() && arrow1.getY()) {
                                arrow1.setAb(true);
                                arrow1.setB(false);
                                arrow1.setA(false);
                                System.out.println("ab");
                            } else {
                                arrow1.setTime(timee);
                                angleboolean = false;
                                arrow1.Setx(xcoordinate);
                                arrow1.Sety(ycoordinate);
                                arrow1.setAngle(angle);

                                if (arrow1.getX()) {
                                    arrow1.setB(false);
                                    arrow1.setA(true);
                                    arrow1.setAb(false);
                                    System.out.println("a");

                                } else {
                                    if (arrow1.getY()) {
                                        arrow1.setB(true);
                                        arrow1.setA(false);
                                        arrow1.setAb(false);
                                        System.out.println("b");
                                    } else {
                                        arrow1.setB(false);
                                        arrow1.setA(false);
                                        arrow1.setAb(false);
                                        System.out.println("none");

                                    }
                                }

                            }

                            canvas.drawBitmap(arrow[player.equiped], arrow1.getArrow(), null);
                            if (arrow1.getAb()) {
                                timee = arrow1.Time;
                                xcoordinate = arrow1.x;
                                ycoordinate = arrow1.y;
                                angle = arrow1.getAngle();

                            } else {
                                finish = true;
                            }


                            arrowheady = (int) (arrow1.y + (int) (2 * Math.sqrt(Math.pow(56, 2) + Math.pow(19, 2)) * Math.sin(Math.toRadians(arrow1.getAngle()))));
                            arrowheadx = (int) (arrow1.x + (int) (2 * Math.sqrt(Math.pow(56, 2) + Math.pow(19, 2)) * Math.cos(Math.toRadians(arrow1.getAngle()))));
                            //canvas.drawBitmap(coin,arrowheadx,arrowheady,null);

                            Time += 1;
                        }
                        if (finish) {
                            restart();
                        }
                    }
                }
            }
        }
        handler.postDelayed(runnable, Delay);
    }
    private void restart() {
        delayfinish++;
        System.out.println("meeee"+delayfinish);
        if (delayfinish == 4) {
            midair = false;
            if (appleboolean) {
                if (heartnum != 0)
                    heartnum--;
                combo = 0;
            } else {
                score += 10;
                combo++;
                System.out.println("popopo"+combo);
                if (combo > maxCombo)
                    maxCombo = combo;
                System.out.println("popopo v2 "+maxCombo);
            }
            appleboolean = true;
            firsttime = true;
            finish = false;
            recreate = false;
            rotation = 10;
            xcoordinate = 0;
            ycoordinate = 0;
            double angle = 0;
            timeInMilliseconds = 0;
            applecorx = (int) (fx * (random.nextInt(644) + 483));
            applecory = (int) (fy * random.nextInt(428) + 100);
            delayfinish = 0;
            x = 5;
            Time = 1;
            z = 0;
            check = true;
            touch = true;
            flag = false;
            up = false;
            first = true;
            settingpress = false;
            bound = true;
            maxDegree = true;
            angleboolean = true;
            heartFrame = 0;
            bowFrame = 0;
            T = 1; // work
            System.out.println("meemee"+up);
        }
    }
    private void updatedata(String key ,Player player1 ) {
        player.setCurrentGold(player1.getCurrentGold());
        player.setLogestGame(player1.getLogestGame());
        player.setLongestCombo(player1.getLongestCombo());
        player.setMostGoldEarnedInSingleGame(player1.getMostGoldEarnedInSingleGame());
        player.setNumberOfGames(player1.getNumberOfGames());
        player.setHighScore(player1.getHighScore());
        PlayerInfo.child(key).setValue(player);
    }
    private void updateSE(String key ,boolean SE ) {
        player.setSE(SE);
        PlayerInfo.child(key).setValue(player);
        System.out.println("seee"+player.isSE());
    }
    private void updateBK(String key ,boolean BK ) {
        player.setBK(BK);
        PlayerInfo.child(key).setValue(player);
    }
    public void updateCollection(String key ,boolean collection){
        final int num = arrownum;

        switch (num){
            case 0:
                player.setBow1(Collection[0]);
                break;
            case 1:
                player.setBow2(Collection[1]);
                break;
            case 2:
                player.setBow3(Collection[2]);
                break;
            case 3:
                player.setBow4(Collection[3]);
                break;
            case 4:
                player.setBow5(Collection[4]);
                break;
        }

        PlayerInfo.child(key).setValue(player);

    }
    public void updateEquiped(String key,int Equiped){
        player.setEquiped(Equiped);
        PlayerInfo.child(key).setValue(player);
    }
}

