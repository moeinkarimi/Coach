package Model;

import android.app.Activity;

import java.util.Random;

public class GenerateCode {


    Activity a;
    private DBHandler dbHandler;
    private int gameCode, groupCode1, groupCode2 , sum;
    private String seperate;

    public String GenerateCode(int GameId,String GroupCode, String Score){
        if (GroupCode.length()!=2){
            return "لطفا کد دو رقمی را وارد نمایید";
        }
        Random rand = new Random();
        int n = rand.nextInt(10);
        groupCode1 = Integer.parseInt(GroupCode.substring(0,1));
        groupCode2 = Integer.parseInt(GroupCode.substring(1,2));
        sum = n + groupCode1;
        if (sum >9){
            seperate = String.valueOf(sum).substring(1,2);
            groupCode2++;
            GroupCode = String.valueOf(n) + seperate + String.valueOf(groupCode2);
        }
        else {
            GroupCode = String.valueOf(n) + String.valueOf(sum) + String.valueOf(groupCode2);
        }

        return GroupCode + String.valueOf(GameId) + ConvertCodeToScore(Score) ;
    }

    public String ConvertCodeToScore(String gameScore){
        return Integer.toString(Integer.parseInt(gameScore, 10), 6);
    }

    public String ConvertBinaryToDesimal(String gameScore){

        return Integer.toString(Integer.parseInt(gameScore, 2), 10);
    }

    public String GenerateGameTime(int GameId,String GroupCode, String GameTime){
        if (GroupCode.length()!=2){
            return "لطفا کد دو رقمی را وارد نمایید";
        }
        Random rand = new Random();
        int n = rand.nextInt(10);
        groupCode1 = Integer.parseInt(GroupCode.substring(0,1));
        groupCode2 = Integer.parseInt(GroupCode.substring(1,2));
        sum = n + groupCode1;
        if (sum >9){
            seperate = String.valueOf(sum).substring(1,2);
            groupCode2++;
            GroupCode = String.valueOf(n) + seperate + String.valueOf(groupCode2);
        }
        else {
            GroupCode = String.valueOf(n) + String.valueOf(sum) + String.valueOf(groupCode2);
        }

        return GroupCode + String.valueOf(GameId) + (GameTime) ;
    }

}
