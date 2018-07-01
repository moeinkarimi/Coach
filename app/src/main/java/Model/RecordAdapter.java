package Model;

import android.content.Context;
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

public class RecordAdapter extends ArrayAdapter<String> {

    private int layout;
    private DBHandler dbHandler;

    public RecordAdapter( Context context,int resource, String[] objects) {
        super(context,resource,objects);
        layout = resource;
        dbHandler = new DBHandler(context);
    }


    @Override
    public View getView(final int position,View convertView,ViewGroup parent) {
        ViewHolder mainView = null;
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(layout, parent, false);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.btnGenerate = (Button)convertView.findViewById(R.id.btnGenerate);
            viewHolder.txtRecord1 = (EditText) convertView.findViewById(R.id.txtRecord1);
            viewHolder.txtRecord2 = (EditText)convertView.findViewById(R.id.txtRecord2);
            viewHolder.txtRecord3 = (EditText)convertView.findViewById(R.id.txtRecord3);
            viewHolder.txtCodeGenerated = (TextViewPlus) convertView.findViewById(R.id.txtCodeGenerated);
            viewHolder.txtGPName = (TextViewPlus)convertView.findViewById(R.id.txtGPName);
            try{

                viewHolder.btnGenerate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                    GenerateCode generateCode = new GenerateCode();
//                    viewHolder.txtCodeGenerated.setText(generateCode.GenerateCode(42,dbHandler.GetGroupCode(position+1,1),"10"));
                        Toast.makeText(getContext(), position, Toast.LENGTH_LONG).show();
                    }
                });
            }catch (Exception e){
                Log.d("ex " , e.getMessage());
            }
            convertView.setTag(viewHolder);

        }
        else {
            mainView = (ViewHolder)convertView.getTag();
            mainView.txtGPName.setText(getItem(position));

        }

        return convertView;
    }

    public class ViewHolder{
        Button btnGenerate;
        EditText txtRecord1, txtRecord2, txtRecord3;
        TextViewPlus txtCodeGenerated, txtGPName;

    }
}
