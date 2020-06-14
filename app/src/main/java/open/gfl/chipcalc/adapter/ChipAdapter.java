package open.gfl.chipcalc.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import open.gfl.chipcalc.R;
import open.gfl.chipcalc.puzzle.Chip;
import open.gfl.chipcalc.view.ChipTileView;
import java.util.ArrayList;

public class ChipAdapter extends ArrayAdapter<Chip> {
    private static final int LAYOUT = 2131492919;

    public ChipAdapter(Context context, ArrayList<Chip> arrayList) {
        super(context, R.layout.layout_chip, arrayList);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2 = 0;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.layout_chip, viewGroup, false);
        }
        ChipTileView chipTileView = (ChipTileView) view.findViewById(R.id.chipTileView);
        TextView textView = (TextView) view.findViewById(R.id.starTextView);
        ImageView imageView = (ImageView) view.findViewById(R.id.rotatedImageView);
        TextView textView2 = (TextView) view.findViewById(R.id.levelTextView);
        TextView[] textViewArr = {(TextView) view.findViewById(R.id.statTextView1), (TextView) view.findViewById(R.id.statTextView2), (TextView) view.findViewById(R.id.statTextView3), (TextView) view.findViewById(R.id.statTextView4)};
        ImageView[] imageViewArr = {(ImageView) view.findViewById(R.id.statImageView1), (ImageView) view.findViewById(R.id.statImageView2), (ImageView) view.findViewById(R.id.statImageView3), (ImageView) view.findViewById(R.id.statImageView4)};
        Chip chip = (Chip) getItem(i);
        if (chip != null) {
            chipTileView.setChip(chip);
            StringBuilder sb = new StringBuilder();
            for (int i3 = 0; i3 < chip.star; i3++) {
                sb.append("★");
            }
            textView.setText(sb.toString());
            imageView.setVisibility(chip.rotated() ? View.VISIBLE : View.INVISIBLE);
            if (chip.level == 0) {
                textView2.setVisibility(View.INVISIBLE);
                textView2.setText("");
            } else {
                textView2.setVisibility(View.GONE);
                textView2.setText("+" + chip.level);
            }
            int[] iArr = {R.drawable.dmg, R.drawable.brk, R.drawable.hit, R.drawable.rld};
            int[] array = chip.getStat().toArray();
            int i4 = 0;
            while (i2 < 4) {
                if (array[i2] > 0) {
                    TextView textView3 = textViewArr[i4];
                    ImageView imageView2 = imageViewArr[i4];
                    textView3.setText(String.valueOf(array[i2]));
                    imageView2.setImageResource(iArr[i2]);
                    i4++;
                }
                i2++;
            }
            i2 = i4;
        }
        while (i2 < 4) {
            TextView textView4 = textViewArr[i2];
            ImageView imageView3 = imageViewArr[i2];
            textView4.setText("");
            imageView3.setImageDrawable((Drawable) null);
            i2++;
        }
        return view;
    }
}
