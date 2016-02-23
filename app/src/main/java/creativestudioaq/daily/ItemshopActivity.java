package creativestudioaq.daily;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.supersonic.mediationsdk.logger.SupersonicError;
import com.supersonic.mediationsdk.model.Placement;
import com.supersonic.mediationsdk.sdk.RewardedVideoListener;
import com.supersonic.mediationsdk.sdk.Supersonic;
import com.supersonic.mediationsdk.sdk.SupersonicFactory;

/**
 * Created by honggyu on 2016-01-06.
 */
public class ItemshopActivity extends BaseActivity {

    SharedPreferences settingshared;
    SharedPreferences.Editor editor;
    MainActivity Aactivity;

    private Supersonic mMediationAgent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemshop);
       Button back = (Button) findViewById(R.id.back);

        Button setting = (Button) findViewById(R.id.buttonsetting);
        Button button = (Button)findViewById(R.id.button);
        Button button1 = (Button)findViewById(R.id.button1);
        Button button2= (Button)findViewById(R.id.button2);
        TextView name1 = (TextView)findViewById(R.id.name1);
        TextView name2 = (TextView)findViewById(R.id.name2);
        TextView name3 = (TextView)findViewById(R.id.name3);
        TextView subtext = (TextView)findViewById(R.id.subtext);


        subtext.setText("유료 버전 출시를 기대해주세요~!");


        settingshared = getSharedPreferences("setting", 0);
        int lv = settingshared.getInt("level",0);
        int speciallv = settingshared.getInt("speciallevel",0);

        Aactivity = MainActivity.Aactivity;



        RewardedVideoListener mRewardedVideoListener = new RewardedVideoListener()
        {
            //Invoked when initialization of RewardedVideo has finished successfully.
            @Override
            public void onRewardedVideoInitSuccess() {
            }
            //Invoked when RewardedVideo initialization process has failed.
            //SupersonicError contains the reason for the failure.
            @Override
            public void onRewardedVideoInitFail(SupersonicError se) {

                //Retrieve details from a SupersonicError object.
                int errorCode =  se.getErrorCode();
                String errorMessage = se.getErrorMessage();
                if (errorCode == SupersonicError.ERROR_CODE_GENERIC){
                    //Write a Handler for specific error's.
                }
            }

            //Invoked when RewardedVideo call to show a rewarded video has failed
            //SupersonicError contains the reason for the failure.
            @Override
            public void onRewardedVideoShowFail(SupersonicError se) {
            }
            //Invoked when the RewardedVideo ad view has opened.
            //Your Activity will lose focus. Please avoid performing heavy
            //tasks till the video ad will be closed.
            @Override
            public void onRewardedVideoAdOpened() {
            }
            //Invoked when the RewardedVideo ad view is about to be closed.
            //Your activity will now regain its focus.
            @Override
            public void onRewardedVideoAdClosed() {
            }
            //Invoked when there is a change in the ad availability status.
            //@param - available - value will change to true when rewarded videos are available.
            //You can then show the video by calling showRewardedVideo().
            //Value will change to false when no videos are available.
            @Override
            public void onVideoAvailabilityChanged(boolean available) {
                //Change the in-app 'Traffic Driver' state according to availability.
            }
            //Invoked when the video ad starts playing.
            @Override
            public void onVideoStart() {
            }
            //Invoked when the video ad finishes playing.
            @Override
            public void onVideoEnd() {
            }
            //Invoked when the user completed the video and should be rewarded.
            //If using server-to-server callbacks you may ignore this events and wait for
            //the callback from the Supersonic server.
            //@param - placement - the Placement the user completed a video from.
            @Override
            public void onRewardedVideoAdRewarded(Placement placement) {
                //TODO - here you can reward the user according to the given amount.
                String rewardName = placement.getRewardName();
                int rewardAmount = placement.getRewardAmount();

                editor = settingshared.edit();
                editor.putInt("speciallevel", rewardAmount);
                editor.apply();


            }
        };



        //Get the mediation publisher instance
        mMediationAgent = SupersonicFactory.getInstance();
        //Set the Rewarded Video Listener
        mMediationAgent.setRewardedVideoListener(mRewardedVideoListener);
        //Set the unique id of your end user.
        String mUserId = "hg1771";
        //Set the Application Key - can be retrieved from Supersonic platform
        String mAppKey = "44669ff5";
        //Init Rewarded Video
        mMediationAgent.initRewardedVideo(this, mAppKey, mUserId);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.button:
                        mMediationAgent.showRewardedVideo("Quests");
                        String resName1 = "@drawable/itemimg_16_1";
                        int resID1 = getResources().getIdentifier(resName1, "drawable", getPackageName());
                        Picasso.with(ItemshopActivity.this).load(resID1).into(Aactivity.itemimage);
                        finish();
                        break;
                    case R.id.button1:
                        mMediationAgent.showRewardedVideo("Quests2");
                        String resName2 = "@drawable/itemimg_17_1";
                        int resID2 = getResources().getIdentifier(resName2, "drawable", getPackageName());
                        Picasso.with(ItemshopActivity.this).load(resID2).into(Aactivity.itemimage);
                        finish();
                        break;
                    case R.id.button2:
                        mMediationAgent.showRewardedVideo("Quests3");
                        String resName3 = "@drawable/itemimg_18_1";
                        int resID3 = getResources().getIdentifier(resName3, "drawable", getPackageName());
                        Picasso.with(ItemshopActivity.this).load(resID3).into(Aactivity.itemimage);
                        finish();
                        break;

                    case R.id.back:
                        finish();
                        break;


                    case R.id.buttonsetting:
                        Intent intent5 = new Intent(ItemshopActivity.this, SettingActivity.class);
                        startActivity(intent5);
                        break;
                }
            }
        };
        back.setOnClickListener(click);
        setting.setOnClickListener(click);
        button.setOnClickListener(click);
        button1.setOnClickListener(click);
        button2.setOnClickListener(click);

        if(lv>=15) {

            if (speciallv == 0) {
                button1.setClickable(false);
                button2.setClickable(false);
                name2.setBackgroundResource(R.drawable.lvblock17);
                name3.setBackgroundResource(R.drawable.lvblock18);
                name1.setText("유료 버전 아이템1");


            } else if (speciallv == 1) {
                name1.setText("유료 버전 아이템1");
                name2.setText("유료 버전 아이템2");
                button2.setClickable(false);
                name3.setBackgroundResource(R.drawable.lvblock18);

            } else{
                name1.setText("유료 버전 아이템1");
                name2.setText("유료 버전 아이템2");
                name3.setText("유료 버전 아이템3");
            }
        }else{
            button.setClickable(false);
            button1.setClickable(false);
            button2.setClickable(false);
            name1.setBackgroundResource(R.drawable.lvblock16);
            name2.setBackgroundResource(R.drawable.lvblock17);
            name3.setBackgroundResource(R.drawable.lvblock18);
        }

    }
    protected void onResume() {
        super.onResume();
        if (mMediationAgent != null) {
            mMediationAgent.onResume (this);
        }
    }
    protected void onPause() {
        super.onPause();
        if (mMediationAgent != null) {
            mMediationAgent.onPause(this);
        }
    }
}
