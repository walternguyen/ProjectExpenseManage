package com.huy.monthlyfinance;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.huy.monthlyfinance.Database.DatabaseHelper;
import com.huy.monthlyfinance.Fragments.BaseFragment;
import com.huy.monthlyfinance.Fragments.ExpenseManagerFragment;
import com.huy.monthlyfinance.Fragments.OverViewFragment;
import com.huy.monthlyfinance.Listener.MainListener;
import com.huy.monthlyfinance.Listener.NavigationListener;
import com.huy.monthlyfinance.MyView.BasicAdapter;
import com.huy.monthlyfinance.MyView.Item.ListItem.SideMenuItem;
import com.huy.monthlyfinance.MyView.RoundImageView;
import com.huy.monthlyfinance.ProcessData.ProcessDataMining;
import com.huy.monthlyfinance.SupportUtils.SupportUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MainActivity extends Activity implements View.OnClickListener, MainListener, NavigationListener {
    private DrawerLayout mDrawer;
    private LinearLayout mLayoutSideMenu;
    private TreeMap<String, BaseFragment> mFragments;
    private FragmentManager mManager;
    private ArrayList<SideMenuItem> mMenuItems;
    private BasicAdapter<SideMenuItem> mSideMenuAdapter;
    private LinearLayout mLayoutTopSideMenu;
    private RoundImageView mImageAvatar;
    // xu ly test thuat toan
    DatabaseHelper db;
    ProcessDataMining p;
    SQLiteDatabase sl;
    List tets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test thuat toan huy
       //db=new DatabaseHelper(this);
        //db.onUpgrade(sl,9,10);
        //db.open();
       // p=new ProcessDataMining();
        //tets=new ArrayList<>();
        //tets=p.getDataProduct();
//        System.out.println("danh sach san pham");
//        for(int i=0;i<tets.size();i++) {
//            System.out.println("phan tu thu:"+tets.get(i).toString());
//        }
        // end
        mDrawer = (DrawerLayout) findViewById(R.id.mainLayout);
        mLayoutSideMenu = (LinearLayout) findViewById(R.id.layoutSideMenu);
        mLayoutTopSideMenu = (LinearLayout) findViewById(R.id.layoutTopSideMenu);
        mImageAvatar = (RoundImageView) findViewById(R.id.imageAvatar);

        mFragments = new TreeMap<>();
        OverViewFragment overViewFragment = new OverViewFragment();
        overViewFragment.setListener(this);
        this.addFragment(overViewFragment);
        ExpenseManagerFragment expenseManagerFragment = new ExpenseManagerFragment();
        expenseManagerFragment.setListener(this);
        expenseManagerFragment.setNavListener(this);
        this.addFragment(expenseManagerFragment);

        ListView mSideMenu = (ListView) findViewById(R.id.sideMenu);
        mMenuItems = new ArrayList<>();
        mMenuItems.add(new SideMenuItem(R.mipmap.ic_cash_24dp, getString(R.string.budget), ""));
        mMenuItems.add(new SideMenuItem(R.mipmap.ic_expense_24dp, getString(R.string.expenses), ""));
        mMenuItems.add(new SideMenuItem(R.mipmap.ic_report_24dp, getString(R.string.recommendation), ""));
        mMenuItems.add(new SideMenuItem(R.mipmap.ic_chart_24dp, getString(R.string.statistic), ""));
        mSideMenuAdapter = new BasicAdapter<>(mMenuItems, R.layout.item_side_menu, getLayoutInflater());
        mSideMenu.setAdapter(mSideMenuAdapter);
        mSideMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (int pos = 0; pos < mMenuItems.size(); pos++) {
                    mMenuItems.get(pos).setFocused(pos == i);
                }
                mSideMenuAdapter.notifyDataSetChanged();
                if (mMenuItems.get(i).getTextName().equals(getString(R.string.expenses))) {
                    showFragment(ExpenseManagerFragment.class, null);
                }
                mDrawer.closeDrawer(mLayoutSideMenu);
            }
        });

        mManager = getFragmentManager();
        mManager.beginTransaction().add(R.id.layoutFragmentsContainer, overViewFragment, overViewFragment.getClass().getName()).commit();
    }

    private void addFragment(BaseFragment fragment) {
        if (mFragments != null) {
            mFragments.put(fragment.getClass().getName(), fragment);
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void toggleSideMenu(boolean isOpen) {
        if (isOpen) {
            mDrawer.openDrawer(mLayoutSideMenu, true);
        } else {
            mDrawer.closeDrawer(mLayoutSideMenu, true);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setStatusBarColor(int color) {
        if (SupportUtils.checkLollipopOrAbove()) {
            getWindow().setStatusBarColor(color);
        }
    }

    @Override
    public void showFragment(Class c, Bundle data) {
        if (c == ExpenseManagerFragment.class) {
            ExpenseManagerFragment fragment = (ExpenseManagerFragment) mFragments.get(c.getName());
            fragment.setArguments(data);
            mManager.beginTransaction().replace(R.id.layoutFragmentsContainer, fragment, fragment.getClass().getName()).commit();
        }
    }

    @Override
    public void changeSideMenuColor(int color) {
        mLayoutTopSideMenu.setBackgroundColor(color);
        mImageAvatar.setColor(color);
    }

    protected void showFragment(String fragmentName) {
        mManager.beginTransaction().replace(R.id.layoutFragmentsContainer, mFragments.get(fragmentName), fragmentName).commit();
    }

    @Override
    public void navBack() {
        for (int pos = 0; pos < mMenuItems.size(); pos++) {
            mMenuItems.get(pos).setFocused(false);
        }
        mSideMenuAdapter.notifyDataSetChanged();
        OverViewFragment overViewFragment = (OverViewFragment) mFragments.get(OverViewFragment.class.getName());
        mManager.beginTransaction().replace(R.id.layoutFragmentsContainer, overViewFragment, overViewFragment.getClass().getName()).commit();
    }
}
