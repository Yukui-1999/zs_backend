import re
import csv
import pandas as pd
rows=-2 #统计行数,去掉前两行,为了去掉前100行和后100行
flag=0
with open('221126184101_1.txt',encoding='utf-8') as file:
    for ann in file.readlines():
        rows+=1
with open('221126184101_1.txt',encoding='utf-8') as file:
    str=file.readline()
    str=file.readline()
    str=str.strip('\n')
    with open('test.csv','w') as f:
        head=re.split('[\s]',str)
        writer = csv.writer(f,quoting=csv.QUOTE_ALL)
        writer.writerow(head)
        for ann in file.readlines():
            flag+=1
            if flag>=100 and flag<=rows-100:
                ann = ann.strip('\n')       #去除文本中的换行符
                row = re.split('[\s]',ann)  # 将一个整体切割成列表
                row.remove('')
                writer.writerow(row)
data = pd.read_csv("test.csv")
print(data.head())
data1=data.drop(labels=['address', 'T(°)','Voltage(v)'],axis=1)
data1.to_csv("result.csv",index=False)
print(data1.head())