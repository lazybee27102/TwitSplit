package twitsplit.project.bee.twitsplit.di.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import twitsplit.project.bee.twitsplit.App;
import twitsplit.project.bee.twitsplit.di.di.module.ApplicationModule;
import twitsplit.project.bee.twitsplit.di.di.scope.ApplicationContext;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(App app);

    @ApplicationContext
    Context getApplicationContext();
}
