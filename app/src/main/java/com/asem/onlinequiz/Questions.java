package com.asem.onlinequiz;

import android.os.Parcel;
import android.os.Parcelable;

public class Questions {

    public Questions() {}
    String Ques, QuesNum, option1, option2,option3,option4 , ans;

    public String getQues() {
        return Ques;
    }

    public void setQues(String ques) {
        Ques = ques;
    }

    public String getQuesNum() {
        return QuesNum;
    }

    public void setQuesNum(String quesNum) {
        QuesNum = quesNum;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public Questions(String ques, String quesNum, String option1, String option2, String option3, String option4, String ans) {
        Ques = ques;
        QuesNum = quesNum;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.ans = ans;
    }


    protected Questions(Parcel in) {
        Ques = in.readString();
        QuesNum = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        ans = in.readString();
    }
    public static final Parcelable.Creator<Questions> CREATOR = new Parcelable.Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };

   public int describeContents(){
       return 0;
   }


    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Ques);
        parcel.writeString(QuesNum);
        parcel.writeString(option1);
        parcel.writeString(option2);
        parcel.writeString(option3);
        parcel.writeString(option4);
        parcel.writeString(ans);
    }
}
