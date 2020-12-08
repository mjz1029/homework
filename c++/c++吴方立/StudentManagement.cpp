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

    Student *next; //ָ����һ�����

    int num;     //ѧ��
    string name; //����
    string sex;  //�Ա�
    int year;
    int month;
    string major;       //רҵ
    string schoolClass; //�༶
    string address;     //סַ
    int domitoryNum;    //�����
};

bool checkVoidFile() //���student.txt�Ƿ�Ϊ��
{
    FILE *fp;
    fp = fopen("student.txt", "r+");
    char ch;
    ch = fgetc(fp); //��ȡһ���ַ�
    fclose(fp);
    if (ch == EOF)
        return true; //�����ȡ�����ַ����ļ�������־�Ǿͷ���true�������
    return false;
}

bool inputStudent(Student *head) //¼��ѧ����Ϣ
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
    cout << "¼��ѧ����Ϣ\n";
inputStudentNum:
    cout << "����ѧ��ѧ�ţ�";
    cin >> newStudent.num;
    while (p->next != NULL)
    {
        if (newStudent.num == p->num)
        {
            cout << "��ѧ���Ѿ����ڣ���˶Ժ��������룡\n";
            system("pause");
            goto inputStudentNum;
        }
        p = p->next;
    }
    p = head;

    cout << "������ѧ��������";
    cin >> newStudent.name;
    cout << "������ѧ�����Ա�";
    cin >> newStudent.sex;
    cout << "������ѧ���ĳ����꣺";
    cin >> newStudent.year;
    cout << "������ѧ���ĳ����£�";
    cin >> newStudent.month;
    cout << "������ѧ������רҵ��";
    cin >> newStudent.major;
    cout << "������ѧ�����ڰ༶��";
    cin >> newStudent.schoolClass;
    cout << "������ѧ���ļ�ͥסַ��";
    cin >> newStudent.address;
    cout << "������ѧ����������룺";
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

    cout << "¼��ɹ���\n";
    cout << "¼���ѧ����Ϣ���£�\n";
    cout << "ѧ�ţ�" << newStudent.num << endl;
    cout << "������" << newStudent.name << endl;
    cout << "�Ա�" << newStudent.sex << endl;
    cout << "�������£�" << newStudent.year << " " << newStudent.month << endl;
    cout << "רҵ��" << newStudent.major << endl;
    cout << "�༶��" << newStudent.schoolClass << endl;
    cout << "סַ��" << newStudent.address << endl;
    cout << "����ţ�" << newStudent.domitoryNum << endl;
    cout << endl;
    cout << "�Ƿ����¼�룿(1.��/2.��):";
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
        fp = fopen("student.txt", "w+"); //��������ڣ������ļ�
    if (checkVoidFile())                 //���ú���������ݿ��Ƿ�Ϊ��
    {
        printf("ѧ�����ݿ�Ϊ��\n");
        return head;
    }
    Student *p = head;
    char temp;
    while (!feof(fp))
    {
        Student *newStudent = new Student();
        fscanf(fp, "%d", &newStudent->num);
        temp = fgetc(fp);
        fscanf(fp, "%[^\n]%*c", newStudent->name.c_str()); //Ϊ����C���ݣ���C��û��string���ͣ��ʱ���ͨ��string�����ĳ�Ա����c_str()��string����ת����C�е��ַ�����ʽ��
        fscanf(fp, "%[^\n]%*c", newStudent->sex.c_str());
        fscanf(fp, "%d", &newStudent->year);
        temp = fgetc(fp); //��ȡ�ո�
        fscanf(fp, "%d", &newStudent->month);
        temp = fgetc(fp); //��ȡ�س�
        fscanf(fp, "%[^\n]%*c", newStudent->major.c_str());
        fscanf(fp, "%[^\n]%*c", newStudent->schoolClass.c_str());
        fscanf(fp, "%[^\n]%*c", newStudent->address.c_str());
        fscanf(fp, "%d", &newStudent->domitoryNum);
        temp = fgetc(fp); //��ȡ�س�

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
        cout << "ѧ�ţ�" << t->num << endl;
        cout << "������" << t->name.c_str() << endl;
        cout << "�Ա�" << t->sex.c_str() << endl;
        cout << "�������£�" << t->year << " " << t->month << endl;
        cout << "רҵ��" << t->major.c_str() << endl;
        cout << "�༶��" << t->schoolClass.c_str() << endl;
        cout << "סַ��" << t->address.c_str() << endl;
        cout << "����ţ�" << t->domitoryNum << endl;
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
        cout << "ѧ�����ݿ�Ϊ�գ�\n";
        return;
    }
    while (p->next != NULL)
    {
        if (strcmp(p->name.c_str(), studentName.c_str()) == 0)
        {
            flag = true;
            cout << endl;
            cout << "ѧ�ţ�" << p->num << endl;
            cout << "������" << p->name.c_str() << endl;
            cout << "�Ա�" << p->sex.c_str() << endl;
            cout << "�������£�" << p->year << " " << p->month << endl;
            cout << "רҵ��" << p->major.c_str() << endl;
            cout << "�༶��" << p->schoolClass.c_str() << endl;
            cout << "סַ��" << p->address.c_str() << endl;
            cout << "����ţ�" << p->domitoryNum << endl;
            cout << endl;
        }
        p = p->next;
    }
    if (!flag)
    {
        cout << "δ�ܲ�ѯ����ѧ����\n";
    }
}

