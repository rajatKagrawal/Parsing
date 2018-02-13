package com.example.rajat.parsing;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Country> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        getCountryData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void getCountryData(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.androidbegin.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);

        Observable<List<Country>> observable = apiService.getcountrydata().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<List<Country>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Country> products) {

                list = new ArrayList<>();
                for (int i =0;i<products.size();i++){

                    Country p = new Country();
                    p.setCountry(products.get(i).getCountry());
                    p.setImageurl(products.get(i).getImageurl());
                    p.setRank(products.get(i).getRank());
                    p.setPopulation(products.get(i).getPopulation());
                    //p.setInstock(products.get(i).getInstock());
                    list.add(p);
                }

                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list,ImageLoader.getInstance());
                RecyclerView.LayoutManager recyce = new GridLayoutManager(MainActivity.this,2);
                recyclerView.addItemDecoration(new GridSpacingdecoration(2, dpToPx(10), true));
                recyclerView.setLayoutManager(recyce);
                recyclerView.setItemAnimator( new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerAdapter);

            }
        });
    }
    public class GridSpacingdecoration extends RecyclerView.ItemDecoration {

        private int span;
        private int space;
        private boolean include;

        public GridSpacingdecoration(int span, int space, boolean include) {
            this.span = span;
            this.space = space;
            this.include = include;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % span;

            if (include) {
                outRect.left = space - column * space / span;
                outRect.right = (column + 1) * space / span;

                if (position < span) {
                    outRect.top = space;
                }
                outRect.bottom = space;
            } else {
                outRect.left = column * space / span;
                outRect.right = space - (column + 1) * space / span;
                if (position >= span) {
                    outRect.top = space;
                }
            }
        }

    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
