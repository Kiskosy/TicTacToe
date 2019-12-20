package university.tictactoe.listeners;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import university.tictactoe.R;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    private static final int TYPE_HEADER = 1;
    private static final int TYPE_ITEM = 2;


    public class HeaderViewHolder extends ViewHolder {
        TextView textView;

        public HeaderViewHolder(View itemView){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.recycler_view_header_text_view);
        }

    }

    public class ItemViewHolder extends  ViewHolder {
        TextView textView;

        public ItemViewHolder(View itemView){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.recycler_view_row_text_view);
        }
    }

    // data is passed into the constructor
    public RecycleAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = mInflater.inflate(R.layout.recycleview_row, parent, false);
        //return new ViewHolder(view);

        View view;

        if(viewType == TYPE_HEADER){
            view = mInflater.inflate(R.layout.recycleview_header, parent, false);
            HeaderViewHolder headerViewHolder = new HeaderViewHolder(view);
            return headerViewHolder;
        }

        view = mInflater.inflate(R.layout.recycleview_row, parent, false);
        ItemViewHolder normalViewHolder = new ItemViewHolder(view);
        return normalViewHolder;
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //String animal = mData.get(position);
        //holder.myTextView.setText(animal);

        if(holder instanceof HeaderViewHolder){
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            String username = mData.get(position);
            headerViewHolder.textView.setText(username);
        } else if(holder instanceof ItemViewHolder){
            final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            final String username = mData.get(position-1);
            itemViewHolder.textView.setText(username);
        }

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }

        return TYPE_ITEM;
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.recycler_view_row_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition()-1);
        }

    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
