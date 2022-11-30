package com.prempal.actmobileassignment.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.prempal.actmobileassignment.Models.CountryNamesModel;
import com.prempal.actmobileassignment.R;
import com.prempal.actmobileassignment.ViewModel.SharedViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class BottomFragRvAdapter extends RecyclerView.Adapter<com.prempal.actmobileassignment.Adapters.BottomFragRvAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CountryNamesModel> countryNamesModel;
    private BottomSheetDialog bottomSheetDialog;
    private int lastCheckedPosition = -1, pos;
    private SharedViewModel sharedViewModel;


    public BottomFragRvAdapter(Context context, ArrayList<CountryNamesModel> countryNamesModel, BottomSheetDialog bottomSheetDialog, SharedViewModel sharedViewModel) {
        this.context = context;
        this.countryNamesModel = countryNamesModel;
        this.bottomSheetDialog = bottomSheetDialog;
        this.sharedViewModel = sharedViewModel;

    }

    @NonNull
    @Override
    public com.prempal.actmobileassignment.Adapters.BottomFragRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.items_allregions_rv, parent, false);
        com.prempal.actmobileassignment.Adapters.BottomFragRvAdapter.ViewHolder viewHolder = new com.prempal.actmobileassignment.Adapters.BottomFragRvAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.prempal.actmobileassignment.Adapters.BottomFragRvAdapter.ViewHolder holder, int position) {

        CountryNamesModel cModel = countryNamesModel.get(position);
        String countryName = cModel.getName();
        holder.regionTv.setText(countryName);

        String countryCode = cModel.getCode().toLowerCase();
        String imageurl = "https://flagpedia.net/data/flags/normal/" + countryCode + ".png";

        Picasso.get().load(imageurl).into(holder.flagIv);

        holder.selectionRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String selectedregion = cModel.getName();
                String selectedregionflag = "https://flagpedia.net/data/flags/normal/" + cModel.getCode().toLowerCase() + ".png";

                sharedViewModel.setSelectedRegion(selectedregion);
                sharedViewModel.setSelectedRegionFlag(selectedregionflag);
                sharedViewModel.setSelectedRegionPosition(position);

                Log.d("adapPos", String.valueOf(position));

                if (lastCheckedPosition == position){
                    holder.selectionRb.setChecked(true);
                    holder.regionTv.setTypeface(null, Typeface.BOLD);
                }else{
                    holder.selectionRb.setChecked(false);
                    holder.regionTv.setTypeface(null, Typeface.NORMAL);
                }

                    bottomSheetDialog.dismiss();

            }
        });

    }

    @Override
    public int getItemCount() {
        return countryNamesModel.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView flagIv;
        TextView regionTv;
        RadioButton selectionRb=null;
        ConstraintLayout mainCl;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            flagIv = itemView.findViewById(R.id.region_flag_iv);
            regionTv = itemView.findViewById(R.id.region_name_tv);
            selectionRb = itemView.findViewById(R.id.selection_rb);
            mainCl = itemView.findViewById(R.id.main_cl);

        }
    }

    private void setOneSelectionOnly(int pos){

    }
}