package moocollege.cn.changetextcolor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zsd on 2017/8/4 11:08
 * desc:
 */

public class FragmentContent extends Fragment {


    public static FragmentContent newInstance(String item) {
        FragmentContent itemFragment = new FragmentContent();
        Bundle bundle = new Bundle();
        bundle.putString("content", item);
        itemFragment.setArguments(bundle);
        return itemFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_content);
        Bundle bundle = getArguments();
        tv.setText(bundle.getString("content"));
        return view;
    }
}
