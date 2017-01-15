package com.example.user.appprog;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.ViewHolder> implements Filterable {

    private ListFilter filter = new ListFilter();
    static private List<ApplicationInfo> allApp;
    private List<ApplicationInfo> clearListApp;
    private ArrayList<ApplicationInfo> filteredListApp;
    static private PackageManager pm;
    private static Context context;
    private boolean[] checked;


    public RecViewAdapter(Context cont, PackageManager packManag, List<ApplicationInfo> objects) {
        context = cont;
        allApp = objects;
        pm = packManag;
        clearListApp = new ArrayList<>(objects);
        filteredListApp = new ArrayList<>();
        checked = new boolean[allApp.size()];
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameApp;
        ImageView imageApp;
        CheckBox chBox;


        ViewHolder(View view) {
            super(view);
            nameApp = (TextView) view.findViewById(R.id.name_app);
            imageApp = (ImageView) view.findViewById(R.id.image_app);
            chBox = (CheckBox) view.findViewById(R.id.check_box_app);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = pm.getLaunchIntentForPackage(allApp.get(getPosition()).packageName);
            context.startActivity(intent);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(AppActivity.getLayo(), parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.nameApp.setText(allApp.get(position).loadLabel(pm));
        holder.imageApp.setImageDrawable(allApp.get(position).loadIcon(pm));
        holder.chBox.setChecked(checked[position]);
        holder.chBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked[position] = !checked[position];
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchApp(allApp.get(position));
            }
        });
    }

    private void launchApp(ApplicationInfo applicationInfo) {
        Intent launchIntent = pm.getLaunchIntentForPackage(applicationInfo.packageName);
        if (launchIntent != null) {
            context.startActivity(launchIntent);
        }
    }

    @Override
    public int getItemCount() {
        return allApp.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }


    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            allApp.clear();
            filteredListApp.clear();
            FilterResults filterResults = new FilterResults();
            String str = constraint.toString().toLowerCase();

            if (constraint != null || constraint.length() != 0) {
                for (ApplicationInfo obj : clearListApp) {
                    if (obj.loadLabel(pm).toString().toLowerCase().contains(str)) {
                        filteredListApp.add(obj);
                    }
                }
            }

            filterResults.values = filteredListApp;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            allApp.addAll((List<ApplicationInfo>) results.values);
            notifyDataSetChanged();
        }
    }
}
