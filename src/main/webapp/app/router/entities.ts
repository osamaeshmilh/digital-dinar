import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

const Activation = () => import('@/entities/activation/activation.vue');
const ActivationUpdate = () => import('@/entities/activation/activation-update.vue');
const ActivationDetails = () => import('@/entities/activation/activation-details.vue');

const Attachment = () => import('@/entities/attachment/attachment.vue');
const AttachmentUpdate = () => import('@/entities/attachment/attachment-update.vue');
const AttachmentDetails = () => import('@/entities/attachment/attachment-details.vue');

const WalletUser = () => import('@/entities/wallet-user/wallet-user.vue');
const WalletUserUpdate = () => import('@/entities/wallet-user/wallet-user-update.vue');
const WalletUserDetails = () => import('@/entities/wallet-user/wallet-user-details.vue');

const WalletTransaction = () => import('@/entities/wallet-transaction/wallet-transaction.vue');
const WalletTransactionUpdate = () => import('@/entities/wallet-transaction/wallet-transaction-update.vue');
const WalletTransactionDetails = () => import('@/entities/wallet-transaction/wallet-transaction-details.vue');

const Beneficiary = () => import('@/entities/beneficiary/beneficiary.vue');
const BeneficiaryUpdate = () => import('@/entities/beneficiary/beneficiary-update.vue');
const BeneficiaryDetails = () => import('@/entities/beneficiary/beneficiary-details.vue');

const WalletProfile = () => import('@/entities/wallet-profile/wallet-profile.vue');
const WalletProfileUpdate = () => import('@/entities/wallet-profile/wallet-profile-update.vue');
const WalletProfileDetails = () => import('@/entities/wallet-profile/wallet-profile-details.vue');

const PaymentMethod = () => import('@/entities/payment-method/payment-method.vue');
const PaymentMethodUpdate = () => import('@/entities/payment-method/payment-method-update.vue');
const PaymentMethodDetails = () => import('@/entities/payment-method/payment-method-details.vue');

const Notification = () => import('@/entities/notification/notification.vue');
const NotificationUpdate = () => import('@/entities/notification/notification-update.vue');
const NotificationDetails = () => import('@/entities/notification/notification-details.vue');

const ViewLog = () => import('@/entities/view-log/view-log.vue');
const ViewLogUpdate = () => import('@/entities/view-log/view-log-update.vue');
const ViewLogDetails = () => import('@/entities/view-log/view-log-details.vue');

const Setting = () => import('@/entities/setting/setting.vue');
const SettingUpdate = () => import('@/entities/setting/setting-update.vue');
const SettingDetails = () => import('@/entities/setting/setting-details.vue');

const Slider = () => import('@/entities/slider/slider.vue');
const SliderUpdate = () => import('@/entities/slider/slider-update.vue');
const SliderDetails = () => import('@/entities/slider/slider-details.vue');

const Country = () => import('@/entities/country/country.vue');
const CountryUpdate = () => import('@/entities/country/country-update.vue');
const CountryDetails = () => import('@/entities/country/country-details.vue');

const City = () => import('@/entities/city/city.vue');
const CityUpdate = () => import('@/entities/city/city-update.vue');
const CityDetails = () => import('@/entities/city/city-details.vue');

const Category = () => import('@/entities/category/category.vue');
const CategoryUpdate = () => import('@/entities/category/category-update.vue');
const CategoryDetails = () => import('@/entities/category/category-details.vue');

const Transaction = () => import('@/entities/transaction/transaction.vue');
const TransactionUpdate = () => import('@/entities/transaction/transaction-update.vue');
const TransactionDetails = () => import('@/entities/transaction/transaction-details.vue');

const VoucherCompany = () => import('@/entities/voucher-company/voucher-company.vue');
const VoucherCompanyUpdate = () => import('@/entities/voucher-company/voucher-company-update.vue');
const VoucherCompanyDetails = () => import('@/entities/voucher-company/voucher-company-details.vue');

