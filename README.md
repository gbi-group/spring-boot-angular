spring-boot-angular
------------------------
spring boot 和 spring data的简单例子

安装gradle
-----------------
1.\ 下载gradle并解压安装到相关目录（例如d:\tools\gradle）

2.\ 追加环境变量"GRADLE_HOME"，值为"d:\tools\gradle"（注：值为gradle安装目录）

3.\ 追加环境变量"GRADLE_USER_HOME"，值为"d:\projects\gradle"（注：值随便设置，用来存放本地jar）

4.\ 将"%GRADLE_HOME%\bin"加到环境变量"PATH"

使用gradle
-----------------------------
清除编译生成的文件
```
gradle clean
```

编译文件
```
gradle build -x test
```

启动项目
```
gradle bootRun
（注：启动后例子的url为"http://localhost:8080/index.html"）
```

references
-----------------------------------------------
* [spring-boot-fun](https://github.com/rafalnowak/spring-boot-fun)


