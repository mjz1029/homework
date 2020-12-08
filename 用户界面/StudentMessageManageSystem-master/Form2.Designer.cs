namespace Test3_1
{
    partial class Form2
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.sexKind = new System.Windows.Forms.ComboBox();
            this.subject = new System.Windows.Forms.ComboBox();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.学号 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.姓名 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.性别 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.专业 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.重置密码ToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.delete = new System.Windows.Forms.Button();
            this.studentUorC = new System.Windows.Forms.Button();
            this.name = new System.Windows.Forms.TextBox();
            this.studentNumber = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.courseDelete = new System.Windows.Forms.Button();
            this.courseUorC = new System.Windows.Forms.Button();
            this.credit = new System.Windows.Forms.TextBox();
            this.courseName = new System.Windows.Forms.TextBox();
            this.courseNo = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.dataGridView2 = new System.Windows.Forms.DataGridView();
            this.课程号 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.课程名 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.学分 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.tabPage3 = new System.Windows.Forms.TabPage();
            this.gradeDelete = new System.Windows.Forms.Button();
            this.gradeUorC = new System.Windows.Forms.Button();
            this.grade = new System.Windows.Forms.TextBox();
            this.courseNu = new System.Windows.Forms.TextBox();
            this.stunumber = new System.Windows.Forms.TextBox();
            this.label8 = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.label10 = new System.Windows.Forms.Label();
            this.dataGridView3 = new System.Windows.Forms.DataGridView();
            this.学号1 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.姓名1 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.课程号1 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.课程名1 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.分数 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.tabControl1.SuspendLayout();
            this.tabPage1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.contextMenuStrip1.SuspendLayout();
            this.tabPage2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView2)).BeginInit();
            this.tabPage3.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView3)).BeginInit();
            this.SuspendLayout();
            // 
            // tabControl1
            // 
            this.tabControl1.ContextMenuStrip = this.contextMenuStrip1;
            this.tabControl1.Controls.Add(this.tabPage1);
            this.tabControl1.Controls.Add(this.tabPage2);
            this.tabControl1.Controls.Add(this.tabPage3);
            this.tabControl1.Location = new System.Drawing.Point(12, 12);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(776, 426);
            this.tabControl1.TabIndex = 1;
            // 
            // tabPage1
            // 
            this.tabPage1.Controls.Add(this.sexKind);
            this.tabPage1.Controls.Add(this.subject);
            this.tabPage1.Controls.Add(this.dataGridView1);
            this.tabPage1.Controls.Add(this.delete);
            this.tabPage1.Controls.Add(this.studentUorC);
            this.tabPage1.Controls.Add(this.name);
            this.tabPage1.Controls.Add(this.studentNumber);
            this.tabPage1.Controls.Add(this.label4);
            this.tabPage1.Controls.Add(this.label3);
            this.tabPage1.Controls.Add(this.label2);
            this.tabPage1.Controls.Add(this.label1);
            this.tabPage1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabPage1.Location = new System.Drawing.Point(4, 25);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(768, 397);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "学生信息表";
            this.tabPage1.UseVisualStyleBackColor = true;
            // 
            // sexKind
            // 
            this.sexKind.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.sexKind.FormattingEnabled = true;
            this.sexKind.Items.AddRange(new object[] {
            "男",
            "女"});
            this.sexKind.Location = new System.Drawing.Point(321, 357);
            this.sexKind.Name = "sexKind";
            this.sexKind.Size = new System.Drawing.Size(71, 23);
            this.sexKind.TabIndex = 15;
            this.sexKind.SelectedIndexChanged += new System.EventHandler(this.sexKind_SelectedIndexChanged);
            // 
            // subject
            // 
            this.subject.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.subject.FormattingEnabled = true;
            this.subject.Items.AddRange(new object[] {
            "大数据",
            "信安",
            "网络",
            "计科",
            "通信"});
            this.subject.Location = new System.Drawing.Point(446, 357);
            this.subject.Name = "subject";
            this.subject.Size = new System.Drawing.Size(121, 23);
            this.subject.TabIndex = 14;
            this.subject.SelectedIndexChanged += new System.EventHandler(this.subject_SelectedIndexChanged);
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.学号,
            this.姓名,
            this.性别,
            this.专业});
            this.dataGridView1.ContextMenuStrip = this.contextMenuStrip1;
            this.dataGridView1.Location = new System.Drawing.Point(0, 0);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowHeadersWidth = 51;
            this.dataGridView1.RowTemplate.Height = 27;
            this.dataGridView1.Size = new System.Drawing.Size(765, 347);
            this.dataGridView1.TabIndex = 13;
            // 
            // 学号
            // 
            this.学号.DataPropertyName = "StudentNo";
            this.学号.HeaderText = "学号";
            this.学号.MinimumWidth = 6;
            this.学号.Name = "学号";
            this.学号.Width = 75;
            // 
            // 姓名
            // 
            this.姓名.DataPropertyName = "StudentName";
            this.姓名.HeaderText = "姓名";
            this.姓名.MinimumWidth = 6;
            this.姓名.Name = "姓名";
            this.姓名.Width = 125;
            // 
            // 性别
            // 
            this.性别.DataPropertyName = "SexKind";
            this.性别.HeaderText = "性别";
            this.性别.MinimumWidth = 6;
            this.性别.Name = "性别";
            this.性别.Width = 75;
            // 
            // 专业
            // 
            this.专业.DataPropertyName = "Major";
            this.专业.HeaderText = "专业";
            this.专业.MinimumWidth = 6;
            this.专业.Name = "专业";
            this.专业.Width = 125;
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.contextMenuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.重置密码ToolStripMenuItem});
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(139, 28);
            // 
            // 重置密码ToolStripMenuItem
            // 
            this.重置密码ToolStripMenuItem.Name = "重置密码ToolStripMenuItem";
            this.重置密码ToolStripMenuItem.Size = new System.Drawing.Size(138, 24);
            this.重置密码ToolStripMenuItem.Text = "重置密码";
            this.重置密码ToolStripMenuItem.Click += new System.EventHandler(this.重置密码ToolStripMenuItem_Click);
            // 
            // delete
            // 
            this.delete.Location = new System.Drawing.Point(695, 355);
            this.delete.Name = "delete";
            this.delete.Size = new System.Drawing.Size(55, 28);
            this.delete.TabIndex = 12;
            this.delete.Text = "删除";
            this.delete.UseVisualStyleBackColor = true;
            this.delete.Click += new System.EventHandler(this.delete_Click);
            // 
            // studentUorC
            // 
            this.studentUorC.Location = new System.Drawing.Point(573, 355);
            this.studentUorC.Name = "studentUorC";
            this.studentUorC.Size = new System.Drawing.Size(103, 28);
            this.studentUorC.TabIndex = 11;
            this.studentUorC.Text = "更新/新增";
            this.studentUorC.UseVisualStyleBackColor = true;
            this.studentUorC.Click += new System.EventHandler(this.studentUorC_Click);
            // 
            // name
            // 
            this.name.Location = new System.Drawing.Point(166, 355);
            this.name.Name = "name";
            this.name.Size = new System.Drawing.Size(91, 25);
            this.name.TabIndex = 8;
            this.name.TextChanged += new System.EventHandler(this.name_TextChanged);
            // 
            // studentNumber
            // 
            this.studentNumber.Location = new System.Drawing.Point(57, 356);
            this.studentNumber.Name = "studentNumber";
            this.studentNumber.Size = new System.Drawing.Size(51, 25);
            this.studentNumber.TabIndex = 6;
            this.studentNumber.TextChanged += new System.EventHandler(this.studentNumber_TextChanged);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(410, 361);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(37, 15);
            this.label4.TabIndex = 5;
            this.label4.Text = "专业";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(278, 366);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(37, 15);
            this.label3.TabIndex = 4;
            this.label3.Text = "性别";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(123, 366);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(37, 15);
            this.label2.TabIndex = 3;
            this.label2.Text = "姓名";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(14, 366);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(37, 15);
            this.label1.TabIndex = 2;
            this.label1.Text = "学号";
            // 
            // tabPage2
            // 
            this.tabPage2.Controls.Add(this.courseDelete);
            this.tabPage2.Controls.Add(this.courseUorC);
            this.tabPage2.Controls.Add(this.credit);
            this.tabPage2.Controls.Add(this.courseName);
            this.tabPage2.Controls.Add(this.courseNo);
            this.tabPage2.Controls.Add(this.label7);
            this.tabPage2.Controls.Add(this.label6);
            this.tabPage2.Controls.Add(this.label5);
            this.tabPage2.Controls.Add(this.dataGridView2);
            this.tabPage2.Location = new System.Drawing.Point(4, 25);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(768, 397);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "课程信息表";
            this.tabPage2.UseVisualStyleBackColor = true;
            // 
            // courseDelete
            // 
            this.courseDelete.Location = new System.Drawing.Point(657, 357);
            this.courseDelete.Name = "courseDelete";
            this.courseDelete.Size = new System.Drawing.Size(55, 23);
            this.courseDelete.TabIndex = 22;
            this.courseDelete.Text = "删除";
            this.courseDelete.UseVisualStyleBackColor = true;
            this.courseDelete.Click += new System.EventHandler(this.courseDelete_Click);
            // 
            // courseUorC
            // 
            this.courseUorC.Location = new System.Drawing.Point(537, 357);
            this.courseUorC.Name = "courseUorC";
            this.courseUorC.Size = new System.Drawing.Size(103, 23);
            this.courseUorC.TabIndex = 21;
            this.courseUorC.Text = "更新/新增";
            this.courseUorC.UseVisualStyleBackColor = true;
            this.courseUorC.Click += new System.EventHandler(this.courseUorC_Click);
            // 
            // credit
            // 
            this.credit.Location = new System.Drawing.Point(430, 353);
            this.credit.Name = "credit";
            this.credit.Size = new System.Drawing.Size(74, 25);
            this.credit.TabIndex = 20;
            this.credit.TextChanged += new System.EventHandler(this.credit_TextChanged);
            // 
            // courseName
            // 
            this.courseName.Location = new System.Drawing.Point(228, 353);
            this.courseName.Name = "courseName";
            this.courseName.Size = new System.Drawing.Size(153, 25);
            this.courseName.TabIndex = 19;
            // 
            // courseNo
            // 
            this.courseNo.Location = new System.Drawing.Point(73, 353);
            this.courseNo.Name = "courseNo";
            this.courseNo.Size = new System.Drawing.Size(91, 25);
            this.courseNo.TabIndex = 18;
            this.courseNo.TextChanged += new System.EventHandler(this.courseNo_TextChanged);
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(170, 361);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(52, 15);
            this.label7.TabIndex = 17;
            this.label7.Text = "课程名";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(387, 361);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(37, 15);
            this.label6.TabIndex = 16;
            this.label6.Text = "学分";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(15, 361);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(52, 15);
            this.label5.TabIndex = 15;
            this.label5.Text = "课程号";
            // 
            // dataGridView2
            // 
            this.dataGridView2.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView2.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.课程号,
            this.课程名,
            this.学分});
            this.dataGridView2.Location = new System.Drawing.Point(0, 0);
            this.dataGridView2.Name = "dataGridView2";
            this.dataGridView2.RowHeadersWidth = 51;
            this.dataGridView2.RowTemplate.Height = 27;
            this.dataGridView2.Size = new System.Drawing.Size(768, 347);
            this.dataGridView2.TabIndex = 14;
            // 
            // 课程号
            // 
            this.课程号.DataPropertyName = "courseno";
            this.课程号.HeaderText = "课程号";
            this.课程号.MinimumWidth = 6;
            this.课程号.Name = "课程号";
            this.课程号.Width = 90;
            // 
            // 课程名
            // 
            this.课程名.DataPropertyName = "coursename";
            this.课程名.HeaderText = "课程名";
            this.课程名.MinimumWidth = 6;
            this.课程名.Name = "课程名";
            this.课程名.Width = 125;
            // 
            // 学分
            // 
            this.学分.DataPropertyName = "credit";
            this.学分.HeaderText = "学分";
            this.学分.MinimumWidth = 6;
            this.学分.Name = "学分";
            this.学分.Width = 80;
            // 
            // tabPage3
            // 
            this.tabPage3.Controls.Add(this.gradeDelete);
            this.tabPage3.Controls.Add(this.gradeUorC);
            this.tabPage3.Controls.Add(this.grade);
            this.tabPage3.Controls.Add(this.courseNu);
            this.tabPage3.Controls.Add(this.stunumber);
            this.tabPage3.Controls.Add(this.label8);
            this.tabPage3.Controls.Add(this.label9);
            this.tabPage3.Controls.Add(this.label10);
            this.tabPage3.Controls.Add(this.dataGridView3);
            this.tabPage3.Location = new System.Drawing.Point(4, 25);
            this.tabPage3.Name = "tabPage3";
            this.tabPage3.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage3.Size = new System.Drawing.Size(768, 397);
            this.tabPage3.TabIndex = 2;
            this.tabPage3.Text = "成绩信息表";
            this.tabPage3.UseVisualStyleBackColor = true;
            // 
            // gradeDelete
            // 
            this.gradeDelete.Location = new System.Drawing.Point(660, 357);
            this.gradeDelete.Name = "gradeDelete";
            this.gradeDelete.Size = new System.Drawing.Size(55, 23);
            this.gradeDelete.TabIndex = 30;
            this.gradeDelete.Text = "删除";
            this.gradeDelete.UseVisualStyleBackColor = true;
            this.gradeDelete.Click += new System.EventHandler(this.button1_Click);
            // 
            // gradeUorC
            // 
            this.gradeUorC.Location = new System.Drawing.Point(540, 357);
            this.gradeUorC.Name = "gradeUorC";
            this.gradeUorC.Size = new System.Drawing.Size(103, 23);
            this.gradeUorC.TabIndex = 29;
            this.gradeUorC.Text = "更新/新增";
            this.gradeUorC.UseVisualStyleBackColor = true;
            this.gradeUorC.Click += new System.EventHandler(this.gradeUorC_Click);
            // 
            // grade
            // 
            this.grade.Location = new System.Drawing.Point(433, 353);
            this.grade.Name = "grade";
            this.grade.Size = new System.Drawing.Size(74, 25);
            this.grade.TabIndex = 28;
            this.grade.TextChanged += new System.EventHandler(this.grade_TextChanged);
            // 
            // courseNu
            // 
            this.courseNu.Location = new System.Drawing.Point(231, 353);
            this.courseNu.Name = "courseNu";
            this.courseNu.Size = new System.Drawing.Size(119, 25);
            this.courseNu.TabIndex = 27;
            this.courseNu.TextChanged += new System.EventHandler(this.courseNu_TextChanged);
            // 
            // stunumber
            // 
            this.stunumber.Location = new System.Drawing.Point(61, 353);
            this.stunumber.Name = "stunumber";
            this.stunumber.Size = new System.Drawing.Size(91, 25);
            this.stunumber.TabIndex = 26;
            this.stunumber.TextChanged += new System.EventHandler(this.stunumber_TextChanged);
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(173, 361);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(52, 15);
            this.label8.TabIndex = 25;
            this.label8.Text = "课程号";
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(390, 361);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(37, 15);
            this.label9.TabIndex = 24;
            this.label9.Text = "成绩";
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(18, 361);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(37, 15);
            this.label10.TabIndex = 23;
            this.label10.Text = "学号";
            // 
            // dataGridView3
            // 
            this.dataGridView3.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView3.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.学号1,
            this.姓名1,
            this.课程号1,
            this.课程名1,
            this.分数});
            this.dataGridView3.Location = new System.Drawing.Point(-4, 0);
            this.dataGridView3.Name = "dataGridView3";
            this.dataGridView3.RowHeadersWidth = 51;
            this.dataGridView3.RowTemplate.Height = 27;
            this.dataGridView3.Size = new System.Drawing.Size(772, 347);
            this.dataGridView3.TabIndex = 14;
            // 
            // 学号1
            // 
            this.学号1.DataPropertyName = "StudentNO";
            this.学号1.HeaderText = "学号";
            this.学号1.MinimumWidth = 6;
            this.学号1.Name = "学号1";
            this.学号1.Width = 75;
            // 
            // 姓名1
            // 
            this.姓名1.DataPropertyName = "Studentname";
            this.姓名1.HeaderText = "姓名";
            this.姓名1.MinimumWidth = 6;
            this.姓名1.Name = "姓名1";
            this.姓名1.Width = 125;
            // 
            // 课程号1
            // 
            this.课程号1.DataPropertyName = "courseno";
            this.课程号1.HeaderText = "课程号";
            this.课程号1.MinimumWidth = 6;
            this.课程号1.Name = "课程号1";
            this.课程号1.Width = 95;
            // 
            // 课程名1
            // 
            this.课程名1.DataPropertyName = "coursename";
            this.课程名1.HeaderText = "课程名";
            this.课程名1.MinimumWidth = 6;
            this.课程名1.Name = "课程名1";
            this.课程名1.Width = 125;
            // 
            // 分数
            // 
            this.分数.DataPropertyName = "score";
            this.分数.HeaderText = "分数";
            this.分数.MinimumWidth = 6;
            this.分数.Name = "分数";
            this.分数.Width = 75;
            // 
            // Form2
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.tabControl1);
            this.Name = "Form2";
            this.Text = "Form2";
            this.Load += new System.EventHandler(this.Form2_Load);
            this.tabControl1.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            this.tabPage1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.contextMenuStrip1.ResumeLayout(false);
            this.tabPage2.ResumeLayout(false);
            this.tabPage2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView2)).EndInit();
            this.tabPage3.ResumeLayout(false);
            this.tabPage3.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView3)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;
        private System.Windows.Forms.TabPage tabPage3;
        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.Button delete;
        private System.Windows.Forms.Button studentUorC;
        private System.Windows.Forms.TextBox name;
        private System.Windows.Forms.TextBox studentNumber;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button courseDelete;
        private System.Windows.Forms.Button courseUorC;
        private System.Windows.Forms.TextBox credit;
        private System.Windows.Forms.TextBox courseName;
        private System.Windows.Forms.TextBox courseNo;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.DataGridView dataGridView2;
        private System.Windows.Forms.Button gradeDelete;
        private System.Windows.Forms.Button gradeUorC;
        private System.Windows.Forms.TextBox grade;
        private System.Windows.Forms.TextBox courseNu;
        private System.Windows.Forms.TextBox stunumber;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.DataGridView dataGridView3;
        private System.Windows.Forms.ComboBox subject;
        private System.Windows.Forms.ComboBox sexKind;
        private System.Windows.Forms.DataGridViewTextBoxColumn 学号;
        private System.Windows.Forms.DataGridViewTextBoxColumn 姓名;
        private System.Windows.Forms.DataGridViewTextBoxColumn 性别;
        private System.Windows.Forms.DataGridViewTextBoxColumn 专业;
        private System.Windows.Forms.DataGridViewTextBoxColumn 课程号;
        private System.Windows.Forms.DataGridViewTextBoxColumn 课程名;
        private System.Windows.Forms.DataGridViewTextBoxColumn 学分;
        private System.Windows.Forms.DataGridViewTextBoxColumn 学号1;
        private System.Windows.Forms.DataGridViewTextBoxColumn 姓名1;
        private System.Windows.Forms.DataGridViewTextBoxColumn 课程号1;
        private System.Windows.Forms.DataGridViewTextBoxColumn 课程名1;
        private System.Windows.Forms.DataGridViewTextBoxColumn 分数;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
        private System.Windows.Forms.ToolStripMenuItem 重置密码ToolStripMenuItem;
    }
}