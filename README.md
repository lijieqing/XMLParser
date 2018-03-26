# XMLParser
通过注解方式，用于XML的快速生成和解析
## XMLParser介绍
[XMLParser](https://github.com/lijieqing/XMLParser) 是一款Android XML文件解析生成工具，通过注解的方式来实现XML文件内标签的生成和解析。
使用gradle引入
``` 
compile 'lee.hua.xmlparse:xmlparse:2.2.2'
```
### 简单使用
场景：一个关于书本信息的描述，一本书包含书名、页数、价格、作者、出版社等信息，我们用JAVA对象简单表示出来，如下
```java
@XmlBean(name = "BookNode")//XMLParser类注解,表示此类是一个XML标签，name表示输出的标签名
public class Book {
    @XmlAttribute 
    public String name;//书名
    @XmlAttribute
    public Integer page;//页数
    @XmlAttribute
    public Float price;//价格
    @XmlSingleNode(name = "Publisher",nodeType = Publisher.class)//XMLParser属性注解，表示一个子标签
    public Publisher publishier;//出版商
    @XmlListNode(name = "Author",nodeType = Author.class)//XMLParser属性注解，表示多个子标签
    public List<Author> authors;//作者，多个
	  //省略无参构造 getter setter 和 toString 方法
}
@XmlBean(name = "Author")
public class Author {
    @XmlAttribute
    public String name;//作者名
    //省略无参构造 getter setter 和 toString 方法
}
@XmlBean(name = "Publisher")
public class Publisher {
    @XmlAttribute(name = "PUB") //XMLParser属性注解，name表示输出的属性名
    public String pub;//出版社名称
    //省略无参构造 getter setter 和 toString 方法
}
```
定义好对象之后我们就可以使用XMLParser来输出和解析了.
生成XML代码如下：
```java
//生成XML文件
public void generate(){
	  //生成book数据
    Book book = new Book();
    book.setName("XMLParser解析");
    book.setPage(55);
    book.setPrice(37.5f);
    book.setPublishier(new Publisher("中国花花出版社"));
    List<Author> authors = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
       Author author = new Author("author0"+i);
       authors.add(author);
    }
    book.setAuthors(authors);
	  //设置注解扫描路径
    XMLAPI.setXmlBeanScanPackage("com.lee");
    //输出book对象
    XMLAPI.writeObj2Xml(book,"/Users/lijie/Desktop/test.xml");
}
```
结果：
```xml
text.xml
<?xml version="1.0" encoding="UTF-8"?>
<BookNode name="XMLParser解析" page="55" price="37.5">
    <Publisher PUB="中国花花出版社"/>
    <Author name="author00"/>
    <Author name="author01"/>
    <Author name="author02"/>
    <Author name="author03"/>
</BookNode>
```
解析XML文件，我们使用刚才输出的test.xml文佳作为解析对象，代码如下：
```java
public void readXML(){
	//设置注解扫描路径
	XMLAPI.setXmlBeanScanPackage("com.lee");
	//调用readXML读取文件，并强转为Book对象
    Book book = (Book) XMLAPI.readXML(new FileInputStream("/Users/lijie/Desktop/test.xml"));
    //打印
    System.out.println(book);
}
```
打印结果：
```log
Book{
	name='XMLParser解析', page=55, price=37.5, 
	publishier=Publisher{pub='中国花花出版社'},
	Author{name='author00'},
	Author{name='author01'}, 
	Author{name='author02'}, 
	Author{name='author03'}]
}
```

## XMLParser使用详解
首先介绍使用过程中用到的注解标签

|标签名|注解对象|作用|属性| 
| ------------- |:-------------:| -----:|-----:|  
|```@XmlBean```|JAVA类|表示一个XML的标签|name：要操作的XML标签名|  
|```@XmlAttribute```|JAVA类成员基本数据类型和String属性|表示一个XML标签的属性|name：要操作标签的属性名|  
|```@XmlListNode```|JAVA类集合成员属性|表示多个相同子标签的集合|name：要操作标签的子标签名称 nodetype:子标签对应的JAVA类|  
|```@XmlSingleNode```|JAVA类成员对象属性|表示单个子标签|name：要操作标签的子标签名称 nodetype:子标签对应的JAVA类|  
|```@Ignore```|JAVA类成员属性|表示忽略此属性，不去解析和生成|无|

### 建模
介绍完上面的注解标签，下面开始实战：
首先建立一个根节点是User的XML文件
```XML
<User name="test" age="18" marry="true" married="true">
	<Books>
		<Book name="java"/>
		<Book name="android"/>
	</Books>
	<Phone number="110110110" type="home"/>
	<Phone number="221221221" type="company"/>
</User> 
```
根据XML结构我们可以构建出对应的JAVA对象关系
```java
/**
 * 根据XML文件解析得来最外层User标签
 */
@XmlBean//标记为XML标签
public class User {
    @XmlAttribute//标记为标签属性
    public String name;
    @XmlAttribute//标记为标签属性
    public int age;
    @XmlAttribute//标记为标签属性
    public boolean marry;
    @Ignore//忽略的标签属性
    public boolean married = false;
    @XmlSingleNode(name = "Books", nodeType = Books.class)//单个子节点 name=节点名字，nodeType=节点对应的JAVA类对象
    public Books books;
    @XmlListNode(name = "Phone", nodeType = Phone.class)//多个子节点
    public List<Phone> phones;

	  //getter 和 setter
}
```

```java
@XmlBean
public class Books {
    @XmlListNode(name = "Book",nodeType = Book.class)
    public List<Book> books;
    //getter 和 setter
}
```
```java
@XmlBean
public class Book {
    @XmlAttribute
    public String name;
    //getter 和 setter
}
```
```java
@XmlBean
public class Phone {
    @XmlAttribute(name = "number")//解析属性为number的标签属性
    public String phoneNum;
    @XmlAttribute
    public String type;
    //getter 和 setter
}
```

### 读取XML文件
建立好模型后，XMLParser有一个```XMLAPI```类，提供了XML解析生成的全部操作。非常简单，直接看代码：
```java
@Test
public void testXML(){
  //设置标签类（之前定义的User、Books等）的包路径
  XMLAPI.changPackageName("lee.hua.databinding.xml_parse");
  //调用XMLAPI.readXML()读取指定路径的XML文件
  User userBean = (User) XMLAPI.readXML("/Users/lijie/Desktop/f.xml");
  //打印user对象信息
  System.out.println("XMLParser====="+ userBean);
}
```
输出：
```log
XMLParser=====UserBean{name='test', age=18, marry=true, married=false, 
books=Books{books=[Book{name='java'}, Book{name='android'}]}, 
phones=[Phone{phoneNum='110110110', type='home'}, 
Phone{phoneNum='221221221', type='company'}]}
```
分析：
> 我们可以发现Ignore和XmlAttribute的解析效果已经出来，User的属性married忽略解析，并且Phone的phoneNum属性也被成功赋值

### 生成XML文件
XML的生成相对更加简单，只需要调用```XMLAPI.writeObj2Xml(Object object,String ouputPath)```，当然前提是：提前定义好XmlBean对象。
上代码：
```java
 @Test
 public void testXML(){
	   //读取f.xml文件，文件内容就是上面定义的User
     XMLAPI.changPackageName("lee.hua.databinding.xml_parse");
     File file = new File("/Users/lijie/Desktop/f.xml");
     User userBean = (User) XMLAPI.readXML(new FileInputStream(file));
     System.out.println("XMLParser====="+ userBean);
	   //为解析出的对象新加一本书，名称为XMLParser，输出到f2.xml
     userBean.books.getBooks().add(new Book("XMLParser"));
     XMLAPI.writeObj2Xml(userBean,"/Users/lijie/Desktop/f2.xml");
 }
```
结果：
```xml
<?xml version="1.0" encoding="UTF-8"?>

<User name="test" age="18" marry="true">
    <Books>
        <Book name="java"/>
        <Book name="android"/>
        <Book name="XMLParser"/>
    </Books>
    <Phone number="110110110" type="home"/>
    <Phone number="221221221" type="company"/>
</User>
```
分析：
> 根据XML文件内容可以发现，被Ignore标记的married没有输出，并且Phone中的phoneNum也是按照name="number"输出的。

### 总结
以上就是XMLParser的基本用法，本猿工作两年，能力有限，程序中还有很多需要优化修改，望各位多多支持。

