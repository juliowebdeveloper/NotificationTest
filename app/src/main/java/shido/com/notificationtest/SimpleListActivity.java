package shido.com.notificationtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SimpleListActivity extends AppCompatActivity {


    public static final String TITLE_EXTRA = "title extra";
    public static final String TEXT_VALUES_EXTRA ="body text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list);

        Intent createintent = getIntent();
        String title = createintent.getStringExtra(TITLE_EXTRA);
        ArrayList<String> values = createintent.getStringArrayListExtra(TEXT_VALUES_EXTRA);

        if (title != null){
            setTitle(title);
        }
        if(values != null){
            ((ListView)findViewById(R.id.listView))
                    .setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values));
        }

    }
}
