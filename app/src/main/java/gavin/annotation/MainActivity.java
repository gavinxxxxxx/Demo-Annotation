package gavin.annotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Field;

import gavin.annotation.a1.GetViewTo;

public class MainActivity extends AppCompatActivity {

    @GetViewTo(R.id.textView)
    private TextView mTv;
    @GetViewTo(R.id.button)
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //通过注解生成View；
        getAllAnnotationView();

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTv.append(" ~ ");
            }
        });
    }

    /**
     * 解析注解，获取控件
     */
    private void getAllAnnotationView() {
        //获得成员变量
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                //判断注解
                if (field.getAnnotations() != null) {
                    //确定注解类型
                    if (field.isAnnotationPresent(GetViewTo.class)) {
                        //允许修改反射属性
                        field.setAccessible(true);
                        GetViewTo GetViewTo = field.getAnnotation(GetViewTo.class);
                        //findViewById将注解的id，找到View注入成员变量中
                        field.set(this, findViewById(GetViewTo.value()));
                    }
                }
            } catch (Exception e) {

            }
        }
    }

}
