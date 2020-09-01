package com.google.myapplication.activity;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.myapplication.R;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.ui.Design;

import androidx.appcompat.app.AppCompatDialog;

public class CardDialog extends AppCompatDialog implements View.OnClickListener {
    MainData.Organ organ;
    MainData.Post.Card card;
    Holder holder;
    public CardDialog(Context context,MainData.Organ organ,MainData.Post.Card card) {
        super(context, R.style.DialogStyle);
        setContentView(R.layout.dialog_card_by_card);
        this.organ = organ;
        this.card = card;
        holder = new Holder();
       }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_conf:
                dismiss();
                break;
        }
    }
    class Holder{
        TextInputEditText ti_card_num,ti_cvv2,ti_pass;
        MaterialButton btn_confirm;
        ImageView iv;
        TextView tv_name_o,tv_card_name,tv_card_num;
        Holder(){
            btn_confirm = findViewById(R.id.btn_conf);
            ti_card_num = findViewById(R.id.edit_text_card_num);
            ti_cvv2 = findViewById(R.id.edit_cvv2);
            ti_pass = findViewById(R.id.edit_pass);
            iv = findViewById(R.id.image_o);
            tv_name_o = findViewById(R.id.text_name_o);
            tv_card_name = findViewById(R.id.text_card_name);
            tv_card_num = findViewById(R.id.text_card_num);
            initHolder();
        }

        private void initHolder() {
            Design.showImage(getContext(),iv,organ.getUri_o(),iv,R.drawable.person,null);
            tv_name_o.setText(organ.getName_o());
            tv_card_name.setText(card.getName());
            tv_card_num.setText(makeCardNum(card.getCard_num()));
            ti_cvv2.addTextChangedListener(new TextWatcher(TextWatcher.CVV2));
            ti_pass.addTextChangedListener(new TextWatcher(TextWatcher.PASS));
            btn_confirm.setOnClickListener(CardDialog.this);
            ti_card_num.addTextChangedListener(new TextWatcher(TextWatcher.CARD_NUM));
            ti_card_num.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                    if (keyCode == KeyEvent.KEYCODE_DEL && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                        int l = ti_card_num.getText().toString().trim().length();
                        Log.i("data_text"," length = "+l);
                        switch (l){
                            case 15:
                            case 10:
                            case 5:
                                String str = ti_card_num.getText().toString();
                                str = str.substring(0,str.length()-2);
                                ti_card_num.setText(str);
                                ti_card_num.setSelection(l-2);
                                return true;
                        }
                    }
                    return false;
                }
            });
        }

        private String makeCardNum(String num) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                sb.append(num.substring(0,4));
                sb.append('-');
                num = num.substring(4);
            }
            sb.append(num);
            return sb.toString();
        }

        class TextWatcher implements android.text.TextWatcher{
            int type;
            static final int CARD_NUM = 0;
            static final int CVV2 = 1;
            static final int PASS = 2;
            TextWatcher(int type){
                this.type = type;
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                switch (type){
                    case CARD_NUM:
                        int state = i1 - i2;
                        int l = charSequence.toString().trim().length();
                        switch (l){
                            case 14:
                            case 9:
                            case 4:
                                ti_card_num.setText(charSequence + "-");
                                ti_card_num.setSelection(l+1);
                                break;
                        }
                        if (l == 19){
                            ti_card_num.clearFocus();
                            ti_cvv2.requestFocus();
                        }
                        break;
                    case CVV2:
                        if (charSequence.length() == 4){
                            if (ti_pass.getText().toString().isEmpty() || ti_pass.getText().toString().trim().length() < 8){
                                ti_cvv2.clearFocus();
                                ti_pass.requestFocus();
                            }
                        }
                        break;
                    case PASS:
                        if (charSequence.length() == 12){
                            ti_pass.clearFocus();
                            ti_card_num.clearFocus();
                            btn_confirm.requestFocus();
                        }
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        }
    }
}
