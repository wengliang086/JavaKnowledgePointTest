-- https://blog.csdn.net/njyr21/article/details/79358938

-- 1、 取得每个部门最高薪水人员的名称
-- 2、 哪些人的薪水在部门平均薪水之上？
-- 3、 取得部门中（所有人的）平均的薪水等级
-- 4、 求最高薪水（给出两种解决方案）
-- 5、 取得平均薪水最高的部门和部门编号（至少给出两种解决方案）
-- 6、 取得平均薪水最高的部门和部门名称
-- 7、 求平均薪水等级最低的部门的部门名称
-- 8、 取得比普通员工（院代码没有在mgr字段出现的）的最高薪水还要高的领导人姓名
-- 9、 取得薪水最高的前五名员工
-- 10、取得薪水最高的第六到第十名员工
-- 11、取得最后入职的5名员工
-- 12、取得每个薪水等级有多少员工

-- 14、列出所有员工及领导的姓名
-- 15、列出受雇日期早于其直接上级的所有员工的编号、姓名、部门名称
-- 16、列出部门名称和这些部门的员工信息，同时列出那些没有员工的部门
-- 17、列出至少有5个员工的所有部门
-- 18、列出薪金比‘simith’多的所有员工信息
-- 19、列出所有‘clerk’（办事员）的姓名及部门名称，部门人数
-- mysql> select e.ename,d.dname,c.deptcount
--     -> from (select *from emp where job = 'clerk') e
--     -> join dept d one.deptno = d.deptno
--     -> join (selectdeptno,count(deptno) deptcount from emp group by deptno) c
--     -> on e.deptno =c.deptno;
-- 20、列出最低薪金大于1500的各种工作及从事此工作的全部雇员人数
-- 21、列出在部门‘sales’（销售部）工作的员工的姓名，假定不知道销售部的部门编号
-- 22、列出薪金高于公司平均薪金的所有员工，所在部门，上级领导，雇员的工资等级
-- 23、列出与‘scott’从事相同工作的所有员工及部门名称
-- 24、列出薪金等于部门30中员工的薪金的其他员工的姓名和薪金
-- 25、列出薪金高于在部门30工作的所有员工的薪金的员工姓名和薪金，部门名称
-- 26、列出在每个部门工作的员工数量，平均工资和平均服务期限
-- 27、列出所有员工的姓名、部门名称、工资
-- 28、列出所有部门的详细信息和人数
-- 29、列出各种工作的最低工资以及从事此工作的雇员姓名
-- 30、列出各个部门的manager的最低薪金
-- 31、列出员工的年工资，按年薪从低到高排序
-- 32、求出员工领导的薪水超过3000的员工名称与领导姓名
-- 33、求出部门名称中，带有‘s’字符的部门员工的工资合计、部门人数
-- 34、给任职日期超过35年的员工加薪10%
mysql> create table emp_bak as select * from emp;
mysql> update emp_bak set sal = sal * 1.1 where((to_days(now()) - to_days(hiredate))/365) > 35;
mysql> select * from emp_bak;

35、求部门平均薪水，和平均薪水对应的等级
SELECT deptno, AVG(sal) as avg from emp GROUP BY deptno;
SELECT grade from salgrade;
SELECT a.deptno,a.avg,s.grade FROM (SELECT deptno, AVG(sal) as avg from emp GROUP BY deptno) a JOIN salgrade s ON a.avg between s.losal and s.hisal;
