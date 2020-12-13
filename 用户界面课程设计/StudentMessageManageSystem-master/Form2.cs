using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.OracleClient;
using System.Drawing;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;

namespace Test3_1
{
    public partial class Form2 : Form
    {
        string userr;
        string mystr = "Data Source=ORCL;user Id=c##tiger;Password=tiger";
        public Form2()
        {
            InitializeComponent();
            dataGridView1.AutoGenerateColumns = false;
        }
        public Form2(string userr)
        {
            this.userr = userr;
            InitializeComponent();
            dataGridView1.AutoGenerateColumns = false;
        }

        private void Form2_Load(object sender, EventArgs e)
        {

            tab1_Load(mystr);                                          //设置tab1绑定源
            

            tab2_Load(mystr);                                          //设置tab2绑定源
           

            tab3_Load(mystr);                                                   //设置tab3绑定源
            
        }
        private void tab1_Load(string mystr)             //加载学生信息表
        {
            DataSet studentDs = new DataSet();
            string sql = "select * from Students";
            using (OracleDataAdapter da = new OracleDataAdapter(sql, mystr))
            {
                da.Fill(studentDs);
            }
            try
            {
                dataGridView1.DataSource = null;            //将绑定关系设置为空
                studentNumber.DataBindings.Clear();
                name.DataBindings.Clear();
                sexKind.DataBindings.Clear();
                subject.DataBindings.Clear();

                dataGridView1.DataSource = studentDs.Tables[0];        //datagridview1绑定源
                studentNumber.DataBindings.Add("Text", studentDs.Tables[0], "studentno");  //设置学号绑定源
                name.DataBindings.Add("Text", studentDs.Tables[0], "StudentName");         //姓名添加绑定源
                sexKind.DataBindings.Add("Text", studentDs.Tables[0], "sexKind");      //设置性别绑定源
                subject.DataBindings.Add("Text", studentDs.Tables[0], "Major");       //设置专业绑定源
            }
            catch (Exception ex)
            {
                MessageBox.Show("students: " + ex.Message);
            }
        }
        private void tab2_Load(string mystr)                    //记载课程信息表
        {
            DataSet courseDs = new DataSet();
            string courseSql = "select * from courses";      //查找课程表sql
            using (OracleDataAdapter da = new OracleDataAdapter(courseSql, mystr))
            {
                da.Fill(courseDs);
            }
            try
            {
                dataGridView2.DataSource = null;            //将原有绑定关系设置为空
                courseNo.DataBindings.Clear();
                courseName.DataBindings.Clear();
                credit.DataBindings.Clear();

                dataGridView2.DataSource = courseDs.Tables[0];                    //课程表gridview添加绑定源
                courseNo.DataBindings.Add("Text", courseDs.Tables[0], "courseno");   //课程号添加绑定源
                courseName.DataBindings.Add("Text", courseDs.Tables[0], "courseName"); //课程名添加绑定源
                credit.DataBindings.Add("Text", courseDs.Tables[0], "credit");        //学分添加绑定源
            }
            catch (Exception ex)
            {
                MessageBox.Show("course:" + ex.Message);
            }
        }
        private void tab3_Load(string mystr)                //加载成绩信息表
        {
            DataSet ds = new DataSet();
            string scoreSql = "select scores.studentno,studentname,scores.courseno,coursename,score" +  //查找成绩表sql
                               " from COURSES, STUDENTS, scores " +
                           "  where students.studentno = scores.studentno and courses.courseno = scores.courseno";      //查找成绩表sql
            using (OracleDataAdapter da = new OracleDataAdapter(scoreSql, mystr))
            {
                da.Fill(ds);
            }
            try
            {
                dataGridView3.DataSource = null;                    //将原有绑定关系设置为空
                stunumber.DataBindings.Clear();
                courseNu.DataBindings.Clear();
                grade.DataBindings.Clear();

                dataGridView3.DataSource = ds.Tables[0];                          //成绩表gridview添加绑定源
                stunumber.DataBindings.Add("Text", ds.Tables[0], "studentno");   //学号添加绑定源
                courseNu.DataBindings.Add("Text", ds.Tables[0], "courseno");     //课程号添加绑定源
                grade.DataBindings.Add("Text", ds.Tables[0], "score");           //成绩添加绑定源
            }
            catch (Exception ex)
            {
                MessageBox.Show("score:" + ex.Message);
            }
        }

