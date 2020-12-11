package ch.bbw.sodacounter.persistence;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.JsonReader;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import ch.bbw.sodacounter.models.SodaEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class EntryController {

    private Context context;

    public EntryController(Context context) {
        this.context = context;
    }

    public void writeDataToJson(String sodaName, String calories, String description, String imagePath) throws JSONException, IOException {
        File file = new File(context.getFilesDir(),"savedSodas.json");

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

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = new Date();
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(text.toString());
        }
        catch (Exception e) {
            jsonArray = new JSONArray();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sodaName", sodaName);
        jsonObject.put("calories", calories);
        jsonObject.put("imageName", imagePath);
        jsonObject.put("description", description);
        jsonObject.put("dateTime", formatter.format(date));
        jsonArray.put(jsonObject);

        String userString = jsonArray.toString();
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userString);
        bufferedWriter.close();
    }

    public ArrayList<SodaEntry> getSodaEntries() {

        JSONArray jsonArray = getAllSodasArray();
        ArrayList<SodaEntry> sodaEntryArrayList = new ArrayList<SodaEntry>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject item = jsonArray.getJSONObject(i);
                String sodaName = item.get("sodaName").toString();
                String sodaDescription = item.get("description").toString();
                String sodaCalories = item.get("calories").toString();
                String sodaDate = item.get("dateTime").toString();
                String sodaImagePath = !Objects.isNull(item.get("imageName")) ? item.get("imageName").toString() : "";
                SodaEntry sodaEntry = new SodaEntry(sodaName, sodaDescription, sodaCalories, sodaDate, sodaImagePath);
                if (!sodaImagePath.isEmpty()) {
                    try {
                        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                        Bitmap bitmap = BitmapFactory.decodeFile(sodaImagePath, bmOptions);
                        sodaEntry.setBitmap(bitmap);
                    }
                    catch (Exception e) {

                    }
                }
                sodaEntryArrayList.add(sodaEntry);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return sodaEntryArrayList;
    }

    public int getTotalConsumption () {
        try {
            JSONArray jsonArray = getAllSodasArray();

            return jsonArray.length();
        }
        catch (Exception e) {
            return 0;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getAveragePerWeek () throws JSONException, ParseException {
        JSONArray jsonArray = getAllSodasArray();

        ArrayList<Integer> weekArray = new ArrayList<Integer>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = (JSONObject) jsonArray.get(i);
            String stringDate = item.get("dateTime").toString();

            Date date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(stringDate);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(date.getTime());
            Integer week = c.get(c.WEEK_OF_YEAR);
            if (!weekArray.contains(week)) {
                weekArray.add(week);
            }
        }

        return Math.round((jsonArray.length() / weekArray.size()));
    }

    private JSONArray getAllSodasArray () {
        File file = new File(context.getFilesDir(),"savedSodas.json");

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
        return jsonArray;
    }
}
