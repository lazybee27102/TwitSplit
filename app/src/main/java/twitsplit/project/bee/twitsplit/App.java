package twitsplit.project.bee.twitsplit;

import android.app.Application;
import android.content.Context;

import twitsplit.project.bee.twitsplit.di.component.ApplicationComponent;
import twitsplit.project.bee.twitsplit.di.component.DaggerApplicationComponent;
import twitsplit.project.bee.twitsplit.di.module.ApplicationModule;

public class App extends Application {
    private ApplicationComponent appComponent;

    public static ApplicationComponent getApplicationComponent(Context context) {
        return ((App) context
                .getApplicationContext())
                .getAppComponent();
    }

    public ApplicationComponent getAppComponent() {
        return appComponent;
    }

    public void setAppComponent(ApplicationComponent appComponent) {
        this.appComponent = appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildAppComponent();
    }

    private void buildAppComponent() {
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        appComponent.inject(this);
    }
}
