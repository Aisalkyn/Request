package neobis.secondtryofrequestaucarecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerAdapter adapter;
    private Button btnPrev, btnNext;
    Toolbar toolbar;

    int[] list = {R.array.request, -1, -1};
    ArrayList<String> path = new ArrayList<String>();
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new RecyclerAdapter());
        adapter.setItem(getResources().getStringArray(R.array.request));
        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrev.setVisibility(View.INVISIBLE);

    }

    public void onClickNext(View v) {
        try {
            String selectedItem = adapter.getSelected();
            if (counter < list.length) {
                counter++;
                path.add(selectedItem);
            }
            if (counter > 0) {
                btnPrev.setVisibility(View.VISIBLE);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            if (counter == list.length - 1) {
                btnNext.setText("Submit");
            }
            if (counter == list.length) {
                btnNext.setVisibility(View.INVISIBLE);
                btnPrev.setVisibility(View.INVISIBLE);
                Toast.makeText(getBaseContext(), "Thanks for your request! ", Toast.LENGTH_SHORT).show();
                finish();
            }


            adapter.setItem(getArray(selectedItem));
        } catch (NullPointerException e) {
            Toast.makeText(getBaseContext(), "Choose Something", Toast.LENGTH_SHORT).show();
        }
    }

    private String[] getArray(String selected) {
        String[] result = {};
        switch (selected) {
            case "General":
                list[counter] = R.array.general;
                return getResources().getStringArray(R.array.general);
            case "Admission":
                list[counter] = R.array.admission;
                return getResources().getStringArray(R.array.admission);
            case "Finance":
                list[counter] = R.array.finance;
                return getResources().getStringArray(R.array.finance);
            case "Human Resources":
                list[counter] = R.array.human_resources;
                return getResources().getStringArray(R.array.human_resources);
            case "Registrar":
                list[counter] = R.array.registrar;
                return getResources().getStringArray(R.array.registrar);

            case "UIDC":
                list[counter] = R.array.uidc;
                return getResources().getStringArray(R.array.uidc);
            case "Locker":
                list[counter] = R.array.locker;
                return getResources().getStringArray(R.array.locker);
            case "AUCA Cafeterias":
                list[counter] = R.array.auca_cafeterias;
                return getResources().getStringArray(R.array.auca_cafeterias);
            case "Lost and Found":
                return getResources().getStringArray(R.array.lost_Found);
            case "AUCA parking":
                return getResources().getStringArray(R.array.auca_parking);

            case "Freshmen study agreements":
                return getResources().getStringArray(R.array.freshmen_study_agreements);
            case "Student Files":
                return getResources().getStringArray(R.array.student_files);
            case "AUCA Entrance Exams":
                return getResources().getStringArray(R.array.auca_entrance_exams);

            case "Reimbursement":
                return getResources().getStringArray(R.array.reimbursement);
            case "Consultations":
                return getResources().getStringArray(R.array.consultations);
            case "Payment through KICB terminals":
                return getResources().getStringArray(R.array.payment_through_KICB_terminals);
            case "Cash-in terminal":
                return getResources().getStringArray(R.array.cash_in_terminal);
            case "Tuition Payment":
                return getResources().getStringArray(R.array.tuition_payment);

            case "Certificate from work place":
                return getResources().getStringArray(R.array.certificate_from_work_place);
            case "Visa":
                return getResources().getStringArray(R.array.visa);
            case "Work permit (upon arrival)":
                return getResources().getStringArray(R.array.work_permit_upon_arrival);
            case "Other assistance for international employee":
                return getResources().getStringArray(R.array.other_assistance_for_international_employee);
            case "New employee hiring":
                return getResources().getStringArray(R.array.new_employee_hiring);
            case "HR forms":
                return getResources().getStringArray(R.array.hr_forms);

            case "Official Transcript":
                return getResources().getStringArray(R.array.official_Transcript);
            case "Certificate in English":
                return getResources().getStringArray(R.array.certificate_in_English);
            case "Certificate in Russian":
                return getResources().getStringArray(R.array.certificate_in_Russian);
            case "Academic Applications":
                return getResources().getStringArray(R.array.academic_Applications);
            case "Academic Forms":
                return getResources().getStringArray(R.array.academic_Forms);
            case "Room booking":
                return getResources().getStringArray(R.array.room_booking);
            case "Online registration":
                return getResources().getStringArray(R.array.online_registration);

        }
        return result;

    }

    public void onClickBack(View v) {
        String selectedItem = null;
        if(counter == 0){
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        if (counter > 0) {
            counter--;
            selectedItem = path.remove(path.size() - 1);
        }
        if (counter == 0)
            btnPrev.setVisibility(View.INVISIBLE);
        if (counter != list.length - 1)
            btnNext.setText("Next");
        adapter.setItem(getResources().getStringArray(list[counter]));
        if (selectedItem != null)
            adapter.setSelected(selectedItem);

    }

    @Override
    public void onBackPressed() {
        if (counter != 0 && counter != list.length)
            onClickBack(null);
        else
            super.onBackPressed();
    }
}

// general          {R.array.uidc, R.array.locker, R.array.auca_cafeterias, R.array.lost_Found, R.array.auca_parking}
// admission        {R.array.freshmen_study_agreements, R.array.student_files, R.array.auca_entrance_exams}
// finance          {R.array.reimbursement, R.array.consultations, R.array.payment_through_KICB_terminals, R.array.cash_in_terminal, R.array.tuition_payment}
// human resource   {R.array.certificate_from_work_place, R.array.visa, R.array.work_permit_upon_arrival, R.array.other_assistance_for_international_employee, R.array.new_employee_hiring, R.array.hr_forms}
// registrar        {R.array.official_Transcript, R.array.certificate_in_English, R.array. certificate_in_Russian, R.array.academic_Applications, R.array.academic_Forms, R.array.room_booking, R.array.online_registration}