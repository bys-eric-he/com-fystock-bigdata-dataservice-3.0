基于SpringCloud-alibaba的oauth2分布式项目，
使用nacos+gateway+spring security oauth2+sentinel
实现 认证服务器+资源服务器
也集成了logstash收集日志

基于 OAuth2.0 distributed project of springcloud-alibaba, 
使用 nacos+gateway+spring security oauth2+sentinel 
实现 Authentication server + Resource server 分离.

启动前安装好nacos，nacos做注册中心和配置中心，以及sentinel服务1.8.0版本。
根据cloud_oauth2_db.sql创建数据库，插入数据。

1. com-fystock-dataservice-oauth2 认证服务器
2. com-fystock-dataservice-common 公共类
3. com-fystock-dataservice-gateway 网关
4. com-fystock-dataservice-api-9001 资源服务器9001
5. com-fystock-dataservice-api-9002 资源服务器9002

授权码存储模式为jdbc(mysql),之前基于内存。导入项目下的cloud_oauth2_db.sql文件到数据库，数据库名称为cloud_oauth2_db.
客户端配置存储也使用jdbc(mysql),之前也是基于内存，可以阅读代码还原为内存模式。
令牌生成策略为JWT形式，使用JWT方式可以使资源服务器不需要调用验证服务器验证，增强服务稳定性和可靠性。
密码生成和验证方式为PasswordEncoder方式，有效避免密码安全问题。

1.####----------- 获取token--------------------
http://127.0.0.1:3344/oauth/token 
Authorization:选择Basic Auth,然后填入
    Username:client-1
    Password:secret
在表单中添加：
    grant_type:password
    username:heyong
    password:123456
    scope:ROLE_ADMIN

    
请求地址：http://127.0.0.1:3344/oauth/token?grant_type=password&scope=ROLE_FYSTOCK&client_id=client-1&client_secret=secret&username=heyong&password=123456
参数说明：
client_id：客户端准入标识。
client_secret：客户端秘钥。
grant_type：授权类型，填写password表示密码模式
username：资源拥有者用户名。
password：资源拥有者密码。


2.####----------------- 验证token------------------
http://127.0.0.1:3344/oauth/check_token
在表单中添加：
token:xxxxx

请求地址：http://127.0.0.1:3344/oauth/check_token?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UtMSJdLCJ1c2VyX25hbWUiOiJoZXlvbmciLCJzY29wZSI6WyJST0xFX0ZZU1RPQ0siXSwiZXhwIjoxNjE2NDgwMzcxLCJhdXRob3JpdGllcyI6WyJGWVNUT0NLIl0sImp0aSI6ImE1OTU3NzEwLWNiNDctNGRkZS04NTJhLTM1NDRmMTY4NDU0ZCIsImNsaWVudF9pZCI6ImNsaWVudC0xIn0.dn8GNh4Ukce1lWkZoFi4ttaz6W9hCcuR4pe60vzTg4M

3.#### ------------------获取接口数据---------------------
http://127.0.0.1:3344/payment/get/6
Authorization:选择Bearer Token,然后填入
Token:xxxxx

4.###  ------------------插入接口数据--------------------- 
http://127.0.0.1:3344/payment/create
Authorization:选择Bearer Token,然后填入
Token:xxxxx
参数:选择body,选择raw,然后填入
{"id":5,"serial":"CAP 500ML"}
 
5.#### -------------------refreshToken--------------------
post请求，跟请求token一样的url地址：http://localhost:3344/oauth/token
post需要的参数：

grant_type ：refresh_token
client_id：分配的客户端id
client_secret：分配的客户端密码
refresh_token：值为上一次请求返回值中refresh_token的值


请求地址：http://127.0.0.1:3344/oauth/token?grant_type=refresh_token&client_id=client-1&client_secret=secret&refresh_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UtMSJdLCJ1c2VyX25hbWUiOiJoZXlvbmciLCJzY29wZSI6WyJST0xFX0ZZU1RPQ0siXSwiYXRpIjoiNzgxMGE4M2YtMjA0My00NGFmLWI5YzAtNTZkNTFhNTVmYzZkIiwiZXhwIjoxNjE2NzI5OTQzLCJhdXRob3JpdGllcyI6WyJGWVNUT0NLIl0sImp0aSI6ImIwYjRmNDI1LTVlMDMtNDE3Yi1hMDBhLWEzODk2MWJmMWJiZiIsImNsaWVudF9pZCI6ImNsaWVudC0xIn0.DeN8JOkoDPeL82wZEgxzmPjNHtmQluxhb8_KxLqkASM