package ch.bbw.sodacounter.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("No nie bumst fucking Informatiker");
    }

    public LiveData<String> getText() {
        return mText;
    }
}