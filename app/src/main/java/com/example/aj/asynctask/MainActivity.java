package com.example.aj.asynctask;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.MalformedURLException;
//Android AsyncTask is an abstract class provided by Android which gives us
// the liberty to perform heavy tasks in the background and keep the UI thread light thus making the application more responsive.

//Params : The type of the parameters sent to the task upon execution
//Progress : The type of the progress units published during the background computation
//Result : The type of the result of the background computation

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Button btnload;
    //String imageurl="https://www.google.co.in/search?q=nature+wallpaper&rlz=1C1CHZL_enIN750IN750&source=lnms&tbm=isch&sa=X&ved=0ahUKEwi9yaS034PXAhUJQY8KHdK2C14Q_AUICigB&biw=1920&bih=948#imgrc=hJc-uz8hJfah-M:";
    //String imageurl="https://www.google.co.in/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwjcwJGbvdnWAhUXSY8KHXD8AtUQjRwIBw&url=http%3A%2F%2Fwww.google.com%2Fdoodles%2Fgoogles-new-logo&psig=AOvVaw3Q8SSqRCmhSKBSVk7jvoo8&ust=1507292713021298";

    String imageurl="https://cdn.pixabay.com/photo/2016/07/10/21/47/cat-1508613_960_720.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img=(ImageView)findViewById(R.id.imageView1);
        btnload=(Button)findViewById(R.id.button1);
        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Myoperation().execute(imageurl);
            }
        });

    }

    public class Myoperation extends AsyncTask<String,String,Bitmap>
    {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(MainActivity.this);

            pd.setTitle("Download Image");

            pd.setMessage("Loading...");

            pd.show();

        }

        @Override
        protected Bitmap doInBackground(String... param) {
            String imageURL=param[0];
            Bitmap bitmap=null;
            try{
                InputStream input=new java.net.URL(imageURL).openStream();
                bitmap = BitmapFactory.decodeStream(input);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;

        }
        @Override
        protected void onPostExecute(Bitmap result) {
            // Set the bitmap into ImageView
            img.setImageBitmap(result);
            // Close progressdialog
            pd.dismiss();
        }

    }
}

