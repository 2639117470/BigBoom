package com.whyalwaysmea.bigboom.module.movielist.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.whyalwaysmea.bigboom.MainActivity;
import com.whyalwaysmea.bigboom.R;
import com.whyalwaysmea.bigboom.base.BaseAdapter;
import com.whyalwaysmea.bigboom.base.BaseViewHolder;
import com.whyalwaysmea.bigboom.bean.MovieInfo;
import com.whyalwaysmea.bigboom.imageloader.ImageUtils;
import com.whyalwaysmea.bigboom.module.moviedetail.ui.MovieDetailActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Long
 * on 2016/9/6.
 */
public class Top250MovieAdapter extends BaseAdapter<MovieInfo> {


    public Top250MovieAdapter(Context context, List<MovieInfo> data, boolean useAnimation) {
        super(context, data, useAnimation);
    }

    @Override
    protected BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.top_movie_item, parent, false);
        return new HotMovieHolder(view);
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position) {
        if (holder instanceof HotMovieHolder) {
            HotMovieHolder hotMovieHolder = (HotMovieHolder) holder;
            MovieInfo movieInfo = mData.get(position);

            hotMovieHolder.mTopMovieItemName.setText(movieInfo.getTitle());
            hotMovieHolder.mTopMovieItemOriginalName.setText(movieInfo.getOriginal_title());
            hotMovieHolder.mTopMovieItemDate.setText(mContext.getResources().getString(R.string.start_data) + movieInfo.getYear());
            hotMovieHolder.mRatingBarHots.setRating((float) movieInfo.getRating().getAverage() / 2);
            hotMovieHolder.mTopMovieItemScore.setText("" + movieInfo.getRating().getAverage());
            ImageUtils.getInstance().display(hotMovieHolder.mTopMovieItemIcon, movieInfo.getImages().getLarge());

            ((HotMovieHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, MovieDetailActivity.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((MainActivity)mContext, hotMovieHolder.mTopMovieItemIcon, "detail_img");
                    mContext.startActivity(intent, options.toBundle());
                }
            });

        }
    }


    class HotMovieHolder extends BaseViewHolder {

        @BindView(R.id.top_movie_item_icon)
        ImageView mTopMovieItemIcon;
        @BindView(R.id.top_movie_item_name)
        TextView mTopMovieItemName;
        @BindView(R.id.top_movie_item_date)
        TextView mTopMovieItemDate;
        @BindView(R.id.top_movie_item_original_name)
        TextView mTopMovieItemOriginalName;
        @BindView(R.id.ratingBar_hots)
        AppCompatRatingBar mRatingBarHots;
        @BindView(R.id.top_movie_item_score)
        TextView mTopMovieItemScore;

        public HotMovieHolder(View itemView) {
            super(itemView);
        }
    }


}
