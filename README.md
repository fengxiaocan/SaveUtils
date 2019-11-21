# SaveUtils
一个快速保存基本数据类型的工具类,基于数据库方式,基本数据类型可以取相同的名字,以代替SP
To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

gradle
maven
sbt
leiningen
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.fengxiaocan:SaveUtils:v1.0'
	}
  
  使用方式:
  
       FastSaveUtils utils = FastSaveUtils.get().editStart(this);
        //new FastSaveUtils().editStart(this).editEnd();
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
        
	
	or 链式调用
        FastSaveUtils.get().editStart(context).save("1123","大事记").save("nmiah",1002398).editEnd();
	
        if (!FastSaveUtils.checkAndReplace(this,"hhh", "判断是否相同,不相同则替换该值," +
                                                      "返回false为不相同"))
        {
            //不相同的值,并且替换为新的value
        }

	or 链式调用
