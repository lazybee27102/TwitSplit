package twitsplit.project.bee.twitsplit.ui.main;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import twitsplit.project.bee.twitsplit.adapter.MessageAdapter;
import twitsplit.project.bee.twitsplit.data.pojo.Message;
import twitsplit.project.bee.twitsplit.di.di.scope.ActivityContext;
import twitsplit.project.bee.twitsplit.exception.TwitOutOfRangeException;
import twitsplit.project.bee.twitsplit.ui.main.tweet.MessageSplitter;

public class MainPresenterImpl implements MainPresenter {
    @Inject
    @ActivityContext
    Context context;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MainManager mainManager;
    private final MainView mainView;

    private MessageAdapter adapter;
    private final List<Message> messageList = new ArrayList<>();

    public MessageAdapter getAdapter() {
        if (adapter == null) {
            adapter = new MessageAdapter(context, messageList);
        }

        return adapter;
    }

    @Inject
    public MainPresenterImpl(MainManager mainManager, MainView mainView) {
        this.mainManager = mainManager;
        this.mainView = mainView;
    }

    @Override
    public void onActivityCreated() {
        mainView.bindView();
        mainView.setupView();
        setupAdapter();
        setupEvent();
    }

    @Override
    public void onActivityDestroyed() {
        compositeDisposable.dispose();
    }

    private void setupAdapter() {
        MessageAdapter adapter = getAdapter();
        mainView.setAdapter(adapter);
    }

    private void setupEvent() {
        editTextChanges();
        buttonSendClicks();
    }

    private void buttonSendClicks() {
        compositeDisposable.add(mainView.buttonSendClicks()
                .subscribe(o -> splitAndSendMessage(), Throwable::printStackTrace));
    }

    private void editTextChanges() {
        compositeDisposable.add(mainView.editTextChanges()
                .map(charSequence -> charSequence.toString().length())
                .subscribe(this::editTextLengthChanges, Throwable::printStackTrace));
    }

    private void splitAndSendMessage() {
        String content = mainView.getInputMessage();
        compositeDisposable.add(Observable.just(content)
                .map(Message::valueOf)
                .map(MessageSplitter::splitMessage)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::sendMessageList, this::splitMessageError));
    }

    private void sendMessageList(List<String> messageList) {
        adapter.addMessageList(messageList);
        mainView.smoothScrollToPosition(adapter.getLastPosition());
        mainView.resetEditText();
    }

    private void splitMessageError(Throwable throwable) {
        if (throwable instanceof TwitOutOfRangeException) {
            Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            mainView.resetEditText();
        }
    }

    private void editTextLengthChanges(int length) {
        if (length > 0) {
            mainView.enableButtonSend();
        } else {
            mainView.disableButtonSend();
        }
    }
}
