#include <iostream>
#include <windows.h>
#include <stdlib.h>
#include <cstring>
using namespace std;
//GB2312
class Student
{
public:
    Student(string name, int num, string sex, int year, int month, string major, string schoolClass, string address, int domitoryNum)
    {
        this->num = num;
        this->sex = sex;
        this->year = year;
        this->month = month;
        this->major = major;
        this->schoolClass = schoolClass;
        this->address = address;
        this->domitoryNum = domitoryNum;
    }

    Student() {}

    Student *next; //指向下一个结点

    int num;     //学号
    string name; //姓名
    string sex;  //性别
    int year;
    int month;
    string major;       //专业
    string schoolClass; //班级
    string address;     //住址
    int domitoryNum;    //宿舍号
};

bool checkVoidFile() //检查student.txt是否为空
{
    FILE *fp;
    fp = fopen("student.txt", "r+");
    char ch;
    ch = fgetc(fp); //读取一个字符
    fclose(fp);
    if (ch == EOF)
        return true; //如果读取到的字符是文件结束标志那就返回true，代表空
    return false;
}

bool inputStudent(Student *head) //录入学生信息
{
    FILE *fp;
    Student newStudent;
    Student *p;
    p = head;
    if (p->next == NULL)
    {
        cout << "NULL\n";
    }
input_loop:
    fp = fopen("student.txt", "a+");
    system("cls");
    cout << "录入学生信息\n";
inputStudentNum:
    cout << "输入学生学号：";
    cin >> newStudent.num;
    while (p->next != NULL)
    {
        if (newStudent.num == p->num)
        {
            cout << "该学号已经存在，请核对后重新输入！\n";
            system("pause");
            goto inputStudentNum;
        }
        p = p->next;
    }
    p = head;

    cout << "请输入学生姓名：";
    cin >> newStudent.name;
    cout << "请输入学生的性别：";
    cin >> newStudent.sex;
    cout << "请输入学生的出生年：";
    cin >> newStudent.year;
    cout << "请输入学生的出生月：";
    cin >> newStudent.month;
    cout << "请输入学生所在专业：";
    cin >> newStudent.major;
    cout << "请输入学生所在班级：";
    cin >> newStudent.schoolClass;
    cout << "请输入学生的家庭住址：";
    cin >> newStudent.address;
    cout << "请输入学生的宿舍号码：";
    cin >> newStudent.domitoryNum;
    newStudent.next = NULL;

    fprintf(fp, "%d ", newStudent.num);
    fprintf(fp, "%s\n", newStudent.name.c_str());
    fprintf(fp, "%s\n", newStudent.sex.c_str());
    fprintf(fp, "%d %d\n", newStudent.year, newStudent.month);
    fprintf(fp, "%s\n", newStudent.major.c_str());
    fprintf(fp, "%s\n", newStudent.schoolClass.c_str());
    fprintf(fp, "%s\n", newStudent.address.c_str());
    fprintf(fp, "%d\n", newStudent.domitoryNum);
    fclose(fp);

    cout << "录入成功！\n";
    cout << "录入的学生信息如下：\n";
    cout << "学号：" << newStudent.num << endl;
    cout << "姓名：" << newStudent.name << endl;
    cout << "性别：" << newStudent.sex << endl;
    cout << "出生年月：" << newStudent.year << " " << newStudent.month << endl;
    cout << "专业：" << newStudent.major << endl;
    cout << "班级：" << newStudent.schoolClass << endl;
    cout << "住址：" << newStudent.address << endl;
    cout << "宿舍号：" << newStudent.domitoryNum << endl;
    cout << endl;
    cout << "是否继续录入？(1.是/2.否):";
    char command;
    cin >> command;
    if (command == '1')
    {
        goto input_loop;
    }
    else
    {
        system("pause");
        return true;
    }
}

Student *makeLinklist()
{
    Student *head = new Student();
    head->next = NULL;
    FILE *fp;
    fp = fopen("student.txt", "r+");
    if (fp == NULL)
        fp = fopen("student.txt", "w+"); //如果不存在，创建文件
    if (checkVoidFile())                 //调用函数检查数据库是否为空
    {
        printf("学生数据库为空\n");
        return head;
    }
    Student *p = head;
    char temp;
    while (!feof(fp))
    {
        Student *newStudent = new Student();
        fscanf(fp, "%d", &newStudent->num);
        temp = fgetc(fp);
        fscanf(fp, "%[^\n]%*c", newStudent->name.c_str()); //为了与C兼容，在C中没有string类型，故必须通过string类对象的成员函数c_str()把string对象转换成C中的字符串样式。
        fscanf(fp, "%[^\n]%*c", newStudent->sex.c_str());
        fscanf(fp, "%d", &newStudent->year);
        temp = fgetc(fp); //读取空格
        fscanf(fp, "%d", &newStudent->month);
        temp = fgetc(fp); //读取回车
        fscanf(fp, "%[^\n]%*c", newStudent->major.c_str());
        fscanf(fp, "%[^\n]%*c", newStudent->schoolClass.c_str());
        fscanf(fp, "%[^\n]%*c", newStudent->address.c_str());
        fscanf(fp, "%d", &newStudent->domitoryNum);
        temp = fgetc(fp); //读取回车

        newStudent->next = NULL;
        p->next = newStudent;
        p = p->next;
    }
    fclose(fp);
    return head;
}

