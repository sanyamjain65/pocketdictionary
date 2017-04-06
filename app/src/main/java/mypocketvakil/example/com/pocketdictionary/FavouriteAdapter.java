package mypocketvakil.example.com.pocketdictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sanyam jain on 07-02-2017.
 */

public class FavouriteAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> ID;
    ArrayList<String> title;
    ArrayList<String> meaning;
    public FavouriteAdapter(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> title,
            ArrayList<String> meaning

    )
    {

        this.context = context2;
        this.ID = id;
        this.title = title;
        this.meaning = meaning;

    }
    @Override
    public int getCount() {
        return ID.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View child, ViewGroup viewGroup) {


        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.favorite_list, null);

            holder = new Holder();

            holder.title = (TextView) child.findViewById(R.id.favorite_text);
            holder.meaning = (TextView) child.findViewById(R.id.favorite_summary);


            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.title.setText(ID.get(position));
        holder.meaning.setText(meaning.get(position));



        return child;
    }
    public class Holder {
        TextView title;
        TextView meaning;

    }

}

