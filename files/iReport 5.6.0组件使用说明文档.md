# iReport 5.6.0组件使用说明文档

## 一、基本组件使用

### 1、Static Text（静态文本框）

#### 1）、组件位置

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/01.jpg)

#### 2）、添加使用

拖动”Static Text“组件至指定区域，双击进行内容编辑，根据自行要求进行属性设置。

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/02.jpg)

### 2、Text Field（文本域）

#### 1）、组件位置

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/03.jpg)

#### 2）、添加使用

拖动“Text Field”组件至指定区域，根据自行要求进行属性设置。

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/04.jpg)

右击选择“Edit expression”进行内容编辑。

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/05.jpg)

找到需要绑定的属性值双击选中即可。

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/06.jpg)

### 3、Break（分割线）

#### 1）、组件位置

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/07.jpg)

#### 2）、添加使用

拖动“Break”组件至指定区域，出现分割线作用“分页”和“换行”选择，根据自行要求进行选择。

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/08.jpg)

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/09.jpg)

### 4、Image（图片）

#### 1）、组件位置

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/10.jpg)

#### 2）、添加使用

拖动“Break”组件至指定区域，出现图片文件选择。

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/11.jpg)

选择打开后可以进行属性设置。

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/12.jpg)

**注意：Image组件可以不选择任何图片文件直接选择取消，这就是"二维码"制作方法。**

### 5、Current Date（当前时间）

#### 1）、组件位置

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/13.jpg)

#### 2）、添加使用

拖动“Break”组件至指定区域，出现时间格式选择。

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/14.jpg)

选择后可以进行属性设置。

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/15.jpg)

### 6、Page number（当前页码数）

#### 1）、组件位置

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/16.jpg)

#### 2）、添加使用

拖动“Page number”组件至指定区域，根据自行要求进行属性设置。

### 7、Total pages（总页码数）

#### 1）、组件位置

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/17.jpg)

#### 2）、添加使用

拖动“Total pages”组件至指定区域，根据自行要求进行属性设置。

### 8、Page X of Y（当前页码数/总页码数）

#### 1）、组件位置

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/18.jpg)

#### 2）、添加使用

拖动“Page X of Y”组件至指定区域，根据自行要求进行属性设置。

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/19.jpg)

**注意：Page number 加 Total pages 组合使用就是 Page X of Y。**

## 二、复杂组件使用

### 1、List（列表）

#### 1）、组件位置

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/20.jpg)

#### 2）、添加使用

例子：展示某个班级中所有学生姓名！

> a、拖动“List”组件至指定区域，根据自行要求进行属性设置。

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/21.jpg)

> b、给List组件添加数据源

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/22.jpg)

> c、给List组件绑定数据源

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/23.jpg)

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/24.jpg)

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/25.jpg)

> d、给List组件中的文本域绑定数据

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/26.jpg)

**数据源：new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource($F{studentList})**

> e、预览编译，应用于项目中即可

> f、效果

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/27.jpg)

### 2、Table（表格）

#### 1）、组件位置

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/28.jpg)

#### 2）、添加使用

例子：展示某个班级中所有学生详细信息！

> a、拖动“Table”组件至指定区域，根据自行要求进行属性设置。

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/29.jpg)

> b、给Table组件添加数据源

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/30.jpg)

> c、给Table组件绑定数据源

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/31.jpg)

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/32.jpg)

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/33.jpg)

**数据源：new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource($F{studentList})**

> d、给Table组件中的文本域绑定数据

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/34.jpg)

> e、预览编译，应用于项目中即可

> f、效果

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/35.jpg)

### 3、Subreport（子报表）

#### 1）、组件位置

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/36.jpg)

#### 2）、添加使用

例子：展示多个班级中所有学生详细信息！

> a、拖动“Subreport”组件至指定区域，根据自行要求进行属性设置。

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/37.jpg)

> b、给Subreport组件添加数据源

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/38.jpg)

> c、给Subreport组件绑定数据源

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/39.jpg)

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/40.jpg)

**数据源：new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource($F{studentList})**

> d、给Subreport组件中的文本域绑定数据

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/41.jpg)

> e、预览编译，应用于项目中即可

> f、效果

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/iReport/42.jpg)