package shido.com.notificationtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class SimplePictureActivity extends AppCompatActivity {

    public static final String TITLE_EXTRA = "Title" ;
    public static final String IMAGE_RESOURCE_ID_EXTRA = "Image" ;


    ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_picture);

        Intent i = getIntent();
        String title = i.getStringExtra(TITLE_EXTRA);
        int resourceId = i.getIntExtra(IMAGE_RESOURCE_ID_EXTRA, 0);

        if(title != null ){
            setTitle(title);
        }

        if(resourceId != 0){
            ((ImageView) findViewById(R.id.bigCatImage)).setImageResource(resourceId);
        }


    }
}
