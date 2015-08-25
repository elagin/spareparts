package pasha.elagin.spareparts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class AddPartActivity extends ActionBarActivity implements View.OnClickListener {

    Button scanBtn;
    Button button_ok;
    Button button_cancel;

    TextView add_part_id;
    TextView add_part_name;
    TextView add_part_price;
    TextView add_part_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_part);

        scanBtn = (Button) findViewById(R.id.buttonScan);
        scanBtn.setOnClickListener(this);
        button_ok = (Button) findViewById(R.id.button_ok);
        button_ok.setOnClickListener(this);
        button_cancel = (Button) findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(this);

        add_part_id = (TextView) findViewById(R.id.add_part_id);
        add_part_name = (TextView) findViewById(R.id.add_part_name);
        add_part_count = (TextView) findViewById(R.id.add_part_count);
        add_part_price = (TextView) findViewById(R.id.add_part_price);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_part, menu);
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
            case R.id.buttonScan:
                scan();
                break;
            case R.id.button_ok:
                Intent intentOk = new Intent();
                intentOk.putExtra("id", add_part_id.getText().toString());
                intentOk.putExtra("name", add_part_name.getText().toString());
                intentOk.putExtra("count", Integer.decode(add_part_count.getText().toString()));
                intentOk.putExtra("price", Double.parseDouble(add_part_price.getText().toString()));
                setResult(RESULT_OK, intentOk);
                finish();
                break;
            case R.id.button_cancel:
                break;
            default:
                break;
        }
    }

    private void scan() {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                int cutPos = contents.indexOf(" ");
                String catalogNumber = contents.substring(0, cutPos);
                //String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                add_part_id.setText(contents.substring(0, cutPos));
            } else if (resultCode == RESULT_CANCELED) { // Handle cancel
                add_part_id.setText("");
            }
        }
    }
}
