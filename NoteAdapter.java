package com.example.mynotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> notes;
    private OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(Note note);
    }
    public void setOnItemClickListener(OnItemClickListener listener){this.listener=listener; }
    public void setNotes (List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position){
        Note currentNote = notes.get(position);
        holder.textViewTitle.setText("Title= "+ currentNote.getTitle());
        holder.textViewDescription.setText("Description= "+ currentNote.getDesc());
        holder.textViewDate.setText("Date= "+ currentNote.getDate());
    }

    @Override
    public int getItemCount() { return notes == null? 0: notes.size(); }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewDate;

        public NoteViewHolder(@NonNull View itemView){
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textviewTitle);
            textViewDescription = itemView.findViewById(R.id.textviewDescription);
            textViewDate = itemView.findViewById(R.id.textviewDate);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION){
                    listener.onItemClick(notes.get(position));
                }
            });
        }
    }
}
