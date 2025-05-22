package org.hash.mock.debug.view.sheet.sweetpick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.disinfect.baselib.C4429R;
import java.util.List;
import org.hash.mock.debug.view.sheet.adapter.MenuRVAdapter;
import org.hash.mock.debug.view.sheet.entity.SimpleEntity;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class MenuListViewHandler {
    private int mIndex;
    private List<SimpleEntity> mMenuEntities;
    private MenuRVAdapter mMenuRVAdapter;
    private OnFragmentInteractionListener mOnFragmentInteractionListener;
    private RecyclerView mRV;
    private TextView mRvTitle;
    private int mRvVisibility = 0;
    private View mView;

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(View view, SimpleEntity simpleEntity);
    }

    public static MenuListViewHandler getInstant(int i, List<SimpleEntity> list) {
        MenuListViewHandler menuListViewHandler = new MenuListViewHandler();
        menuListViewHandler.mMenuEntities = list;
        menuListViewHandler.mIndex = i;
        return menuListViewHandler;
    }

    public void setOnMenuItemClickListener(OnFragmentInteractionListener onFragmentInteractionListener) {
        this.mOnFragmentInteractionListener = onFragmentInteractionListener;
    }

    public View onCreateView(ViewGroup viewGroup) {
        if (this.mView == null) {
            this.mView = LayoutInflater.from(viewGroup.getContext()).inflate(C4429R.layout.debug_layout_grid_menu, viewGroup, false);
            onViewCreated(this.mView);
        }
        return this.mView;
    }

    public void onViewCreated(View view) {
        List<SimpleEntity> list = this.mMenuEntities;
        if (list == null || list.size() == 0) {
            return;
        }
        this.mRV = (RecyclerView) view.findViewById(C4429R.id.f5003rv);
        this.mRV.setLayoutManager(new LinearLayoutManager(view.getContext(), 1, false));
        this.mRV.setHasFixedSize(true);
        this.mMenuRVAdapter = new MenuRVAdapter(this.mMenuEntities);
        this.mMenuRVAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: org.hash.mock.debug.view.sheet.sweetpick.MenuListViewHandler.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i, long j) {
                if (MenuListViewHandler.this.mOnFragmentInteractionListener == null) {
                    return;
                }
                MenuListViewHandler.this.mOnFragmentInteractionListener.onFragmentInteraction(view2, (SimpleEntity) MenuListViewHandler.this.mMenuEntities.get(i));
            }
        });
        this.mRV.setAdapter(this.mMenuRVAdapter);
        this.mRV.setVisibility(this.mRvVisibility);
    }

    public void animationOnStart() {
        RecyclerView recyclerView = this.mRV;
        if (recyclerView != null) {
            recyclerView.setVisibility(8);
        } else {
            this.mRvVisibility = 8;
        }
    }

    public void notifyAnimation() {
        RecyclerView recyclerView = this.mRV;
        if (recyclerView != null) {
            recyclerView.setVisibility(0);
            this.mRvVisibility = 0;
        } else {
            this.mRvVisibility = 0;
        }
    }
}
