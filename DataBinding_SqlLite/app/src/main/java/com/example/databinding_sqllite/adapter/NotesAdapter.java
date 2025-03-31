package com.example.databinding_sqllite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.databinding_sqllite.R;
import com.example.databinding_sqllite.SQLLiteActivity;
import com.example.databinding_sqllite.model.NotesModel;

import java.util.List;

public class NotesAdapter extends BaseAdapter {
    private SQLLiteActivity lite;
    private Context context;
    private int layout;
    private List<NotesModel> noteList;

    public NotesAdapter(Context context, int layout, List<NotesModel> noteList) {
        this.context = context;
        this.layout = layout;
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    private class ViewHolder {
        TextView textViewNote;
        ImageView imageViewEdit;
        ImageView imageViewDelete;


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//goi viewHolder
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.textViewNote = (TextView) convertView.findViewById(R.id.textViewNameNote);
            viewHolder.imageViewDelete= (ImageView) convertView.findViewById(R.id.imageViewDelete);
            viewHolder.imageViewEdit = (ImageView) convertView.findViewById(R.id.imageViewEdit);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //lấy giá trị
        final NotesModel notes = noteList.get(position);
        viewHolder.textViewNote.setText(notes.getNameNote());
        viewHolder.imageViewDelete.setImageResource(R.drawable.delete);
        viewHolder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lite.DialogDelete(notes.getNameNote(), notes.getIdNote());
            }
        });
        viewHolder.imageViewEdit.setImageResource(R.drawable.edit);
        viewHolder.imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lite.DialogCapNhatNotes(notes.getNameNote(), notes.getIdNote());
            }
        });
        return convertView;

    }
}
