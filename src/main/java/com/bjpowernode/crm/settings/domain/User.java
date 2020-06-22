package com.bjpowernode.crm.settings.domain;

/**
 * lzx
 * 2020/2/13
 */
public class User {

     /*

        小专题

        关于主键
        我们之前的开发，一直使用的是什么类型当作主键类型的呢？

        int:整型

        我们为什么使用int类型，是因为整型作为主键，有着独特的优势，就是可以 自动递增

        tbl_student
        id      name        age
        1       wyf         23
        2       lh          24
        3       hzt         25
        4       zyx         26
        5       lyf         27

        做添加操作
        insert into tbl_student(id,name,age) values(6,'cxk',23)
        如果id为整型，id就不用填写，可以自动递增
        sql语句变成了：
        insert into tbl_student(name,age) values('cxk',23)

        tbl_student
        id      name        age
        1       wyf         23
        2       lh          24
        3       hzt         25
        4       zyx         26
        5       lyf         27
        6       cxk         23

        6:数据库系统自动帮我们添的
        之所以为我们添了6，是因为mysql有着自己的表（不是我们创建的，是mysql自己使用的表），在mysql自己的表中
        以前保存了5，当我们新增一条记录之后，mysql系统会将自己表中以前的5取出来，+1，变成了6

        对于主键自动递增，有着比较明显的缺点：
        （1）每一次我们在做添加的时候，其实我们操作的不仅仅只是我们自己的student表，而且还有mysql自己的表
                添加流程：
                mysql自己的表查询操作 5             select
                5+1=6,tbl_student表执行添加操作        insert
                一旦添加学生成功之后，mysql自己的表需要将5更新成为6 update

              通过分析原理，得出结论，使用主键自动递增会降低我们添加数据的效率

        （2）整型主键的自动递增，肯定都是数值，数值会有上限，如果遇到海量级别的数据，需要特殊处理，比较麻烦。

        （3）关于数据库的移植问题

            mysql --> oracle

            以前我们使用mysql，做添加操作，使用的都是mysql数据库提供的自动递增机制
            insert into tbl_student(name,age) values('cxk',23)

            我们现在将项目移植到了Oracle数据库中进行数据管理
            关于这条语句，id我们没有写
            insert into tbl_student(name,age) values('cxk',23)
            oracle识别吗？
            不识别，因为这种简单的主键自动递增的机制，是mysql独有的
                    oracle数据库是由主键自动递增的，但是使用的序列的机制

            假设你在项目中有200处添加操作，这200处添加操作，都得改

            我们如果还是使用以前的，不省略主键的方式
            insert into tbl_student(id,name,age) values(6,'cxk',23)
            就不会担心数据库移植的问题了

            如果我们自己手动填写主键，那么使用整型就没有任何意义了

        总结：
            使用整型类型主键，自动递增机制虽然能够有效的简化开发，但是也有着很多很明显的缺点
            未来的实际项目开发，如果我们遇到了比较简单的表结构，以及比较少的数据量，那么我们就可以使用整型主键，自动递增

            大多数表数据都会非常庞大，而且要求有较高的添加效率，那么我们就不会去选择使用整型自动递增的机制

            我们可以选择使用字符串类型的主键，字符串类型的主键也是实际项目开发中，使用最多的一种方式。


        主键（约束）：
            非空约束+唯一约束

            如何确保主键的唯一性？

            随机数：2837412834812734 不确定性
            时间：20200213093740123 并发性

        在实际项目开发中，我们生成主键使用的是JDK为我们提供的UUID的机制
        java.util包为我们提供的UUID，是随机生成的由数字、字母、中划线(-)所组成的36位的随机串
        这36位的随机串，一定是全世界唯一的，绝对不会重复


        问题：

        1.UUID生成的字符串为什么是全世界唯一的？
            构成UUID是一种非常复杂的算法（数学）
            构成以上算法，需要3个条件
            随机数
            时间
            你当前用来生成UUID的硬件，它的出厂机器（产品）编码

        2.做表设计的时候，如果主键使用的是UUID的机制，应该为主键设置为什么类型？

            字符串

            UUID:32位
            varchar和char都能存
            但是我们一定选择使用的是char，因为char类型的字符串执行的效率要远远的高于varchar

            aaa

            varchar(32):变长 "aaa"

            char(32):定长 "aaa                        "

        =========================================================================================================

        关于日期及时间操作

        在未来的实际项目开发中，只要是使用日期及时间，使用字符串来进行的表述方式，是有着约定俗成的规则的。

        日期：yyyy-MM-dd 10位
        日期+时间：yyyy-MM-dd HH:mm:ss 19位

        =========================================================================================================

        关于登录

        //方式1：不推荐，太麻烦
        1.验证账号和密码
            sql语句去进行验证
            int count = 执行 select count(*) from tbl_user where loginAct=? and loginPwd=?
            count==0：说明没查到任何信息，说明账号密码错误
            count==1：说明查询到了一条记录，说明账号密码正确
            count>1：说明查询到了多条记录，虽然账号密码正确，但是有其他的垃圾数据的存在

            一旦count==1：说明账号密码正确
            需要继续验证下面的其他验证项

        2.验证失效时间
            怎么取得失效时间？
            String expireTime = select expireTime from tbl_user where loginAct=? and loginPwd=?

        3.锁定状态
            怎么取得锁定状态？
            String lockState = select lockState from tbl_user where loginAct=? and loginPwd=?

        4.验证ip地址有没有存在在允许访问的ip地址群中
            怎么取得允许访问的ip地址群？
            String allowIps = select allowIps from tbl_user where loginAct=? and loginPwd=?

        -----------------------------------------------------------------------------------------

        //方式2：推荐
        1.验证账号和密码
            sql语句去进行验证
            User user = 执行 select * from tbl_user where loginAct=? and loginPwd=?
            user==null：说明没查到任何信息，说明账号密码错误
            user!=null：说明查询到了一条记录，说明账号密码正确

            一旦user!=null：说明账号密码正确
            需要继续验证下面的其他验证项

        2.验证失效时间
            怎么取得失效时间？
            String expireTime = user.get..

        3.锁定状态
            怎么取得锁定状态？
            String lockState = user.get...

        4.验证ip地址有没有存在在允许访问的ip地址群中
            怎么取得允许访问的ip地址群？
            String allowIps = user.get...



     */

private String id; //主键 UUID char(32)
private String loginAct;    //登陆账号
private String name;    //用户的真实姓名
private String loginPwd;    //登陆密码
private String email;   //邮箱
private String expireTime;  //失效时间 -------yyyy-MM-dd HH:mm:ss 19位
private String lockState;   //锁定状态
private String deptno;  //部门编号
private String allowIps;    //允许访问的ip地址群
private String createTime;  //创建时间 -------yyyy-MM-dd HH:mm:ss 19位
private String createBy;    //创建人
private String editTime;    //修改时间 -------yyyy-MM-dd HH:mm:ss 19位
private String editBy;      //修改人

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getLoginAct() {
        return loginAct;
    }

    public User setLoginAct(String loginAct) {
        this.loginAct = loginAct;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public User setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public User setExpireTime(String expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    public String getLockState() {
        return lockState;
    }

    public User setLockState(String lockState) {
        this.lockState = lockState;
        return this;
    }

    public String getDeptno() {
        return deptno;
    }

    public User setDeptno(String deptno) {
        this.deptno = deptno;
        return this;
    }

    public String getAllowIps() {
        return allowIps;
    }

    public User setAllowIps(String allowIps) {
        this.allowIps = allowIps;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public User setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public User setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getEditTime() {
        return editTime;
    }

    public User setEditTime(String editTime) {
        this.editTime = editTime;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public User setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }
}
