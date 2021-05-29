原型模式

1.通过 Object object = new Object();
    Object object2=object;
这种方式只是实现了对象的引用, 本质上还是同一个对象

2.实现对象的克隆(深度拷贝)==两个完全相同的 不只是对象的引用的 两个对象

3.原型模式就是实现Cloneable接口的clone方法
 * PrototypeClass{name='原型1', createTime=Sat Feb 15 15:12:10 CST 2020}
 * 83954662
 * PrototypeClass{name='原型1', createTime=Sat Feb 15 15:12:10 CST 2020}
 * 1751075886
 * ====> 一模一样 但是不是同一个对象
 * 但是存在一个问题, 这两个对象中的 createTime属性指向的还是同一块内存区域,是同一个Date
 * 改变p的Date会导致pcv的Date也发生改变
 * 这个称为浅拷贝
 
 PrototypeClass prototypeClass = (PrototypeClass)super.clone();
 // 实现深度拷贝 对象中的属性对象也需要拷贝 把这个对象的createTime属性也拷贝出去给副本
 prototypeClass.createTime = (Date) this.createTime.clone();
 return prototypeClass;
## 这样 两个对象的createTime属性的hashcode虽然一样, 
## 但是改变其中一个对象的createTime另一个对象的createTime不会改变