package twitsplit.project.bee.twitsplit.di.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import twitsplit.project.bee.twitsplit.di.di.scope.ApplicationContext;

@Module
public class ApplicationModule {
    private final Application context;

    public ApplicationModule(Application context) {
        this.context = context;
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context provideApplicationContext() {
        return context;
    }
}
