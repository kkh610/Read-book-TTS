package halla.icsw.kkh2_1112;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class DetailActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private Intent intent;
    private TextView textView;
    private TextView textView2;

    String read;

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        tts = new TextToSpeech(this, this);

        // MainActivity에서 보낸 imgRes를 받기위해 getIntent()로 초기화
        intent = getIntent();

        // "imgRes" key로 받은 값은 int 형이기 때문에 getIntExtra(key, defaultValue);
        // 받는 값이 String 형이면 getStringExtra(key);
        textView.setText(intent.getStringExtra("titleRes"));

        //tts
        String str = intent.getStringExtra("titleRes");

        if (textView.toString() != null) {
            if (str.equals("국화")) {
                textView2.setText("안녕하세요안녕하세요");
            } else if (str.equals("사막")) {
                textView2.setText("ㅎㅎㅎㅎㅎㅎㅎㅎ");
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // Stop the Bluetooth chat services
        if (tts != null) tts.shutdown();
    }

    public void speak(String str){
        tts.speak(textView2.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
    }
    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS)
        {
            Locale locale = Locale.getDefault();
            if(tts.isLanguageAvailable(locale)>=TextToSpeech.LANG_AVAILABLE)
                tts.setLanguage(locale);
            else
                Toast.makeText(this, "지원하지 않는 오류", Toast.LENGTH_SHORT).show();
        }
        else if (status == TextToSpeech.ERROR) {
            Toast.makeText(this, "음성 합성 초기화 오류", Toast.LENGTH_SHORT).show();
        }

        speak(textView2.getText().toString());

    }
}
