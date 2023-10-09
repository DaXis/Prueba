# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Ivan\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Retrofit
-keepattributes Signature, InnerClasses, EnclosingMethod
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>
-dontwarn retrofit.**
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }

-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

# Gson
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class com.google.gson.examples.android.model.** { <fields>; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# Butterknife
-keep public class * implements butterknife.Unbinder { public <init>(**, android.view.View); }
-keep class butterknife.*
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }

# EventBus
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# OkHttp3
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

-dontwarn javax.annotation.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontwarn org.codehaus.mojo.animal_sniffer.*
-dontwarn okhttp3.internal.platform.ConscryptPlatform
-keep class com.squareup.okhttp.** {*;}

# PDFViewer
-keep class com.shockwave.**

# Dagger
-dontwarn com.google.errorprone.annotations.**

# Google
-keepattributes Signature,RuntimeVisibleAnnotations,AnnotationDefault

-keepclassmembers class * {
  @com.google.api.client.util.Key <fields>;
}

-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

-keep class com.google.** { *; }
-dontwarn com.google.**

-keep class com.google.zxing.** { *; }
-dontwarn com.google.zxing.**

# Gradle
-dontwarn org.gradle.api.**
-dontwarn org.gradle.tooling.**
-dontwarn com.google.appengine.api.**
-dontwarn com.google.auto.value.**
-dontwarn com.google.common.util.**
-keep class android.arch.persistence.room.* {*;}
-keep interface android.arch.persistence.room.* {*;}

# RX java
-keep class rx.** { *; }
-dontwarn rx.**

# SereneGiant
-keep class com.serenegiant.** { *; }
-dontwarn com.serenegiant.**

# NAAT
-keep class com.naat.** {*;}
-dontwarn com.naat.**
-keep class com.na_at.** {*;}
-dontwarn com.na_at.**

-keep public class * extends com.na_at.sdk.commons.BaseActivity
-keep public class * extends com.na_at.sdk.commons.BaseFragment

-keep class org.opencv.** { *; }
-dontwarn org.opencv.**

-keep class com.na_at.fad.UI.Activities.base.opencv.model.** { *; }
-dontwarn com.na_at.fad.UI.Activities.base.opencv.model.**

-keep public class com.na_at.sdk.identity.** {
  public protected *;
}

-keep public class com.na_at.sdk.identity.**$* {
  public protected *;
}

-keep class com.na_at.fad.Services.ScreenRecorderService.** { *; }
-dontwarn com.na_at.fad.Services.ScreenRecorderService.**

############