const VoucherType = () => import('@/entities/voucher-type/voucher-type.vue');
const VoucherTypeUpdate = () => import('@/entities/voucher-type/voucher-type-update.vue');
const VoucherTypeDetails = () => import('@/entities/voucher-type/voucher-type-details.vue');

const Voucher = () => import('@/entities/voucher/voucher.vue');
const VoucherUpdate = () => import('@/entities/voucher/voucher-update.vue');
const VoucherDetails = () => import('@/entities/voucher/voucher-details.vue');

const Bank = () => import('@/entities/bank/bank.vue');
const BankUpdate = () => import('@/entities/bank/bank-update.vue');
const BankDetails = () => import('@/entities/bank/bank-details.vue');

const BankBranch = () => import('@/entities/bank-branch/bank-branch.vue');
const BankBranchUpdate = () => import('@/entities/bank-branch/bank-branch-update.vue');
const BankBranchDetails = () => import('@/entities/bank-branch/bank-branch-details.vue');

// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'activation',
      name: 'Activation',
      component: Activation,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'activation/new',
      name: 'ActivationCreate',
      component: ActivationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'activation/:activationId/edit',
      name: 'ActivationEdit',
      component: ActivationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'activation/:activationId/view',
      name: 'ActivationView',
      component: ActivationDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'attachment',
      name: 'Attachment',
      component: Attachment,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'attachment/new',
      name: 'AttachmentCreate',
      component: AttachmentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'attachment/:attachmentId/edit',
      name: 'AttachmentEdit',
      component: AttachmentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'attachment/:attachmentId/view',
      name: 'AttachmentView',
      component: AttachmentDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wallet-user',
      name: 'WalletUser',
      component: WalletUser,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wallet-user/new',
      name: 'WalletUserCreate',
      component: WalletUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wallet-user/:walletUserId/edit',
      name: 'WalletUserEdit',
      component: WalletUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wallet-user/:walletUserId/view',
      name: 'WalletUserView',
      component: WalletUserDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wallet-transaction',
      name: 'WalletTransaction',
      component: WalletTransaction,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wallet-transaction/new',
      name: 'WalletTransactionCreate',
      component: WalletTransactionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wallet-transaction/:walletTransactionId/edit',
      name: 'WalletTransactionEdit',
      component: WalletTransactionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wallet-transaction/:walletTransactionId/view',
      name: 'WalletTransactionView',
      component: WalletTransactionDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'beneficiary',
      name: 'Beneficiary',
      component: Beneficiary,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'beneficiary/new',
      name: 'BeneficiaryCreate',
      component: BeneficiaryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'beneficiary/:beneficiaryId/edit',
      name: 'BeneficiaryEdit',
      component: BeneficiaryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'beneficiary/:beneficiaryId/view',
      name: 'BeneficiaryView',
      component: BeneficiaryDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wallet-profile',
      name: 'WalletProfile',
      component: WalletProfile,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wallet-profile/new',
      name: 'WalletProfileCreate',
      component: WalletProfileUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wallet-profile/:walletProfileId/edit',
      name: 'WalletProfileEdit',
      component: WalletProfileUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'wallet-profile/:walletProfileId/view',
      name: 'WalletProfileView',
      component: WalletProfileDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'payment-method',
      name: 'PaymentMethod',
      component: PaymentMethod,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'payment-method/new',
      name: 'PaymentMethodCreate',
      component: PaymentMethodUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'payment-method/:paymentMethodId/edit',
      name: 'PaymentMethodEdit',
      component: PaymentMethodUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'payment-method/:paymentMethodId/view',
      name: 'PaymentMethodView',
      component: PaymentMethodDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'notification',
      name: 'Notification',
      component: Notification,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'notification/new',
      name: 'NotificationCreate',
      component: NotificationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'notification/:notificationId/edit',
      name: 'NotificationEdit',
      component: NotificationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'notification/:notificationId/view',
      name: 'NotificationView',
      component: NotificationDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'view-log',
      name: 'ViewLog',
      component: ViewLog,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'view-log/new',
      name: 'ViewLogCreate',
      component: ViewLogUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'view-log/:viewLogId/edit',
      name: 'ViewLogEdit',
      component: ViewLogUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'view-log/:viewLogId/view',
      name: 'ViewLogView',
      component: ViewLogDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'setting',
      name: 'Setting',
      component: Setting,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'setting/new',
      name: 'SettingCreate',
      component: SettingUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'setting/:settingId/edit',
      name: 'SettingEdit',
      component: SettingUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'setting/:settingId/view',
      name: 'SettingView',
      component: SettingDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'slider',
      name: 'Slider',
      component: Slider,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'slider/new',
      name: 'SliderCreate',
      component: SliderUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'slider/:sliderId/edit',
      name: 'SliderEdit',
      component: SliderUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'slider/:sliderId/view',
      name: 'SliderView',
      component: SliderDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'country',
      name: 'Country',
      component: Country,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'country/new',
      name: 'CountryCreate',
      component: CountryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'country/:countryId/edit',
      name: 'CountryEdit',
      component: CountryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'country/:countryId/view',
      name: 'CountryView',
      component: CountryDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'city',
      name: 'City',
      component: City,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'city/new',
      name: 'CityCreate',
      component: CityUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'city/:cityId/edit',
      name: 'CityEdit',
      component: CityUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'city/:cityId/view',
      name: 'CityView',
      component: CityDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'category',
      name: 'Category',
      component: Category,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'category/new',
      name: 'CategoryCreate',
      component: CategoryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'category/:categoryId/edit',
      name: 'CategoryEdit',
      component: CategoryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'category/:categoryId/view',
      name: 'CategoryView',
      component: CategoryDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'transaction',
      name: 'Transaction',
      component: Transaction,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'transaction/new',
      name: 'TransactionCreate',
      component: TransactionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'transaction/:transactionId/edit',
      name: 'TransactionEdit',
      component: TransactionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'transaction/:transactionId/view',
      name: 'TransactionView',
      component: TransactionDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'voucher-company',
      name: 'VoucherCompany',
      component: VoucherCompany,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'voucher-company/new',
      name: 'VoucherCompanyCreate',
      component: VoucherCompanyUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'voucher-company/:voucherCompanyId/edit',
      name: 'VoucherCompanyEdit',
      component: VoucherCompanyUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'voucher-company/:voucherCompanyId/view',
      name: 'VoucherCompanyView',
      component: VoucherCompanyDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'voucher-type',
      name: 'VoucherType',
      component: VoucherType,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'voucher-type/new',
      name: 'VoucherTypeCreate',
      component: VoucherTypeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'voucher-type/:voucherTypeId/edit',
      name: 'VoucherTypeEdit',
      component: VoucherTypeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'voucher-type/:voucherTypeId/view',
      name: 'VoucherTypeView',
      component: VoucherTypeDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'voucher',
      name: 'Voucher',
      component: Voucher,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'voucher/new',
      name: 'VoucherCreate',
      component: VoucherUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'voucher/:voucherId/edit',
      name: 'VoucherEdit',
      component: VoucherUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'voucher/:voucherId/view',
      name: 'VoucherView',
      component: VoucherDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'bank',
      name: 'Bank',
      component: Bank,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'bank/new',
      name: 'BankCreate',
      component: BankUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'bank/:bankId/edit',
      name: 'BankEdit',
      component: BankUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'bank/:bankId/view',
      name: 'BankView',
      component: BankDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'bank-branch',
      name: 'BankBranch',
      component: BankBranch,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'bank-branch/new',
      name: 'BankBranchCreate',
      component: BankBranchUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'bank-branch/:bankBranchId/edit',
      name: 'BankBranchEdit',
      component: BankBranchUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'bank-branch/:bankBranchId/view',
      name: 'BankBranchView',
      component: BankBranchDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
