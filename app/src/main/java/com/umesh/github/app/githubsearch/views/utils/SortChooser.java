package com.umesh.github.app.githubsearch.views.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.umesh.github.app.githubsearch.R;

public class SortChooser {

    public interface OnATOZSort {
        public void onATOZSelected();
    }

    public interface OnZTOASort {
        public void onZTOASelected();
    }

    public interface OnRankAsc {
        public void onAscSelected();
    }

    public interface OnRankDesc {
        public void onDescSelected();
    }

    private Context context;
    private OnATOZSort onATOZSort;
    private OnZTOASort onZTOASort;
    private OnRankAsc onRankAsc;
    private OnRankDesc onRankDesc;
    private BottomSheetDialog bottomSheetDialog;

    public SortChooser(Context context, OnATOZSort onATOZSort, OnZTOASort onZTOASort, OnRankAsc onRankAsc, OnRankDesc onRankDesc) {
        this.context = context;
        this.setOnATOZSort(onATOZSort);
        this.setOnZTOASort(onZTOASort);
        this.setOnRankAsc(onRankAsc);
        this.setOnRankDesc(onRankDesc);
    }

    public void show() {
        bottomSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetDialog);
        View v = LayoutInflater.from(context).inflate(R.layout.sort_chooser, null);
        TextView atoz = (TextView) v.findViewById(R.id.sort_a_to_z);
        TextView ztoa = (TextView) v.findViewById(R.id.sort_z_to_a);
        TextView asc = (TextView) v.findViewById(R.id.rank_ascending);
        TextView desc = (TextView) v.findViewById(R.id.rank_descending);
        atoz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                handleATOZSort();
            }
        });

        ztoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                handleZTOASort();
            }
        });

        asc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                handleRankAsc();
            }
        });

        desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                handleRankDesc();
            }
        });
        bottomSheetDialog.contentView(v).show();
    }

    public void handleATOZSort() {
        if (null != onATOZSort)
            onATOZSort.onATOZSelected();
    }

    public void handleZTOASort() {
        if (null != onZTOASort)
            onZTOASort.onZTOASelected();
    }

    public void handleRankAsc() {
        if (null != onRankAsc)
            onRankAsc.onAscSelected();
    }

    public void handleRankDesc() {
        if (null != onRankDesc)
            onRankDesc.onDescSelected();
    }
    /**
     * Interface Setter Methods
     */

    public void setOnATOZSort(OnATOZSort onATOZSort) {
        this.onATOZSort = onATOZSort;
    }

    public void setOnZTOASort(OnZTOASort onZTOASort) {
        this.onZTOASort = onZTOASort;
    }

    public void setOnRankAsc(OnRankAsc onRankAsc) {
        this.onRankAsc = onRankAsc;
    }

    public void setOnRankDesc(OnRankDesc onRankDesc) {
        this.onRankDesc = onRankDesc;
    }
}