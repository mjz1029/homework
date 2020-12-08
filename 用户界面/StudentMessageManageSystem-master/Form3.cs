using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.OracleClient;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Test3_1
{
    public partial class Form3 : Form
    {
        string user;
        string mystr = "Data Source=ORCL;user Id=c##tiger;Password=tiger";
        public Form3()
        {
            InitializeComponent();
        }
        public Form3(string s)
        {
            this.user = s;
            InitializeComponent();
        }

        private void Form3_Load(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void textBox3_TextChanged(object sender, EventArgs e)
        {
            
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            
        }

        private void close_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {
            
            
        }
        public bool isInputCorrect()
        {
            if (String.IsNullOrEmpty(textBox1.Text))
            {
                MessageBox.Show("原密码不能为空");
                return false;
            }
            if (String.IsNullOrEmpty(textBox2.Text))
            {
                MessageBox.Show("新密码不能为空");
                return false;
            }
            if (String.IsNullOrEmpty(textBox3.Text))
            {
                MessageBox.Show("重置密码不能为空");
                return false;
            }
            if (!textBox3.Text.Equals(textBox2.Text))
            {
                MessageBox.Show("两次密码不一样");
                return false;
            }
            return true;
        }

        private void submit_Click(object sender, EventArgs e)
        {
            if (isInputCorrect()) {                     //如果输入正确
                OracleConnection conn = null;
                OracleDataReader reader = null;
                string originPwd = textBox1.Text.Trim();
                string updatePwd = textBox2.Text.Trim();
                try
                {
                    conn = new OracleConnection(mystr);
                    conn.Open();
                    string selectSql = "select * from users where UserNo='{0}' and Password='{1}'";
                    selectSql = String.Format(selectSql, user, originPwd);
                    reader = new OracleCommand(selectSql, conn).ExecuteReader();
                    MessageBox.Show(selectSql);
                    if (reader.Read())
                    {
                        reader.Close();
                        string updateSql = "update users set password='{0}' where userno='{1}'";
                        
                        updateSql = String.Format(updateSql, updatePwd, user);
                        reader= new OracleCommand(updateSql, conn).ExecuteReader();
                        if (reader.Read())
                        {
                            reader.Close();
                            MessageBox.Show("修改密码成功");
                        }
                        else
                        {
                            MessageBox.Show("修改密码未成功");
                        }
                    }
                    else
                    {
                        reader.Close();
                        MessageBox.Show("原密码错误");
                    }
                }
                catch(Exception ex)
                {
                    MessageBox.Show(ex.Message);
                }
                finally
                {
                    conn.Close();
                }
            }
        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }
    }
}
