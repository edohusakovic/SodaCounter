package com.example.bumsliste.ui.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bumsliste.databinding.FragmentDashboardBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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

                if (!sodaImagePath.isEmpty()) {
                    try {
                        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                        Bitmap bitmap = BitmapFactory.decodeFile(sodaImagePath, bmOptions);
                        ImageView imageView = new ImageView(getActivity().getApplicationContext());
                        imageView.setImageBitmap(bitmap);
                        binding.linearLayout.addView(imageView);
                    }
                    catch (Exception e) {

                    }
                }
                String fullText = "";
                fullText = sodaName.isEmpty() ? fullText : "Name:\n" + sodaName;
                fullText = sodaDescription.isEmpty() ? fullText : fullText  + "\n\nDescription:\n"+ sodaDescription;
                fullText = sodaCalories.isEmpty() ? fullText : fullText  + "\n\nKalorien:\n" + sodaCalories;
                fullText = sodaDate.isEmpty() ? fullText : fullText  + "\n\nDatum:\n" + sodaDate;

                TextView textView = new TextView(getActivity().getApplicationContext());
                textView.setText(fullText);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(100, 0,0,0);
                textView.setLayoutParams(params);

                binding.linearLayout.addView(textView);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}