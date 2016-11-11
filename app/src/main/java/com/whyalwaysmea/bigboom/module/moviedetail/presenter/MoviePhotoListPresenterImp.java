package com.whyalwaysmea.bigboom.module.moviedetail.presenter;

import com.whyalwaysmea.bigboom.base.BasePresenter;
import com.whyalwaysmea.bigboom.base.OnLoadCompleteListener;
import com.whyalwaysmea.bigboom.bean.MoviePhoto;
import com.whyalwaysmea.bigboom.module.moviedetail.model.MoviePhotoListModelImp;
import com.whyalwaysmea.bigboom.module.moviedetail.view.IMoviePhotoListView;

/**
 * Created by Long
 * on 2016/11/11.
 */

public class MoviePhotoListPresenterImp extends BasePresenter<IMoviePhotoListView> implements IMoviePhotoListPresenter, OnLoadCompleteListener<MoviePhoto> {

    private MoviePhotoListModelImp mMoviePhotoListModelImp;

    public MoviePhotoListPresenterImp(IMoviePhotoListView iMoviePhotoListView) {
        super(iMoviePhotoListView);
        mMoviePhotoListModelImp = new MoviePhotoListModelImp();
    }

    @Override
    public void getMoviePhotoList(String id, int start) {
        mView.showLoading();
        mMoviePhotoListModelImp.getMoviePhotoList(id, start, this);
    }

    @Override
    public void onLoadSussess(MoviePhoto moviePhoto) {
        mView.hideLoading();
        mView.showMoviePhotoList(moviePhoto);
    }

    @Override
    public void onLoadFailed(String error) {
        mView.hideLoading();
        mView.showToast(error);
    }
}
