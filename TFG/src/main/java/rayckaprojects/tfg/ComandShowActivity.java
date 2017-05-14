package rayckaprojects.tfg;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ComandShowActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    List<AdapterListComandModel> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comand_show);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        ArrayList<Comand> list=
                (ArrayList<Comand>)bundle.getSerializable("list");

        createDataModel(list);

        ListView listView = (ListView) this.findViewById(R.id.listView);
        ComandListAdapter adapter = new ComandListAdapter(this,data,R.layout.list_item);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private void createDataModel(ArrayList<? extends Comand> list){
        int count = list.size();
        data = new ArrayList<AdapterListComandModel>();
        AdapterListComandModel item;

        int count2=0;
        if(count!=0) {
            while (count2 < count) {
                item = new AdapterListComandModel(list.get(count2).getComandName(),list.get(count2).getComandText());
                data.add(item);
                count2++;
            }

        }else{
            Toast.makeText(this, ""+count, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
     //   Toast.makeText(this, ""+data.get(i).getComandName() +" "+data.get(i).getComandText(), Toast.LENGTH_SHORT).show();
    }
}