# Optimization is turned off by default. Dex does not like code run # through the ProGuard optimize and preverify steps (and performs #some of these optimizations on its own).
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizations !method/inlining/*
-optimizationpasses 5
-allowaccessmodification
-useuniqueclassmembernames
-keepattributes SourceFile,LineNumberTable

# Timber
-dontwarn org.jetbrains.annotations.**

-keepattributes Signature
-keepattributes Exceptions

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Spongycastle
-keep class org.spongycastle.** {*;}
-dontwarn org.spongycastle.**

# itextPDF
-keep class com.itextpdf.text.pdf.** {*;}
-keep class org.xmlpull.v1.** { *; }
-keep class javax.xml.crypto.** {*;}
-dontwarn javax.xml.crypto.**

# Apache
-keep class org.apache.** {*;}
-dontwarn org.apache.**
-keep class org.apache.http.** { *; }

# SQLcipher
-keep class net.sqlcipher.** { *; }
-keep class net.sqlcipher.database.** { *; }

# RenderScript
-keepclasseswithmembernames class * {native <methods>;}
-keep class androidx.renderscript.** { *; }


-keep class com.loopj.android.** { *; }
-dontwarn com.loopj.android.**

-keep class cz.msebera.** {*;}
-dontwarn cz.msebera.**

-keep class com.journeyapps.barcodescanner.** { *; }
-dontwarn com.journeyapps.barcodescanner.**

-keep class org.json.** { *; }
-dontwarn org.json.**

-keep class com.qualcomm.** { *; }
-dontwarn com.qualcomm.**

-keep class android.support.v4.** { *; }
-dontwarn android.support.v4.**

-keep class org.vudroid.** { *; }
-dontwarn org.vudroid.**

-keep class com.github.hiteshsondhi88.** { *; }
-dontwarn com.github.hiteshsondhi88.**

-keep public class * extends android.app.Activity
-keep public class * extends android.support.v7.app.AppCompatActivity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-dontwarn android.app.AlarmManager
-dontwarn android.app.Application

-keepclasseswithmembers class * {
    native <methods>;
}

-keep public class * extends android.view.View {
      public <init>(android.content.Context);
      public <init>(android.content.Context, android.util.AttributeSet);
      public <init>(android.content.Context, android.util.AttributeSet, int);
      public void set*(...);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.content.Context {
   public void *(android.view.View);
   public void *(android.view.MenuItem);
}

-keepclassmembers class * implements android.os.Parcelable {
    static android.os.Parcelable$Creator CREATOR;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keep class **.R

-keep class **.R$* {
   public static <fields>;
}

-keepattributes InnerClasses

-keep class **.R
-keep class *.R$ {
    <fields>;
}


-keep public class * extends com.na_at.sdk.commons.BaseActivity
-keep public class * extends com.na_at.sdk.commons.BaseFragment

-keepclassmembers class * extends android.content.Context {
   public void *(android.view.View);
   public void *(android.view.MenuItem);
}

-keepclassmembers class * implements android.os.Parcelable {
    static android.os.Parcelable$Creator CREATOR;
}

-keep class **.R
-keep class *.R$ {
   public static <fields>;
}

-keepclassmembers class *.R$ {
    public static <fields>;
}

# React Native

# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
-keep,allowobfuscation @interface com.facebook.proguard.annotations.DoNotStrip
-keep,allowobfuscation @interface com.facebook.proguard.annotations.KeepGettersAndSetters
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.proguard.annotations.DoNotStrip class *
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.proguard.annotations.DoNotStrip *;
    @com.facebook.common.internal.DoNotStrip *;
}

-keepclassmembers @com.facebook.proguard.annotations.KeepGettersAndSetters class * {
  void set*(*);
  * get*();
}

-keep class * extends com.facebook.react.bridge.JavaScriptModule { *; }
-keep class * extends com.facebook.react.bridge.NativeModule { *; }
-keepclassmembers,includedescriptorclasses class * { native <methods>; }
-keepclassmembers class *  { @com.facebook.react.uimanager.UIProp <fields>; }
-keepclassmembers class *  { @com.facebook.react.uimanager.annotations.ReactProp <methods>; }
-keepclassmembers class *  { @com.facebook.react.uimanager.annotations.ReactPropGroup <methods>; }

-dontwarn com.facebook.react.**
-keep,includedescriptorclasses class com.facebook.react.bridge.** { *; }

-keepattributes Signature
-keepattributes Annotation

# okio

-keep class sun.misc.Unsafe { *; }
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-keep class okio.** { *; }
-dontwarn okio.**

# WebRTC

-keep class org.webrtc.** { *; }
-dontwarn org.chromium.build.BuildHooksAndroid

# Jisti Meet SDK

-keep class org.jitsi.meet.** { *; }
-keep class org.jitsi.meet.sdk.** { *; }

# We added the following when we switched minifyEnabled on. Probably because we
# ran the app and hit problems...

-keep class com.facebook.react.bridge.CatalystInstanceImpl { *; }
-keep class com.facebook.react.bridge.ExecutorToken { *; }
-keep class com.facebook.react.bridge.JavaScriptExecutor { *; }
-keep class com.facebook.react.bridge.ModuleRegistryHolder { *; }
-keep class com.facebook.react.bridge.ReadableType { *; }
-keep class com.facebook.react.bridge.queue.NativeRunnable { *; }
-keep class com.facebook.react.devsupport.** { *; }

-dontwarn com.facebook.react.devsupport.**
-dontwarn com.google.appengine.**
-dontwarn javax.servlet.**

# ^^^ We added the above when we switched minifyEnabled on.

# Rule to avoid build errors related to SVGs.
-keep public class com.horcrux.svg.* { *; }



-keep class com.na_at.sdk.signaturepad.** { *; }
-keep class org.spongycastle.** { *; }

-keep public class com.na_at.fad3.**$* {
    public *;
    protected *;
}

-keep public class com.na_at.fad_viewer.**$* {
    public *;
    protected *;
}

-keep public class com.na_at.videoplayer.**$* {
    public *;
    protected *;
}

-keepclassmembers class * extends android.content.Context {
    public void *(android.view.View);
    public void *(android.view.MenuItem);
}

-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}

-keep public class org.simpleframework.** { *; }
-keep class org.simpleframework.xml.** { *; }
-keep class org.simpleframework.xml.core.** { *; }
-keep class org.simpleframework.xml.util.** { *; }

-keepattributes ElementList, Root

-keepclassmembers class * {
    @org.simpleframework.xml.* *;
}

# AndroidX security crypto
-keep class com.google.crypto.** { *; }

# Crashlytics
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable        # Keep file names and line numbers.
-keep public class * extends java.lang.Exception  # Optional: Keep custom exceptions.
-printmapping mapping.txt

# Watson
-keep class org.libusb.** { *; }
-dontwarn org.libusb.**

-keep class com.integratedbiometrics.** { *; }
-dontwarn com.integratedbiometrics.**