int main()
{
    string key = "admin";
    while (1)
    {
    loop_mainFrame:
        system("cls");
        cout << "��ӭʹ��ѧ������ϵͳ��\n"
             << "1.¼��ѧ����Ϣ\n"
             << "2.��ʾѧ����Ϣ��¼\n"
             << "3.����ѧ����Ϣ\n"
             << "4.�޸�ѧ����Ϣ\n"
             << "������Ҫִ�е�ָ�";
        int command1;
        cin >> command1;
        Student *head = makeLinklist();
        string studentName; //���ڰ�ѧ����������ѧ����Ϣ��������switch�лᱨ��
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
            cout << "��ѯѧ����Ϣ\n";
            cout << "1.��ѧ�Ų���\n";
            cout << "2.����������\n";
            cout << "3.����\n";
            int command2;
            cout << "������Ҫִ�е�ָ�";
            cin >> command2;
            Student *t;
            switch (command2)
            {
            case 1:
            searchByNum:
                system("cls");
                cout << "��ѧ�Ų���ѧ����Ϣ\n";
                cout << "������Ҫ��ѯ��ѧ��ѧ��:";
                int studentNum;
                cin >> studentNum;
                t = searchStudentNum(head, studentNum);
                if (t != NULL)
                {
                    cout << endl;
                    cout << "��ѧ����Ϣ���£�\n";
                    cout << "ѧ�ţ�" << t->num << endl;
                    cout << "������" << t->name.c_str() << endl;
                    cout << "�Ա�" << t->sex.c_str() << endl;
                    cout << "�������£�" << t->year << " " << t->month << endl;
                    cout << "רҵ��" << t->major.c_str() << endl;
                    cout << "�༶��" << t->schoolClass.c_str() << endl;
                    cout << "סַ��" << t->address.c_str() << endl;
                    cout << "����ţ�" << t->domitoryNum << endl;
                    cout << endl;
                    cout << "�Ƿ�Ҫ������ѯ����1.��/2.��:";
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
                cout << "����������ѧ����Ϣ\n";
                cout << "������Ҫ��ѯ��ѧ������:";
                cin >> studentName;
                searchStudentName(head, studentName);
                cout << "�Ƿ�Ҫ������ѯ����1.��/2.��:";
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
                cout << "�����������������룡\n";
                system("pause");
                goto search_loop;
                break;
            }
            goto loop_mainFrame;
            break;
        case 4:
            system("cls");
            cout << "�޸�ѧ����Ϣ\n";
            cout << "���������룺";
            cin >> keyin;
            if (keyin == key)
            {
            modifyInformation:
                system("cls");
                cout << "\n������Ҫ�޸�ѧ����ѧ��:";
                int StudentNum;
                cin >> StudentNum;
                t = searchStudentNum(head, StudentNum);
                if (t != NULL)
                {
                modify:
                    system("cls");
                    cout << "\n������Ҫ�޸ĵ���Ϣ\n"
                         << "1.������\n"
                         << "2.������\n"
                         << "3.רҵ\n"
                         << "4.�༶\n"
                         << "5.סַ\n"
                         << "6.�����\n"
                         << "7.ȡ��\n";
                    int command;
                    cin >> command;
                    int command2;
                    switch (command)
                    {
                    case 1:
                        cout << "\n������ѧ���ĳ����꣺";
                        cin >> t->year;
                        overrideFile(head);
                        cout << "�Ƿ�Ҫ�����޸ģ���1.��/2.�񣩣�";
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
                        cout << "\n������ѧ���ĳ����£�";
                        cin >> t->month;
                        overrideFile(head);
                        cout << "�Ƿ�Ҫ�����޸ģ���1.��/2.�񣩣�";
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
                        cout << "\n������ѧ����רҵ��";
                        cin >> t->major;
                        overrideFile(head);
                        cout << "�Ƿ�Ҫ�����޸ģ���1.��/2.�񣩣�";
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
                        cout << "\n������ѧ���İ༶��";
                        cin >> t->schoolClass;
                        overrideFile(head);
                        cout << "�Ƿ�Ҫ�����޸ģ���1.��/2.�񣩣�";
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
                        cout << "\n������ѧ����סַ��";
                        cin >> t->address;
                        overrideFile(head);
                        cout << "�Ƿ�Ҫ�����޸ģ���1.��/2.�񣩣�";
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
                        cout << "\n������ѧ��������ţ�";
                        cin >> t->domitoryNum;
                        overrideFile(head);
                        cout << "�Ƿ�Ҫ�����޸ģ���1.��/2.�񣩣�";
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
                    cout << "\nδ��ѯ����ѧ����\n";
                    cout << "�Ƿ�����޸ģ���1.��/2.��:";
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
            cout << "�����������������룡\n";
            system("pause");
            goto loop_mainFrame;
            break;
        }
    }
}