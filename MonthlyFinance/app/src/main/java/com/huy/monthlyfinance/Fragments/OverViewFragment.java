package com.huy.monthlyfinance.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.huy.monthlyfinance.Database.DAO.AccountDAO;
import com.huy.monthlyfinance.MainApplication;
import com.huy.monthlyfinance.Model.Account;
import com.huy.monthlyfinance.Model.Product;
import com.huy.monthlyfinance.Model.ProductDetail;
import com.huy.monthlyfinance.Model.ProductGroup;
import com.huy.monthlyfinance.MyView.Item.ListItem.AccountItem;
import com.huy.monthlyfinance.MyView.BasicAdapter;
import com.huy.monthlyfinance.R;
import com.huy.monthlyfinance.SupportUtils.PreferencesUtils;
import com.huy.monthlyfinance.SupportUtils.SupportUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Phuong on 25/08/2016.
 */
public class OverViewFragment extends BaseFragment implements View.OnClickListener {
    private FrameLayout mLayoutQuickAdd;
    private LinearLayout mLayoutQuickAddSelect;
    private Animation mAnimationForward, mAnimationBackward;
    private Animation mAnimationRotateForward30, mAnimationRotateBackward30;

    private ArrayList<Account> mListAccount;
    private ArrayList<AccountItem> mAccountItems;

    private TextView mTextAccountTitle;
    private TextView mBalanceTitle;
    private TextView mBalanceValue;
    private BasicAdapter<AccountItem> mAccountAdapter;
    private ListView mListAccountItems;
    private PieChart mMonthlyExpenseChart;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected int getLayoutXML() {
        return R.layout.fragment_over_view;
    }

