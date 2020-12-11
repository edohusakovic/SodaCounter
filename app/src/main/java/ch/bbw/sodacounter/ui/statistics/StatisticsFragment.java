package ch.bbw.sodacounter.ui.statistics;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.json.JSONException;

import java.text.ParseException;
import java.util.Map;

import ch.bbw.sodacounter.databinding.FragmentStatisticsBinding;
import ch.bbw.sodacounter.persistence.EntryController;

public class StatisticsFragment extends Fragment {

    private StatisticsViewModel notificationsViewModel;
    private FragmentStatisticsBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(StatisticsViewModel.class);

        binding = FragmentStatisticsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textStatistics;
        EntryController entryController = new EntryController(getActivity().getApplicationContext());
        binding.totalConsumption.setText(Integer.toString(entryController.getTotalConsumption()));
        try {
            binding.averageConsumption.setText(Integer.toString(entryController.getAveragePerWeek()));
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}