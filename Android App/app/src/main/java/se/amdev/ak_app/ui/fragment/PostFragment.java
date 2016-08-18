package se.amdev.ak_app.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.LocalStorageHandler;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.data.model.ThreadWeb;
import se.amdev.ak_app.ui.activity.PostActivity;
import se.amdev.ak_app.ui.activity.ThreadActivity;
import se.amdev.ak_app.ui.adapter.PostAdapter;

/**
 * Created by Martin on 01/06/16.
 */
public class PostFragment extends Fragment {

    public static ListView listView;
    public static ListAdapter postAdapter;
    public static DrawerLayout drawerLayout;
    public static LinearLayout postLayout;
    public static CheckBox favCheckbox;
    public static EditText answerText;
    public static TextView stockName;
    public static Button logOut;
    public static Button post;
    public static Button sendAnswerButton;
    public static Button refresh;
    public static Button stockButton;
    public static TextView slidemarketValue;
    public static TextView slidedayHigh;
    public static TextView slidedayLow;
    public static TextView slideaskPrice;
    public static TextView slidebidPrice;
    public static TextView sliderevenue;
    public static TextView slidechangePercent;
    public static TextView slidechangeCurrency;
    public static TextView slideStockName;
    public static TextView slideCompanyName;
    public static boolean answerShowed;
    private static Context context;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.post_fragment, container, false);

        context = getContext();
        answerShowed = false;

        post = (Button) view.findViewById(R.id.post_button_post);
        refresh = (Button) view.findViewById(R.id.post_button_refresh);
        favCheckbox = (CheckBox) view.findViewById(R.id.toolbar_post_fav_checkBox);
        logOut = (Button) view.findViewById(R.id.user_slide_button_logout);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout_post);
        postAdapter = new PostAdapter(getContext(), ApplicationLoader.postList);
        listView = (ListView) view.findViewById(R.id.main_post_listview);
        answerText = (EditText) view.findViewById(R.id.answer);
        stockButton = (Button) view.findViewById(R.id.post_button_stock);
        stockName = (TextView) view.findViewById(R.id.toolbar_post_stockname);
        postLayout = (LinearLayout) view.findViewById(R.id.answer_layout);
        sendAnswerButton = (Button) view.findViewById(R.id.send_answer_button);
        slidemarketValue = (TextView) view.findViewById(R.id.stock_slide_info_marketvalue);
        slidedayHigh = (TextView) view.findViewById(R.id.stock_slide_info_dayhigh);
        slidedayLow = (TextView) view.findViewById(R.id.stock_slide_info_daylow);
        slideaskPrice = (TextView) view.findViewById(R.id.stock_slide_info_askprice);
        slidebidPrice = (TextView) view.findViewById(R.id.stock_slide_info_bidprice);
        sliderevenue = (TextView) view.findViewById(R.id.stock_slide_info_revenue);
        slidechangeCurrency = (TextView) view.findViewById(R.id.stock_slide_info_currency);
        slidechangePercent = (TextView) view.findViewById(R.id.stock_slide_info_percent);
        slideStockName = (TextView) view.findViewById(R.id.stock_slide_stockname);
        slideCompanyName = (TextView) view.findViewById(R.id.stock_slide_companyname);

        if (ApplicationLoader.stockWebHashMap.get(PostActivity.stockName) == null) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            slidechangePercent.setText(ApplicationLoader.stockWebHashMap.get(PostActivity.stockName).getChangePercent());
            slidemarketValue.setText(ApplicationLoader.stockWebHashMap.get(PostActivity.stockName).getMarketValue());
            slidedayHigh.setText(ApplicationLoader.stockWebHashMap.get(PostActivity.stockName).getDayHighCurrency());
            slidedayLow.setText(ApplicationLoader.stockWebHashMap.get(PostActivity.stockName).getDayLowCurrency());
            slideaskPrice.setText(ApplicationLoader.stockWebHashMap.get(PostActivity.stockName).getAskPrice());
            slidebidPrice.setText(ApplicationLoader.stockWebHashMap.get(PostActivity.stockName).getBidPrice());
            sliderevenue.setText(ApplicationLoader.stockWebHashMap.get(PostActivity.stockName).getDayRevenue());
            slidechangeCurrency.setText(ApplicationLoader.stockWebHashMap.get(PostActivity.stockName).getChangeCurrency());
            slideStockName.setText(ApplicationLoader.stockWebHashMap.get(PostActivity.stockName).getStockName());
            slideCompanyName.setText(PostActivity.threadName);

            if (ApplicationLoader.stockWebHashMap.get(PostActivity.stockName).getChangeCurrency().startsWith("+")) {
                slidechangeCurrency.setTextColor(Color.parseColor("#009700"));
            } else {
                slidechangeCurrency.setTextColor(Color.RED);
            }
        }

        postLayout.setVisibility(View.GONE);

        stockName.setText(PostActivity.threadName);
        checkIfFav();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh.setVisibility(View.GONE);
                PostActivity.postBar.setVisibility(View.VISIBLE);
                updatePostView();
            }
        });


        stockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ApplicationLoader.stockWebHashMap.get(PostActivity.stockName) != null) {
                    drawerLayout.openDrawer(GravityCompat.END);
                }
            }
        });

        favCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ApplicationLoader.favoritList.add(ApplicationLoader.threadWebHashMap.get(PostActivity.threadName));
                    favCheckbox.setChecked(true);
                    // Kanske göra vid onDestroy?
                    ThreadsFragment.setFav();
                    LocalStorageHandler.setFavorit(getContext(), ApplicationLoader.favoritList);
                } else {
                    int position = 1 + ApplicationLoader.favoritList.indexOf(ApplicationLoader.threadWebHashMap.get(PostActivity.threadName));
                    ApplicationLoader.favoritList.remove(ApplicationLoader.threadWebHashMap.get(PostActivity.threadName));
                    favCheckbox.setChecked(false);
                    ThreadsFragment.setFav();
                    LocalStorageHandler.removeFav(getContext(), position);
                }
            }
        });

        sendAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostActivity.postBar.setVisibility(View.VISIBLE);
                ApplicationLoader.post(ApplicationLoader.threadPosition, answerText.getText().toString());
                answerText.getText().clear();
                postLayout.setVisibility(View.GONE);
                refresh.setVisibility(View.GONE);
                ((PostActivity) getActivity()).hideKeyboard();
                answerShowed = false;
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerShowed = true;
                postLayout.setVisibility(View.VISIBLE);
                ((PostActivity) getActivity()).showKeyboard();
            }
        });

        setList();
        return view;
    }

    public static void setList() {
        listView.setAdapter(postAdapter);
    }

    public static void updatePostView() {
        ApplicationLoader.loadPosts(PostActivity.threadName);
    }

    public void checkIfFav() {
        for (ThreadWeb t : ApplicationLoader.favoritList) {
            if (ApplicationLoader.threadWebHashMap.get(PostActivity.threadName).equals(t)) {
                favCheckbox.setChecked(true);
            }
        }
    }

    public static void alreadyVote() {
        Toast.makeText(context, R.string.post_already_voted, Toast.LENGTH_SHORT).show();
    }

    public static void updateDone() {
        PostActivity.postBar.setVisibility(View.GONE);
        refresh.setVisibility(View.VISIBLE);
        setList();
    }
}