    @Override
    protected void onPrepare() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mListener.toggleProgress(true);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                setUpAccounts();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mListener.toggleProgress(false);
            }
        }.execute();
    }

    @Override
    protected void initUI(View view) {
        final Activity activity = getActivity();
        Resources resources = activity.getResources();
        mTextAccountTitle = (TextView) view.findViewById(R.id.textAccountTitle);
        mBalanceTitle = (TextView) view.findViewById(R.id.totalBalanceTitle);
        mBalanceValue = (TextView) view.findViewById(R.id.totalBalanceValue);
        ImageButton mButtonOpenSideMenu = (ImageButton) view.findViewById(R.id.buttonOpenSideMenu);
        mButtonOpenSideMenu.setOnClickListener(this);
        ImageButton mButtonQuickAdd = (ImageButton) view.findViewById(R.id.buttonQuickAdd);
        mButtonQuickAdd.setOnClickListener(this);
        mLayoutQuickAddSelect = (LinearLayout) view.findViewById(R.id.layoutQuickAddSelect);
        mLayoutQuickAddSelect.setOnClickListener(this);
        mLayoutQuickAdd = (FrameLayout) view.findViewById(R.id.layoutQuickAdd);
        view.findViewById(R.id.buttonRefresh).setOnClickListener(this);

        float[] mMonthCashFlowAmount = {37.5f, 62.5f};
        String[] mMonthCashFlow = {resources.getString(R.string.cash), resources.getString(R.string.expense)};
        mMonthlyExpenseChart = (PieChart) view.findViewById(R.id.chartMonthExpense);
        PieChart mMonthlyCashFlowChart = (PieChart) view.findViewById(R.id.chartMonthCashFlow);
        addDataToChart(new ArrayList<>(Arrays.asList(mMonthCashFlow)), mMonthCashFlowAmount, mMonthlyCashFlowChart
                , resources.getString(R.string.this_month_cash_chart), ""/*"Month Cash Flow"*/);
        mAnimationRotateForward30 = new RotateAnimation(0.0f, 30.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimationRotateForward30.setDuration(1000);
        mAnimationRotateForward30.setRepeatCount(0);
        mAnimationRotateForward30.setRepeatMode(Animation.REVERSE);
        mAnimationRotateForward30.setFillAfter(true);
        mAnimationRotateBackward30 = new RotateAnimation(0.0f, -30.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimationRotateBackward30.setDuration(1000);
        mAnimationRotateBackward30.setRepeatCount(0);
        mAnimationRotateBackward30.setRepeatMode(Animation.REVERSE);
        mAnimationRotateBackward30.setFillAfter(true);

        final ImageButton mButtonAddReminder = (ImageButton) view.findViewById(R.id.buttonAddReminder);
        final ImageButton mButtonAddTransfer = (ImageButton) view.findViewById(R.id.buttonAddTransfer);
        mButtonAddTransfer.setOnClickListener(this);
        final ImageButton mButtonAddCash = (ImageButton) view.findViewById(R.id.buttonAddCash);
        mButtonAddCash.setOnClickListener(this);
        final ImageButton mButtonAddExpense = (ImageButton) view.findViewById(R.id.buttonAddExpense);
        mButtonAddExpense.setOnClickListener(this);
        view.findViewById(R.id.layoutButtonAddIncome).setOnClickListener(this);
        view.findViewById(R.id.layoutButtonAddTransfer).setOnClickListener(this);

        mAnimationForward = new RotateAnimation(0.0f, 45.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mAnimationForward.setDuration(500);
        mAnimationForward.setRepeatCount(0);
        mAnimationForward.setRepeatMode(Animation.REVERSE);
        mAnimationForward.setFillAfter(true);
        mAnimationForward.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLayoutQuickAddSelect.setVisibility(View.VISIBLE);
                mButtonAddReminder.startAnimation(mAnimationRotateForward30);
                mButtonAddTransfer.startAnimation(mAnimationRotateForward30);
                mButtonAddCash.startAnimation(mAnimationRotateForward30);
                mButtonAddExpense.startAnimation(mAnimationRotateForward30);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mAnimationBackward = new RotateAnimation(45.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimationBackward.setDuration(500);
        mAnimationBackward.setRepeatCount(0);
        mAnimationBackward.setRepeatMode(Animation.REVERSE);
        mAnimationBackward.setFillAfter(true);
        mAnimationBackward.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mLayoutQuickAddSelect.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mButtonAddReminder.startAnimation(mAnimationRotateBackward30);
                mButtonAddTransfer.startAnimation(mAnimationRotateBackward30);
                mButtonAddCash.startAnimation(mAnimationRotateBackward30);
                mButtonAddExpense.startAnimation(mAnimationRotateBackward30);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mListAccountItems = (ListView) view.findViewById(R.id.listAccountItems);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.layoutRefresh);
        mRefreshLayout.setDistanceToTriggerSync(50);
        mRefreshLayout.setColorSchemeColors(Color.parseColor("#009688"), Color.parseColor("#009688"), Color.parseColor("#4052b5"));
        mRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.setRefreshing(true);
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainApplication.getInstance().refreshAllData();
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mRefreshLayout.setRefreshing(false);
                                Toast.makeText(activity, "Refreshed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }, 2000);
            }
        });
    }

    @Override
    protected void setStatusBarColor() {
        mListener.setStatusBarColor(Color.parseColor("#008593"));
    }

    @Override
    protected int getSideMenuColor() {
        return Color.parseColor("#008593");
    }

    @Override
    protected void fragmentReady(Bundle savedInstanceState) {
        Resources resources = getActivity().getResources();
        mAccountAdapter = new BasicAdapter<>(mAccountItems, R.layout.item_account, getActivity().getLayoutInflater());
        mListAccountItems.setAdapter(mAccountAdapter);
        mAccountAdapter.notifyDataSetChanged();
        SupportUtils.setListViewHeight(mListAccountItems);
        mListAccountItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //mAccountItems.get(position).setFocused(!mAccountItems.get(position).isFocused());
                mAccountAdapter.notifyDataSetChanged();
                SupportUtils.setListViewHeight(mListAccountItems);
            }
        });
        String account_title = resources.getString(R.string.account_title);
        account_title = account_title.replace(")", PreferencesUtils.getString(PreferencesUtils.CURRENCY, "VND") + ")");
        mTextAccountTitle.setText(account_title);
        if (!mListAccount.isEmpty()) {
            String currency = mListAccount.get(0).getCurrency();
            String balanceTitle = resources.getString(R.string.balance_title)
                    + " (" + currency + ")";
            double totalBalance = 0;
            for (Account account : mListAccount) {
                totalBalance += account.getCurrentBalance();
            }
            StringBuilder builder = new StringBuilder("")
                    .append(SupportUtils.getNormalDoubleString((int) totalBalance, "#0,000"))
                    .append(" ").append(currency);
            if (currency.toLowerCase().equals("vnd")) {
                mBalanceValue.setText(builder.toString());
            }
            mBalanceTitle.setText(balanceTitle);
        }
        setUpExpenseChart();
    }

    @Override
    protected boolean canGoBack() {
        return false;
    }

    private void setUpAccounts() {
        Resources resources = getActivity().getResources();
        AccountDAO accountDAO = AccountDAO.getInstance(getActivity());
        if (mListAccount == null) {
            mListAccount = new ArrayList<>();
        }
        if (mListAccount.isEmpty()) {
            mListAccount.addAll(accountDAO.getAllAccounts());
        }
        if (mAccountItems == null) {
            mAccountItems = new ArrayList<>();
        }
        if (mAccountItems.isEmpty()) {
            int[] colors = new int[]{Color.parseColor("#88c03f"), Color.parseColor("#88c03f"), Color.parseColor("#f74848")};
            int[] mipmaps = new int[]{R.mipmap.ic_wallet_filled_money_tool_24dp, R.mipmap.ic_bank, R.mipmap.ic_credit_cards_24dp};
            int[] drawables = new int[]{R.drawable.circle_dark_blue, R.drawable.circle_orange, R.drawable.circle_dark_red};
            int count = 0;
            for (Account account : mListAccount) {
                int resIndex = 0;
                String accountName = account.getAccountName();
                String currency = account.getCurrency();
                int currentBalance = (int) account.getCurrentBalance();
                int initBalance = (int) account.getInitialBalance();
                String current = SupportUtils.getNormalDoubleString(currentBalance, "#0,000");
                String init = SupportUtils.getNormalDoubleString(initBalance, "#0,000");
                String stringCurrentBalance = currency.toLowerCase().equals("usd") ? ("$" + current) : (current + " vnđ");
                String stringInitBalance = currency.toLowerCase().equals("usd") ? ("$" + init) : (init + " vnđ");
                if (accountName.equals(SupportUtils.getStringLocalized(getActivity(), "en", R.string.bank))) {
                    resIndex = 1;
                    accountName = resources.getString(R.string.bank);
                } else if (accountName.equals(SupportUtils.getStringLocalized(getActivity(), "en", R.string.credit_card))) {
                    resIndex = 2;
                    accountName = resources.getString(R.string.credit_card);
                } else {
                    accountName = resources.getString(R.string.cash);
                }
                if (accountName.contains("Tài khoản")) {
                    accountName = accountName.replace("Tài khoản", "TK");
                }
                mAccountItems.add(new AccountItem(drawables[resIndex], mipmaps[resIndex], 100, 40, colors[resIndex], accountName,
                        stringCurrentBalance, resources.getString(R.string.init_balance) + ": " + stringInitBalance, "Spent/ Budget: $50.00/ $700.00", false,
                        count == colors.length - 1
                ));
                count++;
            }
        }
    }

    private void setUpExpenseChart() {
        MainApplication application = MainApplication.getInstance();
        ArrayList<ProductGroup> productGroups = application.getProductGroups();
        HashMap<String, Double> expenseByGroup = new HashMap<>();
        for (ProductGroup group : productGroups) {
            expenseByGroup.put(group.getProductGroupID(), 0.d);
        }
        for (Product product : application.getProducts()) {
            String groupId = product.getProductGroupID();
            String productId = product.getProductID();
            if (expenseByGroup.containsKey(groupId)) {
                double currentGroupExpense = expenseByGroup.get(product.getProductGroupID());
                for (ProductDetail productDetail : application.getProductDetails()) {
                    if (productDetail.getProductID().equals(productId)) {
                        currentGroupExpense += productDetail.getProductCost();
                    }
                }
                expenseByGroup.put(groupId, currentGroupExpense);
            }
        }

        HashMap<String, Float> mapExpense = new HashMap<>();
        for (Map.Entry<String, Double> entry : expenseByGroup.entrySet()) {
            String groupId = entry.getKey();
            double expense = entry.getValue();
            Log.d("Data", "group: " + groupId + ", expense: " + expense);
            String groupName = "";
            String country = SupportUtils.getCountryCode();
            for (int i = 0; i < productGroups.size() && groupName.isEmpty(); i++) {
                ProductGroup group = productGroups.get(i);
                if (group.getProductGroupID().equals(groupId)) {
                    groupName = country.contains("vi") ? group.getGroupNameVI() : group.getGroupNameEN();
                }
            }
            mapExpense.put(groupName, (float) expense);
        }
        int size = 0;
        for (Map.Entry<String, Float> entry : mapExpense.entrySet()) {
            if (entry.getValue() > 0) {
                size++;
            }
        }
        float[] mMonthExpenseAmount = new float[size];
        String[] mMonthExpense = new String[size];
        int index = 0;
        for (Map.Entry<String, Float> entry : mapExpense.entrySet()) {
            if (entry.getValue() > 0) {
                mMonthExpense[index] = entry.getKey();
                mMonthExpenseAmount[index] = entry.getValue();
                index++;
            }
        }
        Resources resources = MainApplication.getInstance().getResources();
        addDataToChart(new ArrayList<>(Arrays.asList(mMonthExpense)), mMonthExpenseAmount, mMonthlyExpenseChart,
                resources.getString(R.string.this_month_expense_chart), "Monthly Expenses");
    }

    private void addDataToChart(final ArrayList<String> xValues, final float[] yValuesData, PieChart chart,
                                final String textOnNothingSelected, String chartTitle) {
        chart.setUsePercentValues(true);
        chart.setDescription("");
        chart.setDrawHoleEnabled(true);
        chart.setHoleColorTransparent(true);
        chart.setHoleRadius(7);
        chart.setTransparentCircleRadius(10);
        chart.setRotationAngle(0);
        chart.setRotationEnabled(true);
        ArrayList<Entry> yValues = new ArrayList<>();
        for (int i = 0; i < yValuesData.length; i++) {
            yValues.add(new Entry(yValuesData[i], i));
        }

        PieDataSet pieDataSet = new PieDataSet(yValues, chartTitle);
        pieDataSet.setSliceSpace(3);
        pieDataSet.setSelectionShift(5);

        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.JOYFUL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.COLORFUL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.LIBERTY_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.PASTEL_COLORS) {
            colors.add(c);
        }

        colors.add(ColorTemplate.getHoloBlue());
        pieDataSet.setColors(colors);

        PieData pieData = new PieData(xValues, pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(11f);
        pieData.setValueTextColor(Color.BLACK);

        chart.setData(pieData);
        chart.highlightValue(null);
        chart.invalidate();

        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                Toast.makeText(getActivity(), xValues.get(e.getXIndex()) + ": " + e.getVal(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {
                Toast.makeText(getActivity(), textOnNothingSelected, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        final Activity activity = getActivity();
        switch (view.getId()) {
            case R.id.buttonQuickAdd:
                mLayoutQuickAdd.startAnimation(mAnimationForward);
                break;
            case R.id.layoutQuickAddSelect:
                mLayoutQuickAdd.startAnimation(mAnimationBackward);
                break;
            case R.id.buttonOpenSideMenu:
                mListener.toggleSideMenu(true);
                break;
            case R.id.buttonAddExpense:
                Bundle bundle = new Bundle();
                bundle.putBoolean("isFormOpen", true);
                mListener.showFragment(ExpenseManagerFragment.class, bundle);
                break;
            case R.id.layoutButtonAddIncome:
            case R.id.buttonAddCash:
                bundle = new Bundle();
                bundle.putBoolean("isAddIncome", true);
                bundle.putBoolean("isAddTransfer", false);
                mListener.showFragment(BudgetFragment.class, bundle);
                break;
            case R.id.layoutButtonAddTransfer:
            case R.id.buttonAddTransfer:
                bundle = new Bundle();
                bundle.putBoolean("isAddIncome", false);
                bundle.putBoolean("isAddTransfer", true);
                mListener.showFragment(BudgetFragment.class, bundle);
                break;
            case R.id.buttonRefresh:
                mRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshing(true);
                        mRefreshLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                MainApplication.getInstance().refreshAllData();
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mRefreshLayout.setRefreshing(false);
                                        Toast.makeText(activity, "Refreshed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }, 2000);
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void refreshData() {
        Resources resources = MainApplication.getInstance().getResources();
        if (mListAccount == null) {
            mListAccount = new ArrayList<>();
        }
        mListAccount.clear();
        mListAccount.addAll(MainApplication.getInstance().getAccounts());
        if (mAccountItems == null) {
            mAccountItems = new ArrayList<>();
        }
        mAccountItems.clear();
        int[] colors = new int[]{Color.parseColor("#88c03f"), Color.parseColor("#88c03f"), Color.parseColor("#f74848")};
        int[] mipmaps = new int[]{R.mipmap.ic_wallet_filled_money_tool_24dp, R.mipmap.ic_bank, R.mipmap.ic_credit_cards_24dp};
        int[] drawables = new int[]{R.drawable.circle_dark_blue, R.drawable.circle_orange, R.drawable.circle_dark_red};
        int count = 0;
        for (Account account : mListAccount) {
            int resIndex = 0;
            String accountName = account.getAccountName();
            String currency = account.getCurrency();
            double currentBalance = account.getCurrentBalance();
            double initBalance = account.getInitialBalance();
            String current = SupportUtils.getNormalDoubleString(currentBalance, "#0,000");
            String init = SupportUtils.getNormalDoubleString(initBalance, "#0,000");
            String stringCurrentBalance = currency.toLowerCase().equals("usd") ? ("$" + current) : (current + " vnđ");
            String stringInitBalance = currency.toLowerCase().equals("usd") ? ("$" + init) : (init + " vnđ");
            Context context = getActivity();
            if (context == null) {
                context = MainApplication.getInstance().getApplicationContext();
            }
            if (accountName.equals(SupportUtils.getStringLocalized(context, "en", R.string.bank))) {
                resIndex = 1;
            } else if (accountName.equals(SupportUtils.getStringLocalized(context, "en", R.string.credit_card))) {
                resIndex = 2;
            }
            mAccountItems.add(new AccountItem(drawables[resIndex], mipmaps[resIndex], 100, 40, colors[resIndex], accountName,
                    stringCurrentBalance, resources.getString(R.string.init_balance) + ": " + stringInitBalance, "Spent/ Budget: $50.00/ $700.00", false,
                    count == colors.length - 1
            ));
            count++;
        }

        setUpExpenseChart();
    }
}
