package twitsplit.project.bee.twitsplit.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import twitsplit.project.bee.twitsplit.R;
import twitsplit.project.bee.twitsplit.data.pojo.Message;
import twitsplit.project.bee.twitsplit.databinding.ItemMessageBinding;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {
    private final Context context;
    private final List<Message> messageList;
    private int lastPosition;

    public Context getContext() {
        return context;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public MessageAdapter(Context context, List<Message> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        Message message = messageList.get(position);
        holder.binding.message.setText(message.getContent());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public void addMessage(Message message) {
        messageList.add(message);
        notifyItemInserted(messageList.size() - 1);
    }

    public void addMessage(String content) {
        messageList.add(Message.valueOf(content));
        notifyItemInserted(messageList.size() - 1);
    }

    public void addMessageList(List<String> messageList) {
        int currentPosition = getItemCount();
        for (String messageContent : messageList) {
            this.messageList.add(Message.valueOf(messageContent));
        }
        notifyItemRangeChanged(currentPosition, messageList.size());
    }

    public int getLastPosition() {
        return getItemCount() - 1;
    }

    static class MessageHolder extends ViewHolder {
        private ItemMessageBinding binding;

        MessageHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
