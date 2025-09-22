GameBoost - Android Studio project (Kotlin)
==========================================

ما هذا:
- مشروع Android جاهز للاستيراد في Android Studio. التطبيق بسيط: زر Boost، خيارات DND وAuto-Boost.
- لا يقوم بتعديل ملفات الألعاب ولا يستخدم روت. يوفر أمثلة حول كيفية طلب صلاحيات DND وUsage Access.

كيف تبني APK (Android Studio):
1. نزّل و ثبت Android Studio (Windows/Mac/Linux).
2. افتح Android Studio -> Open -> اختر المجلد GameBoostProject.
3. انتظر Gradle ليحمّل الاعتمادات. ثم من قائمة Build -> Build Bundle(s) / APK(s) -> Build APK(s).
4. بعد البناء ستجد APK في: app/build/outputs/apk/debug/app-debug.apk
5. لتوقيع نسخة للنشر: قم بإنشاء keystore في Build -> Generate Signed Bundle / APK واتباع الإرشادات.

ملاحظات حول ميزات الأداء المتقدمة:
- لإضافة طلبات Game Mode أو Frame Rate متقدمة، يلزم دمج SDKs خاصة بـOEM أو استخدام واجهات Android الحديثة. يمكنك طلب مني إضافة هذه الأكواد لاحقًا.
- بعض خواص الأداء تتطلب روت أو ADB لتغيير سلوك مدير الطاقة على بعض أجهزة سامسونج.
