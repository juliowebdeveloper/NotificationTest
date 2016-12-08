package shido.com.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final static int NOTIFY_ID = 1;

    private Button btnNotify;
    private  Button btnNotifyPersonal;
    private Button btnNotifyMulti;
    private Button btnNotifyBigText;
    private Button btnNotifyBigPicture;
    private int notifyCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotify = (Button) findViewById(R.id.notify);
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNotifyOnClick(btnNotify);
            }
        });

        btnNotifyPersonal = (Button) findViewById(R.id.notifypersonal);
        btnNotifyPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNotifyPersonalOnClick(btnNotifyPersonal);
            }
        });


        btnNotifyMulti = (Button) findViewById(R.id.notifymulti);
        btnNotifyMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNotifyMultiOnClick();
            }
        });


        btnNotifyBigText = (Button) findViewById(R.id.notifybigtext);
        btnNotifyBigText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNotifyBigTextClick();
            }
        });


        btnNotifyBigPicture = (Button) findViewById(R.id.notifyBigPicture);
        btnNotifyBigPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNotifyBigPictureClick();
            }
        });
    }


    private void btnNotifyBigPictureClick(){
        String title ="Meow";
        String text ="Never mind..  just being a cat";
        String bigTitle= "Growing Up";
        String bigSummay ="This is a big picture of a cat summary";

        Intent i = new Intent(this, SimplePictureActivity.class);
        i.putExtra(SimplePictureActivity.TITLE_EXTRA, title);
        i.putExtra(SimplePictureActivity.IMAGE_RESOURCE_ID_EXTRA, R.drawable.bigcat);
        i.setAction("NotifyBigPicture");
        NotificationCompat.Builder builder = initBasicBuilder(title, text ,i);

        //Adicionando a grande imagem

        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
            bigPictureStyle.setBigContentTitle(bigTitle)
             .setSummaryText(bigSummay).
             bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.bigcat));

        builder.setStyle(bigPictureStyle);


        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, notification);
    }

    private void btnNotifyBigTextClick(){
        String title ="Meow";
        String text ="Never mind..  just being a cat";
        String bigTitle= "This is a big title";
        String bigSummay ="This is a big summary";
        String notificationText = "hello i am here to show you a bunch of long text to see...";

        Intent i = new Intent(this, SimpleActivity.class);
        i.putExtra(SimpleActivity.TITLE_EXTRA, title);
        i.putExtra(SimpleActivity.BODY_TEXT_EXTRA, text);
        i.setAction("NotifyBigText");
        NotificationCompat.Builder builder = initBasicBuilder(title, text ,i);

        //Adicionando o grande texto
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle(bigTitle)
                .setSummaryText(bigSummay).bigText(notificationText);

        builder.setStyle(bigTextStyle);

        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, notification);
    }




    private void btnNotifyOnClick(Button x){
        String title ="Meow";
        String text ="Never mind..  just being a cat";

        Intent i = new Intent(this, SimpleActivity.class);
        i.putExtra(SimpleActivity.TITLE_EXTRA, title);
        i.putExtra(SimpleActivity.BODY_TEXT_EXTRA, text);
        i.setAction("Notify");
        NotificationCompat.Builder builder = initBasicBuilder(title, text ,i);

        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, notification);
    }

    private void btnNotifyMultiOnClick(){
        String title ="Notify";
        //Texto que vai para o build da notificação
        String text = "You have multiple entries";

        //Informação de fato que irá para a SimpleListActivity
        String detailText1 ="Never mind..  just being a cat";
        String detailText2 ="Just making sure you're paying attention";
        ++notifyCount;
        ArrayList<String>textValues = new ArrayList<String>();
        textValues.add(detailText1);
        textValues.add(detailText2);


        Intent i = new Intent(this, SimpleListActivity.class);
        i.putExtra(SimpleListActivity.TITLE_EXTRA, title);
        i.putExtra(SimpleListActivity.TEXT_VALUES_EXTRA, textValues);
        i.setAction("NotifyMulti");

        //Building the notification e colocando o total de notificações no notifycount
        NotificationCompat.Builder builder = initBasicBuilder(title, text ,i);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.users))
        .setNumber(notifyCount)
        .setTicker("You Received another value");

        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, notification);
    }

    private void btnNotifyPersonalOnClick(Button x){

        Intent i = new Intent(this, SimpleActivity.class);
        i.putExtra(SimpleActivity.TITLE_EXTRA, "Personal Meow");
        i.putExtra(SimpleActivity.BODY_TEXT_EXTRA, "I'm being a personal cat, meow meow");
        i.setAction("Notify Personal");

        NotificationCompat.Builder notificationbuilder = initBasicBuilder("Personal Meow","I'm being a personal cat, meow meow", i );

        //Personalização da notificação
        notificationbuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.cattt));

        Notification notification = notificationbuilder.build();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFY_ID, notification);

    }
    private NotificationCompat.Builder initBasicBuilder(String title, String text, Intent intent){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.man)
        .setContentTitle(title)
        .setContentText(text);

        //Fazendo o Wrapping da Intent numa pending intent
        if(intent != null){
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            builder.setContentIntent(pendingIntent);
        }

        return builder;
    }





}
