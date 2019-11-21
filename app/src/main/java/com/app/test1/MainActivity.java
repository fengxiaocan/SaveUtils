package com.app.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.app.save.FastSaveUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FastSaveUtils utils = FastSaveUtils.get().editStart(this);
//        new FastSaveUtils().editStart(this).editEnd();
        utils.save("MainActivity","adsf154444");
        utils.save("MainActivity",false);
        utils.save("MainActivity",5410);
        utils.save("MainActivity",10000L);
        utils.save("MainActivity",10000.00111D);

        utils.save("MainActivity","775as54ads");
        utils.save("MainActivity",true);
        utils.save("MainActivity",5401);
        utils.save("MainActivity",120231L);
        utils.save("MainActivity",1830.0011D);

        utils.save("MainActivity","1898465456486");
        utils.save("MainActivity",true);
        utils.save("MainActivity",54501);
        utils.save("MainActivity",121L);
        utils.save("MainActivity",18120D);

        utils.save("MainActivity123","1898465456486");
        utils.save("MainActivity123",true);
        utils.save("MainActivity123",54501);
        utils.save("MainActivity123",121L);
        utils.save("MainActivity123",18120D);

        Log.e("noah",utils.getString("MainActivity"));
        Log.e("noah",String.valueOf(utils.getBoolean("MainActivity")));
        Log.e("noah",String.valueOf(utils.getInt("MainActivity")));
        Log.e("noah",String.valueOf(utils.getLong("MainActivity")));
        Log.e("noah",String.valueOf(utils.getDouble("MainActivity")));

        utils.editEnd();
    }
}
