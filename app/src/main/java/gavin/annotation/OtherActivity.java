package gavin.annotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.Bind;

import gavin.api.ViewInjector;

public class OtherActivity extends AppCompatActivity {

    @Bind(R.id.textView)
    TextView mTv;
    @Bind(R.id.button)
    Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewInjector.injectView(this);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTv.append(" ~ ");
            }
        });
    }

}