void showMessage(Student *t)
{
    system("cls");
    t = t->next;
    while (t->next != NULL)
    {
        cout << "学号：" << t->num << endl;
        cout << "姓名：" << t->name.c_str() << endl;
        cout << "性别：" << t->sex.c_str() << endl;
        cout << "出生年月：" << t->year << " " << t->month << endl;
        cout << "专业：" << t->major.c_str() << endl;
        cout << "班级：" << t->schoolClass.c_str() << endl;
        cout << "住址：" << t->address.c_str() << endl;
        cout << "宿舍号：" << t->domitoryNum << endl;
        cout << endl;
        t = t->next;
    }
    system("pause");
}

void overrideFile(Student *head)
{
    FILE *fp;
    fp = fopen("student.txt", "w");
    Student *p = head->next;
    while (p->next != NULL)
    {
        fprintf(fp, "%d ", p->num);
        fprintf(fp, "%s\n", p->name.c_str());
        fprintf(fp, "%s\n", p->sex.c_str());
        fprintf(fp, "%d %d\n", p->year, p->month);
        fprintf(fp, "%s\n", p->major.c_str());
        fprintf(fp, "%s\n", p->schoolClass.c_str());
        fprintf(fp, "%s\n", p->address.c_str());
        fprintf(fp, "%d\n", p->domitoryNum);
        p = p->next;
    }
    fclose(fp);
}

Student *searchStudentNum(Student *head, int studentNum)
{
    Student *p = head->next;
    if (p == NULL)
    {
        return NULL;
    }
    while (p->next != NULL)
    {
        if (p->num == studentNum)
            return p;
        p = p->next;
    }
    return NULL;
}

void searchStudentName(Student *head, string studentName)
{
    bool flag = false;
    Student *p = head->next;
    if (p == NULL)
    {
        cout << "学生数据库为空！\n";
        return;
    }
    while (p->next != NULL)
    {
        if (strcmp(p->name.c_str(), studentName.c_str()) == 0)
        {
            flag = true;
            cout << endl;
            cout << "学号：" << p->num << endl;
            cout << "姓名：" << p->name.c_str() << endl;
            cout << "性别：" << p->sex.c_str() << endl;
            cout << "出生年月：" << p->year << " " << p->month << endl;
            cout << "专业：" << p->major.c_str() << endl;
            cout << "班级：" << p->schoolClass.c_str() << endl;
            cout << "住址：" << p->address.c_str() << endl;
            cout << "宿舍号：" << p->domitoryNum << endl;
            cout << endl;
        }
        p = p->next;
    }
    if (!flag)
    {
        cout << "未能查询到该学生！\n";
    }
}

