import { defineComponent, provide } from 'vue';

import ActivationService from './activation/activation.service';
import AttachmentService from './attachment/attachment.service';
import MerchantService from './merchant/merchant.service';
import ConsumerService from './consumer/consumer.service';
import WalletTransactionService from './wallet-transaction/wallet-transaction.service';
import BeneficiaryService from './beneficiary/beneficiary.service';
import WalletProfileService from './wallet-profile/wallet-profile.service';
import PaymentMethodService from './payment-method/payment-method.service';
import NotificationService from './notification/notification.service';
import ViewLogService from './view-log/view-log.service';
import SettingService from './setting/setting.service';
import SliderService from './slider/slider.service';
import CountryService from './country/country.service';
import CityService from './city/city.service';
import CategoryService from './category/category.service';
import TransactionService from './transaction/transaction.service';
import VoucherCompanyService from './voucher-company/voucher-company.service';
import VoucherTypeService from './voucher-type/voucher-type.service';
import VoucherService from './voucher/voucher.service';
import UserService from '@/entities/user/user.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Entities',
  setup() {
    provide('userService', () => new UserService());
    provide('activationService', () => new ActivationService());
    provide('attachmentService', () => new AttachmentService());
    provide('merchantService', () => new MerchantService());
    provide('consumerService', () => new ConsumerService());
    provide('walletTransactionService', () => new WalletTransactionService());
    provide('beneficiaryService', () => new BeneficiaryService());
    provide('walletProfileService', () => new WalletProfileService());
    provide('paymentMethodService', () => new PaymentMethodService());
    provide('notificationService', () => new NotificationService());
    provide('viewLogService', () => new ViewLogService());
    provide('settingService', () => new SettingService());
    provide('sliderService', () => new SliderService());
    provide('countryService', () => new CountryService());
    provide('cityService', () => new CityService());
    provide('categoryService', () => new CategoryService());
    provide('transactionService', () => new TransactionService());
    provide('voucherCompanyService', () => new VoucherCompanyService());
    provide('voucherTypeService', () => new VoucherTypeService());
    provide('voucherService', () => new VoucherService());
    // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
  },
});
