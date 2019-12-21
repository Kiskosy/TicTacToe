package university.tictactoe.listeners;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import university.tictactoe.R;
import university.tictactoe.utils.MyDiffCallback;

public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RecyclerView.OnItemTouchListener {

    private static ClickListener clickListener;
    GestureDetector mGestureDetector;

    private static final int TYPE_HEADER = 1;
    private static final int TYPE_ITEM = 2;

    List<String> list;

    public RecycleAdapter(Context context, final RecyclerView recyclerView, ClickListener listener) {
        clickListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());

                if (child != null && clickListener != null) {
                    clickListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }

    @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && clickListener != null && mGestureDetector.onTouchEvent(e)) {
            clickListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

    @Override
    public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}

    public RecycleAdapter(List<String> list) {
        this.list = list;
    }

    // inflates the row layout from xml when needed
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = mInflater.inflate(R.layout.recycleview_row, parent, false);
        //return new ViewHolder(view);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if(viewType == TYPE_HEADER){
            View view = inflater.inflate(R.layout.recycleview_header, parent, false);
            HeaderViewHolder headerViewHolder = new HeaderViewHolder(view);
            return headerViewHolder;
        } else if (viewType == TYPE_ITEM){
            View view = inflater.inflate(R.layout.recycleview_row, parent, false);
            ItemViewHolder normalViewHolder = new ItemViewHolder(view);
            return normalViewHolder;
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }


    // binds the data to the TextView in each row
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //String animal = mData.get(position);
        //holder.myTextView.setText(animal);

        if(holder instanceof HeaderViewHolder){
            String header = list.get(position);
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.textView.setText(header);
        } else if(holder instanceof ItemViewHolder){
            String username = list.get(position);
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.textView.setText(username);
        }

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return list.get(position).equals("Pick one user");
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public HeaderViewHolder(View itemView){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.recycler_view_header_text_view);
        }

    }

    public class ItemViewHolder extends  RecyclerView.ViewHolder {
        TextView textView;

        public ItemViewHolder(View itemView){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.recycler_view_row_text_view);
        }
    }

    public interface ClickListener {
        public void onItemClick(View v, int position);
        public void onLongItemClick(View view, int position);
    }

    public void updateList(List<String> newList){
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(this.list,newList));
        diffResult.dispatchUpdatesTo(this);
    }


}
