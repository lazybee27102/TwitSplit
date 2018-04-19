package twitsplit.project.bee.twitsplit.ui.main;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import javax.inject.Inject;

import io.reactivex.Observable;
import twitsplit.project.bee.twitsplit.R;
import twitsplit.project.bee.twitsplit.adapter.MessageAdapter;
import twitsplit.project.bee.twitsplit.databinding.ActivityMainBinding;

public class MainViewImpl implements MainView {
    private ActivityMainBinding binding;

    @Inject
    Activity activity;

    @Inject
    public MainViewImpl() {
    }

    @Override
    public void bindView() {
        binding = DataBindingUtil.setContentView(activity, R.layout.activity_main);
    }

    @Override
    public void setupView() {
        setupRecyclerView();
    }

    @Override
    public void setAdapter(MessageAdapter adapter) {
        binding.rvMessage.setAdapter(adapter);
    }

    @Override
    public Observable<CharSequence> editTextChanges() {
        return RxTextView.textChanges(binding.etMessage);
    }

    @Override
    public Observable<Object> buttonSendClicks() {
        return RxView.clicks(binding.ivSend);
    }

    @Override
    public void smoothScrollToPosition(int position) {
        binding.rvMessage.smoothScrollToPosition(position);
    }

    @Override
    public void enableButtonSend() {
        binding.ivSend.setEnabled(true);
        binding.ivSend.setImageResource(R.drawable.ic_send_blue_24dp);
    }

    @Override
    public void disableButtonSend() {
        binding.ivSend.setEnabled(false);
        binding.ivSend.setImageResource(R.drawable.ic_send_black_24dp);
    }

    @Override
    public void resetEditText() {
        binding.etMessage.setText("");
    }

    @Override
    public String getInputMessage() {
        return binding.etMessage.getText().toString();
    }

    private void setupRecyclerView() {
        binding.rvMessage.setLayoutManager(new LinearLayoutManager(activity));

    }
}
