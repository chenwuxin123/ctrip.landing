package com.meipiao.ctrip.entity.response.rate;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/9 17:05
 */
public class DailyPrices {

    /// <summary>
    /// 早餐
    /// </summary>
    private Integer breakfast ;

    /// <summary>
    /// 午餐
    /// </summary>
    private Integer lunch ;

    /// <summary>
    /// 晚餐
    /// </summary>
    private Integer dinner ;

    /// <summary>
    /// 展示价
    /// </summary>
    private Double displayCurrency;

    /// <summary>
    /// 币种
    /// </summary>
    private String currency;

    /// <summary>
    /// 价格生效日期
    /// </summary>
    private String effectiveDate;

    public Integer getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Integer breakfast) {
        this.breakfast = breakfast;
    }

    public Integer getLunch() {
        return lunch;
    }

    public void setLunch(Integer lunch) {
        this.lunch = lunch;
    }

    public Integer getDinner() {
        return dinner;
    }

    public void setDinner(Integer dinner) {
        this.dinner = dinner;
    }

    public Double getDisplayCurrency() {
        return displayCurrency;
    }

    public void setDisplayCurrency(Double displayCurrency) {
        this.displayCurrency = displayCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public String toString() {
        return "DailyPrices{" +
                "breakfast=" + breakfast +
                ", lunch=" + lunch +
                ", dinner=" + dinner +
                ", displayCurrency=" + displayCurrency +
                ", currency='" + currency + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                '}';
    }
}
