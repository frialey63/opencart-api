package org.pjp.opencart.api.bean;

import org.pjp.opencart.api.bean.payment.COD;
import org.pjp.opencart.api.bean.payment.FreeCheckout;
import org.pjp.opencart.api.bean.payment.PPStandard;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentMethodsWrapper extends Result {

    public static class PaymentMethods {

        @JsonProperty("pp_standard")
        private PPStandard ppStandard;

        private COD cod;

        @JsonProperty("free_checkout")
        private FreeCheckout freeCheckout;

        public PaymentMethods() {
            super();
        }

        public PPStandard getPpStandard() {
            return ppStandard;
        }

        public void setPpStandard(PPStandard ppStandard) {
            this.ppStandard = ppStandard;
        }

        public COD getCod() {
            return cod;
        }

        public void setCod(COD cod) {
            this.cod = cod;
        }

        public FreeCheckout getFreeCheckout() {
            return freeCheckout;
        }

        public void setFreeCheckout(FreeCheckout freeCheckout) {
            this.freeCheckout = freeCheckout;
        }

        @Override
        public String toString() {
            return "PaymentMethods [ppStandard=" + ppStandard + ", cod=" + cod + ", freeCheckout=" + freeCheckout + "]";
        }

    }

    @JsonProperty("payment_methods")
    private PaymentMethods paymentMethods;

    public PaymentMethodsWrapper() {
        super();
        success = "Success";
    }

    public PaymentMethods getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(PaymentMethods paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    @Override
    public String toString() {
        return "PaymentMethodsWrapper [paymentMethods=" + paymentMethods + "]";
    }

}
