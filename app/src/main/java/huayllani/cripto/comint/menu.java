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
import android.widget.Button;
import android.widget.ImageButton;
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

        ImageButton logout = (ImageButton) findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Main = new Intent(menu.this, menu.class);
                startActivity(Main);
            }
        });
    }

    public void onTema(View view) {
        Intent temas = new Intent(this, TemasPracticas.class);
        temas.putExtra("title", "Temas");
        startActivity(temas);
    }

    public void onPractica(View view) {
        Intent practica = new Intent(this, TemasPracticas.class);
        practica.putExtra("title", "Practicas");
        startActivity(practica);
    }

    public void onArancel(View view) {
        Intent arancel = new Intent(this, search.class);
        startActivity(arancel);
    }
}
