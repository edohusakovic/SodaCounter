package ch.bbw.sodacounter.persistence;

import android.content.Context;
import android.widget.ExpandableListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.bbw.sodacounter.models.Person;

public class PersonController {
    private Context context;

    public PersonController(Context context) {
        this.context = context;
    }

    public void savePersonSettings (String name, int age, float weight, int goal, int notificationHour) throws JSONException, IOException {
        File file = new File(context.getFilesDir(),"personSettings.json");

        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("age", age);
        jsonObject.put("weight", weight);
        jsonObject.put("goal", goal);
        jsonObject.put("notificationHour", notificationHour);
        jsonArray.put(jsonObject);

        String userString = jsonArray.toString();
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userString);
        bufferedWriter.close();
    }

    public Person getPersonSettings () throws JSONException {
        File file = new File(context.getFilesDir(),"personSettings.json");

        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }

        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(text.toString());
        }
        catch (Exception e) {
            jsonArray = new JSONArray();
        }

        JSONObject item = jsonArray.getJSONObject(0);

        Person person = new Person();
        try {
            person.setName(item.get("name").toString());
        }catch (Exception e){}
        try {
            person.setAge(Integer.parseInt(item.get("age").toString()));
        }
        catch (Exception e) {}
        try {
            person.setWeight(Float.parseFloat(item.get("weight").toString()));
        }
        catch (Exception e) {}
        try {
            person.setGoalSodaPerWeek(Integer.parseInt(item.get("goal").toString()));
        }
        catch (Exception e) {}
        try {
            person.setNotificationTime(Integer.parseInt(item.get("notificationHour").toString()));
        }
        catch (Exception e) {}
        return person;
    }
}
