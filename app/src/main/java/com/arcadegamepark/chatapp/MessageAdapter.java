package com.arcadegamepark.chatapp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Message> messages;
    private Context context;
    private static final int TYPE_MY_MESSAGE = 0;
    private static final int TYPE_OTHER_MESSAGE = 1;


    public MessageAdapter(Context context) {
        this.messages = new ArrayList<>();
        this.context = context;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_MY_MESSAGE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_message_item, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.other_message_item, parent, false);
        }
        return new MessageViewHolder(view);
    }

    // Here We set which type of Layout we should use
    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        String author = message.getAuthor();
        if (author != null && author.equals(PreferenceManager.getDefaultSharedPreferences(context).getString("author", "Anonim"))) {
            return TYPE_MY_MESSAGE;
        } else {
            return TYPE_OTHER_MESSAGE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.textViewAuthor.setText(message.getAuthor());
        if (message.getTextOfMessage() != null && !message.getTextOfMessage().isEmpty()) {
            holder.textViewTextOfMessage.setText(message.getTextOfMessage());
            holder.imageViewImageOfMessage.setVisibility(View.GONE);
            holder.textViewTextOfMessage.setVisibility(View.VISIBLE);
        }
        if (message.getURLToImage() != null && !message.getURLToImage().isEmpty()) {
            holder.textViewTextOfMessage.setVisibility(View.INVISIBLE);
            holder.imageViewImageOfMessage.setVisibility(View.VISIBLE);
            Picasso.get().load(message.getURLToImage()).into(holder.imageViewImageOfMessage);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewAuthor;
        private TextView textViewTextOfMessage;
        private ImageView imageViewImageOfMessage;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewTextOfMessage = itemView.findViewById(R.id.textViewTextOfMessage);
            imageViewImageOfMessage = itemView.findViewById(R.id.imageViewImageOfMessage);
        }
    }
}
