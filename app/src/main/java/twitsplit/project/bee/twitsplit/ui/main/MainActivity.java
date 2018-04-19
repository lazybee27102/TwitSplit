package twitsplit.project.bee.twitsplit.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import twitsplit.project.bee.twitsplit.App;
import twitsplit.project.bee.twitsplit.di.di.component.ActivityComponent;
import twitsplit.project.bee.twitsplit.di.di.component.DaggerActivityComponent;
import twitsplit.project.bee.twitsplit.di.di.module.ActivityModule;

public class MainActivity extends AppCompatActivity {
    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildActivityComponent();
        presenter.onActivityCreated();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onActivityDestroyed();
    }

    private void buildActivityComponent() {
        ActivityComponent activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(App.getApplicationComponent(this))
                .activityModule(new ActivityModule(this))
                .build();
        activityComponent.inject(this);
    }

}
