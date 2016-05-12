// Generated code from Butter Knife. Do not modify!
package com.psuwgipgf.myapplication.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.psuwgipgf.myapplication.ui.activity.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099649, "field 'image'");
    target.image = finder.castView(view, 2131099649, "field 'image'");
    view = finder.findRequiredView(source, 2131099648, "field 'text'");
    target.text = finder.castView(view, 2131099648, "field 'text'");
    view = finder.findRequiredView(source, 2131099650, "field 'circleImageView'");
    target.circleImageView = finder.castView(view, 2131099650, "field 'circleImageView'");
    view = finder.findRequiredView(source, 2131099651, "method 'onClick1'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick1(p0);
        }
      });
    view = finder.findRequiredView(source, 2131099652, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.image = null;
    target.text = null;
    target.circleImageView = null;
  }
}
