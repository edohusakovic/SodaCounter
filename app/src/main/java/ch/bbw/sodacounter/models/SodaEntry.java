package ch.bbw.sodacounter.models;

import android.graphics.Bitmap;

import java.util.Objects;

public class SodaEntry {
    private String sodaName;
    private String sodaDescription;
    private String sodaCalories;
    private String sodaDate;
    private String sodaImagePath;
    private Bitmap bitmap;

    public SodaEntry(String sodaName, String sodaDescription, String sodaCalories, String sodaDate, String sodaImagePath) {
        this.sodaName = sodaName;
        this.sodaDescription = sodaDescription;
        this.sodaCalories = sodaCalories;
        this.sodaDate = sodaDate;
        this.sodaImagePath = sodaImagePath;
    }

    public String getSodaName() {
        return sodaName;
    }

    public void setSodaName(String sodaName) {
        this.sodaName = sodaName;
    }

    public String getSodaDescription() {
        return sodaDescription;
    }

    public void setSodaDescription(String sodaDescription) {
        this.sodaDescription = sodaDescription;
    }

    public String getSodaCalories() {
        return sodaCalories;
    }

    public void setSodaCalories(String sodaCalories) {
        this.sodaCalories = sodaCalories;
    }

    public String getSodaDate() {
        return sodaDate;
    }

    public void setSodaDate(String sodaDate) {
        this.sodaDate = sodaDate;
    }

    public String getSodaImagePath() {
        return sodaImagePath;
    }

    public void setSodaImagePath(String sodaImagePath) {
        this.sodaImagePath = sodaImagePath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
