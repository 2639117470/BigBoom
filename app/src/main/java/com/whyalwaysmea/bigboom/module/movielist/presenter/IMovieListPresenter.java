package com.whyalwaysmea.bigboom.module.movielist.presenter;

import android.support.annotation.Nullable;

/**
 * Created by Long
 * on 2016/9/5.
 */
public interface IMovieListPresenter {
    // Top250
    void loadTop250(int start, int count);

    // 正在热映
    void loadInTheaters(@Nullable String city);

    // 即将上映
    void loadComingSoon(int start, int count);
}
