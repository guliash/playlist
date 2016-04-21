package com.github.guliash.playlist.ui.adapters;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.guliash.playlist.R;
import com.github.guliash.playlist.structures.Singer;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SingersAdapter extends RecyclerView.Adapter<SingersAdapter.SingerViewHolder> {

    public static class SingerViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mGenres;
        private TextView mDesc;
        private ImageView mCover;
        private CardView mCardView;

        public SingerViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView)itemView.findViewById(R.id.card);
            mName = (TextView)itemView.findViewById(R.id.name);
            mGenres = (TextView)itemView.findViewById(R.id.genres);
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

    public interface Callbacks {
        void onSingerClicked(Singer singer);
    }

    private Context mContext;
    private List<Singer> mSingers;
    private Callbacks mListener;

    public SingersAdapter(List<Singer> singerList, Context context, Callbacks listener) {
        mSingers = singerList;
        mContext = context;
        mListener = listener;
    }

    @Override
    public SingerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final SingerViewHolder holder = new SingerViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.singer_card, viewGroup, false));

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {
                    mListener.onSingerClicked(mSingers.get(position));
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(final SingerViewHolder singerViewHolder, int position) {
        Singer singer = mSingers.get(position);
        singerViewHolder.mName.setText(singer.name);
        singerViewHolder.mGenres.setText(TextUtils.join(", ", singer.genres));
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
