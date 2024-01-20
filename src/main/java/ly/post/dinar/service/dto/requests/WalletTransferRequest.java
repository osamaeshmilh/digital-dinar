package ly.post.dinar.service.dto.requests;

public class WalletTransferRequest {

    private String walletPublicKey;
    private String walletMobileNo;
    private Float amount;
    private String otp;
    private String notes;
    private Long beneficiaryId;

    public String getWalletPublicKey() {
        return walletPublicKey;
    }

    public void setWalletPublicKey(String walletPublicKey) {
        this.walletPublicKey = walletPublicKey;
    }

    public String getWalletMobileNo() {
        return walletMobileNo;
    }

    public void setWalletMobileNo(String walletMobileNo) {
        this.walletMobileNo = walletMobileNo;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return (
            "WalletTransferRequest{" +
            "walletPublicKey='" +
            walletPublicKey +
            '\'' +
            ", walletMobileNo='" +
            walletMobileNo +
            '\'' +
            ", amount=" +
            amount +
            ", otp='" +
            otp +
            '\'' +
            ", notes='" +
            notes +
            '\'' +
            ", beneficiaryId=" +
            beneficiaryId +
            '}'
        );
    }
}
