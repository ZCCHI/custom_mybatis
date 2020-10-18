package com.risen.utils;

public class ParameterMapping {

    //#{} ${} 参数名称
    private String content;

    public ParameterMapping(String content){
        this.content =content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
