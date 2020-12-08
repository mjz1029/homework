namespace Test3_1
{
    partial class 用户登录
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要修改
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(用户登录));
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.user = new System.Windows.Forms.TextBox();
            this.password = new System.Windows.Forms.TextBox();
            this.determine = new System.Windows.Forms.Button();
            this.cancel = new System.Windows.Forms.Button();
            this.remeberPassword = new System.Windows.Forms.CheckBox();
            this.SuspendLayout();
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.BackColor = System.Drawing.Color.Transparent;
            this.label2.Font = new System.Drawing.Font("宋体", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label2.ForeColor = System.Drawing.Color.Black;
            this.label2.Location = new System.Drawing.Point(173, 111);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(85, 24);
            this.label2.TabIndex = 1;
            this.label2.Text = "账号：";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.BackColor = System.Drawing.Color.Transparent;
            this.label3.Font = new System.Drawing.Font("宋体", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label3.ForeColor = System.Drawing.Color.Black;
            this.label3.Location = new System.Drawing.Point(173, 177);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(85, 24);
            this.label3.TabIndex = 2;
            this.label3.Text = "密码：";
            // 
            // user
            // 
            this.user.Location = new System.Drawing.Point(293, 110);
            this.user.Name = "user";
            this.user.Size = new System.Drawing.Size(226, 25);
            this.user.TabIndex = 3;
            // 
            // password
            // 
            this.password.Location = new System.Drawing.Point(293, 176);
            this.password.Name = "password";
            this.password.PasswordChar = '*';
            this.password.Size = new System.Drawing.Size(226, 25);
            this.password.TabIndex = 4;
            this.password.TextChanged += new System.EventHandler(this.password_TextChanged);
            // 
            // determine
            // 
            this.determine.BackColor = System.Drawing.Color.Transparent;
            this.determine.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.determine.ForeColor = System.Drawing.Color.Black;
            this.determine.Location = new System.Drawing.Point(293, 226);
            this.determine.Name = "determine";
            this.determine.Size = new System.Drawing.Size(90, 37);
            this.determine.TabIndex = 5;
            this.determine.Text = "登录";
            this.determine.UseVisualStyleBackColor = false;
            this.determine.Click += new System.EventHandler(this.determine_Click);
            // 
            // cancel
            // 
            this.cancel.BackColor = System.Drawing.Color.Transparent;
            this.cancel.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.cancel.ForeColor = System.Drawing.Color.Black;
            this.cancel.Location = new System.Drawing.Point(428, 226);
            this.cancel.Name = "cancel";
            this.cancel.Size = new System.Drawing.Size(91, 37);
            this.cancel.TabIndex = 6;
            this.cancel.Text = "取消";
            this.cancel.UseCompatibleTextRendering = true;
            this.cancel.UseVisualStyleBackColor = false;
            this.cancel.Click += new System.EventHandler(this.cancel_Click);
            // 
            // remeberPassword
            // 
            this.remeberPassword.AutoSize = true;
            this.remeberPassword.BackColor = System.Drawing.Color.Transparent;
            this.remeberPassword.Location = new System.Drawing.Point(553, 183);
            this.remeberPassword.Name = "remeberPassword";
            this.remeberPassword.Size = new System.Drawing.Size(89, 19);
            this.remeberPassword.TabIndex = 7;
            this.remeberPassword.Text = "记住密码";
            this.remeberPassword.UseVisualStyleBackColor = false;
            this.remeberPassword.CheckedChanged += new System.EventHandler(this.checkBox1_CheckedChanged);
            // 
            // 用户登录
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = global::Test3_1.Properties.Resources.csust;
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.remeberPassword);
            this.Controls.Add(this.cancel);
            this.Controls.Add(this.determine);
            this.Controls.Add(this.password);
            this.Controls.Add(this.user);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.ForeColor = System.Drawing.Color.DarkBlue;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.ImeMode = System.Windows.Forms.ImeMode.Off;
            this.Name = "用户登录";
            this.RightToLeftLayout = true;
            this.Text = "学生成绩信息管理系统";
            this.Load += new System.EventHandler(this.用户登录_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox user;
        private System.Windows.Forms.TextBox password;
        private System.Windows.Forms.Button determine;
        private System.Windows.Forms.Button cancel;
        private System.Windows.Forms.CheckBox remeberPassword;

    }
}

