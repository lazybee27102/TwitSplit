package twitsplit.project.bee.twitsplit.ui.main;

import twitsplit.project.bee.twitsplit.di.di.scope.PerActivity;

@PerActivity
public interface MainPresenter {

    void onActivityCreated();

    void onActivityDestroyed();
}
