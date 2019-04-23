<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../layui/css/layui.css?t=1554901098009" media="all">
    <style type="text/css">
        .container{
            width: 420px;
            height: 260px;
            min-height: 260px;
            max-height: 320px;
            position: absolute;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            margin: auto;
            padding: 20px;
            z-index: 130;
            border-radius: 8px;
            background-color: #fff;
            box-shadow: 0 10px 200px rgba(68, 235, 187, 0.5);
            font-size: 16px;
        }
        .close{
            background-color: white;
            border: none;
            font-size: 18px;
            margin-left: 410px;
            margin-top: -10px;
        }

        .layui-input{
            border-radius: 5px;
            width: 300px;
            height: 40px;
            font-size: 15px;
        }
        .layui-form-item{
            margin-left: -20px;
        }
        #logoid{
            margin-top: -16px;
            padding-left:150px;
            padding-bottom: 15px;
        }
        .layui-btn{
            margin-left: -50px;
            border-radius: 5px;
            width: 350px;
            height: 40px;
            font-size: 15px;
        }
        .verity{
            width: 120px;
        }
        .font-set{
            font-size: 13px;
            text-decoration: none;
            margin-left: 120px;
        }
        a:hover{
            text-decoration: underline;
        }
        body{
            background-color: #23262E;
        }

    </style>

    <#--<script>-->
        <#--&lt;#&ndash;<#if (message??)>&ndash;&gt;-->
            <#--alert(${message!""});-->
        <#--&lt;#&ndash;</#if>&ndash;&gt;-->
    <#--</script>-->

</head>
<body>
<form class="layui-form" action="/loginPost" method="post">
    <div class="container">
    <div class="layui-form-mid layui-word-aux">
        <p id="logoid" height="35">饿了么商家管理系统</p>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="account" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密 &nbsp;&nbsp;码</label>
        <div class="layui-input-inline">
            <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
        <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
    </div>
    <#--<div class="layui-form-item">-->
    <#--<label class="layui-form-label">验证码</label>-->
    <#--<div class="layui-input-inline">-->
    <#--<input type="text" name="title" required  lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input verity">-->
    <#--</div>-->
    <#--<div class="layui-form-mid layui-word-aux">辅助文字</div>-->

    <#--</div>-->
    <#--<div class="layui-form-item">-->
    <#--<label class="layui-form-label">记住密码</label>-->
    <#--<div class="layui-input-block">-->
    <#--<input type="checkbox" name="close" lay-skin="switch" lay-text="ON|OFF">-->
    <#--</div>-->
    <#--</div>-->
    <div class="layui-form-item">
        <div class="layui-input-inline">
            <a class="font-set">${message!""}</a>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">登陆</button>
        </div>
    </div>

    </div>
</form>
<script src="../layui/layui.js?t=1554901098009" charset="utf-8"></script>
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //日期
        /* laydate.render({
           elem: '#date'
         });
         laydate.render({
           elem: '#date1'
         });

         //创建一个编辑器
         var editIndex = layedit.build('LAY_demo_editor');

         //自定义验证规则
         form.verify({
           title: function(value){
             if(value.length < 5){
               return '标题至少得5个字符啊';
             }
           }
           ,pass: [
             /^[\S]{6,12}$/
             ,'密码必须6到12位，且不能出现空格'
           ]
           ,content: function(value){
             layedit.sync(editIndex);
           }
         });

         //监听指定开关
         form.on('switch(switchTest)', function(data){
           layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
             offset: '6px'
           });
           layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
         });*/

        //监听提交
        form.on('submit(demo1)', function(data){
            layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })
            return false;
        });


    });
</script>
</body>
</html>