package com.example.demo.entity;

import com.example.demo.entity.bo.BaseMessage;

public class NoticeOutput {
    private boolean isCheck;// da cham xong
    private Double mark;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }
}
