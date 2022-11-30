package com.prempal.actmobileassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.prempal.actmobileassignment.Adapters.BottomFragRvAdapter;
import com.prempal.actmobileassignment.Models.CountryNamesModel;
import com.prempal.actmobileassignment.Models.RetrofitModel;
import com.prempal.actmobileassignment.Retrofit.CountryNamesApi;
import com.prempal.actmobileassignment.ViewModel.SharedViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLinearLayout;
    private TextView regionNameTv;
    private ImageView regionFlagIv;
    private RecyclerView recyclerViewBf;
    private BottomFragRvAdapter adapterBottom;
    private ArrayList<CountryNamesModel> countryNamesModel;
    private BottomSheetDialog bottomSheetDialog;
    private SharedViewModel sharedViewModel;
    private String selectedRegion, selectedRegionFlag;
    private Integer adapPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        countryNamesModel = new ArrayList<>();

        mainLinearLayout = findViewById(R.id.mainLinearLayout);
        regionNameTv = findViewById(R.id.selectedRegionName);
        regionFlagIv = findViewById(R.id.selectedRegionFlag);
        Picasso.get().load("https://flagpedia.net/data/flags/normal/in.png").into(regionFlagIv);

        sharedViewModel = new ViewModelProvider(MainActivity.this).get(SharedViewModel.class);
        getCountryNames();

        //bottomsheet
        bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        bottomSheetDialog.setContentView(R.layout.allregions_bottom_fragment);

        recyclerViewBf = bottomSheetDialog.findViewById(R.id.regions_rv);
        LinearLayoutManager layoutManagerBottom= new LinearLayoutManager(MainActivity.this ,LinearLayoutManager.VERTICAL,false);
        recyclerViewBf.setLayoutManager(layoutManagerBottom);
        recyclerViewBf.setHasFixedSize(true);
        //bottomsheet

        mainLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();
            }
        });

    }

    private void getCountryNames() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.printful.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CountryNamesApi countryAPI = retrofit.create(CountryNamesApi.class);
        Call<RetrofitModel> call = countryAPI.getCountryNames();
        call.enqueue(new Callback<RetrofitModel>() {
            @Override
            public void onResponse(Call<RetrofitModel> call, Response<RetrofitModel> response) {
                Log.d("herehere", "on success");

                countryNamesModel = response.body().getResult();

                Log.d("countryNamesModelsize", String.valueOf(countryNamesModel.size()));

                Log.d("adapPossharedFinal", String.valueOf(adapPos));

                adapterBottom = new BottomFragRvAdapter(MainActivity.this, countryNamesModel, bottomSheetDialog, sharedViewModel);
                recyclerViewBf.setAdapter(adapterBottom);
            }

            @Override
            public void onFailure(Call<RetrofitModel> call, Throwable t) {
                Log.d("errorerror", t.toString());
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("cominghere", "in on resume");

        sharedViewModel.getSelectedRegion().observe(MainActivity.this, item -> {
            selectedRegion = sharedViewModel.getSelectedRegion().getValue();
            if (selectedRegion != null) {
                regionNameTv.setText(selectedRegion);
            }else{
                regionNameTv.setText("India");
            }
        });

        sharedViewModel.getSelectedRegionFlag().observe(MainActivity.this, item -> {
            selectedRegionFlag = sharedViewModel.getSelectedRegionFlag().getValue();
            if (selectedRegionFlag != null) {
                Picasso.get().load(selectedRegionFlag).into(regionFlagIv);
            }else{
                Picasso.get().load("https://flagpedia.net/data/flags/normal/in.png").into(regionFlagIv);
            }
        });

        sharedViewModel.getSelectedRegionPos().observe(MainActivity.this, item -> {
            adapPos = sharedViewModel.getSelectedRegionPos().getValue();
            if (adapPos != null) {
                Log.d("adapPosshared", String.valueOf(adapPos));
            }else{
            }
        });

    }
}