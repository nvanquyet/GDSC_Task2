package com.example.gdsc_task2_java.Fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gdsc_task2_java.Adapter.AdapterRecyclerView;
import com.example.gdsc_task2_java.Model.ModelApp;
import com.example.gdsc_task2_java.R;
import com.example.gdsc_task2_java.databinding.FragmentFindAppBinding;

import java.util.ArrayList;
import java.util.List;

public class FindAppFragment extends Fragment {
    private FragmentFindAppBinding binding;
    private ArrayList<ModelApp> listApp;

    private RecyclerView recyclerView;

    private AdapterRecyclerView appAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listApp = CreateListApp();
    }

    private ArrayList<ModelApp> CreateListApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager pm = getActivity().getPackageManager();
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);
        ArrayList<ModelApp> listApp = new ArrayList<ModelApp>();
        for (ResolveInfo resolveInfo : resolveInfos) {
            String appName = resolveInfo.loadLabel(pm).toString();
            Drawable appIcon = resolveInfo.loadIcon(pm);
            String packageName = resolveInfo.activityInfo.packageName;
            listApp.add(new ModelApp(appName, appIcon, packageName));
        }
        return listApp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFindAppBinding.inflate(inflater,container,false);
        recyclerView = binding.listApp;
        appAdapter = new AdapterRecyclerView(this.getContext());
        int itemWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        int spawnColums = (int) getResources().getDisplayMetrics().widthPixels / itemWidth;
        GridLayoutManager layout = new GridLayoutManager(this.getContext(), spawnColums,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layout);
        appAdapter.SetData(listApp);
        recyclerView.setAdapter(appAdapter);
        return binding.getRoot();
    }


}