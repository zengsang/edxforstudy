package com.wuqian.myedx.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.wuqian.myedx.R;

/**
 * Created by wuqian on 2016/5/10.
 */
public abstract class BaseFragment extends Fragment {
    public static final int STATE_NO_DATA=0;//没有数据
    public static final int STATE_GET_DATA_SUCCESS=1;//获取数据成功或者是有数据
    public static final int STATE_LOADING=2;//没有数据
    public static final int STATE_LOADING_ERROR=3;//加载错误
    private int cueerntState=STATE_LOADING;

    private View emptyView;//没有数据是显示的界面
    private View successView;//有数据使得界面
    private View loadingView;//显示加载进度条
    private View errorView;//加载错误
    private FrameLayout fl_container;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if(fl_container==null){
           fl_container=new FrameLayout(getActivity());
           initView();
       }
        refreshView();
        showPage();
        return fl_container;
    }

    /**
     * 向FrameLayout中添加几种状态不同的view
     */
    private void initView(){
        loadingView=View.inflate(getActivity(), R.layout.loading_view,null);
        emptyView=createEmptyView();

        errorView=View.inflate(getActivity(),R.layout.error_view,null);
        if(emptyView!=null){
            fl_container.addView(emptyView,FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        }
        fl_container.addView(loadingView,FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        fl_container.addView(errorView,FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        Button btn_try_load= (Button) errorView.findViewById(R.id.btn_try_load);
        btn_try_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPage();
            }
        });
    }

    /**
     * 根据状态来显示相应的视图
     */
    private void refreshView(){
        if(loadingView!=null){
            loadingView.setVisibility(cueerntState==STATE_LOADING?View.VISIBLE: View.GONE);
        }
        if(emptyView!=null){
            emptyView.setVisibility(cueerntState==STATE_NO_DATA?View.VISIBLE:View.GONE);
        }

        if(successView!=null){
            successView.setVisibility(cueerntState==STATE_GET_DATA_SUCCESS?View.VISIBLE:View.GONE);
        }
        if(errorView!=null){
            errorView.setVisibility(cueerntState==STATE_LOADING_ERROR?View.VISIBLE:View.GONE);
        }

        if(cueerntState==STATE_GET_DATA_SUCCESS){
            if(successView==null){
                successView=createSuccessView();
                fl_container.addView(successView,FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
            }
            successView.setVisibility(View.VISIBLE);
        }else{
            if(successView!=null)
                successView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 向服务器发送请求，请求完成后刷新界面
     */
    private void showPage(){
       if(cueerntState==STATE_LOADING_ERROR){
           cueerntState=STATE_LOADING;
       }
        refreshView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                cueerntState=initDatas();
                SystemClock.sleep(1000);
                if(getActivity()!=null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshView();
                        }
                    });
                }
            }
        }).start();
    }

    /**
     * 获取空界面
     * @return
     */
    protected abstract View createEmptyView();

    /**
     * 获取成功界面
     * @return
     */
    protected abstract View createSuccessView();

    /**
     * 向服务器发送数据请求
     * @return
     * 当数据为空时 ，返回STATE_NO_DATA
     * 当存在数据是，返回STATE_GET_DATA_SUCCESS
     */
    protected abstract int initDatas();
}
