桥接模式
将抽象部分和它的实现部分分离, 使他们都可以独立的变化, 是一种对象结构型模式
又称为柄体模式(Handle and Body) 或 接口(Interface)模式
[多层继承结构]
电脑分为: 台式电脑、笔记本电脑、平板电脑。。。
台式电脑根据牌子不同, 又可以分为多种台式电脑, 神舟台式电脑...
像神舟台式电脑这样, 品牌与物体不分离, 违反了[单一职责原则] 需要拆分
场景中有两个变化维度: 电脑品牌 和 电脑类型(台式、笔记本、平板)
因此,需要品牌写一个类, 类型写一个类, 而不是直接写神州台式电脑类

代码中核心就是在电脑抽象类中[组合]放入了一个品牌接口类
// 品牌是一个属性, 把品牌组合进来 声明成protected的子类才能使用
protected Brand brand; // 这个即可称之为桥

电脑品牌和电脑类型[自定义组装] 灵活 节省代码
1.极大的减少了子类, 降低管理和维护的成本
2.提高了系统的可扩充性, 符合开闭原则
劣势:
增加系统的理解和设计难度
使用的局限性: 系统中要存在两个独立变化的维度 ====> 这个问题可以使用[适配器模式]解决,制造出两个独立变化但是需要组合的维度