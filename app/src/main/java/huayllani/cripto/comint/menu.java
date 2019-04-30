package huayllani.cripto.comint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Drawable originDrawable = getResources().getDrawable(R.drawable.shield);
        Bitmap originBitmap = ((BitmapDrawable) originDrawable).getBitmap();

        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(getResources(), originBitmap);

        roundedDrawable.setCornerRadius(originBitmap.getHeight());
        ImageView imageview = (ImageView) findViewById(R.id.img_menu);
        imageview.setImageDrawable(roundedDrawable);
    }

    public void onTema(View view) {
        Intent temas = new Intent(this, TemasPracticas.class);
        startActivity(temas);
    }

    public void onPractica(View view) {
        Intent practica = new Intent(this, TemasPracticas.class);
        startActivity(practica);
    }

    public void onArancel(View view) {
        Intent arancel = new Intent(this, search.class);
        startActivity(arancel);
    }
}