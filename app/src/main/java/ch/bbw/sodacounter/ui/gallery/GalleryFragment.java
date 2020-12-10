package ch.bbw.sodacounter.ui.gallery;

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

import ch.bbw.sodacounter.databinding.FragmentGalleryBinding;
import ch.bbw.sodacounter.models.SodaEntry;
import ch.bbw.sodacounter.persistence.EntryController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GalleryFragment extends Fragment {

    private GalleryViewModel dashboardViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
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
        EntryController entryController = new EntryController(getActivity().getApplicationContext());
        ArrayList<SodaEntry> sodaEntryArrayList = entryController.getSodaEntries();
        for (SodaEntry item : sodaEntryArrayList) {
            if (item.getBitmap() != null) {
                ImageView imageView = new ImageView(getActivity().getApplicationContext());
                imageView.setImageBitmap(item.getBitmap());
                binding.linearLayout.addView(imageView);
            }
            String fullText = "";
            fullText = item.getSodaName().isEmpty() ? fullText : "Name:\n" + item.getSodaName();
            fullText = item.getSodaDescription().isEmpty() ? fullText : fullText  + "\n\nDescription:\n"+ item.getSodaDescription();
            fullText = item.getSodaCalories().isEmpty() ? fullText : fullText  + "\n\nKalorien:\n" + item.getSodaCalories();
            fullText = item.getSodaDate().isEmpty() ? fullText : fullText  + "\n\nDatum:\n" + item.getSodaDate();

            TextView textView = new TextView(getActivity().getApplicationContext());
            textView.setText(fullText);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(100, 0,0,0);
            textView.setLayoutParams(params);

            binding.linearLayout.addView(textView);
        }
    }
}