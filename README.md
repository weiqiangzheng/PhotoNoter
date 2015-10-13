# PhotoNoter

Material Design风格的开源照片笔记。 

下载：

- <a href="http://android.myapp.com/myapp/detail.htm?apkName=com.yydcdut.note">应用宝</a>
- <a href="http://app.flyme.cn/apps/public/detail?package_name=com.yydcdut.note">Flyme</a>

如果发现bug或者有什么建议，**欢迎在issue里面讨论**！

# 应用截图

## 动画gif

<img width="300" height="553" src="https://raw.githubusercontent.com/yydcdut/PhotoNoter/master/screenshot/animation.gif">

## 界面

<img src="https://raw.githubusercontent.com/yydcdut/PhotoNoter/master/screenshot/screen1.png" width="25%" height="25%" style="max-width:100%;"><img src="https://raw.githubusercontent.com/yydcdut/PhotoNoter/master/screenshot/screen2.png" width="25%" height="25%" style="max-width:100%;"><img src="https://raw.githubusercontent.com/yydcdut/PhotoNoter/master/screenshot/screen3.png" width="25%" height="25%" style="max-width:100%;"><img src="https://raw.githubusercontent.com/yydcdut/PhotoNoter/master/screenshot/screen4.png" width="25%" height="25%" style="max-width:100%;">

<img src="https://raw.githubusercontent.com/yydcdut/PhotoNoter/master/screenshot/screen5.png" width="25%" height="25%" style="max-width:100%;"><img src="https://raw.githubusercontent.com/yydcdut/PhotoNoter/master/screenshot/screen6.png" width="25%" height="25%" style="max-width:100%;"><img src="https://raw.githubusercontent.com/yydcdut/PhotoNoter/master/screenshot/screen7.png" width="25%" height="25%" style="max-width:100%;"><img src="https://raw.githubusercontent.com/yydcdut/PhotoNoter/master/screenshot/screen8.png" width="25%" height="25%" style="max-width:100%;">

# 技术点

1. 相机MVC架构（正在抽离出来中 <a href="https://github.com/yydcdut/Camera-Camera2-MVC">Camera-Camera2-MVC</a>）。
2. 相机的状态机（状态机不对很容易崩哦~还要参数部分）。
3. 照片缓存分为两种，一个是大图，一个是小图，小图是相册界面缩略图的时候加载的，大图是可以照片详情页面的时候加载的。
4. 每个笔记的数据库ID都是某个分类的外键。
5. 图片处理，这是一个老生常谈的了。但是在App中，发现很多这方面的问题我还没有解决。比如红米1s后置摄像头800W，那么拍一张图是3M左右，但是Camera的照片的0度是我们正常手机视角的90度。那么我们需要把这个3M的图片给翻转过来，又不想失分辨率，诶，java臣妾做不到啊！那么现在的解决办法是不去拍摄800W像素的，拍大概400-500W像素的不会OOM的。
6. 沙盒。每次拍完照都是先把数据放到沙盒数据库中，然后再到服务中去作图，做完的话再从数据库中删除掉。作图的Service是和Camera那个Activity绑定的(bind方式)，当不再拍照的时候就退出了Service，然后回到相册界面的时候会去判断沙盒数据库中是否有没有做完的图，没有做完的话另外启一个进程的Service继续作图。
7. 图片处理完之后（比如在Service中做完了图之后）通知更新界面，用的是广播。
8. 高斯模糊（网上找的），如果图大的话会非常慢。
9. activity退出和进入的动画。这块弄了很久，主要是想模仿Android5.0的那种，但是有些界面做出来超级卡，卡到爆。
10. 主题设置，沉浸式状态栏（5.0）。
11. 可以滑动item和可以拖放item的ListView（<a href="https://github.com/yydcdut/SlideAndDragListView">SlideAndDragListView</a>）。
12. …...

# 更新版本说明

## 1.1.0

- 相机界面
- 抽离相机出来成Lib
- 介绍页面
- 添加字体
- QQ登录
- AIDL，Service作图
- 重构设置界面

# 致谢

- <a href="https://github.com/markushi/android-ui">android-ui</a>
- <a href="https://github.com/futuresimple/android-floating-action-button">android-floating-action-button</a>
- <a href="https://github.com/yydcdut/SlideAndDragListView">SlideAndDragListView</a>
- <a href="https://github.com/yydcdut/Camera-Camera2-MVC">Camera-Camera2-MVC</a>

# License

Copyright 2015 yydcdut

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

