using System;
using System.Windows.Forms;
using MySql.Data.MySqlClient;
using System.Data.SqlClient;
using System.Data.OracleClient;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;

namespace Test3_1
{
    public partial class 用户登录 : Form
    {
        public 用户登录()
        {
            InitializeComponent();
        }   

        private void cancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void determine_Click(object sender, EventArgs e)
        {
            string mystr = "Data Source=ORCL;user Id=c##tiger;Password=tiger";

            OracleConnection mycon = null;
            using (mycon = new OracleConnection(mystr)) {
                //打开数据库连接
                mycon.Open();
                
                string usr= user.Text;
                string passwd = password.Text;    //将密码的string类型转换为base64格式
                string sql = "select * from users where userNo = '{0}'";
                sql = String.Format(sql, usr);
                OracleCommand command = new OracleCommand(sql,mycon);
                OracleDataReader reader = command.ExecuteReader();
                //判断SQL语句是否执行成功
                if (reader.Read())
                {
                    reader.Close();
                    string loginSql = "select * from users where UserNo='{0}' and Password='{1}'";
                    loginSql = String.Format(loginSql, usr, passwd);
                    command = new OracleCommand(loginSql, mycon);
                    reader = command.ExecuteReader();
                    if (reader.Read()==true)
                    {
                        reader.Close();
                        MessageBox.Show(" 欢迎使用学生信息管理系统。");
                        
                                                              //验证成功,保存帐号
                        FileStream stream = null;
                        BinaryFormatter formater = null;
                        try
                        {
                            stream= new FileStream("UserInfo.txt", FileMode.OpenOrCreate, FileAccess.Write, FileShare.None);
                            formater= new BinaryFormatter();

                            formater.Serialize(stream, usr);      //如果登陆成功并且记住密码选项勾选的情况下将账号保存在Userinfo.txt下面
                            if (remeberPassword.Checked)                      
                            {
                                string pawd = stringToBase64String(password.Text);
                                formater.Serialize(stream, "true");
                                formater.Serialize(stream, pawd);
                            }
                            else
                            {
                                formater.Serialize(stream, "false");
                            }
                            this.Visible = false;
                            Form2 f = new Form2(user.Text.Trim());  // 创建窗体Form2的对象
                            f.ShowDialog();  // 显示Form2
                            
                        }
                        catch (Exception e1)
                        {
                            MessageBox.Show(e1.Message);
                        }
                        finally {
                            stream.Close();
                            this.Close();
                        }
                    }
                    else
                    {
                        MessageBox.Show("密码错误。");
                    }

                }
                else
                {
                    MessageBox.Show("帐号不存在。");
                }
            }
            
          
       
        }

        private void password_TextChanged(object sender, EventArgs e)
        {

        }

        private void 用户登录_Load(object sender, EventArgs e)
        {
            if (File.Exists("UserInfo.txt"))
            {
                FileStream readstream = null;
                BinaryFormatter formater = null;
                try { 
                    readstream = new FileStream("UserInfo.txt", FileMode.Open, FileAccess.Read, FileShare.Read);
                    formater = new BinaryFormatter();
                    string readdata = (string)formater.Deserialize(readstream);
                    user.Text = readdata;
                    string readdata1 = (string)formater.Deserialize(readstream);
                    if ("true".Equals(readdata1)){
                        remeberPassword.Checked = true;
                        string pawd = (string)formater.Deserialize(readstream);
                        pawd = base64StringToString(pawd);
                        password.Text = pawd;
                    }
                }
                catch(Exception ex)
                {
                    MessageBox.Show(ex.Message);
                }
                finally
                {
                    readstream.Close();
                }
               
            }
        }


        private void checkBox1_CheckedChanged(object sender, EventArgs e)
        {
            if (remeberPassword.Checked)
            {

            }
        }
        public string stringToBase64String(string s)
        {
            try
            {
                byte[] b = System.Text.Encoding.Default.GetBytes(s);
                //转成 Base64 形式的 System.String
                
                return Convert.ToBase64String(b);

            }
            catch 
            {
                return null;
            }
        }

        //base64编码的字符串转为正常字符串
        public string base64StringToString(string a)
        {
            try
            {
                byte[] c = Convert.FromBase64String(a);
                return System.Text.Encoding.Default.GetString(c);
            }
            catch
            {

                return null;
            }
        }
    }
}
