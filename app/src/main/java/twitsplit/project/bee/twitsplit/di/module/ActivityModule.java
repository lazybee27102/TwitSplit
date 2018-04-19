package twitsplit.project.bee.twitsplit.di.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import twitsplit.project.bee.twitsplit.di.scope.ActivityContext;
import twitsplit.project.bee.twitsplit.di.scope.PerActivity;
import twitsplit.project.bee.twitsplit.ui.main.MainManager;
import twitsplit.project.bee.twitsplit.ui.main.MainManagerImpl;
import twitsplit.project.bee.twitsplit.ui.main.MainPresenter;
import twitsplit.project.bee.twitsplit.ui.main.MainPresenterImpl;
import twitsplit.project.bee.twitsplit.ui.main.MainView;
import twitsplit.project.bee.twitsplit.ui.main.MainViewImpl;

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    MainPresenter provideMainPresenter(MainPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainView provideMainView(MainViewImpl view) {
        return view;
    }

    @Provides
    @PerActivity
    MainManager provideMainManager(MainManagerImpl manager) {
        return manager;
    }
}