int main()
{
    string key = "admin";
    while (1)
    {
    loop_mainFrame:
        system("cls");
        cout << "欢迎使用学生管理系统！\n"
             << "1.录入学生信息\n"
             << "2.显示学生信息记录\n"
             << "3.查找学生信息\n"
             << "4.修改学生信息\n"
             << "请输入要执行的指令：";
        int command1;
        cin >> command1;
        Student *head = makeLinklist();
        string studentName; //用于按学生姓名查找学生信息，定义在switch中会报错
        string keyin;
        switch (command1)
        {
        case 1:
            inputStudent(head);
            goto loop_mainFrame;
            break;
        case 2:
            showMessage(head);
            goto loop_mainFrame;
            break;
        case 3:
        search_loop:
            system("cls");
            cout << "查询学生信息\n";
            cout << "1.按学号查找\n";
            cout << "2.按姓名查找\n";
            cout << "3.返回\n";
            int command2;
            cout << "请输入要执行的指令：";
            cin >> command2;
            Student *t;
            switch (command2)
            {
            case 1:
            searchByNum:
                system("cls");
                cout << "按学号查找学生信息\n";
                cout << "请输入要查询的学生学号:";
                int studentNum;
                cin >> studentNum;
                t = searchStudentNum(head, studentNum);
                if (t != NULL)
                {
                    cout << endl;
                    cout << "该学生信息如下：\n";
                    cout << "学号：" << t->num << endl;
                    cout << "姓名：" << t->name.c_str() << endl;
                    cout << "性别：" << t->sex.c_str() << endl;
                    cout << "出生年月：" << t->year << " " << t->month << endl;
                    cout << "专业：" << t->major.c_str() << endl;
                    cout << "班级：" << t->schoolClass.c_str() << endl;
                    cout << "住址：" << t->address.c_str() << endl;
                    cout << "宿舍号：" << t->domitoryNum << endl;
                    cout << endl;
                    cout << "是否要继续查询？（1.是/2.否）:";
                    int command;
                    cin >> command;
                    if (command == 1)
                    {
                        goto searchByNum;
                    }
                    else
                    {
                        system("pause");
                        goto loop_mainFrame;
                    }
                }
                break;
            case 2:
            searchByName:
                system("cls");
                cout << "按姓名查找学生信息\n";
                cout << "请输入要查询的学生姓名:";
                cin >> studentName;
                searchStudentName(head, studentName);
                cout << "是否要继续查询？（1.是/2.否）:";
                int command;
                cin >> command;
                if (command == 1)
                {
                    goto searchByName;
                }
                else
                {
                    system("pause");
                    goto loop_mainFrame;
                }
            default:
                cout << "输入有误，请重新输入！\n";
                system("pause");
                goto search_loop;
                break;
            }
            goto loop_mainFrame;
            break;
        case 4:
            system("cls");
            cout << "修改学生信息\n";
            cout << "请输入密码：";
            cin >> keyin;
            if (keyin == key)
            {
            modifyInformation:
                system("cls");
                cout << "\n请输入要修改学生的学号:";
                int StudentNum;
                cin >> StudentNum;
                t = searchStudentNum(head, StudentNum);
                if (t != NULL)
                {
                modify:
                    system("cls");
                    cout << "\n请输入要修改的信息\n"
                         << "1.出生年\n"
                         << "2.出生月\n"
                         << "3.专业\n"
                         << "4.班级\n"
                         << "5.住址\n"
                         << "6.宿舍号\n"
                         << "7.取消\n";
                    int command;
                    cin >> command;
                    int command2;
                    switch (command)
                    {
                    case 1:
                        cout << "\n请输入学生的出生年：";
                        cin >> t->year;
                        overrideFile(head);
                        cout << "是否要继续修改？（1.是/2.否）：";
                        cin >> command2;
                        if (command2 == 1)
                        {
                            goto modify;
                        }
                        else
                        {
                            goto loop_mainFrame;
                        }

                    case 2:
                        cout << "\n请输入学生的出生月：";
                        cin >> t->month;
                        overrideFile(head);
                        cout << "是否要继续修改？（1.是/2.否）：";
                        cin >> command2;
                        if (command2 == 1)
                        {
                            goto modify;
                        }
                        else
                        {
                            goto loop_mainFrame;
                        }
                    case 3:
                        cout << "\n请输入学生的专业：";
                        cin >> t->major;
                        overrideFile(head);
                        cout << "是否要继续修改？（1.是/2.否）：";
                        cin >> command2;
                        if (command2 == 1)
                        {
                            goto modify;
                        }
                        else
                        {
                            goto loop_mainFrame;
                        }
                    case 4:
                        cout << "\n请输入学生的班级：";
                        cin >> t->schoolClass;
                        overrideFile(head);
                        cout << "是否要继续修改？（1.是/2.否）：";
                        cin >> command2;
                        if (command2 == 1)
                        {
                            goto modify;
                        }
                        else
                        {
                            goto loop_mainFrame;
                        }
                    case 5:
                        cout << "\n请输入学生的住址：";
                        cin >> t->address;
                        overrideFile(head);
                        cout << "是否要继续修改？（1.是/2.否）：";
                        cin >> command2;
                        if (command2 == 1)
                        {
                            goto modify;
                        }
                        else
                        {
                            goto loop_mainFrame;
                        }
                    case 6:
                        cout << "\n请输入学生的宿舍号：";
                        cin >> t->domitoryNum;
                        overrideFile(head);
                        cout << "是否要继续修改？（1.是/2.否）：";
                        cin >> command2;
                        if (command2 == 1)
                        {
                            goto modify;
                        }
                        else
                        {
                            goto loop_mainFrame;
                        }
                    case 7:
                        goto loop_mainFrame;
                    }
                }
                else
                {
                    cout << "\n未查询到该学生！\n";
                    cout << "是否继续修改？（1.是/2.否）:";
                    int command;
                    cin >> command;
                    if (command == 1)
                    {
                        goto modifyInformation;
                    }
                    else
                    {
                        goto loop_mainFrame;
                    }
                }
            }
        default:
            cout << "输入有误，请重新输入！\n";
            system("pause");
            goto loop_mainFrame;
            break;
        }
    }
}