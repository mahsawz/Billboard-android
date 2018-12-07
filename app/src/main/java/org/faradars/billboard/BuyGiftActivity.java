package org.faradars.billboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BuyGiftActivity extends AppCompatActivity implements View.OnClickListener {
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_gift);
        TextView txtView=findViewById(R.id.giftCode);
        Bundle data = getIntent().getExtras();
        assert data != null;
        ShoppingRecord record = data.getParcelable("record");
        assert record != null;
        txtView.setText(record.getCode());
        back=findViewById(R.id.backToGiftShop);
        back.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.backToGiftShop){
            Intent intent = new Intent(BuyGiftActivity.this, ShowGiftActivity.class);
            startActivity(intent);
        }
    }
}
