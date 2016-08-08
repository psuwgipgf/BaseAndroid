// Generated code from Butter Knife. Do not modify!
package com.psuwgipgf.myapplication.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Main2Activity$$ViewBinder<T extends com.psuwgipgf.myapplication.ui.activity.Main2Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099653, "method 'OnCliek'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.OnCliek(p0);
        }
      });
  }

  @Override public void unbind(T target) {
  }
}
