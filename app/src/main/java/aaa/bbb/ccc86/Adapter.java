package aaa.bbb.ccc86;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class Adapter extends PagerAdapter{

    private DatabaseHelper databaseHelper;
    private Cursor cursor;

    LayoutInflater inflater;
    Context context;

    int pos = 1;

    public Adapter(LayoutInflater inflater, Context context) {
        this.inflater = inflater;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View v = inflater.inflate(R.layout.item, null);

        int[] colors = new int[]{Color.WHITE};
        int[] colors_back = new int[]{context.getResources().getColor(R.color.blue), context.getResources().getColor(R.color.ocean),
                context.getResources().getColor(R.color.grey), context.getResources().getColor(R.color.dark_grey), context.getResources().getColor(R.color.light_blue)};
        int[] images = new int[]{R.drawable.logo};

        databaseHelper = new DatabaseHelper(context);


        ImageView image = (ImageView) v.findViewById(R.id.image);
        TextView title = (TextView) v.findViewById(R.id.title);
        TextView text = (TextView) v.findViewById(R.id.text);
        ScrollView llItem = (ScrollView) v.findViewById(R.id.ll_item);

        image.setImageResource(images[0]);

        if (pos > 30) {
            pos = 1;
        }

        try {
            databaseHelper.checkAndCopyDatabase();
            databaseHelper.openDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        try {
            String str = "select * from Code where id = " + String.valueOf(pos);
            cursor = databaseHelper.QueryData(str);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        title.setText(cursor.getString(1));
                        text.setText(cursor.getString(2));
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        title.setTextColor(colors[0]);
        llItem.setBackgroundColor(colors_back[position % 5]);

        pos = pos + 1;

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
