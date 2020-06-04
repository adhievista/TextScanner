package com.vista.textscanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vista.textscanner.databinding.ActivityOcrlistBinding;
import com.vista.textscanner.model.Database_Helper;
import com.vista.textscanner.model.Item;
import com.vista.textscanner.model.Recycler_ClickListener;
import com.vista.textscanner.model.list_adapter;
import com.vista.textscanner.ocr.Custom_Dialog;
import com.vista.textscanner.ocr.ocrActivity_Handler;
import com.vista.textscanner.presenter.OcrPresenter;
import com.vista.textscanner.view.ocrView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Halaman_OCRList extends AppCompatActivity implements ocrView.ocrList{

    RecyclerView.LayoutManager layout = new LinearLayoutManager(this);
    private Database_Helper db = new Database_Helper(this);
    private list_adapter adapter;
    private List<Item> data;
    private boolean multipleStatus = false;
    private List<Item> selectedItem = new ArrayList<>();
    private ActionMode actionMode;
    private Menu Cmenu;
    private ActivityOcrlistBinding binding;
    private OcrPresenter.ocrList alertdialog = new Custom_Dialog(this, this, db);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOcrlistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        data = db.getData();
        adapter = new list_adapter(data,this, selectedItem);
        binding.dataList.setLayoutManager(layout);
        binding.dataList.setHasFixedSize(true);
        binding.dataList.setAdapter(adapter);
        binding.dataList.addOnItemTouchListener(new Recycler_ClickListener(this, binding.dataList, new Recycler_ClickListener.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                if(!multipleStatus){
                    Intent intent = new Intent(view.getContext(), Halaman_DetailOCR.class);
                    intent.putExtra("item", (Serializable) data);
                    intent.putExtra("index", position);
                    startActivity(intent);
                } else {
                    multiplemode(position);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                if(!multipleStatus){
                    multipleStatus=true;
                    if(actionMode==null){
                        actionMode = startActionMode(actionModeCallBack);
                    }
                }
                multiplemode(position);
            }
        }));
    }

    private ActionMode.Callback actionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_ocrlist, menu);
            Cmenu = menu;
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.delete_selected:
                    alertdialog.showAlert(data, selectedItem);
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            multipleStatus = false;
            selectedItem.clear();
            refreshList();
        }
    };

    public void multiplemode(int position){
        if(actionMode!=null){
            if(selectedItem.contains(data.get(position)))
                selectedItem.remove(data.get(position));
            else
                selectedItem.add(data.get(position));

            if(selectedItem.size()>0)
                actionMode.setTitle("" + selectedItem.size());
            else
                actionMode.setTitle("");

            refreshList();
        }
    }

    public void refreshList(){
        adapter.selecteditem = selectedItem;
        adapter.data = data;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteResult() {
        adapter.notifyDataSetChanged();
        if(actionMode!=null){
            actionMode.finish();
        }
        Toast.makeText(this, "Data Dihapus", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMultipleSelectResult() {
        refreshList();
    }
}
