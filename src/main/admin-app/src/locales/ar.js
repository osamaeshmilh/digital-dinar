export default {
  navigation: {
    reports: 'تقارير',
    transactions: 'المعاملات المالية',
    merchants: 'التجار',
    paymentMethods: 'طرق الدفع',
    sliders: 'الإعلانات',
    beneficiaries: 'المستفيدون',
    wallet: 'محفظة',
    wallets: 'المحافظ',
    bank: 'مصرف',
    bankBranch: 'فرع مصرف',
    bankBranches: 'فروع المصارف',
    vouchers: 'الكروت',
    voucherCompanies: 'شركات الكروت',
    voucherTypes: 'انواع الكروت',
    banks: 'المصارف',
    notifications: 'الاشعارات',
    walletProfiles: 'خصائص المحافظ',
    walletUsers: 'مستخدمي المحافظ',
    cities: 'المدن',
    countries: 'الدول',
    categories: 'التصنيفات',
    id: 'ر.ت',
  },
  attributes: {
    username: 'اسم المستخدم',
    password: 'كلمة المرور',

    activation: {
      id: 'الرقم',
      mobileNo: 'رقم الجوال',
      email: 'البريد الإلكتروني',
      code: 'الكود',
      sentOn: 'تم الإرسال في',
      validUntil: 'صالح حتى',
      isUsed: 'تم الاستخدام',
      createdBy: 'تم الإنشاء بواسطة',
      createdDate: 'تاريخ الإنشاء',
      lastModifiedBy: 'آخر تعديل بواسطة',
      lastModifiedDate: 'آخر تاريخ تعديل',
    },

    attachment: {
      id: 'الرقم',
      name: 'الاسم',
      details: 'التفاصيل',
      notes: 'ملاحظات',
      attachmentType: 'نوع المرفق',
      file: 'الملف',
      fileUrl: 'رابط الملف',
      referenceType: 'نوع الإشارة',
      referenceId: 'رقم الإشارة',
      createdBy: 'تم الإنشاء بواسطة',
      createdDate: 'تاريخ الإنشاء',
      lastModifiedBy: 'آخر تعديل بواسطة',
      lastModifiedDate: 'آخر تاريخ تعديل',
    },

    attachmentType: {
      null: '',
      COMMERCIAL_LICENCE: 'رخصة تجارية',
      COMMERCIAL_REGISTRY: 'سجل تجاري',
      DRIVING_LICENCE: 'رخصة قيادة',
      FACE: 'وجه',
      FACE_WITH_ID: 'وجه مع هوية',
      ID: 'هوية',
      PASSPORT: 'جواز سفر',
    },

    bank: {
      nameAr: 'الاسم (بالعربية)',
      nameEn: 'الاسم (بالإنجليزية)',
      swiftCode: 'كود السويفت',
    },

    bankBranch: {
      nameAr: 'الاسم (بالعربية)',
      nameEn: 'الاسم (بالإنجليزية)',
      address: 'العنوان',
      bank: 'المصرف',
    },

    beneficiary: {
      arabicFirstName: 'الاسم الأول بالعربية',
      arabicLastName: 'الاسم الأخير بالعربية',
      englishFirstName: 'الاسم الأول بالإنجليزية',
      englishLastName: 'الاسم الأخير بالإنجليزية',
      bankAccountName: 'اسم الحساب البنكي',
      bankAccountNumber: 'رقم الحساب البنكي',
      idType: 'نوع الهوية',
      idNo: 'رقم الهوية',
      mobileNo: 'رقم الهاتف',
      email: 'البريد الإلكتروني',
      notes: 'ملاحظات',
      isVerified: 'تم التحقق',
      hasTransferred: 'تم التحويل',
    },

    category: {
      nameAr: 'الاسم (بالعربية)',
      nameEn: 'الاسم (بالإنجليزية)',
      code: 'الكود',
    },

    city: {
      nameAr: 'الاسم (بالعربية)',
      nameEn: 'الاسم (بالإنجليزية)',
    },

    country: {
      nameAr: 'الاسم (بالعربية)',
      nameEn: 'الاسم (بالإنجليزية)',
      iso2: 'ISO2',
      iso3: 'ISO3',
      isoNo: 'ISO',
    },

    gender: {
      FEMALE: 'أنثى',
      MALE: 'ذكر',
    },

    idType: {
      DRIVING_LICENCE: 'رخصة القيادة',
      ID: 'هوية',
      PASSPORT: 'جواز السفر',
    },

    paymentType: {
      ADFALI: 'ادفعلي',
      CASH: 'نقداً',
      CHEQUE: 'شيك',
      EMIZA: 'إي ميزا',
      MOAMALAT: 'معاملات',
      MOBICASH: 'موبي كاش',
      SADAD: 'سداد',
      TADAWUL: 'تداول',
      WALLET: 'محفظة',
    },

    referenceType: {
      CONSUMER: 'العميل',
      MERCHANT: 'التاجر',
      TRANSACTION: 'العملية',
    },

    transactionStatus: {
      CANCELLED: 'ملغاة',
      ERROR: 'خطأ',
      PENDING_PAYMENT: 'قيد الانتظار للدفع',
      PENDING_VERIFICATION: 'قيد التحقق',
      PAYED: 'تم الدفع',
    },

    transactionType: {
      CONSUMER_BUY_VOUCHERS: 'شراء كروت العملاء',
      CONSUMER_WALLET_TO_BANK: 'تحويل من محفظة العميل إلى البنك',
      CONSUMER_WALLET_TO_CONSUMER: 'تحويل بين محافظ العملاء',
      CONSUMER_WALLET_TO_CONSUMER_WALLET: 'تحويل بين محافظ العملاء',
      CONSUMER_WALLET_TO_MERCHANT_WALLET:
        'تحويل من محفظة العميل إلى محفظة التاجر',
      CONSUMER_WALLET_CASHOUT: 'سحب نقدي من محفظة العميل',
      MERCHANT_BUY_VOUCHERS: 'شراء كروت التاجر',
      MERCHANT_WALLET_TO_BANK: 'تحويل من محفظة التاجر إلى البنك',
      MERCHANT_WALLET_TO_CONSUMER: 'تحويل من محفظة التاجر إلى محفظة العميل',
      MERCHANT_WALLET_TO_CONSUMER_WALLET: 'تحويل بين محافظ التاجر والعميل',
      MERCHANT_WALLET_TO_MERCHANT_WALLET: 'تحويل بين محافظ التاجر',
      MERCHANT_WALLET_CASHOUT: 'سحب نقدي من محفظة التاجر',
    },

    walletAction: {
      DEPOSIT: 'إيداع',
      WITHDRAW: 'سحب',
    },

    walletStatus: {
      ACTIVE: 'نشطة',
      DEACTIVATED: 'معطلة',
      PENDING_KYC: 'قيد التحقق من الهوية',
      STOLEN: 'مسروقة',
      SUSPENDED: 'معلقة',
    },

    walletType: {
      CONSUMER: 'العميل',
      MERCHANT: 'التاجر',
      POST_OFFICE: 'مكتب البريد',
    },

    walletUser: {
      arabicFirstName: 'الاسم الأول بالعربية',
      arabicMiddleName: 'الاسم الأوسط بالعربية',
      arabicLastName: 'الاسم الأخير بالعربية',
      englishFirstName: 'الاسم الأول بالإنجليزية',
      englishMiddleName: 'الاسم الأوسط بالإنجليزية',
      englishLastName: 'الاسم الأخير بالإنجليزية',
      commercialRegistryNo: 'رقم السجل التجاري',
      commercialLicenceNo: 'رقم الترخيص التجاري',
      commercialName: 'الاسم التجاري',
      // Add other properties as needed
    },

    paymentMethod: {
      name: 'الاسم',
      nameAr: 'الاسم (بالعربية)',
      nameEn: 'الاسم (بالإنجليزية)',
      menuOrder: 'ترتيب القائمة',
      imageFileUrl: 'رابط ملف الصورة',
      imageFile: 'ملف الصورة',
      details: 'التفاصيل',
      feePercentage: 'نسبة الرسوم',
      paymentType: 'نوع الدفع',
      notes: 'ملاحظات',
    },

    notification: {
      title: 'العنوان',
      description: 'الوصف',
      userId: 'معرف المستخدم',
    },

    viewLog: {
      entityName: 'اسم الكيان',
      entityId: 'معرف الكيان',
    },

    setting: {
      key: 'المفتاح',
      value: 'القيمة',
    },

    slider: {
      details: 'التفاصيل',
      menuOrder: 'ترتيب القائمة',
      imageFileUrl: 'رابط ملف الصورة',
      imageFile: 'ملف الصورة',
      url: 'الرابط',
    },

    transaction: {
      transactionReference: 'المرجع العملية',
      transactionSecret: 'الرمز السري للعملية',
      transactionOtp: 'رمز التحقق للعملية',
      paymentType: 'نوع الدفع',
      transactionStatus: 'حالة العملية',
      transactionType: 'نوع العملية',
      vendorReference: 'المرجع التاجر',
      vendorMessage: 'رسالة التاجر',
      amount: 'المبلغ',
      fees: 'الرسوم',
      total: 'المجموع',
      notes: 'ملاحظات',
      senderMobileNo: 'رقم جوال المرسل',
      senderName: 'اسم المرسل',
      senderIdNo: 'رقم هوية المرسل',
      receiverName: 'اسم المستلم',
      receiverMobileNo: 'رقم جوال المستلم',
      receiverIdNo: 'رقم هوية المستلم',
    },

    voucherCompany: {
      nameAr: 'الاسم (بالعربية)',
      nameEn: 'الاسم (بالإنجليزية)',
      mobileNo: 'رقم الهاتف',
      email: 'البريد الإلكتروني',
      imageFile: 'ملف الصورة',
      imageUrlFile: 'رابط ملف الصورة',
      postCode: 'الرمز البريدي',
      address: 'العنوان',
      notes: 'ملاحظات',
    },

    voucherType: {
      nameAr: 'الاسم (بالعربية)',
      nameEn: 'الاسم (بالإنجليزية)',
      slug: 'الرمز',
      amount: 'المبلغ',
      menuOrder: 'ترتيب القائمة',
    },

    voucher: {
      code: 'الرمز',
      amount: 'المبلغ',
      serialNumber: 'الرقم التسلسلي',
      isSold: 'تم البيع',
      sellTransactionId: 'رقم عملية البيع',
    },
  },
}
