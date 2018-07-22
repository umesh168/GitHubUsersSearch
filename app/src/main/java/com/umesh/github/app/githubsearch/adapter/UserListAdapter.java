package com.umesh.github.app.githubsearch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.umesh.github.app.githubsearch.R;
import com.umesh.github.app.githubsearch.events.NextPageEvent;
import com.umesh.github.app.githubsearch.models.User;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Umesh on 21/07/2018.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.OLViewHolder> {

    private Context context;
    private int rowLayoutID;
    private List<User> bookingList;
    private LayoutInflater layoutInflater;
    private String month = "";

    onBottomReachedListener onBottomReachedListener;

    public interface onBottomReachedListener{
        void onBottomReachedListener(int position);

    }
    public UserListAdapter(Context context, List<User> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public OLViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_row_layout, parent, false);
        view.setTag(R.layout.user_row_layout, bookingList.get(viewType));
        OLViewHolder olViewHolder = new OLViewHolder(view);
        return olViewHolder;
    }

    @Override
    public void onBindViewHolder(OLViewHolder holder1, final int position) {
        final OLViewHolder holder = holder1;

        final User booking = bookingList.get(position);

        holder.name.setText(booking.getLogin());
        holder.score.setText(booking.getScore() + "");

        Glide.with(context).load(booking.getAvatarUrl()).placeholder(R.drawable.git_default).into(holder.avatar);
         if (position == bookingList.size() - 1){
            onBottomReachedListener.onBottomReachedListener(position);
            EventBus.getDefault().postSticky(new NextPageEvent("Next", position,"Append"));
        }
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public void setOnBottomReachedListener(onBottomReachedListener onBottomReachedListener){
        this.onBottomReachedListener = onBottomReachedListener;
    }

    public class OLViewHolder  extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name) TextView name;
        @BindView(R.id.iv_avatar) ImageView avatar;
        @BindView(R.id.tv_score) TextView score;

        public OLViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
