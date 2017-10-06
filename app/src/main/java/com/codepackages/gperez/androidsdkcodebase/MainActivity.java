package com.codepackages.gperez.androidsdkcodebase;

import android.content.Context;
import android.content.DialogInterface;
import android.net.sip.SipAudioCall;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//LIVE PERSON LIBRARIES AND IMPORTS
import com.liveperson.infra.ConversationViewParams;
import com.liveperson.infra.ICallback;
import com.liveperson.infra.Infra;
import com.liveperson.infra.InitLivePersonProperties;
import com.liveperson.infra.LPAuthenticationParams;
import com.liveperson.infra.callbacks.InitLivePersonCallBack;
import com.liveperson.messaging.sdk.api.callbacks.LogoutLivePersonCallback;
import com.liveperson.messaging.sdk.api.LivePerson;
import com.liveperson.messaging.sdk.api.model.ConsumerProfile;

public class MainActivity extends AppCompatActivity {


    public Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn  = (Button) findViewById(R.id.btnStartConversation);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String brandID = "48719195", appID = "myapp.id.com";

                LivePerson.initialize(MainActivity.this, new InitLivePersonProperties(brandID, appID, new InitLivePersonCallBack() {

                    @Override
                    public void onInitSucceed() {
                        Toast.makeText(MainActivity.this, "Conversation started", Toast.LENGTH_SHORT).show();

                        String mNickName= "myNickName", mFirstName = "myFirstName", mLastName = "myLastName", mPhoneNumber = "1234567890";
                        String authKey = "";

                        ConsumerProfile consumerProfile = new ConsumerProfile.Builder()
                                .setFirstName(mFirstName)
                                .setLastName(mLastName)
                                .setNickname(mNickName)
                                .setPhoneNumber(mPhoneNumber)
                                .build();

                        LivePerson.setUserProfile(consumerProfile);

                        LivePerson.showConversation(MainActivity.this, new LPAuthenticationParams(), new ConversationViewParams(false));

                    }

                    @Override
                    public void onInitFailed(Exception e) {
                        Toast.makeText(MainActivity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                    }
                }));

            }

        });

    }

}
