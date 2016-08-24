package com.wuqian.myedx.fragment;

import android.view.View;
import android.widget.TextView;

import com.wuqian.myedx.R;

/**
 * Created by wuqian on 2016/5/10.
 * 我的课程页面
 */
public class MyCourseFragment extends BaseFragment{

    @Override
    protected View createEmptyView() {
        View view=View.inflate(getActivity(), R.layout.empty_view,null);
        TextView tv_empty= (TextView) view.findViewById(R.id.tv_empty);
        tv_empty.setText("你没有参加任何课程");
        return view;
    }

    @Override
    protected View createSuccessView() {
        return null;
    }

    @Override
    protected int initDatas() {
        return BaseFragment.STATE_NO_DATA;
    }
}
