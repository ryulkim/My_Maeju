package com.example.myapplication_maeju;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    TextView text;

    TimePicker tPicker;
    Button reserve;


    Button bt;
    NotificationManager manager;
    NotificationCompat.Builder builder;
    private static String CHANNEL_ID = "channel1";
    private static String CHANEL_NAME = "Channel1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간 예약");

        //system time
        text=findViewById(R.id.t1);
        text.setText(getTime());

        tPicker=findViewById(R.id.timePicker);
        reserve=findViewById(R.id.reserve);

        //notice
        //setContentView(R.layout.activity_main);
        bt = findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNoti();
            }
        });

        reserve.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(MainActivity.this,
                        Integer.toString(tPicker.getCurrentHour())+"시"+
                        Integer.toString(tPicker.getCurrentMinute())+"분 저장됨"
                ,Toast.LENGTH_LONG).show();
                
            }
        });
    }



    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
    public void showNoti(){
        builder = null;
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //버전 오레오 이상일 경우
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            manager.createNotificationChannel(
                    new NotificationChannel(CHANNEL_ID, CHANEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            );
        builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        }
        builder.setContentTitle("알림");
        // 알림창 메시지
        builder.setContentText("알림 메시지");
        // 알림창 아이콘
        builder.setSmallIcon(R.drawable.heart);
        //알림창 터치시 상단 알림상태창에서 알림이 자동으로 삭제되게 합니다.
        builder.setAutoCancel(true);


        Notification notification = builder.build();
        // 알림창 실행
        manager.notify(1,notification);
    }
}