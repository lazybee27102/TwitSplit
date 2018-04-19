package twitsplit.project.bee.twitsplit.di.di.module;

import android.app.Service;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {
    private Service mService;

    public ServiceModule(Service service) {
        this.mService = service;
    }

    @Provides
    Service provideService() {
        return mService;
    }
}
