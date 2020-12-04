package com.example.bumsliste.ui.dashboard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;

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

        String path = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

        File directory = new File(path);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                Log.d("File", file.getName());
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),bmOptions);

                ImageView imageView = new ImageView(getActivity().getApplicationContext());
                imageView.setImageBitmap(bitmap);

                TextView textView = new TextView(getActivity().getApplicationContext());
                textView.setText(file.getName());

                binding.linearLayout.addView(imageView);
                binding.linearLayout.addView(textView);
            }
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}