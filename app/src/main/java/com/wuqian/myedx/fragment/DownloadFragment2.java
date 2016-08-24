package com.wuqian.myedx.fragment;

import android.view.View;
import android.widget.TextView;

import com.wuqian.myedx.R;

/**
 * Created by wuqian on 2016/5/10.
 * 正在下载界面
 */
public class DownloadFragment2 extends BaseFragment{
    @Override
    protected View createEmptyView() {
        View view=View.inflate(getActivity(), R.layout.empty_view,null);
        TextView tv_empty= (TextView) view.findViewById(R.id.tv_empty);
        tv_empty.setText("你没有正在下载的课程");
        return view;
    }

    @Override
    protected View createSuccessView() {
        TextView tv=new TextView(getActivity());
        tv.setText("加载成功");
        return tv;
    }

    @Override
    protected int initDatas() {
        return BaseFragment.STATE_NO_DATA;
    }
}
