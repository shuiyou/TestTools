package com.fund.bean;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
 * Created by yimeiweile on 2017/6/21.
 */
public class FundMemberInvestorUpdateBean {
    private String member_id = "";
    private String address_info = "";
    private String occupation = "";
    private String duties = "";
    private String income_source = "";
    private String assets_debt_info = "";
    private String zip_code = "";
    private String email = "";
    private String beneficiary = "";
    private String operator = "";
    private String encodeString = "";

    public String getMember_id() {
        return member_id;
    }

    public String getAddress_info() {
        return address_info;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getDuties() {
        return duties;
    }

    public String getIncome_source() {
        return income_source;
    }

    public String getAssets_debt_info() {
        return assets_debt_info;
    }

    public String getZip_code() {
        return zip_code;
    }

    public String getEmail() {
        return email;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public String getOperator() {
        return operator;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public FundMemberInvestorUpdateBean setMember_id(String member_id) {
        this.member_id = member_id;
        return this;
    }

    public FundMemberInvestorUpdateBean setAddress_info(String address_info) {
        this.address_info = address_info;
        return this;
    }

    public FundMemberInvestorUpdateBean setOccupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public FundMemberInvestorUpdateBean setDuties(String duties) {
        this.duties = duties;
        return this;
    }

    public FundMemberInvestorUpdateBean setIncome_source(String income_source) {
        this.income_source = income_source;
        return this;
    }

    public FundMemberInvestorUpdateBean setAssets_debt_info(String assets_debt_info) {
        this.assets_debt_info = assets_debt_info;
        return this;
    }

    public FundMemberInvestorUpdateBean setZip_code(String zip_code) {
        this.zip_code = zip_code;
        return this;
    }

    public FundMemberInvestorUpdateBean setEmail(String email) {
        this.email = email;
        return this;
    }

    public FundMemberInvestorUpdateBean setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
        return this;
    }

    public FundMemberInvestorUpdateBean setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public FundMemberInvestorUpdateBean setEncodeString(String encodeString) {
        this.encodeString = encodeString;
        return this;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
