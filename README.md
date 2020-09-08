# MyDialog
## 基本介绍
  一个可以自定义的Android弹窗，弹窗内容可以选择自带的listview/gridview，也可以使用自己的view，有简单的伸缩动画效果，可以自定义Header和Footer
### 三种类型
可自定义view的ViewHolder

  ![avatar](https://github.com/birdaaron/MyDialog/blob/master/screenshot/1.png?raw=trueg)
  
可伸缩的ListHolder

 ![avatar](https://github.com/birdaaron/MyDialog/blob/master/screenshot/2.png?raw=true)
 ![avatar](https://github.com/birdaaron/MyDialog/blob/master/screenshot/3.png?raw=true)
 
 可伸缩的GridHolder
 
  ![avatar](https://github.com/birdaaron/MyDialog/blob/master/screenshot/4.png?raw=true)
## 使用方法
### 引入
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
```
  	dependencies {
	        implementation 'com.github.birdaaron:MyDialog:1.0'
	}
  ```
###使用
  ```
  MyDialog myDialog = MyDialog.newDialog(MainActivity.this)
                    .setContentHolder(holder) //选择类型
                    .setHeader(header,isHeaderFixed)
                    .setFooter(footer,isFooterFixed)
                    .setExpanded(isExpanded)//是否伸缩
                    .setGravity(gravity)
                    .setAdapter(adapter)
                    .create();
            myDialog.show();
  ```
