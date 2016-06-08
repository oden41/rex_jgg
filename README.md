# REX/JGG
遺伝的アルゴリズムのJava実装．交叉手法がREX，世代交代モデルがJGGとなっている．

###IFunction.java,TK_Tablet.java,TSphereFunction.java
ベンチマーク関数．K-Table関数とSphere関数を用意している．

###TVector.java
GAの個体を表すベクトルクラス．メソッドとしてベクトル同士の加減乗除や内積，単位ベクトル化などを備えている．

###TIndividual.java
ベクトルクラスと評価値をフィールドとして持っている．

###TPopulation.java
個体集団クラス．

###TRex.java
交叉手法の1つREXを実装しているクラス．

###REX_JGG_TEST.java
実際に上記のクラスを使用している．
