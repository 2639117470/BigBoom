package com.whyalwaysmea.bigboom.module.cast.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.whyalwaysmea.bigboom.Constants;
import com.whyalwaysmea.bigboom.R;
import com.whyalwaysmea.bigboom.base.BaseView;
import com.whyalwaysmea.bigboom.base.MvpActivity;
import com.whyalwaysmea.bigboom.bean.CastWork;
import com.whyalwaysmea.bigboom.module.cast.presenter.CastPresenterImp;
import com.whyalwaysmea.bigboom.module.cast.ui.adapter.AllWorksAdapter;
import com.whyalwaysmea.bigboom.module.cast.view.ICastDetailView;
import com.whyalwaysmea.bigboom.view.MyRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllWorksActivity extends MvpActivity<ICastDetailView, CastPresenterImp> implements ICastDetailView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.all_works_recyclerview)
    MyRecyclerView mAllWorksRecyclerview;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwiperefreshlayout;
    private String mCastId;

    private LinearLayoutManager mLinearLayoutManager;
    private AllWorksAdapter mAllWorksAdapter;
    private int start;

    @Override
    protected CastPresenterImp createPresenter(BaseView view) {
        return new CastPresenterImp(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_works);
        ButterKnife.bind(this);

        init();
    }

    @Override
    protected void initView() {
        mCastId = getIntent().getStringExtra(Constants.KEY.CASTID);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mAllWorksRecyclerview.setLayoutManager(mLinearLayoutManager);

        mAllWorksAdapter = new AllWorksAdapter(this, new ArrayList<>(), true);
        mAllWorksRecyclerview.setAdapter(mAllWorksAdapter);

        mToolbar.setTitle(R.string.all_work);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.icon_back);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void initData() {
        mPresenter.getCastWorks(mCastId, start);
    }

    @Override
    public void showLoading() {
        super.showLoading();
        mSwiperefreshlayout.post(() -> mSwiperefreshlayout.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        mSwiperefreshlayout.post(() -> mSwiperefreshlayout.setRefreshing(false));
    }

    @Override
    public void showDetail(Object o) {
        if(o instanceof CastWork) {
            CastWork castWork = (CastWork) o;
            mAllWorksAdapter.addData(castWork.getWorks());
            mSwiperefreshlayout.setEnabled(false);
        }
    }
}
