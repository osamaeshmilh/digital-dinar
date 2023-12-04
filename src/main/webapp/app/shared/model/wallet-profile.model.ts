export interface IWalletProfile {
  id?: number;
  nameAr?: string | null;
  nameEn?: string | null;
  hasTransferAmountLimitPerTransaction?: boolean | null;
  transferAmountLimitPerTransaction?: number | null;
  hasDailyTransferAmountLimit?: boolean | null;
  dailyTransferAmountLimit?: number | null;
  canTransferToConsumerWallet?: boolean | null;
  canTransferToMerchantWallet?: boolean | null;
  canTransferToConsumer?: boolean | null;
  canTransferToBank?: boolean | null;
  canBuyVouchers?: boolean | null;
  transferToConsumerWalletFees?: boolean | null;
  transferToMerchantWalletFees?: boolean | null;
  transferToConsumerFees?: boolean | null;
  transferToBankFees?: boolean | null;
  buyVouchersFees?: boolean | null;
}

export class WalletProfile implements IWalletProfile {
  constructor(
    public id?: number,
    public nameAr?: string | null,
    public nameEn?: string | null,
    public hasTransferAmountLimitPerTransaction?: boolean | null,
    public transferAmountLimitPerTransaction?: number | null,
    public hasDailyTransferAmountLimit?: boolean | null,
    public dailyTransferAmountLimit?: number | null,
    public canTransferToConsumerWallet?: boolean | null,
    public canTransferToMerchantWallet?: boolean | null,
    public canTransferToConsumer?: boolean | null,
    public canTransferToBank?: boolean | null,
    public canBuyVouchers?: boolean | null,
    public transferToConsumerWalletFees?: boolean | null,
    public transferToMerchantWalletFees?: boolean | null,
    public transferToConsumerFees?: boolean | null,
    public transferToBankFees?: boolean | null,
    public buyVouchersFees?: boolean | null,
  ) {
    this.hasTransferAmountLimitPerTransaction = this.hasTransferAmountLimitPerTransaction ?? false;
    this.hasDailyTransferAmountLimit = this.hasDailyTransferAmountLimit ?? false;
    this.canTransferToConsumerWallet = this.canTransferToConsumerWallet ?? false;
    this.canTransferToMerchantWallet = this.canTransferToMerchantWallet ?? false;
    this.canTransferToConsumer = this.canTransferToConsumer ?? false;
    this.canTransferToBank = this.canTransferToBank ?? false;
    this.canBuyVouchers = this.canBuyVouchers ?? false;
    this.transferToConsumerWalletFees = this.transferToConsumerWalletFees ?? false;
    this.transferToMerchantWalletFees = this.transferToMerchantWalletFees ?? false;
    this.transferToConsumerFees = this.transferToConsumerFees ?? false;
    this.transferToBankFees = this.transferToBankFees ?? false;
    this.buyVouchersFees = this.buyVouchersFees ?? false;
  }
}
