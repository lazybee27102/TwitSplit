package twitsplit.project.bee.twitsplit.ui.main;

import io.reactivex.Observable;
import twitsplit.project.bee.twitsplit.adapter.MessageAdapter;
import twitsplit.project.bee.twitsplit.di.scope.PerActivity;

@PerActivity
public interface MainView {
    void bindView();

    void setupView();

    void setAdapter(MessageAdapter adapter);

    void enableButtonSend();

    void disableButtonSend();

    void resetEditText();

    void smoothScrollToPosition(int position);

    String getInputMessage();

    Observable<CharSequence> editTextChanges();

    Observable<Object> buttonSendClicks();
}
