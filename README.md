# portalSite
zytm公司内部门户网站，包含以下功能模块：  
1.新闻动态  
2.通知公告  
3.培训专栏   
4.制度文档  
5.项目进度  
6.质量专栏  
7.荣誉中亚  
8.知识共享    
# 前端  
https://github.com/maokofan/portalSite_UI_vue  
# 部分接口说明
## 新闻管理
### 1.新闻列表获取  
**接口**  
/news/getNews  
**方法**  
POST  
**返回字段**  
id, imageid,imageUrl, titile, ntid, newsTName, author, publishdate  
**字段解释**  
    id: 新闻id  
    imgeid: 轮播图片文件id  
    imageUrl: 新闻图片url地址，加上服务端地址和端口直接获取，例如：http://127.0.0.1:8289/imageUrl  
    title: 新闻标题  
    ntid： 新闻类型id  
    newsTName: 新闻类型名称  
    author: 作者，发布者  
    publishdate： 发布日期  
