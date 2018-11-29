package com.certis_base_app.ui.custom;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.support.v7.widget.helper.ItemTouchHelper.SimpleCallback;
import android.view.View;

public class RecyclerItemTouchHelper extends SimpleCallback {
    private RecyclerItemTouchHelperListener listener;

    public interface RecyclerItemTouchHelperListener {
        void onSwiped(ViewHolder viewHolder, int i, int i2);
    }

    public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder viewHolder2) {
        return true;
    }

    public RecyclerItemTouchHelper(int i, int i2, RecyclerItemTouchHelperListener recyclerItemTouchHelperListener) {
        super(i, i2);
        this.listener = recyclerItemTouchHelperListener;
    }

    public void onSelectedChanged(ViewHolder viewHolder, int i) {
        if (viewHolder != null) {

            Callback.getDefaultUIUtil().onSelected(((SwipeViewHolder) viewHolder).viewForeground);
        }
    }

    public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        Callback.getDefaultUIUtil().onDrawOver(canvas, recyclerView, ((SwipeViewHolder) viewHolder).viewForeground, f, f2, i, z);
    }

    public void clearView(RecyclerView recyclerView, ViewHolder viewHolder) {
        Callback.getDefaultUIUtil().clearView(((SwipeViewHolder) viewHolder).viewForeground);
    }

    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        SwipeViewHolder subtaskListItemHolder = (SwipeViewHolder) viewHolder;
        View view = subtaskListItemHolder.viewForeground;
        View view2 = subtaskListItemHolder.viewBackground;
        Callback.getDefaultUIUtil().onDraw(canvas, recyclerView, view, f / 4.0f, f2, i, z);
    }

    public void onSwiped(ViewHolder viewHolder, int i) {
        this.listener.onSwiped(viewHolder, i, viewHolder.getAdapterPosition());
    }

    public int convertToAbsoluteDirection(int i, int i2) {
        return super.convertToAbsoluteDirection(i, i2);
    }


}
