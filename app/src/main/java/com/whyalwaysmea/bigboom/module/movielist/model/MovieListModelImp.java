package com.whyalwaysmea.bigboom.module.movielist.model;

import com.whyalwaysmea.bigboom.Constants;
import com.whyalwaysmea.bigboom.base.OnLoadCompleteListener;
import com.whyalwaysmea.bigboom.bean.MovieListResponse;
import com.whyalwaysmea.bigboom.bean.WeeklyMovieInfo;
import com.whyalwaysmea.bigboom.http.ApiManager;
import com.whyalwaysmea.bigboom.http.HttpMethods;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Long
 * on 2016/9/5.
 */
public class MovieListModelImp implements IMovieListModel {

    private Subscription mSubscribe;

    @Override
    public void loadTop250(int start, int count, OnLoadCompleteListener<MovieListResponse> listener) {
        ApiManager apiManager = HttpMethods.createService(Constants.URL.MOVIE, ApiManager.class);
        mSubscribe = apiManager.getTop250Movie(start, count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (null != listener) {
                            listener.onLoadFailed(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(MovieListResponse response) {
                        if (null != listener && response.getCount() > 0) {
                            listener.onLoadSussess(response);
                        } else {
                            listener.onLoadFailed("无更多数据 ヾﾉ≧∀≦)o");
                        }
                    }
                });
    }

    @Override
    public void loadInTheaters(String city, OnLoadCompleteListener<MovieListResponse> listener) {
        ApiManager apiManager = HttpMethods.createService(Constants.URL.MOVIE, ApiManager.class);
        mSubscribe = apiManager.getInTheatersMovie(city)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (null != listener) {
                            listener.onLoadFailed(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(MovieListResponse movieListResponse) {
                        if (null != listener && movieListResponse.getCount() > 0) {
                            listener.onLoadSussess(movieListResponse);
                        } else {
                            listener.onLoadFailed("无更多数据 ヾﾉ≧∀≦)o");
                        }
                    }
                });
    }

    @Override
    public void loadComingSoon(int start, int count, OnLoadCompleteListener<MovieListResponse> listener) {
        ApiManager apiManager = HttpMethods.createService(Constants.URL.MOVIE, ApiManager.class);
        mSubscribe = apiManager.getComingSoonMovie(start, count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (null != listener) {
                            listener.onLoadFailed(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(MovieListResponse movieListResponse) {
                        if (null != listener && movieListResponse.getCount() > 0) {
                            listener.onLoadSussess(movieListResponse);
                        } else {
                            listener.onLoadFailed("无更多数据 ヾﾉ≧∀≦)o");
                        }
                    }
                });
    }

    @Override
    public void loadWeeklyMovies(OnLoadCompleteListener<WeeklyMovieInfo> listener) {
        ApiManager apiManager = HttpMethods.createService(Constants.URL.MOVIE, ApiManager.class);
        mSubscribe = apiManager.getWeeklyMovie(Constants.ID.APIKEY)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeeklyMovieInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WeeklyMovieInfo weeklyMovieInfo) {
                        if (null != listener && weeklyMovieInfo.getSubjects().size() > 0) {
                            listener.onLoadSussess(weeklyMovieInfo);
                        } else {
                            listener.onLoadFailed("无更多数据 ヾﾉ≧∀≦)o");
                        }
                    }
                });
    }


    @Override
    public void loadNewMovies(OnLoadCompleteListener<MovieListResponse> listener) {
        ApiManager apiManager = HttpMethods.createService(Constants.URL.MOVIE, ApiManager.class);
        mSubscribe = apiManager.getNewMovies(Constants.ID.APIKEY)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (null != listener) {
                            listener.onLoadFailed(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(MovieListResponse movieListResponse) {
                        if (null != listener && movieListResponse.getSubjects().size() > 0) {
                            listener.onLoadSussess(movieListResponse);
                        } else {
                            listener.onLoadFailed("无更多数据 ヾﾉ≧∀≦)o");
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        if(null != mSubscribe && mSubscribe.isUnsubscribed()) {
            mSubscribe.unsubscribe();
        }
    }

}
