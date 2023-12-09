export default {
  identity: {
    title: '',
    dashboard: '',
    subtitle: '',
  },
  navigation: {
    general: 'العامة',
    home: 'الرئيسية',
    management: 'الادارة',
    admins: 'المدراء',
    users: 'المستخدمين',
    roles: 'الوظائف',
    logs: 'السجلات',
    activity_logs: 'السجلات',
    settings: 'الاعدادات',
  },
  attributes: {
    name: 'الاسم',
    full_name: 'الاسم بالكامل',
    email: 'البريد الالكتروني',
    phone: 'رقم الهاتف',
    password: 'كلمة المرور',
    password_confirmation: 'تأكيد كلمة المرور',
    current_password: 'كلمة المرور الحالية',
    new_password: 'كلمة المرور الجديدة',
    confirm_password: 'تأكيد كلمة المرور',
    code: 'الرمز',
    recovery_code: 'رمز الإستعادة',
    user: 'المستخدم',
    user_id: 'المستخدم',
    role: 'الوظيفة',
    permission_ids: 'الصلاحيات',
    actions: 'الإجراءات',
    notes: 'ملاحظات',
    images: 'الصور',
    image: 'الصورة',
    document: 'الملف',
    documents: 'الملفات',
    status_id: 'الحالة',
    status: 'الحالة',
    sort_by: 'ترتيب حسب',
    sort_dir: 'ترتيب',
    action: 'الإجراء',
    tfa: 'المصادقة الثنائية',
    otp: 'كلمة المرور لمرة واحدة',
    date: 'التاريخ',
    created_at: 'تاريخ الإضافة',
    updated_at: 'تاريخ التحديث',
    created_at_from: 'من تاريخ الإضافة',
    created_at_to: 'إلى تاريخ الإضافة',
  },
  pages: {
    dashboard: {
      title: 'الرئيسية',
      subtitle: '',
    },
    login: {
      title: 'تسجيل الدخول',
      subtitle: 'الرجاء ادخال بيانات الحساب الخاص بك',
    },
    profile: {
      account: 'اعدادات الحساب',
      title: 'الحساب الشخصي',
      subtitle: 'يمكنك تعديل بيانات حسابك الشخصي.',
    },
    forgetPassword: {
      title: 'نسيت كلمة المرور',
      subtitle: 'ادخل بريدك الالكتروني لارسال تعليمات استعادة كلمة المرور.',
    },
    changePassword: {
      title: 'تغيير كلمة المرور',
      subtitle: '',
    },
    resetPassword: {
      title: 'استعادة كلمة المرور',
      subtitle: 'ادخل بيانات حسابك الجديدة',
    },
    tfa: {
      title: 'المصادقة الثنائية',
      subtitle: 'قم بتعزيز حماية حسابك عبر تفعيل المصادقة الثنائية.',
    },
    recoveryCodes: {
      title: 'إستعمال رمز إحتياطي',
      subtitle: '',
    },
    roles: {
      title: 'الوظائف',
      subtitle: '',
    },
  },
  buttons: {
    login: 'تسجيل الدخول',
    logout: 'تسجيل الخروج',
    save: 'حفظ',
    exit: 'الخروج',
    close: 'اغلاق',
    reset: 'استرجاع',
    register: 'تسجيل',
    submit: 'ارسال',
    search: 'بحث',
    filter: 'تصفية',
    enable: 'تفعيل',
    disable: 'الغاء تفعيل',
    delete: 'حذف',
    cancel: 'الغاء',
    comment: 'اضف تعليق',
    add: 'إضافة',
    addFile: 'إضافة ملف',
    run: 'تنفيذ',
    view: 'عرض',
    viewAll: 'عرض الكل',
    confirm: 'تأكيد',
    copy: 'نسخ',
    copied: 'تم النسخ',
    export: 'تصدير',
    signUp: 'إشترك',
    activate: 'تفعيل',
    upload: 'رفع',
    generate: 'تكوين',
    sendOtp: 'ارسال رمز التفعيل',
    continue: 'استمرار',
    edit: 'تعديل',
    show: 'عرض',
    back: 'العودة',
    print: 'طباعة',
    showQr: 'عرض الQR',
    download: 'تحميل',
    forceEnable: 'فرض تفعيل',
    importFile: 'استيراد الملف',
    confirmExit: 'تأكيد الخروج',
    hideList: 'اخفاء القائمة',
    show: 'عرض',
    move: 'نقل',
    continue: 'استمرار',
    removeAll: 'حذف الكل',
    remove: 'حذف',
    manage: 'ادارة',
    generateRecoveryCodes: 'تكوين رموز الاسترداد',
  },
  notify: {
    added: 'تم إضافة {entity} بنجاح',
    updated: 'تم تعديل {entity} بنجاح',
    deleted: 'تم حذف {entity} بنجاح',
    restored: 'تم استرجاع {entity} بنجاح',
    enabled: 'تم تفعيل {entity} بنجاح',
    disabeld: 'تم الغاء تفعيل {entity} بنجاح',
    tfaEnabled: 'تم تفعيل المصادقة الثنائية لحسابك بنجاح',
    tfaDisabled: 'تم تعطيل المصادقة الثنائية لحسابك',
  },
  labels: {
    rememberMe: 'تذكرني',
    inactive: 'معطل',
    active: 'مفعل',
    uploadFiles: 'انقر أو اسحب ملفًا إلى هذه المنطقة لتحميله',
    minute: 'دقيقة',
    hour: 'ساعة',
    search: 'بحث',
    list: 'القائمة',
    selectAll: 'إختيار الكل',
    noData: 'لا توجد بيانات',
    desc: 'تنازلي',
    asc: 'تصاعدي',
    otherPermissions: 'صلاحية اخرى',
    otherRoles: 'دور أخر',
    permissions: 'الصلاحيات',
    roles: 'الأدوار',
    backToLogin: 'العودة إلى تسجيل الدخول',
    noRecoveryCodes: 'ليس لديك رموز إسترداد',
    confirmPassword: 'تأكيد كلمة المرور',
    useRecoveryCode: 'إستعمال رمز إحتياطي',
    backToTfa: 'العودة لإدخال رمز تطبيق المصادقة',
    noResultsFound: 'لاتوجد نتائج مطابقة للبحث.',
    noResults: 'لاتوجد نتائج',
    searchPage: 'بحث على مستوى الصفحة',
    copiedToClipboard: 'تم النسخ',
    optional: 'إختياري',
    all: 'الكل',
    otherPermissions: 'صلاحيات اخرى',
    tfaEnabled: 'المصادقة الثنائية مفعلة',
    tfaDisabled: 'المصادقة الثنائية غير مفعلة',
    enabled: 'مفعلة',
    disabled: 'غير مفعلة',
    markAsRead: 'تحديد الكل كمقروئة',
    noNotifications: 'لا يوجد إشعارات',
  },
  pagination: {
    per_page: 'لكل صفحة',
    entries: 'الصفحة {from} من {total}',
    total_items: 'اجمالي {total} نتيجة',
  },
  dialogs: {
    confirmPassword: {
      title: 'تأكيد كلمة المرور',
      subtitle: 'يرجى ادخال كلمة المرور الحالية للإستمرار.',
    },
    tfa: {
      enable: {
        title: 'تفعيل المصادقة الثنائية',
        subtitle:
          'قم بمسح رمز تفعيل المصادقة الثنائية باستخدام احد برامج المصادقة (مثل Google authenticator)، ثم قم بادخال الرمز في الحقل ادناه',
      },
      disable: {
        title: 'هل أنت متأكد من إلغاء تفعيل المصادقة الثنائية؟',
        subtitle: '',
      },
      userDisable: {
        title: 'هل أنت متأكد من إلغاء تفعيل المصادقة الثنائية؟',
        subtitle:
          'لن يتمكن هذا المستخدم من إستعمال رمز المصادقة الثنائية الخاص به في تسجيل الدخول وسيصبح حسابه غير محمي',
      },
      recoveryCodes: {
        title: 'رمز استعادة الوصول',
        subtitle:
          'تستطيع استعمالهم في حالة فقدانك بيانات الدخول, وكل رمز يسمح لك بدخول مره واحده فقط, لهذا عليك الحفاظ عليهم في مكان أمن',
      },
    },
    delete: {
      title: 'حذف {entity}',
      subtitle: 'هل أنت متأكد من حذف ال{entity}؟',
    },
    clear: {
      title: 'حذف كل {entity}',
      subtitle: 'لايمكن استرجاع {entity} في حالة حذفها. هل أنت متأكد؟',
    },
    add: {
      title: 'إضافة {entity}',
      subtitle: 'ادخل البيانات المطلوبة لإضافة {entity} الى النظام',
    },
    update: {
      title: 'تعديل {entity}',
      subtitle: '',
    },
    leaveForm: {
      title: 'هل انت متأكد من الخروج من الصفحة؟',
      subtitle: 'لديك تغييرات غير محفوظة يمكن ان تفقدها في حالة تاكيد الخروج.',
    },
  },
  entities: {
    singular: {
      accounts: 'الحساب',
      admins: 'مدير نظام',
      users: 'مستخدم',
      roles: 'وظيفة',
      permissions: 'صلاحية',
      settings: 'الإعدادات',
    },
    // singular
    password: 'كلمة المرور',
    account: 'الحساب',
    admin: 'مدير نظام',
    user: 'مستخدم',
    role: 'وظيفة',
    permission: 'صلاحية',
    // plural
    roles: 'الوظائف',
    permissions: 'الصلاحيات',
    admins: 'مدراء النظام',
    users: 'المستخدمين',
    settings: 'الإعدادات',
  },
  validations: {
    required: 'هذا الحقل مطلوب',
    email: 'صيغة البريد الالكتروني غير صحيحة',
    number: 'يجب ان تكون القيمة المدخلة رقماً',
    alpha: 'يجب ان تكون القيمة المدخلة متكونة من حروف فقط',
    min: 'يجب ان يكون عدد الخانات المدخلة أكبر من {threshold}',
    maxSize: 'يجب ان يكون حجم كل ملف أقل من mb{threshold}',
    minLength: 'يجب أن يحتوي هذا الحقل على {threshold} من الأحرف على الأقل',
  },
  errors: {
    503: {
      title: 'النظام قيد الصيانة',
      description: 'سنعود للعمل قريباً، يمكنك العودة لاحقاً',
    },
    403: {
      title: 'Access Denied',
      description:
        'You do not have permission to access this resource. contact the administrator to obtain access.',
    },
    404: 'هذه الصفحة غير موجودة.',
    other: 'حدث خطأ ما. يرجى المحاولة مجدداً',
    unauthorizedRoute: 'عذراً، ليس لديك الصلاحيات المطلوبة لعرض الصفحة.',
  },
  locales: {
    ar: 'العربية',
    en: 'English',
  },
  lyd: 'د.ل',
  icons: {
    chevronEnd: 'mdi-chevron-left',
    chevronStart: 'mdi-chevron-right',
  },

  footer: {
    copyrights: 'جميع الحقوق محفوظة',
  },
}