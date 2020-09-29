package ru.onedr.earlzzz.testrentateam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CardUserActivity extends AppCompatActivity {
    ImageView cardUserImageView;
    TextView cardUserTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_user);
        InitView();

    }

    private void InitView(){
        cardUserImageView =(ImageView)findViewById(R.id.card_user_imageView);
        cardUserTextView =(TextView) findViewById(R.id.card_user_textView);
        String firstName = getIntent().getExtras().getString("firstName");
        String lastName = getIntent().getExtras().getString("lastName");
        String email = getIntent().getExtras().getString("email");
        String avatarSrc=getIntent().getExtras().getString("avatar");
        Picasso.get().load(avatarSrc).fit().centerInside().into(cardUserImageView);
        cardUserTextView.append(firstName+" "+lastName+"\n");
        cardUserTextView.append("Email: "+email);
    }


}