        private void studentUorC_Click(object sender, EventArgs e)
        {
            String stu = studentNumber.Text.Trim();
            String nam = name.Text.Trim();
            String sex = sexKind.Text.Trim();
            String sub = subject.Text.Trim();
            OracleConnection conn = null;
            OracleDataReader reader = null;
            try
            {
                conn = new OracleConnection(mystr);
                conn.Open();
                {
                    string selectSql = "select * from students where STUDENTNO='{0}'";
                    selectSql = String.Format(selectSql, stu);
                    reader = new OracleCommand(selectSql, conn).ExecuteReader();
                    if (reader.Read())  //判断sql语句是否执行成功
                    {                                       //记录已存在
                        reader.Close();
                        string updateSql = "update students set  STUDENTNAME='{0}',SEXKIND='{1}', MAJOR='{2}' where  STUDENTNO={3}";
                        updateSql = String.Format(updateSql, nam, sex, sub, stu);
                        int update = new OracleCommand(updateSql, conn).ExecuteNonQuery();
                        if (update > 0)
                        {
                            MessageBox.Show("更新了学生表的一条记录");
                            tab1_Load(mystr);
                            //dataGridView1_Load(mystr, ds);
                        }
                        else if (update == -1)
                        {
                            MessageBox.Show("update语句执行错误");
                        }
                        else
                        {
                            MessageBox.Show("没有修改任何记录");
                        }
                    }
                    else
                    {
                        reader.Close();
                        string insertSql = "insert into students(studentno,studentname,sexkind,major) values({0},'{1}','{2}','{3}')";
                        insertSql = String.Format(insertSql, stu, nam, sex, sub);
                        int insert = new OracleCommand(insertSql, conn).ExecuteNonQuery();
                        if (insert > 0)
                        {
                            
                            MessageBox.Show("增加了一条学生记录");
                            tab1_Load(mystr);
                        }
                        else if (insert == -1)
                        {
                            MessageBox.Show("insert语句执行错误");
                        }
                        else
                        {
                            MessageBox.Show("未增加学生记录");
                        }
                    }

                }
            }
            catch (Exception e1)
            {
                MessageBox.Show(e1.Message);
            }
            finally
            {
                conn.Close();
            }
        }
        private void delete_Click(object sender, EventArgs e)           //学生表删除记录
        {
            String stu = studentNumber.Text.Trim();
            if (MessageBox.Show("是否删除学号为"+ stu+"的学生", "确认", MessageBoxButtons.YesNo) == DialogResult.Yes)
            {
                OracleConnection conn = null;
                OracleDataReader reader = null;
                try
                {
                    conn = new OracleConnection(mystr);
                    conn.Open();
                    {
                        string selectSql = "select * from students where STUDENTNO='{0}'";
                        selectSql = String.Format(selectSql, stu);
                        reader = new OracleCommand(selectSql, conn).ExecuteReader();
                        if (reader.Read())  //判断查询sql语句是否执行成功
                        {                                       //记录已存在
                            reader.Close();

                            string selectGradeSql = "select * from scores where studentno='{0}'";
                            selectGradeSql = String.Format(selectGradeSql, stu);
                            if (new OracleCommand(selectGradeSql, conn).ExecuteScalar() == null)
                            { //判断是否有该学号的成绩记录
                                //没有该学号的成绩记录
                                string deleteSql = "delete students where  STUDENTNO={0}";
                                deleteSql = String.Format(deleteSql, stu);
                                int update = new OracleCommand(deleteSql, conn).ExecuteNonQuery();
                                if (update > 0)
                                {
                                    MessageBox.Show("删除了学生表的一条记录");
                                    tab1_Load(mystr);
                                }
                                else if (update == -1)
                                {
                                    MessageBox.Show("delete语句执行错误");
                                }
                                else
                                {
                                    MessageBox.Show("没有删除任何记录");
                                }
                            }
                            else
                            {
                                MessageBox.Show("存在该学生的成绩记录,不能删除");
                            }
                        }
                        else
                        {
                            reader.Close();
                            MessageBox.Show("不存在学号的记录");
                        }

                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show("deleteStudent" + ex.Message);
                }
                finally
                {
                    conn.Close();
                }
            }
        }
        private void courseUorC_Click(object sender, EventArgs e)       //课程表更新或创建记录
        {
            String couNo = courseNo.Text.Trim();
            String courName = courseName.Text.Trim();
            String cred = credit.Text.Trim();
            OracleConnection conn = null;
            OracleDataReader reader = null;
            try
            {
                conn = new OracleConnection(mystr);
                conn.Open();
                {
                    string selectSql = "select * from courses where courseno='{0}'";
                    selectSql = String.Format(selectSql, couNo);
                    reader = new OracleCommand(selectSql, conn).ExecuteReader();
                    if (reader.Read())  //判断sql语句是否执行成功
                    {                                       //记录已存在
                        reader.Close();
                        string updateSql = "update courses set  coursename='{0}',credit='{1}' where  courseno={2}";
                        updateSql = String.Format(updateSql, courName,cred ,couNo );
                        int update = new OracleCommand(updateSql, conn).ExecuteNonQuery();
                        if (update > 0)
                        {
                            MessageBox.Show("更新了课程表的一条记录");
                            tab2_Load(mystr);

                        }
                        else if (update == -1)
                        {
                            MessageBox.Show("update语句执行错误");
                        }
                        else
                        {
                            MessageBox.Show("没有修改任何记录");
                        }
                    }
                    else
                    {
                        reader.Close();
                        string insertSql = "insert into courses values('{0}','{1}','{2}')";
                        insertSql = String.Format(insertSql, couNo, courName,cred);
                        int insert = new OracleCommand(insertSql, conn).ExecuteNonQuery();
                        if (insert > 0)
                        {
                            MessageBox.Show("增加了一条课程记录");
                            tab2_Load(mystr);
                        }
                        else if (insert == -1)
                        {
                            MessageBox.Show("insert语句执行错误");
                        }
                        else
                        {
                            MessageBox.Show("未增加课程记录");
                        }
                    }

                }
            }
            catch (Exception e1)
            {
                MessageBox.Show(e1.Message);
            }
            finally
            {
                conn.Close();
            }
        }

        private void courseDelete_Click(object sender, EventArgs e)     //课程表删除记录
        {
            String couNo = courseNo.Text.Trim();
            if (MessageBox.Show("是否删除课程号为"+ couNo + "的课程", "确认", MessageBoxButtons.YesNo) == DialogResult.Yes)
            {
                OracleConnection conn = null;
                OracleDataReader reader = null;
                try
                {
                    conn = new OracleConnection(mystr);
                    conn.Open();
                    {
                       
                        string selectSql = "select * from courses where courseno='{0}'";
                        selectSql = String.Format(selectSql, couNo);
                        reader = new OracleCommand(selectSql, conn).ExecuteReader();
                        if (reader.Read())  //判断查询sql语句是否执行成功
                        {                                       //记录已存在
                            reader.Close();

                            string selectGradeSql = "select * from scores where courseno='{0}'";
                            selectGradeSql = String.Format(selectGradeSql, couNo);
                            if (new OracleCommand(selectGradeSql, conn).ExecuteScalar() == null)
                            { //判断是否有该课程号的成绩记录
                                //没有该课程号的成绩记录
                                string deleteSql = "delete courses where  courseno={0}";
                                deleteSql = String.Format(deleteSql, couNo);
                                int delete = new OracleCommand(deleteSql, conn).ExecuteNonQuery();
                                if (delete > 0)
                                {
                                    MessageBox.Show("删除了课程表的一条记录");
                                    tab2_Load(mystr);
                                }
                                else if (delete == -1)
                                {
                                    MessageBox.Show("delete语句执行错误");
                                }
                                else
                                {
                                    MessageBox.Show("没有删除任何记录");
                                }
                            }
                            else
                            {
                                MessageBox.Show("存在该课程的成绩记录,不能删除");
                            }
                        }
                        else
                        {
                            reader.Close();
                            MessageBox.Show("不存在课程号的记录");
                        }

                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show("deleteCourse" + ex.Message);
                }
                finally
                {
                    conn.Close();
                }
            }
        }

        private void gradeUorC_Click(object sender, EventArgs e)        //成绩表更新或创建记录
        {
            String stunum = stunumber.Text.Trim();
            String cournu = courseNu.Text.Trim();
            String grad = grade.Text.Trim();
            OracleConnection conn = null;
            OracleDataReader reader = null;
            try
            {
                conn = new OracleConnection(mystr);
                conn.Open();
                {
                    string selectSql = "select * from scores where courseno='{0}' and studentno='{1}'";
                    selectSql = String.Format(selectSql, cournu,stunum);
                    reader = new OracleCommand(selectSql, conn).ExecuteReader();
                    if (reader.Read())  //判断sql语句是否执行成功
                    {                                       //记录已存在
                        reader.Close();
                        string updateSql = "update scores set  credit='{2}' where  courseno='{0}' and studentno='{1}";
                        updateSql = String.Format(updateSql, cournu, stunum,grad);
                        int update = new OracleCommand(updateSql, conn).ExecuteNonQuery();
                        if (update > 0)
                        {
                            MessageBox.Show("更新了成绩表的一条记录");
                            tab3_Load(mystr);
                        }
                        else if (update == -1)
                        {
                            MessageBox.Show("update语句执行错误");
                        }
                        else
                        {
                            MessageBox.Show("没有修改任何记录");
                        }
                    }
                    else
                    {
                        reader.Close();
                        string insertSql = "insert into scores values('{0}','{1}','{2}')";
                        insertSql = String.Format(insertSql, stunum, cournu, grad);
                        int insert = new OracleCommand(insertSql, conn).ExecuteNonQuery();
                        if (insert > 0)
                        {
                            MessageBox.Show("增加了一条成绩记录");
                            tab3_Load(mystr);
                        }
                        else if (insert == -1)
                        {
                            MessageBox.Show("insert语句执行错误");
                        }
                        else
                        {
                            MessageBox.Show("未增加成绩记录");
                        }
                    }

                }
            }
            catch (Exception e1)
            {
                MessageBox.Show(e1.Message);
            }
            finally
            {
                conn.Close();
            }
        }

        private void button1_Click(object sender, EventArgs e)          //成绩表删除记录
        {
            String stunum = stunumber.Text.Trim();
            String cournu = courseNu.Text.Trim();
            if (MessageBox.Show("是否删除课程号为" + cournu +",学号为"+stunum+ "的成绩", "确认", MessageBoxButtons.YesNo) == DialogResult.Yes)
            {
                OracleConnection conn = null;
                OracleDataReader reader = null;
                try
                {
                    conn = new OracleConnection(mystr);
                    conn.Open();
                    {
                        string selectSql = "select * from scores where courseno='{0}' and studentno='{1}'";
                        selectSql = String.Format(selectSql, cournu,stunum);
                        reader = new OracleCommand(selectSql, conn).ExecuteReader();
                        if (reader.Read())  //判断查询sql语句是否执行成功
                        {                                       //记录已存在
                            reader.Close();
                            string deleteSql = "delete scores where courseno='{0}' and studentno='{1}'";
                            deleteSql = String.Format(deleteSql, cournu, stunum);
                            int delete = new OracleCommand(deleteSql, conn).ExecuteNonQuery();
                            if (delete > 0)
                            {
                                MessageBox.Show("删除了成绩表的一条记录");
                                tab3_Load(mystr);

                            }
                            else if (delete == -1)
                            {
                                MessageBox.Show("delete语句执行错误");
                            }
                            else
                            {
                                MessageBox.Show("没有删除任何记录");
                            }
                            
                        }
                        else
                        {
                            reader.Close();
                            MessageBox.Show("不存在学号="+stunum+",课程号="+cournu+"的记录");
                        }

                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show("deleteGrade" + ex.Message);
                }
                finally
                {
                    conn.Close();
                }
            }
        }

        

        private void studentNumber_TextChanged(object sender, EventArgs e)  //判断学号是否是一个正整数                                                         
        {
            
            if (!isInteger(studentNumber.Text.Trim()))
            {
                MessageBox.Show("输入的学号不是一个正整数");
            }

        }
        private void name_TextChanged(object sender, EventArgs e)       //判断姓名长度
        {
            if (name.Text.Length < 1 || name.Text.Length > 9)
            {
                MessageBox.Show("姓名字符串长度在1-9");
            }
        }

        private void sexKind_SelectedIndexChanged(object sender, EventArgs e)   //判断性别和专业是否选了
        {
            if (sexKind.Text == null||subject.Text == null)
            {
                MessageBox.Show("没有选择性别或专业.");
            }
        }

        private void subject_SelectedIndexChanged(object sender, EventArgs e)       //判断性别和专业是否选了
        {
            sexKind_SelectedIndexChanged(sender,e);
        }

        private void courseNo_TextChanged(object sender, EventArgs e)           //判断课程号是否为一个正整数
        {
            if (!isInteger(courseNo.Text.Trim()))
            {
                MessageBox.Show("课程号不是一个正整数");
            }
        }

        private void credit_TextChanged(object sender, EventArgs e)
        {
            
            Regex regex = new Regex("^(-?\\d+)(\\.\\d+)?$");     //判断是否为实数的正则表达式
            if (!regex.IsMatch(credit.Text.Trim()))
            {
                MessageBox.Show("学分不是一个实数");
            }
        }
        
        private void stunumber_TextChanged(object sender, EventArgs e)  //判断学号是否时一个正整数
        {
            if (!isInteger(stunumber.Text.Trim()))
            {
                MessageBox.Show("输入的学号不是一个正整数");
            }
        }

        private void courseNu_TextChanged(object sender, EventArgs e)       //判断课程号是否为一个整数
        {
            if (!isInteger(courseNu.Text.Trim()))
            {
                MessageBox.Show("课程号不是一个正整数");
            }
        }
        private void grade_TextChanged(object sender, EventArgs e)          //判断成绩是否为一个整数
        {
            if (!isInteger(grade.Text.Trim()))
            {
                MessageBox.Show("成绩不是一个正整数");
            }
            else 
            {
                int inge = int.Parse(grade.Text.Trim());
                if (inge < 0 && inge > 100)
                {
                    MessageBox.Show("成绩在0-100范围之内");
                }
            }
            
        }


        private bool isInteger(string s)
        {
            Regex regex = new Regex("^[0-9]*[1-9][0-9]*$");     //判断是否为正整数的正则表达式
            return regex.IsMatch(s);
        }

        private void 重置密码ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            string user = userr;
            Form3 form3 = new Form3(user);
            form3.ShowDialog();
        }
    }
}
