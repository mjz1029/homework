#include <stdio.h>
#define SIZE 10
int main()
{
    int arr[SIZE] = {5, 7, 12, 14, 16, 23, 26, 31, 46, 55};
    int key, low = 0, high = SIZE - 1;
    int mid, index = -1;
    printf("please enter a search number: ");
    scanf("%d", &key);
    while (low <= high)
    {
        mid = (low + high) / 2;
        if (arr[mid] == key)
        {
            index = mid+1;
            break;
        }
        if (arr[mid] < key)
            low = mid + 1;
        else
            high = mid - 1;
    }
    if (index >= 0)
        printf("Array lookup location: No.%d\n", index);
    else
        printf("Not found!\n");
    return 0;
}