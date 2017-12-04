# ACSwipeBackLayout
这是一个滑动边缘关闭当前界面的依赖库，有防微信Style,支持动态设置是否需要开始滑动关闭功能。

## 基本使用
```java
dependencies {
	        implementation 'com.github.Achenglove:ACSwipeBackLayout:v1.0.1'
	}
```

## 初始化
```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 必须在 Application 的 onCreate 方法中执行 ACSwipeBackHelper.init 来初始化滑动返回
         * 第一个参数：应用程序上下文
         * 第二个参数：如果发现滑动返回后立即触摸界面时应用崩溃，请把该界面里比较特殊的 View 的 class 添加到该集合中，目前在库中已经添加了 WebView 和 SurfaceView
         */
        ACSwipeBackHelper.init(this, null);
    }
}
```
