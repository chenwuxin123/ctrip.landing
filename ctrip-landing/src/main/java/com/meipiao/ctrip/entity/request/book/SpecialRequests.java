package com.meipiao.ctrip.entity.request.book;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/9 13:17
 */
public class SpecialRequests {
    private String Text;

    private String Name;

    private String ParagraphNumber;

    private String RequestCode;

    private String CodeContext;

    public void setText(String Text){
        this.Text = Text;
    }
    public String getText(){
        return this.Text;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public String getName(){
        return this.Name;
    }
    public void setParagraphNumber(String ParagraphNumber){
        this.ParagraphNumber = ParagraphNumber;
    }
    public String getParagraphNumber(){
        return this.ParagraphNumber;
    }
    public void setRequestCode(String RequestCode){
        this.RequestCode = RequestCode;
    }
    public String getRequestCode(){
        return this.RequestCode;
    }
    public void setCodeContext(String CodeContext){
        this.CodeContext = CodeContext;
    }
    public String getCodeContext(){
        return this.CodeContext;
    }
}
