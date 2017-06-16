package com.dicksonmully6gmail.doctorapp.util;

/**
 * Created by dickson on 6/16/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
