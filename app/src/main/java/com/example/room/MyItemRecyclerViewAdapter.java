package com.example.room;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.room.Room.Alumn;
import com.example.room.Room.Alumnos;
import com.example.room.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Alumn> mValues;
    private Context context;

    public MyItemRecyclerViewAdapter(Context ctx, List<Alumn> items) {
        context = ctx;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.edUid.setText("" + mValues.get(position).getUid());
        holder.tvName.setText(mValues.get(position).getFirstName());
        holder.tvLast.setText(mValues.get(position).getLastName());
        holder.tvAge.setText("" + mValues.get(position).getAge());
        holder.tvTel.setText(mValues.get(position).getTel());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView edUid;
        public final TextView tvName;
        public final TextView tvLast;
        public final TextView tvAge;
        public final TextView tvTel;
        public Alumn mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            edUid = (TextView) view.findViewById(R.id.edUid);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvLast = (TextView) view.findViewById(R.id.tvLast);
            tvAge = (TextView) view.findViewById(R.id.tvAge);
            tvTel = (TextView) view.findViewById(R.id.tvTel);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + edUid.getText() + "'";
        }
    }
}