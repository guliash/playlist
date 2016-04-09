package com.github.guliash.playlist.adapters;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.guliash.playlist.R;
import com.github.guliash.playlist.structures.Singer;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by gulash on 08.04.16.
 */
public class SingersAdapter extends RecyclerView.Adapter<SingersAdapter.SingerViewHolder> {

    public static class SingerViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mDesc;
        private ImageView mCover;


        public SingerViewHolder(View itemView) {
            super(itemView);
            mName = (TextView)itemView.findViewById(R.id.name);
            mDesc = (TextView)itemView.findViewById(R.id.desc);
            mCover = (ImageView)itemView.findViewById(R.id.cover);
        }
    }

    public static class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int mVerticalSpace;

        public VerticalSpaceItemDecoration(int verticalSpace) {
            mVerticalSpace = verticalSpace;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if(parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
                outRect.bottom = mVerticalSpace;
            }
        }
    }

    private Context mContext;
    private List<Singer> mSingers;

    public SingersAdapter(List<Singer> singerList, Context context) {
        mSingers = singerList;
        mContext = context;
    }

    @Override
    public SingerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        SingerViewHolder holder = new SingerViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.singer_card, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(SingerViewHolder singerViewHolder, int position) {
        Singer singer = mSingers.get(position);
        singerViewHolder.mName.setText(singer.name);
        singerViewHolder.mDesc.setText(singer.description);
        Picasso.with(mContext).load(singer.cover.small).fit().into(singerViewHolder.mCover);
    }

    @Override
    public int getItemCount() {
        return mSingers.size();
    }

    public void setSingers(List<Singer> singers) {
        mSingers.clear();
        mSingers.addAll(singers);
        notifyDataSetChanged();
    }



}
