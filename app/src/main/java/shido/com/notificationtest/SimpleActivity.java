package shido.com.notificationtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SimpleActivity extends AppCompatActivity {

    public static final String TITLE_EXTRA = "title extra";
    public static final String BODY_TEXT_EXTRA ="body text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Intent createintent = getIntent();
        String title = createintent.getStringExtra(TITLE_EXTRA);
        String body = createintent.getStringExtra(BODY_TEXT_EXTRA);

        if (title != null){
            setTitle(title);
        }
        if(body!=null){
            ((TextView) findViewById(R.id.textSimpleActivity)).setText(body);
        }
    }
}
