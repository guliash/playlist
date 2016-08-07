package com.github.guliash.playlist.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.github.guliash.playlist.utils.Checker;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter of singers
 */
public class SingersAdapter extends RecyclerView.Adapter<SingersAdapter.SingerViewHolder> {

    static class SingerViewHolder extends RecyclerView.ViewHolder {

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

    public interface Callbacks {
        void onSingerClicked(Singer singer);
    }

    @NonNull private Context mContext;
    @NonNull private Callbacks mListener;
    @Nullable private List<Singer> mSingers;


    public SingersAdapter(@Nullable List<Singer> singerList, @NonNull Context context, @NonNull Callbacks listener) {
        Checker.notNull(context);
        Checker.notNull(listener);

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
        Picasso.with(mContext).load(singer.cover.small).error(R.drawable.ic_error_outline_black_48dp)
                .fit().centerCrop()
                .into(singerViewHolder.mCover);
    }

    @Override
    public int getItemCount() {
        return mSingers == null ? 0 : mSingers.size();
    }

    public void setSingers(@Nullable List<Singer> singers) {
        if(singers == null) {
            mSingers = null;
        } else {
            if(mSingers == null) {
                mSingers = new ArrayList<>(singers.size());
                mSingers.addAll(singers);
            } else {
                mSingers.clear();
                mSingers.addAll(singers);
            }
        }
        notifyDataSetChanged();
    }
}
