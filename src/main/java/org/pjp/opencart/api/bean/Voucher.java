package org.pjp.opencart.api.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class Voucher {

    private String code;

    private String description;

    @JsonProperty("from_name")
    private String fromName;

    @JsonProperty("from_email")
    private String fromEmail;

    @JsonProperty("to_name")
    private String toName;

    @JsonProperty("to_email")
    private String toEmail;

    @JsonProperty("voucher_theme_id")
    private String voucherThemeId;

    private String message;

    private String price;

    private float amount;

    public Voucher() {
        super();
    }

    public Voucher(String fromName, String fromEmail, String toName, String toEmail, float amount, String code) {
        super();
        this.fromName = fromName;
        this.fromEmail = fromEmail;
        this.toName = toName;
        this.toEmail = toEmail;
        this.amount = amount;
        this.code = code;

        Preconditions.checkArgument(!Strings.isNullOrEmpty(fromName) && (fromName.length() < 65), "Your Name must be between 1 and 64 characters!");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(fromEmail) &&  fromEmail.matches("^(.+)@(\\S+)$"), "E-Mail Address does not appear to be valid!");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(toName) && (toName.length() < 65), "Recipient's Name must be between 1 and 64 characters!");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(toEmail) &&  toEmail.matches("^(.+)@(\\S+)$"), "E-Mail Address does not appear to be valid!");
        Preconditions.checkArgument(amount > 0 && amount <= 1_000, "Amount must be between $1.00 and $1,000.00!");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getVoucherThemeId() {
        return voucherThemeId;
    }

    public void setVoucherThemeId(String voucherThemeId) {
        this.voucherThemeId = voucherThemeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Voucher [code=" + code + ", description=" + description + ", fromName=" + fromName + ", fromEmail="
                + fromEmail + ", toName=" + toName + ", toEmail=" + toEmail + ", voucherThemeId=" + voucherThemeId
                + ", message=" + message + ", price=" + price + ", amount=" + amount + "]";
    }

}
