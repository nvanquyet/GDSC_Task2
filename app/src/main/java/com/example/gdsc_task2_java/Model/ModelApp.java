package com.example.gdsc_task2_java.Model;

import android.graphics.drawable.Drawable;

public class ModelApp {
    public String nameApp;
    public Drawable icApp;
    public String packageName;

    public ModelApp(String nameApp, Drawable icApp, String packageName) {
        this.nameApp = nameApp;
        this.icApp = icApp;
        this.packageName = packageName;
    }
}
