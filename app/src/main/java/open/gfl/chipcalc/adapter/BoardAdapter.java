package open.gfl.chipcalc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import open.gfl.chipcalc.Global;
import open.gfl.chipcalc.R;
import open.gfl.chipcalc.puzzle.Board;
import open.gfl.chipcalc.view.BoardView;
import java.util.ArrayList;

public class BoardAdapter extends ArrayAdapter<Board> {
    private static final int LAYOUT = 2131492920;

    public BoardAdapter(Context context, ArrayList<Board> arrayList) {
        super(context, R.layout.layout_list_board, arrayList);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.layout_list_board, viewGroup, false);
        }
        BoardView boardView = (BoardView) view.findViewById(R.id.boardAdapterBoardView);
        TextView textView = (TextView) view.findViewById(R.id.boardAdapterTextView);
        TextView textView2 = (TextView) view.findViewById(R.id.ticketTextView);
        Board board = (Board) getItem(i);
        if (board != null) {
            boardView.setBoard(board);
            textView.setText(Global.fPercStr(board.statPerc));
            textView2.setText(String.valueOf(board.ticket));
        }
        return view;
    }
}
