package ch.bbw.sodacounter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;

import ch.bbw.sodacounter.persistence.PersonController;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        findViewById(R.id.button).setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PersonController personController = new PersonController(v.getContext());
                EditText name = (EditText) findViewById(R.id.name);
                EditText weight = (EditText) findViewById(R.id.weight);
                EditText age = (EditText) findViewById(R.id.age);
                EditText notificationHour = (EditText) findViewById(R.id.notificationHour);
                EditText goal = (EditText) findViewById(R.id.goal);

                //try {
                //    //personController.savePersonSettings(name.getText().toString(), Integer.parseInt(age.getText().toString()), Float.parseFloat(weight.getText().toString()), Integer.parseInt(goal.getText().toString()), Integer.parseInt(notificationHour.getText().toString()));
                //} catch (JSONException e) {
                //    e.printStackTrace();
                //} catch (IOException e) {
                //    e.printStackTrace();
                //}
            }
        });

    }
}
