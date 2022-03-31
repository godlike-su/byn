# byn

#### common介绍
封装了自定义exception异常，全局统一异常处理

封装了从网关取得的session，通过调用SessionUser，可以取得用户的登录信息id，用户名，session。
## 打算封装常用工具
UrlParse：文件解析，文件头请求判断

SnowFlakeUtil: 封装hutoll,hutoll通过雪花算法生成id

ObjectTransform：转换entity实体类,例如User转换成UserVO
