package com.github.guliash.playlist.ui.adapters;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.guliash.playlist.R;

import java.util.ArrayList;
import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {

    private List<ApplicationInfo> mApps;
    private Callbacks mCallbacks;
    private Context mContext;
    private PackageManager mPackageManager;

    public AppsAdapter(@Nullable List<ApplicationInfo> apps, @NonNull Callbacks callbacks,
                       @NonNull Context context) {
        this.mApps = apps;
        this.mCallbacks = callbacks;
        this.mContext = context;
        this.mPackageManager = context.getPackageManager();
    }

    public interface Callbacks {
        void onAppClicked(ApplicationInfo app);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView appName;

        ViewHolder(View itemView) {
            super(itemView);
            appName = (TextView)itemView.findViewById(R.id.app_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_item, parent, false));
        holder.appName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                if(pos != RecyclerView.NO_POSITION) {
                    mCallbacks.onAppClicked(mApps.get(pos));
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ApplicationInfo app = mApps.get(position);
        holder.appName.setText(app.loadLabel(mPackageManager));
        holder.appName.setCompoundDrawablesWithIntrinsicBounds(app.loadIcon(mPackageManager), null,
                null, null);
    }

    @Override
    public int getItemCount() {
        return mApps == null ? 0 : mApps.size();
    }

    public void setApps(@Nullable List<ApplicationInfo> apps) {
        if(apps != null) {
            apps = new ArrayList<>(apps);
        }
        this.mApps = apps;
        Log.e("TAG", "SET APPS " + apps);
        notifyDataSetChanged();
    }
}
