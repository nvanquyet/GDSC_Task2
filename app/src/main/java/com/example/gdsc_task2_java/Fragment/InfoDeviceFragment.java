package com.example.gdsc_task2_java.Fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gdsc_task2_java.Model.DeviceInfo;
import com.example.gdsc_task2_java.R;
import com.example.gdsc_task2_java.databinding.FragmentFindAppBinding;
import com.example.gdsc_task2_java.databinding.FragmentInfoDeviceBinding;


public class InfoDeviceFragment extends Fragment {

    private FragmentInfoDeviceBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInfoDeviceBinding.inflate(inflater,container,false);
        SetUIValue(GetDeviceInfo());
        return binding.getRoot();
    }

    private DeviceInfo GetDeviceInfo(){
        String name = Build.DEVICE;
        String model = Build.MODEL;
        int sdkVersion = Build.VERSION.SDK_INT;
        String apkVersion = Build.VERSION.RELEASE;
        return new DeviceInfo(name,model,sdkVersion,apkVersion);
    }

    private void SetUIValue(DeviceInfo device){
        binding.tvDeviceName.setText(device.nameDevice);
        binding.tvDeviceModel.setText(device.model);
        binding.tvDeviceSdkVersion.setText(Integer.toString(device.sdkVersion));
        binding.tvDeviceApkVersion.setText(device.apkVersion);
    }

}