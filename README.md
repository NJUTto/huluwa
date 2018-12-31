# MyCalabash
Java Final Homework

## 框架
采用Maven对项目进行管理。用于展示的战场为16 * 18的二维空间。<br>

## 战斗说明
战场中各生物体以一定的策略在战场中进行移动，每次移动时判断下一个位置是敌人 还是友军<br>
如果是敌人则按照战斗力数值的设置以一定概率死亡一方，留下尸体（墓碑显示），尸体并不占据战场位置，上面任然可以放置生物<br>
如果是友军则放弃移动<br>
战斗力设置：
葫芦娃：蛇精 = 1 ：4<br>
葫芦娃：蝎子精 = 1：1<br>
葫芦娃：小喽啰 = 2：1<br>
爷爷：蛇精 = 1 ：10<br>
爷爷：蝎子精 = 1：5<br>
爷爷：小喽啰 = 1：2<br>

## 移动方式
为了防止战斗过早结束，所以采取了一种较为综合我的方式：<br>
以战场中央6 * 6的范围为中央，当生物在中央时，其上下左右移动的概率相同；若不在中央，则向中央移动的概率更大，其他三个方向相同。

## 运行说明
阵型选择：在开始游戏之前先进行双方的阵型选择
开始游戏：点击后或者按下空格（必须双方阵型选择完毕的情况下，否则无法开始），开始各个生物体的线程,进行移动与战斗，知道一方全部死亡，胜利的一方继续在战场中游行。<br>
暂停：可以终止生物的移动，在点击 开始游戏 后可再次移动。<br>
复盘：在尚未开始游戏时，用于打开记录移动情况的文件record.txt（在开始游戏后请不要进行复盘）<br>

## 类说明
>Creature类：<br>
>>作为基类，包含了各种生物的基本信息和共同的动作<br>
>>randonMove和move用于实现生物移动，具体实现在子类之中以适应不同的情况<br>

>CalabashBro，Snake，Scorpion，Grandpa,Minion类：<br>
>>均继承Creature，都实现了Runnable接口以实现线程。
>>CalabashBro中新增变量rank记录葫芦娃的排名
>>覆盖父类的一些函数以适用各种生物不同的情况

>Position类：<br>
>>表示战场中的各个空间位置，包括是否为empty，位置坐标，位置上的的生物Creature<br>

>Formation类：<br>
>>包含各种阵型供选择<br>
>>根据参数name选择对应的阵型，在战场中放置生物<br>
>>生物start为每个阵型的起始位置，cre[]依据start在周围摆出所选的阵型, side用于选择阵型方向<br>

>Battleground类：<br>
>>作为战场，包含了战场的尺寸，Position型的二维数组ground[][]表示战场中的空间位置，葫芦兄弟、蛇精、蝎子精、爷爷和小喽啰们<br>
>>战场与javafx中的画布相关联，对画布进行分割以显示战场<br>
>>其他类中均包含一个static的战场，用于共享。

>Player类
>>实现Runnable接口，用于对战场显示的不断刷新，确保能看到生物的移动动作<br>
>>在创建Player对象时加载BGM循环播放

>ContorlBattle类：
>>初始化关键的类的对象，并与javafx界面想关联，传给battle.fxml文件，用于图形化框架的构建<br>
>>startgame后执行各线程（用线程池管理）。
>>其中的一些函数与button等控件相关联，当点击时调用函数（开始游戏，阵型选择等）<br>

>Main类：
>>加载battle.fxml文件用于图形化界面的显示
>>当关掉界面窗口时退出程序。

三、实现效果
----------
以长蛇，鹤翼，鱼鳞，锋矢这四个阵型为例：<br>
初始化，葫芦娃进入战场：<br>
![image text](https://github.com/NJUTto/java-2018f-homework/blob/master/20180925/童成伟-161220120/1.png)
妖怪们进入战场：<br>
![image text](https://github.com/NJUTto/java-2018f-homework/blob/master/20180925/童成伟-161220120/2.png)
葫芦兄弟重排，妖怪变阵：
![image text](https://github.com/NJUTto/java-2018f-homework/blob/master/20180925/童成伟-161220120/3.png)
妖怪再次变阵：
![image text](https://github.com/NJUTto/java-2018f-homework/blob/master/20180925/童成伟-161220120/4.png)

