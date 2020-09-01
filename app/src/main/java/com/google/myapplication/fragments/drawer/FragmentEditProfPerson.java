package com.google.myapplication.fragments.drawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentEditProfPerson extends Fragment {
    MaterialButton btn_edit,btn_plus,btn_conf;
    SwitchMaterial swh_pass;
    TextInputEditText edit_pass,edit_pass_again;
    TextInputLayout tl_pass,tl_pass_again;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.edit_prof,container,false);
        init(view);
        return view;
    }

    private void init(View view) {
        btn_edit = view.findViewById(R.id.btn_edit);
        btn_plus = view.findViewById(R.id.btn_plus);
        swh_pass = view.findViewById(R.id.swh_pass);
        edit_pass = view.findViewById(R.id.edit_pass);
        edit_pass_again = view.findViewById(R.id.edit_pass_again);
        tl_pass = view.findViewById(R.id.tl_pass);
        tl_pass_again = view.findViewById(R.id.tl_pass_again);
        swh_pass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    tl_pass.setEnabled(b);
                    tl_pass_again.setEnabled(b);
            }
        });
    }
}
