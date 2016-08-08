# BaseAndroid

> Android基础项目，MVP结构

## 用法
* Android Studio 直接导入整个project 或者导入BaseAndroid module
* Eclipse 导入BaseAndroid/BaseAndroid/app/目录下的工程

    

## 支持的功能
* 事件总线Rxbus
    
    
    
    
## 代码示例

### RxBus
   ```java
   发布事件
   RxBus.send(new RxBusType(RxBusType.NETWORK_STATUS)); 
   注册事件(响应所有事件)
   RxBus.toObservable()
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        Log.e("Psuwgipgf",Thread.currentThread().getName());
                    }
                });
   注册事件(根据事件类型响应)
   RxBus.toObservable(1,2,3)
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        Log.e("Psuwgipgf",Thread.currentThread().getName());
                    }
                });
   ```
    
    
    
    
