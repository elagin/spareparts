package pasha.elagin.spareparts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pasha.elagin.spareparts.data.Part;
import pasha.elagin.spareparts.data.PartListAdapter;
import pasha.elagin.spareparts.database.Parts;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    final int REQUEST_CODE_ADD_PART = 1;

    private List<Part> partList;
    private Button buttonAddPart;
    private PartListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        partList = new ArrayList<>();
        partList = Parts.get(this);

        buttonAddPart = (Button) findViewById(R.id.buttonAddPart);
        buttonAddPart.setOnClickListener(this);

        adapter = new PartListAdapter(this, partList);

        final ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.buttonAddPart:
                Intent intent = new Intent(this, AddPartActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_PART);
                break;
            default:
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE_ADD_PART) {
            if (resultCode == RESULT_OK) {
                Bundle b = intent.getExtras();
                int count = b.getInt("count");
                for (int i = 0; i < count; i++) {
                    String id = b.getString("id");
                    String name = b.getString("name");
                    Double price = b.getDouble("price");
                    Part part = new Part(id, name, price);
                    partList.add(part);
                    Parts.add(this, part);
                }
                adapter.notifyDataSetChanged();
            }
        }
    }
}
