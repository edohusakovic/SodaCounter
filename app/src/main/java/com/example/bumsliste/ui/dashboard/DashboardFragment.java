package com.example.bumsliste.ui.dashboard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bumsliste.R;
import com.example.bumsliste.databinding.FragmentDashboardBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //String path = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
//
        //File directory = new File(path);
        //File[] files = directory.listFiles();
        //if (files != null) {
        //    for (File file : files) {
        //        Log.d("File", file.getName());
        //        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        //        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),bmOptions);
//
        //        ImageView imageView = new ImageView(getActivity().getApplicationContext());
        //        imageView.setImageBitmap(bitmap);
//
        //        TextView textView = new TextView(getActivity().getApplicationContext());
        //        textView.setText(file.getName());
//
        //        binding.linearLayout.addView(imageView);
        //        binding.linearLayout.addView(textView);
        //    }
        //}

        readJsonInsert();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void readJsonInsert() {
        File file = new File(getActivity().getApplicationContext().getFilesDir(),"savedSodas.json");

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

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(text.toString());
        }
        catch (Exception e) {
            jsonArray = new JSONArray();
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject item = jsonArray.getJSONObject(i);
                String sodaName = item.get("sodaName").toString();
                String sodaDescription = item.get("description").toString();
                String sodaCalories = item.get("calories").toString();
                String sodaDate = item.get("dateTime").toString();
                String sodaImagePath = !Objects.isNull(item.get("imageName")) ? item.get("imageName").toString() : "";

                Log.d("image", sodaImagePath);

                String path = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
                if (!sodaImagePath.isEmpty()) {
                    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    Bitmap bitmap = BitmapFactory.decodeFile(sodaImagePath, bmOptions);
                    ImageView imageView = new ImageView(getActivity().getApplicationContext());
                    imageView.setImageBitmap(bitmap);
                    binding.linearLayout.addView(imageView);
                }

                TextView textView = new TextView(getActivity().getApplicationContext());
                textView.setText(sodaName + "\n" + sodaDescription + "\n" + sodaCalories + "\n" + sodaDate);

                binding.linearLayout.addView(textView);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}