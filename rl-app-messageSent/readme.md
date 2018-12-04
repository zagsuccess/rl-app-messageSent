调查管理模块
对通报、督导检查通报、每周动态、月度考核简报进行操作
添加：对调查事件进行编辑录入，具备文件上传功能；
删除：实现对调查事件进行删除操作；
查询详情：查询单个调查事件的详细内容；
查询：实现通过日期或类型分页查询；
修改：实现对调查事件详细内容的修改；







2018年9月15日版：


		解决的问题：
			1.首页现实调查报告类型的前十条标题现实以及点击标题现实内容；
			2.首页对正在巡河人数的动态显示；
			3.新增编辑清空上次输入内容；

		修改的文件：
			前端部分：
				1.bulletin-add.js
				2.bulletin-list.js
				3.mainBody.html
				4.mainBody.js
			
			后端部分：
				1.BulletinController.java
				2.BulletinMapper.xml
				3.BulletinServiceImpl.java
				4.BulletinMapper.java
				5.BulletinService.java