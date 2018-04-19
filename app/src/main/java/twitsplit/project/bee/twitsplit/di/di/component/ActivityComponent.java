package twitsplit.project.bee.twitsplit.di.di.component;

import dagger.Component;
import twitsplit.project.bee.twitsplit.di.di.module.ActivityModule;
import twitsplit.project.bee.twitsplit.di.di.scope.PerActivity;
import twitsplit.project.bee.twitsplit.ui.main.MainActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
