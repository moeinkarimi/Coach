package Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import mytechcorp.ir.coach.R;
import mytechcorp.ir.coach.TextViewPlus;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.viewHolder> implements View.OnClickListener{

    private DBHandler dbHandler;
    private List<Record> recordList;

    public RecordAdapter(List<Record> recordList) {
        this.recordList = recordList;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        Button btnGenerate;
        EditText txtRecord1, txtRecord2, txtRecord3;
        TextViewPlus txtCodeGenerated, txtGPName;
        public viewHolder(View view) {
            super(view);
            btnGenerate = view.findViewById(R.id.btnGenerate);
            txtRecord1 = view.findViewById(R.id.txtRecord1);
            txtRecord2 = view.findViewById(R.id.txtRecord2);
            txtRecord3 = view.findViewById(R.id.txtRecord3);
            txtCodeGenerated = view.findViewById(R.id.txtCodeGenerated);
            txtGPName = view.findViewById(R.id.txtGPName);

        }
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.record_item, parent, false);

        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        Record movie = recordList.get(position);
        holder.txtRecord1.setText(movie.getRecord1());
        holder.txtRecord2.setText(movie.getRecord2());
        holder.txtRecord3.setText(movie.getRecord3());
        holder.txtCodeGenerated.setText(movie.getGenCode());
        holder.txtGPName.setText(movie.getGPName());
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }
}
