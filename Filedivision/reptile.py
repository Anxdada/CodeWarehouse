# -*- coding: utf-8 -*-
#script file
 
import urllib.request
import re
import os
 
#connect to a URL 
#一页的搜索结果中url大概是200个左右
file_url = open('url.txt','ab+')
#搜索框里的东西,这块可以设置成数字好让每次搜索的结果不一样
search = '123'
url = "http://www.baidu.com/s?wd="+search
 
 
def setUrlToFile():
    website = urllib.request.urlopen(url)
    #read html code 
 
    html = website.read() 
 
    #use re.findall to get all the links 

    regex = re.compile('"((http|ftp)s?://.*?)"')    
    links = regex.findall(html.decode('utf-8'))
 
    for s in links:
       # print(s[0])
        if len(s[0]) < 256:
            file_url.write((s[0]+'\r\n').encode())
    
#收集实验数据
for i in range(0,1000):
    setUrlToFile()
 
file_url.close()
 
 
###需要重新打开再读一下
file_url = open('url.txt','r')
file_lines = len(file_url.readlines())
print("there are %d url in %s" %(file_lines,file_url))
file_url.close()