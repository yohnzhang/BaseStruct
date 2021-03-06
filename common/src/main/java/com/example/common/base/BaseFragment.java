package com.example.common.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.common.di.AppComponent;
import com.example.common.di.GlobalAppComponent;
import com.trello.rxlifecycle.components.support.RxFragment;
import javax.inject.Inject;

/**
 * Created by Bill on 2017/8/1.
 */

public abstract class BaseFragment<P extends IPresenter> extends RxFragment {

  @Nullable
  protected Unbinder unbinder;

  @Inject
  protected P mPresenter;

  protected AppComponent getAppComponent() {
    return GlobalAppComponent.getAppComponent();
  }

  @Override
  public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
    super.onViewStateRestored(savedInstanceState);
    View view = getView();
    if (view != null) {
      unbinder = ButterKnife.bind(this, view);
    }
    mPresenter.onStart(savedInstanceState);
  }


  @Override
  public void onResume() {
    super.onResume();
    mPresenter.onResume();
  }

  @Override
  public void onPause() {
    super.onPause();
    mPresenter.onPause();
  }

  @CallSuper
  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mPresenter.onSaveInstanceState(outState);
  }

  @Override
  public void onDestroyView() {
    if(unbinder != null){
      unbinder.unbind();
    }
    super.onDestroyView();
  }
}
